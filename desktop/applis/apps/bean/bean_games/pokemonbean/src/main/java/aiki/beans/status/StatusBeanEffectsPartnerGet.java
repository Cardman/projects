package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class StatusBeanEffectsPartnerGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getEffPartStat(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getEffectsPartner());
    }
}
