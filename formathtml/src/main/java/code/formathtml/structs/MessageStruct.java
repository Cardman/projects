package code.formathtml.structs;

import code.bean.validator.Message;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class MessageStruct implements Struct {

    private final Message instance;

    private final String className;

    private MessageStruct(Message _instance, String _className) {
        instance = _instance;
        className = _className;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    public static MessageStruct newInstance(Message _instance, String _className) {
        return new MessageStruct(_instance, _className);
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    public Message getInstance() {
        return instance;
    }

    public String getMessage() {
        return instance.getMessage();
    }

    public StringList getArgs() {
        return instance.getArgs();
    }

    public void setArgs(String[] _res) {
        instance.setArgs(_res);
    }
}
