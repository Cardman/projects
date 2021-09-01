package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.*;
import code.gui.*;


public final class GraphicComboStruct extends InputStruct {
    private GraphicComboGrInt graphicCombo;

    GraphicComboStruct(String _className) {
        super(_className);
    }

    GraphicComboStruct(String _className, GraphicComboGrInt _graphicCombo) {
        super(_className);
        graphicCombo = _graphicCombo;
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(graphicCombo.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        graphicCombo.setEnabled(BooleanStruct.isTrue(_enabled));
    }

    public Struct getSelectedItem() {
        String s_ = graphicCombo.getSelectedItem();
        if (s_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(s_);
    }

    public void addItem(Struct _object) {
        if (!(_object instanceof StringStruct)) {
            return;
        }
        graphicCombo.addItem(((StringStruct)_object).getInstance());
    }

    public IntStruct getItemCount() {
        return new IntStruct(graphicCombo.getItemCount());
    }

    public void selectItem(NumberStruct _index) {
        int index_ = _index.intStruct();
        graphicCombo.selectItem(index_);
    }
    public void addListener(Struct _arg) {
        if (!(_arg instanceof ListSelection)) {
            return;
        }
        graphicCombo.addListener((ListSelection) _arg);
    }

    public void removeListener(Struct _arg) {
        if (!(_arg instanceof ListSelection)) {
            return;
        }
        graphicCombo.removeListener((ListSelection) _arg);
    }
    public ArrayStruct getListeners(ContextEl _ctx) {
        ListSelection[] listeners_ = graphicCombo.getListeners();
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

    public void update() {
        SelectionUtil.tryUp(graphicCombo);
    }
    public ArrayStruct getSelectedIndexes(ContextEl _cont) {
        int selectedIndex_ = graphicCombo.getSelectedIndex();
        String arrInt_ = StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        if (selectedIndex_ == -1) {
            return new ArrayStruct(0, arrInt_);
        }
        ArrayStruct arrInst_ = new ArrayStruct(1, arrInt_);
        arrInst_.set(0,new IntStruct(selectedIndex_));
        return arrInst_;
    }
    public IntStruct getSelectedIndex() {
        int selectedIndex_ = graphicCombo.getSelectedIndex();
        return new IntStruct(selectedIndex_);
    }
    public void removeAllItems() {
        graphicCombo.simpleRemoveAllItems();
    }
    public void removeItem(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        graphicCombo.simpleRemoveItem(index_);
    }

    @Override
    protected AbsCustComponent getVisibleComponent() {
        return graphicCombo.getCurrentSelected();
    }

    @Override
    protected AbsCustComponent getComponent() {
        return graphicCombo.getGlobal();
    }
}
