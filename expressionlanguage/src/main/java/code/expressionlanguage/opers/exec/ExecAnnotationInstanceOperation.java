package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AnnotationInstanceOperation;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class ExecAnnotationInstanceOperation extends ExecInvokingOperation {

    private boolean possibleInitClass;

    private String methodName;

    private String className;
    private StringMap<String> fieldNames;
    private boolean array;

    protected ExecAnnotationInstanceOperation(
            AnnotationInstanceOperation _ann) {
        super(_ann);
        possibleInitClass = _ann.isPossibleInitClass();
        methodName = _ann.getMethodName();
        fieldNames = _ann.getFieldNames();
        className = _ann.getClassName();
        array = _ann.isArray();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = filterInvoking(chidren_, _nodes);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments,
            ExecutableCode _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        if (array) {
            int nbCh_ = chidren_.size();
            Argument a_ = new Argument();
            Numbers<Integer> dims_;
            dims_ = new Numbers<Integer>();
            dims_.add(nbCh_);
            String className_ = PrimitiveTypeUtil.getQuickComponentType(className);
            Struct str_ = PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf);
            for (int i = CustList.FIRST_INDEX; i < nbCh_; i++) {
                Argument chArg_ = _arguments.get(i);
                IntStruct i_ = new IntStruct(i);
                ExecArrOperation.setCheckedElement(str_, i_, chArg_, _conf);
                if (_conf.getContextEl().hasExceptionOrFailInit()) {
                    return a_;
                }
            }
            a_.setStruct(str_);
            return a_;
        }
        if (possibleInitClass) {
            String base_ = Templates.getIdFromAllTypes(className);
            if (ExecInvokingOperation.hasToExit(_conf, base_)) {
                return Argument.createVoid();
            }
        }
        return instancePrepareAnnotation(_conf, className, fieldNames, _arguments);
    }

}
