package org.in5bm.anthonyacabal.donaldosanum.controllers;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.in5bm.anthonyacabal.donaldosanum.db.Conexion;

import org.in5bm.anthonyacabal.donaldosanum.models.Horarios;

import org.in5bm.anthonyacabal.donaldosanum.system.Principal;

/**
 *
 * @author Anthony Acabal
 * @date 6/06/2022
 * @time 18:38:21
 *
 * Código técnico: IN5BM
 */
public class HorariosController implements Initializable {

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
    private TextField txtId;
    
    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colHorarioInicio;

    @FXML
    private TableColumn colHorarioFinal;

    @FXML
    private TableColumn colLunes;

    @FXML
    private TableColumn colMartes;

    @FXML
    private TableColumn colMiercoles;

    @FXML
    private TableColumn colJueves;

    @FXML
    private TableColumn colViernes;
    
    @FXML
    private JFXTimePicker tpkHorarioInicio;

    @FXML
    private JFXTimePicker tpkHorarioFinal;

    @FXML
    private CheckBox cbLunes;

    @FXML
    private CheckBox cbMartes;

    @FXML
    private CheckBox cbMiercoles;

    @FXML
    private CheckBox cbJueves;

    @FXML
    private CheckBox cbViernes;
    
    @FXML
    private TableView<Horarios> tblHorarios;
    
    @FXML
    private ObservableList<Horarios> listaHorarios;
    
