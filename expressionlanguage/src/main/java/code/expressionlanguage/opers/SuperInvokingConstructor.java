package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadConstructorCall;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

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
        RootBlock clBody_ = classes_.getClassBody(base_);
        if (!(clBody_ instanceof UniqueRootedBlock)) {
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.addError(call_);
            return null;
        }
        UniqueRootedBlock unique_ =(UniqueRootedBlock) clBody_;
        String superClass_ = Templates.quickFormat(clCurName_, unique_.getImportedDirectGenericSuperClass(), _conf);
        return new ClassArgumentMatching(superClass_);
    }

}
