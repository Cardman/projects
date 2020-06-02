package code.expressionlanguage.methods;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.MethodAccessKind;

public interface FunctionBlock {

    MethodAccessKind getStaticContext();

    OffsetsBlock getOffset();

}
