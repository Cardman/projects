package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundBeanIndexSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EffectEndRoundBean) ((PokemonBeanStruct)_instance).getInstance()).setIndex(NaPa.convertToNumber(_args[0]).intStruct());
        return NaNu.NULL_VALUE;
    }
}
