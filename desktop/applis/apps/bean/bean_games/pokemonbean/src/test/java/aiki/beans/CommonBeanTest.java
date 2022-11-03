package aiki.beans;

import aiki.facade.FacadeGame;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;
import code.util.core.StringUtil;
import org.junit.Test;

public final class CommonBeanTest extends InitDbWelcome {

    public static final String DEF_DIR = "___";
    public static final String DIRECT = "__";
    public static final String KEY = "_";
    public static final String A = "a";
    public static final String I = "i";
    public static final String M = "m";
    public static final String P = "p";
    public static final String S = "s";

    @Test
    public void redirect1() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(redirectAb(A_ABILITY), KEY, DIRECT,d_.getData(), forms_));
        assertEq(A_ABILITY, value(forms_));
    }

    @Test
    public void redirect2() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(redirectAb(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect3() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(redirectIt(I_ITEM), KEY, DIRECT,d_.getData(), forms_));
        assertEq(I_ITEM, value(forms_));
    }

    @Test
    public void redirect4() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(redirectIt(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect5() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(redirectMv(M_DAM), KEY, DIRECT,d_.getData(), forms_));
        assertEq(M_DAM, value(forms_));
    }

    @Test
    public void redirect6() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(redirectMv(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect7() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(redirectPk(P_POKEMON), KEY, DIRECT,d_.getData(), forms_));
        assertEq(P_POKEMON, value(forms_));
    }

    @Test
    public void redirect8() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(redirectPk(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect9() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(redirectSt(S_STA_SIM), KEY, DIRECT,d_.getData(), forms_));
        assertEq(S_STA_SIM, value(forms_));
    }

    @Test
    public void redirect10() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(redirectSt(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void feed() {
        StringMapObject forms_ = new StringMapObject();
        forms_.safeAbilities(A);
        forms_.safeItems(I);
        forms_.safeMoves(M);
        forms_.safePokedex(P);
        forms_.safeStatus(S);
        forms_.safeAbilities(A);
        forms_.safeItems(I);
        forms_.safeMoves(M);
        forms_.safePokedex(P);
        forms_.safeStatus(S);
        assertTrue(forms_.getValAbilityData(A).isEmpty());
        assertTrue(forms_.getValItemData(I).isEmpty());
        assertTrue(forms_.getValMoveData(M).isEmpty());
        assertTrue(forms_.getValPokemonData(P).isEmpty());
        assertTrue(forms_.getValStatusData(S).isEmpty());
    }
    @Test
    public void rateAbs() {
        assertEq(Rate.one(),callRateAbsNb(new RateStruct(Rate.minusOne())));
    }
    @Test
    public void rateZero1() {
        assertFalse(callRateIsZero(new RateStruct(Rate.one())));
    }
    @Test
    public void rateZero2() {
        assertTrue(callRateIsZero(new RateStruct(Rate.zero())));
    }
    @Test
    public void rateZeroGt1() {
        assertTrue(callRateIsZeroOrGt(new RateStruct(Rate.one())));
    }
    @Test
    public void rateZeroGt2() {
        assertFalse(callRateIsZeroOrGt(new RateStruct(Rate.minusOne())));
    }

    private RedirectAb redirectAb(String _key) {
        return new RedirectAb(_key, DEF_DIR);
    }

    private RedirectIt redirectIt(String _key) {
        return new RedirectIt(_key, DEF_DIR);
    }

    private RedirectMv redirectMv(String _key) {
        return new RedirectMv(_key, DEF_DIR);
    }

    private RedirectPk redirectPk(String _key) {
        return new RedirectPk(_key, DEF_DIR);
    }

    private RedirectSt redirectSt(String _key) {
        return new RedirectSt(_key, DEF_DIR);
    }

    public static Struct callRateAbsNb(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RateAbsNb(),_str,_args);
    }

    public static Struct callRateIsZero(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RateIsZero(),_str,_args);
    }

    public static Struct callRateIsZeroOrGt(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RateIsZeroOrGt(),_str,_args);
    }
    private String value(StringMapObject _forms) {
        return _forms.getValStr(KEY);
    }
}
