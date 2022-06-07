package org.in5bm.anthonyacabal.donaldosanum.models;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anthony Acabal
 */
public class AsignacionesAlumnos {
    private SimpleIntegerProperty id;
    private SimpleStringProperty alumnoId;
    private SimpleIntegerProperty cursoId;
    private SimpleObjectProperty <LocalDate> fechaAsignacion;

    public AsignacionesAlumnos() {
        this.id = new SimpleIntegerProperty();
        this.alumnoId = new SimpleStringProperty();
        this.cursoId = new SimpleIntegerProperty();
        this.fechaAsignacion = new SimpleObjectProperty<>();
    }
    
    public AsignacionesAlumnos(int id, String alumnoId, int cursoId, LocalDate fechaAsignacion) {
        this.id = new SimpleIntegerProperty(id);
        this.alumnoId = new SimpleStringProperty(alumnoId);
        this.cursoId = new SimpleIntegerProperty(cursoId);
        this.fechaAsignacion = new SimpleObjectProperty<>(fechaAsignacion);
    }
    
    //Getter's and Setter's
    
    public IntegerProperty id() {
        return id;
    }
    
    public int getId() {
        return id.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    //Alumno ID
    public StringProperty alumnoId() {
        return alumnoId;
    }
    
    public String getAlumnoId(){
        return alumnoId.get();
    }
    
    public void setAlumnoId(String alumnoId){
        this.alumnoId.set(alumnoId);
    }
    
    //Curso ID
    public IntegerProperty cursoId() {
        return cursoId;
    }
    
    public int getCursoId() {
        return cursoId.get();
    }
    
    public void setCursoId(int cursoId) {
        this.cursoId.set(cursoId);
    }
    
    //Fecha Asignacion ID
    public ObjectProperty<LocalDate> fechaAsignacion() {
        return fechaAsignacion;
    }
    
    public LocalDate getFechaAsignacion() {
        return fechaAsignacion.get();
    }
    
    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion.set(fechaAsignacion);
    }
    
    
    @Override
    public String toString() {
        return "AsignacionAlumnos{" + "Id=" + id + ", Carné=" + alumnoId + ", Curso=" + cursoId + ", Fecha Asignacion=" + fechaAsignacion + '}';
    }

    
    
}
