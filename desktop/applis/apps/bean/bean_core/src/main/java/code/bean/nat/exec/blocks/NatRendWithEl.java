package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;

public interface NatRendWithEl {

    void processEl(Configuration _cont, BeanLgNames _stds, RendStackCall _rendStack);

}
