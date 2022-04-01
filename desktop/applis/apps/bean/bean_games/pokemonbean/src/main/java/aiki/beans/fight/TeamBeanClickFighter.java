package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class TeamBeanClickFighter implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).clickFighter(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
