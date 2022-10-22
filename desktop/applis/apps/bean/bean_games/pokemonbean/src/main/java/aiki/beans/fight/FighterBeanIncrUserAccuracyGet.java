package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class FighterBeanIncrUserAccuracyGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return AikiBeansFightStd.getWcMvTpBool(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getIncrUserAccuracy());
    }
}
