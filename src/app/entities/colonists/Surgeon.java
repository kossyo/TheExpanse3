package app.entities.colonists;

public class Surgeon extends Medic {

    private static final String PRECISE_SURGEON = "precise";
    private static final String BUTCHER_SURGEON = "butcher";

    public Surgeon(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age, sign);
    }

    @Override
    public int getPotential() {

        int potential =  super.getPotential();
               potential += (super.getAge() > 25 && super.getAge() < 35) ? 2 : 0;

        if (PRECISE_SURGEON.equals(super.getSign())) {
            potential += 3;
        } else if (BUTCHER_SURGEON.equals(super.getSign())) {
            potential -= 3;
        }

        return potential;
    }
}
