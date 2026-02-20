package com.proyecto.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private Integer capacidad;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    // Constructores
    public Mesa() {}
    public Mesa(Integer numero, Integer capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }
}

