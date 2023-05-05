package cn.iocoder.springboot.lab23.testdemo.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

public class MyJsonEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private final Properties properties = new Properties();
    private String[] profiles =
    {
        "test.properties"
    };

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        for(String profile:profiles){
            ClassPathResource resource = new ClassPathResource(profile);
            environment.getPropertySources().addLast(loadProfiles(resource));
        }
    }

    private PropertySource<?> loadProfiles(Resource resource){

        try {
            properties.load(resource.getInputStream());
            return new PropertiesPropertySource(resource.getFilename(), properties);
        } catch (IOException e) {
            throw new IllegalStateException("load resource exception");

        }
    }
}
