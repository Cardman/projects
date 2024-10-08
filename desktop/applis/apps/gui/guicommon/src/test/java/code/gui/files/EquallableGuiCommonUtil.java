package code.gui.files;

import code.gui.AbsButton;
import code.gui.AbsTextField;
import code.mock.MockCustComponent;
import code.mock.MockProgramInfos;
import code.mock.MockThreadFactory;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.threads.AbstractThread;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableGuiCommonUtil {

    public static void update(MockProgramInfos _pr) {
        MessagesGuiFct.enTr(MessagesGuiFct.initAppliTr(lg(_pr, "")));
        MessagesGuiFct.frTr(MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.FR)));
    }

    public static void updateBase(MockProgramInfos _pr) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_DIAL,MessagesFileDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_DIAL,MessagesFileDialog.fr());
        _pr.setLanguage(StringUtil.EN);
    }

    public static void updateConfirm(MockProgramInfos _pr) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.initAppliTr(lg(_pr, StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.CONFIRM,MessagesConfirmDialog.fr());
        _pr.setLanguage(StringUtil.EN);
    }

    public static void updateFolderOpen(MockProgramInfos _pr) {
        updateBase(_pr);
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.fr());
        updateFileTable(_pr);
    }

    public static void updateFileOpen(MockProgramInfos _pr) {
        updateBase(_pr);
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.fr());
        updateFileTable(_pr);
    }

    public static void updateFileSave(MockProgramInfos _pr) {
        updateBase(_pr);
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.fr());
        updateFileTable(_pr);
    }

    public static void updateFileOpenSave(MockProgramInfos _pr) {
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

    public static void updateFolderOpenSave(MockProgramInfos _pr) {
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

    public static void updateFileTable(MockProgramInfos _pr) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal(StringUtil.EN)).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_TAB,MessagesFileTable.en());
        StringMap<TranslationsFile> fr_ = MessagesGuiFct.getAppliTr(_pr.getTranslations().getMapping().getVal( StringUtil.FR)).getMapping();
        fr_.addEntry(MessagesGuiFct.FILE_TAB,MessagesFileTable.fr());
    }
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
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
    public static double[] dbs(double... _args) {
        return _args;
    }
}
