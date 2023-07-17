package com.pooespol.proyecto1p;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Oferta {
    private final static String OFERTA_FILE = "ofertas.txt";
    
    private static int nextId = Archivo.cargarUltimoId(OFERTA_FILE);
    
    private int id;
    private int idComprador;
    private double valor;
    
    private int idVehiculo;

    public Oferta(int id, int idComprador,int idVehiculo, double valor) {
        this.id = nextId;
        this.idComprador = idComprador;
        this.valor = valor;        
        this.idVehiculo = idVehiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
       
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    
    public void guardarOfertaEnArchivo() {
        File archivo = new File(OFERTA_FILE);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(OFERTA_FILE, true))) {
            writer.println(this.formatoOfertaArchivo());
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public static void realizarOferta(int idVehiculo, int idComprador) {
        if (idComprador != 0 && idVehiculo != -1) {        
            Scanner sc = new Scanner(System.in);
            System.out.println("Precio de la oferta:");
            String precio = sc.nextLine();
            Oferta oferta = new Oferta(nextId++, idComprador, idVehiculo, Double.parseDouble(precio));
            oferta.guardarOfertaEnArchivo();
        }
    }
        
    private String formatoOfertaArchivo() {
        return this.id+","+this.idComprador+","+this.idVehiculo+","+this.valor;
    }
    
    private static ArrayList<String[]> filtrarOfertas(String[] s) {
        ArrayList<String[]> ofertas = Archivo.listaLinea(OFERTA_FILE);
        ArrayList<String[]> ofertasFiltradas = new ArrayList<>();
        
        for (String[] oferta: ofertas) {
            if (oferta[2].equals(s[0])) {
                String[] filtro = {oferta[1], oferta[3]};
                ofertasFiltradas.add(filtro);
            }
        }
        return ofertasFiltradas;
    }
    
    public static int menuOferta() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la placa:");
        String placa = sc.nextLine();
        int opcion;
        int c = 0;
        
        String[] s = Vehiculo.buscarVehiculo(placa);
        
        ArrayList<String[]> oferta = filtrarOfertas(s);
        System.out.println(s[1]+" "+s[2]+ " " + s[3] + " Precio: " + s[4]);
        if (oferta.isEmpty()){
            System.out.println("No se han encontrado ofertas para esta placa");
            return 0;
        }else{
        System.out.println("Se han realizado " + oferta.size() + " ofertas.");
        do {
            System.out.println("Oferta #" + (c+1));
            System.out.println("Correo: " + Archivo.buscarCorreo("compradores.txt", oferta.get(c)[0]));
            System.out.println("Precio: " + oferta.get(c)[1]);
            System.out.println("""
                               1. Siguiente
                               2. Retroceder
                               3. Aceptar Oferta
                               4. Salir""");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    if(c!=oferta.size()-1){
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
                    eliminarOfertasPorPlaca(placa);
                    int idVendedor=Vendedor.validarCredenciales();       
                    if (idVendedor == 0) {
                        System.out.println("Credenciales de vendedor incorrectas. No se puede enviar el correo.");
                    }else{
                        Utilitaria.enviarCorreo(correo_comprador,idVendedor);   
                    }
                    return c+1;                 
                default:
                    break;
            }                                           
        } while (opcion != 4);
        return -1;
    }    
    private static void eliminarOfertasPorPlaca(String placa) throws IOException {
    ArrayList<String[]> ofertas = Archivo.listaLinea(OFERTA_FILE);
    ArrayList<String[]> ofertasActualizadas = new ArrayList<>(); 
    for (String[] oferta : ofertas) {
        if (!oferta[2].equals(placa)) {
            ofertasActualizadas.add(oferta);
        }
    }
   
    Archivo.guardarListaLinea(OFERTA_FILE, ofertasActualizadas);
    } 
}

