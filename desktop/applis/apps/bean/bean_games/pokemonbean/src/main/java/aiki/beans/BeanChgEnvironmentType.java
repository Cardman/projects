package aiki.beans;

import aiki.map.levels.enums.*;

public class BeanChgEnvironmentType implements IntBeanChgEnvironmentType {

    private EnvironmentType value = EnvironmentType.NOTHING;

    @Override
    public EnvironmentType valueEt() {
        return value;
    }

    @Override
    public void valueEt(EnvironmentType _v) {
        value = _v;
    }
}
