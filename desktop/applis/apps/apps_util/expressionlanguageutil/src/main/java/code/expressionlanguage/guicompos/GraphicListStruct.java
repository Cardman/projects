package code.expressionlanguage.guicompos;

import code.expressionlanguage.ActionGraphicListenerStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.structs.*;
import code.gui.AbsCustComponent;
import code.gui.AbsPreparedLabel;
import code.gui.ListSelection;
import code.gui.RowGraphicList;
import code.gui.events.AbsEnabledAction;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;
import code.util.CustList;
import code.util.Ints;

public final class GraphicListStruct extends InputStruct {

    private final StructScrollCustomGraphicList grList;
    private StructCellRender cellRender;

    public GraphicListStruct(GuiContextEl _ctx,String _className,boolean _simple, EventStruct _render) {
        super(_className);
        GuiExecutingBlocks guiEx_ = ((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks();
        grList = new StructScrollCustomGraphicList(guiEx_.getCompoFactory(),guiEx_.getImageFactory(),((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasActionListener(),_simple,this);
        cellRender = _render;
        CustList<ActionGraphicListenerStruct> ls_ = grList.getActionGraphicListenerStructs();
        int s_ = ls_.size();
        for (int i = 0; i < s_; i++) {
            String k_ = grList.getElements().getActionsMap().getKey(i);
            AbsEnabledAction ac_ = grList.getElements().getActionsMap().getValue(i);
            getActions().addEntry(k_,new EnabledActionStruct(((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasAction(),ls_.get(i),ac_));
        }
    }

    public AbstractImage generateImg(MetaFont _lab, int _index, Struct _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored) {
        Struct str_ = cellRender.generateImg(new IntStruct(_index), _info, BooleanStruct.of(_isSelected), BooleanStruct.of(_cellHasFocus), BooleanStruct.of(_cellIsAnchored), new FontStruct(_lab), this);
        return PreparedLabelStruct.builImage(grList.getImageFactory(), str_);
    }

    public StructCellRender getCellRender() {
        return cellRender;
    }

    public void setCellRender(StructCellRender _c) {
        this.cellRender = _c;
    }

    public void add(int _index, Struct _img, Struct _elt) {
        grList.add(_index, grList.getCompoFactory().newPreparedLabel(PreparedLabelStruct.builImage(grList.getImageFactory(), _img)), _elt);
    }

    public void addRow(int _index, AbsPreparedLabel _label, Struct _info) {
        grList.add(_index, _label, _info);
    }

    public void set(int _index, Struct _img, Struct _elt) {
        RowGraphicList<Struct> next_ = grList.getRow(_index);
        if (next_ == null) {
            return;
        }
        next_.setInfo(_elt);
        next_.refresh(grList.getImageFactory(), PreparedLabelStruct.builImage(getGrList().getImageFactory(), _img));
    }

    public static ArrayStruct getListView(ContextEl _ctx, StructScrollCustomGraphicList _gr) {
        CustList<Struct> list_ = _gr.getList();
        int len_ = list_.size();
        String obj_ = StringExpUtil.getPrettyArrayType(_ctx.getStandards().getContent().getCoreNames().getAliasObject());
        ArrayStruct arr_ = new ArrayStruct(len_, obj_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, list_.get(i));
        }
        return arr_;
    }

    public static ArrayStruct getSelectedIndexes(ContextEl _ctx, StructScrollCustomGraphicList _gr) {
        Ints selectedIndexes_ = _gr.getSelectedIndexes();
        int len_ = selectedIndexes_.size();
        String obj_ = StringExpUtil.getPrettyArrayType(_ctx.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        ArrayStruct arr_ = new ArrayStruct(len_, obj_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, new IntStruct(selectedIndexes_.get(i)));
        }
        return arr_;
    }

    public static Struct getVisibleRowCount(StructScrollCustomGraphicList _gr) {
        return new IntStruct(_gr.getVisibleRowCount());
    }

    public static void setVisibleRowCount(Struct _visibleRowCount, StructScrollCustomGraphicList _gr) {
        int value_ = ((NumberStruct)_visibleRowCount).intStruct();
        _gr.setVisibleRowCount(value_);
    }

    public static ArrayStruct getListeners(ContextEl _ctx, CustList<ListSelection> _list) {
        CustList<Struct> res_ = new CustList<Struct>();
        String aliasListSelection_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasListSelection();
        int len_ = _list.size();
        for (int i = 0; i < len_; i++) {
            if (_list.get(i) instanceof Struct) {
                res_.add((Struct) _list.get(i));
            }
        }
        return nulls(aliasListSelection_,res_);
    }

    public static void removeListener(Struct _listener, StructScrollCustomGraphicList _gr) {
        if (_listener instanceof ListSelection) {
            _gr.removeListener((ListSelection)_listener);
        }
    }
    public static void addListener(Struct _listener, StructScrollCustomGraphicList _gr) {
        if (_listener instanceof ListSelection) {
            _gr.addListener((ListSelection)_listener);
        }
    }

    public void setRender(GuiContextEl _ctx, Struct _render) {
        if (_render instanceof StructCellRender) {
            setCellRender((StructCellRender) _render);
        } else {
            GuiExecutingBlocks guiEx_ = ((LgNamesGui) _ctx.getStandards()).getGuiExecutingBlocks();
            EventStruct rend_ = new EventStruct(_ctx, guiEx_.getDefCellRender().getGenericString(), "", -1, new CustList<ClassFieldStruct>(), NullStruct.NULL_VALUE, "");
            setCellRender(rend_);
        }
    }

    @Override
    protected AbsCustComponent getVisibleComponent() {
        return visible();
    }

    public AbsCustComponent visible() {
        return grList.getElements();
    }

    @Override
    protected AbsCustComponent getComponent() {
        return grList.getScrollPane();
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(grList.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        grList.setEnabled(BooleanStruct.isTrue(_enabled));
    }

    public StructScrollCustomGraphicList getGrList() {
        return grList;
    }
}
