package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.WarningShow;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;

public final class VariablesOffsets {
    private final IdList<OperationNode> visited = new IdList<OperationNode>();
    private final IdList<OperationNode> visitedAnnotations = new IdList<OperationNode>();
    private final CustList<LinkageStackElement> stack = new CustList<LinkageStackElement>();
    private LinkageStackElement state;
    private String currentFileName = "";
    private KeyWords keyWords;
    private DisplayedStrings displayedStrings;
    private StringList toStringOwners;
    private boolean implicit;
    private WarningShow warningShow;

    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String _currentFileName) {
        currentFileName = _currentFileName;
    }

    public IdList<OperationNode> getVisited() {
        return visited;
    }

    public IdList<OperationNode> getVisitedAnnotations() {
        return visitedAnnotations;
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

    public boolean isImplicit() {
        return implicit;
    }

    public void setImplicit(boolean _implicit) {
        this.implicit = _implicit;
    }

    public WarningShow getWarningShow() {
        return warningShow;
    }

    public void setWarningShow(WarningShow _warningShow) {
        this.warningShow = _warningShow;
    }
}
