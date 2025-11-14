
package Vista;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelReporteEsterilizacion extends JPanel {
    public PanelReporteEsterilizacion() {
        setLayout(new BorderLayout());
        add(new JLabel("Aquí se muestra el reporte general de gatos (esterilización, etc.)."), BorderLayout.CENTER);
    }
}
