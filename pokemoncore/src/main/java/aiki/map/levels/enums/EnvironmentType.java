package aiki.map.levels.enums;
import code.util.EnumList;
import code.util.StringList;
import code.util.ints.Listable;


public enum EnvironmentType {
    BUILDING, DESERT, WATER, ICE, GRASS, SNOW, ROCK, ROAD, NOTHING;
    public static boolean equalsSet(Listable<EnvironmentType> _list1,Listable<EnvironmentType> _list2) {
        for (EnvironmentType a: _list2) {
            boolean contains_ = false;
            for (EnvironmentType b: _list1) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (EnvironmentType a: _list1) {
            boolean contains_ = false;
            for (EnvironmentType b: _list2) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    public static EnvironmentType getEnvByName(String _env) {
        for (EnvironmentType e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return NOTHING;
    }
    public static EnumList<EnvironmentType> getEnvironments() {
        return new EnumList<EnvironmentType>(values());
    }
}
