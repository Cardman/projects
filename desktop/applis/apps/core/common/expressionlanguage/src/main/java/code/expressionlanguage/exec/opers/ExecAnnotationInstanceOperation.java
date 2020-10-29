package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInstancingAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.core.StringUtil;

public final class ExecAnnotationInstanceOperation extends ExecInvokingOperation {

    private ExecInstancingAnnotContent instancingAnnotContent;

    private ExecRootBlock rootBlock;

    public ExecAnnotationInstanceOperation(
            ExecRootBlock _rootBlock, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingAnnotContent _instancingAnnotContent) {
        super(_opCont, _intermediateDottedOperation);
        instancingAnnotContent = _instancingAnnotContent;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = listNamedArguments(_nodes,chidren_).getArguments();
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments,
                         ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _conf);
        if (instancingAnnotContent.isArray()) {
            int nbCh_ = chidren_.size();
            Ints dims_;
            dims_ = new Ints();
            dims_.add(nbCh_);
            String className_ = StringExpUtil.getQuickComponentType(instancingAnnotContent.getClassName());
            Struct str_ = ExecTemplates.newCustomArray(className_, dims_, _conf);
            ExecTemplates.setCheckedElements(_arguments,str_,_conf);
            return new Argument(str_);
        }
        String base_ = StringExpUtil.getIdFromAllTypes(instancingAnnotContent.getClassName());
        if (_conf.getExiting().hasToExit(base_)) {
            return Argument.createVoid();
        }
        return instancePrepareAnnotation(_conf, instancingAnnotContent.getClassName(),rootBlock, instancingAnnotContent.getFieldNames(), _arguments);
    }

}
