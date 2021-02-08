package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.utilcompo.RunnableContextEl;
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

    public void selectItem(RunnableContextEl _run, NumberStruct _index) {
        int index_ = _index.intStruct();
        graphicCombo.simpleSelectItem(index_);
        invokeRunnable(_run,new SelectionComboEventStruct(index_, index_, this));
    }
    public void setListener(Struct _arg) {
        if (!(_arg instanceof ListSelection)) {
            return;
        }
        graphicCombo.setListener((ListSelection) _arg);
    }
    public Struct getListener() {
        ListSelection l_ = graphicCombo.getListener();
        if (!(l_ instanceof Struct)) {
            return NullStruct.NULL_VALUE;
        }
        return (Struct) l_;
    }
    ListSelection getSelection() {
        return graphicCombo.getListener();
    }
    public void update() {
        AbsComboBox.tryUp(graphicCombo);
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
    protected CustComponent getVisibleComponent() {
        return graphicCombo.getCurrentSelected();
    }

    @Override
    protected CustComponent getComponent() {
        return graphicCombo.getGlobal();
    }
}
