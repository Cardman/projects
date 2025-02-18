package aiki.beans.items;

import aiki.beans.*;
import code.bean.nat.*;
public class BerryBeanCategoryBoostingGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (BerryBean) ((PokemonBeanStruct)_instance).getInstance()).getCategoryBoosting().getTranslation());
    }
}
