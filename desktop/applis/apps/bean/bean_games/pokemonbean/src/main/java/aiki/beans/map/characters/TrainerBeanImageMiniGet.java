package aiki.beans.map.characters;

import aiki.beans.*;
import code.bean.nat.*;
public class TrainerBeanImageMiniGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (TrainerBean) ((PokemonBeanStruct)_instance).getInstance()).getImageMini());
    }
}
