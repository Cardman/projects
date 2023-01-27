package code.gui;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Ints;

public final class CustGraphicListStruct extends InputStruct implements GraphicListIntStruct {

    private Struct render = NullStruct.NULL_VALUE;

    private final AbsGraphicListStr cuGr;

    public CustGraphicListStruct(GuiContextEl _ctx, String _className, boolean _simple) {
        super(_className);
        cuGr = GraphicListStruct.init(_ctx,_simple);
        init(_ctx);
    }

    private void init(GuiContextEl _ctx) {
        cuGr.setDefCell(this, new DefSpecSelectionCtx(_ctx.getExecutionInfos(),_ctx.getArgs()));
    }

    @Override
    public void add(GuiAliases _aliases, ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall, Struct _elt) {
        add(_aliases, _cont, _guiEx, _stackCall,cuGr.size(), _elt);
    }

    public void add(int _index, Struct _img, Struct _elt) {
        if (!(_img instanceof PreparedLabelStruct)) {
            return;
        }
        AbsPreparedLabel textLabel_ = ((PreparedLabelStruct) _img).getTextLabel();
        cuGr.add(_index,textLabel_, _elt);
    }
    public void add(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,int _index, Struct _elt) {
        cuGr.add(_index, _elt);
        Argument arg_ = new Argument(this);
        CustList<Argument> args_ = new CustList<Argument>(arg_);
        args_.add(new Argument(new IntStruct(_index)));
        args_.add(new Argument(_elt));
        wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintAdd(), _stackCall);
    }
    public void set(int _index, Struct _img, Struct _elt) {
        if (!(_img instanceof PreparedLabelStruct)) {
            return;
        }
        if (!cuGr.getList().isValidIndex(_index)) {
            return;
        }
        PreparedLabelStruct img_ = (PreparedLabelStruct) _img;
        AbsPreparedLabel textLabel_ = img_.getTextLabel();
        cuGr.set(_index, textLabel_,_elt);
    }
    public void set(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,int _index, Struct _elt) {
        if (!cuGr.getList().isValidIndex(_index)) {
            Argument arg_ = new Argument(this);
            CustList<Argument> args_ = new CustList<Argument>(arg_);
            args_.add(new Argument(new IntStruct(_index)));
            args_.add(new Argument(_elt));
            wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintSet(), _stackCall);
            return;
        }
        cuGr.set(_index,_elt);
        Argument arg_ = new Argument(this);
        CustList<Argument> args_ = new CustList<Argument>(arg_);
        args_.add(new Argument(new IntStruct(_index)));
        args_.add(new Argument(_elt));
        wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintSet(), _stackCall);
    }

    public void setSelectedIndexes(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,Struct _selectedIndexes) {
        Ints selectedIndexes_ = new Ints();
        if (_selectedIndexes instanceof ArrayStruct) {
            for (Struct s : ((ArrayStruct)_selectedIndexes).list()) {
                selectedIndexes_.add(((NumberStruct) s).intStruct());
            }
            cuGr.setSelectedIndexes(selectedIndexes_);
        }
        if (_selectedIndexes instanceof ArrayStruct) {
            Argument arg_ = new Argument(this);
            CustList<Argument> args_ = new CustList<Argument>(arg_);
            wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintRefresh(), _stackCall);
        }
    }

    public void clearSelection(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall) {
        cuGr.getSelectedIndexes().clear();
        Argument arg_ = new Argument(this);
        CustList<Argument> args_ = new CustList<Argument>(arg_);
        wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintRefresh(), _stackCall);
    }
    private static void wrapAndCall(GuiAliases _aliases, ContextEl _cont, CustList<Argument> _args, ExecTypeFunction _pair, StackCall _stackCall) {
        ArgumentListCall argList_ = ArgumentListCall.wrapCall(_args);
        ExecTemplates.wrapAndCall(_pair, new ExecFormattedRootBlock(_pair.getType(), _aliases.getAliasPaint()),Argument.createVoid(), _cont, _stackCall, argList_);
    }

    public Struct getRender() {
        return render;
    }

    @Override
    public void setRender(Struct _r) {
        this.render = _r;
    }

    @Override
    protected AbsCustComponent getVisibleComponent() {
        return cuGr.visible();
    }

    @Override
    protected AbsCustComponent getComponent() {
        return cuGr.scroll();
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(cuGr.isEnabled());
    }

    public boolean isEnabledList() {
        return cuGr.isEnabled();
    }
    @Override
    public void setEnabled(Struct _enabled) {
        cuGr.setEnabled(BooleanStruct.isTrue(_enabled));
    }

    public AbsGraphicListStr getGrList() {
        return cuGr;
    }
}
