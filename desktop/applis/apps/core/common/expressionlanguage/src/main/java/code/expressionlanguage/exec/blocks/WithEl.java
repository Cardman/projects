package code.expressionlanguage.exec.blocks;
import code.expressionlanguage.ContextEl;

public interface WithEl {

    void processEl(ContextEl _cont);

    int getOffsetTrim();
}
