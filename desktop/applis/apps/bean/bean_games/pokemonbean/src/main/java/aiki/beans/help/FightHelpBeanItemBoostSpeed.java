package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class FightHelpBeanItemBoostSpeed implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).itemBoostSpeed(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
