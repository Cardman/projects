package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;

public final class BreakPointBlockList {
    private final CustList<BreakPointBlockPair> list = new CustList<BreakPointBlockPair>();
    public void toggleBreakPoint(String _file, int _offset, ResultContext _f) {
        toggleBreakPoint(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg());
    }
    public void toggleBreakPoint(FileBlock _file, int _offset, DebugMapping _d) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        toggle(f_,o_);
    }
    public void toggle(ExecFileBlock _file, int _offset) {
        BreakPointBlockKey b_ = new BreakPointBlockKey(_file, _offset);
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (list.get(i).getKey().match(b_)) {
                list.remove(i);
                return;
            }
        }
        BreakPoint v_ = new BreakPoint();
        v_.setEnabled(true);
        list.add(new BreakPointBlockPair(b_, v_));
    }
    public void toggleBreakPointEnabled(String _file, int _offset, ResultContext _f) {
        toggleBreakPointEnabled(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg());
    }
    public void toggleBreakPointEnabled(FileBlock _file, int _offset, DebugMapping _d) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        toggleEnabled(f_,o_);
    }
    public void toggleEnabled(ExecFileBlock _file, int _offset) {
        BreakPointBlockKey b_ = new BreakPointBlockKey(_file, _offset);
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (list.get(i).getKey().match(b_)) {
                list.get(i).getValue().setEnabled(!list.get(i).getValue().isEnabled());
                return;
            }
        }
        BreakPoint v_ = new BreakPoint();
        v_.setEnabled(true);
        list.add(new BreakPointBlockPair(b_, v_));
    }
    public boolean is(ExecFileBlock _file, int _offset) {
        BreakPoint b_ = get(_file, _offset);
        return b_ != null && b_.isEnabled();
    }
    public BreakPoint get(ExecFileBlock _file, int _offset) {
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            BreakPointBlockPair p_ = list.get(i);
            if (p_.getKey().match(_file, _offset)) {
                return p_.getValue();
            }
        }
        return null;
    }
}
