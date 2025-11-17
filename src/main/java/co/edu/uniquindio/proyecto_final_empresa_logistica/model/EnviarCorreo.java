package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;


public class EnviarCorreo {
    private final String username;
    private final String password;

    public EnviarCorreo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void enviarCorreo(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            JOptionPane.showMessageDialog(null,
                    "Correo enviado exitosamente a " + to,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al enviar el correo:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
