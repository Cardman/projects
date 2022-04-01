package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class LangsBeanGetTrLang implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (LangsBean) ((PokemonBeanStruct)_instance).getInstance()).getTrLang(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
