package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.gui.CustButtonGroup;

public final class CustButtonGroupStruct extends WithoutParentIdStruct implements Struct {
    private CustButtonGroup group;

    public CustButtonGroupStruct() {
        group = new CustButtonGroup();
    }

    public void add(Struct _b) {
        if (!(_b instanceof RadioButtonStruct)) {
            return;
        }
        RadioButtonStruct r_ = (RadioButtonStruct)_b;
        group.add(r_.getRadioButton());
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        LgNamesGui stds_ = (LgNamesGui) _contextEl.getStandards();
        return stds_.getGuiAliases().getAliasButtonGroup();
    }
}
