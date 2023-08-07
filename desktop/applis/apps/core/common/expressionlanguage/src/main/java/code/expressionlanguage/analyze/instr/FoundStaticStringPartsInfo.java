package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.expressionlanguage.common.AnaGeneType;

public final class FoundStaticStringPartsInfo {
    private final String part;
    private final AnaGeneType type;
    private final AnaResultPartTypeDtoInt parts;

    public FoundStaticStringPartsInfo(String _p, AnaGeneType _t, AnaResultPartTypeDtoInt _l) {
        this.part = _p;
        this.type = _t;
        this.parts = _l;
    }

    public AnaGeneType getType() {
        return type;
    }

    public String getPart() {
        return part;
    }

    public AnaResultPartTypeDtoInt getParts() {
        return parts;
    }
}
