
public class Vendedor extends Usuario {
    private List<Vehiculo> vehiculos;

    public Vendedor(int id, String nombres, String apellidos, String correo, String clave, String organizacion) {
        super(id, nombres, apellidos, correo, clave, organizacion);
        this.vehiculos = new ArrayList<>();
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

   @Override
    protected Usuario crearUsuario(String nombres, String apellidos, String correo, String clave, String organizacion) {
        int nuevoId = obtenerNuevoId();
        return new Vendedor(nuevoId, nombres, apellidos, correo, clave, organizacion);
    }


    public void ingresarNuevoVehiculo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.println("Ingrese su clave: ");
        String clave = scanner.nextLine();

        if (!super.validarUsuario(correo, clave)) {
            System.out.println("Credenciales inválidas.");
            return;
        }

        System.out.println("Ingrese la información del nuevo vehículo:");
        System.out.println("Placa: ");
        String placa = scanner.nextLine();
        System.out.println("Marca ");
        String marca = scanner.nextLine();
        System.out.println("Modelo");
        String modelo = scanner.nextLine();
        System.out.println("Tipo de Motor ");
        String tipoMotor = scanner.nextLine();
        System.out.println("Año: ");
        System.out.println
       
        Vehiculo nuevoVehiculo = new Vehiculo(/* datos del vehículo */);
        vehiculos.add(nuevoVehiculo);

        System.out.println("Vehículo ingresado exitosamente.");
    }
}

