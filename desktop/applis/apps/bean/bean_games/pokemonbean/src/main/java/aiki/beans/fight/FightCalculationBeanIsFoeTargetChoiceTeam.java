package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class FightCalculationBeanIsFoeTargetChoiceTeam implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).isFoeTargetChoiceTeam(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
