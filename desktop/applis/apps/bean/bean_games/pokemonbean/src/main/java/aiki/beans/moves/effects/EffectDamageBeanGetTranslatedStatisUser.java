package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectDamageBeanGetTranslatedStatisUser implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectDamageBean) ((PokemonBeanStruct)_instance).getInstance()).getTranslatedStatisUser(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
