package aiki.beans.map;

import aiki.beans.*;
import code.bean.nat.*;
public final class MapLevelBeanClickTileOnMap implements NatCaller, IntBeanAction {
    private final MapLevelBean bean;
    private final int index;
    public MapLevelBeanClickTileOnMap() {
        this(null,0);
    }
    public MapLevelBeanClickTileOnMap(MapLevelBean _m, int _i) {
        bean = _m;
        index = _i;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[]{new NaNbSt(index)})).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MapLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickTileOnMap(NaPa.convertToNumber(_args[0]).intStruct()));
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
