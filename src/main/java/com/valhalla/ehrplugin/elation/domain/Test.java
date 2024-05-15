package com.valhalla.ehrplugin.elation.domain;

import jakarta.persistence.*;

@Entity
@Table(name="test")
public class Test {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name", length=50, nullable=false, unique=false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
