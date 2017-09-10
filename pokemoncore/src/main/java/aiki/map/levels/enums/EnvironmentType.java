package aiki.map.levels.enums;
import code.serialize.CheckedData;
import code.util.EnumList;
import code.util.ints.Listable;


@CheckedData
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
    public static EnumList<EnvironmentType> getEnvironments() {
        return new EnumList<EnvironmentType>(values());
    }
}
