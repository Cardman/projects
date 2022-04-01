package aiki.beans.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class EffectComboBeanIndexSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance()).setIndex(NumParsers.convertToNumber(_args[0]).intStruct());
        return NullStruct.NULL_VALUE;
    }
}
