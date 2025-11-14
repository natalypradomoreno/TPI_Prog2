package Vista;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelReportePorZona extends JPanel {
    public PanelReportePorZona() {
        setLayout(new BorderLayout());
        add(new JLabel("Aquí se muestra el reporte de gatos por zona."), BorderLayout.CENTER);
    }
}
