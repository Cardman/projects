package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class MovesBeanClickLink implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MovesBean) ((PokemonBeanStruct)_instance).getInstance()).clickLink(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
