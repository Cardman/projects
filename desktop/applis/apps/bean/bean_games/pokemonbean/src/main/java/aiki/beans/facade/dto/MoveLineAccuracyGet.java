package aiki.beans.facade.dto;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;

public class MoveLineAccuracyGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((MvLineStruct) _instance).getWildPk()).getAccuracy());
    }
}
