package aiki.beans;

import aiki.facade.FacadeGame;
import code.bean.nat.RateStruct;
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
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectAb(A_ABILITY,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq(A_ABILITY, value(forms_));
    }

    @Test
    public void redirect2() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(new RedirectAb(NULL_REF,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect3() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectIt(I_ITEM,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq(I_ITEM, value(forms_));
    }

    @Test
    public void redirect4() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(new RedirectIt(NULL_REF,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect5() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectMv(M_DAM,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq(M_DAM, value(forms_));
    }

    @Test
    public void redirect6() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(new RedirectMv(NULL_REF,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect7() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectPk(P_POKEMON,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq(P_POKEMON, value(forms_));
    }

    @Test
    public void redirect8() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(new RedirectPk(NULL_REF,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect9() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectSt(S_STA_SIM,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
        assertEq(S_STA_SIM, value(forms_));
    }

    @Test
    public void redirect10() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(new RedirectSt(NULL_REF,DEF_DIR), KEY, DIRECT,d_.getData(), forms_));
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
        assertEq(Rate.one(),callLongs(new RateAbsNb(),new RateStruct(Rate.minusOne())));
    }
    @Test
    public void rateZero1() {
        assertFalse(callLongs(new RateIsZero(),new RateStruct(Rate.one())));
    }
    @Test
    public void rateZero2() {
        assertTrue(callLongs(new RateIsZero(),new RateStruct(Rate.zero())));
    }
    @Test
    public void rateZeroGt1() {
        assertTrue(callLongs(new RateIsZeroOrGt(),new RateStruct(Rate.one())));
    }
    @Test
    public void rateZeroGt2() {
        assertFalse(callLongs(new RateIsZeroOrGt(),new RateStruct(Rate.minusOne())));
    }
    private String value(StringMapObject _forms) {
        return _forms.getValStr(KEY);
    }
}
