package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;

public final class SelectItemBeanClickLink implements NatCaller, IntBeanAction {
    private final SelectItemBean bean;
    private final TranslatedKey index;
    public SelectItemBeanClickLink() {
        this(null,null);
    }
    public SelectItemBeanClickLink(SelectItemBean _b, TranslatedKey _i) {
        bean = _b;
        index = _i;
    }
    @Override
    public String actionBean() {
        return bean.putName(index);
    }
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).clickLink(NaPa.convertToNumber(_args[0]).intStruct()));
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
