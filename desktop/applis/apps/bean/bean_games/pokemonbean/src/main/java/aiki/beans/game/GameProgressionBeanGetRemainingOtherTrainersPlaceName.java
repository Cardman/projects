package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanGetRemainingOtherTrainersPlaceName implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getRemainingOtherTrainersPlaceName(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
