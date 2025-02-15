package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
public final class MovesBeanSearch implements NatCaller, IntBeanAction {
    private final MovesBean bean;
    public MovesBeanSearch() {
        this(null);
    }
    public MovesBeanSearch(MovesBean _m) {
        bean = _m;
    }

    @Override
    public String actionBean() {
        bean.setTypedName(bean.getTypedNameForm().tryRet());
        bean.setTypedType(bean.getTypedTypeForm().tryRet());
        bean.setTypedCategory(bean.getTypedCategoryForm().tryRet());
        bean.setWholeWord(bean.getWholeWordForm().isSelected());
        bean.setMinPower(bean.getMinPowerForm().tryRet());
        bean.setMaxPower(bean.getMaxPowerForm().tryRet());
        bean.setMinAccuracy(bean.getMinAccuracyForm().tryRet());
        bean.setMaxAccuracy(bean.getMaxAccuracyForm().tryRet());
        bean.setLearnt(bean.getLearntForm().tryRet());
        return ((NaStSt)re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MovesBean) ((PokemonBeanStruct)_instance).getInstance()).search());
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
