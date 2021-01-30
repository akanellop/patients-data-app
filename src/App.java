import models.Patient;

import java.util.Date;

public class App {
    public static void main(String[] args){
        data myDataStorage = new data();
        Service myService = new Service(myDataStorage);

        myDataStorage.insertPatientsTb(new Patient("katerina",new Date(12,1,12)));
        myDataStorage.insertPatientsTb(new Patient("katerina4",new Date(12,1,12)));
        myService.createMorbidityGroup("kardia");
        myService.createMorbidityGroup("kardia");
        myService.createMorbidityGroup("kardia1");

        myDataStorage.getPatientsTb().forEach(patient -> {patient.print();});
        System.out.println(myDataStorage.getMorbidityGroupsTb());

    }
}
