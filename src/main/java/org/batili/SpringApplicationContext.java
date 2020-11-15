package org.batili;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {
	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CONTEXT=applicationContext;
	}
	//on decalre cette methode comme bean dans userappapplication
	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
		
	}
}
