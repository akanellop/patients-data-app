package models;

public class PatientsToMorbidityGroups {
    private Long foreignKeyPatient;
    private Long foreignKeyMorbidityGr;

    public PatientsToMorbidityGroups(Long foreignKeyPatient, Long foreignKeyMorbidityGr) {
        this.foreignKeyPatient = foreignKeyPatient;
        this.foreignKeyMorbidityGr = foreignKeyMorbidityGr;
    }
}
