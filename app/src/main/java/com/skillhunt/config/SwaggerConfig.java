package com.skillhunt.config;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
      .displayRequestDuration(true)
      .validatorUrl(StringUtils.EMPTY)
      .build();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .useDefaultResponseMessages(false)
      .select()
      .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
      .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("SkillHunt-app ")
      .description("SkillHunt-app")
      .version(getBuildVersion())
      .build();
  }

  private String getBuildVersion() {
    try (InputStream stream = getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF")) {
      Properties properties = new Properties();
      properties.load(stream);
      return properties.getProperty("Implementation-Version");
    } catch (Exception e) {
      log.warn("Не удалось прочитать версию из манифеста: " + e.getClass() + " " + e.getMessage());
      return "UNKNOWN";
    }
  }

}
