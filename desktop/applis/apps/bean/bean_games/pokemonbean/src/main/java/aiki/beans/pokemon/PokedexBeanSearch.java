package aiki.beans.pokemon;

import aiki.beans.*;
import code.bean.nat.*;
public final class PokedexBeanSearch implements NatCaller, IntBeanAction {
    private final PokedexBean bean;
    public PokedexBeanSearch() {
        this(null);
    }
    public PokedexBeanSearch(PokedexBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setTypedName(bean.getTypedNameForm().tryRet());
        bean.setTypedType(bean.getTypedTypeForm().tryRet());
        bean.setWholeWord(bean.getWholeWordForm().isSelected());
        bean.setTypedMinNbPossEvos(bean.getTypedMinNbPossEvosForm().tryRet());
        bean.setTypedMaxNbPossEvos(bean.getTypedMaxNbPossEvosForm().tryRet());
        bean.setIsEvo(bean.getIsEvoForm().tryRet());
        bean.setIsLeg(bean.getIsLegForm().tryRet());
        return ((NaStSt)re(new PokemonBeanStruct(bean),new NaSt[0])).getInstance();
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (PokedexBean) ((PokemonBeanStruct)_instance).getInstance()).search());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
