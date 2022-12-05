package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class TrainerBeanGetTeamsRewards implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.arrId(( (TrainerBean) ((PokemonBeanStruct)_instance).getInstance()).getTeamsRewards().size());
    }
}
