package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;

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
        LgNames stds_ = _context.getStandards();
        Struct structBlock_ = getGlobalStruct();
        if (!retrievedAnnot) {
            if (onParameters) {
                if (structBlock_ instanceof AnnotatedParamStruct){
                    AnnotatedParamStruct a_ = (AnnotatedParamStruct) structBlock_;
                    ExecAnnotableParametersBlock annotableBlockParam_ = a_.getAnnotableBlockParam();
                    if (annotableBlockParam_ != null) {
                        annotationsParams = annotableBlockParam_.getAnnotationsOpsParams();
                    }
                } else {
                    annotationsParams = new CustList<CustList<CustList<ExecOperationNode>>>();
                }
            } else {
                AnnotatedStruct annotated_ = NumParsers.getAnnotated(structBlock_);
                ExecAnnotableBlock annotableBlock_ = annotated_.getAnnotableBlock();
                if (annotableBlock_ != null) {
                    annotations = annotableBlock_.getAnnotationsOps();
                }
            }
            CustList<Argument> args_ = getArguments();
            String cl_ = "";
            if (!args_.isEmpty()) {
                Struct arg_ = args_.first().getStruct();
                if (arg_ instanceof ClassMetaInfo) {
                    cl_ = NumParsers.getClass(arg_).getName();
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
                String annot_ = stds_.getAliasAnnotationType();
                annot_ = StringExpUtil.getPrettyArrayType(annot_);
                String annotArr_ = StringExpUtil.getPrettyArrayType(annot_);
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
                String annot_ = stds_.getAliasAnnotationType();
                annot_ = StringExpUtil.getPrettyArrayType(annot_);
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
                    Argument ret_ = ExpressionLanguage.tryToCalculate(_context,el_,0);
                    if (_context.callsOrException()) {
                        return false;
                    }
                    clearCurrentEls();
                    ExecArrayFieldOperation.getArray(loc_,_context).getInstance()[j] = ret_.getStruct();
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
                Argument ret_ = ExpressionLanguage.tryToCalculate(_context,el_,0);
                if (_context.callsOrException()) {
                    return false;
                }
                clearCurrentEls();
                array.getInstance()[i] = ret_.getStruct();
                indexAnnotation++;
            }
        }

        Argument out_ = new Argument(array);
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
