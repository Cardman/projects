package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectEndRoundBeanClickItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectEndRoundBean) ((PokemonBeanStruct)_instance).getInstance()).clickItem(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
