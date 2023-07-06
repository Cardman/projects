package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.util.CustList;

public final class BreakPointBlockList {
    private final AbsCollection<BreakPointBlockPair> list;
    private final AbsCollection<BreakPointBlockPair> listTmp;
    private final AbstractInterceptorStdCaller interceptor;
    private final AbsAtBool pausedLoop;
    private final AbsCollection<WatchPointBlockPair> watchList;

    public BreakPointBlockList(AbstractInterceptorStdCaller _i) {
        interceptor = _i;
        listTmp = _i.newBreakPointKeyStringCollection();
        list = _i.newBreakPointKeyStringCollection();
        watchList = _i.newWatchPointKeyStringCollection();
        pausedLoop = _i.newAtBool();
    }

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
        BreakPoint v_ = new BreakPoint(interceptor);
        v_.setEnabled(true);
        v_.setEnabledChgtType(_enType);
        BreakPointBlockPair pair_ = new BreakPointBlockPair(_file, _offset, v_);
        int i_ = 0;
        for (BreakPointBlockPair b: list.elts()) {
            if (b.match(pair_)) {
                list.remove(i_,b);
                return;
            }
            i_++;
        }
        list.add(pair_);
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
        BreakPoint v_ = new BreakPoint(interceptor);
        v_.setEnabled(true);
        v_.setEnabledChgtType(_enType);
        BreakPointBlockPair pair_ = new BreakPointBlockPair(_file, _offset, v_);
        for (BreakPointBlockPair b: list.elts()) {
            if (b.match(pair_)) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        list.add(pair_);
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
    public static void breakPointFileIndexUpdaterExcludeStd(BreakPointBlockPair _bp,CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterExcludeStd().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterExcludeInstance(BreakPointBlockPair _bp, CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterExcludeInstance().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterExcludeStatic(BreakPointBlockPair _bp, CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterExcludeStatic().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterIncludeStd(BreakPointBlockPair _bp,CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterIncludeStd().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterIncludeInstance(BreakPointBlockPair _bp, CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterIncludeInstance().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterIncludeStatic(BreakPointBlockPair _bp, CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterIncludeStatic().update(_bp.getValue(),_newValue);
    }
    public static void breakPointCountStd(BreakPointBlockPair _bp,int _newValue) {
        new BreakPointCountUpdaterStd().update(_bp.getValue(),_newValue);
    }
    public static void breakPointCountInstance(BreakPointBlockPair _bp, int _newValue) {
        new BreakPointCountUpdaterInstance().update(_bp.getValue(),_newValue);
    }
    public static void breakPointCountStatic(BreakPointBlockPair _bp, int _newValue) {
        new BreakPointCountUpdaterStatic().update(_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxStd(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterStd(_f,_gene).update(ExecFileBlock.name(_bp.getFile()),_bp.getOffset(),_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxInstance(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterInstance(_f,_gene).update(ExecFileBlock.name(_bp.getFile()),_bp.getOffset(),_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxStatic(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterStatic(_f,_gene).update(ExecFileBlock.name(_bp.getFile()),_bp.getOffset(),_bp.getValue(),_newValue);
    }

    public void update(ExecFileBlock _file, int _offset, BreakPointBooleanUpdater _updater, boolean _newValue) {
        for (BreakPointBlockPair b: list.elts()) {
            if (b.match(_file, _offset)) {
                _updater.update(b.getValue(), _newValue);
                return;
            }
        }
    }
    public boolean is(ExecFileBlock _file, int _offset) {
        return getNotNull(_file, _offset).isEnabled();
    }
    public BreakPoint getNotNull(ExecFileBlock _file, int _offset) {
        BreakPointBlockPair b_ = getPair(_file, _offset);
        if (b_ == null) {
            BreakPoint bp_ = new BreakPoint(interceptor);
            bp_.setEnabled(false);
            return bp_;
        }
        return b_.getValue();
    }

    public BreakPointBlockPair getPair(ExecFileBlock _file, int _offset) {
        for (BreakPointBlockPair b: list.elts()) {
            if (b.match(_file, _offset)) {
                return b;
            }
        }
        return null;
    }
    public CustList<BreakPointBlockPair> bp(ExecFileBlock _file, FileMetrics _ana, int _off) {
        int r_ = _ana.getRowFile(_off);
        CustList<BreakPointBlockPair> list_ = new CustList<BreakPointBlockPair>();
        for (BreakPointBlockPair b: list.elts()) {
            if (b.matchRow(_file, _ana, r_)) {
                list_.add(b);
            }
        }
        return list_;
    }
    public void resetList() {
        for (BreakPointBlockPair b: list.elts()) {
            b.getValue().resetCount();
        }
    }

    public boolean isTmp(ExecFileBlock _file, int _offset) {
        for (BreakPointBlockPair l: listTmp.elts()) {
            if (l.match(_file, _offset)) {
                return true;
            }
        }
        return false;
    }

    public void toggleWatchPoint(String _file, int _offset, ResultContext _f) {
        ClassField o_ = ResultExpressionOperationNode.vexerChamps(_f.getPageEl(), _file, _offset);
        if (o_.getClassName().isEmpty()) {
            return;
        }
        toggleWatch(o_);
    }
    public void toggleWatch(ClassField _field) {
        WatchPoint v_ = new WatchPoint();
        v_.setEnabled(true);
        v_.setRead(true);
        v_.setWrite(true);
        v_.setCompoundRead(true);
        v_.setCompoundWrite(true);
        WatchPointBlockPair pair_ = new WatchPointBlockPair(_field, v_);
        int i_ = 0;
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.match(pair_)) {
                watchList.remove(i_,b);
                return;
            }
            i_++;
        }
        watchList.add(pair_);
    }
    public void toggleWatchPointEnabled(String _file, int _offset, ResultContext _f) {
        ClassField o_ = ResultExpressionOperationNode.vexerChamps(_f.getPageEl(), _file, _offset);
        if (o_.getClassName().isEmpty()) {
            return;
        }
        toggleEnabledWatch(o_);
    }
    public void toggleEnabledWatch(ClassField _field) {
        WatchPoint v_ = new WatchPoint();
        v_.setEnabled(true);
        v_.setRead(true);
        v_.setWrite(true);
        v_.setCompoundRead(true);
        v_.setCompoundWrite(true);
        WatchPointBlockPair pair_ = new WatchPointBlockPair(_field, v_);
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.match(pair_)) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        watchList.add(pair_);
    }
    public boolean isWatch(ClassField _field) {
        return getNotNullWatch(_field).isEnabled();
    }
    public WatchPoint getNotNullWatch(ClassField _field) {
        WatchPointBlockPair b_ = getPairWatch(_field);
        if (b_ == null) {
            WatchPoint bp_ = new WatchPoint();
            bp_.setEnabled(false);
            return bp_;
        }
        return b_.getValue();
    }

    public WatchPointBlockPair getPairWatch(ClassField _field) {
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.match(_field)) {
                return b;
            }
        }
        return null;
    }
    public AbsCollection<BreakPointBlockPair> getListTmp() {
        return listTmp;
    }

    public AbsAtBool getPausedLoop() {
        return pausedLoop;
    }
}
