package code.mock;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.gui.*;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsWindowListener;
import code.gui.events.AbsWindowListenerClosing;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbsFrameFactory;
import code.stream.AbsClipStream;
import code.stream.AbsSoundRecord;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class MockComponentsTest extends EquallableMockGuiUtil {
    @Test
    public void c1() {
        MockCommonFrameSample fr_ = new MockCommonFrameSample(init());
        fr_.setVisible(true);
        assertTrue(fr_.isVisible());
    }
    @Test
    public void c2() {
        MockProgramInfosSample init_ = init();
        init_.setScreenHeight(128);
        init_.setScreenWidth(1024);
        MockCommonFrameSample fr_ = new MockCommonFrameSample(init_);
        fr_.setLocationRelativeToWindow(null);
        assertEq(512,fr_.getLocationFirst());
        assertEq(64,fr_.getLocationSecond());
    }
    @Test
    public void c3() {
        MockProgramInfosSample init_ = init();
        init_.setScreenHeight(128);
        init_.setScreenWidth(1024);
        MockCommonFrameSample fr_ = new MockCommonFrameSample(init_);
        MockDialogSample fr2_ = new MockDialogSample(init_);
        fr_.setLocationRelativeToWindow(null);
        fr2_.setLocationOnScreen(new MetaPoint(512,64));
        fr2_.getPane().setSize(new MetaDimension(128,64));
        fr_.setLocationRelativeToWindow(fr2_);
        assertEq(576,fr_.getLocationFirst());
        assertEq(96,fr_.getLocationSecond());
    }
    @Test
    public void c4() {
        MockProgramInfosSample init_ = init();
        init_.setScreenHeight(128);
        init_.setScreenWidth(1024);
        MockCommonFrameSample fr_ = new MockCommonFrameSample(init_);
        MockCommonFrameSample fr2_ = new MockCommonFrameSample(init_);
        fr_.setLocationRelativeToWindow(null);
        fr2_.setLocationOnScreen(new MetaPoint(512,64));
        fr2_.getPane().setSize(new MetaDimension(128,64));
        fr_.setLocationRelativeToWindow(fr2_);
        assertEq(576,fr_.getLocationFirst());
        assertEq(96,fr_.getLocationSecond());
    }
    @Test
    public void c5() {
        MockProgramInfosSample init_ = init();
        init_.setScreenHeight(128);
        init_.setScreenWidth(1024);
        MockDialogSample fr_ = new MockDialogSample(init_);
        MockDialogSample fr2_ = new MockDialogSample(fr_,init_);
        fr2_.closeWindow();
        assertFalse(fr2_.isVisible());
    }
    @Test
    public void c6() {
        MockProgramInfosSample init_ = init();
        init_.setScreenHeight(128);
        init_.setScreenWidth(1024);
        MockDialogSample fr_ = new MockDialogSample(init_);
        fr_.closeWindow();
        assertFalse(fr_.isVisible());
    }
    @Test
    public void c7() {
        MockProgramInfosSample init_ = init();
        MockRunnable r_ = new MockRunnable();
        AbsCompoFactory ab_ = init_.getCompoFactory();
        ab_.invokeLater(r_);
        assertFalse(r_.isStarted());
        ((MockCompoFactory)ab_).invoke();
        assertTrue(r_.isStarted());
    }
    @Test
    public void c8() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        AbsMenu m_ = ab_.newMenu();
        b_.add(m_);
        m_.addMenuItem(ab_.newMenuItem());
        m_.addMenuItem(ab_.newCheckBoxMenuItem());
        m_.addMenuItem(ab_.newMenuItem("_"));
        m_.addMenuItem(ab_.newCheckBoxMenuItem("_"));
        assertEq(1,b_.getMenuCount());
        assertEq("",m_.getText());
        assertEq(4,m_.getItemCount());
        assertEq("",m_.getItems().get(0).getText());
        assertEq("",m_.getItems().get(1).getText());
        assertEq("_",m_.getItems().get(2).getText());
        assertEq("_",m_.getItems().get(3).getText());
    }
    @Test
    public void c9() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        AbsMenu m_ = ab_.newMenu("_");
        b_.add(m_);
        m_.addMenuItem(ab_.newMenu());
        m_.addMenuItem(ab_.newMenu("_"));
        assertEq(1,b_.getMenuCount());
        assertEq("_",m_.getText());
        assertEq(2,m_.getItemCount());
        assertEq("",m_.getItems().get(0).getText());
        assertEq("_",m_.getItems().get(1).getText());
    }
    @Test
    public void c10() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        AbsMenu m_ = ab_.newMenu("_");
        b_.add(m_);
        b_.remove(m_);
        assertEq(0,b_.getMenuCount());
    }
    @Test
    public void c11() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        AbsMenu m_ = ab_.newMenu();
        b_.add(m_);
        m_.addMenuItem(ab_.newMenu());
        m_.addMenuItem(ab_.newMenuItem());
        m_.addMenuItem(ab_.newCheckBoxMenuItem());
        m_.removeMenuItem((AbsCheckBoxMenuItem) m_.getItems().last());
        m_.removeMenuItem((AbsMenuItem) m_.getItems().last());
        m_.removeMenuItem((AbsMenu) m_.getItems().last());
        assertEq(0,m_.getItemCount());
        m_.addSeparator();
        m_.setText("_");
        assertEq("_",m_.getText());
        assertNull(m_.getParentMenu());
        m_.setEnabledMenu(false);
        assertFalse(m_.isEnabled());
    }
    @Test
    public void c12() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        AbsMenu m_ = ab_.newMenu();
        b_.add(m_);
        AbsMenuItem i_ = ab_.newMenuItem();
        i_.setEnabledMenu(false);
        i_.setText("_");
        assertFalse(i_.isEnabled());
        assertEq("_",i_.getText());
        assertNull(i_.getParentMenu());
        i_.setAccelerator(' ');
        i_.setAccelerator("");
        i_.setAccelerator(0,0);
        i_.addActionListener((AbsActionListener) null);
        i_.addActionListener((AbsAdvActionListener) null);
        assertEq(1, ((MockMenuItem)i_).getActionListeners().size());
        assertEq(1, ((MockMenuItem)i_).getAdvActionListeners().size());
    }
    @Test
    public void c13() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        AbsMenu m_ = ab_.newMenu();
        b_.add(m_);
        AbsCheckBoxMenuItem i_ = ab_.newCheckBoxMenuItem();
        i_.setSelected(true);
        assertTrue(i_.isSelected());
        i_.setEnabledMenu(false);
        i_.setText("_");
        assertFalse(i_.isEnabled());
        assertEq("_",i_.getText());
        assertNull(i_.getParentMenu());
        i_.setAccelerator(' ');
        i_.setAccelerator("");
        i_.setAccelerator(0,0);
        i_.addActionListener((AbsActionListener) null);
        i_.addActionListener((AbsAdvActionListener) null);
        assertEq(1, ((MockCheckBoxMenuItem)i_).getActionListeners().size());
        assertEq(1, ((MockCheckBoxMenuItem)i_).getAdvActionListeners().size());
    }
    @Test
    public void c14() {
        MockProgramInfosSample pr_ = init();
        AbsCompoFactory ab_ = pr_.getCompoFactory();
        AbsPopupMenu b_ = ab_.newAbsPopupMenu();
        AbsMenu m_ = ab_.newMenu();
        b_.add(m_);
        AbsMenuItem i_ = ab_.newMenuItem();
        b_.add(i_);
        AbsCustCheckBox c_ = ab_.newCustCheckBox();
        b_.add(c_);
        AbsCheckBoxMenuItem o_ = ab_.newCheckBoxMenuItem();
        b_.add(o_);
        b_.remove(m_);
        b_.remove(i_);
        b_.remove(c_);
        b_.remove(o_);
        b_.show(0,0);
        AbsWrappedTextArea ta_ = ab_.newWrappedTextArea(15, 16);
        b_.show(ta_,0,0);
        assertEq(16,((MockWrappedTextArea)ta_).getCols());
        assertEq(15,((MockWrappedTextArea)ta_).getRows());
        ta_.setText("hello");
        ta_.append(" world");
        ta_.setEditable(false);
        assertEq("hello world",((MockWrappedTextArea) ta_).getText());
        AbsPaintableLabel g_ = ab_.newAbsPaintableLabel();
        g_.repaintLabel(pr_.getImageFactory());
        b_.add(g_);
        AbsPlainButton but_ = ab_.newPlainButton();
        but_.setText("");
        b_.add(but_);
        b_.add(ab_.newPlainButton("_"));
        assertEq("",but_.getText());
        but_.addActionListener((AbsActionListener) null);
        but_.addActionListener((AbsAdvActionListener) null);
        assertEq(1, ((MockPlainButton)but_).getActionListeners().size());
        assertEq(1, ((MockPlainButton)but_).getAdvActionListeners().size());
        AbsPreparedLabel prep_ = ab_.newPreparedLabel("");
        prep_.setIcon(pr_.getImageFactory(),pr_.getImageFactory().newImageArgb(1,1));
        b_.add(prep_);
        b_.add(ab_.newPreparedLabel(pr_.getImageFactory().newImageArgb(1,1)));
        b_.add(pr_.getImageFactory().newImageArgb(1,1).newAbsPreparedLabel());
        assertEq(1,prep_.getHeight());
        assertEq(1,prep_.getWidth());
        ab_.newCustCheckBox();
        AbsCustCheckBox ch_ = ab_.newCustCheckBox("");
        ch_.setSelected(true);
        assertTrue(ch_.isSelected());
        ch_.setText("_");
        assertEq("_",ch_.getText());
        ch_.addActionListener(null);
        ch_.removeActionListener(null);
        assertEq(0,ch_.getActionListeners().size());
        MockPaintableLabelAbs paint_ = (MockPaintableLabelAbs) ab_.newAbsPaintableLabel(new MockMetaLabel());
        paint_.setEmptyIcon();
        assertTrue(paint_.isEmpty());
        paint_.setIcon(pr_.getImageFactory(),pr_.getImageFactory().newImageArgb(1,1));
        assertFalse(paint_.isEmpty());
        paint_.requestFocusInWindow();
        paint_.setHorizontalAlignment(2);
        paint_.setVerticalAlignment(2);
        assertEq(2,paint_.getHorizontalAlignment());
        assertEq(2,paint_.getVerticalAlignment());
        AbsImgButton img_ = ab_.newImgButton(pr_.getImageFactory().newImageArgb(1, 1));
        img_.addMouseList(null);
        assertEq(1,((MockImgButton)img_).getAbsActionListeners().size());
        AbstractImage ig_ = pr_.getImageFactory().newImageArgb(1, 1);
        ig_.drawLine(0,0,0,0);
        ig_.drawRect(0,0,0,0);
        ig_.drawOval(0,0,0,0);
        ig_.drawString("",0,0);
        ig_.drawPolygon(new int[0],new int[0],0);
        ig_.fillRect(0,0,0,0);
        ig_.fillOval(0,0,0,0);
        ig_.fillPolygon(new int[0],new int[0],0);
        ig_.translate(0,0);
        assertEq(8,ig_.toBytes().length);
        assertEq(8,ig_.writeImg("").length);
        ig_.setFont(ab_.newAbsolute());
        ig_.setFont(new MockMetaLabel());
        ig_.setFont(new MetaFont("",0,0));
        ig_.setFont("",0,0);
        ig_.setColorBg(ab_.newAbsolute());
        ig_.setColorFg(ab_.newAbsolute());
        ig_.setColor(1);
        ig_.dispose();
        assertEq(1,ig_.getColorValue());
        assertEq("",ig_.getMetaFont().getFontFamily());
        pr_.getLightFrameFactory().newOtherFrame();
        pr_.getLightFrameFactory().newOtherDialog();
        pr_.getMessageDialogAbs().input((AbsDialog) null,"","","",0);
        pr_.getMessageDialogAbs().input((AbsCommonFrame) null,"","","",0);
        pr_.getMessageDialogAbs().input(null,(AbsCustComponent) null,"","",0);
        AbsFrameFactory frFact_ = pr_.getFrameFactory();
        AbsDialog base_ = frFact_.newDialog();
        AbsDialog adv_ = frFact_.newDialog(base_);
        adv_.pack();
        adv_.setDialogIcon(pr_.getImageFactory(),base_);
        adv_.setResizable(true);
        assertTrue(((MockAbsDialog)adv_).isResizable());
        adv_.setModal(true);
        assertTrue(((MockAbsDialog)adv_).isModal());
        MockAbsCommonFrame mf_ = (MockAbsCommonFrame) frFact_.newCommonFrame("", pr_, ig_);
        mf_.setJMenuBar(mf_.getJMenuBar());
        mf_.setFocusable(true);
        mf_.requestFocus();
        mf_.setOwner(mf_.getOwner());
        mf_.setContentPane(mf_.getContentPane());
        mf_.setContentPane(ab_.newAbsScrollPane());
        mf_.setLocationRelativeTo(ab_.newTextArea());
        mf_.addWindowListener((AbsWindowListener) null);
        mf_.addWindowListener((AbsWindowListenerClosing) null);
        mf_.removeWindowListener((AbsWindowListener) null);
        mf_.removeWindowListener((AbsWindowListenerClosing) null);
        mf_.setLocation(0,0);
        mf_.setDefaultCloseOperation(0);
        mf_.setAccessFile(mf_.getAccessFile());
        mf_.setMainFrame(mf_.isMainFrame());
        mf_.setTitle(mf_.getTitle());
        mf_.setIconImage(ig_);
        assertEq(0,mf_.getWindowListenersDef().size());
        assertEq(0,mf_.getWindowListeners().size());
        assertEq("",mf_.getLanguageKey());
        mf_.setFocusableWindowState(true);
        MockSoundRecord sound_ = (MockSoundRecord) mf_.getFrames().newSoundPattern();
        MockWithAdvActionSample advAct_ = new MockWithAdvActionSample();
        assertFalse(advAct_.isAct());
        new MockAdvAction(0,advAct_).action(null,"");
        assertTrue(advAct_.isAct());
        ab_.newAbsSlider();
        ab_.newAbsSlider(0);
        ab_.newAbsSlider(0,0);
        ab_.newAbsSlider(0,0,0);
        ab_.newAbsSlider(0,0,0,0);
        ab_.newTextArea();
        ab_.newTextArea(0,0);
        ab_.newTextArea("");
        ab_.newTextArea("",0,0);
        ab_.newTextField();
        ab_.newTextField(0);
        ab_.newTextField("");
        ab_.newTextField("",0);
        ab_.newAbsScrollPane(ab_.newAbsScrollPane());
        ab_.newAbsScrollPane(new MockMetaLabel());
        ab_.newRadioButton();
        ab_.newRadioButton("");
        ab_.newRadioButton("",true);
        ab_.newGrid(1,1,0,0);
        ab_.newAbsTabbedPane();
        ab_.newTableGui();
        AbsCustCheckBox coch_ = ab_.newCustCheckBox("", true);
        assertEq(0,new MockMouseCoords(0,0).getXcoord());
        assertEq(0,new MockMouseCoords(0,0).getYcoord());
        AbstractInterceptorStdCaller int_ = pr_.getInterceptor().newInterceptorStdCaller("");
        assertTrue(int_.exitAfterCallInt(new MockInitializer(),null,null));
        assertTrue(int_.stop(new MockInitializer(),null,null));
        assertTrue(int_.invoke(new MockStdCaller(),null,null,null,null,null).getValue().getStruct().sameReference(NullStruct.NULL_VALUE));
        pr_.getExcludedFolders();
        pr_.getZipFact();
        pr_.light();
        pr_.getHomePath();
        pr_.getCounts();
        pr_.getConfirmDialogAns();
        pr_.getConfirmDialogText();
        pr_.getFileOpenDialogInt();
        pr_.getFileSaveDialogInt();
        pr_.getFolderOpenDialogInt();
        pr_.getFrames();
        pr_.getValidator();
        pr_.getTmpUserFolder();
        assertFalse(sound_.supported(0,0,0,!coch_.isSelected(),false));
        assertTrue(sound_.supported(0,0,0,coch_.isSelected(),false));
        sound_.update();
        sound_.appendHeader();
        sound_.append(NumberUtil.wrapIntArray(1,5));
        sound_.stop();
        assertEq(0,pr_.getThreadFactory().nanos());
        AbsClipStream clip_ = pr_.openClip(sound_.recordSong());
        assertEq(15,clip_.getMicrosecondLength());
        sound_.restore();
        assertFalse(sound_.isActive());
        sound_.recordSongInFile("");
        pr_.getThreadFactory().sleep(1);
        mf_.dispatchExit();
        assertFalse(mf_.isVisible());
    }
}
