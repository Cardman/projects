package code.gui;

import code.gui.events.AlwaysActionListenerAct;
import code.gui.events.MockProgramInfosSecSample;
import code.mock.MockAbstractAction;
import code.mock.MockSampleFrame;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class LgsTest extends EquallableGuiFctUtil {
    @Test
    public void lgChButtons1() {
        MockProgramInfosSecSample pr_ = init();
        pr_.setLanguages(new StringList("_","__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_","_");
        ds_.addEntry("__","__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialogButtons l_ = new LanguageDialogButtons(pr_,null, new AlwaysActionListenerAct());
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame(pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_,pr_,"",new MockSampleFrame(pr_));
        l_.translate("");
        l_.commonParametersMenu(pr_.getCompoFactory().newMenuItem(),new MockAbstractAction(null),0,0);
        l_.getContent().getGroupe().get(1).getActionListeners().get(0).action();
        assertEq("__",l_.getLanguage());
        l_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(l_.getCommonFrame().isVisible());
    }
    @Test
    public void lgChButtons2() {
        MockProgramInfosSecSample pr_ = init();
        pr_.setLanguages(new StringList("_","__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_","_");
        ds_.addEntry("__","__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialogButtons l_ = new LanguageDialogButtons(pr_,pr_.getCompoFactory().newMenuItem(), new AlwaysActionListenerAct());
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame(pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_,pr_,"",new MockSampleFrame(pr_));
        l_.translate("");
        l_.commonParametersMenu(pr_.getCompoFactory().newMenuItem(),new MockAbstractAction(null),0,0);
        l_.getContent().getGroupe().get(1).getActionListeners().get(0).action();
        assertEq("__",l_.getLanguage());
        l_.getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(l_.getCommonFrame().isVisible());
    }
    @Test
    public void enableButtons1(){
        MockProgramInfosSecSample pr_ = init();
        pr_.setLanguages(new StringList("_","__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_","_");
        ds_.addEntry("__","__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialogButtons l_ = new LanguageDialogButtons(pr_,pr_.getCompoFactory().newMenuItem(), new AlwaysActionListenerAct());
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame(pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_,pr_,"",new MockSampleFrame(pr_));
        l_.enableButtons(false);
        assertEq(2,l_.getContent().getGroupe().size());
        assertFalse(l_.getContent().getGroupe().get(0).isEnabled());
        assertFalse(l_.getContent().getGroupe().get(1).isEnabled());
    }
    @Test
    public void enableButtons2(){
        MockProgramInfosSecSample pr_ = init();
        pr_.setLanguages(new StringList("_","__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_","_");
        ds_.addEntry("__","__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialogButtons l_ = new LanguageDialogButtons(pr_,pr_.getCompoFactory().newMenuItem(), new AlwaysActionListenerAct());
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame(pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_,pr_,"",new MockSampleFrame(pr_));
        l_.enableButtons(true);
        assertEq(2,l_.getContent().getGroupe().size());
        assertTrue(l_.getContent().getGroupe().get(0).isEnabled());
        assertTrue(l_.getContent().getGroupe().get(1).isEnabled());
    }
    @Test
    public void enableButtons3() {
        MockProgramInfosSecSample pr_ = init();
        pr_.setLanguages(new StringList("_", "__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_", "_");
        ds_.addEntry("__", "__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialogButtons l_ = new LanguageDialogButtons(pr_, pr_.getCompoFactory().newMenuItem(), new AlwaysActionListenerAct());
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame(pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_, pr_, "", new MockSampleFrame(pr_));
        assertTrue(LanguageComponentButtons.enableButtons(l_.getContent(), false));
    }
    @Test
    public void enableButtons4(){
        MockProgramInfosSecSample pr_ = init();
        pr_.setLanguages(new StringList("_","__"));
        StringMap<String> ds_ = new StringMap<String>();
        ds_.addEntry("_","_");
        ds_.addEntry("__","__");
        pr_.setDisplayLanguages(ds_);
        LanguageDialogButtons l_ = new LanguageDialogButtons(pr_,pr_.getCompoFactory().newMenuItem(), new AlwaysActionListenerAct());
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame(pr_, pr_.getImageFactory().newImageArgb(1, 1));
        l_.init(fr_,pr_,"",new MockSampleFrame(pr_));
        assertTrue(LanguageComponentButtons.enableButtons(l_.getContent(),true));
    }
    @Test
    public void enableButtons5(){
        assertFalse(LanguageComponentButtons.enableButtons(null,false));
        assertFalse(LanguageComponentButtons.enableButtons(null,true));
    }
    @Test
    public void trs1() {
        MockProgramInfosSecSample pr_ = init();
        AbsPlainLabel lab_ = pr_.getCompoFactory().newPlainLabel("");
        StringMap<String> trs_ = new StringMap<String>();
        trs_.addEntry("_","__");
        assertTrue(LanguageDialogButtons.translate(lab_, trs_,"_"));
        assertEq("__", lab_.getText());
    }
    @Test
    public void trs2() {
        StringMap<String> trs_ = new StringMap<String>();
        trs_.addEntry("_","__");
        assertFalse(LanguageDialogButtons.translate(null, trs_,"_"));
    }
}
