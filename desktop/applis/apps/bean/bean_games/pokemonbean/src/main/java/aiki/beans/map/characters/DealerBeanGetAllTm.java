package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class DealerBeanGetAllTm implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (DealerBean) ((PokemonBeanStruct)_instance).getInstance()).getAllTm());
    }
}
