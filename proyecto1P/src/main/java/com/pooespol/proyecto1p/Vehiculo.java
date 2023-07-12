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
    
  }
}
