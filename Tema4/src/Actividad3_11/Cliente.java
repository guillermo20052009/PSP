package Actividad3_11;

import Actividad3_8.Persona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends JFrame implements ActionListener, Runnable {
    private static final long serialVersionUID = 1L;
    Socket socket = null;

    DataInputStream dis;
    DataOutputStream dos;

    String nombre;
    static JTextField mensaje = new JTextField();
    private JScrollPane scrollPanel;
    static JTextArea textArea;
    JButton boton = new JButton("Enviar");
    JButton boton2 = new JButton("Salir");
    boolean repetir = true;

    public Cliente(Socket socket, String nombre) {
        super("Cliente " + nombre);
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        textArea = new JTextArea();
        scrollPanel = new JScrollPane(textArea);
        scrollPanel.setBounds(10, 50, 400, 300);
        add(scrollPanel);

        boton.setBounds(420, 10, 100, 30);
        add(boton);
        boton2.setBounds(420, 50, 100, 30);
        add(boton2);

        textArea.setEditable(false);
        boton.addActionListener(this);
        boton2.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.socket = socket;
        this.nombre = nombre;
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            String texto = "> Entra en el chat ... " + nombre;
            dos.writeUTF(texto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton) {
            if (mensaje.getText().trim().length() == 0)
                return;
            String texto = nombre + "> " + mensaje.getText();

            try {
                mensaje.setText("");
                dos.writeUTF(texto);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == boton2) {
            String texto = " > Abandona el Chat ... " + nombre;
            try {
                dos.writeUTF(texto);
                dos.writeUTF("*");
                repetir = false;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void run() {
        String texto = "";
        while (repetir) {
            try {
                texto = dis.readUTF();
                textArea.setText(texto);
            } catch (IOException e) {
                e.printStackTrace();
                repetir = false;
            }
        }
        try {
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int puerto = 44444;
        Socket socket = null;
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick");

        if (nombre.trim().length() == 0) {
            System.out.println("El nombre no puede estar vacío");
            return;
        }
        try {
            socket = new Socket("localhost", puerto);
            Cliente cliente = new Cliente(socket, nombre);
            cliente.setBounds(0, 0, 540, 400);
            cliente.setVisible(true);
            new Thread(cliente).start();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
