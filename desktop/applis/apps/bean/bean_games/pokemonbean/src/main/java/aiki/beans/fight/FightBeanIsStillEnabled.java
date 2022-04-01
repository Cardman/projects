package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class FightBeanIsStillEnabled implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (FightBean) ((PokemonBeanStruct)_instance).getInstance()).isStillEnabled(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
