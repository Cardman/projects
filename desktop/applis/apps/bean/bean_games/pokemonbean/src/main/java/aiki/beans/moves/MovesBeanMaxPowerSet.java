package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MovesBeanMaxPowerSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (MovesBean) ((PokemonBeanStruct)_instance).getInstance()).setMaxPower(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
