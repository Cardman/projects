package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;

public final class ReflectAnnotationPageEl extends AbstractReflectPageEl {
    private boolean retrievedAnnot;
    private int indexAnnotation;
    private int indexAnnotationParam;
    private boolean onParameters;
    private ArrayStruct array;
    private CustList<CustList<ExecOperationNode>> annotations = new CustList<CustList<ExecOperationNode>>();
    private CustList<CustList<CustList<ExecOperationNode>>> annotationsParams = new CustList<CustList<CustList<ExecOperationNode>>>();

    @Override
    public boolean checkCondition(ContextEl _context) {
        Struct structBlock_ = getGlobalArgument().getStruct();
        if (!retrievedAnnot) {
            if (onParameters) {
                if (structBlock_ instanceof ConstructorMetaInfo){
                    ConstructorId cid_ = ((ConstructorMetaInfo)structBlock_).getRealId();
                    String cl_ = cid_.getName();
                    String idClass_ = Templates.getIdFromAllTypes(cl_);
                    RootBlock type_ = _context.getClasses().getClassBody(idClass_);
                    if (type_ != null) {
                        for (ConstructorBlock c: Classes.getConstructorBodiesById(_context,idClass_,cid_)) {
                            annotationsParams= c.getAnnotationsOpsParams();
                        }
                    }
                } else if (structBlock_ instanceof MethodMetaInfo){
                    MethodId mid_ =  ((MethodMetaInfo)structBlock_).getRealId();
                    String cl_ = ((MethodMetaInfo)structBlock_).getFormClassName();
                    String idClass_ = Templates.getIdFromAllTypes(cl_);
                    RootBlock type_ = _context.getClasses().getClassBody(idClass_);
                    if (type_ != null) {
                        for (NamedFunctionBlock m: Classes.getMethodBodiesById(_context,idClass_,mid_)) {
                            annotationsParams= m.getAnnotationsOpsParams();
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
                RootBlock type_ = _context.getClasses().getClassBody(id_);
                if (type_ != null) {
                    annotations= type_.getAnnotationsOps();
                }
            } else if (structBlock_ instanceof ConstructorMetaInfo){
                ConstructorId cid_ = ((ConstructorMetaInfo)structBlock_).getRealId();
                String cl_ = cid_.getName();
                String idClass_ = Templates.getIdFromAllTypes(cl_);
                RootBlock type_ = _context.getClasses().getClassBody(idClass_);
                if (type_ != null) {
                    for (ConstructorBlock c: Classes.getConstructorBodiesById(_context,idClass_,cid_)) {
                        annotations= c.getAnnotationsOps();
                    }
                }
            } else if (structBlock_ instanceof MethodMetaInfo){
                MethodId mid_ =  ((MethodMetaInfo)structBlock_).getRealId();
                String cl_ = ((MethodMetaInfo)structBlock_).getFormClassName();
                String idClass_ = Templates.getIdFromAllTypes(cl_);
                RootBlock type_ = _context.getClasses().getClassBody(idClass_);
                if (type_ != null) {
                    for (NamedFunctionBlock m: Classes.getMethodBodiesById(_context,idClass_,mid_)) {
                        annotations= m.getAnnotationsOps();
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
                RootBlock type_ = _context.getClasses().getClassBody(idClass_);
                if (type_ != null) {
                    for (InfoBlock f: ContextEl.getFieldBlocks(type_)) {
                        if (!StringList.contains(f.getFieldName(), fieldId_)) {
                            continue;
                        }
                        annotations= f.getAnnotationsOps();
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
                if (onParameters) {
                    CustList<CustList<CustList<ExecOperationNode>>> filters_ = new CustList<CustList<CustList<ExecOperationNode>>>();
                    for (CustList<CustList<ExecOperationNode>> a: annotationsParams) {
                        CustList<CustList<ExecOperationNode>> filter_ = new CustList<CustList<ExecOperationNode>>();
                        for (CustList<ExecOperationNode> b: a) {
                            ClassArgumentMatching arg_ = b.last().getResultClass();
                            if (arg_.matchClass(cl_)) {
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
                        if (arg_.matchClass(cl_)) {
                            filter_.add(a);
                        }
                    }
                    annotations = filter_;
                }
            }
            if (onParameters) {
                int len_ = annotationsParams.size();
                String annot_ = _context.getStandards().getAliasAnnotationType();
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
                String annot_ = _context.getStandards().getAliasAnnotationType();
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
                    ExpressionLanguage el_ = getCurrentEl(0,ops_);
                    Argument ret_ = ElUtil.tryToCalculate(_context,el_,0);
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
                ExpressionLanguage el_ = getCurrentEl(0,ops_);
                Argument ret_ = ElUtil.tryToCalculate(_context,el_,0);
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
    public void receive(Argument _argument, ContextEl _context) {
        basicReceive(_argument,_context);
    }

    public void setOnParameters(boolean _onParameters) {
        onParameters = _onParameters;
    }
}
