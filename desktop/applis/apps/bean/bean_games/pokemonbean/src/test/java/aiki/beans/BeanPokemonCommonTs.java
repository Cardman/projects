package aiki.beans;

import aiki.beans.abilities.*;
import aiki.beans.facade.dto.*;
import aiki.beans.facade.map.dto.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.solution.dto.*;
import aiki.beans.map.elements.*;
import aiki.comparators.*;
import aiki.fight.enums.*;
import aiki.fight.util.*;
import aiki.map.pokemon.*;
import aiki.map.util.*;
import code.bean.nat.*;
import code.maths.*;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;
import code.util.ints.Countable;

public abstract class BeanPokemonCommonTs extends EquallablePkBeanUtil {

    public static final String AUTRE = "AUTRE";
//    public static final String ACCESS_TO_DEFAULT_FILES = "";
    public static final String EN = StringUtil.EN;
    public static final String FR = StringUtil.FR;
    public static final String BEAN_WELCOME = "welcome";
//    protected static final char NAV_SEP='.';
//
//    public static void transit(PokemonStandards _stds, NatCaller _caller, NaSt _first, NaSt _second, long... _args) {
//        setFormsBy(_stds,_second,_first);
//        callLongs(_caller, _first, _args);
//        beforeDisplaying(_second);
//    }

//    public static void transit(PokemonStandards _stds, IntBeanAction _caller, NaSt _first, NaSt _second) {
//        IntBeanBuilderHelper bu_ = ((PokemonBeanStruct) _second).getInstance().getBuilder();
////        setFormsBy(_stds,_second,_first);
//        bu_.build(_caller);
//    }

    public static void transit(PokemonStandards _stds, IntBeanAction _caller, CommonBean _first, CommonBean _second) {
        IntBeanBuilderHelper bu_ = _second.getBuilder();
//        setFormsBy(_stds,_second,_first);
        bu_.build(_caller);
    }

    public static StringMapObject forms(NaSt _str) {
        return ((CommonBean)((PokemonBeanStruct)_str).getInstance()).getForms();
    }

    public static StringMapObject forms(CommonBean _str) {
        return _str.getForms();
    }


//    public static String navigate(NatCaller _caller, String _concat, NaSt _str, long... _args) {
//        NaSt res_ = callLongs(_caller, _str, _args);
////        BeanNatCommonLgNames.methName(_concat);
//        return BeanNatCommonLgNames.processString(res_);
////        String urlDest_ = _currentUrl;
////        if (_ret != NullStruct.NULL_VALUE) {
////            StringMap<String> cases_ = _navigation.getVal(_concat);
////            String ca_ = BeanNatCommonLgNames.processString(_ret);
////            if (cases_ == null) {
//////                if (ca_.isEmpty()) {
//////                    return _currentUrl;
//////                }
////                return ca_;
////            }
////            urlDest_ = cases_.getVal(ca_);
////            if (urlDest_ == null) {
////                urlDest_ = _currentUrl;
////            }
////        }
////        return urlDest_;
//    }
//
//
//    public static NaSt callRate(NatCaller _caller, NaSt _str, Rate _args) {
//        _caller.re(_str,new NaSt[]{new RtSt(_args)});
//        return _str;
//    }
//    public static NaSt callBool(NatCaller _caller, NaSt _str, boolean _args) {
//        _caller.re(_str,new NaSt[]{NaBoSt.of(_args)});
//        return _str;
//    }
//    public static NaSt callInt(NatCaller _caller, NaSt _str, int _args) {
//        _caller.re(_str,new NaSt[]{new NaNbSt(_args)});
//        return _str;
//    }
//    public static NaSt callString(NatCaller _caller, NaSt _str, String _args) {
//        _caller.re(_str,new NaSt[]{new NaStSt(_args)});
//        return _str;
//    }
//
//    public static NaSt callLongs(NatCaller _caller, NaSt _str, long... _args) {
//        return _caller.re(_str,getLongArray(_args));
//    }
//    public static NaSt callStruct(NatCaller _caller, NaSt _str, NaSt _args) {
//        _caller.re(_str,new NaSt[]{_args});
//        return _str;
//    }
//
//    public static int toInt(NaSt _str) {
//        return NaPa.convertToNumber(_str).intStruct();
//    }

//    public static NaSt byStr(StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _resultAsString) {
//        return _all.getVal(_mapping.getVal(toStr(_resultAsString)));
//    }

