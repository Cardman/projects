package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanGetImagePokemonNotAll implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getImagePokemonNotAll(NaPa.convertToNumber(_args[0]).intStruct(),NaPa.convertToNumber(_args[1]).intStruct(),NaPa.convertToNumber(_args[2]).intStruct()));
    }
}
