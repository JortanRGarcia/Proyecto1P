package com.pooespol.proyecto1p;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Comprador extends Usuario{       
    private static final String COMPRADOR_FILE = "compradores.txt";
    
    private static int nextId = Archivo.cargarUltimoId(COMPRADOR_FILE);
    
    private ArrayList<Oferta> ofertas;
    
    public Comprador(int id, String nombres, String apellidos, String correo, String organizacion, String clave, ArrayList<Oferta> ofertas) {
        super(nextId++, nombres, apellidos, correo, organizacion, clave);
        this.ofertas = ofertas;
        guardarCompradorEnArchivo();
    }
    
    public Comprador(int id, String nombres, String apellidos, String correo, String organizacion, String clave) {
        this(nextId++, nombres, apellidos, correo, organizacion, clave, new ArrayList<Oferta>());
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
       
    
    public static void registrar(String[] s) {
        if(Archivo.correoYaExiste(COMPRADOR_FILE, s[2])) {
            System.out.println("El correo ya se encuentra en uso.");
        } else {
            Comprador comprador = new Comprador(nextId, s[0], s[1], s[2], s[3], s[4]);
        }       
    }
              
    public static int validarCredenciales() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese su dirección de correo: ");
        String correo = sc.nextLine();
        System.out.println("Ingrese su contraseña:");
        String clave = sc.nextLine();
        if (Archivo.validarCredenciales(COMPRADOR_FILE, correo, clave)) {
            return Archivo.buscarIdUsuario(correo, COMPRADOR_FILE);
        }
        return 0;
    }
                
    private void guardarCompradorEnArchivo() {
        File archivo = new File(COMPRADOR_FILE);
        if(!archivo.exists()) {
            try {
                archivo.createNewFile();                
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(COMPRADOR_FILE, true))) {
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
        return this.id +","+this.nombres+","+this.apellidos+","+this.correo+","+this.organizacion+","+Utilitaria.convertirHash(this.clave)+","+s;
    }       
}
