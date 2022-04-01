package aiki.beans;

import aiki.map.pokemon.PkTrainer;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class PkTrainerGetItem implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (PkTrainer) ((PkStruct)_instance).getWildPk()).getItem());
    }
}
