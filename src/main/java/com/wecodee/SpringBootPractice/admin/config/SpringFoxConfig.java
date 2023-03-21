package com.wecodee.SpringBootPractice.admin.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
public class SpringFoxConfig {

	// http://localhost:8081/swagger-ui.html

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("WeBot2.1").apiInfo(apiDetails()).select()
//				.paths(regex("/user")).build();
//	}

//	   private ApiInfo apiDetails() {
//		return new ApiInfoBuilder().title("Spring boot service")
//				.description("Akash Webot Akash Webot API details 2.1www.wecodee.com")
//				.termsOfServiceUrl("www.wecodee.com").license("akash").licenseUrl("www.akash.com").version("2.1")
//				.build();
//	}

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build().apiInfo(apiDetails());
//	}

//	private ApiInfo apiDetails() {
//		return new ApiInfo("Akash Webot", "Akash Webot API details", "2.1", "www.wecodee.com",
//				new springfox.documentation.service.Contact("WeCodee", "https://www.wecodee.com/#/wecodee/home",
//						"support@wecodee.com"),
//				"", "www.wecodee.com", Collections.emptyList());
//	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}

}
