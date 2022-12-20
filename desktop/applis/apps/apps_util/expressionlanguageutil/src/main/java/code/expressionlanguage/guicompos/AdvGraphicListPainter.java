package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.AbsGraphicListDefBase;
import code.gui.AbsPreparedLabel;
import code.gui.Interval;
import code.gui.images.AbstractImageFactory;
import code.util.CustList;
import code.util.StringList;

public final class AdvGraphicListPainter extends AbsAdvGraphicListPainter {

    private final StringList args;
    public AdvGraphicListPainter(AbstractImageFactory _fact, CommonExecutionInfos _executionInfos, StringList _args) {
        super(_fact,_executionInfos);
        args = _args;
    }

    @Override
    public void selectPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return;
        }
        grList_.getSelectedIndexes().clear();
        if (_sel) {
            grList_.getSelectedIndexes().add(_index);
        }
        GuiContextEl ctx_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(value_));
        invokePaint(ctx_, args_);
    }

    @Override
    public boolean selectOneAmongIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return false;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return false;
        }
        _list.selectOneAmongIntervalPaintBase(_sel, _index);
        GuiContextEl ctx_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(value_));
        invokePaint(ctx_, args_);
        return true;
    }

    @Override
    public AbsPreparedLabel selectedOneAmongIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return null;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return null;
        }
        if (_list.getListComponents().isValidIndex(_index)) {
            return _list.getListComponents().get(_index);
        }
        return null;
    }

    @Override
    public Interval selectIntervalPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return null;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return null;
        }
        Interval interval_ = _list.selectIntervalPaintBase(_sel, _index);
        GuiContextEl ctx_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(value_));
        invokePaint(ctx_,args_);
        return interval_;
    }

    @Override
    public Interval selectIntervalKeyPaint(AbsGraphicListDefBase _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return null;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return null;
        }
        int min_ = 0;
        int max_ = _list.getListComponents().size() - 1;
        if (!_sel) {
            for (int i = min_; i <= max_; i++) {
                grList_.getSelectedIndexes().removeObj(i);
            }
        } else {
            for (int i = min_; i <= max_; i++) {
                grList_.getSelectedIndexes().add(i);
            }
            grList_.getSelectedIndexes().removeDuplicates();
        }
        GuiContextEl ctx_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(value_));
        invokePaint(ctx_,args_);
        if (!_sel) {
            grList_.clearRange(0,max_);
        } else {
            grList_.addRange(0,max_);
        }
        return new Interval(0, max_);
    }

    private static void invokePaint(GuiContextEl _r, CustList<Argument> _args) {
        Argument arg_ = new Argument();
        ExecTypeFunction pair_ = ((LgNamesGui) _r.getStandards()).getGuiExecutingBlocks().getPairPaintRefresh();
        LgNamesGui stds_ = (LgNamesGui) _r.getStandards();
        ArgumentListCall argList_ = ArgumentListCall.wrapCall(_args);
        RunnableStruct.invoke(arg_, new ExecFormattedRootBlock(pair_.getType(),stds_.getGuiAliases().getAliasPaint()), _r,pair_, StackCall.newInstance(InitPhase.NOTHING,_r), argList_);
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(null, getExecutionInfos(), args);
        RunnableStruct.setupThread(r_);
        return r_;
    }
}
