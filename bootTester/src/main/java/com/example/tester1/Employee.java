package com.example.tester1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"DOB","createdDateTime"},allowGetters = true)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;

    @Column( name = "DOB")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date DOB;


    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDateTime;

    public Employee(){ }

    public Employee(Long id, String name,String department, Date DOB){
        this.id=id;
        this.name=name;
        this.department=department;
        this.DOB=DOB;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department=department;
    }

    public Date getDOB(){
        return DOB;
    }

    public void setDOB(Date DOB){
        this.DOB=DOB;
    }

    public Date getCreatedDateTime(){
        return createdDateTime;
    }
}
