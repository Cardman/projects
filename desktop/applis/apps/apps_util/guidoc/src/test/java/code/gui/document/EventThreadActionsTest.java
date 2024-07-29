package code.gui.document;

import code.formathtml.render.*;
import code.gui.ProgressingWebDialog;
import code.gui.TextAnswerValue;
import code.gui.images.AbstractImage;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.*;
import code.sml.*;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class EventThreadActionsTest extends EquallableGuiDocUtil {
    @Test
    public void lgButton1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        pr_.setLanguages(new StringList("0","1"));
        RenderedPage ren_ = newRenderedPageLg(pr_);
        ren_.enableLgButtons(false);
        assertEq(2,ren_.getLanguageComponentButtons().getGroupe().size());
        assertFalse(ren_.getLanguageComponentButtons().getGroupe().get(0).isEnabled());
        assertFalse(ren_.getLanguageComponentButtons().getGroupe().get(1).isEnabled());
    }
    @Test
    public void lgButton2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        pr_.setLanguages(new StringList("0","1"));
        RenderedPage ren_ = newRenderedPageLg(pr_);
        ren_.enableLgButtons(true);
        assertEq(2,ren_.getLanguageComponentButtons().getGroupe().size());
        assertTrue(ren_.getLanguageComponentButtons().getGroupe().get(0).isEnabled());
        assertTrue(ren_.getLanguageComponentButtons().getGroupe().get(1).isEnabled());
    }
    @Test
    public void withAction() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"<html><body>_</body></html>",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertTrue(ev_.isRendered());
    }
    @Test
    public void noAction1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
    }
    @Test
    public void noAction2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionNoAction());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
    }
    @Test
    public void withActionDial() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"<html><body>_</body></html>",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertTrue(ev_.isRendered());
    }
    @Test
    public void noActionDial1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
    }
    @Test
    public void noActionDial2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionNoAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(pr_.getFrameFactory().newCommonFrame(pr_,null));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
    }
    @Test
    public void noActionDial3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(pr_.getFrameFactory().newCommonFrame(pr_,null));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
    }
    @Test
    public void noActionDial4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionNoAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(pr_.getFrameFactory().newCommonFrame(pr_,null));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ren_.setDialog(null);
        ev_.run();
        assertFalse(ev_.isRendered());
        ren_.finish();
    }
    @Test
    public void noActionDial5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(pr_.getFrameFactory().newCommonFrame(pr_,null));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
        ev_.run();
    }
    @Test
    public void noActionDial6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionNoAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(ren_.getGene().getFrameFactory().newCommonFrame(ren_.getGene(),null));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
    }
    @Test
    public void noActionDial7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(ren_.getGene().getFrameFactory().newCommonFrame(ren_.getGene(),null));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
    }
    @Test
    public void noActionDial8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionNoAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(ren_.getGene().getFrameFactory().newCommonFrame(ren_.getGene(),null));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ren_.setDialog(null);
        ev_.run();
        assertFalse(ev_.isRendered());
        ren_.finish();
    }
    @Test
    public void noActionDial9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = newRenderedPage(pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(ren_.getGene().getFrameFactory().newCommonFrame(ren_.getGene(),null));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertFalse(ev_.isRendered());
        ev_.run();
    }
    private CustList<AbstractImage> one() {
        CustList<AbstractImage> l_ = new CustList<AbstractImage>();
        l_.add(new MockImage(new int[1][1]));
        return l_;
    }

    private static void setupText(NavigationCore _al,String _txt,RendKeyWordsGroup _g) {
        Document document_ = doc(_txt);
        _al.setupText(_txt,document_,_g.getKeyWordsTags().getKeyWordHead(),_g.getKeyWordsAttrs().getAttrTitle());
    }
    private static Document doc(String _txt) {
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(_txt);
        return res_.getDocument();
    }
}
