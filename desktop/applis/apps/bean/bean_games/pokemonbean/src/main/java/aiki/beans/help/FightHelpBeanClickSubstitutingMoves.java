package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class FightHelpBeanClickSubstitutingMoves implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).clickSubstitutingMoves(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
