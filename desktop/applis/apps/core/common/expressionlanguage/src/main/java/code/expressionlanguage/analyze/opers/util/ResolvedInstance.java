package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.util.CustList;

public final class ResolvedInstance {
    private final boolean inferred;
    private final AnaResultPartType result;
    private final CustList<AnaResultPartType> parts;
    private final int lt;
    private final int gt;
    private final String infer;

    public ResolvedInstance() {
        this(false,new AnaResultPartType(), new CustList<AnaResultPartType>(),0,0,"");
    }

    public ResolvedInstance(AnaResultPartType _result) {
        this(_result, new CustList<AnaResultPartType>());
    }

    public ResolvedInstance(AnaResultPartType _result, CustList<AnaResultPartType> _parts) {
        this(false,_result, _parts,0,0,"");
    }

    public ResolvedInstance(ResolvedInstance _prep, int _lt, int _gt, String _infer) {
        this(true,_prep.result, _prep.parts,_lt,_gt,_infer);
    }

    private ResolvedInstance(boolean _inferred, AnaResultPartType _result, CustList<AnaResultPartType> _parts, int _lt, int _gt, String _infer) {
        this.inferred = _inferred;
        this.result = _result;
        this.parts = _parts;
        this.lt = _lt;
        this.gt = _gt;
        this.infer = _infer;
    }

    public boolean isInferred() {
        return inferred;
    }

    public AnaResultPartType getResult() {
        return result;
    }

    public CustList<AnaResultPartType> getParts() {
        return parts;
    }

    public int getGt() {
        return gt;
    }

    public int getLt() {
        return lt;
    }

    public String getInfer() {
        return infer;
    }
}
