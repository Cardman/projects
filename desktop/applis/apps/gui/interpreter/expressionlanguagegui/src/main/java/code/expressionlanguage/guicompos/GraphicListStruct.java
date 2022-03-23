package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.util.CustList;
import code.util.Ints;

public final class GraphicListStruct extends InputStruct {

    private Struct render = NullStruct.NULL_VALUE;

    private final AbsGraphicListStr grList;

    public GraphicListStruct(GuiContextEl _ctx,String _className,boolean _simple) {
        super(_className);
        grList = init(_ctx,_simple);
        init(_ctx);
    }
    private static AbsGraphicListStr init(GuiContextEl _ctx,boolean _simple) {
        GuiExecutingBlocks guiEx_ = ((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks();
        AbstractAdvGraphicListGenerator graphicListGenerator_ = guiEx_.getGraphicListGenerator();
        AdvGraphicListPainter abs_ = new AdvGraphicListPainter(guiEx_.getImageFactory(), _ctx.getExecutionInfos());
        DefSpecSelectionCtx create_ = new DefSpecSelectionCtx(_ctx.getExecutionInfos());
        if (_simple) {
            return graphicListGenerator_.createSimple(abs_, create_);
        }
        return graphicListGenerator_.createMult(abs_, create_);
    }

    private void init(GuiContextEl _ctx) {
        grList.setDefCell(this, new DefSpecSelectionCtx(_ctx.getExecutionInfos()));
    }

    public boolean isCust() {
        return grList instanceof AbsCustGraphicListStr;
    }
    public void add(int _index, Struct _img, Struct _elt) {
        if (!(_img instanceof PreparedLabelStruct)) {
            return;
        }
        AbsPreparedLabel textLabel_ = ((PreparedLabelStruct) _img).getTextLabel();
        grList.add(_index,textLabel_, _elt);
    }
    public void add(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,int _index, Struct _elt) {
        grList.add(_index, _elt);
        if (isCust()) {
            return;
        }
        Argument arg_ = new Argument(this);
        CustList<Argument> args_ = new CustList<Argument>(arg_);
        args_.add(new Argument(new IntStruct(_index)));
        args_.add(new Argument(_elt));
        wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintAdd(), _stackCall);
    }
    public void updateGraphics() {
        grList.updateGraphics();
    }
    public void set(int _index, Struct _img, Struct _elt) {
        if (!(_img instanceof PreparedLabelStruct)) {
            return;
        }
        if (!grList.getList().isValidIndex(_index)) {
            return;
        }
        PreparedLabelStruct img_ = (PreparedLabelStruct) _img;
        AbsPreparedLabel textLabel_ = img_.getTextLabel();
        grList.set(_index, textLabel_,_elt);
    }
    public void set(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,int _index, Struct _elt) {
        if (!grList.getList().isValidIndex(_index)) {
            if (isCust()) {
                return;
            }
            Argument arg_ = new Argument(this);
            CustList<Argument> args_ = new CustList<Argument>(arg_);
            args_.add(new Argument(new IntStruct(_index)));
            args_.add(new Argument(_elt));
            wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintSet(), _stackCall);
            return;
        }
        grList.set(_index,_elt);
        if (isCust()) {
            return;
        }
        Argument arg_ = new Argument(this);
        CustList<Argument> args_ = new CustList<Argument>(arg_);
        args_.add(new Argument(new IntStruct(_index)));
        args_.add(new Argument(_elt));
        wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintSet(), _stackCall);
    }
    public ArrayStruct getListView(ContextEl _ctx) {
        CustList<Struct> list_ = grList.getList();
        int len_ = list_.size();
        String obj_ = StringExpUtil.getPrettyArrayType(_ctx.getStandards().getContent().getCoreNames().getAliasObject());
        ArrayStruct arr_ = new ArrayStruct(len_, obj_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, list_.get(i));
        }
        return arr_;
    }

    public Ints getSelectedIndexes() {
        return grList.getSelectedIndexes();
    }

    public void setSelectedIndexes(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,Struct _selectedIndexes) {
        Ints selectedIndexes_ = new Ints();
        if (_selectedIndexes instanceof ArrayStruct) {
            for (Struct s : ((ArrayStruct)_selectedIndexes).list()) {
                selectedIndexes_.add(((NumberStruct) s).intStruct());
            }
            grList.setSelectedIndexes(selectedIndexes_);
        }
        if (!isCust()&&_selectedIndexes instanceof ArrayStruct) {
            Argument arg_ = new Argument(this);
            CustList<Argument> args_ = new CustList<Argument>(arg_);
            wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintRefresh(), _stackCall);
        }
    }

    public void clearSelection(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall) {
        if (isCust()) {
            grList.clearSelection();
            return;
        }
        grList.getSelectedIndexes().clear();
        Argument arg_ = new Argument(this);
        CustList<Argument> args_ = new CustList<Argument>(arg_);
        wrapAndCall(_aliases,_cont, args_, _guiEx.getPairPaintRefresh(), _stackCall);
    }
    private static void wrapAndCall(GuiAliases _aliases, ContextEl _cont, CustList<Argument> _args, ExecTypeFunction _pair, StackCall _stackCall) {
        ArgumentListCall argList_ = new ArgumentListCall(_args);
        ExecTemplates.wrapAndCall(_pair, new ExecFormattedRootBlock(_pair.getType(), _aliases.getAliasPaint()),Argument.createVoid(), _cont, _stackCall, argList_);
    }
    public ArrayStruct getSelectedIndexes(ContextEl _ctx) {
        Ints selectedIndexes_ = grList.getSelectedIndexes();
        int len_ = selectedIndexes_.size();
        String obj_ = StringExpUtil.getPrettyArrayType(_ctx.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        ArrayStruct arr_ = new ArrayStruct(len_, obj_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, new IntStruct(selectedIndexes_.get(i)));
        }
        return arr_;
    }

    public Struct getVisibleRowCount() {
        return new IntStruct(grList.getVisibleRowCount());
    }

    public void setVisibleRowCount(Struct _visibleRowCount) {
        int value_ = ((NumberStruct)_visibleRowCount).intStruct();
        grList.setVisibleRowCount(value_);
    }

    public void clear() {
        grList.clear();
    }
    public void remove(int _index) {
        grList.remove(_index);
    }
    public ArrayStruct getListeners(ContextEl _ctx) {
        CustList<ListSelection> listeners_ = grList.getListeners();
        String aliasListSelection_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasListSelection();
        int len_ = listeners_.size();
        ArrayStruct out_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(aliasListSelection_));
        for (int i = 0; i < len_; i++) {
            if (listeners_.get(i) instanceof Struct) {
                out_.set(i,(Struct)listeners_.get(i));
            }
        }
        return out_;
    }
    public void removeListener(Struct _listener) {
        if (_listener instanceof ListSelection) {
            grList.removeListener((ListSelection)_listener);
        }
    }
    public void addListener(Struct _listener) {
        if (_listener instanceof ListSelection) {
            grList.addListener((ListSelection)_listener);
        }
    }

    public void addRange(int _first, int _last) {
        int min_ = Math.min(_first, _last);
        int max_ = Math.max(_first, _last);
        for (int i = min_; i <= max_; i++) {
            grList.getSelectedIndexes().add(i);
        }
    }
    public void clearAllRange() {
        grList.getSelectedIndexes().clear();
    }

    public void clearRange(int _first, int _last) {
        int min_ = Math.min(_first, _last);
        int max_ = Math.max(_first, _last);
        for (int i = min_; i <= max_; i++) {
            grList.getSelectedIndexes().removeObj(i);
        }
    }

    public Struct getRender() {
        return render;
    }

    public void setRender(GuiContextEl _ctx, Struct _render) {
        this.render = _render;
        DefSpecSelectionCtx create_ = new DefSpecSelectionCtx(_ctx.getExecutionInfos());
        if (_render instanceof RenderStruct) {
            RenderStruct rend_ = (RenderStruct) _render;
            Struct paint_ = rend_.getPaint();
            if (paint_ instanceof LambdaStruct) {
                String aliasImageLabel_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasImageLabel();
                PreparedLabelStruct im_ = new PreparedLabelStruct(((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getImageFactory(),aliasImageLabel_);
                AbsPreparedLabel lab_ = im_.getTextLabel();
                grList.setCustCell(this, lab_, im_,
                        new DefSpecSelectionStruct(_ctx, this)
                );
            } else {
                grList.setDefCell(this, create_);
            }
        } else {
            grList.setDefCell(this, create_);
        }
    }

    public boolean isEnabledList() {
        return grList.isEnabled();
    }

    @Override
    protected AbsCustComponent getVisibleComponent() {
        return grList.visible();
    }

    @Override
    protected AbsCustComponent getComponent() {
        return grList.scroll();
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(grList.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        grList.setEnabled(BooleanStruct.isTrue(_enabled));
    }
}
