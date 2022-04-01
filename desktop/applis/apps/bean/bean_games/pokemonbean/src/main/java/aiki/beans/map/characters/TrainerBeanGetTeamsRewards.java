package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class TrainerBeanGetTeamsRewards implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getPkTeam(( (TrainerBean) ((PokemonBeanStruct)_instance).getInstance()).getTeamsRewards());
    }
}
