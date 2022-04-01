package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanRateWinningExpPtsFightGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getRateWinningExpPtsFight(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
