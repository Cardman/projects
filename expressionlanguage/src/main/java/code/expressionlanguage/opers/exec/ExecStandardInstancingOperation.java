package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.StandardInstancingOperation;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecStandardInstancingOperation extends
        ExecAbstractInstancingOperation {

    private boolean possibleInitClass;

    private String methodName;

    private ConstructorId constId;

    private String className;

    private String fieldName = EMPTY_STRING;
    private int blockIndex = -1;

    private int naturalVararg = -1;

    private String lastType = EMPTY_STRING;
    public ExecStandardInstancingOperation(StandardInstancingOperation _s) {
        super(_s);
        possibleInitClass = _s.isPossibleInitClass();
        methodName = _s.getMethodName();
        constId = _s.getConstId();
        className = _s.getClassName();
        fieldName = _s.getFieldName();
        blockIndex = _s.getBlockIndex();
        naturalVararg = _s.getNaturalVararg();
        lastType = _s.getLastType();
    }

    public void setFieldName(String _fieldName) {
        fieldName = _fieldName;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        if (!_conf.isGearConst()) {
            return;
        }
        String cl_ = className;
        if (cl_ == null) {
            return;
        }
        if (_conf.getClasses().isCustomType(cl_)) {
            return;
        }
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        CustList<ExecOperationNode> filter_ = new CustList<ExecOperationNode>();
        for (ExecOperationNode o: chidren_) {
            if (o instanceof ExecStaticInitOperation) {
                continue;
            }
            arguments_.add(o.getArgument());
            filter_.add(o);
        }
        CustList<Argument> firstArgs_ = quickListArguments(filter_, naturalVararg_, lastType_, arguments_, _conf);
        if (firstArgs_ == null) {
            return;
        }
        ResultErrorStd res_ = LgNames.newInstanceStd(_conf, constId, Argument.toArgArray(firstArgs_));
        if (res_.getResult() == null) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(res_.getResult());
        setSimpleArgumentAna(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = filterInvoking(chidren_, _nodes);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }
    Argument getArgument(Argument _previous,CustList<Argument> _arguments,
            ExecutableCode _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<ExecOperationNode> filter_ = filterInvoking(chidren_);
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        PageEl page_ = _conf.getOperationPageEl();
        className_ = page_.formatVarType(className, _conf);
        if (possibleInitClass) {
            String base_ = Templates.getIdFromAllTypes(className_);
            if (ExecInvokingOperation.hasToExit(_conf, base_)) {
                return Argument.createVoid();
            }
        }
        String lastType_ = Templates.quickFormat(className_, lastType, _conf);
        CustList<Argument> firstArgs_ = listArguments(filter_, naturalVararg, lastType_, _arguments, _conf);
        return instancePrepare(_conf, className_, constId, _previous, firstArgs_, fieldName, blockIndex, true);
    }

}
