package code.expressionlanguage.methods.util;

import code.expressionlanguage.FileRowCol;
import code.sml.Element;
import code.sml.RowCol;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringMap;

public final class Metrics {
    private Element associateElement;

    private StringMap<RowCol> attributes;

    private RowCol endHeader;

    private StringMap<Numbers<Integer>> offsets;

    private StringMap<Numbers<Integer>> tabs;

    private StringMap<NatTreeMap<Integer,Integer>> encoded;

    private StringMap<FileRowCol> fileAttributes;

    public Element getAssociateElement() {
        return associateElement;
    }

    public void setAssociateElement(Element _associateElement) {
        associateElement = _associateElement;
    }

    public StringMap<RowCol> getAttributes() {
        return attributes;
    }

    public void setAttributes(StringMap<RowCol> _attributes) {
        attributes = _attributes;
    }

    public RowCol getEndHeader() {
        return endHeader;
    }

    public void setEndHeader(RowCol _endHeader) {
        endHeader = _endHeader;
    }

    public StringMap<Numbers<Integer>> getOffsets() {
        return offsets;
    }

    public void setOffsets(StringMap<Numbers<Integer>> _offsets) {
        offsets = _offsets;
    }

    public StringMap<Numbers<Integer>> getTabs() {
        return tabs;
    }

    public void setTabs(StringMap<Numbers<Integer>> _tabs) {
        tabs = _tabs;
    }

    public StringMap<NatTreeMap<Integer, Integer>> getEncoded() {
        return encoded;
    }

    public void setEncoded(StringMap<NatTreeMap<Integer, Integer>> _encoded) {
        encoded = _encoded;
    }

    public StringMap<FileRowCol> getFileAttributes() {
        return fileAttributes;
    }

    public void setFileAttributes(StringMap<FileRowCol> _fileAttributes) {
        fileAttributes = _fileAttributes;
    }
}
