package aiki.beans.effects;

import aiki.beans.ComboDtoStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectComboBeanCombosSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).setCombos(((ComboDtoStruct)_args[0]).getComboDto());
        return NaNu.NULL_VALUE;
    }
}
