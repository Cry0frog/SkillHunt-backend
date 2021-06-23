package com.skillhunt.db.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="passwords")
@Getter
@Setter
@NoArgsConstructor
public class PasswordEntity extends BaseEntity implements Serializable {
  static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public PasswordEntity(String password) {
    this.password = passwordEncoder.encode(password);
  }

  private String password;

  @JsonIgnore
  private void setPasswordWithEncoding(String password) {
    this.password = passwordEncoder.encode(password);
  }
}
