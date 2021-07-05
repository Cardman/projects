package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;

public final class AnaExplicitContent {
    private String className;
    private AnaFormattedRootBlock formattedTypeOwner;
    private int offset;
    private MemberId memberId = new MemberId();

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        this.className = _className;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _offset) {
        this.offset = _offset;
    }

    public AnaFormattedRootBlock getFormattedTypeOwner() {
        return formattedTypeOwner;
    }

    public void setFormattedTypeOwner(AnaFormattedRootBlock _formattedTypeOwner) {
        this.formattedTypeOwner = _formattedTypeOwner;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }
}
