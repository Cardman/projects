package aiki.beans.simulation;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectAbilityBeanCancel implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(SelectAbilityBean.cancel());
    }
}
