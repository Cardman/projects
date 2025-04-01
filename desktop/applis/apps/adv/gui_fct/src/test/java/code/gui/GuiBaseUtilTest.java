package code.gui;

import code.gui.events.*;
import code.gui.files.ClosingFileFrameEvent;
import code.gui.initialize.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.*;
import code.mock.*;
import code.stream.AbsClipStream;
import code.threads.ConcreteBoolean;
import code.threads.ConcreteInteger;
import code.util.*;
import code.util.comparators.ComparatorMapValue;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.*;
import org.junit.Test;

public final class GuiBaseUtilTest extends EquallableGuiFctUtil {
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
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
//    @Test
//    public void pack() {
//        MockPanel mockPanel_ = new MockPanel(MockLayout.GRID);
//        mockPanel_.add(new MockPlainLabel("_"));
//        MockPanel sub_ = new MockPanel(MockLayout.GRID);
//        sub_.add(new MockPlainLabel("-"));
//        sub_.add(new MockScrollPane(new MockTextArea("_")));
//        mockPanel_.add(sub_);
//        GuiBaseUtil.recalculate(mockPanel_);
//        CustList<MockCustComponent> accessible_ = mockPanel_.getAccessible();
//        assertEq(2, accessible_.size());
//        assertTrue(accessible_.get(0) instanceof MockPlainLabel);
//        assertTrue(accessible_.get(1) instanceof MockPanel);
//        CustList<MockCustComponent> suAcc_ = accessible_.get(1).getAccessible();
//        assertEq(2, suAcc_.size());
//        assertTrue(suAcc_.get(0) instanceof MockPlainLabel);
//        assertTrue(suAcc_.get(1) instanceof MockScrollPane);
//        CustList<MockCustComponent> suSuAcc_ = suAcc_.get(1).getAccessible();
//        assertEq(1, suSuAcc_.size());
//        assertTrue(suSuAcc_.get(0) instanceof MockTextArea);
//    }
//    @Test
//    public void packWindow() {
//        MockPanel mockPanel_ = new MockPanel(MockLayout.GRID);
//        mockPanel_.add(new MockPlainLabel("_"));
//        MockPanel sub_ = new MockPanel(MockLayout.GRID);
//        sub_.add(new MockPlainLabel("-"));
//        sub_.add(new MockScrollPane(new MockTextArea("_")));
//        mockPanel_.add(sub_);
//        MockCommonFrameSecSample fr_ = new MockCommonFrameSecSample();
//        fr_.setContentPane(mockPanel_);
//        GuiBaseUtil.recalculateWindow(fr_);
//        CustList<MockCustComponent> accessible_ = ((MockCustComponent)fr_.getPane()).getAccessible();
//        assertEq(2, accessible_.size());
//        assertTrue(accessible_.get(0) instanceof MockPlainLabel);
//        assertTrue(accessible_.get(1) instanceof MockPanel);
//        CustList<MockCustComponent> suAcc_ = accessible_.get(1).getAccessible();
//        assertEq(2, suAcc_.size());
//        assertTrue(suAcc_.get(0) instanceof MockPlainLabel);
//        assertTrue(suAcc_.get(1) instanceof MockScrollPane);
//        CustList<MockCustComponent> suSuAcc_ = suAcc_.get(1).getAccessible();
//        assertEq(1, suSuAcc_.size());
//        assertTrue(suSuAcc_.get(0) instanceof MockTextArea);
//    }
//    @Test
//    public void packWithout() {
//        MockPanel mockPanel_ = new MockPanel(MockLayout.GRID);
//        GuiBaseUtil.recalculate(mockPanel_);
//        CustList<MockCustComponent> accessible_ = mockPanel_.getAccessible();
//        assertEq(0, accessible_.size());
//    }
    @Test
    public void paques() {
        MockProgramInfosSecSample i_ = init();
        AbsCommonFrame c_ = i_.getFrameFactory().newCommonFrame();
        PackingWindowAfter.pack(c_, i_.getCompoFactory());
        c_.setVisible(true);
        assertTrue(c_.isVisible());
    }
//    @Test
//    public void indexOf() {
//        assertEq(-1, GuiBaseUtil.indexOf(new CustList<AbsCustComponent>(),null));
//    }

    @Test
    public void prep() {
        AbsPreparedLabel l_ = GuiBaseUtil.prep(new MockImageFactory(), new MockCompoFactory());
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
        fr_.getCommonFrame().addWindowListener(new ClosingFileFrameEvent(fr_.getCommonFrame(),new ConcreteBoolean()));
        GuiBaseUtil.removeAllListeners(fr_.getCommonFrame());
        assertEq(0,fr_.getCommonFrame().getWindowListenersDef().size());
    }
    @Test
    public void tryExit1() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        MockSampleFrame fr2_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new ClosingFileFrameEvent(fr_.getCommonFrame(),new ConcreteBoolean()));
        fr_.getCommonFrame().setVisible(true);
//        StringMap<String> m_ = new StringMap<String>();
//        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
        GuiBaseUtil.choose(pr_,fr_);
        GuiBaseUtil.choose(pr_,fr2_);
        GuiBaseUtil.tryExit(fr_.getCommonFrame(), pr_);
        assertEq(1,fr_.getCommonFrame().getWindowListenersDef().size());
    }
    @Test
    public void tryExit2() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new ClosingFileFrameEvent(fr_.getCommonFrame(),new ConcreteBoolean()));
        fr_.getCommonFrame().setVisible(false);
