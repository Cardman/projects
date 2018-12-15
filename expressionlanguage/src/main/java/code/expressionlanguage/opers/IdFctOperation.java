package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
import code.expressionlanguage.errors.custom.VarargError;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.util.IdMap;
import code.util.StringList;

public final class IdFctOperation extends LeafOperation {

    private String className;
    private int offset;

    private ClassMethodId method;

    public IdFctOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        offset = _op.getValues().firstKey();
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        String extr_ = className.substring(className.indexOf("(")+1, className.lastIndexOf(")"));
        StringList args_ = Templates.getAllSepCommaTypes(extr_);
        String fromType_ = ContextEl.removeDottedSpaces(args_.first());
        String cl_ = _conf.resolveAccessibleIdType(fromType_);
        if (cl_.isEmpty()) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String keyWordStatic_ = _conf.getKeyWords().getKeyWordStatic();
        boolean static_ = false;
        int i_ = 1;
        if (args_.size() > 1) {
            if (StringList.quickEq(args_.get(1).trim(), keyWordStatic_)) {
                i_++;
                static_ = true;
            }
        }
        MethodId argsRes_ = resolveArguments(i_, _conf, cl_, EMPTY_STRING, static_, args_);
        if (!(m_ instanceof InvokingOperation)) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        InvokingOperation parent_ = (InvokingOperation) m_;
        if (!parent_.isCallMethodCtor(_conf)) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (!isFirstChild()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (argsRes_ == null) {
            return;
        }
        method = new ClassMethodId(cl_, argsRes_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }
    private MethodId resolveArguments(int _from,Analyzable _conf, String _fromType, String _name,boolean _static, StringList _params){
        StringList out_ = new StringList();
        LgNames stds_ = _conf.getStandards();
        int len_ = _params.size();
        int vararg_ = -1;
        for (int i = _from; i < len_; i++) {
            String arg_ = ContextEl.removeDottedSpaces(_params.get(i));
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    VarargError varg_ = new VarargError();
                    varg_.setFileName(_conf.getCurrentFileName());
                    varg_.setIndexFile(_conf.getCurrentLocationIndex());
                    varg_.setMethodName(VAR_ARG);
                    _conf.getClasses().addError(varg_);
                    setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                    return null;
                }
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = _conf.resolveCorrectAccessibleType(type_, _fromType);
            out_.add(arg_);
        }
        return new MethodId(_static, _name, out_, vararg_ != -1);
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public void calculate(ExecutableCode _conf) {
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument a_ = new Argument();
        _nodes.getVal(this).setArgument(a_);
        return a_;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    public ClassMethodId getMethod() {
        return method;
    }
}
