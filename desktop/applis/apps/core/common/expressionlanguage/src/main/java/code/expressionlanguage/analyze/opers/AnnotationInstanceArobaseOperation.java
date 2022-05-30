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

    public AnnotationInstanceArobaseOperation(int _index,
                                              int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        instancingAnnotContent = new AnaInstancingAnnotContent();
        instancingAnnotContent.setMethodName(_op.getFctName());
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
        StringMap<AnnotationFieldInfo> fields_ = fields(g_);
        CustList<AssocationOperation> suppliedFields_ = suppliedFields(filter_);
        if (filter_.size() == 1 && suppliedFields_.isEmpty()) {
            if (fields_.size() != 1) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                StrTypes operators_ = getOperators();
                int k_ = operators_.lastKey();
                cast_.setIndexFile(_page, k_);
                //last parenthese
                cast_.buildError(_page.getAnalysisMessages().getAnnotFieldNotUniq());
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
                StringList deep_ = getErrs();
                setPartOffsetsEnd(new InfoErrorDto(StringUtil.join(deep_, ExportCst.JOIN_ERR), _page, k_, 1));
                setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
                return;
            }
            guessUnique(_page, filter_, fields_);
            return;
        }
        checkDuplicates(_page, filter_, suppliedFields_);
        feedNames(suppliedFields_);
        checkFieldExistence(_page, fields_, suppliedFields_);
        mergePossibleErrors(_page);
        for (AssocationOperation e: suppliedFields_) {
            String suppliedKey_ = e.getFieldName();
            for (EntryCust<String, AnnotationFieldInfo> f: fields_.entryList()) {
                crossCheck(_page, e, suppliedKey_, f);
            }
        }
        setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
    }

    private void crossCheck(AnalyzedPageEl _page, AssocationOperation _supplied, String _suppliedKey, EntryCust<String, AnnotationFieldInfo> _field) {
        if (!StringUtil.quickEq(_suppliedKey, _field.getKey())) {
            return;
        }
        String paramName_ = _field.getValue().getType();
        AnaClassArgumentMatching param_ = new AnaClassArgumentMatching(paramName_);
        AnaClassArgumentMatching arg_ = _supplied.getResultClass();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
        mapping_.setArg(arg_);
        mapping_.setParam(param_);
        if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
            if (param_.isArray()) {
                mapping_.setParam(AnaTypeUtil.getQuickComponentType(param_));
                if (AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                    AnnotationTypeInfo i_ = instancingAnnotContent.getFieldNames().getVal(_suppliedKey);
                    i_.setType(paramName_);
                    i_.setWrap(true);
                    return;
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
            _supplied.setErrAff(cast_.getBuiltError());
        }
        AnnotationTypeInfo i_ = instancingAnnotContent.getFieldNames().getVal(_suppliedKey);
        i_.setType(paramName_);
    }

    private void mergePossibleErrors(AnalyzedPageEl _page) {
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            StrTypes operators_ = getOperators();
            if (!operators_.isEmpty()) {
                int k_ = operators_.lastKey();
                setPartOffsetsEnd(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR), _page,k_,1));
            } else {
                setPartOffsetsErr(new InfoErrorDto(StringUtil.join(deep_,ExportCst.JOIN_ERR), _page,1));
            }
        }
    }

    private void checkFieldExistence(AnalyzedPageEl _page, StringMap<AnnotationFieldInfo> _fields, CustList<AssocationOperation> _suppliedFields) {
        for (EntryCust<String, AnnotationFieldInfo> e: _fields.entryList()) {
            if (e.getValue().isOptional()) {
                continue;
            }
            boolean found_ = false;
            for (AssocationOperation f: _suppliedFields) {
                if (StringUtil.quickEq(e.getKey(),f.getFieldName())) {
                    found_ = true;
                    break;
                }
            }
            if (!found_) {
                //ERROR
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                StrTypes operators_ = getOperators();
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
    }

    private void feedNames(CustList<AssocationOperation> _suppliedFields) {
        for (AssocationOperation f: _suppliedFields) {
            AnnotationTypeInfo i_ = new AnnotationTypeInfo();
            instancingAnnotContent.getFieldNames().put(f.getFieldName(),i_);
        }
    }

    private void checkDuplicates(AnalyzedPageEl _page, CustList<OperationNode> _filter, CustList<AssocationOperation> _suppliedFields) {
        if (_filter.size() != _suppliedFields.size()) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            StrTypes operators_ = getOperators();
            int k_ = operators_.lastKey();
            cast_.setIndexFile(_page,k_);
            //last parenthese
            cast_.buildError(_page.getAnalysisMessages().getAnnotFieldNotUniq());
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
        }
        IdMap<AssocationOperation,Integer> counts_ = new IdMap<AssocationOperation,Integer>();
        for (AssocationOperation s: _suppliedFields) {
            counts_.put(s,0);
        }
        for (AssocationOperation s: _suppliedFields) {
            int c_ = 0;
            for (AssocationOperation t: _suppliedFields) {
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
    }

    private void guessUnique(AnalyzedPageEl _page, CustList<OperationNode> _filter, StringMap<AnnotationFieldInfo> _fields) {
        //guess the unique field
        AnaClassArgumentMatching arg_ = _filter.first().getResultClass();
        String paramName_ = _fields.getValue(0).getType();
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
                    instancingAnnotContent.getFieldNames().put(_fields.getKey(0), i_);
                    setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
                    return;
                }
            }
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            StrTypes operators_ = getOperators();
            int k_ = operators_.firstKey();
            cast_.setIndexFile(_page, k_);
            //first parenthese
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(arg_.getNames(), ExportCst.JOIN_TYPES),
                    StringUtil.join(param_.getNames(), ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
            StringList deep_ = getErrs();
            setPartOffsetsErrPar(new InfoErrorDto(StringUtil.join(deep_, ExportCst.JOIN_ERR), _page, k_, 1));
        }
        AnnotationTypeInfo i_ = new AnnotationTypeInfo();
        i_.setType(paramName_);
        instancingAnnotContent.getFieldNames().put(_fields.getKey(0), i_);
        setResultClass(new AnaClassArgumentMatching(instancingAnnotContent.getClassName()));
    }

    private CustList<AssocationOperation> suppliedFields(CustList<OperationNode> _filter) {
        CustList<AssocationOperation> suppliedFields_ = new CustList<AssocationOperation>();
        for (OperationNode o: _filter) {
            if (!(o instanceof AssocationOperation)) {
                continue;
            }
            AssocationOperation a_ = (AssocationOperation) o;
            suppliedFields_.add(a_);
        }
        return suppliedFields_;
    }

    private StringMap<AnnotationFieldInfo> fields(RootBlock _g) {
        StringMap<AnnotationFieldInfo> fields_ = new StringMap<AnnotationFieldInfo>();
        for (AbsBk b: ClassesUtil.getDirectChildren(_g)) {
            if (!AbsBk.isAnnotBlock(b)) {
                continue;
            }
            NamedCalledFunctionBlock a_ = (NamedCalledFunctionBlock) b;
            fields_.put(a_.getName(), new AnnotationFieldInfo(a_.getImportedReturnType(),!a_.getDefaultValue().isEmpty()));
        }
        return fields_;
    }

    public AnaInstancingAnnotContent getInstancingAnnotContent() {
        return instancingAnnotContent;
    }
}
