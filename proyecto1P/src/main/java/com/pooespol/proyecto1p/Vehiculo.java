package com.pooespol;

import java.util.ArrayList;

public class Vehiculo {
  protected int id;
  protected int idVendedor;
  protected ArrayList<Oferta> ofertas;
  protected Vendedor vendedor;
  protected String placa;
  protected String marca;
  protected String modelo;
  protected String tipoDeMotor;
  protected String anio;
  protected double recorrido;
  protected String color;
  protected String tipoDeCombustible;
  protected double precio;

  public Vehiculo(int id, int idVendedor, ArrayList<Oferta> ofertas, Vendedor vendedor, String placa, String marca, String modelo, String tipoDeMotor, String anio, double recorrido, String color, String tipoDeCombustible, double precio) {
    this.id = id;
    this.idVendedor = idVendedor;
    this.ofertas = ofertas;
    this.vendedor = vendedor;
    this.placa = placa;
    this.marca = marca;
    this.modelo = modelo;
    this.tipoDeMotor = tipoDeMotor;
    this.anio = anio;
    this.recorrido = recorrido;
    this.color = color;
    this.tipoDeCombustible = tipoDeCombustible;
    this.precio = precio;
  }

  public Vehiculo(int id, int idVendedor, Vendedor vendedor, String placa, String marca, String modelo, String tipoDeMotor, String anio, double recorrido, String color, String tipoDeCombustible, double precio) {
    this(id, idVendedor, new ArrayList<Oferta>(), vendedor, placa, marca, modelo, tipoDeMotor, anio, recorrido, color, tipoDeCombustible, precio);
  }

  public int getId() {
      return id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public int getIdVendedor() {
      return idVendedor;
  }

  public void setIdVendedor(int idVendedor) {
      this.idVendedor = idVendedor;
  }

  public ArrayList<Oferta> getOfertas() {
      return ofertas;
  }

  public void setOfertas(ArrayList<Oferta> ofertas) {
      this.ofertas = ofertas;
  }

  public Vendedor getVendedor() {
      return vendedor;
  }

  public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
  }

  public String getPlaca() {
      return placa;
  }

  public void setPlaca(String placa) {
      this.placa = placa;
  }

  public String getMarca() {
      return marca;
  }

  public void setMarca(String marca) {
      this.marca = marca;
  }

  public String getModelo() {
      return modelo;
  }

  public void setModelo(String modelo) {
      this.modelo = modelo;
  }

  public String getTipoDeMotor() {
      return tipoDeMotor;
  }

  public void setTipoDeMotor(String tipoDeMotor) {
      this.tipoDeMotor = tipoDeMotor;
  }

  public String getAno() {
      return ano;
  }

  public void setAno(String ano) {
      this.ano = ano;
  }

  public double getRecorrido() {
      return recorrido;
  }

  public void setRecorrido(double recorrido) {
      this.recorrido = recorrido;
  }

  public String getColor() {
      return color;
  }

  public void setColor(String color) {
      this.color = color;
  }

  public String getTipoDeCombustible() {
      return tipoDeCombustible;
  }

  public void setTipoDeCombustible(String tipoDeCombustible) {
      this.tipoDeCombustible = tipoDeCombustible;
  }

  public double getPrecio() {
      return precio;
  }

  public void setPrecio(double precio) {
      this.precio = precio;
  }
}
