package aiki.beans.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonBeanHeightGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (PokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getHeight(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
