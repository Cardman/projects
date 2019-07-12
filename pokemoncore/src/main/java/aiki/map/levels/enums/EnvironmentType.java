package aiki.map.levels.enums;
import code.util.EnumList;
import code.util.StringList;
import code.util.ints.Listable;


public enum EnvironmentType {
    BUILDING, DESERT, WATER, ICE, GRASS, SNOW, ROCK, ROAD, NOTHING;
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
