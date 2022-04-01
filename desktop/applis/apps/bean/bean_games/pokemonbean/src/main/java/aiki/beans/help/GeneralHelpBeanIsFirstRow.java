package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class GeneralHelpBeanIsFirstRow implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).isFirstRow(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
