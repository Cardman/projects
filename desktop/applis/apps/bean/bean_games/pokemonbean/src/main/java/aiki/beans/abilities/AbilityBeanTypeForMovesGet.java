package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
public class AbilityBeanTypeForMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AbilityBean) ((PokemonBeanStruct)_instance).getInstance()).getTypeForMoves().getTranslation());
    }
}
