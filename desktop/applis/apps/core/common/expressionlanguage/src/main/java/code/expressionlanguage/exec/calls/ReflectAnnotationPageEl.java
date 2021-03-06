package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.Ints;

public final class ReflectAnnotationPageEl extends AbstractReflectPageEl {
    private boolean retrievedAnnot;
    private int indexAnnotation;
    private int indexAnnotationParam;
    private boolean onParameters;
    private ArrayStruct array;
    private CustList<CustList<ExecOperationNode>> annotations = new CustList<CustList<ExecOperationNode>>();
    private CustList<CustList<CustList<ExecOperationNode>>> annotationsParams = new CustList<CustList<CustList<ExecOperationNode>>>();
    private final Ints annotationsIndexes = new Ints();
    private final CustList<Ints> annotationsParamsIndexes = new CustList<Ints>();
    private final AnnotatedStruct annotated;

    private final CustList<Argument> arguments;

    public ReflectAnnotationPageEl(CustList<Argument> _arguments, AnnotatedStruct _annotated) {
        arguments = _arguments;
        annotated = _annotated;
        setGlobalArgumentStruct(_annotated);
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        LgNames stds_ = _context.getStandards();
        if (!retrievedAnnot) {
            if (onParameters) {
                if (annotated instanceof AnnotatedParamStruct){
                    AnnotatedParamStruct a_ = (AnnotatedParamStruct) annotated;
                    ExecAnnotableParamBlock annotableBlockParam_ = a_.getAnnotableBlockParam();
                    if (annotableBlockParam_ != null) {
                        annotationsParams = annotableBlockParam_.getAnnotationsOpsParams();
                    }
                } else {
                    annotationsParams = new CustList<CustList<CustList<ExecOperationNode>>>();
                }
            } else {
                ExecAnnotableBlock annotableBlock_ = annotated.getAnnotableBlock();
                if (annotableBlock_ != null) {
                    annotations = annotableBlock_.getAnnotationsOps();
                }
            }
            String cl_ = "";
            if (!arguments.isEmpty()) {
                Struct arg_ = arguments.first().getStruct();
                if (arg_ instanceof ClassMetaInfo) {
                    cl_ = NumParsers.getClass(arg_).getName();
                }
            }
            if (!cl_.isEmpty()) {
                if (onParameters) {
                    CustList<CustList<CustList<ExecOperationNode>>> filters_ = new CustList<CustList<CustList<ExecOperationNode>>>();
                    for (CustList<CustList<ExecOperationNode>> a: annotationsParams) {
                        CustList<CustList<ExecOperationNode>> filter_ = new CustList<CustList<ExecOperationNode>>();
                        Ints indexes_ = new Ints();
                        filter(cl_, a, filter_, indexes_);
                        annotationsParamsIndexes.add(indexes_);
                        filters_.add(filter_);
                    }
                    annotationsParams = filters_;
                } else {
                    CustList<CustList<ExecOperationNode>> filter_ = new CustList<CustList<ExecOperationNode>>();
                    filter(cl_, annotations, filter_, annotationsIndexes);
                    annotations = filter_;
                }
            } else {
                if (onParameters) {
                    for (CustList<CustList<ExecOperationNode>> a: annotationsParams) {
                        int s_ = a.size();
                        Ints indexes_ = new Ints(new CollCapacity(s_));
                        feedIndexes(s_, indexes_);
                        annotationsParamsIndexes.add(indexes_);
                    }
                } else {
                    int s_ = annotations.size();
                    feedIndexes(s_, annotationsIndexes);
                }
            }
            if (onParameters) {
                int len_ = annotationsParams.size();
                String annot_ = stds_.getContent().getReflect().getAliasAnnotationType();
                annot_ = StringExpUtil.getPrettyArrayType(annot_);
                String annotArr_ = StringExpUtil.getPrettyArrayType(annot_);
                array = new ArrayStruct(len_, annotArr_);
                int i_ = 0;
                for (CustList<CustList<ExecOperationNode>> e: annotationsParams) {
                    ArrayStruct loc_;
                    loc_ = new ArrayStruct(e.size(), annot_);
                    array.set(i_, loc_);
                    i_++;
                }
            } else {
                int len_ = annotations.size();
                String annot_ = stds_.getContent().getReflect().getAliasAnnotationType();
                annot_ = StringExpUtil.getPrettyArrayType(annot_);
                array = new ArrayStruct(len_, annot_);
            }
            retrievedAnnot = true;
        }
        if (onParameters) {
            int len_ = annotationsParams.size();
            for (int i = indexAnnotationParam; i < len_; i++) {
                Struct loc_ = array.get(i);
                int lenLoc_ = annotationsParams.get(i).size();
                for (int j = indexAnnotation; j < lenLoc_; j++) {
                    CustList<ExecOperationNode> ops_ = annotationsParams.get(i).get(j);
                    ExpressionLanguage el_ = getCurrentEl(0,ops_);
                    Argument ret_ = ExpressionLanguage.tryToCalculate(_context,el_,0, _stack);
                    if (_context.callsOrException(_stack)) {
                        return false;
                    }
                    clearCurrentEls();
                    ExecArrayFieldOperation.getArray(loc_,_context).set(j, ret_.getStruct());
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
                Argument ret_ = ExpressionLanguage.tryToCalculate(_context,el_,0, _stack);
                if (_context.callsOrException(_stack)) {
                    return false;
                }
                clearCurrentEls();
                array.set(i, ret_.getStruct());
                indexAnnotation++;
            }
        }

        Argument out_ = new Argument(array);
        setReturnedArgument(out_);
        return true;
    }

    private static void feedIndexes(int _s, Ints _indexes) {
        for (int i = 0; i < _s; i++) {
            _indexes.add(i);
        }
    }

    private static void filter(String _cl, CustList<CustList<ExecOperationNode>> _annotations, CustList<CustList<ExecOperationNode>> _filter, Ints _indexes) {
        int i_ = 0;
        for (CustList<ExecOperationNode> b: _annotations) {
            ExecClassArgumentMatching arg_ = b.last().getResultClass();
            if (arg_.matchClass(_cl)) {
                _filter.add(b);
                _indexes.add(i_);
            }
            i_++;
        }
    }

    @Override
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
    }

    public void setOnParameters(boolean _onParameters) {
        onParameters = _onParameters;
    }

    public AnnotatedStruct getAnnotated() {
        return annotated;
    }

    public int getIndexAnnotation() {
        return indexAnnotation;
    }

    public int getIndexAnnotationParam() {
        return indexAnnotationParam;
    }

    public CustList<Ints> getAnnotationsParamsIndexes() {
        return annotationsParamsIndexes;
    }

    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public boolean isOnParameters() {
        return onParameters;
    }
}
