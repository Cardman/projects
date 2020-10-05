package code.expressionlanguage.analyze.errors;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.classes.CustLgNames;
import code.expressionlanguage.exec.coverage.Coverage;
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

        def_.setAbstractMethodBody("");
        def_.setAbstractMethodConc("");
        def_.setAbstractMethodImpl("");
        def_.setAbstractMethodRef("");
        def_.setInaccessibleType("");
        def_.setUnexpectedType("");
        def_.setUnexpectedRetType("");
        def_.setMethodsAccesses("");
        def_.setEmptyPackage("");
        def_.setEmptyPartClassName("");
        def_.setBadPartClassName("");
        def_.setKeyWordPartClassName("");
        def_.setPrimPartClassName("");
        def_.setDigitPartClassName("");
        def_.setBadPartVarClassName("");
        def_.setKeyWordPartVarClassName("");
        def_.setPrimPartVarClassName("");
        def_.setDigitPartVarClassName("");
        def_.setDuplicatedPartVarClassName("");
        def_.setCallCtorEnd("");
        def_.setCallCtor("");
        def_.setCallCtorBeforeBlock("");
        def_.setCallCtorFirstLine("");
        def_.setCallCtorIntFromSuperInt("");
        def_.setCallCtorIntNotFromInt("");
        def_.setCallCtorIntAfterSuperThis("");
        def_.setCallCtorIntInherits("");
        def_.setCallCtorSuperClassEnumSingleton("");
        def_.setAnnotFieldNotUniq("");
        def_.setAnnotFieldMust("");
        def_.setDupSuppliedAnnotField("");
        def_.setBadExpression("");
        def_.setBadFieldName("");
        def_.setKeyWordFieldName("");
        def_.setPrimFieldName("");
        def_.setDigitFieldName("");
        def_.setNotRetrievedFields("");
        def_.setBadNbFormat("");
        def_.setBadCharFormat("");
        def_.setBadImplicitCast("");
        def_.setNotPrimitiveWrapper("");
        def_.setVoidType("");
        def_.setBadIndexInParser("");
        def_.setIllegalCharacter("");
        def_.setCallIntInherits("");
        def_.setCallIntNoNeed("");
        def_.setCallIntNoNeedType("");
        def_.setCallIntNeedType("");
        def_.setCallIntOnly("");
        def_.setBadInheritsType("");
        def_.setBadInheritsTypeInn("");
        def_.setBadInheritsTypeAsInn("");
        def_.setBadInheritsTypeInt("");
        def_.setFinalType("");
        def_.setDuplicateSuper("");
        def_.setReservedType("");
        def_.setSuperClass("");
        def_.setUnknownSuperType("");
        def_.setCyclicInherits("");
        def_.setAnnotationParam("");
        def_.setCyclicMapping("");
        def_.setAbsMapping("");
        def_.setFinalMapping("");
        def_.setMustCallIntCtor("");
        def_.setMustNotCallIntCtorAfterThis("");
        def_.setMustCallIntCtorNeed("");
        def_.setMustCallIntCtorNotNeed("");
        def_.setBadLabel("");
        def_.setDuplicatedLabel("");
        def_.setBadMethodName("");
        def_.setKeyWordMethodName("");
        def_.setPrimMethodName("");
        def_.setDigitMethodName("");
        def_.setBadOperatorName("");
        def_.setBadAccess("");
        def_.setBadReturnType("");
        def_.setBadParams("");
        def_.setBadMethodModifier("");
        def_.setBadMethodVararg("");
        def_.setBadIndexerParams("");
        def_.setBadIndexerModifier("");
        def_.setBadIndexerModifiers("");
        def_.setBadIndexerAccesses("");
        def_.setBadIndexerPairGet("");
        def_.setBadIndexerPairSet("");
        def_.setDuplicateCustomMethod("");
        def_.setReservedCustomMethod("");
        def_.setDuplicateIndexer("");
        def_.setDuplicateOperator("");
        def_.setFunctionalApplyNbDiff("");
        def_.setFunctionalApplyOnly("");
        def_.setOperatorNbDiff("");
        def_.setSplitComa("");
        def_.setSplitComaLow("");
        def_.setSplitDiff("");
        def_.setBadDotted("");
        def_.setBadParamName("");
        def_.setKeyWordParamName("");
        def_.setPrimParamName("");
        def_.setDigitParamName("");
        def_.setReservedParamName("");
        def_.setDuplicatedParamName("");
        def_.setBadReturnTypeInherit("");
        def_.setBadReturnTypeIndexer("");
        def_.setDuplicatedOverriding("");
        def_.setTwoFinal("");
        def_.setFinalNotSubReturnType("");
        def_.setTwoReturnTypes("");
        def_.setReturnTypes("");
        def_.setBadVariableName("");
        def_.setKeyWordVariableName("");
        def_.setPrimVariableName("");
        def_.setDigitVariableName("");
        def_.setDuplicatedVariableName("");
        def_.setCyclicCtorCall("");
        def_.setDeadCode("");
        def_.setDuplicatedCtor("");
        def_.setDuplicatedGenericSuperTypes("");
        def_.setDuplicatedInnerType("");
        def_.setDuplicatedType("");
        def_.setDuplicatedTypePrim("");
        def_.setDuplicatedTypeStd("");
        def_.setDuplicatedTypePkg("");
        def_.setEmptyExpressionPart("");
        def_.setDoWhileNotEmpty("");
        def_.setDuplicatedFinal("");
        def_.setIllegalCtorEnum("");
        def_.setIllegalGenericSuperTypeBound("");
        def_.setIllegalCtorAnnotation("");
        def_.setIllegalCtorAbstract("");
        def_.setIllegalCtorBound("");
        def_.setIllegalCtorArray("");
        def_.setIllegalCtorUnknown("");
        def_.setMissingAbrupt("");
        def_.setNotInitClass("");
        def_.setNullValue("");
        def_.setBadParameTypeForId("");
        def_.setNotResolvedOwner("");
        def_.setUndefinedAccessibleField("");
        def_.setStaticAccess("");
        def_.setStaticAccessPrev("");
        def_.setUnassignedFinalField("");
        def_.setUnassignedInferingType("");
        def_.setUndefinedCtor("");
        def_.setUndefinedMethod("");
        def_.setArrayCloneOnly("");
        def_.setUndefinedSuperCtor("");
        def_.setUndefinedSuperCtorCall("");
        def_.setUndefinedVariable("");
        def_.setUnexpectedAffect("");
        def_.setFinalField("");
        def_.setBadOperatorRef("");
        def_.setUnexpectedCatchElseFinally("");
        def_.setUnexpectedAbrupt("");
        def_.setUnexpectedAbruptLab("");
        def_.setUnexpectedCaseDef("");
        def_.setUnexpectedCaseVar("");
        def_.setUnexpectedCaseValue("");
        def_.setUnexpectedCaseDup("");
        def_.setUnexpectedDefDup("");
        def_.setUnexpectedDoTry("");
        def_.setUnexpectedSwitch("");
        def_.setUnexpectedMemberInst("");
        def_.setUnexpectedBlockExp("");
        def_.setUnexpectedOperandTypes("");
        def_.setUnknownType("");
        def_.setEmptyType("");
        def_.setBadParamerizedType("");
        def_.setUnexpectedTypeBound("");
        def_.setUnexpectedVararg("");
        def_.setUnexpectedLeaf("");
        def_.setEmptyPart("");
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        getCtx(lgName_, opts_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(def_);
        AnalysisMessages.validateMessageContents(def_.allMessages(), page_);
        assertTrue(!page_.isEmptyMessageError());
    }

    private static ContextEl getCtx(LgNames lgName_, Options opts_) {
        return lgName_.newContext(4,-1, new Coverage(opts_.isCovering()));
    }
}
