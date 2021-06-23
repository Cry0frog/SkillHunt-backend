package com.skill_hunt.web.dto.auth;

import lombok.Getter;

@Getter
public enum BaseRole {

  ADMIN("ROLE_ADMIN", "ADMIN"),
  USER("ROLE_USER", "USER");


  private final String value;
  private final String role;

  private BaseRole(String value, String role) {
    this.value = value;
    this.role = role;
  }
}
