package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.AnnotationBlock;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.AnnotationFieldInfo;
import code.expressionlanguage.opers.util.AnnotationTypeInfo;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.util.*;

public final class AnnotationInstanceOperation extends InvokingOperation implements PreAnalyzableOperation {

    private String methodName;

    private String className;
    private StringMap<AnnotationTypeInfo> fieldNames = new StringMap<AnnotationTypeInfo>();
    private boolean array;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public AnnotationInstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void preAnalyze(Analyzable _conf) {
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
                GeneType typeInfo_ = _conf.getContextEl().getClassBody(className_);
                if (!(typeInfo_ instanceof Block)) {
                    className = _conf.getStandards().getAliasObject();
                    return;
                }
                Block ann_ = (Block) typeInfo_;
                String type_ = EMPTY_STRING;
                CustList<AnnotationMethodBlock> list_ = Classes.getMethodAnnotationBodiesById(ann_, fieldName_);
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
                    GeneType type_ = _conf.getContextEl().getClassBody(className_);
                    if (!(type_ instanceof Block)) {
                        className = _conf.getStandards().getAliasObject();
                        return;
                    }
                    Block ann_ = (Block) type_;
                    CustList<Block> bls_ = Classes.getDirectChildren(ann_);
                    CustList<AnnotationMethodBlock> blsAnn_ = new CustList<AnnotationMethodBlock>();
                    for (Block b: bls_) {
                        if (!(b instanceof AnnotationMethodBlock)) {
                            continue;
                        }
                        AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
                        blsAnn_.add(a_);
                    }
                    if (blsAnn_.size() != 1) {
                        className = _conf.getStandards().getAliasObject();
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
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            GeneType g_ = _conf.getContextEl().getClassBody(realClassName_);
            if (!(g_ instanceof AnnotationBlock)) {
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
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (isIntermediateDottedOperation()){
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            un_.setFileName(_conf.getCurrentFileName());
            //first separator char
            un_.buildError(_conf.getContextEl().getAnalysisMessages().getUnexpectedType(),
                    _conf.getStandards().getAliasObject());
            _conf.addError(un_);
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        if (array) {
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            String eltType_ = PrimitiveTypeUtil.getQuickComponentType(className);
            if (eltType_ == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                un_.setFileName(_conf.getCurrentFileName());
                //first separator char
                un_.buildError(_conf.getContextEl().getAnalysisMessages().getUnexpectedType(),
                        className);
                _conf.addError(un_);
                setResultClass(new ClassArgumentMatching(className));
                return;
            }
            Mapping mapping_ = new Mapping();
            mapping_.setParam(eltType_);
            for (OperationNode o: chidren_) {
                setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
                ClassArgumentMatching argType_ = o.getResultClass();
                mapping_.setArg(argType_);
                mapping_.setMapping(map_);
                if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    //first separator char child
                    cast_.buildError(_conf.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(argType_.getNames(),"&"),
                            eltType_);
                    _conf.addError(cast_);
                }
                if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                    o.getResultClass().setUnwrapObject(eltType_);
                    o.cancelArgument();
                }
            }
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        setStaticAccess(_conf.getAnalyzing().getStaticContext());
        analyzeCtor(_conf);
    }

    private void analyzeCtor(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        String objCl_ = _conf.getStandards().getAliasObject();
        if (StringList.quickEq(className, objCl_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            //text header after @
            call_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorAnnotation(),
                    methodName.trim().substring(AROBASE.length()).trim());
            _conf.addError(call_);
            setResultClass(new ClassArgumentMatching(className));
            return;
        }

        GeneType g_ = _conf.getContextEl().getClassBody(className);
        StringMap<AnnotationFieldInfo> fields_ = new StringMap<AnnotationFieldInfo>();
        for (Block b: Classes.getDirectChildren((Block)g_)) {
            if (!(b instanceof AnnotationMethodBlock)) {
                continue;
            }
            AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
            fields_.put(a_.getName(), new AnnotationFieldInfo(a_.getImportedReturnType(),!a_.getDefaultValue().isEmpty()));
        }
        StringList suppliedFields_ = new StringList();
        StringMap<ClassArgumentMatching> suppliedFieldsType_ = new StringMap<ClassArgumentMatching>();
        for (OperationNode o: filter_) {
            if (!(o instanceof AssocationOperation)) {
                continue;
            }
            AssocationOperation a_ = (AssocationOperation) o;
            suppliedFields_.add(a_.getFieldName());
            suppliedFieldsType_.put(a_.getFieldName(), a_.getResultClass());
        }
        if (filter_.size() == 1 && suppliedFieldsType_.isEmpty()) {
            if (fields_.size() == 1) {
                //guess the unique field
                ClassArgumentMatching arg_ = filter_.first().getResultClass();
                String paramName_ = fields_.getValue(0).getType();
                ClassArgumentMatching param_ = new ClassArgumentMatching(paramName_);
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
                mapping_.setArg(arg_);
                mapping_.setParam(param_);
                if (!Templates.isCorrectOrNumbers(mapping_,_conf)) {
                    if (param_.isArray()) {
                        ClassArgumentMatching c_ = PrimitiveTypeUtil.getQuickComponentType(param_);
                        mapping_.setParam(c_);
                        if (Templates.isCorrectOrNumbers(mapping_,_conf)) {
                            AnnotationTypeInfo i_ = new AnnotationTypeInfo();
                            i_.setType(paramName_);
                            i_.setWrap(true);
                            fieldNames.put(fields_.getKey(0), i_);
                            setResultClass(new ClassArgumentMatching(className));
                            return;
                        }
                    }
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    //first parenthese
                    cast_.buildError(_conf.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(arg_.getNames(),"&"),
                            StringList.join(param_.getNames(),"&"));
                    _conf.addError(cast_);
                }
                AnnotationTypeInfo i_ = new AnnotationTypeInfo();
                i_.setType(paramName_);
                fieldNames.put(fields_.getKey(0),i_);
                setResultClass(new ClassArgumentMatching(className));
                return;
            }
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            //last parenthese
            cast_.buildError(_conf.getContextEl().getAnalysisMessages().getAnnotFieldNotUniq());
            _conf.addError(cast_);
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        StringMap<Integer> counts_ = new StringMap<Integer>();
        for (String s: suppliedFields_) {
            counts_.put(s,0);
        }
        for (String s: suppliedFields_) {
            counts_.put(s, counts_.getVal(s)+1);
        }
        for (EntryCust<String,Integer> e: counts_.entryList()) {
            if (e.getValue() > 1) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                //key len at operation
                cast_.buildError(_conf.getContextEl().getAnalysisMessages().getDupSuppliedAnnotField(),
                        e.getKey()
                );
                _conf.addError(cast_);
            }
        }
        for (String f: suppliedFieldsType_.getKeys()) {
            if (!fields_.contains(f)) {
                //ERROR
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                //key len at operation
                cast_.buildError(_conf.getContextEl().getAnalysisMessages().getUndefinedAccessibleField(),
                        f,
                        className);
                _conf.addError(cast_);
            }
            AnnotationTypeInfo i_ = new AnnotationTypeInfo();
            fieldNames.put(f,i_);
        }
        for (EntryCust<String, AnnotationFieldInfo> e: fields_.entryList()) {
            if (e.getValue().isOptional()) {
                continue;
            }
            if (!suppliedFieldsType_.contains(e.getKey())) {
                //ERROR
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                //last parenthese
                cast_.buildError(_conf.getContextEl().getAnalysisMessages().getAnnotFieldMust(),
                        e.getKey());
                _conf.addError(cast_);
            }
        }
        for (EntryCust<String, ClassArgumentMatching> e: suppliedFieldsType_.entryList()) {
            String suppliedKey_ = e.getKey();
            for (EntryCust<String, AnnotationFieldInfo> f: fields_.entryList()) {
                if (!StringList.quickEq(suppliedKey_, f.getKey())) {
                    continue;
                }
                String paramName_ = f.getValue().getType();
                ClassArgumentMatching param_ = new ClassArgumentMatching(paramName_);
                ClassArgumentMatching arg_ = e.getValue();
                Mapping mapping_ = new Mapping();
                mapping_.setMapping(_conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints());
                mapping_.setArg(arg_);
                mapping_.setParam(param_);
                if (!Templates.isCorrectOrNumbers(mapping_,_conf)) {
                    if (param_.isArray()) {
                        ClassArgumentMatching c_ = PrimitiveTypeUtil.getQuickComponentType(param_);
                        mapping_.setParam(c_);
                        if (Templates.isCorrectOrNumbers(mapping_,_conf)) {
                            AnnotationTypeInfo i_ = fieldNames.getVal(suppliedKey_);
                            i_.setType(paramName_);
                            i_.setWrap(true);
                            continue;
                        }
                    }
                    //ERROR
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    //equal char
                    cast_.buildError(_conf.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(arg_.getNames(),"&"),
                            StringList.join(param_.getNames(),"&"));
                    _conf.addError(cast_);
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
}
