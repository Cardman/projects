package aiki.beans.map.elements;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AreaBeanGetImageFishing implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AreaBean) ((PokemonBeanStruct)_instance).getInstance()).getImageFishing(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
