package code.expressionlanguage.errors;

import code.expressionlanguage.ContextEl;
import code.util.EntryCust;
import code.util.StringMap;

public final class AnalysisMessages {
    public static final String DUPLICATE_MERGED_METHOD="DuplicateMergedMethod";
    public static final String DUPLICATE_FIELD="DuplicateField";
    public static final String DUPLICATE_VAR_TYPE="DuplicateVarType";
    public static final String EMPTY_PKG_REF_TYPE="EmptyPkgRefType";
    public static final String PRIMITIVE_KEY_WORD="PrimitiveKeyWord";
    public static final String DEFAULT_PKG_REF_TYPE="DefaultPkgRefType";
    public static final String REF_TYPE_KEY_WORD="RefTypeKeyWord";
    public static final String DIGIT_FIRST_PRIMITIVE="DigitFirstPrimitive";
    public static final String ILLEGAL_FIRST_CHAR="IllegalFirstChar";
    public static final String EMPTY_PRIMITIVE="EmptyPrimitive";
    public static final String NOT_WORD_CHAR_PRIMITIVE="NotWordCharPrimitive";
    public static final String NOT_WORD_CHAR_REF_TYPE="NotWordCharRefType";
    public static final String EMPTY_REF_TYPE_IN="EmptyRefTypeIn";
    public static final String REF_TYPE_PRIMITIVE="RefTypePrimitive";
    public static final String DIGIT_FIRST_REF_TYPE="DigitFirstRefType";
    public static final String DUPLICATE_PRIMTIVE="DuplicatePrimtive";
    public static final String DUPLICATE_METHOD="DuplicateMethod";
    public static final String DEFAULT_PKG_NO_MATCH="DefaultPkgNoMatch";
    public static final String DUPLICATE_REF_TYPE="DuplicateRefType";
    public static final String DIGIT_FIRST_METHOD="DigitFirstMethod";
    public static final String NOT_WORD_CHAR_FIELD="NotWordCharField";
    public static final String NOT_WORD_CHAR_METHOD="NotWordCharMethod";
    public static final String VAR_TYPE_KEY_WORD="VarTypeKeyWord";
    public static final String VAR_TYPE_PRIMITIVE="VarTypePrimitive";
    public static final String DIGIT_FIRST_VAR_TYPE="DigitFirstVarType";
    public static final String DUPLICATE_NUMBER_WORD="DuplicateNumberWord";
    public static final String METHOD_PRIMITIVE="MethodPrimitive";
    public static final String FIELD_PRIMITIVE="FieldPrimitive";
    public static final String DUPLICATE_STRING_WORD="DuplicateStringWord";
    public static final String DUPLICATE_KEY_WORD="DuplicateKeyWord";
    public static final String DIGIT_FIRST_FIELD="DigitFirstField";
    public static final String DUPLICATE_STARTING_NB="DuplicateStartingNb";
    public static final String DUPLICATE_STARTING_UNI="DuplicateStartingUni";
    public static final String DUPLICATE_STARTING="DuplicateStarting";
    public static final String NOT_WORD_CHAR_VAR_TYPE="NotWordCharVarType";
    public static final String EMPTY_PRE_BIN="EmptyPreBin";
    public static final String EMPTY_VAR_TYPE="EmptyVarType";
    public static final String EMPTY_WORD="EmptyWord";
    public static final String EMPTY_FIELD="EmptyField";
    public static final String EMPTY_NB="EmptyNb";
    public static final String NOT_WORD_CHAR="NotWordChar";
    public static final String EMPTY_PRE_HEX="EmptyPreHex";
    public static final String EMPTY_METHOD="EmptyMethod";
    public static final String DIGIT_FIRST="DigitFirst";
    public static final String EMPTY_BIN_EXP="EmptyBinExp";
    public static final String EMPTY_STRING="EmptyString";
    public static final String ILLEGAL_CHAR="IllegalChar";
    public static final String METHOD_KEY_WORD="MethodKeyWord";
    public static final String EMPTY_REF_TYPE="EmptyRefType";
    public static final String FIELD_KEY_WORD="FieldKeyWord";
    public static final String ABSTRACT_METHOD_BODY="AbstractMethodBody";
    public static final String ABSTRACT_METHOD_CONC="AbstractMethodConc";
    public static final String ABSTRACT_METHOD_IMPL="AbstractMethodImpl";
    public static final String ABSTRACT_METHOD_REF="AbstractMethodRef";
    public static final String INACCESSIBLE_TYPE="InaccessibleType";
    public static final String UNEXPECTED_TYPE="UnexpectedType";
    public static final String METHODS_ACCESSES="MethodsAccesses";
    public static final String EMPTY_PACKAGE="EmptyPackage";
    public static final String EMPTY_PART_CLASS_NAME="EmptyPartClassName";
    public static final String BAD_PART_CLASS_NAME="BadPartClassName";
    public static final String BAD_PART_VAR_CLASS_NAME="BadPartVarClassName";
    public static final String CALL_CTOR_END="CallCtorEnd";
    public static final String CALL_CTOR="CallCtor";
    public static final String CALL_CTOR_BEFORE_BLOCK="CallCtorBeforeBlock";
    public static final String CALL_CTOR_FIRST_LINE="CallCtorFirstLine";
    public static final String CALL_CTOR_INT_FROM_SUPER_INT="CallCtorIntFromSuperInt";
    public static final String CALL_CTOR_INT_NOT_FROM_INT="CallCtorIntNotFromInt";
    public static final String CALL_CTOR_INT_AFTER_SUPER_THIS="CallCtorIntAfterSuperThis";
    public static final String CALL_CTOR_INT_INHERITS="CallCtorIntInherits";
    public static final String CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON="CallCtorSuperClassEnumSingleton";
    public static final String ANNOT_FIELD_NOT_UNIQ="AnnotFieldNotUniq";
    public static final String ANNOT_FIELD_MUST="AnnotFieldMust";
    public static final String DUP_SUPPLIED_ANNOT_FIELD="DupSuppliedAnnotField";
    public static final String BAD_EXPRESSION="BadExpression";
    public static final String BAD_FIELD_NAME="BadFieldName";
    public static final String NOT_RETRIEVED_FIELDS="NotRetrievedFields";
    public static final String BAD_NB_FORMAT="BadNbFormat";
    public static final String BAD_CHAR_FORMAT="BadCharFormat";
    public static final String BAD_IMPLICIT_CAST="BadImplicitCast";
    public static final String NOT_PRIMITIVE_WRAPPER="NotPrimitiveWrapper";
    public static final String VOID_TYPE="VoidType";
    public static final String BAD_INDEX_IN_PARSER="BadIndexInParser";
    public static final String ILLEGAL_CHARACTER="IllegalCharacter";
    public static final String CALL_INT_INHERITS="CallIntInherits";
    public static final String CALL_INT_NO_NEED="CallIntNoNeed";
    public static final String CALL_INT_NO_NEED_TYPE="CallIntNoNeedType";
    public static final String CALL_INT_NEED_TYPE="CallIntNeedType";
    public static final String CALL_INT_ONLY="CallIntOnly";
    public static final String BAD_INHERITS_TYPE="BadInheritsType";
    public static final String BAD_INHERITS_TYPE_INN="BadInheritsTypeInn";
    public static final String BAD_INHERITS_TYPE_AS_INN="BadInheritsTypeAsInn";
    public static final String BAD_INHERITS_TYPE_INT="BadInheritsTypeInt";
    public static final String FINAL_TYPE="FinalType";
    public static final String DUPLICATE_SUPER="DuplicateSuper";
    public static final String RESERVED_TYPE="ReservedType";
    public static final String SUPER_CLASS="SuperClass";
    public static final String UNKNOWN_SUPER_TYPE="UnknownSuperType";
    public static final String CYCLIC_INHERITS="CyclicInherits";
    public static final String ANNOTATION_PARAM="AnnotationParam";
    public static final String CYCLIC_MAPPING="CyclicMapping";
    public static final String ABS_MAPPING="AbsMapping";
    public static final String FINAL_MAPPING="FinalMapping";
    public static final String MUST_CALL_INT_CTOR="MustCallIntCtor";
    public static final String MUST_NOT_CALL_INT_CTOR_AFTER_THIS="MustNotCallIntCtorAfterThis";
    public static final String MUST_CALL_INT_CTOR_NEED="MustCallIntCtorNeed";
    public static final String MUST_CALL_INT_CTOR_NOT_NEED="MustCallIntCtorNotNeed";
    public static final String BAD_LABEL="BadLabel";
    public static final String DUPLICATED_LABEL="DuplicatedLabel";
    public static final String BAD_METHOD_NAME="BadMethodName";
    public static final String BAD_OPERATOR_NAME="BadOperatorName";
    public static final String BAD_ACCESS="BadAccess";
    public static final String BAD_RETURN_TYPE="BadReturnType";
    public static final String BAD_PARAMS="BadParams";
    public static final String BAD_METHOD_MODIFIER="BadMethodModifier";
    public static final String BAD_METHOD_VARARG="BadMethodVararg";
    public static final String BAD_INDEXER_PARAMS="BadIndexerParams";
    public static final String BAD_INDEXER_MODIFIER="BadIndexerModifier";
    public static final String BAD_INDEXER_MODIFIERS="BadIndexerModifiers";
    public static final String BAD_INDEXER_ACCESSES="BadIndexerAccesses";
    public static final String BAD_INDEXER_PAIR_GET="BadIndexerPairGet";
    public static final String BAD_INDEXER_PAIR_SET="BadIndexerPairSet";
    public static final String DUPLICATE_CUSTOM_METHOD="DuplicateCustomMethod";
    public static final String RESERVED_CUSTOM_METHOD="ReservedCustomMethod";
    public static final String DUPLICATE_INDEXER="DuplicateIndexer";
    public static final String DUPLICATE_OPERATOR="DuplicateOperator";
    public static final String FUNCTIONAL_APPLY_NB_DIFF="FunctionalApplyNbDiff";
    public static final String FUNCTIONAL_APPLY_ONLY="FunctionalApplyOnly";
    public static final String OPERATOR_NB_DIFF="OperatorNbDiff";
    public static final String SPLIT_COMA="SplitComa";
    public static final String SPLIT_COMA_LOW="SplitComaLow";
    public static final String SPLIT_DIFF="SplitDiff";
    public static final String BAD_DOTTED="BadDotted";
    public static final String BAD_PARAM_NAME="BadParamName";
    public static final String RESERVED_PARAM_NAME="ReservedParamName";
    public static final String DUPLICATED_PARAM_NAME="DuplicatedParamName";
    public static final String BAD_RETURN_TYPE_INHERIT="BadReturnTypeInherit";
    public static final String BAD_RETURN_TYPE_INDEXER="BadReturnTypeIndexer";
    public static final String DUPLICATED_OVERRIDING="DuplicatedOverriding";
    public static final String TWO_FINAL="TwoFinal";
    public static final String FINAL_NOT_SUB_RETURN_TYPE="FinalNotSubReturnType";
    public static final String TWO_RETURN_TYPES="TwoReturnTypes";
    public static final String RETURN_TYPES="ReturnTypes";
    public static final String BAD_VARIABLE_NAME="BadVariableName";
    public static final String CYCLIC_CTOR_CALL="CyclicCtorCall";
    public static final String DEAD_CODE="DeadCode";
    public static final String DUPLICATED_CTOR="DuplicatedCtor";
    public static final String DUPLICATED_GENERIC_SUPER_TYPES="DuplicatedGenericSuperTypes";
    public static final String DUPLICATED_INNER_TYPE="DuplicatedInnerType";
    public static final String DUPLICATED_TYPE="DuplicatedType";
    public static final String DUPLICATED_TYPE_PRIM="DuplicatedTypePrim";
    public static final String DUPLICATED_TYPE_STD="DuplicatedTypeStd";
    public static final String DUPLICATED_TYPE_PKG="DuplicatedTypePkg";
    public static final String EMPTY_EXPRESSION_PART="EmptyExpressionPart";
    public static final String DO_WHILE_NOT_EMPTY="DoWhileNotEmpty";
    public static final String DUPLICATED_FINAL="DuplicatedFinal";
    public static final String ILLEGAL_CTOR_ENUM="IllegalCtorEnum";
    public static final String ILLEGAL_GENERIC_SUPER_TYPE_BOUND="IllegalGenericSuperTypeBound";
    public static final String ILLEGAL_CTOR_ANNOTATION="IllegalCtorAnnotation";
    public static final String ILLEGAL_CTOR_ABSTRACT="IllegalCtorAbstract";
    public static final String ILLEGAL_CTOR_BOUND="IllegalCtorBound";
    public static final String ILLEGAL_CTOR_ARRAY="IllegalCtorArray";
    public static final String ILLEGAL_CTOR_UNKNOWN="IllegalCtorUnknown";
    public static final String MISSING_ABRUPT="MissingAbrupt";
    public static final String NOT_INIT_CLASS="NotInitClass";
    public static final String NULL_VALUE="NullValue";
    public static final String BAD_PARAME_TYPE_FOR_ID="BadParameTypeForId";
    public static final String NOT_RESOLVED_OWNER="NotResolvedOwner";
    public static final String UNDEFINED_ACCESSIBLE_FIELD="UndefinedAccessibleField";
    public static final String STATIC_ACCESS="StaticAccess";
    public static final String STATIC_ACCESS_PREV="StaticAccessPrev";
    public static final String UNASSIGNED_FINAL_FIELD="UnassignedFinalField";
    public static final String UNASSIGNED_INFERING_TYPE="UnassignedInferingType";
    public static final String UNDEFINED_CTOR="UndefinedCtor";
    public static final String UNDEFINED_METHOD="UndefinedMethod";
    public static final String ARRAY_CLONE_ONLY="ArrayCloneOnly";
    public static final String UNDEFINED_SUPER_CTOR="UndefinedSuperCtor";
    public static final String UNDEFINED_SUPER_CTOR_CALL="UndefinedSuperCtorCall";
    public static final String UNDEFINED_VARIABLE="UndefinedVariable";
    public static final String UNEXPECTED_AFFECT="UnexpectedAffect";
    public static final String FINAL_FIELD="FinalField";
    public static final String BAD_OPERATOR_REF="BadOperatorRef";
    public static final String UNEXPECTED_CATCH_ELSE_FINALLY="UnexpectedCatchElseFinally";
    public static final String UNEXPECTED_ABRUPT="UnexpectedAbrupt";
    public static final String UNEXPECTED_ABRUPT_LAB="UnexpectedAbruptLab";
    public static final String UNEXPECTED_CASE_DEF="UnexpectedCaseDef";
    public static final String UNEXPECTED_CASE_VAR="UnexpectedCaseVar";
    public static final String UNEXPECTED_CASE_VALUE="UnexpectedCaseValue";
    public static final String UNEXPECTED_CASE_DUP="UnexpectedCaseDup";
    public static final String UNEXPECTED_DEF_DUP="UnexpectedDefDup";
    public static final String UNEXPECTED_DO_TRY="UnexpectedDoTry";
    public static final String UNEXPECTED_SWITCH="UnexpectedSwitch";
    public static final String UNEXPECTED_MEMBER_INST="UnexpectedMemberInst";
    public static final String UNEXPECTED_BLOCK_EXP="UnexpectedBlockExp";
    public static final String UNEXPECTED_OPERAND_TYPES="UnexpectedOperandTypes";
    public static final String UNKNOWN_TYPE="UnknownType";
    public static final String BAD_PARAMERIZED_TYPE="BadParamerizedType";
    public static final String UNEXPECTED_TYPE_BOUND="UnexpectedTypeBound";
    public static final String UNEXPECTED_VARARG="UnexpectedVararg";
    public static final String UNEXPECTED_LEAF="UnexpectedLeaf";
    public static final String EMPTY_PART="EmptyPart";
    private String emptyWord = "{0} has an empty key word value.";
    private String notWordChar = "{0} contains a character {1} that is not a character of a word.";
    private String digitFirst = "{0} starts with {1} that is digit.";

