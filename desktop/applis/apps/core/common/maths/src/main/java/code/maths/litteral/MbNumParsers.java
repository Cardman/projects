package code.maths.litteral;

import code.util.CustList;

public final class MbNumParsers {
    private MbNumParsers(){}

    public static MbArgument tryGet(MethodMbOperation _parent, int _index) {
        CustList<MbOperationNode> childrenNodes_ = _parent.getChildrenNodes();
        return tryGet(childrenNodes_, _index);
    }

    public static MbArgument tryGet(CustList<MbOperationNode> _childrenNodes, int _index) {
        if (!_childrenNodes.isValidIndex(_index)) {
            return new MbArgument();
        }
        return _childrenNodes.get(_index).getArgument();
    }
}
