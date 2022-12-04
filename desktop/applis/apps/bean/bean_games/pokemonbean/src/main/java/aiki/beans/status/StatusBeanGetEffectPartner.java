package aiki.beans.status;

import aiki.beans.EffectPartnerStatusStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusBeanGetEffectPartner implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new EffectPartnerStatusStruct(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getEffectPartner());
    }
}
