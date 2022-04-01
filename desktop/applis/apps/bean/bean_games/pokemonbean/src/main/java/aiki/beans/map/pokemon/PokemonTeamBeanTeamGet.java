package aiki.beans.map.pokemon;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PokemonTeamBeanTeamGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getPkTrainerArray(( (PokemonTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getTeam());
    }
}
