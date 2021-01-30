import models.MorbidityGroup;
import models.Patient;
import models.PatientsToMorbidityGroups;
import models.Symptom;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Services {
    private Data dataStorage;

    public Services(Data dataStorage){
        this.dataStorage= dataStorage;
    } //constructor

    public boolean createMorbidityGroup(String morbidityName){
        return dataStorage.insertMorbidityGroupsTb(new MorbidityGroup(morbidityName));
    }

    public boolean createPatient(String name, Date dob, String[] morbGr){
        Patient patient = new Patient(name,dob);
        MorbidityGroup morbGroup ;
        if (!dataStorage.insertPatientsTb(patient)){return false;}
        for(String morbidityName : morbGr){

            morbGroup = dataStorage.findMorbGrByName(morbidityName);
            dataStorage.insertPatientsToMorbitidyGroupsTb(new PatientsToMorbidityGroups(patient.getId(),morbGroup.getId()));
        }
        return true;
    }

    public boolean createSymptom(Long patient, String name,Date date, float value){
        if(dataStorage.findPatientById(patient)!=null){
            return dataStorage.insertSymptomsTb(new Symptom(name,date,value,patient));
        }
        return false;
    }


    public List<Patient> fetchPatientsOlderThan(int age){
        Date today = new Date();
        LocalDate todayLocale = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        List<Patient> filteredPatients = dataStorage.getPatientsTb().stream()
                .filter(patient -> {
                    int patientAge = Period.between(patient.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), todayLocale).getYears();
                    return (patientAge>age);
                })
                .collect(Collectors.toList());

        return filteredPatients;

    }

    public List<Symptom> fetchSymptomsByPatientId(Long id){
        List<Symptom> filteredSymptoms = dataStorage.getSymptomsTb().stream()
                .filter(s -> s.getForeignKeyPatient() == id).collect(Collectors.toList());

        return filteredSymptoms;
    }

    public List<Patient> fetchPatientsByMorbidityGroups(String[] morbidities){
        List<Long> filteredMorbIds = dataStorage.findMorbidityIdsByNames(Arrays.asList(morbidities)); //List of ids of given names

        List<Patient> filteredPatients = dataStorage.getPatientsTb().stream()
                .filter(patient->{
                    List<Long> groups = dataStorage.findRelByPatientId(patient.getId()).stream().map(x->x.getForeignKeyMorbidityGr()).collect(Collectors.toList());
                    if(groups.containsAll(filteredMorbIds)){
                        return true;
                    }
                    else{
                        return false;
                    }
                })
                .collect(Collectors.toList());

        return filteredPatients;
    }


}