    private String emptyString = "{0} has an empty key string value.";
    private String emptyNb = "{0} has an empty key number value.";
    private String emptyBinExp = "empty binary exp string";
    private String emptyPreBin = "empty prefix binary string";
    private String emptyPreHex = "empty prefix hexadecimal string";
    private String illegalChar = "the key word number {0} contains a character {1} that is illegal";
    private String illegalFirstChar = "the key word number {0} starts with {1} that is illegal as first character";

    private String emptyPrimitive = "{0} has an empty primitive value.";
    private String notWordCharPrimitive = "{0} contains a character {1} that is not a character of a word.";
    private String primitiveKeyWord = "the primitive {0}:{1} is a duplicate string of a key word.";
    private String digitFirstPrimitive = "{0} starts with {1} that is digit.";

    private String emptyRefType = "{0} has an empty reference type value.";
    private String emptyRefTypeIn = "{0} has an empty reference string between dots {1}.";
    private String notWordCharRefType = "{0} contains a character {1} that is not a character of a word.";
    private String refTypeKeyWord = "the reference type {0}:{1} contains a duplicate string of a key word.";
    private String refTypePrimitive = "the reference type {0}:{1} contains a duplicate string of a primitive.";
    private String digitFirstRefType = "{0} starts with {1} that is digit.";
    private String emptyPkgRefType = "{0}:{1} does not belong to a package.";
    private String defaultPkgRefType = "{0} match the default package {1}.";
    private String defaultPkgNoMatch = "the default package {0} has no reference type.";

    private String emptyMethod = "the method key {0} in reference type {1} has an empty method name.";
    private String notWordCharMethod = "the method name {0} in reference type {1} contains a character {2} that is not a character of a word.";
    private String methodKeyWord = "the method name {0} in reference type {1}:{2} is a duplicate string of a key word.";
    private String methodPrimitive = "the method name {0} in reference type {1}:{2} is a duplicate string of a primitive.";
    private String digitFirstMethod = "the method name {0} in reference type {1} starts with {2} that is digit.";

    private String emptyField = "the field key {0} in reference type {1} has an empty method name.";
    private String notWordCharField = "the field name {0} in reference type {1} contains a character {2} that is not a character of a word.";
    private String fieldKeyWord = "the field name {0} in reference type {1}:{2} is a duplicate string of a key word.";
    private String fieldPrimitive = "the field name {0} in reference type {1}:{2} is a duplicate string of a primitive.";
    private String digitFirstField = "the field name {0} in reference type {1} starts with {2} that is digit.";

    private String emptyVarType = "the variable type key {0} in reference type {1} has an empty method name.";
    private String notWordCharVarType = "the variable type name {0} in reference type {1} contains a character {2} that is not a character of a word.";
    private String varTypeKeyWord = "the variable type name {0} in reference type {1}:{2} is a duplicate string of a key word.";
    private String varTypePrimitive = "the variable type name {0} in reference type {1}:{2} is a duplicate string of a primitive.";
    private String digitFirstVarType = "the variable type name {0} in reference type {1} starts with {2} that is digit.";

    private String duplicateKeyWord = "{0} is duplicated as key word.";
    private String duplicateStringWord = "{0} is duplicated as escaping word string in string.";
    private String duplicateStarting = "the escaping word {0} starts with the escaping word {1}.";
    private String duplicateStartingUni = "the escaping word {0} starts with the unicode escaping character {1}.";
    private String duplicateNumberWord = "{0} is duplicated as number word.";
    private String duplicateStartingNb = "the number word {0} starts with the number word {1}.";
    private String duplicatePrimtive = "the primitive {0} is duplicated.";
    private String duplicateRefType = "the reference type {0} is duplicated.";
    private String duplicateMethod = "the method name {0} is duplicated.";
    private String duplicateField = "the field name {0} is duplicated.";
    private String duplicateVarType = "the variable type {0} is duplicated.";
    private String duplicateMergedMethod = "the merged method name {0}:{1} is duplicated.";

