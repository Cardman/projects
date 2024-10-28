package aiki.beans;

import code.bean.nat.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.EquallablePkBeanUtil;
import code.util.Longs;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class BeanPokemonCommonTs extends EquallablePkBeanUtil {

    public static final String AUTRE = "AUTRE";
//    public static final String ACCESS_TO_DEFAULT_FILES = "";
    public static final String EN = StringUtil.EN;
    public static final String FR = StringUtil.FR;
//    protected static final char NAV_SEP='.';

    public static void transit(PokemonStandards _stds, NatCaller _caller, NaSt _first, NaSt _second, long... _args) {
        callLongs(_caller, _first, _args);
        setFormsBy(_stds,_second,_first);
        beforeDisplaying(_second);
    }

    public static StringMapObject forms(NaSt _str) {
        return ((CommonBean)((PokemonBeanStruct)_str).getInstance()).getForms();
    }


    public static String navigate(NatCaller _caller, String _concat, NaSt _str, long... _args) {
        NaSt res_ = callLongs(_caller, _str, _args);
//        BeanNatCommonLgNames.methName(_concat);
        return BeanNatCommonLgNames.processString(res_);
//        String urlDest_ = _currentUrl;
//        if (_ret != NullStruct.NULL_VALUE) {
//            StringMap<String> cases_ = _navigation.getVal(_concat);
//            String ca_ = BeanNatCommonLgNames.processString(_ret);
//            if (cases_ == null) {
////                if (ca_.isEmpty()) {
////                    return _currentUrl;
////                }
//                return ca_;
//            }
//            urlDest_ = cases_.getVal(ca_);
//            if (urlDest_ == null) {
//                urlDest_ = _currentUrl;
//            }
//        }
//        return urlDest_;
    }


    public static NaSt callRate(NatCaller _caller, NaSt _str, Rate _args) {
        _caller.re(_str,new NaSt[]{new RtSt(_args)});
        return _str;
    }
    public static NaSt callBool(NatCaller _caller, NaSt _str, boolean _args) {
        _caller.re(_str,new NaSt[]{NaBoSt.of(_args)});
        return _str;
    }
    public static NaSt callInt(NatCaller _caller, NaSt _str, int _args) {
        _caller.re(_str,new NaSt[]{new NaNbSt(_args)});
        return _str;
    }
    public static NaSt callString(NatCaller _caller, NaSt _str, String _args) {
        _caller.re(_str,new NaSt[]{new NaStSt(_args)});
        return _str;
    }

    public static NaSt callLongs(NatCaller _caller, NaSt _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }
    public static NaSt callStruct(NatCaller _caller, NaSt _str, NaSt _args) {
        _caller.re(_str,new NaSt[]{_args});
        return _str;
    }

    public static int toInt(NaSt _str) {
        return NaPa.convertToNumber(_str).intStruct();
    }

    public static NaSt byStr(StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _resultAsString) {
        return _all.getVal(_mapping.getVal(toStr(_resultAsString)));
    }

    protected static String toStr(NaSt _resultAsString) {
        return BeanNatCommonLgNames.processString(_resultAsString);
    }

    public NaSt displaying(NaSt _b) {
        beforeDisplaying(_b);
        return _b;
    }

    public static void beforeDisplaying(NaSt _bean) {
        ((BeanStruct)_bean).beforeDisplaying();
    }

    public static void setFormsBy(PokemonStandards _pk, NaSt _to, NaSt _from) {
        _pk.setForms(forms(_from),_to);
    }

//    public static void setBeanFormsBy(PokemonStandards _pk, Struct _to, Struct _from) {
//        _pk.setBeanForms(_from,_to);
//    }
    public static NaSt[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }

//    public static void assertSizeLongsEq(int _exp, Struct _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)((NatArrayStruct)_result).get(_index)).getLength()));
//    }
//    public static void assertLongsEq(long _exp, Struct _result, int _index, int _second) {
//        assertEq(_exp,((NatArrayStruct)(((NatArrayStruct)_result).get(_index))).get(_second));
//    }

    public static void assertEq(int[][] _exp, NaSt _result) {
        assertEq(_exp, NaImgSt.tryGet(_result));
    }
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
    public static void assertTrue(NaSt _value) {
        assertSame(NaBoSt.of(true),_value);
    }
    public static void assertFalse(NaSt _value) {
        assertSame(NaBoSt.of(false),_value);
    }
    public static void assertSizeEq(int _exp, NaSt _result) {
        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
    }
    public static NaSt elt(NaSt _arr, int _index) {
        return ((NatArrayStruct)_arr).get(_index);
    }
    public static NaSt first(NaSt _arr) {
        return ((PairStruct)_arr).getFirst();
    }
    public static NaSt second(NaSt _arr) {
        return ((PairStruct)_arr).getSecond();
    }
    public static StringList indexes(){
        return new StringList(EN);
    }
    public static StringList indexesAll(){
        return new StringList(EN,FR);
    }
}
