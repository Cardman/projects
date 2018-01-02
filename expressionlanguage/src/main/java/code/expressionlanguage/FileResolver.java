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
        int row_ = 1;
        int col_ = 0;
        int rowBegImport_ = 1;
        int colBegImport_ = 1;
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (currentChar_ == LINE_RETURN) {
                row_++;
                col_ = 0;
            } else {
                col_++;
                if (currentChar_ == TAB) {
                    col_ += _context.getTabWidth() - 1;
                }
            }
            if (currentChar_ == KEY_WORD_PREFIX) {
                break;
            }
            if (commentedSingleLine_) {
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                }
                i_++;
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
                        i_++;
                        i_++;
                        previousChar_ = nextChar_;
                        continue;
                    }
                }
                i_++;
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
                    i_++;
                    i_++;
                    previousChar_ = nextChar_;
                    continue;
                }
                if (nextChar_ == SECOND_COMMENT) {
                    commentedMultiLine_ = true;
                    i_++;
                    i_++;
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
                        rowBegImport_ = row_;
                        colBegImport_ = col_;
                    }
                    str_.append(currentChar_);
                } else if (currentChar_ == LINE_RETURN && previousChar_ == END_IMPORTS) {
                    allowedComments_ = true;
                }
            }
            previousChar_ = currentChar_;
            i_++;
        }
        if (i_ >= len_) {
            return;
        }
        InputTypeCreation input_ = new InputTypeCreation();
        input_.setNextRowCol(new RowCol());
        input_.getNextRowCol().setRow(row_);
        input_.getNextRowCol().setCol(col_);
        input_.setNextIndex(i_);
        while (true) {
            input_.setNextRowCol(new RowCol());
            input_.getNextRowCol().setRow(row_);
            input_.getNextRowCol().setCol(col_);
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
                if (currentChar_ == LINE_RETURN) {
                    row_++;
                    col_ = 0;
                } else {
                    col_++;
                    if (currentChar_ == TAB) {
                        col_ += _context.getTabWidth() - 1;
                    }
                }
                if (currentChar_ == KEY_WORD_PREFIX) {
                    hasNext_ = true;
                    break;
                }
                if (commentedSingleLine_) {
                    if (currentChar_ == LINE_RETURN) {
                        commentedSingleLine_ = false;
                    }
                    i_++;
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
                            i_++;
                            i_++;
                            previousChar_ = nextChar_;
                            continue;
                        }
                    }
                    i_++;
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
                        i_++;
                        i_++;
                        previousChar_ = nextChar_;
                        continue;
                    }
                    if (nextChar_ == SECOND_COMMENT) {
                        commentedMultiLine_ = true;
                        i_++;
                        i_++;
                        previousChar_ = nextChar_;
                        continue;
                    }
                    //ERROR
                    return;
                }
                if (!Character.isWhitespace(currentChar_)) {
                    allowedComments_ = false;
                    if (str_.length() == 0) {
                        rowBegImport_ = row_;
                        colBegImport_ = col_;
                    }
                    str_.append(currentChar_);
                } else if (currentChar_ == LINE_RETURN && previousChar_ == END_IMPORTS) {
                    allowedComments_ = true;
                }
                previousChar_ = currentChar_;
                i_++;
            }
            if (!hasNext_) {
                return;
            }
            input_.getNextRowCol().setRow(row_);
            input_.getNextRowCol().setCol(col_);
            input_.setNextIndex(i_);
        }
    }
    private static ResultTypeCreation createType(ContextEl _context, String _fileName, String _file, InputTypeCreation _input, ObjectMap<FileRowCol, String> _import) {
        boolean enabledTab_ = true;
        ResultTypeCreation out_ = new ResultTypeCreation();
        AccessEnum access_;
        int i_ = _input.getNextIndex();
        int col_ = _input.getNextRowCol().getCol();
        int row_ = _input.getNextRowCol().getRow();
        int len_ = _file.length();
        int nextIndex_ = i_ + 1;
        if (_file.substring(nextIndex_).startsWith(KEY_WORD_PUBLIC)) {
            access_ = AccessEnum.PUBLIC;
            nextIndex_ += KEY_WORD_PUBLIC.length();
            col_ += KEY_WORD_PUBLIC.length();
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_PROTECTED)) {
            access_ = AccessEnum.PROTECTED;
            nextIndex_ += KEY_WORD_PROTECTED.length();
            col_ += KEY_WORD_PROTECTED.length();
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_PACKAGE)) {
            access_ = AccessEnum.PACKAGE;
            nextIndex_ += KEY_WORD_PACKAGE.length();
            col_ += KEY_WORD_PACKAGE.length();
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_PRIVATE)) {
            access_ = AccessEnum.PRIVATE;
            nextIndex_ += KEY_WORD_PRIVATE.length();
            col_ += KEY_WORD_PRIVATE.length();
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
                row_++;
                col_ = 0;
            } else {
                col_++;
                if (currentChar_ == TAB) {
                    col_ += _context.getTabWidth() - 1;
                }
            }
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
            nextIndex_++;
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
        nextIndex_++;
        boolean abstractType_ = false;
        boolean finalType_ = false;
        if (_file.substring(nextIndex_).startsWith(KEY_WORD_ABSTRACT)) {
            abstractType_ = true;
            nextIndex_ += KEY_WORD_ABSTRACT.length();
            col_ += KEY_WORD_ABSTRACT.length();
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_FINAL)) {
            finalType_ = true;
            nextIndex_ += KEY_WORD_FINAL.length();
            col_ += KEY_WORD_FINAL.length();
        }
        if (abstractType_ || finalType_) {
            while (nextIndex_ < len_) {
                currentChar_ = _file.charAt(nextIndex_);
                if (currentChar_ == LINE_RETURN) {
                    row_++;
                    col_ = 0;
                } else {
                    col_++;
                    if (currentChar_ == TAB) {
                        col_ += _context.getTabWidth() - 1;
                    }
                }
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
                nextIndex_++;
            }
            if (nextIndex_ > len_) {
                //ERROR
                return out_;
            }
            if (_file.charAt(nextIndex_) != KEY_WORD_PREFIX) {
                //ERROR
                return out_;
            }
            nextIndex_++;
        }
        currentChar_ = _file.charAt(nextIndex_);
        col_++;
        String type_;
        if (_file.substring(nextIndex_).startsWith(KEY_WORD_CLASS)) {
            type_ = KEY_WORD_CLASS;
            nextIndex_ += KEY_WORD_CLASS.length();
            col_ += KEY_WORD_CLASS.length();
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_ENUM)) {
            type_ = KEY_WORD_ENUM;
            nextIndex_ += KEY_WORD_ENUM.length();
            col_ += KEY_WORD_ENUM.length();
        } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_INTERFACE)) {
            type_ = KEY_WORD_INTERFACE;
            nextIndex_ += KEY_WORD_INTERFACE.length();
            col_ += KEY_WORD_INTERFACE.length();
        } else {
            //ERROR
            return out_;
        }
        enabledTab_ = true;
        while (nextIndex_ < len_) {
            currentChar_ = _file.charAt(nextIndex_);
            if (Character.isWhitespace(currentChar_)) {
                if (currentChar_ == LINE_RETURN) {
                    row_++;
                    col_ = 0;
                } else {
                    col_++;
                    if (currentChar_ == TAB) {
                        col_ += _context.getTabWidth() - 1;
                    }
                }
            }
            if (currentChar_ == LINE_RETURN) {
                enabledTab_ = true;
            } else if (currentChar_ != TAB) {
                enabledTab_ = false;
            } else if (!enabledTab_){
                //ERROR
                return out_;
            }
            if (Character.isWhitespace(currentChar_)) {
                nextIndex_++;
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
        ObjectMap<FileRowCol, String> superTypes_ = new ObjectMap<FileRowCol, String>();
        StringBuilder typeNamePref_ = new StringBuilder();
        StringBuilder templateDef_ = new StringBuilder();
        RowCol rctype_ = new RowCol();
        RowCol rctmp_ = new RowCol();
        int rowSuperTypes_ = 1;
        int colSuperTypes_ = 1;
        int nbOpened_ = 0;
        boolean ok_ = false;
        boolean foundInherit_ = false;
        Numbers<Integer> braces_ = new Numbers<Integer>();
        boolean allowedComments_ = false;
        while (nextIndex_ < len_) {
            currentChar_ = _file.charAt(nextIndex_);
            if (currentChar_ == LINE_RETURN) {
                row_++;
                col_ = 0;
            } else {
                col_++;
                if (currentChar_ == TAB) {
                    col_ += _context.getTabWidth() - 1;
                }
            }
            
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
                    if (templateDef_.length() == 0) {
                        rctmp_.setRow(row_);
                        rctmp_.setCol(col_);
                    }
                    templateDef_.append(currentChar_);
                }
            } else if (templateDef_.length() == 0 && currentChar_ != BEGIN_BLOCK) {
                if (typeNamePref_.length() == 0) {
                    rctype_.setRow(row_);
                    rctype_.setCol(col_);
                }
                if (!foundInherit_ && currentChar_ != INHERIT) {
                    typeNamePref_.append(currentChar_);
                }
            }
            if (currentChar_ == END_TEMPLATE) {
                nbOpened_--;
            }
            if (currentChar_ == INHERIT && nbOpened_ == 0) {
                if (foundInherit_) {
                    RowCol rc_ = new RowCol();
                    rc_.setRow(rowSuperTypes_);
                    rc_.setCol(colSuperTypes_);
                    FileRowCol frc_ = new FileRowCol(_fileName, typeNamePref_.toString(), rc_);
                    superTypes_.put(frc_, str_.toString());
                }
                str_.delete(0, str_.length());
                foundInherit_ = true;
                nextIndex_++;
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
                if (str_.length() == 0) {
                    rowSuperTypes_ = row_;
                    colSuperTypes_ = col_;
                }
                str_.append(currentChar_);
            }
            nextIndex_++;
        }
        if (foundInherit_) {
            RowCol rc_ = new RowCol();
            rc_.setRow(rowSuperTypes_);
            rc_.setCol(colSuperTypes_);
            FileRowCol frc_ = new FileRowCol(_fileName, typeNamePref_.toString(), rc_);
            superTypes_.put(frc_, str_.toString());
        }
        if (!ok_) {
            //ERROR
            return out_;
        }
        col_--;
        if (nextIndex_ >= len_) {
            //ERROR
            return out_;
        }
        currentChar_ = _file.charAt(nextIndex_);
        col_++;
        nextIndex_++;
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
        String typeName_ = typeNamePref_.toString().trim();
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
        if (StringList.quickEq(type_, KEY_WORD_ENUM)) {
            enableByEndLine_ = true;
            typeBlock_ = new EnumBlock(_context, 0, null, baseName_, packageName_, access_, tempDef_, superTypes_);
        } else if (StringList.quickEq(type_, KEY_WORD_CLASS)) {
            typeBlock_ = new ClassBlock(_context, 0, null, baseName_, packageName_, access_, tempDef_, superTypes_, finalType_, abstractType_);
        } else {
            typeBlock_ = new InterfaceBlock(_context, 0, null, baseName_, packageName_, access_, tempDef_, superTypes_);
        }
        out_.setType(typeBlock_);
        currentParent_ = typeBlock_;
        i_ = nextIndex_;
        boolean okType_ = false;
        while (i_ < len_) {
            currentChar_ = _file.charAt(i_);
            if (currentChar_ == LINE_RETURN) {
                row_++;
                col_ = 0;
            } else {
                col_++;
                if (currentChar_ == TAB) {
                    col_ += _context.getTabWidth() - 1;
                }
            }
            if (commentedSingleLine_) {
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                }
                i_++;
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
                    i_++;
                    i_++;
                    continue;
                }
                if (nextChar_ == SECOND_COMMENT) {
                    if (!allowedComments_) {
                        //ERROR
                        break;
                    }
                    commentedMultiLine_ = true;
                    i_++;
                    i_++;
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
                    instructionLocation_.setCol(col_);
                    instructionLocation_.setRow(row_);
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
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        break;
                    }
                    instruction_.append(_file.charAt(i_+1));
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
                    String info_ = found_.trim();
                    String fieldName_;
                    String expression_ = EMPTY_STRING;
                    if (info_.indexOf(BEGIN_CALLING) >= 0) {
                        fieldName_ = info_.substring(0, info_.indexOf(BEGIN_CALLING));
                        expression_ = info_.substring(info_.indexOf(BEGIN_CALLING) + 1, info_.lastIndexOf(END_CALLING));
                    } else {
                        fieldName_ = info_;
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
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING)).trim();
                        currentParent_.appendChild(new WhileCondition(_context, index_, currentParent_, exp_));
                    } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_CATCH))) {
                        String info_ = found_.trim().substring((prefixKeyWord(KEY_WORD_CATCH)).length());
                        info_ = info_.substring(info_.indexOf(BEGIN_CALLING)+1).trim();
                        String declaringType_ = getDeclaringType(info_);
                        info_ = info_.substring(declaringType_.length()).trim();
                        String variable_ = info_.substring(0, info_.indexOf(END_CALLING)).trim();
                        currentParent_.appendChild(new CatchEval(_context, index_, currentParent_, declaringType_, variable_));
                    } else if (found_.trim().endsWith(INCR)) {
                        String exp_ = found_.trim().substring(0, found_.trim().length() - INCR.length());
                        currentParent_.appendChild(new SemiAffectation(_context, index_, currentParent_, exp_, INCR));
                    } else if (found_.trim().endsWith(DECR)) {
                        String exp_ = found_.trim().substring(0, found_.trim().length() - DECR.length());
                        currentParent_.appendChild(new SemiAffectation(_context, index_, currentParent_, exp_, DECR));
                    } else {
                        boolean field_ = false;
                        AccessEnum accessField_ = AccessEnum.PACKAGE;
                        String word_ = EMPTY_STRING;
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
                        boolean method_ = false;
                        if (field_) {
                            if (found_.trim().substring(word_.length()).trim().startsWith(prefixKeyWord(KEY_WORD_ABSTRACT))) {
                                method_ = true;
                            }
                        }
                        if (method_) {
                            String info_ = found_.trim().substring(word_.length()).trim();
                            info_ = info_.substring((prefixKeyWord(KEY_WORD_ABSTRACT)).length()).trim();
                            String declaringType_ = getDeclaringType(info_);
                            info_ = info_.substring(declaringType_.length()).trim();
                            String methodName_ = info_.substring(0, info_.indexOf(BEGIN_CALLING));
                            info_ = info_.substring(info_.indexOf(BEGIN_CALLING) + 1).trim();
                            StringList parametersType_ = new StringList();
                            StringList parametersName_ = new StringList();
                            while (true) {
                                if (info_.indexOf(END_CALLING) == 0) {
                                    break;
                                }
                                String paramType_ = getDeclaringParamType(info_);
                                parametersType_.add(paramType_);
                                info_ = info_.substring(paramType_.length()).trim();
                                int call_ = info_.indexOf(SEP_CALLING);
                                if (call_ < 0) {
                                    call_ = info_.indexOf(END_CALLING);
                                }
                                String paramName_ = info_.substring(0, call_);
                                parametersName_.add(paramName_.trim());
                                info_ = info_.substring(call_ + 1).trim();
                                if (info_.isEmpty()) {
                                    break;
                                }
                            }
                            currentParent_.appendChild(new MethodBlock(_context, index_, currentParent_, accessField_, declaringType_, methodName_, parametersType_, parametersName_, KEY_WORD_ABSTRACT));
                        } else if (field_) {
                            String info_ = found_.trim().substring(word_.length()).trim();
                            boolean static_ = false;
                            boolean final_ = false;
                            if (info_.startsWith(prefixKeyWord(KEY_WORD_STATIC))) {
                                static_ = true;
                                info_ = info_.substring((prefixKeyWord(KEY_WORD_STATIC)).length()).trim();
                            }
                            if (info_.startsWith(prefixKeyWord(KEY_WORD_FINAL))) {
                                final_ = true;
                                info_ = info_.substring((prefixKeyWord(KEY_WORD_FINAL)).length()).trim();
                            }
                            String declaringType_ = getDeclaringType(info_);
                            info_ = info_.substring(declaringType_.length()).trim();
                            String expression_ = EMPTY_STRING;
                            String fieldName_;
                            if (info_.indexOf(PART_SEPARATOR) > 0) {
                                fieldName_ = info_.substring(0, info_.indexOf(PART_SEPARATOR)).trim();
                                expression_ = info_.substring(info_.indexOf(PART_SEPARATOR) + 1);
                            } else {
                                fieldName_ = info_.trim();
                            }
                            currentParent_.appendChild(new FieldBlock(_context, index_, currentParent_, accessField_, static_, final_, fieldName_, declaringType_, expression_));
                        } else {
                            String declaringType_ = getDeclaringTypeInstr(found_.trim());
                            boolean typeDeclaring_ = !declaringType_.isEmpty();
                            boolean declaring_ = false;
                            String info_;
                            if (typeDeclaring_) {
                                info_ = found_.trim().substring(declaringType_.length());
                                declaring_ = true;
                            } else {
                                info_ = found_;
                            }
                            int beforeInstr_ = 0;
                            int instrLen_ = found_.length();
                            while (beforeInstr_ < instrLen_) {
                                char locChar_ = info_.charAt(beforeInstr_);
                                if (!Character.isWhitespace(locChar_)) {
                                    break;
                                }
                                beforeInstr_++;
                            }
                            int indexInstr_ = 0;
                            instrLen_ = info_.length();
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
                    if (fct_ && found_.indexOf(BEGIN_CALLING) > 0) {
                        String info_ = found_.trim().substring(word_.length()).trim();
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
                            info_ = info_.substring((prefixKeyWord(modifier_)).length()).trim();
                            String declaringType_ = getDeclaringType(info_);
                            info_ = info_.substring(declaringType_.length()).trim();
                            String methodName_ = info_.substring(0, info_.indexOf(BEGIN_CALLING));
                            info_ = info_.substring(info_.indexOf(BEGIN_CALLING) + 1).trim();
                            StringList parametersType_ = new StringList();
                            StringList parametersName_ = new StringList();
                            while (true) {
                                if (info_.indexOf(END_CALLING) == 0) {
                                    break;
                                }
                                String paramType_ = getDeclaringParamType(info_);
                                parametersType_.add(paramType_);
                                info_ = info_.substring(paramType_.length()).trim();
                                int call_ = info_.indexOf(SEP_CALLING);
                                if (call_ < 0) {
                                    call_ = info_.indexOf(END_CALLING);
                                }
                                String paramName_ = info_.substring(0, call_);
                                parametersName_.add(paramName_.trim());
                                info_ = info_.substring(call_ + 1).trim();
                                if (info_.isEmpty()) {
                                    break;
                                }
                            }
                            br_ = new MethodBlock(_context, index_, currentParent_, accessFct_, declaringType_, methodName_, parametersType_, parametersName_, modifier_);
                            currentParent_.appendChild(br_);
                        } else {
                            //
                            StringList interfaces_ = new StringList();
                            if (info_.startsWith(prefixKeyWord(KEY_WORD_INTERFACES))) {
                                info_ = info_.substring((prefixKeyWord(KEY_WORD_INTERFACES)).length()).trim();
                                info_ = info_.substring(info_.indexOf(BEGIN_CALLING) + 1).trim();
                                while (true) {
                                    if (info_.indexOf(END_CALLING) == 0) {
                                        info_ = info_.substring(1).trim();
                                        break;
                                    }
                                    String paramType_ = getDeclaringType(info_);
                                    interfaces_.add(paramType_);
                                    info_ = info_.substring(paramType_.length()).trim();
                                    int call_ = info_.indexOf(SEP_CALLING);
                                    if (call_ < 0) {
                                        call_ = info_.indexOf(END_CALLING);
                                    }
                                    info_ = info_.substring(call_ + 1).trim();
                                    if (info_.isEmpty()) {
                                        break;
                                    }
                                }
                            }
                            StringList parametersType_ = new StringList();
                            StringList parametersName_ = new StringList();
                            while (true) {
                                if (info_.indexOf(END_CALLING) == 0) {
                                    break;
                                }
                                String paramType_ = getDeclaringParamType(info_);
                                parametersType_.add(paramType_);
                                info_ = info_.substring(paramType_.length()).trim();
                                int call_ = info_.indexOf(SEP_CALLING);
                                if (call_ < 0) {
                                    call_ = info_.indexOf(END_CALLING);
                                }
                                String paramName_ = info_.substring(0, call_);
                                parametersName_.add(paramName_.trim());
                                info_ = info_.substring(call_ + 1).trim();
                                if (info_.isEmpty()) {
                                    break;
                                }
                            }
                            br_ = new ConstructorBlock(_context, index_, currentParent_, accessFct_, EMPTY_STRING, EMPTY_STRING, parametersType_, parametersName_);
                            currentParent_.appendChild(br_);
                        }
                    } else {
                        if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_CATCH))) {
                            String info_ = found_.trim().substring((prefixKeyWord(KEY_WORD_CATCH)).length());
                            info_ = info_.substring(info_.indexOf(BEGIN_CALLING)+1).trim();
                            String declaringType_ = getDeclaringType(info_);
                            info_ = info_.substring(declaringType_.length()).trim();
                            String variable_ = info_.substring(0, info_.indexOf(END_CALLING)).trim();
                            br_ = new CatchEval(_context, index_, currentParent_, declaringType_, variable_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_CASE))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_CASE)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING)).trim();
                            br_ = new CaseCondition(_context, index_, currentParent_, exp_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_DEFAULT))) {
                            br_ = new DefaultCondition(_context, index_, currentParent_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_IF))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_IF)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING)).trim();
                            br_ = new IfCondition(_context, index_, currentParent_, exp_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_ELSEIF))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_ELSEIF)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING)).trim();
                            br_ = new ElseIfCondition(_context, index_, currentParent_, exp_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_WHILE))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_WHILE)).length());
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING)).trim();
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
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_FOREACH)).length()).trim();
                            String indexClassName_ = EMPTY_STRING;
                            if (exp_.indexOf(BEGIN_ARRAY) == 0) {
                                indexClassName_ = exp_.substring(0, exp_.indexOf(END_ARRAY));
                                exp_ = exp_.substring(exp_.indexOf(END_ARRAY) + 1).trim();
                            }
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1).trim();
                            String declaringType_ = getDeclaringType(exp_);
                            exp_ = exp_.substring(declaringType_.length()).trim();
                            String variable_ = exp_.substring(0, exp_.indexOf(FOR_BLOCKS)).trim();
                            exp_ = exp_.substring(exp_.indexOf(FOR_BLOCKS) + 1, exp_.lastIndexOf(END_CALLING)).trim();
                            br_ = new ForEachLoop(_context, index_, currentParent_, declaringType_, variable_, exp_, indexClassName_);
                            currentParent_.appendChild(br_);
                        } else if (found_.trim().startsWith(prefixKeyWord(KEY_WORD_FOR))) {
                            String exp_ = found_.trim().substring((prefixKeyWord(KEY_WORD_FOR)).length()).trim();
                            String indexClassName_ = EMPTY_STRING;
                            if (exp_.indexOf(BEGIN_ARRAY) == 0) {
                                indexClassName_ = exp_.substring(0, exp_.indexOf(END_ARRAY));
                                exp_ = exp_.substring(exp_.indexOf(END_ARRAY) + 1).trim();
                            }
                            exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1).trim();
                            String declaringType_ = getDeclaringType(exp_);
                            exp_ = exp_.substring(declaringType_.length()).trim();
                            String variable_ = exp_.substring(0, exp_.indexOf(PART_SEPARATOR)).trim();
                            exp_ = exp_.substring(exp_.indexOf(PART_SEPARATOR) + 1).trim();
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
            i_++;
        }
        if (okType_) {
            i_++;
            col_++;
        }
        out_.setNextIndex(i_);
        out_.setNextRowCol(new RowCol());
        out_.getNextRowCol().setCol(col_);
        out_.getNextRowCol().setCol(row_);
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
}
