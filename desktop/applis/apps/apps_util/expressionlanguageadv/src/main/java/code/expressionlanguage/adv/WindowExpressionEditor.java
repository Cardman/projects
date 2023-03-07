package code.expressionlanguage.adv;

import code.expressionlanguage.utilimpl.ManageOptions;
import code.gui.*;
import code.gui.events.AbsEnabledAction;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.BytesInfo;
import code.stream.StreamBinaryFile;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;

public final class WindowExpressionEditor implements WindowWithTree {
    private final WindowCdmEditor mainFrame;
    private final AbsCommonFrame commonFrame;
    private final AbsPanel panel;
    private final StringList openedFiles = new StringList();
    private final CustList<TabEditor> tabs = new CustList<TabEditor>();
    private AbsTreeGui folderSystem;
    private AbsTabbedPane editors;
    private AbsEnabledAction refreshNode;
    private ManageOptions manageOptions;
//    private AbsEnabledAction renameNode;
//    private AbsEnabledAction removeNode;
//    private AbsEnabledAction createSystem;
    public WindowExpressionEditor(WindowCdmEditor _parent) {
        mainFrame = _parent;
        AbstractProgramInfos frames_ = _parent.getCommonFrame().getFrames();
        commonFrame = frames_.getFrameFactory().newCommonFrame(_parent.getCommonFrame().getLanguageKey(), frames_, null);
        panel = frames_.getCompoFactory().newPageBox();
        editors = frames_.getCompoFactory().newAbsTabbedPane();
    }
    public void updateEnv(boolean _first) {
        if (!_first) {
            commonFrame.setVisible(true);
            return;
        }
        manageOptions = new ManageOptions(commonFrame.getFrames().getLanguages(), mainFrame.getSoftParams().getLines(), mainFrame.getFactory(), commonFrame.getFrames().getThreadFactory());
        String acc_ = mainFrame.getFolderExpression();
        panel.removeAll();
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        AbstractMutableTreeNode default_ = frs_.getCompoFactory().newMutableTreeNode(acc_+"/");
        folderSystem = frs_.getCompoFactory().newTreeGui(default_);
        folderSystem.select(folderSystem.getRoot());
        WindowCdmEditor.refreshList(folderSystem.selectEvt(),acc_, commonFrame.getFrames());
        folderSystem.addTreeSelectionListener(new ShowSrcTreeEvent(this));
        refreshNode = frs_.getCompoFactory().wrap(new RefreshTreeAction(this));
        folderSystem.registerKeyboardAction(refreshNode, GuiConstants.VK_F5, GuiConstants.CTRL_DOWN_MASK);
        tabs.clear();
        openedFiles.clear();
        editors = frs_.getCompoFactory().newAbsTabbedPane();
        editors.addChangeListener(new TabValueChanged(this));
        openedFiles.addAllElts(mainFrame.getOpenedFilesToInit());
        int len_ = openedFiles.size();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc()+openedFiles.get(i);
            BytesInfo content_ = StreamBinaryFile.loadFile(fullPath_, commonFrame.getFrames().getStreams());
            if (content_.isNul()) {
                continue;
            }
            WindowCdmEditor.addTab(this,fullPath_,content_);
        }
        panel.add(frs_.getCompoFactory().newHorizontalSplitPane(frs_.getCompoFactory().newAbsScrollPane(folderSystem), editors));
        commonFrame.setContentPane(panel);
        commonFrame.pack();
        commonFrame.setVisible(true);
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    @Override
    public AbsTreeGui getTree() {
        return folderSystem;
    }

    @Override
    public void changeEnable(boolean _en) {
        refreshNode.setEnabled(_en);
    }

    @Override
    public String pathToSrc() {
        return mainFrame.getFolderExpression()+ StreamTextFile.SEPARATEUR;
    }

    @Override
    public CustList<TabEditor> getTabs() {
        return tabs;
    }

    @Override
    public AbsTabbedPane getEditors() {
        return editors;
    }

    @Override
    public WindowCdmEditor getMainFrame() {
        return mainFrame;
    }

    @Override
    public void removeObj(String _rel) {
        mainFrame.getOpenedFilesToInit().removeObj(_rel);
    }

    public ManageOptions getManageOptions() {
        return manageOptions;
    }
}
