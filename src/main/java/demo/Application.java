package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/* Setup static file mappings (Hives) */
	    // registry.addResourceHandler("/img/**").addResourceLocations("file:///users/ctu/Pictures/");
	}

}
