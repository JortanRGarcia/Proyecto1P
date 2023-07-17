
package com.pooespol.proyecto1p;

import java.util.Scanner;


public abstract class Usuario {
    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String correo;
    protected String organizacion;
    protected String clave;

    public Usuario(int id, String nombres, String apellidos, String correo, String organizacion, String clave) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.organizacion = organizacion;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
        

    public static String[] registrar(Scanner sc) {
        sc.nextLine();
        System.out.println("Ingrese sus nombres: ");
        String nombres = sc.nextLine();
        System.out.println("Ingrese sus apellidos: ");
        String apellidos = sc.nextLine();
        System.out.println("Ingrese su correo: ");
        String correo = sc.nextLine();
        System.out.println("Ingrese su organizacion: ");
        String org = sc.nextLine();
        System.out.println("Ingrese su clave:");
        String clave = sc.nextLine();  
        
        return new String[] {nombres,apellidos,correo,org,clave};
   }
}
