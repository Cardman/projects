package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationMethod extends AbsSrcFileLocation {
    private final BracedBlock owner;
    private final MemberCallingsBlock method;

    public SrcFileLocationMethod(BracedBlock _o,MemberCallingsBlock _m) {
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
        MemberCallingsBlock m_ = getMethod();
        if (m_ instanceof NamedFunctionBlock) {
            return ((NamedFunctionBlock)m_).getNameOffset();
        }
        return m_.getOffset();
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        MemberCallingsBlock m_ = getMethod();
        AccessedBlock acc_ = MemberCallingsBlock.accessed(m_);
        if (acc_ instanceof RootBlock) {
            return new RowSrcLocation(EnSrcLocation.METHOD,((RootBlock)acc_).getFullName()+"."+ m_.getSignature(_dis), getFileName(),getIndex());
        }
        if (acc_ instanceof OperatorBlock) {
            return new RowSrcLocation(EnSrcLocation.METHOD,((OperatorBlock)acc_).getSignature(_dis)+"."+ m_.getSignature(_dis), getFileName(),getIndex());
        }
        BracedBlock o_ = getOwner();
        if (o_ instanceof RootBlock) {
            return new RowSrcLocation(EnSrcLocation.METHOD,((RootBlock) o_).getFullName()+"."+ m_.getSignature(_dis), getFileName(),getIndex());
        }
        return new RowSrcLocation(EnSrcLocation.METHOD, m_.getSignature(_dis), getFileName(),getIndex());
    }

    public BracedBlock getOwner() {
        return owner;
    }

    public MemberCallingsBlock getMethod() {
        return method;
    }
}
