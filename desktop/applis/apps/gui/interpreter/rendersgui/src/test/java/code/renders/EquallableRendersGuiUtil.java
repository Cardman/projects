package code.renders;

import code.expressionlanguage.utilcompo.*;
import code.formathtml.util.DefaultBeanAliases;
import code.gui.*;
import code.gui.files.*;
import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.sml.util.*;
import code.stream.core.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Assert;

public abstract class EquallableRendersGuiUtil {
    public static TranslationsLg lg(MockProgramInfos _pr, String _key) {
        return _pr.lg(_key);
    }
    public static CreateMainWindowRenders create(String..._args) {
        MockProgramInfos pr_ = build();
        return create(pr_,_args);
    }
    public static void update(MockProgramInfos _pr) {
        _pr.setLanguages(new StringList(StringUtil.EN,StringUtil.FR));
        _pr.setLanguage(StringUtil.EN);
        TranslationsLg en_ = lg(_pr, StringUtil.EN);
        TranslationsLg fr_ = lg(_pr, StringUtil.FR);
        DefaultBeanAliases.enTr(MessagesRenders.updateEn(FileInfos.enTr(FileInfos.initComments(en_))));
        DefaultBeanAliases.frTr(MessagesRenders.updateFr(FileInfos.enTr(FileInfos.initComments(fr_))));
        updateBase(_pr.currentLg());
    }
    public static void updateBase(TranslationsLg _en) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.initAppliTr(_en).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(MessagesGuiFct.CONFIRM, MessagesConfirmDialog.en());
        en_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL, MessagesFolderOpenDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_TAB,MessagesFileTable.en());
    }
    public static CreateMainWindowRenders create(MockProgramInfos _pr,String..._args) {
        update(_pr);
        CdmFactory cdm_ = new CdmFactory(_pr,new MockInterceptor());
        return new CreateMainWindowRenders("",new StringList(_args),cdm_,_pr, new LanguagesButtonsPair(null,null,null));
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
    public static void assertNull(String _result) {
        Assert.assertNull(_result);
    }
    public static void assertNotNull(String _result) {
        Assert.assertNotNull(_result);
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
