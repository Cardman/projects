package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class FighterBeanDamageRateByTypeGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return AikiBeansFightStd.getMultPowStr(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getDamageRateByType());
    }
}
