package com.parser.getitdone;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin; 

@RestController
public class WikiParserController {

    @Autowired
    private WikiParserService wikiParserService;

    @CrossOrigin
    @GetMapping("/parser")
    public ArrayList<String> scrapeWikiHow(@RequestParam(value = "userSearch") String userSearch) {
        char[] suffix = userSearch.toCharArray();
        for (int i = 0; i < suffix.length; i++) {
            if (suffix[i] == ' ') {
                suffix[i] = '+';
            }
        }
        String urlSuffix = new String(suffix);
        String targetURL = wikiParserService.findTargetURL("https://www.wikihow.com/wikiHowTo?search=" + urlSuffix);
        return wikiParserService.scrapeTargetURL(targetURL);
    }
}