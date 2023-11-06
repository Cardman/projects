package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.SelectTreeNodeState;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.TreeNodeStruct;
import code.expressionlanguage.guicompos.TreeStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Ints;
import code.util.core.StringUtil;

public final class FctTreeGetSelected1 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        TreeStruct inst_ = (TreeStruct) _instance;
        Struct arg_ = _firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        inst_.select(arg_);
        AbsLogDbg logger_ = _stackCall.getStopper().getLogger();
        if (logger_ != null) {
            CustList<String> info_ = new CustList<String>();
            if (arg_ instanceof TreeNodeStruct) {
                Ints ints_ = ((TreeNodeStruct) arg_).getTreeNode().getIndexes();
                int l = ints_.size();
                for (int i = 0; i < l; i++) {
                    info_.add(Long.toString(ints_.get(i)));
                }
                logger_.log("select node:"+ StringUtil.join(info_,","));
            } else {
                logger_.log("select node");
            }
            _stackCall.setCallingState(new SelectTreeNodeState(inst_,arg_));
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
