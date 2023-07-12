
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

    public void registrarVendedor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los siguientes datos:");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Organización: ");
        String organizacion = scanner.nextLine();
        System.out.print("Correo Electrónico: ");
        String correo = scanner.nextLine();

        if (existeCorreo(correo)) {
            System.out.println("El correo ya está registrado.");
            return;
        }

        System.out.print("Clave: ");
        String clave = scanner.nextLine();
        String hashClave = generarHash(clave);
        int nuevoId = obtenerNuevoId();
        Vendedor nuevoVendedor = new Vendedor(nuevoId, nombres, apellidos, correo, hashClave, organizacion);
        ListaVendedores.agregarVendedor(nuevoVendedor);

        System.out.println("Registro exitoso.");
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