    private String abstractMethodBody="The method {1} in the type {0} is abstract and cannot have a body.";
    private String abstractMethodConc="The method {1} in the concrete type {0} must not be abstract.";
    private String abstractMethodImpl="The method {1} from the type {0} must be overriden in the concrete type {2}.";
    private String abstractMethodRef="The method {1} from the type {0} must not be called directly because of abstract.";
    private String inaccessibleType="The type {0} is not accessible from the type {1}.";
    private String unexpectedType="The type {0} is unexpected.";
    private String methodsAccesses="The method {1} from the type {0} is strictly more accessible than the method {3} from the type {2}.";
    private String emptyPackage="A type must have an non empty package.";
    private String emptyPartClassName="The part must not be empty.";
    private String badPartClassName="The part {0} in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.";
    private String badPartVarClassName="The part {0} in a variable type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.";
    private String callCtorEnd="The call of a constructor using implicitly the instance must be applied at the end of the instruction.";
    private String callCtor="The call of a constructor using implicitly the instance must be applied in a constructor.";
    private String callCtorBeforeBlock="The call of a constructor using implicitly the instance must be applied before a block of instructions.";
    private String callCtorFirstLine="The call of a constructor of the type or the super class using implicitly the instance must be applied on the first line.";
    private String callCtorIntFromSuperInt="The call of a constructor of interface must refer a super interface of the calling type.";
    private String callCtorIntNotFromInt="The call of a constructor of interface must not applied in a constructor of interface.";
    private String callCtorIntAfterSuperThis="A call of a constructor of interface must be applied only after a call of a constructor using implicitly the instance.";
    private String callCtorIntInherits="The call of a constructor of the interface {0} cannot be applied before calling the constructor of the interface {1}.";
    private String callCtorSuperClassEnumSingleton="The super constructor can be called only from a class or an enum (singleton or normal).";
    private String annotFieldNotUniq="The field of the annotatation could not be found uniquely.";
    private String annotFieldMust="The field {0} of the annotatation is compulsory.";
    private String dupSuppliedAnnotField="The field {0} of the annotatation is supplied by duplicate.";
    private String badExpression="Unexpected character {0} at the position {1} in the expression {2}";
    private String badFieldName="The field name {0} is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.";
    private String notRetrievedFields="No field could be retrieved.";
    private String badNbFormat="Bad number {0}";
    private String badCharFormat="Bad character format {0}";
    private String badImplicitCast="The type {0} cannot be implicitly cast to {1}";
    private String notPrimitiveWrapper="The type {0} is not a primitive type or a wrapper type.";
    private String voidType="The type cannot be the key word {0}.";
    private String badIndexInParser="Bad index by parsing.";
    private String illegalCharacter="The characters ascii {0} are illegal.";
    private String callIntInherits="Initializing the interface {0} cannot be applied before initializing the interface {1}.";
    private String callIntNoNeed="Initializing a type from the interface {0} is not needed.";
    private String callIntNoNeedType="Initializing the interface {0} from the type {1} is not needed.";
    private String callIntNeedType="Initializing the interface {0} from the type {1} is needed.";
    private String callIntOnly="The type {0} is not an interface.";
    private String badInheritsType="The type {0} cannot have the type {1} as super type.";
    private String badInheritsTypeInn="The type {0} cannot have the type {1} as super type because {1} is instance type.";
    private String badInheritsTypeAsInn="The type {0} cannot have the type {1} as super type because {0} has {2} parents types and {1} has {3} parents types.";
    private String badInheritsTypeInt="The interface {0} cannot have the type {1} as super type because {1} is not an interface.";
    private String finalType="The type {0} cannot have the type {1} as super type because {1} is final.";
    private String duplicateSuper="The type {0} cannot have the type {1} duplicated as super type {2} times.";
    private String reservedType="The type {0} cannot have explicitly the type {1} as super type because {1} is reserved.";
    private String superClass="The class {0} cannot have more than one super class ({1} times).";
    private String unknownSuperType="The super types of the type {0} could not be found.";
    private String cyclicInherits="The type {0} belongs to a cyclic inheriting.";
    private String annotationParam="The annotation {0} cannot be parameterized.";
    private String cyclicMapping="The type variables of the type {0} have a cyclic inheriting.";
    private String absMapping="There are {0} abstract types as upper bounds.";
    private String finalMapping="There is at least one final type as upper bound ({0} times).";
    private String mustCallIntCtor="The type {0} must have a constructor because of implementing interfaces with instance elements.";
    private String mustNotCallIntCtorAfterThis="There must not be have constructor call after an alternate constructor call.";
    private String mustCallIntCtorNeed="A constructor of the type {0} must be called in the constructor.";
    private String mustCallIntCtorNotNeed="A constructor of the type {0} must not be called in the constructor.";
    private String badLabel="A label must be a word (included characters dollars).";
    private String duplicatedLabel="The label is duplicated.";
    private String badMethodName="The method name {0} is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.";
    private String badOperatorName="The operator symbol {0} is not valid.";
    private String badAccess="The method {0} must be public.";
    private String badReturnType="The method {0} must have {1} as return type.";
    private String badParams="The method {0} must have one parameter.";
    private String badMethodModifier="The method {0} must be static.";
    private String badMethodVararg="The method {0} must not be variable argument.";
    private String badIndexerParams="The indexer {0} must have at least one parameter.";
    private String badIndexerModifier="The indexer {0} must not be static.";
    private String badIndexerModifiers="The indexers {0} get and set must have the same modifier.";
    private String badIndexerAccesses="The indexers {0} get and set must have the same access.";
    private String badIndexerPairGet="The indexer {0} get must be defined.";
    private String badIndexerPairSet="The indexer {0} set must be defined.";
    private String duplicateCustomMethod="The method {0} is duplicated.";
    private String reservedCustomMethod="The method {0} is reserved.";
    private String duplicateIndexer="The indexer {0} is duplicated.";
    private String duplicateOperator="The operator {0} is duplicated.";
    private String functionalApplyNbDiff="The number of required arguments {0} is different from the number of supplied arguments {1} for the method of the elliptic type {2}";
    private String functionalApplyOnly="Only the method {0} can be used for the elliptic type {1}";
    private String operatorNbDiff="The number of required operands {0} is different from the number of supplied arguments {1} for the operator {2}";
    private String splitComa="The number of required splitted parts by comas {0} is greater than the number of supplied splitted parts by comas {1}.";
    private String splitComaLow="The number of required splitted parts by comas {0} is lower than the number of supplied splitted parts by comas {1}.";
    private String splitDiff="The number of required operands {0} is different from the number of supplied arguments {1}.";
    private String badDotted="The code part following the dot operator cannot be used.";
    private String badParamName="The parameter method name {0} is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.";
    private String reservedParamName="The parameter method name {0} is reserved for indexer set.";
    private String duplicatedParamName="The parameter function name {0} is duplicated.";
    private String badReturnTypeInherit="The return type {0} of the method {1} of the type {2} is not sub type of the return type {3} of the method {4} of the type {5}";
    private String badReturnTypeIndexer="The return type {0} of the indexer {1} of the type {2} is not the return type {3} of the indexer {4} of the type {5}";
    private String duplicatedOverriding="The type {0} inherits a function {1} that is duplicated.";
    private String twoFinal="The type {0} inherits two final functions with key {1}.";
    private String finalNotSubReturnType="The return type {0} of the final method {1} of the type {2} is not sub type of the return type {3} of the method {4} of the type {5}";
    private String returnTypes="The indexers with key {0} of the types {1} have the types {2} as return types.";
    private String twoReturnTypes="The merged methods with key {0} of the types {1} have the sub types {2} as return types.";
    private String badVariableName="The variable name {0} is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.";
    private String cyclicCtorCall="The constructors {0} of the type {1} belong to cyclic calls.";
    private String deadCode="The code is unreachable in the function {0}";
    private String duplicatedCtor="The constructor {0} is duplicated.";
    private String duplicatedGenericSuperTypes="The generic super types {0} are duplicated.";
    private String duplicatedInnerType="The inner type simple name {0} is duplicated.";
    private String duplicatedType="The type name {0} is duplicated with an other custom type.";
    private String duplicatedTypePrim="The type name {0} is duplicated with a primitive type.";
    private String duplicatedTypeStd="The type name {0} is duplicated with a standard type.";
    private String duplicatedTypePkg="The type name {0} is shadowed by the package {1}.";
    private String emptyExpressionPart="The expression part is empty.";
    private String doWhileNotEmpty="The {0} block associated to a {1} block must be empty.";
    private String duplicatedFinal="The function {0} of the type {1} is final. So overriding it is forbidden.";
    private String illegalCtorEnum="A constructor of a enum cannot be called explicitly.";
    private String illegalGenericSuperTypeBound="The argument {0} of the generic super type {1} is bound. It cannot be used in generic super type.";
    private String illegalCtorAnnotation="After @ the type {0} is not an annotation.";
    private String illegalCtorAbstract="The type {0} cannot be instantiated because of abstract.";
    private String illegalCtorBound="The argument {0} of the type {1} is bound. It cannot be used in constructor call.";
    private String illegalCtorArray="The type {0} is an array type. It cannot be used as previous dotted argument for instancing.";
    private String illegalCtorUnknown="The type {0} is not resolved for instancing.";
    private String missingAbrupt="A {0} block or a {1} block is missing for the method {2}.";
    private String notInitClass="The type {0} is not initialized.";
    private String nullValue="The value must not be null because of possible {0}.";
    private String badParameTypeForId="The parameter type {0} is not resolved for getting identity function.";
    private String notResolvedOwner="The owner for the type {0} is not resolved for instancing.";
    private String undefinedAccessibleField="There is no accessible field named {0} from the type {1} in this context.";
    private String staticAccess="The context is static. The key word {0} cannot be used in this context.";
    private String staticAccessPrev="A type must be used to access the key word {0}.";
    private String unassignedFinalField="The field {0} of the type {1} is not assigned with a constant.";
    private String unassignedInferingType="The inferring type {0} is not defined for the variables {1}.";
    private String undefinedCtor="The constructor {0} is undefined.";
    private String undefinedMethod="The function {0} is undefined.";
    private String arrayCloneOnly="Only the method {0} can ne used for the array type {1}";
    private String undefinedSuperCtor="No super constructor with implicit call is defined and accessible. There must be at least one constructor for the type {0}";
    private String undefinedSuperCtorCall="No super constructor with implicit call is defined and accessible. The explicit call of a super constructor is required for the constructor {0}.";
    private String undefinedVariable="The variable {0} is undefined in this context.";
    private String unexpectedAffect="The assignment operator {0} is unexpected.";
    private String finalField="The field {0} is already assigned.";
    private String badOperatorRef="The string {0} is not an operator reference.";
    private String unexpectedCatchElseFinally="The {0} block must be preceded by one of the blocks {1}.";
    private String unexpectedAbrupt="The {0} block must be inner of the blocks {1}.";
    private String unexpectedAbruptLab="The {0} block with label {1} must be inner of a labelled with {2} block.";
    private String unexpectedCaseDef="The {0} block with expression {1} must be child of a block {2}.";
    private String unexpectedCaseVar="The {0} block with expression {1} is not constant.";
    private String unexpectedCaseValue="The {0} block with value {1} is not a sub type of {2}.";
    private String unexpectedCaseDup="The {0} block with value {1} is duplicated in the parent {2} block.";
    private String unexpectedDefDup="The {0} block is duplicated in the parent {1} block.";
    private String unexpectedDoTry="The {0} block must be followed by one of the blocks {1}.";
    private String unexpectedSwitch="The {0} block must contain only one of the blocks {1}.";
    private String unexpectedMemberInst="The instance type {0} must contain only instance types and instance initilizing blocks.";
    private String unexpectedBlockExp="The block is unexpected.";
    private String unexpectedOperandTypes="The operands types {0} for the operator {1} are unexpected.";
    private String unknownType="The type {0} is unknown.";
    private String badParamerizedType="The type {0} is not parameterized correctly.";
    private String unexpectedTypeBound="The type {0} is unexpected for bound.";
    private String unexpectedVararg="The three dots are unexpected here.";
    private String unexpectedLeaf="The key word {0} is unexpected here.";
    private String emptyPart="There must be an expression.";

