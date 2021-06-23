package com.skillhunt.db.auth;

import com.skill_hunt.web.dto.auth.BaseRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class RoleUserEntity extends BaseEntity {

  private String username;

  @Enumerated(EnumType.STRING)
  private BaseRole role;

}