package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAnnotableParamBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ReflectAnnotationSuppPageEl extends AbstractReflectPageEl {
    private final MethodId realId;
    private boolean retrievedAnnotSet;
    private int indexAnnotationSet;
    private ArrayStruct arraySet;
    private CustList<ExecAnnotContent> annotationsSet = new CustList<ExecAnnotContent>();
    private final Ints annotationsIndexesSet = new Ints();
    private final MethodMetaInfo annotatedSet;

    private final CustList<Argument> argumentsSet;

    public ReflectAnnotationSuppPageEl(CustList<Argument> _arguments, MethodMetaInfo _annotated) {
        super(false);
        argumentsSet = _arguments;
        annotatedSet = _annotated;
        realId = _annotated.getRealId();
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
        retrAllAnnot(_context);
        int len_ = annotationsSet.size();
        for (int i = indexAnnotationSet; i < len_; i++) {
            ExecAnnotContent ops_ = annotationsSet.get(i);
            globalOffset(ops_.getOffset());
            Argument ret_ = ExecHelperBlocks.tryToCalculate(_context,0,_stack,ops_.getOperations(),0, null);
            if (_context.callsOrException(_stack)) {
                return false;
            }
            clearCurrentEls();
            arraySet.set(i, ret_.getStruct());
            indexAnnotationSet++;
        }
        Argument out_ = new Argument(arraySet);
        setReturnedArgument(out_);
        return true;
    }

    private void retrAllAnnot(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        if (retrievedAnnotSet) {
            return;
        }
        annot();
        String cl_ = filterType();
        if (!cl_.isEmpty()) {
            CustList<ExecAnnotContent> filter_ = new CustList<ExecAnnotContent>();
            filter(cl_, annotationsSet, filter_, annotationsIndexesSet);
            annotationsSet = filter_;
        } else {
            int s_ = annotationsSet.size();
            feedIndexes(s_, annotationsIndexesSet);
        }
        int len_ = annotationsSet.size();
        String annot_ = stds_.getContent().getReflect().getAliasAnnotationType();
        annot_ = StringExpUtil.getPrettyArrayType(annot_);
        arraySet = new ArrayStruct(len_, annot_);
        retrievedAnnotSet = true;
    }

    private void annot() {
        ExecBlock bl_ = annotatedSet.getBl();
        if (bl_ instanceof ExecAnnotableParamBlock) {
            annotationsSet = ((ExecAnnotableParamBlock)bl_).getAnnotationsOpsSupp();
        } else {
            annotationsSet = new CustList<ExecAnnotContent>();
        }
    }

    private String filterType() {
        String cl_ = "";
        if (!argumentsSet.isEmpty()) {
            Struct arg_ = argumentsSet.first().getStruct();
            if (arg_ instanceof ClassMetaInfo) {
                cl_ = NumParsers.getClass(arg_).getFormatted().getFormatted();
            }
        }
        return cl_;
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

    public MethodId getRealId() {
        return realId;
    }

    public MethodMetaInfo getAnnotatedSet() {
        return annotatedSet;
    }

    public int getIndexAnnotationSet() {
        return indexAnnotationSet;
    }

    public Ints getAnnotationsIndexesSet() {
        return annotationsIndexesSet;
    }

}
