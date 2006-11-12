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
package wicket;

import wicket.markup.html.WebPage;
import wicket.util.tester.WicketTester;
import junit.framework.TestCase;

/**
 * @author jcompagner
 */
public class HomePageRedirectTest extends TestCase
{
	/**
	 * Construct.
	 * 
	 * @param name
	 */
	public HomePageRedirectTest(String name)
	{
		super(name);
	}

	/**
	 * @throws Exception
	 */
	public void testPage() throws Exception
	{
		WicketTester tester = new WicketTester(Page1.class);
		tester.getApplication().mountBookmarkablePage("/page1", Page1.class);

		tester.setupRequestAndResponse();
		tester.processRequestCycle();
	}

	/**
	 * Test page
	 */
	public static class Page1 extends WebPage
	{
		private static final long serialVersionUID = 1L;

		/**
		 * Construct.
		 */
		public Page1()
		{
		}
	}
}