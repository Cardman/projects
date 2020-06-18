package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.AnnotationTypeInfo;
import code.util.CustList;
import code.util.StringMap;

public final class CustomFoundAnnotation implements CallingState {

    private final String className;
    private final ExecRootBlock type;

    private final StringMap<AnnotationTypeInfo> id;

    private final CustList<Argument> arguments;

    public CustomFoundAnnotation(String _className,ExecRootBlock _type,
                                 StringMap<AnnotationTypeInfo> _id,
                                 CustList<Argument> _arguments) {
        className = _className;
        type = _type;
        id = _id;
        arguments = _arguments;
    }

    public String getClassName() {
        return className;
    }

    public ExecRootBlock getType() {
        return type;
    }

    public StringMap<AnnotationTypeInfo> getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}
