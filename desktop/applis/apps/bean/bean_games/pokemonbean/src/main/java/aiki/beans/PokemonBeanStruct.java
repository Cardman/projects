package aiki.beans;

import code.bean.nat.BeanStruct;
import code.bean.nat.exec.opers.NatExecMethodOperation;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.util.StringList;

public final class PokemonBeanStruct extends BeanStruct {

    public PokemonBeanStruct(CommonBean _bean) {
        super(_bean);
    }

    public static StringList arg(StringList _alt) {
        StringList arg_ = new StringList();
        int len_ = _alt.size();
        for (int i = 1; i < len_; i += 2) {
            arg_.add(_alt.get(i));
        }
        return arg_;
    }

    public static NatExecOperationNode castDottedTo(NatExecOperationNode _root) {
//        NatExecOperationNode elt_;
//        if (!(_root instanceof NatAbstractDotOperation)) {
//            elt_ = _root;
//        } else {
//            elt_ = ((NatExecMethodOperation) _root).getChildrenNodes().last();
//        }
        return ((NatExecMethodOperation) _root).getChildrenNodes().last();
    }

    public CommonBean getInstance() {
        return (CommonBean) getBean();
    }


}
