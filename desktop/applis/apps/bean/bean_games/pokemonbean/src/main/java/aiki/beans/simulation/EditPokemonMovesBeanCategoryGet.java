package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EditPokemonMovesBeanCategoryGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(((EditPokemonMovesBean) ((PokemonBeanStruct) _instance).getInstance()).getTypedCategory().tryRet());
    }
}
