package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.FileInfos;
import code.gui.AbsCustComponent;
import code.gui.GuiBaseUtil;
import code.gui.GuiConstants;
import code.gui.TextAnswerValue;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.util.TranslationsLg;
import code.stream.core.AbstractBinFact;
import code.stream.core.AbstractTextFact;
import code.stream.core.AbstractZipFact;
import code.threads.ThState;
import code.util.IdList;
import code.util.StringList;
import org.junit.Assert;

public abstract class EquallableElAdvUtil {
    public static void assertNull(AbsCustComponent _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractBinFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractZipFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractTextFact _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertSame(AbsCustComponent _expected, AbsCustComponent _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(ThState _expected, ThState _result) {
        Assert.assertSame(_expected, _result);
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

    public static WindowCdmEditor window(AbstractProgramInfos _pr) {
        return window(_pr,new IdList<WindowCdmEditor>());
    }

    public static WindowCdmEditor window(AbstractProgramInfos _pr, IdList<WindowCdmEditor> _opened) {
        WindowCdmEditor w_ = new WindowCdmEditor("en", _pr, _opened);
        w_.getTabEditor().getWholeWord().setSelected(false);
        return w_;
    }

    public static double[] dbs(double... _args) {
        return _args;
    }

    public static MockProgramInfos prWrite() {
        MockProgramInfos prs_ = prWriteQuick();
        prs_.setLanguages(new StringList(FileInfos.EN,FileInfos.FR));
        prs_.setLanguage(FileInfos.EN);
        update(prs_);
        return prs_;
    }

    public static MockProgramInfos prWriteQuick() {
        return newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(0, new long[1], new String[]{"/"}));
    }

    public static void update(MockProgramInfos _pr) {
        FileInfos.initComments(lg(_pr,FileInfos.EN));
        FileInfos.initComments(lg(_pr,FileInfos.FR));
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        TranslationsLg lg_ = new TranslationsLg();
        _pr.getTranslations().getMapping().addEntry(_key, lg_);
        return lg_;
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        return new MockProgramInfos("", "", new MockEventListIncr(_s,new int[0],new String[0],new TextAnswerValue[0]), _set);
    }
    public static MockProgramInfos newMockProgramInfos(MockEventListIncr _s, MockFileSet _set) {
        return new MockProgramInfos("", "", _s, _set);
    }

    protected static TabEditor tabEditor(WindowCdmEditor _w) {
        return _w.getTabEditor();
    }

    protected void findNow(MockProgramInfos _pr, WindowCdmEditor _w, String _v) {
        tabEditor(_w).getFinder().setText(_v);
        invokeAndClear(_pr);
    }

    protected void changeNow(MockProgramInfos _pr, WindowCdmEditor _w, String _v) {
        tabEditor(_w).getCenter().setText(_v);
        invokeAndClear(_pr);
    }
    protected void findText(WindowCdmEditor _w, MockProgramInfos _pr) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK)).action();
        invokeAndClear(_pr);
    }
    protected void replaceText(WindowCdmEditor _w, MockProgramInfos _pr) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_R,GuiConstants.CTRL_DOWN_MASK)).action();
        invokeAndClear(_pr);
    }
    protected static void invokeAndClear(MockProgramInfos _pr) {
        ((MockCompoFactory) _pr.getCompoFactory()).invoke();
        ((MockCompoFactory) _pr.getCompoFactory()).getLater().clear();
    }
    protected void storeEdit(WindowCdmEditor _w, MockProgramInfos _pr) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_Y,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK)).action();
        invokeAndClear(_pr);
    }
    protected void undo(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_Z,GuiConstants.CTRL_DOWN_MASK)).action();
    }
    protected void redo(WindowCdmEditor _w) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_Y,GuiConstants.CTRL_DOWN_MASK)).action();
    }
    protected void clearEdit(WindowCdmEditor _w, MockProgramInfos _pr) {
        ((MockAbstractAction) GuiBaseUtil.getAction(tabEditor(_w).getCenter(), GuiConstants.VK_Z,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK)).action();
        invokeAndClear(_pr);
    }
}
