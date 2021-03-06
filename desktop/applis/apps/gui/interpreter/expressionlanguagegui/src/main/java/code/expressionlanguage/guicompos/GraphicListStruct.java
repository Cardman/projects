package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.util.CustList;
import code.util.Ints;

public final class GraphicListStruct extends InputStruct {

    private Struct render = NullStruct.NULL_VALUE;

    private final AbsGraphicListStr grList;

    public GraphicListStruct(GuiContextEl _ctx,String _className,boolean _simple) {
        super(_className);
        grList = ((LgNamesGui)_ctx.getStandards()).getGuiExecutingBlocks().getWindow().getFact().getGraphicListGenerator().create(_simple, new AdvGraphicListPainter(_ctx.getExecutionInfos()));
        init(_ctx);
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
        PreparedLabel textLabel_ = ((PreparedLabelStruct) _img).getTextLabel();
        grList.add(_index,textLabel_, _elt);
    }
    public void add(int _index, Struct _elt) {
        grList.add(_index, _elt);
    }
    void updateGraphics() {
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
        PreparedLabel textLabel_ = img_.getTextLabel();
        grList.set(_index, textLabel_,_elt);
    }
    public void set(int _index, Struct _elt) {
        if (!grList.getList().isValidIndex(_index)) {
            return;
        }
        grList.set(_index,_elt);
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

    public void setSelectedIndexes(Struct _selectedIndexes) {
        Ints selectedIndexes_ = new Ints();
        if (_selectedIndexes instanceof ArrayStruct) {
            for (Struct s : ((ArrayStruct)_selectedIndexes).list()) {
                selectedIndexes_.add(((NumberStruct) s).intStruct());
            }
            grList.setSelectedIndexes(selectedIndexes_);
        }
    }

    public void clearSelection() {
        if (isCust()) {
            grList.clearSelection();
            return;
        }
        grList.getSelectedIndexes().clear();
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
        ListSelection[] listeners_ = grList.getListeners();
        String aliasListSelection_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasListSelection();
        int len_ = listeners_.length;
        ArrayStruct out_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(aliasListSelection_));
        for (int i = 0; i < len_; i++) {
            if (listeners_[i] instanceof Struct) {
                out_.set(i,(Struct)listeners_[i]);
            }
        }
        return out_;
    }
    public void removeListener(Struct _listener) {
        if (_listener instanceof ListSelection) {
            grList.removeListener((ListSelection)_listener);
        } else {
            grList.removeListener(null);
        }
    }
    public void addListener(Struct _listener) {
        if (_listener instanceof ListSelection) {
            grList.addListener((ListSelection)_listener);
        } else {
            grList.addListener(null);
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
                PreparedLabelStruct im_ = new PreparedLabelStruct(aliasImageLabel_);
                PreparedLabel lab_ = im_.getTextLabel();
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
    protected CustComponent getVisibleComponent() {
        return grList.visible();
    }

    @Override
    protected CustComponent getComponent() {
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
