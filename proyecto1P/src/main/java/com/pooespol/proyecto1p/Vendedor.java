import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void guardarVendedor() {
        File archivo = new File("vendedor.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            String vendedorInfo = id + "," + nombres + "," + apellidos + "," + correo + "," + clave;
            writer.write(vendedorInfo);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("¡Error al guardar el vendedor en el archivo!");
        }
    }

    @Override
    protected Usuario crearUsuario(String nombres, String apellidos, String correo, String clave, String organizacion) {
        int nuevoId = obtenerNuevoId();
        Vendedor nuevoVendedor = new Vendedor(nuevoId, nombres, apellidos, correo, clave, organizacion);
        nuevoVendedor.guardarVendedor();
        return nuevoVendedor;
    }

    public void ingresarNuevoVehiculo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.println("Ingrese su clave: ");
        String clave = scanner.nextLine();

        if (validarUsuario(correo, clave)) {
            System.out.println("Acceso concedido. Ingrese los datos del nuevo vehículo:");

            System.out.println("Tipo de vehículo (auto, camioneta, moto): ");
            String tipoVehiculo = scanner.nextLine();

            System.out.println("Placa: ");
            String placa = scanner.nextLine();

            if (existePlaca(placa)) {
                System.out.println("¡Error! La placa ingresada ya existe en el sistema.");
                return;
            }

            System.out.println("Marca: ");
            String marca = scanner.nextLine();

            System.out.println("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.println("Tipo de motor: ");
            String tipoMotor = scanner.nextLine();

            System.out.println("Año: ");
            int año = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Recorrido: ");
            int recorrido = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Color: ");
            String color = scanner.nextLine();

            System.out.println("Tipo de combustible: ");
            String tipoCombustible = scanner.nextLine();

            int vidrios = 0;
            String transmision = "";
            String traccion = "";

            if (tipoVehiculo.equalsIgnoreCase("auto") || tipoVehiculo.equalsIgnoreCase("camioneta")) {
                System.out.println("Vidrios: ");
                vidrios = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Transmisión: ");
                transmision = scanner.nextLine();

                if (tipoVehiculo.equalsIgnoreCase("camioneta")) {
                    System.out.println("Tracción: ");
                    traccion = scanner.nextLine();
                }
            }

            System.out.println("Precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine();
            Vehiculo vehiculo = new Vehiculo(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible,
                    vidrios, transmision, traccion, precio);

            guardarVehiculo(vehiculo);

            System.out.println("El vehículo ha sido ingresado correctamente.");
        } else {
            System.out.println("¡Error! Correo o clave incorrectos. Acceso denegado.");
        }
    }

    private static int cargarUltimoId() {
        File file = new File("vendedor.txt");
        if (!file.exists()) {
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lastLine = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            if (lastLine != null) {
                String[] values = lastLine.split(",");
                return Integer.parseInt(values[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    private boolean existePlaca(String placa) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("vehiculos.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            if (values[5].equals(placa)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    @Override
    public String toString() {
        return id + "," +
                idVendedor + "," +
                ofertas + "," +
                vendedor + "," +
                placa + "," +
                marca + "," +
                modelo + "," +
                tipoDeMotor + "," +
                anio + "," +
                recorrido + "," +
                color + "," +
                tipoDeCombustible + "," +
                precio;
    }

    private void guardarVehiculo(Vehiculo vehiculo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehiculos.txt", true))) {
            writer.write(vehiculo.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("¡Error al guardar el vehículo en el archivo!");
        }
    }

    public static boolean validarCredenciales() throws IOException {
        Scanner scanner = new Scanner(System.in);
        File archivo = new File(COMPRADOR_FILE);

        if (archivo.exists()) {
            System.out.println("Ingrese su dirección de correo: ");
            String correo = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            String clave = scanner.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            boolean success = false;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[3].equals(correo) && partes[5].equals(Utilitaria.convertirHash(clave))) {
                    System.out.println("Inicio de sesión exitoso");
                    success = true;
                    break;
                }
            }

            reader.close();

            if (success) {
                return true;
            }
        }

        System.out.println("Correo o contraseña incorrectos");
        return false;
    }
}

    
