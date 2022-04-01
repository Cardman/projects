package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class EditPokemonBeanHealSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (EditPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).setHeal(BooleanStruct.isTrue(_args[0]));
        return NullStruct.NULL_VALUE;
    }
}
