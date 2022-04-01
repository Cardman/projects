package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.LgIntStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class GameProgressionBeanMoneyGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new LgIntStruct(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getMoney(),BeanNatCommonLgNames.TYPE_LG_INT);
    }
}
