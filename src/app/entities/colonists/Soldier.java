package app.entities.colonists;

public class Soldier extends Colonist {

    private static final int DEFAULT_CLASS_BONUS = 3;
    private static final int DEFAULT_AGE_BONUS = 3;

    private int classBonus;
    private int ageBonus;


    public Soldier(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
        classBonus = DEFAULT_CLASS_BONUS;
        ageBonus = DEFAULT_AGE_BONUS;
    }

    @Override
    public int getPotential() {
        return super.getTalent() + classBonus + ageBonus;
    }
}
