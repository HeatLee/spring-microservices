package com.task.microservicesscripts.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;

@Data
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(schema = "scripts", name = "script")
public class Script {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "command_name")
    private String name;

    private String description;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] command;

    private String filenameExtension;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedAt;
}
