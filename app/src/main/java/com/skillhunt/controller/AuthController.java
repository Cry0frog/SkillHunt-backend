package com.skillhunt.controller;

import com.skillhunt.db.email.EmailService;
import com.skillhunt.db.repository.AuthUserRepo;
import com.skill_hunt.web.dto.auth.BaseRole;
import com.skill_hunt.web.dto.support.SupportDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Api(tags = {"Auth part"})
@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final HttpServletRequest httpServletRequest;

  private final AuthUserRepo authUserRepo;
  private final EmailService emailService;

  @GetMapping("/user")
  @ResponseBody
  public Principal user(Principal user) {
    log.warn("getUserAuth: " + (user != null ? user.getName() : "null"));
    UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
    Set<BaseRole> roles = token.getAuthorities().stream()
      .map(auth -> Arrays.stream(BaseRole.values())
        .filter(role -> role.getRole().equals(auth.getAuthority()))
        .findFirst().get()
      )
      .collect(Collectors.toSet());

    Map creds = new HashMap();
    String[] userRoles = roles.stream()
      .map(role -> role.getRole())
      .toArray(String[]::new);

    Authentication authentication = new UsernamePasswordAuthenticationToken(
      ((UsernamePasswordAuthenticationToken) user).getPrincipal(),
      creds,
      AuthorityUtils.createAuthorityList(userRoles)
    );

    return authentication;
  }

  @PostMapping(path = "/logout", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public Principal logout(Principal user, HttpServletRequest request, HttpServletResponse response) {
    CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    cookieClearingLogoutHandler.logout(request, response, null);
    securityContextLogoutHandler.logout(request, response, null);

    return user;
  }
  @GetMapping("/development/authAsUser")
  @ResponseBody
  public String authAsPartner(Principal user) {
      development_authentication("admin",
        Stream.of(BaseRole.ADMIN.getRole()).toArray(String[]::new)
      );
      return "OK";
  }

  @PostMapping(path = "/support", consumes = "application/json", produces = "application/json")
  @ResponseBody
  public boolean sendSupportMessage(@RequestBody SupportDto dto) {
    try {
      if(dto.getEmail() != null & dto.getMessage() != null & dto.getFio() != null) {
        emailService.sendSupportMessage(dto.getFio(), dto.getEmail(), dto.getMessage());
      }
      else {
        return false;
      }
    }
    catch(MailException e ) {
      log.error(e.getLocalizedMessage(), e.getMessage());
      return false;
    }
    return true;
  }
  private void development_authentication(String username, String[] roles) {
    Authentication authentication = new UsernamePasswordAuthenticationToken(
      username, null, AuthorityUtils.createAuthorityList(roles)
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
