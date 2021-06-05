package code.expressionlanguage.structs;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;

public abstract class AbAnMeStruct extends AbsAnnotatedStruct implements AnnotatedStruct {
    private final AbsRetType retType;

    protected AbAnMeStruct(AbsRetType _retType, AccessEnum _access) {
        super(_access);
        retType = _retType;
    }

    protected AbsRetType type() {
        return retType;
    }

    public String getType() {
        return retType.retType();
    }

    public ClassMetaInfo typeGene(ContextEl _contextEl) {
        return ClassMetaInfo.getExtendedClassMetaInfo(_contextEl,type().retTypeGene(),this);
    }

    public ClassMetaInfo getFormattedReturnType(ContextEl _contextEl) {
        return ClassMetaInfo.getExtendedClassMetaInfo(_contextEl,tryFormatType(_contextEl,this),this);
    }

    protected String tryFormatType(ContextEl _cont, AnnotatedStruct _member) {
        return tryFormatType(_cont,_member.getFormatted().getFormatted(),retType.retType());
    }

}
