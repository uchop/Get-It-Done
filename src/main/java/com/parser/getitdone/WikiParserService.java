package com.parser.getitdone;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class WikiParserService {

    //finds the target URL to scrape by entering user input into search bar
    public String findTargetURL(String searchBarURL) {
        String resultURL = "";
        try {
            Document doc = Jsoup.connect(searchBarURL).get();
            Element resultLink = doc.select("a.result_link").first();
            resultURL = resultLink.attr("abs:href");
            if (resultURL.contains("Category")) {
                Document categoryDoc = Jsoup.connect(resultURL).get();
                Element categoryURL = categoryDoc.select("div.cat_section a[href]").first();
                resultURL = categoryURL.attr("abs:href");
            }
            System.out.println(resultURL);
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return resultURL;
    }

    //parse the target URL to retrieve data relevant to user goal
    public ArrayList<String> scrapeTargetURL(String URL) {
        ArrayList<String> parsedData = new ArrayList<String>();
        try {
            Document document = Jsoup.connect(URL).get();
            Elements data = document.select("div.step");
            cleanUp(data);
            for (Element d: data) {
                parsedData.add(d.text());
            }
            // printParsedData(parsedData);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return parsedData;
    }

    public void cleanUp(Elements elements) {
        String tagstoRemove="sup,script,li,p,ul,ol,span";
        elements.select(tagstoRemove).remove();
    }

    public void printParsedData(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(i + " : " + arrayList.get(i));
            }
    }
}