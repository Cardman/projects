package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MoveBeanRequiredStatusGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getRequiredStatus());
    }
}
