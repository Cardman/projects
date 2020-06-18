package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.opers.util.AnnotationTypeInfo;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;

public final class NewAnnotationPageEl extends AbstractCallingInstancingPageEl {

    private StringMap<AnnotationTypeInfo> names;
    private CustList<Argument> args;
    @Override
    public void tryProcessEl(ContextEl _context) {
        //set fields for annotation after calculating default one
        int len_ = names.size();
        Struct str_ = getGlobalArgument().getStruct();
        String className_ = str_.getClassName(_context);
        for (int i = 0; i <len_; i++) {
            String name_ = names.getKey(i);
            Argument value_ = args.get(i);
            AnnotationTypeInfo i_ = names.getValue(i);
            String t_ = i_.getType();
            if (i_.isWrap()) {
                ArrayStruct a_ = new ArrayStruct(new Struct[1], t_);
                Templates.setCheckedElements(new CustList<Argument>(value_),a_,_context);
                ExecAnnotationMethodBlock.setValue(className_,name_,t_,_context,new Argument(a_));
                if (_context.callsOrException()) {
                    return;
                }
                continue;
            }
            ExecAnnotationMethodBlock.setValue(className_,name_,t_,_context,value_);
            if (_context.callsOrException()) {
                return;
            }
        }
        setNullReadWrite();
    }

    public void setNames(StringMap<AnnotationTypeInfo> _names) {
        names = _names;
    }

    public void setArgs(CustList<Argument> _args) {
        args = _args;
    }
}
