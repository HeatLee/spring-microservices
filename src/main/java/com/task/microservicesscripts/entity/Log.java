package com.task.microservicesscripts.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue
    private int id;
    @Lob
    @Column(name = "script_output", columnDefinition = "BLOB")
    private byte[] out;
    @Lob
    @Column(name = "error_log", columnDefinition = "BLOB")
    private byte[] err;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id")
    private Script script;


}
