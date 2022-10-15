package aiki.beans;

import aiki.beans.game.*;
import aiki.facade.FacadeGame;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.maths.Rate;
import code.scripts.confs.EquallablePkBeanUtil;
import code.util.Longs;

public abstract class BeanPokemonCommonTs extends EquallablePkBeanUtil {
    public static final String EN = "en";

    public static Struct callPokemonPlayerBeanNameGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNameGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanUsedBallCatchingGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanUsedBallCatchingGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanLevelGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanLevelGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanGenderGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGenderGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanAbilityGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanAbilityGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanItemGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanItemGet(),_str,_args);
    }
    public static Struct beanPk(String _language, FacadeGame _dataBase) {
        PkInd stds_ = new PkInd();
        stds_.setDataBase(_dataBase);
        return stds_.beanPk(_language);
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
    public static void assertEq(Rate _exp, Struct _result) {
        assertTrue(_exp.eq(((RateStruct)_result).getInstance()));
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
}
