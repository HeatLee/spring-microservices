package com.task.microservicesscripts.repository;

import com.task.microservicesscripts.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptRepository extends JpaRepository<Script, Integer> {

}
