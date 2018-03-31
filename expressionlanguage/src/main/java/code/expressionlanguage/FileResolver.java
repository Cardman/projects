package code.expressionlanguage;

import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.Affectation;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.BreakBlock;
import code.expressionlanguage.methods.CaseCondition;
import code.expressionlanguage.methods.CatchEval;
import code.expressionlanguage.methods.ClassBlock;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.ContinueBlock;
import code.expressionlanguage.methods.DeclareAffectVariable;
import code.expressionlanguage.methods.DeclareVariable;
import code.expressionlanguage.methods.DefaultCondition;
import code.expressionlanguage.methods.DoBlock;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.ElseCondition;
import code.expressionlanguage.methods.ElseIfCondition;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.methods.FinallyEval;
import code.expressionlanguage.methods.ForEachLoop;
import code.expressionlanguage.methods.ForIterativeLoop;
import code.expressionlanguage.methods.IfCondition;
import code.expressionlanguage.methods.InstanceBlock;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.Line;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.ReturnMehod;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.SemiAffectation;
import code.expressionlanguage.methods.StaticBlock;
import code.expressionlanguage.methods.SwitchBlock;
import code.expressionlanguage.methods.Throwing;
import code.expressionlanguage.methods.TryEval;
import code.expressionlanguage.methods.WhileCondition;
import code.sml.RowCol;
import code.util.CustList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;

public final class FileResolver {

    private static final char INHERIT = ':';
    private static final char FOR_BLOCKS = ':';
    private static final char END_LINE = ':';
    private static final char END_IMPORTS = ';';
    private static final char LINE_RETURN = '\n';
    private static final char TAB = '\t';
    private static final char BEGIN_COMMENT = '/';
    private static final char SECOND_COMMENT = '*';
    private static final char PKG = '.';
    private static final String EMPTY_STRING = "";
    private static final String VARARG = "...";
    private static final String NEW = "$new";
    private static final char SEP_ENUM_CONST = ',';
    private static final char BEGIN_TEMPLATE = '<';
    private static final char END_TEMPLATE = '>';
    private static final char BEGIN_BLOCK = '{';
    private static final char END_BLOCK = '}';
    private static final char BEGIN_ARRAY = '[';
    private static final char GET_VAR = ';';
    private static final char END_ARRAY = ']';
    private static final char BEGIN_CALLING = '(';
    private static final char SEP_CALLING = ',';
    private static final char END_CALLING = ')';
    private static final char PART_SEPARATOR = '=';
    private static final char PLUS_CHAR = '+';
    private static final char MINUS_CHAR = '-';
    private static final char MULT_CHAR = '*';
    private static final char DIV_CHAR = '/';
    private static final char MOD_CHAR = '%';
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char ESCAPE = '\\';
    private static final String KEY_WORD_INTERFACES = "interfaces";
    private static final String INCR = "++";
    private static final String DECR = "--";
    private static final char KEY_WORD_PREFIX = '$';
    private static final String KEY_WORD_PUBLIC = "public";
    private static final String KEY_WORD_PACKAGE = "package";
    private static final String KEY_WORD_PROTECTED = "protected";
    private static final String KEY_WORD_PRIVATE = "private";
    private static final String KEY_WORD_INTERFACE = "interface";
    private static final String KEY_WORD_CLASS = "class";
    private static final String KEY_WORD_ENUM = "enum";
    private static final String KEY_WORD_STATIC = "static";
    private static final String KEY_WORD_ABSTRACT = "abstract";
    private static final String KEY_WORD_FINAL = "final";
    private static final String KEY_WORD_NORMAL = "normal";

    private static final String KEY_WORD_FOR = "for";
    private static final String KEY_WORD_FOREACH = "foreach";
    private static final String KEY_WORD_WHILE = "while";
    private static final String KEY_WORD_DO = "do";

    private static final String KEY_WORD_IF = "if";
    private static final String KEY_WORD_ELSE = "else";
    private static final String KEY_WORD_ELSEIF = "elseif";

    private static final String KEY_WORD_TRY = "try";
    private static final String KEY_WORD_FINALLY = "finally";

    private static final String KEY_WORD_CATCH = "catch";

    private static final String KEY_WORD_SWITCH = "switch";
    private static final String KEY_WORD_CASE = "case";
    private static final String KEY_WORD_DEFAULT = "default";

    private static final String KEY_WORD_RETURN = "return";
    private static final String KEY_WORD_THROW = "throw";
    private static final String KEY_WORD_BREAK = "break";
    private static final String KEY_WORD_CONTINUE = "continue";

