
package com.pooespol.proyecto1p;

import java.util.ArrayList;
import java.util.Scanner;

/
public class Auto extends Vehiculo{
    
    
    protected String vidrios;
    protected String transmision;

    public Auto(int id, int idVendedor, ArrayList<Oferta> ofertas, String placa, String marca, String modelo, String tipoDeMotor, String ano, double recorrido, String color, String tipoDeCombustible, double precio, String vidrios, String transmision) {
        super(id, idVendedor, ofertas, placa, marca, modelo, tipoDeMotor, ano, recorrido, color, tipoDeCombustible, precio);
        this.vidrios = vidrios;
        this.transmision = transmision;
    }
    
    public Auto(int id, int idVendedor, String placa, String marca, String modelo, String tipoDeMotor, String ano, double recorrido, String color, String tipoDeCombustible, double precio, String vidrios, String transmision) {
        this(Vehiculo.nextId, idVendedor, new ArrayList<Oferta>(), placa, marca, modelo, tipoDeMotor, ano, recorrido, color, tipoDeCombustible, precio, vidrios, transmision);
    }
    
    public static String[] vidriosYTransmision() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Vidrios:");
        String vidrios = sc.nextLine();
        
        System.out.println("Transmision:");
        String trans = sc.nextLine();
        
        return new String[] {vidrios, trans};
    }
    
    public static void registrarAuto(String[] c, String[] d) {        
        int idVen = Vendedor.validarCredenciales();        
        if (!Archivo.placaYaExiste(VEHICULO_FILE, c[0])) {
            if (idVen != 0) {
                Auto auto = new Auto(Vehiculo.nextId++, idVen, c[0], c[1], c[2], c[3], c[4], Double.parseDouble(c[5]), c[6], c[7], Double.parseDouble(c[8]), d[0], d[1]);
                auto.guardarVehiculoEnArchivo();
            }
        } else {
            System.out.println("Ese vehiculo ya esta registrado.");
        }       
    }
    
    @Override
    public String toString() {
        return super.toString()+","+this.vidrios+","+this.transmision;
    }
        
}
