package code.minirts;

import code.gui.*;
import code.maths.*;
import code.maths.geo.*;
import code.maths.montecarlo.*;
import code.minirts.events.*;
import code.mock.*;
import code.threads.*;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableRtsUtil {
    public static WindowRts window() {
        MockProgramInfos pr_ = build();
        WindowRts wa_ = new WindowRts(pr_, new LanguagesButtonsPair(null,null,null), new int[][]{new int[]{GuiConstants.BLACK,GuiConstants.WHITE}});
        wa_.setTaskEnabled(new MockRtsTaskEnabled());
        wa_.pack();
        wa_.setVisible(true);
        return wa_;
    }
    public static AbstractThread tryAn(MockThreadFactory _g) {
        assertEq(1, _g.getAllThreads().size());
        AbstractThread th_ = _g.getAllThreads().get(0);
        _g.getAllThreads().remove(0);
        th_.join();
        checkNoAnim(_g);
        return th_;
    }
    public static AbstractThread tryAnNoCheck(MockThreadFactory _g) {
        assertEq(1, _g.getAllThreads().size());
        AbstractThread th_ = _g.getAllThreads().get(0);
        _g.getAllThreads().remove(0);
        th_.join();
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
    public static void tryClick(AbsMetaLabelRts _m) {
        assertTrue(_m.getPaintableLabel().isVisible());
        assertTrue(_m.getPaintableLabel().isEnabled());
        _m.getPaintableLabel().getMouseListenersRel().get(0).mouseReleased(null, null, null);
    }
    public static void tryPress(AbsMetaLabelRts _m) {
        assertTrue(_m.getPaintableLabel().isVisible());
        assertTrue(_m.getPaintableLabel().isEnabled());
        RtsMouseTask mt_ = (RtsMouseTask) ((MockCustComponent) _m.getPaintableLabel()).getMouseWithoutClickPrListeners().get(0);
        mt_.mouseEntered(null, null, null);
        ((MockBaseExecutorService)mt_.getTimer()).getTasks().lastValue().attendre();
        mt_.mouseExited(null, null, null);
    }
    public static void tryPress(AbsMetaLabelRts _m, int _dir) {
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

    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = MockProgramInfos.inst(_h, _t, new CustomSeedGene(_dbs), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguages(new StringList(StringUtil.EN,StringUtil.FR));
        MessagesRts.updateEn(MessagesRts.initAppliTr(pr_.lg(StringUtil.EN)));
        MessagesRts.updateFr(MessagesRts.initAppliTr(pr_.lg(StringUtil.FR)));
        pr_.setLanguage(StringUtil.EN);
        return pr_;
    }

    public static double[] dbs(double... _args) {
        return _args;
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
    public static void assertEq(RatePoint _expected, RatePoint _result) {
        Assert.assertTrue(_expected.eq(_result));
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
}
