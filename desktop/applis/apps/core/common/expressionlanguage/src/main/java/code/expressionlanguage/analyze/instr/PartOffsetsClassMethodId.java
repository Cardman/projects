package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.functionid.ClassMethodId;
import code.util.CustList;

public final class PartOffsetsClassMethodId {
    private final CustList<AnaResultPartType> types;
    private final CustList<AnaResultPartType> superTypes;
    private final ClassMethodId id;
    private final AnaTypeFct fct;
    private final int begin;
    private final int length;
    private final InfoErrorDto info;

    public PartOffsetsClassMethodId(CustList<AnaResultPartType> _types, CustList<AnaResultPartType> _superTypes, ClassMethodId _id, AnaTypeFct _fct, AnalyzedPageEl _page, int _begin, int _length) {
        this(_types,_superTypes,_id,_fct,_page.getIndex()+_begin,_length,new InfoErrorDto(""));
    }

    public PartOffsetsClassMethodId(CustList<AnaResultPartType> _types, CustList<AnaResultPartType> _superTypes, ClassMethodId _id, AnaTypeFct _fct,int _begin, int _length) {
        this(_types,_superTypes,_id,_fct,_begin,_length,new InfoErrorDto(""));
    }

    public PartOffsetsClassMethodId(CustList<AnaResultPartType> _types, CustList<AnaResultPartType> _superTypes, ClassMethodId _id, AnaTypeFct _fct,int _begin, int _length, InfoErrorDto _info) {
        this.types = _types;
        this.superTypes = _superTypes;
        this.id = _id;
        fct = _fct;
        this.begin = _begin;
        this.length = _length;
        info = _info;
    }

    public CustList<AnaResultPartType> getTypes() {
        return types;
    }

    public InfoErrorDto getInfo() {
        return info;
    }

    public CustList<AnaResultPartType> getSuperTypes() {
        return superTypes;
    }

    public ClassMethodId getId() {
        return id;
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
