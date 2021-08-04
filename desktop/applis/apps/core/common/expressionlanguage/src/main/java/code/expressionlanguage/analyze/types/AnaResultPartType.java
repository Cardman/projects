package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.util.StringList;

public final class AnaResultPartType {
    private final String input;
    private final int loc;
    private final AnaPartType partType;
    private final AccessedBlock rooted;
    private final boolean generated;
    private boolean ok;

    public AnaResultPartType() {
        this("",0, AnaPartType.createErrorType(),null, true);
    }

    public AnaResultPartType(String _input, int _loc, AnalysisMessages _page, AccessedBlock _rooted) {
        this(_input, _loc, AnaPartType.createErrorType(_input, _page), _rooted, true);
    }
    public AnaResultPartType(String _input, int _loc, AnaPartType _partType, AccessedBlock _rooted) {
        this(_input,_loc, _partType,_rooted,false);
    }
    public AnaResultPartType(String _input, int _loc, AnaPartType _partType, AccessedBlock _rooted, boolean _generated) {
        input = _input;
        loc = _loc;
        partType = _partType;
        generated = _generated;
        rooted = _rooted;
    }

    public String getInput() {
        return input;
    }

    public int getLoc() {
        return loc;
    }

    public AccessedBlock getRooted() {
        return rooted;
    }

    public boolean isGenerated() {
        return generated;
    }

    public AnaPartType getPartType() {
        return partType;
    }

    public String getResult(AnalyzedPageEl _page) {
        if (!ok) {
            return _page.getAliasObject();
        }
        return getResult();
    }
    public String getResult() {
        return partType.getAnalyzedType();
    }

    public StringList getErrs() {
        return partType.getErrs();
    }

    public void errs(StringList _errs) {
        partType.errs(_errs);
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean _ok) {
        this.ok = _ok;
    }
}
