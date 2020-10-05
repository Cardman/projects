package code.formathtml.exec.blocks;


import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;

public interface RendWithEl {

    void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx);

    int getOffsetTrim();
}
