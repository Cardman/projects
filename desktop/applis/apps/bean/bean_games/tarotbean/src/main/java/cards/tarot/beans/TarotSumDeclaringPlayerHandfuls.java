package cards.tarot.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class TarotSumDeclaringPlayerHandfuls implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return TarotStandards.getStringShortTree((((TarotSumDeclaringPlayerStruct) _instance).getSumDeclaringPlayer()).getHandfuls());
    }
}
