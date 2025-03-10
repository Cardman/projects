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
        assertEq(DIRECT, tryRedirect(d_, forms_, redirectAb(A_ABILITY)));
        assertEq(A_ABILITY, value(forms_));
    }

    @Test
    public void redirect2() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR, tryRedirect(d_, forms_, redirectAb(NULL_REF)));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect3() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(CommonBean.REN_ADD_WEB_HTML_ITEMS_BALL_HTML, tryRedirect(d_, forms_, redirectIt(I_ITEM,d_.getData())));
        assertEq(I_ITEM, value(forms_));
    }

    @Test
    public void redirect4() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR, tryRedirect(d_, forms_, redirectIt(NULL_REF,d_.getData())));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect5() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT, tryRedirect(d_, forms_, redirectMv(M_DAM)));
        assertEq(M_DAM, value(forms_));
    }

    @Test
    public void redirect6() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR, tryRedirect(d_, forms_, redirectMv(NULL_REF)));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect7() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT, tryRedirect(d_, forms_, redirectPk(P_POKEMON)));
        assertEq(P_POKEMON, value(forms_));
    }

    @Test
    public void redirect8() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR, tryRedirect(d_, forms_, redirectPk(NULL_REF)));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect9() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT, tryRedirect(d_, forms_, redirectSt(S_STA_SIM)));
        assertEq(S_STA_SIM, value(forms_));
    }

    @Test
    public void redirect10() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DEF_DIR, tryRedirect(d_, forms_, redirectSt(NULL_REF)));
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

    private String tryRedirect(FacadeGame _d, StringMapObject _forms, AbsRedirectAdv _red) {
        return AbsRedirect.tryRedirect(_red, KEY, DIRECT, _d.getData(), _forms);
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

    public static NaSt callRateAbsNb(NaSt _str, int... _args) {
        return new RtSt(( ((RtSt) _str).getInstance()).absNb());
    }

    public static NaSt callRateIsZero(NaSt _str, int... _args) {
        return NaBoSt.of(( ((RtSt) _str).getInstance()).isZero());
    }

    public static NaSt callRateIsZeroOrGt(NaSt _str, int... _args) {
        return NaBoSt.of(( ((RtSt) _str).getInstance()).isZeroOrGt());
    }
    private String value(StringMapObject _forms) {
        return _forms.getValStr(KEY);
    }
}
