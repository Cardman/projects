package aiki.beans.help;

import aiki.comparators.DictionaryComparator;
import aiki.facade.FacadeGame;
import aiki.fight.util.TypesDuo;
import aiki.instances.Instances;
import code.maths.Rate;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanEfficiencyTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        DictionaryComparator<TypesDuo, Rate> ls_ = FightHelpBean.efficiencyInit(db().getData(),EN);
        assertEq(4,ls_.size());
    }
    @Test
    public void nextRowAfter1() {
        assertTrue(callFightHelpBeanNextRowAfter(bean(db()),0));
    }
    @Test
    public void nextRowAfter2() {
        assertFalse(callFightHelpBeanNextRowAfter(bean(db()),1));
    }
    @Test
    public void nextRowAfter3() {
        assertTrue(callFightHelpBeanNextRowAfter(bean(db()),2));
    }
    @Test
    public void nextRowAfter4() {
        assertFalse(callFightHelpBeanNextRowAfter(bean(db()),3));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1,T_TYPE1_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE2,T_TYPE2_TR);
        f_.getData().getTableTypes().addEntry(new TypesDuo(T_TYPE1,T_TYPE1), Rate.newRate("2"));
        f_.getData().getTableTypes().addEntry(new TypesDuo(T_TYPE2,T_TYPE2), Rate.newRate("3"));
        f_.getData().getTableTypes().addEntry(new TypesDuo(T_TYPE2,T_TYPE1), Rate.newRate("4"));
        f_.getData().getTableTypes().addEntry(new TypesDuo(T_TYPE1,T_TYPE2), Rate.newRate("5"));
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
