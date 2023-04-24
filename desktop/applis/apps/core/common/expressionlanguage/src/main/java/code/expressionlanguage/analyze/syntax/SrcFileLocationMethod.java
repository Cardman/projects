package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationMethod implements SrcFileLocation {
    private final BracedBlock owner;
    private final NamedFunctionBlock method;

    public SrcFileLocationMethod(BracedBlock _o,NamedFunctionBlock _m) {
        owner = _o;
        this.method = _m;
    }

    @Override
    public FileBlock getFile() {
        return getMethod().getFile();
    }

    @Override
    public String getFileName() {
        return FileBlock.name(getFile());
    }

    @Override
    public int getIndex() {
        return getMethod().getNameOffset();
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        NamedFunctionBlock m_ = getMethod();
        if (m_ instanceof NamedCalledFunctionBlock) {
            AccessedBlock acc_ = ((NamedCalledFunctionBlock) m_).getAccessedBlock();
            if (acc_ instanceof RootBlock) {
                return new RowSrcLocation(EnSrcLocation.METHOD,((RootBlock)acc_).getFullName()+"."+ m_.getSignature(_dis), getFileName(),getIndex());
            }
            if (acc_ instanceof OperatorBlock) {
                return new RowSrcLocation(EnSrcLocation.METHOD,((OperatorBlock)acc_).getSignature(_dis)+"."+ m_.getSignature(_dis), getFileName(),getIndex());
            }
        }
        if (owner instanceof RootBlock) {
            return new RowSrcLocation(EnSrcLocation.METHOD,((RootBlock)owner).getFullName()+"."+ m_.getSignature(_dis), getFileName(),getIndex());
        }
        return new RowSrcLocation(EnSrcLocation.METHOD, m_.getSignature(_dis), getFileName(),getIndex());
    }

    public NamedFunctionBlock getMethod() {
        return method;
    }
}
