package aiki.map.levels.enums;


import aiki.db.*;
import code.util.CustList;
import code.util.core.StringUtil;

public enum EnvironmentType {
    BUILDING(DataBase.DEF_ENV_BUILDING),DESERT(DataBase.DEF_ENV_DESERT),WATER(DataBase.DEF_ENV_WATER),ICE(DataBase.DEF_ENV_ICE),GRASS(DataBase.DEF_ENV_GRASS),SNOW(DataBase.DEF_ENV_SNOW),ROCK(DataBase.DEF_ENV_ROCK),ROAD(DataBase.DEF_ENV_ROAD),NOTHING(DataBase.DEF_ENV_NOTHING);
    private final String envName;
    EnvironmentType(String _k){
        envName = _k;
    }

    public static EnvironmentType getEnvByName(String _env) {
        for (EnvironmentType e: EnvironmentType.all()) {
            if (StringUtil.quickEq(e.envName, _env)) {
                return e;
            }
        }
        return EnvironmentType.NOTHING;
    }
    public static CustList<EnvironmentType> all() {
        CustList<EnvironmentType> genders_ = new CustList<EnvironmentType>();
        genders_.add(BUILDING);
        genders_.add(DESERT);
        genders_.add(WATER);
        genders_.add(ICE);
        genders_.add(GRASS);
        genders_.add(SNOW);
        genders_.add(ROCK);
        genders_.add(ROAD);
        genders_.add(NOTHING);
        return genders_;
    }
    public String getEnvName() {
        return envName;
    }
}
