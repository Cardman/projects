package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;

public final class VariablesOffsets {
    private final IdList<OperationNode> visited = new IdList<OperationNode>();
    private final IdList<OperationNode> visitedAnnotations = new IdList<OperationNode>();
    private final CustList<LinkageStackElement> stack = new CustList<LinkageStackElement>();
    private LinkageStackElement state;
    private FileBlock currentFile;
    private KeyWords keyWords;
    private DisplayedStrings displayedStrings;
    private StringList toStringOwners;
    private StringList randCodeOwners;
    private boolean implicit;
    private final CustList<PartOffset> parts = new CustList<PartOffset>();

    public boolean hasEltStack() {
        return !stack.isEmpty();
    }

    public void removeLastStackElt() {
        stack.removeQuicklyLast();
    }

    public void addStackElt() {
        addStackElt(state);
        state = null;
    }

    public void addStackElt(LinkageStackElement _elt) {
        stack.add(_elt);
    }
    public LinkageStackElement getLastStackElt() {
        return stack.last();
    }

    public boolean goesToProcess() {
        return state != null;
    }

    public FileBlock getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(FileBlock _currentFile) {
        this.currentFile = _currentFile;
    }

    public IdList<OperationNode> getVisited() {
        return visited;
    }

    public IdList<OperationNode> getVisitedAnnotations() {
        return visitedAnnotations;
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

    public StringList getRandCodeOwners() {
        return randCodeOwners;
    }

    public void setRandCodeOwners(StringList _randCodeOwners) {
        this.randCodeOwners = _randCodeOwners;
    }

    public boolean isImplicit() {
        return implicit;
    }

    public void setImplicit(boolean _implicit) {
        this.implicit = _implicit;
    }

    public void addPart(PartOffset _part) {
        parts.add(_part);
    }

    public void addParts(CustList<PartOffset> _parts) {
        parts.addAllElts(_parts);
    }
    public CustList<PartOffset> getParts() {
        return parts.getReverse();
    }

}
