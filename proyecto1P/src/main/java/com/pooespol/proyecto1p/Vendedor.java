import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Vendedor extends Usuario{
    private final static String VENDEDOR_FILE = "vendedores.txt";
    
    private static int nextId = Archivo.cargarUltimoId(VENDEDOR_FILE);
    
    private ArrayList<Vehiculo> vehiculos;
    
    public Vendedor(int id, String nombres, String apellidos, String correo, String organizacion, String clave, ArrayList<Vehiculo> vehiculos) {
        super(nextId, nombres, apellidos, correo, organizacion, clave);
        this.vehiculos = vehiculos;
        guardarVendedorEnArchivo();
    }
    
    public Vendedor(int id, String nombres, String apellidos, String correo, String organizacion, String clave) {
        this(nextId++, nombres, apellidos, correo, organizacion, clave, new ArrayList<Vehiculo>());
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    public static void registrar(String[] s) {
        if(Archivo.correoYaExiste(VENDEDOR_FILE, s[2])) {
            System.out.println("El correo ya se encuentra en uso.");
        } else {
            Vendedor vendedor = new Vendedor(nextId, s[0], s[1], s[2], s[3], s[4]);
        }       
    }
    
    public static boolean validarCredenciales() {
        return Archivo.validarCredenciales(VENDEDOR_FILE);
    }
    
    private void guardarVendedorEnArchivo() {
        File archivo = new File(VENDEDOR_FILE);
        if(!archivo.exists()) {
            try {
                archivo.createNewFile();                
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(VENDEDOR_FILE, true))) {
            writer.println(this.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
      
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Vehiculo v:this.vehiculos) {
            if(vehiculos.indexOf(v) != vehiculos.size()-1) {
                s.append(v.getId());
                s.append("/");
            } else {
                s.append(v.getId());
            }            
        }
        
        return this.id +","+this.nombres+","+this.apellidos+","+this.correo+","+this.organizacion+","+Utilitaria.convertirHash(this.clave)+","+s;
    }
}

        
