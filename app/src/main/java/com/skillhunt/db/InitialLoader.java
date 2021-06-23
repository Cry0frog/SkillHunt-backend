package com.skillhunt.db;

import com.skill_hunt.web.dto.auth.BaseRole;
import com.skillhunt.db.auth.AuthUserEntity;
import com.skillhunt.db.auth.PasswordEntity;
import com.skillhunt.db.auth.RoleUserEntity;
import com.skillhunt.db.repository.AuthUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitialLoader {

  private final AuthUserRepo authUserRepo;

  public void initial() throws IOException {
    userInit();
  }

  private void userInit() {
    String defPwd = "admin";
    boolean enabled = true;
    AuthUserEntity admin = new AuthUserEntity(
            defPwd,
            enabled,
            AuthUserEntity.setEnabled(enabled),
      new PasswordEntity(defPwd),
      Collections.singleton(new RoleUserEntity("admin", BaseRole.ADMIN))
    );
    if(authUserRepo.findAll().size()==0) {
      authUserRepo.save(admin);
    }
  }
}
