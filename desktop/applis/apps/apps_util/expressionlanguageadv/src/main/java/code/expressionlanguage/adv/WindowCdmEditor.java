package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.options.CommentsUtil;
import code.expressionlanguage.utilcompo.CustAliases;
import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.*;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class WindowCdmEditor implements AbsGroupFrame {
    public static final String NODE_COMM = "0";
    public static final String DEF_CONF = "def_conf.txt";
    public static final String ROOT_CONF = "_";
    private final TabEditor tabEditor;
    private final AbsDialog dialogComments;
    private final AbsMenuItem commentsMenu;
    private StringMap<String> messages;
    private final AbsCommonFrame commonFrame;
    private final AbsSpinner spinner;
    private final AbsPanel panel;
    private final IdList<WindowCdmEditor> ides;
    private String openedConf = "";
    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    public WindowCdmEditor(String _lg, AbstractProgramInfos _list, IdList<WindowCdmEditor> _opened) {
        commonFrame = _list.getFrameFactory().newCommonFrame(_lg, _list, null);
        dialogComments = _list.getFrameFactory().newDialog();
        GuiBaseUtil.choose(_lg, _list, this, MessGuiGr.ms());
        AbsMenuBar bar_ = _list.getCompoFactory().newMenuBar();
        AbsMenu menu_ = _list.getCompoFactory().newMenu("boss");
        bar_.add(menu_);
        commentsMenu = _list.getCompoFactory().newMenuItem("comments");
        commentsMenu.addActionListener(new ChangeCommentsEvent(this));
        menu_.addMenuItem(commentsMenu);
        tabEditor = new TabEditor(this);
        spinner = _list.getCompoFactory().newSpinner(4,1,64,1);
        TabValueChanged l_ = new TabValueChanged(this);
        spinner.addChangeListener(l_);
        l_.stateChanged();
        panel = _list.getCompoFactory().newPageBox();
        panel.add(spinner);
        panel.add(tabEditor.getPanel());
        commonFrame.setContentPane(panel);
        commonFrame.setJMenuBar(bar_);
        commonFrame.pack();
        commonFrame.setVisible(true);
        commonFrame.addWindowListener(new QuittingEvent(this));
        ides = _opened;
        _opened.add(this);
    }
    public void updateCommentsInit(StringList _files) {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        if (!frs_.getFileCoreStream().newFile(DEF_CONF).exists()) {
            StreamTextFile.saveTextFile(DEF_CONF,buildConfFile(),frs_.getStreams());
        }
        String fileName_ = defConf(_files);
        openedConf = fileName_;
        String content_ = StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(fileName_, frs_.getFileCoreStream(), frs_.getStreams()));
        Document doc_ = DocumentBuilder.parseSax(content_);
        CustList<CommentDelimiters> comments_ = retrieveComments(doc_);
        CommentsUtil.checkAndUpdateComments(comments_, CustAliases.defComments("",frs_.getTranslations(),frs_.getLanguage()));
        comments = comments_;
    }
    private CustList<CommentDelimiters> retrieveComments(Document _doc) {
        if (_doc == null || !StringUtil.quickEq(ROOT_CONF, _doc.getDocumentElement().getTagName())) {
            return new CustList<CommentDelimiters>();
        }
        ElementList e_ = _doc.getElementsByTagName(NODE_COMM);
        int l_ = e_.getLength();
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < l_; i++) {
            str_.append(e_.item(i).getTextContent());
        }
        String res_ = str_.toString();
        if (res_.isEmpty()) {
            return new CustList<CommentDelimiters>();
        }
        return ParseLinesArgUtil.buildComments(res_);
    }
    private static String defConf(StringList _files) {
        if (_files.isEmpty()) {
            return DEF_CONF;
        }
        return _files.first();
    }

    public String getOpenedConf() {
        return openedConf;
    }

    public void afterChangingComments(OutputDialogCommentsResult _res) {
        if (!_res.getValid().get()) {
            return;
        }
        saveComments(_res.getComments());
        DocumentTextChange.updateEditorText(tabEditor);
    }

    public void saveComments(CustList<CommentDelimiters> _comm) {
        AbstractProgramInfos frs_ = commonFrame.getFrames();
        CommentsUtil.checkAndUpdateComments(_comm, comments);
        comments = _comm;
        StreamTextFile.saveTextFile(openedConf, buildConfFile(),frs_.getStreams());
    }

    private String buildConfFile() {
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element elt_ = doc_.createElement(ROOT_CONF);
        Element eltSub_ = doc_.createElement(NODE_COMM);
        eltSub_.appendChild(doc_.createTextNode(ParseLinesArgUtil.buildCommentsLine(comments)));
        elt_.appendChild(eltSub_);
        doc_.appendChild(elt_);
        return doc_.export();
    }

    public AbsMenuItem getCommentsMenu() {
        return commentsMenu;
    }

    public AbsDialog getDialogComments() {
        return dialogComments;
    }

    public TabEditor getTabEditor() {
        return tabEditor;
    }

    @Override
    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public AbsSpinner getSpinner() {
        return spinner;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> _messages) {
        this.messages = _messages;
    }
    @Override
    public void changeLanguage(String _language) {
        setMessages(getMessages());
    }

    @Override
    public void dispatchExit() {
        commonFrame.dispatchExit();
    }

    @Override
    public void quit() {
        commonFrame.setVisible(false);
        ides.removeObj(this);
        GuiBaseUtil.tryExit(commonFrame);
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }
    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public void setComments(CustList<CommentDelimiters> _c) {
        this.comments = _c;
    }


    public IdList<WindowCdmEditor> getIdes() {
        return ides;
    }
}
