package com.cyberark.conjur.cloudconfig.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import com.cyberark.conjur.cloudconfig.env.ConjurConfigParam;
import com.cyberark.conjur.cloudconfig.service.CustomPropertySourceChain;
import com.cyberark.conjur.cloudconfig.service.DefaultPropertySourceChain;

@Configuration
public class ValueProcess implements BeanPostProcessor, InitializingBean, EnvironmentAware, ApplicationContextAware {

	private static Logger logger = LoggerFactory.getLogger(ValueProcess.class);
	private ApplicationContext context;
	private ConfigurableEnvironment environment;

	private com.cyberark.conjur.cloudconfig.service.PropertyProcessorChain processorChain;
	@Autowired
	private ConjurConfigParam configParam = new ConjurConfigParam();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		System.out.println("AfterPropertiesSet");

		this.processorChain = new DefaultPropertySourceChain();
		CustomPropertySourceChain customPS = new CustomPropertySourceChain();
		processorChain.setNextChain(customPS);

		// environment.getPropertySources().addLast(new ConjurPropertySource());
		environment.getPropertySources().addLast(processorChain);

		logger.info("Property Sources>>>" + environment.getPropertySources());

	}

	@Override
	public void setEnvironment(Environment environment) {

		if (environment instanceof ConfigurableEnvironment) {

			this.environment = (ConfigurableEnvironment) environment;
			logger.info("Available environment>>>" + environment);
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;

	}

}
