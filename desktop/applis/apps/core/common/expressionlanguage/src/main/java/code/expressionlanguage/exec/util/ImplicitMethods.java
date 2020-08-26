package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;

public final class ImplicitMethods {
    private final CustList<ExecNamedFunctionBlock> converter = new CustList<ExecNamedFunctionBlock>();
    private String ownerClass="";

    public CustList<ExecNamedFunctionBlock> getConverter() {
        return converter;
    }

    public String getOwnerClass() {
        return ownerClass;
    }

    public void setOwnerClass(String ownerClass) {
        this.ownerClass = ownerClass;
    }

    public boolean isValidIndex(int indexImplicit_) {
        return converter.isValidIndex(indexImplicit_);
    }

    public ExecNamedFunctionBlock get(int indexImplicit_) {
        return converter.get(indexImplicit_);
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return converter.size();
    }
}
