package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class BoostBeanGetTrHappiness implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (BoostBean) ((PokemonBeanStruct)_instance).getInstance()).getTrHappiness(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
