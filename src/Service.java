import models.MorbidityGroup;
import models.Patient;
import models.PatientsToMorbidityGroups;
import models.Symptom;

import java.lang.reflect.Array;
import java.util.Date;

public class Service {
    private data dataStorage;

    public Service(data dataStorage){
        this.dataStorage= dataStorage;
    }
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
}
