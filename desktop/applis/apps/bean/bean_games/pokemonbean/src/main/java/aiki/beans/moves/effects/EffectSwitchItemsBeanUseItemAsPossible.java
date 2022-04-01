package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectSwitchItemsBeanUseItemAsPossible implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (EffectSwitchItemsBean) ((PokemonBeanStruct)_instance).getInstance()).useItemAsPossible());
    }
}
