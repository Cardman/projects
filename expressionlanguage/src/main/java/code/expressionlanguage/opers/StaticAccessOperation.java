package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class StaticAccessOperation extends LeafOperation {

    private boolean possibleInitClass;

    public StaticAccessOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    @Override
    public final boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = getParent();
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public final boolean isCalculated() {
        OperationNode op_ = getParent();
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, Analyzable _conf,
            String _fieldName, String _op) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String argClName_;
        String type_ = str_.substring(STATIC_ACCESS.length() + 2);
        StringBuilder class_ = new StringBuilder();
        if (type_.trim().startsWith(String.valueOf(EXTERN_CLASS))) {
            class_.append(type_);
        } else {
            for (char p: type_.toCharArray()) {
                if (Character.isWhitespace(p)) {
                    continue;
                }
                if (StringList.isWordChar(p)) {
                    class_.append(p);
                } else if (p == EXTERN_CLASS){
                    class_.append(DOT_VAR);
                } else {
                    class_.append(INTERN_CLASS);
                }
            }
        }
        String classStr_ = StringList.removeAllSpaces(class_.toString());
        String base_ = StringList.getAllTypes(classStr_).first();
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        if (!checkExistBase(_conf, false, base_, false, 0)) {
            Argument a_ = new Argument();
            argClName_ = _conf.getStandards().getAliasObject();
            setArguments(a_);
            setStaticResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (classes_.isCustomType(classStr_)) {
            String curClassBase_ = null;
            if (glClass_ != null) {
                curClassBase_ = StringList.getAllTypes(glClass_).first();
            }
            if (!Classes.canAccessClass(curClassBase_, classStr_, _conf)) {
                BadAccessClass badAccess_ = new BadAccessClass();
                badAccess_.setId(classStr_);
                badAccess_.setRc(_conf.getCurrentLocation());
                badAccess_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().getErrorsDet().add(badAccess_);
            }
            possibleInitClass = true;
        }
        Argument a_ = new Argument();
        argClName_ = classStr_;
        setArguments(a_);
        setStaticResultClass(new ClassArgumentMatching(argClName_));
        return;
    }

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        Argument previous_ = _conf.getLastPage().getGlobalArgument();
        ArgumentCall argres_ = getCommonArgument(getArgument(), previous_, _conf, _op);
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getCommonArgument(getArgument(), previous_, _conf, _op);
        }
        if (_conf.getException() != null) {
            return;
        }
        Argument arg_ = argres_.getArgument();
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        Argument previous_ = _conf.getLastPage().getGlobalArgument();
        ArgumentCall argres_ = getCommonArgument(_nodes.getVal(this).getArgument(), previous_, _conf, _op);
        Argument arg_ = argres_.getArgument();
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        } else {
            PossibleIntermediateDotted n_ = getSiblingToSet();
            if (n_ != null) {
                _nodes.getVal((OperationNode)n_).setPreviousArgument(arg_);
            }
        }
        return arg_;
    }
    ArgumentCall getCommonArgument(Argument _argument, Argument _previous, ContextEl _conf,
            String _op) {
        if (possibleInitClass) {
            String className_ = getResultClass().getName();
            if (!_conf.getClasses().isInitialized(className_)) {
                _conf.getClasses().initialize(className_);
                InitializatingClass inv_ = new InitializatingClass(className_);
                return ArgumentCall.newCall(inv_);
            }
        }
        Argument cur_ = _argument;
        return ArgumentCall.newArgument(cur_);
    }

    @Override
    public boolean isPossibleInitClass() {
        return false;
    }

    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
