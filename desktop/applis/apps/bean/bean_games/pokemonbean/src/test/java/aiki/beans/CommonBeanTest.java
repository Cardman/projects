package aiki.beans;

import aiki.facade.FacadeGame;
import code.util.core.StringUtil;
import org.junit.Test;

public final class CommonBeanTest extends InitDbWelcome {

    public static final String DIRECT = "__";
    public static final String KEY = "_";

    @Test
    public void redirect1() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectAb(A_ABILITY), KEY, DIRECT,d_.getData(), forms_));
        assertEq(A_ABILITY, value(forms_));
    }

    @Test
    public void redirect2() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq("",AbsRedirect.tryRedirect(new RedirectAb(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect3() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectIt(I_ITEM), KEY, DIRECT,d_.getData(), forms_));
        assertEq(I_ITEM, value(forms_));
    }

    @Test
    public void redirect4() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq("",AbsRedirect.tryRedirect(new RedirectIt(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect5() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectMv(M_DAM), KEY, DIRECT,d_.getData(), forms_));
        assertEq(M_DAM, value(forms_));
    }

    @Test
    public void redirect6() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq("",AbsRedirect.tryRedirect(new RedirectMv(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect7() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectPk(P_POKEMON), KEY, DIRECT,d_.getData(), forms_));
        assertEq(P_POKEMON, value(forms_));
    }

    @Test
    public void redirect8() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq("",AbsRedirect.tryRedirect(new RedirectPk(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    @Test
    public void redirect9() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq(DIRECT,AbsRedirect.tryRedirect(new RedirectSt(S_STA_SIM), KEY, DIRECT,d_.getData(), forms_));
        assertEq(S_STA_SIM, value(forms_));
    }

    @Test
    public void redirect10() {
        FacadeGame d_ = feedDbBase();
        StringMapObject forms_ = new StringMapObject();
        assertEq("",AbsRedirect.tryRedirect(new RedirectSt(NULL_REF), KEY, DIRECT,d_.getData(), forms_));
        assertEq("", StringUtil.nullToEmpty(value(forms_)));
    }

    private String value(StringMapObject _forms) {
        return _forms.getValStr(KEY);
    }
}
