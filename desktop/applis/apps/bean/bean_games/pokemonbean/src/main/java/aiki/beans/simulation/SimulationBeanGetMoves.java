package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanGetMoves implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getMoves(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
