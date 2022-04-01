package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class FightCalculationBeanGetTargetNameFoeChoice implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance()).getTargetNameFoeChoice(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
