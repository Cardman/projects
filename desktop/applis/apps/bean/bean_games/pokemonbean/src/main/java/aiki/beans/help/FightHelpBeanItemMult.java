package aiki.beans.help;

import aiki.beans.*;
import aiki.fight.enums.Statistic;
import code.bean.nat.*;

public class FightHelpBeanItemMult implements NatCaller{

    private final Statistic stat;

    public FightHelpBeanItemMult(Statistic _stat) {
        stat = _stat;
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).itemMult(NaPa.convertToNumber(_args[0]).intStruct(), stat));
    }
}
