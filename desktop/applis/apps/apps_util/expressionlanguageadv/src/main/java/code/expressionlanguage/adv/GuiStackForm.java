package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.exec.dbg.ExecFileBlockFct;
import code.expressionlanguage.exec.dbg.ExecFileBlockTraceIndex;
import code.expressionlanguage.options.ResultContext;
import code.gui.*;
import code.util.CustList;
import code.util.StringMap;

public final class GuiStackForm {
    private AbsCustCheckBox hit;
    private AbsCustCheckBox enabledSub;
    private AbsCustCheckBox disabledWhenHit;
    private AbsTextArea conditional;
    private AbsSpinner count;
    private AbsSpinner countSub;
    private ReadOnlyFormTabEditor readOnlyFormTabEditor;
    private AbsPlainButton bpAddFile;
    private AbsPlainButton bpRemoveFile;
    private AbsCustCheckBox singleCaret;
    private AbsTreeGui bpFolderSystem;
    private AbsPanel includedFileIndex;
    private AbsPanel excludedFileIndex;
    private AbsPanel staIncExc;
    private AbsScrollPane staScIncExc;
    private final CustList<AbsCallContraints> mustBe = new CustList<AbsCallContraints>();
    private final CustList<AbsCallContraints> mustNotBe = new CustList<AbsCallContraints>();

    public void add(ResultContext _res, CustList<AbsCallContraints> _list, ReadOnlyFormTabEditor _e) {
        FileBlock v_ = _res.getPageEl().getPreviousFilesBodies().getVal(_e.getFullPath());
        ExecFileBlock f_ = _res.getForwards().dbg().getFiles().getVal(v_);
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

    public AbsScrollPane guiBuild(AbsDebuggerGui _d) {
        enabledSub = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("specific enabled");
        enabledSub.setSelected(true);
        hit = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("hit");
        disabledWhenHit = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("disabled when hit");
        conditional = _d.getCommonFrame().getFrames().getCompoFactory().newTextArea();
        count = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        countSub = _d.getCommonFrame().getFrames().getCompoFactory().newSpinner(0, 0, Integer.MAX_VALUE, 1);
        bpFolderSystem = _d.getCommonFrame().getFrames().getCompoFactory().newTreeGui(_d.getCommonFrame().getFrames().getCompoFactory().newMutableTreeNode(""));
        bpFolderSystem.select(bpFolderSystem.getRoot());
        bpFolderSystem.addTreeSelectionListener(new ShowSrcReadOnlyTreeEvent(_d,bpFolderSystem,new SelOpeningReadOnlyFile(this)));
        readOnlyFormTabEditor = new ReadOnlyFormTabEditor(_d,_d.getCommonFrame().getFrames(), _d.getManageOptions().getOptions());
        staIncExc = _d.getCommonFrame().getFrames().getCompoFactory().newPageBox();
        staIncExc.add(enabledSub);
        staIncExc.add(hit);
        staIncExc.add(disabledWhenHit);
        staIncExc.add(conditional);
        staIncExc.add(count);
        staIncExc.add(countSub);
        staIncExc.add(_d.getCommonFrame().getFrames().getCompoFactory().newHorizontalSplitPane(_d.getCommonFrame().getFrames().getCompoFactory().newAbsScrollPane(bpFolderSystem),readOnlyFormTabEditor.getPanel()));
        singleCaret = _d.getCommonFrame().getFrames().getCompoFactory().newCustCheckBox("single");
        singleCaret.setSelected(true);
        staIncExc.add(singleCaret);
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
        for (AbsCallContraints l: getMustBe()) {
            AbsPlainButton r_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("+ "+l.valueStr());
            r_.addActionListener(new RemoveIncludeEvent(this,_d, l));
            includedFileIndex.add(r_);
        }
        for (AbsCallContraints l: getMustNotBe()) {
            AbsPlainButton r_ = _d.getCommonFrame().getFrames().getCompoFactory().newPlainButton("- "+l.valueStr());
            r_.addActionListener(new RemoveExcludeEvent(this,_d, l));
            excludedFileIndex.add(r_);
        }
        border();
        _d.getCommonFrame().pack();
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

    public AbsScrollPane getStaScIncExc() {
        return staScIncExc;
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

    public AbsCustCheckBox getSingleCaret() {
        return singleCaret;
    }

    public AbsCustCheckBox getEnabledSub() {
        return enabledSub;
    }

    public AbsCustCheckBox getHit() {
        return hit;
    }

    public AbsCustCheckBox getDisabledWhenHit() {
        return disabledWhenHit;
    }

    public AbsTextArea getConditional() {
        return conditional;
    }

    public AbsSpinner getCount() {
        return count;
    }

    public AbsSpinner getCountSub() {
        return countSub;
    }
}
