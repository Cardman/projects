package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.Struct;

public final class CustomFoundConstructor implements CallingState,GlobalClassCallingState {

    private final ExecFormattedRootBlock className;
    private final ExecTypeFunction pair;

    private final Struct currentObject;

    private final Parameters arguments;

    public CustomFoundConstructor(ExecFormattedRootBlock _className,
                                  Struct _currentObject) {
        this(_className,_className.getRootBlock().getEmptyCtorPair(),_currentObject,new Parameters());
    }

    public CustomFoundConstructor(ContextEl _context,ExecFormattedRootBlock _className,
                                  Struct _currentObject) {
        this(_context,_className,_className.getRootBlock().getEmptyCtorPair(), "",-1,_currentObject,new Parameters());
    }
    public CustomFoundConstructor(ContextEl _context,ExecFormattedRootBlock _className,
                                  ExecTypeFunction _pair,
                                  String _fieldName, int _childIndex,
                                  Struct _currentObject, Parameters _arguments) {
        this(_className,_pair, _context.getInit().processInit(_context, struct(_currentObject), _className, _fieldName, _childIndex),_arguments);
    }
    public CustomFoundConstructor(ExecFormattedRootBlock _className,
                                  ExecTypeFunction _pair,
                                  Struct _currentObject, Parameters _arguments) {
        className = _className;
        pair = _pair;
        currentObject = _currentObject;
        arguments = _arguments;
    }
    private static Struct struct(Struct _global) {
        return ArgumentListCall.getNull(_global);
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createInstancing(_context,this);
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public Struct getCurrentObject() {
        return currentObject;
    }

    public Parameters getArguments() {
        return arguments;
    }

}
