package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class FighterBeanGroundPlaceSubstGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getGroundPlaceSubst());
    }
}
