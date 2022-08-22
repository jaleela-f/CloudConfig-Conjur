package com.conjur.valueprocessor.service;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import com.conjur.valueprocessor.property.ConjurPropertySource;

@Configuration
public class ValueProcess 
		implements InitializingBean, EnvironmentAware, ApplicationContextAware {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ConfigurableEnvironment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AfterPropertiesSet");
		environment.getPropertySources().addLast(new ConjurPropertySource());
		

	}

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		this.environment = (ConfigurableEnvironment) environment;

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;

	}

	@Bean
	@Lazy
	public PropertySourcesPlaceholderConfigurer getProperyPlaceHolderConfigurer() {
		System.out.println("callng placeholder config");
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		MutablePropertySources sources = environment.getPropertySources();
		cfg.setPropertySources(sources);
		environment.setIgnoreUnresolvableNestedPlaceholders(true);
		return cfg;

	}

	

	
}
