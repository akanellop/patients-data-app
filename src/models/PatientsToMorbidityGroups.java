package models;

public class PatientsToMorbidityGroups {
    private Long foreignKeyPatient;
    private Long foreignKeyMorbidityGr;

    public PatientsToMorbidityGroups(Long foreignKeyPatient, Long foreignKeyMorbidityGr) {
        this.foreignKeyPatient = foreignKeyPatient;
        this.foreignKeyMorbidityGr = foreignKeyMorbidityGr;
    }

    public void print(){
        System.out.println("PatientKEY "+ this.getForeignKeyPatient().toString()+" and MorbidityKEY "+ this.getForeignKeyMorbidityGr().toString() );
    }

    public Long getForeignKeyPatient() {
        return foreignKeyPatient;
    }

    public Long getForeignKeyMorbidityGr() {
        return foreignKeyMorbidityGr;
    }
}
