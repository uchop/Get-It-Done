package parser;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;


public class JSoupParser {
    private String searchBarURL;

    public JSoupParser(String URL) {
        String searchBarURL = URL;
         
        try {
            Document doc = Jsoup.connect(searchBarURL).get();
            Element resultLink = doc.select("a.result_link").first();
            String targetURL = resultLink.attr("abs:href");
            System.out.println(targetURL);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}