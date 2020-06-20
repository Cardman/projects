package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.AnnotationInstanceOperation;
import code.expressionlanguage.common.AnnotationTypeInfo;
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
            String className_ = StringExpUtil.getQuickComponentType(className);
            Struct str_ = ExecTemplates.newCustomArray(className_, dims_, _conf);
            ExecTemplates.setCheckedElements(_arguments,str_,_conf);
            a_.setStruct(str_);
            return a_;
        }
        String base_ = StringExpUtil.getIdFromAllTypes(className);
        if (ExecutingUtil.hasToExit(_conf,base_)) {
            return Argument.createVoid();
        }
        return instancePrepareAnnotation(_conf, className, fieldNames, _arguments);
    }

}
