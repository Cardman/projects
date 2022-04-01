package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class FighterBeanGetIncrPrivateMovesTeam implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getIncrPrivateMovesTeam(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
