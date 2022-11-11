package aiki.beans.pokemon;

import aiki.facade.enums.SelectedBoolean;
import code.expressionlanguage.structs.Struct;
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
        Struct bean_ = dispAllPks();
        callPokedexBeanWholeWordSet(bean_,false);
        assertFalse(callPokedexBeanWholeWordGet(bean_));
    }
    @Test
    public void wholeWordSet2() {
        Struct bean_ = dispAllPks();
        callPokedexBeanWholeWordSet(bean_,true);
        assertTrue(callPokedexBeanWholeWordGet(bean_));
    }
    @Test
    public void maxAccSet() {
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedMaxNbPossEvosSet(bean_,"1");
        assertEq("1",callPokedexBeanTypedMaxNbPossEvosGet(bean_));
    }
    @Test
    public void minAccSet() {
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedMinNbPossEvosSet(bean_,"1");
        assertEq("1",callPokedexBeanTypedMinNbPossEvosGet(bean_));
    }
    @Test
    public void typedNameSet() {
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedNameSet(bean_,M_DAM_TR);
        assertEq(M_DAM_TR,callPokedexBeanTypedNameGet(bean_));
    }
    @Test
    public void typedTypeSet() {
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedTypeSet(bean_, T_TYPE1_TR);
        assertEq(T_TYPE1_TR,callPokedexBeanTypedTypeGet(bean_));
    }
    @Test
    public void evoSet1() {
        Struct bean_ = dispAllPks();
        callPokedexBeanIsEvoSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(SelectedBoolean.YES.getBoolName(), callPokedexBeanIsEvoGet(bean_));
    }
    @Test
    public void evoSet2() {
        Struct bean_ = dispAllPks();
        callPokedexBeanIsEvoSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(SelectedBoolean.NO.getBoolName(), callPokedexBeanIsEvoGet(bean_));
    }
    @Test
    public void evoSet3() {
        Struct bean_ = dispAllPks();
        callPokedexBeanHasEvoSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(SelectedBoolean.YES.getBoolName(), callPokedexBeanHasEvoGet(bean_));
    }
    @Test
    public void evoSet4() {
        Struct bean_ = dispAllPks();
        callPokedexBeanHasEvoSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(SelectedBoolean.NO.getBoolName(), callPokedexBeanHasEvoGet(bean_));
    }
    @Test
    public void legSet1() {
        Struct bean_ = dispAllPks();
        callPokedexBeanIsLegSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(SelectedBoolean.YES.getBoolName(), callPokedexBeanIsLegGet(bean_));
    }
    @Test
    public void legSet2() {
        Struct bean_ = dispAllPks();
        callPokedexBeanIsLegSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(SelectedBoolean.NO.getBoolName(), callPokedexBeanIsLegGet(bean_));
    }
    @Test
    public void search1() {
        Struct bean_ = dispAllPks();
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedNameSet(bean_,"*0_TR");
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
        assertTrue(StringUtil.contains(keys_,P_POK_10));
    }
    @Test
    public void search3() {
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedTypeSet(bean_,"*3*");
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search4() {
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedTypeSet(bean_,"*2*");
        callPokedexBeanWholeWordSet(bean_, false);
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_14));
        assertTrue(StringUtil.contains(keys_,P_POK_15));
    }
    @Test
    public void search5() {
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedTypeSet(bean_,"*3*");
        callPokedexBeanWholeWordSet(bean_, true);
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search6() {
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedMinNbPossEvosSet(bean_,"1");
        callPokedexBeanTypedMaxNbPossEvosSet(bean_,"1");
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedMaxNbPossEvosSet(bean_,"1");
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedMinNbPossEvosSet(bean_,"1");
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanHasEvoSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanHasEvoSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanIsEvoSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanIsEvoSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanIsLegSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_14));
        assertTrue(StringUtil.contains(keys_,P_POK_15));
    }
    @Test
    public void search15() {
        Struct bean_ = dispAllPks();
        callPokedexBeanIsLegSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_POKEDEX_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
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
        Struct bean_ = dispAllPks();
        callPokedexBeanTypedNameSet(bean_,P_POK_00_TR);
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML, navigatePkSearch(bean_));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        CustList<String> keys_ = forms(bean_).getValPokemonData(CST_POKEMON_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,P_POK_00));
    }
    @Test
    public void clickLink1(){
        assertEq(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML,callPokedexBeanClickLink(0));
    }
    @Test
    public void clickLink2(){
        assertEq(P_POK_00,callPokedexBeanClickLinkId(0));
    }
    @Test
    public void getMiniImagePk() {
        assertEq("AAACAAAABAAA////////",callPokedexBeanGetMiniImage(dispAllPksSearch(),0));
    }
    @Test
    public void lineName() {
        assertEq(P_POK_00_TR,callPokemonLineDisplayNameGet(elt(callPokedexBeanPokedexGet(dispAllPksSearch()),0)));
    }
    @Test
    public void lineTypes1() {
        assertSizeEq(1,callPokemonLineTypesGet(elt(callPokedexBeanPokedexGet(dispAllPksSearch()),0)));
    }
    @Test
    public void lineTypes2() {
        assertEq(T_TYPE1_TR,elt(callPokemonLineTypesGet(elt(callPokedexBeanPokedexGet(dispAllPksSearch()),0)),0));
    }
    @Test
    public void lineEvos() {
        assertEq(1,callPokemonLineEvolutionsGet(elt(callPokedexBeanPokedexGet(dispAllPksSearch()),0)));
    }
}
