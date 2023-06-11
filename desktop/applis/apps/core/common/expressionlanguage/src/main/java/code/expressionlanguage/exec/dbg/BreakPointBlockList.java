package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.FileMetrics;
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
        toggle(f_,o_, ResultExpressionOperationNode.enabledTypeBp(_offset, _file));
    }
    public void toggle(ExecFileBlock _file, int _offset, boolean _enType) {
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
        v_.setEnabledChgtType(_enType);
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
        toggleEnabled(f_,o_, ResultExpressionOperationNode.enabledTypeBp(_offset, _file));
    }
    public void toggleEnabled(ExecFileBlock _file, int _offset, boolean _enType) {
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
        v_.setEnabledChgtType(_enType);
        list.add(new BreakPointBlockPair(b_, v_));
    }
    public void breakPointEnabled(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterEnabled(),_newValue);
    }
    public void breakPointInstanceType(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterInstanceType(),_newValue);
    }
    public void breakPointStaticType(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterStaticType(),_newValue);
    }
    public void breakPointUpdate(String _file, int _offset, ResultContext _f, BreakPointBooleanUpdater _updater, boolean _newValue) {
        breakPointUpdate(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg(), _updater,_newValue);
    }
    public void breakPointUpdate(FileBlock _file, int _offset, DebugMapping _d, BreakPointBooleanUpdater _updater, boolean _newValue) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        update(f_,o_, _updater,_newValue);
    }

    public void update(ExecFileBlock _file, int _offset, BreakPointBooleanUpdater _updater, boolean _newValue) {
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            if (list.get(i).getKey().match(_file, _offset)) {
                _updater.update(list.get(i).getValue(), _newValue);
                return;
            }
        }
    }
    public boolean is(ExecFileBlock _file, int _offset) {
        return getNotNull(_file, _offset).isEnabled();
    }
    public BreakPoint getNotNull(ExecFileBlock _file, int _offset) {
        BreakPoint b_ = get(_file, _offset);
        if (b_ == null) {
            BreakPoint bp_ = new BreakPoint();
            bp_.setEnabled(false);
            return bp_;
        }
        return b_;
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
    public CustList<BreakPointBlockPair> bp(ExecFileBlock _file, FileMetrics _ana, int _off) {
        int r_ = _ana.getRowFile(_off);
        CustList<BreakPointBlockPair> list_ = new CustList<BreakPointBlockPair>();
        int len_ = list.size();
        for (int i = 0; i < len_; i++) {
            BreakPointBlockPair p_ = list.get(i);
            if (p_.getKey().matchRow(_file, _ana, r_)) {
                list_.add(p_);
            }
        }
        return list_;
    }
}
