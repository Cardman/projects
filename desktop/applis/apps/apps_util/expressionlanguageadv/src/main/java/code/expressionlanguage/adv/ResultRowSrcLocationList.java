package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.expressionlanguage.analyze.syntax.RowSrcLocation;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.gui.AbstractMutableTreeNodeCore;
import code.util.CustList;

public final class ResultRowSrcLocationList {
    private final AnalyzedPageEl page;
    private final String relPath;
    private final int caret;
    private final CustList<SrcFileLocation> usages;
    private final CustList<RowSrcLocation> symbols;
    private MetaCaller root;
    private AbstractMutableTreeNodeCore<String> node;
    public ResultRowSrcLocationList(AnalyzedPageEl _p, String _relPath, int _caret,  CustList<SrcFileLocation> _us, CustList<RowSrcLocation> _symb) {
        page = _p;
        relPath = _relPath;
        caret = _caret;
        usages = _us;
        symbols = _symb;
        root = new MetaCaller(null,null,new CustList<FileBlockIndex>());
    }

    public void buildTree(WindowWithTreeImpl _window) {
        root = new MetaCaller(null,null,new CustList<FileBlockIndex>());
        node = LookForCallersTask.node(_window,this, root);
    }

    public String getRelPath() {
        return relPath;
    }

    public int getCaret() {
        return caret;
    }

    public MetaCaller getRoot() {
        return root;
    }

    public AbstractMutableTreeNodeCore<String> getNode() {
        return node;
    }

    public AnalyzedPageEl getPage() {
        return page;
    }

    public CustList<SrcFileLocation> getUsages() {
        return usages;
    }

    public CustList<RowSrcLocation> getSymbols() {
        return symbols;
    }
}
