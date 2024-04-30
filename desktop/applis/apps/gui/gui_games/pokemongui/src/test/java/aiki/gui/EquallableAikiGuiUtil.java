package aiki.gui;

import aiki.db.DataBase;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.listeners.MouseTask;
import aiki.main.AikiFactory;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.sml.GamesPk;
import code.gui.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.util.TranslationsLg;
import code.threads.AbstractThread;
import code.util.StringList;
import org.junit.Assert;

public abstract class EquallableAikiGuiUtil {
    public static final String FR = "fr";
    public static final String EN = "en";
    public static final String LANGUAGE = EN;


    public static WindowAiki newGame() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        gameTr(pr_);
        return window(pr_, fact_);
    }

    public static void gameTr(MockProgramInfos _pr) {
        TranslationsLg en_ = _pr.lg(EN);
        GamesPk.enTr(GamesPk.initAppliTr(en_));
    }

    public static MockProgramInfos buildListLgs() {
        MockProgramInfos pr_ = build("/__/", "/_/", dbs(0.75));
        pr_.setLanguages(new StringList(EN));
        return pr_;
    }

    public static WindowAiki window(MockProgramInfos _pr, AikiFactory _fact) {
        WindowAiki wa_ = new WindowAiki(EN, _pr, _fact);
        wa_.setTaskEnabled(new MockTaskEnabled());
        wa_.pack();
        wa_.setVisible(true);
        wa_.getFacade().setSexList(new MockLSexList());
        wa_.getFacade().setLanguage(LANGUAGE);
        return wa_;
    }

    public static AikiFactory pkFact(MockProgramInfos _pr) {
        AikiFactory fact_ = new AikiFactory(_pr, new MockBaseExecutorServiceParam<AikiNatLgNamesNavigation>(), new MockBaseExecutorServiceParam<DataBase>());
        fact_.setConfPkStream(new MockConfPkStream());
        fact_.setGamePkStream(new MockGamePkStream());
        return fact_;
    }

    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = new MockProgramInfos(_h, _t, new MockEventListIncr(new CustomSeedGene(_dbs), new int[0], new String[0], new TextAnswerValue[]{new TextAnswerValue(GuiConstants.YES_OPTION, "file.txt")}), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguage(EN);
        return pr_;
    }

    public static AbstractThread tryAn(MockThreadFactory _g) {
        assertEq(1, _g.getAllThreads().size());
        AbstractThread th_ = _g.getAllThreads().get(0);
        _g.getAllThreads().remove(0);
        th_.join();
        checkNoAnim(_g);
        return th_;
    }

    public static void checkNoAnim(MockThreadFactory _thFact) {
        assertEq(0, _thFact.getAllThreads().size());
    }
    public static void tryClick(AbsButton _m) {
        assertTrue(_m.isVisible());
        assertTrue(_m.isEnabled());
        _m.getActionListeners().get(0).action();
    }
    public static void tryClick(AbsMetaLabelPk _m) {
        assertTrue(_m.getPaintableLabel().isVisible());
        assertTrue(_m.getPaintableLabel().isEnabled());
        _m.getPaintableLabel().getMouseListenersRel().get(0).mouseReleased(null, null, null);
    }
    public static void tryPress(AbsMetaLabelPk _m) {
        assertTrue(_m.getPaintableLabel().isVisible());
        assertTrue(_m.getPaintableLabel().isEnabled());
        MouseTask mt_ = (MouseTask) ((MockCustComponent) _m.getPaintableLabel()).getMouseWithoutClickListeners().get(0);
        mt_.mousePressed(null, null, null);
        ((MockBaseExecutorService)mt_.getTimer()).getTasks().lastValue().attendre();
        mt_.mouseReleased(null, null, null);
    }
    public static void tryPress(AbsMetaLabelPk _m, int _dir) {
        assertTrue(_m.getPaintableLabel().isVisible());
        assertTrue(_m.getPaintableLabel().isEnabled());
        ((MockAbstractAction) GuiBaseUtil.getAction(_m.getPaintableLabel(),_dir,0)).action();
    }
    public static void tryCheck(AbsCustCheckBox _ch, boolean _v) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        _ch.setSelected(_v);
    }

    public static void tryToggle(AbsCustCheckBox _ch) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        ((MockCustCheckBox)_ch).toggle();
    }

    public static double[] dbs(double... _args) {
        return _args;
    }

    public static void eventsCombo(ScrollCustomCombo _combo, int _i) {
        _combo.select(_i);
        _combo.events(null);
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
    }
    public static void assertNull(AbsCustComponent _compo) {
        Assert.assertNull(_compo);
    }
    public static void assertNotNull(AbsCustComponent _compo) {
        Assert.assertNotNull(_compo);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
}
