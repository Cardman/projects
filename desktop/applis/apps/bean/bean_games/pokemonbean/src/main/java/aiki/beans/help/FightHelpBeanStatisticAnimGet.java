package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class FightHelpBeanStatisticAnimGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getSta(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getStatisticAnim());
    }
}
