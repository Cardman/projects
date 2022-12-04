package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanLastSufferedMoveTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getLastSufferedMoveTypes());
    }
}
