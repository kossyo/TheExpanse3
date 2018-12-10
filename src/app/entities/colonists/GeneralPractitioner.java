package app.entities.colonists;

public class GeneralPractitioner extends Medic {

    private static final String CARING_SIGN = "caring";
    private static final String CARELESS_SIGN= "careless";

    public GeneralPractitioner(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age, sign);
    }

    @Override
    public int getPotential() {

        int potential = super.getPotential();

        if (super.getAge() > 15) {// > ? // > ? // > ? // > ? // > ?
            potential += 1;
        }

        if (CARING_SIGN.equals(super.getSign())){
            potential += 1;
        }
        else if(CARELESS_SIGN.equals(super.getSign())){
            potential -= 2;
        }
        return  potential;
    }
}
