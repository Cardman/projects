package aiki.beans.moves;

import aiki.beans.*;
import code.bean.nat.*;
public class MoveBeanCategoryGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getCategory().getTranslation());
    }
}
