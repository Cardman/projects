package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.AbsBasicGraphicList;
import code.gui.Interval;
import code.util.CustList;

public final class AdvGraphicListPainter extends AbsAdvGraphicListPainter {

    public AdvGraphicListPainter(CommonExecutionInfos _executionInfos) {
        super(_executionInfos);
    }

    @Override
    public void selectPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
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
    public boolean selectOneAmongIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return false;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return false;
        }
        grList_.setFirstIndex(_index);
        if (!_sel) {
            grList_.getSelectedIndexes().removeObj(_index);
        } else {
            grList_.getSelectedIndexes().add(_index);
            grList_.getSelectedIndexes().removeDuplicates();
        }
        GuiContextEl ctx_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(value_));
        invokePaint(ctx_, args_);
        return true;
    }

    @Override
    public void afterSelectOneAmongIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return;
        }
        if (grList_.getListComponents().isValidIndex(_index)) {
            grList_.getListComponents().get(_index).requestFocus();
        }
    }

    @Override
    public Interval selectIntervalPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return null;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return null;
        }
        int firstIndex_ = grList_.getFirstIndex();
        int min_ = Math.min(firstIndex_, _index);
        int max_ = Math.max(firstIndex_, _index);
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
        return new Interval(min_,max_);
    }

    @Override
    public Interval selectIntervalKeyPaint(AbsBasicGraphicList _list, boolean _sel, int _index) {
        Struct value_ = getValue();
        if (!(value_ instanceof GraphicListStruct)) {
            return null;
        }
        GraphicListStruct grList_ = (GraphicListStruct) value_;
        if (!grList_.isEnabledList()) {
            return null;
        }
        int min_ = 0;
        int max_ = grList_.getListComponents().size() - 1;
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
        RunnableStruct.invoke(arg_, stds_.getGuiAliases().getAliasPaint(), _args, _r,pair_, StackCall.newInstance(InitPhase.NOTHING,_r));
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(InitPhase.NOTHING, getExecutionInfos());
        RunnableStruct.setupThread(r_);
        return r_;
    }
}
