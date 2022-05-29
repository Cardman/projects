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

public final class EltInferringDiamondType extends AbstractInferringDiamondType {
    private final int dimArr;
    private final ElementArrayInstancing oper;

    public EltInferringDiamondType(String _inf, int _d, ElementArrayInstancing _op, int _l, String _cl) {
        super(_inf, _l, _cl);
        dimArr = _d;
        oper = _op;
    }

    @Override
    protected String getQuickComponentType(String _typeAff, int _nbParentsInfer) {
        return StringExpUtil.getQuickComponentType(_typeAff, _nbParentsInfer+ dimArr);
    }

    @Override
    protected String tryInferOrImplicitArr(String _type, StringMap<String> _vars, AnalyzedPageEl _page, String _cp) {
        return InvokingOperation.tryInferOrImplicitArr(_type, _vars, _page, _cp);
    }

    @Override
    protected String tryFormatArr(Parametrable _methodInfo, int _indexChild, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryFormatArr(_methodInfo, _indexChild, _nbParentsInfer+dimArr, _type, _vars, _page);
    }

    @Override
    protected String tryParamFormatArr(NameParametersFilter _filter, Parametrable _methodInfo, String _name, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryParamFormatArr(_filter, _methodInfo, _name, _nbParentsInfer+dimArr, _type, _vars, _page);
    }

    @Override
    protected String tryFormatArrRec(String _inferRecord, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryFormatArrRec(_inferRecord, _nbParentsInfer+dimArr, _type, _vars, _page);
    }

    @Override
    protected String getPrettyArrayType(String _format) {
        String infer_ = StringExpUtil.getPrettyArrayType(_format, dimArr);
        oper.setClassName(StringExpUtil.getQuickComponentType(infer_));
        return infer_;
    }

    @Override
    protected String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        return ElementArrayInstancing.typeAff(_page, _par);
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

        if (dimArr == 0) {
            return "";
        }
        return type_;
    }
}
