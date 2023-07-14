package com.pooespol.proyecto1p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Archivo {
    
   public static int cargarUltimoId(String file) {
        File archivo = new File(file);
        if (!archivo.exists()) {
            return 0; 
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
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
   
    public static boolean correoYaExiste(String file, String correo) {
        File archivo = new File(file);
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
    
    public static boolean validarCredenciales(String file) {
        Scanner sc = new Scanner(System.in);
        File archivo = new File(file);
        
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
}
