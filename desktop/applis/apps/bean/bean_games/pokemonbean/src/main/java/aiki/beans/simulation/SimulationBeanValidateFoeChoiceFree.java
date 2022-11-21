package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanValidateFoeChoiceFree implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).validateFoeChoiceFree();
        return new StringStruct(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML);
    }
}
