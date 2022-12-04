package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundGlobalBeanImmuneTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeanNatCommonLgNames.getStringArray(( (EffectEndRoundGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getImmuneTypes());
    }
}
