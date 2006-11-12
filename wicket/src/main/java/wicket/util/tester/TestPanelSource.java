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
package wicket.util.tester;

import java.io.Serializable;

import wicket.MarkupContainer;

/**
 * A test panel factory for WicketTester
 * 
 * @author Ingram Chen
 */
public interface TestPanelSource extends Serializable
{
	/**
	 * Define a panel instance source for WicketTester. note that testing
	 * panel's componentId must use supplied <code>panelId</code>.
	 * 
	 * @param parent
	 *            Parent of the testing panel
	 * @param panelId
	 *            panelId of the testing panel
	 */
	public void getTestPanel(final MarkupContainer parent, final String panelId);
}
