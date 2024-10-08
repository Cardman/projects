package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class ItemBeanItemImageGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (ItemBean) ((PokemonBeanStruct)_instance).getInstance()).getItemImage());
    }
}
