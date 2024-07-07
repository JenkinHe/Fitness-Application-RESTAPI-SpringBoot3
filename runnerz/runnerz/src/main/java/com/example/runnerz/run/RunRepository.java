package com.example.runnerz.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;


import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;
    
    public RunRepository(JdbcClient jdbcClient){
        this.jdbcClient=jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    public Optional<Run> findById(Integer id){
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id= :id")
        .param("id",id)
        .query(Run.class)
        .optional();
    }

    

}
