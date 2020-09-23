package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.DeclareVariable;
import code.expressionlanguage.exec.blocks.ExecDeclareVariable;
import code.util.StringList;

public final class ReachDeclareVariable extends ReachLeaf implements ReachBuildableElMethod {
    private final StringList variableNames;
    private String importedClassName;

    protected ReachDeclareVariable(DeclareVariable _info) {
        super(_info);
        importedClassName = _info.getImportedClassName();
        variableNames = _info.getVariableNames();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        ExecDeclareVariable exec_ = new ExecDeclareVariable(getOffset(), importedClassName,variableNames);
        _page.setExecDeclareVariable(exec_);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }
}
