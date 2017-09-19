package com.gremadex.constructionloanmanager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Shashank on 19/9/2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.gremadex.constructionloanmanager.persistance.repository")
public class DatabaseConfiguration {
}
