package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanGetKoPlayers implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getKoPlayers());
    }
}