    public NaSt displaying(NaSt _b) {
        beforeDisplaying(_b);
        return _b;
    }

    public static void beforeDisplaying(NaSt _bean) {
        beforeDisplaying((CommonBean)((BeanStruct) _bean).getBean());
    }

    public static void beforeDisplaying(CommonBean _bean) {
        ((BeanRenderWithAppName)_bean).build(_bean.getFacade());
    }

//    public static void setFormsBy(PokemonStandards _pk, NaSt _to, NaSt _from) {
//        _pk.setForms(forms(_from),_to);
//    }

//    public static void setFormsBy(CommonBean _to, CommonBean _from) {
//        _to.setForms(_from.getForms());
//    }

//    public static void setBeanFormsBy(PokemonStandards _pk, Struct _to, Struct _from) {
//        _pk.setBeanForms(_from,_to);
//    }
//    public static NaSt[] getLongArray(long... _ls){
//        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
//    }

//    public static void assertSizeLongsEq(int _exp, Struct _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)((NatArrayStruct)_result).get(_index)).getLength()));
//    }
//    public static void assertLongsEq(long _exp, Struct _result, int _index, int _second) {
//        assertEq(_exp,((NatArrayStruct)(((NatArrayStruct)_result).get(_index))).get(_second));
//    }

//    public static void assertEq(int[][] _exp, NaSt _result) {
//        assertEq(_exp, NaImgSt.tryGet(_result));
//    }
    public static void assertEq(String _exp, NaSt _result) {
        assertEq(_exp,((NaStSt)_result).getInstance());
    }
    public static void assertEq(LgInt _exp, NaSt _result) {
        assertEq(_exp,((LgSt)_result).getInstance());
    }
    public static void assertEq(Rate _exp, NaSt _result) {
        assertEq(_exp,((RtSt)_result).getInstance());
    }
    public static void assertEq(LgInt _exp, LgInt _result) {
        assertEq(_exp.toNumberString(),_result.toNumberString());
        assertTrue(_exp.eq(_result));
    }
    public static void assertEq(Rate _exp, Rate _result) {
        assertEq(_exp.toNumberString(),_result.toNumberString());
        assertTrue(_exp.eq(_result));
    }
    public static void assertEq(long _exp, NaSt _result) {
        assertEq(_exp,(((NaNbSt)_result).longStruct()));
    }
    public static void assertTrue(int _value) {
        assertEq(CommonBean.TRUE_VALUE,_value);
    }
    public static void assertFalse(int _value) {
        assertEq(CommonBean.FALSE_VALUE,_value);
    }
    public static void assertTrue(NaSt _value) {
        assertSame(NaBoSt.of(true),_value);
    }
    public static void assertFalse(NaSt _value) {
        assertSame(NaBoSt.of(false),_value);
    }
    public static void assertSizeEq(int _exp, NaSt _result) {
        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
    }
    public static void assertSizeEq(int _exp, Countable _result) {
        assertEq(_exp,_result.size());
    }
//    public static NaSt elt(NaSt _arr, int _index) {
//        return ((NatArrayStruct)_arr).get(_index);
//    }
//    public static NaSt eltStrTr(NaSt _arr, int _index) {
//        return ((NatArrayStruct)_arr).get(_index);
//    }
//    public static NaSt eltStrKey(NaSt _arr, int _index) {
//        return ((NatArrayStruct)_arr).get(_index);
//    }
//    public static NaSt eltVar(NaSt _arr, int _index) {
//        return ((NatArrayStruct)_arr).get(_index);
//    }
//    public static NaSt firstVar(NaSt _arr) {
//        return ((PairStruct)_arr).getFirst();
//    }
//    public static NaSt secondVar(NaSt _arr) {
//        return ((PairStruct)_arr).getSecond();
//    }
//    public static NaSt eltIdMove(NaSt _arr, int _index) {
//        return ((NatArrayStruct)_arr).get(_index);
//    }
//    public static NaSt firstIdMove(NaSt _arr) {
//        return ((PairStruct)_arr).getFirst();
//    }
//    public static NaSt secondIdMove(NaSt _arr) {
//        return ((PairStruct)_arr).getSecond();
//    }
//    public static NaSt eltPlInd(NaSt _arr, int _index) {
//        return ((NatArrayStruct)_arr).get(_index);
//    }
//    public static NaSt first(NaSt _arr) {
//        return ((PairStruct)_arr).getFirst();
//    }
//    public static NaSt second(NaSt _arr) {
//        return ((PairStruct)_arr).getSecond();
//    }
    public static String secondEntryTkTk(EntryCust<TranslatedKey, TranslatedKey> _arr) {
        return _arr.getValue().getTranslation();
    }
    public static StringList indexes(){
        return new StringList(EN);
    }
    public static StringList indexesAll(){
        return new StringList(EN,FR);
    }

//    public static String eltStrKey(CustList<TranslatedKey> _arr, int _index) {
//        return _arr.get(_index).getKey();
//    }
    public static ItemLine eltItemLine(CustList<ItemLine> _ls, int _i){
        return _ls.get(_i);
    }
    public static String elt(CustList<String> _arr, int _index) {
        return _arr.get(_index);
    }

