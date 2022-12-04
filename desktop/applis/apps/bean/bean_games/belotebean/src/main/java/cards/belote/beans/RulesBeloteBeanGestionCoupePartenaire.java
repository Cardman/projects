package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class RulesBeloteBeanGestionCoupePartenaire implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(((RulesBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getGestionCoupePartenaire());
    }
}
