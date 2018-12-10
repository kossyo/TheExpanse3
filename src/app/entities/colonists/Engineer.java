package app.entities.colonists;

public abstract class Engineer extends Colonist {

    private static final int DEFAULT_CLASS_BONUS = 3;

    private int classBonus;

    protected Engineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
        this.classBonus = DEFAULT_CLASS_BONUS;
    }

    public int getClassBonus() {

        return this.classBonus;
    }

    @Override
    public int getPotential() {

        int potential = super.getTalent();

        if (super.getAge() > 30){
            potential += 2;
        }
        return potential + this.classBonus;
    }
}
