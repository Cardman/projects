package code.expressionlanguage.errors;

import code.expressionlanguage.ContextEl;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AnalysisMessages {
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
    public void validateMessageContents(ContextEl _cont, StringMap<String> _list) {
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                _cont.getClasses().addMessageError(StringList.concat("empty word ",key_));
            }
        }
    }
    public StringMap<String> allMessages() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry("DuplicateMergedMethod",duplicateMergedMethod);
        mess_.addEntry("DuplicateField",duplicateField);
        mess_.addEntry("DuplicateVarType",duplicateVarType);
        mess_.addEntry("EmptyPkgRefType",emptyPkgRefType);
        mess_.addEntry("PrimitiveKeyWord",primitiveKeyWord);
        mess_.addEntry("DefaultPkgRefType",defaultPkgRefType);
        mess_.addEntry("RefTypeKeyWord",refTypeKeyWord);
        mess_.addEntry("DigitFirstPrimitive",digitFirstPrimitive);
        mess_.addEntry("IllegalFirstChar",illegalFirstChar);
        mess_.addEntry("EmptyPrimitive",emptyPrimitive);
        mess_.addEntry("NotWordCharPrimitive",notWordCharPrimitive);
        mess_.addEntry("NotWordCharRefType",notWordCharRefType);
        mess_.addEntry("EmptyRefTypeIn",emptyRefTypeIn);
        mess_.addEntry("RefTypePrimitive",refTypePrimitive);
        mess_.addEntry("DigitFirstRefType",digitFirstRefType);
        mess_.addEntry("DuplicatePrimtive",duplicatePrimtive);
        mess_.addEntry("DuplicateMethod",duplicateMethod);
        mess_.addEntry("DefaultPkgNoMatch",defaultPkgNoMatch);
        mess_.addEntry("DuplicateRefType",duplicateRefType);
        mess_.addEntry("DigitFirstMethod",digitFirstMethod);
        mess_.addEntry("NotWordCharField",notWordCharField);
        mess_.addEntry("NotWordCharMethod",notWordCharMethod);
        mess_.addEntry("VarTypeKeyWord",varTypeKeyWord);
        mess_.addEntry("VarTypePrimitive",varTypePrimitive);
        mess_.addEntry("DigitFirstVarType",digitFirstVarType);
        mess_.addEntry("DuplicateNumberWord",duplicateNumberWord);
        mess_.addEntry("MethodPrimitive",methodPrimitive);
        mess_.addEntry("FieldPrimitive",fieldPrimitive);
        mess_.addEntry("DuplicateStringWord",duplicateStringWord);
        mess_.addEntry("DuplicateKeyWord",duplicateKeyWord);
        mess_.addEntry("DigitFirstField",digitFirstField);
        mess_.addEntry("DuplicateStartingNb",duplicateStartingNb);
        mess_.addEntry("DuplicateStartingUni",duplicateStartingUni);
        mess_.addEntry("DuplicateStarting",duplicateStarting);
        mess_.addEntry("NotWordCharVarType",notWordCharVarType);
        mess_.addEntry("EmptyPreBin",emptyPreBin);
        mess_.addEntry("EmptyVarType",emptyVarType);
        mess_.addEntry("EmptyWord",emptyWord);
        mess_.addEntry("EmptyField",emptyField);
        mess_.addEntry("EmptyNb",emptyNb);
        mess_.addEntry("NotWordChar",notWordChar);
        mess_.addEntry("EmptyPreHex",emptyPreHex);
        mess_.addEntry("EmptyMethod",emptyMethod);
        mess_.addEntry("DigitFirst",digitFirst);
        mess_.addEntry("EmptyBinExp",emptyBinExp);
        mess_.addEntry("EmptyString",emptyString);
        mess_.addEntry("IllegalChar",illegalChar);
        mess_.addEntry("MethodKeyWord",methodKeyWord);
        mess_.addEntry("EmptyRefType",emptyRefType);
        mess_.addEntry("FieldKeyWord",fieldKeyWord);
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
}
