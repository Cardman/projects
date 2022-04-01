package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class StatusBeanGetTrMultStat implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getTrMultStat(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
