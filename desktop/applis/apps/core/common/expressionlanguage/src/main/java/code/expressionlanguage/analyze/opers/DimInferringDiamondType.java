package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.opers.util.ResolvedInstance;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.util.StringMap;

public final class DimInferringDiamondType extends AbstractInferringDiamondType {
    private final int sum;

    public DimInferringDiamondType(String _inf, DimensionArrayInstancing _op, int _l, String _cl) {
        super(_inf, _l, _cl);
        sum = _op.getChildren().size() + _op.getCountArrayDims();
    }

    @Override
    protected String getQuickComponentType(String _typeAff, int _nbParentsInfer) {
        return StringExpUtil.getQuickComponentType(_typeAff, _nbParentsInfer+sum);
    }
    @Override
    protected String tryInferOrImplicitArr(String _type, StringMap<String> _vars, AnalyzedPageEl _page, String _cp) {
        return InvokingOperation.tryInferOrImplicitArr(_type, _vars, _page, _cp);
    }

    @Override
    protected String tryFormatArr(Parametrable _methodInfo, int _indexChild, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryFormatArr(_methodInfo, _indexChild, _nbParentsInfer+sum, _type, _vars, _page);
    }

    @Override
    protected String tryParamFormatArr(NameParametersFilter _filter, Parametrable _methodInfo, String _name, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryParamFormatArr(_filter, _methodInfo, _name, _nbParentsInfer+sum, _type, _vars, _page);
    }

    @Override
    protected String tryFormatArrRec(String _inferRecord, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryFormatArrRec(_inferRecord, _nbParentsInfer+sum, _type, _vars, _page);
    }

    @Override
    protected String getPrettyArrayType(String _format) {
        return _format;
    }

    @Override
    protected String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        return DimensionArrayInstancing.typeAff(_page, _par);
    }

    @Override
    protected String solve(String _inferForm, AnalyzedPageEl _page) {
        String newKeyWord_ = _page.getKeyWords().getKeyWordNew();
        AnaResultPartType result_ = ResolvingTypes.resolveAccessibleIdTypeWithoutError(newKeyWord_.length() + getLocal(), getInferForm(), _page);
        String type_ = result_.getResult();
        if (type_.isEmpty()) {
            return "";
        }
        setResolvedInstance(new ResolvedInstance(result_));
        return type_;
    }
}
