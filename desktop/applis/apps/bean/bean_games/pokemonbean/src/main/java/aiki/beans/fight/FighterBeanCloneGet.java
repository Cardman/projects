package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class FighterBeanCloneGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getClone());
    }
}
