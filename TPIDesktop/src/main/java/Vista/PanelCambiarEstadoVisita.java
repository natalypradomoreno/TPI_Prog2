package Vista;

import Controlador.ControladorVisitas;
import modelo.VisitaDeSeguimiento;
import modelo.Hogar;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

// este panel es para que el voluntario pueda ver las visitas que todavia estan pendientes
// y cambiarles el estado a realizada. la idea es que todo sea bien directo:
// lista de visitas -> seleccionas una -> la cambias de estado
public class PanelCambiarEstadoVisita extends JPanel {

    private final JList<String> listaVisitas;
    private final JComboBox<String> cbEstado;
    private final JButton btnGuardar;

    private final DefaultListModel<String> modeloLista;
    private final ControladorVisitas controlador;

    // esta lista guarda las visitas reales, asi podemos editar la correcta
    private List<VisitaDeSeguimiento> visitasPendientes;

    public PanelCambiarEstadoVisita() {
        controlador = new ControladorVisitas();
        setLayout(new BorderLayout(10, 10));

        // titulo arriba para separar visualmente, nada raro
        JLabel titulo = new JLabel("Cambiar estado de visita");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        // panel del centro: muestra las visitas pendientes en una lista
        // se usa DefaultListModel porque es facil de actualizar
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createTitledBorder("Visitas Pendientes"));

        modeloLista = new DefaultListModel<>();
        listaVisitas = new JList<>(modeloLista);
        panelCentro.add(new JScrollPane(listaVisitas), BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        // panel de abajo para elegir el nuevo estado y guardar
        // por ahora solo existe "Realizada", pero queda preparado para agregar mas
        JPanel panelAbajo = new JPanel(new GridLayout(2, 2, 5, 5));
        panelAbajo.add(new JLabel("Nuevo estado:"));

        cbEstado = new JComboBox<>(new String[]{"Realizada"});
        panelAbajo.add(cbEstado);

        btnGuardar = new JButton("Guardar Cambios");
        panelAbajo.add(new JLabel()); // espacio vacio para alinear
        panelAbajo.add(btnGuardar);

        add(panelAbajo, BorderLayout.SOUTH);

        // cuando se toca guardar, se actualiza el estado de la visita seleccionada
        btnGuardar.addActionListener(e -> actualizarEstado());

        // carga inicial de las visitas pendientes
        cargarVisitasPendientes();
    }

    // este metodo arma el contenido legible de la lista:
    // fecha en formato dd/MM/yyyy y la direccion del hogar si tiene
    private void cargarVisitasPendientes() {
        modeloLista.clear();

        visitasPendientes = controlador.obtenerVisitasPendientes();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (VisitaDeSeguimiento v : visitasPendientes) {

            String fechaFormateada =
                    v.getFecha() != null ? formato.format(v.getFecha()) : "Sin fecha";

            String direccionHogar = "Sin hogar asignado";
            if (v.getHogar() != null && v.getHogar().getDireccion() != null) {
                direccionHogar = v.getHogar().getDireccion();
            }

            // se arma un texto claro para mostrar en la lista
            String linea =
                    "ID: " + v.getIdVisita() +
                    " | Fecha: " + fechaFormateada +
                    " | Ubicacion: " + v.getUbicacion() +
                    " | Hogar: " + direccionHogar;

            modeloLista.addElement(linea);
        }
    }

    // agarra el item seleccionado, busca la visita real y llama al controlador
    // para actualizar el estado en la base. despues refresca la lista
    private void actualizarEstado() {
        int index = listaVisitas.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una visita", "Atencion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        VisitaDeSeguimiento visita = visitasPendientes.get(index);
        String nuevoEstado = (String) cbEstado.getSelectedItem();

        boolean ok = controlador.actualizarEstadoVisita(visita.getIdVisita(), nuevoEstado);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Estado actualizado correctamente");
            cargarVisitasPendientes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la visita", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
