package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class GameProgressionBeanGetImagePokemonFull implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getImagePokemonFull(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct()));
    }
}
