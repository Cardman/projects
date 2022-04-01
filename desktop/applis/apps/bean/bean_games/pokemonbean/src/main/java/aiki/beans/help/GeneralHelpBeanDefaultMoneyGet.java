package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class GeneralHelpBeanDefaultMoneyGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getDefaultMoney(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
