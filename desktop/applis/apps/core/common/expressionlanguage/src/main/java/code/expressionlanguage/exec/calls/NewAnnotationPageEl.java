package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;

public final class NewAnnotationPageEl extends AbstractCallingInstancingPageEl {

    private final StringMap<AnnotationTypeInfo> names;
    private final CustList<Struct> args;
    private int indexSupplied = -1;
    public NewAnnotationPageEl(StringMap<AnnotationTypeInfo> _names, CustList<Struct> _args, ExecFormattedRootBlock _global) {
        super(_global);
        names = _names;
        args = _args;
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_stack)) {
            return;
        }
        if (indexSupplied < 0) {
            indexSupplied = 0;
        }
        //set fields for annotation after calculating default one
        int len_ = NumberUtil.min(names.size(),args.size());
        Struct str_ = getGlobalStruct();
        String className_ = str_.getClassName(_context);
        for (int i = indexSupplied; i <len_; i++) {
            if (_stack.getStopper().isStopAtRef(_context,_stack)) {
                indexSupplied = i;
                return;
            }
            String name_ = names.getKey(i);
            Struct value_ = args.get(i);
            AnnotationTypeInfo i_ = names.getValue(i);
            String t_ = i_.getType();
            if (i_.isWrap()) {
                ArrayStruct arr_ = ArrayStruct.instance(t_, new CustList<Struct>(value_));
                ExecArrayTemplates.checkedElements(arr_, _context, _stack);
                ExecAnnotationMethodBlock.setValue(getBlockRootType(),className_,name_,t_,_context,arr_, _stack);
                if (_context.callsOrException(_stack)) {
                    return;
                }
                continue;
            }
            ExecAnnotationMethodBlock.setValue(getBlockRootType(),className_,name_,t_,_context,value_, _stack);
            if (_context.callsOrException(_stack)) {
                return;
            }
        }
        indexSupplied = len_;
        _stack.nullReadWrite();
    }

    public StringMap<AnnotationTypeInfo> getNames() {
        return names;
    }

    public CustList<Struct> getArgs() {
        return args;
    }

    public int getIndexSupplied() {
        return indexSupplied;
    }
}
