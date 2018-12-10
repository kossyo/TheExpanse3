package app.core;


import app.entities.Colony;
import app.entities.Family;
import app.entities.colonists.*;
import app.exceptions.ColonyFullException;
import app.exceptions.FamilyFullException;

import java.util.List;
import java.util.Objects;

public class Manager {

//    public Manager() {/////////////////////// tova mo maxni????
//    }

    public String family(Colony colony, String familyId) {
        return colony.family(familyId);
    }

    public String capacity(Colony colony) {
        return colony.getCapacity();
    }

    public String potential(Colony colony) {
        return String.format("potential: %d%n", colony.getPotential());
    }

    public void grow(Colony colony, int years) {
        colony.grow(years);
    }

    public void remove(Colony colony, List<String> cmdArgs) {

        String modificator = cmdArgs.get(1);
        String familyId = cmdArgs.get(2);

        switch (modificator) {
            case "family":

                colony.removeFamily(familyId);

                break;
            case "colonist":

                String colonistId = cmdArgs.get(3);

                    colony.removeColonist(familyId, colonistId);
                    break;
        }
    }

    public String insert(Colony colony, List<String> cmdArgs, int maxFamilyCapacity) {

        Colonist colonist = null;

        String colonistType = cmdArgs.get(1);
        String id = cmdArgs.get(2);
        String familyId = cmdArgs.get(3);
        int talent = Integer.parseInt(cmdArgs.get(4));
        int age = Integer.parseInt(cmdArgs.get(5));

        switch (colonistType) {
            case "SoftwareEngineer":
                colonist = new SoftwareEngineer(id, familyId, talent, age);
                break;
            case "HardwareEngineer":
                colonist = new HardwareEngineer(id, familyId, talent, age);
                break;
            case "Soldier":
                colonist = new Soldier(id, familyId, talent, age);
                break;
            case "GeneralPractitioner":
                String sign = cmdArgs.get(6);
                colonist = new GeneralPractitioner(id, familyId, talent, age, sign);
                break;
            case "Surgeon":
                sign = cmdArgs.get(6);
                colonist = new Surgeon(id, familyId, talent, age, sign);
                break;
        }

        String familyWereLookingFor = Objects.requireNonNull(colonist).getFamilyId();
        boolean familyExists = doesFamilyExist(familyWereLookingFor, colony);
        boolean thereIsAFreeSlotInFamily = false;

        if (familyExists) {

            thereIsAFreeSlotInFamily = isThereAFreeSlotInFamily(colony.getFamilies().stream()
                    .filter(family -> family.getId().equals(familyWereLookingFor))
                    .findFirst().get(), maxFamilyCapacity);

        }
        boolean thereIsPlaceForOneMoreFamily = isTherePlaceForOneMoreFamily(colony, maxFamilyCapacity);

        if (familyExists) {
            if (thereIsAFreeSlotInFamily) {

                colony.addColonist(colonist);
            } else {
                //return "family is full\r\n";
                throw new FamilyFullException();
                //"family is full\r\n"
            }
        } else {
            if (thereIsPlaceForOneMoreFamily) {
                colony.addFamily(familyWereLookingFor);

                colony
                        .addColonist(colonist);


            } else {
                //return "colony is full";
                throw new ColonyFullException();
            }
        }
        return null;
    }

    private boolean isTherePlaceForOneMoreFamily(Colony colony, int maxFamilyCount) {
        return colony.getFamilies().size() <= maxFamilyCount;
    }

    private boolean isThereAFreeSlotInFamily(Family family, int maxFamilyCapacity) {

        return family.getColonists().size() < maxFamilyCapacity;
    }

    private boolean doesFamilyExist(String familyWereLookingFor, Colony colony) {

        for (Family family : colony.getFamilies()) {
            if (family.getId().equals(familyWereLookingFor)) {
                return true;
            }
        }
        return false;
    }
}
