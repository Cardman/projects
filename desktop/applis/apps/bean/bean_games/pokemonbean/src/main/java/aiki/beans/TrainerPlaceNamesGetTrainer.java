package aiki.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class TrainerPlaceNamesGetTrainer implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((TrainerPlaceNamesStruct) _instance).getInstance()).getTrainer());
    }
}
