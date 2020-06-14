package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.AnnotationInstanceOperation;
import code.expressionlanguage.opers.util.AnnotationTypeInfo;
import code.expressionlanguage.structs.Struct;
import code.util.*;

public final class ExecAnnotationInstanceOperation extends ExecInvokingOperation {

    private String methodName;

    private String className;
    private StringMap<AnnotationTypeInfo> fieldNames;
    private boolean array;

    protected ExecAnnotationInstanceOperation(
            AnnotationInstanceOperation _ann) {
        super(_ann);
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
                         ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        if (array) {
            int nbCh_ = chidren_.size();
            Argument a_ = new Argument();
            Ints dims_;
            dims_ = new Ints();
            dims_.add(nbCh_);
            String className_ = PrimitiveTypeUtil.getQuickComponentType(className);
            Struct str_ = PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf);
            Templates.setCheckedElements(_arguments,str_,_conf);
            a_.setStruct(str_);
            return a_;
        }
        String base_ = Templates.getIdFromAllTypes(className);
        if (_conf.hasToExit(base_)) {
            return Argument.createVoid();
        }
        return instancePrepareAnnotation(_conf, className, fieldNames, _arguments);
    }

}
