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
package wicket.markup.parser.filter;

import java.text.ParseException;

import wicket.Component;
import wicket.markup.ComponentTag;
import wicket.markup.MarkupElement;
import wicket.markup.MarkupFragment;
import wicket.markup.MarkupParser;
import wicket.markup.parser.AbstractMarkupFilter;
import wicket.markup.parser.XmlTag;

/**
 * This is a markup inline filter. It assumes that WicketTagIdentifier has been
 * called first and search for a &lt;head&gt; tag (note: not wicket:head).
 * Provided the markup contains a &lt;body&gt; tag it will automatically prepend
 * a &lt;head&gt; tag if missing.
 * <p>
 * Note: This handler is only relevant for Pages (see
 * MarkupParser.newFilterChain())
 * 
 * @see wicket.markup.MarkupParser
 * @author Juergen Donnerstag
 */
public final class HtmlHeaderSectionHandler extends AbstractMarkupFilter
{
	private static final String HEAD = "head";

	/** The automatically assigned wicket:id to &gt;head&lt; tag */
	public static final String HEADER_ID = Component.AUTO_COMPONENT_PREFIX + "header";

	/** True if all the rest of the markup file can be ignored */
	private boolean ignoreTheRest = false;

	/** The markup parser */
	private final MarkupParser parser;

	/**
	 * Construct.
	 * 
	 * @param parser
	 *            The markup parser
	 */
	public HtmlHeaderSectionHandler(final MarkupParser parser)
	{
		this.parser = parser;
	}

	/**
	 * Get the next tag from the next MarkupFilter in the chain and search for
	 * Wicket specific tags.
	 * <p>
	 * 
	 * @see wicket.markup.parser.IMarkupFilter#nextTag()
	 * @return The next tag from markup to be processed. If null, no more tags
	 *         are available
	 */
	public MarkupElement nextTag() throws ParseException
	{
		// Get the next tag from the markup.
		// If null, no more tags are available
		final ComponentTag tag = nextComponentTag();
		if (tag == null)
		{
			return tag;
		}

		// Whatever there is left in the markup, ignore it
		if (ignoreTheRest == true)
		{
			return tag;
		}

		// if it is <head> or </head>
		if (tag.isHeadTag())
		{
			ignoreTheRest = true;

			if (tag.getId() == null)
			{
				tag.setId(HEADER_ID);
			}

			// Usually <head> is not a wicket special tag. But because we want
			// transparent header support we insert it automatically if missing
			// and while rendering its content all child components are asked if
			// they want to contribute something to the header. Thus we have to
			// handle <head> accordingly.
			tag.setInternalTag(true);
			return tag;
		}
		else if (tag.isBodyTag())
		{
			insertHeadTag();

			// <head> must always be before <body>
			ignoreTheRest = true;
			return tag;
		}

		return tag;
	}

	/**
	 * Insert <head> open and close tag (with empty body) to the current
	 * position.
	 */
	private void insertHeadTag()
	{
		// Note: only the open-tag must be a AutoComponentTag
		final ComponentTag openTag = new ComponentTag(HEAD, XmlTag.Type.OPEN);
		openTag.setId(HEADER_ID);

		final ComponentTag closeTag = new ComponentTag(HEAD, XmlTag.Type.CLOSE);
		closeTag.setOpenTag(openTag);

		// insert the tags into the markup stream
		MarkupFragment fragment = new MarkupFragment(parser.getMarkup());
		fragment.addMarkupElement(openTag);
		fragment.addMarkupElement(closeTag);
		this.parser.getCurrentMarkupFragment().addMarkupElement(fragment);
	}
}
