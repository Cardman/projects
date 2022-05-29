package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.common.StringExpUtil;

public final class DimInferringEmptyType extends AbstractInferringEmptyType {
    private final int sum;
    public DimInferringEmptyType(DimensionArrayInstancing _op) {
        sum = _op.getChildren().size() + _op.getCountArrayDims();
    }
    @Override
    protected String tryFormatEmpInst(Parametrable _methodInfo, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page) {
        return DimensionArrayInstancing.tryFormatEmp(_methodInfo,_indexChild,_nbParentsInfer+sum);
    }

    @Override
    protected String tryParamFormatEmpInst(NameParametersFilter _filter, Parametrable _methodInfo, String _name, int _nbParentsInfer, AnalyzedPageEl _page) {
        return DimensionArrayInstancing.tryParamFormatEmp(_filter,_methodInfo,_name,_nbParentsInfer+sum);
    }

    @Override
    protected String tryFormatEmpInstRec(String _inferRecord, int _nbParentsInfer, AnalyzedPageEl _page) {
        return InvokingOperation.tryGetRecordDim(_inferRecord,_nbParentsInfer+sum);
    }

    @Override
    protected String getQuickComponentType(String _typeAff, int _nbParentsInfer) {
        return StringExpUtil.getQuickComponentType(_typeAff,_nbParentsInfer+sum);
    }

    @Override
    protected String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        return DimensionArrayInstancing.typeAff(_page, _par);
    }
}
