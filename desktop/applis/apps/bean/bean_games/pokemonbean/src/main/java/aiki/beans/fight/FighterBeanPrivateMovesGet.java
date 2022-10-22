package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class FighterBeanPrivateMovesGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return AikiBeansFightStd.getMvTpStr(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getPrivateMoves());
    }
}
