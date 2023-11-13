package code.expressionlanguage.guicompos;

import code.expressionlanguage.ActionGraphicListenerStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.events.AbsEnabledAction;
import code.util.CustList;


public final class GraphicComboStruct extends CustComponentStruct {
    private final StructScrollCustomComboList graphicCombo;

    public GraphicComboStruct(String _className, ContextEl _ctx, StructScrollCustomComboList _graphicCombo) {
        super(_className);
        graphicCombo = _graphicCombo;
        CustList<ActionGraphicListenerStruct> ls_ = _graphicCombo.getActionGraphicListenerStructs();
        int s_ = ls_.size();
        for (int i = 0; i < s_; i++) {
            String k_ = _graphicCombo.getSelected().getActionsMap().getKey(i);
            AbsEnabledAction ac_ = _graphicCombo.getSelected().getActionsMap().getValue(i);
            getActions().addEntry(k_,new EnabledActionStruct(((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasAction(),ls_.get(i),ac_));
        }
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
        int index_ = graphicCombo.getSelectedIndex();
        ScrollCustomGraphicList<String> ls_ = graphicCombo.getList();
        RowGraphicList<String> s_ = ls_.getRow(index_);
        if (s_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(s_.getInfo());
    }

    public IntStruct getItemCount() {
        return new IntStruct(graphicCombo.size());
    }

    public void selectItem(NumberStruct _index) {
        int index_ = _index.intStruct();
        graphicCombo.select(index_);
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
        CustList<ListSelection> listeners_ = graphicCombo.getSelections();
        CustList<Struct> res_ = new CustList<Struct>();
        int len_ = listeners_.size();
        for (int i = 0; i < len_; i++) {
            if (listeners_.get(i) instanceof Struct) {
                res_.add((Struct)listeners_.get(i));
            }
        }
        String aliasListSelection_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasListSelection();
        return nulls(aliasListSelection_,res_);
    }

//    public void update() {
//        SelectionUtil.tryUp(graphicCombo);
//    }
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
    public boolean removeAllItems() {
        boolean empty_ = graphicCombo.isEmpty();
        graphicCombo.clear();
        return !empty_;
    }
    public boolean removeItem(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        return graphicCombo.remove(index_);
    }

    public StructScrollCustomComboList getGraphicCombo() {
        return graphicCombo;
    }

    @Override
    protected AbsCustComponent getVisibleComponent() {
        return graphicCombo.getSelected();
    }

    @Override
    protected AbsCustComponent getComponent() {
        return graphicCombo.getGlobal();
    }
}
