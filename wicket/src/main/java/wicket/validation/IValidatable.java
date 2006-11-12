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
package wicket.validation;


/**
 * Represents any object that can be validated
 * 
 * @param <T>
 *            type of value that will be validated
 * 
 * @author ivaynberg
 * 
 */
public interface IValidatable<T>
{
	/**
	 * @return the value to be validated
	 */
	T getValue();

	/**
	 * Reports an error against the validatable value. Multiple errors can be
	 * reported by calling this method multiple times.
	 * 
	 * @param error
	 *            reported error
	 */
	void error(IValidationError error);
}
