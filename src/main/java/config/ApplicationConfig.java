package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {PresentationConfig.class, PersistenceConfig.class})
@ComponentScan(basePackages = {"controller","data.repository", "service","interceptor"})
public class ApplicationConfig {
}
