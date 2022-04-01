package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MoveLineBeanTypesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (MoveLineBean) ((PokemonBeanStruct)_instance).getInstance()).getTypes());
    }
}
