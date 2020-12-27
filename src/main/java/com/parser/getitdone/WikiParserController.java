package com.parser.getitdone;

// import java.util.Scanner;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 

@RestController
public class WikiParserController {

    @GetMapping("/parser")
    public ArrayList<String> scrapeWikiHow(@RequestParam(value = "userSearch") String userSearch) {
        char[] suffix = userSearch.toCharArray();
        for (int i = 0; i < suffix.length; i++) {
            if (suffix[i] == ' ') {
                suffix[i] = '+';
            }
        }
        String urlSuffix = new String(suffix);
        WikiParser wikiParser = new WikiParser("https://www.wikihow.com/wikiHowTo?search=" + urlSuffix);
        return wikiParser.scrapeTargetURL();
    }

    //*************Previous way of getting user input through terminal input********************** */
    // public String grabUserSearchQuery() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("What would you like to do today?");
    //     String input = scanner.nextLine();
    //     char[] suffix = input.toCharArray();
    //     for (int i = 0; i < suffix.length; i++) {
    //         if (suffix[i] == ' ') {
    //             suffix[i] = '+';
    //         }
    //     }
    //     return new String(suffix);
    // }
    
}