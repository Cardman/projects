package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AnnotableBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.OperatorBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReflectAnnotationPageEl extends AbstractReflectPageEl {
    private boolean retrievedAnnot;
    private int indexAnnotation;
    private int indexAnnotationParam;
    private boolean onParameters;
    private ArrayStruct array;
    private CustList<CustList<ExecOperationNode>> annotations;
    private CustList<CustList<CustList<ExecOperationNode>>> annotationsParams;

    @Override
    public boolean checkCondition(ContextEl _context) {
        Struct structBlock_ = getGlobalArgument().getStruct();
        if (!retrievedAnnot) {
            if (onParameters) {
                if (structBlock_ instanceof ConstructorMetaInfo){
                    ConstructorId cid_ = ((ConstructorMetaInfo)structBlock_).getRealId();
                    String cl_ = cid_.getName();
                    String idClass_ = Templates.getIdFromAllTypes(cl_);
                    GeneType type_ = _context.getClassBody(idClass_);
                    for (GeneConstructor c: ContextEl.getConstructorBlocks(type_)) {
                        if (c.getId().eq(cid_)) {
                            if (c instanceof NamedFunctionBlock) {
                                annotationsParams=((NamedFunctionBlock)c).getAnnotationsOpsParams();
                            } else {
                                annotationsParams = new CustList<CustList<CustList<ExecOperationNode>>>();
                            }
                        }
                    }
                } else if (structBlock_ instanceof MethodMetaInfo){
                    MethodId mid_ =  ((MethodMetaInfo)structBlock_).getRealId();
                    String cl_ = ((MethodMetaInfo)structBlock_).getFormClassName();
                    String idClass_ = Templates.getIdFromAllTypes(cl_);
                    GeneType type_ = _context.getClassBody(idClass_);
                    for (GeneMethod m: ContextEl.getMethodBlocks(type_)) {
                        if (m.getId().eq(mid_)) {
                            if (m instanceof NamedFunctionBlock) {
                                annotationsParams=((NamedFunctionBlock)m).getAnnotationsOpsParams();
                            } else {
                                annotationsParams = new CustList<CustList<CustList<ExecOperationNode>>>();
                            }
                        }
                    }
                    for (OperatorBlock m: _context.getClasses().getOperators()) {
                        if (m.getId().eq(mid_)) {
                            annotationsParams= m.getAnnotationsOpsParams();
                        }
                    }
                } else {
                    annotationsParams = new CustList<CustList<CustList<ExecOperationNode>>>();
                }
            } else if (structBlock_ instanceof ClassMetaInfo) {
                String cl_ = ((ClassMetaInfo)structBlock_).getName();
                String id_ = Templates.getIdFromAllTypes(cl_);
                GeneType type_ = _context.getClassBody(id_);
                if (type_ instanceof AnnotableBlock) {
                    annotations=((AnnotableBlock)type_).getAnnotationsOps();
                } else {
                    annotations = new CustList<CustList<ExecOperationNode>>();
                }
            } else if (structBlock_ instanceof ConstructorMetaInfo){
                ConstructorId cid_ = ((ConstructorMetaInfo)structBlock_).getRealId();
                String cl_ = cid_.getName();
                String idClass_ = Templates.getIdFromAllTypes(cl_);
                GeneType type_ = _context.getClassBody(idClass_);
                for (GeneConstructor c: ContextEl.getConstructorBlocks(type_)) {
                    if (c.getId().eq(cid_)) {
                        if (c instanceof AnnotableBlock) {
                            annotations=((AnnotableBlock)c).getAnnotationsOps();
                        } else {
                            annotations = new CustList<CustList<ExecOperationNode>>();
                        }
                    }
                }
            } else if (structBlock_ instanceof MethodMetaInfo){
                MethodId mid_ =  ((MethodMetaInfo)structBlock_).getRealId();
                String cl_ = ((MethodMetaInfo)structBlock_).getFormClassName();
                String idClass_ = Templates.getIdFromAllTypes(cl_);
                GeneType type_ = _context.getClassBody(idClass_);
                for (GeneMethod m: ContextEl.getMethodBlocks(type_)) {
                    if (m.getId().eq(mid_)) {
                        if (m instanceof AnnotableBlock) {
                            annotations=((AnnotableBlock)m).getAnnotationsOps();
                        } else {
                            annotations = new CustList<CustList<ExecOperationNode>>();
                        }
                    }
                }
                for (OperatorBlock m: _context.getClasses().getOperators()) {
                    if (m.getId().eq(mid_)) {
                        annotations= m.getAnnotationsOps();
                    }
                }
            } else {
                //Field
                String fieldId_ = ((FieldMetaInfo)structBlock_).getName();
                String cl_ = ((FieldMetaInfo)structBlock_).getDeclaringClass();
                String idClass_ = Templates.getIdFromAllTypes(cl_);
                GeneType type_ = _context.getClassBody(idClass_);
                for (GeneField f: ContextEl.getFieldBlocks(type_)) {
                    if (!f.getFieldName().containsStr(fieldId_)) {
                        continue;
                    }
                    if (f instanceof AnnotableBlock) {
                        annotations=((AnnotableBlock)f).getAnnotationsOps();
                    } else {
                        annotations = new CustList<CustList<ExecOperationNode>>();
                    }
                }
            }
            CustList<Argument> args_ = getArguments();
            String cl_ = "";
            if (!args_.isEmpty()) {
                Struct arg_ = args_.first().getStruct();
                if (arg_ instanceof ClassMetaInfo) {
                    cl_ = ((ClassMetaInfo)arg_).getName();
                }
            }
            if (!cl_.isEmpty()) {
                ClassArgumentMatching param_ = new ClassArgumentMatching(cl_);
                if (onParameters) {
                    CustList<CustList<CustList<ExecOperationNode>>> filters_ = new CustList<CustList<CustList<ExecOperationNode>>>();
                    for (CustList<CustList<ExecOperationNode>> a: annotationsParams) {
                        CustList<CustList<ExecOperationNode>> filter_ = new CustList<CustList<ExecOperationNode>>();
                        for (CustList<ExecOperationNode> b: a) {
                            ClassArgumentMatching arg_ = b.last().getResultClass();
                            if (PrimitiveTypeUtil.canBeUseAsArgument(param_, arg_, _context)) {
                                filter_.add(b);
                            }
                        }
                        filters_.add(filter_);
                    }
                    annotationsParams = filters_;
                } else {
                    CustList<CustList<ExecOperationNode>> filter_ = new CustList<CustList<ExecOperationNode>>();
                    for (CustList<ExecOperationNode> a: annotations) {
                        ClassArgumentMatching arg_ = a.last().getResultClass();
                        if (PrimitiveTypeUtil.canBeUseAsArgument(param_, arg_, _context)) {
                            filter_.add(a);
                        }
                    }
                    annotations = filter_;
                }
            }
            if (onParameters) {
                int len_ = annotationsParams.size();
                String annot_ = _context.getStandards().getAliasAnnotation();
                annot_ = PrimitiveTypeUtil.getPrettyArrayType(annot_);
                String annotArr_ = PrimitiveTypeUtil.getPrettyArrayType(annot_);
                array = new ArrayStruct(new Struct[len_], annotArr_);
                int i_ = 0;
                for (CustList<CustList<ExecOperationNode>> e: annotationsParams) {
                    ArrayStruct loc_;
                    loc_ = new ArrayStruct(new Struct[e.size()], annot_);
                    array.getInstance()[i_] = loc_;
                    i_++;
                }
            } else {
                int len_ = annotations.size();
                String annot_ = _context.getStandards().getAliasAnnotation();
                annot_ = PrimitiveTypeUtil.getPrettyArrayType(annot_);
                array = new ArrayStruct(new Struct[len_], annot_);
            }
            retrievedAnnot = true;
        }
        if (onParameters) {
            int len_ = annotationsParams.size();
            for (int i = indexAnnotationParam; i < len_; i++) {
                Struct[] rr_ = array.getInstance();
                Struct loc_ = rr_[i];
                int lenLoc_ = annotationsParams.get(i).size();
                for (int j = indexAnnotation; j < lenLoc_; j++) {
                    CustList<ExecOperationNode> ops_ = annotationsParams.get(i).get(j);
                    ExpressionLanguage el_;
                    if (!isEmptyEl()) {
                        el_ = getLastEl();
                    } else {
                        el_ = new ExpressionLanguage(ops_);
                        addCurrentEl(el_);
                    }
                    Argument ret_ = el_.calculateMember(_context);
                    if (_context.callsOrException()) {
                        return false;
                    }
                    clearCurrentEls();
                    ((ArrayStruct)loc_).getInstance()[j] = ret_.getStruct();
                    indexAnnotation++;
                }
                indexAnnotationParam++;
                indexAnnotation = 0;
            }
        } else {
            int len_ = annotations.size();
            for (int i = indexAnnotation; i < len_; i++) {
                CustList<ExecOperationNode> ops_ = annotations.get(i);
                ExpressionLanguage el_;
                if (!isEmptyEl()) {
                    el_ = getLastEl();
                } else {
                    el_ = new ExpressionLanguage(ops_);
                    addCurrentEl(el_);
                }
                Argument ret_ = el_.calculateMember(_context);
                if (_context.callsOrException()) {
                    return false;
                }
                clearCurrentEls();
                array.getInstance()[i] = ret_.getStruct();
                indexAnnotation++;
            }
        }
        
        Argument out_ = new Argument();
        out_.setStruct(array);
        setReturnedArgument(out_);
        return true;
    }

    @Override
    public boolean receive(Argument _argument, ContextEl _context) {
        return basicReceive(_argument,_context);
    }

    public void setOnParameters(boolean _onParameters) {
        onParameters = _onParameters;
    }
}
