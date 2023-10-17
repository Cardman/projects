package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;

public interface StructCellRender extends Struct {
    Struct generateImg(Struct _index, Struct _info, Struct _isSelected, Struct _cellHasFocus, Struct _cellIsAnchored, Struct _lab, Struct _compo);
}
