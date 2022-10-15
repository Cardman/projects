package aiki.beans.map.elements;

import aiki.beans.AreaApparitionStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AreaBeanAreaGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new AreaApparitionStruct(( (AreaBean) ((PokemonBeanStruct)_instance).getInstance()).getArea());
    }
}
