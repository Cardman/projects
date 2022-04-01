package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectGlobalBeanGetTrInvokedMoveTerrain implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectGlobalBean) ((PokemonBeanStruct)_instance).getInstance()).getTrInvokedMoveTerrain());
    }
}
