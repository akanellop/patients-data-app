import models.MorbidityGroup;
import models.Patient;
import models.PatientsToMorbidityGroups;
import models.Symptom;

import java.lang.reflect.Array;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class data {
    private List<Patient> PatientsTb;
    private List<MorbidityGroup> MorbidityGroupsTb;
    private List<PatientsToMorbidityGroups> PatientsToMorbitidyGroupsTb; //TWO FOREIGN KEYS CONSTITUTING ITS PRIMARY
    private List<Symptom> SymptomsTb; //WITH EXTRA FOREIGN KEY REFERRING TO PATIENT

    public data(){
        PatientsTb = new ArrayList<>();
        MorbidityGroupsTb = new ArrayList<>();
        PatientsToMorbitidyGroupsTb = new ArrayList<>();
        SymptomsTb = new ArrayList<>();
    }


    public List<Patient> getPatientsTb() {
        return PatientsTb;
    }

    public List<MorbidityGroup> getMorbidityGroupsTb() {
        return MorbidityGroupsTb;
    }

    public List<PatientsToMorbidityGroups> getPatientsToMorbitidyGroupsTb() {
        return PatientsToMorbitidyGroupsTb;
    }

    public List<Symptom> getSymptomsTb() {
        return SymptomsTb;
    }

    public boolean insertPatientsTb(Patient patient) {
        return this.PatientsTb.add(patient);
    }

    public boolean insertMorbidityGroupsTb(MorbidityGroup MG) {
        for( MorbidityGroup MGinTb : this.MorbidityGroupsTb) {
            if(MGinTb.getName().equals(MG.getName())) {
                MorbidityGroup.decreaseTotalIds();
                return false;
            }
        }
        return this.MorbidityGroupsTb.add(MG);
    }

    public boolean insertPatientsToMorbitidyGroupsTb(PatientsToMorbidityGroups PatientsMorbidityRel) {
        return this.PatientsToMorbitidyGroupsTb.add(PatientsMorbidityRel);
    }

    public boolean insertSymptomsTb(Symptom symptom) {
        for( Symptom symInTb : this.SymptomsTb) {
            if(symInTb.getName().equals(symptom.getName())) {
                Symptom.decreaseTotalIds();
                return false;
            }
        }
        return this.SymptomsTb.add(symptom);
    }

    public MorbidityGroup findMorbGrByName(String name) {
        for( MorbidityGroup morbidity : this.MorbidityGroupsTb) {
            if(morbidity.getName().equals(name)) { return morbidity;}
        }
        return null;
    }
    public MorbidityGroup findMorbGrById(Long id) {
        for( MorbidityGroup morbidity : this.MorbidityGroupsTb) {
            if(morbidity.getId().equals(id)) { return morbidity;}
        }
        return null;
    }
    public List<PatientsToMorbidityGroups> findRelByPatientId(Long id) {
        List<PatientsToMorbidityGroups> filtered = new ArrayList<>();
        for( PatientsToMorbidityGroups relationship : this.PatientsToMorbitidyGroupsTb) {
            if(relationship.getForeignKeyPatient().equals(id)) {
                filtered.add(relationship);
            }
        }
        return filtered;
    }

    public List<PatientsToMorbidityGroups> findRelByMorbdityId(Long id) {
        List<PatientsToMorbidityGroups> filtered = new ArrayList<>();
        for( PatientsToMorbidityGroups relationship : this.PatientsToMorbitidyGroupsTb) {
            if(relationship.getForeignKeyMorbidityGr().equals(id)) {
                filtered.add(relationship);
            }
        }
        return filtered;
    }

    public Patient findPatientById(Long id){
        for( Patient patient : this.PatientsTb) {
            if(patient.getId().equals(id)) { return patient;}
        }
        return null;
    }

    public List<Long> findMorbidityIdsByNames(List<String> morbidities){
        List<Long> filteredIds = this.MorbidityGroupsTb.stream()
                .filter(mb -> morbidities.contains(mb.getName()))
                .map(x->x.getId()).collect(Collectors.toList());
        return  filteredIds ;
    }


};

