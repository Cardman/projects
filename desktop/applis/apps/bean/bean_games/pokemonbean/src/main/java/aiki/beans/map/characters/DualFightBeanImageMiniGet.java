package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DualFightBeanImageMiniGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (DualFightBean) ((PokemonBeanStruct)_instance).getInstance()).getImageMini());
    }
}
