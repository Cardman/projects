package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class GameProgressionBeanFinishedGameGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getFinishedGame());
    }
}
