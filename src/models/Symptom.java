package models;

import java.util.Date;

public class Symptom {
    private static Long totalIds;
    private Long id;
    private String name;
    private Date dateOfOccurence;
    private float value;
    private Long foreignKeyPatient;

    public  Symptom(String name,Date date,float value, Long patient){

        this.totalIds = this.totalIds ==null?1L:this.totalIds +1L;
        this.id = this.totalIds;
        this.name = name;
        this.dateOfOccurence = date;
        this.value = value;
        this.foreignKeyPatient = patient;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Long getForeignKeyPatient() {
        return this.foreignKeyPatient;
    }

    public static void decreaseTotalIds(){
        totalIds -= 1;
    }

    public void print(){
        System.out.println("Symptom instance "+ this.getId().toString()+" : "+ this.getName() +" , of patient "+this.getForeignKeyPatient().toString());
    }


}
