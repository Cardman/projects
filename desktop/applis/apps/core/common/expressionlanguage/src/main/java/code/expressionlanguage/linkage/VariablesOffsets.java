package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.OperatorBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class VariablesOffsets {
    private IdList<OperationNode> visited = new IdList<OperationNode>();
    private CustList<LinkageStackElement> stack = new CustList<LinkageStackElement>();
    private LinkageStackElement state;
    private String currentFileName = "";
    private CustList<RootBlock> refFoundTypes;
    private CustList<OperatorBlock> refOperators;
    private KeyWords keyWords;
    private DisplayedStrings displayedStrings;
    private StringList toStringOwners;

    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String _currentFileName) {
        currentFileName = _currentFileName;
    }

    public CustList<RootBlock> getRefFoundTypes() {
        return refFoundTypes;
    }

    public static RootBlock getClassBody(String _type, CustList<RootBlock> _refFoundTypes) {
        for (RootBlock r: _refFoundTypes) {
            if (StringUtil.quickEq(r.getFullName(),_type)) {
                return r;
            }
        }
        return null;
    }

    public void setRefFoundTypes(CustList<RootBlock> _refFoundTypes) {
        refFoundTypes = _refFoundTypes;
    }

    public CustList<OperatorBlock> getRefOperators() {
        return refOperators;
    }

    public void setRefOperators(CustList<OperatorBlock> _refOperators) {
        refOperators = _refOperators;
    }

    public IdList<OperationNode> getVisited() {
        return visited;
    }

    public CustList<LinkageStackElement> getStack() {
        return stack;
    }

    public LinkageStackElement getState() {
        return state;
    }

    public void setState(LinkageStackElement _v) {
        this.state = _v;
    }

    public KeyWords getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(KeyWords _v) {
        this.keyWords = _v;
    }

    public DisplayedStrings getDisplayedStrings() {
        return displayedStrings;
    }

    public void setDisplayedStrings(DisplayedStrings _v) {
        this.displayedStrings = _v;
    }

    public StringList getToStringOwners() {
        return toStringOwners;
    }

    public void setToStringOwners(StringList _v) {
        this.toStringOwners = _v;
    }
}
