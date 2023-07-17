package com.pooespol.proyecto1p;

import java.util.Scanner;
import java.io.IOException;

public class Proyecto1p {

    public static void main(String[] args)throws IOException {
        
        Scanner sc = new Scanner(System.in);
        int op;
        int op2;        
        
        do {
            System.out.println("""
                               Menu de opciones:
                               1. Vendedor
                               2. Comprador
                               3. Salir""");
            
            op = sc.nextInt();
            
            switch(op) {
                case 1:
                    System.out.println("""
                                       1. Registrar un nuevo vendedor
                                       2. Registrar un nuevo vehiculo
                                       3. Aceptar oferta
                                       4. Regresar""");
                    op2 = sc.nextInt();
                    switch(op2) {
                        case 1:
                            Vendedor.registrar(Usuario.registrar(sc));
                            break;
                        case 2:
                            sc.nextLine();
                            System.out.println("¿Cuál es el vehiculo que quiere registrar? (auto, camioneta, otro)");
                            String tipo = sc.nextLine();                           
                            if (tipo.equals("auto")) {
                                System.out.println("Registrar auto");
                                Auto.registrarAuto(Vehiculo.registrar(sc), Auto.vidriosYTransmision());
                            } else if(tipo.equals("Registrar camioneta")) {
                                Camioneta.registrarCamioneta(Vehiculo.registrar(sc), Auto.vidriosYTransmision());
                            } else {
                                System.out.println("Registrar moto");
                                Vehiculo.registrarVehiculo(Vehiculo.registrar(sc));
                            }                           
                            break;

                        case 3:
                            Oferta.menuOferta();
                            break;
                        case 4:                           
                            break;
                        default:
                            break;
                    }
                    break;
                
                case 2:
                    System.out.println("""
                                       1. Registrar un nuevo comprador
                                       2. ofertar por un vehiculo
                                       3. Regresar""");
                    op2 = sc.nextInt();
                    switch(op2) {
                        case 1:
                            Comprador.registrar(Usuario.registrar(sc));                           
                            break;
                        case 2:
                            Oferta.realizarOferta(Vehiculo.mostrarVehiculos(), Comprador.validarCredenciales());
                            break;
                        case 3:                            
                            break;                        
                        default:
                            break;
                    }
                    break;
                    
                case 3:   
                    break;               
                default:
                    break;
            }
            
        } while (op != 3);       
    }
}

