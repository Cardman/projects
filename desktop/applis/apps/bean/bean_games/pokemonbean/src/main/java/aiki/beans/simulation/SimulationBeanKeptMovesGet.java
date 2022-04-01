package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class SimulationBeanKeptMovesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getSelectLineMove(( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).getKeptMoves());
    }
}
