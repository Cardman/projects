package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class AllyBeanGetGender implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (AllyBean) ((PokemonBeanStruct)_instance).getInstance()).getGender(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}