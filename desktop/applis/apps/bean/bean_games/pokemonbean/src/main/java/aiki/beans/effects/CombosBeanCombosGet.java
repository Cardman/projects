package aiki.beans.effects;

import aiki.beans.ComboDtoStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class CombosBeanCombosGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new ComboDtoStruct(( (CombosBean) ((PokemonBeanStruct)_instance).getInstance()).getCombos());
    }
}
