package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.opers.util.AnnotationFieldInfo;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaInstancingAnnotContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.StringUtil;

public final class AnnotationInstanceArobaseOperation extends AnnotationInstanceOperation {
    private AnaResultPartType partOffsets = new AnaResultPartType();

    private final AnaInstancingAnnotContent instancingAnnotContent;

    private int rootNumber = -1;

    public AnnotationInstanceArobaseOperation(int _index,
                                              int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        instancingAnnotContent = new AnaInstancingAnnotContent();
        instancingAnnotContent.setMethodName(getOperations().getFctName());
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingAnnotContent.getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        String realClassName_ = instancingAnnotContent.getMethodName().trim().substring(AROBASE.length());
        partOffsets = ResolvingTypes.resolveCorrectType(1 + StringExpUtil.getOffset(realClassName_), realClassName_, _page);
        realClassName_ = partOffsets.getResult(_page);
        AnaFormattedRootBlock form_ = new AnaFormattedRootBlock(_page,realClassName_);
        RootBlock g_ = form_.getRootBlock();
        if (!(g_ instanceof AnnotationBlock)) {
            instancingAnnotContent.setClassName(_page.getAliasObject());
            setResultClass(new AnaClassArgumentMatching(realClassName_));
            return;
        }
        rootNumber = g_.getNumberAll();
        instancingAnnotContent.setClassName(realClassName_);
        instancingAnnotContent.setFormattedType(form_);
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public String getClassName() {
        return instancingAnnotContent.getClassName();
    }
    @Override
    public void analyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingAnnotContent.getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        if (isIntermediateDottedOperation()){
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    _page.getAliasObject());
            _page.getLocalizer().addError(un_);
            setPartOffsetsErr(new InfoErrorDto(un_,_page,1));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setStaticAccess(_page.getStaticContext());
        analyzeCtor(_page);
    }

