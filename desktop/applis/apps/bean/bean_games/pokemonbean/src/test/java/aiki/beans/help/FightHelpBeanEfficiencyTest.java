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
    @Test
    public void getEfficiency() {
        assertSizeEq(4,callFightHelpBeanEfficiencyGet(bean(db())));
    }
    @Test
    public void getEfficiency1() {
        assertEq("2",callFightHelpBeanGetEfficiency(bean(db()),0,0));
    }
    @Test
    public void getEfficiencyKey1() {
        assertEq(T_TYPE1_TR,callTypesDuoGetDamageType(first(elt(callFightHelpBeanEfficiencyGet(bean(db())),0))));
    }
    @Test
    public void getEfficiencyValue1() {
        assertEq(T_TYPE1_TR,callTypesDuoGetPokemonType(first(elt(callFightHelpBeanEfficiencyGet(bean(db())),0))));
    }
    @Test
    public void getEfficiencyVal1() {
        assertEq(Rate.newRate("2"),second(elt(callFightHelpBeanEfficiencyGet(bean(db())),0)));
    }
    @Test
    public void getEfficiency2() {
        assertEq("4",callFightHelpBeanGetEfficiency(bean(db()),0,1));
    }
    @Test
    public void getEfficiencyKey2() {
        assertEq(T_TYPE2_TR,callTypesDuoGetDamageType(first(elt(callFightHelpBeanEfficiencyGet(bean(db())),1))));
    }
    @Test
    public void getEfficiencyValue2() {
        assertEq(T_TYPE1_TR,callTypesDuoGetPokemonType(first(elt(callFightHelpBeanEfficiencyGet(bean(db())),1))));
    }
    @Test
    public void getEfficiencyVal2() {
        assertEq(Rate.newRate("4"),second(elt(callFightHelpBeanEfficiencyGet(bean(db())),1)));
    }
    @Test
    public void getEfficiency3() {
        assertEq("5",callFightHelpBeanGetEfficiency(bean(db()),1,0));
    }
    @Test
    public void getEfficiencyKey3() {
        assertEq(T_TYPE1_TR,callTypesDuoGetDamageType(first(elt(callFightHelpBeanEfficiencyGet(bean(db())),2))));
    }
    @Test
    public void getEfficiencyValue3() {
        assertEq(T_TYPE2_TR,callTypesDuoGetPokemonType(first(elt(callFightHelpBeanEfficiencyGet(bean(db())),2))));
    }
    @Test
    public void getEfficiencyVal3() {
        assertEq(Rate.newRate("5"),second(elt(callFightHelpBeanEfficiencyGet(bean(db())),2)));
    }
    @Test
    public void getEfficiency4() {
        assertEq("3",callFightHelpBeanGetEfficiency(bean(db()),1,1));
    }
    @Test
    public void getEfficiencyKey4() {
        assertEq(T_TYPE2_TR,callTypesDuoGetDamageType(first(elt(callFightHelpBeanEfficiencyGet(bean(db())),3))));
    }
    @Test
    public void getEfficiencyValue4() {
        assertEq(T_TYPE2_TR,callTypesDuoGetPokemonType(first(elt(callFightHelpBeanEfficiencyGet(bean(db())),3))));
    }
    @Test
    public void getEfficiencyVal4() {
        assertEq(Rate.newRate("3"),second(elt(callFightHelpBeanEfficiencyGet(bean(db())),3)));
    }
    @Test
    public void getTypes() {
        assertSizeEq(2,callFightHelpBeanTypesGet(bean(db())));
    }
    @Test
    public void getTypes1() {
        assertEq(T_TYPE1_TR,elt(callFightHelpBeanTypesGet(bean(db())),0));
    }
    @Test
    public void getTypes2() {
        assertEq(T_TYPE2_TR,elt(callFightHelpBeanTypesGet(bean(db())),1));
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
