package code.formathtml.classes;

import code.util.ints.Displayable;

public enum EnumNumber implements Displayable {
    ONE,TWO,THREE,FOUR,FIVE,SIX;

    @Override
    public String display() {
        return name();
    }
}
