/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natal
 */
package Vista;

import modelo.Gato;
import modelo.Veterinario;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;

public class PanelEmitirCertificado extends JPanel {

    private final JTextField txtCodigoQR;
    private final JTextArea txtResultado;
    private final JButton btnEmitir;
    private final JButton btnCerrar;
    private final Veterinario veterinario; // para sacar matrícula

    public PanelEmitirCertificado(Veterinario vet) {
        this.veterinario = vet;

        setLayout(new BorderLayout(5,5));
        JPanel arriba = new JPanel(new GridLayout(2,2,5,5));

        arriba.add(new JLabel("Código QR del gato:"));
        txtCodigoQR = new JTextField();
        arriba.add(txtCodigoQR);

        btnEmitir = new JButton("Emitir certificado");
        arriba.add(new JLabel());
        arriba.add(btnEmitir);

        add(arriba, BorderLayout.NORTH);

        txtResultado = new JTextArea(6, 30);
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnCerrar = new JButton("Cerrar");
        JPanel abajo = new JPanel();
        abajo.add(btnCerrar);
        add(abajo, BorderLayout.SOUTH);

        btnEmitir.addActionListener(e -> emitirCertificado());
        btnCerrar.addActionListener(e -> {
            // si esto está dentro de un JDialog
            SwingUtilities.getWindowAncestor(PanelEmitirCertificado.this).dispose();
        });
    }

    private void emitirCertificado() {
        String qr = txtCodigoQR.getText().trim();
        if (qr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribí el código QR.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
        EntityManager em = emf.createEntityManager();

        try {
            Gato gato = em.find(Gato.class, qr);
            if (gato == null) {
                txtResultado.setText("No se encontró un gato con ese código QR.");
                return;
            }

            // Comparar estado
            if (!"Sano".equalsIgnoreCase(gato.getEstado())) {
                txtResultado.setText("El gato existe pero no está SANO.\nEstado actual: " + gato.getEstado());
                return;
            }

            em.getTransaction().begin();
            gato.setCertificadoAptitud(true);
            em.merge(gato);
            em.getTransaction().commit();

            String matricula = (veterinario != null) ? veterinario.getMatricula() : "matrícula no informada";

            txtResultado.setText(
                    "CERTIFICADO DE APTITUD\n" +
                    "Gato (código): " + gato.getCodigoQR() + "\n" +
                    "Nombre: " + gato.getNombre() + "\n" +
                    "Se certifica que el animal se encuentra APTO médicamente.\n" +
                    "Veterinario matriculado: " + matricula
            );

        } catch (Exception ex) {
            em.getTransaction().rollback();
            txtResultado.setText("Error al emitir el certificado: " + ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
