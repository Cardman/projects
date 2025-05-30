package aiki.beans.pokemon;

import aiki.beans.CommonBean;
import aiki.beans.WithFilterBean;
import aiki.facade.enums.SelectedBoolean;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class PokedexBeanTest extends InitDbPk {
    @Test
    public void pkBegin() {
        assertSizeEq(0,callPokedexBeanPokedexGet(dispAllPks()));
    }
    @Test
    public void maxAcc() {
        assertEq("",callPokedexBeanTypedMaxNbPossEvosGet(dispAllPks()));
    }
    @Test
    public void minAcc() {
        assertEq("",callPokedexBeanTypedMinNbPossEvosGet(dispAllPks()));
    }
    @Test
    public void typedName() {
        assertEq("",callPokedexBeanTypedNameGet(dispAllPks()));
    }
    @Test
    public void typedType() {
        assertEq("",callPokedexBeanTypedTypeGet(dispAllPks()));
    }
    @Test
    public void hasEvo() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(), callPokedexBeanHasEvoGet(dispAllPks()));
    }
    @Test
    public void isEvo() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(), callPokedexBeanIsEvoGet(dispAllPks()));
    }
    @Test
    public void isLeg() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(), callPokedexBeanIsLegGet(dispAllPks()));
    }
    @Test
    public void bools() {
        assertSizeEq(3, callPokedexBeanBooleansGet());
    }
    @Test
    public void wholeWord() {
        assertFalse(callPokedexBeanWholeWordGet(dispAllPks()));
    }
    @Test
    public void wholeWordSet1() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanWholeWordSet(bean_,false);
        assertFalse(callPokedexBeanWholeWordGet(bean_));
    }
    @Test
    public void wholeWordSet2() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanWholeWordSet(bean_,true);
        assertTrue(callPokedexBeanWholeWordGet(bean_));
    }
    @Test
    public void maxAccSet() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedMaxNbPossEvosSet(bean_,"1");
        assertEq("1",callPokedexBeanTypedMaxNbPossEvosGet(bean_));
    }
    @Test
    public void minAccSet() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedMinNbPossEvosSet(bean_,"1");
        assertEq("1",callPokedexBeanTypedMinNbPossEvosGet(bean_));
    }
    @Test
    public void typedNameSet() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedNameSet(bean_,M_DAM_TR);
        assertEq(M_DAM_TR,callPokedexBeanTypedNameGet(bean_));
    }
    @Test
    public void typedTypeSet() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedTypeSet(bean_, T_TYPE1_TR);
        assertEq(T_TYPE1_TR,callPokedexBeanTypedTypeGet(bean_));
    }
    @Test
    public void evoSet1() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanIsEvoSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(SelectedBoolean.YES.getBoolName(), callPokedexBeanIsEvoGet(bean_));
    }
    @Test
    public void evoSet2() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanIsEvoSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(SelectedBoolean.NO.getBoolName(), callPokedexBeanIsEvoGet(bean_));
    }
    @Test
    public void evoSet3() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanHasEvoSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(SelectedBoolean.YES.getBoolName(), callPokedexBeanHasEvoGet(bean_));
    }
    @Test
    public void evoSet4() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanHasEvoSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(SelectedBoolean.NO.getBoolName(), callPokedexBeanHasEvoGet(bean_));
    }
    @Test
    public void legSet1() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanIsLegSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(SelectedBoolean.YES.getBoolName(), callPokedexBeanIsLegGet(bean_));
    }
    @Test
    public void legSet2() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanIsLegSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(SelectedBoolean.NO.getBoolName(), callPokedexBeanIsLegGet(bean_));
    }
    @Test
    public void search1() {
        PokedexBean bean_ = dispAllPks();
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(16,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_01));
        assertTrue(StringUtil.contains(keys_,P_POK_02));
        assertTrue(StringUtil.contains(keys_,P_POK_03));
        assertTrue(StringUtil.contains(keys_,P_POK_04));
        assertTrue(StringUtil.contains(keys_,P_POK_05));
        assertTrue(StringUtil.contains(keys_,P_POK_06));
        assertTrue(StringUtil.contains(keys_,P_POK_07));
        assertTrue(StringUtil.contains(keys_,P_POK_08));
        assertTrue(StringUtil.contains(keys_,P_POK_09));
        assertTrue(StringUtil.contains(keys_,P_POK_10));
        assertTrue(StringUtil.contains(keys_,P_POK_11));
        assertTrue(StringUtil.contains(keys_,P_POK_12));
        assertTrue(StringUtil.contains(keys_,P_POK_13));
        assertTrue(StringUtil.contains(keys_,P_POK_14));
        assertTrue(StringUtil.contains(keys_,P_POK_15));
    }
    @Test
    public void search2() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedNameSet(bean_,"*0_TR");
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_10));
    }
    @Test
    public void search3() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedTypeSet(bean_,"*3*");
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(0,keys_.size());
    }
    @Test
    public void search4() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedTypeSet(bean_,"*2*");
        callPokedexBeanWholeWordSet(bean_, false);
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_14));
        assertTrue(StringUtil.contains(keys_,P_POK_15));
    }
    @Test
    public void search5() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedTypeSet(bean_,"*3*");
        callPokedexBeanWholeWordSet(bean_, true);
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(0,keys_.size());
    }
    @Test
    public void search6() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedMinNbPossEvosSet(bean_,"1");
        callPokedexBeanTypedMaxNbPossEvosSet(bean_,"1");
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(7,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_01));
        assertTrue(StringUtil.contains(keys_,P_POK_03));
        assertTrue(StringUtil.contains(keys_,P_POK_04));
        assertTrue(StringUtil.contains(keys_,P_POK_08));
        assertTrue(StringUtil.contains(keys_,P_POK_09));
        assertTrue(StringUtil.contains(keys_,P_POK_12));
    }
    @Test
    public void search7() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedMaxNbPossEvosSet(bean_,"1");
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(15,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_01));
        assertTrue(StringUtil.contains(keys_,P_POK_02));
        assertTrue(StringUtil.contains(keys_,P_POK_03));
        assertTrue(StringUtil.contains(keys_,P_POK_04));
        assertTrue(StringUtil.contains(keys_,P_POK_06));
        assertTrue(StringUtil.contains(keys_,P_POK_07));
        assertTrue(StringUtil.contains(keys_,P_POK_08));
        assertTrue(StringUtil.contains(keys_,P_POK_09));
        assertTrue(StringUtil.contains(keys_,P_POK_10));
        assertTrue(StringUtil.contains(keys_,P_POK_11));
        assertTrue(StringUtil.contains(keys_,P_POK_12));
        assertTrue(StringUtil.contains(keys_,P_POK_13));
        assertTrue(StringUtil.contains(keys_,P_POK_14));
        assertTrue(StringUtil.contains(keys_,P_POK_15));
    }
    @Test
    public void search8() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedMinNbPossEvosSet(bean_,"1");
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(8,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_01));
        assertTrue(StringUtil.contains(keys_,P_POK_03));
        assertTrue(StringUtil.contains(keys_,P_POK_04));
        assertTrue(StringUtil.contains(keys_,P_POK_05));
        assertTrue(StringUtil.contains(keys_,P_POK_08));
        assertTrue(StringUtil.contains(keys_,P_POK_09));
        assertTrue(StringUtil.contains(keys_,P_POK_12));
    }
    @Test
    public void search9() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanHasEvoSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(8,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_01));
        assertTrue(StringUtil.contains(keys_,P_POK_03));
        assertTrue(StringUtil.contains(keys_,P_POK_04));
        assertTrue(StringUtil.contains(keys_,P_POK_05));
        assertTrue(StringUtil.contains(keys_,P_POK_08));
        assertTrue(StringUtil.contains(keys_,P_POK_09));
        assertTrue(StringUtil.contains(keys_,P_POK_12));
    }
    @Test
    public void search10() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanHasEvoSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(8,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_02));
        assertTrue(StringUtil.contains(keys_,P_POK_06));
        assertTrue(StringUtil.contains(keys_,P_POK_07));
        assertTrue(StringUtil.contains(keys_,P_POK_10));
        assertTrue(StringUtil.contains(keys_,P_POK_11));
        assertTrue(StringUtil.contains(keys_,P_POK_13));
        assertTrue(StringUtil.contains(keys_,P_POK_14));
        assertTrue(StringUtil.contains(keys_,P_POK_15));
    }
    @Test
    public void search11() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanIsEvoSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(9,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_01));
        assertTrue(StringUtil.contains(keys_,P_POK_02));
        assertTrue(StringUtil.contains(keys_,P_POK_04));
        assertTrue(StringUtil.contains(keys_,P_POK_05));
        assertTrue(StringUtil.contains(keys_,P_POK_06));
        assertTrue(StringUtil.contains(keys_,P_POK_07));
        assertTrue(StringUtil.contains(keys_,P_POK_09));
        assertTrue(StringUtil.contains(keys_,P_POK_10));
        assertTrue(StringUtil.contains(keys_,P_POK_13));
    }
    @Test
    public void search12() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanIsEvoSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(7,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_03));
        assertTrue(StringUtil.contains(keys_,P_POK_08));
        assertTrue(StringUtil.contains(keys_,P_POK_11));
        assertTrue(StringUtil.contains(keys_,P_POK_12));
        assertTrue(StringUtil.contains(keys_,P_POK_14));
        assertTrue(StringUtil.contains(keys_,P_POK_15));
    }
    @Test
    public void search14() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanIsLegSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_14));
        assertTrue(StringUtil.contains(keys_,P_POK_15));
    }
    @Test
    public void search15() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanIsLegSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(14,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_01));
        assertTrue(StringUtil.contains(keys_,P_POK_02));
        assertTrue(StringUtil.contains(keys_,P_POK_03));
        assertTrue(StringUtil.contains(keys_,P_POK_04));
        assertTrue(StringUtil.contains(keys_,P_POK_05));
        assertTrue(StringUtil.contains(keys_,P_POK_06));
        assertTrue(StringUtil.contains(keys_,P_POK_07));
        assertTrue(StringUtil.contains(keys_,P_POK_08));
        assertTrue(StringUtil.contains(keys_,P_POK_09));
        assertTrue(StringUtil.contains(keys_,P_POK_10));
        assertTrue(StringUtil.contains(keys_,P_POK_11));
        assertTrue(StringUtil.contains(keys_,P_POK_12));
        assertTrue(StringUtil.contains(keys_,P_POK_13));
    }
    @Test
    public void search16() {
        PokedexBean bean_ = dispAllPks();
        callPokedexBeanTypedNameSet(bean_,P_POK_00_TR);
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_DATA_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys());
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
    }
    @Test
    public void clickLink1(){
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callPokedexBeanClickLink(0));
    }
    @Test
    public void clickLink2(){
        assertEq(P_POK_00,callPokedexBeanClickLinkId(0));
    }
    @Test
    public void getMiniImagePk() {
        assertEq(mini(IMG_M00),callPokedexBeanGetMiniImage(dispAllPksSearch(),0));
    }
    @Test
    public void lineName() {
        assertEq(P_POK_00_TR,callPokemonLineDisplayNameGet(eltPkLine(callPokedexBeanPokedexGet(dispAllPksSearch()),0)));
    }
    @Test
    public void lineTypes1() {
        assertSizeEq(1,callPokemonLineTypesGet(eltPkLine(callPokedexBeanPokedexGet(dispAllPksSearch()),0)));
    }
    @Test
    public void lineTypes2() {
        assertEq(T_TYPE1_TR,elt(callPokemonLineTypesGet(eltPkLine(callPokedexBeanPokedexGet(dispAllPksSearch()),0)),0));
    }
    @Test
    public void lineEvos() {
        assertEq(1,callPokemonLineEvolutionsGet(eltPkLine(callPokedexBeanPokedexGet(dispAllPksSearch()),0)));
    }
}
