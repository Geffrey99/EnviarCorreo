import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Interfaz {
    private JTextField textEmailRemitente;
    private JPasswordField password;
    private JTextField textAsunto;
    private JTextArea textAreaMensaje;
    private JButton CANCELARButton;
    private JButton ENVIARButton;
    public JPanel JpanelAplicacion;
    private JTextField textFieldCorreoRec;

    public Interfaz() {
       //JpanelAplicacion = new JPanel();
        //JpanelAplicacion.setLayout(new BoxLayout(JpanelAplicacion, BoxLayout.Y_AXIS));


        textEmailRemitente = new JTextField();
        password = new JPasswordField();
        textAsunto = new JTextField();
        textAreaMensaje = new JTextArea();
        textFieldCorreoRec = new JTextField();
        CANCELARButton = new JButton("CANCELAR");
        ENVIARButton = new JButton("ENVIAR");

// Okkk mejooooor diseño --------------------------------------------- ✔️
        JpanelAplicacion = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Enviar Correo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JpanelAplicacion.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        JpanelAplicacion.add(new JLabel("Email Remitente:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        textEmailRemitente = new JTextField(20);
        JpanelAplicacion.add(textEmailRemitente, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        JpanelAplicacion.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        password = new JPasswordField(20);
        JpanelAplicacion.add(password, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        JpanelAplicacion.add(new JLabel("Asunto:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        textAsunto = new JTextField(20);
        JpanelAplicacion.add(textAsunto, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        JpanelAplicacion.add(new JLabel("Mensaje:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        textAreaMensaje = new JTextArea(5, 20);
        JpanelAplicacion.add(new JScrollPane(textAreaMensaje), gbc);
        gbc.gridheight = 1;


        gbc.gridx = 0;
        gbc.gridy = 6;
        JpanelAplicacion.add(new JLabel("Email Receptor:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        textFieldCorreoRec = new JTextField(20);
        JpanelAplicacion.add(textFieldCorreoRec, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        JpanelAplicacion.add(ENVIARButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        JpanelAplicacion.add(CANCELARButton, gbc);

        /*
        JpanelAplicacion.add(new JLabel("Contraseña:"));
        JpanelAplicacion.add(password);
        JpanelAplicacion.add(new JLabel("Asunto:"));
        JpanelAplicacion.add(textAsunto);
        JpanelAplicacion.add(new JLabel("Mensaje:"));
        JpanelAplicacion.add(new JScrollPane(textAreaMensaje));
        JpanelAplicacion.add(new JLabel("Email Receptor:"));
        JpanelAplicacion.add(textFieldCorreoRec);
        JpanelAplicacion.add(ENVIARButton);
        JpanelAplicacion.add(CANCELARButton);
*/
        // LLAMO AL METODO DE ACEPTAR O CANCELAR

        configurarAcciones();
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void enviarCorreo(String emailRemitente, String passwordRemitente, String emailReceptor, String asunto, String mensaje) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        if (emailRemitente.endsWith("@educamadrid.org")) {
            props.put("mail.smtp.host", "smtp.educamadrid.org");
        } else {
            props.put("mail.smtp.host", "smtp.gmail.com");
        }

        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailRemitente, passwordRemitente);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailRemitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailReceptor));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
            JOptionPane.showMessageDialog(JpanelAplicacion, "Mensaje enviado");
            textEmailRemitente.setText("");
            password.setText("");
            textAsunto.setText("");
            textAreaMensaje.setText("");
            textFieldCorreoRec.setText("");

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(JpanelAplicacion, "Error al enviar el mensaje: " + e.getMessage());
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    // Accionessss para enviar o cancelar --------------------------------------------- All rightttt Okkkkk ✔️✔️✔️✔️

    private void configurarAcciones() {
        ENVIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailRemitente = textEmailRemitente.getText();
                String passwordRemitente = new String(password.getPassword());
                String asunto = textAsunto.getText();
                String mensaje = textAreaMensaje.getText();
                String emailReceptor = textFieldCorreoRec.getText();

                if (!isValidEmail(emailRemitente) || !isValidEmail(emailReceptor)) {
                    JOptionPane.showMessageDialog(JpanelAplicacion, "Por favor, introduce correos electrónicos válidos.");
                    return;
                }

                enviarCorreo(emailRemitente, passwordRemitente, emailReceptor, asunto, mensaje);
            }
        });

        CANCELARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textEmailRemitente.setText("");
                password.setText("");
                textAsunto.setText("");
                textAreaMensaje.setText("");
                textFieldCorreoRec.setText("");
            }
        });
    }
}
