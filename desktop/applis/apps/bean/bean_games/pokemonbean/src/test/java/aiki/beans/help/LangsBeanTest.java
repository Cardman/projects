package aiki.beans.help;

import org.junit.Test;

public final class LangsBeanTest extends InitDbLangs {
    @Test
    public void getLanguages1() {
        assertSizeEq(2,callLangsBeanLanguagesGet());
    }
    @Test
    public void getLanguages2() {
        assertEq(EN,elt(callLangsBeanLanguagesGet(),0));
    }
    @Test
    public void getLanguages3() {
        assertEq(FR,elt(callLangsBeanLanguagesGet(),1));
    }
    @Test
    public void getTrLang1() {
        assertEq("EN1",callLangsBeanGetTrLang(0));
    }
    @Test
    public void getTrLang2() {
        assertEq("FR2",callLangsBeanGetTrLang(1));
    }
    @Test
    public void getKeysGenders() {
        assertSizeEq(3,callLangsBeanGetKeysGenders());
    }
    @Test
    public void getRowGenderCount1() {
        assertSizeEq(2,callLangsBeanGetRowGender(0));
    }
    @Test
    public void getRowGenderCount2() {
        assertSizeEq(2,callLangsBeanGetRowGender(1));
    }
    @Test
    public void getRowGenderCount3() {
        assertSizeEq(2,callLangsBeanGetRowGender(2));
    }
    @Test
    public void getRowGender1() {
        assertEq("FE1",elt(callLangsBeanGetRowGender(0),0));
    }
    @Test
    public void getRowGender2() {
        assertEq("MA1",elt(callLangsBeanGetRowGender(1),0));
    }
    @Test
    public void getRowGender3() {
        assertEq("NO_G1",elt(callLangsBeanGetRowGender(2),0));
    }
    @Test
    public void getRowGender4() {
        assertEq("FE2",elt(callLangsBeanGetRowGender(0),1));
    }
    @Test
    public void getRowGender5() {
        assertEq("MA2",elt(callLangsBeanGetRowGender(1),1));
    }
    @Test
    public void getRowGender6() {
        assertEq("NO_G2",elt(callLangsBeanGetRowGender(2),1));
    }
    @Test
    public void getKeysBooleans() {
        assertSizeEq(3,callLangsBeanGetKeysBooleans());
    }
    @Test
    public void getRowBooleanCount1() {
        assertSizeEq(2,callLangsBeanGetRowBoolean(0));
    }
    @Test
    public void getRowBooleanCount2() {
        assertSizeEq(2,callLangsBeanGetRowBoolean(1));
    }
    @Test
    public void getRowBooleanCount3() {
        assertSizeEq(2,callLangsBeanGetRowBoolean(2));
    }
    @Test
    public void getRowBoolean1() {
        assertEq("NO1",elt(callLangsBeanGetRowBoolean(0),0));
    }
    @Test
    public void getRowBoolean2() {
        assertEq("YES1",elt(callLangsBeanGetRowBoolean(1),0));
    }
    @Test
    public void getRowBoolean3() {
        assertEq("YES_AND_NO1",elt(callLangsBeanGetRowBoolean(2),0));
    }
    @Test
    public void getRowBoolean4() {
        assertEq("NO2",elt(callLangsBeanGetRowBoolean(0),1));
    }
    @Test
    public void getRowBoolean5() {
        assertEq("YES2",elt(callLangsBeanGetRowBoolean(1),1));
    }
    @Test
    public void getRowBoolean6() {
        assertEq("YES_AND_NO2",elt(callLangsBeanGetRowBoolean(2),1));
    }
    @Test
    public void getKeysEnvironments() {
        assertSizeEq(9,callLangsBeanGetKeysEnvironments());
    }
    @Test
    public void getRowEnvironmentCount() {
        assertSizeEq(2,callLangsBeanGetRowEnvironment(0));
    }
    @Test
    public void getRowEnvironment1() {
        assertEq("BUILDING1",elt(callLangsBeanGetRowEnvironment(0),0));
    }
    @Test
    public void getRowEnvironment2() {
        assertEq("BUILDING2",elt(callLangsBeanGetRowEnvironment(0),1));
    }
    @Test
    public void getKeysStatistics() {
        assertSizeEq(10,callLangsBeanGetKeysStatistics());
    }
    @Test
    public void getRowStatisticCount() {
        assertSizeEq(2,callLangsBeanGetRowStatistic(0));
    }
    @Test
    public void getRowStatistic1() {
        assertEq("ACCURACY1",elt(callLangsBeanGetRowStatistic(0),0));
    }
    @Test
    public void getRowStatistic2() {
        assertEq("ACCURACY2",elt(callLangsBeanGetRowStatistic(0),1));
    }
    @Test
    public void getKeysTargets() {
        assertSizeEq(13,callLangsBeanGetKeysTargets());
    }
    @Test
    public void getRowTargetCount() {
        assertSizeEq(2,callLangsBeanGetRowTarget(0));
    }
    @Test
    public void getRowTarget1() {
        assertEq("ADJ_ADV1",elt(callLangsBeanGetRowTarget(0),0));
    }
    @Test
    public void getRowTarget2() {
        assertEq("ADJ_ADV2",elt(callLangsBeanGetRowTarget(0),1));
    }
    @Test
    public void getKeysCategories() {
        assertSizeEq(1,callLangsBeanGetKeysCategories());
    }
    @Test
    public void getRowCategoryCount() {
        assertSizeEq(2,callLangsBeanGetRowCategory(0));
    }
    @Test
    public void getRowCategory1() {
        assertEq("C_CA1",elt(callLangsBeanGetRowCategory(0),0));
    }
    @Test
    public void getRowCategory2() {
        assertEq("C_CA2",elt(callLangsBeanGetRowCategory(0),1));
    }
    @Test
    public void getKeysTypes() {
        assertSizeEq(1,callLangsBeanGetKeysTypes());
    }
    @Test
    public void getRowTypeCount() {
        assertSizeEq(2,callLangsBeanGetRowType(0));
    }
    @Test
    public void getRowType1() {
        assertEq("T_T1",elt(callLangsBeanGetRowType(0),0));
    }
    @Test
    public void getRowType2() {
        assertEq("T_T2",elt(callLangsBeanGetRowType(0),1));
    }
    @Test
    public void getKeysAbilities() {
        assertSizeEq(1,callLangsBeanGetKeysAbilities());
    }
    @Test
    public void getRowAbilityCount() {
        assertSizeEq(2,callLangsBeanGetRowAbility(0));
    }
    @Test
    public void getRowAbility1() {
        assertEq("A_ABILITY1",elt(callLangsBeanGetRowAbility(0),0));
    }
    @Test
    public void getRowAbility2() {
        assertEq("A_ABILITY2",elt(callLangsBeanGetRowAbility(0),1));
    }
    @Test
    public void getKeysClItems() {
        assertSizeEq(1,callLangsBeanGetKeysDesc());
    }
    @Test
    public void getRowClItemCount() {
        assertSizeEq(2,callLangsBeanGetRowDesc(0));
    }
    @Test
    public void getRowClItem1() {
        assertEq("I_ITEM_1",elt(callLangsBeanGetRowDesc(0),0));
    }
    @Test
    public void getRowClItem2() {
        assertEq("I_ITEM_2",elt(callLangsBeanGetRowDesc(0),1));
    }
    @Test
    public void getKeysItems() {
        assertSizeEq(1,callLangsBeanGetKeysItems());
    }
    @Test
    public void getRowItemCount() {
        assertSizeEq(2,callLangsBeanGetRowItem(0));
    }
    @Test
    public void getRowItem1() {
        assertEq("I_ITEM1",elt(callLangsBeanGetRowItem(0),0));
    }
    @Test
    public void getRowItem2() {
        assertEq("I_ITEM2",elt(callLangsBeanGetRowItem(0),1));
    }
    @Test
    public void getKeysMoves() {
        assertSizeEq(1,callLangsBeanGetKeysMoves());
    }
    @Test
    public void getRowMoveCount() {
        assertSizeEq(2,callLangsBeanGetRowMove(0));
    }
    @Test
    public void getRowMove1() {
        assertEq("M_DAM1",elt(callLangsBeanGetRowMove(0),0));
    }
    @Test
    public void getRowMove2() {
        assertEq("M_DAM2",elt(callLangsBeanGetRowMove(0),1));
    }
    @Test
    public void getKeysPokemon() {
        assertSizeEq(1,callLangsBeanGetKeysPokemon());
    }
    @Test
    public void getRowPokemonCount() {
        assertSizeEq(2,callLangsBeanGetRowPokemon(0));
    }
    @Test
    public void getRowPokemon1() {
        assertEq("P_POKEMON1",elt(callLangsBeanGetRowPokemon(0),0));
    }
    @Test
    public void getRowPokemon2() {
        assertEq("P_POKEMON2",elt(callLangsBeanGetRowPokemon(0),1));
    }
    @Test
    public void getKeysStatus() {
        assertSizeEq(1,callLangsBeanGetKeysStatus());
    }
    @Test
    public void getRowStatusCount() {
        assertSizeEq(2,callLangsBeanGetRowStatus(0));
    }
    @Test
    public void getRowStatus1() {
        assertEq("S_STA_SIM1",elt(callLangsBeanGetRowStatus(0),0));
    }
    @Test
    public void getRowStatus2() {
        assertEq("S_STA_SIM2",elt(callLangsBeanGetRowStatus(0),1));
    }
    @Test
    public void getKeysFct() {
        assertSizeEq(1,callLangsBeanGetKeysMath());
    }
    @Test
    public void getRowFctCount() {
        assertSizeEq(2,callLangsBeanGetRowMath(0));
    }
    @Test
    public void getRowFct1() {
        assertEq("lg1",elt(callLangsBeanGetRowMath(0),0));
    }
    @Test
    public void getRowFct2() {
        assertEq("lg2",elt(callLangsBeanGetRowMath(0),1));
    }
}
