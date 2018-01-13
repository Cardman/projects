package code.formathtml.classes;

import code.util.StringList;
import code.util.ints.Displayable;

public enum EnumNumber implements Displayable {
    ONE,TWO,THREE,FOUR,FIVE,SIX;

    public static EnumNumber getByName(String _name) {
        for (EnumNumber e: values()) {
            if (StringList.quickEq(e.name(), _name)) {
                return e;
            }
        }
        return null;
    }
    @Override
    public String display() {
        return name();
    }
}
