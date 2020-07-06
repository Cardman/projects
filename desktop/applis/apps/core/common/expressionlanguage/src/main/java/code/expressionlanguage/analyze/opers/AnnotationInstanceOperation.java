package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.blocks.AnnotationMethodBlock;
import code.expressionlanguage.analyze.blocks.Block;
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

    public AnnotationInstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void preAnalyze(ContextEl _conf) {
        if (methodName.trim().isEmpty()) {
            array = true;
            MethodOperation mOp_ = getParent();
            Block curr_ = _conf.getAnalyzing().getCurrentBlock();
            className = _conf.getStandards().getAliasObject();
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
                GeneType typeInfo_ = _conf.getClassBody(className_);
                if (!(typeInfo_ instanceof ExecBlock)) {
                    className = _conf.getStandards().getAliasObject();
                    return;
                }
                ExecBlock ann_ = (ExecBlock) typeInfo_;
                String type_ = EMPTY_STRING;
                CustList<ExecAnnotationMethodBlock> list_ = ExecBlock.getMethodAnnotationBodiesById(ann_, fieldName_);
                if (!list_.isEmpty()) {
                    type_ = list_.first().getImportedReturnType();
                }
                if (!type_.isEmpty()) {
                    className = type_;
                } else {
                    className = _conf.getStandards().getAliasObject();
                }
            } else if (mOp_ instanceof AnnotationInstanceOperation) {
                if (((AnnotationInstanceOperation)mOp_).isArray()) {
                    className = _conf.getStandards().getAliasObject();
                } else {
                    AnnotationInstanceOperation inst_;
                    inst_ = (AnnotationInstanceOperation)mOp_;
                    String className_ = inst_.getClassName();
                    GeneType type_ = _conf.getClassBody(className_);
                    if (!(type_ instanceof ExecBlock)) {
                        className = _conf.getStandards().getAliasObject();
                        return;
                    }
                    ExecBlock ann_ = (ExecBlock) type_;
                    CustList<ExecBlock> bls_ = ExecBlock.getDirectChildren(ann_);
                    CustList<ExecAnnotationMethodBlock> blsAnn_ = new CustList<ExecAnnotationMethodBlock>();
                    for (ExecBlock b: bls_) {
                        if (!(b instanceof ExecAnnotationMethodBlock)) {
                            continue;
                        }
                        ExecAnnotationMethodBlock a_ = (ExecAnnotationMethodBlock) b;
                        blsAnn_.add(a_);
                    }
                    if (blsAnn_.size() != 1) {
                        className = _conf.getStandards().getAliasObject();
                    } else {
                        ExecAnnotationMethodBlock a_ =blsAnn_.first();
                        className = a_.getImportedReturnType();
                    }
                }
            }
        } else {
            int off_ = StringList.getFirstPrintableCharIndex(methodName);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
            String realClassName_ = methodName.trim().substring(AROBASE.length());
            realClassName_ = ResolvingImportTypes.resolveCorrectType(_conf,1,realClassName_);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
            GeneType g_ = _conf.getClassBody(realClassName_);
            if (!(g_ instanceof ExecAnnotationBlock)) {
                className = _conf.getStandards().getAliasObject();
                setResultClass(new ClassArgumentMatching(realClassName_));
                return;
            }
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
        if (isIntermediateDottedOperation()){
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            un_.setIndexFile(i_);
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    _conf.getStandards().getAliasObject());
            _conf.getAnalyzing().getLocalizer().addError(un_);
            partOffsetsErr.add(new PartOffset("<a title=\""+un_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
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
                int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+offFirstOp_;
                un_.setIndexFile(i_);
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                //first separator char
                un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                        className);
                _conf.getAnalyzing().getLocalizer().addError(un_);
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
                int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                ClassArgumentMatching argType_ = o.getResultClass();
                mapping_.setArg(argType_);
                mapping_.setMapping(map_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(i_);
                    //first separator char child
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(argType_.getNames(),"&"),
                            eltType_);
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
                    parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                    parts_.add(new PartOffset("</a>",i_+1));
                }
                if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                    o.getResultClass().setUnwrapObject(eltType_);
                    o.cancelArgument();
                }
                getPartOffsetsChildren().add(parts_);
            }
            IntTreeMap<String> operators_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.lastKey(), _conf);
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            StringList deep_ = getErrs();
            if (!deep_.isEmpty()) {
                getPartOffsetsEnd().add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
                getPartOffsetsEnd().add(new PartOffset("</a>",i_+1));
            }
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        setStaticAccess(_conf.getAnalyzing().getStaticContext());
        analyzeCtor(_conf);
    }

    private void analyzeCtor(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        String objCl_ = _conf.getStandards().getAliasObject();
        if (StringList.quickEq(className, objCl_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            call_.setIndexFile(i_);
            //text header after @
            call_.buildError(_conf.getAnalysisMessages().getIllegalCtorAnnotation(),
                    methodName.trim().substring(AROBASE.length()).trim());
            _conf.getAnalyzing().getLocalizer().addError(call_);
            partOffsetsErr.add(new PartOffset("<a title=\""+call_.getBuiltError()+"\" class=\"e\">",i_));
            partOffsetsErr.add(new PartOffset("</a>",i_+1));
            setResultClass(new ClassArgumentMatching(className));
            return;
        }

        GeneType g_ = _conf.getClassBody(className);
        StringMap<AnnotationFieldInfo> fields_ = new StringMap<AnnotationFieldInfo>();
        for (ExecBlock b: ExecBlock.getDirectChildren((ExecBlock)g_)) {
            if (!(b instanceof ExecAnnotationMethodBlock)) {
                continue;
            }
            ExecAnnotationMethodBlock a_ = (ExecAnnotationMethodBlock) b;
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
                mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
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
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    IntTreeMap<String> operators_ = getOperations().getOperators();
                    int k_ = operators_.firstKey();
                    cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+k_);
                    //first parenthese
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(arg_.getNames(),"&"),
                            StringList.join(param_.getNames(),"&"));
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
                    getErrs().add(cast_.getBuiltError());
                    StringList deep_ = getErrs();
                    int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+k_;
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
            cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            IntTreeMap<String> operators_ = getOperations().getOperators();
            int k_ = operators_.lastKey();
            cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+k_);
            //last parenthese
            cast_.buildError(_conf.getAnalysisMessages().getAnnotFieldNotUniq());
            _conf.getAnalyzing().getLocalizer().addError(cast_);
            getErrs().add(cast_.getBuiltError());
            StringList deep_ = getErrs();
            int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+k_;
            getPartOffsetsEnd().add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
            getPartOffsetsEnd().add(new PartOffset("</a>",i_+1));
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        if (filter_.size() != suppliedFields_.size()) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            IntTreeMap<String> operators_ = getOperations().getOperators();
            int k_ = operators_.lastKey();
            cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+k_);
            //last parenthese
            cast_.buildError(_conf.getAnalysisMessages().getAnnotFieldNotUniq());
            _conf.getAnalyzing().getLocalizer().addError(cast_);
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
                cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //key len at operation
                cast_.buildError(_conf.getAnalysisMessages().getDupSuppliedAnnotField(),
                        e.getKey().getFieldName()
                );
                _conf.getAnalyzing().getLocalizer().addError(cast_);
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
                cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                IntTreeMap<String> operators_ = getOperations().getOperators();
                int k_ = 0;
                if (!operators_.isEmpty()) {
                    k_ = operators_.lastKey();
                }
                cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+ k_);
                //last parenthese
                cast_.buildError(_conf.getAnalysisMessages().getAnnotFieldMust(),
                        e.getKey());
                _conf.getAnalyzing().getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
            }
        }
        StringList deep_ = getErrs();
        if (!deep_.isEmpty()) {
            IntTreeMap<String> operators_ = getOperations().getOperators();
            if (!operators_.isEmpty()) {
                int k_ = operators_.lastKey();
                int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex()+k_;
                getPartOffsetsEnd().add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(deep_,"\n\n")) +"\" class=\"e\">",i_));
                getPartOffsetsEnd().add(new PartOffset("</a>",i_+1));
            } else {
                int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
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
                mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
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
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //equal char
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(arg_.getNames(),"&"),
                            StringList.join(param_.getNames(),"&"));
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
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
}
