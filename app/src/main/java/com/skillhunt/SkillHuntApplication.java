package com.skillhunt;

import com.skillhunt.db.InitialLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@EntityScan({"com.skillhunt.db"})
@EnableJpaRepositories(basePackages = {"com.skillhunt.db.repository"})
@SpringBootApplication
@EnableFeignClients
public class SkillHuntApplication {

	private static InitialLoader initiator;

	@Autowired
	public void setInitialLoader(InitialLoader initiator) {
		SkillHuntApplication.initiator = initiator;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SkillHuntApplication.class, args);
		initiator.initial();
	}

}
