package auca.example.attendance_management_system.model;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class Student{
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique=true)

    private String regNumber;
    private String email;
    private String programme;

    public Student(){}

    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id=id;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getRegNumber(){
        return regNumber;
    }
    public void setRegNumber(String regNumber){
        this.regNumber=regNumber;
    }
    public String getProgramme(){
        return programme;
    }

    
    public void setProgramme(String programme){
        this.programme=programme;
    }
    
        
    


    

}