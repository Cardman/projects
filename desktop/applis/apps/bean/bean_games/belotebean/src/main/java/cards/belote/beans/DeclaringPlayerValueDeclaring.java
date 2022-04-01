package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class DeclaringPlayerValueDeclaring implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct((((DeclaringPlayerValueStruct)_instance).getDeclaringPlayerValue()).getDeclaring());
    }
}
