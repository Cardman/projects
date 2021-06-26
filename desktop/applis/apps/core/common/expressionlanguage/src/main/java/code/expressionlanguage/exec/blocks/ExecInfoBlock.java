package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.fwd.blocks.ExecAnnotContent;
import code.util.CustList;

public interface ExecInfoBlock {
    ExecMemberContainer getElementContent();

    String getImportedClassName();
    String getRealImportedClassName();
    CustList<ExecAnnotContent> getAnnotationsOps();

}
