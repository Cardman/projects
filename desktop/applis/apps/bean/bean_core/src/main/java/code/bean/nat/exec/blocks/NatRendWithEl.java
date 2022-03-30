package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;

public interface NatRendWithEl {

    void processEl(Configuration _cont, BeanLgNames _stds, NatRendStackCall _rendStack);

}
