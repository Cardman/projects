package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;

import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class InferArrayInstancing extends AbstractArrayInstancingOperation implements PreAnalyzableOperation {
    private CustList<PartOffset> partOffsetsErr = new CustList<PartOffset>();
    private String typeInfer = EMPTY_STRING;

    InferArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(ContextEl _an) {
        MethodOperation par_ = getParent();
        if (!(par_ instanceof NamedArgumentOperation)) {
            return;
        }
        NamedArgumentOperation n_ = (NamedArgumentOperation) par_;
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
                setClassName(StringExpUtil.getQuickComponentType(infer_));
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
                setClassName(StringExpUtil.getQuickComponentType(infer_));
            }
        }
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        setClassName(_conf.getStandards().getAliasObject());

        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
        String type_;
        AnalyzedBlock cur_ = _conf.getAnalyzing().getCurrentAnaBlock();
        if (!typeInfer.isEmpty()) {
            type_ = typeInfer;
        } else if (m_ == null && cur_ instanceof ReturnMethod) {
            type_ = tryGetRetType(_conf);
        } else if (m_ == null && cur_ instanceof ImportForEachLoop) {
            ImportForEachLoop i_ = (ImportForEachLoop) cur_;
            type_ = i_.getImportedClassName();
            if (!type_.isEmpty()) {
                type_ = StringExpUtil.getPrettyArrayType(type_);
            }
        } else {
            type_ = tryGetTypeAff(m_);
        }
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (isUndefined(type_,keyWordVar_)) {
            IntTreeMap<String> operators_ = getOperations().getOperators();
            int offFirstOp_ = operators_.firstKey();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+offFirstOp_;
            un_.setIndexFile(i_);
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    type_);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            partOffsetsErr.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(StringExpUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        String n_ = type_;
        String cp_ = StringExpUtil.getQuickComponentType(n_, nbParentsInfer_);
        if (cp_ == null) {
            IntTreeMap<String> operators_ = getOperations().getOperators();
            int offFirstOp_ = operators_.firstKey();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+offFirstOp_;
            un_.setIndexFile(i_);
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    n_);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            partOffsetsErr.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(StringExpUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        String classNameFinal_ = StringExpUtil.getQuickComponentType(cp_);
        if (classNameFinal_ == null) {
            IntTreeMap<String> operators_ = getOperations().getOperators();
            int offFirstOp_ = operators_.firstKey();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+offFirstOp_;
            un_.setIndexFile(i_);
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    cp_);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            partOffsetsErr.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(StringExpUtil.getPrettyArrayType(stds_.getAliasObject())));
            return;
        }
        setClassName(classNameFinal_);
        StringMap<StringList> map_;
        map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setParam(classNameFinal_);
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            IntTreeMap<String> operators_ = getOperations().getOperators();
            CustList<PartOffset> parts_ = new CustList<PartOffset>();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _conf);
            ClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_conf, classNameFinal_, argType_);
                if (res_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    argType_.getImplicits().add(cl_);
                    argType_.setRootNumber(res_.getRootNumber());
                    argType_.setMemberNumber(res_.getMemberNumber());
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                    cast_.setIndexFile(i_);
                    //first separator char child
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(argType_.getNames(),"&"),
                            classNameFinal_);
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
                    parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                    parts_.add(new PartOffset("</a>",i_+1));
                }
            }
            if (PrimitiveTypeUtil.isPrimitive(classNameFinal_, _conf)) {
                o.getResultClass().setUnwrapObject(classNameFinal_);
                o.cancelArgument();
            }
            getPartOffsetsChildren().add(parts_);
        }
        setResultClass(new ClassArgumentMatching(cp_));
    }

    public CustList<PartOffset> getPartOffsetsErr() {
        return partOffsetsErr;
    }
}
