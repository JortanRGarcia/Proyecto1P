package com.pooespol.proyecto1p;

public class Oferta {
  private int id;
  private int idComprador;
  private double valor;
  private Comprador comprador;
  private Vehiculo vehiculo;
  private int idVehiculo;

  public Oferta(int id, int idComprador, double valor, Comprador comprador, Vehiculo vehiculo, int idVehiculo) {
    this.id = id;
    this.idComprador = idComprador;
    this.valor = valor;
    this.comprador = comprador;
    this.vehiculo = vehiculo;
    this.idVehiculo = idVehiculo;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIdComprador() {
    return this.idComprador;
  }

  public void setIdComprador(int idComprador) {
    this.idComprador = idComprador;
  }

  public double getValor() {
    return this.valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Comprador getComprador() {
    return this.comprador;
  }

  public void setComprador(Comprador comprador) {
    this.comprador = comprador;
  }

  public Vehiculo getVehiculo() {
    return this.vehiculo;
  }

  public void setVehiculo(Vehiculo vehiculo) {
    this.vehiculo = vehiculo;
  }

  public int getIdVehiculo() {
    return this.idVehiculo;
  }

  public void setIdVehiculo(int idVehiculo) {
    this.idVehiculo = idVehiculo;
  }


