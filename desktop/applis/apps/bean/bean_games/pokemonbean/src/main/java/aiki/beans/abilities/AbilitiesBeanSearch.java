package aiki.beans.abilities;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class AbilitiesBeanSearch implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (AbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).search());
    }
}
