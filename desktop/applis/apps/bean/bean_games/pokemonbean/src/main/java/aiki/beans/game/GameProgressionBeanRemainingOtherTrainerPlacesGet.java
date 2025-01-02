package aiki.beans.game;

import aiki.beans.*;
import code.bean.nat.*;
public class GameProgressionBeanRemainingOtherTrainerPlacesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getIntInt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getRemainingOtherTrainerPlaces());
    }
}
