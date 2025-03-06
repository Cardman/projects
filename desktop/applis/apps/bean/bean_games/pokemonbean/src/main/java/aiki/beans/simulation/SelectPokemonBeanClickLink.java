package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;

public final class SelectPokemonBeanClickLink implements NatCaller, IntBeanAction {
    private final SelectPokemonBean bean;
    private final String index;
    public SelectPokemonBeanClickLink() {
        this(null,"");
    }
    public SelectPokemonBeanClickLink(SelectPokemonBean _b, String _i) {
        bean = _b;
        index = _i;
    }

    @Override
    public String actionBean() {
        return bean.putName(index);
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).clickLink(NaPa.convertToNumber(_args[0]).intStruct()));
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
