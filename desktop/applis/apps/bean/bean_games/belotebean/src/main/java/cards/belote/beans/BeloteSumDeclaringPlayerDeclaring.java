package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class BeloteSumDeclaringPlayerDeclaring implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BeloteStandards.getDeclaringPlayerValueArray((((BeloteSumDeclaringPlayerStruct)_instance).getSumDeclaringPlayer()).getDeclaring());
    }
}
