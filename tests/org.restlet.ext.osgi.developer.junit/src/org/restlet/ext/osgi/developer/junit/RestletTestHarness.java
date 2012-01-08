/*******************************************************************************
 * Copyright (c) 2011 Bryan Hunt.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bryan Hunt - initial API and implementation
 *******************************************************************************/

package org.restlet.ext.osgi.developer.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.osgi.service.http.HttpService;
import org.osgi.service.log.LogReaderService;
import org.osgi.service.log.LogService;
import org.restlet.ext.osgi.IApplicationProvider;
import org.restlet.ext.osgi.developer.junit.bundle.Activator;
import org.restlet.ext.osgi.servlet.RestletServletService;

/**
 * @author bhunt
 * 
 */
public class RestletTestHarness
{
	public void addApplication(IApplicationProvider applicationProvider)
	{
		restletServletService.bindApplicationProvider(applicationProvider);
	}

	public LogReaderService getLogReaderService()
	{
		return logReaderService;
	}

	public void removeApplication(IApplicationProvider applicationProvider)
	{
		restletServletService.unbindApplicationProvider(applicationProvider);
	}

	public void setUp() throws InterruptedException
	{
		httpService = Activator.getInstance().getHttpService();
		assertThat(httpService, is(notNullValue()));

		logService = Activator.getInstance().getLogService();
		assertThat(logService, is(notNullValue()));

		logReaderService = Activator.getInstance().getLogReaderService();
		assertThat(logReaderService, is(notNullValue()));

		restletServletService = new RestletServletService();
		restletServletService.bindHttpService(httpService);
		restletServletService.bindLogService(logService);
	}

	public void tearDown(String applicationAlias)
	{
		try
		{
			httpService.unregister(applicationAlias);
		}
		catch (Throwable t)
		{}
	}

	private HttpService httpService;
	private static LogService logService;
	private static LogReaderService logReaderService;
	private RestletServletService restletServletService;
}
