package com.nutritionApp.nutrition.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medicion")
public class Medicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "peso", nullable = false)
    private Double peso; // en kg

    @Column(name = "altura", nullable = false)
    private Double altura; // en cm

    @Column(name = "circunferencia_cintura")
    private Double circunferenciaCintura; // en cm

    @Column(name = "circunferencia_cadera")
    private Double circunferenciaCadera; // en cm
    
    @Column(name = "porcentaje_grasa_corporal")
    private Double porcentajeGrasaCorporal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nutricionista_id", nullable = false)
    private Nutricionista nutricionista;
    
    // Método para calcular el IMC
    public Double calcularIMC() {
        if (altura == null || peso == null || altura <= 0) {
            return null;
        }
        double alturaEnMetros = altura / 100.0;
        return peso / (alturaEnMetros * alturaEnMetros);
    }
    
    // Constructores

    public Medicion() {
    }

    public Medicion(LocalDate fecha, Double peso, Double altura, Double circunferenciaCintura, Double circunferenciaCadera, Double porcentajeGrasaCorporal, Paciente paciente, Nutricionista nutricionista) {
        this.fecha = fecha;
        this.peso = peso;
        this.altura = altura;
        this.circunferenciaCintura = circunferenciaCintura;
        this.circunferenciaCadera = circunferenciaCadera;
        this.porcentajeGrasaCorporal = porcentajeGrasaCorporal;
        this.paciente = paciente;
        this.nutricionista = nutricionista;
    }

    //getters

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getCircunferenciaCintura() {
        return circunferenciaCintura;
    }

    public Double getCircunferenciaCadera() {
        return circunferenciaCadera;
    }

    public Double getPorcentajeGrasaCorporal() {
        return porcentajeGrasaCorporal;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void setCircunferenciaCintura(Double circunferenciaCintura) {
        this.circunferenciaCintura = circunferenciaCintura;
    }

    public void setCircunferenciaCadera(Double circunferenciaCadera) {
        this.circunferenciaCadera = circunferenciaCadera;
    }

    public void setPorcentajeGrasaCorporal(Double porcentajeGrasaCorporal) {
        this.porcentajeGrasaCorporal = porcentajeGrasaCorporal;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

}
