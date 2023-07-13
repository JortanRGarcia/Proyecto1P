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
public class Comprador extends Usuario{       
    private static final String COMPRADOR_FILE = "compradores.txt";
    
    private static int nextId = cargarUltimoId();
    
    private ArrayList<Oferta> ofertas;
    
    public Comprador(int id, String nombres, String apellidos, String correo, String organizacion, String clave, ArrayList<Oferta> ofertas) {
        super(nextId, nombres, apellidos, correo, organizacion, clave);
        this.ofertas = ofertas;
        guardarVendedorEnArchivo();
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
        if(correoYaExiste(s[2])) {
            System.out.println("El correo ya se encuentra en uso.");
        } else {
            Comprador comprador = new Comprador(nextId, s[0], s[1], s[2], s[3], s[4]);
        }       
    }
       
    private static boolean correoYaExiste(String correo) {
        File archivo = new File(COMPRADOR_FILE);
        if(!archivo.exists()) {
            return false;
        }       
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[3].equals(correo)) {
                    return true;
                }
            }             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean validarCredenciales() {
        Scanner sc = new Scanner(System.in);
        File archivo = new File(COMPRADOR_FILE);
        
        if (archivo.exists()) {
            System.out.println("Ingrese su dirección de correo: ");
            String correo = sc.nextLine();
            System.out.println("Ingrese su contraseña:");
            String clave = sc.nextLine();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes[3].equals(correo)) {
                        if (Utilitaria.convertirHash(clave).equals(partes[5])){
                            System.out.println("Inicio de sesion éxitoso");
                            return true;
                        } else {
                            System.out.println("Contraseña Incorrecta");
                            return false;
                        }
                    }                     
                }
                System.out.println("No se encuentra el correo registrado.");
                return false;
                
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Ha Ocurrido un error.");               
        return false;
    }
    
    private static int cargarUltimoId() {
        File file = new File(COMPRADOR_FILE);
        if (!file.exists()) {
            return 0; 
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lastLine = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;  
            }
            return lastLine != null ? Integer.parseInt(lastLine.split(",")[0]) : 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
       
    
    private void guardarVendedorEnArchivo() {
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
