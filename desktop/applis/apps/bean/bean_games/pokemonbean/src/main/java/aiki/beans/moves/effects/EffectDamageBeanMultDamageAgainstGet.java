package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EffectDamageBeanMultDamageAgainstGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrRate(( (EffectDamageBean) ((PokemonBeanStruct)_instance).getInstance()).getMultDamageAgainst());
    }
}
