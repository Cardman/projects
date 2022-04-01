package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class HealingPpBeanLimitedPpMoves implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (HealingPpBean) ((PokemonBeanStruct)_instance).getInstance()).limitedPpMoves());
    }
}
