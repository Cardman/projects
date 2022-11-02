package aiki.beans.facade.comparators;

import aiki.beans.db.InitDbConstr;
import aiki.beans.help.LanguageElementStringKey;
import aiki.facade.FacadeGame;
import aiki.fight.util.TypesDuo;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class CmpsPkBeanTest extends InitDbConstr {
    @Test
    public void cmp1() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorStringList csl_ = new ComparatorStringList(f_.getData(),EN,true);
        CustList<StringList> lists_ = new CustList<StringList>();
        lists_.add(new StringList(M_DAM_VAR,M_DAM));
        lists_.add(new StringList(M_DAM_VAR,M_DAM_BAD));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(2, lists_.get(0).size());
        assertEq(M_DAM_VAR, lists_.get(0).get(0));
        assertEq(M_DAM, lists_.get(0).get(1));
        assertEq(2, lists_.get(1).size());
        assertEq(M_DAM_VAR, lists_.get(1).get(0));
        assertEq(M_DAM_BAD, lists_.get(1).get(1));
    }
    @Test
    public void cmp2() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorStringList csl_ = new ComparatorStringList(f_.getData(),EN,false);
        CustList<StringList> lists_ = new CustList<StringList>();
        lists_.add(new StringList(M_DAM_VAR,M_DAM));
        lists_.add(new StringList(M_DAM_VAR,M_DAM_BAD));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(2, lists_.get(0).size());
        assertEq(M_DAM_VAR, lists_.get(0).get(0));
        assertEq(M_DAM_BAD, lists_.get(0).get(1));
        assertEq(2, lists_.get(1).size());
        assertEq(M_DAM_VAR, lists_.get(1).get(0));
        assertEq(M_DAM, lists_.get(1).get(1));
    }
    @Test
    public void cmp3() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorStringList csl_ = new ComparatorStringList(f_.getData(),EN,true);
        CustList<StringList> lists_ = new CustList<StringList>();
        lists_.add(new StringList(M_DAM_VAR,M_DAM));
        lists_.add(new StringList(M_DAM_VAR,M_DAM,M_DAM_BAD));
        lists_.add(new StringList(M_DAM_VAR,M_DAM_BAD));
        lists_.sortElts(csl_);
        assertEq(3,lists_.size());
        assertEq(2, lists_.get(0).size());
        assertEq(M_DAM_VAR, lists_.get(0).get(0));
        assertEq(M_DAM, lists_.get(0).get(1));
        assertEq(3, lists_.get(1).size());
        assertEq(M_DAM_VAR, lists_.get(1).get(0));
        assertEq(M_DAM, lists_.get(1).get(1));
        assertEq(M_DAM_BAD, lists_.get(1).get(2));
        assertEq(2, lists_.get(2).size());
        assertEq(M_DAM_VAR, lists_.get(2).get(0));
        assertEq(M_DAM_BAD, lists_.get(2).get(1));
    }
    @Test
    public void cmp4() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorStringList csl_ = new ComparatorStringList(f_.getData(),EN,false);
        CustList<StringList> lists_ = new CustList<StringList>();
        lists_.add(new StringList(M_DAM_VAR,M_DAM));
        lists_.add(new StringList(M_DAM_VAR,M_DAM,M_DAM_BAD));
        lists_.add(new StringList(M_DAM_VAR,M_DAM_BAD));
        lists_.sortElts(csl_);
        assertEq(3,lists_.size());
        assertEq(2, lists_.get(0).size());
        assertEq(M_DAM_VAR, lists_.get(0).get(0));
        assertEq(M_DAM_BAD, lists_.get(0).get(1));
        assertEq(2, lists_.get(1).size());
        assertEq(M_DAM_VAR, lists_.get(1).get(0));
        assertEq(M_DAM, lists_.get(1).get(1));
        assertEq(3, lists_.get(2).size());
        assertEq(M_DAM_VAR, lists_.get(2).get(0));
        assertEq(M_DAM, lists_.get(2).get(1));
        assertEq(M_DAM_BAD, lists_.get(2).get(2));
    }
    @Test
    public void cmp5() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorTypesDuo csl_ = new ComparatorTypesDuo(f_.getData(),EN,true,false);
        CustList<TypesDuo> lists_ = new CustList<TypesDuo>();
        lists_.add(new TypesDuo(M_DAM_VAR,M_DAM));
        lists_.add(new TypesDuo(M_DAM_VAR,M_DAM_BAD));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(M_DAM_VAR, lists_.get(0).getDamageType());
        assertEq(M_DAM, lists_.get(0).getPokemonType());
        assertEq(M_DAM_VAR, lists_.get(1).getDamageType());
        assertEq(M_DAM_BAD, lists_.get(1).getPokemonType());
    }
    @Test
    public void cmp6() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorTypesDuo csl_ = new ComparatorTypesDuo(f_.getData(),EN,false,false);
        CustList<TypesDuo> lists_ = new CustList<TypesDuo>();
        lists_.add(new TypesDuo(M_DAM_VAR,M_DAM));
        lists_.add(new TypesDuo(M_DAM_VAR,M_DAM_BAD));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(M_DAM_VAR, lists_.get(0).getDamageType());
        assertEq(M_DAM_BAD, lists_.get(0).getPokemonType());
        assertEq(M_DAM_VAR, lists_.get(1).getDamageType());
        assertEq(M_DAM, lists_.get(1).getPokemonType());
    }
    @Test
    public void cmp7() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorTypesDuo csl_ = new ComparatorTypesDuo(f_.getData(),EN,true,false);
        CustList<TypesDuo> lists_ = new CustList<TypesDuo>();
        lists_.add(new TypesDuo(M_DAM,M_DAM_VAR));
        lists_.add(new TypesDuo(M_DAM_BAD,M_DAM_VAR));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(M_DAM, lists_.get(0).getDamageType());
        assertEq(M_DAM_VAR, lists_.get(0).getPokemonType());
        assertEq(M_DAM_BAD, lists_.get(1).getDamageType());
        assertEq(M_DAM_VAR, lists_.get(1).getPokemonType());
    }
    @Test
    public void cmp8() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorTypesDuo csl_ = new ComparatorTypesDuo(f_.getData(),EN,false,false);
        CustList<TypesDuo> lists_ = new CustList<TypesDuo>();
        lists_.add(new TypesDuo(M_DAM,M_DAM_VAR));
        lists_.add(new TypesDuo(M_DAM_BAD,M_DAM_VAR));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(M_DAM_BAD, lists_.get(0).getDamageType());
        assertEq(M_DAM_VAR, lists_.get(0).getPokemonType());
        assertEq(M_DAM, lists_.get(1).getDamageType());
        assertEq(M_DAM_VAR, lists_.get(1).getPokemonType());
    }
    @Test
    public void cmp9() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorTypesDuo csl_ = new ComparatorTypesDuo(f_.getData(),EN,true,true);
        CustList<TypesDuo> lists_ = new CustList<TypesDuo>();
        lists_.add(new TypesDuo(M_DAM_VAR,M_DAM));
        lists_.add(new TypesDuo(M_DAM_VAR,M_DAM_BAD));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(M_DAM_VAR, lists_.get(0).getDamageType());
        assertEq(M_DAM, lists_.get(0).getPokemonType());
        assertEq(M_DAM_VAR, lists_.get(1).getDamageType());
        assertEq(M_DAM_BAD, lists_.get(1).getPokemonType());
    }
    @Test
    public void cmp10() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorTypesDuo csl_ = new ComparatorTypesDuo(f_.getData(),EN,false,true);
        CustList<TypesDuo> lists_ = new CustList<TypesDuo>();
        lists_.add(new TypesDuo(M_DAM_VAR,M_DAM));
        lists_.add(new TypesDuo(M_DAM_VAR,M_DAM_BAD));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(M_DAM_VAR, lists_.get(0).getDamageType());
        assertEq(M_DAM_BAD, lists_.get(0).getPokemonType());
        assertEq(M_DAM_VAR, lists_.get(1).getDamageType());
        assertEq(M_DAM, lists_.get(1).getPokemonType());
    }
    @Test
    public void cmp11() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorTypesDuo csl_ = new ComparatorTypesDuo(f_.getData(),EN,true,true);
        CustList<TypesDuo> lists_ = new CustList<TypesDuo>();
        lists_.add(new TypesDuo(M_DAM,M_DAM_VAR));
        lists_.add(new TypesDuo(M_DAM_BAD,M_DAM_VAR));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(M_DAM, lists_.get(0).getDamageType());
        assertEq(M_DAM_VAR, lists_.get(0).getPokemonType());
        assertEq(M_DAM_BAD, lists_.get(1).getDamageType());
        assertEq(M_DAM_VAR, lists_.get(1).getPokemonType());
    }
    @Test
    public void cmp12() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorTypesDuo csl_ = new ComparatorTypesDuo(f_.getData(),EN,false,true);
        CustList<TypesDuo> lists_ = new CustList<TypesDuo>();
        lists_.add(new TypesDuo(M_DAM,M_DAM_VAR));
        lists_.add(new TypesDuo(M_DAM_BAD,M_DAM_VAR));
        lists_.sortElts(csl_);
        assertEq(2,lists_.size());
        assertEq(M_DAM_BAD, lists_.get(0).getDamageType());
        assertEq(M_DAM_VAR, lists_.get(0).getPokemonType());
        assertEq(M_DAM, lists_.get(1).getDamageType());
        assertEq(M_DAM_VAR, lists_.get(1).getPokemonType());
    }
    @Test
    public void cmp13() {
        StringMap<StringMap<String>> trs_ = new StringMap<StringMap<String>>();
        trs_.addEntry("EN",withTr(withTr(new StringMap<String>(),"0","ZERO"),"1","ONE"));
        trs_.addEntry("FR",withTr(withTr(new StringMap<String>(),"0","ZERO"),"1","UN"));
        trs_.addEntry("ES",withTr(withTr(new StringMap<String>(),"0","CERO"),"1","UNO"));
        trs_.addEntry("DE",withTr(withTr(new StringMap<String>(),"0","NULL"),"1","EINS"));
        StringList ls_ = new StringList();
        ls_.add("EN");
        ls_.add("FR");
        ls_.add("DE");
        ls_.add("ES");
        ComparatorLanguageString cls_ = new ComparatorLanguageString(trs_,"FR",ls_);
        CustList<LanguageElementStringKey> keys_ = new CustList<LanguageElementStringKey>();
        keys_.add(new LanguageElementStringKey("EN","1"));
        keys_.add(new LanguageElementStringKey("FR","0"));
        keys_.add(new LanguageElementStringKey("ES","0"));
        keys_.add(new LanguageElementStringKey("FR","1"));
        keys_.add(new LanguageElementStringKey("DE","1"));
        keys_.add(new LanguageElementStringKey("EN","0"));
        keys_.add(new LanguageElementStringKey("ES","1"));
        keys_.add(new LanguageElementStringKey("DE","0"));
        keys_.sortElts(cls_);
        assertEq(8,keys_.size());
        assertEq("EN",keys_.get(0).getLanguage());
        assertEq("1",keys_.get(0).getKey());
        assertEq("FR",keys_.get(1).getLanguage());
        assertEq("1",keys_.get(1).getKey());
        assertEq("DE",keys_.get(2).getLanguage());
        assertEq("1",keys_.get(2).getKey());
        assertEq("ES",keys_.get(3).getLanguage());
        assertEq("1",keys_.get(3).getKey());
        assertEq("EN",keys_.get(4).getLanguage());
        assertEq("0",keys_.get(4).getKey());
        assertEq("FR",keys_.get(5).getLanguage());
        assertEq("0",keys_.get(5).getKey());
        assertEq("DE",keys_.get(6).getLanguage());
        assertEq("0",keys_.get(6).getKey());
        assertEq("ES",keys_.get(7).getLanguage());
        assertEq("0",keys_.get(7).getKey());
    }
    private static StringMap<String> withTr(StringMap<String> _tr, String _k, String _v) {
        _tr.addEntry(_k, _v);
        return _tr;
    }
}
