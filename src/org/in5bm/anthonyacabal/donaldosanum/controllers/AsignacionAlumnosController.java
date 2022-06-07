package org.in5bm.anthonyacabal.donaldosanum.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.in5bm.anthonyacabal.donaldosanum.db.Conexion;

import org.in5bm.anthonyacabal.donaldosanum.system.Principal;
import org.in5bm.anthonyacabal.donaldosanum.models.AsignacionesAlumnos;
import org.in5bm.anthonyacabal.donaldosanum.models.Alumnos;
import org.in5bm.anthonyacabal.donaldosanum.models.Cursos;

/**
 *
 * @author Anthony Acabal
 */
public class AsignacionAlumnosController implements Initializable {
    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }

    private Operacion operacion = Operacion.NINGUNO;

    private Principal escenarioPrincipal;

    @FXML
    private ImageView imgNuevo;
    
    @FXML
    private Button btnNuevo;
    
    @FXML
    private ImageView imgModificar;
    
    @FXML
    private Button btnModificar;
    
    @FXML
    private ImageView imgEliminar;
    
    @FXML
    private Button btnEliminar;
    
    @FXML
    private ImageView imgReporte;
    
    @FXML
    private Button btnReporte;
    
    @FXML
    private TextField txtAlumno;
    
    
    @FXML
    private TableView<AsignacionesAlumnos> tblAsignacionesAlumnos;
    
    @FXML
    private TableColumn <AsignacionesAlumnos, Integer> colId;
    
    @FXML
    private TableColumn <AsignacionesAlumnos, String> colCarne;
    
    @FXML
    private TableColumn <Alumnos, String> colNombreAlumno;
    
    @FXML
    private TableColumn <AsignacionesAlumnos, Integer> colCurso;
    
    @FXML
    private TableColumn <Cursos, String> colNombreCurso;
    
    @FXML
    private TableColumn <AsignacionesAlumnos, LocalDate> colFechaAsignacion;
    
    @FXML
    private ComboBox <Alumnos> cmbCarne;
    
    @FXML
    private ComboBox <Cursos> cmbCurso;
    
    @FXML
    public void clicRegresar(MouseEvent event) {
        escenarioPrincipal.mostrarEscenaPrincipal();
    }
    
    private final String PAQUETE_IMAGES = "org/in5bm/anthonyacabal/donaldosanum/resources/images/";
    
    @FXML
    private ObservableList<AsignacionesAlumnos> listaAsignacionAlumnos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    /*public void cargarDatos() {
        tblAsignacionAlumnos.setItems(getAsignacionAlumnos());
        colId.setCellValueFactory(new PropertyValueFactory<AsignacionAlumnos, String>("id"));
        colNombre1.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre1"));
        colNombre2.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre2"));
        colNombre3.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre3"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellido2"));
    }*/
    
    public ObservableList getAsignacionAlumnos() {

        ArrayList<AsignacionesAlumnos> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = Conexion.getInstance().getConexion().prepareCall("call sp_asignacion_alumnos_read()");
            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AsignacionesAlumnos asignacionAlumnos = new AsignacionesAlumnos();

                asignacionAlumnos.setId(IntegerProperty.integerProperty((Property<Integer>) rs.getObject(1)));
                asignacionAlumnos.setAlumnoId((StringProperty) StringProperty.stringExpression((Property<String>)rs.getObject(2)));
                asignacionAlumnos.setCursoId(IntegerProperty.integerProperty((Property<Integer>) rs.getObject(3)));
                //asignacionAlumnos.setFechaAsignacion(ObjectProperty.objectExpression((Property<Object>)rs.getObject(4)));

                lista.add(asignacionAlumnos);

                System.out.println(asignacionAlumnos.toString());
            }

            listaAsignacionAlumnos = FXCollections.observableArrayList(lista);

        } catch (SQLException e) {
            System.err.println("\n Se produjo un error al intentar consultar la lista de Alumnos");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error code: " + e.getErrorCode());
            System.out.println("SQLState: " + e.getSQLState());
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listaAsignacionAlumnos;
    }

    @FXML
    private void clicNuevo(ActionEvent event) {
    }

    @FXML
    private void clicModificar(ActionEvent event) {
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
    }

    @FXML
    private void clicReporte(ActionEvent event) {
    }

    @FXML
    private void seleccionarElemento(MouseEvent event) {
    }

    @FXML
    private void seleccionarElemento(KeyEvent event) {
    }
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
}
