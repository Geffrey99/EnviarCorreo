import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("Interfaz");
            frame.setContentPane(new Interfaz().JpanelAplicacion);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al inicializar la interfaz: " + e.getMessage());
        }
    }

    //Okkk maiiinn 😀✔️
}