package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;

import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class InferArrayInstancing extends AbstractArrayInstancingOperation implements PreAnalyzableOperation, WithArrayElementInstancing  {
    private InfoErrorDto partOffsetsErr = new InfoErrorDto("");
    private String typeInfer = EMPTY_STRING;

    InferArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        MethodOperation par_ = getParent();
        if (!(par_ instanceof NamedArgumentOperation)) {
            return;
        }
        NamedArgumentOperation n_ = (NamedArgumentOperation) par_;
        String inferRecord_ = n_.infer();
        if (!inferRecord_.isEmpty()) {
            typeInfer = inferRecord_;
            setClassName(StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(inferRecord_)));
            return;
        }
        String name_ = n_.getName();
        MethodOperation call_ = n_.getParent();
        if (call_ instanceof RetrieveMethod) {
            RetrieveMethod f_ = (RetrieveMethod) call_;
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            StringList candidates_ = new StringList();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    String format_ = tryParamVarargFormat(methodInfo_, name_);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.set(i,newList_);
            }
            if (candidates_.onlyOneElt()) {
                String infer_ = candidates_.first();
                typeInfer = infer_;
                setClassName(StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(infer_)));
            }
        }
        if (call_ instanceof RetrieveConstructor) {
            RetrieveConstructor f_ = (RetrieveConstructor) call_;
            CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
            int len_ = methodInfos_.size();
            StringList candidates_ = new StringList();
            CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
            for (int i = 0; i < len_; i++) {
                ConstructorInfo methodInfo_ = methodInfos_.get(i);
                String format_ = tryParamVarargFormat(methodInfo_, name_);
                if (format_ == null) {
                    continue;
                }
                candidates_.add(format_);
                newList_.add(methodInfo_);
            }
            methodInfos_.clear();
            methodInfos_.addAllElts(newList_);
            if (candidates_.onlyOneElt()) {
                String infer_ = candidates_.first();
                typeInfer = infer_;
                setClassName(StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(infer_)));
            }
        }
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        setClassName(_page.getAliasObject());

        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
        String type_;
        if (!typeInfer.isEmpty()) {
            type_ = typeInfer;
        } else if (m_ == null && _page.getCurrentBlock() instanceof ReturnMethod) {
            type_ = tryGetRetType(_page);
        } else if (m_ == null && _page.getCurrentAnaBlockForEachLoop() != null) {
            ImportForEachLoop i_ = _page.getCurrentAnaBlockForEachLoop();
            type_ = i_.getImportedClassName();
            if (!type_.isEmpty()) {
                type_ = StringExpUtil.getPrettyArrayType(type_);
            }
        } else {
            type_ = tryGetTypeAff(m_, par_.getOperationChild().getIndexChild());
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (isUndefined(type_,keyWordVar_)) {
            StrTypes operators_ = getOperators();
            int offFirstOp_ = operators_.firstKey();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page,offFirstOp_);
            un_.setFile(_page.getCurrentFile());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    type_);
            _page.getLocalizer().addError(un_);
            partOffsetsErr=new InfoErrorDto(un_.getBuiltError(),_page,offFirstOp_,1);
            setResultClass(new AnaClassArgumentMatching(StringExpUtil.getPrettyArrayType(_page.getAliasObject())));
            return;
        }
        String n_ = type_;
        String cp_ = StringExpUtil.getQuickComponentType(n_, nbParentsInfer_);
        if (cp_ == null) {
            StrTypes operators_ = getOperators();
            int offFirstOp_ = operators_.firstKey();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page,offFirstOp_);
            un_.setFile(_page.getCurrentFile());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    n_);
            _page.getLocalizer().addError(un_);
            partOffsetsErr=new InfoErrorDto(un_.getBuiltError(),_page,offFirstOp_,1);
            setResultClass(new AnaClassArgumentMatching(StringExpUtil.getPrettyArrayType(_page.getAliasObject())));
            return;
        }
        String classNameFinal_ = StringExpUtil.getQuickComponentType(cp_);
        if (classNameFinal_ == null) {
            StrTypes operators_ = getOperators();
            int offFirstOp_ = operators_.firstKey();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page,offFirstOp_);
            un_.setFile(_page.getCurrentFile());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    cp_);
            _page.getLocalizer().addError(un_);
            partOffsetsErr=new InfoErrorDto(un_.getBuiltError(),_page,offFirstOp_,1);
            setResultClass(new AnaClassArgumentMatching(StringExpUtil.getPrettyArrayType(_page.getAliasObject())));
            return;
        }
        setClassName(classNameFinal_);
        StringMap<StringList> map_;
        map_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setParam(classNameFinal_);
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            StrTypes operators_ = getOperators();
            InfoErrorDto parts_ = new InfoErrorDto("");
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _page);
            AnaClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(classNameFinal_, argType_, _page);
                if (res_ != null) {
                    argType_.implicitInfos(res_);
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page);
                    //first separator char child
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(argType_.getNames(),ExportCst.JOIN_TYPES),
                            classNameFinal_);
                    _page.getLocalizer().addError(cast_);
                    parts_=(new InfoErrorDto(cast_,_page,1));
                }
            }
            if (AnaTypeUtil.isPrimitive(classNameFinal_, _page)) {
                o.getResultClass().setUnwrapObject(classNameFinal_, _page.getPrimitiveTypes());
            }
            getPartOffsetsChildren().add(parts_);
        }
        setResultClass(new AnaClassArgumentMatching(cp_));
    }

    public InfoErrorDto getPartOffsetsErr() {
        return partOffsetsErr;
    }
}
