package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.BytesInfo;
import code.stream.StreamBinaryFile;
import code.stream.StreamTextFile;
import code.util.StringList;

public final class WindowExpressionEditor extends WindowWithTreeImpl {
    private final WindowCdmEditor mainFrame;
    private final AbsMenuItem folderExpressionMenu;
    public WindowExpressionEditor(WindowCdmEditor _parent, AbsMenuItem _menu) {
        super(_parent.getCommonFrame().getLanguageKey(),_parent.getCommonFrame().getFrames(),_parent.getFactory());
        folderExpressionMenu = _menu;
        mainFrame = _parent;
        AbstractProgramInfos frames_ = _parent.getCommonFrame().getFrames();
        getCommonFrame().addWindowListener(new CloseExpFrame(this,_menu));
        AbsMenuBar bar_ = frames_.getCompoFactory().newMenuBar();
        AbsMenu file_ = frames_.getCompoFactory().newMenu("file");
        bar_.add(file_);
        file_.addMenuItem(getSrcMenu());
        file_.addMenuItem(getCreate());
        file_.addMenuItem(getDelete());
        AbsMenu menu_ = getParameters();
        bar_.add(menu_);
        getCommonFrame().setJMenuBar(bar_);
        chgManagement(false);
        setEditors(getCommonFrame().getFrames().getCompoFactory().newAbsTabbedPane());
    }
    public void updateEnv(boolean _first) {
        folderExpressionMenu.setEnabled(false);
        if (!_first) {
            getCommonFrame().setVisible(true);
            return;
        }
        chgManagement(true);
        setManageOptions(manage(mainFrame.getSoftParams().getLines()));
        String acc_ = mainFrame.getFolderExpression();
        getPanel().removeAll();
        initTree(acc_);
        AbstractProgramInfos frs_ = getCommonFrame().getFrames();
        getTabs().clear();
        setEditors(frs_.getCompoFactory().newAbsTabbedPane());
        getEditors().addChangeListener(new TabValueChanged(this));
        StringList src_ = mainFrame.getOpenedFilesToInit();
        int len_ = src_.size();
        StringList existing_ = new StringList();
        for (int i = 0; i < len_; i++) {
            String fullPath_ = pathToSrc()+src_.get(i);
            BytesInfo content_ = StreamBinaryFile.loadFile(fullPath_, getCommonFrame().getFrames().getStreams());
            if (content_.isNul()) {
                continue;
            }
            existing_.add(src_.get(i));
            WindowWithTreeImpl.addTab(this,fullPath_,content_);
        }
        src_.clear();
        src_.addAllElts(existing_);
        endTree();
        getCommonFrame().setContentPane(getPanel());
        getCommonFrame().pack();
        getCommonFrame().setVisible(true);
    }

    @Override
    public void saveConf() {
        softParams().setLines(WindowCdmEditor.linesConf(getManageOptions()));
        mainFrame.trySubmit();
        updateDoc();
    }

    public AbsMenuItem getMenu() {
        return folderExpressionMenu;
    }
    @Override
    public AbsTreeGui getTree() {
        return getFolderSystem();
    }

    @Override
    public void changeEnable(AbstractMutableTreeNode _en) {
        changeEnable(_en != null);
    }

    @Override
    public String pathToSrc() {
        return mainFrame.getFolderExpression()+ StreamTextFile.SEPARATEUR;
    }

    @Override
    public WindowCdmEditor getMainFrame() {
        return mainFrame;
    }

    @Override
    public StringList openedFiles() {
        return mainFrame.getOpenedFilesToInit();
    }

    public void closeWindows() {
        getCommonFrame().setVisible(false);
        closeSecs();
    }
}
