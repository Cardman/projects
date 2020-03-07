package code.expressionlanguage.errors;

import code.expressionlanguage.DefaultInitializer;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.classes.CustLgNames;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class AnalysisMessagesTest {
    @Test
    public void fail() {
        AnalysisMessages def_ = new AnalysisMessages();
        def_.setDuplicateMethod("");
        def_.setDuplicateStartingNb("");
        def_.setDuplicateRefType("");
        def_.setDuplicateMergedMethod("");
        def_.setDuplicateField("");
        def_.setDuplicatePrimtive("");
        def_.setDuplicateVarType("");
        def_.setRefTypeKeyWord("");
        def_.setNotWordCharRefType("");
        def_.setEmptyPrimitive("");
        def_.setIllegalFirstChar("");
        def_.setPrimitiveKeyWord("");
        def_.setEmptyRefTypeIn("");
        def_.setNotWordCharPrimitive("");
        def_.setDigitFirstPrimitive("");
        def_.setRefTypePrimitive("");
        def_.setDefaultPkgNoMatch("");
        def_.setDigitFirstVarType("");
        def_.setEmptyPkgRefType("");
        def_.setDuplicateStarting("");
        def_.setDuplicateStartingUni("");
        def_.setMethodPrimitive("");
        def_.setDuplicateNumberWord("");
        def_.setDigitFirstMethod("");
        def_.setVarTypeKeyWord("");
        def_.setDuplicateStringWord("");
        def_.setDigitFirstRefType("");
        def_.setVarTypePrimitive("");
        def_.setDefaultPkgRefType("");
        def_.setNotWordCharField("");
        def_.setFieldPrimitive("");
        def_.setDigitFirstField("");
        def_.setNotWordCharMethod("");
        def_.setNotWordCharVarType("");
        def_.setDuplicateKeyWord("");
        def_.setNotWordChar("");
        def_.setEmptyPreBin("");
        def_.setEmptyNb("");
        def_.setIllegalChar("");
        def_.setEmptyPreHex("");
        def_.setMethodKeyWord("");
        def_.setFieldKeyWord("");
        def_.setEmptyString("");
        def_.setEmptyWord("");
        def_.setEmptyVarType("");
        def_.setEmptyRefType("");
        def_.setEmptyMethod("");
        def_.setEmptyField("");
        def_.setDigitFirst("");
        def_.setEmptyBinExp("");
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, def_,kw_, lgName_, opts_);
        def_.validateMessageContents(s_,def_.allMessages());
        assertTrue(s_.getClasses().displayMessageErrors(),!s_.getClasses().isEmptyMessageError());
    }

    private static SingleContextEl getCtx(DefaultLockingClass lk_, DefaultInitializer di_, AnalysisMessages _mess, KeyWords kw_, LgNames lgName_, Options opts_) {
        return new SingleContextEl(-1,lk_,di_,opts_,_mess,kw_,lgName_,4);
    }
}
