package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.gui.CustButtonGroup;
import code.util.IdList;

public final class CustButtonGroupStruct extends WithoutParentIdStruct implements Struct {
    private final CustButtonGroup group;
    private final IdList<Struct> radios = new IdList<Struct>();

    public CustButtonGroupStruct() {
        group = new CustButtonGroup();
    }

    public void add(Struct _b) {
        if (!(_b instanceof RadioButtonStruct)) {
            return;
        }
        RadioButtonStruct r_ = (RadioButtonStruct)_b;
        group.add(r_.getRadioButton());
        radios.add(r_);
    }

    public void remove(Struct _b) {
        if (!(_b instanceof RadioButtonStruct)) {
            return;
        }
        RadioButtonStruct r_ = (RadioButtonStruct)_b;
        group.remove(r_.getRadioButton());
        radios.removeObj(r_);
    }

    public ArrayStruct getButtons(ContextEl _ctx) {
        return CustComponentStruct.nulls(((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasRadio(), radios);
    }

    public IdList<Struct> getRadios() {
        return radios;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        LgNamesGui stds_ = (LgNamesGui) _contextEl.getStandards();
        return stds_.getGuiAliases().getAliasButtonGroup();
    }
}
