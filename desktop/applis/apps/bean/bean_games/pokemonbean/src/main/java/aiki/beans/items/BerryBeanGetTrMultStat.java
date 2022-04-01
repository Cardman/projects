package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class BerryBeanGetTrMultStat implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (BerryBean) ((PokemonBeanStruct)_instance).getInstance()).getTrMultStat(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
