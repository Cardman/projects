package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class ItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrShort(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getIncreasingMaxNbRoundTeamMove());
    }
}
