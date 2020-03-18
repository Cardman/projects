package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneFunction;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AccessEnum;
import code.util.StringList;

public abstract class StandardNamedFunction implements GeneFunction {

    private final String name;

    private final StringList parametersTypes;

    private final String returnType;

    private final boolean varargs;

    private final StandardType owner;

    protected StandardNamedFunction(String _name, StringList _parametersTypes,
            String _returnType, boolean _varargs, StandardType _owner) {
        name = _name;
        parametersTypes = _parametersTypes;
        returnType = _returnType;
        varargs = _varargs;
        owner = _owner;
    }

    public StandardType getOwner() {
        return owner;
    }

    @Override
    public String getName() {
        return name;
    }

    public StringList getImportedParametersTypes() {
        return new StringList(parametersTypes);
    }

    @Override
    public String getImportedReturnType() {
        return returnType;
    }

    @Override
    public final boolean isVarargs() {
        return varargs;
    }
}
