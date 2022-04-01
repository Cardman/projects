package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class TarotSumDeclaringPlayerNickname implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct((((TarotSumDeclaringPlayerStruct) _instance).getSumDeclaringPlayer()).getNickname());
    }
}
