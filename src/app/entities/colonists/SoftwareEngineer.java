package app.entities.colonists;

public class SoftwareEngineer extends Engineer {

    private static final int DEFAULT_FLAT_BONUS = 2;

    public SoftwareEngineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    public int getPotential() {

        int potential = super.getPotential();

        return potential + DEFAULT_FLAT_BONUS ;
    }
}
