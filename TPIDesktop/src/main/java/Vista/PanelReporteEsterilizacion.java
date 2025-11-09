/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author natal
 */


public class PanelReporteEsterilizacion extends JPanel {
    public PanelReporteEsterilizacion() {
        setLayout(new BorderLayout());
        add(new JLabel("Aquí se muestra el reporte general de gatos (esterilización, etc.)."), BorderLayout.CENTER);
    }
}
