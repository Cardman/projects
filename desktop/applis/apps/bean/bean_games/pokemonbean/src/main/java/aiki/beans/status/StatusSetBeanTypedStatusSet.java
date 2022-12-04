package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class StatusSetBeanTypedStatusSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (StatusSetBean) ((PokemonBeanStruct)_instance).getInstance()).setTypedStatus(NaPa.getString(_args[0]).getInstance());
        return NaNu.NULL_VALUE;
    }
}
