package com.rest.service;

import com.rest.model.WebCrawlerResponse;

public interface WebCrawlerService
{
	WebCrawlerResponse webCrawl(String page) throws Exception;
}