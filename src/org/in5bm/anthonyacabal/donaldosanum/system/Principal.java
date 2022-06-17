 package org.in5bm.anthonyacabal.donaldosanum.system;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.in5bm.anthonyacabal.donaldosanum.controllers.AlumnosController;
import org.in5bm.anthonyacabal.donaldosanum.controllers.AsignacionAlumnosController;
import org.in5bm.anthonyacabal.donaldosanum.controllers.CarrerasTecnicasController;
import org.in5bm.anthonyacabal.donaldosanum.controllers.CursosController;
import org.in5bm.anthonyacabal.donaldosanum.controllers.HorariosController;
import org.in5bm.anthonyacabal.donaldosanum.controllers.InstructoresController;
import org.in5bm.anthonyacabal.donaldosanum.controllers.SalonesController;
import org.in5bm.anthonyacabal.donaldosanum.controllers.MenuPrincipalController;


/**
 *
 * @author Anthony Acabal
 */
public class Principal extends Application {

    //private final String PAQUETE_VIEW = "org/in5bm/anthonyacabal/donaldosanum/views/";
    private final String PAQUETE_VIEW = "../views/";
    private final String PAQUETE_IMAGES = "org/in5bm/anthonyacabal/donaldosanum/resources/images/";
    private Stage escenarioPrincipal;
    private Scene escena;

    public static void main(String[] args) {
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bd_control_academico_in5bm", "root", "admin");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("show databases;");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }*/
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.escenarioPrincipal = primaryStage;
        this.escenarioPrincipal.setTitle("Control Académico Kinal");
        this.escenarioPrincipal.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
        this.escenarioPrincipal.setResizable(false);
        this.escenarioPrincipal.centerOnScreen();
        mostrarEscenaPrincipal();
        //mostrarEscenaAlumnos();

        //Parent root = FXMLLoader.load(getClass().getResource("../views/MenuPrincipal.fxml"));
        //Scene scene = new Scene(root);
        //primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        //primaryStage.setTitle("Control Académico Kinal");
        /*Stage stage = (Stage) primaryStage.getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("../resources/images/logo.png").toString()));*/
        //primaryStage.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
        //primaryStage.show();
    }

    /*public Initializable cambiarEscena(String vistaFxml, int alto, int ancho) throws IOException {
        Initializable resultado;
        
        //Cargador del Archivo XML
        FXMLLoader cargadorFXML = new FXMLLoader();
        
        // Asignación lógica del archivo
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VIEW + vistaFxml);
        
        //Cargador de fábrica para cargar el archivo FXML
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        
        //Ubicación del archivo FXML que se pintará en el escenario
        cargadorFXML.setLocation(PrintStream.class.getResource(PAQUETE_VIEW + vistaFxml));
        
        //
        AnchorPane root = cargadorFXML.load();
        
        //Creación de la Escena
        Scene nuevaEscena = new Scene(root, alto, ancho);
        
        // Trasladar la escena al escenario
        this.escenarioPrincipal.setScene(nuevaEscena);
        this.escenarioPrincipal.setResizable(false);
        this.escenarioPrincipal.centerOnScreen();
        this.escenarioPrincipal.setTitle("Control Académico");
        this.escenarioPrincipal.show();
        
        this.escenarioPrincipal.getIcons().add(new Image(PAQUETE_IMAGES + "logo.png"));
        
        //Obtener el controlador del archivo FXML
        resultado = (Initializable)cargadorFXML.getController();
        return resultado;
        
    }*/
    public Initializable cambiarEscena(String vistaFxml) throws IOException {
        Initializable resultado = null;
        System.out.println("Cambiando Escena: " + PAQUETE_VIEW + vistaFxml);
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(this.PAQUETE_VIEW + vistaFxml));
        Scene scene = new Scene((AnchorPane) cargadorFXML.load());
        this.escenarioPrincipal.setScene(scene);
        this.escenarioPrincipal.sizeToScene();
        this.escenarioPrincipal.show();
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }

    /*public Initializable cambiarEscena(String vistaFxml, int ancho, int alto) throws IOException {
        Initializable resultado = null;
// Cargador de archivo FXML
        FXMLLoader cargadorFXML = new FXMLLoader();
// Cargador de fabrica para cargar el archivo FXML
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
// Ubicación del archivo FXML que se pintará en el escenario
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VIEW + vistaFxml));
// Asignación de la lógica del archivo
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VIEW + vistaFxml);
// Creación del nodo raíz
        AnchorPane root = cargadorFXML.load(archivo);
// Creación de la escena
        Scene nuevaEscena = new Scene(root, ancho, alto);
// Cargar la escena en el escenario principal
        this.escenarioPrincipal.setScene(nuevaEscena);
        this.escenarioPrincipal.sizeToScene();
// Mostrar el escenario
        this.escenarioPrincipal.show();
// Obtener una instancia del controlador de la vista
        resultado = cargadorFXML.getController();
        return resultado;
    }*/

    public void mostrarEscenaPrincipal() {
        try {
            MenuPrincipalController menuController = (MenuPrincipalController) cambiarEscena("MenuPrincipalView.fxml");
            menuController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("\nSe produjo un error al cargar la vista del menu principal");
            ex.printStackTrace();
        }
    }

    public void mostrarEscenaAlumnos() {
        try {
            AlumnosController alumnosController = (AlumnosController) cambiarEscena("AlumnosView.fxml");
            alumnosController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("\nSe produjo un error al intentar mostrar la vista de Alumnos");
            ex.printStackTrace();
        }
    }
    
    public void mostrarEscenaInstructores() {
        try {
            InstructoresController instructoresController = (InstructoresController) cambiarEscena("InstructoresView.fxml");
            instructoresController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("\nSe produjo un error al intentar mostrar la vista de Alumnos");
            ex.printStackTrace();
        }
    }
    
    public void mostrarEscenaSalones() {
        try {
            SalonesController salonesController = (SalonesController) cambiarEscena("SalonesView.fxml");
            salonesController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("\nSe produjo un error al intentar mostrar la vista de Salones");
            ex.printStackTrace();
        }
    }
    
    public void mostrarEscenaCarrerasTecnicas() {
        try {
            CarrerasTecnicasController carrerasTecnicasController = (CarrerasTecnicasController) cambiarEscena("CarrerasTecnicasView.fxml");
            carrerasTecnicasController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("\nSe produjo un error al intentar mostrar la vista de Carreras Técnicas");
            ex.printStackTrace();
        }
        
    }
    
    public void mostrarEscenaCursos() {
        try {
            CursosController cursos = (CursosController) cambiarEscena("CursosView.fxml");
            cursos.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("\nSe produjo un error al intentar mostrar la vista de Carreras Técnicas");
            ex.printStackTrace();
        }
        
    }
    
    public void mostrarEscenaHorarios() {
        try {
            HorariosController horarios = (HorariosController) cambiarEscena("HorariosView.fxml");
            horarios.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("\nSe produjo un error al intentar mostrar la vista de Horarios");
            ex.printStackTrace();
        }
        
    }
    
    public void mostrarEscenaAsignacionAlumnos() {
        try {
            AsignacionAlumnosController asignacionAlumnosController = (AsignacionAlumnosController) cambiarEscena("AsignacionesAlumnosView.fxml");
            asignacionAlumnosController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            System.err.println("\nSe produjo un error al intentar mostrar la vista de Asignacion Alumnos");
            ex.printStackTrace();
        }
        
    }

}