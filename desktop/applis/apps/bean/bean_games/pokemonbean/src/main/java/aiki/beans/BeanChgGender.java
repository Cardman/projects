package aiki.beans;

import aiki.map.pokemon.enums.*;

public class BeanChgGender implements IntBeanChgGender {
    private Gender selected = Gender.NO_GENDER;

    @Override
    public Gender valGender() {
        return selected;
    }

    @Override
    public void valGender(Gender _v) {
        selected = _v;
    }
}
