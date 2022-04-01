package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class FightHelpBeanMinHpNotKoGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getMinHpNotKo(),BeanNatCommonLgNames.TYPE_RATE);
    }
}
