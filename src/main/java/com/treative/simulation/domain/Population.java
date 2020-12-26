package com.treative.simulation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long Pi;
    private Long Pv;
    private Long Pm;
    private Long Pr;
    private LocalDate date;


    public Population() {
    }


    public Population(Long pi, Long pv, Long pm, Long pr, LocalDate date) {
        Pi = pi;
        Pv = pv;
        Pm = pm;
        Pr = pr;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPi() {
        return Pi;
    }

    public void setPi(Long pi) {
        Pi = pi;
    }

    public Long getPv() {
        return Pv;
    }

    public void setPv(Long pv) {
        Pv = pv;
    }

    public Long getPm() {
        return Pm;
    }

    public void setPm(Long pm) {
        Pm = pm;
    }

    public Long getPr() {
        return Pr;
    }

    public void setPr(Long pr) {
        Pr = pr;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Population{" +
                "id=" + id +
                ", Pi=" + Pi +
                ", Pv=" + Pv +
                ", Pm=" + Pm +
                ", Pr=" + Pr +
                ", date=" + date +
                '}';
    }
}

