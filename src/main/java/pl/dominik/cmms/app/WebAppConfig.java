package pl.dominik.cmms.app;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public	void addViewControllers(ViewControllerRegistry registry)	{
        registry.addViewController("/403").setViewName("403");
    }
}
