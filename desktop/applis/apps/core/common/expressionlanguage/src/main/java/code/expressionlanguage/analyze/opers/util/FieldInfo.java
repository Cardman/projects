package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.CstFieldInfo;

public abstract class FieldInfo {
    private final boolean staticField;
    private final boolean finalField;
    private final String type;
    private final ClassField classField;

    protected FieldInfo(ClassField _id, String _type,
                        boolean _staticField, boolean _finalField) {
        classField = _id;
        type = _type;
        staticField = _staticField;
        finalField = _finalField;
    }
    public static String newFieldInfo(String _declaringClass, String _type,
                                      boolean _staticField, boolean _aff, AnalyzedPageEl _page) {
        String formattedType_ = _type;
        if (_staticField) {
            return formattedType_;
        }
        if (_aff) {
            formattedType_ = AnaInherits.wildCardFormatParam(_declaringClass, formattedType_, _page);
        } else {
            formattedType_ = AnaInherits.wildCardFormatReturn(_declaringClass, formattedType_, _page);
        }
        return formattedType_;
    }

    public ClassField getClassField() {
        return classField;
    }

    public String getType() {
        return type;
    }

    public boolean isStaticField() {
        return staticField;
    }
    public boolean isFinalField() {
        return finalField;
    }


    public abstract Accessed getAccessed();

    public abstract String getFileName();

    public abstract MemberId getMemberId();

    public abstract int getValOffset();

    public abstract CstFieldInfo cst();
}
