package aiki.beans.effects;

import aiki.beans.ComboDtoStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class EffectComboBeanCombosSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).setCombos(((ComboDtoStruct)_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
