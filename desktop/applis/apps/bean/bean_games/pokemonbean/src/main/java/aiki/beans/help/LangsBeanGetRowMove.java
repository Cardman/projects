package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
public class LangsBeanGetRowMove implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (LangsBean) ((PokemonBeanStruct)_instance).getInstance()).getRowMove(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
