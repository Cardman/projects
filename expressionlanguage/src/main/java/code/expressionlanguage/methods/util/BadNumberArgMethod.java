package code.expressionlanguage.methods.util;
import code.util.StringList;

public final class BadNumberArgMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad matching arguments number; type:{0} variables:{1} in {2}";

    private String id;

    private int nbTypes;

    private int nbVars;

    @Override
    public String display() {
        String message_ = StringList.simpleStringsFormat(CLASS_NAME, Long.toString(nbTypes), Long.toString(nbVars), id);
        return super.display()+message_+SEP_INFO;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }

    public int getNbTypes() {
        return nbTypes;
    }

    public void setNbTypes(int _nbTypes) {
        nbTypes = _nbTypes;
    }

    public int getNbVars() {
        return nbVars;
    }

    public void setNbVars(int _nbVars) {
        nbVars = _nbVars;
    }

}
