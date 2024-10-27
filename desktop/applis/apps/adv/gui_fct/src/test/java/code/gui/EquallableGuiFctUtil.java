package code.gui;

import code.gui.events.*;
import code.gui.files.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.mock.*;
import code.sml.util.*;
import code.stream.*;
import code.stream.core.*;
import code.threads.*;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableGuiFctUtil {

    public static void update(MockProgramInfosSecSample _pr) {
        MessagesGuiFct.enTr(MessagesGuiFct.initAppliTr(lg(_pr, "")));
        MessagesGuiFct.frTr(MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.FR)));
    }

    public static void updateBase(MockProgramInfosSecSample _pr) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_DIAL, MessagesFileDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_DIAL,MessagesFileDialog.fr());
        _pr.setLanguage(StringUtil.EN);
    }

    public static void updateConfirm(MockProgramInfosSecSample _pr) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.CONFIRM, MessagesConfirmDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.fr());
        _pr.setLanguage(StringUtil.EN);
    }

    public static void updateFolderOpen(MockProgramInfosSecSample _pr) {
        updateBase(_pr);
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL, MessagesFolderOpenDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.fr());
        updateFileTable(_pr);
    }

    public static void updateFileOpen(MockProgramInfosSecSample _pr) {
        updateBase(_pr);
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.fr());
        updateFileTable(_pr);
    }

    public static void updateFileSave(MockProgramInfosSecSample _pr) {
        updateBase(_pr);
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.fr());
        updateFileTable(_pr);
    }

    public static void updateFileOpenSave(MockProgramInfosSecSample _pr) {
        updateBase(_pr);
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.fr());
        fr_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.fr());
        fr_.addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.fr());
        updateFileTable(_pr);
    }

    public static void updateFolderOpenSave(MockProgramInfosSecSample _pr) {
        updateBase(_pr);
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        en_.addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.fr());
        fr_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.fr());
        fr_.addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.fr());
        updateFileTable(_pr);
    }

    public static void updateFileTable(MockProgramInfosSecSample _pr) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_TAB,MessagesFileTable.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_TAB,MessagesFileTable.fr());
    }
    public static TranslationsLg lg(MockProgramInfosSecSample _pr, String _key) {
        return _pr.lg(_key);
    }
    public static double[] dbs(double... _args) {
        return _args;
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

    public static void tryType(AbsTextField _field, String _txt) {
        assertTrue(((MockCustComponent)_field).isDeepAccessible());
        _field.setText(_txt);
    }
    public static void tryClick(AbsButton _but) {
        assertTrue(((MockCustComponent)_but).isDeepAccessible());
        _but.getActionListeners().get(0).action();
    }
    public static void assertNull(String _expected) {
        Assert.assertNull(_expected);
    }
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
    public static void assertSame(AbstractMutableTreeNodeCore<String> _expected, AbstractMutableTreeNodeCore<String> _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AbsCustComponent _expected, AbsCustComponent _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AbstractAtomicIntegerCore _expected, AbstractAtomicIntegerCore _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(ThState _expected, ThState _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringUtil.concat(_expected.toNumberString(),"!=",_result.toNumberString()), _expected.eq(_result));
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringUtil.concat(_expected.toNumberString(),"!=",_result.toNumberString()), _expected.eq(_result));
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }

    public static void assertNull(AbsClipStream _value) {
        Assert.assertNull(_value);
    }

    public static void assertNotNull(AbsClipStream _value) {
        Assert.assertNotNull(_value);
    }

    public static MockProgramInfosSecSample init() {
        return init("/");
    }

    public static MockProgramInfosSecSample init(int _inc) {
        return new MockProgramInfosSecSample("", "",0, new long[]{_inc}, true, "/");
    }

    public static MockProgramInfosSecSample init(String _r) {
        return new MockProgramInfosSecSample("", "",0, new long[0], true, _r);
    }

    public static MockProgramInfosSecSample initHomePath() {
        return new MockProgramInfosSecSample("/home", "",0, new long[0], true, "/");
    }
}
