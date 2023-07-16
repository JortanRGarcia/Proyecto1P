package com.pooespol.proyecto1p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Vehiculo {
    protected static final String VEHICULO_FILE = "vehiculos.txt";
    
    protected static int nextId = Archivo.cargarUltimoId(VEHICULO_FILE);
    
    protected int id;
    protected int idVendedor;
    protected ArrayList<Oferta> ofertas;
    protected Vendedor vendedor;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipoDeMotor;
    protected String ano;
    protected double recorrido;
    protected String color;
    protected String tipoDeCombustible;
    protected double precio;

    public Vehiculo(int id, int idVendedor, ArrayList<Oferta> ofertas,String placa, String marca, String modelo, String tipoDeMotor, String ano, double recorrido, String color, String tipoDeCombustible, double precio) {
        this.id = id;
        this.idVendedor = idVendedor;
        this.ofertas = ofertas;      
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoDeMotor = tipoDeMotor;
        this.ano = ano;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoDeCombustible = tipoDeCombustible;
        this.precio = precio;
        
    }
    
    public Vehiculo(int id, int idVendedor, String placa, String marca, String modelo, String tipoDeMotor, String ano, double recorrido, String color, String tipoDeCombustible, double precio) {
        this(nextId, idVendedor, new ArrayList<Oferta>(), placa, marca, modelo, tipoDeMotor, ano, recorrido, color, tipoDeCombustible, precio );
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
    
    public static String[] registrar(Scanner sc) {              
        System.out.println("Ingrese las caracteristicas del vehículo:");
        System.out.println("Placa:");
        String placa = sc.nextLine();
        
        System.out.println("Marca:");
        String marca = sc.nextLine();
        
        System.out.println("Modelo:");
        String modelo = sc.nextLine();
        
        System.out.println("Tipo de motor:");
        String tipoDeMotor = sc.nextLine();
        
        System.out.println("Año de fabricación: ");
        String ano = sc.nextLine();
        
        System.out.println("Recorrido:");
        String recorrido = sc.nextLine();        
        
        System.out.println("Color:");
        String color = sc.nextLine();
        
        System.out.println("Tipo de combustible:");
        String tipoDeCombustible = sc.nextLine();
        
        System.out.println("Precio (Con dos decimales)");
        String precio = sc.nextLine();
            
        return new String[] {placa, marca, modelo, tipoDeMotor, ano, recorrido, color, tipoDeCombustible, precio};    
    }
    
    public static void registrarVehiculo(String[] c) {
        int idVen = Vendedor.validarCredenciales();  
        if (!Archivo.placaYaExiste(VEHICULO_FILE, c[0])) {
            if (idVen != 0) {
                Vehiculo vehiculo = new Vehiculo(nextId++, idVen, c[0], c[1], c[2], c[3], c[4], Double.parseDouble(c[5]), c[6], c[7], Double.parseDouble(c[8]));
                vehiculo.guardarVehiculoEnArchivo();
            }
        } else {
            System.out.println("Ese vehiculo ya esta registrado.");
        }
    }
        
    
    protected void guardarVehiculoEnArchivo() {
        File archivo = new File(VEHICULO_FILE);
        if(!archivo.exists()) {
            try {
                archivo.createNewFile();                
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(VEHICULO_FILE, true))) {
            writer.println(this.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Oferta o:this.ofertas) {
            if(ofertas.indexOf(o) != ofertas.size()-1) {
                s.append(o.getId());
                s.append("/");
            } else {
                s.append(o.getId());
            }            
        }        
        return this.id+","+this.idVendedor+","+s+","+this.placa+","+this.marca+","+this.modelo+","+this.tipoDeMotor+","+this.ano+","+this.recorrido+","+this.color+","+this.tipoDeCombustible+","+this.precio;   
    }
    
    
    
    private static void mostrarVehiculo(String[] s) {
        String[] texto = {"Placa:","Marca:","Modelo:","Tipo de motor:","Año de fabricación:","Recorrido:","Color:","Tipo de combustible:","precio", "Vidrios:", "Transmisión:", "Tracció:"};
        int n = 0;
        while(n < s.length - 3) {
            System.out.println(texto[n]);
            System.out.println(s[3+n]);
            n++;
        }
    }
    
    public static int mostrarVehiculos() {
        ArrayList<String[]> vehiculos = Archivo.listaLinea(VEHICULO_FILE);
        Scanner sc = new Scanner(System.in);
        int opcion;
        int c = 0;
        
        do {
            System.out.println("Vehiculo #" + (c+1));
            mostrarVehiculo(vehiculos.get(c));
            System.out.println("""
                               1. Siguiente
                               2. Regresar
                               3. Ofertar
                               4. Salir""");
            opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    if(c!=vehiculos.size()-1){
                        c++;
                    } else {
                        System.out.println("No puede avanzar");
                    }
                    break;
                case 2:
                    if(c!=0) {
                        c--;
                    } else {
                        System.out.println("No puede retroceder");
                    }
                    break;
                case 3:
                    return c+1;                   
                default:
                    break;
            }                         
        } while (opcion != 4);
        return -1;
    }
    
    public static String[] buscarVehiculo(String placa) {
        File archivo = new File(VEHICULO_FILE);
        if(archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(VEHICULO_FILE))) {
                String linea;
                while((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (placa.equals(partes[3])) {
                        return new String[] {partes[0], partes[4], partes[5], partes[6], partes[11]};
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new String[0];
    }
}
