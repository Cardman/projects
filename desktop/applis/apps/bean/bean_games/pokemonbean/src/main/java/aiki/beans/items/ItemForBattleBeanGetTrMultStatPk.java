package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanGetTrMultStatPk implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getTrMultStatPk(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
