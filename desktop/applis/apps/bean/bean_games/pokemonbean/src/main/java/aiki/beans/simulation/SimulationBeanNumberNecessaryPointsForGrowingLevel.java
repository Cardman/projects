package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanNumberNecessaryPointsForGrowingLevel implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).numberNecessaryPointsForGrowingLevel(NumParsers.convertToNumber(_args[0]).intStruct()),BeanNatCommonLgNames.TYPE_RATE);
    }
}
