package code.formathtml.exec;

import code.expressionlanguage.analyze.opers.VariableOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;

public final class RendVariableOperation extends RendStdVariableOperation {

    public RendVariableOperation(VariableOperation _v) {
        super(_v);
    }
    public RendVariableOperation(int _indexChild, String _varName, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_varName,_res,_order);
    }
}
