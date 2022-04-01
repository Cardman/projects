package aiki.beans.map.pokemon;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonTeamBeanNoFightSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).setNoFight(NumParsers.convertToNumber(_args[0]).intStruct());
        return NullStruct.NULL_VALUE;
    }
}
