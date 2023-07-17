package com.pooespol.proyecto1p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;


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
            throw new RuntimeException(e);
        }
        return false;
    }
    
    public static boolean placaYaExiste(String file, String placa) {
        File archivo = new File(file);
        if(!archivo.exists()) {
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[3].equals(placa)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
    public static boolean validarCredenciales(String file, String correo, String clave) {        
        File archivo = new File(file);
        
        if (archivo.exists()) {
            
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
    
    public static int buscarIdUsuario(String correo, String file) {
        File archivo = new File(file);
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes[3].equals(correo)) {
                        return Integer.parseInt(partes[0]);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }
    
    
    public static ArrayList<String[]> listaLinea(String file) {
        ArrayList<String[]> lineasSplit = new ArrayList<>();
        
        File archivo = new File(file);
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                lineasSplit.add(partes);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineasSplit;
    }
    
    public static String buscarCorreo(String file, String idUsuario) {
        File archivo = new File(file);
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while((linea = reader.readLine())!= null) {
                    String[] partes = linea.split(",");
                    if (idUsuario.equals(partes[0]));
                    return partes[3];
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    
    public static void guardarListaLinea(String archivo, ArrayList<String[]> lista) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String[] linea : lista) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < linea.length; i++) {
                    sb.append(linea[i]);
                    if (i < linea.length - 1) {
                        sb.append(",");
                    }
                }
                writer.write(sb.toString());
                writer.newLine();
            }   
        }
    }
}
