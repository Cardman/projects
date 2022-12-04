package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.WithFilterBean;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MovesBeanLearntSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).setLearnt(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
