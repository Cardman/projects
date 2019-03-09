package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.BadAccessClass;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;

public final class StaticAccessOperation extends ConstLeafOperation {

    public StaticAccessOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String ext_ = op_.getExtractType();
        ext_ = ContextEl.removeDottedSpaces(ext_);
        if (!ext_.isEmpty()) {
            Classes classes_ = _conf.getClasses();
            if (classes_.isCustomType(ext_)) {
                String glClass_ = _conf.getGlobalClass();
                String curClassBase_ = Templates.getIdFromAllTypes(glClass_);
                if (!Classes.canAccessClass(curClassBase_, ext_, _conf)) {
                    BadAccessClass badAccess_ = new BadAccessClass();
                    badAccess_.setId(ext_);
                    badAccess_.setIndexFile(_conf.getCurrentLocationIndex());
                    badAccess_.setFileName(_conf.getCurrentFileName());
                    _conf.getClasses().addError(badAccess_);
                }
            }
            Argument a_ = new Argument();
            setSimpleArgument(a_);
            setStaticResultClass(new ClassArgumentMatching(ext_));
            return;
        }
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String realCl_ = str_.substring(str_.indexOf(PAR_LEFT)+1, str_.lastIndexOf(PAR_RIGHT));
        String glClass_ = _conf.getGlobalClass();
        String classStr_;
        if (!realCl_.trim().isEmpty()) {
            classStr_ = _conf.resolveAccessibleIdType(realCl_);
        } else {
            classStr_ = glClass_;
            if (classStr_ == null) {
                classStr_ = _conf.getStandards().getAliasObject();
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(EMPTY_STRING);
                un_.setFileName(_conf.getCurrentFileName());
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(un_);
            }
        }
        Classes classes_ = _conf.getClasses();
        if (classes_.isCustomType(classStr_)) {
            String curClassBase_ = null;
            if (glClass_ != null) {
                curClassBase_ = Templates.getIdFromAllTypes(glClass_);
            }
            if (!Classes.canAccessClass(curClassBase_, classStr_, _conf)) {
                BadAccessClass badAccess_ = new BadAccessClass();
                badAccess_.setId(classStr_);
                badAccess_.setIndexFile(_conf.getCurrentLocationIndex());
                badAccess_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(badAccess_);
            }
        }
        Argument a_ = new Argument();
        setSimpleArgument(a_);
        setStaticResultClass(new ClassArgumentMatching(classStr_));
    }

}
