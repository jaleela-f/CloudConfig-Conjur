package com.cyberark.conjur.clientapp.processor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import com.cyberark.conjur.clientapp.core.ConjurPropertySource;
import com.cyberark.conjur.clientapp.util.ConjurPropertyLoaderUtil;



@Configuration
public class ConjurValueProcessor
		implements BeanPostProcessor, InitializingBean, ApplicationContextAware, EnvironmentAware {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConjurPropertySource propertySource;

	private ConjurPropertyLoaderUtil propertyLoader = new ConjurPropertyLoaderUtil();
	private static Map<String, Object> secretParams = new HashMap<String, Object>();
	private ConfigurableEnvironment environment;

	private final String propertySourceName = "classpath:/conjur.properties";

	private ApplicationContext applicationContext;

	private Map<String, Object> systemConfigMap = new HashMap<>();
	private Map<String, Object> newMap = new HashMap<String, Object>();

	@Autowired
	Environment env;
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		environment.getPropertySources().addLast(new MapPropertySource(propertySourceName, systemConfigMap));
		MutablePropertySources source = environment.getPropertySources();
		for (PropertySource<?> bean : source) {

			String value = (String) bean.getProperty("jenkins-app.dbUserName");
			
			String beanName = bean.getName();
//			if ((beanName.contains("bootstrapProperties-file")) || (beanName.contains("systemProperties")) || (beanName.contains("systemEnvironment")) ||
//					(beanName.contains("random")) ||  (beanName.contains("springCloudClientHostInfo")) || (beanName.contains("applicationConfig"))
//					|| (beanName.contains("springCloudDefaultProperties")) || (beanName.contains("cachedrandom")) 
//					|| (beanName.contains("configurationProperties")) || (beanName.contains("servletConfigInitParams")) 
//					|| (beanName.contains("servletContextInitParams")))
//			
			if(!beanName.contains(propertySourceName))
			{
				
				if(value !=null && !value.isBlank())
				{
					System.out.println("Property Source1>>>" + beanName+ "Value="+value);
					propertyLoader.readPropertiesFromFile();

					Set<Object> keySet = propertyLoader.getKey();
					Iterator<Object> iter = keySet.iterator();
					String key;
					while (iter.hasNext()) {
						key = (String) iter.next();
						newMap.put(key, bean.getProperty(key));
					}
				
				//System.out.println("New MAp value"+newMap);
				environment.getPropertySources().addFirst(new MapPropertySource(bean.getName(), newMap));
				break;
				}

			} else {
				logger.info("Property Source2>>>" + beanName);
				//System.out.println("Property Source>>>" + systemConfigMap);
				secretParams = (Map<String, Object>) propertySource.getPropertyMethod();
				logger.info("SEcret value"+secretParams);
				for (Map.Entry<String, Object> map : secretParams.entrySet()) {
					String key = map.getKey();
					logger.info("Key>>>" + key);

					Object secretValue = map.getValue();
					logger.info("VAlue >>>>" + secretValue);
					systemConfigMap.put(key, secretValue);
				}
				logger.debug("Secrets >>>" + systemConfigMap);
				
				environment.getPropertySources().addFirst(new MapPropertySource(propertySourceName, systemConfigMap));

			}
		}
	}

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		if (environment instanceof ConfigurableEnvironment) {
			logger.info("Environment>>" + environment);
			this.environment = (ConfigurableEnvironment) environment;
		}

	}
}

