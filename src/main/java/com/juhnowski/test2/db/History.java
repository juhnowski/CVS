package com.juhnowski.test2.db;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "history")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class History {

    private long id;
    private Date changedate;
    private String author;
    private int version;
    private String description;
    private String methods;
    private int hashcode;
    
    public History(int version, Date changeDate, String author, String description, String methods, int hashcode){
        this.version = version;
        this.changedate = changeDate;
        this.author = author;
        this.description = description;
        this.methods = methods;
        this.hashcode = hashcode;
    }
    
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "createdate")
    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    @Column(name = "author", length = 150)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "description", length = 150)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "methods", length = 150)
    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    @Column(name = "hashcode")
    public int getHashcode() {
        return hashcode;
    }

    public void setHashcode(int hashcode) {
        this.hashcode = hashcode;
    }
    


}
