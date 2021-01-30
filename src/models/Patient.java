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
        System.out.println("Patient "+ this.getId().toString()+" : "+ this.getName() + " / "+this.getDateOfBirth().toString());
    }

    public Long getId() { return this.id; }

    public String getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public static void decreaseTotalIds(){
        totalIds -= 1;
    }

}
