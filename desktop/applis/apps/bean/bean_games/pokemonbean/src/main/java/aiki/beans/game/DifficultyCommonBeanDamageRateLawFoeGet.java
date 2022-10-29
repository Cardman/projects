package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
public class DifficultyCommonBeanDamageRateLawFoeGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanLgNames.wrapStd(((DifficultyCommonBean)((PokemonBeanStruct)_instance).getInstance()).getDamageRateLawFoe());
    }
}
