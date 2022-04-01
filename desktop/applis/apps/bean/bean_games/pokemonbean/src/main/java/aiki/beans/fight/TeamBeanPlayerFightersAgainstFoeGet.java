package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class TeamBeanPlayerFightersAgainstFoeGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getByteBytes(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).getPlayerFightersAgainstFoe());
    }
}
