package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MovesBeanMaxAccuracySet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (MovesBean) ((PokemonBeanStruct)_instance).getInstance()).setMaxAccuracy(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
