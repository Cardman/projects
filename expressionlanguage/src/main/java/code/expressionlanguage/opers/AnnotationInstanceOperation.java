package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.AnnotationBlock;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.*;

public final class AnnotationInstanceOperation extends InvokingOperation implements PreAnalyzableOperation {

    private boolean possibleInitClass;

    private String methodName;

    private String className;
    private StringMap<String> fieldNames = new StringMap<String>();
    private boolean array;

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
            Block curr_ = _conf.getCurrentBlock();
            boolean found_ = false;
            if (curr_ instanceof AnnotationMethodBlock && mOp_ == null) {
                className = ((AnnotationMethodBlock)curr_).getImportedReturnType();
                found_ = true;
            }
            if (mOp_ instanceof AssocationOperation) {
                AssocationOperation ass_ = (AssocationOperation) mOp_;
                String fieldName_ = ass_.getFieldName();
                MethodOperation mOpAss_ = ass_.getParent();
                if (mOpAss_ instanceof AnnotationInstanceOperation) {
                    AnnotationInstanceOperation inst_;
                    inst_ = (AnnotationInstanceOperation)mOpAss_;
                    String className_ = inst_.getClassName();
                    GeneType typeInfo_ = _conf.getClassBody(className_);
                    if (!(typeInfo_ instanceof Block)) {
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        un_.setFileName(_conf.getCurrentFileName());
                        _conf.getClasses().addError(un_);
                        className = _conf.getStandards().getAliasObject();
                        return;
                    }
                    Block ann_ = (Block) typeInfo_;
                    String type_ = EMPTY_STRING;
                    for (Block b: Classes.getDirectChildren(ann_)) {
                        if (!(b instanceof AnnotationMethodBlock)) {
                            continue;
                        }
                        AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
                        if (StringList.quickEq(a_.getName(), fieldName_)) {
                            type_ = a_.getImportedReturnType();
                            break;
                        }
                    }
                    if (!type_.isEmpty()) {
                        className = type_;
                    } else {
                        className = _conf.getStandards().getAliasObject();
                    }
                } else {
                    className = _conf.getStandards().getAliasObject();
                }
            } else if (mOp_ instanceof AnnotationInstanceOperation) {
                if (((AnnotationInstanceOperation)mOp_).isArray()) {
                    UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    un_.setFileName(_conf.getCurrentFileName());
                    _conf.getClasses().addError(un_);
                    className = _conf.getStandards().getAliasObject();
                } else {
                    AnnotationInstanceOperation inst_;
                    inst_ = (AnnotationInstanceOperation)mOp_;
                    String className_ = inst_.getClassName();
                    GeneType type_ = _conf.getClassBody(className_);
                    if (!(type_ instanceof Block)) {
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        un_.setFileName(_conf.getCurrentFileName());
                        _conf.getClasses().addError(un_);
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
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        un_.setFileName(_conf.getCurrentFileName());
                        _conf.getClasses().addError(un_);
                        className = _conf.getStandards().getAliasObject();
                    } else {
                        AnnotationMethodBlock a_ =blsAnn_.first();
                        className = a_.getImportedReturnType();
                    }
                }
            } else if (!found_) {
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                un_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(un_);
                className = _conf.getStandards().getAliasObject();
            }
        } else {
            String className_ = methodName.trim().substring(AROBASE.length());
            className_ = className_.trim();
            String realClassName_ = className_;
            realClassName_ = _conf.resolveCorrectType(realClassName_);
            GeneType g_ = _conf.getClassBody(realClassName_);
            if (!(g_ instanceof AnnotationBlock)) {
                IllegalCallCtorByType call_ = new IllegalCallCtorByType();
                call_.setType(realClassName_);
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(call_);
                className = _conf.getStandards().getAliasObject();
                setResultClass(new ClassArgumentMatching(realClassName_));
                return;
            }
            className = realClassName_;
            possibleInitClass = !_conf.getOptions().isInitializeStaticClassFirst();
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
        if (array) {
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            String eltType_ = PrimitiveTypeUtil.getQuickComponentType(className);
            if (eltType_ == null) {
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
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(cast_);
                }
                if (PrimitiveTypeUtil.isPrimitive(eltType_, _conf)) {
                    o.getResultClass().setUnwrapObject(eltType_);
                    o.cancelArgument();
                }
            }
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        setStaticAccess(_conf.isStaticContext());
        analyzeCtor(_conf);
    }

    void analyzeCtor(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        String objCl_ = _conf.getStandards().getAliasObject();
        if (StringList.quickEq(className, objCl_)) {
            setResultClass(new ClassArgumentMatching(className));
            return;
        }

        GeneType g_ = _conf.getClassBody(className);
        StringMap<Boolean> fieldsOpt_ = new StringMap<Boolean>();
        StringMap<String> fieldsTypes_ = new StringMap<String>();
        for (Block b: Classes.getDirectChildren((Block)g_)) {
            if (!(b instanceof AnnotationMethodBlock)) {
                continue;
            }
            AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
            fieldsOpt_.put(a_.getName(), !a_.getDefaultValue().isEmpty());
            fieldsTypes_.put(a_.getName(), a_.getImportedReturnType());
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
        if (filter_.size() == 1 && suppliedFields_.isEmpty()) {
            if (fieldsTypes_.size() == 1) {
                //guess the unique field
                ClassArgumentMatching arg_ = filter_.first().getResultClass();
                String paramName_ = fieldsTypes_.getValue(0);
                ClassArgumentMatching param_ = new ClassArgumentMatching(paramName_);
                if (!PrimitiveTypeUtil.canBeUseAsArgument(param_, arg_, _conf)) {
                    //ERROR
                    if (param_.isArray()) {
                        ClassArgumentMatching c_ = PrimitiveTypeUtil.getQuickComponentType(param_);
                        if (PrimitiveTypeUtil.canBeUseAsArgument(c_, arg_, _conf)) {
                            fieldNames.put(fieldsTypes_.getKey(0), paramName_);
                            setResultClass(new ClassArgumentMatching(className));
                            return;
                        }
                    }
                    StringMap<StringList> vars_ = new StringMap<StringList>();
                    Mapping mapping_ = new Mapping();
                    mapping_.setMapping(vars_);
                    mapping_.setArg(arg_);
                    mapping_.setParam(param_);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(cast_);
                }
                fieldNames.put(fieldsTypes_.getKey(0),EMPTY_STRING);
                setResultClass(new ClassArgumentMatching(className));
                return;
            }
            BadConstructorCall cast_ = new BadConstructorCall();
            cast_.setLocalOffset(_conf.getCurrentLocationIndex());
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(cast_);
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        int nb_ = suppliedFields_.size();
        suppliedFields_.removeDuplicates();
        if (nb_ != suppliedFields_.size()) {
            //ERROR
            BadConstructorCall cast_ = new BadConstructorCall();
            cast_.setLocalOffset(_conf.getCurrentLocationIndex());
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(cast_);
        }
        for (String f: suppliedFields_) {
            if (!fieldsOpt_.contains(f)) {
                //ERROR
                UndefinedFieldError cast_ = new UndefinedFieldError();
                cast_.setId(f);
                cast_.setClassName(className);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(cast_);
            }
            fieldNames.put(f,EMPTY_STRING);
        }
        for (EntryCust<String, Boolean> e: fieldsOpt_.entryList()) {
            if (e.getValue()) {
                continue;
            }
            if (!StringList.contains(suppliedFields_, e.getKey())) {
                //ERROR
                BadConstructorCall cast_ = new BadConstructorCall();
                cast_.setLocalOffset(_conf.getCurrentLocationIndex());
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(cast_);
            }
        }
        for (EntryCust<String, ClassArgumentMatching> e: suppliedFieldsType_.entryList()) {
            for (EntryCust<String, String> f: fieldsTypes_.entryList()) {
                if (!StringList.quickEq(e.getKey(), f.getKey())) {
                    continue;
                }
                String paramName_ = f.getValue();
                ClassArgumentMatching param_ = new ClassArgumentMatching(paramName_);
                ClassArgumentMatching arg_ = e.getValue();
                if (!PrimitiveTypeUtil.canBeUseAsArgument(param_, arg_, _conf)) {
                    if (param_.isArray()) {
                        ClassArgumentMatching c_ = PrimitiveTypeUtil.getQuickComponentType(param_);
                        if (PrimitiveTypeUtil.canBeUseAsArgument(c_, arg_, _conf)) {
                            fieldNames.put(e.getKey(), paramName_);
                            continue;
                        }
                    }
                    //ERROR
                    StringMap<StringList> vars_ = new StringMap<StringList>();
                    Mapping mapping_ = new Mapping();
                    mapping_.setMapping(vars_);
                    mapping_.setArg(arg_);
                    mapping_.setParam(param_);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(cast_);
                }
            }
        }
        setResultClass(new ClassArgumentMatching(className));
    }

    public boolean isPossibleInitClass() {
        return possibleInitClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public StringMap<String> getFieldNames() {
        return fieldNames;
    }

}
