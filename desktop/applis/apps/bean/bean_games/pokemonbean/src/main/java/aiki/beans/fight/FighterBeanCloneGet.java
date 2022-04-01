package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class FighterBeanCloneGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getClone(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
