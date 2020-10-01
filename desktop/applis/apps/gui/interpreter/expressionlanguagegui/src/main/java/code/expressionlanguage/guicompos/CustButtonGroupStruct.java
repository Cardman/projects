package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentStruct;
import code.gui.CustButtonGroup;

public final class CustButtonGroupStruct extends WithoutParentStruct implements Struct {
    private CustButtonGroup group;

    public CustButtonGroupStruct() {
        group = new CustButtonGroup();
    }
    public CustButtonGroupStruct(CustButtonGroup _group) {
        group = _group;
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

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof CustButtonGroupStruct)) {
            return false;
        }
        return group == ((CustButtonGroupStruct)_other).group;
    }
}
