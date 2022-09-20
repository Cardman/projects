package aiki.map.levels.enums;


import code.util.CustList;

public enum EnvironmentType {
    BUILDING("BUILDING"), DESERT("DESERT"), WATER("WATER"), ICE("ICE"), GRASS("GRASS"), SNOW("SNOW"), ROCK("ROCK"), ROAD("ROAD"), NOTHING("NOTHING");
    private final String envName;
    EnvironmentType(String _k){
        envName = _k;
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
