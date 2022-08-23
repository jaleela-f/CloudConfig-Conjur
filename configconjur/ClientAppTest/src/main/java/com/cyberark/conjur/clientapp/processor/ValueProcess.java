package com.cyberark.conjur.clientapp.processor;

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
import org.springframework.core.env.PropertySource;

import com.cyberark.conjur.clientapp.core.ConjurPropertySource;
import com.cyberark.conjur.clientapp.service.CustomPropertySourceChain;
import com.cyberark.conjur.clientapp.service.DefaultPropertySourceChain;
import com.cyberark.conjur.clientapp.service.PropertyProcessorChain;


@Configuration
public class ValueProcess implements BeanPostProcessor,InitializingBean,EnvironmentAware, ApplicationContextAware {

	
	private ApplicationContext context;
	private ConfigurableEnvironment environment;
	@Autowired
	private ConjurPropertySource propertySource;
	
	private PropertyProcessorChain processorChain;
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AfterPropertiesSet");
		
		this.processorChain = new DefaultPropertySourceChain();
		CustomPropertySourceChain customPS = new CustomPropertySourceChain();
		processorChain.setNextChain(customPS);
		
		//environment.getPropertySources().addLast(new ConjurPropertySource());
		environment.getPropertySources().addLast(processorChain);
		
		System.out.println("Property Sources>>>"+environment.getPropertySources());
		

	}

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		if (environment instanceof ConfigurableEnvironment) {
			
			this.environment = (ConfigurableEnvironment) environment;
			System.out.println("Available environment>>>"+environment);
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;

	}

	/*
	 * @Bean
	 * 
	 * @Lazy public PropertySourcesPlaceholderConfigurer
	 * getProperyPlaceHolderConfigurer() {
	 * System.out.println("callng placeholder config");
	 * PropertySourcesPlaceholderConfigurer cfg = new
	 * PropertySourcesPlaceholderConfigurer(); MutablePropertySources sources =
	 * environment.getPropertySources(); cfg.setPropertySources(sources);
	 * environment.setIgnoreUnresolvableNestedPlaceholders(true); return cfg;
	 * 
	 * }
	 */

	/*@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("Bean Factory Post Processor");
		
		environment.getPropertySources().addLast(new ConjurPropertySource());
		System.out.println("Property Sources>>>"+environment.getPropertySources());
		
	}*/


	
}
