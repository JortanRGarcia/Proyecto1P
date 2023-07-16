package com.pooespol.proyecto1p;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Camioneta extends Auto{
    private String traccion;

    public Camioneta(int id, int idVendedor, ArrayList<Oferta> ofertas,String placa, String marca, String modelo, String tipoDeMotor, String ano, double recorrido, String color, String tipoDeCombustible, double precio, String vidrios, String transmision, String traccion) {
        super(id, idVendedor, ofertas, placa, marca, modelo, tipoDeMotor, ano, recorrido, color, tipoDeCombustible, precio, vidrios, transmision);
        this.traccion = traccion;
    }
    
    public Camioneta(int id, int idVendedor, String placa, String marca, String modelo, String tipoDeMotor, String ano, double recorrido, String color, String tipoDeCombustible, double precio, String vidrios, String transmision, String traccion) {
        this(Vehiculo.nextId, idVendedor, new ArrayList<Oferta>(), placa, marca, modelo, tipoDeMotor, ano, recorrido, color, tipoDeCombustible, precio, vidrios, transmision, traccion);
    }
    
    public static void registrarCamioneta(String[] c, String[] d) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Traccion:");
        String traccion = sc.nextLine();
        int idVen = Vendedor.validarCredenciales();
        if(!Archivo.placaYaExiste(VEHICULO_FILE, c[0])) {
            Camioneta camioneta = new Camioneta(Vehiculo.nextId++, idVen, c[0], c[1], c[2], c[3], c[4], Double.parseDouble(c[5]), c[6], c[7], Double.parseDouble(c[8]), d[0], d[1], traccion);
            camioneta.guardarVehiculoEnArchivo();
        }
    } 
    
    @Override
    public String toString() {
        return super.toString()+","+this.traccion;
    }
    
    
}

