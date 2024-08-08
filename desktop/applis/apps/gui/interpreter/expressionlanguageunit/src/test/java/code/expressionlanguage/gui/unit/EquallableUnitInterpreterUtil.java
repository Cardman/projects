package code.expressionlanguage.gui.unit;

import code.expressionlanguage.utilcompo.FileInfos;
import code.gui.AbsButton;
import code.gui.AbsCustCheckBox;
import code.gui.CdmFactory;
import code.gui.LanguagesButtonsPair;
import code.gui.files.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.util.*;
import code.stream.core.ContentTime;
import code.threads.AbstractThread;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableUnitInterpreterUtil {

    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
    }
    public static CreateMainWindowUnit create(String..._args) {
        MockProgramInfos pr_ = build();
        return create(pr_,_args);
    }
    public static void update(MockProgramInfos _pr) {
        _pr.setLanguages(new StringList(""));
        _pr.setLanguage("");
        FileInfos.enTr(FileInfos.initComments(lg(_pr,"")));
        updateBase(_pr.currentLg());
    }
    public static void updateBase(TranslationsLg _en) {
        StringMap<TranslationsFile> en_ = FileFrame.initAppliTr(_en).getMapping();
        en_.addEntry(FileFrame.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(FileFrame.CONFIRM, MessagesConfirmDialog.en());
        en_.addEntry(FolderOpenFrame.FOLDER_OPEN_DIAL, MessagesFolderOpenDialog.en());
        en_.addEntry(FileOpenFrame.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(FileSaveFrame.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(FileTable.FILE_TAB,MessagesFileTable.en());
    }
    public static CreateMainWindowUnit create(MockProgramInfos _pr,String..._args) {
        update(_pr);
        CdmFactory cdm_ = new CdmFactory(_pr,new MockInterceptor());
        return new CreateMainWindowUnit(new StringList(_args),cdm_,_pr, new LanguagesButtonsPair(null,null,null));
    }
    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = MockProgramInfos.inst(_h, _t, new CustomSeedGene(_dbs), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguage("");
        return pr_;
    }
    public static StringMap<ContentTime> init() {
        return new StringMap<ContentTime>();
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name, String _content) {
        _all.put(_name,new ContentTime(StringUtil.encode(_content),_light.getThreadFactory().millis()));
        return _all;
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name) {
        _all.put(_name,new ContentTime(null,_light.getThreadFactory().millis()));
        return _all;
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
