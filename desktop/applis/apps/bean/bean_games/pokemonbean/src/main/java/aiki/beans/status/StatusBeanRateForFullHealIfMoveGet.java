package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class StatusBeanRateForFullHealIfMoveGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getRateForFullHealIfMove());
    }
}
