package com.example.demo.paradise.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Place implements Serializable,Bean {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(unique=true)
    private String name;

    public Place() {}

    public Place(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
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
