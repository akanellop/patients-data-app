import jdk.swing.interop.SwingInterOpUtils;
import models.Patient;
import models.PatientsToMorbidityGroups;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private static void initializer(data myDataStorage, Service myService){
        myService.createMorbidityGroup("Respiratory");
        myService.createMorbidityGroup("Cardiovascular");
        myService.createMorbidityGroup("Diabetes");
        myService.createMorbidityGroup("Diabetes");

        myService.createPatient("Kanellopoulou",new Date(96,3,22),new String[]{"Respiratory"});
        myService.createPatient("Gianakopoulos",new Date(35,1,17),new String[]{"Respiratory","Cardiovascular","Diabetes"});
        myService.createPatient("Thodoropoulos",new Date(20,1,17),new String[]{"Cardiovascular"});

        System.out.println("Morbidities saved in table = 3");
        myDataStorage.getMorbidityGroupsTb().forEach(x-> x.print());
        System.out.println("-------------------------------------------");
        System.out.println("Patients saved in table = 3");
        myDataStorage.getPatientsTb().forEach(x-> x.print());
        System.out.println("-------------------------------------------");
        System.out.println("Their relationships :");
        myDataStorage.getPatientsToMorbitidyGroupsTb().forEach(x-> x.print());
        System.out.println("-------------------------------------------");

        myService.createSymptom(1L,"difficult breathing",new Date(),3.2f);
        myService.createSymptom(2L,"hiccups",new Date(),5.2f);
        myService.createSymptom(3L,"vertigo",new Date(),3.2f);
        myService.createSymptom(3L,"nausea",new Date(),2.2f);
        myService.createSymptom(1L,"nausea",new Date(),2.2f);

        System.out.println("Patients' symptoms in table :4");
        myDataStorage.getSymptomsTb().forEach(x-> x.print());

    }

    public static void main(String[] args){
        data myDataStorage = new data();
        Service myService = new Service(myDataStorage);

        initializer(myDataStorage,myService);


        System.out.println("\n\n------------------------");
        System.out.println("Searching for symptoms for patients over 65 with in  Cardiovascular and Respiratory morbidity groups------------------------");

        List<Patient> selectedPatients = myService.fetchPatientsByMorbidityGroups(new String[]{"Cardiovascular","Respiratory"});
        System.out.println(selectedPatients);
        selectedPatients.retainAll(myService.fetchPatientsOlderThan(65));
        System.out.println(selectedPatients);
        selectedPatients.forEach(patient -> myService.fetchSymptomsByPatientId(patient.getId()).forEach(symptom -> symptom.print()));


        myService.fetchPatientsByMorbidityGroups(new String[]{"Respiratory","Cardiovascular","Diabetes"}).forEach(x-> x.print());
        /*
        List<Long> ids =  myService.fetchPatientsOlderThan(65).stream().map(p->p.getId()).collect(Collectors.toList());
        ids.forEach(id-> {
            System.out.println("--------------------------------- ");
            List<PatientsToMorbidityGroups>relationships = myDataStorage.findRelByPatientId(id);
            if(relationships.size()>=2){
                System.out.println("Symptoms of patient over 65 who registers in multiple morbidity groups :");
                myService.fetchSymptomsByPatientId(id).forEach(symptom->symptom.print());
            }
        });

        //example user of fetchPatientsUsingMorbidityGroupNames
        System.out.println("\n\n------------------------");
        System.out.println("example use of fetchPatientsUsingMorbidityGroupNames for group \"Cardiovascular\", should print 2 patients");
        myService.fetchPatientsByMorbidityGroups(new String[]{"Cardiovascular"}).forEach(x-> x.print());*/

    }
}
