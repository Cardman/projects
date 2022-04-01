package cards.belote.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class BeloteSumDeclaringPlayerStatut implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct((((BeloteSumDeclaringPlayerStruct)_instance).getSumDeclaringPlayer()).getStatut());
    }
}
