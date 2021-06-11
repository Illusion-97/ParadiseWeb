package com.example.demo.paradise.app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Trip implements Serializable,Bean {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(unique=true)
    private String name;
    @OneToOne
    @JoinColumn(name="id")
    private Place departure;
    @OneToOne
    @JoinColumn(name="id")
    private Place terminus;
    private float cost;

    public Trip() {}

    public Trip(Long id, String name, Place departure, Place terminus, float cost) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.terminus = terminus;
        this.cost = cost;
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

    public Place getDeparture() {
        return departure;
    }

    public Place getTerminus() {
        return terminus;
    }

    public float getCost() {
        return cost;
    }
}
