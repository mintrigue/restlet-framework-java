/*******************************************************************************
 * Copyright (c) 2011.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     - initial API and implementation
 *******************************************************************************/

package org.restlet.ext.osgi.di.junit.support;

import org.restlet.ext.osgi.ApplicationProvider;

/**
 * @author bhunt
 * 
 */
public class TestApplicationProvider extends ApplicationProvider
{
	@Override
	public String getAlias()
	{
		return "/";
	}
}
