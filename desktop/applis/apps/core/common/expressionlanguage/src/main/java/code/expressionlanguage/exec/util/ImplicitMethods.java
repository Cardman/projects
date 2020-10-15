package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.util.CustList;

public final class ImplicitMethods {
    private final CustList<ExecNamedFunctionBlock> converter = new CustList<ExecNamedFunctionBlock>();
    private String ownerClass="";
    private ExecRootBlock rootBlock;

    public CustList<ExecNamedFunctionBlock> getConverter() {
        return converter;
    }

    public String getOwnerClass() {
        return ownerClass;
    }

    public void setOwnerClass(String _ownerClass) {
        this.ownerClass = _ownerClass;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public void setRootBlock(ExecRootBlock _rootBlock) {
        this.rootBlock = _rootBlock;
    }

    public boolean isValidIndex(int _indexImplicit) {
        return converter.isValidIndex(_indexImplicit);
    }

    public ExecNamedFunctionBlock get(int _indexImplicit) {
        return converter.get(_indexImplicit);
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return converter.size();
    }
}
