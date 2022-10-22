package aiki.beans;

import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.EquallablePkBeanUtil;
import code.util.Longs;
import code.util.StringMap;

public abstract class BeanPokemonCommonTs extends EquallablePkBeanUtil {

    public static final String ACCESS_TO_DEFAULT_FILES = "resources_pk/rom/";
    public static final String EN = "en";
    protected static final char NAV_SEP='.';

    public static void transit(PokemonStandards _stds, NatCaller _caller, Struct _first, Struct _second, long... _args) {
        callLongs(_caller, _first, _args);
        setFormsBy(_stds,_second,_first);
        beforeDisplaying(_second);
    }

    public static StringMapObject forms(Struct _str) {
        return ((CommonBean)((PokemonBeanStruct)_str).getInstance()).getForms();
    }


    public static String navigate(NatCaller _caller, String _url, StringMap<StringMap<String>> _navigation, String _concat, Struct _str, long... _args) {
        Struct res_ = callLongs(_caller, _str, _args);
        return BeanNatCommonLgNames.getString(res_,_url,_navigation,BeanNatCommonLgNames.methName(_concat));
    }

    public static Struct callUsesOfMoveGetMax(Struct _str, long... _args) {
        return callLongs(new UsesOfMoveGetMax(),_str,_args);
    }


    public static Struct callRate(NatCaller _caller, Struct _str, Rate _args) {
        _caller.re(_str,new Struct[]{new RateStruct(_args)});
        return _str;
    }
    public static Struct callBool(NatCaller _caller, Struct _str, boolean _args) {
        _caller.re(_str,new Struct[]{BooleanStruct.of(_args)});
        return _str;
    }
    public static Struct callInt(NatCaller _caller, Struct _str, int _args) {
        _caller.re(_str,new Struct[]{new IntStruct(_args)});
        return _str;
    }
    public static Struct callString(NatCaller _caller, Struct _str, String _args) {
        _caller.re(_str,new Struct[]{new StringStruct(_args)});
        return _str;
    }

    public static Struct callLongs(NatCaller _caller, Struct _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }

    public Struct displaying(Struct _b) {
        beforeDisplaying(_b);
        return _b;
    }

    public static void beforeDisplaying(Struct _bean) {
        ((BeanStruct)_bean).beforeDisplaying();
    }

    public static void setFormsBy(PokemonStandards _pk, Struct _to, Struct _from) {
        _pk.setForms(forms(_from),_to);
    }

    public static void setBeanFormsBy(PokemonStandards _pk, Struct _to, Struct _from) {
        _pk.setBeanForms(_from,_to);
    }
    public static Struct[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }

    public static void assertSizeLongsEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)((NatArrayStruct)_result).get(_index)).getLength()));
    }
    public static void assertLongsEq(long _exp, Struct _result, int _index, int _second) {
        assertEq(_exp,((NatArrayStruct)(((NatArrayStruct)_result).get(_index))).get(_second));
    }

    public static void assertEq(String _exp, Struct _result) {
        assertEq(_exp,((StringStruct)_result).getInstance());
    }
    public static void assertEq(LgInt _exp, Struct _result) {
        assertEq(_exp,((LgIntStruct)_result).getInstance());
    }
    public static void assertEq(Rate _exp, Struct _result) {
        assertEq(_exp,((RateStruct)_result).getInstance());
    }
    public static void assertEq(LgInt _exp, LgInt _result) {
        assertEq(_exp.toNumberString(),_result.toNumberString());
        assertTrue(_exp.eq(_result));
    }
    public static void assertEq(Rate _exp, Rate _result) {
        assertEq(_exp.toNumberString(),_result.toNumberString());
        assertTrue(_exp.eq(_result));
    }
    public static void assertEq(long _exp, Struct _result) {
        assertEq(_exp,(((NumberStruct)_result).longStruct()));
    }
    public static void assertTrue(Struct _value) {
        assertSame(BooleanStruct.of(true),_value);
    }
    public static void assertFalse(Struct _value) {
        assertSame(BooleanStruct.of(false),_value);
    }
    public static void assertSizeEq(int _exp, Struct _result) {
        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
    }
    public static void assertEq(String _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(Rate _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(long _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static Struct elt(Struct _arr, int _index) {
        return ((NatArrayStruct)_arr).get(_index);
    }
    public static Struct first(Struct _arr) {
        return ((PairStruct)_arr).getFirst();
    }
    public static Struct second(Struct _arr) {
        return ((PairStruct)_arr).getSecond();
    }
}
