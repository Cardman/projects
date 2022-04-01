package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanLayers implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getLayers(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).layers(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
