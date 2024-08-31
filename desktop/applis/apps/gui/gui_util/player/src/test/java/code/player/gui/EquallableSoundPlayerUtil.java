package code.player.gui;

import code.gui.AbsButton;
import code.gui.AbsCustCheckBox;
import code.gui.LanguagesButtonsPair;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.stream.AbsClipStream;
import code.threads.AbstractThread;
import code.util.Ints;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableSoundPlayerUtil {
    public static final String EN = StringUtil.EN;
    public static final String FR = StringUtil.FR;
    public static WindowPlayer windowPlayer() {
        MockProgramInfos pr_ = build();
        return new WindowPlayer(EN,pr_, new LanguagesButtonsPair(null,null,null), pr_.getImageFactory().newImageRgb(1,1));
    }
    public static WindowRecorder windowRecorder() {
        MockProgramInfos pr_ = build();
        return new WindowRecorder(pr_, new LanguagesButtonsPair(null,null,null));
    }
    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = MockProgramInfos.inst(_h, _t, new CustomSeedGene(_dbs), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguage(EN);
        MessagesSongs.updateEn(MessagesSongs.initAppliTr(pr_.lg(EN)));
        MessagesSongs.updateFr(MessagesSongs.initAppliTr(pr_.lg(FR)));
        MessagesSongs.sys(MessagesSongs.initAppliFilesTr(pr_.getTranslations()));
        MessagesSongs.getAppliFilesTr(pr_.getTranslations());
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
    public static byte[] wrapInts(int... _files) {
        return Ints.newList(_files).toArrByte();
    }
    public static void tryClick(AbsButton _m) {
        assertTrue(_m.isVisible());
        assertTrue(_m.isEnabled());
        _m.getActionListeners().get(0).action();
    }

    public static void tryToggle(AbsCustCheckBox _ch) {
        assertTrue(((MockCustComponent) _ch).isDeepAccessible());
        ((MockCustCheckBox)_ch).toggle();
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
    public static void assertNull(AbsClipStream _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNotNull(AbsClipStream _expected) {
        Assert.assertNotNull(_expected);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
}
