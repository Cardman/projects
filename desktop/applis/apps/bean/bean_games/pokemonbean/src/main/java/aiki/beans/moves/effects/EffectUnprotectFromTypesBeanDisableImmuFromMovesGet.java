package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EffectUnprotectFromTypesBeanDisableImmuFromMovesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanNatCommonLgNames.getStringArray(( (EffectUnprotectFromTypesBean) ((PokemonBeanStruct)_instance).getInstance()).getDisableImmuFromMoves());
    }
}
