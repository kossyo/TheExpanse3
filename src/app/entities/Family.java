package app.entities;

import app.entities.colonists.Colonist;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Family {

    private String id;
    private List<Colonist> colonists;

    public Family(String id) {
        this.id = id;
        this.colonists = new LinkedList<>();
    }

    public String getId() {
        return this.id;
    }

    public List<Colonist> getColonists() {


        return Collections.unmodifiableList(this.colonists.stream().sorted(Comparator.comparing(Colonist::getId)).collect(Collectors.toList()));
    }

    public void addColonist(Colonist colonist){
        this.colonists.add(colonist);
    }

    public void removeColonist(String memberId){
        this.colonists.removeIf(colonist -> colonist.getId().equals(memberId));
    }

    public void grow(int years){
        this.colonists.forEach(colonist -> colonist.grow(years));
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
