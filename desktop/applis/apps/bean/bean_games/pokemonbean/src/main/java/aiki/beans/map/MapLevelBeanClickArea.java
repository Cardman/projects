package aiki.beans.map;

import aiki.beans.*;
import code.bean.nat.*;

public class MapLevelBeanClickArea implements NatCaller, IntBeanAction {
    private final AbsLevelBean bean;
    private final int index;
    public MapLevelBeanClickArea() {
        this(null,0);
    }
    public MapLevelBeanClickArea(AbsLevelBean _m, int _i) {
        bean = _m;
        index = _i;
    }

    @Override
    public String actionBean() {
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[]{new NaNbSt(index)})).getInstance();
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbsLevelBean) ((PokemonBeanStruct)_instance).getInstance()).clickArea(NaPa.convertToNumber(_args[0]).intStruct()));
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
