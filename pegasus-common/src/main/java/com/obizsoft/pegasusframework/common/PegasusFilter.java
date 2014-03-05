package com.obizsoft.pegasusframework.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.apache.wicket.protocol.http.WicketFilter;

@WebFilter(value = "/*", initParams = { @WebInitParam(name = "applicationClassName", value = "com.obizsoft.pegasusframework.common.PegasusWicketApplication"), 
		@WebInitParam(name="filterMappingUrlPattern", value="/*") })
public class PegasusFilter extends WicketFilter {
	public void 
		doFilter(ServletRequest request, 
				ServletResponse response, FilterChain chain) throws IOException, ServletException
	{	
		super.doFilter(request, response, chain);
	}
}