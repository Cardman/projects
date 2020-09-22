package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.functionid.ClassMethodId;
import code.util.CustList;

public final class PartOffsetsClassMethodId {
    private final CustList<PartOffset> types;
    private final CustList<PartOffset> superTypes;
    private final ClassMethodId id;
    private final int begin;
    private final int length;

    public PartOffsetsClassMethodId(CustList<PartOffset> types, CustList<PartOffset> superTypes, ClassMethodId id, int begin, int length) {
        this.types = types;
        this.superTypes = superTypes;
        this.id = id;
        this.begin = begin;
        this.length = length;
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
