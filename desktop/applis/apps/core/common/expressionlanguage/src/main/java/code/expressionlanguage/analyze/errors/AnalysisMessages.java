package code.expressionlanguage.analyze.errors;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.options.WarningShow;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnalysisMessages {
    public static final String DUPLICATE_MERGED_METHOD="0";
    public static final String DUPLICATE_FIELD="1";
    public static final String DUPLICATE_VAR_TYPE="2";
    public static final String EMPTY_PKG_REF_TYPE="3";
    public static final String PRIMITIVE_KEY_WORD="4";
    public static final String DEFAULT_PKG_REF_TYPE="5";
    public static final String REF_TYPE_KEY_WORD="6";
    public static final String DIGIT_FIRST_PRIMITIVE="7";
    public static final String ILLEGAL_FIRST_CHAR="8";
    public static final String EMPTY_PRIMITIVE="9";
    public static final String NOT_WORD_CHAR_PRIMITIVE="10";
    public static final String NOT_WORD_CHAR_REF_TYPE="11";
    public static final String EMPTY_REF_TYPE_IN="12";
    public static final String REF_TYPE_PRIMITIVE="13";
    public static final String DIGIT_FIRST_REF_TYPE="14";
    public static final String DUPLICATE_PRIMTIVE="15";
    public static final String DUPLICATE_METHOD="16";
    public static final String DEFAULT_PKG_NO_MATCH="17";
    public static final String DUPLICATE_REF_TYPE="18";
    public static final String DIGIT_FIRST_METHOD="19";
    public static final String NOT_WORD_CHAR_FIELD="20";
    public static final String NOT_WORD_CHAR_METHOD="21";
    public static final String VAR_TYPE_KEY_WORD="22";
    public static final String VAR_TYPE_PRIMITIVE="23";
    public static final String DIGIT_FIRST_VAR_TYPE="24";
    public static final String DUPLICATE_NUMBER_WORD="25";
    public static final String METHOD_PRIMITIVE="26";
    public static final String FIELD_PRIMITIVE="27";
    public static final String DUPLICATE_STRING_WORD="28";
    public static final String DUPLICATE_KEY_WORD="29";
    public static final String DIGIT_FIRST_FIELD="30";
    public static final String DUPLICATE_STARTING_NB="31";
    public static final String DUPLICATE_STARTING_UNI="32";
    public static final String DUPLICATE_STARTING="33";
    public static final String NOT_WORD_CHAR_VAR_TYPE="34";
    public static final String EMPTY_PRE_BIN="35";
    public static final String EMPTY_VAR_TYPE="36";
    public static final String EMPTY_WORD="37";
    public static final String EMPTY_FIELD="38";
    public static final String EMPTY_NB="39";
    public static final String NOT_WORD_CHAR="40";
    public static final String EMPTY_PRE_HEX="41";
    public static final String EMPTY_METHOD="42";
    public static final String DIGIT_FIRST="43";
    public static final String EMPTY_BIN_EXP="44";
    public static final String EMPTY_STRING="45";
    public static final String ILLEGAL_CHAR="46";
    public static final String METHOD_KEY_WORD="47";
    public static final String EMPTY_REF_TYPE="48";
    public static final String FIELD_KEY_WORD="49";
    public static final String ABSTRACT_METHOD_BODY="50";
    public static final String ABSTRACT_METHOD_CONC="51";
    public static final String ABSTRACT_METHOD_IMPL="52";
    public static final String ABSTRACT_METHOD_REF="53";
    public static final String INACCESSIBLE_TYPE="54";
    public static final String UNEXPECTED_TYPE="55";
    public static final String UNEXPECTED_RET_TYPE="56";
    public static final String METHODS_ACCESSES="57";
    public static final String EMPTY_PACKAGE="58";
    public static final String EMPTY_PART_CLASS_NAME="59";
    public static final String BAD_PART_CLASS_NAME="60";
    public static final String KEY_WORD_PART_CLASS_NAME="61";
    public static final String PRIM_PART_CLASS_NAME="62";
    public static final String DIGIT_PART_CLASS_NAME="63";
    public static final String BAD_PART_VAR_CLASS_NAME="64";
    public static final String KEY_WORD_PART_VAR_CLASS_NAME="65";
    public static final String PRIM_PART_VAR_CLASS_NAME="66";
    public static final String DIGIT_PART_VAR_CLASS_NAME="67";
    public static final String DUPLICATED_PART_VAR_CLASS_NAME="68";
    public static final String CALL_CTOR_END="69";
    public static final String CALL_CTOR="70";
    public static final String CALL_CTOR_BEFORE_BLOCK="71";
    public static final String CALL_CTOR_FIRST_LINE="72";
    public static final String CALL_CTOR_INT_FROM_SUPER_INT="73";
    public static final String CALL_CTOR_INT_NOT_FROM_INT="74";
    public static final String CALL_CTOR_INT_AFTER_SUPER_THIS="75";
    public static final String CALL_CTOR_INT_INHERITS="76";
    public static final String CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON="77";
    public static final String CALL_CTOR_NO_SUPER_CLASS_ENUM="78";
    public static final String ANNOT_FIELD_NOT_UNIQ="79";
    public static final String ANNOT_FIELD_MUST="80";
    public static final String DUP_SUPPLIED_ANNOT_FIELD="81";
    public static final String BAD_EXPRESSION="82";
    public static final String BAD_FIELD_NAME="83";
    public static final String KEY_WORD_FIELD_NAME="84";
    public static final String PRIM_FIELD_NAME="85";
    public static final String DIGIT_FIELD_NAME="86";
    public static final String NOT_RETRIEVED_FIELDS="87";
    public static final String BAD_NB_FORMAT="88";
    public static final String BAD_CHAR_FORMAT="89";
    public static final String BAD_IMPLICIT_CAST="90";
    public static final String NOT_PRIMITIVE_WRAPPER="91";
    public static final String VOID_TYPE="92";
    public static final String BAD_INDEX_IN_PARSER="93";
    public static final String ILLEGAL_CHARACTER="94";
    public static final String CALL_INT_INHERITS="95";
    public static final String CALL_INT_NO_NEED="96";
    public static final String CALL_INT_NO_NEED_TYPE="97";
    public static final String CALL_INT_NEED_TYPE="98";
    public static final String CALL_INT_ONLY="99";
    public static final String BAD_INHERITS_TYPE="100";
    public static final String BAD_INHERITS_TYPE_INN="101";
    public static final String BAD_INHERITS_TYPE_AS_INN="102";
    public static final String BAD_INHERITS_TYPE_INT="103";
    public static final String FINAL_TYPE="104";
    public static final String DUPLICATE_SUPER="105";
    public static final String RESERVED_TYPE="106";
    public static final String SUPER_CLASS="107";
    public static final String UNKNOWN_SUPER_TYPE="108";
    public static final String CYCLIC_INHERITS="109";
    public static final String ANNOTATION_PARAM="110";
    public static final String CYCLIC_MAPPING="111";
    public static final String ABS_MAPPING="112";
    public static final String FINAL_MAPPING="113";
    public static final String MUST_CALL_INT_CTOR="114";
    public static final String MUST_NOT_CALL_INT_CTOR_AFTER_THIS="115";
    public static final String MUST_CALL_INT_CTOR_NEED="116";
    public static final String MUST_CALL_INT_CTOR_NOT_NEED="117";
    public static final String BAD_LABEL="118";
    public static final String DUPLICATED_LABEL="119";
    public static final String BAD_METHOD_NAME="120";
    public static final String KEY_WORD_METHOD_NAME="121";
    public static final String PRIM_METHOD_NAME="122";
    public static final String DIGIT_METHOD_NAME="123";
    public static final String BAD_OPERATOR_NAME="124";
    public static final String BAD_ACCESS="125";
    public static final String BAD_RETURN_TYPE="126";
    public static final String BAD_PARAMS="127";
    public static final String BAD_METHOD_MODIFIER="128";
    public static final String BAD_METHOD_VARARG="129";
    public static final String BAD_INDEXER_PARAMS="130";
    public static final String BAD_INDEXER_MODIFIER="131";
    public static final String BAD_INDEXER_MODIFIERS="132";
    public static final String BAD_INDEXER_ACCESSES="133";
    public static final String BAD_INDEXER_PAIR_GET="134";
    public static final String BAD_INDEXER_PAIR_SET="135";
    public static final String DUPLICATE_CUSTOM_METHOD="136";
    public static final String RESERVED_CUSTOM_METHOD="137";
    public static final String DUPLICATE_INDEXER="138";
    public static final String DUPLICATE_OPERATOR="139";
    public static final String FUNCTIONAL_APPLY_NB_DIFF="140";
    public static final String FUNCTIONAL_APPLY_ONLY="141";
    public static final String OPERATOR_NB_DIFF="142";
    public static final String SPLIT_COMA="143";
    public static final String SPLIT_COMA_LOW="144";
    public static final String SPLIT_DIFF="145";
    public static final String BAD_DOTTED="146";
    public static final String BAD_PARAM_NAME="147";
    public static final String KEY_WORD_PARAM_NAME="148";
    public static final String PRIM_PARAM_NAME="149";
    public static final String DIGIT_PARAM_NAME="150";
    public static final String RESERVED_PARAM_NAME="151";
    public static final String DUPLICATED_PARAM_NAME="152";
    public static final String BAD_RETURN_TYPE_INHERIT="153";
    public static final String BAD_RETURN_TYPE_INDEXER="154";
    public static final String DUPLICATED_OVERRIDING="155";
    public static final String TWO_FINAL="156";
    public static final String FINAL_NOT_SUB_RETURN_TYPE="157";
    public static final String TWO_RETURN_TYPES="158";
    public static final String RETURN_TYPES="159";
    public static final String BAD_VARIABLE_NAME="160";
    public static final String KEY_WORD_VARIABLE_NAME="161";
    public static final String PRIM_VARIABLE_NAME="162";
    public static final String DIGIT_VARIABLE_NAME="163";
    public static final String DUPLICATED_VARIABLE_NAME="164";
    public static final String CYCLIC_CTOR_CALL="165";
    public static final String DEAD_CODE="166";
    public static final String DEAD_CODE_TERNARY="167";
    public static final String UNUSED_PARAM_STATIC="168";
    public static final String DUPLICATED_CTOR="169";
    public static final String DUPLICATED_GENERIC_SUPER_TYPES="170";
    public static final String DUPLICATED_INNER_TYPE="171";
    public static final String DUPLICATED_TYPE="172";
    public static final String DUPLICATED_TYPE_PRIM="173";
    public static final String DUPLICATED_TYPE_STD="174";
    public static final String DUPLICATED_TYPE_PKG="175";
    public static final String EMPTY_EXPRESSION_PART="176";
    public static final String DO_WHILE_NOT_EMPTY="177";
    public static final String DUPLICATED_FINAL="178";
    public static final String ILLEGAL_CTOR_ENUM="179";
    public static final String ILLEGAL_GENERIC_SUPER_TYPE_BOUND="180";
    public static final String ILLEGAL_CTOR_ANNOTATION="181";
    public static final String ILLEGAL_CTOR_ABSTRACT="182";
    public static final String ILLEGAL_CTOR_BOUND="183";
    public static final String ILLEGAL_CTOR_ARRAY="184";
    public static final String ILLEGAL_CTOR_UNKNOWN="185";
    public static final String MISSING_ABRUPT="186";
    public static final String NOT_INIT_CLASS="187";
    public static final String NULL_VALUE="188";
    public static final String BAD_PARAME_TYPE_FOR_ID="189";
    public static final String NOT_RESOLVED_OWNER="190";
    public static final String UNDEFINED_ACCESSIBLE_FIELD="191";
    public static final String STATIC_ACCESS="192";
    public static final String STATIC_ACCESS_PREV="193";
    public static final String UNASSIGNED_FINAL_FIELD="194";
    public static final String UNASSIGNED_INFERING_TYPE="195";
    public static final String UNDEFINED_CTOR="196";
    public static final String UNDEFINED_METHOD="197";
    public static final String ARRAY_CLONE_ONLY="198";
    public static final String UNDEFINED_SUPER_CTOR="199";
    public static final String UNDEFINED_SUPER_CTOR_CALL="200";
    public static final String UNDEFINED_VARIABLE="201";
    public static final String UNEXPECTED_AFFECT="202";
    public static final String FINAL_FIELD="203";
    public static final String BAD_OPERATOR_REF="204";
    public static final String UNEXPECTED_CATCH_ELSE_FINALLY="205";
    public static final String UNEXPECTED_ABRUPT="206";
    public static final String UNEXPECTED_ABRUPT_LAB="207";
    public static final String UNEXPECTED_CASE_DEF="208";
    public static final String UNEXPECTED_CASE_VAR="209";
    public static final String UNEXPECTED_CASE_VALUE="210";
    public static final String UNEXPECTED_CASE_DUP="211";
    public static final String UNEXPECTED_DEF_DUP="212";
    public static final String UNEXPECTED_DO_TRY="213";
    public static final String UNEXPECTED_SWITCH="214";
    public static final String UNEXPECTED_MEMBER_INST="215";
    public static final String UNEXPECTED_BLOCK_EXP="216";
    public static final String UNEXPECTED_OPERAND_TYPES="217";
    public static final String UNKNOWN_TYPE="218";
    public static final String EMPTY_TYPE="219";
    public static final String BAD_PARAMERIZED_TYPE="220";
    public static final String UNEXPECTED_TYPE_BOUND="221";
    public static final String UNEXPECTED_VARARG="222";
    public static final String UNEXPECTED_LEAF="223";
    public static final String CASE_TYPE_VAR="224";
    public static final String EMPTY_PART="225";
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
    private String notWordCharPrimitive = "{0} contains a character {1} that is not a character of a word (primitive).";
    private String primitiveKeyWord = "the primitive {0}:{1} is a duplicate string of a key word.";
    private String digitFirstPrimitive = "{0} starts with {1} that is digit (primitive).";

    private String emptyRefType = "{0} has an empty reference type value.";
    private String emptyRefTypeIn = "{0} has an empty reference string between dots {1}.";
    private String notWordCharRefType = "{0} contains a character {1} that is not a character of a word (reference type).";
    private String refTypeKeyWord = "the reference type {0}:{1} contains a duplicate string of a key word.";
    private String refTypePrimitive = "the reference type {0}:{1} contains a duplicate string of a primitive.";
    private String digitFirstRefType = "{0} starts with {1} that is digit (reference type).";
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
    private String unexpectedRetType="The return type {0} in the function {1} is unexpected.";
    private String methodsAccesses="The method {1} from the type {0} is strictly more accessible than the method {3} from the type {2}.";
    private String emptyPackage="A type must have an non empty package.";
    private String emptyPartClassName="The part must not be empty.";
    private String badPartClassName="The part {0} in a type is not valid. It must be a word.";
    private String keyWordPartClassName="The part {0} in a type is not valid. It must not be a key word.";
    private String primPartClassName="The part {0} in a type is not valid. It must not a primitive type.";
    private String digitPartClassName="The part {0} in a type is not valid. It must not start with a digit.";
    private String badPartVarClassName="The part {0} in a variable type is not valid. It must be a word.";
    private String keyWordPartVarClassName="The part {0} in a variable type is not valid. It must not be a key word.";
    private String primPartVarClassName="The part {0} in a type variable is not valid. It must not a primitive type.";
    private String digitPartVarClassName="The part {0} in a type variable is not valid. It must not start with a digit.";
    private String duplicatedPartVarClassName="The part {0} in a type variable is duplicated.";
    private String callCtorEnd="The call of a constructor using implicitly the instance must be applied at the end of the instruction.";
    private String callCtor="The call of a constructor using implicitly the instance must be applied in a constructor.";
    private String callCtorBeforeBlock="The call of a constructor using implicitly the instance must be applied before a block of instructions.";
    private String callCtorFirstLine="The call of a constructor of the type or the super class using implicitly the instance must be applied on the first line.";
    private String callCtorIntFromSuperInt="The call of a constructor of interface must refer a super interface of the calling type.";
    private String callCtorIntNotFromInt="The call of a constructor of interface must not applied in a constructor of interface.";
    private String callCtorIntAfterSuperThis="A call of a constructor of interface must be applied only after a call of a constructor using implicitly the instance.";
    private String callCtorIntInherits="The call of a constructor of the interface {0} cannot be applied before calling the constructor of the interface {1}.";
    private String callCtorSuperClassEnumSingleton="The super constructor can be called only from a class or an enum (singleton or normal).";
    private String callCtorNoSuperClassEnum="There is no super custom type to be called from this class or an enum (singleton or normal).";
    private String annotFieldNotUniq="The field of the annotatation could not be found uniquely.";
    private String annotFieldMust="The field {0} of the annotatation is compulsory.";
    private String dupSuppliedAnnotField="The field {0} of the annotatation is supplied by duplicate.";
    private String badExpression="Unexpected character {0} at the position {1} in the expression {2}";
    private String badFieldName="The field name {0} is not valid. It must be a word.";
    private String keyWordFieldName="The field name {0} is not valid. It must not be a key word.";
    private String primFieldName="The field name {0} is not valid. It must not a primitive type.";
    private String digitFieldName="The field name {0} is not valid. It must not start with a digit.";
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
    private String badMethodName="The method name {0} is not valid. It must be a word.";
    private String keyWordMethodName="The method name {0} is not valid. It must not be a key word.";
    private String primMethodName="The method name {0} is not valid. It must not a primitive type.";
    private String digitMethodName="The method name {0} is not valid. It must not start with a digit.";
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
    private String badParamName="The parameter method name {0} is not valid. It must be a word.";
    private String reservedParamName="The parameter method name {0} is reserved for indexer set.";
    private String duplicatedParamName="The parameter function name {0} is duplicated.";
    private String keyWordParamName="The parameter function name {0} is not valid. It must not be a key word.";
    private String primParamName="The parameter function name {0} is not valid. It must not a primitive type.";
    private String digitParamName="The parameter function name {0} is not valid. It must not start with a digit.";
    private String badReturnTypeInherit="The return type {0} of the method {1} of the type {2} is not sub type of the return type {3} of the method {4} of the type {5}";
    private String badReturnTypeIndexer="The return type {0} of the indexer {1} of the type {2} is not the return type {3} of the indexer {4} of the type {5}";
    private String duplicatedOverriding="The type {0} inherits a function {1} that is duplicated.";
    private String twoFinal="The type {0} inherits two final functions with key {1}.";
    private String finalNotSubReturnType="The return type {0} of the final method {1} of the type {2} is not sub type of the return type {3} of the method {4} of the type {5}";
    private String returnTypes="The indexers with key {0} of the types {1} have the types {2} as return types.";
    private String twoReturnTypes="The merged methods with key {0} of the types {1} have the sub types {2} as return types.";
    private String badVariableName="The variable name {0} is not valid. It must be a word.";
    private String keyWordVariableName="The variable name {0} is not valid. It must not be a key word.";
    private String primVariableName="The variable name {0} is not valid. It must not a primitive type.";
    private String digitVariableName="The variable name {0} is not valid. It must not start with a digit.";
    private String duplicatedVariableName="The variable name {0} is not valid. It must not be the name of an other variable of the scope.";
    private String cyclicCtorCall="The constructors {0} of the type {1} belong to cyclic calls.";
    private String deadCode="The code is unreachable in the function {0}";
    private String deadCodeTernary="A part of code is unreachable in this ternary operation.";
    private String unusedParamStatic="The parameter {0} is unused.";
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
    private String emptyType="There must be a type.";
    private String badParamerizedType="The type {0} is not parameterized correctly.";
    private String unexpectedTypeBound="The type {0} is unexpected for bound.";
    private String unexpectedVararg="The three dots are unexpected here.";
    private String unexpectedLeaf="The key word {0} is unexpected here.";
    private String caseTypeVar="This case block must be constant.";
    private String emptyPart="There must be an expression.";

    public static void validateMessageContents(StringMap<String> _list, AnalyzedPageEl _page) {
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                _page.addMessageError(key_);
            }
        }
    }
    public static WarningShow build(StringList _optionsWarn, StringMap<String> _mapping) {
        WarningShow w_ = new WarningShow();
        w_.setUnusedParameterStaticMethod(StringUtil.contains(_optionsWarn, _mapping.getVal(UNUSED_PARAM_STATIC)));
        w_.setTernary(StringUtil.contains(_optionsWarn, _mapping.getVal(DEAD_CODE_TERNARY)));
        return w_;
    }

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setDuplicateMergedMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_MERGED_METHOD)));
        setDuplicateField(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_FIELD)));
        setDuplicateVarType(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_VAR_TYPE)));
        setEmptyPkgRefType(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_PKG_REF_TYPE)));
        setPrimitiveKeyWord(LgNamesContent.get(_util, _cust, _mapping.getVal(PRIMITIVE_KEY_WORD)));
        setDefaultPkgRefType(LgNamesContent.get(_util, _cust, _mapping.getVal(DEFAULT_PKG_REF_TYPE)));
        setRefTypeKeyWord(LgNamesContent.get(_util, _cust, _mapping.getVal(REF_TYPE_KEY_WORD)));
        setDigitFirstPrimitive(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_FIRST_PRIMITIVE)));
        setIllegalFirstChar(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_FIRST_CHAR)));
        setEmptyPrimitive(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_PRIMITIVE)));
        setNotWordCharPrimitive(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_WORD_CHAR_PRIMITIVE)));
        setNotWordCharRefType(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_WORD_CHAR_REF_TYPE)));
        setEmptyRefTypeIn(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_REF_TYPE_IN)));
        setRefTypePrimitive(LgNamesContent.get(_util, _cust, _mapping.getVal(REF_TYPE_PRIMITIVE)));
        setDigitFirstRefType(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_FIRST_REF_TYPE)));
        setDuplicatePrimtive(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_PRIMTIVE)));
        setDuplicateMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_METHOD)));
        setDefaultPkgNoMatch(LgNamesContent.get(_util, _cust, _mapping.getVal(DEFAULT_PKG_NO_MATCH)));
        setDuplicateRefType(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_REF_TYPE)));
        setDigitFirstMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_FIRST_METHOD)));
        setNotWordCharField(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_WORD_CHAR_FIELD)));
        setNotWordCharMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_WORD_CHAR_METHOD)));
        setVarTypeKeyWord(LgNamesContent.get(_util, _cust, _mapping.getVal(VAR_TYPE_KEY_WORD)));
        setVarTypePrimitive(LgNamesContent.get(_util, _cust, _mapping.getVal(VAR_TYPE_PRIMITIVE)));
        setDigitFirstVarType(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_FIRST_VAR_TYPE)));
        setDuplicateNumberWord(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_NUMBER_WORD)));
        setMethodPrimitive(LgNamesContent.get(_util, _cust, _mapping.getVal(METHOD_PRIMITIVE)));
        setFieldPrimitive(LgNamesContent.get(_util, _cust, _mapping.getVal(FIELD_PRIMITIVE)));
        setDuplicateStringWord(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_STRING_WORD)));
        setDuplicateKeyWord(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_KEY_WORD)));
        setDigitFirstField(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_FIRST_FIELD)));
        setDuplicateStartingNb(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_STARTING_NB)));
        setDuplicateStartingUni(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_STARTING_UNI)));
        setDuplicateStarting(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_STARTING)));
        setNotWordCharVarType(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_WORD_CHAR_VAR_TYPE)));
        setEmptyPreBin(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_PRE_BIN)));
        setEmptyVarType(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_VAR_TYPE)));
        setEmptyWord(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_WORD)));
        setEmptyField(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_FIELD)));
        setEmptyNb(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_NB)));
        setNotWordChar(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_WORD_CHAR)));
        setEmptyPreHex(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_PRE_HEX)));
        setEmptyMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_METHOD)));
        setDigitFirst(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_FIRST)));
        setEmptyBinExp(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_BIN_EXP)));
        setEmptyString(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_STRING)));
        setIllegalChar(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_CHAR)));
        setMethodKeyWord(LgNamesContent.get(_util, _cust, _mapping.getVal(METHOD_KEY_WORD)));
        setEmptyRefType(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_REF_TYPE)));
        setFieldKeyWord(LgNamesContent.get(_util, _cust, _mapping.getVal(FIELD_KEY_WORD)));
        setAbstractMethodBody(LgNamesContent.get(_util, _cust, _mapping.getVal(ABSTRACT_METHOD_BODY)));
        setAbstractMethodConc(LgNamesContent.get(_util, _cust, _mapping.getVal(ABSTRACT_METHOD_CONC)));
        setAbstractMethodImpl(LgNamesContent.get(_util, _cust, _mapping.getVal(ABSTRACT_METHOD_IMPL)));
        setAbstractMethodRef(LgNamesContent.get(_util, _cust, _mapping.getVal(ABSTRACT_METHOD_REF)));
        setInaccessibleType(LgNamesContent.get(_util, _cust, _mapping.getVal(INACCESSIBLE_TYPE)));
        setUnexpectedType(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_TYPE)));
        setUnexpectedRetType(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_RET_TYPE)));
        setMethodsAccesses(LgNamesContent.get(_util, _cust, _mapping.getVal(METHODS_ACCESSES)));
        setEmptyPackage(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_PACKAGE)));
        setEmptyPartClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_PART_CLASS_NAME)));
        setBadPartClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_PART_CLASS_NAME)));
        setKeyWordPartClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(KEY_WORD_PART_CLASS_NAME)));
        setPrimPartClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(PRIM_PART_CLASS_NAME)));
        setDigitPartClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_PART_CLASS_NAME)));
        setBadPartVarClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_PART_VAR_CLASS_NAME)));
        setKeyWordPartVarClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(KEY_WORD_PART_VAR_CLASS_NAME)));
        setPrimPartVarClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(PRIM_PART_VAR_CLASS_NAME)));
        setDigitPartVarClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_PART_VAR_CLASS_NAME)));
        setDuplicatedPartVarClassName(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_PART_VAR_CLASS_NAME)));
        setCallCtorEnd(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_END)));
        setCallCtor(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR)));
        setCallCtorBeforeBlock(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_BEFORE_BLOCK)));
        setCallCtorFirstLine(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_FIRST_LINE)));
        setCallCtorIntFromSuperInt(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_INT_FROM_SUPER_INT)));
        setCallCtorIntNotFromInt(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_INT_NOT_FROM_INT)));
        setCallCtorIntAfterSuperThis(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_INT_AFTER_SUPER_THIS)));
        setCallCtorIntInherits(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_INT_INHERITS)));
        setCallCtorSuperClassEnumSingleton(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON)));
        setCallCtorNoSuperClassEnum(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_CTOR_NO_SUPER_CLASS_ENUM)));
        setAnnotFieldNotUniq(LgNamesContent.get(_util, _cust, _mapping.getVal(ANNOT_FIELD_NOT_UNIQ)));
        setAnnotFieldMust(LgNamesContent.get(_util, _cust, _mapping.getVal(ANNOT_FIELD_MUST)));
        setDupSuppliedAnnotField(LgNamesContent.get(_util, _cust, _mapping.getVal(DUP_SUPPLIED_ANNOT_FIELD)));
        setBadExpression(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_EXPRESSION)));
        setBadFieldName(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_FIELD_NAME)));
        setKeyWordFieldName(LgNamesContent.get(_util, _cust, _mapping.getVal(KEY_WORD_FIELD_NAME)));
        setPrimFieldName(LgNamesContent.get(_util, _cust, _mapping.getVal(PRIM_FIELD_NAME)));
        setDigitFieldName(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_FIELD_NAME)));
        setNotRetrievedFields(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_RETRIEVED_FIELDS)));
        setBadNbFormat(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_NB_FORMAT)));
        setBadCharFormat(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_CHAR_FORMAT)));
        setBadImplicitCast(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_IMPLICIT_CAST)));
        setNotPrimitiveWrapper(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_PRIMITIVE_WRAPPER)));
        setVoidType(LgNamesContent.get(_util, _cust, _mapping.getVal(VOID_TYPE)));
        setBadIndexInParser(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INDEX_IN_PARSER)));
        setIllegalCharacter(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_CHARACTER)));
        setCallIntInherits(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_INT_INHERITS)));
        setCallIntNoNeed(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_INT_NO_NEED)));
        setCallIntNoNeedType(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_INT_NO_NEED_TYPE)));
        setCallIntNeedType(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_INT_NEED_TYPE)));
        setCallIntOnly(LgNamesContent.get(_util, _cust, _mapping.getVal(CALL_INT_ONLY)));
        setBadInheritsType(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INHERITS_TYPE)));
        setBadInheritsTypeInn(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INHERITS_TYPE_INN)));
        setBadInheritsTypeAsInn(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INHERITS_TYPE_AS_INN)));
        setBadInheritsTypeInt(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INHERITS_TYPE_INT)));
        setFinalType(LgNamesContent.get(_util, _cust, _mapping.getVal(FINAL_TYPE)));
        setDuplicateSuper(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_SUPER)));
        setReservedType(LgNamesContent.get(_util, _cust, _mapping.getVal(RESERVED_TYPE)));
        setSuperClass(LgNamesContent.get(_util, _cust, _mapping.getVal(SUPER_CLASS)));
        setUnknownSuperType(LgNamesContent.get(_util, _cust, _mapping.getVal(UNKNOWN_SUPER_TYPE)));
        setCyclicInherits(LgNamesContent.get(_util, _cust, _mapping.getVal(CYCLIC_INHERITS)));
        setAnnotationParam(LgNamesContent.get(_util, _cust, _mapping.getVal(ANNOTATION_PARAM)));
        setCyclicMapping(LgNamesContent.get(_util, _cust, _mapping.getVal(CYCLIC_MAPPING)));
        setAbsMapping(LgNamesContent.get(_util, _cust, _mapping.getVal(ABS_MAPPING)));
        setFinalMapping(LgNamesContent.get(_util, _cust, _mapping.getVal(FINAL_MAPPING)));
        setMustCallIntCtor(LgNamesContent.get(_util, _cust, _mapping.getVal(MUST_CALL_INT_CTOR)));
        setMustNotCallIntCtorAfterThis(LgNamesContent.get(_util, _cust, _mapping.getVal(MUST_NOT_CALL_INT_CTOR_AFTER_THIS)));
        setMustCallIntCtorNeed(LgNamesContent.get(_util, _cust, _mapping.getVal(MUST_CALL_INT_CTOR_NEED)));
        setMustCallIntCtorNotNeed(LgNamesContent.get(_util, _cust, _mapping.getVal(MUST_CALL_INT_CTOR_NOT_NEED)));
        setBadLabel(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_LABEL)));
        setDuplicatedLabel(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_LABEL)));
        setBadMethodName(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_METHOD_NAME)));
        setKeyWordMethodName(LgNamesContent.get(_util, _cust, _mapping.getVal(KEY_WORD_METHOD_NAME)));
        setPrimMethodName(LgNamesContent.get(_util, _cust, _mapping.getVal(PRIM_METHOD_NAME)));
        setDigitMethodName(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_METHOD_NAME)));
        setBadOperatorName(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_OPERATOR_NAME)));
        setBadAccess(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_ACCESS)));
        setBadReturnType(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_RETURN_TYPE)));
        setBadParams(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_PARAMS)));
        setBadMethodModifier(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_METHOD_MODIFIER)));
        setBadMethodVararg(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_METHOD_VARARG)));
        setBadIndexerParams(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INDEXER_PARAMS)));
        setBadIndexerModifier(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INDEXER_MODIFIER)));
        setBadIndexerModifiers(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INDEXER_MODIFIERS)));
        setBadIndexerAccesses(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INDEXER_ACCESSES)));
        setBadIndexerPairGet(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INDEXER_PAIR_GET)));
        setBadIndexerPairSet(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INDEXER_PAIR_SET)));
        setDuplicateCustomMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_CUSTOM_METHOD)));
        setReservedCustomMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(RESERVED_CUSTOM_METHOD)));
        setDuplicateIndexer(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_INDEXER)));
        setDuplicateOperator(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATE_OPERATOR)));
        setFunctionalApplyNbDiff(LgNamesContent.get(_util, _cust, _mapping.getVal(FUNCTIONAL_APPLY_NB_DIFF)));
        setFunctionalApplyOnly(LgNamesContent.get(_util, _cust, _mapping.getVal(FUNCTIONAL_APPLY_ONLY)));
        setOperatorNbDiff(LgNamesContent.get(_util, _cust, _mapping.getVal(OPERATOR_NB_DIFF)));
        setSplitComa(LgNamesContent.get(_util, _cust, _mapping.getVal(SPLIT_COMA)));
        setSplitComaLow(LgNamesContent.get(_util, _cust, _mapping.getVal(SPLIT_COMA_LOW)));
        setSplitDiff(LgNamesContent.get(_util, _cust, _mapping.getVal(SPLIT_DIFF)));
        setBadDotted(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_DOTTED)));
        setBadParamName(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_PARAM_NAME)));
        setKeyWordParamName(LgNamesContent.get(_util, _cust, _mapping.getVal(KEY_WORD_PARAM_NAME)));
        setPrimParamName(LgNamesContent.get(_util, _cust, _mapping.getVal(PRIM_PARAM_NAME)));
        setDigitParamName(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_PARAM_NAME)));
        setReservedParamName(LgNamesContent.get(_util, _cust, _mapping.getVal(RESERVED_PARAM_NAME)));
        setDuplicatedParamName(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_PARAM_NAME)));
        setBadReturnTypeInherit(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_RETURN_TYPE_INHERIT)));
        setBadReturnTypeIndexer(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_RETURN_TYPE_INDEXER)));
        setDuplicatedOverriding(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_OVERRIDING)));
        setTwoFinal(LgNamesContent.get(_util, _cust, _mapping.getVal(TWO_FINAL)));
        setFinalNotSubReturnType(LgNamesContent.get(_util, _cust, _mapping.getVal(FINAL_NOT_SUB_RETURN_TYPE)));
        setTwoReturnTypes(LgNamesContent.get(_util, _cust, _mapping.getVal(TWO_RETURN_TYPES)));
        setReturnTypes(LgNamesContent.get(_util, _cust, _mapping.getVal(RETURN_TYPES)));
        setBadVariableName(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_VARIABLE_NAME)));
        setKeyWordVariableName(LgNamesContent.get(_util, _cust, _mapping.getVal(KEY_WORD_VARIABLE_NAME)));
        setPrimVariableName(LgNamesContent.get(_util, _cust, _mapping.getVal(PRIM_VARIABLE_NAME)));
        setDigitVariableName(LgNamesContent.get(_util, _cust, _mapping.getVal(DIGIT_VARIABLE_NAME)));
        setDuplicatedVariableName(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_VARIABLE_NAME)));
        setCyclicCtorCall(LgNamesContent.get(_util, _cust, _mapping.getVal(CYCLIC_CTOR_CALL)));
        setDeadCode(LgNamesContent.get(_util, _cust, _mapping.getVal(DEAD_CODE)));
        setDeadCodeTernary(LgNamesContent.get(_util, _cust, _mapping.getVal(DEAD_CODE_TERNARY)));
        setUnusedParamStatic(LgNamesContent.get(_util, _cust, _mapping.getVal(UNUSED_PARAM_STATIC)));
        setDuplicatedCtor(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_CTOR)));
        setDuplicatedGenericSuperTypes(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_GENERIC_SUPER_TYPES)));
        setDuplicatedInnerType(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_INNER_TYPE)));
        setDuplicatedType(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_TYPE)));
        setDuplicatedTypePrim(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_TYPE_PRIM)));
        setDuplicatedTypeStd(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_TYPE_STD)));
        setDuplicatedTypePkg(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_TYPE_PKG)));
        setEmptyExpressionPart(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_EXPRESSION_PART)));
        setDoWhileNotEmpty(LgNamesContent.get(_util, _cust, _mapping.getVal(DO_WHILE_NOT_EMPTY)));
        setDuplicatedFinal(LgNamesContent.get(_util, _cust, _mapping.getVal(DUPLICATED_FINAL)));
        setIllegalCtorEnum(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_CTOR_ENUM)));
        setIllegalGenericSuperTypeBound(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_GENERIC_SUPER_TYPE_BOUND)));
        setIllegalCtorAnnotation(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_CTOR_ANNOTATION)));
        setIllegalCtorAbstract(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_CTOR_ABSTRACT)));
        setIllegalCtorBound(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_CTOR_BOUND)));
        setIllegalCtorArray(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_CTOR_ARRAY)));
        setIllegalCtorUnknown(LgNamesContent.get(_util, _cust, _mapping.getVal(ILLEGAL_CTOR_UNKNOWN)));
        setMissingAbrupt(LgNamesContent.get(_util, _cust, _mapping.getVal(MISSING_ABRUPT)));
        setNotInitClass(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_INIT_CLASS)));
        setNullValue(LgNamesContent.get(_util, _cust, _mapping.getVal(NULL_VALUE)));
        setBadParameTypeForId(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_PARAME_TYPE_FOR_ID)));
        setNotResolvedOwner(LgNamesContent.get(_util, _cust, _mapping.getVal(NOT_RESOLVED_OWNER)));
        setUndefinedAccessibleField(LgNamesContent.get(_util, _cust, _mapping.getVal(UNDEFINED_ACCESSIBLE_FIELD)));
        setStaticAccess(LgNamesContent.get(_util, _cust, _mapping.getVal(STATIC_ACCESS)));
        setStaticAccessPrev(LgNamesContent.get(_util, _cust, _mapping.getVal(STATIC_ACCESS_PREV)));
        setUnassignedFinalField(LgNamesContent.get(_util, _cust, _mapping.getVal(UNASSIGNED_FINAL_FIELD)));
        setUnassignedInferingType(LgNamesContent.get(_util, _cust, _mapping.getVal(UNASSIGNED_INFERING_TYPE)));
        setUndefinedCtor(LgNamesContent.get(_util, _cust, _mapping.getVal(UNDEFINED_CTOR)));
        setUndefinedMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(UNDEFINED_METHOD)));
        setArrayCloneOnly(LgNamesContent.get(_util, _cust, _mapping.getVal(ARRAY_CLONE_ONLY)));
        setUndefinedSuperCtor(LgNamesContent.get(_util, _cust, _mapping.getVal(UNDEFINED_SUPER_CTOR)));
        setUndefinedSuperCtorCall(LgNamesContent.get(_util, _cust, _mapping.getVal(UNDEFINED_SUPER_CTOR_CALL)));
        setUndefinedVariable(LgNamesContent.get(_util, _cust, _mapping.getVal(UNDEFINED_VARIABLE)));
        setUnexpectedAffect(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_AFFECT)));
        setFinalField(LgNamesContent.get(_util, _cust, _mapping.getVal(FINAL_FIELD)));
        setBadOperatorRef(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_OPERATOR_REF)));
        setUnexpectedCatchElseFinally(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_CATCH_ELSE_FINALLY)));
        setUnexpectedAbrupt(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_ABRUPT)));
        setUnexpectedAbruptLab(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_ABRUPT_LAB)));
        setUnexpectedCaseDef(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_CASE_DEF)));
        setUnexpectedCaseVar(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_CASE_VAR)));
        setUnexpectedCaseValue(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_CASE_VALUE)));
        setUnexpectedCaseDup(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_CASE_DUP)));
        setUnexpectedDefDup(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_DEF_DUP)));
        setUnexpectedDoTry(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_DO_TRY)));
        setUnexpectedSwitch(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_SWITCH)));
        setUnexpectedMemberInst(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_MEMBER_INST)));
        setUnexpectedBlockExp(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_BLOCK_EXP)));
        setUnexpectedOperandTypes(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_OPERAND_TYPES)));
        setUnknownType(LgNamesContent.get(_util, _cust, _mapping.getVal(UNKNOWN_TYPE)));
        setEmptyType(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_TYPE)));
        setBadParamerizedType(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_PARAMERIZED_TYPE)));
        setUnexpectedTypeBound(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_TYPE_BOUND)));
        setUnexpectedVararg(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_VARARG)));
        setUnexpectedLeaf(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_LEAF)));
        setCaseTypeVar(LgNamesContent.get(_util, _cust, _mapping.getVal(CASE_TYPE_VAR)));
        setEmptyPart(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_PART)));
    }
    public StringMap<String> allMessages() {
        return allMessages(mapping());
    }
    public StringMap<String> allMessages(StringMap<String> _mapping) {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry(_mapping.getVal(DUPLICATE_MERGED_METHOD),getDuplicateMergedMethod());
        mess_.addEntry(_mapping.getVal(DUPLICATE_FIELD),getDuplicateField());
        mess_.addEntry(_mapping.getVal(DUPLICATE_VAR_TYPE),getDuplicateVarType());
        mess_.addEntry(_mapping.getVal(EMPTY_PKG_REF_TYPE),getEmptyPkgRefType());
        mess_.addEntry(_mapping.getVal(PRIMITIVE_KEY_WORD),getPrimitiveKeyWord());
        mess_.addEntry(_mapping.getVal(DEFAULT_PKG_REF_TYPE),getDefaultPkgRefType());
        mess_.addEntry(_mapping.getVal(REF_TYPE_KEY_WORD),getRefTypeKeyWord());
        mess_.addEntry(_mapping.getVal(DIGIT_FIRST_PRIMITIVE),getDigitFirstPrimitive());
        mess_.addEntry(_mapping.getVal(ILLEGAL_FIRST_CHAR),getIllegalFirstChar());
        mess_.addEntry(_mapping.getVal(EMPTY_PRIMITIVE),getEmptyPrimitive());
        mess_.addEntry(_mapping.getVal(NOT_WORD_CHAR_PRIMITIVE),getNotWordCharPrimitive());
        mess_.addEntry(_mapping.getVal(NOT_WORD_CHAR_REF_TYPE),getNotWordCharRefType());
        mess_.addEntry(_mapping.getVal(EMPTY_REF_TYPE_IN),getEmptyRefTypeIn());
        mess_.addEntry(_mapping.getVal(REF_TYPE_PRIMITIVE),getRefTypePrimitive());
        mess_.addEntry(_mapping.getVal(DIGIT_FIRST_REF_TYPE),getDigitFirstRefType());
        mess_.addEntry(_mapping.getVal(DUPLICATE_PRIMTIVE),getDuplicatePrimtive());
        mess_.addEntry(_mapping.getVal(DUPLICATE_METHOD),getDuplicateMethod());
        mess_.addEntry(_mapping.getVal(DEFAULT_PKG_NO_MATCH),getDefaultPkgNoMatch());
        mess_.addEntry(_mapping.getVal(DUPLICATE_REF_TYPE),getDuplicateRefType());
        mess_.addEntry(_mapping.getVal(DIGIT_FIRST_METHOD),getDigitFirstMethod());
        mess_.addEntry(_mapping.getVal(NOT_WORD_CHAR_FIELD),getNotWordCharField());
        mess_.addEntry(_mapping.getVal(NOT_WORD_CHAR_METHOD),getNotWordCharMethod());
        mess_.addEntry(_mapping.getVal(VAR_TYPE_KEY_WORD),getVarTypeKeyWord());
        mess_.addEntry(_mapping.getVal(VAR_TYPE_PRIMITIVE),getVarTypePrimitive());
        mess_.addEntry(_mapping.getVal(DIGIT_FIRST_VAR_TYPE),getDigitFirstVarType());
        mess_.addEntry(_mapping.getVal(DUPLICATE_NUMBER_WORD),getDuplicateNumberWord());
        mess_.addEntry(_mapping.getVal(METHOD_PRIMITIVE),getMethodPrimitive());
        mess_.addEntry(_mapping.getVal(FIELD_PRIMITIVE),getFieldPrimitive());
        mess_.addEntry(_mapping.getVal(DUPLICATE_STRING_WORD),getDuplicateStringWord());
        mess_.addEntry(_mapping.getVal(DUPLICATE_KEY_WORD),getDuplicateKeyWord());
        mess_.addEntry(_mapping.getVal(DIGIT_FIRST_FIELD),getDigitFirstField());
        mess_.addEntry(_mapping.getVal(DUPLICATE_STARTING_NB),getDuplicateStartingNb());
        mess_.addEntry(_mapping.getVal(DUPLICATE_STARTING_UNI),getDuplicateStartingUni());
        mess_.addEntry(_mapping.getVal(DUPLICATE_STARTING),getDuplicateStarting());
        mess_.addEntry(_mapping.getVal(NOT_WORD_CHAR_VAR_TYPE),getNotWordCharVarType());
        mess_.addEntry(_mapping.getVal(EMPTY_PRE_BIN),getEmptyPreBin());
        mess_.addEntry(_mapping.getVal(EMPTY_VAR_TYPE),getEmptyVarType());
        mess_.addEntry(_mapping.getVal(EMPTY_WORD),getEmptyWord());
        mess_.addEntry(_mapping.getVal(EMPTY_FIELD),getEmptyField());
        mess_.addEntry(_mapping.getVal(EMPTY_NB),getEmptyNb());
        mess_.addEntry(_mapping.getVal(NOT_WORD_CHAR),getNotWordChar());
        mess_.addEntry(_mapping.getVal(EMPTY_PRE_HEX),getEmptyPreHex());
        mess_.addEntry(_mapping.getVal(EMPTY_METHOD),getEmptyMethod());
        mess_.addEntry(_mapping.getVal(DIGIT_FIRST),getDigitFirst());
        mess_.addEntry(_mapping.getVal(EMPTY_BIN_EXP),getEmptyBinExp());
        mess_.addEntry(_mapping.getVal(EMPTY_STRING),getEmptyString());
        mess_.addEntry(_mapping.getVal(ILLEGAL_CHAR),getIllegalChar());
        mess_.addEntry(_mapping.getVal(METHOD_KEY_WORD),getMethodKeyWord());
        mess_.addEntry(_mapping.getVal(EMPTY_REF_TYPE),getEmptyRefType());
        mess_.addEntry(_mapping.getVal(FIELD_KEY_WORD),getFieldKeyWord());
        mess_.addEntry(_mapping.getVal(ABSTRACT_METHOD_BODY),getAbstractMethodBody());
        mess_.addEntry(_mapping.getVal(ABSTRACT_METHOD_CONC),getAbstractMethodConc());
        mess_.addEntry(_mapping.getVal(ABSTRACT_METHOD_IMPL),getAbstractMethodImpl());
        mess_.addEntry(_mapping.getVal(ABSTRACT_METHOD_REF),getAbstractMethodRef());
        mess_.addEntry(_mapping.getVal(INACCESSIBLE_TYPE),getInaccessibleType());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_TYPE),getUnexpectedType());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_RET_TYPE),getUnexpectedRetType());
        mess_.addEntry(_mapping.getVal(METHODS_ACCESSES),getMethodsAccesses());
        mess_.addEntry(_mapping.getVal(EMPTY_PACKAGE),getEmptyPackage());
        mess_.addEntry(_mapping.getVal(EMPTY_PART_CLASS_NAME),getEmptyPartClassName());
        mess_.addEntry(_mapping.getVal(BAD_PART_CLASS_NAME),getBadPartClassName());
        mess_.addEntry(_mapping.getVal(KEY_WORD_PART_CLASS_NAME),getKeyWordPartClassName());
        mess_.addEntry(_mapping.getVal(PRIM_PART_CLASS_NAME),getPrimPartClassName());
        mess_.addEntry(_mapping.getVal(DIGIT_PART_CLASS_NAME),getDigitPartClassName());
        mess_.addEntry(_mapping.getVal(BAD_PART_VAR_CLASS_NAME),getBadPartVarClassName());
        mess_.addEntry(_mapping.getVal(KEY_WORD_PART_VAR_CLASS_NAME),getKeyWordPartVarClassName());
        mess_.addEntry(_mapping.getVal(PRIM_PART_VAR_CLASS_NAME),getPrimPartVarClassName());
        mess_.addEntry(_mapping.getVal(DIGIT_PART_VAR_CLASS_NAME),getDigitPartVarClassName());
        mess_.addEntry(_mapping.getVal(DUPLICATED_PART_VAR_CLASS_NAME),getDuplicatedPartVarClassName());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_END),getCallCtorEnd());
        mess_.addEntry(_mapping.getVal(CALL_CTOR),getCallCtor());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_BEFORE_BLOCK),getCallCtorBeforeBlock());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_FIRST_LINE),getCallCtorFirstLine());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_INT_FROM_SUPER_INT),getCallCtorIntFromSuperInt());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_INT_NOT_FROM_INT),getCallCtorIntNotFromInt());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_INT_AFTER_SUPER_THIS),getCallCtorIntAfterSuperThis());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_INT_INHERITS),getCallCtorIntInherits());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON),getCallCtorSuperClassEnumSingleton());
        mess_.addEntry(_mapping.getVal(CALL_CTOR_NO_SUPER_CLASS_ENUM),getCallCtorNoSuperClassEnum());
        mess_.addEntry(_mapping.getVal(ANNOT_FIELD_NOT_UNIQ),getAnnotFieldNotUniq());
        mess_.addEntry(_mapping.getVal(ANNOT_FIELD_MUST),getAnnotFieldMust());
        mess_.addEntry(_mapping.getVal(DUP_SUPPLIED_ANNOT_FIELD),getDupSuppliedAnnotField());
        mess_.addEntry(_mapping.getVal(BAD_EXPRESSION),getBadExpression());
        mess_.addEntry(_mapping.getVal(BAD_FIELD_NAME),getBadFieldName());
        mess_.addEntry(_mapping.getVal(KEY_WORD_FIELD_NAME),getKeyWordFieldName());
        mess_.addEntry(_mapping.getVal(PRIM_FIELD_NAME),getPrimFieldName());
        mess_.addEntry(_mapping.getVal(DIGIT_FIELD_NAME),getDigitFieldName());
        mess_.addEntry(_mapping.getVal(NOT_RETRIEVED_FIELDS),getNotRetrievedFields());
        mess_.addEntry(_mapping.getVal(BAD_NB_FORMAT),getBadNbFormat());
        mess_.addEntry(_mapping.getVal(BAD_CHAR_FORMAT),getBadCharFormat());
        mess_.addEntry(_mapping.getVal(BAD_IMPLICIT_CAST),getBadImplicitCast());
        mess_.addEntry(_mapping.getVal(NOT_PRIMITIVE_WRAPPER),getNotPrimitiveWrapper());
        mess_.addEntry(_mapping.getVal(VOID_TYPE),getVoidType());
        mess_.addEntry(_mapping.getVal(BAD_INDEX_IN_PARSER),getBadIndexInParser());
        mess_.addEntry(_mapping.getVal(ILLEGAL_CHARACTER),getIllegalCharacter());
        mess_.addEntry(_mapping.getVal(CALL_INT_INHERITS),getCallIntInherits());
        mess_.addEntry(_mapping.getVal(CALL_INT_NO_NEED),getCallIntNoNeed());
        mess_.addEntry(_mapping.getVal(CALL_INT_NO_NEED_TYPE),getCallIntNoNeedType());
        mess_.addEntry(_mapping.getVal(CALL_INT_NEED_TYPE),getCallIntNeedType());
        mess_.addEntry(_mapping.getVal(CALL_INT_ONLY),getCallIntOnly());
        mess_.addEntry(_mapping.getVal(BAD_INHERITS_TYPE),getBadInheritsType());
        mess_.addEntry(_mapping.getVal(BAD_INHERITS_TYPE_INN),getBadInheritsTypeInn());
        mess_.addEntry(_mapping.getVal(BAD_INHERITS_TYPE_AS_INN),getBadInheritsTypeAsInn());
        mess_.addEntry(_mapping.getVal(BAD_INHERITS_TYPE_INT),getBadInheritsTypeInt());
        mess_.addEntry(_mapping.getVal(FINAL_TYPE),getFinalType());
        mess_.addEntry(_mapping.getVal(DUPLICATE_SUPER),getDuplicateSuper());
        mess_.addEntry(_mapping.getVal(RESERVED_TYPE),getReservedType());
        mess_.addEntry(_mapping.getVal(SUPER_CLASS),getSuperClass());
        mess_.addEntry(_mapping.getVal(UNKNOWN_SUPER_TYPE),getUnknownSuperType());
        mess_.addEntry(_mapping.getVal(CYCLIC_INHERITS),getCyclicInherits());
        mess_.addEntry(_mapping.getVal(ANNOTATION_PARAM),getAnnotationParam());
        mess_.addEntry(_mapping.getVal(CYCLIC_MAPPING),getCyclicMapping());
        mess_.addEntry(_mapping.getVal(ABS_MAPPING),getAbsMapping());
        mess_.addEntry(_mapping.getVal(FINAL_MAPPING),getFinalMapping());
        mess_.addEntry(_mapping.getVal(MUST_CALL_INT_CTOR),getMustCallIntCtor());
        mess_.addEntry(_mapping.getVal(MUST_NOT_CALL_INT_CTOR_AFTER_THIS),getMustNotCallIntCtorAfterThis());
        mess_.addEntry(_mapping.getVal(MUST_CALL_INT_CTOR_NEED),getMustCallIntCtorNeed());
        mess_.addEntry(_mapping.getVal(MUST_CALL_INT_CTOR_NOT_NEED),getMustCallIntCtorNotNeed());
        mess_.addEntry(_mapping.getVal(BAD_LABEL),getBadLabel());
        mess_.addEntry(_mapping.getVal(DUPLICATED_LABEL),getDuplicatedLabel());
        mess_.addEntry(_mapping.getVal(BAD_METHOD_NAME),getBadMethodName());
        mess_.addEntry(_mapping.getVal(KEY_WORD_METHOD_NAME),getKeyWordMethodName());
        mess_.addEntry(_mapping.getVal(PRIM_METHOD_NAME),getPrimMethodName());
        mess_.addEntry(_mapping.getVal(DIGIT_METHOD_NAME),getDigitMethodName());
        mess_.addEntry(_mapping.getVal(BAD_OPERATOR_NAME),getBadOperatorName());
        mess_.addEntry(_mapping.getVal(BAD_ACCESS),getBadAccess());
        mess_.addEntry(_mapping.getVal(BAD_RETURN_TYPE),getBadReturnType());
        mess_.addEntry(_mapping.getVal(BAD_PARAMS),getBadParams());
        mess_.addEntry(_mapping.getVal(BAD_METHOD_MODIFIER),getBadMethodModifier());
        mess_.addEntry(_mapping.getVal(BAD_METHOD_VARARG),getBadMethodVararg());
        mess_.addEntry(_mapping.getVal(BAD_INDEXER_PARAMS),getBadIndexerParams());
        mess_.addEntry(_mapping.getVal(BAD_INDEXER_MODIFIER),getBadIndexerModifier());
        mess_.addEntry(_mapping.getVal(BAD_INDEXER_MODIFIERS),getBadIndexerModifiers());
        mess_.addEntry(_mapping.getVal(BAD_INDEXER_ACCESSES),getBadIndexerAccesses());
        mess_.addEntry(_mapping.getVal(BAD_INDEXER_PAIR_GET),getBadIndexerPairGet());
        mess_.addEntry(_mapping.getVal(BAD_INDEXER_PAIR_SET),getBadIndexerPairSet());
        mess_.addEntry(_mapping.getVal(DUPLICATE_CUSTOM_METHOD),getDuplicateCustomMethod());
        mess_.addEntry(_mapping.getVal(RESERVED_CUSTOM_METHOD),getReservedCustomMethod());
        mess_.addEntry(_mapping.getVal(DUPLICATE_INDEXER),getDuplicateIndexer());
        mess_.addEntry(_mapping.getVal(DUPLICATE_OPERATOR),getDuplicateOperator());
        mess_.addEntry(_mapping.getVal(FUNCTIONAL_APPLY_NB_DIFF),getFunctionalApplyNbDiff());
        mess_.addEntry(_mapping.getVal(FUNCTIONAL_APPLY_ONLY),getFunctionalApplyOnly());
        mess_.addEntry(_mapping.getVal(OPERATOR_NB_DIFF),getOperatorNbDiff());
        mess_.addEntry(_mapping.getVal(SPLIT_COMA),getSplitComa());
        mess_.addEntry(_mapping.getVal(SPLIT_COMA_LOW),getSplitComaLow());
        mess_.addEntry(_mapping.getVal(SPLIT_DIFF),getSplitDiff());
        mess_.addEntry(_mapping.getVal(BAD_DOTTED),getBadDotted());
        mess_.addEntry(_mapping.getVal(BAD_PARAM_NAME),getBadParamName());
        mess_.addEntry(_mapping.getVal(KEY_WORD_PARAM_NAME),getKeyWordParamName());
        mess_.addEntry(_mapping.getVal(PRIM_PARAM_NAME),getPrimParamName());
        mess_.addEntry(_mapping.getVal(DIGIT_PARAM_NAME),getDigitParamName());
        mess_.addEntry(_mapping.getVal(RESERVED_PARAM_NAME),getReservedParamName());
        mess_.addEntry(_mapping.getVal(DUPLICATED_PARAM_NAME),getDuplicatedParamName());
        mess_.addEntry(_mapping.getVal(BAD_RETURN_TYPE_INHERIT),getBadReturnTypeInherit());
        mess_.addEntry(_mapping.getVal(BAD_RETURN_TYPE_INDEXER),getBadReturnTypeIndexer());
        mess_.addEntry(_mapping.getVal(DUPLICATED_OVERRIDING),getDuplicatedOverriding());
        mess_.addEntry(_mapping.getVal(TWO_FINAL),getTwoFinal());
        mess_.addEntry(_mapping.getVal(FINAL_NOT_SUB_RETURN_TYPE),getFinalNotSubReturnType());
        mess_.addEntry(_mapping.getVal(TWO_RETURN_TYPES),getTwoReturnTypes());
        mess_.addEntry(_mapping.getVal(RETURN_TYPES),getReturnTypes());
        mess_.addEntry(_mapping.getVal(BAD_VARIABLE_NAME),getBadVariableName());
        mess_.addEntry(_mapping.getVal(KEY_WORD_VARIABLE_NAME),getKeyWordVariableName());
        mess_.addEntry(_mapping.getVal(PRIM_VARIABLE_NAME),getPrimVariableName());
        mess_.addEntry(_mapping.getVal(DIGIT_VARIABLE_NAME),getDigitVariableName());
        mess_.addEntry(_mapping.getVal(DUPLICATED_VARIABLE_NAME),getDuplicatedVariableName());
        mess_.addEntry(_mapping.getVal(CYCLIC_CTOR_CALL),getCyclicCtorCall());
        mess_.addEntry(_mapping.getVal(DEAD_CODE),getDeadCode());
        mess_.addEntry(_mapping.getVal(DEAD_CODE_TERNARY),getDeadCodeTernary());
        mess_.addEntry(_mapping.getVal(UNUSED_PARAM_STATIC),getUnusedParamStatic());
        mess_.addEntry(_mapping.getVal(DUPLICATED_CTOR),getDuplicatedCtor());
        mess_.addEntry(_mapping.getVal(DUPLICATED_GENERIC_SUPER_TYPES),getDuplicatedGenericSuperTypes());
        mess_.addEntry(_mapping.getVal(DUPLICATED_INNER_TYPE),getDuplicatedInnerType());
        mess_.addEntry(_mapping.getVal(DUPLICATED_TYPE),getDuplicatedType());
        mess_.addEntry(_mapping.getVal(DUPLICATED_TYPE_PRIM),getDuplicatedTypePrim());
        mess_.addEntry(_mapping.getVal(DUPLICATED_TYPE_STD),getDuplicatedTypeStd());
        mess_.addEntry(_mapping.getVal(DUPLICATED_TYPE_PKG),getDuplicatedTypePkg());
        mess_.addEntry(_mapping.getVal(EMPTY_EXPRESSION_PART),getEmptyExpressionPart());
        mess_.addEntry(_mapping.getVal(DO_WHILE_NOT_EMPTY),getDoWhileNotEmpty());
        mess_.addEntry(_mapping.getVal(DUPLICATED_FINAL),getDuplicatedFinal());
        mess_.addEntry(_mapping.getVal(ILLEGAL_CTOR_ENUM),getIllegalCtorEnum());
        mess_.addEntry(_mapping.getVal(ILLEGAL_GENERIC_SUPER_TYPE_BOUND),getIllegalGenericSuperTypeBound());
        mess_.addEntry(_mapping.getVal(ILLEGAL_CTOR_ANNOTATION),getIllegalCtorAnnotation());
        mess_.addEntry(_mapping.getVal(ILLEGAL_CTOR_ABSTRACT),getIllegalCtorAbstract());
        mess_.addEntry(_mapping.getVal(ILLEGAL_CTOR_BOUND),getIllegalCtorBound());
        mess_.addEntry(_mapping.getVal(ILLEGAL_CTOR_ARRAY),getIllegalCtorArray());
        mess_.addEntry(_mapping.getVal(ILLEGAL_CTOR_UNKNOWN),getIllegalCtorUnknown());
        mess_.addEntry(_mapping.getVal(MISSING_ABRUPT),getMissingAbrupt());
        mess_.addEntry(_mapping.getVal(NOT_INIT_CLASS),getNotInitClass());
        mess_.addEntry(_mapping.getVal(NULL_VALUE),getNullValue());
        mess_.addEntry(_mapping.getVal(BAD_PARAME_TYPE_FOR_ID),getBadParameTypeForId());
        mess_.addEntry(_mapping.getVal(NOT_RESOLVED_OWNER),getNotResolvedOwner());
        mess_.addEntry(_mapping.getVal(UNDEFINED_ACCESSIBLE_FIELD),getUndefinedAccessibleField());
        mess_.addEntry(_mapping.getVal(STATIC_ACCESS),getStaticAccess());
        mess_.addEntry(_mapping.getVal(STATIC_ACCESS_PREV),getStaticAccessPrev());
        mess_.addEntry(_mapping.getVal(UNASSIGNED_FINAL_FIELD),getUnassignedFinalField());
        mess_.addEntry(_mapping.getVal(UNASSIGNED_INFERING_TYPE),getUnassignedInferingType());
        mess_.addEntry(_mapping.getVal(UNDEFINED_CTOR),getUndefinedCtor());
        mess_.addEntry(_mapping.getVal(UNDEFINED_METHOD),getUndefinedMethod());
        mess_.addEntry(_mapping.getVal(ARRAY_CLONE_ONLY),getArrayCloneOnly());
        mess_.addEntry(_mapping.getVal(UNDEFINED_SUPER_CTOR),getUndefinedSuperCtor());
        mess_.addEntry(_mapping.getVal(UNDEFINED_SUPER_CTOR_CALL),getUndefinedSuperCtorCall());
        mess_.addEntry(_mapping.getVal(UNDEFINED_VARIABLE),getUndefinedVariable());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_AFFECT),getUnexpectedAffect());
        mess_.addEntry(_mapping.getVal(FINAL_FIELD),getFinalField());
        mess_.addEntry(_mapping.getVal(BAD_OPERATOR_REF),getBadOperatorRef());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_CATCH_ELSE_FINALLY),getUnexpectedCatchElseFinally());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_ABRUPT),getUnexpectedAbrupt());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_ABRUPT_LAB),getUnexpectedAbruptLab());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_CASE_DEF),getUnexpectedCaseDef());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_CASE_VAR),getUnexpectedCaseVar());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_CASE_VALUE),getUnexpectedCaseValue());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_CASE_DUP),getUnexpectedCaseDup());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_DEF_DUP),getUnexpectedDefDup());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_DO_TRY),getUnexpectedDoTry());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_SWITCH),getUnexpectedSwitch());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_MEMBER_INST),getUnexpectedMemberInst());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_BLOCK_EXP),getUnexpectedBlockExp());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_OPERAND_TYPES),getUnexpectedOperandTypes());
        mess_.addEntry(_mapping.getVal(UNKNOWN_TYPE),getUnknownType());
        mess_.addEntry(_mapping.getVal(EMPTY_TYPE),getEmptyType());
        mess_.addEntry(_mapping.getVal(BAD_PARAMERIZED_TYPE),getBadParamerizedType());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_TYPE_BOUND),getUnexpectedTypeBound());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_VARARG),getUnexpectedVararg());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_LEAF),getUnexpectedLeaf());
        mess_.addEntry(_mapping.getVal(CASE_TYPE_VAR),getCaseTypeVar());
        mess_.addEntry(_mapping.getVal(EMPTY_PART),getEmptyPart());
        return mess_;
    }
    public static StringMap<String> mapping() {
        StringMap<String> m_=new StringMap<String>();
        m_.addEntry(DUPLICATE_MERGED_METHOD,"DuplicateMergedMethod");
        m_.addEntry(DUPLICATE_FIELD,"DuplicateField");
        m_.addEntry(DUPLICATE_VAR_TYPE,"DuplicateVarType");
        m_.addEntry(EMPTY_PKG_REF_TYPE,"EmptyPkgRefType");
        m_.addEntry(PRIMITIVE_KEY_WORD,"PrimitiveKeyWord");
        m_.addEntry(DEFAULT_PKG_REF_TYPE,"DefaultPkgRefType");
        m_.addEntry(REF_TYPE_KEY_WORD,"RefTypeKeyWord");
        m_.addEntry(DIGIT_FIRST_PRIMITIVE,"DigitFirstPrimitive");
        m_.addEntry(ILLEGAL_FIRST_CHAR,"IllegalFirstChar");
        m_.addEntry(EMPTY_PRIMITIVE,"EmptyPrimitive");
        m_.addEntry(NOT_WORD_CHAR_PRIMITIVE,"NotWordCharPrimitive");
        m_.addEntry(NOT_WORD_CHAR_REF_TYPE,"NotWordCharRefType");
        m_.addEntry(EMPTY_REF_TYPE_IN,"EmptyRefTypeIn");
        m_.addEntry(REF_TYPE_PRIMITIVE,"RefTypePrimitive");
        m_.addEntry(DIGIT_FIRST_REF_TYPE,"DigitFirstRefType");
        m_.addEntry(DUPLICATE_PRIMTIVE,"DuplicatePrimtive");
        m_.addEntry(DUPLICATE_METHOD,"DuplicateMethod");
        m_.addEntry(DEFAULT_PKG_NO_MATCH,"DefaultPkgNoMatch");
        m_.addEntry(DUPLICATE_REF_TYPE,"DuplicateRefType");
        m_.addEntry(DIGIT_FIRST_METHOD,"DigitFirstMethod");
        m_.addEntry(NOT_WORD_CHAR_FIELD,"NotWordCharField");
        m_.addEntry(NOT_WORD_CHAR_METHOD,"NotWordCharMethod");
        m_.addEntry(VAR_TYPE_KEY_WORD,"VarTypeKeyWord");
        m_.addEntry(VAR_TYPE_PRIMITIVE,"VarTypePrimitive");
        m_.addEntry(DIGIT_FIRST_VAR_TYPE,"DigitFirstVarType");
        m_.addEntry(DUPLICATE_NUMBER_WORD,"DuplicateNumberWord");
        m_.addEntry(METHOD_PRIMITIVE,"MethodPrimitive");
        m_.addEntry(FIELD_PRIMITIVE,"FieldPrimitive");
        m_.addEntry(DUPLICATE_STRING_WORD,"DuplicateStringWord");
        m_.addEntry(DUPLICATE_KEY_WORD,"DuplicateKeyWord");
        m_.addEntry(DIGIT_FIRST_FIELD,"DigitFirstField");
        m_.addEntry(DUPLICATE_STARTING_NB,"DuplicateStartingNb");
        m_.addEntry(DUPLICATE_STARTING_UNI,"DuplicateStartingUni");
        m_.addEntry(DUPLICATE_STARTING,"DuplicateStarting");
        m_.addEntry(NOT_WORD_CHAR_VAR_TYPE,"NotWordCharVarType");
        m_.addEntry(EMPTY_PRE_BIN,"EmptyPreBin");
        m_.addEntry(EMPTY_VAR_TYPE,"EmptyVarType");
        m_.addEntry(EMPTY_WORD,"EmptyWord");
        m_.addEntry(EMPTY_FIELD,"EmptyField");
        m_.addEntry(EMPTY_NB,"EmptyNb");
        m_.addEntry(NOT_WORD_CHAR,"NotWordChar");
        m_.addEntry(EMPTY_PRE_HEX,"EmptyPreHex");
        m_.addEntry(EMPTY_METHOD,"EmptyMethod");
        m_.addEntry(DIGIT_FIRST,"DigitFirst");
        m_.addEntry(EMPTY_BIN_EXP,"EmptyBinExp");
        m_.addEntry(EMPTY_STRING,"EmptyString");
        m_.addEntry(ILLEGAL_CHAR,"IllegalChar");
        m_.addEntry(METHOD_KEY_WORD,"MethodKeyWord");
        m_.addEntry(EMPTY_REF_TYPE,"EmptyRefType");
        m_.addEntry(FIELD_KEY_WORD,"FieldKeyWord");
        m_.addEntry(ABSTRACT_METHOD_BODY,"AbstractMethodBody");
        m_.addEntry(ABSTRACT_METHOD_CONC,"AbstractMethodConc");
        m_.addEntry(ABSTRACT_METHOD_IMPL,"AbstractMethodImpl");
        m_.addEntry(ABSTRACT_METHOD_REF,"AbstractMethodRef");
        m_.addEntry(INACCESSIBLE_TYPE,"InaccessibleType");
        m_.addEntry(UNEXPECTED_TYPE,"UnexpectedType");
        m_.addEntry(UNEXPECTED_RET_TYPE,"UnexpectedRetType");
        m_.addEntry(METHODS_ACCESSES,"MethodsAccesses");
        m_.addEntry(EMPTY_PACKAGE,"EmptyPackage");
        m_.addEntry(EMPTY_PART_CLASS_NAME,"EmptyPartClassName");
        m_.addEntry(BAD_PART_CLASS_NAME,"BadPartClassName");
        m_.addEntry(KEY_WORD_PART_CLASS_NAME,"KeyWordPartClassName");
        m_.addEntry(PRIM_PART_CLASS_NAME,"PrimPartClassName");
        m_.addEntry(DIGIT_PART_CLASS_NAME,"DigitPartClassName");
        m_.addEntry(BAD_PART_VAR_CLASS_NAME,"BadPartVarClassName");
        m_.addEntry(KEY_WORD_PART_VAR_CLASS_NAME,"KeyWordPartVarClassName");
        m_.addEntry(PRIM_PART_VAR_CLASS_NAME,"PrimPartVarClassName");
        m_.addEntry(DIGIT_PART_VAR_CLASS_NAME,"DigitPartVarClassName");
        m_.addEntry(DUPLICATED_PART_VAR_CLASS_NAME,"DuplicatedPartVarClassName");
        m_.addEntry(CALL_CTOR_END,"CallCtorEnd");
        m_.addEntry(CALL_CTOR,"CallCtor");
        m_.addEntry(CALL_CTOR_BEFORE_BLOCK,"CallCtorBeforeBlock");
        m_.addEntry(CALL_CTOR_FIRST_LINE,"CallCtorFirstLine");
        m_.addEntry(CALL_CTOR_INT_FROM_SUPER_INT,"CallCtorIntFromSuperInt");
        m_.addEntry(CALL_CTOR_INT_NOT_FROM_INT,"CallCtorIntNotFromInt");
        m_.addEntry(CALL_CTOR_INT_AFTER_SUPER_THIS,"CallCtorIntAfterSuperThis");
        m_.addEntry(CALL_CTOR_INT_INHERITS,"CallCtorIntInherits");
        m_.addEntry(CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON,"CallCtorSuperClassEnumSingleton");
        m_.addEntry(CALL_CTOR_NO_SUPER_CLASS_ENUM,"CallCtorNoSuperClassEnum");
        m_.addEntry(ANNOT_FIELD_NOT_UNIQ,"AnnotFieldNotUniq");
        m_.addEntry(ANNOT_FIELD_MUST,"AnnotFieldMust");
        m_.addEntry(DUP_SUPPLIED_ANNOT_FIELD,"DupSuppliedAnnotField");
        m_.addEntry(BAD_EXPRESSION,"BadExpression");
        m_.addEntry(BAD_FIELD_NAME,"BadFieldName");
        m_.addEntry(KEY_WORD_FIELD_NAME,"KeyWordFieldName");
        m_.addEntry(PRIM_FIELD_NAME,"PrimFieldName");
        m_.addEntry(DIGIT_FIELD_NAME,"DigitFieldName");
        m_.addEntry(NOT_RETRIEVED_FIELDS,"NotRetrievedFields");
        m_.addEntry(BAD_NB_FORMAT,"BadNbFormat");
        m_.addEntry(BAD_CHAR_FORMAT,"BadCharFormat");
        m_.addEntry(BAD_IMPLICIT_CAST,"BadImplicitCast");
        m_.addEntry(NOT_PRIMITIVE_WRAPPER,"NotPrimitiveWrapper");
        m_.addEntry(VOID_TYPE,"VoidType");
        m_.addEntry(BAD_INDEX_IN_PARSER,"BadIndexInParser");
        m_.addEntry(ILLEGAL_CHARACTER,"IllegalCharacter");
        m_.addEntry(CALL_INT_INHERITS,"CallIntInherits");
        m_.addEntry(CALL_INT_NO_NEED,"CallIntNoNeed");
        m_.addEntry(CALL_INT_NO_NEED_TYPE,"CallIntNoNeedType");
        m_.addEntry(CALL_INT_NEED_TYPE,"CallIntNeedType");
        m_.addEntry(CALL_INT_ONLY,"CallIntOnly");
        m_.addEntry(BAD_INHERITS_TYPE,"BadInheritsType");
        m_.addEntry(BAD_INHERITS_TYPE_INN,"BadInheritsTypeInn");
        m_.addEntry(BAD_INHERITS_TYPE_AS_INN,"BadInheritsTypeAsInn");
        m_.addEntry(BAD_INHERITS_TYPE_INT,"BadInheritsTypeInt");
        m_.addEntry(FINAL_TYPE,"FinalType");
        m_.addEntry(DUPLICATE_SUPER,"DuplicateSuper");
        m_.addEntry(RESERVED_TYPE,"ReservedType");
        m_.addEntry(SUPER_CLASS,"SuperClass");
        m_.addEntry(UNKNOWN_SUPER_TYPE,"UnknownSuperType");
        m_.addEntry(CYCLIC_INHERITS,"CyclicInherits");
        m_.addEntry(ANNOTATION_PARAM,"AnnotationParam");
        m_.addEntry(CYCLIC_MAPPING,"CyclicMapping");
        m_.addEntry(ABS_MAPPING,"AbsMapping");
        m_.addEntry(FINAL_MAPPING,"FinalMapping");
        m_.addEntry(MUST_CALL_INT_CTOR,"MustCallIntCtor");
        m_.addEntry(MUST_NOT_CALL_INT_CTOR_AFTER_THIS,"MustNotCallIntCtorAfterThis");
        m_.addEntry(MUST_CALL_INT_CTOR_NEED,"MustCallIntCtorNeed");
        m_.addEntry(MUST_CALL_INT_CTOR_NOT_NEED,"MustCallIntCtorNotNeed");
        m_.addEntry(BAD_LABEL,"BadLabel");
        m_.addEntry(DUPLICATED_LABEL,"DuplicatedLabel");
        m_.addEntry(BAD_METHOD_NAME,"BadMethodName");
        m_.addEntry(KEY_WORD_METHOD_NAME,"KeyWordMethodName");
        m_.addEntry(PRIM_METHOD_NAME,"PrimMethodName");
        m_.addEntry(DIGIT_METHOD_NAME,"DigitMethodName");
        m_.addEntry(BAD_OPERATOR_NAME,"BadOperatorName");
        m_.addEntry(BAD_ACCESS,"BadAccess");
        m_.addEntry(BAD_RETURN_TYPE,"BadReturnType");
        m_.addEntry(BAD_PARAMS,"BadParams");
        m_.addEntry(BAD_METHOD_MODIFIER,"BadMethodModifier");
        m_.addEntry(BAD_METHOD_VARARG,"BadMethodVararg");
        m_.addEntry(BAD_INDEXER_PARAMS,"BadIndexerParams");
        m_.addEntry(BAD_INDEXER_MODIFIER,"BadIndexerModifier");
        m_.addEntry(BAD_INDEXER_MODIFIERS,"BadIndexerModifiers");
        m_.addEntry(BAD_INDEXER_ACCESSES,"BadIndexerAccesses");
        m_.addEntry(BAD_INDEXER_PAIR_GET,"BadIndexerPairGet");
        m_.addEntry(BAD_INDEXER_PAIR_SET,"BadIndexerPairSet");
        m_.addEntry(DUPLICATE_CUSTOM_METHOD,"DuplicateCustomMethod");
        m_.addEntry(RESERVED_CUSTOM_METHOD,"ReservedCustomMethod");
        m_.addEntry(DUPLICATE_INDEXER,"DuplicateIndexer");
        m_.addEntry(DUPLICATE_OPERATOR,"DuplicateOperator");
        m_.addEntry(FUNCTIONAL_APPLY_NB_DIFF,"FunctionalApplyNbDiff");
        m_.addEntry(FUNCTIONAL_APPLY_ONLY,"FunctionalApplyOnly");
        m_.addEntry(OPERATOR_NB_DIFF,"OperatorNbDiff");
        m_.addEntry(SPLIT_COMA,"SplitComa");
        m_.addEntry(SPLIT_COMA_LOW,"SplitComaLow");
        m_.addEntry(SPLIT_DIFF,"SplitDiff");
        m_.addEntry(BAD_DOTTED,"BadDotted");
        m_.addEntry(BAD_PARAM_NAME,"BadParamName");
        m_.addEntry(KEY_WORD_PARAM_NAME,"KeyWordParamName");
        m_.addEntry(PRIM_PARAM_NAME,"PrimParamName");
        m_.addEntry(DIGIT_PARAM_NAME,"DigitParamName");
        m_.addEntry(RESERVED_PARAM_NAME,"ReservedParamName");
        m_.addEntry(DUPLICATED_PARAM_NAME,"DuplicatedParamName");
        m_.addEntry(BAD_RETURN_TYPE_INHERIT,"BadReturnTypeInherit");
        m_.addEntry(BAD_RETURN_TYPE_INDEXER,"BadReturnTypeIndexer");
        m_.addEntry(DUPLICATED_OVERRIDING,"DuplicatedOverriding");
        m_.addEntry(TWO_FINAL,"TwoFinal");
        m_.addEntry(FINAL_NOT_SUB_RETURN_TYPE,"FinalNotSubReturnType");
        m_.addEntry(TWO_RETURN_TYPES,"TwoReturnTypes");
        m_.addEntry(RETURN_TYPES,"ReturnTypes");
        m_.addEntry(BAD_VARIABLE_NAME,"BadVariableName");
        m_.addEntry(KEY_WORD_VARIABLE_NAME,"KeyWordVariableName");
        m_.addEntry(PRIM_VARIABLE_NAME,"PrimVariableName");
        m_.addEntry(DIGIT_VARIABLE_NAME,"DigitVariableName");
        m_.addEntry(DUPLICATED_VARIABLE_NAME,"DuplicatedVariableName");
        m_.addEntry(CYCLIC_CTOR_CALL,"CyclicCtorCall");
        m_.addEntry(DEAD_CODE,"DeadCode");
        m_.addEntry(DEAD_CODE_TERNARY,"DeadCodeTernary");
        m_.addEntry(UNUSED_PARAM_STATIC,"UnusedParamStatic");
        m_.addEntry(DUPLICATED_CTOR,"DuplicatedCtor");
        m_.addEntry(DUPLICATED_GENERIC_SUPER_TYPES,"DuplicatedGenericSuperTypes");
        m_.addEntry(DUPLICATED_INNER_TYPE,"DuplicatedInnerType");
        m_.addEntry(DUPLICATED_TYPE,"DuplicatedType");
        m_.addEntry(DUPLICATED_TYPE_PRIM,"DuplicatedTypePrim");
        m_.addEntry(DUPLICATED_TYPE_STD,"DuplicatedTypeStd");
        m_.addEntry(DUPLICATED_TYPE_PKG,"DuplicatedTypePkg");
        m_.addEntry(EMPTY_EXPRESSION_PART,"EmptyExpressionPart");
        m_.addEntry(DO_WHILE_NOT_EMPTY,"DoWhileNotEmpty");
        m_.addEntry(DUPLICATED_FINAL,"DuplicatedFinal");
        m_.addEntry(ILLEGAL_CTOR_ENUM,"IllegalCtorEnum");
        m_.addEntry(ILLEGAL_GENERIC_SUPER_TYPE_BOUND,"IllegalGenericSuperTypeBound");
        m_.addEntry(ILLEGAL_CTOR_ANNOTATION,"IllegalCtorAnnotation");
        m_.addEntry(ILLEGAL_CTOR_ABSTRACT,"IllegalCtorAbstract");
        m_.addEntry(ILLEGAL_CTOR_BOUND,"IllegalCtorBound");
        m_.addEntry(ILLEGAL_CTOR_ARRAY,"IllegalCtorArray");
        m_.addEntry(ILLEGAL_CTOR_UNKNOWN,"IllegalCtorUnknown");
        m_.addEntry(MISSING_ABRUPT,"MissingAbrupt");
        m_.addEntry(NOT_INIT_CLASS,"NotInitClass");
        m_.addEntry(NULL_VALUE,"NullValue");
        m_.addEntry(BAD_PARAME_TYPE_FOR_ID,"BadParameTypeForId");
        m_.addEntry(NOT_RESOLVED_OWNER,"NotResolvedOwner");
        m_.addEntry(UNDEFINED_ACCESSIBLE_FIELD,"UndefinedAccessibleField");
        m_.addEntry(STATIC_ACCESS,"StaticAccess");
        m_.addEntry(STATIC_ACCESS_PREV,"StaticAccessPrev");
        m_.addEntry(UNASSIGNED_FINAL_FIELD,"UnassignedFinalField");
        m_.addEntry(UNASSIGNED_INFERING_TYPE,"UnassignedInferingType");
        m_.addEntry(UNDEFINED_CTOR,"UndefinedCtor");
        m_.addEntry(UNDEFINED_METHOD,"UndefinedMethod");
        m_.addEntry(ARRAY_CLONE_ONLY,"ArrayCloneOnly");
        m_.addEntry(UNDEFINED_SUPER_CTOR,"UndefinedSuperCtor");
        m_.addEntry(UNDEFINED_SUPER_CTOR_CALL,"UndefinedSuperCtorCall");
        m_.addEntry(UNDEFINED_VARIABLE,"UndefinedVariable");
        m_.addEntry(UNEXPECTED_AFFECT,"UnexpectedAffect");
        m_.addEntry(FINAL_FIELD,"FinalField");
        m_.addEntry(BAD_OPERATOR_REF,"BadOperatorRef");
        m_.addEntry(UNEXPECTED_CATCH_ELSE_FINALLY,"UnexpectedCatchElseFinally");
        m_.addEntry(UNEXPECTED_ABRUPT,"UnexpectedAbrupt");
        m_.addEntry(UNEXPECTED_ABRUPT_LAB,"UnexpectedAbruptLab");
        m_.addEntry(UNEXPECTED_CASE_DEF,"UnexpectedCaseDef");
        m_.addEntry(UNEXPECTED_CASE_VAR,"UnexpectedCaseVar");
        m_.addEntry(UNEXPECTED_CASE_VALUE,"UnexpectedCaseValue");
        m_.addEntry(UNEXPECTED_CASE_DUP,"UnexpectedCaseDup");
        m_.addEntry(UNEXPECTED_DEF_DUP,"UnexpectedDefDup");
        m_.addEntry(UNEXPECTED_DO_TRY,"UnexpectedDoTry");
        m_.addEntry(UNEXPECTED_SWITCH,"UnexpectedSwitch");
        m_.addEntry(UNEXPECTED_MEMBER_INST,"UnexpectedMemberInst");
        m_.addEntry(UNEXPECTED_BLOCK_EXP,"UnexpectedBlockExp");
        m_.addEntry(UNEXPECTED_OPERAND_TYPES,"UnexpectedOperandTypes");
        m_.addEntry(UNKNOWN_TYPE,"UnknownType");
        m_.addEntry(EMPTY_TYPE,"EmptyType");
        m_.addEntry(BAD_PARAMERIZED_TYPE,"BadParamerizedType");
        m_.addEntry(UNEXPECTED_TYPE_BOUND,"UnexpectedTypeBound");
        m_.addEntry(UNEXPECTED_VARARG,"UnexpectedVararg");
        m_.addEntry(UNEXPECTED_LEAF,"UnexpectedLeaf");
        m_.addEntry(CASE_TYPE_VAR,"CaseTypeVar");
        m_.addEntry(EMPTY_PART,"EmptyPart");
        return m_;
    }

    public String getEmptyWord() {
        return emptyWord;
    }

    public void setEmptyWord(String _v) {
        this.emptyWord =_v;
    }

    public String getNotWordChar() {
        return notWordChar;
    }

    public void setNotWordChar(String _v) {
        this.notWordChar =_v;
    }

    public String getDigitFirst() {
        return digitFirst;
    }

    public void setDigitFirst(String _v) {
        this.digitFirst =_v;
    }

    public String getEmptyString() {
        return emptyString;
    }

    public void setEmptyString(String _v) {
        this.emptyString =_v;
    }

    public String getEmptyNb() {
        return emptyNb;
    }

    public void setEmptyNb(String _v) {
        this.emptyNb =_v;
    }

    public String getEmptyBinExp() {
        return emptyBinExp;
    }

    public void setEmptyBinExp(String _v) {
        this.emptyBinExp =_v;
    }

    public String getEmptyPreBin() {
        return emptyPreBin;
    }

    public void setEmptyPreBin(String _v) {
        this.emptyPreBin =_v;
    }

    public String getEmptyPreHex() {
        return emptyPreHex;
    }

    public void setEmptyPreHex(String _v) {
        this.emptyPreHex =_v;
    }

    public String getIllegalChar() {
        return illegalChar;
    }

    public void setIllegalChar(String _v) {
        this.illegalChar =_v;
    }

    public String getIllegalFirstChar() {
        return illegalFirstChar;
    }

    public void setIllegalFirstChar(String _v) {
        this.illegalFirstChar =_v;
    }

    public String getEmptyPrimitive() {
        return emptyPrimitive;
    }

    public void setEmptyPrimitive(String _v) {
        this.emptyPrimitive =_v;
    }

    public String getNotWordCharPrimitive() {
        return notWordCharPrimitive;
    }

    public void setNotWordCharPrimitive(String _v) {
        this.notWordCharPrimitive =_v;
    }

    public String getPrimitiveKeyWord() {
        return primitiveKeyWord;
    }

    public void setPrimitiveKeyWord(String _v) {
        this.primitiveKeyWord =_v;
    }

    public String getDigitFirstPrimitive() {
        return digitFirstPrimitive;
    }

    public void setDigitFirstPrimitive(String _v) {
        this.digitFirstPrimitive =_v;
    }

    public String getEmptyRefType() {
        return emptyRefType;
    }

    public void setEmptyRefType(String _v) {
        this.emptyRefType =_v;
    }

    public String getEmptyRefTypeIn() {
        return emptyRefTypeIn;
    }

    public void setEmptyRefTypeIn(String _v) {
        this.emptyRefTypeIn =_v;
    }

    public String getNotWordCharRefType() {
        return notWordCharRefType;
    }

    public void setNotWordCharRefType(String _v) {
        this.notWordCharRefType =_v;
    }

    public String getRefTypeKeyWord() {
        return refTypeKeyWord;
    }

    public void setRefTypeKeyWord(String _v) {
        this.refTypeKeyWord =_v;
    }

    public String getRefTypePrimitive() {
        return refTypePrimitive;
    }

    public void setRefTypePrimitive(String _v) {
        this.refTypePrimitive =_v;
    }

    public String getDigitFirstRefType() {
        return digitFirstRefType;
    }

    public void setDigitFirstRefType(String _v) {
        this.digitFirstRefType =_v;
    }

    public String getEmptyPkgRefType() {
        return emptyPkgRefType;
    }

    public void setEmptyPkgRefType(String _v) {
        this.emptyPkgRefType =_v;
    }

    public String getDefaultPkgRefType() {
        return defaultPkgRefType;
    }

    public void setDefaultPkgRefType(String _v) {
        this.defaultPkgRefType =_v;
    }

    public String getDefaultPkgNoMatch() {
        return defaultPkgNoMatch;
    }

    public void setDefaultPkgNoMatch(String _v) {
        this.defaultPkgNoMatch =_v;
    }

    public String getEmptyMethod() {
        return emptyMethod;
    }

    public void setEmptyMethod(String _v) {
        this.emptyMethod =_v;
    }

    public String getNotWordCharMethod() {
        return notWordCharMethod;
    }

    public void setNotWordCharMethod(String _v) {
        this.notWordCharMethod =_v;
    }

    public String getMethodKeyWord() {
        return methodKeyWord;
    }

    public void setMethodKeyWord(String _v) {
        this.methodKeyWord =_v;
    }

    public String getMethodPrimitive() {
        return methodPrimitive;
    }

    public void setMethodPrimitive(String _v) {
        this.methodPrimitive =_v;
    }

    public String getDigitFirstMethod() {
        return digitFirstMethod;
    }

    public void setDigitFirstMethod(String _v) {
        this.digitFirstMethod =_v;
    }

    public String getEmptyField() {
        return emptyField;
    }

    public void setEmptyField(String _v) {
        this.emptyField =_v;
    }

    public String getNotWordCharField() {
        return notWordCharField;
    }

    public void setNotWordCharField(String _v) {
        this.notWordCharField =_v;
    }

    public String getFieldKeyWord() {
        return fieldKeyWord;
    }

    public void setFieldKeyWord(String _v) {
        this.fieldKeyWord =_v;
    }

    public String getFieldPrimitive() {
        return fieldPrimitive;
    }

    public void setFieldPrimitive(String _v) {
        this.fieldPrimitive =_v;
    }

    public String getDigitFirstField() {
        return digitFirstField;
    }

    public void setDigitFirstField(String _v) {
        this.digitFirstField =_v;
    }

    public String getEmptyVarType() {
        return emptyVarType;
    }

    public void setEmptyVarType(String _v) {
        this.emptyVarType =_v;
    }

    public String getNotWordCharVarType() {
        return notWordCharVarType;
    }

    public void setNotWordCharVarType(String _v) {
        this.notWordCharVarType =_v;
    }

    public String getVarTypeKeyWord() {
        return varTypeKeyWord;
    }

    public void setVarTypeKeyWord(String _v) {
        this.varTypeKeyWord =_v;
    }

    public String getVarTypePrimitive() {
        return varTypePrimitive;
    }

    public void setVarTypePrimitive(String _v) {
        this.varTypePrimitive =_v;
    }

    public String getDigitFirstVarType() {
        return digitFirstVarType;
    }

    public void setDigitFirstVarType(String _v) {
        this.digitFirstVarType =_v;
    }

    public String getDuplicateKeyWord() {
        return duplicateKeyWord;
    }

    public void setDuplicateKeyWord(String _v) {
        this.duplicateKeyWord =_v;
    }

    public String getDuplicateStringWord() {
        return duplicateStringWord;
    }

    public void setDuplicateStringWord(String _v) {
        this.duplicateStringWord =_v;
    }

    public String getDuplicateStarting() {
        return duplicateStarting;
    }

    public void setDuplicateStarting(String _v) {
        this.duplicateStarting =_v;
    }

    public String getDuplicateStartingUni() {
        return duplicateStartingUni;
    }

    public void setDuplicateStartingUni(String _v) {
        this.duplicateStartingUni =_v;
    }

    public String getDuplicateNumberWord() {
        return duplicateNumberWord;
    }

    public void setDuplicateNumberWord(String _v) {
        this.duplicateNumberWord =_v;
    }

    public String getDuplicateStartingNb() {
        return duplicateStartingNb;
    }

    public void setDuplicateStartingNb(String _v) {
        this.duplicateStartingNb =_v;
    }

    public String getDuplicatePrimtive() {
        return duplicatePrimtive;
    }

    public void setDuplicatePrimtive(String _v) {
        this.duplicatePrimtive =_v;
    }

    public String getDuplicateRefType() {
        return duplicateRefType;
    }

    public void setDuplicateRefType(String _v) {
        this.duplicateRefType =_v;
    }

    public String getDuplicateMethod() {
        return duplicateMethod;
    }

    public void setDuplicateMethod(String _v) {
        this.duplicateMethod =_v;
    }

    public String getDuplicateField() {
        return duplicateField;
    }

    public void setDuplicateField(String _v) {
        this.duplicateField =_v;
    }

    public String getDuplicateVarType() {
        return duplicateVarType;
    }

    public void setDuplicateVarType(String _v) {
        this.duplicateVarType =_v;
    }

    public String getDuplicateMergedMethod() {
        return duplicateMergedMethod;
    }

    public void setDuplicateMergedMethod(String _v) {
        this.duplicateMergedMethod =_v;
    }

    public String getAbstractMethodBody() {
        return abstractMethodBody;
    }

    public void setAbstractMethodBody(String _v) {
        this.abstractMethodBody =_v;
    }

    public String getAbstractMethodConc() {
        return abstractMethodConc;
    }

    public void setAbstractMethodConc(String _v) {
        this.abstractMethodConc =_v;
    }

    public String getAbstractMethodImpl() {
        return abstractMethodImpl;
    }

    public void setAbstractMethodImpl(String _v) {
        this.abstractMethodImpl =_v;
    }

    public String getAbstractMethodRef() {
        return abstractMethodRef;
    }

    public void setAbstractMethodRef(String _v) {
        this.abstractMethodRef =_v;
    }

    public String getInaccessibleType() {
        return inaccessibleType;
    }

    public void setInaccessibleType(String _v) {
        this.inaccessibleType =_v;
    }

    public String getUnexpectedType() {
        return unexpectedType;
    }

    public void setUnexpectedType(String _v) {
        this.unexpectedType =_v;
    }

    public String getUnexpectedRetType() {
        return unexpectedRetType;
    }

    public void setUnexpectedRetType(String _v) {
        this.unexpectedRetType =_v;
    }

    public String getMethodsAccesses() {
        return methodsAccesses;
    }

    public void setMethodsAccesses(String _v) {
        this.methodsAccesses =_v;
    }

    public String getEmptyPackage() {
        return emptyPackage;
    }

    public void setEmptyPackage(String _v) {
        this.emptyPackage =_v;
    }

    public String getEmptyPartClassName() {
        return emptyPartClassName;
    }

    public void setEmptyPartClassName(String _v) {
        this.emptyPartClassName =_v;
    }

    public String getBadPartClassName() {
        return badPartClassName;
    }

    public void setBadPartClassName(String _v) {
        this.badPartClassName =_v;
    }

    public String getBadPartVarClassName() {
        return badPartVarClassName;
    }

    public void setBadPartVarClassName(String _v) {
        this.badPartVarClassName =_v;
    }

    public String getCallCtorEnd() {
        return callCtorEnd;
    }

    public void setCallCtorEnd(String _v) {
        this.callCtorEnd =_v;
    }

    public String getCallCtor() {
        return callCtor;
    }

    public void setCallCtor(String _v) {
        this.callCtor =_v;
    }

    public String getCallCtorBeforeBlock() {
        return callCtorBeforeBlock;
    }

    public void setCallCtorBeforeBlock(String _v) {
        this.callCtorBeforeBlock =_v;
    }

    public String getCallCtorFirstLine() {
        return callCtorFirstLine;
    }

    public void setCallCtorFirstLine(String _v) {
        this.callCtorFirstLine =_v;
    }

    public String getCallCtorIntFromSuperInt() {
        return callCtorIntFromSuperInt;
    }

    public void setCallCtorIntFromSuperInt(String _v) {
        this.callCtorIntFromSuperInt =_v;
    }

    public String getCallCtorIntNotFromInt() {
        return callCtorIntNotFromInt;
    }

    public void setCallCtorIntNotFromInt(String _v) {
        this.callCtorIntNotFromInt =_v;
    }

    public String getCallCtorIntAfterSuperThis() {
        return callCtorIntAfterSuperThis;
    }

    public void setCallCtorIntAfterSuperThis(String _v) {
        this.callCtorIntAfterSuperThis =_v;
    }

    public String getCallCtorIntInherits() {
        return callCtorIntInherits;
    }

    public void setCallCtorIntInherits(String _v) {
        this.callCtorIntInherits =_v;
    }

    public String getCallCtorSuperClassEnumSingleton() {
        return callCtorSuperClassEnumSingleton;
    }

    public void setCallCtorSuperClassEnumSingleton(String _v) {
        this.callCtorSuperClassEnumSingleton =_v;
    }

    public String getCallCtorNoSuperClassEnum() {
        return callCtorNoSuperClassEnum;
    }

    public void setCallCtorNoSuperClassEnum(String _v) {
        this.callCtorNoSuperClassEnum = _v;
    }

    public String getAnnotFieldNotUniq() {
        return annotFieldNotUniq;
    }

    public void setAnnotFieldNotUniq(String _v) {
        this.annotFieldNotUniq =_v;
    }

    public String getAnnotFieldMust() {
        return annotFieldMust;
    }

    public void setAnnotFieldMust(String _v) {
        this.annotFieldMust =_v;
    }

    public String getDupSuppliedAnnotField() {
        return dupSuppliedAnnotField;
    }

    public void setDupSuppliedAnnotField(String _v) {
        this.dupSuppliedAnnotField =_v;
    }

    public String getBadExpression() {
        return badExpression;
    }

    public void setBadExpression(String _v) {
        this.badExpression =_v;
    }

    public String getBadFieldName() {
        return badFieldName;
    }

    public void setBadFieldName(String _v) {
        this.badFieldName =_v;
    }

    public String getNotRetrievedFields() {
        return notRetrievedFields;
    }

    public void setNotRetrievedFields(String _v) {
        this.notRetrievedFields =_v;
    }

    public String getBadNbFormat() {
        return badNbFormat;
    }

    public void setBadNbFormat(String _v) {
        this.badNbFormat =_v;
    }

    public String getBadCharFormat() {
        return badCharFormat;
    }

    public void setBadCharFormat(String _v) {
        this.badCharFormat =_v;
    }

    public String getBadImplicitCast() {
        return badImplicitCast;
    }

    public void setBadImplicitCast(String _v) {
        this.badImplicitCast =_v;
    }

    public String getNotPrimitiveWrapper() {
        return notPrimitiveWrapper;
    }

    public void setNotPrimitiveWrapper(String _v) {
        this.notPrimitiveWrapper =_v;
    }

    public String getVoidType() {
        return voidType;
    }

    public void setVoidType(String _v) {
        this.voidType =_v;
    }

    public String getBadIndexInParser() {
        return badIndexInParser;
    }

    public void setBadIndexInParser(String _v) {
        this.badIndexInParser =_v;
    }

    public String getIllegalCharacter() {
        return illegalCharacter;
    }

    public void setIllegalCharacter(String _v) {
        this.illegalCharacter =_v;
    }

    public String getCallIntInherits() {
        return callIntInherits;
    }

    public void setCallIntInherits(String _v) {
        this.callIntInherits =_v;
    }

    public String getCallIntNoNeed() {
        return callIntNoNeed;
    }

    public void setCallIntNoNeed(String _v) {
        this.callIntNoNeed =_v;
    }

    public String getCallIntNoNeedType() {
        return callIntNoNeedType;
    }

    public void setCallIntNoNeedType(String _v) {
        this.callIntNoNeedType =_v;
    }

    public String getCallIntNeedType() {
        return callIntNeedType;
    }

    public void setCallIntNeedType(String _v) {
        this.callIntNeedType =_v;
    }

    public String getCallIntOnly() {
        return callIntOnly;
    }

    public void setCallIntOnly(String _v) {
        this.callIntOnly =_v;
    }

    public String getBadInheritsType() {
        return badInheritsType;
    }

    public void setBadInheritsType(String _v) {
        this.badInheritsType =_v;
    }

    public String getBadInheritsTypeInn() {
        return badInheritsTypeInn;
    }

    public void setBadInheritsTypeInn(String _v) {
        this.badInheritsTypeInn =_v;
    }

    public String getBadInheritsTypeAsInn() {
        return badInheritsTypeAsInn;
    }

    public void setBadInheritsTypeAsInn(String _v) {
        this.badInheritsTypeAsInn =_v;
    }

    public String getBadInheritsTypeInt() {
        return badInheritsTypeInt;
    }

    public void setBadInheritsTypeInt(String _v) {
        this.badInheritsTypeInt =_v;
    }

    public String getFinalType() {
        return finalType;
    }

    public void setFinalType(String _v) {
        this.finalType =_v;
    }

    public String getDuplicateSuper() {
        return duplicateSuper;
    }

    public void setDuplicateSuper(String _v) {
        this.duplicateSuper =_v;
    }

    public String getReservedType() {
        return reservedType;
    }

    public void setReservedType(String _v) {
        this.reservedType =_v;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String _v) {
        this.superClass =_v;
    }

    public String getUnknownSuperType() {
        return unknownSuperType;
    }

    public void setUnknownSuperType(String _v) {
        this.unknownSuperType =_v;
    }

    public String getCyclicInherits() {
        return cyclicInherits;
    }

    public void setCyclicInherits(String _v) {
        this.cyclicInherits =_v;
    }

    public String getAnnotationParam() {
        return annotationParam;
    }

    public void setAnnotationParam(String _v) {
        this.annotationParam =_v;
    }

    public String getCyclicMapping() {
        return cyclicMapping;
    }

    public void setCyclicMapping(String _v) {
        this.cyclicMapping =_v;
    }

    public String getAbsMapping() {
        return absMapping;
    }

    public void setAbsMapping(String _v) {
        this.absMapping =_v;
    }

    public String getFinalMapping() {
        return finalMapping;
    }

    public void setFinalMapping(String _v) {
        this.finalMapping =_v;
    }

    public String getMustCallIntCtor() {
        return mustCallIntCtor;
    }

    public void setMustCallIntCtor(String _v) {
        this.mustCallIntCtor =_v;
    }

    public String getMustNotCallIntCtorAfterThis() {
        return mustNotCallIntCtorAfterThis;
    }

    public void setMustNotCallIntCtorAfterThis(String _v) {
        this.mustNotCallIntCtorAfterThis =_v;
    }

    public String getMustCallIntCtorNeed() {
        return mustCallIntCtorNeed;
    }

    public void setMustCallIntCtorNeed(String _v) {
        this.mustCallIntCtorNeed =_v;
    }

    public String getMustCallIntCtorNotNeed() {
        return mustCallIntCtorNotNeed;
    }

    public void setMustCallIntCtorNotNeed(String _v) {
        this.mustCallIntCtorNotNeed =_v;
    }

    public String getBadLabel() {
        return badLabel;
    }

    public void setBadLabel(String _v) {
        this.badLabel =_v;
    }

    public String getDuplicatedLabel() {
        return duplicatedLabel;
    }

    public void setDuplicatedLabel(String _v) {
        this.duplicatedLabel =_v;
    }

    public String getBadMethodName() {
        return badMethodName;
    }

    public void setBadMethodName(String _v) {
        this.badMethodName =_v;
    }

    public String getBadOperatorName() {
        return badOperatorName;
    }

    public void setBadOperatorName(String _v) {
        this.badOperatorName =_v;
    }

    public String getBadAccess() {
        return badAccess;
    }

    public void setBadAccess(String _v) {
        this.badAccess =_v;
    }

    public String getBadReturnType() {
        return badReturnType;
    }

    public void setBadReturnType(String _v) {
        this.badReturnType =_v;
    }

    public String getBadParams() {
        return badParams;
    }

    public void setBadParams(String _v) {
        this.badParams =_v;
    }

    public String getBadMethodModifier() {
        return badMethodModifier;
    }

    public void setBadMethodModifier(String _v) {
        this.badMethodModifier =_v;
    }

    public String getBadMethodVararg() {
        return badMethodVararg;
    }

    public void setBadMethodVararg(String _v) {
        this.badMethodVararg =_v;
    }

    public String getBadIndexerParams() {
        return badIndexerParams;
    }

    public void setBadIndexerParams(String _v) {
        this.badIndexerParams =_v;
    }

    public String getBadIndexerModifier() {
        return badIndexerModifier;
    }

    public void setBadIndexerModifier(String _v) {
        this.badIndexerModifier =_v;
    }

    public String getBadIndexerModifiers() {
        return badIndexerModifiers;
    }

    public void setBadIndexerModifiers(String _v) {
        this.badIndexerModifiers =_v;
    }

    public String getBadIndexerAccesses() {
        return badIndexerAccesses;
    }

    public void setBadIndexerAccesses(String _v) {
        this.badIndexerAccesses =_v;
    }

    public String getBadIndexerPairGet() {
        return badIndexerPairGet;
    }

    public void setBadIndexerPairGet(String _v) {
        this.badIndexerPairGet =_v;
    }

    public String getBadIndexerPairSet() {
        return badIndexerPairSet;
    }

    public void setBadIndexerPairSet(String _v) {
        this.badIndexerPairSet =_v;
    }

    public String getDuplicateCustomMethod() {
        return duplicateCustomMethod;
    }

    public void setDuplicateCustomMethod(String _v) {
        this.duplicateCustomMethod =_v;
    }

    public String getReservedCustomMethod() {
        return reservedCustomMethod;
    }

    public void setReservedCustomMethod(String _v) {
        this.reservedCustomMethod =_v;
    }

    public String getDuplicateIndexer() {
        return duplicateIndexer;
    }

    public void setDuplicateIndexer(String _v) {
        this.duplicateIndexer =_v;
    }

    public String getDuplicateOperator() {
        return duplicateOperator;
    }

    public void setDuplicateOperator(String _v) {
        this.duplicateOperator =_v;
    }

    public String getFunctionalApplyNbDiff() {
        return functionalApplyNbDiff;
    }

    public void setFunctionalApplyNbDiff(String _v) {
        this.functionalApplyNbDiff =_v;
    }

    public String getFunctionalApplyOnly() {
        return functionalApplyOnly;
    }

    public void setFunctionalApplyOnly(String _v) {
        this.functionalApplyOnly =_v;
    }

    public String getOperatorNbDiff() {
        return operatorNbDiff;
    }

    public void setOperatorNbDiff(String _v) {
        this.operatorNbDiff =_v;
    }

    public String getSplitComa() {
        return splitComa;
    }

    public void setSplitComa(String _v) {
        this.splitComa =_v;
    }

    public String getSplitComaLow() {
        return splitComaLow;
    }

    public void setSplitComaLow(String _v) {
        this.splitComaLow =_v;
    }

    public String getSplitDiff() {
        return splitDiff;
    }

    public void setSplitDiff(String _v) {
        this.splitDiff =_v;
    }

    public String getBadDotted() {
        return badDotted;
    }

    public void setBadDotted(String _v) {
        this.badDotted =_v;
    }

    public String getBadParamName() {
        return badParamName;
    }

    public void setBadParamName(String _v) {
        this.badParamName =_v;
    }

    public String getReservedParamName() {
        return reservedParamName;
    }

    public void setReservedParamName(String _v) {
        this.reservedParamName =_v;
    }

    public String getDuplicatedParamName() {
        return duplicatedParamName;
    }

    public void setDuplicatedParamName(String _v) {
        this.duplicatedParamName =_v;
    }

    public String getBadReturnTypeInherit() {
        return badReturnTypeInherit;
    }

    public void setBadReturnTypeInherit(String _v) {
        this.badReturnTypeInherit =_v;
    }

    public String getBadReturnTypeIndexer() {
        return badReturnTypeIndexer;
    }

    public void setBadReturnTypeIndexer(String _v) {
        this.badReturnTypeIndexer =_v;
    }

    public String getDuplicatedOverriding() {
        return duplicatedOverriding;
    }

    public void setDuplicatedOverriding(String _v) {
        this.duplicatedOverriding =_v;
    }

    public String getTwoFinal() {
        return twoFinal;
    }

    public void setTwoFinal(String _v) {
        this.twoFinal =_v;
    }

    public String getFinalNotSubReturnType() {
        return finalNotSubReturnType;
    }

    public void setFinalNotSubReturnType(String _v) {
        this.finalNotSubReturnType =_v;
    }

    public String getTwoReturnTypes() {
        return twoReturnTypes;
    }

    public void setTwoReturnTypes(String _v) {
        this.twoReturnTypes =_v;
    }

    public String getReturnTypes() {
        return returnTypes;
    }

    public void setReturnTypes(String _v) {
        this.returnTypes =_v;
    }

    public String getBadVariableName() {
        return badVariableName;
    }

    public void setBadVariableName(String _v) {
        this.badVariableName =_v;
    }

    public String getCyclicCtorCall() {
        return cyclicCtorCall;
    }

    public void setCyclicCtorCall(String _v) {
        this.cyclicCtorCall =_v;
    }

    public String getDeadCode() {
        return deadCode;
    }

    public void setDeadCode(String _v) {
        this.deadCode =_v;
    }

    public String getDeadCodeTernary() {
        return deadCodeTernary;
    }

    public void setDeadCodeTernary(String _v) {
        this.deadCodeTernary = _v;
    }

    public String getUnusedParamStatic() {
        return unusedParamStatic;
    }

    public void setUnusedParamStatic(String _v) {
        this.unusedParamStatic = _v;
    }

    public String getDuplicatedCtor() {
        return duplicatedCtor;
    }

    public void setDuplicatedCtor(String _v) {
        this.duplicatedCtor =_v;
    }

    public String getDuplicatedGenericSuperTypes() {
        return duplicatedGenericSuperTypes;
    }

    public void setDuplicatedGenericSuperTypes(String _v) {
        this.duplicatedGenericSuperTypes =_v;
    }

    public String getDuplicatedInnerType() {
        return duplicatedInnerType;
    }

    public void setDuplicatedInnerType(String _v) {
        this.duplicatedInnerType =_v;
    }

    public String getDuplicatedType() {
        return duplicatedType;
    }

    public void setDuplicatedType(String _v) {
        this.duplicatedType =_v;
    }

    public String getDuplicatedTypePrim() {
        return duplicatedTypePrim;
    }

    public void setDuplicatedTypePrim(String _v) {
        this.duplicatedTypePrim =_v;
    }

    public String getDuplicatedTypeStd() {
        return duplicatedTypeStd;
    }

    public void setDuplicatedTypeStd(String _v) {
        this.duplicatedTypeStd =_v;
    }

    public String getDuplicatedTypePkg() {
        return duplicatedTypePkg;
    }

    public void setDuplicatedTypePkg(String _v) {
        this.duplicatedTypePkg =_v;
    }

    public String getEmptyExpressionPart() {
        return emptyExpressionPart;
    }

    public void setEmptyExpressionPart(String _v) {
        this.emptyExpressionPart =_v;
    }

    public String getDoWhileNotEmpty() {
        return doWhileNotEmpty;
    }

    public void setDoWhileNotEmpty(String _v) {
        this.doWhileNotEmpty =_v;
    }

    public String getDuplicatedFinal() {
        return duplicatedFinal;
    }

    public void setDuplicatedFinal(String _v) {
        this.duplicatedFinal =_v;
    }

    public String getIllegalCtorEnum() {
        return illegalCtorEnum;
    }

    public void setIllegalCtorEnum(String _v) {
        this.illegalCtorEnum =_v;
    }

    public String getIllegalGenericSuperTypeBound() {
        return illegalGenericSuperTypeBound;
    }

    public void setIllegalGenericSuperTypeBound(String _v) {
        this.illegalGenericSuperTypeBound =_v;
    }

    public String getIllegalCtorAnnotation() {
        return illegalCtorAnnotation;
    }

    public void setIllegalCtorAnnotation(String _v) {
        this.illegalCtorAnnotation =_v;
    }

    public String getIllegalCtorAbstract() {
        return illegalCtorAbstract;
    }

    public void setIllegalCtorAbstract(String _v) {
        this.illegalCtorAbstract =_v;
    }

    public String getIllegalCtorBound() {
        return illegalCtorBound;
    }

    public void setIllegalCtorBound(String _v) {
        this.illegalCtorBound =_v;
    }

    public String getIllegalCtorArray() {
        return illegalCtorArray;
    }

    public void setIllegalCtorArray(String _v) {
        this.illegalCtorArray =_v;
    }

    public String getIllegalCtorUnknown() {
        return illegalCtorUnknown;
    }

    public void setIllegalCtorUnknown(String _v) {
        this.illegalCtorUnknown =_v;
    }

    public String getMissingAbrupt() {
        return missingAbrupt;
    }

    public void setMissingAbrupt(String _v) {
        this.missingAbrupt =_v;
    }

    public String getNotInitClass() {
        return notInitClass;
    }

    public void setNotInitClass(String _v) {
        this.notInitClass =_v;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String _v) {
        this.nullValue =_v;
    }

    public String getBadParameTypeForId() {
        return badParameTypeForId;
    }

    public void setBadParameTypeForId(String _v) {
        this.badParameTypeForId =_v;
    }

    public String getNotResolvedOwner() {
        return notResolvedOwner;
    }

    public void setNotResolvedOwner(String _v) {
        this.notResolvedOwner =_v;
    }

    public String getUndefinedAccessibleField() {
        return undefinedAccessibleField;
    }

    public void setUndefinedAccessibleField(String _v) {
        this.undefinedAccessibleField =_v;
    }

    public String getStaticAccess() {
        return staticAccess;
    }

    public void setStaticAccess(String _v) {
        this.staticAccess =_v;
    }

    public String getStaticAccessPrev() {
        return staticAccessPrev;
    }

    public void setStaticAccessPrev(String _v) {
        this.staticAccessPrev =_v;
    }

    public String getUnassignedFinalField() {
        return unassignedFinalField;
    }

    public void setUnassignedFinalField(String _v) {
        this.unassignedFinalField =_v;
    }

    public String getUnassignedInferingType() {
        return unassignedInferingType;
    }

    public void setUnassignedInferingType(String _v) {
        this.unassignedInferingType =_v;
    }

    public String getUndefinedCtor() {
        return undefinedCtor;
    }

    public void setUndefinedCtor(String _v) {
        this.undefinedCtor =_v;
    }

    public String getUndefinedMethod() {
        return undefinedMethod;
    }

    public void setUndefinedMethod(String _v) {
        this.undefinedMethod =_v;
    }

    public String getArrayCloneOnly() {
        return arrayCloneOnly;
    }

    public void setArrayCloneOnly(String _v) {
        this.arrayCloneOnly =_v;
    }

    public String getUndefinedSuperCtor() {
        return undefinedSuperCtor;
    }

    public void setUndefinedSuperCtor(String _v) {
        this.undefinedSuperCtor =_v;
    }

    public String getUndefinedSuperCtorCall() {
        return undefinedSuperCtorCall;
    }

    public void setUndefinedSuperCtorCall(String _v) {
        this.undefinedSuperCtorCall =_v;
    }

    public String getUndefinedVariable() {
        return undefinedVariable;
    }

    public void setUndefinedVariable(String _v) {
        this.undefinedVariable =_v;
    }

    public String getUnexpectedAffect() {
        return unexpectedAffect;
    }

    public void setUnexpectedAffect(String _v) {
        this.unexpectedAffect =_v;
    }

    public String getFinalField() {
        return finalField;
    }

    public void setFinalField(String _v) {
        this.finalField =_v;
    }

    public String getBadOperatorRef() {
        return badOperatorRef;
    }

    public void setBadOperatorRef(String _v) {
        this.badOperatorRef =_v;
    }

    public String getUnexpectedCatchElseFinally() {
        return unexpectedCatchElseFinally;
    }

    public void setUnexpectedCatchElseFinally(String _v) {
        this.unexpectedCatchElseFinally =_v;
    }

    public String getUnexpectedAbrupt() {
        return unexpectedAbrupt;
    }

    public void setUnexpectedAbrupt(String _v) {
        this.unexpectedAbrupt =_v;
    }

    public String getUnexpectedAbruptLab() {
        return unexpectedAbruptLab;
    }

    public void setUnexpectedAbruptLab(String _v) {
        this.unexpectedAbruptLab =_v;
    }

    public String getUnexpectedCaseDef() {
        return unexpectedCaseDef;
    }

    public void setUnexpectedCaseDef(String _v) {
        this.unexpectedCaseDef =_v;
    }

    public String getUnexpectedCaseVar() {
        return unexpectedCaseVar;
    }

    public void setUnexpectedCaseVar(String _v) {
        this.unexpectedCaseVar =_v;
    }

    public String getUnexpectedCaseValue() {
        return unexpectedCaseValue;
    }

    public void setUnexpectedCaseValue(String _v) {
        this.unexpectedCaseValue =_v;
    }

    public String getUnexpectedCaseDup() {
        return unexpectedCaseDup;
    }

    public void setUnexpectedCaseDup(String _v) {
        this.unexpectedCaseDup =_v;
    }

    public String getUnexpectedDefDup() {
        return unexpectedDefDup;
    }

    public void setUnexpectedDefDup(String _v) {
        this.unexpectedDefDup =_v;
    }

    public String getUnexpectedDoTry() {
        return unexpectedDoTry;
    }

    public void setUnexpectedDoTry(String _v) {
        this.unexpectedDoTry =_v;
    }

    public String getUnexpectedSwitch() {
        return unexpectedSwitch;
    }

    public void setUnexpectedSwitch(String _v) {
        this.unexpectedSwitch =_v;
    }

    public String getUnexpectedMemberInst() {
        return unexpectedMemberInst;
    }

    public void setUnexpectedMemberInst(String _v) {
        this.unexpectedMemberInst =_v;
    }

    public String getUnexpectedBlockExp() {
        return unexpectedBlockExp;
    }

    public void setUnexpectedBlockExp(String _v) {
        this.unexpectedBlockExp =_v;
    }

    public String getUnexpectedOperandTypes() {
        return unexpectedOperandTypes;
    }

    public void setUnexpectedOperandTypes(String _v) {
        this.unexpectedOperandTypes =_v;
    }

    public String getUnknownType() {
        return unknownType;
    }

    public void setUnknownType(String _v) {
        this.unknownType =_v;
    }

    public String getEmptyType() {
        return emptyType;
    }

    public void setEmptyType(String _v) {
        this.emptyType =_v;
    }

    public String getBadParamerizedType() {
        return badParamerizedType;
    }

    public void setBadParamerizedType(String _v) {
        this.badParamerizedType =_v;
    }

    public String getUnexpectedTypeBound() {
        return unexpectedTypeBound;
    }

    public void setUnexpectedTypeBound(String _v) {
        this.unexpectedTypeBound =_v;
    }

    public String getUnexpectedVararg() {
        return unexpectedVararg;
    }

    public void setUnexpectedVararg(String _v) {
        this.unexpectedVararg =_v;
    }

    public String getUnexpectedLeaf() {
        return unexpectedLeaf;
    }

    public void setUnexpectedLeaf(String _v) {
        this.unexpectedLeaf =_v;
    }

    public String getEmptyPart() {
        return emptyPart;
    }

    public void setEmptyPart(String _v) {
        emptyPart =_v;
    }

    public String getCaseTypeVar() {
        return caseTypeVar;
    }

    public void setCaseTypeVar(String _v) {
        this.caseTypeVar = _v;
    }

    public String getKeyWordPartClassName() {
        return keyWordPartClassName;
    }

    public void setKeyWordPartClassName(String _v) {
        this.keyWordPartClassName =_v;
    }

    public String getPrimPartClassName() {
        return primPartClassName;
    }

    public void setPrimPartClassName(String _v) {
        this.primPartClassName =_v;
    }

    public String getDigitPartClassName() {
        return digitPartClassName;
    }

    public void setDigitPartClassName(String _v) {
        this.digitPartClassName =_v;
    }

    public String getKeyWordPartVarClassName() {
        return keyWordPartVarClassName;
    }

    public void setKeyWordPartVarClassName(String _v) {
        this.keyWordPartVarClassName =_v;
    }

    public String getPrimPartVarClassName() {
        return primPartVarClassName;
    }

    public void setPrimPartVarClassName(String _v) {
        this.primPartVarClassName =_v;
    }

    public String getDigitPartVarClassName() {
        return digitPartVarClassName;
    }

    public void setDigitPartVarClassName(String _v) {
        this.digitPartVarClassName =_v;
    }

    public String getDuplicatedPartVarClassName() {
        return duplicatedPartVarClassName;
    }

    public void setDuplicatedPartVarClassName(String _v) {
        this.duplicatedPartVarClassName =_v;
    }

    public String getKeyWordFieldName() {
        return keyWordFieldName;
    }

    public void setKeyWordFieldName(String _v) {
        this.keyWordFieldName =_v;
    }

    public String getPrimFieldName() {
        return primFieldName;
    }

    public void setPrimFieldName(String _v) {
        this.primFieldName =_v;
    }

    public String getDigitFieldName() {
        return digitFieldName;
    }

    public void setDigitFieldName(String _v) {
        this.digitFieldName =_v;
    }

    public String getKeyWordMethodName() {
        return keyWordMethodName;
    }

    public void setKeyWordMethodName(String _v) {
        this.keyWordMethodName =_v;
    }

    public String getPrimMethodName() {
        return primMethodName;
    }

    public void setPrimMethodName(String _v) {
        this.primMethodName =_v;
    }

    public String getDigitMethodName() {
        return digitMethodName;
    }

    public void setDigitMethodName(String _v) {
        this.digitMethodName =_v;
    }

    public String getKeyWordParamName() {
        return keyWordParamName;
    }

    public void setKeyWordParamName(String _v) {
        this.keyWordParamName =_v;
    }

    public String getPrimParamName() {
        return primParamName;
    }

    public void setPrimParamName(String _v) {
        this.primParamName =_v;
    }

    public String getDigitParamName() {
        return digitParamName;
    }

    public void setDigitParamName(String _v) {
        this.digitParamName =_v;
    }

    public String getKeyWordVariableName() {
        return keyWordVariableName;
    }

    public void setKeyWordVariableName(String _v) {
        this.keyWordVariableName =_v;
    }

    public String getPrimVariableName() {
        return primVariableName;
    }

    public void setPrimVariableName(String _v) {
        this.primVariableName =_v;
    }

    public String getDigitVariableName() {
        return digitVariableName;
    }

    public void setDigitVariableName(String _v) {
        this.digitVariableName =_v;
    }

    public String getDuplicatedVariableName() {
        return duplicatedVariableName;
    }

    public void setDuplicatedVariableName(String _v) {
        this.duplicatedVariableName =_v;
    }
}
