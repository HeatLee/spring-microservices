package com.task.microservicesscripts.repository;

import com.task.microservicesscripts.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {

    public List<Log> getLogByScriptId(int scriptId);
}
