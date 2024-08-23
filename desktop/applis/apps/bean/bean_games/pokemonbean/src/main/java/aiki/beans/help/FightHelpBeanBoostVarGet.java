package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;

public class FightHelpBeanBoostVarGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getBoostVar());
    }
}
