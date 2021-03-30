package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.WithEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;

public final class NewAnnotationPageEl extends AbstractCallingInstancingPageEl {

    private final StringMap<AnnotationTypeInfo> names;
    private final CustList<Argument> args;
    public NewAnnotationPageEl(StringMap<AnnotationTypeInfo> _names,CustList<Argument> _args) {
        names = _names;
        args = _args;
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        //set fields for annotation after calculating default one
        int len_ = Math.min(names.size(),args.size());
        Struct str_ = getGlobalStruct();
        String className_ = str_.getClassName(_context);
        for (int i = 0; i <len_; i++) {
            String name_ = names.getKey(i);
            Argument value_ = args.get(i);
            AnnotationTypeInfo i_ = names.getValue(i);
            String t_ = i_.getType();
            if (i_.isWrap()) {
                ArrayStruct a_ = new ArrayStruct(1, t_);
                ExecTemplates.setCheckedElements(new CustList<Argument>(value_),a_,_context, _stack);
                ExecAnnotationMethodBlock.setValue(getBlockRootType(),className_,name_,t_,_context,new Argument(a_), _stack);
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
        setNullReadWrite();
    }

}
