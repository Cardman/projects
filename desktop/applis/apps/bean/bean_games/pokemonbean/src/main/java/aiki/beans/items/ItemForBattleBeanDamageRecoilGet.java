package aiki.beans.items;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class ItemForBattleBeanDamageRecoilGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)_instance).getInstance()).getDamageRecoil());
    }
}
