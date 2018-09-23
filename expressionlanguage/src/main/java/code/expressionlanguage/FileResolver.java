package code.expressionlanguage;

import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.AnnotationBlock;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.BreakBlock;
import code.expressionlanguage.methods.CaseCondition;
import code.expressionlanguage.methods.CatchEval;
import code.expressionlanguage.methods.ClassBlock;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.ContinueBlock;
import code.expressionlanguage.methods.DeclareVariable;
import code.expressionlanguage.methods.DefaultCondition;
import code.expressionlanguage.methods.DoBlock;
import code.expressionlanguage.methods.DoWhileCondition;
import code.expressionlanguage.methods.ElementBlock;
import code.expressionlanguage.methods.ElseCondition;
import code.expressionlanguage.methods.ElseIfCondition;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.methods.FinallyEval;
import code.expressionlanguage.methods.ForEachLoop;
import code.expressionlanguage.methods.ForIterativeLoop;
import code.expressionlanguage.methods.ForMutableIterativeLoop;
import code.expressionlanguage.methods.IfCondition;
import code.expressionlanguage.methods.InstanceBlock;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.Line;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.NullCatchEval;
import code.expressionlanguage.methods.OperatorBlock;
import code.expressionlanguage.methods.ReturnMehod;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.StaticBlock;
import code.expressionlanguage.methods.SwitchBlock;
import code.expressionlanguage.methods.Throwing;
import code.expressionlanguage.methods.TryEval;
import code.expressionlanguage.methods.WhileCondition;
import code.expressionlanguage.methods.util.DuplicateType;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;

public final class FileResolver {

    public static final char SUPPLEMENT_CHAR = '$';
    private static final char INHERIT = ':';
    private static final char FOR_BLOCKS = ':';
    private static final char END_LINE = ':';
    private static final char END_IMPORTS = ';';
    private static final char LINE_RETURN = '\n';
    private static final char TAB = '\t';
    private static final char BEGIN_COMMENT = '/';
    private static final char SECOND_COMMENT = '*';
    private static final char PKG = '.';
    private static final char TYPE_VAR = '#';
    private static final String EMPTY_STRING = "";
    private static final String VARARG = "...";
    private static final String NEW = "$new";
    private static final char SEP_ENUM_CONST = ',';
    private static final char BEGIN_TEMPLATE = '<';
    private static final char END_TEMPLATE = '>';
    private static final char BEGIN_BLOCK = '{';
    private static final char END_BLOCK = '}';
    private static final char BEGIN_ARRAY = '[';
    private static final char END_ARRAY = ']';
    private static final char BEGIN_CALLING = '(';
    private static final char SEP_CALLING = ',';
    private static final char END_CALLING = ')';
    private static final char PART_SEPARATOR = '=';
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char ESCAPE = '\\';
    private static final char ANNOT = '@';
    private static final String KEY_WORD_INTERFACES = "interfaces";
    private static final char KEY_WORD_PREFIX = SUPPLEMENT_CHAR;
    private static final String KEY_WORD_PUBLIC = "public";
    private static final String KEY_WORD_PACKAGE = "package";
    private static final String KEY_WORD_PROTECTED = "protected";
    private static final String KEY_WORD_PRIVATE = "private";
    private static final String KEY_WORD_INTERFACE = "interface";
    private static final String KEY_WORD_ANNOTATION = "annotation";
    private static final String KEY_WORD_CLASS = "class";
    private static final String KEY_WORD_ENUM = "enum";
    private static final String KEY_WORD_STATIC = "static";
    private static final String KEY_WORD_ABSTRACT = "abstract";
    private static final String KEY_WORD_FINAL = "final";
    private static final String KEY_WORD_NORMAL = "normal";

    private static final String KEY_WORD_ITER = "iter";
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

    private static final String KEY_WORD_OPERATOR = "operator";

