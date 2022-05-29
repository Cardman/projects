package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.common.StringExpUtil;

public final class StdNewInferringEmptyType extends AbstractInferringEmptyType {
    private final AbstractInstancingOperation operation;

    public StdNewInferringEmptyType(AbstractInstancingOperation _op) {
        this.operation = _op;
    }

    @Override
    protected String tryFormatEmpInst(Parametrable _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page) {
        return AbstractInstancingOperation.tryFormatEmpInst(operation,_methodInfo,_indexChild,_nbParentsInfer,_page);
    }

    @Override
    protected String tryParamFormatEmpInst(NameParametersFilter _filter, Parametrable _methodInfo, String _name, int _nbParentsInfer, AnalyzedPageEl _page) {
        return AbstractInstancingOperation.tryParamFormatEmpInst(operation,_filter,_methodInfo,_name,_nbParentsInfer,_page);
    }

    @Override
    protected String tryFormatEmpInstRec(String _inferRecord, int _nbParentsInfer, AnalyzedPageEl _page) {
        return AbstractInstancingOperation.tryFormatEmpInstRec(operation,_inferRecord,_nbParentsInfer,_page);
    }

    @Override
    protected String getQuickComponentType(String _typeAff, int _nbParentsInfer) {
        return StringExpUtil.getQuickComponentType(_typeAff,_nbParentsInfer);
    }

    @Override
    protected String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        return AbstractInstancingOperation.typeAff(_page, _par);
    }
}
