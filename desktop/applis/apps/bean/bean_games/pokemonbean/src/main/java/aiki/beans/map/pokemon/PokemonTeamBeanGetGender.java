package aiki.beans.map.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public class PokemonTeamBeanGetGender implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getGender(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}