    public static EntryCust<TranslatedKey,DictionaryComparator<TranslatedKey,Long>> eltTkMapTkLg(AbsMap<TranslatedKey,DictionaryComparator<TranslatedKey,Long>> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String firstTkMapTkLgKey(EntryCust<TranslatedKey,DictionaryComparator<TranslatedKey,Long>> _e){
        return _e.getKey().getKey();
    }

    public static DictionaryComparator<TranslatedKey, Long> secondTkMapTkLg(EntryCust<TranslatedKey,DictionaryComparator<TranslatedKey,Long>> _e){
        return _e.getValue();
    }

    public static EntryCust<TranslatedKeyPair,Rate> eltTkPairRt(AbsMap<TranslatedKeyPair,Rate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static CategoryMult firstTkPairRt(EntryCust<TranslatedKeyPair,Rate> _e){
        return new CategoryMult(_e.getKey().getFirst().getTranslation(),NumberUtil.parseInt(_e.getKey().getSecond().getKey()));
    }

    public static Rate secondTkPairRt(EntryCust<TranslatedKeyPair,Rate> _e){
        return _e.getValue();
    }

    public static EntryCust<TranslatedKey,EfficiencyRate> eltTkEffRt(AbsMap<TranslatedKey,EfficiencyRate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String firstTkEffRtKey(EntryCust<TranslatedKey,EfficiencyRate> _e){
        return _e.getKey().getKey();
    }

    public static EfficiencyRate secondTkEffRt(EntryCust<TranslatedKey,EfficiencyRate> _e){
        return _e.getValue();
    }

    public static EntryCust<Statistic,EvLine> eltStatisticEv(AbsMap<Statistic,EvLine> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static EvLine secondStatisticEv(EntryCust<Statistic,EvLine> _e){
        return _e.getValue();
    }

    public static int eltInt(CustList<Integer> _ls, int _i){
        return _ls.get(_i);
    }

    public static EntryCust<Integer,String> eltIntStr(AbsMap<Integer,String> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static long firstIntStrKey(EntryCust<Integer,String> _e){
        return _e.getKey();
    }

    public static String secondIntStr(EntryCust<Integer,String> _e){
        return StringUtil.nullToEmpty(_e.getValue());
    }

    public static EntryCust<Integer,TranslatedKey> eltIntTk(AbsMap<Integer,TranslatedKey> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static long firstIntTkKey(EntryCust<Integer,TranslatedKey> _e){
        return _e.getKey();
    }

    public static String secondIntTk(EntryCust<Integer,TranslatedKey> _e){
        return StringUtil.nullToEmpty(_e.getValue().getTranslation());
    }

    public static String eltTkKey(CustList<TranslatedKey> _ls, int _i){
        return _ls.get(_i).getKey();
    }

    public static EntryCust<LgInt,Rate> eltLtRt(AbsMap<LgInt,Rate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static LgInt firstLtRtKey(EntryCust<LgInt,Rate> _e){
        return _e.getKey();
    }

    public static Rate secondLtRt(EntryCust<LgInt,Rate> _e){
        return _e.getValue();
    }

    public static EntryCust<Long,Rate> eltLgRt(AbsMap<Long,Rate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static long firstLgRtKey(EntryCust<Long,Rate> _e){
        return _e.getKey();
    }

    public static Rate secondLgRt(EntryCust<Long,Rate> _e){
        return _e.getValue();
    }

    public static EntryCust<Long,TranslatedKey> eltLgTk(AbsMap<Long,TranslatedKey> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static long firstLgTkKey(EntryCust<Long,TranslatedKey> _e){
        return _e.getKey();
    }

    public static String secondLgTk(EntryCust<Long,TranslatedKey> _e){
        return StringUtil.nullToEmpty(_e.getValue().getKey());
    }

    public static PokemonPlayerDto eltPkPlDto(CustList<PokemonPlayerDto> _ls, int _i){
        return _ls.get(_i);
    }

    public static PokemonPlayer eltPkPl(CustList<PokemonPlayer> _ls, int _i){
        return _ls.get(_i);
    }

    public static PokemonTrainerDto eltPkTraDto(CustList<PokemonTrainerDto> _ls, int _i){
        return _ls.get(_i);
    }

    public static PkTrainer eltTrPkElts(CustList<TranslatedPkElements> _ls, int _i){
        return _ls.get(_i).getTrained();
    }

    public static PlaceIndex eltPlaceIndex(CustList<PlaceIndex> _ls, int _i){
        return _ls.get(_i);
    }

    public static EntryCust<PlaceLevel,CustList<WildPokemonDto>> eltPlaceLevelListWildPokemonDto(AbsMap<PlaceLevel,CustList<WildPokemonDto>> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static CustList<WildPokemonDto> secondPlaceLevelListWildPokemonDto(EntryCust<PlaceLevel,CustList<WildPokemonDto>> _e){
        return _e.getValue();
    }

    public static WildPokemonDto eltWildPk(CustList<WildPokemonDto> _ls, int _i){
        return _ls.get(_i);
    }

    public static PlaceTrainerDto eltPlaceTrainerDto(CustList<PlaceTrainerDto> _ls, int _i){
        return _ls.get(_i);
    }

    public static EntryCust<Rate,Rate> eltRtRt(AbsMap<Rate,Rate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static Rate firstRtRtKey(EntryCust<Rate,Rate> _e){
        return _e.getKey();
    }

    public static Rate secondRtRt(EntryCust<Rate,Rate> _e){
        return _e.getValue();
    }

    public static RadioLineMove eltRadioMove(CustList<RadioLineMove> _ls, int _i){
        return _ls.get(_i);
    }

    public static SelectLineMove eltSelectMove(CustList<SelectLineMove> _ls, int _i){
        return _ls.get(_i);
    }

    public static EntryCust<Long,CustList<TranslatedKey>> eltLgListTk(AbsMap<Long,CustList<TranslatedKey>> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static long firstLgListTkKey(EntryCust<Long,CustList<TranslatedKey>> _e){
        return _e.getKey();
    }

    public static CustList<TranslatedKey> secondLgListTk(EntryCust<Long,CustList<TranslatedKey>> _e){
        return _e.getValue();
    }

    public static EntryCust<TranslatedKey,BoostHpRate> eltTkBoost(AbsMap<TranslatedKey,BoostHpRate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static BoostHpRate secondTkBoost(EntryCust<TranslatedKey,BoostHpRate> _e){
        return _e.getValue();
    }

    public static EntryCust<TranslatedKey,Long> eltTkLgKey(AbsMap<TranslatedKey,Long> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static long secondTkLgKey(EntryCust<TranslatedKey,Long> _e){
        return _e.getValue();
    }

    public static EntryCust<TranslatedKey,Rate> eltTkRtKey(AbsMap<TranslatedKey,Rate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static Rate secondTkRtKey(EntryCust<TranslatedKey,Rate> _e){
        return _e.getValue();
    }

    public static EntryCust<TranslatedKey,String> eltTkStr(AbsMap<TranslatedKey,String> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String secondTkStr(EntryCust<TranslatedKey,String> _e){
        return StringUtil.nullToEmpty(_e.getValue());
    }

    public static StepDto eltStepDto(CustList<StepDto> _ls, int _i){
        return _ls.get(_i);
    }

    public static EntryCust<TranslatedKey,Ints> eltTkInts(AbsMap<TranslatedKey,Ints> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String firstTkIntsKey(EntryCust<TranslatedKey,Ints> _e){
        return _e.getKey().getKey();
    }

    public static Ints secondTkInts(EntryCust<TranslatedKey,Ints> _e){
        return _e.getValue();
    }

    public static CustList<TranslatedKey> eltListTk(CustList<CustList<TranslatedKey>> _ls, int _i){
        return _ls.get(_i);
    }

    public static String firstTkLgKey(EntryCust<TranslatedKey,Long> _e){
        return StringUtil.nullToEmpty(_e.getKey().getKey());
    }

    public static EntryCust<TranslatedKey,StatRankRate> eltTkStRtKey(AbsMap<TranslatedKey,StatRankRate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String firstTkStRtTr(EntryCust<TranslatedKey,StatRankRate> _e){
        return StringUtil.nullToEmpty(_e.getKey().getTranslation());
    }

    public static long secondTkStRk(EntryCust<TranslatedKey,StatRankRate> _e){
        return _e.getValue().getRank();
    }

    public static String firstTkStRtKey(EntryCust<TranslatedKey,StatRankRate> _e){
        return StringUtil.nullToEmpty(_e.getKey().getKey());
    }

    public static Rate secondTkStRt(EntryCust<TranslatedKey,StatRankRate> _e){
        return _e.getValue().getRate();
    }

    public static EntryCust<String,Rate> eltStrRt(AbsMap<String,Rate> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String firstStrRt(EntryCust<String,Rate> _e){
        return StringUtil.nullToEmpty(_e.getKey());
    }

    public static Rate secondStrRt(EntryCust<String,Rate> _e){
        return _e.getValue();
    }

    public static String firstTkRtKey(EntryCust<TranslatedKey,Rate> _e){
        return StringUtil.nullToEmpty(_e.getKey().getKey());
    }

    public static String firstTkRtTr(EntryCust<TranslatedKey,Rate> _e){
        return StringUtil.nullToEmpty(_e.getKey().getTranslation());
    }

    public static EntryCust<String,String> eltStrStr(AbsMap<String,String> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String firstStrStr(EntryCust<String,String> _e){
        return StringUtil.nullToEmpty(_e.getKey());
    }

    public static String secondStrStr(EntryCust<String,String> _e){
        return StringUtil.nullToEmpty(_e.getValue());
    }

    public static EntryCust<TranslatedKey,TranslatedKey> eltTkTk(AbsMap<TranslatedKey,TranslatedKey> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String firstTkTk(EntryCust<TranslatedKey,TranslatedKey> _e){
        return StringUtil.nullToEmpty(_e.getKey().getKey());
    }

    public static String secondTkTk(EntryCust<TranslatedKey,TranslatedKey> _e){
        return StringUtil.nullToEmpty(_e.getValue().getKey());
    }

    public static String firstTkStr(EntryCust<TranslatedKey,String> _e){
        return StringUtil.nullToEmpty(_e.getKey().getKey());
    }

    public static EntryCust<TranslatedKey,TypeDamageBoostKey> eltDam(AbsMap<TranslatedKey,TypeDamageBoostKey> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static String firstDamKey(EntryCust<TranslatedKey,TypeDamageBoostKey> _e){
        return _e.getKey().getKey();
    }

    public static TypeDamageBoost secondDam(EntryCust<TranslatedKey,TypeDamageBoostKey> _e){
        return new TypeDamageBoost(_e.getValue().getType().getKey(),_e.getValue().getBoost());
    }

    public static TypesDuo eltTkPair(CustList<TranslatedKeyPair> _ls, int _i){
        return new TypesDuo(_ls.get(_i).getFirst().getKey(),_ls.get(_i).getSecond().getKey());
    }

    public static String eltTkTr(CustList<TranslatedKey> _ls, int _i){
        return _ls.get(_i).getTranslation();
    }

    public static EntryCust<TranslatedKeyPair,Long> eltTkPairLg(AbsMap<TranslatedKeyPair,Long> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static long secondTkPairLg(EntryCust<TranslatedKeyPair,Long> _e){
        return _e.getValue();
    }

    public static EntryCust<MiniMapCoords,int[][]> eltMini(AbsMap<MiniMapCoords,int[][]> _ls, int _i){
        return _ls.getEntry(_i);
    }

    public static int[][] secondMini(EntryCust<MiniMapCoords,int[][]> _e){
        return _e.getValue();
    }

    public static TypesDuo firstTd(EntryCust<TranslatedKeyPair,Rate> _e){
        return new TypesDuo(_e.getKey().getFirst().getTranslation(),_e.getKey().getSecond().getTranslation());
    }


}
