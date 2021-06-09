package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ReflectAnnotationPageEl extends AbstractReflectPageEl {
    private boolean retrievedAnnot;
    private int indexAnnotation;
    private int indexAnnotationParam;
    private boolean onParameters;
    private ArrayStruct array;
    private CustList<ExecAnnotContent> annotations = new CustList<ExecAnnotContent>();
    private CustList<CustList<ExecAnnotContent>> annotationsParams = new CustList<CustList<ExecAnnotContent>>();
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
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        setNullReadWrite();
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        LgNames stds_ = _context.getStandards();
        if (!retrievedAnnot) {
            if (onParameters) {
                if (annotated instanceof AnnotatedParamStruct){
                    annotationsParams = ((AnnotatedParamStruct)annotated).getAnnotationsOpsParams();
                } else {
                    annotationsParams = new CustList<CustList<ExecAnnotContent>>();
                }
            } else {
                annotations = annotated.getAnnotationsOps();
            }
            String cl_ = "";
            if (!arguments.isEmpty()) {
                Struct arg_ = arguments.first().getStruct();
                if (arg_ instanceof ClassMetaInfo) {
                    cl_ = NumParsers.getClass(arg_).getFormatted().getFormatted();
                }
            }
            if (!cl_.isEmpty()) {
                if (onParameters) {
                    CustList<CustList<ExecAnnotContent>> filters_ = new CustList<CustList<ExecAnnotContent>>();
                    for (CustList<ExecAnnotContent> a: annotationsParams) {
                        CustList<ExecAnnotContent> filter_ = new CustList<ExecAnnotContent>();
                        Ints indexes_ = new Ints();
                        filter(cl_, a, filter_, indexes_);
                        annotationsParamsIndexes.add(indexes_);
                        filters_.add(filter_);
                    }
                    annotationsParams = filters_;
                } else {
                    CustList<ExecAnnotContent> filter_ = new CustList<ExecAnnotContent>();
                    filter(cl_, annotations, filter_, annotationsIndexes);
                    annotations = filter_;
                }
            } else {
                if (onParameters) {
                    for (CustList<ExecAnnotContent> a: annotationsParams) {
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
                for (CustList<ExecAnnotContent> e: annotationsParams) {
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
                    ExecAnnotContent ops_ = annotationsParams.get(i).get(j);
                    ExpressionLanguage el_ = getCurrentEl(0,ops_.getOperations());
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
                ExecAnnotContent ops_ = annotations.get(i);
                ExpressionLanguage el_ = getCurrentEl(0,ops_.getOperations());
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

    private static void filter(String _cl, CustList<ExecAnnotContent> _annotations, CustList<ExecAnnotContent> _filter, Ints _indexes) {
        int i_ = 0;
        for (ExecAnnotContent b: _annotations) {
            if (StringUtil.equalsSet(b.getTypes(),new StringList(_cl))) {
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
