package com.webcrawler.unittest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.controller.WebCrawlerController;
import com.rest.model.WebCrawler;
import com.rest.model.WebCrawlerResponse;
import com.rest.service.impl.WebCrawlerServiceImpl;

public class WebCrawlerTest 
{
	private static final Logger log = LoggerFactory.getLogger(WebCrawlerController.class);

	@BeforeClass
	public static void beforeCrawl()
	{
		log.info("Starting the web crawler...");
	}
	
	@Before
	public void loadData() throws Exception
	{
		log.info("Loading the web pages...");
		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		WebCrawler webCrawler = mapper.readValue(new File(classLoader.getResource("internet.json").getFile()), WebCrawler.class);
		assertNotNull(webCrawler);
		log.info("End of loading the web pages.");
	}
	
	@Test
	public void test1() throws Exception
	{
		log.info("Running the test1...");
		WebCrawlerServiceImpl serviceImpl = new WebCrawlerServiceImpl();
		String[] errorPage = {"page-53"};
		WebCrawlerResponse response = serviceImpl.webCrawl("page-50");
		assertArrayEquals(errorPage, response.getError().toArray());
		log.info("End of runnning the test1.");
	}
	
	@After
	public void checkResponse() throws Exception
	{
		log.info("Running the checkResponse...");
		WebCrawlerServiceImpl serviceImpl = new WebCrawlerServiceImpl();
		WebCrawlerResponse response = serviceImpl.webCrawl("page-50");
		assertNotNull(response);
		log.info("End of runnning the test1.");
	}
	
	@AfterClass
	public static void afterCrawl()
	{
		log.info("End of the web crawler.");
	}
}
