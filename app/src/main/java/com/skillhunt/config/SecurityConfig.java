package com.skillhunt.config;

import com.skill_hunt.web.dto.auth.BaseRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth)
          throws Exception {

    auth.jdbcAuthentication().dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery(
                    "select username, p.password as password, enabled "
                            + "from users as u "
                            + "inner join passwords as p on u.password_id = p.id "
                            + "where username=?")
            .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    System.setProperty("https.protocols", "TLSv1.2");

    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);
    http.addFilterBefore(filter, CsrfFilter.class);

    http.httpBasic().and().authorizeRequests()
            /*swagger*/
            .antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**"
            ).permitAll()
            /*end swagger*/

            /*for All*/
            .antMatchers("/api/skill_hunt/user", "/user")
            .permitAll()
            .antMatchers(HttpMethod.POST,
                    "/api/skill_hunt/logout", "/logout",
                                "/api/skill_hunt/support", "/support",
                                "/api/skill_hunt/statistic", "/statistic","/request/supportRequest"
            ).permitAll()
            .antMatchers(HttpMethod.GET,
                    /* authorization user */
                    "/development/authAsUser",
                    /* courses */
                                "/api/skill_hunt/courses/**",
                                "/api/skill_hunt/courses/published",
                    /* companies */
                                "/api/skill_hunt/companies/**",
                    /* fields area */
                                "/api/skill_hunt/fields_area/**",
                    /* directions */
                                "/api/skill_hunt/directions/**",
                    /* reviews */
                                "/api/skill_hunt/reviews/**"
            ).permitAll()
            /*end All*/

            /*for ADMIN*/
            .antMatchers(HttpMethod.POST,
                    /* courses */
                    "/api/skill_hunt/courses/add/*",
                                "/api/skill_hunt/courses/*",
                    /* companies */
                                "/api/skill_hunt/companies/add/*",
                                "/api/skill_hunt/companies/*",
                    /* fields area */
                                "/api/skill_hunt/fields_area/add/*",
                                "/api/skill_hunt/fields_area/*",
                    /* directions */
                                "/api/skill_hunt/directions/add/*",
                                "/api/skill_hunt/directions/*",
                    /* reviews */
                                "/api/skill_hunt/reviews/add/*",
                                "/api/skill_hunt/reviews/*"

            ).hasRole(BaseRole.ADMIN.getRole())
            .antMatchers(HttpMethod.DELETE,
                    /* courses */
                    "/api/skill_hunt/courses/**",
                    /* companies */
                                "/api/skill_hunt/companies/**",
                    /* fields area */
                                "/api/skill_hunt/fields_area/**",
                    /* directions */
                                "/api/skill_hunt/directions/**",
                    /* reviews */
                                "/api/skill_hunt/reviews/**"
            ).hasRole(BaseRole.ADMIN.getRole())
            /*end ADMIN*/

            .and()
            .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
      @Override
      public void commence(HttpServletRequest request, HttpServletResponse response,
                           AuthenticationException authException) throws IOException, ServletException {
        if (request.getRequestURI().startsWith("/api/skill_hunt") && !request.getRequestURI().endsWith("login")) {
          response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else if (request.getRequestURI().startsWith("/webjars/springfox-swagger-ui/springfox.js")) {

        }
      }
    })
            .and()
            .csrf().disable();
    http.cors().disable();
  }

}


