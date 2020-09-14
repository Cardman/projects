package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.common.AnnotationFieldInfo;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;

public final class AnnotationInstanceOperation extends InvokingOperation implements PreAnalyzableOperation {

    private String methodName;

    private String className;
    private StringMap<AnnotationTypeInfo> fieldNames = new StringMap<AnnotationTypeInfo>();
    private boolean array;

    private CustList<PartOffset> partOffsetsErr = new CustList<PartOffset>();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private CustList<PartOffset> partOffsetsErrPar = new CustList<PartOffset>();
    private int rootNumber = -1;

    public AnnotationInstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void preAnalyze(ContextEl _conf) {
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        if (methodName.trim().isEmpty()) {
            array = true;
            MethodOperation mOp_ = getParent();
            Block curr_ = page_.getCurrentBlock();
            className = page_.getStandards().getAliasObject();
            if (mOp_ == null) {
                className = ((AnnotationMethodBlock)curr_).getImportedReturnType();
            }
            if (mOp_ instanceof AssocationOperation) {
                AssocationOperation ass_ = (AssocationOperation) mOp_;
                String fieldName_ = ass_.getFieldName();
                MethodOperation mOpAss_ = ass_.getParent();
                AnnotationInstanceOperation inst_;
                inst_ = (AnnotationInstanceOperation)mOpAss_;
                String className_ = inst_.getClassName();
                RootBlock typeInfo_ = page_.getAnaClassBody(className_);
                if (typeInfo_ == null) {
                    className = page_.getStandards().getAliasObject();
                    return;
                }
                String type_ = EMPTY_STRING;
                CustList<AnnotationMethodBlock> list_ = ClassesUtil.getMethodAnnotationBodiesById(typeInfo_, fieldName_);
                if (!list_.isEmpty()) {
                    type_ = list_.first().getImportedReturnType();
                }
                if (!type_.isEmpty()) {
                    className = type_;
                } else {
                    className = page_.getStandards().getAliasObject();
                }
            } else if (mOp_ instanceof AnnotationInstanceOperation) {
                if (((AnnotationInstanceOperation)mOp_).isArray()) {
                    className = page_.getStandards().getAliasObject();
                } else {
                    AnnotationInstanceOperation inst_;
                    inst_ = (AnnotationInstanceOperation)mOp_;
                    String className_ = inst_.getClassName();
                    RootBlock type_ = page_.getAnaClassBody(className_);
                    if (type_ == null) {
                        className = page_.getStandards().getAliasObject();
                        return;
                    }
                    CustList<Block> bls_ = ClassesUtil.getDirectChildren(type_);
                    CustList<AnnotationMethodBlock> blsAnn_ = new CustList<AnnotationMethodBlock>();
                    for (Block b: bls_) {
                        if (!(b instanceof AnnotationMethodBlock)) {
                            continue;
                        }
                        AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
                        blsAnn_.add(a_);
                    }
                    if (blsAnn_.size() != 1) {
                        className = page_.getStandards().getAliasObject();
                    } else {
                        AnnotationMethodBlock a_ =blsAnn_.first();
                        className = a_.getImportedReturnType();
                    }
                }
            }
        } else {
            int off_ = StringList.getFirstPrintableCharIndex(methodName);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
            String realClassName_ = methodName.trim().substring(AROBASE.length());
            realClassName_ = ResolvingImportTypes.resolveCorrectType(_conf,1,realClassName_);
            partOffsets.addAllElts(page_.getCurrentParts());
            RootBlock g_ = page_.getAnaClassBody(realClassName_);
            if (!(g_ instanceof AnnotationBlock)) {
                className = page_.getStandards().getAliasObject();
                setResultClass(new ClassArgumentMatching(realClassName_));
                return;
            }
            rootNumber = g_.getNumberAll();
            className = realClassName_;
        }
    }
    public boolean isArray() {
        return array;
    }
    public String getClassName() {
        return className;
    }
    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (array) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        } else {
            int off_ = StringList.getFirstPrintableCharIndex(methodName);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        }
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        if (isIntermediateDottedOperation()){
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            int i_ = page_.getLocalizer().getCurrentLocationIndex();
            un_.setIndexFile(i_);
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                    page_.getStandards().getAliasObject());
            page_.getLocalizer().addError(un_);
            partOffsetsErr.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            setResultClass(new ClassArgumentMatching(page_.getStandards().getAliasObject()));
            return;
        }
        if (array) {
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            String eltType_ = StringExpUtil.getQuickComponentType(className);
            if (eltType_ == null) {
                IntTreeMap<String> operators_ = getOperations().getOperators();
                int offFirstOp_ = operators_.firstKey();
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                int i_ = page_.getLocalizer().getCurrentLocationIndex()+offFirstOp_;
                un_.setIndexFile(i_);
                un_.setFileName(page_.getLocalizer().getCurrentFileName());
                //first separator char
                un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                        className);
                page_.getLocalizer().addError(un_);
                partOffsetsErr.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
                partOffsetsErr.add(new PartOffset("</a>",i_+1));
                setResultClass(new ClassArgumentMatching(className));
                return;
            }
            Mapping mapping_ = new Mapping();
            mapping_.setParam(eltType_);
            for (OperationNode o: chidren_) {
                int index_ = getPartOffsetsChildren().size();
                IntTreeMap<String> operators_ = getOperations().getOperators();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(index_), _conf);
                int i_ = page_.getLocalizer().getCurrentLocationIndex();
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                ClassArgumentMatching argType_ = o.getResultClass();
                mapping_.setArg(argType_);
                mapping_.setMapping(map_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(i_);
                    //first separator char child
                    cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(argType_.getNames(),"&"),
                            eltType_);
                    page_.getLocalizer().addError(cast_);
                    parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                    parts_.add(new PartOffset("</a>",i_+1));
                }
                if (AnaTypeUtil.isPrimitive(eltType_, page_)) {
                    o.getResultClass().setUnwrapObject(eltType_);
                    o.cancelArgument();
                }
                getPartOffsetsChildren().add(parts_);
            }
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        setStaticAccess(page_.getStaticContext());
        analyzeCtor(_conf);
    }

    private void analyzeCtor(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        String objCl_ = page_.getStandards().getAliasObject();
        if (StringList.quickEq(className, objCl_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(page_.getLocalizer().getCurrentFileName());
            int i_ = page_.getLocalizer().getCurrentLocationIndex();
            call_.setIndexFile(i_);
            //text header after @
            call_.buildError(_conf.getAnalyzing().getAnalysisMessages().getIllegalCtorAnnotation(),
                    methodName.trim().substring(AROBASE.length()).trim());
            page_.getLocalizer().addError(call_);
            partOffsetsErr.add(new PartOffset("<a title=\""+call_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            setResultClass(new ClassArgumentMatching(className));
            return;
        }

        RootBlock g_ = page_.getAnaClassBody(className);
        StringMap<AnnotationFieldInfo> fields_ = new StringMap<AnnotationFieldInfo>();
        for (Block b: ClassesUtil.getDirectChildren(g_)) {
            if (!(b instanceof AnnotationMethodBlock)) {
                continue;
            }
            AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
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
                ClassArgumentMatching arg_ = filter_.first().getResultClass();
                String paramName_ = fields_.getValue(0).getType();
                ClassArgumentMatching param_ = new ClassArgumentMatching(paramName_);
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(page_.getCurrentConstraints().getCurrentConstraints());
                mapping_.setArg(arg_);
                mapping_.setParam(param_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_,_conf)) {
                    if (param_.isArray()) {
                        ClassArgumentMatching c_ = StringExpUtil.getQuickComponentType(param_);
                        mapping_.setParam(c_);
                        if (AnaTemplates.isCorrectOrNumbers(mapping_,_conf)) {
                            AnnotationTypeInfo i_ = new AnnotationTypeInfo();
                            i_.setType(paramName_);
                            i_.setWrap(true);
                            fieldNames.put(fields_.getKey(0), i_);
                            setResultClass(new ClassArgumentMatching(className));
                            return;
                        }
                    }
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                    IntTreeMap<String> operators_ = getOperations().getOperators();
                    int k_ = operators_.firstKey();
                    cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex()+k_);
                    //first parenthese
                    cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(arg_.getNames(),"&"),
                            StringList.join(param_.getNames(),"&"));
                    page_.getLocalizer().addError(cast_);
                    getErrs().add(cast_.getBuiltError());
                    StringList deep_ = getErrs();
                    int i_ = page_.getLocalizer().getCurrentLocationIndex()+k_;
                    partOffsetsErrPar.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
                    partOffsetsErrPar.add(new PartOffset("</a>",i_+1));
                }
                AnnotationTypeInfo i_ = new AnnotationTypeInfo();
                i_.setType(paramName_);
                fieldNames.put(fields_.getKey(0),i_);
                setResultClass(new ClassArgumentMatching(className));
                return;
            }
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(page_.getLocalizer().getCurrentFileName());
            IntTreeMap<String> operators_ = getOperations().getOperators();
            int k_ = operators_.lastKey();
            cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex()+k_);
            //last parenthese
            cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getAnnotFieldNotUniq());
            page_.getLocalizer().addError(cast_);
            getErrs().add(cast_.getBuiltError());
            StringList deep_ = getErrs();
            int i_ = page_.getLocalizer().getCurrentLocationIndex()+k_;
            getPartOffsetsEnd().add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
            getPartOffsetsEnd().add(new PartOffset("</a>",i_+1));
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        if (filter_.size() != suppliedFields_.size()) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(page_.getLocalizer().getCurrentFileName());
            IntTreeMap<String> operators_ = getOperations().getOperators();
            int k_ = operators_.lastKey();
            cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex()+k_);
            //last parenthese
            cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getAnnotFieldNotUniq());
            page_.getLocalizer().addError(cast_);
            getErrs().add(cast_.getBuiltError());
        }
        IdMap<AssocationOperation,Integer> counts_ = new IdMap<AssocationOperation,Integer>();
        for (AssocationOperation s: suppliedFields_) {
            counts_.put(s,0);
        }
        for (AssocationOperation s: suppliedFields_) {
            int c_ = 0;
            for (AssocationOperation t: suppliedFields_) {
                if (StringList.quickEq(s.getFieldName(), t.getFieldName())) {
                    c_++;
                }
            }
            counts_.put(s, c_);
        }
        for (EntryCust<AssocationOperation,Integer> e: counts_.entryList()) {
            if (e.getValue() > 1) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //key len at operation
                cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getDupSuppliedAnnotField(),
                        e.getKey().getFieldName()
                );
                page_.getLocalizer().addError(cast_);
                e.getKey().getErrs().add(cast_.getBuiltError());
            }
        }
        for (AssocationOperation f: suppliedFields_) {
            AnnotationTypeInfo i_ = new AnnotationTypeInfo();
            fieldNames.put(f.getFieldName(),i_);
        }
        for (EntryCust<String, AnnotationFieldInfo> e: fields_.entryList()) {
            if (e.getValue().isOptional()) {
                continue;
            }
            boolean found_ = false;
            for (AssocationOperation f: suppliedFields_) {
                if (StringList.quickEq(e.getKey(),f.getFieldName())) {
                    found_ = true;
                    break;
                }
            }
            if (!found_) {
                //ERROR
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                IntTreeMap<String> operators_ = getOperations().getOperators();
                int k_ = 0;
                if (!operators_.isEmpty()) {
                    k_ = operators_.lastKey();
                }
                cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex()+ k_);
                //last parenthese
                cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getAnnotFieldMust(),
                        e.getKey());
                page_.getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
            }
        }
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            IntTreeMap<String> operators_ = getOperations().getOperators();
            if (!operators_.isEmpty()) {
                int k_ = operators_.lastKey();
                int i_ = page_.getLocalizer().getCurrentLocationIndex()+k_;
                getPartOffsetsEnd().add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
                getPartOffsetsEnd().add(new PartOffset("</a>",i_+1));
            } else {
                int i_ = page_.getLocalizer().getCurrentLocationIndex();
                partOffsetsErr.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n"))+"\" class=\"e\">",i_));
                partOffsetsErr.add(new PartOffset("</a>",i_+1));
            }
        }
        for (AssocationOperation e: suppliedFields_) {
            String suppliedKey_ = e.getFieldName();
            for (EntryCust<String, AnnotationFieldInfo> f: fields_.entryList()) {
                if (!StringList.quickEq(suppliedKey_, f.getKey())) {
                    continue;
                }
                String paramName_ = f.getValue().getType();
                ClassArgumentMatching param_ = new ClassArgumentMatching(paramName_);
                ClassArgumentMatching arg_ = e.getResultClass();
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(page_.getCurrentConstraints().getCurrentConstraints());
                mapping_.setArg(arg_);
                mapping_.setParam(param_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_,_conf)) {
                    if (param_.isArray()) {
                        ClassArgumentMatching c_ = StringExpUtil.getQuickComponentType(param_);
                        mapping_.setParam(c_);
                        if (AnaTemplates.isCorrectOrNumbers(mapping_,_conf)) {
                            AnnotationTypeInfo i_ = fieldNames.getVal(suppliedKey_);
                            i_.setType(paramName_);
                            i_.setWrap(true);
                            continue;
                        }
                    }
                    //ERROR
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                    //equal char
                    cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(arg_.getNames(),"&"),
                            StringList.join(param_.getNames(),"&"));
                    page_.getLocalizer().addError(cast_);
                    e.setErrAff(cast_.getBuiltError());
                }
                AnnotationTypeInfo i_ = fieldNames.getVal(suppliedKey_);
                i_.setType(paramName_);
            }
        }
        setResultClass(new ClassArgumentMatching(className));
    }

    public String getMethodName() {
        return methodName;
    }

    public StringMap<AnnotationTypeInfo> getFieldNames() {
        return fieldNames;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public CustList<PartOffset> getPartOffsetsErr() {
        return partOffsetsErr;
    }

    public CustList<PartOffset> getPartOffsetsErrPar() {
        return partOffsetsErrPar;
    }

    public int getRootNumber() {
        return rootNumber;
    }
}