    private FileResolver(){
    }
    public static void parseFile(String _fileName, String _file, boolean _predefined, ContextEl _context) {
        FileBlock fileBlock_ = new FileBlock(new OffsetsBlock(),_predefined);
        fileBlock_.setFileName(_fileName);
        EnablingSpaces enabledSpaces_ = new EnablingSpaces();
        enabledSpaces_.setEnabledSpace(true);
        enabledSpaces_.setEnabledTab(true);
        enabledSpaces_.setFile(fileBlock_);
        enabledSpaces_.setTabWidth(_context.getTabWidth());
        StringList importedTypes_ = new StringList();
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
        int indexImport_ = 0;
        Numbers<Integer> offsetsImports_ = new Numbers<Integer>();
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (commentedSingleLine_) {
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                    enabledSpaces_.setCheckTabs(true);
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
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
                        enabledSpaces_.setCheckTabs(true);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                        previousChar_ = nextChar_;
                        continue;
                    }
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                continue;
            }
            if (currentChar_ == KEY_WORD_PREFIX) {
                if (_file.indexOf(prefixKeyWord(KEY_WORD_PUBLIC), i_) == i_) {
                    break;
                }
                if (_file.indexOf(prefixKeyWord(KEY_WORD_OPERATOR), i_) == i_) {
                    break;
                }
                if (_file.indexOf(prefixKeyWord(KEY_WORD_PROTECTED), i_) == i_) {
                    break;
                }
                if (_file.indexOf(prefixKeyWord(KEY_WORD_PACKAGE), i_) == i_) {
                    break;
                }
                if (_file.indexOf(prefixKeyWord(KEY_WORD_PRIVATE), i_) == i_) {
                    break;
                }
            }
            if (currentChar_ == ANNOT) {
                break;
            }
            if (!enabledSpaces_.isOk()) {
                //ERROR
                return;
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
                    enabledSpaces_.setCheckTabs(false);
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                    previousChar_ = nextChar_;
                    continue;
                }
                if (nextChar_ == SECOND_COMMENT) {
                    commentedMultiLine_ = true;
                    enabledSpaces_.setCheckTabs(false);
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                    previousChar_ = nextChar_;
                    continue;
                }
                //ERROR
                return;
            }
            if (currentChar_ == END_IMPORTS) {
                importedTypes_.add(str_.toString());
                offsetsImports_.add(indexImport_);
                str_.delete(0, str_.length());
            } else {
                if (!Character.isWhitespace(currentChar_)) {
                    allowedComments_ = false;
                    if (str_.length() == 0) {
                        indexImport_ = i_;
                    }
                    str_.append(currentChar_);
                } else if (currentChar_ == LINE_RETURN && previousChar_ == END_IMPORTS) {
                    allowedComments_ = true;
                } else {
                    str_.append(currentChar_);
                }
            }
            previousChar_ = currentChar_;
            i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
        }
        if (i_ >= len_) {
            return;
        }
        InputTypeCreation input_ = new InputTypeCreation();
        input_.setNextRowCol(new RowCol());
        input_.getNextRowCol().setRow(readRc_.getRow());
        input_.getNextRowCol().setCol(readRc_.getCol());
        input_.setNextIndex(i_);
        fileBlock_.getImports().addAllElts(importedTypes_);
        fileBlock_.getImportsOffset().addAllElts(offsetsImports_);
        input_.setFileBlock(fileBlock_);
        input_.setEnabledSpaces(enabledSpaces_);
        _context.getClasses().putFileBlock(_fileName, fileBlock_);
        while (true) {
            input_.setNextRowCol(new RowCol());
            input_.getNextRowCol().setRow(readRc_.getRow());
            input_.getNextRowCol().setCol(readRc_.getCol());
            input_.setNextIndex(i_);
            ResultCreation res_ = createType(_context, _fileName, _file, input_, importedTypes_);
            if (!res_.isOk()) {
                return;
            }
            if (res_ instanceof ResultTypeCreation) {
                ResultTypeCreation restype_ = (ResultTypeCreation) res_;
                RootBlock r_ = restype_.getType();
                fileBlock_.appendChild(r_);
                Block c_ = r_;
                if (c_.getFirstChild() != null) {
                    StringList simpleNames_ = new StringList();
                    while (true) {
                        if (c_ instanceof RootBlock) {
                            RootBlock cur_ = (RootBlock) c_;
                            String s_ = cur_.getName();
                            if (simpleNames_.containsStr(s_)) {
                                //ERROR
                                DuplicateType d_ = new DuplicateType();
                                d_.setId(cur_.getFullName());
                                d_.setFileName(cur_.getFile().getFileName());
                                d_.setRc(cur_.getRowCol(0, cur_.getIdRowCol()));
                                _context.getClasses().addError(d_);
                            }
                            _context.getClasses().processBracedClass(cur_, _predefined, _context);
                        }
                        Block fc_ = c_.getFirstChild();
                        if (fc_ != null) {
                            if (c_ instanceof RootBlock) {
                                String s_ = ((RootBlock)c_).getName();
                                simpleNames_.add(s_);
                            }
                            c_ = fc_;
                            continue;
                        }
                        boolean end_ = false;
                        while (true) {
                            Block n_ = c_.getNextSibling();
                            if (n_ != null) {
                                c_ = n_;
                                break;
                            }
                            BracedBlock p_ = c_.getParent();
                            if (p_ == r_) {
                                end_ = true;
                                break;
                            }
                            c_ = p_;
                            simpleNames_.removeLast();
                        }
                        if (end_) {
                            break;
                        }
                    }
                } else {
                    _context.getClasses().processBracedClass(r_, _predefined, _context);
                }
            }
            if (res_ instanceof ResultOperatorCreation) {
                ResultOperatorCreation restype_ = (ResultOperatorCreation) res_;
                OperatorBlock r_ = restype_.getType();
                fileBlock_.appendChild(r_);
                _context.getClasses().getOperators().add(r_);
            }
            input_.setIndexChild(input_.getIndexChild() + 1);
            i_ = res_.getNextIndex();
            boolean hasNext_ = false;
            while (i_ < len_) {
                char currentChar_ = _file.charAt(i_);
                if (commentedSingleLine_) {
                    if (currentChar_ == LINE_RETURN) {
                        commentedSingleLine_ = false;
                        enabledSpaces_.setCheckTabs(true);
                    }
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
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
                            enabledSpaces_.setCheckTabs(true);
                            i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                            i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                            previousChar_ = nextChar_;
                            continue;
                        }
                    }
                    i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                    continue;
                }
                if (currentChar_ == KEY_WORD_PREFIX) {
                    hasNext_ = true;
                    break;
                }
                if (currentChar_ == ANNOT) {
                    hasNext_ = true;
                    break;
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
                        enabledSpaces_.setCheckTabs(false);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                        previousChar_ = nextChar_;
                        continue;
                    }
                    if (nextChar_ == SECOND_COMMENT) {
                        commentedMultiLine_ = true;
                        enabledSpaces_.setCheckTabs(false);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
                        previousChar_ = nextChar_;
                        continue;
                    }
                    //ERROR
                    return;
                }
                if (!enabledSpaces_.isOk()) {
                    //ERROR
                    return;
                }
                if (!Character.isWhitespace(currentChar_)) {
                    allowedComments_ = false;
                    str_.append(currentChar_);
                } else if (currentChar_ == LINE_RETURN && previousChar_ == END_IMPORTS) {
                    allowedComments_ = true;
                }
                previousChar_ = currentChar_;
                i_ = incrementRowCol(i_, _file, tabWidth_, readRc_, enabledSpaces_);
            }
            if (!hasNext_) {
                return;
            }
            input_.getNextRowCol().setRow(readRc_.getRow());
            input_.getNextRowCol().setCol(readRc_.getCol());
            input_.setNextIndex(i_);
        }
    }
    private static ResultCreation createType(ContextEl _context, String _fileName, String _file, InputTypeCreation _input, StringList _import) {
        Options options_ = _context.getOptions();
        EnablingSpaces enabledSpaces_ = _input.getEnabledSpaces();
        ResultCreation out_ = new ResultTypeCreation();
        AccessEnum access_;
        int i_ = _input.getNextIndex();
        RowCol current_ = _input.getNextRowCol();
        int tabWidth_ = _context.getTabWidth();
        int len_ = _file.length();
        updateAllowedSpaces(i_, _file, enabledSpaces_);
        int nextIndex_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
        int beginType_ = nextIndex_;
        int accessOffsetType_ = beginType_;
        String afterAccessType_ = _file.substring(i_);
        Numbers<Integer> annotationsIndexesTypes_ = new Numbers<Integer>();
        StringList annotationsTypes_ = new StringList();
        int deltaType_ = 0;
        if (afterAccessType_.trim().charAt(0) == ANNOT) {
            // accessOffesType_ == nextIndex_ == i_ + 1;
            ParsedAnnotations par_ = new ParsedAnnotations(afterAccessType_, i_);
            par_.parse();
            annotationsIndexesTypes_ = par_.getAnnotationsIndexes();
            annotationsTypes_ = par_.getAnnotations();
            afterAccessType_ = par_.getAfter();
            accessOffsetType_ = par_.getIndex();
            deltaType_ = accessOffsetType_ - i_;
        }
        nextIndex_ += deltaType_;
        boolean oper_ = false;
        StringBuilder symbol_ = new StringBuilder();
        int symbolIndex_ = -1;
        if (startsWithPrefixKeyWord(afterAccessType_, KEY_WORD_PUBLIC)) {
            access_ = AccessEnum.PUBLIC;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_PUBLIC.length(), _file, tabWidth_, current_, enabledSpaces_);
        } else if (startsWithPrefixKeyWord(afterAccessType_, KEY_WORD_PROTECTED)) {
            access_ = AccessEnum.PROTECTED;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_PROTECTED.length(), _file, tabWidth_, current_, enabledSpaces_);
        } else if (startsWithPrefixKeyWord(afterAccessType_, KEY_WORD_PACKAGE)) {
            access_ = AccessEnum.PACKAGE;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_PACKAGE.length(), _file, tabWidth_, current_, enabledSpaces_);
        } else if (startsWithPrefixKeyWord(afterAccessType_, KEY_WORD_PRIVATE)) {
            access_ = AccessEnum.PRIVATE;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_PRIVATE.length(), _file, tabWidth_, current_, enabledSpaces_);
        } else if (startsWithPrefixKeyWord(afterAccessType_, KEY_WORD_OPERATOR)) {
            access_ = AccessEnum.PUBLIC;
            oper_ = true;
            nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_OPERATOR.length(), _file, tabWidth_, current_, enabledSpaces_);
        } else {
            //ERROR
            return out_;
        }
        nextIndex_ = skipWhitespace(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
        if (nextIndex_ < 0) {
            //ERROR
            return out_;
        }
        if (oper_) {
            symbolIndex_ = nextIndex_;
            while (nextIndex_ < len_) {
                char currentChar_ = _file.charAt(nextIndex_);
                if (!isOperatorCharacter(currentChar_)) {
                    //found space or import or return type
                    break;
                }
                symbol_.append(currentChar_);
                nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            }
            nextIndex_ = skipWhitespace(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            if (nextIndex_ < 0) {
                //ERROR
                return out_;
            }
        }
        if (nextIndex_ > len_) {
            //ERROR
            return out_;
        }
        StringList importedTypes_;
        Numbers<Integer> offsetsImports_;
        boolean enableByEndLine_ = false;
        BracedBlock currentParent_;
        Numbers<Integer> braces_ = new Numbers<Integer>();
        boolean allowedComments_ = false;
        if (oper_) {
            ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            importedTypes_ = p_.getImportedTypes();
            offsetsImports_ = p_.getOffsetsImports();
            nextIndex_ = p_.getNextIndex();
            if (!p_.isOk()) {
                //ERROR
                return out_;
            }
            out_ = new ResultOperatorCreation();
            int until_ = untilOperator(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            String header_ = _file.substring(nextIndex_, until_);
            String info_ = header_;
            int typeOffset_ = nextIndex_;
            int paramOffest_;
            String declaringType_ = EMPTY_STRING;
            String afterModifier_ = info_;
            info_ = afterModifier_.trim();
            declaringType_ = getDeclaringTypeOper(info_, options_);
            int declTypeLen_ = declaringType_.length();
            String afterType_ = info_.substring(declTypeLen_);
            info_ = afterType_.trim();
            int leftParIndex_ = info_.indexOf(BEGIN_CALLING);
            String afterMethodName_ = info_.substring(leftParIndex_ + 1);
            paramOffest_ = typeOffset_ + declTypeLen_ + 1;
            paramOffest_ += StringList.getFirstPrintableCharIndex(afterMethodName_);
            info_ = afterMethodName_.trim();
            Numbers<Integer> offestsTypes_ = new Numbers<Integer>();
            Numbers<Integer> offestsParams_ = new Numbers<Integer>();
            StringList parametersType_ = new StringList();
            StringList parametersName_ = new StringList();
            CustList<Numbers<Integer>> annotationsIndexesParams_ = new CustList<Numbers<Integer>>();
            CustList<StringList> annotationsParams_ = new CustList<StringList>();
            while (true) {
                if (info_.indexOf(END_CALLING) == 0) {
                    break;
                }
                Numbers<Integer> annotationsIndexesParam_ = new Numbers<Integer>();
                StringList annotationsParam_ = new StringList();
                if (info_.trim().charAt(0) == ANNOT) {
                    ParsedAnnotations par_ = new ParsedAnnotations(info_, paramOffest_);
                    par_.parse();
                    annotationsIndexesParam_ = par_.getAnnotationsIndexes();
                    annotationsParam_ = par_.getAnnotations();
                    info_ = par_.getAfter();
                    paramOffest_ = par_.getIndex();
                    paramOffest_ += StringList.getFirstPrintableCharIndex(info_);
                }
                annotationsIndexesParams_.add(annotationsIndexesParam_);
                annotationsParams_.add(annotationsParam_);
                offestsTypes_.add(paramOffest_);
                String paramType_ = getDeclaringParamType(info_, options_);
                parametersType_.add(paramType_.trim());
                String afterParamType_ = info_.substring(paramType_.length());
                info_ = afterParamType_.trim();
                int call_ = info_.indexOf(SEP_CALLING);
                if (call_ < 0) {
                    call_ = info_.indexOf(END_CALLING);
                }
                int off_ = StringList.getFirstPrintableCharIndex(afterParamType_);
                if (off_ < 0) {
                    off_ = 0;
                }
                offestsParams_.add(paramOffest_ + paramType_.length() + off_);
                String paramName_ = info_.substring(0, call_);
                parametersName_.add(paramName_.trim());
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
            currentParent_ = new OperatorBlock(_context, _input.getIndexChild(), _input.getFileBlock(), new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(symbolIndex_, symbol_.toString().trim()), parametersType_, offestsTypes_, parametersName_, offestsParams_, new OffsetsBlock(nextIndex_, nextIndex_));
            ((NamedFunctionBlock)currentParent_).getAnnotationsParams().addAllElts(annotationsParams_);
            ((NamedFunctionBlock)currentParent_).getAnnotationsIndexesParams().addAllElts(annotationsIndexesParams_);
            ((OperatorBlock)currentParent_).getImports().addAllElts(importedTypes_);
            ((OperatorBlock)currentParent_).getImportsOffset().addAllElts(offsetsImports_);
            currentParent_.getAnnotations().addAllElts(annotationsTypes_);
            currentParent_.getAnnotationsIndexes().addAllElts(annotationsIndexesTypes_);
            ((ResultOperatorCreation)out_).setType((OperatorBlock) currentParent_);
            nextIndex_ = incrementRowCol(until_, _file, tabWidth_, current_, enabledSpaces_);
        } else {
//            ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
//            importedTypes_ = p_.getImportedTypes();
//            offsetsImports_ = p_.getOffsetsImports();
//            nextIndex_ = p_.getNextIndex();
//            if (!p_.isOk()) {
//                //ERROR
//                return out_;
//            }
            out_ = new ResultTypeCreation();
            if (_file.charAt(nextIndex_) != KEY_WORD_PREFIX) {
                //ERROR
                return out_;
            }
            char currentChar_ = _file.charAt(nextIndex_);
            nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            boolean abstractType_ = false;
            boolean finalType_ = false;
            if (_file.substring(nextIndex_).startsWith(KEY_WORD_ABSTRACT)) {
                abstractType_ = true;
                nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_ABSTRACT.length(), _file, tabWidth_, current_, enabledSpaces_);
                nextIndex_ = skipWhitespace(nextIndex_, _file, tabWidth_, current_,  enabledSpaces_);
            }
            if (_file.substring(nextIndex_).startsWith(KEY_WORD_FINAL)) {
                finalType_ = true;
                nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_FINAL.length(), _file, tabWidth_, current_, enabledSpaces_);
                nextIndex_ = skipWhitespace(nextIndex_, _file, tabWidth_, current_,  enabledSpaces_);
            }
            if (abstractType_ || finalType_) {
                if (nextIndex_ < 0) {
                    //ERROR
                    return out_;
                }
                if (nextIndex_ > len_) {
                    //ERROR
                    return out_;
                }
                if (_file.charAt(nextIndex_) != KEY_WORD_PREFIX) {
                    //ERROR
                    return out_;
                }
                nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            }
            int categoryOffset_ = nextIndex_ - 1;
            currentChar_ = _file.charAt(nextIndex_);
            String type_;
            if (_file.substring(nextIndex_).startsWith(KEY_WORD_CLASS)) {
                type_ = KEY_WORD_CLASS;
                nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_CLASS.length(), _file, tabWidth_, current_, enabledSpaces_);
            } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_ENUM)) {
                type_ = KEY_WORD_ENUM;
                nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_ENUM.length(), _file, tabWidth_, current_, enabledSpaces_);
            } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_INTERFACE)) {
                type_ = KEY_WORD_INTERFACE;
                nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_INTERFACE.length(), _file, tabWidth_, current_, enabledSpaces_);
            } else if (_file.substring(nextIndex_).startsWith(KEY_WORD_ANNOTATION)) {
                type_ = KEY_WORD_ANNOTATION;
                nextIndex_ = incrementRowCol(nextIndex_, KEY_WORD_ANNOTATION.length(), _file, tabWidth_, current_, enabledSpaces_);
            } else {
                //ERROR
                return out_;
            }
            nextIndex_ = skipWhitespace(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            if (nextIndex_ < 0) {
                //ERROR
                return out_;
            }
            ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            importedTypes_ = p_.getImportedTypes();
            offsetsImports_ = p_.getOffsetsImports();
            nextIndex_ = p_.getNextIndex();
            if (!p_.isOk()) {
                //ERROR
                return out_;
            }
            if (!StringList.isDollarWordChar(_file.charAt(nextIndex_))) {
                //ERROR
                return out_;
            }
            //insert interfaces static initialization for class and enums
            StringList staticInitInterfaces_ = new StringList();
            Numbers<Integer> staticInitInterfacesOffset_ = new Numbers<Integer>();
            if (startsWithPrefixKeyWord(_file.substring(nextIndex_), KEY_WORD_INTERFACES)) {
                int begin_ = _file.indexOf(BEGIN_CALLING, nextIndex_);
                if (begin_ < 0) {
                    //ERROR
                    return out_;
                }
                int end_ = _file.indexOf(END_CALLING, begin_);
                if (end_ < 0) {
                    //ERROR
                    return out_;
                }
                int interfaceOffest_ = begin_ + 1;
                String interfacesInfo_ = _file.substring(begin_ + 1, end_);
                for (int i = begin_ + 1; i < end_; i++) {
                    updateAllowedSpaces(i, _file, enabledSpaces_);
                    if (!enabledSpaces_.isOk()) {
                        //ERROR
                        return out_;
                    }
                }
                for (String p: StringList.splitChars(interfacesInfo_, SEP_CALLING)) {
                    staticInitInterfaces_.add(p);
                    staticInitInterfacesOffset_.add(interfaceOffest_);
                    interfaceOffest_ += p.length() + 1;
                }
                nextIndex_ = end_ + 1;
            }
            nextIndex_ = skipWhitespace(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            if (nextIndex_ < 0) {
                //ERROR
                return out_;
            }
            StringBuilder str_ = new StringBuilder();
            NatTreeMap<Integer, String> superTypes_ = new NatTreeMap<Integer, String>();
            StringBuilder typeNamePref_ = new StringBuilder();
            StringBuilder templateDef_ = new StringBuilder();
            int nbOpened_ = 0;
            boolean ok_ = false;
            boolean foundInherit_ = false;
            int beginDefinition_ = nextIndex_;
            int inheritIndex_ = -1;
            while (nextIndex_ < len_) {
                currentChar_ = _file.charAt(nextIndex_);
                if (!enabledSpaces_.isOk()) {
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
                        superTypes_.put(inheritIndex_, str_.toString());
                    }
                    str_.delete(0, str_.length());
                    foundInherit_ = true;
                    nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
                    inheritIndex_ = nextIndex_;
                    continue;
                }
                if (currentChar_ == BEGIN_BLOCK) {
                    braces_.add(nextIndex_);
                    ok_ = true;
                    break;
                }
                if (currentChar_ == BEGIN_COMMENT) {
                    //ERROR
                    return out_;
                }
                if (foundInherit_) {
                    str_.append(currentChar_);
                }
                nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            }
            if (foundInherit_) {
                superTypes_.put(inheritIndex_, str_.toString());
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
            nextIndex_ = incrementRowCol(nextIndex_, _file, tabWidth_, current_, enabledSpaces_);
            RootBlock typeBlock_;
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
            
            if (StringList.quickEq(type_, KEY_WORD_ENUM)) {
                enableByEndLine_ = true;
                typeBlock_ = new EnumBlock(_context, _input.getIndexChild(), _input.getFileBlock(), beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, access_) , tempDef_, superTypes_, new OffsetsBlock(beginType_ - 1,beginType_ - 1));
            } else if (StringList.quickEq(type_, KEY_WORD_CLASS)) {
                typeBlock_ = new ClassBlock(_context, _input.getIndexChild(), _input.getFileBlock(), beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, access_), tempDef_, superTypes_, finalType_, abstractType_, true, new OffsetsBlock(beginType_ - 1,beginType_ - 1));
            } else if (StringList.quickEq(type_, KEY_WORD_INTERFACE)) {
                typeBlock_ = new InterfaceBlock(_context, _input.getIndexChild(), _input.getFileBlock(), beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, access_) , tempDef_, superTypes_, true, new OffsetsBlock(beginType_ - 1,beginType_ - 1));
            } else {
                typeBlock_ = new AnnotationBlock(_context, _input.getIndexChild(), _input.getFileBlock(), beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, access_) , tempDef_, superTypes_, new OffsetsBlock(beginType_ - 1,beginType_ - 1));
            }
            typeBlock_.getImports().addAllElts(importedTypes_);
            typeBlock_.getImportsOffset().addAllElts(offsetsImports_);
            typeBlock_.getStaticInitInterfaces().addAllElts(staticInitInterfaces_);
            typeBlock_.getStaticInitInterfacesOffset().addAllElts(staticInitInterfacesOffset_);
            typeBlock_.getAnnotations().addAllElts(annotationsTypes_);
            typeBlock_.getAnnotationsIndexes().addAllElts(annotationsIndexesTypes_);
            ((ResultTypeCreation) out_).setType(typeBlock_);
            currentParent_ = typeBlock_;
        }
        StringBuilder instruction_ = new StringBuilder();
        int instructionLocation_ = -1;
        Numbers<Integer> parentheses_ = new Numbers<Integer>();
        Numbers<Integer> indexes_ = new Numbers<Integer>();
        indexes_.add(0);
        boolean commentedSingleLine_ = false;
        boolean commentedMultiLine_ = false;
        boolean constChar_ = false;
        boolean constString_ = false;
        boolean declType_ = false;
        
        i_ = nextIndex_;
        boolean okType_ = false;
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (commentedSingleLine_) {
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                    enabledSpaces_.setCheckTabs(true);
                    instruction_.delete(0, instruction_.length());
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
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
                        enabledSpaces_.setCheckTabs(true);
                        instruction_.delete(0, instruction_.length());
                        i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                        continue;
                    }
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                continue;
            }
            boolean const_ = false;
            if (constString_ || constChar_) {
                const_ = true;
            }
            if (!const_) {
                if (!enabledSpaces_.isOk()) {
                    //ERROR
                    return out_;
                }
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
                    enabledSpaces_.setCheckTabs(false);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    continue;
                }
                if (nextChar_ == SECOND_COMMENT) {
                    if (!allowedComments_) {
                        //ERROR
                        break;
                    }
                    commentedMultiLine_ = true;
                    enabledSpaces_.setCheckTabs(false);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    continue;
                }
            }
            boolean endInstruction_ = false;
            if (parentheses_.isEmpty() && !const_) {
                if (currentChar_ == END_LINE && !declType_) {
                    endInstruction_ = true;
                }
                if (currentChar_ == SEP_ENUM_CONST && enableByEndLine_) {
                    endInstruction_ = true;
                }
                if (currentChar_ == END_BLOCK) {
                    endInstruction_ = true;
                }
                if (currentChar_ == BEGIN_BLOCK) {
                    String tr_ = instruction_.toString().trim();
                    if (tr_.isEmpty()) {
                        endInstruction_ = true;
                    } else if (!StringList.quickEq(tr_, prefixKeyWord(KEY_WORD_RETURN))) {
                        StringList parts_ = StringList.getDollarWordSeparators(instruction_);
                        StringList printable_ = new StringList();
                        for (String p: parts_) {
                            String t_ = p.trim();
                            if (!StringList.isDollarWord(t_)) {
                                continue;
                            }
                            printable_.add(t_);
                        }
                        String last_ = printable_.last();
                        if (!isKeyWordCategory(last_)) {
                            char lastChar_ = tr_.charAt(tr_.length() - 1);
                            if (currentParent_ instanceof AnnotationBlock) {
                                if (lastChar_ != END_CALLING) {
                                    endInstruction_ = true;
                                } else {
                                    String trLeft_ = tr_.substring(0, tr_.length() - 1).trim();
                                    if (!trLeft_.endsWith(String.valueOf(BEGIN_CALLING))) {
                                        endInstruction_ = true;
                                    }
                                }
                            } else if (lastChar_ != PART_SEPARATOR) {
                                if (lastChar_ != END_ARRAY) {
                                    endInstruction_ = true;
                                }
                            }
                        } else {
                            declType_ = true;
                        }
                    }
                }
                //End line
            }
            if (!endInstruction_) {
                if (instruction_.length() == 0) {
                    instructionLocation_ = i_;
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
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    continue;
                }
                if (currentChar_ == DEL_CHAR) {
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    constChar_ = false;
                    enabledSpaces_.setCheckTabs(true);
                    continue;
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                continue;
            }
            if (constString_) {
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        break;
                    }
                    instruction_.append(_file.charAt(i_+1));
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    continue;
                }
                if (currentChar_ == DEL_STRING) {
                    i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
                    constString_ = false;
                    enabledSpaces_.setCheckTabs(true);
                    continue;
                }
                i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
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
                enabledSpaces_.setCheckTabs(false);
            }
            if (currentChar_ == DEL_STRING) {
                constString_ = true;
                enabledSpaces_.setCheckTabs(false);
            }
            if (currentChar_ == BEGIN_CALLING) {
                if (StringList.quickEq(instruction_.substring(0, instruction_.lastIndexOf(String.valueOf(BEGIN_CALLING))).toString().trim(), prefixKeyWord(KEY_WORD_CLASS))) {
                    declType_ = false;
                }
                parentheses_.add(i_);
            }
            if (currentChar_ == BEGIN_ARRAY) {
                parentheses_.add(i_);
            }
            if (currentChar_ == END_CALLING) {
                parentheses_.removeLast();
            }
            if (currentChar_ == END_ARRAY) {
                parentheses_.removeLast();
            }
            if (currentChar_ == BEGIN_BLOCK) {
                if (endInstruction_) {
                    braces_.add(i_);
                } else {
                    parentheses_.add(i_);
                }
            }
            if (currentChar_ == END_BLOCK) {
                if (endInstruction_) {
                    braces_.removeLast();
                } else {
                    parentheses_.removeLast();
                }
            }
            if (braces_.size() == 0 && currentChar_ == END_BLOCK) {
                okType_ = true;
                break;
            }
            if (endInstruction_) {
                String found_ = instruction_.toString();
                String trimmedInstruction_ = found_.trim();
                int instructionRealLocation_ = instructionLocation_;
                if (!trimmedInstruction_.isEmpty()) {
                    instructionLocation_ += StringList.getFirstPrintableCharIndex(found_);
                }
                int index_ = indexes_.last();
                Block br_ = null;
                if (currentParent_ instanceof AnnotationBlock) {
                    if (!trimmedInstruction_.isEmpty()) {
                        String fieldName_;
                        int typeOffset_ = instructionLocation_;
                        int expressionOffest_ = -1;
                        String expression_ = EMPTY_STRING;
                        int delta_ = 0;
                        Numbers<Integer> annotationsIndexes_ = new Numbers<Integer>();
                        StringList annotations_ = new StringList();
                        if (trimmedInstruction_.charAt(0) == ANNOT) {
                            ParsedAnnotations par_ = new ParsedAnnotations(found_, instructionRealLocation_);
                            par_.parse();
                            annotationsIndexes_ = par_.getAnnotationsIndexes();
                            annotations_ = par_.getAnnotations();
                            found_ = par_.getAfter();
                            typeOffset_ = par_.getIndex();
                            delta_ = typeOffset_ - instructionRealLocation_;
                        }
                        String otherModifier_ = EMPTY_STRING;
                        String infoModifiers_ = found_.trim();
                        if (declType_ && currentChar_ == BEGIN_BLOCK) {
                            AccessEnum accessFct_ = AccessEnum.PUBLIC;
                            String word_ = EMPTY_STRING;
                            int trFound_ = StringList.getFirstPrintableCharIndex(found_);
                            if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_PRIVATE)) {
                                accessFct_ = AccessEnum.PRIVATE;
                                word_ = prefixKeyWord(KEY_WORD_PRIVATE);
                            } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_PACKAGE)) {
                                accessFct_ = AccessEnum.PACKAGE;
                                word_ = prefixKeyWord(KEY_WORD_PACKAGE);
                            } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_PROTECTED)) {
                                accessFct_ = AccessEnum.PROTECTED;
                                word_ = prefixKeyWord(KEY_WORD_PROTECTED);
                            } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_PUBLIC)) {
                                accessFct_ = AccessEnum.PUBLIC;
                                word_ = prefixKeyWord(KEY_WORD_PUBLIC);
                            }
                            int accessOffest_ = trFound_ + i_ - found_.length();
                            String afterAccess_ = trimmedInstruction_.substring(word_.length());
                            int locIndex_ = accessOffest_ + word_.length();
                            locIndex_ += StringList.getFirstPrintableCharIndex(afterAccess_);
                            RowCol locRc_ = new RowCol();
                            locRc_.setCol(current_.getCol());
                            locRc_.setRow(current_.getRow());
                            EnablingSpaces enLoc_ = new EnablingSpaces();
                            enLoc_.setBegin(enabledSpaces_.getBegin());
                            enLoc_.setCheckTabs(enabledSpaces_.isCheckTabs());
                            enLoc_.setEnabledSpace(enabledSpaces_.isEnabledSpace());
                            enLoc_.setEnabledTab(enabledSpaces_.isEnabledTab());
                            enLoc_.setEnd(enabledSpaces_.getEnd());
                            enLoc_.setFile(enabledSpaces_.getFile());
                            enLoc_.setOk(enabledSpaces_.isOk());
                            enLoc_.setOnlySpacesLine(enabledSpaces_.isOnlySpacesLine());
                            enLoc_.setTabWidth(enabledSpaces_.getTabWidth());
                            //Inner types
                            boolean abstractType_ = false;
                            boolean finalType_ = false;
                            if (_file.substring(locIndex_).startsWith(prefixKeyWord(KEY_WORD_ABSTRACT))) {
                                abstractType_ = true;
                                locIndex_ = incrementRowCol(locIndex_, KEY_WORD_ABSTRACT.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_,  enLoc_);
                            }
                            if (_file.substring(locIndex_).startsWith(prefixKeyWord(KEY_WORD_FINAL))) {
                                finalType_ = true;
                                locIndex_ = incrementRowCol(locIndex_, KEY_WORD_FINAL.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_,  enLoc_);
                            }
                            String type_ = EMPTY_STRING;
                            int categoryOffset_ = locIndex_;
                            infoModifiers_ = _file.substring(locIndex_);
                            if (infoModifiers_.startsWith(prefixKeyWord(KEY_WORD_CLASS))) {
                                type_ = KEY_WORD_CLASS;
                                locIndex_ = incrementRowCol(locIndex_, KEY_WORD_CLASS.length()+1, _file, tabWidth_, locRc_, enLoc_);
                            } else if (infoModifiers_.startsWith(prefixKeyWord(KEY_WORD_ENUM))) {
                                type_ = KEY_WORD_ENUM;
                                locIndex_ = incrementRowCol(locIndex_, KEY_WORD_ENUM.length()+1, _file, tabWidth_, locRc_, enLoc_);
                            } else if (infoModifiers_.startsWith(prefixKeyWord(KEY_WORD_INTERFACE))) {
                                type_ = KEY_WORD_INTERFACE;
                                locIndex_ = incrementRowCol(locIndex_, KEY_WORD_INTERFACE.length()+1, _file, tabWidth_, locRc_, enLoc_);
                            } else if (infoModifiers_.startsWith(prefixKeyWord(KEY_WORD_ANNOTATION))) {
                                type_ = KEY_WORD_ANNOTATION;
                                locIndex_ = incrementRowCol(locIndex_, KEY_WORD_ANNOTATION.length()+1, _file, tabWidth_, locRc_, enLoc_);
                            }
                            if (locIndex_ < 0) {
                                //ERROR
                                return out_;
                            }
                            locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_,  enLoc_);
                            ParsedImportedTypes p_ = new ParsedImportedTypes(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                            importedTypes_ = p_.getImportedTypes();
                            offsetsImports_ = p_.getOffsetsImports();
                            locIndex_ = p_.getNextIndex();
                            if (!p_.isOk()) {
                                return out_;
                            }
                            if (!StringList.isDollarWordChar(_file.charAt(locIndex_))) {
                                //ERROR
                                return out_;
                            }
                            //insert interfaces static initialization for class and enums
                            StringList staticInitInterfaces_ = new StringList();
                            Numbers<Integer> staticInitInterfacesOffset_ = new Numbers<Integer>();
                            if (startsWithPrefixKeyWord(_file.substring(locIndex_), KEY_WORD_INTERFACES)) {
                                int begin_ = _file.indexOf(BEGIN_CALLING, locIndex_);
                                if (begin_ < 0) {
                                    //ERROR
                                    return out_;
                                }
                                int end_ = _file.indexOf(END_CALLING, begin_);
                                if (end_ < 0) {
                                    //ERROR
                                    return out_;
                                }
                                int interfaceOffest_ = begin_ + 1;
                                String interfacesInfo_ = _file.substring(begin_ + 1, end_);
                                for (int i = begin_ + 1; i < end_; i++) {
                                    updateAllowedSpaces(i, _file, enLoc_);
                                    if (!enLoc_.isOk()) {
                                        //ERROR
                                        return out_;
                                    }
                                }
                                for (String p: StringList.splitChars(interfacesInfo_, SEP_CALLING)) {
                                    staticInitInterfaces_.add(p);
                                    staticInitInterfacesOffset_.add(interfaceOffest_);
                                    interfaceOffest_ += p.length() + 1;
                                }
                                locIndex_ = end_ + 1;
                            }
                            locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                            if (locIndex_ < 0) {
                                //ERROR
                                return out_;
                            }
                            StringBuilder str_ = new StringBuilder();
                            NatTreeMap<Integer, String> superTypes_ = new NatTreeMap<Integer, String>();
                            StringBuilder typeNamePref_ = new StringBuilder();
                            StringBuilder templateDef_ = new StringBuilder();
                            int nbOpened_ = 0;
                            boolean ok_ = false;
                            boolean foundInherit_ = false;
                            int beginDefinition_ = locIndex_;
                            int inheritIndex_ = -1;
                            while (locIndex_ < len_) {
                                char locChar_ = _file.charAt(locIndex_);
                                if (!enLoc_.isOk()) {
                                    //ERROR
                                    return out_;
                                }
                                if (locChar_ == BEGIN_TEMPLATE) {
                                    nbOpened_++;
                                }
                                if (nbOpened_ > 0) {
                                    if (!foundInherit_) {
                                        templateDef_.append(locChar_);
                                    }
                                } else if (templateDef_.length() == 0 && locChar_ != BEGIN_BLOCK) {
                                    if (!foundInherit_ && locChar_ != INHERIT) {
                                        if (typeNamePref_.length() == 0) {
                                            beginDefinition_ = locIndex_;
                                        }
                                        typeNamePref_.append(locChar_);
                                    }
                                }
                                if (locChar_ == END_TEMPLATE) {
                                    nbOpened_--;
                                }
                                if (locChar_ == INHERIT && nbOpened_ == 0) {
                                    if (foundInherit_) {
                                        superTypes_.put(inheritIndex_, str_.toString());
                                    }
                                    str_.delete(0, str_.length());
                                    foundInherit_ = true;
                                    locIndex_ = incrementRowCol(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                                    inheritIndex_ = locIndex_;
                                    continue;
                                }
                                if (locChar_ == BEGIN_BLOCK) {
                                    ok_ = true;
                                    break;
                                }
                                if (locChar_ == BEGIN_COMMENT) {
                                    //ERROR
                                    return out_;
                                }
                                if (foundInherit_) {
                                    str_.append(locChar_);
                                }
                                locIndex_ = incrementRowCol(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                            }
                            if (foundInherit_) {
                                superTypes_.put(inheritIndex_, str_.toString());
                            }
                            if (!ok_) {
                                //ERROR
                                return out_;
                            }
                            if (nextIndex_ >= len_) {
                                //ERROR
                                return out_;
                            }
                            locIndex_ = incrementRowCol(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                            RootBlock typeBlock_;
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
                            
                            if (StringList.quickEq(type_, KEY_WORD_ENUM)) {
                                enableByEndLine_ = true;
                                typeBlock_ = new EnumBlock(_context, index_, currentParent_, beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, accessFct_) , tempDef_, superTypes_, new OffsetsBlock(instructionRealLocation_ + trFound_, instructionLocation_ + trFound_));
                            } else if (StringList.quickEq(type_, KEY_WORD_CLASS)) {
                                typeBlock_ = new ClassBlock(_context, index_, currentParent_, beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, accessFct_), tempDef_, superTypes_, finalType_, abstractType_, true, new OffsetsBlock(instructionRealLocation_ + trFound_, instructionLocation_ + trFound_));
                            } else if (StringList.quickEq(type_, KEY_WORD_INTERFACE)) {
                                typeBlock_ = new InterfaceBlock(_context, index_, currentParent_, beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, accessFct_) , tempDef_, superTypes_, true, new OffsetsBlock(instructionRealLocation_ + trFound_, instructionLocation_ + trFound_));
                            } else {
                                typeBlock_ = new AnnotationBlock(_context, index_, currentParent_, beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, accessFct_) , tempDef_, superTypes_, new OffsetsBlock(instructionRealLocation_ + trFound_, instructionLocation_ + trFound_));
                            }
                            typeBlock_.getImports().addAllElts(importedTypes_);
                            typeBlock_.getImportsOffset().addAllElts(offsetsImports_);
                            typeBlock_.getStaticInitInterfaces().addAllElts(staticInitInterfaces_);
                            typeBlock_.getStaticInitInterfacesOffset().addAllElts(staticInitInterfacesOffset_);
                            typeBlock_.getAnnotations().addAllElts(annotationsTypes_);
                            typeBlock_.getAnnotationsIndexes().addAllElts(annotationsIndexesTypes_);
                            br_ = typeBlock_;
                            currentParent_.appendChild(br_);
                        } else {
                            int finalOff_ = 0;
                            boolean final_ = false;
                            boolean meth_ = true;
                            int deltaFinal_ = 0;
                            if (startsWithPrefixKeyWord(infoModifiers_,KEY_WORD_FINAL)) {
                                otherModifier_ = prefixKeyWord(KEY_WORD_FINAL);
                                int lenLoc_ = otherModifier_.length();
                                deltaFinal_ = lenLoc_;
                                String sub_ = infoModifiers_.substring(lenLoc_);
                                int deltaSec_ = StringList.getFirstPrintableCharIndex(sub_);
                                deltaFinal_ += deltaSec_;
                                finalOff_ = typeOffset_;
                                found_ = sub_.substring(deltaSec_);
                                final_ = true;
                                meth_ = false;
                            }
                            typeOffset_ += deltaFinal_;
                            String declaringType_ = getDeclaringTypeInstr(found_,options_);
                            found_ = found_.substring(declaringType_.length());
                            int trimmed_ = StringList.getFirstPrintableCharIndex(found_);
                            String realFound_ = found_;
                            found_ = found_.trim();
                            int lenAfterModifiers_ = found_.length();
                            int indexMod_ = 0;
                            while (indexMod_ < lenAfterModifiers_) {
                                char cur_ = found_.charAt(indexMod_);
                                if (!StringList.isDollarWordChar(cur_)) {
                                    break;
                                }
                                indexMod_++;
                            }
                            while (indexMod_ < lenAfterModifiers_) {
                                char cur_ = found_.charAt(indexMod_);
                                if (!Character.isWhitespace(cur_)) {
                                    break;
                                }
                                indexMod_++;
                            }
                            if (found_.indexOf(BEGIN_CALLING, indexMod_) != indexMod_) {
                                meth_ = false;
                            }
                            if (!meth_) {
                                int fieldOffest_ = typeOffset_;
                                fieldOffest_ += declaringType_.trim().length();
                                fieldOffest_ += trimmed_;
                                int sepOffest_ = found_.indexOf(PART_SEPARATOR);
                                if (sepOffest_ >= 0) {
                                    fieldName_ = found_.substring(0, sepOffest_);
                                    expression_ = found_.substring(sepOffest_ + 1);
                                    sepOffest_ += fieldOffest_;
                                    sepOffest_++;
                                } else {
                                    fieldName_ = found_;
                                    sepOffest_ = fieldOffest_;
                                }
                                br_ = new FieldBlock(_context, index_, currentParent_,
                                        new OffsetAccessInfo(-1, AccessEnum.PUBLIC),
                                        new OffsetBooleanInfo(-1, true),
                                        new OffsetBooleanInfo(finalOff_, final_),
                                        new OffsetStringInfo(fieldOffest_,fieldName_.trim()),
                                        new OffsetStringInfo(typeOffset_, declaringType_.trim()),
                                        new OffsetStringInfo(sepOffest_, expression_),
                                        new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                            } else {
                                found_ = realFound_;
                                int fieldOffest_ = typeOffset_;
                                fieldOffest_ += declaringType_.trim().length();
                                fieldOffest_ += StringList.getFirstPrintableCharIndex(found_);
                                int indexBeginCalling_ = found_.indexOf(BEGIN_CALLING);
                                fieldName_ = found_.substring(0, indexBeginCalling_);
                                expression_ = found_.substring(found_.indexOf(END_CALLING)+1);
                                expressionOffest_ = instructionLocation_ + trimmedInstruction_.indexOf(END_CALLING) + 1 + delta_;
                                if (!expression_.trim().isEmpty()) {
                                    expressionOffest_ += StringList.getFirstPrintableCharIndex(expression_);
                                }
                                br_ = new AnnotationMethodBlock(_context, index_, currentParent_,
                                        new OffsetStringInfo(typeOffset_, declaringType_.trim()),
                                        new OffsetStringInfo(fieldOffest_,fieldName_.trim()),
                                        new OffsetStringInfo(expressionOffest_,expression_.trim()),
                                        new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                            }
                            br_.getAnnotations().addAllElts(annotations_);
                            br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                            currentParent_.appendChild(br_);
                        }
                    } else {
                        //implicit static block
                        br_ = new StaticBlock(_context, index_, currentParent_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    }
                    if (currentChar_ == END_LINE) {
                        index_++;
                        indexes_.setLast(index_);
                    } else if (br_ instanceof BracedBlock) {
                        indexes_.add(0);
                        currentParent_ = (BracedBlock) br_;
                    }
                } else if (currentParent_ instanceof EnumBlock && enableByEndLine_) {
                    if (!trimmedInstruction_.isEmpty()) {
                        String fieldName_;
                        int fieldOffest_ = instructionLocation_;
                        int expressionOffest_ = -1;
                        String expression_ = EMPTY_STRING;
                        int delta_ = 0;
                        Numbers<Integer> annotationsIndexes_ = new Numbers<Integer>();
                        StringList annotations_ = new StringList();
                        if (trimmedInstruction_.charAt(0) == ANNOT) {
                            ParsedAnnotations par_ = new ParsedAnnotations(found_, instructionRealLocation_);
                            par_.parse();
                            annotationsIndexes_ = par_.getAnnotationsIndexes();
                            annotations_ = par_.getAnnotations();
                            found_ = par_.getAfter();
                            fieldOffest_ = par_.getIndex();
                            delta_ = fieldOffest_ - instructionRealLocation_;
                        }
                        int indexBeginCalling_ = found_.indexOf(BEGIN_CALLING);
                        if (indexBeginCalling_ >= 0) {
                            fieldName_ = found_.substring(0, indexBeginCalling_);
                            expression_ = found_.substring(indexBeginCalling_ + 1, found_.lastIndexOf(END_CALLING));
                            expressionOffest_ = instructionRealLocation_ + indexBeginCalling_ + 1 + delta_;
                            if (!expression_.isEmpty()) {
                                expressionOffest_ += StringList.getFirstPrintableCharIndex(expression_);
                            }
                        } else {
                            fieldName_ = found_;
                            expressionOffest_ = fieldOffest_;
                            expressionOffest_ += fieldName_.trim().length();
                            expressionOffest_ += fieldName_.length() - StringList.getLastPrintableCharIndex(fieldName_) - 1;
                        }
                        int indexTmp_ = fieldName_.indexOf(Templates.TEMPLATE_BEGIN);
                        String tmpPart_ = EMPTY_STRING;
                        int templateOffset_ = 0;
                        if (indexTmp_ > -1) {
                            templateOffset_ = fieldOffest_;
                            tmpPart_ = fieldName_.substring(indexTmp_);
                            fieldName_ = fieldName_.substring(0, indexTmp_);
                            templateOffset_ += fieldName_.trim().length();
                            templateOffset_ += fieldName_.length() - StringList.getLastPrintableCharIndex(fieldName_) - 1;
                        }
                        br_ = new ElementBlock(_context, index_, currentParent_, new OffsetStringInfo(fieldOffest_, fieldName_.trim()),
                                new OffsetStringInfo(templateOffset_, tmpPart_.trim()),
                                new OffsetStringInfo(expressionOffest_, expression_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        br_.getAnnotations().addAllElts(annotations_);
                        br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                        currentParent_.appendChild(br_);
                        index_++;
                        indexes_.setLast(index_);
                    }
                    if (currentChar_ == END_LINE) {
                        enableByEndLine_ = false;
                    }
                } else if (currentChar_ != END_BLOCK) {
                    if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_BREAK)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_BREAK)).length());
                        String label_ = exp_.trim();
                        int conditionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_BREAK).length();
                        int lastPar_ = StringList.getFirstPrintableCharIndex(exp_);
                        if (!exp_.isEmpty()) {
                            lastPar_--;
                        }
                        int labelOff_ = conditionOffest_ + lastPar_+ 1;
                        br_ = new BreakBlock(_context, index_, currentParent_, new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_CONTINUE)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_CONTINUE)).length());
                        String label_ = exp_.trim();
                        int conditionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_CONTINUE).length();
                        int lastPar_ = StringList.getFirstPrintableCharIndex(exp_);
                        if (!exp_.isEmpty()) {
                            lastPar_--;
                        }
                        int labelOff_ = conditionOffest_ + lastPar_+ 1;
                        br_ = new ContinueBlock(_context, index_, currentParent_, new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_RETURN)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_RETURN)).length());
                        int expressionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_RETURN).length();
                        if (!exp_.trim().isEmpty()) {
                            expressionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
                        }
                        br_ = new ReturnMehod(_context, index_, currentParent_, new OffsetStringInfo(expressionOffest_,exp_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_THROW)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_THROW)).length());
                        int expressionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_THROW).length();
                        if (!exp_.trim().isEmpty()) {
                            expressionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
                        }
                        br_ = new Throwing(_context, index_, currentParent_, new OffsetStringInfo(expressionOffest_,exp_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_CASE)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_CASE)).length());
                        int valueOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_CASE).length();
                        valueOffest_ += exp_.indexOf(BEGIN_CALLING) + 1;
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING));
                        valueOffest_ += StringList.getFirstPrintableCharIndex(exp_);
                        br_ = new CaseCondition(_context, index_, currentParent_, new OffsetStringInfo(valueOffest_, exp_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_DEFAULT)) {
                        br_ = new DefaultCondition(_context, index_, currentParent_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_WHILE)) {
                        Block child_ = currentParent_.getFirstChild();
                        if (child_ != null) {
                            while (child_.getNextSibling() != null) {
                                child_ = child_.getNextSibling();
                            }
                        }
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_WHILE)).length());
                        int conditionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_WHILE).length();
                        int lastPar_ = exp_.lastIndexOf(END_CALLING);
                        int labelOff_ = conditionOffest_ + lastPar_+ 1;
                        conditionOffest_ += exp_.indexOf(BEGIN_CALLING)+1;
                        String label_ = exp_;
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, lastPar_);
                        conditionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
                        if (child_ instanceof DoBlock) {
                            br_ = new DoWhileCondition(_context, index_, currentParent_, new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        } else {
                            label_ = label_.substring(lastPar_ + 1);
                            if (!label_.isEmpty()) {
                                labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                            }
                            br_ = new WhileCondition(_context, index_, currentParent_, new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));    
                        }
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_CATCH)) {
                        String info_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_CATCH)).length());
                        int leftPar_ = info_.indexOf(BEGIN_CALLING);
                        Numbers<Integer> annotationsIndexes_ = new Numbers<Integer>();
                        StringList annotations_ = new StringList();
                        if (leftPar_ > -1) {
                            int typeOffset_ = instructionLocation_ + leftPar_+1;
                            info_ = info_.substring(leftPar_+1);
                            int delta_ = 0;
                            if (info_.trim().charAt(0) == ANNOT) {
                                ParsedAnnotations par_ = new ParsedAnnotations(info_, instructionLocation_);
                                par_.parse();
                                annotationsIndexes_ = par_.getAnnotationsIndexes();
                                annotations_ = par_.getAnnotations();
                                info_ = par_.getAfter();
                                typeOffset_ = par_.getIndex();
                                delta_ = typeOffset_ - instructionLocation_;
                            }
                            String declaringType_ = getDeclaringTypeInstr(info_,options_);
                            typeOffset_ += delta_;
                            typeOffset_ += StringList.getFirstPrintableCharIndex(declaringType_);
                            int variableOffset_ = typeOffset_ + declaringType_.length();
                            info_ = info_.substring(declaringType_.length());
                            variableOffset_ += StringList.getFirstPrintableCharIndex(info_);
                            String variable_ = info_.substring(0, info_.indexOf(END_CALLING));
                            br_ = new CatchEval(_context, index_, currentParent_, new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(variableOffset_,variable_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        } else {
                            br_ = new NullCatchEval(_context, index_, currentParent_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        }
                        br_.getAnnotations().addAllElts(annotations_);
                        br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_IF)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_IF)).length());
                        int conditionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_IF).length();
                        int lastPar_ = exp_.lastIndexOf(END_CALLING);
                        int labelOff_ = conditionOffest_ + lastPar_+ 1;
                        conditionOffest_ += exp_.indexOf(BEGIN_CALLING)+1;
                        String label_ = exp_;
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1,lastPar_);
                        conditionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
                        label_ = label_.substring(lastPar_ + 1);
                        if (!label_.isEmpty()) {
                            labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                        }
                        br_ = new IfCondition(_context, index_, currentParent_, new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_ELSEIF)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_ELSEIF)).length());
                        int conditionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_ELSEIF).length();
                        conditionOffest_ += exp_.indexOf(BEGIN_CALLING)+1;
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, exp_.lastIndexOf(END_CALLING));
                        conditionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
                        br_ = new ElseIfCondition(_context, index_, currentParent_, new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_ELSE)) {
                        br_ = new ElseCondition(_context, index_, currentParent_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_DO)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_DO)).length());
                        String label_ = exp_.trim();
                        int conditionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_DO).length();
                        int lastPar_ = StringList.getFirstPrintableCharIndex(exp_);
                        if (!exp_.isEmpty()) {
                            lastPar_--;
                        }
                        int labelOff_ = conditionOffest_ + lastPar_+ 1;
                        br_ = new DoBlock(_context, index_, currentParent_, new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_FINALLY)) {
                        br_ = new FinallyEval(_context, index_, currentParent_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_TRY)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_TRY)).length());
                        String label_ = exp_.trim();
                        int conditionOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_TRY).length();
                        int lastPar_ = StringList.getFirstPrintableCharIndex(exp_);
                        if (!exp_.isEmpty()) {
                            lastPar_--;
                        }
                        int labelOff_ = conditionOffest_ + lastPar_+ 1;
                        br_ = new TryEval(_context, index_, currentParent_, new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_FOREACH)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_FOREACH)).length());
                        int indexClassOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_FOREACH).length();
                        int lastPar_ = exp_.lastIndexOf(END_CALLING);
                        int labelOff_ = indexClassOffest_ + lastPar_+ 1;
                        String label_ = exp_;
                        int typeOffset_ = instructionLocation_ + trimmedInstruction_.indexOf(BEGIN_CALLING) + 1;
                        if (!exp_.trim().isEmpty()) {
                            indexClassOffest_ += StringList.getFirstPrintableCharIndex(exp_) + 1;
                        }
                        String indexClassName_ = EMPTY_STRING;
                        if (exp_.trim().indexOf(BEGIN_ARRAY) == 0) {
                            indexClassName_ = exp_.substring(0, exp_.indexOf(END_ARRAY));
                            indexClassOffest_ += StringList.getFirstPrintableCharIndex(indexClassName_);
                            exp_ = exp_.substring(exp_.indexOf(END_ARRAY) + 1);
                        }
                        String afterIndex_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1);
                        typeOffset_ += StringList.getFirstPrintableCharIndex(afterIndex_);
                        exp_ = afterIndex_;
                        Numbers<Integer> annotationsIndexes_ = new Numbers<Integer>();
                        StringList annotations_ = new StringList();
                        int delta_ = 0;
                        if (exp_.trim().charAt(0) == ANNOT) {
                            ParsedAnnotations par_ = new ParsedAnnotations(exp_, instructionLocation_);
                            par_.parse();
                            annotationsIndexes_ = par_.getAnnotationsIndexes();
                            annotations_ = par_.getAnnotations();
                            exp_ = par_.getAfter();
                            typeOffset_ = par_.getIndex();
                            delta_ = typeOffset_ - instructionLocation_;
                        }
                        typeOffset_ += delta_;
                        String declaringType_ = getDeclaringTypeInstr(exp_,options_);
                        int varOffset_ = typeOffset_ + declaringType_.length();
                        exp_ = exp_.substring(declaringType_.length());
                        String variable_ = exp_.substring(0, exp_.indexOf(FOR_BLOCKS));
                        varOffset_ += StringList.getFirstPrintableCharIndex(variable_);
                        int expOffset_ = varOffset_;
                        expOffset_ += exp_.indexOf(FOR_BLOCKS);
                        exp_ = exp_.substring(exp_.indexOf(FOR_BLOCKS) + 1, exp_.lastIndexOf(END_CALLING));
                        expOffset_ += StringList.getFirstPrintableCharIndex(exp_);
                        label_ = label_.substring(lastPar_ + 1);
                        if (!label_.isEmpty()) {
                            labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                        }
                        br_ = new ForEachLoop(_context, index_, currentParent_, new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(varOffset_, variable_.trim()),
                                new OffsetStringInfo(expOffset_, exp_.trim()), new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                                new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        br_.getAnnotations().addAllElts(annotations_);
                        br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_ITER)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_ITER)).length());
                        int indexClassOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_ITER).length();
                        int lastPar_ = exp_.lastIndexOf(END_CALLING);
                        int labelOff_ = indexClassOffest_ + lastPar_+ 1;
                        String label_ = exp_;
                        int typeOffset_ = instructionLocation_ + trimmedInstruction_.indexOf(BEGIN_CALLING) + 1;
                        if (!exp_.trim().isEmpty()) {
                            indexClassOffest_ += StringList.getFirstPrintableCharIndex(exp_) + 1;
                        }
                        String indexClassName_ = EMPTY_STRING;
                        if (exp_.trim().indexOf(BEGIN_ARRAY) == 0) {
                            indexClassName_ = exp_.substring(0, exp_.indexOf(END_ARRAY));
                            exp_ = exp_.substring(exp_.indexOf(END_ARRAY) + 1);
                        }
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1);
                        Numbers<Integer> annotationsIndexes_ = new Numbers<Integer>();
                        StringList annotations_ = new StringList();
                        int delta_ = 0;
                        if (exp_.trim().charAt(0) == ANNOT) {
                            ParsedAnnotations par_ = new ParsedAnnotations(exp_, instructionLocation_);
                            par_.parse();
                            annotationsIndexes_ = par_.getAnnotationsIndexes();
                            annotations_ = par_.getAnnotations();
                            exp_ = par_.getAfter();
                            typeOffset_ = par_.getIndex();
                            delta_ = typeOffset_ - instructionLocation_;
                        }
                        typeOffset_ += delta_;
                        String declaringType_ = getDeclaringTypeInstr(exp_,options_);
                        typeOffset_ += StringList.getFirstPrintableCharIndex(exp_);
                        int varOffset_ = typeOffset_ + declaringType_.length();
                        exp_ = exp_.substring(declaringType_.length());
                        String variable_ = exp_.substring(0, exp_.indexOf(PART_SEPARATOR));
                        varOffset_ += StringList.getFirstPrintableCharIndex(variable_);
                        exp_ = exp_.substring(exp_.indexOf(PART_SEPARATOR) + 1);
                        int nextElt_ = getIndex(exp_);
                        int initOff_ = varOffset_ + nextElt_;
                        String init_ = exp_.substring(0, nextElt_);
                        initOff_ += StringList.getFirstPrintableCharIndex(init_);
                        exp_ = exp_.substring(init_.length()+1);
                        nextElt_ = getIndex(exp_);
                        int toOff_ = initOff_ + nextElt_;
                        boolean eq_ = false;
                        String to_ = exp_.substring(0, nextElt_);
                        toOff_ += StringList.getFirstPrintableCharIndex(to_);
                        int expOff_ = toOff_ + nextElt_;
                        int stepOff_ = expOff_ + 1;
                        if (exp_.charAt(nextElt_ + 1) == END_LINE) {
                            eq_ = true;
                            nextElt_++;
                            stepOff_++;
                        }
                        exp_ = exp_.substring(nextElt_ + 1);
                        String step_ = exp_.substring(0, exp_.lastIndexOf(END_CALLING));
                        stepOff_ += StringList.getFirstPrintableCharIndex(step_);
                        label_ = label_.substring(lastPar_ + 1);
                        if (!label_.isEmpty()) {
                            labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                        }
                        br_ = new ForIterativeLoop(_context, index_, currentParent_, new OffsetStringInfo(typeOffset_,declaringType_.trim()), new OffsetStringInfo(varOffset_,variable_.trim()),
                                new OffsetStringInfo(initOff_,init_.trim()), new OffsetStringInfo(toOff_,to_.trim()),  new OffsetBooleanInfo(expOff_, eq_) , new OffsetStringInfo(stepOff_,step_.trim()), new OffsetStringInfo(indexClassOffest_,indexClassName_.trim()),
                                new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        br_.getAnnotations().addAllElts(annotations_);
                        br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_FOR)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_FOR)).length());
                        int indexClassOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_FOR).length();
                        int lastPar_ = exp_.lastIndexOf(END_CALLING);
                        int labelOff_ = indexClassOffest_ + lastPar_+ 1;
                        String label_ = exp_;
                        int typeOffset_ = instructionLocation_ + trimmedInstruction_.indexOf(BEGIN_CALLING) + 1;
                        if (!exp_.trim().isEmpty()) {
                            indexClassOffest_ += StringList.getFirstPrintableCharIndex(exp_) + 1;
                        }
                        String indexClassName_ = EMPTY_STRING;
                        if (exp_.trim().indexOf(BEGIN_ARRAY) == 0) {
                            indexClassName_ = exp_.substring(0, exp_.indexOf(END_ARRAY));
                            exp_ = exp_.substring(exp_.indexOf(END_ARRAY) + 1);
                        }
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1);
                        boolean finalLocalVar_ = startsWithPrefixKeyWord(exp_, KEY_WORD_FINAL);
                        int finalOffset_ = typeOffset_;
                        int delta_ = 0;
                        int deltaAfter_ = 0;
                        if (finalLocalVar_) {
                            deltaAfter_ = prefixKeyWord(KEY_WORD_FINAL).length();
                            delta_ = StringList.getFirstPrintableCharIndex(exp_) + deltaAfter_;
                            deltaAfter_ = delta_;
                            deltaAfter_ += StringList.getFirstPrintableCharIndex(exp_.substring(delta_));
                        }
                        exp_ = exp_.substring(delta_);
                        Numbers<Integer> annotationsIndexes_ = new Numbers<Integer>();
                        StringList annotations_ = new StringList();
                        delta_ = 0;
                        if (exp_.trim().charAt(0) == ANNOT) {
                            ParsedAnnotations par_ = new ParsedAnnotations(exp_, instructionLocation_);
                            par_.parse();
                            annotationsIndexes_ = par_.getAnnotationsIndexes();
                            annotations_ = par_.getAnnotations();
                            exp_ = par_.getAfter();
                            typeOffset_ = par_.getIndex();
                            delta_ = typeOffset_ - instructionLocation_;
                        }
                        typeOffset_ += delta_;
                        String declaringType_ = getDeclaringTypeInstr(exp_,options_);
                        typeOffset_ += StringList.getFirstPrintableCharIndex(exp_);
                        int initOff_ = typeOffset_ + declaringType_.length();
                        exp_ = exp_.substring(declaringType_.length());
                        int nextElt_ = getIndex(exp_);
                        String init_ = exp_.substring(0, nextElt_);
                        initOff_ += StringList.getFirstPrintableCharIndex(init_);
                        exp_ = exp_.substring(init_.length()+1);
                        nextElt_ = getIndex(exp_);
                        int toOff_ = initOff_ + nextElt_;
                        String to_ = exp_.substring(0, nextElt_);
                        toOff_ += StringList.getFirstPrintableCharIndex(to_);
                        int expOff_ = toOff_ + nextElt_;
                        int stepOff_ = expOff_ + 1;
                        exp_ = exp_.substring(nextElt_ + 1);
                        String step_ = exp_.substring(0, exp_.lastIndexOf(END_CALLING));
                        stepOff_ += StringList.getFirstPrintableCharIndex(step_);
                        label_ = label_.substring(lastPar_ + 1);
                        if (!label_.isEmpty()) {
                            labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                        }
                        br_ = new ForMutableIterativeLoop(_context, index_, currentParent_,
                                new OffsetBooleanInfo(finalOffset_, finalLocalVar_),
                                new OffsetStringInfo(typeOffset_,declaringType_.trim()),
                                new OffsetStringInfo(initOff_,init_.trim()), new OffsetStringInfo(toOff_,to_.trim()),  new OffsetStringInfo(stepOff_,step_.trim()), new OffsetStringInfo(indexClassOffest_,indexClassName_.trim()),
                                new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        br_.getAnnotations().addAllElts(annotations_);
                        br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                        currentParent_.appendChild(br_);
                    } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_SWITCH)) {
                        String exp_ = trimmedInstruction_.substring((prefixKeyWord(KEY_WORD_SWITCH)).length());
                        int valueOffest_ = instructionLocation_ + prefixKeyWord(KEY_WORD_SWITCH).length();
                        int lastPar_ = exp_.lastIndexOf(END_CALLING);
                        int labelOff_ = valueOffest_ + lastPar_+ 1;
                        valueOffest_ += exp_.indexOf(BEGIN_CALLING)+1;
                        String label_ = exp_;
                        exp_ = exp_.substring(exp_.indexOf(BEGIN_CALLING)+1, lastPar_);
                        valueOffest_ += StringList.getFirstPrintableCharIndex(exp_);
                        label_ = label_.substring(lastPar_ + 1);
                        if (!label_.isEmpty()) {
                            labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                        }
                        br_ = new SwitchBlock(_context, index_, currentParent_, new OffsetStringInfo(valueOffest_, exp_.trim()), new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (StringList.quickEq(trimmedInstruction_, prefixKeyWord(KEY_WORD_STATIC))) {
                        br_ = new StaticBlock(_context, index_, currentParent_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else if (trimmedInstruction_.isEmpty()) {
                        br_ = new InstanceBlock(_context, index_, currentParent_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    } else {
                        boolean fct_ = false;
                        AccessEnum accessFct_ = AccessEnum.PACKAGE;
                        String word_ = EMPTY_STRING;
                        int trFound_ = StringList.getFirstPrintableCharIndex(found_);
                        int accessOffest_ = trFound_ + i_ - found_.length();
                        Numbers<Integer> annotationsIndexes_ = new Numbers<Integer>();
                        StringList annotations_ = new StringList();
                        int deltaAccess_ = 0;
                        if (found_.trim().charAt(0) == ANNOT) {
                            ParsedAnnotations par_ = new ParsedAnnotations(trimmedInstruction_, instructionRealLocation_ + trFound_);
                            par_.parse();
                            annotationsIndexes_ = par_.getAnnotationsIndexes();
                            annotations_ = par_.getAnnotations();
                            trimmedInstruction_ = par_.getAfter();
                            accessOffest_ = par_.getIndex();
                            deltaAccess_ = accessOffest_ - (trFound_ + i_ - found_.length());
                        }
                        if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_PRIVATE)) {
                            fct_ = true;
                            accessFct_ = AccessEnum.PRIVATE;
                            word_ = prefixKeyWord(KEY_WORD_PRIVATE);
                        } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_PACKAGE)) {
                            fct_ = true;
                            accessFct_ = AccessEnum.PACKAGE;
                            word_ = prefixKeyWord(KEY_WORD_PACKAGE);
                        } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_PROTECTED)) {
                            fct_ = true;
                            accessFct_ = AccessEnum.PROTECTED;
                            word_ = prefixKeyWord(KEY_WORD_PROTECTED);
                        } else if (startsWithPrefixKeyWord(trimmedInstruction_,KEY_WORD_PUBLIC)) {
                            fct_ = true;
                            accessFct_ = AccessEnum.PUBLIC;
                            word_ = prefixKeyWord(KEY_WORD_PUBLIC);
                        }
                        if (fct_) {
                            //fields, constructors or methods
                            String afterAccess_ = trimmedInstruction_.substring(word_.length());
                            int locIndex_ = accessOffest_ + word_.length();
                            locIndex_ += StringList.getFirstPrintableCharIndex(afterAccess_);
                            RowCol locRc_ = new RowCol();
                            locRc_.setCol(current_.getCol());
                            locRc_.setRow(current_.getRow());
                            EnablingSpaces enLoc_ = new EnablingSpaces();
                            enLoc_.setBegin(enabledSpaces_.getBegin());
                            enLoc_.setCheckTabs(enabledSpaces_.isCheckTabs());
                            enLoc_.setEnabledSpace(enabledSpaces_.isEnabledSpace());
                            enLoc_.setEnabledTab(enabledSpaces_.isEnabledTab());
                            enLoc_.setEnd(enabledSpaces_.getEnd());
                            enLoc_.setFile(enabledSpaces_.getFile());
                            enLoc_.setOk(enabledSpaces_.isOk());
                            enLoc_.setOnlySpacesLine(enabledSpaces_.isOnlySpacesLine());
                            enLoc_.setTabWidth(enabledSpaces_.getTabWidth());
                            if (declType_ && currentChar_ == BEGIN_BLOCK) {
                                //Inner types
                                boolean staticType_ = false;
                                boolean abstractType_ = false;
                                boolean finalType_ = false;
                                if (_file.substring(locIndex_).startsWith(prefixKeyWord(KEY_WORD_ABSTRACT))) {
                                    abstractType_ = true;
                                    locIndex_ = incrementRowCol(locIndex_, KEY_WORD_ABSTRACT.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                    locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_,  enLoc_);
                                }
                                if (_file.substring(locIndex_).startsWith(prefixKeyWord(KEY_WORD_STATIC))) {
                                    staticType_ = true;
                                    locIndex_ = incrementRowCol(locIndex_, KEY_WORD_STATIC.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                    locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_,  enLoc_);
                                }
                                if (_file.substring(locIndex_).startsWith(prefixKeyWord(KEY_WORD_FINAL))) {
                                    finalType_ = true;
                                    locIndex_ = incrementRowCol(locIndex_, KEY_WORD_FINAL.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                    locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_,  enLoc_);
                                }
                                String type_ = EMPTY_STRING;
                                int categoryOffset_ = locIndex_;
                                String infoModifiers_ = _file.substring(locIndex_);
                                if (infoModifiers_.startsWith(prefixKeyWord(KEY_WORD_CLASS))) {
                                    type_ = KEY_WORD_CLASS;
                                    locIndex_ = incrementRowCol(locIndex_, KEY_WORD_CLASS.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                } else if (infoModifiers_.startsWith(prefixKeyWord(KEY_WORD_ENUM))) {
                                    type_ = KEY_WORD_ENUM;
                                    locIndex_ = incrementRowCol(locIndex_, KEY_WORD_ENUM.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                } else if (infoModifiers_.startsWith(prefixKeyWord(KEY_WORD_INTERFACE))) {
                                    type_ = KEY_WORD_INTERFACE;
                                    locIndex_ = incrementRowCol(locIndex_, KEY_WORD_INTERFACE.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                } else if (infoModifiers_.startsWith(prefixKeyWord(KEY_WORD_ANNOTATION))) {
                                    type_ = KEY_WORD_ANNOTATION;
                                    locIndex_ = incrementRowCol(locIndex_, KEY_WORD_ANNOTATION.length()+1, _file, tabWidth_, locRc_, enLoc_);
                                }
                                if (locIndex_ < 0) {
                                    //ERROR
                                    return out_;
                                }
                                locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_,  enLoc_);
                                if (locIndex_ < 0) {
                                    return out_;
                                }
                                ParsedImportedTypes p_ = new ParsedImportedTypes(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                                importedTypes_ = p_.getImportedTypes();
                                offsetsImports_ = p_.getOffsetsImports();
                                locIndex_ = p_.getNextIndex();
                                if (!p_.isOk()) {
                                    return out_;
                                }
                                if (!StringList.isDollarWordChar(_file.charAt(locIndex_))) {
                                    //ERROR
                                    return out_;
                                }
                                //insert interfaces static initialization for class and enums
                                StringList staticInitInterfaces_ = new StringList();
                                Numbers<Integer> staticInitInterfacesOffset_ = new Numbers<Integer>();
                                if (startsWithPrefixKeyWord(_file.substring(locIndex_), KEY_WORD_INTERFACES)) {
                                    int begin_ = _file.indexOf(BEGIN_CALLING, locIndex_);
                                    if (begin_ < 0) {
                                        //ERROR
                                        return out_;
                                    }
                                    int end_ = _file.indexOf(END_CALLING, begin_);
                                    if (end_ < 0) {
                                        //ERROR
                                        return out_;
                                    }
                                    int interfaceOffest_ = begin_ + 1;
                                    String interfacesInfo_ = _file.substring(begin_ + 1, end_);
                                    for (int i = begin_ + 1; i < end_; i++) {
                                        updateAllowedSpaces(i, _file, enLoc_);
                                        if (!enLoc_.isOk()) {
                                            //ERROR
                                            return out_;
                                        }
                                    }
                                    for (String p: StringList.splitChars(interfacesInfo_, SEP_CALLING)) {
                                        staticInitInterfaces_.add(p);
                                        staticInitInterfacesOffset_.add(interfaceOffest_);
                                        interfaceOffest_ += p.length() + 1;
                                    }
                                    locIndex_ = end_ + 1;
                                }
                                locIndex_ = skipWhitespace(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                                if (locIndex_ < 0) {
                                    //ERROR
                                    return out_;
                                }
                                StringBuilder str_ = new StringBuilder();
                                NatTreeMap<Integer, String> superTypes_ = new NatTreeMap<Integer, String>();
                                StringBuilder typeNamePref_ = new StringBuilder();
                                StringBuilder templateDef_ = new StringBuilder();
                                int nbOpened_ = 0;
                                boolean ok_ = false;
                                boolean foundInherit_ = false;
                                int beginDefinition_ = locIndex_;
                                int inheritIndex_ = -1;
                                while (locIndex_ < len_) {
                                    char locChar_ = _file.charAt(locIndex_);
                                    if (!enLoc_.isOk()) {
                                        //ERROR
                                        return out_;
                                    }
                                    if (locChar_ == BEGIN_TEMPLATE) {
                                        nbOpened_++;
                                    }
                                    if (nbOpened_ > 0) {
                                        if (!foundInherit_) {
                                            templateDef_.append(locChar_);
                                        }
                                    } else if (templateDef_.length() == 0 && locChar_ != BEGIN_BLOCK) {
                                        if (!foundInherit_ && locChar_ != INHERIT) {
                                            if (typeNamePref_.length() == 0) {
                                                beginDefinition_ = locIndex_;
                                            }
                                            typeNamePref_.append(locChar_);
                                        }
                                    }
                                    if (locChar_ == END_TEMPLATE) {
                                        nbOpened_--;
                                    }
                                    if (locChar_ == INHERIT && nbOpened_ == 0) {
                                        if (foundInherit_) {
                                            superTypes_.put(inheritIndex_, str_.toString());
                                        }
                                        str_.delete(0, str_.length());
                                        foundInherit_ = true;
                                        locIndex_ = incrementRowCol(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                                        inheritIndex_ = locIndex_;
                                        continue;
                                    }
                                    if (locChar_ == BEGIN_BLOCK) {
                                        ok_ = true;
                                        break;
                                    }
                                    if (locChar_ == BEGIN_COMMENT) {
                                        //ERROR
                                        return out_;
                                    }
                                    if (foundInherit_) {
                                        str_.append(locChar_);
                                    }
                                    locIndex_ = incrementRowCol(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                                }
                                if (foundInherit_) {
                                    superTypes_.put(inheritIndex_, str_.toString());
                                }
                                if (!ok_) {
                                    //ERROR
                                    return out_;
                                }
                                if (nextIndex_ >= len_) {
                                    //ERROR
                                    return out_;
                                }
                                locIndex_ = incrementRowCol(locIndex_, _file, tabWidth_, locRc_, enLoc_);
                                RootBlock typeBlock_;
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
                                
                                if (StringList.quickEq(type_, KEY_WORD_ENUM)) {
                                    enableByEndLine_ = true;
                                    typeBlock_ = new EnumBlock(_context, index_, currentParent_, beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, accessFct_) , tempDef_, superTypes_, new OffsetsBlock(instructionRealLocation_ + trFound_, instructionLocation_ + trFound_));
                                } else if (StringList.quickEq(type_, KEY_WORD_CLASS)) {
                                    typeBlock_ = new ClassBlock(_context, index_, currentParent_, beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, accessFct_), tempDef_, superTypes_, finalType_, abstractType_, staticType_, new OffsetsBlock(instructionRealLocation_ + trFound_, instructionLocation_ + trFound_));
                                } else if (StringList.quickEq(type_, KEY_WORD_INTERFACE)) {
                                    typeBlock_ = new InterfaceBlock(_context, index_, currentParent_, beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, accessFct_) , tempDef_, superTypes_, staticType_, new OffsetsBlock(instructionRealLocation_ + trFound_, instructionLocation_ + trFound_));
                                } else {
                                    typeBlock_ = new AnnotationBlock(_context, index_, currentParent_, beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, accessFct_) , tempDef_, superTypes_, new OffsetsBlock(instructionRealLocation_ + trFound_, instructionLocation_ + trFound_));
                                }
                                typeBlock_.getImports().addAllElts(importedTypes_);
                                typeBlock_.getImportsOffset().addAllElts(offsetsImports_);
                                typeBlock_.getStaticInitInterfaces().addAllElts(staticInitInterfaces_);
                                typeBlock_.getStaticInitInterfacesOffset().addAllElts(staticInitInterfacesOffset_);
                                typeBlock_.getAnnotations().addAllElts(annotations_);
                                typeBlock_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                                br_ = typeBlock_;
                                currentParent_.appendChild(br_);
                            } else {
                                String otherModifier_ = EMPTY_STRING;
                                String infoModifiers_ = afterAccess_.trim();
                                while (true) {
                                    if (startsWithPrefixKeyWord(infoModifiers_,KEY_WORD_NORMAL)) {
                                        otherModifier_ = prefixKeyWord(KEY_WORD_NORMAL);
                                        int lenLoc_ = otherModifier_.length();
                                        String sub_ = infoModifiers_.substring(lenLoc_);
                                        int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                                        infoModifiers_ = sub_.substring(delta_);
                                        continue;
                                    }
                                    if (startsWithPrefixKeyWord(infoModifiers_,KEY_WORD_ABSTRACT)) {
                                        otherModifier_ = prefixKeyWord(KEY_WORD_ABSTRACT);
                                        int lenLoc_ = otherModifier_.length();
                                        String sub_ = infoModifiers_.substring(lenLoc_);
                                        int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                                        infoModifiers_ = sub_.substring(delta_);
                                        continue;
                                    }
                                    if (startsWithPrefixKeyWord(infoModifiers_,KEY_WORD_STATIC)) {
                                        otherModifier_ = prefixKeyWord(KEY_WORD_STATIC);
                                        int lenLoc_ = otherModifier_.length();
                                        String sub_ = infoModifiers_.substring(lenLoc_);
                                        int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                                        infoModifiers_ = sub_.substring(delta_);
                                        continue;
                                    }
                                    if (startsWithPrefixKeyWord(infoModifiers_,KEY_WORD_FINAL)) {
                                        otherModifier_ = prefixKeyWord(KEY_WORD_FINAL);
                                        int lenLoc_ = otherModifier_.length();
                                        String sub_ = infoModifiers_.substring(lenLoc_);
                                        int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                                        infoModifiers_ = sub_.substring(delta_);
                                        continue;
                                    }
                                    break;
                                }
                                String typeStr_ = getDeclaringTypeInstr(infoModifiers_,options_);
                                boolean ctor_ = false;
                                boolean meth_ = false;
                                if (typeStr_.isEmpty()) {
                                    //constructor
                                    ctor_ = true;
                                } else {
                                    infoModifiers_ = infoModifiers_.substring(typeStr_.length());
                                    int first_ = StringList.getFirstPrintableCharIndex(infoModifiers_);
                                    infoModifiers_ = infoModifiers_.substring(first_);
                                    int lenAfterModifiers_ = infoModifiers_.length();
                                    int indexMod_ = 0;
                                    while (indexMod_ < lenAfterModifiers_) {
                                        char cur_ = infoModifiers_.charAt(indexMod_);
                                        if (!StringList.isDollarWordChar(cur_)) {
                                            break;
                                        }
                                        indexMod_++;
                                    }
                                    while (indexMod_ < lenAfterModifiers_) {
                                        char cur_ = infoModifiers_.charAt(indexMod_);
                                        if (!Character.isWhitespace(cur_)) {
                                            break;
                                        }
                                        indexMod_++;
                                    }
                                    if (infoModifiers_.indexOf(BEGIN_CALLING, indexMod_) == indexMod_) {
                                        meth_ = true;
                                    }
                                }
                                if (meth_||ctor_) {

                                    //constructors or methods or types
                                    int modifierOffest_ = accessOffest_ + word_.length();
                                    modifierOffest_ += StringList.getFirstPrintableCharIndex(afterAccess_);
                                    String info_ = afterAccess_.trim();
                                    String modifier_ = EMPTY_STRING;
                                    if (startsWithPrefixKeyWord(info_,KEY_WORD_NORMAL)) {
                                        modifier_ = KEY_WORD_NORMAL;
                                    } else if (startsWithPrefixKeyWord(info_,KEY_WORD_ABSTRACT)) {
                                        modifier_ = KEY_WORD_ABSTRACT;
                                    } else if (startsWithPrefixKeyWord(info_,KEY_WORD_STATIC)) {
                                        modifier_ = KEY_WORD_STATIC;
                                    } else if (startsWithPrefixKeyWord(info_,KEY_WORD_FINAL)) {
                                        modifier_ = KEY_WORD_FINAL;
                                    }
                                    boolean method_ = !modifier_.isEmpty();
                                    int methodNameOffest_ = -1;
                                    int typeOffset_ = -1;
                                    int paramOffest_;
                                    String methodName_ = EMPTY_STRING;
                                    String declaringType_ = EMPTY_STRING;

                                    if (method_) {
                                        String afterModifier_ = info_.substring((prefixKeyWord(modifier_)).length());
                                        typeOffset_ = modifierOffest_ + prefixKeyWord(modifier_).length();
                                        typeOffset_ += StringList.getFirstPrintableCharIndex(afterModifier_);
                                        info_ = afterModifier_.trim();
                                        declaringType_ = getDeclaringTypeInstr(info_,options_);
                                        String afterType_ = info_.substring(declaringType_.length());
                                        methodNameOffest_ = typeOffset_ + declaringType_.length();
                                        methodNameOffest_ += StringList.getFirstPrintableCharIndex(afterType_);
                                        info_ = afterType_.trim();
                                        int leftParIndex_ = info_.indexOf(BEGIN_CALLING);
                                        methodName_ = info_.substring(0, leftParIndex_);
                                        String afterMethodName_ = info_.substring(leftParIndex_ + 1);
                                        paramOffest_ = methodNameOffest_ + leftParIndex_ + 1;
                                        paramOffest_ += StringList.getFirstPrintableCharIndex(afterMethodName_);
                                        info_ = afterMethodName_.trim();
                                        int indexLeftPar_ = info_.indexOf(BEGIN_CALLING);
                                        paramOffest_ += indexLeftPar_ + 1;
                                        paramOffest_ += StringList.getFirstPrintableCharIndex(info_.substring(indexLeftPar_ + 1));
                                    } else {
                                        paramOffest_ = modifierOffest_;
                                        int indexLeftPar_ = info_.indexOf(BEGIN_CALLING);
                                        paramOffest_ += indexLeftPar_ + 1;
                                        String after_ = info_.substring(indexLeftPar_ + 1);
                                        paramOffest_ += StringList.getFirstPrintableCharIndex(after_);
                                        info_ = after_.trim();
                                    }
                                    Numbers<Integer> offestsTypes_ = new Numbers<Integer>();
                                    Numbers<Integer> offestsParams_ = new Numbers<Integer>();
                                    StringList parametersType_ = new StringList();
                                    StringList parametersName_ = new StringList();
                                    CustList<Numbers<Integer>> annotationsIndexesParams_ = new CustList<Numbers<Integer>>();
                                    CustList<StringList> annotationsParams_ = new CustList<StringList>();
                                    while (true) {
                                        if (info_.indexOf(END_CALLING) == 0) {
                                            break;
                                        }
                                        Numbers<Integer> annotationsIndexesParam_ = new Numbers<Integer>();
                                        StringList annotationsParam_ = new StringList();
                                        if (info_.trim().charAt(0) == ANNOT) {
                                            ParsedAnnotations par_ = new ParsedAnnotations(info_, paramOffest_);
                                            par_.parse();
                                            annotationsIndexesParam_ = par_.getAnnotationsIndexes();
                                            annotationsParam_ = par_.getAnnotations();
                                            info_ = par_.getAfter();
                                            paramOffest_ = par_.getIndex();
                                            paramOffest_ += StringList.getFirstPrintableCharIndex(info_);
                                        }
                                        annotationsIndexesParams_.add(annotationsIndexesParam_);
                                        annotationsParams_.add(annotationsParam_);
                                        offestsTypes_.add(paramOffest_);
                                        String paramType_ = getDeclaringParamType(info_, options_);
                                        parametersType_.add(paramType_.trim());
                                        String afterParamType_ = info_.substring(paramType_.length());
                                        info_ = afterParamType_.trim();
                                        int call_ = info_.indexOf(SEP_CALLING);
                                        if (call_ < 0) {
                                            call_ = info_.indexOf(END_CALLING);
                                        }
                                        int off_ = StringList.getFirstPrintableCharIndex(afterParamType_);
                                        if (off_ < 0) {
                                            off_ = 0;
                                        }
                                        offestsParams_.add(paramOffest_ + paramType_.length() + off_);
                                        String paramName_ = info_.substring(0, call_);
                                        parametersName_.add(paramName_.trim());
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
                                    if (method_) {
                                        br_ = new MethodBlock(_context, index_, currentParent_, new OffsetAccessInfo(accessOffest_, accessFct_), new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(methodNameOffest_, methodName_.trim()), parametersType_, offestsTypes_, parametersName_, offestsParams_, new OffsetStringInfo(modifierOffest_, modifier_), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                                    } else {
                                        br_ = new ConstructorBlock(_context, index_, currentParent_, new OffsetAccessInfo(accessOffest_, accessFct_), new OffsetStringInfo(accessOffest_, EMPTY_STRING), new OffsetStringInfo(accessOffest_, EMPTY_STRING), parametersType_, offestsTypes_, parametersName_, offestsParams_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                                    }
                                    ((NamedFunctionBlock)br_).getAnnotationsParams().addAllElts(annotationsParams_);
                                    ((NamedFunctionBlock)br_).getAnnotationsIndexesParams().addAllElts(annotationsIndexesParams_);
                                    br_.getAnnotations().addAllElts(annotations_);
                                    br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                                    currentParent_.appendChild(br_);
                                } else {

                                    //fields
                                    int delta_ = StringList.getFirstPrintableCharIndex(found_) + word_.length();
                                    delta_ += deltaAccess_;
                                    delta_ += StringList.getFirstPrintableCharIndex(afterAccess_);
                                    String info_ = afterAccess_.trim();
                                    int staticOffest_ = -1;
                                    int finalOffest_ = -1;
                                    boolean static_ = false;
                                    boolean final_ = false;
                                    if (startsWithPrefixKeyWord(info_, KEY_WORD_STATIC)) {
                                        staticOffest_ = i_ - found_.length() + delta_;
                                        static_ = true;
                                        String afterStatic_ = info_.substring((prefixKeyWord(KEY_WORD_STATIC)).length());
                                        delta_ += prefixKeyWord(KEY_WORD_STATIC).length();
                                        delta_ += StringList.getFirstPrintableCharIndex(afterStatic_);
                                        info_ = afterStatic_.trim();
                                    }
                                    if (startsWithPrefixKeyWord(info_,KEY_WORD_FINAL)) {
                                        finalOffest_ = i_ - found_.length() + delta_;
                                        final_ = true;
                                        String afterFinal_ = info_.substring((prefixKeyWord(KEY_WORD_FINAL)).length());
                                        delta_ += prefixKeyWord(KEY_WORD_FINAL).length();
                                        delta_ += StringList.getFirstPrintableCharIndex(afterFinal_);
                                        info_ = afterFinal_.trim();
                                    }
                                    int typeOffest_ = i_ - found_.length() + delta_;
                                    String declaringType_ = getDeclaringTypeInstr(info_,options_);
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
                                        sepOffest_ = fieldNameOffest_;
                                    }
                                    br_ = new FieldBlock(_context, index_, currentParent_, new OffsetAccessInfo(accessOffest_, accessFct_), new OffsetBooleanInfo(staticOffest_, static_), new OffsetBooleanInfo(finalOffest_, final_), new OffsetStringInfo(fieldNameOffest_,fieldName_.trim()), new OffsetStringInfo(typeOffest_,declaringType_.trim()), new OffsetStringInfo(sepOffest_, expression_), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                                    br_.getAnnotations().addAllElts(annotations_);
                                    br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                                    currentParent_.appendChild(br_);
                                }
                            }
                        } else {
                            int affectOffset_ = -1;
                            int typeOffset_ = instructionRealLocation_;
                            int afterDeclareOffset_ = -1;
                            boolean finalLocalVar_ = startsWithPrefixKeyWord(trimmedInstruction_, KEY_WORD_FINAL);
                            int delta_ = 0;
                            int deltaAfter_ = 0;
                            if (finalLocalVar_) {
                                deltaAfter_ = prefixKeyWord(KEY_WORD_FINAL).length();
                                delta_ = StringList.getFirstPrintableCharIndex(found_) + deltaAfter_;
                                deltaAfter_ = delta_;
                                deltaAfter_ += StringList.getFirstPrintableCharIndex(found_.substring(delta_));
                            }
                            found_ = found_.substring(delta_);
                            String declaringType_ = getDeclaringTypeInstr(found_,options_);
                            boolean typeDeclaring_ = !declaringType_.trim().isEmpty();
                            String info_;
                            int realTypeOffset_;
                            if (finalLocalVar_) {
                                realTypeOffset_ = typeOffset_ + deltaAfter_;
                            } else {
                                realTypeOffset_ = instructionLocation_;
                            }
                            if (typeDeclaring_) {
                                int varNameOffset_ = typeOffset_ + delta_;
                                varNameOffset_ += declaringType_.length();
                                info_ = found_.substring(declaringType_.length());
                                varNameOffset_ += StringList.getFirstPrintableCharIndex(info_);
                                afterDeclareOffset_ = varNameOffset_;
                            } else {
                                affectOffset_ = instructionRealLocation_;
                                affectOffset_ += StringList.getFirstPrintableCharIndex(found_);
                                afterDeclareOffset_ = affectOffset_;
                                info_ = found_;
                            }
                            String inst_ = info_;
                            if (typeDeclaring_) {
                                br_ = new DeclareVariable(false,_context, index_, currentParent_, new OffsetBooleanInfo(instructionLocation_, finalLocalVar_), new OffsetStringInfo(realTypeOffset_, declaringType_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                                br_.getAnnotations().addAllElts(annotations_);
                                br_.getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                                currentParent_.appendChild(br_);
                                index_++;
                                indexes_.setLast(index_);
                                inst_ = info_;
                            }
                            br_ = new Line(_context, index_, currentParent_, new OffsetStringInfo(afterDeclareOffset_, inst_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                            currentParent_.appendChild(br_);
                        }
                    }
                    if (currentChar_ == END_LINE) {
                        index_++;
                        indexes_.setLast(index_);
                    } else if (br_ instanceof BracedBlock) {
                        indexes_.add(0);
                        currentParent_ = (BracedBlock) br_;
                    }
                } else {
                    //currentChar_ == END_BLOCK
                    indexes_.removeLast();
                    index_ = indexes_.last();
                    index_++;
                    indexes_.setLast(index_);
                    currentParent_ = currentParent_.getParent();
                }
                instruction_.delete(0, instruction_.length());
                declType_ = false;
            } else {
                if (Character.isWhitespace(currentChar_) && !declType_) {
                    StringList parts_ = StringList.getDollarWordSeparators(instruction_);
                    StringList printable_ = new StringList();
                    for (String p: parts_) {
                        String t_ = p.trim();
                        if (!StringList.isDollarWord(t_)) {
                            continue;
                        }
                        printable_.add(t_);
                    }
                    if (printable_.size() == 1) {
                        boolean pr_ = true;
                        String category_ = printable_.last();
                        if (isKeyWordCategory(category_)) {
                            if (StringList.quickEq(category_, prefixKeyWord(KEY_WORD_CLASS))) {
                                int j_ = i_ + 1;
                                while (j_ < len_) {
                                    char next_ = _file.charAt(j_);
                                    if (Character.isWhitespace(next_)) {
                                        j_++;
                                        continue;
                                    }
                                    if (next_ == BEGIN_CALLING) {
                                        pr_ = false;
                                    }
                                    break;
                                }
                            }
                            if (pr_) {
                                declType_ = true;
                            }
                        }
                    }
                    if (printable_.size() > 1) {
                        String category_ = printable_.last();
                        if (isKeyWordCategory(category_)) {
                            int nb_ = parts_.size() - 1;
                            while (nb_ >= 0) {
                                String mod_ = parts_.get(nb_).trim();
                                if (StringList.quickEq(category_, mod_)) {
                                    nb_--;
                                    break;
                                }
                                if (!StringList.isDollarWord(mod_)) {
                                    if (!mod_.isEmpty()) {
                                        nb_=-1;
                                        break;
                                    }
                                }
                                nb_--;
                            }
                            while (nb_ >= 0) {
                                String mod_ = parts_.get(nb_).trim();
                                if (isKeyWordTypeModifier(mod_)) {
                                    nb_--;
                                    continue;
                                }
                                if (!StringList.isDollarWord(mod_)) {
                                    if (!mod_.isEmpty()) {
                                        nb_=-1;
                                        break;
                                    }
                                }
                                if (isKeyWordAccess(mod_)) {
                                    break;
                                }
                                nb_--;
                            }
                            if (nb_ >= 0) {
                                declType_ = true;
                            }
                        }
                    }
                }
            }
            i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
        }
        if (okType_) {
            i_ = incrementRowCol(i_, _file, tabWidth_, current_, enabledSpaces_);
        }
        out_.setNextIndex(i_);
        out_.setNextRowCol(new RowCol());
        out_.getNextRowCol().setCol(current_.getCol());
        out_.getNextRowCol().setCol(current_.getRow());
        out_.setOk(okType_);
        return out_;
    }
    private static boolean isKeyWordAccess(String _key) {
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_PRIVATE))) {
            return true;
        }
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_PACKAGE))) {
            return true;
        }
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_PROTECTED)))  {
            return true;
        }
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_PUBLIC)))  {
            return true;
        }
        return false;
    }
    private static boolean isKeyWordCategory(String _key) {
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_CLASS))) {
            return true;
        }
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_ENUM))) {
            return true;
        }
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_INTERFACE)))  {
            return true;
        }
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_ANNOTATION)))  {
            return true;
        }
        return false;
    }
    private static boolean isKeyWordTypeModifier(String _key) {
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_ABSTRACT))) {
            return true;
        }
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_STATIC))) {
            return true;
        }
        if (StringList.quickEq(_key, prefixKeyWord(KEY_WORD_FINAL)))  {
            return true;
        }
        return false;
    }
    private static String getDeclaringParamType(String _found, Options _options) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (declTypeName_.toString().trim().endsWith(VARARG)) {
                typeDeclaring_ = true;
                break;
            }
            if (currentCharFound_ == END_ARRAY && nbOpenedTmp_ == 0) {
                String nextPart_ = _found.substring(indexInstr_+1).trim();
                if (!nextPart_.isEmpty()) {
                    if (StringList.isDollarWordChar(nextPart_.charAt(0))) {
                        declTypeName_.append(currentCharFound_);
                        typeDeclaring_ = true;
                        break;
                    }
                }
            }
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                String nextPart_ = _found.substring(indexInstr_).trim();
                String trimmed_ = declTypeName_.toString().trim();
                if (trimmed_.length() > 0) {
                    char ch_ = trimmed_.charAt(trimmed_.length() - 1);
                    if (StringList.isDollarWordChar(ch_)) {
                        if (!nextPart_.isEmpty()) {
                            if (StringList.isDollarWordChar(nextPart_.charAt(0))) {
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
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    declTypeName_.append(currentCharFound_);
                    String nextPart_ = _found.substring(indexInstr_+1).trim();
                    if (nextPart_.startsWith("..") && !nextPart_.startsWith(VARARG)) {
                        indexInstr_++;
                        continue;
                    }
                    if (nextPart_.startsWith(String.valueOf("["))) {
                        indexInstr_++;
                        continue;
                    }
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
    private static String getDeclaringTypeOper(String _found, Options _options) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                String trimmed_ = declTypeName_.toString().trim();
                String nextPart_ = _found.substring(indexInstr_).trim();
                if (trimmed_.length() > 0) {
                    char ch_ = trimmed_.charAt(trimmed_.length() - 1);
                    if (StringList.isDollarWordChar(ch_)) {
                        if (nextPart_.substring(0).trim().startsWith(String.valueOf(BEGIN_CALLING))) {
                            typeDeclaring_ = true;
                            break;
                        }
                    }
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == BEGIN_TEMPLATE) {
                nbOpenedTmp_++;
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    String nextPart_ = _found.substring(indexInstr_+1).trim();
                    if (nextPart_.startsWith("..")) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    if (nextPart_.startsWith(String.valueOf(BEGIN_ARRAY))) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    declTypeName_.append(currentCharFound_);
                    typeDeclaring_ = true;
                    break;
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (nbOpenedTmp_ > 0) {
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            // !Character.isWhitespace(currentCharFound_)
            if (StringList.isDollarWordChar(currentCharFound_)) {
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == PKG) {
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == TYPE_VAR) {
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            // !Character.isWhitespace(currentCharFound_)
            break;
        }
        boolean ok_ = false;
        if (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (currentCharFound_ == BEGIN_ARRAY) {
                ok_ = true;
                while (indexInstr_ < instLen_) {
                    currentCharFound_ = _found.charAt(indexInstr_);
                    if (Character.isWhitespace(currentCharFound_)) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == BEGIN_ARRAY) {
                        declTypeName_.append(currentCharFound_);
                        if (!ok_) {
                            break;
                        }
                        ok_ = false;
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == END_ARRAY) {
                        declTypeName_.append(currentCharFound_);
                        if (ok_) {
                            ok_ = false;
                            break;
                        }
                        ok_ = true;
                        indexInstr_++;
                        continue;
                    }
                    break;
                }
            }
        }
        if (ok_) {
            typeDeclaring_ = true;
        }
        if (typeDeclaring_) {
            return declTypeName_.toString();
        }
        return EMPTY_STRING;
    }
    private static boolean isOperatorCharacter(char _char) {
        if (_char == '+') {
            return true;
        }
        if (_char == '-') {
            return true;
        }
        if (_char == '*') {
            return true;
        }
        if (_char == '%') {
            return true;
        }
        if (_char == '/') {
            return true;
        }
        if (_char == '!') {
            return true;
        }
        if (_char == '=') {
            return true;
        }
        if (_char == '<') {
            return true;
        }
        if (_char == '>') {
            return true;
        }
        return false;
    }
    private static String getDeclaringTypeInstr(String _found, Options _options) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
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
                String nextPart_ = _found.substring(indexInstr_).trim();
                if (trimmed_.length() > 0) {
                    char ch_ = trimmed_.charAt(trimmed_.length() - 1);
                    if (StringList.isDollarWordChar(ch_)) {
                        if (!nextPart_.isEmpty()) {
                            if (StringList.isDollarWordChar(nextPart_.charAt(0))) {
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
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    String nextPart_ = _found.substring(indexInstr_+1).trim();
                    if (nextPart_.startsWith("..")) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    if (nextPart_.startsWith(String.valueOf(BEGIN_ARRAY))) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    declTypeName_.append(currentCharFound_);
                    typeDeclaring_ = true;
                    break;
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (nbOpenedTmp_ > 0) {
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            // !Character.isWhitespace(currentCharFound_)
            if (StringList.isDollarWordChar(currentCharFound_)) {
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == PKG) {
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == TYPE_VAR) {
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            // !Character.isWhitespace(currentCharFound_)
            break;
        }

        boolean ok_ = false;
        if (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (currentCharFound_ == BEGIN_ARRAY) {
                ok_ = true;
                while (indexInstr_ < instLen_) {
                    currentCharFound_ = _found.charAt(indexInstr_);
                    if (Character.isWhitespace(currentCharFound_)) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == BEGIN_ARRAY) {
                        declTypeName_.append(currentCharFound_);
                        if (!ok_) {
                            break;
                        }
                        ok_ = false;
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == END_ARRAY) {
                        declTypeName_.append(currentCharFound_);
                        if (ok_) {
                            ok_ = false;
                            break;
                        }
                        ok_ = true;
                        indexInstr_++;
                        continue;
                    }
                    break;
                }
            }
        }
        if (ok_) {
            typeDeclaring_ = true;
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

    private static boolean startsWithPrefixKeyWord(String _found, String _keyWord) {
        String prefix_ = prefixKeyWord(_keyWord);
        if (!_found.startsWith(prefix_)) {
            return false;
        }
        String sub_ = _found.substring(prefix_.length());
        if (sub_.isEmpty()) {
            return true;
        }
        char first_ = sub_.charAt(0);
        return !StringList.isDollarWordChar(first_);
    }
    private static String prefixKeyWord(String _keyWord) {
        return StringList.concat(String.valueOf(KEY_WORD_PREFIX), _keyWord);
    }

    private static int incrementRowCol(int _index, int _delta,String _file, int _tabWidth, RowCol _current, EnablingSpaces _enabling) {
        int index_ = _index;
        for (int i = 0; i < _delta; i++) {
            index_ = incrementRowCol(index_, _file, _tabWidth, _current, _enabling);
        }
        return index_;
    }

    static int incrementRowCol(int _index, String _file, int _tabWidth, RowCol _current, EnablingSpaces _enabling) {
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
        updateAllowedSpaces(_index, _file, _enabling);
        return _index+1;
    }
    private static void updateAllowedSpaces(int _index, String _file, EnablingSpaces _enabledSpaces) {
        char cur_ = _file.charAt(_index);
        if (cur_ == LINE_RETURN) {
            if (_enabledSpaces.isOnlySpacesLine()) {
                _enabledSpaces.getFile().getLineReturns().add(_enabledSpaces.getBegin());
                _enabledSpaces.getFile().getLineReturns().add(Math.max(_index - 1,_enabledSpaces.getBegin()));
                int length_ = 0;
                for (int i = _enabledSpaces.getBegin() + 1; i < _index; i++) {
                    char current_ = _file.charAt(i);
                    if (current_ == TAB) {
                        length_ += _enabledSpaces.getTabWidth();
                    } else {
                        length_++;
                    }
                }
                _enabledSpaces.getFile().getLeftSpaces().add(length_);
            }
            _enabledSpaces.setOnlySpacesLine(true);
            _enabledSpaces.setEnabledTab(true);
            _enabledSpaces.setBegin(_index);
            return;
        }
        if (!Character.isWhitespace(cur_)) {
            if (_enabledSpaces.isOnlySpacesLine()) {
                if (_index == 0) {
                    _enabledSpaces.getFile().getLineReturns().add(-1);
                    _enabledSpaces.getFile().getLineReturns().add(Math.max(_index - 1,_enabledSpaces.getBegin()));
                } else {
                    _enabledSpaces.getFile().getLineReturns().add(_enabledSpaces.getBegin());
                    _enabledSpaces.getFile().getLineReturns().add(Math.max(_index - 1,_enabledSpaces.getBegin()));
                }
                int length_ = 0;
                for (int i = _enabledSpaces.getBegin() + 1; i < _index; i++) {
                    char current_ = _file.charAt(i);
                    if (current_ == TAB) {
                        length_ += _enabledSpaces.getTabWidth();
                    } else {
                        length_++;
                    }
                }
                _enabledSpaces.getFile().getLeftSpaces().add(length_);
            }
            _enabledSpaces.setOnlySpacesLine(false);
            _enabledSpaces.setEnabledTab(false);
            return;
        }
        if (cur_ == TAB && _enabledSpaces.isCheckTabs()) {
            if (!_enabledSpaces.isEnabledTab()) {
                _enabledSpaces.setOk(false);
                return;
            }
        }
    }
    private static int untilOperator(int _from, String _file, int _tabWidth,RowCol _rc, EnablingSpaces _enabledSpaces) {
        int len_ = _file.length();
        int i_ = _from;
        Numbers<Integer> localCallings_ = new Numbers<Integer>();
        boolean localConstChar_ = false;
        boolean localConstString_ = false;
        while (i_ < len_) {
            char locChar_ = _file.charAt(i_);
            if (localConstChar_) {
                if (locChar_ == ESCAPE) {
                    i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
                    i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
                    continue;
                }
                if (locChar_ == DEL_CHAR) {
                    i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
                    localConstChar_ = false;
                    continue;
                }
                i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
                continue;
            }
            if (localConstString_) {
                if (locChar_ == ESCAPE) {
                    i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
                    i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
                    continue;
                }
                if (locChar_ == DEL_STRING) {
                    i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
                    localConstString_ = false;
                    continue;
                }
                i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
                continue;
            }
            if (localCallings_.isEmpty() && locChar_ == BEGIN_BLOCK) {
                return i_;
            }
            if (locChar_ == DEL_CHAR) {
                localConstChar_ = true;
            }
            if (locChar_ == DEL_STRING) {
                localConstString_ = true;
            }
            if (locChar_ == BEGIN_CALLING) {
                localCallings_.add(i_);
            }
            if (locChar_ == END_CALLING) {
                localCallings_.removeLast();
            }
            if (locChar_ == BEGIN_BLOCK) {
                localCallings_.add(i_);
            }
            if (locChar_ == END_BLOCK) {
                localCallings_.removeLast();
            }
            i_=incrementRowCol(i_, _file, _tabWidth, _rc, _enabledSpaces);
        }
        return -1;
    }
    static int skipWhitespace(int _nextIndex, String _file, int _tabWidth,RowCol _rc, EnablingSpaces _enabledSpaces) {
        int nextIndex_ = _nextIndex;
        int len_ = _file.length();
        while (nextIndex_ < len_) {
            char currentChar_ = _file.charAt(nextIndex_);
            if (!_enabledSpaces.isOk()) {
                //ERROR
                return -1;
            }
            if (!Character.isWhitespace(currentChar_)) {
                break;
            }
            nextIndex_ = incrementRowCol(nextIndex_, _file, _tabWidth, _rc, _enabledSpaces);
        }
        return nextIndex_;
    }
}
