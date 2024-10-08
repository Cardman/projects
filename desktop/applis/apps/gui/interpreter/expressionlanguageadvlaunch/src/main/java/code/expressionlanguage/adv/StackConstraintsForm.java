package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.exec.dbg.ExecFileBlockFct;
import code.expressionlanguage.exec.dbg.ExecFileBlockTraceIndex;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class StackConstraintsForm {
    private ReadOnlyFormTabEditor readOnlyFormTabEditor;
    private AbsButton bpAddFile;
    private AbsButton bpRemoveFile;
    private AbsCustCheckBox singleCaret;
    private AbsTreeGui bpFolderSystem;
    private AbsPanel includedFileIndex;
    private AbsPanel excludedFileIndex;
    private AbsSplitPane staIncExc;
    private final CustList<AbsCallContraints> mustBe = new CustList<AbsCallContraints>();
    private final CustList<AbsCallContraints> mustNotBe = new CustList<AbsCallContraints>();
    private final AbstractProgramInfos frames;

    public StackConstraintsForm(AbstractProgramInfos _api) {
        frames = _api;
    }

    public void add(ResultContext _res, CustList<AbsCallContraints> _list, ReadOnlyFormTabEditor _e) {
        FileBlock v_ = _res.getPageEl().getPreviousFilesBodies().getVal(_e.getFullPath());
        ExecFileBlock f_ = _res.getFiles().getVal(v_);
        if (f_ == null) {
            return;
        }
        if (singleCaret.isSelected()) {
            add(_list, new ExecFileBlockTraceIndex(f_,FileBlock.number(v_),_e.getCenter().getCaretPosition()));
        } else {
            add(_list, new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFctKey(_e.getCenter().getCaretPosition(),v_),ResultExpressionOperationNode.beginPartFct(_e.getCenter().getCaretPosition(),v_,_res.getPageEl().getDisplayedStrings())));
        }
    }

    public static void add(CustList<AbsCallContraints> _list, AbsCallContraints _l) {
        int i_ = index(_list, _l);
        if (i_ == -1) {
            _list.add(_l);
        }
    }

    public static void remove(CustList<AbsCallContraints> _list, AbsCallContraints _l) {
        int i_ = index(_list, _l);
        if (i_ > -1) {
            _list.remove(i_);
        }
    }

    public static int index(CustList<AbsCallContraints> _list, AbsCallContraints _l) {
        int s_ = _list.size();
        for (int i = 0; i < s_; i++) {
            if (match(_l, _list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static boolean match(AbsCallContraints _l, AbsCallContraints _one) {
        return _l.match(_one);
    }
    public AbsSplitPane guiBuild(AbsDebuggerGui _d, AbsButton _valid) {
        StringMap<String> mes_ = MessagesIde.valPointForm(_d.getFrames().currentLg());
        bpFolderSystem = _d.getFrames().getCompoFactory().newTreeGui(_d.getFrames().getCompoFactory().newMutableTreeNode(AbsEditorTabList.EMPTY_STRING));
        bpFolderSystem.select(bpFolderSystem.getRoot());
        readOnlyFormTabEditor = new ReadOnlyFormTabEditor(_d,_d.getFrames(), _d.getManageOptions().getOptions());
        AbsPanel actions_ = _d.getFrames().getCompoFactory().newPageBox();
        staIncExc = _d.getFrames().getCompoFactory().newVerticalSplitPane(_d.getFrames().getCompoFactory().newHorizontalSplitPane(_d.getFrames().getCompoFactory().newAbsScrollPane(bpFolderSystem),readOnlyFormTabEditor.getPanel()),actions_);
        singleCaret = _d.getFrames().getCompoFactory().newCustCheckBox(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_SINGLE)));
        singleCaret.setSelected(true);
        actions_.add(singleCaret);
        bpAddFile = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_INC)));
        actions_.add(bpAddFile);
        bpRemoveFile = _d.getFrames().getCompoFactory().newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_POINT_FORM_EXC)));
        actions_.add(bpRemoveFile);
        includedFileIndex = _d.getFrames().getCompoFactory().newPageBox();
        excludedFileIndex = _d.getFrames().getCompoFactory().newPageBox();
        actions_.add(includedFileIndex);
        actions_.add(excludedFileIndex);
        if (_valid != null){
            actions_.add(_valid);
        }
        return staIncExc;
    }

    public void refresh(StringMap<String> _files, String _folderToVisit, ResultContext _r, AbsDebuggerGui _d) {
        GuiBaseUtil.removeActionListeners(bpAddFile);
        bpAddFile.addActionListener(new AddIncludeEvent(this,_d, _r));
        GuiBaseUtil.removeActionListeners(bpRemoveFile);
        bpRemoveFile.addActionListener(new AddExcludeEvent(this,_d, _r));
        GuiBaseUtil.removeTreeSelectionListeners(bpFolderSystem);
        bpFolderSystem.addTreeSelectionListener(new ShowSrcReadOnlyTreeEvent(_d,_r,bpFolderSystem,new SelOpeningReadOnlyFile(this)));
        _d.refParent(bpFolderSystem.getRoot(),_files,_folderToVisit,bpFolderSystem);
    }

    public void actualiseLists(AbsCommonFrame _c) {
        includedFileIndex.removeAll();
        excludedFileIndex.removeAll();
        for (AbsCallContraints l: getMustBe()) {
            AbsButton r_ = frames.getCompoFactory().newPlainButton("+ "+l.valueStr());
            r_.addActionListener(new RemoveIncludeEvent(this, l, _c));
            includedFileIndex.add(r_);
        }
        for (AbsCallContraints l: getMustNotBe()) {
            AbsButton r_ = frames.getCompoFactory().newPlainButton("- "+l.valueStr());
            r_.addActionListener(new RemoveExcludeEvent(this, l, _c));
            excludedFileIndex.add(r_);
        }
        border();
        _c.pack();
    }

    private void border() {
        for (AbsCallContraints l: getMustBe()) {
            for (AbsCallContraints m: getMustNotBe()) {
                if (match(l,m)) {
                    staIncExc.setLineBorder(GuiConstants.RED);
                    return;
                }
            }
        }
        staIncExc.setLineBorder(GuiConstants.GREEN);
    }

    public AbsPanel getIncludedFileIndex() {
        return includedFileIndex;
    }

    public AbsPanel getExcludedFileIndex() {
        return excludedFileIndex;
    }

    public CustList<AbsCallContraints> getMustBe() {
        return mustBe;
    }

    public CustList<AbsCallContraints> getMustNotBe() {
        return mustNotBe;
    }

    public AbsButton getBpAddFile() {
        return bpAddFile;
    }

    public AbsButton getBpRemoveFile() {
        return bpRemoveFile;
    }

    public AbsTreeGui getBpFolderSystem() {
        return bpFolderSystem;
    }

    public ReadOnlyFormTabEditor getReadOnlyFormTabEditor() {
        return readOnlyFormTabEditor;
    }

    public AbsCustCheckBox getSingleCaret() {
        return singleCaret;
    }

}
