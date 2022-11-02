package aiki.beans.facade.comparators;

import aiki.beans.db.InitDbConstr;
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
}
