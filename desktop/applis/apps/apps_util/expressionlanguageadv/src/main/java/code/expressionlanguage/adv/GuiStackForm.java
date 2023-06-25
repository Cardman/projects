package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.linkage.LinkedNamedArgParts;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.util.CustList;
import code.util.StringMap;

public final class GuiStackForm {
    private AbsTextArea conditional;
    private AbsSpinner count;
    private ReadOnlyFormTabEditor readOnlyFormTabEditor;
    private AbsPlainButton bpAddFile;
    private AbsPlainButton bpRemoveFile;
    private AbsTreeGui bpFolderSystem;
    private AbsPanel includedFileIndex;
    private AbsPanel excludedFileIndex;
    private AbsPanel staIncExc;
    private AbsScrollPane staScIncExc;
    private final CustList<LinkedNamedArgParts> mustBe = new CustList<LinkedNamedArgParts>();
    private final CustList<LinkedNamedArgParts> mustNotBe = new CustList<LinkedNamedArgParts>();

    public static void add(ResultContext _res, CustList<LinkedNamedArgParts> _list, ReadOnlyFormTabEditor _e) {
        FileBlock v_ = _res.getPageEl().getPreviousFilesBodies().getVal(_e.getFullPath());
        if (v_ == null) {
            return;
        }
        add(_list, new LinkedNamedArgParts(v_,_e.getCenter().getCaretPosition()));
    }

    public static void add(CustList<LinkedNamedArgParts> _list, LinkedNamedArgParts _l) {
        int i_ = index(_list, _l);
        if (i_ == -1) {
            _list.add(_l);
        }
    }

    public static void remove(CustList<LinkedNamedArgParts> _list, LinkedNamedArgParts _l) {
        int i_ = index(_list, _l);
        if (i_ > -1) {
            _list.remove(i_);
        }
    }

    public static int index(CustList<LinkedNamedArgParts> _list, LinkedNamedArgParts _l) {
        int s_ = _list.size();
        for (int i = 0; i < s_; i++) {
            if (match(_l, _list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static boolean match(LinkedNamedArgParts _l, LinkedNamedArgParts _one) {
        return _one.getFile() == _l.getFile() && _one.getOffset() == _l.getOffset();
    }

    public AbsScrollPane guiBuild(AbsDebuggerGui _d) {
        conditional = _d.getCommonFrame().getFrames().getCompoFactory().newTextArea();
        count = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        bpFolderSystem = _d.getCommonFrame().getFrames().getCompoFactory().newTreeGui(_d.getCommonFrame().getFrames().getCompoFactory().newMutableTreeNode(""));
        bpFolderSystem.select(bpFolderSystem.getRoot());
        bpFolderSystem.addTreeSelectionListener(new ShowSrcReadOnlyTreeEvent(_d,bpFolderSystem,new SelOpeningReadOnlyFile(this)));
        readOnlyFormTabEditor = new ReadOnlyFormTabEditor(_d,_d.getCommonFrame().getFrames(), _d.getManageOptions().getOptions());
        staIncExc = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        staIncExc.add(conditional);
        staIncExc.add(count);
        staIncExc.add(_d.getCommonFrame().getFrames().getCompoFactory().newHorizontalSplitPane(_d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(bpFolderSystem),readOnlyFormTabEditor.getPanel()));
        bpAddFile = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add include");
        bpAddFile.addActionListener(new AddIncludeEvent(this,_d));
        staIncExc.add(bpAddFile);
        bpRemoveFile = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("add exclude");
        bpRemoveFile.addActionListener(new AddExcludeEvent(this,_d));
        staIncExc.add(bpRemoveFile);
        includedFileIndex = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        excludedFileIndex = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        staIncExc.add(includedFileIndex);
        staIncExc.add(excludedFileIndex);
        staScIncExc = _d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(staIncExc);
        return staScIncExc;
    }
    public void refresh(StringMap<String> _files, String _folderToVisit) {
        AbsDebuggerGui.refreshList(bpFolderSystem.selectEvt(),_files, _folderToVisit);
    }

    public void actualiseLists(AbsDebuggerGui _d) {
        includedFileIndex.removeAll();
        excludedFileIndex.removeAll();
        for (LinkedNamedArgParts l: getMustBe()) {
            AbsPlainButton r_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("+ "+l.getFile().getFileName() + ":" + l.getOffset());
            r_.addActionListener(new RemoveIncludeEvent(this,_d, l));
            includedFileIndex.add(r_);
        }
        for (LinkedNamedArgParts l: getMustNotBe()) {
            AbsPlainButton r_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("- "+l.getFile().getFileName() + ":" + l.getOffset());
            r_.addActionListener(new RemoveExcludeEvent(this,_d, l));
            excludedFileIndex.add(r_);
        }
        border();
        _d.getCommonFrame().pack();
    }

    private void border() {
        for (LinkedNamedArgParts l: getMustBe()) {
            for (LinkedNamedArgParts m: getMustNotBe()) {
                if (match(l,m)) {
                    staIncExc.setLineBorder(GuiConstants.RED);
                    return;
                }
            }
        }
        staIncExc.setLineBorder(GuiConstants.GREEN);
    }

    public AbsScrollPane getStaScIncExc() {
        return staScIncExc;
    }

    public AbsPanel getIncludedFileIndex() {
        return includedFileIndex;
    }

    public AbsPanel getExcludedFileIndex() {
        return excludedFileIndex;
    }

    public CustList<LinkedNamedArgParts> getMustBe() {
        return mustBe;
    }

    public CustList<LinkedNamedArgParts> getMustNotBe() {
        return mustNotBe;
    }

    public AbsPlainButton getBpAddFile() {
        return bpAddFile;
    }

    public AbsPlainButton getBpRemoveFile() {
        return bpRemoveFile;
    }

    public AbsTreeGui getBpFolderSystem() {
        return bpFolderSystem;
    }

    public ReadOnlyFormTabEditor getReadOnlyFormTabEditor() {
        return readOnlyFormTabEditor;
    }

    public AbsTextArea getConditional() {
        return conditional;
    }

    public AbsSpinner getCount() {
        return count;
    }
}
