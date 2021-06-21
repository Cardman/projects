package code.formathtml.exec.opers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;


public abstract class RendQuickOperation extends RendMethodOperation {

    private final Struct absorbingValue;
    protected RendQuickOperation(ExecOperationContent _content, boolean _absorbingValue) {
        super(_content);
        absorbingValue = BooleanStruct.of(_absorbingValue);
    }

    public Struct getAbsorbingValue() {
        return absorbingValue;
    }

    public boolean match(Struct _struct){
        return absorbingValue.sameReference(_struct);
    }
}
