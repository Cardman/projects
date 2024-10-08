package aiki.beans.map.elements;

import aiki.beans.*;
import code.bean.nat.*;
public class AreaBeanGetImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (AreaBean) ((PokemonBeanStruct)_instance).getInstance()).getImage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
