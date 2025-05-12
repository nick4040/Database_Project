package com.example.vactionHome.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "records")
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordId;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private Timestamp createAt;

    @Column(name = "foreignId")
    private int foreignId;


}
