package com.treative.simulation.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Simulation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String n;
    private Long P;
    private Double I;
    private Double R;
    private Double M;
    private Integer Ti;
    private Integer Tm;
    private Integer Ts;
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Population> populationList;

    public Simulation() {
        this.populationList = new ArrayList<>();
    }

    public Simulation(String n, Long p, Double i, Double r, Double m, Integer ti, Integer tm, Integer ts) {
        this.n = n;
        P = p;
        I = i;
        R = r;
        M = m;
        Ti = ti;
        Tm = tm;
        Ts = ts;
        this.populationList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getN() {
        return n;
    }

    public Long getP() {
        return P;
    }

    public Double getI() {
        return I;
    }

    public Double getR() {
        return R;
    }

    public Double getM() {
        return M;
    }

    public Integer getTi() {
        return Ti;
    }

    public Integer getTm() {
        return Tm;
    }

    public Integer getTs() {
        return Ts;
    }

    public List<Population> getPopulationList() {
        return populationList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setN(String n) {
        this.n = n;
    }

    public void setP(Long p) {
        P = p;
    }

    public void setI(Double i) {
        I = i;
    }

    public void setR(Double r) {
        R = r;
    }

    public void setM(Double m) {
        M = m;
    }

    public void setTi(Integer ti) {
        Ti = ti;
    }

    public void setTm(Integer tm) {
        Tm = tm;
    }

    public void setTs(Integer ts) {
        Ts = ts;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPopulationList(LinkedList<Population> populationList) {
        this.populationList = populationList;
    }


    @Override
    public String toString() {
        return "Simulation{" +
                "id=" + id +
                ", N='" + n + '\'' +
                ", P=" + P +
                ", I=" + I +
                ", R=" + R +
                ", M=" + M +
                ", Ti=" + Ti +
                ", Tm=" + Tm +
                ", Ts=" + Ts +
                ", date=" + date +
                ", populationList=" + populationList +
                '}';
    }
}
