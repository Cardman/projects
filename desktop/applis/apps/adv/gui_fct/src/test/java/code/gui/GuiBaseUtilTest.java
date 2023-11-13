package code.gui;

import code.gui.events.*;
import code.gui.initialize.*;
import code.mock.*;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.ConcreteInteger;
import code.util.*;
import code.util.comparators.NaturalComparator;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import org.junit.Test;

public final class GuiBaseUtilTest extends EquallableGuiFctUtil {

    @Test
    public void eq1() {
        MockImage img_ = new MockImage(new int[2][3]);
        MockImage img2_ = new MockImage(new int[2][3]);
        assertTrue(GuiBaseUtil.eq(img_,img2_));
    }
    @Test
    public void eq2() {
        MockImage img_ = new MockImage(new int[2][3]);
        MockImage img2_ = new MockImage(new int[2][3]);
        img_.setRGB(1,2,29);
        assertFalse(GuiBaseUtil.eq(img_,img2_));
    }
    @Test
    public void eq3() {
        MockImage img_ = new MockImage(new int[2][3]);
        MockImage img2_ = new MockImage(new int[2][4]);
        assertFalse(GuiBaseUtil.eq(img_,img2_));
    }
    @Test
    public void eq4() {
        MockImage img_ = new MockImage(new int[2][3]);
        MockImage img2_ = new MockImage(new int[4][3]);
        assertFalse(GuiBaseUtil.eq(img_,img2_));
    }
    @Test
    public void pack() {
        MockPanel mockPanel_ = new MockPanel(MockLayout.GRID);
        mockPanel_.add(new MockPlainLabel("_"));
        MockPanel sub_ = new MockPanel(MockLayout.GRID);
        sub_.add(new MockPlainLabel("-"));
        sub_.add(new MockScrollPane(new MockTextArea("_")));
        mockPanel_.add(sub_);
        GuiBaseUtil.recalculate(mockPanel_);
        CustList<MockCustComponent> accessible_ = mockPanel_.getAccessible();
        assertEq(2, accessible_.size());
        assertTrue(accessible_.get(0) instanceof MockPlainLabel);
        assertTrue(accessible_.get(1) instanceof MockPanel);
        CustList<MockCustComponent> suAcc_ = accessible_.get(1).getAccessible();
        assertEq(2, suAcc_.size());
        assertTrue(suAcc_.get(0) instanceof MockPlainLabel);
        assertTrue(suAcc_.get(1) instanceof MockScrollPane);
        CustList<MockCustComponent> suSuAcc_ = suAcc_.get(1).getAccessible();
        assertEq(1, suSuAcc_.size());
        assertTrue(suSuAcc_.get(0) instanceof MockTextArea);
    }
    @Test
    public void paques() {
        MockProgramInfosSecSample i_ = init();
        AbsCommonFrame c_ = i_.getFrameFactory().newCommonFrame("", i_, null);
        PackingWindowAfter.pack(c_);
        c_.setVisible(true);
        assertTrue(c_.isVisible());
    }
    @Test
    public void indexOf() {
        assertEq(-1, GuiBaseUtil.indexOf(new CustList<AbsCustComponent>(),null));
    }

