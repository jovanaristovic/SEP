package com.ftn.backend.model;

import com.ftn.backend.dto.NewWorkDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table
@Entity(name = "WORK")
public class Work implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column
    private String title;

    @Column
    private String apstrakt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "work_user",
            joinColumns = @JoinColumn(name = "work_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users;

    @ManyToOne
    private ScientificField scientificField;

    @Column
    private Long price;

    @Column
    private String fileName;

    public Work(){}

    public Work(NewWorkDto newWorkDto) {
        this.apstrakt = newWorkDto.getApstrakt();
        this.title = newWorkDto.getTitle();
        this.price = newWorkDto.getPrice();
        this.fileName = newWorkDto.getFileName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApstrakt() {
        return apstrakt;
    }

    public void setApstrakt(String apstrakt) {
        this.apstrakt = apstrakt;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
