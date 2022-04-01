package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanClickWeather implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).clickWeather(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
