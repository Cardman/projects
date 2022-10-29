package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class DifficultyCommonBeanDamageRatesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrStr(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getDamageRates());
    }
}
