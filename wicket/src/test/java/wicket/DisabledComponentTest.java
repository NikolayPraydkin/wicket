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

import wicket.markup.html.link.Link;

/**
 * @author jcompagner
 */
public class DisabledComponentTest extends WicketTestCase
{

	/**
	 * Construct.
	 * 
	 * @param name
	 */
	public DisabledComponentTest(String name)
	{
		super(name);
	}

	/**
	 * @throws Exception
	 */
	public void testEnabled() throws Exception
	{
		executeTest(DisabledComponentPage1.class, "DisabledComponentPage1a_result.html");
		Link link = ((DisabledComponentPage1)tester.getLastRenderedPage()).link;
		executedListener(DisabledComponentPage2.class, link, "DisabledComponentPage2_result.html");
	}

	/**
	 * @throws Exception
	 */
	public void testDisabled() throws Exception
	{
		executeTest(DisabledComponentPage1.class, "DisabledComponentPage1a_result.html");
		Link link = ((DisabledComponentPage1)tester.getLastRenderedPage()).link;
		link.setEnabled(false);
		executedListener(DisabledComponentPage1.class, link, "DisabledComponentPage1b_result.html");
	}

}
