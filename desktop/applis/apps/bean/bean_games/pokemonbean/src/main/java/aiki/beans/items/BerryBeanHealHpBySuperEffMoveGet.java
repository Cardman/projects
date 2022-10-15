package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class BerryBeanHealHpBySuperEffMoveGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (BerryBean) ((PokemonBeanStruct)_instance).getInstance()).getHealHpBySuperEffMove());
    }
}
