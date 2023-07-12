package com.pooespol.proyecto1p;

import java.util.ArrayList;

public class Comprador {
    private Arraylist<Oferta> ofertas;

    public Comprador(int id, , String nombres, String apellidos, String correo, String organizacion, String clave, ArrayList<Oferta> ofertas) {
        super(id, nombres, apellidos, correo, organizacion, clave);
        this.ofertas = ofertas;
    }

    public ArrayList<Oferta> getOfertas() {
        return this.ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public boolean validarCredenciales() {
        
    }
}
