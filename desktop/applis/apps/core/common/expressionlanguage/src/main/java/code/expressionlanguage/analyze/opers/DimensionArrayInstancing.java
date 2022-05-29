package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.ReturnMethod;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DimensionArrayInstancing extends
        AbstractArrayInstancingOperation implements PreAnalyzableOperation {
    private final int countArrayDims;
    private String typeInfer = EMPTY_STRING;
    private ResolvedInstance resolvedInstance = new ResolvedInstance();

    public DimensionArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        countArrayDims = _op.getCountArrays();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String methodName_ = getMethodName();
        String className_ = methodName_.trim().substring(newKeyWord_.length());
        int local_ = StringUtil.getFirstPrintableCharIndex(className_);
        className_ = className_.trim();
        StringMap<String> vars_ = new StringMap<String>();
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        if (className_.trim().isEmpty()) {
            DimInferringEmptyType d_ = new DimInferringEmptyType(this);
            d_.infer(_page,par_);
            typeInfer = d_.getTypeInfer();
            return;
        }
        String inferForm_ = AnaTemplates.getInferForm(className_);
        if (inferForm_ == null) {
            return;
        }
        String mName_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(mName_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        DimInferringDiamondType d_ = new DimInferringDiamondType(inferForm_,this, local_, className_);
        d_.infer(_page,par_,vars_);
        resolvedInstance = d_.getResolvedInstance();
        typeInfer = d_.getTypeInfer();
    }

    static String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        OperationNode m_ = _par.getOperation();
        String typeAff_;
        AbsBk cur_ = _page.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_page);
        } else {
            typeAff_ = tryGetTypeAff(m_, _par.getOperationChild().getIndexChild());
        }
        return typeAff_;
    }

    static String tryParamFormatEmp(NameParametersFilter _filter, Parametrable _param, String _name, int _nbParentsInfer) {
        if (!isValidNameIndex(_filter,_param,_name)) {
            return null;
        }
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
        return tryFormatEmp(_param, ind_, _nbParentsInfer);
    }
    static String tryFormatEmp(Parametrable _param, int _indexChild, int _nbParentsInfer) {
        return tryGetParamDim(_param,_indexChild,_nbParentsInfer);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        setClassName(_page.getAliasObject());
        KeyWords keyWords_ = _page.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        if (typeInfer.isEmpty()) {
            int local_ = StringUtil.getFirstPrintableCharIndex(className_);
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(new_.length() + local_, className_, _page);
            className_ = result_.getResult(_page);
            resolvedInstance = new ResolvedInstance(result_);
        } else {
            className_ = typeInfer;
        }
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            StrTypes operators_ = getOperators();
            InfoErrorDto parts_ = new InfoErrorDto("");
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(2*index_), _page);
            AnaClassArgumentMatching resCh_ = o.getResultClass();
            if (!resCh_.isNumericInt(_page)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimInteger(), resCh_, _page);
                if (res_ != null) {
                    resCh_.implicitInfos(res_);
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setIndexFile(_page);
                    un_.setFile(_page.getCurrentFile());
                    //first part child bracket
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(resCh_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(un_);
                    parts_=new InfoErrorDto(un_,_page,1);
                }
            }
            resCh_.setUnwrapObjectNb(PrimitiveTypes.INT_WRAP);
            getPartOffsetsChildren().add(parts_);
        }
        setClassName(className_);
        setResultClass(new AnaClassArgumentMatching(StringExpUtil.getPrettyArrayType(className_, chidren_.size()+countArrayDims)));
    }

    public String getTypeInfer() {
        return typeInfer;
    }

    public int getCountArrayDims() {
        return countArrayDims;
    }

    public ResolvedInstance getPartOffsets() {
        return resolvedInstance;
    }
}
