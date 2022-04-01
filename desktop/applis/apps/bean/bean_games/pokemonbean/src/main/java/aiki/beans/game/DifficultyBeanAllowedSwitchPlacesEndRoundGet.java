package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class DifficultyBeanAllowedSwitchPlacesEndRoundGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance()).getAllowedSwitchPlacesEndRound());
    }
}
