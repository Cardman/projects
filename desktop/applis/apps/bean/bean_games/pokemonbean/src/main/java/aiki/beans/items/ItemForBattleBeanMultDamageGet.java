package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanMultDamageGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getMultDamage());
    }
}
