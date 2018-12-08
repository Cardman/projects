package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class FinalPart extends FoundErrorInterpret {

    private static final String MESSAGE = "final member or variable";
    private String className;
    private String id;
    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,MESSAGE,className,".",id);
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
