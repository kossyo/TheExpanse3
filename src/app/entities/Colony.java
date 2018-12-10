package app.entities;


import app.entities.colonists.Colonist;
import app.exceptions.FamilyDoesNotExistException;

import java.util.*;
import java.util.stream.Collectors;

public class Colony {

    private int maxFamilyCount;
    private int maxFamilyCapacity;

    private List<Family> families;
    private List<Colonist> colonists;

    public Colony(int maxFamilyCount, int maxFamilyCapacity) {
        this.maxFamilyCount = maxFamilyCount;
        this.maxFamilyCapacity = maxFamilyCapacity;
        this.families = new LinkedList<>();
        this.colonists = new LinkedList<>();
    }

    public int getMaxFamilyCount() {
        return this.maxFamilyCount;
    }

    public int getMaxFamilyCapacity() {
        return this.maxFamilyCapacity;
    }

    public List<Family> getFamilies() {
        return Collections.unmodifiableList(this.families.stream().sorted(Comparator.comparing(Family::getId)).collect(Collectors.toList()));
    }

    public List<Colonist> getColonistsByFamilyId(String familyId) {

        Family family = this.families.stream().filter(fam -> familyId.equals(fam.getId())).findFirst().get();

        List<Colonist> sortedList = family
                .getColonists()
                .stream()
                .sorted(Comparator.comparing(Colonist::getId))
                .collect(Collectors.toList());

        return Collections.unmodifiableList(sortedList);
    }

    public void addColonist(Colonist colonist) {

        this.families
                .stream()
                .filter(family -> family.getId().equals(colonist.getFamilyId()))
                .findFirst()
                .get()
                .addColonist(colonist);

        this.colonists.add(colonist);

    }

    public void addFamily(String familyName) {
        this.families.add(new Family(familyName));


    }

    public void removeColonist(String familyId, String memberId) {


        this.colonists.removeIf(colonist -> colonist.getFamilyId().equals(familyId) && colonist.getId().equals(memberId));
        Optional<Family> familyOptional =
                this.families.stream()
                        .filter(family -> family.getId().equals(familyId))
                        .findFirst();

        familyOptional.ifPresent(family -> family
                .removeColonist(memberId));

        this.families.removeIf(family -> family.getColonists().isEmpty());
    }

    public void removeFamily(String id) {

        try {
            this.families.removeIf(family -> family.getId().equals(id));
            this.colonists.removeIf(colonist -> colonist.getFamilyId().equals(id));
        } catch (Exception e) {
        }
    }

    public void grow(int years) {

        this.colonists.stream()
                .forEach(colonist -> colonist.grow(years));

        this.families
                .stream().forEach(family -> family.grow(years));

    }

    public int getPotential() {

        int potential = 0;

        for (Colonist colonist : this.colonists) {
            potential += colonist.getPotential();
        }
        return potential;

    }

    public String getCapacity() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("families: %d/%d%n", this.families.size(), this.maxFamilyCount));

        for (Family family : this.getFamilies()) {

            sb.append(String.format("-%s: %d/%d%n", family.getId(), family.getColonists().size(), this.maxFamilyCapacity));

        }
        return sb.toString();
    }

    public String family(String familyId) {


        StringBuilder sb = new StringBuilder();

        Optional<Family> familyOptional = this.families.stream()
                .filter(family -> family.getId().equals(familyId))
                .findFirst();

        familyOptional.ifPresent(family -> {

            sb.append(String.format("%s:%n", family.getId()));

            for (Colonist colonist : family.getColonists()) {
                sb.append(String.format("-%s: %d%n", colonist.getId(), colonist.getPotential()));
            }
        });

        if (!familyOptional.isPresent()) {
            throw new FamilyDoesNotExistException();
        }
        return sb.toString();
    }
}