    @Test
    public void prep() {
        AbsPreparedLabel l_ = GuiBaseUtil.prep(new MockImageFactory());
        assertEq(1, l_.getWidth());
        assertEq(1, l_.getHeight());
    }
    @Test
    public void invokeLater() {
        MockProgramInfosSecSample init_ = init();
        MockRunnable r_ = new MockRunnable();
        AbsCompoFactory ab_ = init_.getCompoFactory();
        GuiBaseUtil.invokeLater(r_,init_);
        assertFalse(r_.isStarted());
        ((MockCompoFactory)ab_).invoke();
        assertTrue(r_.isStarted());
    }
    @Test
    public void setOrient1() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsSlider s_ = init_.newAbsSlider();
        GuiBaseUtil.setOrient(s_,GuiConstants.HORIZONTAL);
        assertEq(GuiConstants.HORIZONTAL,s_.getOrientation());
    }
    @Test
    public void setOrient2() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsSlider s_ = init_.newAbsSlider();
        GuiBaseUtil.setOrient(s_,GuiConstants.VERTICAL);
        assertEq(GuiConstants.VERTICAL,s_.getOrientation());
    }
    @Test
    public void setOrientPr1() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsProgressBar s_ = init_.newAbsProgressBar();
        GuiBaseUtil.setHorizProg(s_,true);
        assertTrue(s_.isHorizontal());
    }
    @Test
    public void setOrientPr2() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsProgressBar s_ = init_.newAbsProgressBar();
        GuiBaseUtil.setHorizProg(s_,false);
        assertFalse(s_.isHorizontal());
    }
    @Test
    public void setSelectTable1() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsTableGui s_ = init_.newTableGui("_");
        GuiBaseUtil.setSelectTable(s_,true);
        assertTrue(s_.isMultiSelect());
    }
    @Test
    public void setSelectTable2() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsTableGui s_ = init_.newTableGui("_");
        GuiBaseUtil.setSelectTable(s_,false);
        assertFalse(s_.isMultiSelect());
    }
    @Test
    public void formatDate1() {
        assertEq("0",GuiBaseUtil.getTime(init().getThreadFactory(),""));
    }
    @Test
    public void formatDate2() {
        assertEq("0",GuiBaseUtil.getTimeText(init().getThreadFactory(),"","",""));
    }
    @Test
    public void removeAllListeners1() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new CrossClosingDialogListEvent(null,null));
        GuiBaseUtil.removeAllListeners(fr_.getCommonFrame());
        assertEq(0,fr_.getCommonFrame().getWindowListenersDef().size());
    }
    @Test
    public void tryExit1() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        MockSampleFrame fr2_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new CrossClosingDialogListEvent(null,null));
        fr_.getCommonFrame().setVisible(true);
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
        GuiBaseUtil.choose("",pr_,fr_, m_);
        GuiBaseUtil.choose("",pr_,fr2_, m_);
        GuiBaseUtil.tryExit(fr_.getCommonFrame());
        assertEq(1,fr_.getCommonFrame().getWindowListenersDef().size());
    }
    @Test
    public void tryExit2() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new CrossClosingDialogListEvent(null,null));
        fr_.getCommonFrame().setVisible(false);
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
        GuiBaseUtil.choose("",pr_,fr_,m_);
        GuiBaseUtil.tryExit(fr_.getCommonFrame());
        assertEq(0,fr_.getCommonFrame().getWindowListenersDef().size());
    }
    @Test
    public void tryToReopen1() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new CrossClosingDialogListEvent(null,null));
        fr_.getCommonFrame().setVisible(true);
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
        GuiBaseUtil.choose("",pr_,fr_,m_);
        GuiBaseUtil.choose("",fr_,m_);
        assertFalse(GuiBaseUtil.changeStaticLanguage("",pr_,m_));
        assertTrue(GuiBaseUtil.changeStaticLanguage("_",pr_,m_));
        GuiBaseUtil.showDialogError(0,fr_.getCommonFrame());
        assertTrue(GuiBaseUtil.tryToReopen("",pr_));
        GuiBaseUtil.getStaticLanguage(new MockSetterLanguage());
        GuiBaseUtil.setLanguageDialog(fr_,new MockWithDialogs(pr_),"");
    }
    @Test
    public void tryToReopen2() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new CrossClosingDialogListEvent(null,null));
        fr_.getCommonFrame().setVisible(true);
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
        GuiBaseUtil.choose("",pr_,fr_,m_);
        GuiBaseUtil.showDialogError(0, fr_.getCommonFrame());
        assertFalse(GuiBaseUtil.tryToReopen("_",pr_));
    }
