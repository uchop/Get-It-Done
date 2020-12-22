package parser;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;


public class JSoupParser {
    private String searchBarURL;
    private String targetURL;

    //constructor which locates correct URL based on user's input
    public JSoupParser(String URL) {
        String searchBarURL = URL;
         
        try {
            Document doc = Jsoup.connect(searchBarURL).get();
            Element resultLink = doc.select("a.result_link").first();
            this.targetURL = resultLink.attr("abs:href");
            System.out.println(targetURL);
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //parse the target URL to retrieve data relevant to user goal
    public void scrapeTargetURL() {
        try {
            ArrayList<String> parsedData = new ArrayList<String>();
            Document document = Jsoup.connect(this.targetURL).get();
            Elements data = document.select("div.step");
            cleanUp(data);
            for (Element d: data) {
                parsedData.add(d.text());
            }
            printParsedData(parsedData);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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