    public static void validateMessageContents(ContextEl _cont, StringMap<String> _list) {
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                _cont.addMessageError(key_);
            }
        }
    }
    public StringMap<String> allMessages() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry(DUPLICATE_MERGED_METHOD,getDuplicateMergedMethod());
        mess_.addEntry(DUPLICATE_FIELD,getDuplicateField());
        mess_.addEntry(DUPLICATE_VAR_TYPE,getDuplicateVarType());
        mess_.addEntry(EMPTY_PKG_REF_TYPE,getEmptyPkgRefType());
        mess_.addEntry(PRIMITIVE_KEY_WORD,getPrimitiveKeyWord());
        mess_.addEntry(DEFAULT_PKG_REF_TYPE,getDefaultPkgRefType());
        mess_.addEntry(REF_TYPE_KEY_WORD,getRefTypeKeyWord());
        mess_.addEntry(DIGIT_FIRST_PRIMITIVE,getDigitFirstPrimitive());
        mess_.addEntry(ILLEGAL_FIRST_CHAR,getIllegalFirstChar());
        mess_.addEntry(EMPTY_PRIMITIVE,getEmptyPrimitive());
        mess_.addEntry(NOT_WORD_CHAR_PRIMITIVE,getNotWordCharPrimitive());
        mess_.addEntry(NOT_WORD_CHAR_REF_TYPE,getNotWordCharRefType());
        mess_.addEntry(EMPTY_REF_TYPE_IN,getEmptyRefTypeIn());
        mess_.addEntry(REF_TYPE_PRIMITIVE,getRefTypePrimitive());
        mess_.addEntry(DIGIT_FIRST_REF_TYPE,getDigitFirstRefType());
        mess_.addEntry(DUPLICATE_PRIMTIVE,getDuplicatePrimtive());
        mess_.addEntry(DUPLICATE_METHOD,getDuplicateMethod());
        mess_.addEntry(DEFAULT_PKG_NO_MATCH,getDefaultPkgNoMatch());
        mess_.addEntry(DUPLICATE_REF_TYPE,getDuplicateRefType());
        mess_.addEntry(DIGIT_FIRST_METHOD,getDigitFirstMethod());
        mess_.addEntry(NOT_WORD_CHAR_FIELD,getNotWordCharField());
        mess_.addEntry(NOT_WORD_CHAR_METHOD,getNotWordCharMethod());
        mess_.addEntry(VAR_TYPE_KEY_WORD,getVarTypeKeyWord());
        mess_.addEntry(VAR_TYPE_PRIMITIVE,getVarTypePrimitive());
        mess_.addEntry(DIGIT_FIRST_VAR_TYPE,getDigitFirstVarType());
        mess_.addEntry(DUPLICATE_NUMBER_WORD,getDuplicateNumberWord());
        mess_.addEntry(METHOD_PRIMITIVE,getMethodPrimitive());
        mess_.addEntry(FIELD_PRIMITIVE,getFieldPrimitive());
        mess_.addEntry(DUPLICATE_STRING_WORD,getDuplicateStringWord());
        mess_.addEntry(DUPLICATE_KEY_WORD,getDuplicateKeyWord());
        mess_.addEntry(DIGIT_FIRST_FIELD,getDigitFirstField());
        mess_.addEntry(DUPLICATE_STARTING_NB,getDuplicateStartingNb());
        mess_.addEntry(DUPLICATE_STARTING_UNI,getDuplicateStartingUni());
        mess_.addEntry(DUPLICATE_STARTING,getDuplicateStarting());
        mess_.addEntry(NOT_WORD_CHAR_VAR_TYPE,getNotWordCharVarType());
        mess_.addEntry(EMPTY_PRE_BIN,getEmptyPreBin());
        mess_.addEntry(EMPTY_VAR_TYPE,getEmptyVarType());
        mess_.addEntry(EMPTY_WORD,getEmptyWord());
        mess_.addEntry(EMPTY_FIELD,getEmptyField());
        mess_.addEntry(EMPTY_NB,getEmptyNb());
        mess_.addEntry(NOT_WORD_CHAR,getNotWordChar());
        mess_.addEntry(EMPTY_PRE_HEX,getEmptyPreHex());
        mess_.addEntry(EMPTY_METHOD,getEmptyMethod());
        mess_.addEntry(DIGIT_FIRST,getDigitFirst());
        mess_.addEntry(EMPTY_BIN_EXP,getEmptyBinExp());
        mess_.addEntry(EMPTY_STRING,getEmptyString());
        mess_.addEntry(ILLEGAL_CHAR,getIllegalChar());
        mess_.addEntry(METHOD_KEY_WORD,getMethodKeyWord());
        mess_.addEntry(EMPTY_REF_TYPE,getEmptyRefType());
        mess_.addEntry(FIELD_KEY_WORD,getFieldKeyWord());
        mess_.addEntry(ABSTRACT_METHOD_BODY,getAbstractMethodBody());
        mess_.addEntry(ABSTRACT_METHOD_CONC,getAbstractMethodConc());
        mess_.addEntry(ABSTRACT_METHOD_IMPL,getAbstractMethodImpl());
        mess_.addEntry(ABSTRACT_METHOD_REF,getAbstractMethodRef());
        mess_.addEntry(INACCESSIBLE_TYPE,getInaccessibleType());
        mess_.addEntry(UNEXPECTED_TYPE,getUnexpectedType());
        mess_.addEntry(METHODS_ACCESSES,getMethodsAccesses());
        mess_.addEntry(EMPTY_PACKAGE,getEmptyPackage());
        mess_.addEntry(EMPTY_PART_CLASS_NAME,getEmptyPartClassName());
        mess_.addEntry(BAD_PART_CLASS_NAME,getBadPartClassName());
        mess_.addEntry(BAD_PART_VAR_CLASS_NAME,getBadPartVarClassName());
        mess_.addEntry(CALL_CTOR_END,getCallCtorEnd());
        mess_.addEntry(CALL_CTOR,getCallCtor());
        mess_.addEntry(CALL_CTOR_BEFORE_BLOCK,getCallCtorBeforeBlock());
        mess_.addEntry(CALL_CTOR_FIRST_LINE,getCallCtorFirstLine());
        mess_.addEntry(CALL_CTOR_INT_FROM_SUPER_INT,getCallCtorIntFromSuperInt());
        mess_.addEntry(CALL_CTOR_INT_NOT_FROM_INT,getCallCtorIntNotFromInt());
        mess_.addEntry(CALL_CTOR_INT_AFTER_SUPER_THIS,getCallCtorIntAfterSuperThis());
        mess_.addEntry(CALL_CTOR_INT_INHERITS,getCallCtorIntInherits());
        mess_.addEntry(CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON,getCallCtorSuperClassEnumSingleton());
        mess_.addEntry(ANNOT_FIELD_NOT_UNIQ,getAnnotFieldNotUniq());
        mess_.addEntry(ANNOT_FIELD_MUST,getAnnotFieldMust());
        mess_.addEntry(DUP_SUPPLIED_ANNOT_FIELD,getDupSuppliedAnnotField());
        mess_.addEntry(BAD_EXPRESSION,getBadExpression());
        mess_.addEntry(BAD_FIELD_NAME,getBadFieldName());
        mess_.addEntry(NOT_RETRIEVED_FIELDS,getNotRetrievedFields());
        mess_.addEntry(BAD_NB_FORMAT,getBadNbFormat());
        mess_.addEntry(BAD_CHAR_FORMAT,getBadCharFormat());
        mess_.addEntry(BAD_IMPLICIT_CAST,getBadImplicitCast());
        mess_.addEntry(NOT_PRIMITIVE_WRAPPER,getNotPrimitiveWrapper());
        mess_.addEntry(VOID_TYPE,getVoidType());
        mess_.addEntry(BAD_INDEX_IN_PARSER,getBadIndexInParser());
        mess_.addEntry(ILLEGAL_CHARACTER,getIllegalCharacter());
        mess_.addEntry(CALL_INT_INHERITS,getCallIntInherits());
        mess_.addEntry(CALL_INT_NO_NEED,getCallIntNoNeed());
        mess_.addEntry(CALL_INT_NO_NEED_TYPE,getCallIntNoNeedType());
        mess_.addEntry(CALL_INT_NEED_TYPE,getCallIntNeedType());
        mess_.addEntry(CALL_INT_ONLY,getCallIntOnly());
        mess_.addEntry(BAD_INHERITS_TYPE,getBadInheritsType());
        mess_.addEntry(BAD_INHERITS_TYPE_INN,getBadInheritsTypeInn());
        mess_.addEntry(BAD_INHERITS_TYPE_AS_INN,getBadInheritsTypeAsInn());
        mess_.addEntry(BAD_INHERITS_TYPE_INT,getBadInheritsTypeInt());
        mess_.addEntry(FINAL_TYPE,getFinalType());
        mess_.addEntry(DUPLICATE_SUPER,getDuplicateSuper());
        mess_.addEntry(RESERVED_TYPE,getReservedType());
        mess_.addEntry(SUPER_CLASS,getSuperClass());
        mess_.addEntry(UNKNOWN_SUPER_TYPE,getUnknownSuperType());
        mess_.addEntry(CYCLIC_INHERITS,getCyclicInherits());
        mess_.addEntry(ANNOTATION_PARAM,getAnnotationParam());
        mess_.addEntry(CYCLIC_MAPPING,getCyclicMapping());
        mess_.addEntry(ABS_MAPPING,getAbsMapping());
        mess_.addEntry(FINAL_MAPPING,getFinalMapping());
        mess_.addEntry(MUST_CALL_INT_CTOR,getMustCallIntCtor());
        mess_.addEntry(MUST_NOT_CALL_INT_CTOR_AFTER_THIS,getMustNotCallIntCtorAfterThis());
        mess_.addEntry(MUST_CALL_INT_CTOR_NEED,getMustCallIntCtorNeed());
        mess_.addEntry(MUST_CALL_INT_CTOR_NOT_NEED,getMustCallIntCtorNotNeed());
        mess_.addEntry(BAD_LABEL,getBadLabel());
        mess_.addEntry(DUPLICATED_LABEL,getDuplicatedLabel());
        mess_.addEntry(BAD_METHOD_NAME,getBadMethodName());
        mess_.addEntry(BAD_OPERATOR_NAME,getBadOperatorName());
        mess_.addEntry(BAD_ACCESS,getBadAccess());
        mess_.addEntry(BAD_RETURN_TYPE,getBadReturnType());
        mess_.addEntry(BAD_PARAMS,getBadParams());
        mess_.addEntry(BAD_METHOD_MODIFIER,getBadMethodModifier());
        mess_.addEntry(BAD_METHOD_VARARG,getBadMethodVararg());
        mess_.addEntry(BAD_INDEXER_PARAMS,getBadIndexerParams());
        mess_.addEntry(BAD_INDEXER_MODIFIER,getBadIndexerModifier());
        mess_.addEntry(BAD_INDEXER_MODIFIERS,getBadIndexerModifiers());
        mess_.addEntry(BAD_INDEXER_ACCESSES,getBadIndexerAccesses());
        mess_.addEntry(BAD_INDEXER_PAIR_GET,getBadIndexerPairGet());
        mess_.addEntry(BAD_INDEXER_PAIR_SET,getBadIndexerPairSet());
        mess_.addEntry(DUPLICATE_CUSTOM_METHOD,getDuplicateCustomMethod());
        mess_.addEntry(RESERVED_CUSTOM_METHOD,getReservedCustomMethod());
        mess_.addEntry(DUPLICATE_INDEXER,getDuplicateIndexer());
        mess_.addEntry(DUPLICATE_OPERATOR,getDuplicateOperator());
        mess_.addEntry(FUNCTIONAL_APPLY_NB_DIFF,getFunctionalApplyNbDiff());
        mess_.addEntry(FUNCTIONAL_APPLY_ONLY,getFunctionalApplyOnly());
        mess_.addEntry(OPERATOR_NB_DIFF,getOperatorNbDiff());
        mess_.addEntry(SPLIT_COMA,getSplitComa());
        mess_.addEntry(SPLIT_COMA_LOW,getSplitComaLow());
        mess_.addEntry(SPLIT_DIFF,getSplitDiff());
        mess_.addEntry(BAD_DOTTED,getBadDotted());
        mess_.addEntry(BAD_PARAM_NAME,getBadParamName());
        mess_.addEntry(RESERVED_PARAM_NAME,getReservedParamName());
        mess_.addEntry(DUPLICATED_PARAM_NAME,getDuplicatedParamName());
        mess_.addEntry(BAD_RETURN_TYPE_INHERIT,getBadReturnTypeInherit());
        mess_.addEntry(BAD_RETURN_TYPE_INDEXER,getBadReturnTypeIndexer());
        mess_.addEntry(DUPLICATED_OVERRIDING,getDuplicatedOverriding());
        mess_.addEntry(TWO_FINAL,getTwoFinal());
        mess_.addEntry(FINAL_NOT_SUB_RETURN_TYPE,getFinalNotSubReturnType());
        mess_.addEntry(TWO_RETURN_TYPES,getTwoReturnTypes());
        mess_.addEntry(RETURN_TYPES,getReturnTypes());
        mess_.addEntry(BAD_VARIABLE_NAME,getBadVariableName());
        mess_.addEntry(CYCLIC_CTOR_CALL,getCyclicCtorCall());
        mess_.addEntry(DEAD_CODE,getDeadCode());
        mess_.addEntry(DUPLICATED_CTOR,getDuplicatedCtor());
        mess_.addEntry(DUPLICATED_GENERIC_SUPER_TYPES,getDuplicatedGenericSuperTypes());
        mess_.addEntry(DUPLICATED_INNER_TYPE,getDuplicatedInnerType());
        mess_.addEntry(DUPLICATED_TYPE,getDuplicatedType());
        mess_.addEntry(DUPLICATED_TYPE_PRIM,getDuplicatedTypePrim());
        mess_.addEntry(DUPLICATED_TYPE_STD,getDuplicatedTypeStd());
        mess_.addEntry(DUPLICATED_TYPE_PKG,getDuplicatedTypePkg());
        mess_.addEntry(EMPTY_EXPRESSION_PART,getEmptyExpressionPart());
        mess_.addEntry(DO_WHILE_NOT_EMPTY,getDoWhileNotEmpty());
        mess_.addEntry(DUPLICATED_FINAL,getDuplicatedFinal());
        mess_.addEntry(ILLEGAL_CTOR_ENUM,getIllegalCtorEnum());
        mess_.addEntry(ILLEGAL_GENERIC_SUPER_TYPE_BOUND,getIllegalGenericSuperTypeBound());
        mess_.addEntry(ILLEGAL_CTOR_ANNOTATION,getIllegalCtorAnnotation());
        mess_.addEntry(ILLEGAL_CTOR_ABSTRACT,getIllegalCtorAbstract());
        mess_.addEntry(ILLEGAL_CTOR_BOUND,getIllegalCtorBound());
        mess_.addEntry(ILLEGAL_CTOR_ARRAY,getIllegalCtorArray());
        mess_.addEntry(ILLEGAL_CTOR_UNKNOWN,getIllegalCtorUnknown());
        mess_.addEntry(MISSING_ABRUPT,getMissingAbrupt());
        mess_.addEntry(NOT_INIT_CLASS,getNotInitClass());
        mess_.addEntry(NULL_VALUE,getNullValue());
        mess_.addEntry(BAD_PARAME_TYPE_FOR_ID,getBadParameTypeForId());
        mess_.addEntry(NOT_RESOLVED_OWNER,getNotResolvedOwner());
        mess_.addEntry(UNDEFINED_ACCESSIBLE_FIELD,getUndefinedAccessibleField());
        mess_.addEntry(STATIC_ACCESS,getStaticAccess());
        mess_.addEntry(STATIC_ACCESS_PREV,getStaticAccessPrev());
        mess_.addEntry(UNASSIGNED_FINAL_FIELD,getUnassignedFinalField());
        mess_.addEntry(UNASSIGNED_INFERING_TYPE,getUnassignedInferingType());
        mess_.addEntry(UNDEFINED_CTOR,getUndefinedCtor());
        mess_.addEntry(UNDEFINED_METHOD,getUndefinedMethod());
        mess_.addEntry(ARRAY_CLONE_ONLY,getArrayCloneOnly());
        mess_.addEntry(UNDEFINED_SUPER_CTOR,getUndefinedSuperCtor());
        mess_.addEntry(UNDEFINED_SUPER_CTOR_CALL,getUndefinedSuperCtorCall());
        mess_.addEntry(UNDEFINED_VARIABLE,getUndefinedVariable());
        mess_.addEntry(UNEXPECTED_AFFECT,getUnexpectedAffect());
        mess_.addEntry(FINAL_FIELD,getFinalField());
        mess_.addEntry(BAD_OPERATOR_REF,getBadOperatorRef());
        mess_.addEntry(UNEXPECTED_CATCH_ELSE_FINALLY,getUnexpectedCatchElseFinally());
        mess_.addEntry(UNEXPECTED_ABRUPT,getUnexpectedAbrupt());
        mess_.addEntry(UNEXPECTED_ABRUPT_LAB,getUnexpectedAbruptLab());
        mess_.addEntry(UNEXPECTED_CASE_DEF,getUnexpectedCaseDef());
        mess_.addEntry(UNEXPECTED_CASE_VAR,getUnexpectedCaseVar());
        mess_.addEntry(UNEXPECTED_CASE_VALUE,getUnexpectedCaseValue());
        mess_.addEntry(UNEXPECTED_CASE_DUP,getUnexpectedCaseDup());
        mess_.addEntry(UNEXPECTED_DEF_DUP,getUnexpectedDefDup());
        mess_.addEntry(UNEXPECTED_DO_TRY,getUnexpectedDoTry());
        mess_.addEntry(UNEXPECTED_SWITCH,getUnexpectedSwitch());
        mess_.addEntry(UNEXPECTED_MEMBER_INST,getUnexpectedMemberInst());
        mess_.addEntry(UNEXPECTED_BLOCK_EXP,getUnexpectedBlockExp());
        mess_.addEntry(UNEXPECTED_OPERAND_TYPES,getUnexpectedOperandTypes());
        mess_.addEntry(UNKNOWN_TYPE,getUnknownType());
        mess_.addEntry(BAD_PARAMERIZED_TYPE,getBadParamerizedType());
        mess_.addEntry(UNEXPECTED_TYPE_BOUND,getUnexpectedTypeBound());
        mess_.addEntry(UNEXPECTED_VARARG,getUnexpectedVararg());
        mess_.addEntry(UNEXPECTED_LEAF,getUnexpectedLeaf());
        mess_.addEntry(EMPTY_PART,getEmptyPart());
        return mess_;
    }

    public String getEmptyWord() {
        return emptyWord;
    }

    public void setEmptyWord(String emptyWord) {
        this.emptyWord = emptyWord;
    }

    public String getNotWordChar() {
        return notWordChar;
    }

    public void setNotWordChar(String notWordChar) {
        this.notWordChar = notWordChar;
    }

    public String getDigitFirst() {
        return digitFirst;
    }

    public void setDigitFirst(String digitFirst) {
        this.digitFirst = digitFirst;
    }

    public String getEmptyString() {
        return emptyString;
    }

    public void setEmptyString(String emptyString) {
        this.emptyString = emptyString;
    }

    public String getEmptyNb() {
        return emptyNb;
    }

    public void setEmptyNb(String emptyNb) {
        this.emptyNb = emptyNb;
    }

    public String getEmptyBinExp() {
        return emptyBinExp;
    }

    public void setEmptyBinExp(String emptyBinExp) {
        this.emptyBinExp = emptyBinExp;
    }

    public String getEmptyPreBin() {
        return emptyPreBin;
    }

    public void setEmptyPreBin(String emptyPreBin) {
        this.emptyPreBin = emptyPreBin;
    }

    public String getEmptyPreHex() {
        return emptyPreHex;
    }

    public void setEmptyPreHex(String emptyPreHex) {
        this.emptyPreHex = emptyPreHex;
    }

    public String getIllegalChar() {
        return illegalChar;
    }

    public void setIllegalChar(String illegalChar) {
        this.illegalChar = illegalChar;
    }

    public String getIllegalFirstChar() {
        return illegalFirstChar;
    }

    public void setIllegalFirstChar(String illegalFirstChar) {
        this.illegalFirstChar = illegalFirstChar;
    }

    public String getEmptyPrimitive() {
        return emptyPrimitive;
    }

    public void setEmptyPrimitive(String emptyPrimitive) {
        this.emptyPrimitive = emptyPrimitive;
    }

    public String getNotWordCharPrimitive() {
        return notWordCharPrimitive;
    }

    public void setNotWordCharPrimitive(String notWordCharPrimitive) {
        this.notWordCharPrimitive = notWordCharPrimitive;
    }

    public String getPrimitiveKeyWord() {
        return primitiveKeyWord;
    }

    public void setPrimitiveKeyWord(String primitiveKeyWord) {
        this.primitiveKeyWord = primitiveKeyWord;
    }

    public String getDigitFirstPrimitive() {
        return digitFirstPrimitive;
    }

    public void setDigitFirstPrimitive(String digitFirstPrimitive) {
        this.digitFirstPrimitive = digitFirstPrimitive;
    }

    public String getEmptyRefType() {
        return emptyRefType;
    }

    public void setEmptyRefType(String emptyRefType) {
        this.emptyRefType = emptyRefType;
    }

    public String getEmptyRefTypeIn() {
        return emptyRefTypeIn;
    }

    public void setEmptyRefTypeIn(String emptyRefTypeIn) {
        this.emptyRefTypeIn = emptyRefTypeIn;
    }

    public String getNotWordCharRefType() {
        return notWordCharRefType;
    }

    public void setNotWordCharRefType(String notWordCharRefType) {
        this.notWordCharRefType = notWordCharRefType;
    }

    public String getRefTypeKeyWord() {
        return refTypeKeyWord;
    }

    public void setRefTypeKeyWord(String refTypeKeyWord) {
        this.refTypeKeyWord = refTypeKeyWord;
    }

    public String getRefTypePrimitive() {
        return refTypePrimitive;
    }

    public void setRefTypePrimitive(String refTypePrimitive) {
        this.refTypePrimitive = refTypePrimitive;
    }

    public String getDigitFirstRefType() {
        return digitFirstRefType;
    }

    public void setDigitFirstRefType(String digitFirstRefType) {
        this.digitFirstRefType = digitFirstRefType;
    }

    public String getEmptyPkgRefType() {
        return emptyPkgRefType;
    }

    public void setEmptyPkgRefType(String emptyPkgRefType) {
        this.emptyPkgRefType = emptyPkgRefType;
    }

    public String getDefaultPkgRefType() {
        return defaultPkgRefType;
    }

    public void setDefaultPkgRefType(String defaultPkgRefType) {
        this.defaultPkgRefType = defaultPkgRefType;
    }

    public String getDefaultPkgNoMatch() {
        return defaultPkgNoMatch;
    }

    public void setDefaultPkgNoMatch(String defaultPkgNoMatch) {
        this.defaultPkgNoMatch = defaultPkgNoMatch;
    }

    public String getEmptyMethod() {
        return emptyMethod;
    }

    public void setEmptyMethod(String emptyMethod) {
        this.emptyMethod = emptyMethod;
    }

    public String getNotWordCharMethod() {
        return notWordCharMethod;
    }

    public void setNotWordCharMethod(String notWordCharMethod) {
        this.notWordCharMethod = notWordCharMethod;
    }

    public String getMethodKeyWord() {
        return methodKeyWord;
    }

    public void setMethodKeyWord(String methodKeyWord) {
        this.methodKeyWord = methodKeyWord;
    }

    public String getMethodPrimitive() {
        return methodPrimitive;
    }

    public void setMethodPrimitive(String methodPrimitive) {
        this.methodPrimitive = methodPrimitive;
    }

    public String getDigitFirstMethod() {
        return digitFirstMethod;
    }

    public void setDigitFirstMethod(String digitFirstMethod) {
        this.digitFirstMethod = digitFirstMethod;
    }

    public String getEmptyField() {
        return emptyField;
    }

    public void setEmptyField(String emptyField) {
        this.emptyField = emptyField;
    }

    public String getNotWordCharField() {
        return notWordCharField;
    }

    public void setNotWordCharField(String notWordCharField) {
        this.notWordCharField = notWordCharField;
    }

    public String getFieldKeyWord() {
        return fieldKeyWord;
    }

    public void setFieldKeyWord(String fieldKeyWord) {
        this.fieldKeyWord = fieldKeyWord;
    }

    public String getFieldPrimitive() {
        return fieldPrimitive;
    }

    public void setFieldPrimitive(String fieldPrimitive) {
        this.fieldPrimitive = fieldPrimitive;
    }

    public String getDigitFirstField() {
        return digitFirstField;
    }

    public void setDigitFirstField(String digitFirstField) {
        this.digitFirstField = digitFirstField;
    }

    public String getEmptyVarType() {
        return emptyVarType;
    }

    public void setEmptyVarType(String emptyVarType) {
        this.emptyVarType = emptyVarType;
    }

    public String getNotWordCharVarType() {
        return notWordCharVarType;
    }

    public void setNotWordCharVarType(String notWordCharVarType) {
        this.notWordCharVarType = notWordCharVarType;
    }

    public String getVarTypeKeyWord() {
        return varTypeKeyWord;
    }

    public void setVarTypeKeyWord(String varTypeKeyWord) {
        this.varTypeKeyWord = varTypeKeyWord;
    }

    public String getVarTypePrimitive() {
        return varTypePrimitive;
    }

    public void setVarTypePrimitive(String varTypePrimitive) {
        this.varTypePrimitive = varTypePrimitive;
    }

    public String getDigitFirstVarType() {
        return digitFirstVarType;
    }

    public void setDigitFirstVarType(String digitFirstVarType) {
        this.digitFirstVarType = digitFirstVarType;
    }

    public String getDuplicateKeyWord() {
        return duplicateKeyWord;
    }

    public void setDuplicateKeyWord(String duplicateKeyWord) {
        this.duplicateKeyWord = duplicateKeyWord;
    }

    public String getDuplicateStringWord() {
        return duplicateStringWord;
    }

    public void setDuplicateStringWord(String duplicateStringWord) {
        this.duplicateStringWord = duplicateStringWord;
    }

    public String getDuplicateStarting() {
        return duplicateStarting;
    }

    public void setDuplicateStarting(String duplicateStarting) {
        this.duplicateStarting = duplicateStarting;
    }

    public String getDuplicateStartingUni() {
        return duplicateStartingUni;
    }

    public void setDuplicateStartingUni(String duplicateStartingUni) {
        this.duplicateStartingUni = duplicateStartingUni;
    }

    public String getDuplicateNumberWord() {
        return duplicateNumberWord;
    }

    public void setDuplicateNumberWord(String duplicateNumberWord) {
        this.duplicateNumberWord = duplicateNumberWord;
    }

    public String getDuplicateStartingNb() {
        return duplicateStartingNb;
    }

    public void setDuplicateStartingNb(String duplicateStartingNb) {
        this.duplicateStartingNb = duplicateStartingNb;
    }

    public String getDuplicatePrimtive() {
        return duplicatePrimtive;
    }

    public void setDuplicatePrimtive(String duplicatePrimtive) {
        this.duplicatePrimtive = duplicatePrimtive;
    }

    public String getDuplicateRefType() {
        return duplicateRefType;
    }

    public void setDuplicateRefType(String duplicateRefType) {
        this.duplicateRefType = duplicateRefType;
    }

    public String getDuplicateMethod() {
        return duplicateMethod;
    }

    public void setDuplicateMethod(String duplicateMethod) {
        this.duplicateMethod = duplicateMethod;
    }

    public String getDuplicateField() {
        return duplicateField;
    }

    public void setDuplicateField(String duplicateField) {
        this.duplicateField = duplicateField;
    }

    public String getDuplicateVarType() {
        return duplicateVarType;
    }

    public void setDuplicateVarType(String duplicateVarType) {
        this.duplicateVarType = duplicateVarType;
    }

    public String getDuplicateMergedMethod() {
        return duplicateMergedMethod;
    }

    public void setDuplicateMergedMethod(String duplicateMergedMethod) {
        this.duplicateMergedMethod = duplicateMergedMethod;
    }

    public String getAbstractMethodBody() {
        return abstractMethodBody;
    }

    public void setAbstractMethodBody(String abstractMethodBody) {
        this.abstractMethodBody = abstractMethodBody;
    }

    public String getAbstractMethodConc() {
        return abstractMethodConc;
    }

    public void setAbstractMethodConc(String abstractMethodConc) {
        this.abstractMethodConc = abstractMethodConc;
    }

    public String getAbstractMethodImpl() {
        return abstractMethodImpl;
    }

    public void setAbstractMethodImpl(String abstractMethodImpl) {
        this.abstractMethodImpl = abstractMethodImpl;
    }

    public String getAbstractMethodRef() {
        return abstractMethodRef;
    }

    public void setAbstractMethodRef(String abstractMethodRef) {
        this.abstractMethodRef = abstractMethodRef;
    }

    public String getInaccessibleType() {
        return inaccessibleType;
    }

    public void setInaccessibleType(String inaccessibleType) {
        this.inaccessibleType = inaccessibleType;
    }

    public String getUnexpectedType() {
        return unexpectedType;
    }

    public void setUnexpectedType(String unexpectedType) {
        this.unexpectedType = unexpectedType;
    }

    public String getMethodsAccesses() {
        return methodsAccesses;
    }

    public void setMethodsAccesses(String methodsAccesses) {
        this.methodsAccesses = methodsAccesses;
    }

    public String getEmptyPackage() {
        return emptyPackage;
    }

    public void setEmptyPackage(String emptyPackage) {
        this.emptyPackage = emptyPackage;
    }

    public String getEmptyPartClassName() {
        return emptyPartClassName;
    }

    public void setEmptyPartClassName(String emptyPartClassName) {
        this.emptyPartClassName = emptyPartClassName;
    }

    public String getBadPartClassName() {
        return badPartClassName;
    }

    public void setBadPartClassName(String badPartClassName) {
        this.badPartClassName = badPartClassName;
    }

    public String getBadPartVarClassName() {
        return badPartVarClassName;
    }

    public void setBadPartVarClassName(String badPartVarClassName) {
        this.badPartVarClassName = badPartVarClassName;
    }

    public String getCallCtorEnd() {
        return callCtorEnd;
    }

    public void setCallCtorEnd(String callCtorEnd) {
        this.callCtorEnd = callCtorEnd;
    }

    public String getCallCtor() {
        return callCtor;
    }

    public void setCallCtor(String callCtor) {
        this.callCtor = callCtor;
    }

    public String getCallCtorBeforeBlock() {
        return callCtorBeforeBlock;
    }

    public void setCallCtorBeforeBlock(String callCtorBeforeBlock) {
        this.callCtorBeforeBlock = callCtorBeforeBlock;
    }

    public String getCallCtorFirstLine() {
        return callCtorFirstLine;
    }

    public void setCallCtorFirstLine(String callCtorFirstLine) {
        this.callCtorFirstLine = callCtorFirstLine;
    }

    public String getCallCtorIntFromSuperInt() {
        return callCtorIntFromSuperInt;
    }

    public void setCallCtorIntFromSuperInt(String callCtorIntFromSuperInt) {
        this.callCtorIntFromSuperInt = callCtorIntFromSuperInt;
    }

    public String getCallCtorIntNotFromInt() {
        return callCtorIntNotFromInt;
    }

    public void setCallCtorIntNotFromInt(String callCtorIntNotFromInt) {
        this.callCtorIntNotFromInt = callCtorIntNotFromInt;
    }

    public String getCallCtorIntAfterSuperThis() {
        return callCtorIntAfterSuperThis;
    }

    public void setCallCtorIntAfterSuperThis(String callCtorIntAfterSuperThis) {
        this.callCtorIntAfterSuperThis = callCtorIntAfterSuperThis;
    }

    public String getCallCtorIntInherits() {
        return callCtorIntInherits;
    }

    public void setCallCtorIntInherits(String callCtorIntInherits) {
        this.callCtorIntInherits = callCtorIntInherits;
    }

    public String getCallCtorSuperClassEnumSingleton() {
        return callCtorSuperClassEnumSingleton;
    }

    public void setCallCtorSuperClassEnumSingleton(String callCtorSuperClassEnumSingleton) {
        this.callCtorSuperClassEnumSingleton = callCtorSuperClassEnumSingleton;
    }

    public String getAnnotFieldNotUniq() {
        return annotFieldNotUniq;
    }

    public void setAnnotFieldNotUniq(String annotFieldNotUniq) {
        this.annotFieldNotUniq = annotFieldNotUniq;
    }

    public String getAnnotFieldMust() {
        return annotFieldMust;
    }

    public void setAnnotFieldMust(String annotFieldMust) {
        this.annotFieldMust = annotFieldMust;
    }

    public String getDupSuppliedAnnotField() {
        return dupSuppliedAnnotField;
    }

    public void setDupSuppliedAnnotField(String dupSuppliedAnnotField) {
        this.dupSuppliedAnnotField = dupSuppliedAnnotField;
    }

    public String getBadExpression() {
        return badExpression;
    }

    public void setBadExpression(String badExpression) {
        this.badExpression = badExpression;
    }

    public String getBadFieldName() {
        return badFieldName;
    }

    public void setBadFieldName(String badFieldName) {
        this.badFieldName = badFieldName;
    }

    public String getNotRetrievedFields() {
        return notRetrievedFields;
    }

    public void setNotRetrievedFields(String notRetrievedFields) {
        this.notRetrievedFields = notRetrievedFields;
    }

    public String getBadNbFormat() {
        return badNbFormat;
    }

    public void setBadNbFormat(String badNbFormat) {
        this.badNbFormat = badNbFormat;
    }

    public String getBadCharFormat() {
        return badCharFormat;
    }

    public void setBadCharFormat(String badCharFormat) {
        this.badCharFormat = badCharFormat;
    }

    public String getBadImplicitCast() {
        return badImplicitCast;
    }

    public void setBadImplicitCast(String badImplicitCast) {
        this.badImplicitCast = badImplicitCast;
    }

    public String getNotPrimitiveWrapper() {
        return notPrimitiveWrapper;
    }

    public void setNotPrimitiveWrapper(String notPrimitiveWrapper) {
        this.notPrimitiveWrapper = notPrimitiveWrapper;
    }

    public String getVoidType() {
        return voidType;
    }

    public void setVoidType(String voidType) {
        this.voidType = voidType;
    }

    public String getBadIndexInParser() {
        return badIndexInParser;
    }

    public void setBadIndexInParser(String badIndexInParser) {
        this.badIndexInParser = badIndexInParser;
    }

    public String getIllegalCharacter() {
        return illegalCharacter;
    }

    public void setIllegalCharacter(String illegalCharacter) {
        this.illegalCharacter = illegalCharacter;
    }

    public String getCallIntInherits() {
        return callIntInherits;
    }

    public void setCallIntInherits(String callIntInherits) {
        this.callIntInherits = callIntInherits;
    }

    public String getCallIntNoNeed() {
        return callIntNoNeed;
    }

    public void setCallIntNoNeed(String callIntNoNeed) {
        this.callIntNoNeed = callIntNoNeed;
    }

    public String getCallIntNoNeedType() {
        return callIntNoNeedType;
    }

    public void setCallIntNoNeedType(String callIntNoNeedType) {
        this.callIntNoNeedType = callIntNoNeedType;
    }

    public String getCallIntNeedType() {
        return callIntNeedType;
    }

    public void setCallIntNeedType(String callIntNeedType) {
        this.callIntNeedType = callIntNeedType;
    }

    public String getCallIntOnly() {
        return callIntOnly;
    }

    public void setCallIntOnly(String callIntOnly) {
        this.callIntOnly = callIntOnly;
    }

    public String getBadInheritsType() {
        return badInheritsType;
    }

    public void setBadInheritsType(String badInheritsType) {
        this.badInheritsType = badInheritsType;
    }

    public String getBadInheritsTypeInn() {
        return badInheritsTypeInn;
    }

    public void setBadInheritsTypeInn(String badInheritsTypeInn) {
        this.badInheritsTypeInn = badInheritsTypeInn;
    }

    public String getBadInheritsTypeAsInn() {
        return badInheritsTypeAsInn;
    }

    public void setBadInheritsTypeAsInn(String badInheritsTypeAsInn) {
        this.badInheritsTypeAsInn = badInheritsTypeAsInn;
    }

    public String getBadInheritsTypeInt() {
        return badInheritsTypeInt;
    }

    public void setBadInheritsTypeInt(String badInheritsTypeInt) {
        this.badInheritsTypeInt = badInheritsTypeInt;
    }

    public String getFinalType() {
        return finalType;
    }

    public void setFinalType(String finalType) {
        this.finalType = finalType;
    }

    public String getDuplicateSuper() {
        return duplicateSuper;
    }

    public void setDuplicateSuper(String duplicateSuper) {
        this.duplicateSuper = duplicateSuper;
    }

    public String getReservedType() {
        return reservedType;
    }

    public void setReservedType(String reservedType) {
        this.reservedType = reservedType;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String getUnknownSuperType() {
        return unknownSuperType;
    }

    public void setUnknownSuperType(String unknownSuperType) {
        this.unknownSuperType = unknownSuperType;
    }

    public String getCyclicInherits() {
        return cyclicInherits;
    }

    public void setCyclicInherits(String cyclicInherits) {
        this.cyclicInherits = cyclicInherits;
    }

    public String getAnnotationParam() {
        return annotationParam;
    }

    public void setAnnotationParam(String annotationParam) {
        this.annotationParam = annotationParam;
    }

    public String getCyclicMapping() {
        return cyclicMapping;
    }

    public void setCyclicMapping(String cyclicMapping) {
        this.cyclicMapping = cyclicMapping;
    }

    public String getAbsMapping() {
        return absMapping;
    }

    public void setAbsMapping(String absMapping) {
        this.absMapping = absMapping;
    }

    public String getFinalMapping() {
        return finalMapping;
    }

    public void setFinalMapping(String finalMapping) {
        this.finalMapping = finalMapping;
    }

    public String getMustCallIntCtor() {
        return mustCallIntCtor;
    }

    public void setMustCallIntCtor(String mustCallIntCtor) {
        this.mustCallIntCtor = mustCallIntCtor;
    }

    public String getMustNotCallIntCtorAfterThis() {
        return mustNotCallIntCtorAfterThis;
    }

    public void setMustNotCallIntCtorAfterThis(String mustNotCallIntCtorAfterThis) {
        this.mustNotCallIntCtorAfterThis = mustNotCallIntCtorAfterThis;
    }

    public String getMustCallIntCtorNeed() {
        return mustCallIntCtorNeed;
    }

    public void setMustCallIntCtorNeed(String mustCallIntCtorNeed) {
        this.mustCallIntCtorNeed = mustCallIntCtorNeed;
    }

    public String getMustCallIntCtorNotNeed() {
        return mustCallIntCtorNotNeed;
    }

    public void setMustCallIntCtorNotNeed(String mustCallIntCtorNotNeed) {
        this.mustCallIntCtorNotNeed = mustCallIntCtorNotNeed;
    }

    public String getBadLabel() {
        return badLabel;
    }

    public void setBadLabel(String badLabel) {
        this.badLabel = badLabel;
    }

    public String getDuplicatedLabel() {
        return duplicatedLabel;
    }

    public void setDuplicatedLabel(String duplicatedLabel) {
        this.duplicatedLabel = duplicatedLabel;
    }

    public String getBadMethodName() {
        return badMethodName;
    }

    public void setBadMethodName(String badMethodName) {
        this.badMethodName = badMethodName;
    }

    public String getBadOperatorName() {
        return badOperatorName;
    }

    public void setBadOperatorName(String badOperatorName) {
        this.badOperatorName = badOperatorName;
    }

    public String getBadAccess() {
        return badAccess;
    }

    public void setBadAccess(String badAccess) {
        this.badAccess = badAccess;
    }

    public String getBadReturnType() {
        return badReturnType;
    }

    public void setBadReturnType(String badReturnType) {
        this.badReturnType = badReturnType;
    }

    public String getBadParams() {
        return badParams;
    }

    public void setBadParams(String badParams) {
        this.badParams = badParams;
    }

    public String getBadMethodModifier() {
        return badMethodModifier;
    }

    public void setBadMethodModifier(String badMethodModifier) {
        this.badMethodModifier = badMethodModifier;
    }

    public String getBadMethodVararg() {
        return badMethodVararg;
    }

    public void setBadMethodVararg(String badMethodVararg) {
        this.badMethodVararg = badMethodVararg;
    }

    public String getBadIndexerParams() {
        return badIndexerParams;
    }

    public void setBadIndexerParams(String badIndexerParams) {
        this.badIndexerParams = badIndexerParams;
    }

    public String getBadIndexerModifier() {
        return badIndexerModifier;
    }

    public void setBadIndexerModifier(String badIndexerModifier) {
        this.badIndexerModifier = badIndexerModifier;
    }

    public String getBadIndexerModifiers() {
        return badIndexerModifiers;
    }

    public void setBadIndexerModifiers(String badIndexerModifiers) {
        this.badIndexerModifiers = badIndexerModifiers;
    }

    public String getBadIndexerAccesses() {
        return badIndexerAccesses;
    }

    public void setBadIndexerAccesses(String badIndexerAccesses) {
        this.badIndexerAccesses = badIndexerAccesses;
    }

    public String getBadIndexerPairGet() {
        return badIndexerPairGet;
    }

    public void setBadIndexerPairGet(String badIndexerPairGet) {
        this.badIndexerPairGet = badIndexerPairGet;
    }

    public String getBadIndexerPairSet() {
        return badIndexerPairSet;
    }

    public void setBadIndexerPairSet(String badIndexerPairSet) {
        this.badIndexerPairSet = badIndexerPairSet;
    }

    public String getDuplicateCustomMethod() {
        return duplicateCustomMethod;
    }

    public void setDuplicateCustomMethod(String duplicateCustomMethod) {
        this.duplicateCustomMethod = duplicateCustomMethod;
    }

    public String getReservedCustomMethod() {
        return reservedCustomMethod;
    }

    public void setReservedCustomMethod(String reservedCustomMethod) {
        this.reservedCustomMethod = reservedCustomMethod;
    }

    public String getDuplicateIndexer() {
        return duplicateIndexer;
    }

    public void setDuplicateIndexer(String duplicateIndexer) {
        this.duplicateIndexer = duplicateIndexer;
    }

    public String getDuplicateOperator() {
        return duplicateOperator;
    }

    public void setDuplicateOperator(String duplicateOperator) {
        this.duplicateOperator = duplicateOperator;
    }

    public String getFunctionalApplyNbDiff() {
        return functionalApplyNbDiff;
    }

    public void setFunctionalApplyNbDiff(String functionalApplyNbDiff) {
        this.functionalApplyNbDiff = functionalApplyNbDiff;
    }

    public String getFunctionalApplyOnly() {
        return functionalApplyOnly;
    }

    public void setFunctionalApplyOnly(String functionalApplyOnly) {
        this.functionalApplyOnly = functionalApplyOnly;
    }

    public String getOperatorNbDiff() {
        return operatorNbDiff;
    }

    public void setOperatorNbDiff(String operatorNbDiff) {
        this.operatorNbDiff = operatorNbDiff;
    }

    public String getSplitComa() {
        return splitComa;
    }

    public void setSplitComa(String splitComa) {
        this.splitComa = splitComa;
    }

    public String getSplitComaLow() {
        return splitComaLow;
    }

    public void setSplitComaLow(String splitComaLow) {
        this.splitComaLow = splitComaLow;
    }

    public String getSplitDiff() {
        return splitDiff;
    }

    public void setSplitDiff(String splitDiff) {
        this.splitDiff = splitDiff;
    }

    public String getBadDotted() {
        return badDotted;
    }

    public void setBadDotted(String badDotted) {
        this.badDotted = badDotted;
    }

    public String getBadParamName() {
        return badParamName;
    }

    public void setBadParamName(String badParamName) {
        this.badParamName = badParamName;
    }

    public String getReservedParamName() {
        return reservedParamName;
    }

    public void setReservedParamName(String reservedParamName) {
        this.reservedParamName = reservedParamName;
    }

    public String getDuplicatedParamName() {
        return duplicatedParamName;
    }

    public void setDuplicatedParamName(String duplicatedParamName) {
        this.duplicatedParamName = duplicatedParamName;
    }

    public String getBadReturnTypeInherit() {
        return badReturnTypeInherit;
    }

    public void setBadReturnTypeInherit(String badReturnTypeInherit) {
        this.badReturnTypeInherit = badReturnTypeInherit;
    }

    public String getBadReturnTypeIndexer() {
        return badReturnTypeIndexer;
    }

    public void setBadReturnTypeIndexer(String badReturnTypeIndexer) {
        this.badReturnTypeIndexer = badReturnTypeIndexer;
    }

    public String getDuplicatedOverriding() {
        return duplicatedOverriding;
    }

    public void setDuplicatedOverriding(String duplicatedOverriding) {
        this.duplicatedOverriding = duplicatedOverriding;
    }

    public String getTwoFinal() {
        return twoFinal;
    }

    public void setTwoFinal(String twoFinal) {
        this.twoFinal = twoFinal;
    }

    public String getFinalNotSubReturnType() {
        return finalNotSubReturnType;
    }

    public void setFinalNotSubReturnType(String finalNotSubReturnType) {
        this.finalNotSubReturnType = finalNotSubReturnType;
    }

    public String getTwoReturnTypes() {
        return twoReturnTypes;
    }

    public void setTwoReturnTypes(String twoReturnTypes) {
        this.twoReturnTypes = twoReturnTypes;
    }

    public String getReturnTypes() {
        return returnTypes;
    }

    public void setReturnTypes(String returnTypes) {
        this.returnTypes = returnTypes;
    }

    public String getBadVariableName() {
        return badVariableName;
    }

    public void setBadVariableName(String badVariableName) {
        this.badVariableName = badVariableName;
    }

    public String getCyclicCtorCall() {
        return cyclicCtorCall;
    }

    public void setCyclicCtorCall(String cyclicCtorCall) {
        this.cyclicCtorCall = cyclicCtorCall;
    }

    public String getDeadCode() {
        return deadCode;
    }

    public void setDeadCode(String deadCode) {
        this.deadCode = deadCode;
    }

    public String getDuplicatedCtor() {
        return duplicatedCtor;
    }

    public void setDuplicatedCtor(String duplicatedCtor) {
        this.duplicatedCtor = duplicatedCtor;
    }

    public String getDuplicatedGenericSuperTypes() {
        return duplicatedGenericSuperTypes;
    }

    public void setDuplicatedGenericSuperTypes(String duplicatedGenericSuperTypes) {
        this.duplicatedGenericSuperTypes = duplicatedGenericSuperTypes;
    }

    public String getDuplicatedInnerType() {
        return duplicatedInnerType;
    }

    public void setDuplicatedInnerType(String duplicatedInnerType) {
        this.duplicatedInnerType = duplicatedInnerType;
    }

    public String getDuplicatedType() {
        return duplicatedType;
    }

    public void setDuplicatedType(String duplicatedType) {
        this.duplicatedType = duplicatedType;
    }

    public String getDuplicatedTypePrim() {
        return duplicatedTypePrim;
    }

    public void setDuplicatedTypePrim(String duplicatedTypePrim) {
        this.duplicatedTypePrim = duplicatedTypePrim;
    }

    public String getDuplicatedTypeStd() {
        return duplicatedTypeStd;
    }

    public void setDuplicatedTypeStd(String duplicatedTypeStd) {
        this.duplicatedTypeStd = duplicatedTypeStd;
    }

    public String getDuplicatedTypePkg() {
        return duplicatedTypePkg;
    }

    public void setDuplicatedTypePkg(String duplicatedTypePkg) {
        this.duplicatedTypePkg = duplicatedTypePkg;
    }

    public String getEmptyExpressionPart() {
        return emptyExpressionPart;
    }

    public void setEmptyExpressionPart(String emptyExpressionPart) {
        this.emptyExpressionPart = emptyExpressionPart;
    }

    public String getDoWhileNotEmpty() {
        return doWhileNotEmpty;
    }

    public void setDoWhileNotEmpty(String doWhileNotEmpty) {
        this.doWhileNotEmpty = doWhileNotEmpty;
    }

    public String getDuplicatedFinal() {
        return duplicatedFinal;
    }

    public void setDuplicatedFinal(String duplicatedFinal) {
        this.duplicatedFinal = duplicatedFinal;
    }

    public String getIllegalCtorEnum() {
        return illegalCtorEnum;
    }

    public void setIllegalCtorEnum(String illegalCtorEnum) {
        this.illegalCtorEnum = illegalCtorEnum;
    }

    public String getIllegalGenericSuperTypeBound() {
        return illegalGenericSuperTypeBound;
    }

    public void setIllegalGenericSuperTypeBound(String illegalGenericSuperTypeBound) {
        this.illegalGenericSuperTypeBound = illegalGenericSuperTypeBound;
    }

    public String getIllegalCtorAnnotation() {
        return illegalCtorAnnotation;
    }

    public void setIllegalCtorAnnotation(String illegalCtorAnnotation) {
        this.illegalCtorAnnotation = illegalCtorAnnotation;
    }

    public String getIllegalCtorAbstract() {
        return illegalCtorAbstract;
    }

    public void setIllegalCtorAbstract(String illegalCtorAbstract) {
        this.illegalCtorAbstract = illegalCtorAbstract;
    }

    public String getIllegalCtorBound() {
        return illegalCtorBound;
    }

    public void setIllegalCtorBound(String illegalCtorBound) {
        this.illegalCtorBound = illegalCtorBound;
    }

    public String getIllegalCtorArray() {
        return illegalCtorArray;
    }

    public void setIllegalCtorArray(String illegalCtorArray) {
        this.illegalCtorArray = illegalCtorArray;
    }

    public String getIllegalCtorUnknown() {
        return illegalCtorUnknown;
    }

    public void setIllegalCtorUnknown(String illegalCtorUnknown) {
        this.illegalCtorUnknown = illegalCtorUnknown;
    }

    public String getMissingAbrupt() {
        return missingAbrupt;
    }

    public void setMissingAbrupt(String missingAbrupt) {
        this.missingAbrupt = missingAbrupt;
    }

    public String getNotInitClass() {
        return notInitClass;
    }

    public void setNotInitClass(String notInitClass) {
        this.notInitClass = notInitClass;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    public String getBadParameTypeForId() {
        return badParameTypeForId;
    }

    public void setBadParameTypeForId(String badParameTypeForId) {
        this.badParameTypeForId = badParameTypeForId;
    }

    public String getNotResolvedOwner() {
        return notResolvedOwner;
    }

    public void setNotResolvedOwner(String notResolvedOwner) {
        this.notResolvedOwner = notResolvedOwner;
    }

    public String getUndefinedAccessibleField() {
        return undefinedAccessibleField;
    }

    public void setUndefinedAccessibleField(String undefinedAccessibleField) {
        this.undefinedAccessibleField = undefinedAccessibleField;
    }

    public String getStaticAccess() {
        return staticAccess;
    }

    public void setStaticAccess(String staticAccess) {
        this.staticAccess = staticAccess;
    }

    public String getStaticAccessPrev() {
        return staticAccessPrev;
    }

    public void setStaticAccessPrev(String staticAccessPrev) {
        this.staticAccessPrev = staticAccessPrev;
    }

    public String getUnassignedFinalField() {
        return unassignedFinalField;
    }

    public void setUnassignedFinalField(String unassignedFinalField) {
        this.unassignedFinalField = unassignedFinalField;
    }

    public String getUnassignedInferingType() {
        return unassignedInferingType;
    }

    public void setUnassignedInferingType(String unassignedInferingType) {
        this.unassignedInferingType = unassignedInferingType;
    }

    public String getUndefinedCtor() {
        return undefinedCtor;
    }

    public void setUndefinedCtor(String undefinedCtor) {
        this.undefinedCtor = undefinedCtor;
    }

    public String getUndefinedMethod() {
        return undefinedMethod;
    }

    public void setUndefinedMethod(String undefinedMethod) {
        this.undefinedMethod = undefinedMethod;
    }

    public String getArrayCloneOnly() {
        return arrayCloneOnly;
    }

    public void setArrayCloneOnly(String arrayCloneOnly) {
        this.arrayCloneOnly = arrayCloneOnly;
    }

    public String getUndefinedSuperCtor() {
        return undefinedSuperCtor;
    }

    public void setUndefinedSuperCtor(String undefinedSuperCtor) {
        this.undefinedSuperCtor = undefinedSuperCtor;
    }

    public String getUndefinedSuperCtorCall() {
        return undefinedSuperCtorCall;
    }

    public void setUndefinedSuperCtorCall(String undefinedSuperCtorCall) {
        this.undefinedSuperCtorCall = undefinedSuperCtorCall;
    }

    public String getUndefinedVariable() {
        return undefinedVariable;
    }

    public void setUndefinedVariable(String undefinedVariable) {
        this.undefinedVariable = undefinedVariable;
    }

    public String getUnexpectedAffect() {
        return unexpectedAffect;
    }

    public void setUnexpectedAffect(String unexpectedAffect) {
        this.unexpectedAffect = unexpectedAffect;
    }

    public String getFinalField() {
        return finalField;
    }

    public void setFinalField(String finalField) {
        this.finalField = finalField;
    }

    public String getBadOperatorRef() {
        return badOperatorRef;
    }

    public void setBadOperatorRef(String badOperatorRef) {
        this.badOperatorRef = badOperatorRef;
    }

    public String getUnexpectedCatchElseFinally() {
        return unexpectedCatchElseFinally;
    }

    public void setUnexpectedCatchElseFinally(String unexpectedCatchElseFinally) {
        this.unexpectedCatchElseFinally = unexpectedCatchElseFinally;
    }

    public String getUnexpectedAbrupt() {
        return unexpectedAbrupt;
    }

    public void setUnexpectedAbrupt(String unexpectedAbrupt) {
        this.unexpectedAbrupt = unexpectedAbrupt;
    }

    public String getUnexpectedAbruptLab() {
        return unexpectedAbruptLab;
    }

    public void setUnexpectedAbruptLab(String unexpectedAbruptLab) {
        this.unexpectedAbruptLab = unexpectedAbruptLab;
    }

    public String getUnexpectedCaseDef() {
        return unexpectedCaseDef;
    }

    public void setUnexpectedCaseDef(String unexpectedCaseDef) {
        this.unexpectedCaseDef = unexpectedCaseDef;
    }

    public String getUnexpectedCaseVar() {
        return unexpectedCaseVar;
    }

    public void setUnexpectedCaseVar(String unexpectedCaseVar) {
        this.unexpectedCaseVar = unexpectedCaseVar;
    }

    public String getUnexpectedCaseValue() {
        return unexpectedCaseValue;
    }

    public void setUnexpectedCaseValue(String unexpectedCaseValue) {
        this.unexpectedCaseValue = unexpectedCaseValue;
    }

    public String getUnexpectedCaseDup() {
        return unexpectedCaseDup;
    }

    public void setUnexpectedCaseDup(String unexpectedCaseDup) {
        this.unexpectedCaseDup = unexpectedCaseDup;
    }

    public String getUnexpectedDefDup() {
        return unexpectedDefDup;
    }

    public void setUnexpectedDefDup(String unexpectedDefDup) {
        this.unexpectedDefDup = unexpectedDefDup;
    }

    public String getUnexpectedDoTry() {
        return unexpectedDoTry;
    }

    public void setUnexpectedDoTry(String unexpectedDoTry) {
        this.unexpectedDoTry = unexpectedDoTry;
    }

    public String getUnexpectedSwitch() {
        return unexpectedSwitch;
    }

    public void setUnexpectedSwitch(String unexpectedSwitch) {
        this.unexpectedSwitch = unexpectedSwitch;
    }

    public String getUnexpectedMemberInst() {
        return unexpectedMemberInst;
    }

    public void setUnexpectedMemberInst(String unexpectedMemberInst) {
        this.unexpectedMemberInst = unexpectedMemberInst;
    }

    public String getUnexpectedBlockExp() {
        return unexpectedBlockExp;
    }

    public void setUnexpectedBlockExp(String unexpectedBlockExp) {
        this.unexpectedBlockExp = unexpectedBlockExp;
    }

    public String getUnexpectedOperandTypes() {
        return unexpectedOperandTypes;
    }

    public void setUnexpectedOperandTypes(String unexpectedOperandTypes) {
        this.unexpectedOperandTypes = unexpectedOperandTypes;
    }

    public String getUnknownType() {
        return unknownType;
    }

    public void setUnknownType(String unknownType) {
        this.unknownType = unknownType;
    }

    public String getBadParamerizedType() {
        return badParamerizedType;
    }

    public void setBadParamerizedType(String badParamerizedType) {
        this.badParamerizedType = badParamerizedType;
    }

    public String getUnexpectedTypeBound() {
        return unexpectedTypeBound;
    }

    public void setUnexpectedTypeBound(String unexpectedTypeBound) {
        this.unexpectedTypeBound = unexpectedTypeBound;
    }

    public String getUnexpectedVararg() {
        return unexpectedVararg;
    }

    public void setUnexpectedVararg(String unexpectedVararg) {
        this.unexpectedVararg = unexpectedVararg;
    }

    public String getUnexpectedLeaf() {
        return unexpectedLeaf;
    }

    public void setUnexpectedLeaf(String unexpectedLeaf) {
        this.unexpectedLeaf = unexpectedLeaf;
    }

    public String getEmptyPart() {
        return emptyPart;
    }

    public void setEmptyPart(String _emptyPart) {
        emptyPart = _emptyPart;
    }
}
