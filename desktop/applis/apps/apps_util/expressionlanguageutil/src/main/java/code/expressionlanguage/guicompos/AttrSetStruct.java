package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.initialize.*;

public final class AttrSetStruct extends WithoutParentIdStruct {
    private final AbsAttrSet attrSet;

    public AttrSetStruct(AbsCompoFactory _c) {
        this.attrSet = _c.newAttrSet();
    }

    public void addBackground(Struct _color) {
        attrSet.addBackground(((NumberStruct)_color).intStruct());
    }

    public void addForeground(Struct _color) {
        attrSet.addForeground(((NumberStruct)_color).intStruct());
    }

    public void addFontSize(Struct _size) {
        attrSet.addFontSize(((NumberStruct)_size).intStruct());
    }

    public void addFontFamily(Struct _size) {
        attrSet.addFontFamily(NumParsers.getString(_size).getInstance());
    }

    public void addItalic(Struct _size) {
        attrSet.addItalic(BooleanStruct.isTrue(_size));
    }

    public void addBold(Struct _size) {
        attrSet.addBold(BooleanStruct.isTrue(_size));
    }

    public void addUnderline(Struct _size) {
        attrSet.addUnderline(BooleanStruct.isTrue(_size));
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesGui)_contextEl.getStandards()).getGuiAliases().getAliasAttrSet();
    }

    public AbsAttrSet getAttrSet() {
        return attrSet;
    }
}
