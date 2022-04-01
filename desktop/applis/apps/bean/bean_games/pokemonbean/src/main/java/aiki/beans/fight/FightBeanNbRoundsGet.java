package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.LgIntStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class FightBeanNbRoundsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LgIntStruct(( (FightBean) ((PokemonBeanStruct)_instance).getInstance()).getNbRounds(),BeanNatCommonLgNames.TYPE_LG_INT);
    }
}