//        StringMap<String> m_ = new StringMap<String>();
//        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
        GuiBaseUtil.choose(pr_,fr_);
        GuiBaseUtil.tryExit(fr_.getCommonFrame(), pr_);
        assertEq(0,fr_.getCommonFrame().getWindowListenersDef().size());
    }
    @Test
    public void tryToReopen1() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new ClosingFileFrameEvent(fr_.getCommonFrame(),new ConcreteBoolean()));
        fr_.getCommonFrame().setVisible(true);
//        StringMap<String> m_ = new StringMap<String>();
//        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
        GuiBaseUtil.choose(pr_,fr_);
        GuiBaseUtil.choose(fr_, pr_);
        assertFalse(GuiBaseUtil.changeStaticLanguage("",pr_));
        assertTrue(GuiBaseUtil.changeStaticLanguage("_",pr_));
//        GuiBaseUtil.showDialogError(0,fr_.getCommonFrame());
        assertTrue(GuiBaseUtil.tryToReopen("",pr_));
        GuiBaseUtil.getStaticLanguage(new MockSetterLanguage());
//        GuiBaseUtil.setLanguageDialog(fr_,new MockWithDialogs(pr_),"");
    }
    @Test
    public void tryToReopen2() {
        MockProgramInfosSecSample pr_ = init();
        MockSampleFrame fr_ = new MockSampleFrame(pr_);
        fr_.getCommonFrame().addWindowListener(new ClosingFileFrameEvent(fr_.getCommonFrame(),new ConcreteBoolean()));
        fr_.getCommonFrame().setVisible(true);
//        StringMap<String> m_ = new StringMap<String>();
//        m_.addEntry(ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, "", GuiBaseUtil.ACCESS),"");
        GuiBaseUtil.choose(pr_,fr_);
//        GuiBaseUtil.showDialogError(0, fr_.getCommonFrame());
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
//        pr_.getCounts().addEntry("",new ConcreteInteger());
        GroupFrame s_ = new SampleGroupFrame(pr_);
        s_.setImageIconFrame(null);
//        s_.getLanguageDialog();
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
//        s_.setAccessFile("");
        GuiBaseUtil.trEx((AbsOpenQuit)s_, pr_);
        s_.getLocation();
//        assertEq("",s_.getAccessFile());
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
    public void addListEvts() {
        MockTableGui m_ = new MockTableGui();
        CustList<AbsListSelectionListener> ls_ = new CustList<AbsListSelectionListener>();
        ls_.add(new MockListSelectionListener());
        GuiBaseUtil.addListSelectionListeners(m_, ls_);
        assertEq(1,m_.getListSelectionListeners().size());
    }

    @Test
    public void removeListEvts() {
        MockTableGui m_ = new MockTableGui();
        m_.addListSelectionListener(new MockListSelectionListener());
        GuiBaseUtil.removeListSelectionListeners(m_);
        assertEq(0,m_.getListSelectionListeners().size());
    }

    @Test
    public void setSelectedIndices() {
        MockTableGui m_ = new MockTableGui();
        m_.setRowCount(8);
        GuiBaseUtil.setSelectedIndices(m_,new int[]{3,5,9});
        int[] rows_ = m_.getSelectedRows();
        assertEq(2, rows_.length);
        assertEq(3, rows_[0]);
        assertEq(5, rows_[1]);
    }
    @Test
    public void tabbed1() {
        MockTabbedPane m_ = new MockTabbedPane();
        m_.addChangeListener(new MockChangeListener(new MockWithChangeListenerSample(init(),""),0));
        assertTrue(GuiBaseUtil.stateChanged(m_,0,1));
    }
    @Test
    public void tabbed2() {
        MockTabbedPane m_ = new MockTabbedPane();
        m_.addChangeListener(new MockChangeListener(new MockWithChangeListenerSample(init(),""),0));
        assertFalse(GuiBaseUtil.stateChanged(m_,1,1));
    }
    @Test
    public void crud1() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm c_ = crud(m_, new StringList("ONE"));
        assertEq(1,c_.getList().size());
        StringMap<Integer> map_ = GuiBaseUtil.retrieve(c_);
        assertEq("ONE",map_.getKey(0));
        assertEq(15,map_.getValue(0));
        assertFalse(c_.isVisibleSingle());
        assertEq(4,c_.getButtons().getComponentCount());
    }
    @Test
    public void crud2() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        assertTrue(c_.isVisibleSingle());
        update(c_,"TWO",20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        StringMap<Integer> map_ = GuiBaseUtil.retrieve(c_);
        assertEq(2,map_.size());
        assertEq("ONE",map_.getKey(0));
        assertEq(15,map_.getValue(0));
        assertEq("TWO",map_.getKey(1));
        assertEq(20,map_.getValue(1));
    }
    @Test
    public void crud3() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        update(c_,"TWO",20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        update(c_,"ONE",16);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        StringMap<Integer> map_ = GuiBaseUtil.retrieve(c_);
        assertEq(2,map_.size());
        assertEq("ONE",map_.getKey(0));
        assertEq(16,map_.getValue(0));
        assertEq("TWO",map_.getKey(1));
        assertEq(20,map_.getValue(1));
    }
    @Test
    public void crud4() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        update(c_,"TWO",20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getValidRemove().getActionListeners().get(0).action();
        StringMap<Integer> map_ = GuiBaseUtil.retrieve(c_);
        assertEq(1,map_.size());
        assertEq("TWO",map_.getKey(0));
        assertEq(20,map_.getValue(0));
    }
    @Test
    public void crud5() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        update(c_,"TWO",20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        update(c_,"ONE",16);
        c_.getCancel().getActionListeners().get(0).action();
        StringMap<Integer> map_ = GuiBaseUtil.retrieve(c_);
        assertEq(2,map_.size());
        assertEq("ONE",map_.getKey(0));
        assertEq(15,map_.getValue(0));
        assertEq("TWO",map_.getKey(1));
        assertEq(20,map_.getValue(1));
    }
    @Test
    public void crud6() {
        StringMap<Integer> m_ = new StringMap<Integer>();
        m_.addEntry("ONE",15);
        CrudGeneForm c_ = crud(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        assertTrue(c_.isVisibleSingle());
        update(c_,"ONE",20);
        c_.getValidAddEdit().getActionListeners().get(0).action();
        StringMap<Integer> map_ = GuiBaseUtil.retrieve(c_);
        assertEq(1,map_.size());
        assertEq("ONE",map_.getKey(0));
        assertEq(15,map_.getValue(0));
    }
    @Test
    public void crudList1() {
        CustList<EditedCrudPair<String,Integer>> m_ = new CustList<EditedCrudPair<String,Integer>>();
        m_.add(new EditedCrudPair<String, Integer>("ONE",1));
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = crudList(m_, new StringList("ONE"));
        assertEq(1,c_.getList().size());
        assertEq("ONE",c_.getList().get(0).getKey());
        assertFalse(c_.isVisibleSingle());
        assertEq(4,c_.getButtons().getComponentCount());
    }
    @Test
    public void crudList2() {
        CustList<EditedCrudPair<String,Integer>> m_ = new CustList<EditedCrudPair<String,Integer>>();
        m_.add(new EditedCrudPair<String, Integer>("ONE",1));
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = crudList(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        assertTrue(c_.isVisibleSingle());
        c_.getGene().value(new EditedCrudPair<String, Integer>("TWO",2));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        assertEq(2,c_.getList().size());
        assertEq("ONE",c_.getList().get(0).getKey());
        assertEq("TWO",c_.getList().get(1).getKey());
    }
    @Test
    public void crudList3() {
        CustList<EditedCrudPair<String,Integer>> m_ = new CustList<EditedCrudPair<String,Integer>>();
        m_.add(new EditedCrudPair<String, Integer>("ONE",1));
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = crudList(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        c_.getGene().value(new EditedCrudPair<String, Integer>("TWO",2));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getGene().value(new EditedCrudPair<String, Integer>("ONE",1));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        assertEq(2,c_.getList().size());
        assertEq("ONE",c_.getList().get(0).getKey());
        assertEq("TWO",c_.getList().get(1).getKey());
    }
    @Test
    public void crudList4() {
        CustList<EditedCrudPair<String,Integer>> m_ = new CustList<EditedCrudPair<String,Integer>>();
        m_.add(new EditedCrudPair<String, Integer>("ONE",1));
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = crudList(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        c_.getGene().value(new EditedCrudPair<String, Integer>("TWO",2));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getValidRemove().getActionListeners().get(0).action();
        assertEq(1,c_.getList().size());
        assertEq("TWO",c_.getList().get(0).getKey());
    }
    @Test
    public void crudList5() {
        CustList<EditedCrudPair<String,Integer>> m_ = new CustList<EditedCrudPair<String,Integer>>();
        m_.add(new EditedCrudPair<String, Integer>("ONE",1));
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = crudList(m_, new StringList("ONE","TWO"));
        c_.getAdd().getActionListeners().get(0).action();
        c_.getGene().value(new EditedCrudPair<String, Integer>("TWO",2));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getGene().value(new EditedCrudPair<String, Integer>("ONE", 1));
        c_.getCancel().getActionListeners().get(0).action();
        assertEq(2,c_.getList().size());
        assertEq("ONE",c_.getList().get(0).getKey());
        assertEq("TWO",c_.getList().get(1).getKey());
    }
    @Test
    public void crudList6() {
        CustList<EditedCrudPair<String,Integer>> m_ = new CustList<EditedCrudPair<String,Integer>>();
        m_.add(new EditedCrudPair<String, Integer>("ONE",1));
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = crudListCmp(m_, new StringList("ONE","TWO"),new ComparingStringKey<Integer>());
        c_.getAdd().getActionListeners().get(0).action();
        c_.getGene().value(new EditedCrudPair<String, Integer>("TWO",2));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getGene().value(new EditedCrudPair<String, Integer>("ONE", 1));
        c_.getCancel().getActionListeners().get(0).action();
        assertEq(2,c_.getList().size());
        assertEq("ONE",c_.getList().get(0).getKey());
        assertEq("TWO",c_.getList().get(1).getKey());
    }
    @Test
    public void crudList7() {
        CustList<EditedCrudPair<String,Integer>> m_ = new CustList<EditedCrudPair<String,Integer>>();
        EditedCrudPair<String, Integer> one_ = new EditedCrudPair<String, Integer>("ONE", 1);
        m_.add(one_);
        EditedCrudPair<String, Integer> two_ = new EditedCrudPair<String, Integer>("TWO", 2);
        m_.add(two_);
        EditedCrudPair<String, Integer> three_ = new EditedCrudPair<String, Integer>("THREE", 3);
        m_.add(three_);
        IdMap<EditedCrudPair<String,Integer>,String> map_ = new IdMap<EditedCrudPair<String,Integer>,String>();
        map_.addEntry(one_,"1");
        map_.addEntry(two_,"2");
        map_.addEntry(three_,"3");
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = crudListCmp(m_, new StringList("ONE","TWO"),new ComparatorMapValue<EditedCrudPair<String,Integer>>(map_));
        c_.getAllButtons().get(1).getActionListeners().get(0).action();
        assertEq(1,c_.getSelectedIndex());
        map_.clear();
        map_.addEntry(one_,"1");
        map_.addEntry(three_,"2");
        map_.addEntry(two_,"3");
        c_.possibleSort();
        c_.refresh();
        assertEq(2,c_.getSelectedIndex());
    }
    @Test
    public void crudMc1() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        assertEq(1,c_.getList().size());
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(15),map_.getFreq(0));
        assertFalse(c_.isVisibleSingle());
        assertEq(4,c_.getButtons().getComponentCount());
    }
    @Test
    public void crudMc2() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        assertTrue(c_.isVisibleSingle());
        update(c_, new Rate(2), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(2,map_.size());
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(15),map_.getFreq(0));
        assertEq(new Rate(2),map_.getEvent(1));
        assertEq(new LgInt(20),map_.getFreq(1));
    }
    @Test
    public void crudMc3() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        update(c_, new Rate(2), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        update(c_, new Rate(1), new LgInt(16));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(2,map_.size());
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(16),map_.getFreq(0));
        assertEq(new Rate(2),map_.getEvent(1));
        assertEq(new LgInt(20),map_.getFreq(1));
    }
    @Test
    public void crudMc4() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        update(c_, new Rate(2), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        c_.getValidRemove().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(1,map_.size());
        assertEq(new Rate(2),map_.getEvent(0));
        assertEq(new LgInt(20),map_.getFreq(0));
    }
    @Test
    public void crudMc5() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        update(c_, new Rate(2), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton)c_.getElements().getComponent(0)).getActionListeners().get(0).action();
        update(c_, new Rate(1), new LgInt(16));
        c_.getCancel().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(2,map_.size());
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(15),map_.getFreq(0));
        assertEq(new Rate(2),map_.getEvent(1));
        assertEq(new LgInt(20),map_.getFreq(1));
    }
    @Test
    public void crudMc6() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        assertTrue(c_.isVisibleSingle());
        update(c_, new Rate(1), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(1,map_.size());
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(15),map_.getFreq(0));
    }
    @Test
    public void modelInt() {
        GeneComponentModelInt g_ = new GeneComponentModelInt(init());
        g_.geneInt();
        g_.value(5);
        assertEq(5,g_.value());
    }
    @Test
    public void modelLong() {
        GeneComponentModelLong g_ = new GeneComponentModelLong(init());
        g_.geneLong();
        g_.value(5L);
        assertEq(5L,g_.value());
    }
    @Test
    public void modelLgIntChg() {
        MockTextField txt_ = new MockTextField();
        txt_.setText("2");
        ChgLgTextInput c_ = new ChgLgTextInput(1, txt_);
        c_.action();
        assertEq("3",txt_.getText());
    }
    @Test
    public void modelLgInt() {
        GeneComponentModelLgInt g_ = new GeneComponentModelLgInt(init());
        g_.geneLgInt();
        g_.value(new LgInt(5L));
        assertEq(new LgInt(5L), g_.value());
    }
    @Test
    public void modelRate() {
        GeneComponentModelRate g_ = new GeneComponentModelRate(init());
        g_.geneRate();
        g_.value(new Rate(5L));
        assertEq(new Rate(5L), g_.value());
    }
    @Test
    public void modelStr() {
        GeneComponentModelString g_ = new GeneComponentModelString(init(),new StringList(),new DefValidateText());
        g_.geneString();
        g_.value("5");
        assertEq("5",g_.value());
    }
    @Test
    public void modelElt0() {
        StringMap<String> mess_ = new StringMap<String>();
        GeneComponentModelElt<String> g_ = new GeneComponentModelElt<String>(init(), mess_, new EmptyDefValue());
        g_.geneEnum();
        assertEq("",g_.value());
    }
    @Test
    public void modelElt1() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelElt<String> g_ = new GeneComponentModelElt<String>(init(), mess_, new EmptyDefValue());
        g_.geneEnum();
        assertEq("1",g_.value());
    }
    @Test
    public void modelElt2() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelElt<String> g_ = new GeneComponentModelElt<String>(init(), mess_, new EmptyDefValue());
        g_.geneEnum();
        g_.setupValue("2");
        assertEq("2",g_.value());
    }
    @Test
    public void modelElt3() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelElt<String> g_ = new GeneComponentModelElt<String>(init(), mess_, new EmptyDefValue());
        g_.geneEnum();
        g_.value("1");
        assertEq("1",g_.value());
    }
    @Test
    public void modelElt4() {
        StringMap<String> mess_ = new StringMap<String>();
        GeneComponentModelElt<String> g_ = new GeneComponentModelElt<String>(init(), mess_, new EmptyDefValue());
        g_.reset();
        assertEq(0,g_.getElements().size());
    }
    @Test
    public void modelElt5() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelElt<String> g_ = new GeneComponentModelElt<String>(init(), mess_, new EmptyDefValue());
        g_.geneEnum();
        mess_.addEntry("3","THREE");
        g_.reset();
        assertEq(3,g_.getElements().size());
    }
    @Test
    public void modelEltId0() {
        IdMap<ConcreteInteger,String> mess_ = new IdMap<ConcreteInteger,String>();
        GeneComponentModelElt<ConcreteInteger> g_ = new GeneComponentModelElt<ConcreteInteger>(init(), mess_);
        g_.geneEnum();
        assertSame(null,g_.valueElt());
    }
    @Test
    public void modelEltId1() {
        ConcreteInteger one_ = new ConcreteInteger(1);
        ConcreteInteger two_ = new ConcreteInteger(2);
        IdMap<ConcreteInteger,String> mess_ = new IdMap<ConcreteInteger,String>();
        mess_.addEntry(one_,"ONE");
        mess_.addEntry(two_,"TWO");
        GeneComponentModelElt<ConcreteInteger> g_ = new GeneComponentModelElt<ConcreteInteger>(init(), mess_);
        g_.geneEnum();
        assertSame(one_,g_.value());
    }
    @Test
    public void modelEltId2() {
        ConcreteInteger one_ = new ConcreteInteger(1);
        ConcreteInteger two_ = new ConcreteInteger(2);
        IdMap<ConcreteInteger,String> mess_ = new IdMap<ConcreteInteger,String>();
        mess_.addEntry(one_,"ONE");
        mess_.addEntry(two_,"TWO");
        GeneComponentModelElt<ConcreteInteger> g_ = new GeneComponentModelElt<ConcreteInteger>(init(), mess_);
        g_.geneEnum();
        g_.setupValue(two_);
        assertSame(two_,g_.value());
    }
    @Test
    public void modelEltId3() {
        ConcreteInteger one_ = new ConcreteInteger(1);
        ConcreteInteger two_ = new ConcreteInteger(2);
        IdMap<ConcreteInteger,String> mess_ = new IdMap<ConcreteInteger,String>();
        mess_.addEntry(one_,"ONE");
        mess_.addEntry(two_,"TWO");
        GeneComponentModelElt<ConcreteInteger> g_ = new GeneComponentModelElt<ConcreteInteger>(init(), mess_);
        g_.geneEnum();
        g_.valueElt(one_);
        assertSame(one_,g_.value());
    }
    @Test
    public void modelLs1() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelLs<String> g_ = new GeneComponentModelLs<String>(init(), mess_);
        g_.buildLs();
        assertEq(0,g_.value().size());
    }
    @Test
    public void modelLs2() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelLs<String> g_ = new GeneComponentModelLs<String>(init(), mess_);
        g_.geneCommon(new StringList("2"));
        CustList<String> ls_ = g_.value();
        assertEq(1, ls_.size());
        assertEq("2", ls_.get(0));
    }
    @Test
    public void modelLs3() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelLs<String> g_ = new GeneComponentModelLs<String>(init(), mess_);
        g_.geneCommon(new StringList("2"));
        g_.setupValue(new StringList("1"));
        CustList<String> ls_ = g_.value();
        assertEq(1, ls_.size());
        assertEq("1", ls_.get(0));
    }
    @Test
    public void modelLs4() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelLs<String> g_ = new GeneComponentModelLs<String>(init(), mess_);
        g_.geneCommon(new StringList("2"));
        g_.setupValue(new StringList("2","3"));
        CustList<String> ls_ = g_.value();
        assertEq(1, ls_.size());
        assertEq("2", ls_.get(0));
    }
    @Test
    public void modelLs5() {
        StringMap<String> mess_ = new StringMap<String>();
        GeneComponentModelLs<String> g_ = new GeneComponentModelLs<String>(init(), mess_);
        g_.reset();
        assertEq(0,g_.getElements().size());
    }
    @Test
    public void modelLs6() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("1","ONE");
        mess_.addEntry("2","TWO");
        GeneComponentModelLs<String> g_ = new GeneComponentModelLs<String>(init(), mess_);
        g_.buildLs();
        mess_.addEntry("3","THREE");
        g_.reset();
        assertEq(3,g_.getElements().size());
    }
    @Test
    public void modelLsId1() {
        ConcreteInteger one_ = new ConcreteInteger(1);
        ConcreteInteger two_ = new ConcreteInteger(2);
        IdMap<ConcreteInteger,String> mess_ = new IdMap<ConcreteInteger,String>();
        mess_.addEntry(one_,"ONE");
        mess_.addEntry(two_,"TWO");
        GeneComponentModelLs<ConcreteInteger> g_ = new GeneComponentModelLs<ConcreteInteger>(init(), mess_);
        g_.buildLs();
        assertEq(0,g_.value().size());
    }
    @Test
    public void modelLsId2() {
        ConcreteInteger one_ = new ConcreteInteger(1);
        ConcreteInteger two_ = new ConcreteInteger(2);
        IdMap<ConcreteInteger,String> mess_ = new IdMap<ConcreteInteger,String>();
        mess_.addEntry(one_,"ONE");
        mess_.addEntry(two_,"TWO");
        GeneComponentModelLs<ConcreteInteger> g_ = new GeneComponentModelLs<ConcreteInteger>(init(), mess_);
        g_.geneCommon(new IdList<ConcreteInteger>(two_));
        CustList<ConcreteInteger> ls_ = g_.value();
        assertEq(1, ls_.size());
        assertSame(two_, ls_.get(0));
    }
    @Test
    public void modelLsId3() {
        ConcreteInteger one_ = new ConcreteInteger(1);
        ConcreteInteger two_ = new ConcreteInteger(2);
        IdMap<ConcreteInteger,String> mess_ = new IdMap<ConcreteInteger,String>();
        mess_.addEntry(one_,"ONE");
        mess_.addEntry(two_,"TWO");
        GeneComponentModelLs<ConcreteInteger> g_ = new GeneComponentModelLs<ConcreteInteger>(init(), mess_);
        g_.geneCommon(new IdList<ConcreteInteger>(two_));
        g_.value(new IdList<ConcreteInteger>(one_));
        CustList<ConcreteInteger> ls_ = g_.value();
        assertEq(1, ls_.size());
        assertSame(one_, ls_.get(0));
    }
    @Test
    public void modelLsId4() {
        ConcreteInteger one_ = new ConcreteInteger(1);
        ConcreteInteger two_ = new ConcreteInteger(2);
        ConcreteInteger three_ = new ConcreteInteger(3);
        IdMap<ConcreteInteger,String> mess_ = new IdMap<ConcreteInteger,String>();
        mess_.addEntry(one_,"ONE");
        mess_.addEntry(two_,"TWO");
        GeneComponentModelLs<ConcreteInteger> g_ = new GeneComponentModelLs<ConcreteInteger>(init(), mess_);
        g_.geneCommon(new IdList<ConcreteInteger>(two_));
        g_.value(new IdList<ConcreteInteger>(two_,three_));
        CustList<ConcreteInteger> ls_ = g_.value();
        assertEq(1, ls_.size());
        assertSame(two_, ls_.get(0));
    }
    @Test
    public void quit() {
        SampleGroupFrame fr_ = new SampleGroupFrame(init());
        new QuitEvent(fr_).action();
        assertEq(0,fr_.getCommonFrame().getWindowListenersDef().size());
        new ClosingChildFrameEvent(fr_).windowClosing();
        assertFalse(fr_.getCommonFrame().isVisible());
    }
    @Test
    public void buildPath1() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("/");
        MockMutableTreeNode c_ = new MockMutableTreeNode("0/");
        r_.add(c_);
        MockMutableTreeNode s_ = new MockMutableTreeNode("1/");
        c_.add(s_);
        assertEq("/0/1/",GuiBaseUtil.buildPath(s_));
    }
    @Test
    public void buildPath2() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("");
        MockMutableTreeNode c_ = new MockMutableTreeNode("0");
        r_.add(c_);
        MockMutableTreeNode s_ = new MockMutableTreeNode("1");
        c_.add(s_);
        assertEq("/0/1",GuiBaseUtil.buildPath(s_,"/"));
    }
    @Test
    public void actionCond1() {
        assertTrue(GuiBaseUtil.action(new AlwaysActionListenerAct(),new MockAction(0,new MockWithActionSample())));
    }
    @Test
    public void actionCond2() {
        assertFalse(GuiBaseUtil.action(new AbsActionListenerActSample(),new MockAction(0,new MockWithActionSample())));
    }
    @Test
    public void actionCond3() {
        assertTrue(GuiBaseUtil.action(new AlwaysActionListenerAct(),new MockMouseListener(),new CoreMouseLocation(0,0),new KeyActionEvent(0),new CoreMouseButtons(false,false,false,0)));
    }
    @Test
    public void actionCond4() {
        assertFalse(GuiBaseUtil.action(new AbsActionListenerActSample(),new MockMouseListener(),new CoreMouseLocation(1,1),new KeyActionEvent(1),new CoreMouseButtons(true,true,true,1)));
    }
    @Test
    public void actionCond5() {
        assertTrue(GuiBaseUtil.actionPressed(new AlwaysActionListenerAct(),new MockMouseListener(),new CoreMouseLocation(0,0),new KeyActionEvent(0),new CoreMouseButtons(false,false,false,0)));
    }
    @Test
    public void actionCond6() {
        assertFalse(GuiBaseUtil.actionPressed(new AbsActionListenerActSample(),new MockMouseListener(),new CoreMouseLocation(1,1),new KeyActionEvent(1),new CoreMouseButtons(true,true,true,1)));
    }
    @Test
    public void parseBaseSixtyFourBinary1() {
        byte[] bytes_ = parseBaseSixtyFourBinary("AAAB");
        assertEq(3,bytes_.length);
        assertEq(0,bytes_[0]);
        assertEq(0,bytes_[1]);
        assertEq(1,bytes_[2]);
    }
    @Test
    public void parseBaseSixtyFourBinary2() {
        byte[] bytes_ = parseBaseSixtyFourBinary("AAAB===");
        assertEq(3,bytes_.length);
        assertEq(0,bytes_[0]);
        assertEq(0,bytes_[1]);
        assertEq(1,bytes_[2]);
    }
    @Test
    public void parseBaseSixtyFourBinary3() {
        byte[] bytes_ = parseBaseSixtyFourBinary("====");
        assertEq(0,bytes_.length);
    }
    @Test
    public void parseBaseSixtyFourBinary4() {
        byte[] bytes_ = parseBaseSixtyFourBinary("AAA");
        assertEq(0,bytes_.length);
    }
    @Test
    public void parseBaseSixtyFourBinary5() {
        byte[] bytes_ = parseBaseSixtyFourBinary("AA");
        assertEq(0,bytes_.length);
    }
    @Test
    public void parseBaseSixtyFourBinary6() {
        byte[] bytes_ = parseBaseSixtyFourBinary("A");
        assertEq(0,bytes_.length);
    }
    @Test
    public void parseBaseSixtyFourBinary7() {
        byte[] bytes_ = parseBaseSixtyFourBinary("");
        assertEq(0,bytes_.length);
    }
    @Test
    public void parseBaseSixtyFourBinary8() {
        byte[] bytes_ = parseBaseSixtyFourBinary("AAA=");
        assertEq(2,bytes_.length);
        assertEq(0,bytes_[0]);
        assertEq(0,bytes_[1]);
    }
    @Test
    public void parseBaseSixtyFourBinary9() {
        byte[] bytes_ = parseBaseSixtyFourBinary("AA==");
        assertEq(1,bytes_.length);
        assertEq(0,bytes_[0]);
    }
    @Test
    public void parseBaseSixtyFourBinary10() {
        byte[] bytes_ = parseBaseSixtyFourBinary("AAAB/1z_");
        assertEq(0,bytes_.length);
    }
    @Test
    public void printBaseSixtyFourBinary1() {
        assertEq("AAAB",printBaseSixtyFourBinary(wrapInts(0,0,1)));
    }
    @Test
    public void printBaseSixtyFourBinary2() {
        assertEq("TWE=", printBaseSixtyFourBinary(wrapInts(77,97)));
    }
    @Test
    public void printBaseSixtyFourBinary3() {
        assertEq("TQ==", printBaseSixtyFourBinary(wrapInts(77)));
    }
    @Test
    public void printBaseSixtyFourBinary4() {
        assertEq("////",printBaseSixtyFourBinary(wrapInts(-1,-1,-1)));
    }
    @Test
    public void printBaseSixtyFourBinary8() {
        assertEq("AAA=",printBaseSixtyFourBinary(wrapInts(0,0)));
    }
    @Test
    public void printBaseSixtyFourBinary9() {
        assertEq("//8=",printBaseSixtyFourBinary(wrapInts(-1,-1)));
    }
    @Test
    public void printBaseSixtyFourBinary10() {
        assertEq("AA==",printBaseSixtyFourBinary(wrapInts(0)));
    }
    @Test
    public void printBaseSixtyFourBinary11() {
        assertEq("SUQzAQ==",printBaseSixtyFourBinary(wrapInts('I','D','3',1)));
    }
    @Test
    public void printBaseSixtyFourBinary12() {
        assertEq("UklGRgAAAABXQVZFAQ==",printBaseSixtyFourBinary(wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1)));
    }
    @Test
    public void getAbsClipStream1() {
        assertNull(getAbsClipStream(init(), wrapInts()));
    }
    @Test
    public void getAbsClipStream2() {
        assertNotNull(getAbsClipStream(init(), wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1)));
    }
    @Test
    public void getAbsClipStream3() {
        assertNotNull(getAbsClipStream(init(), wrapInts('I', 'D', '3', 1)));
    }
    @Test
    public void getAbsClipStream4() {
        assertNotNull(getAbsClipStream(init(), StringUtil.encode("<_ 0='UklGRgAAAABXQVZFAQ=='/>")));
    }
    @Test
    public void getAbsClipStream5() {
        assertNotNull(getAbsClipStream(init(), StringUtil.encode("<_ 0='SUQzAQ=='/>")));
    }
    @Test
    public void getAbsClipStream6() {
        assertNotNull(getAbsClipStream(init(), StringUtil.encode("UklGRgAAAABXQVZFAQ==")));
    }
    @Test
    public void getAbsClipStream7() {
        assertNotNull(getAbsClipStream(init(), StringUtil.encode("SUQzAQ==")));
    }
    @Test
    public void getStringTime1() {
        assertEq(" 1: 1: 1",GuiBaseUtil.getStringTime(1000000L*(1L+60L+60L*60L)));
    }
    @Test
    public void getStringTime2() {
        assertEq("10:10:10",GuiBaseUtil.getStringTime(10000000L*(1L+60L+60L*60L)));
    }

    @Test
    public void interpretedFile1() {
        MockProgramInfosSecSample init_ = init();
        InterpretedFile i_ = new InterpretedFile(init_,new String[0]);
        assertTrue(i_.getInput().isNul());
    }

    @Test
    public void interpretedFile2() {
        MockProgramInfosSecSample init_ = init();
        MockBinFact.write(init_.getStreams().getBinFact(),"/file", "<_/>", false);
        InterpretedFile i_ = new InterpretedFile(init_,new String[]{"/file"});
        assertFalse(i_.getInput().isNul());
        assertEq("/file",i_.getFileNames().get(0));
        assertEq("<_/>",i_.getText());
        assertEq("<_/>",i_.getDocument().export());
    }

    @Test
    public void interpretedFile3() {
        MockProgramInfosSecSample init_ = init();
        MockSoundRecord sr_ = new MockSoundRecord();
        sr_.appendHeader();
        sr_.append(new int[]{1});
        init_.getStreams().getBinFact().writeFile("/file",sr_.recordSong(), false);
        InterpretedFile i_ = new InterpretedFile(init_,new String[]{"/file"});
        assertFalse(i_.getInput().isNul());
        assertEq(1,i_.getClipSimple().getMicrosecondLength());
    }

    @Test
    public void interpretedFile4() {
        MockProgramInfosSecSample init_ = init();
        MockSoundRecord sr_ = new MockSoundRecord();
        sr_.append(new int[]{'I','D','3'});
        sr_.append(new int[]{1});
        init_.getStreams().getBinFact().writeFile("/file",sr_.recordSong(), false);
        InterpretedFile i_ = new InterpretedFile(init_,new String[]{"/file"});
        assertFalse(i_.getInput().isNul());
        assertEq(1,i_.getClipMp3().getMicrosecondLength());
    }

    public static byte[] wrapInts(int... _files) {
        return Ints.newList(_files).toArrByte();
    }
    private static void update(CrudGeneForm _form, String _key, int _value) {
        _form.getGeneComponentModelEntryStringInteger().getKey().valueString(_key);
        _form.getGeneComponentModelEntryStringInteger().getValue().valueInt(_value);
    }
    private static void update(CrudGeneFormMonteCarlo<Rate> _form, Rate _key, LgInt _value) {
        ((GeneComponentModelEventRate)_form.getGene()).getEvent().valueRate(_key);
        ((GeneComponentModelEventRate)_form.getGene()).getProba().valueLgInt(_value);
    }
    private CrudGeneForm crud(StringMap<Integer> _map, StringList _dico) {
        MockProgramInfosSecSample pr_ = init();
        AbsCommonFrame f_ = pr_.getFrameFactory().newCommonFrame();
        CrudGeneForm c_ = new CrudGeneForm(pr_);
        c_.setFrame(f_);
        c_.initForm();
        GuiBaseUtil.initStringMapInt(c_, _map, _dico,new DefValidateText());
        return c_;
    }
    private CrudGeneFormList<EditedCrudPair<String,Integer>> crudList(CustList<EditedCrudPair<String,Integer>> _map, StringList _dico) {
        MockProgramInfosSecSample pr_ = init();
        AbsCommonFrame f_ = pr_.getFrameFactory().newCommonFrame();
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = new CrudGeneFormList<EditedCrudPair<String,Integer>>(pr_);
        c_.setFrame(f_);
        c_.initForm();
        GuiBaseUtil.initStringList(c_, _map, _dico,new DefValidateText());
        return c_;
    }
    private CrudGeneFormList<EditedCrudPair<String,Integer>> crudListCmp(CustList<EditedCrudPair<String,Integer>> _map, StringList _dico, Comparing<EditedCrudPair<String,Integer>> _str) {
        MockProgramInfosSecSample pr_ = init();
        AbsCommonFrame f_ = pr_.getFrameFactory().newCommonFrame();
        CrudGeneFormList<EditedCrudPair<String,Integer>> c_ = new CrudGeneFormList<EditedCrudPair<String,Integer>>(pr_);
        c_.setFrame(f_);
        c_.initForm();
        GuiBaseUtil.initStringList(c_, _map, _dico,new DefValidateText(),_str);
        return c_;
    }
    private CrudGeneFormMonteCarlo<Rate> crudMc(AbMonteCarlo<Rate> _map) {
        MockProgramInfosSecSample pr_ = init();
        AbsCommonFrame f_ = pr_.getFrameFactory().newCommonFrame();
        CrudGeneFormMonteCarlo<Rate> c_ = new CrudGeneFormMonteCarlo<Rate>(null, pr_,new ComparingRateKey<LgInt>());
        c_.setFrame(f_);
        c_.initForm();
        c_.initFormKeys(new RateLgIntDisplayEntryCust(),new GeneComponentModelEventRate(pr_), new ComparingRateKey<LgInt>());
        c_.setupValues(new MapToEntriesListUtil<Rate, LgInt>().build(_map));
        return c_;
    }

    public static MonteCarloNumber retrieve(CrudGeneFormMonteCarlo<Rate> _f) {
        MonteCarloNumber out_ = new MonteCarloNumber();
        new MapToEntriesListUtil<Rate,LgInt>().feedMap(_f.getList(), out_);
        return out_;
    }

    private boolean launch(MockSoundRecord _pl) {
        return GuiBaseUtil.launch(_pl.build());
    }

    private AbsClipStream getAbsClipStream(MockProgramInfosSecSample _pr, byte[] _bytes) {
        return GuiBaseUtil.getAbsClipStream(_pr, _bytes, BASE);
    }

    private byte[] parseBaseSixtyFourBinary(String _text) {
        return GuiBaseUtil.parseBaseSixtyFourBinary(_text, BASE);
    }

    private String printBaseSixtyFourBinary(byte[] _text) {
        return GuiBaseUtil.printBaseSixtyFourBinary(_text, BASE);
    }

}
