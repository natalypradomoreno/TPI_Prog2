package Vista;

import Controlador.ControladorVisitas;
import modelo.Hogar;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

/*
 panel que usa el voluntario para registrar una visita de seguimiento.
 la idea es super simple: cargar fecha, ubicacion, estado y ademas asociar
 la visita a un hogar. no se mete con el calendario ni nada raro, eso lo hace
 el controlador.
*/
public class PanelVisita extends JPanel {

    private final JTextField txtFecha;
    private final JTextField txtUbicacion;
    private final JComboBox<String> cbEstado;

    private final JTextField txtIdHogar;
    private final JButton btnBuscarHogar;

    private final JButton btnGuardar;

    private final ControladorVisitas controlador;
    private Hogar hogarSeleccionado;

    public PanelVisita() {
        controlador = new ControladorVisitas();

        /*
         el layout es un grid simple, porque este panel no necesita nada rebuscado.
         solo inputs y botones uno al lado del otro.
        */
        setLayout(new GridLayout(5, 2, 10, 10));

        // campo para la fecha (el formato lo valida el controlador)
        add(new JLabel("Fecha (yyyy-MM-dd):"));
        txtFecha = new JTextField();
        add(txtFecha);

        // ubicacion donde se hizo la visita
        add(new JLabel("Ubicacion:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        // combo para el estado, por ahora solo dos estados
        add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"Pendiente", "Completada"});
        add(cbEstado);

        /*
         parte del hogar: se ingresa un id y se busca ese hogar en base de datos.
         no se carga un combo ni nada, porque el id es lo unico que identifica
         al hogar en el modelo actual.
        */
        add(new JLabel("ID Hogar:"));
        JPanel panelHogar = new JPanel(new BorderLayout());
        txtIdHogar = new JTextField();
        panelHogar.add(txtIdHogar, BorderLayout.CENTER);

        btnBuscarHogar = new JButton("Buscar");
        panelHogar.add(btnBuscarHogar, BorderLayout.EAST);
        add(panelHogar);

        // boton para guardar la visita
        btnGuardar = new JButton("Registrar Visita");
        add(new JLabel()); // espacio en blanco para alinear
        add(btnGuardar);

        // listeners basicos
        btnBuscarHogar.addActionListener(e -> buscarHogar());
        btnGuardar.addActionListener(e -> registrarVisita());
    }

    /*
     cuando el voluntario ingresa un id de hogar y toca buscar, se llama a este metodo.
     lo unico que hace es ir al controlador a consultar el hogar. si existe, se guarda
     en una variable interna y se muestra un mensaje. si no, avisa que no existe.
    */
    private void buscarHogar() {
        try {
            int id = Integer.parseInt(txtIdHogar.getText().trim());
            hogarSeleccionado = controlador.buscarHogar(id);

            if (hogarSeleccionado != null) {
                JOptionPane.showMessageDialog(this,
                        "Hogar encontrado:\nDireccion: " + hogarSeleccionado.getDireccion());
            } else {
                JOptionPane.showMessageDialog(this,
                        "No existe un hogar con ese ID.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un numero entero.");
        }
    }

    /*
     este metodo termina el registro de la visita.
     valida los campos, arma el Date sql, y le pide al controlador que guarde
     la visita junto con el hogar previamente buscado.
    */
    private void registrarVisita() {
        try {
            if (hogarSeleccionado == null) {
                JOptionPane.showMessageDialog(this,
                        "Debes seleccionar un hogar antes de registrar la visita.");
                return;
            }

            String fechaTxt = txtFecha.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            String estado = (String) cbEstado.getSelectedItem();

            if (fechaTxt.isEmpty() || ubicacion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            // convertir texto a fecha sql
            Date fechaSQL = Date.valueOf(fechaTxt);

            boolean ok = controlador.crearVisita(estado, fechaSQL, ubicacion, hogarSeleccionado);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Visita registrada correctamente.");
                txtFecha.setText("");
                txtUbicacion.setText("");
                txtIdHogar.setText("");
                hogarSeleccionado = null;
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la visita.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Formato incorrecto de fecha. Debe ser yyyy-MM-dd.");
        }
    }
}
