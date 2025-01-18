package aiki.beans.facade.comparators;

import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.solution.dto.WildPokemonDto;
import aiki.beans.help.LanguageElementStringKey;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.util.*;
import aiki.instances.Instances;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.util.Point;
import code.util.*;
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
    @Test
    public void cmp14() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedStatus().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic,String>());
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ACCURACY,M_DAM_TR);
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,M_DAM_BAD_TR);
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,M_DAM_VAR_TR);
        ComparatorStatusStatistic c_ = new ComparatorStatusStatistic(f_.getData(),EN);
        CustList<StatisticStatus> lists_ = new CustList<StatisticStatus>();
        lists_.add(new StatisticStatus(Statistic.SPEED,M_DAM));
        lists_.add(new StatisticStatus(Statistic.ACCURACY,M_DAM));
        lists_.add(new StatisticStatus(Statistic.SPEED,M_DAM_BAD));
        lists_.add(new StatisticStatus(Statistic.ACCURACY,M_DAM_BAD));
        lists_.sortElts(c_);
        assertEq(4,lists_.size());
        assertEq(M_DAM_BAD,lists_.get(0).getStatus());
        assertSame(Statistic.SPEED,lists_.get(0).getStatistic());
        assertEq(M_DAM_BAD,lists_.get(1).getStatus());
        assertSame(Statistic.ACCURACY,lists_.get(1).getStatistic());
        assertEq(M_DAM,lists_.get(2).getStatus());
        assertSame(Statistic.SPEED,lists_.get(2).getStatistic());
        assertEq(M_DAM,lists_.get(3).getStatus());
        assertSame(Statistic.ACCURACY,lists_.get(3).getStatistic());
    }
    @Test
    public void cmp15() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic,String>());
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ACCURACY,M_DAM_TR);
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,M_DAM_BAD_TR);
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,M_DAM_VAR_TR);
        ComparatorStatisticType c_ = new ComparatorStatisticType(f_.getData(),EN);
        CustList<StatisticType> lists_ = new CustList<StatisticType>();
        lists_.add(new StatisticType(Statistic.SPEED,M_DAM));
        lists_.add(new StatisticType(Statistic.ACCURACY,M_DAM));
        lists_.add(new StatisticType(Statistic.SPEED,M_DAM_BAD));
        lists_.add(new StatisticType(Statistic.ACCURACY,M_DAM_BAD));
        lists_.sortElts(c_);
        assertEq(4,lists_.size());
        assertEq(M_DAM_BAD,lists_.get(0).getType());
        assertSame(Statistic.SPEED,lists_.get(0).getStatistic());
        assertEq(M_DAM,lists_.get(1).getType());
        assertSame(Statistic.SPEED,lists_.get(1).getStatistic());
        assertEq(M_DAM_BAD,lists_.get(2).getType());
        assertSame(Statistic.ACCURACY,lists_.get(2).getStatistic());
        assertEq(M_DAM,lists_.get(3).getType());
        assertSame(Statistic.ACCURACY,lists_.get(3).getStatistic());
    }
    @Test
    public void cmp16() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedPokemon().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedPokemon().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedPokemon().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic,String>());
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ACCURACY,M_DAM_TR);
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,M_DAM_BAD_TR);
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,M_DAM_VAR_TR);
        ComparatorStatisticPokemon c_ = new ComparatorStatisticPokemon(f_.getData(),EN);
        CustList<StatisticPokemon> lists_ = new CustList<StatisticPokemon>();
        lists_.add(new StatisticPokemon(Statistic.SPEED,M_DAM));
        lists_.add(new StatisticPokemon(Statistic.ACCURACY,M_DAM));
        lists_.add(new StatisticPokemon(Statistic.SPEED,M_DAM_BAD));
        lists_.add(new StatisticPokemon(Statistic.ACCURACY,M_DAM_BAD));
        lists_.sortElts(c_);
        assertEq(4,lists_.size());
        assertEq(M_DAM_BAD,lists_.get(0).getPokemon());
        assertSame(Statistic.SPEED,lists_.get(0).getStatistic());
        assertEq(M_DAM,lists_.get(1).getPokemon());
        assertSame(Statistic.SPEED,lists_.get(1).getStatistic());
        assertEq(M_DAM_BAD,lists_.get(2).getPokemon());
        assertSame(Statistic.ACCURACY,lists_.get(2).getStatistic());
        assertEq(M_DAM,lists_.get(3).getPokemon());
        assertSame(Statistic.ACCURACY,lists_.get(3).getStatistic());
    }
    @Test
    public void cmp17() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        ComparatorWeatherType c_ = new ComparatorWeatherType(f_.getData(),EN);
        CustList<WeatherType> lists_ = new CustList<WeatherType>();
        lists_.add(new WeatherType(M_DAM_BAD,M_DAM));
        lists_.add(new WeatherType(M_DAM,M_DAM));
        lists_.add(new WeatherType(M_DAM_BAD,M_DAM_BAD));
        lists_.add(new WeatherType(M_DAM,M_DAM_BAD));
        lists_.sortElts(c_);
        assertEq(4,lists_.size());
        assertEq(M_DAM_BAD,lists_.get(0).getWeather());
        assertEq(M_DAM_BAD,lists_.get(0).getType());
        assertEq(M_DAM_BAD,lists_.get(1).getWeather());
        assertEq(M_DAM,lists_.get(1).getType());
        assertEq(M_DAM,lists_.get(2).getWeather());
        assertEq(M_DAM_BAD,lists_.get(2).getType());
        assertEq(M_DAM,lists_.get(3).getWeather());
        assertEq(M_DAM,lists_.get(3).getType());
    }
    @Test
    public void cmp18() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedCategories().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedCategories().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedCategories().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic,String>());
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ACCURACY,M_DAM_TR);
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,M_DAM_BAD_TR);
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,M_DAM_VAR_TR);
        ComparatorStatisticCategory c_ = new ComparatorStatisticCategory(f_.getData(),EN);
        CustList<StatisticCategory> lists_ = new CustList<StatisticCategory>();
        lists_.add(new StatisticCategory(Statistic.SPEED,M_DAM));
        lists_.add(new StatisticCategory(Statistic.ACCURACY,M_DAM));
        lists_.add(new StatisticCategory(Statistic.SPEED,M_DAM_BAD));
        lists_.add(new StatisticCategory(Statistic.ACCURACY,M_DAM_BAD));
        lists_.sortElts(c_);
        assertEq(4,lists_.size());
        assertEq(M_DAM_BAD,lists_.get(0).getCategory());
        assertSame(Statistic.SPEED,lists_.get(0).getStatistic());
        assertEq(M_DAM,lists_.get(1).getCategory());
        assertSame(Statistic.SPEED,lists_.get(1).getStatistic());
        assertEq(M_DAM_BAD,lists_.get(2).getCategory());
        assertSame(Statistic.ACCURACY,lists_.get(2).getStatistic());
        assertEq(M_DAM,lists_.get(3).getCategory());
        assertSame(Statistic.ACCURACY,lists_.get(3).getStatistic());
    }
    @Test
    public void cmp19() {
        ComparatorWildPokemonDto c_ = new ComparatorWildPokemonDto();
        CustList<WildPokemonDto> lists_ = new CustList<WildPokemonDto>();
        lists_.add(new WildPokemonDto(new int[0][0],M_DAM, M_DAM_VAR));
        lists_.add(new WildPokemonDto(new int[0][0],M_DAM, M_DAM_BAD));
        lists_.add(new WildPokemonDto(new int[0][0],M_DAM_POW, M_DAM_VAR));
        lists_.add(new WildPokemonDto(new int[0][0],M_DAM_POW, M_DAM_BAD));
        lists_.sortElts(c_);
        assertEq(4,lists_.size());
        assertEq(M_DAM,lists_.get(0).getName());
        assertEq(M_DAM_BAD,lists_.get(0).getGender());
        assertEq(M_DAM,lists_.get(1).getName());
        assertEq(M_DAM_VAR,lists_.get(1).getGender());
        assertEq(M_DAM_POW,lists_.get(2).getName());
        assertEq(M_DAM_BAD,lists_.get(2).getGender());
        assertEq(M_DAM_POW,lists_.get(3).getName());
        assertEq(M_DAM_VAR,lists_.get(3).getGender());
    }
    @Test
    public void cmp20() {
        ComparatorCategoryMult c_ = new ComparatorCategoryMult();
        CustList<CategoryMult> lists_ = new CustList<CategoryMult>();
        lists_.add(new CategoryMult(M_DAM_BAD,0));
        lists_.add(new CategoryMult(M_DAM,0));
        lists_.add(new CategoryMult(M_DAM_BAD,1));
        lists_.add(new CategoryMult(M_DAM,1));
        lists_.sortElts(c_);
        assertEq(4,lists_.size());
        assertEq(M_DAM,lists_.get(0).getCategory());
        assertEq(0,lists_.get(0).getMult());
        assertEq(M_DAM,lists_.get(1).getCategory());
        assertEq(1,lists_.get(1).getMult());
        assertEq(M_DAM_BAD,lists_.get(2).getCategory());
        assertEq(0,lists_.get(2).getMult());
        assertEq(M_DAM_BAD,lists_.get(3).getCategory());
        assertEq(1,lists_.get(3).getMult());
    }
    @Test
    public void cmp21() {
        ComparatorPoint c_ = new ComparatorPoint();
        CustList<Point> lists_ = new CustList<Point>();
        lists_.add(newPoint(0,0));
        lists_.add(newPoint(1,0));
        lists_.add(newPoint(0,1));
        lists_.add(newPoint(1,1));
        lists_.sortElts(c_);
        assertEq(4,lists_.size());
        assertEq(0,lists_.get(0).getx());
        assertEq(0,lists_.get(0).gety());
        assertEq(1,lists_.get(1).getx());
        assertEq(0,lists_.get(1).gety());
        assertEq(0,lists_.get(2).getx());
        assertEq(1,lists_.get(2).gety());
        assertEq(1,lists_.get(3).getx());
        assertEq(1,lists_.get(3).gety());
    }
    @Test
    public void cmp22() {
        FacadeGame f_ = facade();
        f_.getData().getMap().setPlaces(new CustList<Place>());
        f_.getData().getMap().addPlace(place("ONE"));
        f_.getData().getMap().addPlace(place("TWO"));
        f_.getData().getMap().addPlace(place("ONE"));
        f_.getData().getMap().addPlace(place("TWO"));
        ComparatorPlaceNumber c_ = new ComparatorPlaceNumber(f_.getData().getMap());
        Ints pls_ = Ints.newList(0,1,2,3);
        pls_.sortElts(c_);
        assertEq(4,pls_.size());
        assertEq(0,pls_.get(0));
        assertEq(2,pls_.get(1));
        assertEq(1,pls_.get(2));
        assertEq(3,pls_.get(3));
    }

    private League place(String _name) {
        League one_ = Instances.newLeague();
        one_.setName(_name);
        return one_;
    }

    private static StringMap<String> withTr(StringMap<String> _tr, String _k, String _v) {
        _tr.addEntry(_k, _v);
        return _tr;
    }
}
