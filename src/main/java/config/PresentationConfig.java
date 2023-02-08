package config;

import interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class PresentationConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/template/", ".jsp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/static/css/**", "/static/js/**",
                        "/user/authentication", "/user/registration");
    }

    @Bean
    public SessionInterceptor sessionInterceptor(){
        return new SessionInterceptor();
    }
}
