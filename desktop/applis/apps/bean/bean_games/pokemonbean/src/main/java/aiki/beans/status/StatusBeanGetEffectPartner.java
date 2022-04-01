package aiki.beans.status;

import aiki.beans.EffectPartnerStatusStruct;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class StatusBeanGetEffectPartner implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new EffectPartnerStatusStruct(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getEffectPartner(),PokemonStandards.TYPE_EFFECT_PARTNER_STATUS);
    }
}
