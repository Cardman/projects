package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.StaticCallAccessOperation;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDirectDto;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.util.CustList;

public final class ResolvedInstance {
    private final boolean inferred;
    private final AnaResultPartTypeDtoInt result;
    private final CustList<AnaResultPartType> parts;
    private final int lt;
    private final int gt;
    private final String infer;

    public ResolvedInstance() {
        this(false,new AnaResultPartTypeDirectDto(), new CustList<AnaResultPartType>(),0,0,"");
    }

    public ResolvedInstance(AnaResultPartType _result) {
        this(new AnaResultPartTypeDirectDto(_result));
    }

    public ResolvedInstance(AnaResultPartTypeDtoInt _result) {
        this(_result, new CustList<AnaResultPartType>());
    }

    public ResolvedInstance(AnaResultPartTypeDtoInt _result, CustList<AnaResultPartType> _parts) {
        this(false,_result, _parts,0,0,"");
    }

    public ResolvedInstance(ResolvedInstance _prep, AnalyzedPageEl _ana, int _lt, int _gt, String _infer) {
        this(_prep,_ana.getIndex()+_lt,_ana.getIndex()+_gt,_infer);
    }

    public ResolvedInstance(StaticCallAccessOperation _op, String _infer) {
        this(_op.getPartOffsets(),_op.getLt(),_op.getGt(),_infer);
    }

    private ResolvedInstance(ResolvedInstance _prep, int _lt, int _gt, String _infer) {
        this(true,_prep.result, _prep.parts,_lt,_gt,_infer);
    }

    private ResolvedInstance(boolean _inferred, AnaResultPartTypeDtoInt _result, CustList<AnaResultPartType> _parts, int _lt, int _gt, String _infer) {
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

    public AnaResultPartTypeDtoInt getResult() {
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
