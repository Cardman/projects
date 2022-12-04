package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MovesBeanLearntGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).getLearnt());
    }
}