    private FileResolver(){
    }
    public static void parseFile(String _fileName, String _file, boolean _predefined, ContextEl _context) {
        ObjectMap<FileRowCol, String> importedTypes_ = new ObjectMap<FileRowCol, String>();
        StringBuilder str_ = new StringBuilder();
        boolean allowedComments_ = true;
        char previousChar_ = LINE_RETURN;
        int i_ = CustList.FIRST_INDEX;
        int len_ = _file.length();
        boolean commentedSingleLine_ = false;
        boolean commentedMultiLine_ = false;
        RowCol readRc_ = new RowCol();
        readRc_.setCol(1);
        readRc_.setRow(1);
        int tabWidth_ = _context.getTabWidth();
        int rowBegImport_ = 1;
        int colBegImport_ = 1;
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (currentChar_ == KEY_WORD_PREFIX) {
                break;
            }
            if (commentedSingleLine_) {
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                continue;
            }
            if (commentedMultiLine_) {
                if (currentChar_ == SECOND_COMMENT) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        return;
                    }
                    char nextChar_ = _file.charAt(i_ + 1);
                    if (nextChar_ == BEGIN_COMMENT) {
                        commentedMultiLine_ = false;
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                        previousChar_ = nextChar_;
                        continue;
                    }
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                continue;
            }
            if (currentChar_ == BEGIN_COMMENT) {
                if (!allowedComments_) {
                    //ERROR
                    return;
                }
                if (i_ + 1 >= len_) {
                    //ERROR
                    return;
                }
                char nextChar_ = _file.charAt(i_ + 1);
                if (nextChar_ == BEGIN_COMMENT) {
                    commentedSingleLine_ = true;
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                    previousChar_ = nextChar_;
                    continue;
                }
                if (nextChar_ == SECOND_COMMENT) {
                    commentedMultiLine_ = true;
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                    previousChar_ = nextChar_;
                    continue;
                }
                //ERROR
                return;
            }
            if (currentChar_ == END_IMPORTS) {
                RowCol rc_ = new RowCol();
                rc_.setCol(colBegImport_);
                rc_.setRow(rowBegImport_);
                FileRowCol frc_ = new FileRowCol(_fileName, rc_);
                importedTypes_.put(frc_, str_.toString());
                str_.delete(0, str_.length());
            } else {
                if (!Character.isWhitespace(currentChar_)) {
                    allowedComments_ = false;
                    if (str_.length() == 0) {
                        rowBegImport_ = readRc_.getRow();
                        colBegImport_ = readRc_.getCol();
                    }
                    str_.append(currentChar_);
                } else if (currentChar_ == LINE_RETURN && previousChar_ == END_IMPORTS) {
                    allowedComments_ = true;
                }
            }
            previousChar_ = currentChar_;
            i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
        }
        if (i_ >= len_) {
            return;
        }
        InputTypeCreation input_ = new InputTypeCreation();
        input_.setNextRowCol(new RowCol());
        input_.getNextRowCol().setRow(readRc_.getRow());
        input_.getNextRowCol().setCol(readRc_.getCol());
        input_.setNextIndex(i_);
        while (true) {
            input_.setNextRowCol(new RowCol());
            input_.getNextRowCol().setRow(readRc_.getRow());
            input_.getNextRowCol().setCol(readRc_.getCol());
            input_.setNextIndex(i_);
            ResultTypeCreation res_ = createType(_context, _fileName, _file, input_, importedTypes_);
            if (!res_.isOk()) {
                return;
            }
            _context.getClasses().processBracedClass(res_.getType(), _predefined, _context);
            i_ = res_.getNextIndex();
            boolean hasNext_ = false;
            while (i_ < len_) {
                char currentChar_ = _file.charAt(i_);
                if (currentChar_ == KEY_WORD_PREFIX) {
                    hasNext_ = true;
                    break;
                }
                if (commentedSingleLine_) {
                    if (currentChar_ == LINE_RETURN) {
                        commentedSingleLine_ = false;
                    }
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                    continue;
                }
                if (commentedMultiLine_) {
                    if (currentChar_ == SECOND_COMMENT) {
                        if (i_ + 1 >= len_) {
                            //ERROR
                            return;
                        }
                        char nextChar_ = _file.charAt(i_ + 1);
                        if (nextChar_ == BEGIN_COMMENT) {
                            commentedMultiLine_ = false;
                            i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                            i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                            previousChar_ = nextChar_;
                            continue;
                        }
                    }
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                    continue;
                }
                if (currentChar_ == BEGIN_COMMENT) {
                    if (!allowedComments_) {
                        //ERROR
                        return;
                    }
                    if (i_ + 1 >= len_) {
                        //ERROR
                        return;
                    }
                    char nextChar_ = _file.charAt(i_ + 1);
                    if (nextChar_ == BEGIN_COMMENT) {
                        commentedSingleLine_ = true;
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                        previousChar_ = nextChar_;
                        continue;
                    }
                    if (nextChar_ == SECOND_COMMENT) {
                        commentedMultiLine_ = true;
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
                        previousChar_ = nextChar_;
                        continue;
                    }
                    //ERROR
                    return;
                }
                if (!Character.isWhitespace(currentChar_)) {
                    allowedComments_ = false;
                    if (str_.length() == 0) {
                        rowBegImport_ = readRc_.getRow();
                        colBegImport_ = readRc_.getCol();
                    }
                    str_.append(currentChar_);
                } else if (currentChar_ == LINE_RETURN && previousChar_ == END_IMPORTS) {
                    allowedComments_ = true;
                }
                previousChar_ = currentChar_;
                i_ = incrementRowCol(i_, _file, tabWidth_, readRc_);
            }
            if (!hasNext_) {
                return;
            }
            input_.getNextRowCol().setRow(readRc_.getRow());
            input_.getNextRowCol().setCol(readRc_.getCol());
            input_.setNextIndex(i_);
        }
    }
    private static ResultTypeCreation createType(ContextEl _context, String _fileName, String _file, InputTypeCreation _input, ObjectMap<FileRowCol, String> _import) {
        boolean enabledTab_ = true;
        ResultTypeCreation out_ = new ResultTypeCreation();
        AccessEnum access_;
        int i_ = _input.getNextIndex();
        RowCol current_ = _input.getNextRowCol();
        int tabWidth_ = _context.getTabWidth();
        int len_ = _file.length();
        int nextIndex_ = i_ + 1;
        if (_file.substring(nextIndex_).startsWith(KEY_WORD_PUBLIC)) {
            access_ = AccessEnum.PUBLIC;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_PUBLIC.length(), _file, tabWidth_, current_);
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_PROTECTED)) {
            access_ = AccessEnum.PROTECTED;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_PROTECTED.length(), _file, tabWidth_, current_);
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_PACKAGE)) {
            access_ = AccessEnum.PACKAGE;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_PACKAGE.length(), _file, tabWidth_, current_);
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_PRIVATE)) {
            access_ = AccessEnum.PRIVATE;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_PRIVATE.length(), _file, tabWidth_, current_);
        } else {
            //ERROR
            return out_;
        }
        if (!Character.isWhitespace(_file.charAt(nextIndex_))) {
            //ERROR
            return out_;
        }
        while (nextIndex_ < len_) {
            char currentChar_ = _file.charAt(nextIndex_);
            if (currentChar_ == LINE_RETURN) {
                enabledTab_ = true;
            } else if (currentChar_ != TAB) {
                enabledTab_ = false;
            } else if (!enabledTab_) {
                //ERROR
                return out_;
            }
            if (!Character.isWhitespace(currentChar_)) {
                break;
            }
            nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_);
        }
        if (nextIndex_ > len_) {
            //ERROR
            return out_;
        }
        if (_file.charAt(nextIndex_) != KEY_WORD_PREFIX) {
            //ERROR
            return out_;
        }
        char currentChar_ = _file.charAt(nextIndex_);
        nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_);
        boolean abstractType_ = false;
        boolean finalType_ = false;
        if (_file.substring(nextIndex_).startsWith(KEY_WORD_ABSTRACT)) {
            abstractType_ = true;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_ABSTRACT.length(), _file, tabWidth_, current_);
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_FINAL)) {
            finalType_ = true;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_FINAL.length(), _file, tabWidth_, current_);
        }
        if (abstractType_ || finalType_) {
            while (nextIndex_ < len_) {
                currentChar_ = _file.charAt(nextIndex_);
                if (currentChar_ == LINE_RETURN) {
                    enabledTab_ = true;
                } else if (currentChar_ != TAB) {
                    enabledTab_ = false;
                } else if (!enabledTab_) {
                    //ERROR
                    return out_;
                }
                if (!Character.isWhitespace(currentChar_)) {
                    break;
                }
                nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_);
            }
            if (nextIndex_ > len_) {
                //ERROR
                return out_;
            }
            if (_file.charAt(nextIndex_) != KEY_WORD_PREFIX) {
                //ERROR
                return out_;
            }
            nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_);
        }
        currentChar_ = _file.charAt(nextIndex_);
        String type_;
        if (_file.substring(nextIndex_).startsWith(KEY_WORD_CLASS)) {
            type_ = KEY_WORD_CLASS;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_CLASS.length(), _file, tabWidth_, current_);
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_ENUM)) {
            type_ = KEY_WORD_ENUM;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_ENUM.length(), _file, tabWidth_, current_);
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_INTERFACE)) {
            type_ = KEY_WORD_INTERFACE;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_INTERFACE.length(), _file, tabWidth_, current_);
        } else {
            //ERROR
            return out_;
        }
        enabledTab_ = true;
        while (nextIndex_ < len_) {
            currentChar_ = _file.charAt(nextIndex_);
            if (currentChar_ == LINE_RETURN) {
                enabledTab_ = true;
            } else if (currentChar_ != TAB) {
                enabledTab_ = false;
            } else if (!enabledTab_){
                //ERROR
                return out_;
            }
            if (Character.isWhitespace(currentChar_)) {
                nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_);
                continue;
            }
            enabledTab_ = false;
            break;
        }
        if (!StringList.isWordChar(_file.charAt(nextIndex_)) && _file.charAt(nextIndex_) != KEY_WORD_PREFIX) {
            //ERROR
            return out_;
        }
        StringBuilder str_ = new StringBuilder();
        ObjectMap<FileIndex, String> superTypes_ = new ObjectMap<FileIndex, String>();
        StringBuilder typeNamePref_ = new StringBuilder();
        StringBuilder templateDef_ = new StringBuilder();
        int nbOpened_ = 0;
        boolean ok_ = false;
        boolean foundInherit_ = false;
        Numbers<Integer> braces_ = new Numbers<Integer>();
        boolean allowedComments_ = false;
        int beginDefinition_ = nextIndex_;
        while (nextIndex_ < len_) {
            currentChar_ = _file.charAt(nextIndex_);
            if (currentChar_ == LINE_RETURN) {
                enabledTab_ = true;
            } else if (currentChar_ != TAB) {
                enabledTab_ = false;
            } else if (!enabledTab_) {
                //ERROR
                return out_;
            }
            if (currentChar_ == BEGIN_TEMPLATE) {
                nbOpened_++;
            }
            if (nbOpened_ > 0) {
                if (!foundInherit_) {
                    templateDef_.append(currentChar_);
                }
            } else if (templateDef_.length() == 0 && currentChar_ != BEGIN_BLOCK) {
                if (!foundInherit_ && currentChar_ != INHERIT) {
                    if (typeNamePref_.length() == 0) {
                        beginDefinition_ = nextIndex_;
                    }
                    typeNamePref_.append(currentChar_);
                }
            }
            if (currentChar_ == END_TEMPLATE) {
                nbOpened_--;
            }
            if (currentChar_ == INHERIT && nbOpened_ == 0) {
                if (foundInherit_) {
                    FileIndex frc_ = new FileIndex(_fileName, nextIndex_);
                    superTypes_.put(frc_, str_.toString());
                }
                str_.delete(0, str_.length());
                foundInherit_ = true;
                nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_);
                continue;
            }
            if (currentChar_ == BEGIN_BLOCK) {
                braces_.add(nextIndex_);
                ok_ = true;
                if (nextIndex_ + 1 < len_) {
                    if (_file.charAt(nextIndex_ + 1) == LINE_RETURN) {
                        allowedComments_ = true;
                    }
                }
                break;
            }
            if (currentChar_ == BEGIN_COMMENT) {
                //ERROR
                return out_;
            }
            if (foundInherit_) {
                str_.append(currentChar_);
            }
            nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_);
        }
        if (foundInherit_) {
            FileIndex frc_ = new FileIndex(_fileName, nextIndex_);
            superTypes_.put(frc_, str_.toString());
        }
        if (!ok_) {
            //ERROR
            return out_;
        }
        if (nextIndex_ >= len_) {
            //ERROR
            return out_;
        }
        currentChar_ = _file.charAt(nextIndex_);
        nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_);
        boolean commentedSingleLine_ = false;
        boolean commentedMultiLine_ = false;
        boolean constChar_ = false;
        boolean constString_ = false;
        StringBuilder instruction_ = new StringBuilder();
        RowCol instructionLocation_ = new RowCol();
        Numbers<Integer> parentheses_ = new Numbers<Integer>();
        Numbers<Integer> indexes_ = new Numbers<Integer>();
        indexes_.add(0);
        RootBlock typeBlock_;
        BracedBlock currentParent_;
        String tempDef_ = templateDef_.toString();
        String typeName_ = typeNamePref_.toString();
        String packageName_ = EMPTY_STRING;
        String baseName_ = EMPTY_STRING;
        int lastDot_ = typeName_.lastIndexOf(PKG);
        if (lastDot_ >= 0) {
            packageName_ = typeName_.substring(0, lastDot_);
            baseName_ = typeName_.substring(lastDot_ + 1);
        } else {
            baseName_ = typeName_;
        }
        boolean enableByEndLine_ = false;
        FileIndex fileLoc_ = new FileIndex(_fileName, beginDefinition_);
        if (StringList.quickEq(type_, KEY_WORD_ENUM)) {
            enableByEndLine_ = true;
            typeBlock_ = new EnumBlock(_context, 0, null, fileLoc_, baseName_, packageName_, access_, tempDef_, superTypes_);
        } else if (StringList.quickEq(type_, KEY_WORD_CLASS)) {
            typeBlock_ = new ClassBlock(_context, 0, null, fileLoc_, baseName_, packageName_, access_, tempDef_, superTypes_, finalType_, abstractType_);
        } else {
            typeBlock_ = new InterfaceBlock(_context, 0, null, fileLoc_, baseName_, packageName_, access_, tempDef_, superTypes_);
        }
        out_.setType(typeBlock_);
        currentParent_ = typeBlock_;
        i_ = nextIndex_;
        boolean okType_ = false;
        while (i_ < len_) {
            currentChar_ = _file.charAt(i_);
            if (commentedSingleLine_) {
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                    if (instruction_.toString().trim().isEmpty()) {
                        instruction_.delete(0, instruction_.length());
                    }
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                continue;
            }
            if (commentedMultiLine_) {
                if (currentChar_ == SECOND_COMMENT) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        break;
                    }
                    char nextChar_ = _file.charAt(i_ + 1);
                    if (nextChar_ == BEGIN_COMMENT) {
                        commentedMultiLine_ = false;
                        if (instruction_.toString().trim().isEmpty()) {
                            instruction_.delete(0, instruction_.length());
                        }
                        i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                        i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                        continue;
                    }
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                continue;
            }
            boolean const_ = false;
            if (constString_ || constChar_) {
                const_ = true;
            }
            if (currentChar_ == BEGIN_COMMENT && !const_) {
                if (i_ + 1 >= len_) {
                    //ERROR
                    break;
                }
                char nextChar_ = _file.charAt(i_ + 1);
                if (nextChar_ == BEGIN_COMMENT) {
                    if (!allowedComments_) {
                        //ERROR
                        break;
                    }
                    commentedSingleLine_ = true;
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    continue;
                }
                if (nextChar_ == SECOND_COMMENT) {
                    if (!allowedComments_) {
                        //ERROR
                        break;
                    }
                    commentedMultiLine_ = true;
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    continue;
                }
            }
            boolean endInstruction_ = false;
            if (parentheses_.isEmpty() && !const_) {
                if (currentChar_ == END_LINE) {
                    endInstruction_ = true;
                }
                if (currentChar_ == SEP_ENUM_CONST && enableByEndLine_) {
                    endInstruction_ = true;
                }
                if (currentChar_ == END_BLOCK) {
                    endInstruction_ = true;
                }
                if (currentChar_ == BEGIN_BLOCK) {
                    endInstruction_ = true;
                }
                //End line
            }
            if (!endInstruction_) {
                if (instruction_.length() == 0) {
                    instructionLocation_ = new RowCol();
                    instructionLocation_.setCol(current_.getCol());
                    instructionLocation_.setRow(current_.getRow());
                }
                instruction_.append(currentChar_);
            }
            if (constChar_) {
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        break;
                    }
                    instruction_.append(_file.charAt(i_+1));
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    continue;
                }
                if (currentChar_ == DEL_CHAR) {
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    constChar_ = false;
                    continue;
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                continue;
            }
            if (constString_) {
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        break;
                    }
                    instruction_.append(_file.charAt(i_+1));
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    continue;
                }
                if (currentChar_ == DEL_STRING) {
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                    constString_ = false;
                    continue;
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, current_);
                continue;
            }
            if (!Character.isWhitespace(currentChar_)) {
                allowedComments_ = false;
            } else if (currentChar_ == LINE_RETURN) {
                if (instruction_.toString().trim().isEmpty()) {
                    allowedComments_ = true;
                }
            }
            if (currentChar_ == DEL_CHAR) {
                constChar_ = true;
            }
            if (currentChar_ == DEL_STRING) {
                constString_ = true;
            }
            if (currentChar_ == BEGIN_CALLING) {
                parentheses_.add(i_);
            }
            if (currentChar_ == END_CALLING) {
                parentheses_.removeLast();
            }
            if (currentChar_ == BEGIN_BLOCK) {
                braces_.add(i_);
            }
            if (currentChar_ == END_BLOCK) {
                braces_.removeLast();
            }
            if (braces_.size() == 0 && currentChar_ == END_BLOCK) {
                okType_ = true;
                break;
            }
            if (endInstruction_) {
                String found_ = instruction_.toString();
                int index_ = indexes_.last();
                boolean enum_ = false;
                if (found_.indexOf(BEGIN_CALLING) >= 0) {
                    String part_ = found_.trim();
                    part_ = part_.substring(0, part_.indexOf(BEGIN_CALLING));
                    if (StringList.isWord(part_)) {
                        enum_ = true;
                    }
                } else {
                    String part_ = found_.trim();
                    if (StringList.isWord(part_)) {
                        enum_ = true;
                    }
                }
                if (enableByEndLine_ && enum_) {
                    String fieldName_;
                    String expression_ = EMPTY_STRING;
                    int indexBeginCalling_ = found_.indexOf(BEGIN_CALLING);
                    if (indexBeginCalling_ >= 0) {
                        fieldName_ = found_.substring(0, indexBeginCalling_);
                        expression_ = found_.substring(indexBeginCalling_ + 1, found_.lastIndexOf(END_CALLING));
                    } else {
                        fieldName_ = found_;
                    }
                    currentParent_.appendChild(new ElementBlock(_context, index_, currentParent_, fieldName_, expression_));
                    index_++;
                    indexes_.setLast(index_);
                } else if (currentChar_ == END_LINE) {
                    if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_BREAK))) {
                        currentParent_.appendChild(new BreakBlock(_context, index_, currentParent_));
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_CONTINUE))) {
                        currentParent_.appendChild(new ContinueBlock(_context, index_, currentParent_));
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_RETURN))) {
                        String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_RETURN)).length());
                        currentParent_.appendChild(new ReturnMehod(_context, index_, currentParent_, exp_));
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_THROW))) {
                        String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_THROW)).length());
                        currentParent_.appendChild(new Throwing(_context, index_, currentParent_, exp_));
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_CASE))) {
                        String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_CASE)).length());
                        currentParent_.appendChild(new CaseCondition(_context, index_, currentParent_, exp_));
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_DEFAULT))) {
                        currentParent_.appendChild(new DefaultCondition(_context, index_, currentParent_));
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_WHILE))) {
                        String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_WHILE)).length());
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING));
                        currentParent_.appendChild(new WhileCondition(_context, index_, currentParent_, exp_));
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_CATCH))) {
                        String info_ = found_.trim().substring((prefixKeyWord(KEY_WORD_CATCH)).length());
                        info_ = info_.substring(info_.indexOf(BEGIN_CALLING)+1);
                        String declaringType_ = getDeclaringType(info_);
                        info_ = info_.substring(declaringType_.length());
                        String variable_ = info_.substring(0, info_.indexOf(END_CALLING));
                        currentParent_.appendChild(new CatchEval(_context, index_, currentParent_, declaringType_, variable_));
                    } else if (found_.trim().endsWith(INCR)) {
                        int lastPrint_ = StringList.getLastPrintableCharIndex(found_);
                        String leftPart_ = found_.substring(0, lastPrint_ + 1);
                        String exp_ = leftPart_.substring(0, leftPart_.length() - INCR.length());
                        currentParent_.appendChild(new SemiAffectation(_context, index_, currentParent_, exp_, INCR));
                    } else if (found_.trim().endsWith(DECR)) {
                        int lastPrint_ = StringList.getLastPrintableCharIndex(found_);
                        String leftPart_ = found_.substring(0, lastPrint_ + 1);
                        String exp_ = leftPart_.substring(0, leftPart_.length() - DECR.length());
                        currentParent_.appendChild(new SemiAffectation(_context, index_, currentParent_, exp_, DECR));
                    } else {
                        boolean field_ = false;
                        AccessEnum accessField_ = AccessEnum.PACKAGE;
                        String word_ = EMPTY_STRING;
                        int accessOffest_ = StringList.getFirstPrintableCharIndex(found_) + i_ - found_.length();
                        if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_PRIVATE))) {
                            field_ = true;
                            accessField_ = AccessEnum.PRIVATE;
                            word_ = prefixKeyWord(KEY_WORD_PRIVATE);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_PACKAGE))) {
                            field_ = true;
                            accessField_ = AccessEnum.PACKAGE;
                            word_ = prefixKeyWord(KEY_WORD_PACKAGE);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_PROTECTED))) {
                            field_ = true;
                            accessField_ = AccessEnum.PROTECTED;
                            word_ = prefixKeyWord(KEY_WORD_PROTECTED);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_PUBLIC))) {
                            field_ = true;
                            accessField_ = AccessEnum.PUBLIC;
                            word_ = prefixKeyWord(KEY_WORD_PUBLIC);
                        }
                        int delta_ = StringList.getFirstPrintableCharIndex(found_) + word_.length();
                        boolean method_ = false;
                        String afterAccess_ = found_.trim().substring(word_.length());
                        delta_ += StringList.getFirstPrintableCharIndex(afterAccess_);
                        if (field_) {
                            if (afterAccess_.trim().startsWith(prefixKeyWord(KEY_WORD_ABSTRACT))) {
                                method_ = true;
                            }
                        }
                        if (method_) {
                            String info_ = afterAccess_.trim();
                            int abstractOffest_ = i_ - found_.length() + delta_;
                            String afterModifier_ = info_.substring((prefixKeyWord(KEY_WORD_ABSTRACT)).length());
                            info_ = afterModifier_.trim();
                            int typeOffset_ = abstractOffest_ + prefixKeyWord(KEY_WORD_ABSTRACT).length();
                            typeOffset_ += StringList.getFirstPrintableCharIndex(afterModifier_);
                            String declaringType_ = getDeclaringType(info_);
                            String afterType_ = info_.substring(declaringType_.length());
                            int methodNameOffest_ = typeOffset_ + declaringType_.length();
                            methodNameOffest_ += StringList.getFirstPrintableCharIndex(afterType_);
                            info_ = afterType_.trim();
                            int leftParIndex_ = info_.indexOf(BEGIN_CALLING);
                            String methodName_ = info_.substring(0, leftParIndex_);
                            String afterMethodName_ = info_.substring(leftParIndex_ + 1);
                            int paramOffest_ = methodNameOffest_ + leftParIndex_ + 1;
                            paramOffest_ += StringList.getFirstPrintableCharIndex(afterMethodName_);
                            info_ = afterMethodName_.trim();
                            Numbers<Integer> offests_ = new Numbers<Integer>();
                            StringList parametersType_ = new StringList();
                            StringList parametersName_ = new StringList();
                            while (true) {
                                if (info_.indexOf(END_CALLING) == 0) {
                                    break;
                                }
                                offests_.add(paramOffest_);
                                String paramType_ = getDeclaringParamType(info_);
                                parametersType_.add(paramType_);
                                String afterParamType_ = info_.substring(paramType_.length());
                                info_ = afterParamType_.trim();
                                int call_ = info_.indexOf(SEP_CALLING);
                                if (call_ < 0) {
                                    call_ = info_.indexOf(END_CALLING);
                                }
                                String paramName_ = info_.substring(0, call_);
                                parametersName_.add(paramName_);
                                String afterParamName_ = info_.substring(call_ + 1);
                                info_ = afterParamName_.trim();
                                if (info_.isEmpty()) {
                                    break;
                                }
                                paramOffest_ += paramType_.length();
                                paramOffest_ += StringList.getFirstPrintableCharIndex(afterParamType_);
                                paramOffest_ += call_ + 1;
                                paramOffest_ += StringList.getFirstPrintableCharIndex(afterParamName_);
                            }
                            currentParent_.appendChild(new MethodBlock(_context, index_, currentParent_, accessField_, declaringType_, methodName_, parametersType_, parametersName_, KEY_WORD_ABSTRACT));
                        } else if (field_) {
                            String info_ = afterAccess_.trim();
                            int staticOffest_ = -1;
                            int finalOffest_ = -1;
                            boolean static_ = false;
                            boolean final_ = false;
                            if (info_.startsWith(prefixKeyWord(KEY_WORD_STATIC))) {
                                staticOffest_ = i_ - found_.length() + delta_;
                                static_ = true;
                                String afterStatic_ = info_.substring((prefixKeyWord(KEY_WORD_STATIC)).length());
                                delta_ += prefixKeyWord(KEY_WORD_STATIC).length();
                                delta_ += StringList.getFirstPrintableCharIndex(afterStatic_);
                                info_ = afterStatic_.trim();
                            }
                            if (info_.startsWith(prefixKeyWord(KEY_WORD_FINAL))) {
                                finalOffest_ = i_ - found_.length() + delta_;
                                final_ = true;
                                String afterFinal_ = info_.substring((prefixKeyWord(KEY_WORD_FINAL)).length());
                                delta_ += prefixKeyWord(KEY_WORD_FINAL).length();
                                delta_ += StringList.getFirstPrintableCharIndex(afterFinal_);
                                info_ = afterFinal_.trim();
                            }
                            int typeOffest_ = i_ - found_.length() + delta_;
                            String declaringType_ = getDeclaringType(info_);
                            String afterType_ = info_.substring(declaringType_.length());
                            int fieldNameOffest_ = StringList.getFirstPrintableCharIndex(afterType_) +declaringType_.length() + typeOffest_;
                            String expression_ = EMPTY_STRING;
                            String fieldName_;
                            int sepOffest_ = afterType_.indexOf(PART_SEPARATOR);
                            if (sepOffest_ >= 0) {
                                fieldName_ = afterType_.substring(0, sepOffest_);
                                expression_ = afterType_.substring(sepOffest_ + 1);
                                sepOffest_ += fieldNameOffest_;
                            } else {
                                fieldName_ = afterType_;
                            }
                            currentParent_.appendChild(new FieldBlock(_context, index_, currentParent_, accessField_, static_, final_, fieldName_, declaringType_, expression_));
                        } else {
                            int affectOffset_ = -1;
                            int typeOffset_ = -1;
                            int afterDeclareOffset_ = -1;
                            int afterAffectOffset_ = -1;
                            String declaringType_ = getDeclaringTypeInstr(found_);
                            boolean typeDeclaring_ = !declaringType_.trim().isEmpty();
                            boolean declaring_ = false;
                            String info_;
                            if (typeDeclaring_) {
                                typeOffset_ = i_ - found_.length();
                                int varNameOffset_ = typeOffset_;
                                varNameOffset_ += declaringType_.length();
                                info_ = found_.substring(declaringType_.length());
                                varNameOffset_ += StringList.getFirstPrintableCharIndex(info_);
                                afterDeclareOffset_ = varNameOffset_;
                                declaring_ = true;
                            } else {
                                affectOffset_ = i_ - found_.length();
                                affectOffset_ += StringList.getFirstPrintableCharIndex(found_);
                                afterDeclareOffset_ = affectOffset_;
                                info_ = found_;
                            }
                            afterAffectOffset_ = afterDeclareOffset_;
                            int indexInstr_ = 0;
                            int instrLen_ = info_.length();
                            Numbers<Integer> localCallings_ = new Numbers<Integer>();
                            boolean localConstChar_ = false;
                            boolean localConstString_ = false;
                            boolean affect_ = false;
                            while (indexInstr_ < instrLen_) {
                                char locChar_ = info_.charAt(indexInstr_);
                                if (localConstChar_) {
                                    if (locChar_ == ESCAPE) {
                                        indexInstr_++;
                                        indexInstr_++;
                                        continue;
                                    }
                                    if (locChar_ == DEL_CHAR) {
                                        indexInstr_++;
                                        localConstChar_ = false;
                                        continue;
                                    }
                                    indexInstr_++;
                                    continue;
                                }
                                if (localConstString_) {
                                    if (locChar_ == ESCAPE) {
                                        indexInstr_++;
                                        indexInstr_++;
                                        continue;
                                    }
                                    if (locChar_ == DEL_STRING) {
                                        indexInstr_++;
                                        localConstString_ = false;
                                        continue;
                                    }
                                    indexInstr_++;
                                    continue;
                                }
                                if (localCallings_.isEmpty() && locChar_ == PART_SEPARATOR) {
                                    affect_ = true;
                                    afterAffectOffset_ += indexInstr_;
                                    break;
                                }
                                if (locChar_ == DEL_CHAR) {
                                    localConstChar_ = true;
                                }
                                if (locChar_ == DEL_STRING) {
                                    localConstString_ = true;
                                }
                                if (locChar_ == BEGIN_CALLING) {
                                    localCallings_.add(indexInstr_);
                                }
                                if (locChar_ == END_CALLING) {
                                    localCallings_.removeLast();
                                }
                                indexInstr_++;
                            }
                            if (affect_) {
                                if (declaring_) {
                                    String left_ = info_.substring(0, indexInstr_);
                                    String right_ = info_.substring(indexInstr_ + 1);
                                    afterAffectOffset_ += StringList.getFirstPrintableCharIndex(right_);
                                    currentParent_.appendChild(new DeclareAffectVariable(_context, index_, currentParent_, declaringType_, left_, right_));
                                } else {
                                    if (indexInstr_ >= info_.length()) {
                                        //ERROR
                                        break;
                                    }
                                    int maxLeft_ = indexInstr_ - 1;
                                    int minRight_ = indexInstr_ + 1;
                                    StringBuilder oper_ = new StringBuilder();
                                    if (info_.charAt(indexInstr_ + 1) == PLUS_CHAR) {
                                        oper_.append(PART_SEPARATOR);
                                        oper_.append(PLUS_CHAR);
                                        maxLeft_++;
                                        minRight_++;
                                    } else if (info_.charAt(indexInstr_ - 1) == PLUS_CHAR) {
                                        oper_.append(PLUS_CHAR);
                                        oper_.append(PART_SEPARATOR);
                                    } else if (info_.charAt(indexInstr_ - 1) == MINUS_CHAR) {
                                        oper_.append(MINUS_CHAR);
                                        oper_.append(PART_SEPARATOR);
                                    } else if (info_.charAt(indexInstr_ - 1) == MULT_CHAR) {
                                        oper_.append(MULT_CHAR);
                                        oper_.append(PART_SEPARATOR);
                                    } else if (info_.charAt(indexInstr_ - 1) == DIV_CHAR) {
                                        oper_.append(DIV_CHAR);
                                        oper_.append(PART_SEPARATOR);
                                    } else if (info_.charAt(indexInstr_ - 1) == MOD_CHAR) {
                                        oper_.append(MOD_CHAR);
                                        oper_.append(PART_SEPARATOR);
                                    } else {
                                        maxLeft_++;
                                        oper_.append(PART_SEPARATOR);
                                    }
                                    String left_ = info_.substring(0, maxLeft_);
                                    String right_ = info_.substring(minRight_);
                                    afterAffectOffset_ += StringList.getFirstPrintableCharIndex(right_);
                                    currentParent_.appendChild(new Affectation(_context, index_, currentParent_, left_, oper_.toString(), right_));
                                }
                            } else {
                                String left_ = info_;
                                if (declaring_) {
                                    currentParent_.appendChild(new DeclareVariable(_context, index_, currentParent_, declaringType_, left_));
                                } else {
                                    currentParent_.appendChild(new Line(_context, index_, currentParent_, left_));
                                }
                            }
                        }
                    }
                    index_++;
                    indexes_.setLast(index_);
                } else if (currentChar_ == END_BLOCK) {
                    indexes_.removeLast();
                    index_ = indexes_.last();
                    index_++;
                    indexes_.setLast(index_);
                    currentParent_ = currentParent_.getParent();
                } else if (currentChar_ == BEGIN_BLOCK) {
                    BracedBlock br_ = null;
                    indexes_.add(0);
                    boolean fct_ = false;
                    AccessEnum accessFct_ = AccessEnum.PACKAGE;
                    String word_ = EMPTY_STRING;
                    int accessOffest_ = StringList.getFirstPrintableCharIndex(found_) + i_ - found_.length();
                    if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_PRIVATE))) {
                        fct_ = true;
                        accessFct_ = AccessEnum.PRIVATE;
                        word_ = prefixKeyWord(KEY_WORD_PRIVATE);
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_PACKAGE))) {
                        fct_ = true;
                        accessFct_ = AccessEnum.PACKAGE;
                        word_ = prefixKeyWord(KEY_WORD_PACKAGE);
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_PROTECTED))) {
                        fct_ = true;
                        accessFct_ = AccessEnum.PROTECTED;
                        word_ = prefixKeyWord(KEY_WORD_PROTECTED);
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_PUBLIC))) {
                        fct_ = true;
                        accessFct_ = AccessEnum.PUBLIC;
                        word_ = prefixKeyWord(KEY_WORD_PUBLIC);
                    }
                    String afterAccess_ = found_.trim().substring(word_.length());
                    if (fct_ && found_.indexOf(BEGIN_CALLING) > 0) {
                        int modifierOffest_ = accessOffest_ + word_.length();
                        modifierOffest_ += StringList.getFirstPrintableCharIndex(afterAccess_);
                        String info_ = afterAccess_.trim();
                        String modifier_ = EMPTY_STRING;
                        if (info_.startsWith(prefixKeyWord(KEY_WORD_NORMAL))) {
                            modifier_ = KEY_WORD_NORMAL;
                        } else if (info_.startsWith(prefixKeyWord(KEY_WORD_ABSTRACT))) {
                            modifier_ = KEY_WORD_ABSTRACT;
                        } else if (info_.startsWith(prefixKeyWord(KEY_WORD_STATIC))) {
                            modifier_ = KEY_WORD_STATIC;
                        }  else if (info_.startsWith(prefixKeyWord(KEY_WORD_FINAL))) {
                            modifier_ = KEY_WORD_FINAL;
                        }
                        if (!modifier_.isEmpty()) {
                            String afterModifier_ = info_.substring((prefixKeyWord(modifier_)).length());
                            int typeOffset_ = modifierOffest_ + modifier_.length();
                            typeOffset_ += StringList.getFirstPrintableCharIndex(afterModifier_);
                            info_ = afterModifier_.trim();
                            String declaringType_ = getDeclaringType(info_);
                            String afterType_ = info_.substring(declaringType_.length());
                            int methodNameOffest_ = typeOffset_ + declaringType_.length();
                            methodNameOffest_ += StringList.getFirstPrintableCharIndex(afterType_);
                            info_ = afterType_.trim();
                            int leftParIndex_ = info_.indexOf(BEGIN_CALLING);
                            String methodName_ = info_.substring(0, leftParIndex_);
                            String afterMethodName_ = info_.substring(leftParIndex_ + 1);
                            int paramOffest_ = methodNameOffest_ + leftParIndex_ + 1;
                            paramOffest_ += StringList.getFirstPrintableCharIndex(afterMethodName_);
                            info_ = afterMethodName_.trim();
                            Numbers<Integer> offests_ = new Numbers<Integer>();
                            StringList parametersType_ = new StringList();
                            StringList parametersName_ = new StringList();
                            while (true) {
                                if (info_.indexOf(END_CALLING) == 0) {
                                    break;
                                }
                                offests_.add(paramOffest_);
                                String paramType_ = getDeclaringParamType(info_);
                                parametersType_.add(paramType_);
                                String afterParamType_ = info_.substring(paramType_.length());
                                info_ = afterParamType_.trim();
                                int call_ = info_.indexOf(SEP_CALLING);
                                if (call_ < 0) {
                                    call_ = info_.indexOf(END_CALLING);
                                }
                                String paramName_ = info_.substring(0, call_);
                                parametersName_.add(paramName_);
                                String afterParamName_ = info_.substring(call_ + 1);
                                info_ = afterParamName_.trim();
                                if (info_.isEmpty()) {
                                    break;
                                }
                                paramOffest_ += paramType_.length();
                                paramOffest_ += StringList.getFirstPrintableCharIndex(afterParamType_);
                                paramOffest_ += call_ + 1;
                                paramOffest_ += StringList.getFirstPrintableCharIndex(afterParamName_);
                            }
                            br_ = new MethodBlock(_context, index_, currentParent_, accessFct_, declaringType_, methodName_, parametersType_, parametersName_, modifier_);
                            currentParent_.appendChild(br_);
                        } else {
                            StringList interfaces_ = new StringList();
                            Numbers<Integer> interfacesOffests_ = new Numbers<Integer>();
                            int interfaceOffest_ = modifierOffest_;
                            int paramOffest_;
                            if (info_.startsWith(prefixKeyWord(KEY_WORD_INTERFACES))) {
                                int indexLeftPar_ = info_.indexOf(BEGIN_CALLING);
                                interfaceOffest_ += indexLeftPar_ + 1;
                                String interfacesInfo_ = info_.substring(indexLeftPar_ + 1, info_.indexOf(END_CALLING));
                                for (String p: StringList.splitChars(interfacesInfo_, SEP_CALLING)) {
                                    interfaces_.add(p);
                                    interfacesOffests_.add(interfaceOffest_);
                                    interfaceOffest_ += p.length() + 1;
                                }
                                String afterInterfaces_ = info_.substring(indexLeftPar_ + 1 + interfacesInfo_.length() + 1);
                                int delta_ = afterInterfaces_.indexOf(BEGIN_CALLING) + 1;
                                interfaceOffest_ += delta_;
                                interfaceOffest_ += StringList.getFirstPrintableCharIndex(afterInterfaces_.substring(delta_));
                                paramOffest_ = interfaceOffest_;
                                info_ = info_.substring(info_.indexOf(BEGIN_CALLING, info_.indexOf(BEGIN_CALLING) + 1) + 1).trim();
                            } else {
                                paramOffest_ = interfaceOffest_;
                                int indexLeftPar_ = info_.indexOf(BEGIN_CALLING);
                                paramOffest_ += indexLeftPar_ + 1;
                                paramOffest_ += StringList.getFirstPrintableCharIndex(info_.substring(indexLeftPar_ + 1));
                                info_ = info_.substring(info_.indexOf(BEGIN_CALLING) + 1).trim();
                            }
                            Numbers<Integer> offests_ = new Numbers<Integer>();
                            StringList parametersType_ = new StringList();
                            StringList parametersName_ = new StringList();
                            while (true) {
                                if (info_.indexOf(END_CALLING) == 0) {
                                    break;
                                }
                                offests_.add(paramOffest_);
                                String paramType_ = getDeclaringParamType(info_);
                                parametersType_.add(paramType_);
                                String afterParamType_ = info_.substring(paramType_.length());
                                info_ = afterParamType_.trim();
                                int call_ = info_.indexOf(SEP_CALLING);
                                if (call_ < 0) {
                                    call_ = info_.indexOf(END_CALLING);
                                }
                                String paramName_ = info_.substring(0, call_);
                                parametersName_.add(paramName_);
                                String afterParamName_ = info_.substring(call_ + 1);
                                info_ = afterParamName_.trim();
                                if (info_.isEmpty()) {
                                    break;
                                }
                                paramOffest_ += paramType_.length();
                                paramOffest_ += StringList.getFirstPrintableCharIndex(afterParamType_);
                                paramOffest_ += call_ + 1;
                                paramOffest_ += StringList.getFirstPrintableCharIndex(afterParamName_);
                            }
                            br_ = new ConstructorBlock(_context, index_, currentParent_, interfaces_, interfacesOffests_, accessFct_, EMPTY_STRING, EMPTY_STRING, parametersType_, parametersName_);
                            currentParent_.appendChild(br_);
                        }
                    } else {
                        if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_CATCH))) {
                            String info_ = found_.trim().substring((prefixKeyWord(KEY_WORD_CATCH)).length());
                            info_ = info_.substring(info_.indexOf(BEGIN_CALLING)+1);
                            String declaringType_ = getDeclaringType(info_);
                            info_ = info_.substring(declaringType_.length());
                            String variable_ = info_.substring(0, info_.indexOf(END_CALLING));
                            br_ = new CatchEval(_context, index_, currentParent_, declaringType_, variable_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_CASE))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_CASE)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING));
                            br_ = new CaseCondition(_context, index_, currentParent_, exp_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_DEFAULT))) {
                            br_ = new DefaultCondition(_context, index_, currentParent_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_IF))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_IF)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING));
                            br_ = new IfCondition(_context, index_, currentParent_, exp_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_ELSEIF))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_ELSEIF)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING));
                            br_ = new ElseIfCondition(_context, index_, currentParent_, exp_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_WHILE))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_WHILE)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING));
                            br_ = new WhileCondition(_context, index_, currentParent_, exp_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_ELSE))) {
                            br_ = new ElseCondition(_context, index_, currentParent_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_DO))) {
                            br_ = new DoBlock(_context, index_, currentParent_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_FINALLY))) {
                            br_ = new FinallyEval(_context, index_, currentParent_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_TRY))) {
                            br_ = new TryEval(_context, index_, currentParent_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_FOREACH))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_FOREACH)).length());
                            String indexClassName_ = EMPTY_STRING;
                            if (exp_.trim().indexOf(BEGIN_ARRAY) == 0) {
                                indexClassName_ = exp_.substring(0, exp_.indexOf(END_ARRAY));
                                exp_ = exp_.substring(exp_.indexOf(END_ARRAY) + 1);
                            }
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1);
                            String declaringType_ = getDeclaringType(exp_);
                            exp_ = exp_.substring(declaringType_.length());
                            String variable_ = exp_.substring(0, exp_.indexOf(FOR_BLOCKS));
                            exp_ = exp_.substring(exp_.indexOf(FOR_BLOCKS) + 1, exp_.lastIndexOf(END_CALLING));
                            br_ = new ForEachLoop(_context, index_, currentParent_, declaringType_, variable_, exp_, indexClassName_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_FOR))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_FOR)).length());
                            String indexClassName_ = EMPTY_STRING;
                            if (exp_.trim().indexOf(BEGIN_ARRAY) == 0) {
                                indexClassName_ = exp_.substring(0, exp_.indexOf(END_ARRAY));
                                exp_ = exp_.substring(exp_.indexOf(END_ARRAY) + 1);
                            }
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1);
                            String declaringType_ = getDeclaringType(exp_);
                            exp_ = exp_.substring(declaringType_.length());
                            String variable_ = exp_.substring(0, exp_.indexOf(PART_SEPARATOR));
                            exp_ = exp_.substring(exp_.indexOf(PART_SEPARATOR) + 1);
                            int nextElt_ = getIndex(exp_);
                            String init_ = exp_.substring(0, nextElt_);
                            exp_ = exp_.substring(init_.length()+1);
                            nextElt_ = getIndex(exp_);
                            boolean eq_ = false;
                            String to_ = exp_.substring(0, nextElt_);
                            if (exp_.charAt(nextElt_ + 1) == END_LINE) {
                                eq_ = true;
                                nextElt_++;
                            }
                            exp_ = exp_.substring(nextElt_ + 1);
                            String step_ = exp_.substring(0, exp_.lastIndexOf(END_CALLING));
                            br_ = new ForIterativeLoop(_context, index_, currentParent_, declaringType_, variable_, init_, to_, eq_, step_, indexClassName_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_SWITCH))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_SWITCH)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING));
                            br_ = new SwitchBlock(_context, index_, currentParent_, exp_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_STATIC))) {
                            br_ = new StaticBlock(_context, index_, currentParent_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().isEmpty()) {
                            br_ = new InstanceBlock(_context, index_, currentParent_);
                            currentParent_.appendChild(br_);
                        }
                    }
                    currentParent_ = br_;
                }
                instruction_.delete(0, instruction_.length());
            }
            if (parentheses_.isEmpty()) {
                if (currentChar_ == END_LINE) {
                    enableByEndLine_ = false;
                }
            }
            i_ = incrementRowCol(i_, _file, tabWidth_, current_);
        }
        if (okType_) {
            i_ = incrementRowCol(i_, _file, tabWidth_, current_);
        }
        out_.setNextIndex(i_);
        out_.setNextRowCol(new RowCol());
        out_.getNextRowCol().setCol(current_.getCol());
        out_.getNextRowCol().setCol(current_.getRow());
        out_.setOk(okType_);
        return out_;
    }
    private static String getDeclaringParamType(String _found) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        boolean foundTmp_ = false;
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (declTypeName_.toString().trim().endsWith(VARARG)) {
                typeDeclaring_ = true;
                break;
            }
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                if (foundTmp_) {
                    typeDeclaring_ = true;
                    break;
                }
                String nextPart_ = _found.substring(indexInstr_).trim();
                String trimmed_ = declTypeName_.toString().trim();
                if (trimmed_.length() > 0) {
                    char ch_ = trimmed_.charAt(trimmed_.length() - 1);
                    if (StringList.isWordChar(ch_)) {
                        if (!nextPart_.isEmpty()) {
                            if (StringList.isWordChar(nextPart_.charAt(0))) {
                                typeDeclaring_ = true;
                                break;
                            }
                        }
                    }
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == BEGIN_TEMPLATE) {
                nbOpenedTmp_++;
                foundTmp_ = true;
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    declTypeName_.append(currentCharFound_);
                    if (indexInstr_ + 1 < instLen_ && _found.substring(indexInstr_ + 1).trim().startsWith(VARARG)) {
                        int offset_ = StringList.getFirstPrintableCharIndex(_found.substring(indexInstr_ + 1));
                        for (int i = CustList.FIRST_INDEX; i < offset_; i++) {
                            declTypeName_.append(_found.charAt(indexInstr_ + 1 + i));
                        }
                        declTypeName_.append(VARARG);
                    }
                    typeDeclaring_ = true;
                    break;
                }
            }
            declTypeName_.append(currentCharFound_);
            indexInstr_++;
        }
        if (typeDeclaring_) {
            return declTypeName_.toString();
        }
        return EMPTY_STRING;
    }
    private static String getDeclaringInterfacesType(String _found) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        boolean foundTmp_ = false;
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                if (foundTmp_) {
                    typeDeclaring_ = true;
                    break;
                }
                String nextPart_ = _found.substring(indexInstr_).trim();
                String trimmed_ = declTypeName_.toString().trim();
                if (trimmed_.length() > 0) {
                    char ch_ = trimmed_.charAt(trimmed_.length() - 1);
                    if (StringList.isWordChar(ch_)) {
                        if (!nextPart_.isEmpty()) {
                            if (StringList.isWordChar(nextPart_.charAt(0))) {
                                typeDeclaring_ = true;
                                break;
                            }
                        }
                    }
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == SEP_CALLING && nbOpenedTmp_ == 0) {
                typeDeclaring_ = true;
                break;
            }
            if (currentCharFound_ == BEGIN_TEMPLATE) {
                nbOpenedTmp_++;
                foundTmp_ = true;
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    typeDeclaring_ = true;
                    declTypeName_.append(currentCharFound_);
                    break;
                }
            }
            declTypeName_.append(currentCharFound_);
            indexInstr_++;
        }
        if (typeDeclaring_) {
            return declTypeName_.toString();
        }
        return EMPTY_STRING;
    }
    private static String getDeclaringType(String _found) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        boolean foundTmp_ = false;
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                if (foundTmp_) {
                    typeDeclaring_ = true;
                    break;
                }
                String nextPart_ = _found.substring(indexInstr_).trim();
                String trimmed_ = declTypeName_.toString().trim();
                if (trimmed_.length() > 0) {
                    char ch_ = trimmed_.charAt(trimmed_.length() - 1);
                    if (StringList.isWordChar(ch_)) {
                        if (!nextPart_.isEmpty()) {
                            if (StringList.isWordChar(nextPart_.charAt(0))) {
                                typeDeclaring_ = true;
                                break;
                            }
                        }
                    }
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == BEGIN_TEMPLATE) {
                nbOpenedTmp_++;
                foundTmp_ = true;
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    typeDeclaring_ = true;
                    declTypeName_.append(currentCharFound_);
                    break;
                }
            }
            declTypeName_.append(currentCharFound_);
            indexInstr_++;
        }
        if (typeDeclaring_) {
            return declTypeName_.toString();
        }
        return EMPTY_STRING;
    }
    private static String getDeclaringTypeInstr(String _found) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        boolean foundTmp_ = false;
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                String trimmed_ = declTypeName_.toString().trim();
                if (StringList.quickEq(trimmed_,NEW)) {
                    break;
                }
                if (trimmed_.endsWith(NEW)) {
                    //there exist a character before the pseudo key word
                    int lenDeclTypeName_ = trimmed_.length() - NEW.length();
                    String firstPart_ = trimmed_.substring(0, lenDeclTypeName_).trim();
                    char last_ = firstPart_.charAt(firstPart_.length() - 1);
                    if (last_ == PKG) {
                        break;
                    }
                }
                if (foundTmp_) {
                    typeDeclaring_ = true;
                    break;
                }
                String nextPart_ = _found.substring(indexInstr_).trim();
                if (trimmed_.length() > 0) {
                    char ch_ = trimmed_.charAt(trimmed_.length() - 1);
                    if (StringList.isWordChar(ch_)) {
                        if (!nextPart_.isEmpty()) {
                            if (StringList.isWordChar(nextPart_.charAt(0))) {
                                typeDeclaring_ = true;
                                break;
                            }
                        }
                    }
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == BEGIN_CALLING) {
                break;
            }
            if (currentCharFound_ == PLUS_CHAR) {
                break;
            }
            if (currentCharFound_ == MINUS_CHAR) {
                break;
            }
            if (currentCharFound_ == PART_SEPARATOR) {
                break;
            }
            if (currentCharFound_ == MULT_CHAR) {
                break;
            }
            if (currentCharFound_ == DIV_CHAR) {
                break;
            }
            if (currentCharFound_ == MOD_CHAR) {
                break;
            }
            if (currentCharFound_ == BEGIN_CALLING) {
                break;
            }
            if (currentCharFound_ == GET_VAR) {
                break;
            }
            if (currentCharFound_ == BEGIN_ARRAY) {
                String trimmed_ = declTypeName_.toString().trim();
                if (trimmed_.length() > 0) {
                    char ch_ = trimmed_.charAt(trimmed_.length() - 1);
                    if (ch_ != BEGIN_ARRAY) {
                        break;
                    }
                }
            }
            if (currentCharFound_ == BEGIN_TEMPLATE) {
                nbOpenedTmp_++;
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    declTypeName_.append(currentCharFound_);
                    typeDeclaring_ = true;
                    break;
                }
            }
            declTypeName_.append(currentCharFound_);
            indexInstr_++;
        }
        if (typeDeclaring_) {
            return declTypeName_.toString();
        }
        return EMPTY_STRING;
    }
    private static int getIndex(String _info) {
        int indexInstr_ = 0;
        int instrLen_ = _info.length();
        Numbers<Integer> localCallings_ = new Numbers<Integer>();
        boolean localConstChar_ = false;
        boolean localConstString_ = false;
        while (indexInstr_ < instrLen_) {
            char locChar_ = _info.charAt(indexInstr_);
            if (localConstChar_) {
                if (locChar_ == ESCAPE) {
                    indexInstr_++;
                    indexInstr_++;
                    continue;
                }
                if (locChar_ == DEL_CHAR) {
                    indexInstr_++;
                    localConstChar_ = false;
                    continue;
                }
                indexInstr_++;
                continue;
            }
            if (localConstString_) {
                if (locChar_ == ESCAPE) {
                    indexInstr_++;
                    indexInstr_++;
                    continue;
                }
                if (locChar_ == DEL_STRING) {
                    indexInstr_++;
                    localConstString_ = false;
                    continue;
                }
                indexInstr_++;
                continue;
            }
            if (localCallings_.isEmpty() && locChar_ == END_LINE) {
                return indexInstr_;
            }
            if (locChar_ == DEL_CHAR) {
                localConstChar_ = true;
            }
            if (locChar_ == DEL_STRING) {
                localConstString_ = true;
            }
            if (locChar_ == BEGIN_CALLING) {
                localCallings_.add(indexInstr_);
            }
            if (locChar_ == END_CALLING) {
                localCallings_.removeLast();
            }
            indexInstr_++;
        }
        return -1;
    }

    private static String prefixKeyWord(String _keyWord) {
        return StringList.concat(String.valueOf(KEY_WORD_PREFIX), _keyWord);
    }

    private static int incrementRowCol(int _index, int _delta,String _file, int _tabWidth, RowCol _current) {
        int index_ = _index;
        for (int i = 0; i < _delta; i++) {
            index_ = incrementRowCol(index_, _file, _tabWidth, _current);
        }
        return index_;
    }

    private static int incrementRowCol(int _index, String _file, int _tabWidth, RowCol _current) {
        char cur_ = _file.charAt(_index);
        if (!Character.isWhitespace(cur_)) {
            _current.setCol(_current.getCol() + 1);
        } else if (cur_ == LINE_RETURN) {
            _current.setCol(1);
            _current.setRow(_current.getRow() + 1);
        } else if (cur_ == TAB) {
            _current.setCol(_current.getCol() + _tabWidth);
        } else {
            _current.setCol(_current.getCol() + 1);
        }
        return _index+1;
    }
}
