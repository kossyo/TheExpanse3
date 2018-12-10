package app.entities.colonists;

//import app.contracts.Colonist;

public abstract class Colonist  {

    //id (String), familyId (String), talent (int), age (int).
    private String id;
    private String familyId;
    private int talent;
    private int age;

    protected Colonist(String id, String familyId, int talent, int age) {
        this.id = id;
        this.familyId = familyId;
        this.talent = talent;
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public String getFamilyId() {
        return this.familyId;
    }

    public int getTalent() {
        return this.talent;
    }

    public int getAge() {
        return this.age;
    }

    public void grow(int years){
        this.age += years;
    }

    public abstract int getPotential();
}
