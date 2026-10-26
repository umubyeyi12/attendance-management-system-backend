package auca.example.attendance_management_system.model;

import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Course{

    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(mappedBy ="courses")
    @JsonIgnore
    private List<Lecturer> lecturers;

    public UUID getId()
    {
        return id;
    }
    public void setId(UUID id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public List<Lecturer> getLecturers(){
        return lecturers;
    }
    public void setLecturers(List<Lecturer> lecturers){
        this.lecturers=lecturers;
    }
}