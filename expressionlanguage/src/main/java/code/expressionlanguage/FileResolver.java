package code.expressionlanguage;

import code.expressionlanguage.methods.AccessEnum;
import code.sml.RowCol;
import code.util.CustList;
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
    private static final String COMMENT_LINE = "//";
    private static final String COMMENT_MULTI_LINE_BEGIN = "/*";
    private static final String COMMENT_MULTI_LINE_END = "*/";
    private static final String NEW = "$new";
    private static final char SEP_ENUM_CONST = ',';
    private static final char BEGIN_TEMPLATE = '<';
    private static final char END_TEMPLATE = '>';
    private static final char BEGIN_BLOCK = '{';
    private static final char END_BLOCK = '}';
    private static final char BEGIN_CALLING = '(';
    private static final char END_CALLING = ')';
    private static final char PART_SEPARATOR = '=';
    private static final String INTERFACES = "interfaces";
    private static final char KEY_WORD_PREFIX = '$';
    private static final char SPACE = ' ';
    private static final String KEY_WORD_PUBLIC = "public";
    private static final String KEY_WORD_PACKAGE = "package";
    private static final String KEY_WORD_PROTECTED = "protected";
    private static final String KEY_WORD_PRIVATE = "private";
    private static final String KEY_WORD_INTERFACE = "interface";
    private static final String KEY_WORD_CLASS = "class";
    private static final String KEY_WORD_ENUM = "enum";
    private static final String KEY_WORD_FIELD = "field";
    private static final String KEY_WORD_METHOD = "method";
    private static final String KEY_WORD_STATIC = "static";

    private FileResolver(){
    }
    public static void parseFile(String _fileName, String _file, ContextEl _context) {
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
                    System.err.println(importedTypes_);
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
        boolean enabledTab_ = true;
        AccessEnum access_;
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
            return;
        }
        if (!Character.isWhitespace(_file.charAt(nextIndex_))) {
            //ERROR
            return;
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
                return;
            }
            if (!Character.isWhitespace(currentChar_)) {
                break;
            }
            nextIndex_++;
        }
        if (nextIndex_ > len_) {
            //ERROR
            return;
        }
        if (_file.charAt(nextIndex_) != KEY_WORD_PREFIX) {
            //ERROR
            return;
        }
        nextIndex_++;
        char currentChar_ = _file.charAt(nextIndex_);
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
            return;
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
                return;
            }
            if (Character.isWhitespace(currentChar_)) {
                nextIndex_++;
                continue;
            }
            enabledTab_ = false;
            break;
        }
        if (!StringList.isWordChar(_file.charAt(nextIndex_))) {
            //ERROR
            return;
        }
        str_ = new StringBuilder();
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
        System.out.println();
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
                return;
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
                typeNamePref_.append(currentChar_);
            }
            if (currentChar_ == END_TEMPLATE) {
                nbOpened_--;
            }
            if (currentChar_ == INHERIT && nbOpened_ == 0) {
                if (foundInherit_) {
                    RowCol rc_ = new RowCol();
                    rc_.setRow(rowSuperTypes_);
                    rc_.setCol(colSuperTypes_);
                    System.out.println(rc_.display());
                    System.out.println(str_.toString());
                    FileRowCol frc_ = new FileRowCol(_fileName, typeNamePref_.toString(), rc_);
                    superTypes_.put(frc_, str_.toString());
                }
                str_.delete(0, str_.length());
                foundInherit_ = true;
                nextIndex_++;
                continue;
            }
            if (currentChar_ == BEGIN_BLOCK) {
                ok_ = true;
                break;
            }
            if (currentChar_ == BEGIN_COMMENT) {
                //ERROR
                return;
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
            System.out.println(rc_.display());
            System.out.println(str_.toString());
            FileRowCol frc_ = new FileRowCol(_fileName, typeNamePref_.toString(), rc_);
            superTypes_.put(frc_, str_.toString());
        }
        if (!ok_) {
            //ERROR
            return;
        }
        col_--;
        System.out.println(row_+"/"+col_);
        allowedComments_ = true;
        System.out.println(_file);
        //System.out.println(importedTypes_);
        System.out.println(typeNamePref_);
        //System.out.println(superTypes_);
        System.out.println(templateDef_);
        //System.out.println(row_+"/"+col_);
    }
}
