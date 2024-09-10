package aiki.beans;

import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.instances.Instances;
import aiki.map.pokemon.enums.Gender;
import code.bean.nat.RtSt;
import code.bean.nat.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.scripts.confs.PkScriptPages;
import code.util.core.BoolVal;
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
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,AbsRedirect.tryRedirect(redirectIt(I_ITEM,d_.getData()), KEY, DIRECT,d_.getData(), forms_));
        assertEq(I_ITEM, value(forms_));
    }

    @Test
    public void redirect4() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR,AbsRedirect.tryRedirect(redirectIt(NULL_REF,d_.getData()), KEY, DIRECT,d_.getData(), forms_));
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
    public void rateTrue1() {
        assertEq(Rate.zero(),CommonBean.rateTrue(new MonteCarloBoolean()));
    }
    @Test
    public void rateTrue2() {
        MonteCarloBoolean t_ = new MonteCarloBoolean();
        t_.addQuickEvent(BoolVal.TRUE, LgInt.one());
        assertEq(Rate.one(),CommonBean.rateTrue(t_));
    }
    @Test
    public void rateAbs() {
        assertEq(Rate.one(),callRateAbsNb(new RtSt(Rate.minusOne())));
    }
    @Test
    public void rateZero1() {
        assertFalse(callRateIsZero(new RtSt(Rate.one())));
    }
    @Test
    public void rateZero2() {
        assertTrue(callRateIsZero(new RtSt(Rate.zero())));
    }
    @Test
    public void rateZeroGt1() {
        assertTrue(callRateIsZeroOrGt(new RtSt(Rate.one())));
    }
    @Test
    public void rateZeroGt2() {
        assertFalse(callRateIsZeroOrGt(new RtSt(Rate.minusOne())));
    }

    @Test
    public void forms1() {
        StringMapObject s_ = new StringMapObject();
        assertFalse(s_.contains(""));
    }

    @Test
    public void forms2() {
        StringMapObject s_ = new StringMapObject();
        s_.put("",Gender.NO_GENDER);
        assertTrue(s_.contains(""));
    }

    @Test
    public void forms3() {
        StringMapObject s_ = new StringMapObject();
        s_.put("",TeamCrud.NOTHING);
        assertTrue(s_.contains(""));
    }

    @Test
    public void forms4() {
        StringMapObject s_ = new StringMapObject();
        s_.put("",Instances.newAlly());
        assertTrue(s_.contains(""));
    }

    @Test
    public void forms5() {
        StringMapObject s_ = new StringMapObject();
        s_.put("",Instances.newGerantPokemon());
        assertTrue(s_.contains(""));
    }

    @Test
    public void forms6() {
        StringMapObject s_ = new StringMapObject();
        s_.put("",Instances.newWildPk());
        assertTrue(s_.contains(""));
    }
    @Test
    public void inRange1() {
        assertFalse(CommonBean.inRange(0,1,10));
    }
    @Test
    public void inRange2() {
        assertFalse(CommonBean.inRange(11,1,10));
    }
    @Test
    public void inRange3() {
        assertTrue(CommonBean.inRange(1,1,10));
    }
    @Test
    public void inRange4() {
        assertTrue(CommonBean.inRange(10,1,10));
    }
    private RedirectAb redirectAb(String _key) {
        return new RedirectAb(_key, DEF_DIR);
    }

    private RedirectIt redirectIt(String _key, DataBase _db) {
        return new RedirectIt(_key, DEF_DIR,_db.getItem(_key));
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

    public static NaSt callRateAbsNb(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RateAbsNb(),_str,_args);
    }

    public static NaSt callRateIsZero(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RateIsZero(),_str,_args);
    }

    public static NaSt callRateIsZeroOrGt(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RateIsZeroOrGt(),_str,_args);
    }
    private String value(StringMapObject _forms) {
        return _forms.getValStr(KEY);
    }
}