    private void analyzeCtor(AnalyzedPageEl _page) {
        CustList<OperationNode> filter_ = getChildrenNodes();
        String objCl_ = _page.getAliasObject();
        if (StringUtil.quickEq(instancingAnnotContent.getClassName(), objCl_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //text header after @
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorAnnotation(),
                    instancingAnnotContent.getMethodName().trim().substring(AROBASE.length()).trim());
            _page.getLocalizer().addError(call_);
            setPartOffsetsErr(new InfoErrorDto(call_,_page,1));
            setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
            return;
        }

        RootBlock g_ = _page.getAnaClassBody(instancingAnnotContent.getClassName());
        StringMap<AnnotationFieldInfo> fields_ = new StringMap<AnnotationFieldInfo>();
        for (AbsBk b: ClassesUtil.getDirectChildren(g_)) {
            if (!AbsBk.isAnnotBlock(b)) {
                continue;
            }
            NamedCalledFunctionBlock a_ = (NamedCalledFunctionBlock) b;
            fields_.put(a_.getName(), new AnnotationFieldInfo(a_.getImportedReturnType(),!a_.getDefaultValue().isEmpty()));
        }
        CustList<AssocationOperation> suppliedFields_ = new CustList<AssocationOperation>();
        for (OperationNode o: filter_) {
            if (!(o instanceof AssocationOperation)) {
                continue;
            }
            AssocationOperation a_ = (AssocationOperation) o;
            suppliedFields_.add(a_);
        }
        if (filter_.size() == 1 && suppliedFields_.isEmpty()) {
            if (fields_.size() == 1) {
                //guess the unique field
                AnaClassArgumentMatching arg_ = filter_.first().getResultClass();
                String paramName_ = fields_.getValue(0).getType();
                AnaClassArgumentMatching param_ = new AnaClassArgumentMatching(paramName_);
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
                mapping_.setArg(arg_);
                mapping_.setParam(param_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    if (param_.isArray()) {
                        mapping_.setParam(AnaTypeUtil.getQuickComponentType(param_));
                        if (AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                            AnnotationTypeInfo i_ = new AnnotationTypeInfo();
                            i_.setType(paramName_);
                            i_.setWrap(true);
                            instancingAnnotContent.getFieldNames().put(fields_.getKey(0), i_);
                            setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
                            return;
                        }
                    }
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    StrTypes operators_ = getOperations().getOperators();
                    int k_ = operators_.firstKey();
                    cast_.setIndexFile(_page,k_);
                    //first parenthese
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(arg_.getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(param_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    addErr(cast_.getBuiltError());
                    StringList deep_ = getErrs();
                    setPartOffsetsErrPar(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR),_page,k_,1));
                }
                AnnotationTypeInfo i_ = new AnnotationTypeInfo();
                i_.setType(paramName_);
                instancingAnnotContent.getFieldNames().put(fields_.getKey(0),i_);
                setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
                return;
            }
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            StrTypes operators_ = getOperations().getOperators();
            int k_ = operators_.lastKey();
            cast_.setIndexFile(_page,k_);
            //last parenthese
            cast_.buildError(_page.getAnalysisMessages().getAnnotFieldNotUniq());
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
            StringList deep_ = getErrs();
            setPartOffsetsEnd(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR),_page,k_,1));
            setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
            return;
        }
        if (filter_.size() != suppliedFields_.size()) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            StrTypes operators_ = getOperations().getOperators();
            int k_ = operators_.lastKey();
            cast_.setIndexFile(_page,k_);
            //last parenthese
            cast_.buildError(_page.getAnalysisMessages().getAnnotFieldNotUniq());
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
        }
        IdMap<AssocationOperation,Integer> counts_ = new IdMap<AssocationOperation,Integer>();
        for (AssocationOperation s: suppliedFields_) {
            counts_.put(s,0);
        }
        for (AssocationOperation s: suppliedFields_) {
            int c_ = 0;
            for (AssocationOperation t: suppliedFields_) {
                if (StringUtil.quickEq(s.getFieldName(), t.getFieldName())) {
                    c_++;
                }
            }
            counts_.put(s, c_);
        }
        for (EntryCust<AssocationOperation,Integer> e: counts_.entryList()) {
            if (e.getValue() > 1) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //key len at operation
                cast_.buildError(_page.getAnalysisMessages().getDupSuppliedAnnotField(),
                        e.getKey().getFieldName()
                );
                _page.getLocalizer().addError(cast_);
                e.getKey().addErr(cast_.getBuiltError());
            }
        }
        for (AssocationOperation f: suppliedFields_) {
            AnnotationTypeInfo i_ = new AnnotationTypeInfo();
            instancingAnnotContent.getFieldNames().put(f.getFieldName(),i_);
        }
        for (EntryCust<String, AnnotationFieldInfo> e: fields_.entryList()) {
            if (e.getValue().isOptional()) {
                continue;
            }
            boolean found_ = false;
            for (AssocationOperation f: suppliedFields_) {
                if (StringUtil.quickEq(e.getKey(),f.getFieldName())) {
                    found_ = true;
                    break;
                }
            }
            if (!found_) {
                //ERROR
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                StrTypes operators_ = getOperations().getOperators();
                int k_ = 0;
                if (!operators_.isEmpty()) {
                    k_ = operators_.lastKey();
                }
                cast_.setIndexFile(_page,k_);
                //last parenthese
                cast_.buildError(_page.getAnalysisMessages().getAnnotFieldMust(),
                        e.getKey());
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
            }
        }
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            StrTypes operators_ = getOperations().getOperators();
            if (!operators_.isEmpty()) {
                int k_ = operators_.lastKey();
                setPartOffsetsEnd(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR),_page,k_,1));
            } else {
                setPartOffsetsErr(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR),_page,1));
            }
        }
        for (AssocationOperation e: suppliedFields_) {
            String suppliedKey_ = e.getFieldName();
            for (EntryCust<String, AnnotationFieldInfo> f: fields_.entryList()) {
                if (!StringUtil.quickEq(suppliedKey_, f.getKey())) {
                    continue;
                }
                String paramName_ = f.getValue().getType();
                AnaClassArgumentMatching param_ = new AnaClassArgumentMatching(paramName_);
                AnaClassArgumentMatching arg_ = e.getResultClass();
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
                mapping_.setArg(arg_);
                mapping_.setParam(param_);
                if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    if (param_.isArray()) {
                        mapping_.setParam(AnaTypeUtil.getQuickComponentType(param_));
                        if (AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                            AnnotationTypeInfo i_ = instancingAnnotContent.getFieldNames().getVal(suppliedKey_);
                            i_.setType(paramName_);
                            i_.setWrap(true);
                            continue;
                        }
                    }
                    //ERROR
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page);
                    //equal char
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(arg_.getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(param_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    e.setErrAff(cast_.getBuiltError());
                }
                AnnotationTypeInfo i_ = instancingAnnotContent.getFieldNames().getVal(suppliedKey_);
                i_.setType(paramName_);
            }
        }
        setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public AnaInstancingAnnotContent getInstancingAnnotContent() {
        return instancingAnnotContent;
    }
}
