package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public class BadReturnTypeInherit extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad return type {0} of method: {1} of parent class {2}";

    private String returnType;

    private String method;

    private String parentClass;

    @Override
    public String display(Classes _classes) {
        String for_ = StringList.simpleStringsFormat(CLASS_NAME, returnType, method, parentClass);
        return StringList.concat(super.display(_classes),for_,SEP_INFO);
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    public void setMethod(String _method) {
        method = _method;
    }

    public void setParentClass(String _parentClass) {
        parentClass = _parentClass;
    }

}
