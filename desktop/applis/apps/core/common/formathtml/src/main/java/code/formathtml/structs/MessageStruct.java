package code.formathtml.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.util.StringList;

public final class MessageStruct extends WithoutParentIdStruct {

    private final Message instance;

    private final String className;

    private MessageStruct(Message _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    public static MessageStruct newInstance(Message _instance, String _className) {
        return new MessageStruct(_instance, _className);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public Message getInstance() {
        return instance;
    }

    public String getMessage() {
        return instance.getContent();
    }

    public StringList getArgs() {
        return instance.getArgs();
    }

    public void setArgs(String[] _res) {
        instance.setArgs(_res);
    }
}
