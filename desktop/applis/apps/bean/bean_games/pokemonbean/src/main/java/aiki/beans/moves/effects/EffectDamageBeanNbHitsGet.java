package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
public class EffectDamageBeanNbHitsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LongStruct(( (EffectDamageBean) ((PokemonBeanStruct)_instance).getInstance()).getNbHits());
    }
}
