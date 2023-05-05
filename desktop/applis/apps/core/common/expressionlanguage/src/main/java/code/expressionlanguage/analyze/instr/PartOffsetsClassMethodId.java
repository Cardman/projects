package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.util.CustList;

public final class PartOffsetsClassMethodId {
    private final CustList<AnaResultPartTypeDtoInt> types;
    private final CustList<AnaResultPartTypeDtoInt> superTypes;
    private final AnaTypeFct fct;
    private final int begin;
    private final int length;
    private final InfoErrorDto info;

    public PartOffsetsClassMethodId(CustList<AnaResultPartTypeDtoInt> _types, CustList<AnaResultPartTypeDtoInt> _superTypes, AnaTypeFct _fct, AnalyzedPageEl _page, int _begin, int _length) {
        this(_types,_superTypes, _fct,_page.getIndex()+_begin,_length,new InfoErrorDto(""));
    }

    public PartOffsetsClassMethodId(CustList<AnaResultPartTypeDtoInt> _types, CustList<AnaResultPartTypeDtoInt> _superTypes, AnaTypeFct _fct, int _begin, int _length) {
        this(_types,_superTypes, _fct,_begin,_length,new InfoErrorDto(""));
    }

    public PartOffsetsClassMethodId(CustList<AnaResultPartTypeDtoInt> _types, CustList<AnaResultPartTypeDtoInt> _superTypes, AnaTypeFct _fct, int _begin, int _length, InfoErrorDto _info) {
        this.types = _types;
        this.superTypes = _superTypes;
        fct = _fct;
        this.begin = _begin;
        this.length = _length;
        info = _info;
    }

    public CustList<AnaResultPartTypeDtoInt> getTypes() {
        return types;
    }

    public InfoErrorDto getInfo() {
        return info;
    }

    public CustList<AnaResultPartTypeDtoInt> getSuperTypes() {
        return superTypes;
    }

    public AnaTypeFct getFct() {
        return fct;
    }

    public int getBegin() {
        return begin;
    }

    public int getLength() {
        return length;
    }
}
