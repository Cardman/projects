package code.expressionlanguage.methods.util;
import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public class BadReturnTypeInherit extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad return type {0} of method: {1} of parent class {2}";

    private String returnType;

    private MethodId method;

    private String parentClass;

    @Override
    public String toString() {
        String for_ = StringList.simpleFormat(CLASS_NAME, returnType, method.getSignature(), parentClass);
        return super.toString()+for_+SEP_INFO;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    public MethodId getMethod() {
        return method;
    }

    public void setMethod(MethodId _method) {
        method = _method;
    }

    public String getParentClass() {
        return parentClass;
    }

    public void setParentClass(String _parentClass) {
        parentClass = _parentClass;
    }

}
