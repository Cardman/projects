package aiki.beans.effects;

import aiki.beans.*;
import code.bean.nat.*;
public class EffectComboBeanMovesGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getValues(( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).getMoves());
    }
}
