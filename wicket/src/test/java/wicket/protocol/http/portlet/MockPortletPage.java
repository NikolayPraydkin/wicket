/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wicket.protocol.http.portlet;

import wicket.behavior.HeaderContributor;
import wicket.markup.html.basic.Label;
import wicket.markup.html.link.Link;
import wicket.model.PropertyModel;

/**
 * Example mock portlet page.
 * 
 * @author Janne Hietam&auml;ki (jannehietamaki)
 */
public class MockPortletPage extends PortletPage
{
	private static final long serialVersionUID = 1L;

	private int linkClickCount = 0;

	/**
	 * Construct.
	 * 
	 */
	public MockPortletPage()
	{
		// Action link counts link clicks
		final Link actionLink = new Link(this, "actionLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				linkClickCount++;
			}
		};
		new Label(actionLink, "linkClickCount", new PropertyModel(this, "linkClickCount"));
		add(HeaderContributor.forCss(MockPortletPage.class, "style.css"));
	}

	/**
	 * @return Returns the linkClickCount.
	 */
	public int getLinkClickCount()
	{
		return linkClickCount;
	}

	/**
	 * @param linkClickCount
	 *            The linkClickCount to set.
	 */
	public void setLinkClickCount(final int linkClickCount)
	{
		this.linkClickCount = linkClickCount;
	}
}
