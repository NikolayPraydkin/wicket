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
package wicket.ajax.markup.html;

import wicket.MarkupContainer;
import wicket.ajax.AjaxEventBehavior;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.ClientEvent;
import wicket.ajax.IAjaxCallDecorator;
import wicket.ajax.calldecorator.CancelEventIfNoAjaxDecorator;
import wicket.markup.ComponentTag;
import wicket.markup.html.link.Link;
import wicket.model.IModel;

/**
 * An ajax link that will degrade to a normal request if ajax is not available
 * or javascript is disabled
 * 
 * @param <T>
 *            The type
 * 
 * @since 1.2
 * 
 * @author Igor Vaynberg (ivaynberg)
 * 
 */
public abstract class AjaxFallbackLink<T> extends Link<T> implements IAjaxLink
{
	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 * 
	 * @param parent
	 *            The parent of this component
	 * 
	 * @param id
	 */
	public AjaxFallbackLink(MarkupContainer parent, final String id)
	{
		this(parent, id, null);
	}

	/**
	 * Construct.
	 * 
	 * @param parent
	 *            The parent of this component
	 * 
	 * @param id
	 * @param model
	 */
	public AjaxFallbackLink(MarkupContainer parent, final String id, final IModel<T> model)
	{
		super(parent, id, model);

		add(new AjaxEventBehavior(ClientEvent.CLICK)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target)
			{
				onClick(target);
			}

			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator()
			{
				return new CancelEventIfNoAjaxDecorator(AjaxFallbackLink.this
						.getAjaxCallDecorator());
			}
			
			@Override
			protected void onComponentTag(ComponentTag tag)
			{
				// only render handler if link is enabled
				if (isLinkEnabled())
				{
					super.onComponentTag(tag);
				}
			}

		});
	}

	protected IAjaxCallDecorator getAjaxCallDecorator()
	{
		return null;
	}

	/**
	 * 
	 * @see wicket.markup.html.link.Link#onClick()
	 */
	@Override
	public final void onClick()
	{
		onClick(null);
	}

	/**
	 * Callback for the onClick event. If ajax failed and this event was
	 * generated via a normal link the target argument will be null
	 * 
	 * @param target
	 *            ajax target if this linked was invoked using ajax, null
	 *            otherwise
	 */
	public abstract void onClick(final AjaxRequestTarget target);
}
