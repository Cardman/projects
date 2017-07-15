package code.expressionlanguage.methods.util;
import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public final class BadNumberArgMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad matching arguments number; type:{0} variables:{1} in {2}";

    private MethodId id;

    private int nbTypes;

    private int nbVars;

    @Override
    public String toString() {
        String message_ = StringList.simpleFormat(CLASS_NAME, nbTypes, nbVars, id.getSignature());
        return super.toString()+message_+SEP_INFO;
    }

    public MethodId getId() {
        return id;
    }

    public void setId(MethodId _id) {
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
