package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectOrderBeanTargetAttacksLastGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (EffectOrderBean) ((PokemonBeanStruct)_instance).getInstance()).getTargetAttacksLast());
    }
}
