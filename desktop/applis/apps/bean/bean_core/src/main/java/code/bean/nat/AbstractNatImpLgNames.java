package code.bean.nat;

import code.bean.nat.exec.blocks.NatRendImport;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;

public interface AbstractNatImpLgNames {
    void setBeanForms(Configuration _conf, Struct _mainBean,
                      NatRendImport _node, boolean _keepField, String _beanName, ContextEl _ctx, RendStackCall _rendStack);
}
