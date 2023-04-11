package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.util.CustList;

public final class PartOffsetsClassMethodIdList {
    private final CustList<AnaResultPartTypeDtoInt> types;
    private final CustList<PartOffsetsClassMethodId> overrides;
    private final InfoErrorDto info;

    public PartOffsetsClassMethodIdList(CustList<AnaResultPartTypeDtoInt> _types, CustList<PartOffsetsClassMethodId> _overrides) {
        this(_types,_overrides,new InfoErrorDto(""));
    }

    public PartOffsetsClassMethodIdList(CustList<AnaResultPartTypeDtoInt> _types, CustList<PartOffsetsClassMethodId> _overrides, InfoErrorDto _info) {
        this.types = _types;
        this.overrides = _overrides;
        info = _info;
    }

    public CustList<AnaResultPartTypeDtoInt> getTypes() {
        return types;
    }

    public CustList<PartOffsetsClassMethodId> getOverrides() {
        return overrides;
    }

    public InfoErrorDto getInfo() {
        return info;
    }
}
