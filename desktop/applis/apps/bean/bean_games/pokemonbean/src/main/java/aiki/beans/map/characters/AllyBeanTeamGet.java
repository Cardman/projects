package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AllyBeanTeamGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getPkTrainerArray(( (AllyBean) ((PokemonBeanStruct)_instance).getInstance()).getTeam());
    }
}
