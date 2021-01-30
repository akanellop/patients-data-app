package models;

import java.util.Date;

public class Patient {
    private static Long totalIds;
    private Long id;
    private String name;
    private Date dateOfBirth;

    public  Patient(String name,Date dOb){

        this.totalIds = this.totalIds ==null?1L:this.totalIds +1L;
        this.id = this.totalIds;
        this.name = name;
        this.dateOfBirth = dOb;
    }

    public void print(){
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.dateOfBirth);

    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
}
