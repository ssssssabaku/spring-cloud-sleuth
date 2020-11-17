/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sleuth.brave.instrument.messaging;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.config.TracingJmsListenerEndpointRegistry;

class TracingJmsBeanPostProcessor implements BeanPostProcessor {

	private final BeanFactory beanFactory;

	TracingJmsBeanPostProcessor(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return wrap(bean);
	}

	Object wrap(Object bean) {
		if (typeMatches(bean)) {
			return new TracingJmsListenerEndpointRegistry((JmsListenerEndpointRegistry) bean, this.beanFactory);
		}
		return bean;
	}

	private boolean typeMatches(Object bean) {
		return bean instanceof JmsListenerEndpointRegistry && !(bean instanceof TracingJmsListenerEndpointRegistry);
	}

}
