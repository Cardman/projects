package aiki.beans.fight;

import aiki.beans.*;
import code.bean.nat.*;
public class FighterBeanBelongingToPlayerGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(CommonBean.toBool(((FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getBelongingToPlayer()));
    }
}
