package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class AbilityBeanImmuAllyFromMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuAllyFromMoves());
    }
}
