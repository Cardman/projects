package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.functionid.ClassMethodId;
import code.util.CustList;

public final class PartOffsetsClassMethodId {
    private final CustList<PartOffset> types;
    private final CustList<PartOffset> superTypes;
    private final ClassMethodId id;
    private final int begin;
    private final int length;

    public PartOffsetsClassMethodId(CustList<PartOffset> _types, CustList<PartOffset> _superTypes, ClassMethodId _id, int _begin, int _length) {
        this.types = _types;
        this.superTypes = _superTypes;
        this.id = _id;
        this.begin = _begin;
        this.length = _length;
    }

    public CustList<PartOffset> getTypes() {
        return types;
    }

    public CustList<PartOffset> getSuperTypes() {
        return superTypes;
    }

    public ClassMethodId getId() {
        return id;
    }

    public int getBegin() {
        return begin;
    }

    public int getLength() {
        return length;
    }
}
