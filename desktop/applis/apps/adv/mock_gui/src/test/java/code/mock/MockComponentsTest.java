package code.mock;

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
import code.gui.initialize.AbsLightFrameFactory;
import code.stream.*;
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
        MockDialogSample fr2_ = new MockDialogSample(new MockCloseableDialog(fr_),init_);
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
        EnabledMenu m_ = ab_.newMenu();
        b_.add(m_);
        m_.addMenuItem(ab_.newMenuItem());
        m_.addMenuItem(ab_.newCheckBoxMenuItem());
        m_.addMenuItem(ab_.newMenuItem("_"));
        m_.addMenuItem(ab_.newCheckBoxMenuItem("_"));
        assertEq(1,b_.getMenuCount());
        assertEq("",m_.getText());
        assertEq(4,m_.getItemCount());
        assertEq("",((EnabledMenu)m_.getItems().get(0)).getText());
        assertEq("",((EnabledMenu)m_.getItems().get(1)).getText());
        assertEq("_",((EnabledMenu)m_.getItems().get(2)).getText());
        assertEq("_",((EnabledMenu)m_.getItems().get(3)).getText());
    }
    @Test
    public void c9() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        EnabledMenu m_ = ab_.newMenu("_");
        b_.add(m_);
        m_.addMenuItem(ab_.newMenu());
        m_.addMenuItem(ab_.newMenu("_"));
        assertEq(1,b_.getMenuCount());
        assertEq("_",m_.getText());
        assertEq(2,m_.getItemCount());
        assertEq("",((EnabledMenu)m_.getItems().get(0)).getText());
        assertEq("_",((EnabledMenu)m_.getItems().get(1)).getText());
    }
    @Test
    public void c10() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        EnabledMenu m_ = ab_.newMenu("_");
        b_.add(m_);
        b_.remove(m_);
        assertEq(0,b_.getMenuCount());
    }
    @Test
    public void c11() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        EnabledMenu m_ = ab_.newMenu();
        b_.add(m_);
        m_.addMenuItem(ab_.newMenu());
        m_.addMenuItem(ab_.newMenuItem());
        m_.addMenuItem(ab_.newCheckBoxMenuItem());
        m_.removeMenuItem((EnabledMenu) m_.getItems().last());
        m_.removeMenuItem((EnabledMenu) m_.getItems().last());
        m_.removeMenuItem((EnabledMenu) m_.getItems().last());
        assertEq(0,m_.getItemCount());
        MockSeparator menu_ = new MockSeparator();
        menu_.orientation(menu_.orientation());
        m_.addMenuItem(menu_);
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
        EnabledMenu m_ = ab_.newMenu();
        b_.add(m_);
        EnabledMenu i_ = ab_.newMenuItem();
        i_.setEnabledMenu(false);
        i_.setText("_");
        assertFalse(i_.isEnabled());
        assertEq("_",i_.getText());
        assertNull(i_.getParentMenu());
        i_.setAccelerator(0,0);
        MockAction l_ = new MockAction(0, new MockWithActionSample());
        i_.addActionListener(l_);
        MockAdvAction adv_ = new MockAdvAction(0, new MockWithAdvActionSample());
        i_.addActionListener(adv_);
        assertEq(1, ((MockMenuItem)i_).getActionListeners().size());
        assertEq(1, ((MockMenuItem)i_).getAdvActionListeners().size());
        i_.removeActionListener(l_);
        i_.removeActionListener(adv_);
        assertEq(0, ((MockMenuItem)i_).getActionListeners().size());
        assertEq(0, ((MockMenuItem)i_).getAdvActionListeners().size());
    }
    @Test
    public void c13() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        AbsMenuBar b_ = ab_.newMenuBar();
        EnabledMenu m_ = ab_.newMenu();
        b_.add(m_);
        EnabledMenu i_ = ab_.newCheckBoxMenuItem();
        i_.setSelected(true);
        assertTrue(i_.isSelected());
        i_.setEnabledMenu(false);
        i_.setText("_");
        assertFalse(i_.isEnabled());
        assertEq("_",i_.getText());
        assertNull(i_.getParentMenu());
        i_.setAccelerator(0,0);
        i_.addActionListener(new MockAction(0, new MockWithActionSample()));
        i_.addActionListener(new MockAdvAction(0,new MockWithAdvActionSample()));
        assertEq(1, ((MockCheckBoxMenuItem)i_).getActionListeners().size());
        assertEq(1, ((MockCheckBoxMenuItem)i_).getAdvActionListeners().size());
    }
    @Test
    public void c13_() {
        AbsCompoFactory ab_ = init().getCompoFactory();
        EnabledMenu m_ = ab_.newMenu();
        AbsSeparator s_ = ab_.newSep();
        m_.addMenuItem(s_);
        assertEq(1,m_.getItemCount());
        m_.removeMenuItem(s_);
        assertEq(0,m_.getItemCount());
    }
    @Test
    public void c14() {
        MockProgramInfosSample pr_ = init();
        AbsCompoFactory ab_ = pr_.getCompoFactory();
        AbsPopupMenu b_ = ab_.newAbsPopupMenu();
        EnabledMenu m_ = ab_.newMenu();
        b_.add(m_);
        EnabledMenu i_ = ab_.newMenuItem();
        b_.add(i_);
        AbsCustCheckBox c_ = ab_.newCustCheckBox();
        b_.add(c_);
        EnabledMenu o_ = ab_.newCheckBoxMenuItem();
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
//        g_.repaintLabel(pr_.getImageFactory());
        b_.add(g_);
        AbsButton but_ = ab_.newPlainButton();
        but_.setText("");
        b_.add(but_);
        b_.add(ab_.newPlainButton("_"));
        assertEq("",but_.getText());
        MockAction act_ = new MockAction(0, new MockWithActionSample());
        but_.addActionListener(act_);
        MockAdvAction advAc_ = new MockAdvAction(0, new MockWithAdvActionSample());
        but_.addActionListener(advAc_);
        assertEq(1, ((MockPlainButton)but_).getActionListeners().size());
        assertEq(1, ((MockPlainButton)but_).getAdvActionListeners().size());
        but_.removeActionListener(act_);
        but_.removeActionListener(advAc_);
        assertEq(0, ((MockPlainButton)but_).getActionListeners().size());
        assertEq(0, ((MockPlainButton)but_).getAdvActionListeners().size());
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
        MockWithActionSample s_ = new MockWithActionSample();
        MockAction acList_ = new MockAction(1, s_);
        ch_.addActionListener(acList_);
        ch_.setSelected(true);
        assertTrue(ch_.isSelected());
        assertEq(0,s_.getNb());
        ch_.setSelected(false);
        assertFalse(ch_.isSelected());
        assertEq(1,s_.getNb());
        s_.setNb(0);
        ch_.setSelected(false);
        assertFalse(ch_.isSelected());
        assertEq(0,s_.getNb());
        ch_.setSelected(true);
        assertTrue(ch_.isSelected());
        assertEq(1,s_.getNb());
        ch_.removeActionListener(acList_);
        assertEq(0,ch_.getActionListeners().size());
        MockPaintableLabelAbs paint_ = (MockPaintableLabelAbs) ab_.newAbsPaintableLabel();
        paint_.setEmptyIcon();
        assertTrue(paint_.isEmpty());
        paint_.setIcon(pr_.getImageFactory(),pr_.getImageFactory().newImageArgb(1,1));
        assertFalse(paint_.isEmpty());
        paint_.requestFocusInWindow();
        paint_.setHorizontalAlignment(2);
        paint_.setVerticalAlignment(2);
        assertEq(2,paint_.getHorizontalAlignment());
        assertEq(2,paint_.getVerticalAlignment());
        AbsButton img_ = ab_.newImgButton(pr_.getImageFactory().newImageArgb(1, 1));
        img_.addActionListener(new MockAction(0,new MockWithActionSample()));
        assertEq(1,((MockPlainButton)img_).getActionListeners().size());
        AbstractImage ig_ = pr_.getImageFactory().newImageArgb(1, 1);
        pr_.getImageFactory().setIcon(new MockPreparedLabel(),ig_);
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
        pr_.getSetterLanguage().getLanguage();
        AbsFrameFactory frFact_ = pr_.getFrameFactory();
        AbsDialog base_ = frFact_.newDialog();
        AbsDialog adv_ = frFact_.newDialog(new MockCloseableDialog(base_));
        AbsDialog intDial_ = frFact_.newDialog();
        AbsDialog after_ = frFact_.newDialog(new MockCloseableDialog(intDial_));
        after_.closeWindow();
        after_.closeWindow();
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
        MockWindowListener mw_ = new MockWindowListener();
        mw_.windowActivated();
        mw_.windowDeactivated();
        mw_.windowIconified();
        mw_.windowDeiconified();
        mw_.windowClosed();
        mw_.windowClosing();
        mw_.windowOpened();
        assertEq(1,NumberUtil.signum(mw_.getState()));
        mf_.addWindowListener((AbsWindowListener) mw_);
        mf_.addWindowListener((AbsWindowListenerClosing) mw_);
        mf_.removeWindowListener((AbsWindowListener) mw_);
        mf_.removeWindowListener((AbsWindowListenerClosing) mw_);
        mf_.setLocation(0,0);
//        mf_.setDefaultCloseOperation(0);
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
//        ab_.newAbsScrollPane(new MockMetaLabel());
        ab_.newRadioButton();
        ab_.newRadioButton("");
        ab_.newRadioButton("",true);
        ab_.newGrid(1,1,0,0);
        ab_.newAbsTabbedPane();
        ab_.newTableGui();
        AbsCustCheckBox coch_ = ab_.newCustCheckBox("", true);
        assertEq(0,new MockMouseCoords(0,0).getXcoord());
        assertEq(0,new MockMouseCoords(0,0).getYcoord());
        pr_.getExcludedFolders();
        pr_.getZipFact();
        pr_.light();
        pr_.getHomePath();
        pr_.getCounts();
        pr_.getButtons();
        pr_.getConfirmDialogAns();
        pr_.getConfirmDialogText();
        pr_.getFileOpenDialogInt();
        pr_.getFileSaveDialogInt();
        pr_.getFolderOpenDialogInt();
        pr_.getFrames();
        pr_.getValidator();
        pr_.getTmpUserFolder();
        assertTrue(sound_.supported(0,0,0,coch_.isSelected(),false));
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
//        sound_.recordSongInFile("");
        pr_.getThreadFactory().sleep(1);
        mf_.dispatchExit();
        mf_.setVisible(false);
        assertFalse(mf_.isVisible());
        new MockDialogSample(pr_).setLocationRelativeTo((AbsOtherDialog)adv_);
        new MockDialogSample(pr_).setLocationRelativeTo((AbsOtherFrame)new MockCommonFrameSample(pr_));
    }
    @Test
    public void c15() {
        MockProgramInfosSample init_ = init();
        AbsCompoFactory ab_ = init_.getCompoFactory();
        assertEq(10,ab_.stringWidth(new MetaFont("",0,2),"hello"));
        assertEq(2,ab_.heightFont(new MetaFont("",0,2)));
    }
    @Test
    public void c16() {
        MockProgramInfosSample init_ = init();
        MockRunnable r_ = new MockRunnable();
        AbsCompoFactory ab_ = init_.getCompoFactory();
        ab_.invokeNow(r_);
        assertTrue(r_.isStarted());
        AbsLightFrameFactory li_ = init_.getLightFrameFactory();
        li_.copy("_");
        assertEq("_", li_.paste());
    }
    @Test
    public void c17(){
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1));
        m_.getState().set(true);
        assertTrue(m_.getState().get());
        assertEq(1,m_.readBytes());
        m_.writeBytes();
        assertEq(1, m_.millis());
    }
    @Test
    public void c18(){
        MockSoundRecord m_ = new MockSoundRecord();
        m_.getState().set(true);
        assertTrue(m_.getState().get());
        m_.append(NumberUtil.wrapIntArray(1));
        m_.readBytes();
        assertEq(-1,m_.readBytes());
        m_.getState().set(false);
        assertFalse(m_.getState().get());
    }
    @Test
    public void c19(){
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1));
        m_.writeBytes();
        AbsPlayBack p_ = m_.build();
        p_.getState().set(true);
        assertTrue(p_.getState().get());
        assertEq(1,p_.readBytes());
        p_.writeBytes();
    }
    @Test
    public void c20(){
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1));
        m_.writeBytes();
        AbsPlayBack p_ = m_.build();
        p_.getState().set(true);
        assertTrue(p_.getState().get());
        p_.readBytes();
        assertEq(-1,p_.readBytes());
        p_.writeBytes();
    }
    @Test
    public void c21(){
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1));
        m_.getState().set(true);
        m_.build();
        assertTrue(m_.getState().get());
    }
    @Test
    public void c22(){
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1,2,3,4));
        m_.writeBytes();
        AbsPlayBack p_ = m_.build();
        assertFalse(p_.prepare());
        assertFalse(p_.getOk().get());
    }
    @Test
    public void c23(){
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1,2,3,4,5,6,7,8));
        m_.writeBytes();
        AbsPlayBack p_ = m_.build();
        assertTrue(p_.prepare());
    }
    @Test
    public void c24(){
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16));
        m_.writeBytes();
        AbsPlayBack p_ = m_.build();
        assertTrue(p_.prepare());
        p_.remainPrep();
        p_.remain();
        p_.drain();
        p_.finish();
        assertTrue(p_.getOk().get());
    }
}
