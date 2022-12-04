package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusBeanEffectsPartnerGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getEffPartStat(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getEffectsPartner());
    }
}
