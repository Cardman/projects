package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaArrayInstancingContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.StringUtil;

public final class AnnotationInstanceArrOperation extends AnnotationInstanceOperation implements WithArrayElementInstancing {
    private final AnaArrayInstancingContent arrayInstancingContent;
    public AnnotationInstanceArrOperation(int _index,
                                          int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrayInstancingContent = new AnaArrayInstancingContent(getOperations().getOldFct());
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        MethodOperation mOp_ = getParent();
        AbsBk curr_ = _page.getCurrentBlock();
        arrayInstancingContent.setClassName(_page.getAliasObject());
        if (mOp_ == null) {
            arrayInstancingContent.setClassName(((NamedCalledFunctionBlock) curr_).getImportedReturnType());
        }
        if (mOp_ instanceof AssocationOperation) {
            AssocationOperation ass_ = (AssocationOperation) mOp_;
            String fieldName_ = ass_.getFieldName();
            MethodOperation mOpAss_ = ass_.getParent();
            AnnotationInstanceArobaseOperation inst_;
            inst_ = (AnnotationInstanceArobaseOperation)mOpAss_;
            String className_ = inst_.getClassName();
            RootBlock typeInfo_ = _page.getAnaClassBody(className_);
            if (typeInfo_ == null) {
                arrayInstancingContent.setClassName(_page.getAliasObject());
                return;
            }
            String type_ = EMPTY_STRING;
            CustList<NamedCalledFunctionBlock> list_ = ClassesUtil.getMethodAnnotationBodiesById(typeInfo_, fieldName_);
            if (!list_.isEmpty()) {
                type_ = list_.first().getImportedReturnType();
            }
            if (!type_.isEmpty()) {
                arrayInstancingContent.setClassName(type_);
            } else {
                arrayInstancingContent.setClassName(_page.getAliasObject());
            }
        } else if (mOp_ instanceof AnnotationInstanceOperation) {
            if (!(mOp_ instanceof AnnotationInstanceArobaseOperation)) {
                arrayInstancingContent.setClassName(_page.getAliasObject());
            } else {
                AnnotationInstanceArobaseOperation inst_;
                inst_ = (AnnotationInstanceArobaseOperation)mOp_;
                String className_ = inst_.getClassName();
                RootBlock type_ = _page.getAnaClassBody(className_);
                if (type_ == null) {
                    arrayInstancingContent.setClassName(_page.getAliasObject());
                    return;
                }
                CustList<AbsBk> bls_ = ClassesUtil.getDirectChildren(type_);
                CustList<NamedCalledFunctionBlock> blsAnn_ = new CustList<NamedCalledFunctionBlock>();
                for (AbsBk b: bls_) {
                    if (!AbsBk.isAnnotBlock(b)) {
                        continue;
                    }
                    NamedCalledFunctionBlock a_ = (NamedCalledFunctionBlock) b;
                    blsAnn_.add(a_);
                }
                if (blsAnn_.size() != 1) {
                    arrayInstancingContent.setClassName(_page.getAliasObject());
                } else {
                    NamedCalledFunctionBlock a_ =blsAnn_.first();
                    arrayInstancingContent.setClassName(a_.getImportedReturnType());
                }
            }
        }
    }

    @Override
    public AnaArrayInstancingContent getArrayInstancingContent() {
        return arrayInstancingContent;
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _page);
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        String eltType_ = StringExpUtil.getQuickComponentType(arrayInstancingContent.getClassName());
        if (eltType_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    arrayInstancingContent.getClassName());
            _page.getLocalizer().addError(un_);
            setPartOffsetsErr(new InfoErrorDto(un_,_page,1));
            setResultClass(new AnaClassArgumentMatching(arrayInstancingContent.getClassName()));
            return;
        }
        Mapping mapping_ = new Mapping();
        mapping_.setParam(eltType_);
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            StrTypes operators_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _page);
            InfoErrorDto parts_ = new InfoErrorDto("");
            AnaClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //first separator char child
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(argType_.getNames(),ExportCst.JOIN_TYPES),
                        eltType_);
                _page.getLocalizer().addError(cast_);
                parts_=new InfoErrorDto(cast_,_page,1);
            }
            if (AnaTypeUtil.isPrimitive(eltType_, _page)) {
                o.getResultClass().setUnwrapObject(eltType_, _page.getPrimitiveTypes());
            }
            getPartOffsetsChildren().add(parts_);
        }
        setResultClass(new AnaClassArgumentMatching(arrayInstancingContent.getClassName()));
        arrayInstancingContent.setClassName(eltType_);
    }

}
