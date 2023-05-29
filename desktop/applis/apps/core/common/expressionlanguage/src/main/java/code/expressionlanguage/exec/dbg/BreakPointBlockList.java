package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;
import code.util.Ints;

public final class BreakPointBlockList {
    private final CustList<BreakPointBlockPair> list = new CustList<BreakPointBlockPair>();
    public void toggleBreakPoint(String _file, int _offset, ResultContext _f) {
        toggleBreakPoint(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg());
    }
    public void toggleBreakPoint(FileBlock _file, int _offset, DebugMapping _d) {
        Ints o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_.size() < 2) {
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        toggle(f_,o_.get(0),o_.get(1));
    }
    public void toggle(ExecFileBlock _file, int _m, int _o) {
        BreakPointBlockKey b_ = new BreakPointBlockKey(_file, _m, _o);
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
        Ints o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_.size() < 2) {
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        toggleEnabled(f_,o_.get(0),o_.get(1));
    }
    public void toggleEnabled(ExecFileBlock _file, int _m, int _o) {
        BreakPointBlockKey b_ = new BreakPointBlockKey(_file, _m, _o);
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
    public BreakPoint get(ExecFileBlock _file, int _main, int _offset) {
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            BreakPointBlockPair p_ = list.get(i);
            if (p_.getKey().match(_file, _main, _offset)) {
                return p_.getValue();
            }
        }
        return null;
    }
}
