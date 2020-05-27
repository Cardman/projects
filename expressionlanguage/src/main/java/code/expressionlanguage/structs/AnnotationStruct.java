package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassFieldStruct;
import code.expressionlanguage.opers.util.annotation.ExportAnnotationUtil;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.CustList;
import code.util.StringList;

public final class AnnotationStruct extends WithoutParentStruct implements FieldableStruct,DisplayableStruct {

    private static final String INFINITY = "I";
    private static final String NAN = "N";
    private static final String EXP = "E";
    private final String className;

    private final CustList<ClassFieldStruct> fields;

    public AnnotationStruct(String _className,
                            CustList<ClassFieldStruct> _fields) {
        fields = _fields;
        className = _className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof AnnotationStruct)) {
            return false;
        }
        AnnotationStruct a_ = (AnnotationStruct) _other;
        return StringList.quickEq(ExportAnnotationUtil.exportAnnotation(INFINITY, NAN, EXP,a_), ExportAnnotationUtil.exportAnnotation(INFINITY, NAN, EXP,this));
    }

    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }


    public String getClassName() {
        return className;
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        DisplayedStrings dis_ = _an.getStandards().getDisplayedStrings();
        return new StringStruct(ExportAnnotationUtil.exportAnnotation(dis_.getInfinity(),
                dis_.getNan(),
                dis_.getExponent(),this));
    }
}
