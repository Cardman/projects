package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.Parametrable;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.opers.util.ResolvedInstance;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.PreLinkagePartTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class StdNewInferringDiamondType extends AbstractInferringDiamondType {
    private final AbstractInstancingOperation current;
    private final String sup;
    private final AnaGeneType innTypeInf;

    public StdNewInferringDiamondType(AbstractInstancingOperation _oper, String _inf, int _l, String _cl, String _s, AnaGeneType _g) {
        super(_inf, _l, _cl);
        current = _oper;
        sup = _s;
        innTypeInf = _g;
    }

    @Override
    protected String getQuickComponentType(String _typeAff, int _nbParentsInfer) {
        return StringExpUtil.getQuickComponentType(_typeAff, _nbParentsInfer);
    }

    @Override
    protected String tryInferOrImplicitArr(String _type, StringMap<String> _vars, AnalyzedPageEl _page, String _cp) {
        return InvokingOperation.tryInferOrImplicit(_type, _vars, _page, _cp);
    }

    @Override
    protected String tryFormatArr(Parametrable _methodInfo, int _indexChild, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryFormat(_methodInfo, _indexChild, _nbParentsInfer, _type, _vars, _page);
    }

    @Override
    protected String tryParamFormatArr(NameParametersFilter _filter, Parametrable _methodInfo, String _name, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryParamFormat(_filter, _methodInfo, _name, _nbParentsInfer, _type, _vars, _page);
    }

    @Override
    protected String tryFormatArrRec(String _inferRecord, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        return InvokingOperation.tryFormatRec(_inferRecord,_nbParentsInfer,_type,_vars,_page);
    }

    @Override
    protected String getPrettyArrayType(String _format) {
        return _format;
    }

    @Override
    protected String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        return AbstractInstancingOperation.typeAff(_page, _par);
    }

    @Override
    protected String solve(String _inferForm, AnalyzedPageEl _page) {
        int rc_ = _page.getIndex();
        String type_;
        String newKeyWord_ = _page.getKeyWords().getKeyWordNew();
        if (!current.isIntermediateDottedOperation()) {
            AnaResultPartType result_ = ResolvingTypes.resolveAccessibleIdTypeWithoutError(newKeyWord_.length() + getLocal(), getInferForm(), _page);
            type_ = result_.getResult();
            if (type_.isEmpty()) {
                return "";
            }
            setResolvedInstance(new ResolvedInstance(result_));
        } else {
            String idClass_ = StringExpUtil.getIdFromAllTypes(getClassName()).trim();
            String id_ = StringExpUtil.getIdFromAllTypes(sup);
            type_ = StringUtil.concat(id_,"..",idClass_);
            int begin_ = newKeyWord_.length()+ getLocal();
            FileBlock r_ = _page.getCurrentFile();
            setResolvedInstance(new ResolvedInstance(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(getInferForm(), innTypeInf, StringExpUtil.getIdFromAllTypes(type_), r_, rc_ + begin_, _page)));
        }
        return type_;
    }
}
