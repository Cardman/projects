package aiki.beans.map;

import aiki.beans.*;
import code.bean.nat.*;

public class MapLevelBeanClickNeighbour implements NatCaller, IntBeanAction {
    private final AbsLevelBean bean;
    private final int index;
    public MapLevelBeanClickNeighbour() {
        this(null,0);
    }
    public MapLevelBeanClickNeighbour(AbsLevelBean _m, int _i) {
        bean = _m;
        index = _i;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[]{new NaNbSt(index)})).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickNeighbour(NaPa.convertToNumber(_args[0]).intStruct()));
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