//    @Test
//    public void canChangeLanguageAll1() {
//        MockProgramInfosSecSample pr_ = init();
//        MockSampleFrame fr_ = new MockSampleFrame(pr_);
//        fr_.getCommonFrame().addWindowListener(new CrossClosingDialogListEvent(null,null));
//        fr_.getCommonFrame().setVisible(true);
//        fr_.setChangeable(true);
//        StringMap<String> m_ = new StringMap<String>();
//        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
//        GuiBaseUtil.choose("",pr_,fr_,m_);
//        assertTrue(GuiBaseUtil.canChangeLanguageAll(pr_));
//    }
//    @Test
//    public void canChangeLanguageAll2() {
//        MockProgramInfosSecSample pr_ = init();
//        MockSampleFrame fr_ = new MockSampleFrame(pr_);
//        fr_.getCommonFrame().addWindowListener(new CrossClosingDialogListEvent(null,null));
//        fr_.getCommonFrame().setVisible(true);
//        fr_.setChangeable(false);
//        StringMap<String> m_ = new StringMap<String>();
//        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
//        GuiBaseUtil.choose("",pr_,fr_,m_);
//        assertFalse(GuiBaseUtil.canChangeLanguageAll(pr_));
//    }
    @Test
    public void eventQuit() {
        MockSampleFrame m_ = new MockSampleFrame(init());
        new QuittingEvent(m_).windowClosing();
        assertFalse(m_.getCommonFrame().isVisible());
    }
    @Test
    public void actions() {
        MockTextPane t_ = (MockTextPane) init().getCompoFactory().newTextPane();
        MockWithAdvActionSample e_ = new MockWithAdvActionSample();
        t_.registerKeyboardAction(new MockAdvAbstractAction(new MockAdvAction(0, e_)),0,0);
        assertEq(1,GuiBaseUtil.getActions(t_).size());
        assertEq(1,GuiBaseUtil.getKeysAction(t_).size());
        ((MockAdvAbstractAction)GuiBaseUtil.getAction(t_,0,0)).action(null,null);
        assertTrue(e_.isAct());
    }
    @Test
    public void register1() {
        MockSoundRecord m_ = new MockSoundRecord();
        m_.getState().set(false);
        GuiBaseUtil.recordSong(m_);
        assertEq(0, m_.millis());
    }
    @Test
    public void register2() {
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1,2,3,4));
        m_.getState().set(true);
        GuiBaseUtil.recordSong(m_);
        assertEq(4, m_.millis());
    }
    @Test
    public void playBack1() {
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1,2,3,4));
        m_.getState().set(true);
        GuiBaseUtil.recordSong(m_);
        assertFalse(launch(m_));
    }
    @Test
    public void playBack2() {
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1,2,3,4,5,6,7,8));
        m_.getState().set(true);
        GuiBaseUtil.recordSong(m_);
        assertTrue(launch(m_));
    }
    @Test
    public void playBack3() {
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16));
        m_.getState().set(true);
        GuiBaseUtil.recordSong(m_);
        assertTrue(launch(m_));
    }
    @Test
    public void playBack14() {
        MockSoundRecord m_ = new MockSoundRecord();
        m_.append(NumberUtil.wrapIntArray());
        m_.getState().set(true);
        GuiBaseUtil.recordSong(m_);
        assertFalse(launch(m_));
    }
    @Test
    public void gr() {
        MockProgramInfosSecSample pr_ = init();
        pr_.getCounts().addEntry("",new ConcreteInteger());
        GroupFrame s_ = new SampleGroupFrame("",pr_,new StringMap<String>());
        s_.setImageIconFrame(null);
        s_.getFolderOpenDialogInt();
        s_.getFileOpenDialogInt();
        s_.getFileSaveDialogInt();
        s_.getConfirmDialogText();
        s_.getConfirmDialogAns();
        s_.getLanguageDialog();
        s_.getValidator();
        s_.getFileCoreStream();
        s_.getStreams();
        s_.getThreadFactory();
        s_.getCompoFactory();
        s_.getImageFactory();
        s_.getGenerator();
        s_.setContentPane(new MockPanel(MockLayout.ABSOLUTE));
        s_.revalidateFrame();
        s_.pack();
        s_.requestFocus();
        ((AbsOpenQuit)s_).changeLanguage("");
        ((AbsOpenQuit)s_).quit();
        s_.dispatchExit();
//        s_.dispose();
        s_.setJMenuBar(new MockMenuBar());
        s_.getPane().add(new MockPlainLabel(""));
        s_.getJMenuBar().add(new MockMenu(""));
        s_.setFocusable(true);
        s_.setFocusable(false);
        s_.setVisible(true);
        s_.setVisible(false);
        s_.setFocusableWindowState(true);
        s_.setFocusableWindowState(false);
        s_.addWindowListener(new QuittingEvent(((AbsOpenQuit)s_)));
        s_.setAccessFile("");
        GuiBaseUtil.trEx((AbsOpenQuit)s_);
        s_.getLocation();
        assertEq("",s_.getAccessFile());
        assertEq(0,s_.getMessages().size());
        s_.setTitle("frime");
        assertEq("frime",s_.getTitle());
    }

    @Test
    public void clock() {
        MockProgramInfosSecSample pr_ = init();
        Clock c_ = new Clock(pr_);
        ThreadInvoker.invokeNow(pr_.getThreadFactory(),new UpdateTimeEvent(pr_.getThreadFactory(),c_),pr_);
        ((MockCompoFactory)pr_.getCompoFactory()).invoke();
        LabelButtonUtil.paintDefaultLabel(new MockImage(new int[1][1]),"",1,1,1,1,1);
        assertFalse(StringUtil.nullToEmpty(((MockTextField)c_.getComponent()).getText()+"_"+Clock.getDateTimeText(pr_.getThreadFactory())).isEmpty());
    }

    @Test
    public void topLeft() {
        TopLeftFrame t_ = new TopLeftFrame();
        t_.setHeight(1);
        t_.setWidth(2);
        assertEq(1,t_.getHeight());
        assertEq(2,t_.getWidth());
    }

    @Test
    public void addTreeEvts() {
        MockTreeGui m_ = new MockTreeGui(new MockMutableTreeNode(""));
        CustList<AbsShortListTree> ls_ = new CustList<AbsShortListTree>();
        ls_.add(new MockShortListTree(0,new MockCommonFrameTreeSample(init())));
        GuiBaseUtil.addTreeSelectionListeners(m_, ls_);
        assertEq(1,m_.getTreeSelectionListeners().size());
    }

    @Test
    public void removeTreeEvts() {
        MockTreeGui m_ = new MockTreeGui(new MockMutableTreeNode(""));
        m_.addTreeSelectionListener(new MockShortListTree(0,new MockCommonFrameTreeSample(init())));
        GuiBaseUtil.removeTreeSelectionListeners(m_);
        assertEq(0,m_.getTreeSelectionListeners().size());
    }

    @Test
    public void removeButtonEvts() {
        MockPlainButton m_ = new MockPlainButton();
        m_.addActionListener(new MockAction(0,new MockWithActionSample()));
        GuiBaseUtil.removeActionListeners(m_);
        assertEq(0,m_.getActionListeners().size());
    }
    @Test
    public void crud1() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm<String, Integer> c_ = crud(m_, new StringList("ONE"));
        assertEq(1,c_.getList().size());
        assertEq("ONE",c_.getList().getKey(0));
        assertEq(15,c_.getList().getValue(0));
        assertFalse(c_.isVisibleSingle());
        assertEq(4,c_.getButtons().getComponentCount());
    }
    @Test
    public void crud2() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm<String, Integer> c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        assertTrue(c_.isVisibleSingle());
        c_.getGeneKey().value("TWO");
        c_.getGeneValue().value(20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        assertEq(2,c_.getList().size());
        assertEq("ONE",c_.getList().getKey(0));
        assertEq(15,c_.getList().getValue(0));
        assertEq("TWO",c_.getList().getKey(1));
        assertEq(20,c_.getList().getValue(1));
    }
    @Test
    public void crud3() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm<String, Integer> c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        c_.getGeneKey().value("TWO");
        c_.getGeneValue().value(20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getGeneKey().value("ONE");
        c_.getGeneValue().value(16);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        assertEq(2,c_.getList().size());
        assertEq("ONE",c_.getList().getKey(0));
        assertEq(16,c_.getList().getValue(0));
        assertEq("TWO",c_.getList().getKey(1));
        assertEq(20,c_.getList().getValue(1));
    }
    @Test
    public void crud4() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm<String, Integer> c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        c_.getGeneKey().value("TWO");
        c_.getGeneValue().value(20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getValidRemove().getActionListeners().get(0).action();
        assertEq(1,c_.getList().size());
        assertEq("TWO",c_.getList().getKey(0));
        assertEq(20,c_.getList().getValue(0));
    }
    @Test
    public void crud5() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm<String, Integer> c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        c_.getGeneKey().value("TWO");
        c_.getGeneValue().value(20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getGeneKey().value("ONE");
        c_.getGeneValue().value(16);
        c_.getCancel().getActionListeners().get(0).action();
        assertEq(2,c_.getList().size());
        assertEq("ONE",c_.getList().getKey(0));
        assertEq(15,c_.getList().getValue(0));
        assertEq("TWO",c_.getList().getKey(1));
        assertEq(20,c_.getList().getValue(1));
    }
    @Test
    public void quit() {
        SampleGroupFrame fr_ = new SampleGroupFrame("", init(), new StringMap<String>());
        new QuitEvent(fr_).action();
        assertEq(0,fr_.getCommonFrame().getWindowListenersDef().size());
        new ClosingChildFrameEvent(fr_).windowClosing();
        assertFalse(fr_.getCommonFrame().isVisible());
    }
    private CrudGeneForm<String, Integer> crud(StringMap<Integer> _map, StringList _dico) {
        MockProgramInfosSecSample pr_ = init();
        AbsCommonFrame f_ = pr_.getFrameFactory().newCommonFrame("",pr_,null);
        CrudGeneForm<String, Integer> c_ = new CrudGeneForm<String, Integer>(pr_, new NaturalComparator());
        GuiBaseUtil.initStringMapInt(f_, c_, _map, _dico,new DefValidateText());
        return c_;
    }

    private boolean launch(MockSoundRecord _pl) {
        return GuiBaseUtil.launch(_pl.build());
    }
}
