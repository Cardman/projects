package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.text.OperationsSequence;

public final class SuperInvokingConstructor extends AbstractInvokingConstructor {

    public SuperInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        Classes classes_ = _conf.getClasses();
        String clCurName_ = _conf.getGlobalClass();
        String base_ = Templates.getIdFromAllTypes(clCurName_);
        UniqueRootedBlock unique_ =(UniqueRootedBlock) classes_.getClassBody(base_);
        String superClass_ = Templates.quickFormat(clCurName_, unique_.getImportedDirectGenericSuperClass(), _conf);
        return new ClassArgumentMatching(superClass_);
    }

}
