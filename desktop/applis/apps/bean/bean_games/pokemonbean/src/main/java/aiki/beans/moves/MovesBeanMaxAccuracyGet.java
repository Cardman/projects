package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class MovesBeanMaxAccuracyGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MovesBean) ((PokemonBeanStruct)_instance).getInstance()).getMaxAccuracy().tryRet());
    }
}
