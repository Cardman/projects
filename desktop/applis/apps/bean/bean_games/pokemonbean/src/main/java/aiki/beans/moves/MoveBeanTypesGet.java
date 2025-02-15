package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
public class MoveBeanTypesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getTypes());
    }
}
