package aiki.map.levels.enums;


public enum EnvironmentType {
    BUILDING("BUILDING"), DESERT("DESERT"), WATER("WATER"), ICE("ICE"), GRASS("GRASS"), SNOW("SNOW"), ROCK("ROCK"), ROAD("ROAD"), NOTHING("ROAD");
    private final String envName;
    EnvironmentType(String _k){
        envName = _k;
    }
    public String getEnvName() {
        return envName;
    }
}
