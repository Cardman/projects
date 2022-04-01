package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class FighterBeanIsFoeIncrUserAccuracyTeam implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).isFoeIncrUserAccuracyTeam(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
