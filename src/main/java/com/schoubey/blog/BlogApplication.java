package com.schoubey.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public static void main(String[] args) {
      SpringApplication.run(BlogApplication.class, args);
  }

  @Override
  public void run(String... strings) {
    try {
      jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS posts (id uuid NOT NULL, title character varying(100) NOT NULL,createtime timestamp with time zone NOT NULL,updatetime timestamp with time zone NOT NULL)");
      jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS post_content (id uuid, content text)");
      jdbcTemplate.execute("INSERT into posts values('fcd171ef-8dd2-4ceb-afcd-41e29d2c6824' ,'First Test Post', '1999-01-01 02:05:06+05:30', '1999-01-01 02:05:06+05:30')");
      jdbcTemplate.execute("INSERT into post_content values('fcd171ef-8dd2-4ceb-afcd-41e29d2c6824', '#First Post \n## This is the first post')");
    } catch(DataAccessException dae) {
      System.out.println("Failed to create tables quitting");
      dae.printStackTrace();
    }
  }
}
