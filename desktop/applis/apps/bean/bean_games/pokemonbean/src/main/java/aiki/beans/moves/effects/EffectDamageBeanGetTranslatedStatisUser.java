package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EffectDamageBeanGetTranslatedStatisUser implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EffectDamageBean) ((PokemonBeanStruct)_instance).getInstance()).getTranslatedStatisUser(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
