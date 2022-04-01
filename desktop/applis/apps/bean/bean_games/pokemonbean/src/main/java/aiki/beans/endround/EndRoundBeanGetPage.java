package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EndRoundBeanGetPage implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EndRoundBean) ((PokemonBeanStruct)_instance).getInstance()).getPage(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
