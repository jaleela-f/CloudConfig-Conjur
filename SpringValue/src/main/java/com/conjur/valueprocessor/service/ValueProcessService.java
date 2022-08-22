package com.conjur.valueprocessor.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.context.ContextLoader;

import com.conjur.valueprocessor.property.ConjurPropertySource;

//@Configuration
public class ValueProcessService
		implements BeanFactoryPostProcessor, ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;

	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("Calling Bean Factory Post Processor");
		//AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
		//PropertyPlaceholderHelper helper;

		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		Value key;
		String keyProps;
		List<String> placeHolderList = new ArrayList<String>();
		MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
		Class<?> clazz;
		StandardEnvironment env = beanFactory.getBean(StandardEnvironment.class);
		Properties props = new Properties();

		for (String className : beanFactory.getBeanDefinitionNames()) {
			BeanDefinition bd = beanFactory.getBeanDefinition(className);

			if (bd.getBeanClassName() != null && bd.getBeanClassName().contains(ClassUtils.CGLIB_CLASS_SEPARATOR)) {
				System.out.println("Bean Name>>>" + bd.getBeanClassName());

				clazz = ClassUtils.getUserClass(beanFactory.getBean(className).getClass());
				System.out.println("ClassNAme >>>." + clazz);
				Field[] fields = clazz.getDeclaredFields();

				for (Field f : fields) {
					System.out.println("Field Names >>>>>>" + f.getName());
					key = f.getDeclaredAnnotation(Value.class);

					if (key != null) {

						keyProps = key.value();// .replaceAll("[^a-zA-Z0-9\\-\\.\\s+]", "");
						placeHolderList.add(keyProps);
						mutablePropertyValues.add(keyProps, "value");
						props.setProperty(keyProps, "value");
						System.out.println("Field with annotation >>>" + keyProps);

					}
				}

			}

		}

		Collection<com.conjur.valueprocessor.property.ConjurPropertySource> beans = beanFactory
				.getBeansOfType(com.conjur.valueprocessor.property.ConjurPropertySource.class).values();

		MutablePropertySources propertySources = env.getPropertySources();
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		
		for (PropertySource source : beans) {
			// System.out.println("PropertySource"+propertySources);
			if (propertySources.contains(source.getName())) {
				continue;
			}

			propertySources.addLast(source);
			System.out.println("PropertySource" + propertySources);
			configurer.setProperties(props);
			configurer.setBeanFactory(beanFactory);
			configurer.setEnvironment(env);
			

		}
		
		


	}

	/*
	 * @Bean static private Map<String, Object>
	 * parseProperties(ConfigurableEnvironment environment) {
	 * System.out.println("Inside parseProperties"); Map<String, Object> propertyMap
	 * = new HashMap<>(); for (PropertySource source :
	 * environment.getPropertySources()) { //System.out.println("PropertyNames>>>" +
	 * source.getName()); if (source instanceof EnumerablePropertySource) {
	 * EnumerablePropertySource propertySource = (EnumerablePropertySource) source;
	 * for (String property : propertySource.getPropertyNames()) {
	 * System.out.println("PropertyNames>>>" + property); } } } return propertyMap;
	 * }
	 */

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("CAlling post process Bean registry");
		String name = "conjurPropertySource";
		BeanDefinitionBuilder builder = BeanDefinitionBuilder
				.genericBeanDefinition(com.conjur.valueprocessor.property.ConjurPropertySource.class);
		builder.addConstructorArgValue("jenkins-app");
		// builder.addConstructorArgValue(null);
		// builder.addPropertyValue("dbUserName", "value");
		AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
		registry.registerBeanDefinition(name, beanDefinition);

	}

}
