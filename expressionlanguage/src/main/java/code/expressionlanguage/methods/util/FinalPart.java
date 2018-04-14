package code.expressionlanguage.methods.util;

import code.util.StringList;

public final class FinalPart extends FoundErrorInterpret {

    private static final String MESSAGE = "final member or variable";
    private String className;
    private String id;
    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,MESSAGE,className,".",id);
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String _className) {
        className = _className;
    }
    public String getId() {
        return id;
    }
    public void setId(String _id) {
        id = _id;
    }

}
