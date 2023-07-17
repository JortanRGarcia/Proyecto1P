package com.pooespol.proyecto1p;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class Utilitaria {
    
    public static String convertirHash(String clave) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(clave.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    public static void enviarCorreo(String correoComprador, int idVendedor) {
        Scanner sc = new Scanner(System.in);
        
        String remitente = "dbaque220@gmail.com";
        String claveemail = "fgxgpoywqdxpilju";
        
        System.out.println("Ingrese el asunto del correo:");
        String asunto = sc.nextLine();

        System.out.println("Ingrese el contenido del correo:");
        String contenido = sc.nextLine();


        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true"); 
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.user", remitente);
        properties.put("mail.smtp.clave", claveemail);
        properties.put("mail.smtp.auth", "true");
              
       
        Session session = Session.getInstance(properties);
        MimeMessage message = new MimeMessage(session);
        try {
            

            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(correoComprador));
            message.setSubject(asunto);
            message.setText(contenido);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com",remitente, claveemail);
            Transport.send(message,message.getAllRecipients());
            transport.close();
            System.out.println("El correo ha sido enviado exitosamente.");
        } catch (MessagingException e) {
            System.out.println("Ocurrió un error al enviar el correo: " + e.getMessage());
        }
    }

    private static String obtenerServidorSMTP(String correo) {
        String dominio = correo.substring(correo.indexOf('@') + 1);
        return "smtp." + dominio;
    }
}
