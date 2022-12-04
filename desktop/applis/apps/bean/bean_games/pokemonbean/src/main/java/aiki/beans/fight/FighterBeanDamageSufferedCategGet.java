package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanDamageSufferedCategGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return AikiBeansFightStd.getSuffCatStr(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getDamageSufferedCateg());
    }
}
