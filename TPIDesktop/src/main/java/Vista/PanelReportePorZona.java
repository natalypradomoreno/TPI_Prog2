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


public class PanelReportePorZona extends JPanel {
    public PanelReportePorZona() {
        setLayout(new BorderLayout());
        add(new JLabel("Aquí se muestra el reporte de gatos por zona."), BorderLayout.CENTER);
    }
}
