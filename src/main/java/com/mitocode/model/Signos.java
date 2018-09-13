package com.mitocode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "signos")
public class Signos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSignos;

    @ManyToOne
    @JoinColumn(name = "idPaciente", nullable = false)
    private Paciente paciente;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "temperatura", nullable = false)
    private BigDecimal temperatura;

    @Column(name = "pulso", nullable = false)
    private BigDecimal pulso;

    @Column(name = "ritmo_cardiaco", nullable = false)
    private BigDecimal ritmoCardiaco;

    public int getIdSignos() {
        return idSignos;
    }

    public void setIdSignos(int idSignos) {
        this.idSignos = idSignos;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }

    public BigDecimal getPulso() {
        return pulso;
    }

    public void setPulso(BigDecimal pulso) {
        this.pulso = pulso;
    }

    public BigDecimal getRitmoCardiaco() {
        return ritmoCardiaco;
    }

    public void setRitmoCardiaco(BigDecimal ritmoCardiaco) {
        this.ritmoCardiaco = ritmoCardiaco;
    }
}
