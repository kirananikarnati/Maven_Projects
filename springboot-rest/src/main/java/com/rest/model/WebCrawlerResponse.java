package com.rest.model;

import java.util.Set;

public class WebCrawlerResponse {

	private Set<String> Success;
	private Set<String> Skipped;
	private Set<String> Error;

	public Set<String> getSuccess()
	{
		return Success;
	}

	public void setSuccess(Set<String> success)
	{
		Success = success;
	}

	public Set<String> getSkipped()
	{
		return Skipped;
	}

	public void setSkipped(Set<String> skipped)
	{
		Skipped = skipped;
	}

	public Set<String> getError()
	{
		return Error;
	}

	public void setError(Set<String> error)
	{
		Error = error;
	}
}