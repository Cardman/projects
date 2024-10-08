package aiki.beans.simulation;

import aiki.beans.*;
import code.bean.nat.*;
public class SelectItemBeanGetMiniImage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (SelectItemBean) ((PokemonBeanStruct)_instance).getInstance()).getMiniImage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
