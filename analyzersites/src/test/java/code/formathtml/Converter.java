package code.formathtml;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import code.serialize.ConstClasses;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class Converter {

    private static final char END_LINE = ';';
    private static final char LINE_RETURN = '\n';
    private static final char BEGIN_COMMENT = '/';
    private static final char SECOND_COMMENT = '*';
    private static final char BEGIN_BLOCK = '{';
    private static final char END_BLOCK = '}';
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char ESCAPE = '\\';
    private static final String KEY_WORD_PUBLIC = "public";
    private static final String KEY_WORD_PROTECTED = "protected";
    private static final String KEY_WORD_PRIVATE = "private";
    private Converter() {
    }
    public static String convertFile(String _fileContent, String _typeName, StringMap<BooleanList> _getSet) {
        StringBuilder str_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int len_ = _fileContent.length();
        boolean commentedSingleLine_ = false;
        boolean commentedMultiLine_ = false;
        Numbers<Integer> braces_ = new Numbers<Integer>();
        while (i_ < len_) {
            char currentChar_ = _fileContent.charAt(i_);
            if (commentedSingleLine_) {
                str_.append(currentChar_);
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                }
                i_++;
                continue;
            }
            if (commentedMultiLine_) {
                str_.append(currentChar_);
                if (currentChar_ == SECOND_COMMENT) {
                    char nextChar_ = _fileContent.charAt(i_ + 1);
                    if (nextChar_ == BEGIN_COMMENT) {
                        str_.append(nextChar_);
                        commentedMultiLine_ = false;
                        i_++;
                        i_++;
                        continue;
                    }
                }
                i_++;
                continue;
            }
            if (currentChar_ == BEGIN_COMMENT) {
                str_.append(currentChar_);
                char nextChar_ = _fileContent.charAt(i_ + 1);
                if (nextChar_ == BEGIN_COMMENT) {
                    commentedSingleLine_ = true;
                    str_.append(nextChar_);
                    i_++;
                    i_++;
                    continue;
                }
                commentedMultiLine_ = true;
                str_.append(nextChar_);
                i_++;
                i_++;
                continue;
            }
            if (Character.isWhitespace(currentChar_)) {
                str_.append(currentChar_);
                i_++;
                continue;
            }
            if (currentChar_ == '@') {
                int j_ = i_ + 1;
                while (j_ < len_) {
                    currentChar_ = _fileContent.charAt(j_);
                    if (!StringList.isWordChar(currentChar_)) {
                        break;
                    }
                    j_++;
                }
                i_ = j_;
                continue;
            }
            if (currentChar_ == BEGIN_BLOCK) {
                str_.append(currentChar_);
                braces_.add(i_);
                i_++;
                break;
            }
            str_.append(currentChar_);
            i_++;
        }
        StringBuilder instruction_ = new StringBuilder();
        boolean constChar_ = false;
        boolean constString_ = false;
        while (i_ < len_) {
            char currentChar_ = _fileContent.charAt(i_);
            if (commentedSingleLine_) {
                instruction_.append(currentChar_);
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                }
                i_++;
                continue;
            }
            if (commentedMultiLine_) {
                instruction_.append(currentChar_);
                if (currentChar_ == SECOND_COMMENT) {
                    char nextChar_ = _fileContent.charAt(i_ + 1);
                    if (nextChar_ == BEGIN_COMMENT) {
                        instruction_.append(nextChar_);
                        commentedMultiLine_ = false;
                        i_++;
                        i_++;
                        continue;
                    }
                }
                i_++;
                continue;
            }
            boolean const_ = false;
            if (constString_ || constChar_) {
                const_ = true;
            }
            if (currentChar_ == BEGIN_COMMENT && !const_) {
                char nextChar_ = _fileContent.charAt(i_ + 1);
                if (nextChar_ == BEGIN_COMMENT) {
                    instruction_.append(currentChar_);
                    commentedSingleLine_ = true;
                    instruction_.append(nextChar_);
                    i_++;
                    i_++;
                    continue;
                }
                if (nextChar_ == SECOND_COMMENT) {
                    instruction_.append(currentChar_);
                    commentedMultiLine_ = true;
                    instruction_.append(nextChar_);
                    i_++;
                    i_++;
                    continue;
                }
            }
            if (currentChar_ == '@' && !const_) {
                instruction_.append(currentChar_);
                int j_ = i_ + 1;
                while (j_ < len_) {
                    currentChar_ = _fileContent.charAt(j_);
                    if (!StringList.isWordChar(currentChar_)) {
                        break;
                    }
                    instruction_.append(currentChar_);
                    j_++;
                }
                i_ = j_;
                continue;
            }
            if (currentChar_ == BEGIN_BLOCK) {
                braces_.add(i_);
            }
            if (currentChar_ == END_BLOCK) {
                braces_.removeLast();
            }
            boolean endInstruction_ = false;
            if (braces_.size() == 1 && !const_) {
                if (currentChar_ == END_LINE) {
                    endInstruction_ = true;
                }
                if (currentChar_ == END_BLOCK) {
                    endInstruction_ = true;
                }
                //End line
            }
            if (braces_.size() == 0 && currentChar_ == END_BLOCK) {
                //add getters and setters here
                //
                for (EntryCust<String, BooleanList> s: _getSet.entryList()) {
                    String key_ = s.getKey();
                    String className_ = key_.substring(0, key_.lastIndexOf("."));
                    if (!className_.equalsIgnoreCase(_typeName)) {
                        continue;
                    }
                    String fieldName_ = key_.substring(key_.lastIndexOf(".") + 1);
                    Class<?> foundClass_ = ConstClasses.classForNameNotInit(className_);
                    String clRet_;
                    try {
                        Type field_ = foundClass_.getDeclaredField(fieldName_).getGenericType();
                        if (field_ instanceof Class<?>) {
                            clRet_ = ((Class<?>) field_).getSimpleName();
                        } else {
                            clRet_ = field_.toString();
                        }
                    } catch (Exception _0_) {
                        clRet_ = "";
                    }
                    String imported_;
                    if (!clRet_.contains(".")) {
                        imported_ = clRet_;
                    } else {
                        StringBuilder importedType_ = new StringBuilder();
                        int j_ = 0;
                        for (String p: StringList.splitStringsSep(clRet_, "<",">",",")) {
                            if (j_ % 2 == 0) {
                                importedType_.append(p.substring(p.lastIndexOf(".") + 1));
                            } else {
                                importedType_.append(p);
                            }
                            j_++;
                        }
                        imported_ = importedType_.toString();
                    }
                    imported_ = StringList.removeAllSpaces(imported_);
                    for (boolean w: s.getValue()) {
                        if (!w) {
                            char ch_ = fieldName_.charAt(0);
                            String next_ = fieldName_.substring(1);
                            String methodName_ = "get"+Character.toUpperCase(ch_)+next_;
                            boolean add_ = true;
                            for (Method m :foundClass_.getDeclaredMethods()) {
                                if (StringList.quickEq(m.getName(), methodName_) && m.getParameterTypes().length == 0) {
                                    add_ = false;
                                    break;
                                }
                            }
                            if (!add_) {
                                continue;
                            }
                            StringBuilder getter_ = new StringBuilder();
                            getter_.append("\n    public ");
                            //return type
                            getter_.append(imported_);
                            getter_.append(" ");
                            getter_.append(methodName_);
                            getter_.append("() {\n");
                            getter_.append("        return ");
                            getter_.append(fieldName_);
                            getter_.append(";\n");
                            getter_.append("    }\n");
                            instruction_.append(getter_);
                        } else {
                            char ch_ = fieldName_.charAt(0);
                            String next_ = fieldName_.substring(1);
                            String methodName_ = "set"+Character.toUpperCase(ch_)+next_;
                            boolean add_ = true;
                            for (Method m :foundClass_.getDeclaredMethods()) {
                                if (StringList.quickEq(m.getName(), methodName_) && m.getParameterTypes().length == 1) {
                                    add_ = false;
                                    break;
                                }
                            }
                            if (!add_) {
                                continue;
                            }
                            StringBuilder setter_ = new StringBuilder();
                            setter_.append("\n    public void ");
                            setter_.append(methodName_);
                            setter_.append("(");
                            setter_.append(imported_);
                            setter_.append(" _");
                            setter_.append(fieldName_);
                            setter_.append(") {\n");
                            setter_.append("        ");
                            setter_.append(fieldName_);
                            setter_.append(" = _");
                            setter_.append(fieldName_);
                            setter_.append(";\n");
                            setter_.append("    }\n");
                            instruction_.append(setter_);
                        }
                    }
                }
                instruction_.append(currentChar_);
                str_.append(instruction_);
                break;
            }
            if (endInstruction_) {
                instruction_.append(currentChar_);
                StringBuilder newInstr_ = new StringBuilder();
                int lenLoc_ = instruction_.length();
                int j_ = 0;
                if (currentChar_ == END_LINE) {
                    while (j_ < lenLoc_) {
                        char curLoc_ = instruction_.charAt(j_);
                        if (commentedSingleLine_) {
                            newInstr_.append(curLoc_);
                            if (curLoc_ == LINE_RETURN) {
                                commentedSingleLine_ = false;
                            }
                            j_++;
                            continue;
                        }
                        if (commentedMultiLine_) {
                            newInstr_.append(curLoc_);
                            if (curLoc_ == SECOND_COMMENT) {
                                char nextChar_ = instruction_.charAt(j_ + 1);
                                if (nextChar_ == BEGIN_COMMENT) {
                                    newInstr_.append(nextChar_);
                                    commentedMultiLine_ = false;
                                    j_++;
                                    j_++;
                                    continue;
                                }
                            }
                            j_++;
                            continue;
                        }
                        boolean constLoc_ = false;
                        if (constString_ || constChar_) {
                            constLoc_ = true;
                        }
                        if (curLoc_ == BEGIN_COMMENT && !constLoc_) {
                            char nextChar_ = instruction_.charAt(j_ + 1);
                            if (nextChar_ == BEGIN_COMMENT) {
                                newInstr_.append(curLoc_);
                                commentedSingleLine_ = true;
                                newInstr_.append(nextChar_);
                                j_++;
                                j_++;
                                continue;
                            }
                            if (nextChar_ == SECOND_COMMENT) {
                                newInstr_.append(curLoc_);
                                commentedMultiLine_ = true;
                                newInstr_.append(nextChar_);
                                j_++;
                                j_++;
                                continue;
                            }
                        }
                        if (curLoc_ == '@' && !constLoc_) {
                            newInstr_.delete(0, newInstr_.length());
                            int k_ = j_ + 1;
                            while (k_ < lenLoc_) {
                                char locCurrentChar_ = instruction_.charAt(k_);
                                if (!StringList.isWordChar(locCurrentChar_)) {
                                    break;
                                }
                                k_++;
                            }
                            j_ = k_;
                            continue;
                        }
                        if (!constLoc_) {
                            newInstr_.append(curLoc_);
                        }
                        if (constChar_) {
                            newInstr_.append(curLoc_);
                            if (curLoc_ == ESCAPE) {
                                newInstr_.append(instruction_.charAt(j_+1));
                                j_++;
                                j_++;
                                continue;
                            }
                            if (curLoc_ == DEL_CHAR) {
                                j_++;
                                constChar_ = false;
                                continue;
                            }
                            j_++;
                            continue;
                        }
                        if (constString_) {
                            newInstr_.append(curLoc_);
                            if (curLoc_ == ESCAPE) {
                                newInstr_.append(instruction_.charAt(j_+1));
                                j_++;
                                j_++;
                                continue;
                            }
                            if (curLoc_ == DEL_STRING) {
                                j_++;
                                constString_ = false;
                                continue;
                            }
                            j_++;
                            continue;
                        }
                        if (curLoc_ == DEL_CHAR) {
                            constChar_ = true;
                        }
                        if (curLoc_ == DEL_STRING) {
                            constString_ = true;
                        }
                        j_++;
                    }
                    str_.append(newInstr_);
                } else {
                    while (j_ < lenLoc_) {
                        char curLoc_ = instruction_.charAt(j_);
                        if (curLoc_ == '@') {
                            int k_ = j_ + 1;
                            StringBuilder type_ = new StringBuilder();
                            while (k_ < lenLoc_) {
                                char locCurrentChar_ = instruction_.charAt(k_);
                                if (!StringList.isWordChar(locCurrentChar_)) {
                                    break;
                                }
                                type_.append(locCurrentChar_);
                                k_++;
                            }
                            if (StringList.quickEq(type_.toString(), "Accessible")) {
//                                addPublic_ = true;
                                newInstr_.delete(0, newInstr_.length());
                                while (k_ < lenLoc_) {
                                    char locCurrentChar_ = instruction_.charAt(k_);
                                    if (!Character.isWhitespace(locCurrentChar_)) {
                                        break;
                                    }
                                    newInstr_.append(locCurrentChar_);
                                    k_++;
                                }
                                StringBuilder access_ = new StringBuilder();
                                int begin_ = k_;
                                while (k_ < lenLoc_) {
                                    char locCurrentChar_ = instruction_.charAt(k_);
                                    if (!StringList.isWordChar(locCurrentChar_)) {
                                        break;
                                    }
                                    access_.append(locCurrentChar_);
                                    k_++;
                                }
                                if (StringList.quickEq(access_.toString(), KEY_WORD_PUBLIC)) {
                                    newInstr_.append(instruction_.substring(begin_));
                                    break;
                                } else if (StringList.quickEq(access_.toString(), KEY_WORD_PROTECTED)) {
                                    newInstr_.append(KEY_WORD_PUBLIC);
                                    newInstr_.append(instruction_.substring(k_));
                                    break;
                                } else if (StringList.quickEq(access_.toString(), KEY_WORD_PRIVATE)) {
                                    newInstr_.append(KEY_WORD_PUBLIC);
                                    newInstr_.append(instruction_.substring(k_));
                                    break;
                                } else {
                                    newInstr_.append(KEY_WORD_PUBLIC);
                                    newInstr_.append(" ");
                                    newInstr_.append(instruction_.substring(begin_));
                                    break;
                                }
                            } else {
                                newInstr_.append("@");
                                newInstr_.append(type_);
                                newInstr_.append(instruction_.substring(k_));
                                break;
                            }
                        }
                        if (StringList.isWordChar(curLoc_)) {
                            newInstr_.delete(0, newInstr_.length());
                            newInstr_.append(instruction_);
                            break;
                        }
                        newInstr_.append(curLoc_);
                        j_++;
                    }
                    str_.append(newInstr_);
                }
                instruction_.delete(0, instruction_.length());
            } else if (!const_) {
                instruction_.append(currentChar_);
            }
            if (constChar_) {
                instruction_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    instruction_.append(_fileContent.charAt(i_+1));
                    i_++;
                    i_++;
                    continue;
                }
                if (currentChar_ == DEL_CHAR) {
                    i_++;
                    constChar_ = false;
                    continue;
                }
                i_++;
                continue;
            }
            if (constString_) {
                instruction_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    instruction_.append(_fileContent.charAt(i_+1));
                    i_++;
                    i_++;
                    continue;
                }
                if (currentChar_ == DEL_STRING) {
                    i_++;
                    constString_ = false;
                    continue;
                }
                i_++;
                continue;
            }
            if (currentChar_ == DEL_CHAR) {
                constChar_ = true;
            }
            if (currentChar_ == DEL_STRING) {
                constString_ = true;
            }
            i_++;
        }
        return str_.toString();
    }
}