    private final String PAQUETE_IMAGES = "org/in5bm/anthonyacabal/donaldosanum/resources/images/";
    
    
    @FXML
    public void clicRegresar(MouseEvent event) {
        escenarioPrincipal.mostrarEscenaPrincipal();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void cargarDatos() {
        tblHorarios.setItems(getHorarios());
        colId.setCellValueFactory(new PropertyValueFactory<Horarios, Integer>("id"));
        colHorarioInicio.setCellValueFactory(new PropertyValueFactory<Horarios, String>("horarioInicio"));
        colHorarioFinal.setCellValueFactory(new PropertyValueFactory<Horarios, String>("horarioFinal"));
        colLunes.setCellValueFactory(new PropertyValueFactory<Horarios, String>("lunes"));
        colMartes.setCellValueFactory(new PropertyValueFactory<Horarios, String>("martes"));
        colMiercoles.setCellValueFactory(new PropertyValueFactory<Horarios, String>("miercoles"));
        colJueves.setCellValueFactory(new PropertyValueFactory<Horarios, String>("jueves"));
        colViernes.setCellValueFactory(new PropertyValueFactory<Horarios, String>("viernes"));

    }
    
    public boolean existeElementoSeleccionado() {
        return (tblHorarios.getSelectionModel().getSelectedItem() != null);
    }
    
    @FXML
    public void seleccionarElemento() {
        if (existeElementoSeleccionado()) {
            txtId.setText(String.valueOf(((Horarios) tblHorarios.getSelectionModel().getSelectedItem()).getId()));

            tpkHorarioInicio.setValue(((Horarios) tblHorarios.getSelectionModel().getSelectedItem()).getHorarioInicio());

            tpkHorarioFinal.setValue(((Horarios) tblHorarios.getSelectionModel().getSelectedItem()).getHorarioFinal());

            cbLunes.setSelected(((Horarios) tblHorarios.getSelectionModel().getSelectedItem()).getLunes());

            cbMartes.setSelected(((Horarios) tblHorarios.getSelectionModel().getSelectedItem()).getMartes());

            cbMiercoles.setSelected(((Horarios) tblHorarios.getSelectionModel().getSelectedItem()).getMiercoles());

            cbJueves.setSelected(((Horarios) tblHorarios.getSelectionModel().getSelectedItem()).getJueves());
            
            cbViernes.setSelected(((Horarios) tblHorarios.getSelectionModel().getSelectedItem()).getViernes());
        }

    }
    
    public boolean agregarHorario() {
        Horarios horario = new Horarios();
        horario.setHorarioInicio(tpkHorarioInicio.getValue());
        horario.setHorarioFinal(tpkHorarioFinal.getValue());
        horario.setLunes(cbLunes.isSelected());
        horario.setMartes(cbMartes.isSelected());
        horario.setMiercoles(cbMiercoles.isSelected());
        horario.setJueves(cbJueves.isSelected());
        horario.setViernes(cbViernes.isSelected());

        PreparedStatement pstmt = null;

        try {

            pstmt = Conexion.getInstance().getConexion().prepareCall("{call sp_horarios_create(?, ?, ?, ?, ?, ?, ?)}");

            pstmt.setObject(1, horario.getHorarioInicio());
            pstmt.setObject(2, horario.getHorarioFinal());
            pstmt.setBoolean(3, horario.getLunes());
            pstmt.setBoolean(4, horario.getMartes());
            pstmt.setBoolean(5, horario.getMiercoles());
            pstmt.setBoolean(6, horario.getJueves());
            pstmt.setBoolean(7, horario.getViernes());

            System.out.println(pstmt.toString());
            pstmt.execute();
            listaHorarios.add(horario);

            return true;

        } catch (SQLException e) {
            System.err.println("\n Se produjo un error al intentar insertar el siguiente alumno: " + horario.toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;

    }
    
    public boolean actualizarHorario() {
        Horarios horario = new Horarios();
        horario.setId(Integer.valueOf(txtId.getText()));
        horario.setHorarioInicio(tpkHorarioInicio.getValue());
        horario.setHorarioFinal(tpkHorarioFinal.getValue());
        horario.setLunes(cbLunes.isSelected());
        horario.setMartes(cbMartes.isSelected());
        horario.setMiercoles(cbMiercoles.isSelected());
        horario.setJueves(cbJueves.isSelected());
        horario.setViernes(cbViernes.isSelected());

        PreparedStatement pstmt = null;

        try {

            pstmt = Conexion.getInstance().getConexion().prepareCall("{call sp_horarios_update(?, ?, ?, ?, ?, ?, ?, ?)}");
            
            pstmt.setInt(1, horario.getId());
            pstmt.setObject(2, horario.getHorarioInicio());
            pstmt.setObject(3, horario.getHorarioFinal());
            pstmt.setBoolean(4, horario.getLunes());
            pstmt.setBoolean(5, horario.getMartes());
            pstmt.setBoolean(6, horario.getMiercoles());
            pstmt.setBoolean(7, horario.getJueves());
            pstmt.setBoolean(8, horario.getViernes());

            System.out.println(pstmt.toString());
            pstmt.execute();
            listaHorarios.add(horario);

            return true;

        } catch (SQLException e) {
            System.err.println("\n Se produjo un error al intentar insertar el siguiente horario: " + horario.toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;

    }
    
    private boolean eliminarHorario() {
        if (existeElementoSeleccionado()) {
            Horarios horario = (Horarios) tblHorarios.getSelectionModel().getSelectedItem();
            System.out.println(horario.toString());

            PreparedStatement pstmt = null;
            try {
                pstmt = Conexion.getInstance().getConexion().prepareCall("{call sp_horarios_delete(?)}");
                pstmt.setInt(1, horario.getId());
                System.out.println(pstmt.toString());

                pstmt.execute();
                return true;

            } catch (SQLException e) {
                System.err.println("\nSe produjo un error al intentar eliminar el siguiente horario: " + horario.toString());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }
    
    public ObservableList getHorarios() {

        ArrayList<Horarios> lista = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = Conexion.getInstance().getConexion().prepareCall("call sp_horarios_read()");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Horarios horarios = new Horarios();

                horarios.setId(rs.getInt(1));
                horarios.setHorarioInicio(rs.getTime(2).toLocalTime());
                horarios.setHorarioFinal(rs.getTime(3).toLocalTime());
                horarios.setLunes(rs.getBoolean(4));
                horarios.setMartes(rs.getBoolean(5));
                horarios.setMiercoles(rs.getBoolean(6));
                horarios.setJueves(rs.getBoolean(7));
                horarios.setViernes(rs.getBoolean(8));
                lista.add(horarios);

                System.out.println(horarios.toString());
            }

            listaHorarios = FXCollections.observableArrayList(lista);

        } catch (SQLException e) {
            System.err.println("\n Se produjo un error al intentar consultar la lista de Horarios");
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

        return listaHorarios;
    }
    
    private void habilitarCampos() {
        txtId.setEditable(false);
        tpkHorarioInicio.setEditable(true);
        tpkHorarioFinal.setEditable(true);
        cbLunes.isSelected();
        cbMartes.isSelected();
        cbMiercoles.isSelected();
        cbJueves.isSelected();
        cbViernes.isSelected();

        txtId.setDisable(true);
        tpkHorarioInicio.setDisable(false);
        tpkHorarioFinal.setDisable(false);
        cbLunes.setDisable(false);
        cbMartes.setDisable(false);
        cbMiercoles.setDisable(false);
        cbJueves.setDisable(false);
        cbViernes.setDisable(false);
    }

    private void deshabilitarCampos() {
        txtId.setEditable(false);
        cbLunes.setSelected(false);
        cbMartes.setSelected(false);
        cbMiercoles.setSelected(false);
        cbJueves.setSelected(false);
        cbViernes.setSelected(false);

        txtId.setDisable(true);
        tpkHorarioInicio.setDisable(true);
        tpkHorarioFinal.setDisable(true);
        cbLunes.setDisable(true);
        cbMartes.setDisable(true);
        cbMiercoles.setDisable(true);
        cbJueves.setDisable(true);
        cbViernes.setDisable(true);
    }

    private void limpiarCampos() {
        txtId.clear();
        tpkHorarioInicio.getEditor().clear();
        tpkHorarioFinal.getEditor().clear();
        cbLunes.setSelected(false);
        cbMartes.setSelected(false);
        cbMiercoles.setSelected(false);
        cbJueves.setSelected(false);
        cbViernes.setSelected(false);

    }
    
    @FXML
    private void clicNuevo() {

        switch (operacion) {
            case NINGUNO:
                habilitarCampos();
                tblHorarios.setDisable(true);

                txtId.setEditable(false);
                txtId.setDisable(true);

                limpiarCampos();
                btnNuevo.setText("Guardar");
                imgNuevo.setImage(new Image(PAQUETE_IMAGES + "guardar.png"));

                btnModificar.setText("Cancelar");
                imgModificar.setImage(new Image(PAQUETE_IMAGES + "cancelar.png"));

                btnEliminar.setDisable(true);
                imgEliminar.setDisable(true);

                btnEliminar.setVisible(false);
                imgEliminar.setVisible(false);

                btnReporte.setDisable(true);
                imgReporte.setDisable(true);

                btnReporte.setVisible(false);
                imgReporte.setVisible(false);

                operacion = Operacion.GUARDAR;
                break;

            case GUARDAR:

                if (tpkHorarioInicio.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Control Académico Kinal");
                    alert.setHeaderText(null);
                    alert.setContentText("El Horario Inicio es obligatorio");
                    Stage dialog = new Stage();
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
                    alert.show();

                } else if (tpkHorarioFinal.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Control Académico Kinal");
                    alert.setHeaderText(null);
                    alert.setContentText("El campo Horario Final es obligatorio");
                    Stage dialog = new Stage();
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
                    alert.show();
                } else {
                    if (agregarHorario()) {
                        cargarDatos();
                        limpiarCampos();
                        deshabilitarCampos();
                        tblHorarios.setDisable(false);

                        btnNuevo.setText("Nuevo");
                        imgNuevo.setImage(new Image(PAQUETE_IMAGES + "agregar.png"));

                        btnModificar.setText("Modificar");
                        imgModificar.setImage(new Image(PAQUETE_IMAGES + "editar.png"));

                        btnEliminar.setDisable(false);
                        imgEliminar.setDisable(false);

                        btnEliminar.setVisible(true);
                        imgEliminar.setVisible(true);

                        btnReporte.setDisable(false);
                        btnReporte.setDisable(false);

                        btnReporte.setVisible(true);
                        imgReporte.setVisible(true);

                        operacion = Operacion.NINGUNO;
                    }
                }

                break;
        }
    }
    
    @FXML
    private void clicModificar() {

        switch (operacion) {
            case NINGUNO:
                if (existeElementoSeleccionado()) {
                    habilitarCampos();

                    btnNuevo.setDisable(true);
                    imgNuevo.setDisable(true);

                    btnNuevo.setVisible(false);
                    imgNuevo.setVisible(false);

                    btnModificar.setText("Guardar");
                    imgModificar.setImage(new Image(PAQUETE_IMAGES + "guardar.png"));

                    btnEliminar.setText("Cancelar");
                    imgEliminar.setImage(new Image(PAQUETE_IMAGES + "cancelar.png"));

                    btnReporte.setDisable(true);
                    imgReporte.setDisable(true);

                    btnReporte.setVisible(false);
                    imgReporte.setVisible(false);

                    operacion = Operacion.ACTUALIZAR;

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Control Académico Kinal");
                    alert.setHeaderText(null);
                    alert.setContentText("Antes de continuar seleccione un registro");
                    Stage dialog = new Stage();
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
                    alert.show();
                }

                break;

            case GUARDAR:

                btnNuevo.setText("Nuevo");
                imgNuevo.setImage(new Image(PAQUETE_IMAGES + "agregar.png"));

                btnModificar.setText("Modificar");
                imgModificar.setImage(new Image(PAQUETE_IMAGES + "editar.png"));

                btnEliminar.setDisable(false);
                imgEliminar.setDisable(false);

                btnEliminar.setVisible(true);
                imgEliminar.setVisible(true);

                btnReporte.setDisable(false);
                imgReporte.setDisable(false);

                btnReporte.setVisible(true);
                imgReporte.setVisible(true);

                limpiarCampos();
                deshabilitarCampos();

                tblHorarios.setDisable(false);

                operacion = Operacion.NINGUNO;
                break;

            case ACTUALIZAR:
                if (existeElementoSeleccionado()) {
                    if (actualizarHorario()) {
                        cargarDatos();
                        limpiarCampos();
                        deshabilitarCampos();
                        tblHorarios.setDisable(false);
                        tblHorarios.getSelectionModel().clearSelection();

                        btnNuevo.setVisible(true);
                        imgNuevo.setVisible(true);

                        btnNuevo.setDisable(false);
                        imgNuevo.setDisable(false);

                        btnNuevo.setText("Nuevo");
                        imgNuevo.setImage(new Image(PAQUETE_IMAGES + "agregar.png"));

                        btnModificar.setText("Modificar");
                        imgModificar.setImage(new Image(PAQUETE_IMAGES + "editar.png"));

                        btnEliminar.setText("Eliminar");
                        imgEliminar.setImage(new Image(PAQUETE_IMAGES + "eliminar.png"));

                        btnEliminar.setDisable(false);
                        imgEliminar.setDisable(false);

                        btnEliminar.setVisible(true);
                        imgEliminar.setVisible(true);

                        btnReporte.setDisable(false);
                        btnReporte.setDisable(false);

                        btnReporte.setVisible(true);
                        imgReporte.setVisible(true);

                        operacion = Operacion.NINGUNO;
                    }
                }
                break;

        }
    }
    
    @FXML
    private void clicEliminar() {
        switch (operacion) {
            case ACTUALIZAR: //CANCELAR LA ACTUALIZACIÓN
                btnNuevo.setDisable(false);
                imgNuevo.setDisable(false);

                btnNuevo.setVisible(true);
                imgNuevo.setVisible(true);

                btnModificar.setText("Modificar");
                imgModificar.setImage(new Image(PAQUETE_IMAGES + "editar.png"));

                btnEliminar.setText("Eliminar");
                imgEliminar.setImage(new Image(PAQUETE_IMAGES + "eliminar.png"));

                btnReporte.setDisable(false);
                imgReporte.setDisable(false);

                btnReporte.setVisible(true);
                imgReporte.setVisible(true);

                limpiarCampos();
                deshabilitarCampos();

                tblHorarios.getSelectionModel().clearSelection();

                operacion = Operacion.NINGUNO;

                break;

            case NINGUNO:

                if (existeElementoSeleccionado()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Control Académico Kinal");
                    alert.setHeaderText(null);
                    Stage dialog = new Stage();
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
                    alert.setContentText("¿Desea eliminar el registro seleccionado?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get().equals(ButtonType.OK)) {
                        if (eliminarHorario()) {
                            listaHorarios.remove(tblHorarios.getSelectionModel().getFocusedIndex());
                            limpiarCampos();
                            cargarDatos();

                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Control Académico Kinal");
                            alert.setHeaderText(null);
                            alert.setContentText("Registro eliminado exitosamente");
                            dialog = new Stage();
                            stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
                            alert.show();
                        }

                    } else if (result.get().equals(ButtonType.CANCEL)) {
                        alert.close();
                        tblHorarios.getSelectionModel().clearSelection();
                        limpiarCampos();
                    }
                    /*if (eliminarAlumno()) {

                        //alert.showAndWait();
                        //alert.show();

                        listaAlumnos.remove(tblAlumnos.getSelectionModel().getFocusedIndex());
                        cargarDatos();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Control Académico Kinal");
                        alert.setHeaderText(null);
                        alert.setContentText("Registro eliminado exitosamente");
                        alert.show();
                    }*/

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Control Académico Kinal");
                    alert.setHeaderText(null);
                    alert.setContentText("Antes de continuar seleccione un registro");
                    Stage dialog = new Stage();
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
                    alert.show();
                }
                break;
        }
    }
    
    @FXML
    private void clicReporte() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("¡AVISO!");
        alert.setHeaderText(null);
        alert.setContentText("Esta opcion solo esta disponible en la versión PRO");
        Stage dialog = new Stage();
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
        alert.show();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
}
