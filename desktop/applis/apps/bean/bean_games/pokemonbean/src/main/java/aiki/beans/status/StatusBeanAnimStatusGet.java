package aiki.beans.status;

import aiki.beans.*;
import code.bean.nat.*;
public class StatusBeanAnimStatusGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getAnimStatus());
    }
}
