package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.IntBeanAction;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NaSt;
import code.bean.nat.NaStSt;

public final class SimulationBeanSelectAllyPkValidation implements IntBeanAction {
    private final SimulationBean bean;
    private final String act;
    private final int index;

    public SimulationBeanSelectAllyPkValidation(SimulationBean _b, String _a, int _i) {
        this.index = _i;
        this.act = _a;
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setSelectedAllyAction(act);
        bean.setSelectedAllyPk(index);
        return ((NaStSt)new SimulationBeanSelectAllyPk().re(new PokemonBeanStruct(getBean()),new NaSt[0])).getInstance();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
