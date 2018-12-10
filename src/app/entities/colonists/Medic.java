package app.entities.colonists;


public abstract class Medic extends Colonist {

    private static final int DEFAULT_BONUS = 2;

    private String sign;

    protected Medic(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age);
        this.sign = sign;
    }

    public String getSign() {
        return this.sign;
    }

    public int getPotential() {
        return DEFAULT_BONUS + super.getTalent();
    }


}
