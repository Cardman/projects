package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.common.DimComp;
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

public final class ElementArrayInstancing extends AbstractArrayInstancingOperation implements PreAnalyzableOperation, WithArrayElementInstancing {

    private ResolvedInstance resolvedInstance = new ResolvedInstance();
    private InfoErrorDto partOffsetsErr = new InfoErrorDto("");
    private String typeInfer = EMPTY_STRING;
    public ElementArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        String me_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        setClassName(_page.getAliasObject());
        KeyWords keyWords_ = _page.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = me_.trim().substring(new_.length());
        int local_ = StringUtil.getFirstPrintableCharIndex(className_);
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        DimComp dim_ = AnaInherits.getComponentForm(className_);
        String inferForm_ = AnaTemplates.getInferForm(dim_.getComponent());
        if (inferForm_ == null) {
            AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(new_.length() + local_, className_.trim(), _page);
            if (!resType_.isOk()) {
                return;
            }
            String res_ = resType_.getResult();
            String comp_ = StringExpUtil.getQuickComponentType(res_);
            if (comp_ != null) {
                resolvedInstance = new ResolvedInstance(resType_);
                typeInfer = res_;
                setClassName(comp_);
            }
            return;
        }
        EltInferringDiamondType elt_ = new EltInferringDiamondType(inferForm_,dim_.getDim(),this,local_,className_);
        elt_.infer(_page,par_,new StringMap<String>());
        resolvedInstance = elt_.getResolvedInstance();
        typeInfer = elt_.getTypeInfer();
    }

    static String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        OperationNode m_ = _par.getOperation();
        String typeAff_;
        if (m_ == null && _page.getCurrentBlock() instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_page);
        } else if (m_ == null && _page.getCurrentAnaBlockForEachLoop() != null) {
            ImportForEachLoop i_ = _page.getCurrentAnaBlockForEachLoop();
            typeAff_ = i_.getImportedClassName();
            if (!typeAff_.isEmpty()) {
                typeAff_ = StringExpUtil.getPrettyArrayType(typeAff_);
            }
        } else {
            typeAff_ = tryGetTypeAff(m_, _par.getOperationChild().getIndexChild());
        }
        return typeAff_;
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String className_ = initAnalyze(_page);
        if (typeInfer.isEmpty()) {
            int loc_ = StringUtil.getFirstPrintableCharIndex(className_);
            String new_ = _page.getKeyWords().getKeyWordNew();
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(new_.length() + loc_, className_, _page);
            resolvedInstance = new ResolvedInstance(result_);
            className_ = result_.getResult(_page);
        } else {
            className_ = typeInfer;
        }
        String eltType_ = StringExpUtil.getQuickComponentType(className_);
        if (eltType_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    className_);
            _page.getLocalizer().addError(un_);
            StrTypes operators_ = getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.firstKey(), _page);
            partOffsetsErr = new InfoErrorDto(un_,_page,1);
            String obj_ = _page.getAliasObject();
            obj_ = StringExpUtil.getPrettyArrayType(obj_);
            AnaClassArgumentMatching class_ = new AnaClassArgumentMatching(obj_);
            setResultClass(class_);
            return;
        }
        StringMap<StringList> map_;
        map_ = _page.getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setParam(eltType_);
        for (OperationNode o: chidren_) {
            int index_ = getPartOffsetsChildren().size();
            StrTypes operators_ = getOperators();
            InfoErrorDto parts_ = new InfoErrorDto("");
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _page);
            AnaClassArgumentMatching argType_ = o.getResultClass();
            mapping_.setArg(argType_);
            mapping_.setMapping(map_);
            if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(eltType_, argType_, _page);
                if (res_ != null) {
                    argType_.implicitInfos(res_);
                } else {
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
            }
            if (AnaTypeUtil.isPrimitive(eltType_, _page)) {
                o.getResultClass().setUnwrapObject(eltType_, _page.getPrimitiveTypes());
            }
            getPartOffsetsChildren().add(parts_);
        }
        String arrayCl_ = className_;
        setClassName(StringExpUtil.getQuickComponentType(arrayCl_));
        setResultClass(new AnaClassArgumentMatching(arrayCl_));
    }

    public String getTypeInfer() {
        return typeInfer;
    }

    public ResolvedInstance getPartOffsets() {
        return resolvedInstance;
    }

    public InfoErrorDto getPartOffsetsErr() {
        return partOffsetsErr;
    }
}
