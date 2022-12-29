package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.util.CustList;
import code.util.Ints;

public final class GraphicListStruct extends InputStruct implements GraphicListIntStruct {

    private Struct render = NullStruct.NULL_VALUE;

    private final AbsGraphicListStr grList;

    public GraphicListStruct(GuiContextEl _ctx,String _className,boolean _simple) {
        super(_className);
        grList = init(_ctx,_simple);
        init(_ctx);
    }
    public static AbsGraphicListStr init(GuiContextEl _ctx,boolean _simple) {
        GuiExecutingBlocks guiEx_ = ((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks();
        AbstractAdvGraphicListGenerator graphicListGenerator_ = guiEx_.getGraphicListGenerator();
        DefSpecSelectionCtx create_ = new DefSpecSelectionCtx(_ctx.getExecutionInfos(),_ctx.getArgs());
        if (_simple) {
            return graphicListGenerator_.createSimple(create_,guiEx_.getCompoFactory());
        }
        return graphicListGenerator_.createMult(create_,guiEx_.getCompoFactory());
    }

    private void init(GuiContextEl _ctx) {
        grList.setDefCell(this, new DefSpecSelectionCtx(_ctx.getExecutionInfos(),_ctx.getArgs()));
    }

    public void add(int _index, Struct _img, Struct _elt) {
        grList.add(_index,null, _elt);
    }
    public void add(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,int _index, Struct _elt) {
        grList.add(_index, _elt);
    }
    public static void updateGraphics(AbsGraphicListStr _gr) {
        _gr.updateGraphics();
    }
    public void set(int _index, Struct _img, Struct _elt) {
        if (!grList.getList().isValidIndex(_index)) {
            return;
        }
        grList.set(_index, null,_elt);
    }
    public void set(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,int _index, Struct _elt) {
        if (!grList.getList().isValidIndex(_index)) {
            return;
        }
        grList.set(_index,_elt);
    }
    public static ArrayStruct getListView(ContextEl _ctx, AbsGraphicListStr _gr) {
        CustList<Struct> list_ = _gr.getList();
        int len_ = list_.size();
        String obj_ = StringExpUtil.getPrettyArrayType(_ctx.getStandards().getContent().getCoreNames().getAliasObject());
        ArrayStruct arr_ = new ArrayStruct(len_, obj_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, list_.get(i));
        }
        return arr_;
    }

    public void setSelectedIndexes(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall,Struct _selectedIndexes) {
        Ints selectedIndexes_ = new Ints();
        if (_selectedIndexes instanceof ArrayStruct) {
            for (Struct s : ((ArrayStruct)_selectedIndexes).list()) {
                selectedIndexes_.add(((NumberStruct) s).intStruct());
            }
            grList.setSelectedIndexes(selectedIndexes_);
        }
    }

    public void clearSelection(GuiAliases _aliases,ContextEl _cont, GuiExecutingBlocks _guiEx, StackCall _stackCall) {
        grList.clearSelection();
    }
    public static ArrayStruct getSelectedIndexes(ContextEl _ctx, AbsGraphicListStr _gr) {
        Ints selectedIndexes_ = _gr.getSelectedIndexes();
        int len_ = selectedIndexes_.size();
        String obj_ = StringExpUtil.getPrettyArrayType(_ctx.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        ArrayStruct arr_ = new ArrayStruct(len_, obj_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, new IntStruct(selectedIndexes_.get(i)));
        }
        return arr_;
    }

    public static Struct getVisibleRowCount(AbsGraphicListStr _gr) {
        return new IntStruct(_gr.getVisibleRowCount());
    }

    public static void setVisibleRowCount(Struct _visibleRowCount, AbsGraphicListStr _gr) {
        int value_ = ((NumberStruct)_visibleRowCount).intStruct();
        _gr.setVisibleRowCount(value_);
    }

    public static void clear(AbsGraphicListStr _gr) {
        _gr.clear();
    }
    public static void remove(int _index, AbsGraphicListStr _gr) {
        _gr.remove(_index);
    }
    public static ArrayStruct getListeners(ContextEl _ctx, AbsGraphicListStr _gr) {
        CustList<ListSelection> listeners_ = _gr.getListeners();
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
    public static void removeListener(Struct _listener, AbsGraphicListStr _gr) {
        if (_listener instanceof ListSelection) {
            _gr.removeListener((ListSelection)_listener);
        }
    }
    public static void addListener(Struct _listener, AbsGraphicListStr _gr) {
        if (_listener instanceof ListSelection) {
            _gr.addListener((ListSelection)_listener);
        }
    }

    public Struct getRender() {
        return render;
    }

    public void setRender(Struct _r) {
        this.render = _r;
    }

    public static void setRender(GuiContextEl _ctx, Struct _render, GraphicListIntStruct _cr) {
        _cr.setRender(_render);
        DefSpecSelectionCtx create_ = new DefSpecSelectionCtx(_ctx.getExecutionInfos(),_ctx.getArgs());
        if (_render instanceof RenderStruct) {
            RenderStruct rend_ = (RenderStruct) _render;
            Struct paint_ = rend_.getPaint();
            if (paint_ instanceof LambdaStruct) {
                String aliasImageLabel_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasImageLabel();
                PreparedLabelStruct im_ = new PreparedLabelStruct(((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks().getImageFactory(),aliasImageLabel_);
                AbsPreparedLabel lab_ = im_.getTextLabel();
                _cr.getGrList().setCustCell(_cr, lab_, im_,
                        new DefSpecSelectionStruct(_ctx, _cr)
                );
            } else {
                _cr.getGrList().setDefCell(_cr, create_);
            }
        } else {
            _cr.getGrList().setDefCell(_cr, create_);
        }
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

    public AbsGraphicListStr getGrList() {
        return grList;
    }
}
