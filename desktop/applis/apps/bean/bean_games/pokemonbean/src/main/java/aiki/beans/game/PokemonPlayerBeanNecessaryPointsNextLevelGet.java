package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerBeanNecessaryPointsNextLevelGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getNecessaryPointsNextLevel());
    }
}
