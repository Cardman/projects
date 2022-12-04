package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanRemainingOtherTrainerPlacesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getShortInt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getRemainingOtherTrainerPlaces());
    }
}
