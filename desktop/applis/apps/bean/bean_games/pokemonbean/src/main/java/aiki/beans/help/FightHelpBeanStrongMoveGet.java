package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class FightHelpBeanStrongMoveGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getStrongMove());
    }
}
