package com.rest.service.impl;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.controller.WebCrawlerController;
import com.rest.model.Pages;
import com.rest.model.WebCrawler;
import com.rest.model.WebCrawlerResponse;
import com.rest.service.WebCrawlerService;

@Service
@RequestScope
public class WebCrawlerServiceImpl implements WebCrawlerService
{
	private static final Logger log = LoggerFactory.getLogger(WebCrawlerController.class);
	
	Set<String> success = new HashSet<String>();
	Set<String> skipped = new HashSet<String>();
	Set<String> error = new HashSet<String>();
	
	@Override
	public WebCrawlerResponse webCrawl(String startPage) throws Exception
	{
		log.info("Enter the method.");
		WebCrawlerResponse response = null;
		try
		{
			if(!"".equals(checkNull(startPage)))
			{
				ObjectMapper mapper = new ObjectMapper();
				ClassLoader classLoader = getClass().getClassLoader();
				WebCrawler webCrawler = mapper.readValue(new File(classLoader.getResource("internet.json").getFile()), WebCrawler.class);
				checkPageStatus(webCrawler, startPage);
				response = new WebCrawlerResponse();
				response.setSuccess(success);
				response.setSkipped(skipped);
				response.setError(error);
			}
		}
		finally 
		{
			log.info("Exiting the method.");
		}
		return response;
	}
	
	private void checkPageStatus(WebCrawler webCrawler, String page)
	{
		if (success.contains(page))
		{
			skipped.add(page);
		}
		else
		{
			List<String> links = checkPages(webCrawler.getPages(), page);
			if (links != null && links.size() > 0)
			{
				success.add(page);
				for (String link : links)
				{
					checkPageStatus(webCrawler, link.toString());
				}
			} else if (links == null)
			{
				error.add(page);
			}
		}
	}
	
	private List<String> checkPages(List<Pages> pages, String startPage)
	{
		try
		{
			if (pages != null && pages.size() > 0)
			{
				for (Pages page : pages)
				{
					if(page.getAddress().equals(startPage))
					{
						List<String> links = page.getLinks();
						return links;
					}
				}
			}
		} catch (Exception e)
		{
			log.error("Exception while checkPages : {}", e);
		}
		return null;
	}
	
	public String checkNull(String value) {
		return value == null ? "" : value;
	}
}
