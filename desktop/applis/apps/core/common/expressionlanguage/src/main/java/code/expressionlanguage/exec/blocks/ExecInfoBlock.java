package code.expressionlanguage.exec.blocks;

public interface ExecInfoBlock extends ExecAnnotableBlock {
    ExecMemberContainer getElementContent();

    String getImportedClassName();
    String getRealImportedClassName();

}
