package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectSwitchAbilitiesBeanGiveToUser implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (EffectSwitchAbilitiesBean) ((PokemonBeanStruct)_instance).getInstance()).giveToUser());
    }
}
