package aiki.beans.map.characters;

import aiki.beans.*;
import code.bean.nat.*;
public class DealerBeanGetItems implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getKeys(( (DealerBean) ((PokemonBeanStruct)_instance).getInstance()).getItemsDealer());
    }
}
