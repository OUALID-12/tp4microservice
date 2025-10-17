package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        // Create salles
        Salle salle1 = new Salle("A1");
        Salle salle2 = new Salle("B2");
        salleService.create(salle1);
        salleService.create(salle2);

        // Create machines
        Machine machine1 = new Machine("M123", new Date(), salleService.findById(salle1.getId()));
        Machine machine2 = new Machine("M124", new Date(), salleService.findById(salle2.getId()));
        machineService.create(machine1);
        machineService.create(machine2);

        // Print salles and machines
        for (Salle salle : salleService.findAll()) {
            System.out.println("Salle: " + salle.getCode());
            if (salle.getMachines() != null) {
                for (Machine m : salle.getMachines()) {
                    System.out.println("  Machine: " + m.getRef());
                }
            }
        }

        // Date range query
        Date d1 = new Date(110, 0, 1);
        Date d2 = new Date();
        System.out.println("Machines between " + d1 + " and " + d2 + ":");
        for (Machine m : machineService.findBetweenDate(d1, d2)) {
            System.out.println(m.getRef() + " bought on " + m.getDateAchat());
        }
    }
}
