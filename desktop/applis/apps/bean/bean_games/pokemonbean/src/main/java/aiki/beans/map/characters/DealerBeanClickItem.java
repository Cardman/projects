package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DealerBeanClickItem implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (DealerBean) ((PokemonBeanStruct)_instance).getInstance()).clickItem(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
