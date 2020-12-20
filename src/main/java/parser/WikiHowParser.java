package parser;

import java.util.Scanner; 

public class WikiHowParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do today?");
        String input = scanner.nextLine();
        char[] suffix = input.toCharArray();
        for (int i = 0; i < suffix.length; i++) {
            if (suffix[i] == ' ') {
                suffix[i] = '+';
            }
        }
        String urlSuffix = new String(suffix);
        JSoupParser jSoupParser = new JSoupParser("https://www.wikihow.com/wikiHowTo?search=" + urlSuffix);
        
    }
}