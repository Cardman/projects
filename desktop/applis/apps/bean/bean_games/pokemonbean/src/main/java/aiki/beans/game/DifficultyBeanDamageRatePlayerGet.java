package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
public class DifficultyBeanDamageRatePlayerGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeanLgNames.wrapStd(( (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance()).getDamageRatePlayer());
    }
}
