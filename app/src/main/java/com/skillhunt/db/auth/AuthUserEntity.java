package com.skillhunt.db.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AuthUserEntity extends BaseEntity {

  private String username;
  private boolean enabled;

  @Enumerated(EnumType.STRING)
  private StatusUser status;

  @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, orphanRemoval=true)
  @JoinColumn(name="password_id", nullable=false)
  private PasswordEntity passwordEntity;

  @OneToMany(cascade={CascadeType.ALL})
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name="user_id", updatable=true)
  private Set<RoleUserEntity> roles;

  public static StatusUser setEnabled(boolean enabled) {
    if(enabled == true) {
      return StatusUser.NORMAL;
    }
    else {
      return StatusUser.DISABLED;
    }
  }
}

