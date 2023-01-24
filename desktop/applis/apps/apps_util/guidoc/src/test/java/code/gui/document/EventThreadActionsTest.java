package code.gui.document;

import code.formathtml.render.*;
import code.gui.ProgressingWebDialog;
import code.gui.TextAnswerValue;
import code.gui.images.AbstractImage;
import code.mock.*;
import code.sml.*;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class EventThreadActionsTest extends EquallableGuiDocUtil {
    @Test
    public void withAction() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"<html><body>_</body></html>",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertEq(1,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
    }
    @Test
    public void noAction1() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertEq(0,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
    }
    @Test
    public void noAction2() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionNoAction());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertEq(0,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
    }
    @Test
    public void withActionDial() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"<html><body>_</body></html>",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertEq(1,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
    }
    @Test
    public void noActionDial1() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertEq(0,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
    }
    @Test
    public void noActionDial2() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionNoAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(pr_.getFrameFactory().newDialog());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertEq(0,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
    }
    @Test
    public void noActionDial3() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(pr_.getFrameFactory().newDialog());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertEq(0,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
    }
    @Test
    public void noActionDial4() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionNoAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(pr_.getFrameFactory().newDialog());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ren_.setDialog(null);
        ev_.run();
        assertEq(0,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
        ren_.finish();
    }
    @Test
    public void noActionDial5() {
        MockProgramInfos pr_ = newMockProgramInfos(new MockEventListIncr(new int[]{1}, new String[0], new TextAnswerValue[0]), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        RenderedPage ren_ = new RenderedPage(pr_.getCompoFactory().newAbsScrollPane(), pr_);
        ren_.setRenderAction(new AbstractRenderActionWithAction());
        ren_.setDialog(new ProgressingWebDialog(pr_));
        ren_.setProcess(one());
        ren_.setFrame(pr_.getFrameFactory().newDialog());
        NavigationCore c_ = new NavigationCore();
        RendKeyWordsGroup g_ = new RendKeyWordsGroup();
        setupText(c_,"",g_);
        ren_.initNav(c_, g_);
        EventThreadActions ev_ = EventThreadActions.inst(ren_, false, null);
        ev_.run();
        assertEq(0,((MockCompoFactory)pr_.getCompoFactory()).getLater().size());
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
