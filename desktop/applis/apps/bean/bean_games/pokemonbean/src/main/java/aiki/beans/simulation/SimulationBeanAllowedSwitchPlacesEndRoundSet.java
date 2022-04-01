package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanAllowedSwitchPlacesEndRoundSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).setAllowedSwitchPlacesEndRound(BooleanStruct.isTrue(_args[0]));
        return NullStruct.NULL_VALUE;
    }
}
