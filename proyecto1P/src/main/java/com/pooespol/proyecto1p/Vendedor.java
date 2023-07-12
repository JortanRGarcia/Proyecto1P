Public class Vendedor extends Usuario {
  private ArrayList<Vehiculo> vehiculos;

  public Vendedor(int id,String nombres,String apellidos, String clave,String organizacion)){
  super(id,nombres,apellidos,correo,clave,organizacion);
    this,vehiculos=new Arraylist<>();
  }
  public Arraylist<Vehiculo> getVehiculos(){
    return vehiculos;
  }
