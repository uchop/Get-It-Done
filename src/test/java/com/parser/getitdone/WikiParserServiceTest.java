package com.parser.getitdone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WikiParserServiceTest {

	@Autowired
	private WikiParserService wikiParserService;


	@Test
	public void constructService() {
		Assertions.assertNotNull(wikiParserService);
	}

	@Test
	public void testFindTargetURL() {
		String targetURL = "https://www.wikihow.com/Lose-Weight";
		String searchURL = "https://www.wikihow.com/wikiHowTo?search=lose+weight";
		Assertions.assertEquals(targetURL, wikiParserService.findTargetURL(searchURL));
	}
	@Test
	public void testscrapeTargetURL() {
		String scrapeURL = "https://www.wikihow.com/Lose-Weight";
		Assertions.assertNotNull(wikiParserService.scrapeTargetURL(scrapeURL));
	}

}
