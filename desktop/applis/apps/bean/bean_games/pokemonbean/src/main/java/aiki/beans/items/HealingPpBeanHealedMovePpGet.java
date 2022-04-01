package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
public class HealingPpBeanHealedMovePpGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LongStruct(( (HealingPpBean) ((PokemonBeanStruct)_instance).getInstance()).getHealedMovePp());
    }
}
