package aiki.beans.pokemon.evolutions;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EvolutionTeamBeanClickTeam implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EvolutionTeamBean) ((PokemonBeanStruct)_instance).getInstance()).clickTeam(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
