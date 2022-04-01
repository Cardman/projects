package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EffectDamageBeanHitsLawGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getLongRate(( (EffectDamageBean) ((PokemonBeanStruct)_instance).getInstance()).getHitsLaw());
    }
}
