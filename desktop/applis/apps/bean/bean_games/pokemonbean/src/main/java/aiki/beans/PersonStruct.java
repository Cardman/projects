package aiki.beans;

import aiki.map.characters.Person;
import code.bean.nat.NaNuSt;

public final class PersonStruct extends NaNuSt {
    private final Person inst;
    public PersonStruct(Person _instance) {
        inst=(_instance);
    }
    public Person getPerson() {
        return inst;
    }
}
