package aiki.beans.map.characters;

import aiki.beans.*;
import code.bean.nat.*;
public class DualFightBeanImageMiniGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (DualFightBean) ((PokemonBeanStruct)_instance).getInstance()).getImageMini());
    }
}
