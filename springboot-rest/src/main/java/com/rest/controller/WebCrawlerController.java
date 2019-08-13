package com.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.WebCrawlerResponse;
import com.rest.service.WebCrawlerService;

@RestController
public class WebCrawlerController {
	
	private static final Logger log = LoggerFactory.getLogger(WebCrawlerController.class);
	
	@Autowired
	WebCrawlerService webCrawlerService;
	
	@GetMapping("/webcrawler/{page}")
	public WebCrawlerResponse webCrawl(@PathVariable String page) throws Exception
	{
		log.info("Running the webCrawler...");
		return webCrawlerService.webCrawl(page);
	}
}
