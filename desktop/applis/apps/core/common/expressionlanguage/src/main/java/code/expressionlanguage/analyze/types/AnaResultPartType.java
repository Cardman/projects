package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.util.StringList;

public final class AnaResultPartType {
    private final String input;
    private final int loc;
    private final String result;
    private final AnaPartType partType;
    private final AccessedBlock rooted;
    private final StringList errs = new StringList();

    public AnaResultPartType(String _input, int _loc,String _result, AnaPartType _partType, AccessedBlock _rooted) {
        input = _input;
        loc = _loc;
        result = _result;
        partType = _partType;
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

    public AnaPartType getPartType() {
        return partType;
    }

    public String getResult() {
        if (partType != null) {
            return partType.getAnalyzedType();
        }
        return result;
    }

    public StringList getErrs() {
        return errs;
    }
}
