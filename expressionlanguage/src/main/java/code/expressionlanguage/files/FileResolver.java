package code.expressionlanguage.files;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.BadIndexInParser;
import code.expressionlanguage.errors.custom.DuplicateType;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.*;

public final class FileResolver {

    private static final char INHERIT = ':';
    private static final char FOR_BLOCKS = ':';
    private static final char END_IMPORTS = ';';
    private static final char LINE_RETURN = '\n';
    private static final char TAB = '\t';
    private static final char BEGIN_COMMENT = '/';
    private static final char SECOND_COMMENT = '*';
    private static final char PKG = '.';
    private static final char TYPE_VAR = '#';
    private static final String EMPTY_STRING = "";
    private static final String VARARG = "...";
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
    private static final char DEL_TEXT = '`';
    private static final char ESCAPE = '\\';
    private static final char ANNOT = '@';

    private FileResolver(){
    }
    public static void parseFile(String _fileName, String _file, boolean _predefined, ContextEl _context) {
        int tabWidth_ = _context.getTabWidth();
        FileBlock fileBlock_ = new FileBlock(new OffsetsBlock(),_predefined,tabWidth_);
        fileBlock_.setFileName(_fileName);
        EnablingSpaces enabledSpaces_ = new EnablingSpaces();
        enabledSpaces_.setEnabledSpace(true);
        enabledSpaces_.setEnabledTab(true);
        enabledSpaces_.setFile(fileBlock_);
        StringList importedTypes_ = new StringList();
        StringBuilder str_ = new StringBuilder();
        boolean allowedComments_ = true;
        KeyWords keyWords_ = _context.getKeyWords();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        String keyWordPackage_ = keyWords_.getKeyWordPackage();
        String keyWordPrivate_ = keyWords_.getKeyWordPrivate();
        String keyWordProtected_ = keyWords_.getKeyWordProtected();
        String keyWordPublic_ = keyWords_.getKeyWordPublic();
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordAnnotation_ = keyWords_.getKeyWordAnnotation();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordEnum_ = keyWords_.getKeyWordEnum();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordInterface_ = keyWords_.getKeyWordInterface();
        char previousChar_ = LINE_RETURN;
        int i_ = CustList.FIRST_INDEX;
        int len_ = _file.length();
        boolean commentedSingleLine_ = false;
        boolean commentedMultiLine_ = false;
        int indexImport_ = 0;
        Ints badIndexes_ = new Ints();
        Ints offsetsImports_ = new Ints();
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (commentedSingleLine_) {
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                    enabledSpaces_.setCheckTabs(true);
                }
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (commentedMultiLine_) {
                if (currentChar_ == SECOND_COMMENT) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        badIndexes_.add(i_);
                        break;
                    }
                    char nextChar_ = _file.charAt(i_ + 1);
                    if (nextChar_ == BEGIN_COMMENT) {
                        commentedMultiLine_ = false;
                        enabledSpaces_.setCheckTabs(true);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        previousChar_ = nextChar_;
                        continue;
                    }
                }
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (_file.indexOf(keyWordPublic_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordOperator_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordProtected_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordPackage_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordPrivate_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordAbstract_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordAnnotation_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordClass_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordEnum_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordFinal_, i_) == i_) {
                break;
            }
            if (_file.indexOf(keyWordInterface_, i_) == i_) {
                break;
            }
            if (currentChar_ == ANNOT) {
                break;
            }
            if (!enabledSpaces_.isOk()) {
                //ERROR
                badIndexes_.add(i_);
                break;
            }
            if (currentChar_ == BEGIN_COMMENT) {
                if (!allowedComments_) {
                    //ERROR
                    badIndexes_.add(i_);
                    break;
                }
                if (i_ + 1 >= len_) {
                    //ERROR
                    badIndexes_.add(i_);
                    break;
                }
                char nextChar_ = _file.charAt(i_ + 1);
                if (nextChar_ == BEGIN_COMMENT) {
                    commentedSingleLine_ = true;
                    enabledSpaces_.setCheckTabs(false);
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    previousChar_ = nextChar_;
                    continue;
                }
                if (nextChar_ == SECOND_COMMENT) {
                    commentedMultiLine_ = true;
                    enabledSpaces_.setCheckTabs(false);
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    previousChar_ = nextChar_;
                    continue;
                }
                //ERROR
                badIndexes_.add(i_);
                break;
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
            i_ = incrementRowCol(i_, _file, enabledSpaces_);
        }
        if (i_ >= len_) {
            badIndexes_.add(i_);
        }
        InputTypeCreation input_ = new InputTypeCreation();
        input_.setNextIndex(i_);
        fileBlock_.getImports().addAllElts(importedTypes_);
        fileBlock_.getImportsOffset().addAllElts(offsetsImports_);
        input_.setEnabledSpaces(enabledSpaces_);
        Classes cls_ = _context.getClasses();
        cls_.putFileBlock(_fileName, fileBlock_);
        if (!badIndexes_.isEmpty()) {
            for (int i: badIndexes_) {
                BadIndexInParser b_ = new BadIndexInParser();
                b_.setFileName(_fileName);
                b_.setIndex(i);
                cls_.addError(b_);
            }
            return;
        }
        while (true) {
            input_.setNextIndex(i_);
            ResultCreation res_ = createType(_context, _file, input_);
            badIndexes_ = input_.getBadIndexes();
            if (!res_.isOk()) {
                for (int i: badIndexes_) {
                    BadIndexInParser b_ = new BadIndexInParser();
                    b_.setFileName(_fileName);
                    b_.setIndex(i);
                    cls_.addError(b_);
                }
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
                            if (StringList.contains(simpleNames_, s_)) {
                                //ERROR
                                DuplicateType d_ = new DuplicateType();
                                d_.setId(cur_.getFullName());
                                d_.setFileName(cur_.getFile().getFileName());
                                d_.setIndexFile(cur_.getIdRowCol());
                                cls_.addError(d_);
                            }
                            cls_.processBracedClass(cur_, _context);
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
                    cls_.processBracedClass(r_, _context);
                }
            }
            if (res_ instanceof ResultOperatorCreation) {
                ResultOperatorCreation restype_ = (ResultOperatorCreation) res_;
                OperatorBlock r_ = restype_.getType();
                fileBlock_.appendChild(r_);
                cls_.getOperators().add(r_);
            }
            i_ = res_.getNextIndex();
            boolean hasNext_ = false;
            while (i_ < len_) {
                char currentChar_ = _file.charAt(i_);
                if (commentedSingleLine_) {
                    if (currentChar_ == LINE_RETURN) {
                        commentedSingleLine_ = false;
                        enabledSpaces_.setCheckTabs(true);
                    }
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    continue;
                }
                if (commentedMultiLine_) {
                    if (currentChar_ == SECOND_COMMENT) {
                        if (i_ + 1 >= len_) {
                            break;
                        }
                        char nextChar_ = _file.charAt(i_ + 1);
                        if (nextChar_ == BEGIN_COMMENT) {
                            commentedMultiLine_ = false;
                            enabledSpaces_.setCheckTabs(true);
                            i_ = incrementRowCol(i_, _file, enabledSpaces_);
                            i_ = incrementRowCol(i_, _file, enabledSpaces_);
                            continue;
                        }
                    }
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    continue;
                }
                if (StringList.isDollarWordChar(currentChar_)) {
                    hasNext_ = true;
                    break;
                }
                if (currentChar_ == ANNOT) {
                    hasNext_ = true;
                    break;
                }
                if (currentChar_ == BEGIN_COMMENT) {
                    if (i_ + 1 >= len_) {
                        break;
                    }
                    char nextChar_ = _file.charAt(i_ + 1);
                    if (nextChar_ == BEGIN_COMMENT) {
                        commentedSingleLine_ = true;
                        enabledSpaces_.setCheckTabs(false);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        continue;
                    }
                    if (nextChar_ == SECOND_COMMENT) {
                        commentedMultiLine_ = true;
                        enabledSpaces_.setCheckTabs(false);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        continue;
                    }
                    //ERROR
                    badIndexes_.add(i_);
                    break;
                }
                if (!enabledSpaces_.isOk()) {
                    //ERROR
                    badIndexes_.add(i_);
                    break;
                }
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
            }
            if (!hasNext_) {
                for (int i: badIndexes_) {
                    BadIndexInParser b_ = new BadIndexInParser();
                    b_.setFileName(_fileName);
                    b_.setIndex(i);
                    cls_.addError(b_);
                }
                return;
            }
            input_.setNextIndex(i_);
        }
    }
    private static ResultCreation createType(ContextEl _context, String _file, InputTypeCreation _input) {
        KeyWords keyWords_ = _context.getKeyWords();
        EnablingSpaces enabledSpaces_ = _input.getEnabledSpaces();
        ResultCreation out_ = new ResultTypeCreation();
        AccessEnum access_;
        int i_ = _input.getNextIndex();
        int len_ = _file.length();
        int nextIndex_ = i_;
        int beginType_ = nextIndex_;
        int accessOffsetType_ = beginType_;
        String afterAccessType_ = _file.substring(i_);
        Ints annotationsIndexesTypes_ = new Ints();
        StringList annotationsTypes_ = new StringList();
        Ints badIndexes_ = _input.getBadIndexes();
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
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordAnnotation_ = keyWords_.getKeyWordAnnotation();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordEnum_ = keyWords_.getKeyWordEnum();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordInterface_ = keyWords_.getKeyWordInterface();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        String keyWordPackage_ = keyWords_.getKeyWordPackage();
        String keyWordPrivate_ = keyWords_.getKeyWordPrivate();
        String keyWordProtected_ = keyWords_.getKeyWordProtected();
        String keyWordPublic_ = keyWords_.getKeyWordPublic();
        if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordPublic_)) {
            access_ = AccessEnum.PUBLIC;
            nextIndex_ = incrementRowCol(nextIndex_, keyWordPublic_.length(), _file, enabledSpaces_);
            nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
       } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordProtected_)) {
            access_ = AccessEnum.PROTECTED;
            nextIndex_ = incrementRowCol(nextIndex_, keyWordProtected_.length(), _file, enabledSpaces_);
            nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordPackage_)) {
            access_ = AccessEnum.PACKAGE;
            nextIndex_ = incrementRowCol(nextIndex_, keyWordPackage_.length(), _file, enabledSpaces_);
            nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordPrivate_)) {
            access_ = AccessEnum.PRIVATE;
            nextIndex_ = incrementRowCol(nextIndex_, keyWordPrivate_.length(), _file, enabledSpaces_);
            nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordOperator_)) {
            access_ = AccessEnum.PUBLIC;
            oper_ = true;
            nextIndex_ = incrementRowCol(nextIndex_, keyWordOperator_.length(), _file, enabledSpaces_);
            nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordAbstract_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordFinal_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordClass_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordInterface_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordEnum_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (ContextEl.startsWithKeyWord(afterAccessType_, keyWordAnnotation_)) {
            access_ = AccessEnum.PACKAGE;
        } else {
            //ERROR
            badIndexes_.add(nextIndex_);
            return out_;
        }
        if (nextIndex_ < 0) {
            //ERROR
            badIndexes_.add(0);
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
                nextIndex_ = incrementRowCol(nextIndex_, _file, enabledSpaces_);
            }
            int bk_ = nextIndex_;
            nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
            if (nextIndex_ < 0) {
                //ERROR
                badIndexes_.add(bk_);
                return out_;
            }
        }
        StringList importedTypes_;
        Ints offsetsImports_;
        boolean enableByEndLine_ = false;
        BracedBlock currentParent_;
        Ints braces_ = new Ints();
        if (oper_) {
            ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _file, badIndexes_, enabledSpaces_);
            importedTypes_ = p_.getImportedTypes();
            offsetsImports_ = p_.getOffsetsImports();
            nextIndex_ = p_.getNextIndex();
            if (!p_.isOk()) {
                //ERROR
                badIndexes_.add(nextIndex_);
                return out_;
            }
            out_ = new ResultOperatorCreation();
            int until_ = _file.indexOf(BEGIN_BLOCK,nextIndex_);
            if (until_ < 0) {
                badIndexes_.add(nextIndex_);
                return out_;
            }
            String info_ = _file.substring(nextIndex_, until_);
            int typeOffset_ = nextIndex_;
            int paramOffest_;
            String declaringType_;
            String afterModifier_ = info_;
            info_ = afterModifier_.trim();
            declaringType_ = getDeclaringTypeOper(info_);
            int declTypeLen_ = declaringType_.length();
            String afterType_ = info_.substring(declTypeLen_);
            info_ = afterType_.trim();
            int leftParIndex_ = info_.indexOf(BEGIN_CALLING);
            if (leftParIndex_ < 0) {
                badIndexes_.add(nextIndex_);
                return out_;
            }
            String afterMethodName_ = info_.substring(leftParIndex_ + 1);
            paramOffest_ = typeOffset_ + declTypeLen_ + 1;
            paramOffest_ += StringList.getFirstPrintableCharIndex(afterMethodName_);
            info_ = afterMethodName_.trim();
            Ints offestsTypes_ = new Ints();
            Ints offestsParams_ = new Ints();
            StringList parametersType_ = new StringList();
            StringList parametersName_ = new StringList();
            CustList<Ints> annotationsIndexesParams_ = new CustList<Ints>();
            CustList<StringList> annotationsParams_ = new CustList<StringList>();
            while (true) {
                if (info_.indexOf(END_CALLING) == 0) {
                    break;
                }
                Ints annotationsIndexesParam_ = new Ints();
                StringList annotationsParam_ = new StringList();
                String trim_ = info_.trim();
                if (trim_.isEmpty()) {
                    badIndexes_.add(nextIndex_);
                    return out_;
                }
                if (trim_.charAt(0) == ANNOT) {
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
                String paramType_ = getDeclaringParamType(info_);
                parametersType_.add(paramType_.trim());
                String afterParamType_ = info_.substring(paramType_.length());
                info_ = afterParamType_.trim();
                int call_ = info_.indexOf(SEP_CALLING);
                if (call_ < 0) {
                    call_ = info_.indexOf(END_CALLING);
                }
                if (call_ < 0) {
                    badIndexes_.add(nextIndex_);
                    return out_;
                }
                int off_ = StringList.getFirstPrintableCharIndex(afterParamType_);
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
            currentParent_ = new OperatorBlock(new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(symbolIndex_, symbol_.toString().trim()), parametersType_, offestsTypes_, parametersName_, offestsParams_, new OffsetsBlock(nextIndex_, nextIndex_));
            ((NamedFunctionBlock)currentParent_).getAnnotationsParams().addAllElts(annotationsParams_);
            ((NamedFunctionBlock)currentParent_).getAnnotationsIndexesParams().addAllElts(annotationsIndexesParams_);
            ((OperatorBlock)currentParent_).getImports().addAllElts(importedTypes_);
            ((OperatorBlock)currentParent_).getImportsOffset().addAllElts(offsetsImports_);
            ((OperatorBlock)currentParent_).getAnnotations().addAllElts(annotationsTypes_);
            ((OperatorBlock)currentParent_).getAnnotationsIndexes().addAllElts(annotationsIndexesTypes_);
            ((ResultOperatorCreation)out_).setType((OperatorBlock) currentParent_);
            braces_.add(until_);
            nextIndex_ = incrementRowCol(until_, _file, enabledSpaces_);
        } else {
            out_ = new ResultTypeCreation();
            char currentChar_;
            boolean abstractType_ = false;
            boolean finalType_ = false;
            int bk_ = nextIndex_;
            String beforeQu_ = _file.substring(nextIndex_);
            if (ContextEl.startsWithKeyWord(beforeQu_, keyWordAbstract_)) {
                abstractType_ = true;
                nextIndex_ = incrementRowCol(nextIndex_, keyWordAbstract_.length(), _file, enabledSpaces_);
                nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
                if (nextIndex_ < 0) {
                    //ERROR
                    badIndexes_.add(bk_);
                    return out_;
                }
            }
            beforeQu_ = _file.substring(nextIndex_);
            if (ContextEl.startsWithKeyWord(beforeQu_, keyWordFinal_)) {
                finalType_ = true;
                nextIndex_ = incrementRowCol(nextIndex_, keyWordFinal_.length(), _file, enabledSpaces_);
                nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
                if (nextIndex_ < 0) {
                    //ERROR
                    badIndexes_.add(bk_);
                    return out_;
                }
            }
            int categoryOffset_ = nextIndex_ - 1;
            String type_;
            String beforeCat_ = _file.substring(nextIndex_);
            if (ContextEl.startsWithKeyWord(beforeCat_, keyWordClass_)) {
                type_ = keyWordClass_;
                nextIndex_ = incrementRowCol(nextIndex_, keyWordClass_.length(), _file, enabledSpaces_);
            } else if (ContextEl.startsWithKeyWord(beforeCat_, keyWordEnum_)) {
                type_ = keyWordEnum_;
                nextIndex_ = incrementRowCol(nextIndex_, keyWordEnum_.length(), _file, enabledSpaces_);
            } else if (ContextEl.startsWithKeyWord(beforeCat_, keyWordInterface_)) {
                type_ = keyWordInterface_;
                nextIndex_ = incrementRowCol(nextIndex_, keyWordInterface_.length(), _file, enabledSpaces_);
            } else if (ContextEl.startsWithKeyWord(beforeCat_, keyWordAnnotation_)) {
                type_ = keyWordAnnotation_;
                nextIndex_ = incrementRowCol(nextIndex_, keyWordAnnotation_.length(), _file, enabledSpaces_);
            } else {
                //ERROR
                badIndexes_.add(nextIndex_);
                return out_;
            }
            bk_ = nextIndex_;
            nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
            if (nextIndex_ < 0) {
                //ERROR
                badIndexes_.add(bk_);
                return out_;
            }
            ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _file, badIndexes_, enabledSpaces_);
            importedTypes_ = p_.getImportedTypes();
            offsetsImports_ = p_.getOffsetsImports();
            nextIndex_ = p_.getNextIndex();
            if (!p_.isOk()) {
                //ERROR
                badIndexes_.add(nextIndex_);
                return out_;
            }
            //insert interfaces static initialization for class and enums
            StringList staticInitInterfaces_ = new StringList();
            Ints staticInitInterfacesOffset_ = new Ints();
            if (ContextEl.startsWithKeyWord(_file.substring(nextIndex_), keyWordInterfaces_)) {
                int begin_ = _file.indexOf(BEGIN_CALLING, nextIndex_);
                if (begin_ < 0) {
                    //ERROR
                    badIndexes_.add(nextIndex_);
                    return out_;
                }
                int end_ = _file.indexOf(END_CALLING, begin_);
                if (end_ < 0) {
                    //ERROR
                    badIndexes_.add(begin_);
                    return out_;
                }
                int interfaceOffest_ = begin_ + 1;
                String interfacesInfo_ = _file.substring(begin_ + 1, end_);
                for (int i = begin_ + 1; i < end_; i++) {
                    updateAllowedSpaces(i, _file, enabledSpaces_);
                    if (!enabledSpaces_.isOk()) {
                        //ERROR
                        badIndexes_.add(i);
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
            bk_ = nextIndex_;
            nextIndex_ = skipWhitespace(nextIndex_, _file, enabledSpaces_);
            if (nextIndex_ < 0) {
                //ERROR
                badIndexes_.add(bk_);
                return out_;
            }
            StringBuilder str_ = new StringBuilder();
            IntTreeMap< String> superTypes_ = new IntTreeMap< String>();
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
                    badIndexes_.add(nextIndex_);
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
                    nextIndex_ = incrementRowCol(nextIndex_, _file, enabledSpaces_);
                    inheritIndex_ = nextIndex_;
                    continue;
                }
                if (currentChar_ == BEGIN_BLOCK) {
                    braces_.add(nextIndex_);
                    ok_ = true;
                    break;
                }
                if (foundInherit_) {
                    str_.append(currentChar_);
                }
                nextIndex_ = incrementRowCol(nextIndex_, _file, enabledSpaces_);
            }
            if (foundInherit_) {
                superTypes_.put(inheritIndex_, str_.toString());
            }
            if (!ok_) {
                //ERROR
                badIndexes_.add(len_-1);
                return out_;
            }
            nextIndex_ = incrementRowCol(nextIndex_, _file, enabledSpaces_);
            RootBlock typeBlock_;
            String tempDef_ = templateDef_.toString();
            String typeName_ = typeNamePref_.toString();
            String packageName_ = EMPTY_STRING;
            String baseName_;
            int lastDot_ = typeName_.lastIndexOf(PKG);
            if (lastDot_ >= 0) {
                packageName_ = typeName_.substring(0, lastDot_);
                baseName_ = typeName_.substring(lastDot_ + 1);
            } else {
                baseName_ = typeName_;
            }
            
            if (StringList.quickEq(type_, keyWordEnum_)) {
                enableByEndLine_ = true;
                typeBlock_ = new EnumBlock(beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, access_) , tempDef_, superTypes_, new OffsetsBlock(beginType_ - 1,beginType_ - 1));
            } else if (StringList.quickEq(type_, keyWordClass_)) {
                typeBlock_ = new ClassBlock(beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, access_), tempDef_, superTypes_, finalType_, abstractType_, true, new OffsetsBlock(beginType_ - 1,beginType_ - 1));
            } else if (StringList.quickEq(type_, keyWordInterface_)) {
                typeBlock_ = new InterfaceBlock(beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, access_) , tempDef_, superTypes_, true, new OffsetsBlock(beginType_ - 1,beginType_ - 1));
            } else {
                typeBlock_ = new AnnotationBlock(beginDefinition_, categoryOffset_, baseName_, packageName_, new OffsetAccessInfo(accessOffsetType_ - 1, access_) , tempDef_, superTypes_, new OffsetsBlock(beginType_ - 1,beginType_ - 1));
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
        return processOuterTypeBody(_context, _input, nextIndex_, _file, currentParent_, braces_, enableByEndLine_, out_);
    }
    private static ResultCreation processOuterTypeBody(ContextEl _context, InputTypeCreation _input, int _nextIndex,
            String _file, BracedBlock _currentParent, Ints _braces, boolean _enabledEnum, ResultCreation _out) {
        int len_ = _file.length();
        Options options_ = _context.getOptions();
        KeyWords keyWords_ = _context.getKeyWords();
        String keyWordCase_ = keyWords_.getKeyWordCase();
        String keyWordDefault_ = keyWords_.getKeyWordDefault();
        String keyWordReturn_ = keyWords_.getKeyWordReturn();
        boolean allowedComments_ = false;
        IdMap<SwitchPartBlock, Boolean> bracedSwitchPart_ = new IdMap<SwitchPartBlock, Boolean>();
        StringBuilder instruction_ = new StringBuilder();
        int instructionLocation_ = -1;
        EnablingSpaces enabledSpaces_ = _input.getEnabledSpaces();
        Ints badIndexes_ = _input.getBadIndexes();
        Ints parentheses_ = new Ints();
        boolean commentedSingleLine_ = false;
        boolean commentedMultiLine_ = false;
        boolean constChar_ = false;
        boolean constString_ = false;
        boolean constText_ = false;
        boolean declType_ = false;
        BracedBlock currentParent_ = _currentParent;

        int i_ = _nextIndex;
        boolean okType_ = false;
        char endLine_ = _context.getOptions().getEndLine();
        char suffix_ = _context.getOptions().getSuffix();
        boolean enableByEndLine_ = _enabledEnum;
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        after_.setEnabledEnumHeader(enableByEndLine_);
        after_.setIndex(i_);
        after_.setParent(currentParent_);
        int ltGt_ = 0;
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (commentedSingleLine_) {
                if (currentChar_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                    enabledSpaces_.setCheckTabs(true);
                    instruction_.delete(0, instruction_.length());
                }
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (commentedMultiLine_) {
                if (currentChar_ == SECOND_COMMENT) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        badIndexes_.add(i_);
                        break;
                    }
                    char nextChar_ = _file.charAt(i_ + 1);
                    if (nextChar_ == BEGIN_COMMENT) {
                        commentedMultiLine_ = false;
                        enabledSpaces_.setCheckTabs(true);
                        instruction_.delete(0, instruction_.length());
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        continue;
                    }
                }
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (constChar_) {
                instruction_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        badIndexes_.add(i_);
                        break;
                    }
                    instruction_.append(_file.charAt(i_+1));
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    continue;
                }
                if (currentChar_ == DEL_CHAR) {
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    constChar_ = false;
                    allowedComments_ = false;
                    enabledSpaces_.setCheckTabs(true);
                    continue;
                }
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (constString_) {
                instruction_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        badIndexes_.add(i_);
                        break;
                    }
                    instruction_.append(_file.charAt(i_+1));
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    continue;
                }
                if (currentChar_ == DEL_STRING) {
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    constString_ = false;
                    allowedComments_ = false;
                    enabledSpaces_.setCheckTabs(true);
                    continue;
                }
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (constText_) {
                instruction_.append(currentChar_);
                if (i_ + 1 >= len_) {
                    //ERROR
                    badIndexes_.add(i_);
                    break;
                }
                if(currentChar_ == DEL_TEXT) {
                    if (_file.charAt(i_ + 1) != DEL_TEXT) {
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        constText_ = false;
                        allowedComments_ = false;
                        enabledSpaces_.setCheckTabs(true);
                        continue;
                    }
                    instruction_.append(_file.charAt(i_+1));
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    i_ = incrementRowCol(i_, _file, enabledSpaces_);
                    continue;
                }
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (!enabledSpaces_.isOk()) {
                //ERROR
                badIndexes_.add(i_);
                break;
            }
            if (currentChar_ == BEGIN_COMMENT) {
                if (i_ + 1 >= len_) {
                    //ERROR
                    badIndexes_.add(i_);
                    break;
                }
                char nextChar_ = _file.charAt(i_ + 1);
                if (nextChar_ == BEGIN_COMMENT) {
                    if (allowedComments_) {
                        commentedSingleLine_ = true;
                        enabledSpaces_.setCheckTabs(false);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        continue;
                    }
                }
                if (nextChar_ == SECOND_COMMENT) {
                    if (allowedComments_) {
                        commentedMultiLine_ = true;
                        enabledSpaces_.setCheckTabs(false);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        i_ = incrementRowCol(i_, _file, enabledSpaces_);
                        continue;
                    }
                }
            }
            if (currentChar_ == DEL_CHAR) {
                if (instruction_.length() == 0) {
                    instructionLocation_ = i_;
                }
                instruction_.append(currentChar_);
                constChar_ = true;
                enabledSpaces_.setCheckTabs(false);
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (currentChar_ == DEL_STRING) {
                if (instruction_.length() == 0) {
                    instructionLocation_ = i_;
                }
                instruction_.append(currentChar_);
                constString_ = true;
                enabledSpaces_.setCheckTabs(false);
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            if (currentChar_ == DEL_TEXT) {
                if (instruction_.length() == 0) {
                    instructionLocation_ = i_;
                }
                instruction_.append(currentChar_);
                constText_ = true;
                enabledSpaces_.setCheckTabs(false);
                i_ = incrementRowCol(i_, _file, enabledSpaces_);
                continue;
            }
            boolean endInstruction_ = false;
            if (parentheses_.isEmpty()) {
                if (currentChar_ == endLine_ && !declType_) {
                    endInstruction_ = true;
                }
                if (!declType_ && options_.getSuffixVar() == VariableSuffix.NONE) {
                    if (currentChar_ == suffix_) {
                        if (currentParent_ instanceof SwitchBlock) {
                            endInstruction_ = true;
                        }
                        if (currentParent_ instanceof SwitchPartBlock) {
                            String str_ = instruction_.toString().trim();
                            if (ContextEl.startsWithKeyWord(str_, keyWordCase_)) {
                                currentParent_ = currentParent_.getParent();
                                endInstruction_ = true;
                            }
                            if (ContextEl.startsWithKeyWord(str_, keyWordDefault_)) {
                                currentParent_ = currentParent_.getParent();
                                endInstruction_ = true;
                            }
                        }
                    }
                }
                if (currentChar_ == SEP_ENUM_CONST && enableByEndLine_) {
                    if (ltGt_ == 0) {
                        endInstruction_ = true;
                    }
                }
                if (currentChar_ == END_BLOCK) {
                    endInstruction_ = true;
                }
                if (currentChar_ == BEGIN_BLOCK) {
                    String tr_ = instruction_.toString().trim();
                    if (declType_) {
                        endInstruction_ = true;
                    } else if (tr_.isEmpty()) {
                        endInstruction_ = true;
                    } else if (!StringList.quickEq(tr_, keyWordReturn_)) {
                        char lastChar_ = tr_.charAt(tr_.length() - 1);
                        if (!(currentParent_ instanceof AnnotationBlock)) {
                            if (lastChar_ != PART_SEPARATOR) {
                                if (lastChar_ != END_ARRAY) {
                                    if (options_.getSuffixVar() == VariableSuffix.NONE) {
                                        if (lastChar_ != suffix_ && lastChar_ != '?') {
                                            endInstruction_ = true;
                                        }
                                    } else {
                                        endInstruction_ = true;
                                    }
                                }
                            }
                        }
                    }
                }
                if (enableByEndLine_) {
                    if (currentChar_ == BEGIN_TEMPLATE) {
                        ltGt_++;
                    }
                    if (currentChar_ == END_TEMPLATE) {
                        ltGt_--;
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
            if (!Character.isWhitespace(currentChar_)) {
                allowedComments_ = false;
            } else if (currentChar_ == LINE_RETURN) {
                if (instruction_.toString().trim().isEmpty()) {
                    allowedComments_ = true;
                }
            }
            if (currentChar_ == BEGIN_CALLING) {
                parentheses_.add(i_);
            }
            if (currentChar_ == BEGIN_ARRAY) {
                parentheses_.add(i_);
            }
            if (currentChar_ == END_CALLING) {
                if (parentheses_.isEmpty()) {
                    badIndexes_.add(i_);
                    break;
                }
                parentheses_.removeLast();
            }
            if (currentChar_ == END_ARRAY) {
                if (parentheses_.isEmpty()) {
                    badIndexes_.add(i_);
                    break;
                }
                parentheses_.removeLast();
            }
            if (currentChar_ == BEGIN_BLOCK) {
                if (endInstruction_) {
                    _braces.add(i_);
                } else {
                    parentheses_.add(i_);
                }
            }
            if (currentChar_ == END_BLOCK) {
                if (endInstruction_) {
                    _braces.removeLast();
                } else {
                    parentheses_.removeLast();
                }
            }
            if (currentParent_ == null) {
                badIndexes_.add(_nextIndex);
                break;
            }
            if (endInstruction_) {
                after_ = processInstruction(_context, _input, currentChar_, currentParent_, bracedSwitchPart_, _braces, instructionLocation_, instruction_, _file, declType_, i_, _nextIndex, enableByEndLine_);
                if (after_ == null) {
                    badIndexes_.add(_nextIndex);
                    return _out;
                }
                enableByEndLine_ = after_.isEnabledEnumHeader();
                currentParent_ = after_.getParent();
                i_ = after_.getIndex();
                declType_ = false;
            } else {
                if (StringList.isDollarWordChar(currentChar_) && i_ + 1 < len_ && !StringList.isDollarWordChar(_file.charAt(i_ + 1))) {
                    StringList parts_ = StringList.getDollarWordSeparators(instruction_.toString());
                    StringList printable_ = new StringList();
                    for (String p: parts_) {
                        String t_ = p.trim();
                        if (!StringList.isDollarWord(t_)) {
                            continue;
                        }
                        printable_.add(t_);
                    }
                    String category_ = printable_.last();
                    if (isKeyWordCategory(category_, keyWords_)) {
                        int j_ = i_ + 1;
                        boolean pr_ = false;
                        while (j_ < len_) {
                            char next_ = _file.charAt(j_);
                            if (Character.isWhitespace(next_)) {
                                j_++;
                                continue;
                            }
                            if (StringList.isDollarWordChar(next_)) {
                                pr_ = true;
                            }
                            if (next_ == BEGIN_ARRAY) {
                                pr_ = true;
                            }
                            break;
                        }
                        if (pr_) {
                            declType_ = true;
                        }
                    }
                }
            }
            if (_braces.isEmpty()) {
                okType_ = true;
                break;
            }
            i_ = incrementRowCol(i_, _file, enabledSpaces_);
        }
        if (okType_) {
            i_ = incrementRowCol(i_, _file, enabledSpaces_);
        } else {
            badIndexes_.add(len_);
        }
        _out.setNextIndex(i_);
        _out.setOk(okType_);
        return _out;
    }
    private static AfterBuiltInstruction processInstruction(ContextEl _context, InputTypeCreation _input, char _currentChar,
                                                            BracedBlock _currentParent, IdMap<SwitchPartBlock, Boolean> _bracedSwitchPart, Ints _braces,
                                                            int _instructionLocation, StringBuilder _instruction, String _file, boolean _declType, int _i, int _nextIndex, boolean _enabledEnum) {
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        EnablingSpaces enabledSpaces_ = _input.getEnabledSpaces();
        Ints badIndexes_ = _input.getBadIndexes();
        Options options_ = _context.getOptions();
        BracedBlock currentParent_ = _currentParent;
        int instructionLocation_ = _instructionLocation;
        int i_ = _i;
        Block br_ = null;
        char endLine_ = _context.getOptions().getEndLine();
        char suffix_ = _context.getOptions().getSuffix();
        String found_ = _instruction.toString();
        String trimmedInstruction_ = found_.trim();
        int instructionRealLocation_ = instructionLocation_;
        KeyWords keyWords_ = _context.getKeyWords();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordCase_ = keyWords_.getKeyWordCase();
        String keyWordDefault_ = keyWords_.getKeyWordDefault();
        if (!trimmedInstruction_.isEmpty()) {
            instructionLocation_ += StringList.getFirstPrintableCharIndex(found_);
        }
        boolean enableByEndLine_ = _enabledEnum;
        if (currentParent_ instanceof AnnotationBlock) {
            if (!trimmedInstruction_.isEmpty()) {
                String fieldName_;
                int typeOffset_ = instructionLocation_;
                int expressionOffest_;
                String expression_;
                int delta_ = 0;
                Ints annotationsIndexes_ = new Ints();
                StringList annotations_ = new StringList();
                if (_declType) {
                    RootBlock built_ = processTypeHeader(_context, _input, true,
                            _file,
                            instructionLocation_, instructionRealLocation_,
                            found_, enabledSpaces_,
                            currentParent_, trimmedInstruction_,
                            AccessEnum.PUBLIC);
                    if (built_ == null) {
                        return null;
                    }
                    if (built_ instanceof EnumBlock) {
                        enableByEndLine_ = true;
                    }
                    br_ = built_;
                } else {
                    if (trimmedInstruction_.charAt(0) == ANNOT) {
                        ParsedAnnotations par_ = new ParsedAnnotations(found_, instructionRealLocation_);
                        par_.parse();
                        annotationsIndexes_ = par_.getAnnotationsIndexes();
                        annotations_ = par_.getAnnotations();
                        found_ = par_.getAfter();
                        typeOffset_ = par_.getIndex();
                        delta_ = typeOffset_ - instructionRealLocation_;
                    }
                    String otherModifier_;
                    String infoModifiers_ = found_.trim();
                    int finalOff_ = 0;
                    boolean final_ = false;
                    boolean meth_ = true;
                    int deltaFinal_;
                    if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordFinal_)) {
                        otherModifier_ = keyWordFinal_;
                        int lenLoc_ = otherModifier_.length();
                        deltaFinal_ = lenLoc_;
                        String sub_ = infoModifiers_.substring(lenLoc_);
                        int deltaSec_ = StringList.getFirstPrintableCharIndex(sub_);
                        deltaFinal_ += deltaSec_;
                        finalOff_ = typeOffset_;
                        found_ = sub_.substring(deltaSec_);
                        final_ = true;
                        meth_ = false;
                        typeOffset_ += deltaFinal_;
                    }
                    String declaringType_ = getDeclaringTypeBlock(found_);
                    found_ = found_.substring(declaringType_.length());
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
                        int fieldNameOffest_ = StringList.getFirstPrintableCharIndex(realFound_) +declaringType_.trim().length() + typeOffset_;
                        br_ = new FieldBlock(
                                new OffsetAccessInfo(-1, AccessEnum.PUBLIC),
                                new OffsetBooleanInfo(-1, true),
                                new OffsetBooleanInfo(finalOff_, final_),
                                new OffsetStringInfo(typeOffset_, declaringType_.trim()),
                                new OffsetStringInfo(fieldNameOffest_, realFound_.trim()),
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
                        br_ = new AnnotationMethodBlock(
                                new OffsetStringInfo(typeOffset_, declaringType_.trim()),
                                new OffsetStringInfo(fieldOffest_,fieldName_.trim()),
                                new OffsetStringInfo(expressionOffest_,expression_.trim()),
                                new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                    }
                    ((AnnotableBlock) br_).getAnnotations().addAllElts(annotations_);
                    ((AnnotableBlock) br_).getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                    currentParent_.appendChild(br_);
                }
            } else {
                //implicit static block
                if (_currentChar != END_BLOCK) {
                    br_ = new StaticBlock(new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                    currentParent_.appendChild(br_);
                }
            }
            if (_currentChar == END_BLOCK) {
                if (!trimmedInstruction_.isEmpty()) {
                    badIndexes_.add(_nextIndex);
                }
                currentParent_ = currentParent_.getParent();
            } else if (br_ instanceof BracedBlock && _currentChar != endLine_) {
                currentParent_ = (BracedBlock) br_;
            }
        } else if (currentParent_ instanceof EnumBlock && enableByEndLine_) {
            if (!trimmedInstruction_.isEmpty()) {
                String fieldName_;
                int fieldOffest_ = instructionLocation_;
                int expressionOffest_;
                String expression_ = EMPTY_STRING;
                int delta_ = 0;
                Ints annotationsIndexes_ = new Ints();
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
                    int endIndex_ = found_.lastIndexOf(END_CALLING);
                    if (endIndex_ < indexBeginCalling_ + 1) {
                        badIndexes_.add(indexBeginCalling_ + 1);
                        return null;
                    }
                    expression_ = found_.substring(indexBeginCalling_ + 1, endIndex_);
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
                if (_currentChar == BEGIN_BLOCK) {
                    enableByEndLine_ = false;
                    br_ = new InnerElementBlock((EnumBlock) currentParent_, new OffsetStringInfo(fieldOffest_, fieldName_.trim()),
                            new OffsetStringInfo(templateOffset_, tmpPart_.trim()),
                            new OffsetStringInfo(expressionOffest_, expression_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                } else {
                    br_ = new ElementBlock(new OffsetStringInfo(fieldOffest_, fieldName_.trim()),
                            new OffsetStringInfo(templateOffset_, tmpPart_.trim()),
                            new OffsetStringInfo(expressionOffest_, expression_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                }
                ((AnnotableBlock) br_).getAnnotations().addAllElts(annotations_);
                ((AnnotableBlock) br_).getAnnotationsIndexes().addAllElts(annotationsIndexes_);
                currentParent_.appendChild(br_);
                if (_currentChar == BEGIN_BLOCK) {
                    currentParent_ = (BracedBlock) br_;
                }
            }
            if (_currentChar == END_BLOCK) {
                currentParent_ = currentParent_.getParent();
            }
            if (_currentChar == endLine_ || _currentChar == END_BLOCK) {
                enableByEndLine_ = false;
            }
        } else if (_currentChar != END_BLOCK) {
            Block bl_ = processInstructionBlock(_context, badIndexes_, _file, _bracedSwitchPart, instructionLocation_, instructionRealLocation_, i_, currentParent_, trimmedInstruction_);
            if (bl_ == null) {
                if (_declType || currentParent_ instanceof RootBlock) {
                    //fields, constructors or methods
                    if (_declType) {
                        //Inner types
                        RootBlock built_ = processTypeHeader(_context, _input, false,
                                _file,
                                instructionLocation_, instructionRealLocation_,
                                found_, enabledSpaces_,
                                currentParent_, trimmedInstruction_,
                                AccessEnum.PACKAGE);
                        if (built_ == null) {
                            return null;
                        }
                        if (built_ instanceof EnumBlock) {
                            enableByEndLine_ = true;
                        }
                        br_ = built_;
                    } else {
                        br_ = processTypeMember(_context, _instruction, instructionLocation_, i_, currentParent_);
                        if (br_ == null) {
                            return null;
                        }
                    }
                } else {
                    int affectOffset_;
                    int afterDeclareOffset_;
                    boolean finalLocalVar_ = ContextEl.startsWithKeyWord(trimmedInstruction_, keyWordFinal_);
                    int delta_ = 0;
                    int deltaAfter_ = 0;
                    if (finalLocalVar_) {
                        deltaAfter_ = keyWordFinal_.length();
                        delta_ = StringList.getFirstPrintableCharIndex(found_) + deltaAfter_;
                        deltaAfter_ = delta_;
                        deltaAfter_ += StringList.getFirstPrintableCharIndex(found_.substring(delta_));
                    }
                    found_ = found_.substring(delta_);
                    String declaringType_ = getDeclaringTypeInstr(found_,keyWords_);
                    boolean typeDeclaring_ = !declaringType_.trim().isEmpty();
                    String info_;
                    int realTypeOffset_;
                    if (finalLocalVar_) {
                        realTypeOffset_ = instructionRealLocation_ + deltaAfter_;
                    } else {
                        realTypeOffset_ = instructionLocation_;
                    }
                    if (typeDeclaring_) {
                        int varNameOffset_ = instructionRealLocation_ + delta_;
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
                        br_ = new DeclareVariable(new OffsetBooleanInfo(instructionLocation_, finalLocalVar_), new OffsetStringInfo(realTypeOffset_, declaringType_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                        inst_ = info_;
                    }
                    br_ = new Line(new OffsetStringInfo(afterDeclareOffset_, inst_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                    currentParent_.appendChild(br_);
                }
            } else {
                br_ = bl_;
            }
            boolean switchPart_ = false;
            boolean emptySwitchPart_ = false;
            if (options_.getSuffixVar() == VariableSuffix.NONE) {
                if (currentParent_ instanceof SwitchBlock) {
                    if (_currentChar == suffix_) {
                        String next_ = _file.substring(i_ + 1).trim();
                        if (next_.indexOf(BEGIN_BLOCK, 0) == 0) {
                            switchPart_ = true;
                        } else {
                            int c_ = afterComments(_file, i_ + 1);
                            if (c_ < 0) {
                                badIndexes_.add(_file.length());
                                return null;
                            }
                            next_ = _file.substring(c_);
                            if (ContextEl.startsWithKeyWord(next_, keyWordCase_)) {
                                emptySwitchPart_ = true;
                            }
                            if (ContextEl.startsWithKeyWord(next_, keyWordDefault_)) {
                                emptySwitchPart_ = true;
                            }
                        }
                    }
                }
            }
            if (!emptySwitchPart_ && br_ instanceof BracedBlock) {
                if (switchPart_) {
                    i_ = skipWhitespace(i_ + 1, _file, enabledSpaces_);
                    _braces.add(i_);
                    currentParent_ = (BracedBlock) br_;
                } else if (_currentChar != endLine_) {
                    currentParent_ = (BracedBlock) br_;
                }
            }
        } else {
            //currentChar_ == END_BLOCK
            if (options_.getSuffixVar() == VariableSuffix.NONE) {
                if (currentParent_ instanceof SwitchPartBlock) {
                    if (!_bracedSwitchPart.getVal((SwitchPartBlock) currentParent_)) {
                        currentParent_ = currentParent_.getParent();
                    }
                }
            }
            if (!trimmedInstruction_.isEmpty()) {
                badIndexes_.add(_nextIndex);
            }
            if (currentParent_ instanceof InnerTypeOrElement) {
                enableByEndLine_ = true;
            }
            currentParent_ = currentParent_.getParent();
        }
        _instruction.delete(0, _instruction.length());
        after_.setIndex(i_);
        after_.setParent(currentParent_);
        after_.setEnabledEnumHeader(enableByEndLine_);
        return after_;
    }
    private static RootBlock processTypeHeader(ContextEl _context, InputTypeCreation _input,
                                               boolean _defStatic, String _file,
                                               int _instructionLocation, int _instructionRealLocation, String _found,
                                               EnablingSpaces _enLoc,
                                               BracedBlock _currentParent, String _trimmedInstruction,
                                               AccessEnum _defAccess) {
        //Inner types
        KeyWords keyWords_ = _context.getKeyWords();
        AccessEnum accessFct_ = _defAccess;
        String word_ = EMPTY_STRING;
        int trFound_ = StringList.getFirstPrintableCharIndex(_found);
        String keyWordPackage_ = keyWords_.getKeyWordPackage();
        String keyWordPrivate_ = keyWords_.getKeyWordPrivate();
        String keyWordProtected_ = keyWords_.getKeyWordProtected();
        String keyWordPublic_ = keyWords_.getKeyWordPublic();
        String trimmedInstruction_ = _trimmedInstruction;
        Ints annotationsIndexes_ = new Ints();
        StringList annotations_ = new StringList();
        int typeOffset_ = _instructionLocation;
        if (trimmedInstruction_.charAt(0) == ANNOT) {
            ParsedAnnotations par_ = new ParsedAnnotations(trimmedInstruction_, _instructionLocation);
            par_.parse();
            annotationsIndexes_ = par_.getAnnotationsIndexes();
            annotations_ = par_.getAnnotations();
            trimmedInstruction_ = par_.getAfter();
            typeOffset_ = par_.getIndex();
        }
        if (ContextEl.startsWithKeyWord(trimmedInstruction_,keyWordPrivate_)) {
            accessFct_ = AccessEnum.PRIVATE;
            word_ = keyWordPrivate_;
        } else if (ContextEl.startsWithKeyWord(trimmedInstruction_,keyWordPackage_)) {
            accessFct_ = AccessEnum.PACKAGE;
            word_ = keyWordPackage_;
        } else if (ContextEl.startsWithKeyWord(trimmedInstruction_,keyWordProtected_)) {
            accessFct_ = AccessEnum.PROTECTED;
            word_ = keyWordProtected_;
        } else if (ContextEl.startsWithKeyWord(trimmedInstruction_,keyWordPublic_)) {
            accessFct_ = AccessEnum.PUBLIC;
            word_ = keyWordPublic_;
        }
        String afterAccess_ = trimmedInstruction_.substring(word_.length());
        int locIndex_ = typeOffset_ + word_.length();
        locIndex_ += StringList.getFirstPrintableCharIndex(afterAccess_);
        EnablingSpaces enLoc_ = new EnablingSpaces();
        enLoc_.setBegin(_enLoc.getBegin());
        enLoc_.setCheckTabs(_enLoc.isCheckTabs());
        enLoc_.setEnabledSpace(_enLoc.isEnabledSpace());
        enLoc_.setEnabledTab(_enLoc.isEnabledTab());
        enLoc_.setEnd(_enLoc.getEnd());
        enLoc_.setFile(_enLoc.getFile());
        enLoc_.setOk(_enLoc.isOk());
        enLoc_.setOnlySpacesLine(_enLoc.isOnlySpacesLine());
        Ints badIndexes_ = _input.getBadIndexes();
        boolean staticType_ = _defStatic;
        boolean abstractType_ = false;
        boolean finalType_ = false;
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordAnnotation_ = keyWords_.getKeyWordAnnotation();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordEnum_ = keyWords_.getKeyWordEnum();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordInterface_ = keyWords_.getKeyWordInterface();
        String keyWordInterfaces_ = keyWords_.getKeyWordInterfaces();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String beforeQu_ = _file.substring(locIndex_);
        if (ContextEl.startsWithKeyWord(beforeQu_,keyWordAbstract_)) {
            abstractType_ = true;
            locIndex_ = incrementRowCol(locIndex_, keyWordAbstract_.length(), _file, enLoc_);
            locIndex_ = skipWhitespace(locIndex_, _file, enLoc_);
        }
        if (!_defStatic) {
            beforeQu_ = _file.substring(locIndex_);
            if (ContextEl.startsWithKeyWord(beforeQu_,keyWordStatic_)) {
                staticType_ = true;
                locIndex_ = incrementRowCol(locIndex_, keyWordStatic_.length(), _file, enLoc_);
                locIndex_ = skipWhitespace(locIndex_, _file, enLoc_);
            }
        }
        beforeQu_ = _file.substring(locIndex_);
        if (ContextEl.startsWithKeyWord(beforeQu_,keyWordFinal_)) {
            finalType_ = true;
            locIndex_ = incrementRowCol(locIndex_, keyWordFinal_.length(), _file, enLoc_);
            locIndex_ = skipWhitespace(locIndex_, _file, enLoc_);
        }
        String type_;
        int categoryOffset_ = locIndex_;
        String infoModifiers_ = _file.substring(locIndex_);
        if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordClass_)) {
            type_ = keyWordClass_;
            locIndex_ = incrementRowCol(locIndex_, keyWordClass_.length(), _file, enLoc_);
        } else if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordEnum_)) {
            type_ = keyWordEnum_;
            locIndex_ = incrementRowCol(locIndex_, keyWordEnum_.length(), _file, enLoc_);
        } else if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordInterface_)) {
            type_ = keyWordInterface_;
            locIndex_ = incrementRowCol(locIndex_, keyWordInterface_.length(), _file, enLoc_);
        } else {
            type_ = keyWordAnnotation_;
            locIndex_ = incrementRowCol(locIndex_, keyWordAnnotation_.length(), _file, enLoc_);
        }
        locIndex_ = skipWhitespace(locIndex_, _file, enLoc_);

        return tryBuiltTypeWithInfos(_file, _instructionLocation, _instructionRealLocation, _currentParent, accessFct_, trFound_, annotationsIndexes_, annotations_, typeOffset_, locIndex_, enLoc_, badIndexes_, staticType_, abstractType_, finalType_, keyWordClass_, keyWordEnum_, keyWordInterface_, keyWordInterfaces_, type_, categoryOffset_);
    }

    private static RootBlock tryBuiltTypeWithInfos(String _file, int _instructionLocation, int _instructionRealLocation, BracedBlock _currentParent, AccessEnum _accessFct, int _trFound,
                                                   Ints _annotationsIndexes, StringList _annotations, int _typeOffset, int _locIndex, EnablingSpaces _enLoc, Ints _badIndexes,
                                                   boolean _staticType, boolean _abstractType, boolean _finalType, String _keyWordClass, String _keyWordEnum, String _keyWordInterface, String _keyWordInterfaces, String _type,
                                                   int _categoryOffset) {
        ParsedImportedTypes p_ = new ParsedImportedTypes(_locIndex, _file, _badIndexes, _enLoc);
        StringList importedTypes_ = p_.getImportedTypes();
        Ints offsetsImports_ = p_.getOffsetsImports();
        int locIndex_ = p_.getNextIndex();
        //insert interfaces static initialization for class and enums
        StringList staticInitInterfaces_ = new StringList();
        Ints staticInitInterfacesOffset_ = new Ints();
        if (ContextEl.startsWithKeyWord(_file.substring(locIndex_), _keyWordInterfaces)) {
            int begin_ = _file.indexOf(BEGIN_CALLING, locIndex_);
            if (begin_ < 0) {
                //ERROR
                _badIndexes.add(locIndex_);
                return null;
            }
            int end_ = _file.indexOf(END_CALLING, begin_);
            if (end_ < 0) {
                //ERROR
                _badIndexes.add(locIndex_);
                return null;
            }
            int interfaceOffest_ = begin_ + 1;
            String interfacesInfo_ = _file.substring(begin_ + 1, end_);
            for (int i = begin_ + 1; i < end_; i++) {
                updateAllowedSpaces(i, _file, _enLoc);
            }
            for (String p: StringList.splitChars(interfacesInfo_, SEP_CALLING)) {
                staticInitInterfaces_.add(p);
                staticInitInterfacesOffset_.add(interfaceOffest_);
                interfaceOffest_ += p.length() + 1;
            }
            locIndex_ = end_ + 1;
        }
        return tryBuildType(_file, _instructionLocation, _instructionRealLocation, _currentParent, _accessFct, _trFound, _annotationsIndexes, _annotations, _typeOffset, locIndex_, _enLoc, _staticType, _abstractType, _finalType, _keyWordClass, _keyWordEnum, _keyWordInterface, _type, _categoryOffset, importedTypes_, offsetsImports_, staticInitInterfaces_, staticInitInterfacesOffset_);
    }

    private static RootBlock tryBuildType(String _file, int _instructionLocation, int _instructionRealLocation, BracedBlock _currentParent,
                                          AccessEnum _accessFct, int _trFound, Ints _annotationsIndexes, StringList _annotations, int _typeOffset, int _locIndex,
                                          EnablingSpaces _enLoc, boolean _staticType, boolean _abstractType, boolean _finalType,
                                          String _keyWordClass, String _keyWordEnum, String _keyWordInterface, String _type, int _categoryOffset, StringList _importedTypes,
                                          Ints _offsetsImports, StringList _staticInitInterfaces, Ints _staticInitInterfacesOffset) {
        int locIndex_ = skipWhitespace(_locIndex, _file, _enLoc);
        StringBuilder str_ = new StringBuilder();
        IntTreeMap< String> superTypes_ = new IntTreeMap< String>();
        StringBuilder typeNamePref_ = new StringBuilder();
        StringBuilder templateDef_ = new StringBuilder();
        int nbOpened_ = 0;
        boolean foundInherit_ = false;
        int beginDefinition_ = locIndex_;
        int inheritIndex_ = -1;
        while (true) {
            char locChar_ = _file.charAt(locIndex_);
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
                locIndex_ = incrementRowCol(locIndex_, _file, _enLoc);
                inheritIndex_ = locIndex_;
                continue;
            }
            if (locChar_ == BEGIN_BLOCK) {
                break;
            }
            if (foundInherit_) {
                str_.append(locChar_);
            }
            locIndex_ = incrementRowCol(locIndex_, _file, _enLoc);
        }
        if (foundInherit_) {
            superTypes_.put(inheritIndex_, str_.toString());
        }
        incrementRowCol(locIndex_, _file, _enLoc);
        RootBlock typeBlock_;
        String tempDef_ = templateDef_.toString();
        String typeName_ = typeNamePref_.toString();
        String packageName_ = EMPTY_STRING;

        if (StringList.quickEq(_type, _keyWordEnum)) {
            typeBlock_ = new EnumBlock(beginDefinition_, _categoryOffset, typeName_, packageName_, new OffsetAccessInfo(_typeOffset - 1, _accessFct) , tempDef_, superTypes_, new OffsetsBlock(_instructionRealLocation + _trFound, _instructionLocation + _trFound));
        } else if (StringList.quickEq(_type, _keyWordClass)) {
            typeBlock_ = new ClassBlock(beginDefinition_, _categoryOffset, typeName_, packageName_, new OffsetAccessInfo(_typeOffset - 1, _accessFct), tempDef_, superTypes_, _finalType, _abstractType, _staticType, new OffsetsBlock(_instructionRealLocation + _trFound, _instructionLocation + _trFound));
        } else if (StringList.quickEq(_type, _keyWordInterface)) {
            typeBlock_ = new InterfaceBlock(beginDefinition_, _categoryOffset, typeName_, packageName_, new OffsetAccessInfo(_typeOffset - 1, _accessFct) , tempDef_, superTypes_, _staticType, new OffsetsBlock(_instructionRealLocation + _trFound, _instructionLocation + _trFound));
        } else {
            typeBlock_ = new AnnotationBlock(beginDefinition_, _categoryOffset, typeName_, packageName_, new OffsetAccessInfo(_typeOffset - 1, _accessFct) , tempDef_, superTypes_, new OffsetsBlock(_instructionRealLocation + _trFound, _instructionLocation + _trFound));
        }
        typeBlock_.getImports().addAllElts(_importedTypes);
        typeBlock_.getImportsOffset().addAllElts(_offsetsImports);
        typeBlock_.getStaticInitInterfaces().addAllElts(_staticInitInterfaces);
        typeBlock_.getStaticInitInterfacesOffset().addAllElts(_staticInitInterfacesOffset);
        typeBlock_.getAnnotations().addAllElts(_annotations);
        typeBlock_.getAnnotationsIndexes().addAllElts(_annotationsIndexes);
        BracedBlock br_ = typeBlock_;
        _currentParent.appendChild(br_);
        return typeBlock_;
    }

    private static Block processTypeMember(ContextEl _context,
                                           StringBuilder _instruction, int _instructionLocation, int _i, BracedBlock _currentParent) {
        int instructionLocation_ = _instructionLocation;
        String found_ = _instruction.toString();
        String trimmedInstruction_ = found_.trim();
        int instructionRealLocation_ = instructionLocation_;
        instructionLocation_ += StringList.getFirstPrintableCharIndex(found_);
        Block br_;
        AccessEnum accessFct_ = AccessEnum.PACKAGE;
        String word_ = EMPTY_STRING;
        int trFound_ = StringList.getFirstPrintableCharIndex(found_);
        int accessOffest_ = trFound_ + _i - found_.length();
        Ints annotationsIndexes_ = new Ints();
        StringList annotations_ = new StringList();
        int deltaAccess_ = 0;
        KeyWords keyWords_ = _context.getKeyWords();
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordNormal_ = keyWords_.getKeyWordNormal();
        String keyWordPackage_ = keyWords_.getKeyWordPackage();
        String keyWordPrivate_ = keyWords_.getKeyWordPrivate();
        String keyWordProtected_ = keyWords_.getKeyWordProtected();
        String keyWordPublic_ = keyWords_.getKeyWordPublic();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        if (found_.trim().charAt(0) == ANNOT) {
            ParsedAnnotations par_ = new ParsedAnnotations(trimmedInstruction_, accessOffest_);
            par_.parse();
            annotationsIndexes_ = par_.getAnnotationsIndexes();
            annotations_ = par_.getAnnotations();
            trimmedInstruction_ = par_.getAfter();
            accessOffest_ = par_.getIndex();
            deltaAccess_ = accessOffest_ - (trFound_ + _i - found_.length());
        }
        if (ContextEl.startsWithKeyWord(trimmedInstruction_,keyWordPrivate_)) {
            accessFct_ = AccessEnum.PRIVATE;
            word_ = keyWordPrivate_;
        } else if (ContextEl.startsWithKeyWord(trimmedInstruction_,keyWordPackage_)) {
            word_ = keyWordPackage_;
        } else if (ContextEl.startsWithKeyWord(trimmedInstruction_,keyWordProtected_)) {
            accessFct_ = AccessEnum.PROTECTED;
            word_ = keyWordProtected_;
        } else if (ContextEl.startsWithKeyWord(trimmedInstruction_,keyWordPublic_)) {
            accessFct_ = AccessEnum.PUBLIC;
            word_ = keyWordPublic_;
        }
        String afterAccess_ = trimmedInstruction_.substring(word_.length());
        String trimmedAfterAccess_ = afterAccess_.trim();
        String infoModifiers_ = trimmedAfterAccess_;
        boolean field_ = false;
        if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordStatic_)) {
            String otherModifier_;
            otherModifier_ = keyWordStatic_;
            int lenLoc_ = otherModifier_.length();
            String sub_ = infoModifiers_.substring(lenLoc_);
            int delta_ = StringList.getFirstPrintableCharIndex(sub_);
            infoModifiers_ = sub_.substring(delta_);
            if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordFinal_)) {
                field_ = true;
            }
        }
        boolean ctor_ = false;
        if (!field_) {
            if (trimmedAfterAccess_.startsWith("(")) {
                ctor_ = true;
            } else {
                String name_ = ((RootBlock) _currentParent).getName();
                if (ContextEl.startsWithKeyWord(trimmedAfterAccess_,name_)){
                    String after_ = trimmedAfterAccess_.substring(name_.length()).trim();
                    if (after_.startsWith("(")) {
                        ctor_ = true;
                    }
                }
            }
        }
        boolean meth_ = false;
        if (!field_ && !ctor_) {
            infoModifiers_ = trimmedAfterAccess_;
            String otherModifier_;
            if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordNormal_)) {
                otherModifier_ = keyWordNormal_;
                int lenLoc_ = otherModifier_.length();
                String sub_ = infoModifiers_.substring(lenLoc_);
                int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                infoModifiers_ = sub_.substring(delta_);
            } else if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordAbstract_)) {
                otherModifier_ = keyWordAbstract_;
                int lenLoc_ = otherModifier_.length();
                String sub_ = infoModifiers_.substring(lenLoc_);
                int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                infoModifiers_ = sub_.substring(delta_);
            } else if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordStatic_)) {
                otherModifier_ = keyWordStatic_;
                int lenLoc_ = otherModifier_.length();
                String sub_ = infoModifiers_.substring(lenLoc_);
                int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                infoModifiers_ = sub_.substring(delta_);
            } else if (ContextEl.startsWithKeyWord(infoModifiers_,keyWordFinal_)) {
                otherModifier_ = keyWordFinal_;
                int lenLoc_ = otherModifier_.length();
                String sub_ = infoModifiers_.substring(lenLoc_);
                int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                infoModifiers_ = sub_.substring(delta_);
            }
            String typeStr_ = getDeclaringTypeBlock(infoModifiers_);
            infoModifiers_ = infoModifiers_.substring(typeStr_.length());
            int first_ = StringList.getFirstPrintableCharIndex(infoModifiers_);
            if (first_ < 0) {
                return null;
            }
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
            if (_currentParent instanceof InterfaceBlock) {
                if (word_.isEmpty()) {
                    accessFct_ = AccessEnum.PUBLIC;
                }
            }

            //constructors or methods or types
            int modifierOffest_ = accessOffest_ + word_.length();
            modifierOffest_ += StringList.getFirstPrintableCharIndex(afterAccess_);
            String info_ = afterAccess_.trim();
            int methodNameOffest_ = -1;
            int typeOffset_ = -1;
            int paramOffest_;
            String methodName_ = EMPTY_STRING;
            String declaringType_ = EMPTY_STRING;
            String modifier_ = EMPTY_STRING;
            String prefModifier_ = EMPTY_STRING;
            if (meth_) {
                if (ContextEl.startsWithKeyWord(info_,keyWordNormal_)) {
                    modifier_ = keyWordNormal_;
                    prefModifier_ = modifier_;
                } else if (ContextEl.startsWithKeyWord(info_,keyWordAbstract_)) {
                    modifier_ = keyWordAbstract_;
                    prefModifier_ = modifier_;
                } else if (ContextEl.startsWithKeyWord(info_,keyWordStatic_)) {
                    modifier_ = keyWordStatic_;
                    prefModifier_ = modifier_;
                } else if (ContextEl.startsWithKeyWord(info_,keyWordFinal_)) {
                    modifier_ = keyWordFinal_;
                    prefModifier_ = modifier_;
                }
                String afterModifier_ = info_.substring(prefModifier_.length());
                typeOffset_ = modifierOffest_ + prefModifier_.length();
                if (modifier_.isEmpty()) {
                    if (_currentParent instanceof InterfaceBlock) {
                        modifier_ = keyWordAbstract_;
                    } else {
                        modifier_ = keyWordNormal_;
                    }
                }
                typeOffset_ += StringList.getFirstPrintableCharIndex(afterModifier_);
                info_ = afterModifier_.trim();
                declaringType_ = getDeclaringTypeBlock(info_);
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
            } else {
                paramOffest_ = modifierOffest_;
                int indexLeftPar_ = info_.indexOf(BEGIN_CALLING);
                paramOffest_ += indexLeftPar_ + 1;
                String after_ = info_.substring(indexLeftPar_ + 1);
                paramOffest_ += StringList.getFirstPrintableCharIndex(after_);
                info_ = after_.trim();
            }
            Ints offestsTypes_ = new Ints();
            Ints offestsParams_ = new Ints();
            StringList parametersType_ = new StringList();
            StringList parametersName_ = new StringList();
            CustList<Ints> annotationsIndexesParams_ = new CustList<Ints>();
            CustList<StringList> annotationsParams_ = new CustList<StringList>();
            while (true) {
                if (info_.indexOf(END_CALLING) == 0) {
                    break;
                }
                Ints annotationsIndexesParam_ = new Ints();
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
                String paramType_ = getDeclaringParamType(info_);
                parametersType_.add(paramType_.trim());
                String afterParamType_ = info_.substring(paramType_.length());
                info_ = afterParamType_.trim();
                int call_ = info_.indexOf(SEP_CALLING);
                if (call_ < 0) {
                    call_ = info_.indexOf(END_CALLING);
                }
                int off_ = StringList.getFirstPrintableCharIndex(afterParamType_);
                offestsParams_.add(paramOffest_ + paramType_.length() + off_);
                if (call_ < 0) {
                    return null;
                }
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
            if (meth_) {
                String retType_ = declaringType_.trim();
                String trimMeth_ = methodName_.trim();
                MethodKind kind_;
                OverridableBlock ov_;
                if (StringList.quickEq(trimMeth_,_context.getKeyWords().getKeyWordThis())) {
                    boolean get_ = !StringList.quickEq(retType_,_context.getStandards().getAliasVoid());
                    if (!get_) {
                        kind_ = MethodKind.SET_INDEX;
                        trimMeth_ = "[]=";
                    } else {
                        kind_ = MethodKind.GET_INDEX;
                        trimMeth_ = "[]";
                    }
                    ov_ = new OverridableBlock(_context, new OffsetAccessInfo(accessOffest_, accessFct_), new OffsetStringInfo(typeOffset_, retType_), new OffsetStringInfo(methodNameOffest_, trimMeth_), parametersType_, offestsTypes_, parametersName_, offestsParams_, new OffsetStringInfo(modifierOffest_, modifier_), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                } else {
                    kind_ = MethodKind.STD_METHOD;
                    ov_ = new OverridableBlock(_context, new OffsetAccessInfo(accessOffest_, accessFct_), new OffsetStringInfo(typeOffset_, retType_), new OffsetStringInfo(methodNameOffest_, trimMeth_), parametersType_, offestsTypes_, parametersName_, offestsParams_, new OffsetStringInfo(modifierOffest_, modifier_), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                }
                ov_.setKind(kind_);
                br_ = ov_;
            } else {
                br_ = new ConstructorBlock(new OffsetAccessInfo(accessOffest_, accessFct_), new OffsetStringInfo(accessOffest_, EMPTY_STRING), new OffsetStringInfo(accessOffest_, EMPTY_STRING), parametersType_, offestsTypes_, parametersName_, offestsParams_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
            }
            ((NamedFunctionBlock)br_).getAnnotationsParams().addAllElts(annotationsParams_);
            ((NamedFunctionBlock)br_).getAnnotationsIndexesParams().addAllElts(annotationsIndexesParams_);
            ((NamedFunctionBlock)br_).getAnnotations().addAllElts(annotations_);
            ((NamedFunctionBlock)br_).getAnnotationsIndexes().addAllElts(annotationsIndexes_);
            _currentParent.appendChild(br_);
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
            if (ContextEl.startsWithKeyWord(info_, keyWordStatic_)) {
                staticOffest_ = _i - found_.length() + delta_;
                static_ = true;
                String afterStatic_ = info_.substring(keyWordStatic_.length());
                delta_ += keyWordStatic_.length();
                delta_ += StringList.getFirstPrintableCharIndex(afterStatic_);
                info_ = afterStatic_.trim();
            }
            if (ContextEl.startsWithKeyWord(info_,keyWordFinal_)) {
                finalOffest_ = _i - found_.length() + delta_;
                final_ = true;
                String afterFinal_ = info_.substring(keyWordFinal_.length());
                delta_ += keyWordFinal_.length();
                delta_ += StringList.getFirstPrintableCharIndex(afterFinal_);
                info_ = afterFinal_.trim();
            }
            int typeOffest_ = _i - found_.length() + delta_;
            String declaringType_ = getDeclaringTypeBlock(info_);
            String afterType_ = info_.substring(declaringType_.length());
            int fieldNameOffest_ = StringList.getFirstPrintableCharIndex(afterType_) +declaringType_.length() + typeOffest_;
            br_ = new FieldBlock(
                    new OffsetAccessInfo(accessOffest_, accessFct_),
                    new OffsetBooleanInfo(staticOffest_, static_), new OffsetBooleanInfo(finalOffest_, final_),
                    new OffsetStringInfo(typeOffest_,declaringType_.trim()),
                    new OffsetStringInfo(fieldNameOffest_, afterType_.trim()),
                    new OffsetsBlock(instructionRealLocation_, instructionLocation_));
            ((FieldBlock)br_).getAnnotations().addAllElts(annotations_);
            ((FieldBlock)br_).getAnnotationsIndexes().addAllElts(annotationsIndexes_);
            _currentParent.appendChild(br_);
        }
        return br_;
    }
    private static Block processInstructionBlock(ContextEl _context, Ints _badIndexes,String _file,
                                                 IdMap<SwitchPartBlock, Boolean> _bracedSwitchPart, int _instructionLocation,
                                                 int _instructionRealLocation, int _i, BracedBlock _currentParent, String _trimmedInstruction) {
        char endLine_ = _context.getOptions().getEndLine();
        char suffix_ = _context.getOptions().getSuffix();
        Options options_ = _context.getOptions();
        KeyWords keyWords_ = _context.getKeyWords();
        String keyWordBreak_ = keyWords_.getKeyWordBreak();
        String keyWordCase_ = keyWords_.getKeyWordCase();
        String keyWordCatch_ = keyWords_.getKeyWordCatch();
        String keyWordContinue_ = keyWords_.getKeyWordContinue();
        String keyWordDefault_ = keyWords_.getKeyWordDefault();
        String keyWordDo_ = keyWords_.getKeyWordDo();
        String keyWordElse_ = keyWords_.getKeyWordElse();
        String keyWordElseif_ = keyWords_.getKeyWordElseif();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordFinally_ = keyWords_.getKeyWordFinally();
        String keyWordFor_ = keyWords_.getKeyWordFor();
        String keyWordForeach_ = keyWords_.getKeyWordForeach();
        String keyWordIf_ = keyWords_.getKeyWordIf();
        String keyWordIter_ = keyWords_.getKeyWordIter();
        String keyWordReturn_ = keyWords_.getKeyWordReturn();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordSwitch_ = keyWords_.getKeyWordSwitch();
        String keyWordThrow_ = keyWords_.getKeyWordThrow();
        String keyWordTry_ = keyWords_.getKeyWordTry();
        String keyWordWhile_ = keyWords_.getKeyWordWhile();
        Block br_ = null;
        if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordBreak_)) {
            String exp_ = _trimmedInstruction.substring(keyWordBreak_.length());
            String label_ = exp_.trim();
            int conditionOffest_ = _instructionLocation + keyWordBreak_.length();
            int lastPar_ = StringList.getFirstPrintableCharIndex(exp_);
            if (!exp_.isEmpty()) {
                lastPar_--;
            }
            int labelOff_ = conditionOffest_ + lastPar_+ 1;
            br_ = new BreakBlock(new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordContinue_)) {
            String exp_ = _trimmedInstruction.substring(keyWordContinue_.length());
            String label_ = exp_.trim();
            int conditionOffest_ = _instructionLocation + keyWordContinue_.length();
            int lastPar_ = StringList.getFirstPrintableCharIndex(exp_);
            if (!exp_.isEmpty()) {
                lastPar_--;
            }
            int labelOff_ = conditionOffest_ + lastPar_+ 1;
            br_ = new ContinueBlock(new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordReturn_)) {
            String exp_ = _trimmedInstruction.substring(keyWordReturn_.length());
            int expressionOffest_ = _instructionLocation + keyWordReturn_.length();
            if (!exp_.trim().isEmpty()) {
                expressionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            }
            br_ = new ReturnMehod(new OffsetStringInfo(expressionOffest_,exp_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordThrow_)) {
            String exp_ = _trimmedInstruction.substring(keyWordThrow_.length());
            int expressionOffest_ = _instructionLocation + keyWordThrow_.length();
            if (!exp_.trim().isEmpty()) {
                expressionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            }
            br_ = new Throwing(new OffsetStringInfo(expressionOffest_,exp_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordCase_)) {
            String exp_ = _trimmedInstruction.substring(keyWordCase_.length());
            int valueOffest_ = _instructionLocation + keyWordCase_.length();
            int indexLeftPar_ = exp_.indexOf(BEGIN_CALLING);
            if (indexLeftPar_ > -1) {
                valueOffest_ += indexLeftPar_ + 1;
                int endIndex_ = exp_.lastIndexOf(END_CALLING);
                if (endIndex_ < 0) {
                    _badIndexes.add(_i);
                    return null;
                }
                exp_ = exp_.substring(indexLeftPar_+1, endIndex_);
                valueOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            } else {
                valueOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            }
            CaseCondition caseCond_ = new CaseCondition(
                    new OffsetStringInfo(valueOffest_, exp_.trim()),
                    new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            //if next after i starts with brace or not
            _bracedSwitchPart.put(caseCond_, _file.substring(_i +1).trim().startsWith(String.valueOf(BEGIN_BLOCK))||_file.charAt(_i) == BEGIN_BLOCK);
            br_ = caseCond_;
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordDefault_)) {
            DefaultCondition defCond_ = new DefaultCondition(
                    new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _bracedSwitchPart.put(defCond_, _file.substring(_i +1).trim().startsWith(String.valueOf(BEGIN_BLOCK))||_file.charAt(_i) == BEGIN_BLOCK);
            br_ = defCond_;
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordWhile_)) {
            Block child_ = _currentParent.getFirstChild();
            if (child_ != null) {
                while (child_.getNextSibling() != null) {
                    child_ = child_.getNextSibling();
                }
            }
            String exp_ = _trimmedInstruction.substring(keyWordWhile_.length());
            int conditionOffest_ = _instructionLocation + keyWordWhile_.length();
            int lastPar_ = exp_.lastIndexOf(END_CALLING);
            int labelOff_ = conditionOffest_ + lastPar_+ 1;
            int beg_ = exp_.indexOf(BEGIN_CALLING);
            if (beg_ < 0 || lastPar_ < beg_ + 1) {
                _badIndexes.add(_i);
                return null;
            }
            conditionOffest_ += beg_ +1;
            String label_ = exp_;
            exp_ = exp_.substring(beg_ +1, lastPar_);
            conditionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            if (child_ instanceof DoBlock) {
                br_ = new DoWhileCondition(new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            } else {
                label_ = label_.substring(lastPar_ + 1);
                if (!label_.isEmpty()) {
                    labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                }
                br_ = new WhileCondition(new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            }
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordCatch_)) {
            String info_ = _trimmedInstruction.substring(keyWordCatch_.length());
            int leftPar_ = info_.indexOf(BEGIN_CALLING);
            if (leftPar_ > -1) {
                int typeOffset_ = _instructionLocation + leftPar_+1;
                info_ = info_.substring(leftPar_+1);
                String declaringType_ = getDeclaringTypeBlock(info_);
                typeOffset_ += StringList.getFirstPrintableCharIndex(declaringType_);
                int variableOffset_ = typeOffset_ + declaringType_.length();
                info_ = info_.substring(declaringType_.length());
                variableOffset_ += StringList.getFirstPrintableCharIndex(info_);
                int endIndex_ = info_.indexOf(END_CALLING);
                if (endIndex_ < 0) {
                    _badIndexes.add(_i);
                    return null;
                }
                String variable_ = info_.substring(0, endIndex_);
                br_ = new CatchEval(new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(variableOffset_,variable_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            } else {
                br_ = new NullCatchEval(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            }
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordIf_)) {
            String exp_ = _trimmedInstruction.substring(keyWordIf_.length());
            int conditionOffest_ = _instructionLocation + keyWordIf_.length();
            int lastPar_ = exp_.lastIndexOf(END_CALLING);
            int labelOff_ = conditionOffest_ + lastPar_+ 1;
            int beg_ = exp_.indexOf(BEGIN_CALLING);
            if (beg_ < 0 || lastPar_ < beg_ + 1) {
                _badIndexes.add(_i);
                return null;
            }
            conditionOffest_ += beg_ +1;
            String label_ = exp_;
            exp_ = exp_.substring(beg_ +1,lastPar_);
            conditionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            label_ = label_.substring(lastPar_ + 1);
            if (!label_.isEmpty()) {
                labelOff_ += StringList.getFirstPrintableCharIndex(label_);
            }
            br_ = new IfCondition(new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordElseif_)) {
            String exp_ = _trimmedInstruction.substring(keyWordElseif_.length());
            int conditionOffest_ = _instructionLocation + keyWordElseif_.length();
            int beg_ = exp_.indexOf(BEGIN_CALLING);
            conditionOffest_ += beg_ +1;
            int lastPar_ = exp_.lastIndexOf(END_CALLING);
            if (beg_ < 0 || lastPar_ < beg_ + 1) {
                _badIndexes.add(_i);
                return null;
            }
            exp_ = exp_.substring(beg_ +1, lastPar_);
            conditionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            br_ = new ElseIfCondition(new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation),keyWordElseif_.length());
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordElse_)) {
            String afterElse_ = _trimmedInstruction.substring(keyWordElse_.length());
            String exp_ = afterElse_.trim();
            if (ContextEl.startsWithKeyWord(exp_,keyWordIf_)) {
                int deltaFirst_ = keyWordElse_.length();
                int firstPr_ = StringList.getFirstPrintableCharIndex(afterElse_);
                deltaFirst_ += firstPr_;
                deltaFirst_ += keyWordIf_.length();
                exp_ = exp_.substring(keyWordIf_.length());
                int conditionOffest_ = _instructionLocation;
                conditionOffest_ += keyWordElse_.length();
                conditionOffest_ += firstPr_;
                conditionOffest_ += keyWordIf_.length();
                int beg_ = exp_.indexOf(BEGIN_CALLING);
                conditionOffest_ += beg_ +1;
                int lastPar_ = exp_.lastIndexOf(END_CALLING);
                if (beg_ < 0 || lastPar_ < beg_ + 1) {
                    _badIndexes.add(_i);
                    return null;
                }
                exp_ = exp_.substring(beg_ +1, lastPar_);
                conditionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
                br_ = new ElseIfCondition(new OffsetStringInfo(conditionOffest_, exp_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation),deltaFirst_);
                _currentParent.appendChild(br_);
            } else {
                br_ = new ElseCondition(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                _currentParent.appendChild(br_);
            }
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordDo_)) {
            String exp_ = _trimmedInstruction.substring(keyWordDo_.length());
            String label_ = exp_.trim();
            int conditionOffest_ = _instructionLocation + keyWordDo_.length();
            int lastPar_ = StringList.getFirstPrintableCharIndex(exp_);
            if (!exp_.isEmpty()) {
                lastPar_--;
            }
            int labelOff_ = conditionOffest_ + lastPar_+ 1;
            br_ = new DoBlock(new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordFinally_)) {
            br_ = new FinallyEval(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordTry_)) {
            String exp_ = _trimmedInstruction.substring(keyWordTry_.length());
            String label_ = exp_.trim();
            int conditionOffest_ = _instructionLocation + keyWordTry_.length();
            int lastPar_ = StringList.getFirstPrintableCharIndex(exp_);
            if (!exp_.isEmpty()) {
                lastPar_--;
            }
            int labelOff_ = conditionOffest_ + lastPar_+ 1;
            br_ = new TryEval(new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordForeach_)) {
            String exp_ = _trimmedInstruction.substring(keyWordForeach_.length());
            int indexClassOffest_ = _instructionLocation + keyWordForeach_.length();
            int lastPar_ = exp_.lastIndexOf(END_CALLING);
            int labelOff_ = indexClassOffest_ + lastPar_+ 1;
            String label_ = exp_;
            label_ = label_.substring(lastPar_ + 1);
            if (!label_.isEmpty()) {
                labelOff_ += StringList.getFirstPrintableCharIndex(label_);
            }
            int typeOffset_ = _instructionLocation + _trimmedInstruction.indexOf(BEGIN_CALLING) + 1;
            if (!exp_.trim().isEmpty()) {
                indexClassOffest_ += StringList.getFirstPrintableCharIndex(exp_) + 1;
            }
            String indexClassName_ = EMPTY_STRING;
            if (exp_.trim().indexOf(BEGIN_ARRAY) == 0) {
                int endArr_ = exp_.indexOf(END_ARRAY);
                if (endArr_ < 0) {
                    _badIndexes.add(_i);
                    return null;
                }
                indexClassName_ = exp_.substring(0, endArr_);
                indexClassOffest_ += StringList.getFirstPrintableCharIndex(indexClassName_);
                exp_ = exp_.substring(endArr_ + 1);
            }
            String afterIndex_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1);
            typeOffset_ += StringList.getFirstPrintableCharIndex(afterIndex_);
            exp_ = afterIndex_;
            String declaringType_ = getDeclaringTypeBlock(exp_);
            int varOffset_ = typeOffset_ + declaringType_.length();
            exp_ = exp_.substring(declaringType_.length());
            int forBlocks_ = exp_.indexOf(FOR_BLOCKS);
            int endIndex_ = exp_.lastIndexOf(END_CALLING);
            if (forBlocks_ < 0 || endIndex_ < forBlocks_ + 1) {
                _badIndexes.add(_i);
                return null;
            }
            String variable_ = exp_.substring(0, forBlocks_);
            varOffset_ += StringList.getFirstPrintableCharIndex(variable_);
            int expOffset_ = varOffset_;
            expOffset_ += forBlocks_;
            exp_ = exp_.substring(forBlocks_ + 1, endIndex_);
            expOffset_ += StringList.getFirstPrintableCharIndex(exp_);
            String variableName_ = variable_.trim();
            LgNames stds_ = _context.getStandards();
            if (StringList.isDollarWord(variableName_)) {
                br_ = stds_.newForeachLoop(_context, new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(varOffset_, variableName_),
                        new OffsetStringInfo(expOffset_, exp_.trim()), new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                        new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            } else {
                int nextIndexVar_ = variableName_.indexOf(',');
                if (nextIndexVar_ < 0) {
                    _badIndexes.add(_i);
                    return null;
                }
                String firstVar_ = variableName_.substring(0, nextIndexVar_);
                String afterFirst_ = variableName_.substring(nextIndexVar_+1);
                String declaringTypeSec_ = getDeclaringTypeBlock(afterFirst_);
                int secType_ = varOffset_;
                secType_ += nextIndexVar_+1;
                int secVarOff_ = secType_;
                secType_ += StringList.getFirstPrintableCharIndex(declaringTypeSec_);
                secVarOff_ += declaringTypeSec_.length();
                String padSecVar_= afterFirst_.substring(declaringTypeSec_.length());
                secVarOff_ += StringList.getFirstPrintableCharIndex(padSecVar_);
                String secVar_ = padSecVar_.trim();
                br_ = new ForEachTable(_context,
                        new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(varOffset_, firstVar_),
                        new OffsetStringInfo(secType_, declaringTypeSec_.trim()), new OffsetStringInfo(secVarOff_, secVar_),
                        new OffsetStringInfo(expOffset_, exp_.trim()), new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                        new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            }
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordIter_)) {
            String exp_ = _trimmedInstruction.substring(keyWordIter_.length());
            int indexClassOffest_ = _instructionLocation + keyWordIter_.length();
            int lastPar_ = exp_.lastIndexOf(END_CALLING);
            int labelOff_ = indexClassOffest_ + lastPar_+ 1;
            String label_ = exp_;
            int typeOffset_ = _instructionLocation + _trimmedInstruction.indexOf(BEGIN_CALLING) + 1;
            if (!exp_.trim().isEmpty()) {
                indexClassOffest_ += StringList.getFirstPrintableCharIndex(exp_) + 1;
            }
            String indexClassName_ = EMPTY_STRING;
            if (exp_.trim().indexOf(BEGIN_ARRAY) == 0) {
                int endArr_ = exp_.indexOf(END_ARRAY);
                if (endArr_ < 0) {
                    _badIndexes.add(_i);
                    return null;
                }
                indexClassName_ = exp_.substring(0, endArr_);
                exp_ = exp_.substring(endArr_ + 1);
            }
            int begCall_ = exp_.indexOf(BEGIN_CALLING);
            if (begCall_ < 0) {
                _badIndexes.add(_i);
                return null;
            }
            int endIndex_ = exp_.lastIndexOf(END_CALLING);
            if (endIndex_ < begCall_ + 1) {
                _badIndexes.add(_i);
                return null;
            }
            exp_ = exp_.substring(begCall_ + 1, endIndex_);
            String declaringType_ = getDeclaringTypeBlock(exp_);
            typeOffset_ += StringList.getFirstPrintableCharIndex(exp_);
            int varOffset_ = typeOffset_ + declaringType_.length();
            exp_ = exp_.substring(declaringType_.length());
            int eqIndex_ = exp_.indexOf(PART_SEPARATOR);
            if (eqIndex_ < 0) {
                _badIndexes.add(_i);
                return null;
            }
            String variable_ = exp_.substring(0, eqIndex_);
            varOffset_ += StringList.getFirstPrintableCharIndex(variable_);
            exp_ = exp_.substring(eqIndex_ + 1);
            int nextElt_ = getIndex(exp_,endLine_);
            if (nextElt_ < 0) {
                _badIndexes.add(_i);
                return null;
            }
            int initOff_ = varOffset_ + nextElt_;
            String init_ = exp_.substring(0, nextElt_);
            initOff_ += StringList.getFirstPrintableCharIndex(init_);
            exp_ = exp_.substring(init_.length()+1);
            nextElt_ = getIndex(exp_, endLine_);
            if (nextElt_ < 0) {
                _badIndexes.add(_i);
                return null;
            }
            int toOff_ = initOff_ + nextElt_;
            boolean eq_ = false;
            String to_ = exp_.substring(0, nextElt_);
            toOff_ += StringList.getFirstPrintableCharIndex(to_);
            int expOff_ = toOff_ + nextElt_;
            int stepOff_ = expOff_ + 1;
            if (nextElt_ + 1 >= exp_.length()) {
                _badIndexes.add(_i);
                return null;
            }
            if (exp_.charAt(nextElt_ + 1) == endLine_) {
                eq_ = true;
                nextElt_++;
                stepOff_++;
            }
            exp_ = exp_.substring(nextElt_ + 1);
            String step_ = exp_;
            stepOff_ += StringList.getFirstPrintableCharIndex(step_);
            label_ = label_.substring(lastPar_ + 1);
            if (!label_.isEmpty()) {
                labelOff_ += StringList.getFirstPrintableCharIndex(label_);
            }
            br_ = new ForIterativeLoop(_context, new OffsetStringInfo(typeOffset_,declaringType_.trim()), new OffsetStringInfo(varOffset_,variable_.trim()),
                    new OffsetStringInfo(initOff_,init_.trim()), new OffsetStringInfo(toOff_,to_.trim()),  new OffsetBooleanInfo(expOff_, eq_) , new OffsetStringInfo(stepOff_,step_.trim()), new OffsetStringInfo(indexClassOffest_,indexClassName_.trim()),
                    new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordFor_)) {
            String exp_ = _trimmedInstruction.substring(keyWordFor_.length());
            int indexClassOffest_ = _instructionLocation + keyWordFor_.length();
            int lastPar_ = exp_.lastIndexOf(END_CALLING);
            int labelOff_ = indexClassOffest_ + lastPar_+ 1;
            String label_ = exp_;
            int typeOffset_ = _instructionLocation + _trimmedInstruction.indexOf(BEGIN_CALLING) + 1;
            if (!exp_.trim().isEmpty()) {
                indexClassOffest_ += StringList.getFirstPrintableCharIndex(exp_) + 1;
            }
            String indexClassName_ = EMPTY_STRING;
            if (exp_.trim().indexOf(BEGIN_ARRAY) == 0) {
                int endArr_ = exp_.indexOf(END_ARRAY);
                if (endArr_ < 0) {
                    _badIndexes.add(_i);
                    return null;
                }
                indexClassName_ = exp_.substring(0, endArr_);
                exp_ = exp_.substring(endArr_ + 1);
            }
            int begCall_ = exp_.indexOf(BEGIN_CALLING);
            if (begCall_ < 0) {
                _badIndexes.add(_i);
                return null;
            }
            int endCall_ = exp_.lastIndexOf(END_CALLING);
            if (endCall_ < begCall_ + 1) {
                _badIndexes.add(_i);
                return null;
            }
            exp_ = exp_.substring(begCall_ + 1, endCall_);
            boolean finalLocalVar_ = ContextEl.startsWithKeyWord(exp_.trim(), keyWordFinal_);
            int finalOffset_ = typeOffset_;
            int delta_;
            int deltaAfter_ = 0;
            if (finalLocalVar_) {
                deltaAfter_ = keyWordFinal_.length();
                delta_ = StringList.getFirstPrintableCharIndex(exp_) + deltaAfter_;
                deltaAfter_ = delta_;
                String afterDelta_ = exp_.substring(delta_);
                deltaAfter_ += StringList.getFirstPrintableCharIndex(afterDelta_);
            }
            typeOffset_ += deltaAfter_;
            exp_ = exp_.substring(deltaAfter_);
            String declaringType_ = getDeclaringTypeInstr(exp_,keyWords_);
            int initOff_ = typeOffset_ + declaringType_.length();
            exp_ = exp_.substring(declaringType_.length());
            boolean ok_ = false;
            if (options_.getSuffixVar() == VariableSuffix.NONE) {
                int nextElt_ = getIndex(exp_,suffix_);
                if (nextElt_ > -1) {
                    String init_ = exp_.substring(0, nextElt_);
                    initOff_ += StringList.getFirstPrintableCharIndex(init_);
                    exp_ = exp_.substring(init_.length()+1);
                    int varOffset_ = typeOffset_ + declaringType_.length();
                    varOffset_ += StringList.getFirstPrintableCharIndex(init_);
                    int expOffset_ = varOffset_;
                    expOffset_ += init_.length();
                    expOffset_ += StringList.getFirstPrintableCharIndex(exp_);
                    label_ = label_.substring(lastPar_ + 1);
                    if (!label_.isEmpty()) {
                        labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                    }
                    LgNames stds_ = _context.getStandards();
                    String variableName_ = init_.trim();
                    if (StringList.isDollarWord(variableName_)) {
                        br_ = stds_.newForeachLoop(_context, new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(varOffset_, variableName_),
                                new OffsetStringInfo(expOffset_, exp_.trim()), new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                                new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                    } else {
                        int nextIndexVar_ = variableName_.indexOf(',');
                        if (nextIndexVar_ < 0) {
                            _badIndexes.add(_i);
                            return null;
                        }
                        String firstVar_ = variableName_.substring(0, nextIndexVar_);
                        String afterFirst_ = variableName_.substring(nextIndexVar_+1);
                        String declaringTypeSec_ = getDeclaringTypeBlock(afterFirst_);
                        int secType_ = varOffset_;
                        secType_ += nextIndexVar_+1;
                        int secVarOff_ = secType_;
                        secType_ += StringList.getFirstPrintableCharIndex(declaringTypeSec_);
                        secVarOff_ += declaringTypeSec_.length();
                        String padSecVar_= afterFirst_.substring(declaringTypeSec_.length());
                        secVarOff_ += StringList.getFirstPrintableCharIndex(padSecVar_);
                        String secVar_ = padSecVar_.trim();
                        br_ = new ForEachTable(_context,
                                new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(varOffset_, firstVar_),
                                new OffsetStringInfo(secType_, declaringTypeSec_.trim()), new OffsetStringInfo(secVarOff_, secVar_),
                                new OffsetStringInfo(expOffset_, exp_.trim()), new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                                new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                    }
                    _currentParent.appendChild(br_);
                    ok_ = true;
                }
            }
            if (!ok_) {
                int nextElt_ = getIndex(exp_,endLine_);
                if (nextElt_ < 0) {
                    _badIndexes.add(_i);
                    return null;
                }
                String init_ = exp_.substring(0, nextElt_);
                initOff_ += StringList.getFirstPrintableCharIndex(init_);
                exp_ = exp_.substring(init_.length()+1);
                int toOff_ = initOff_ + nextElt_;
                nextElt_ = getIndex(exp_,endLine_);
                if (nextElt_ == -1) {
                    int varOffset_ = typeOffset_ + declaringType_.length();
                    varOffset_ += StringList.getFirstPrintableCharIndex(init_);
                    int expOffset_ = varOffset_;
                    expOffset_ += init_.length();
                    expOffset_ += StringList.getFirstPrintableCharIndex(exp_);
                    label_ = label_.substring(lastPar_ + 1);
                    if (!label_.isEmpty()) {
                        labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                    }
                    LgNames stds_ = _context.getStandards();
                    String variableName_ = init_.trim();
                    if (StringList.isDollarWord(variableName_)) {
                        br_ = stds_.newForeachLoop(_context, new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(varOffset_, variableName_),
                                new OffsetStringInfo(expOffset_, exp_.trim()), new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                                new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                    } else {
                        int nextIndexVar_ = variableName_.indexOf(',');
                        if (nextIndexVar_ < 0) {
                            _badIndexes.add(_i);
                            return null;
                        }
                        String firstVar_ = variableName_.substring(0, nextIndexVar_);
                        String afterFirst_ = variableName_.substring(nextIndexVar_+1);
                        String declaringTypeSec_ = getDeclaringTypeBlock(afterFirst_);
                        int secType_ = varOffset_;
                        secType_ += nextIndexVar_+1;
                        int secVarOff_ = secType_;
                        secType_ += StringList.getFirstPrintableCharIndex(declaringTypeSec_);
                        secVarOff_ += declaringTypeSec_.length();
                        String padSecVar_= afterFirst_.substring(declaringTypeSec_.length());
                        secVarOff_ += StringList.getFirstPrintableCharIndex(padSecVar_);
                        String secVar_ = padSecVar_.trim();
                        br_ = new ForEachTable(_context,
                                new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(varOffset_, firstVar_),
                                new OffsetStringInfo(secType_, declaringTypeSec_.trim()), new OffsetStringInfo(secVarOff_, secVar_),
                                new OffsetStringInfo(expOffset_, exp_.trim()), new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                                new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                    }
                    _currentParent.appendChild(br_);
                } else {
                    String to_ = exp_.substring(0, nextElt_);
                    toOff_ += StringList.getFirstPrintableCharIndex(to_);
                    int expOff_ = toOff_ + nextElt_;
                    int stepOff_ = expOff_ + 1;
                    exp_ = exp_.substring(nextElt_ + 1);
                    String step_ = exp_;
                    stepOff_ += StringList.getFirstPrintableCharIndex(step_);
                    label_ = label_.substring(lastPar_ + 1);
                    if (!label_.isEmpty()) {
                        labelOff_ += StringList.getFirstPrintableCharIndex(label_);
                    }
                    br_ = new ForMutableIterativeLoop(_context,
                            new OffsetBooleanInfo(finalOffset_, finalLocalVar_),
                            new OffsetStringInfo(typeOffset_,declaringType_.trim()),
                            new OffsetStringInfo(initOff_,init_.trim()), new OffsetStringInfo(toOff_,to_.trim()),  new OffsetStringInfo(stepOff_,step_.trim()), new OffsetStringInfo(indexClassOffest_,indexClassName_.trim()),
                            new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                    _currentParent.appendChild(br_);
                }
            }
        } else if (ContextEl.startsWithKeyWord(_trimmedInstruction,keyWordSwitch_)) {
            String exp_ = _trimmedInstruction.substring(keyWordSwitch_.length());
            int valueOffest_ = _instructionLocation + keyWordSwitch_.length();
            int lastPar_ = exp_.lastIndexOf(END_CALLING);
            int labelOff_ = valueOffest_ + lastPar_+ 1;
            int afterLeftPar_ = exp_.indexOf(BEGIN_CALLING) + 1;
            valueOffest_ += afterLeftPar_;
            String label_ = exp_;
            if (afterLeftPar_ > lastPar_) {
                _badIndexes.add(_i);
                return null;
            }
            exp_ = exp_.substring(afterLeftPar_, lastPar_);
            valueOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            label_ = label_.substring(lastPar_ + 1);
            if (!label_.isEmpty()) {
                labelOff_ += StringList.getFirstPrintableCharIndex(label_);
            }
            br_ = new SwitchBlock(new OffsetStringInfo(valueOffest_, exp_.trim()), new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (StringList.quickEq(_trimmedInstruction, keyWordStatic_)) {
            br_ = new StaticBlock(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        } else if (_trimmedInstruction.isEmpty()) {
            br_ = new InstanceBlock(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
        }
        //Not an error
        return br_;
    }
    private static boolean isKeyWordCategory(String _key, KeyWords _keyWords) {
        if (StringList.quickEq(_key, _keyWords.getKeyWordClass())) {
            return true;
        }
        if (StringList.quickEq(_key, _keyWords.getKeyWordEnum())) {
            return true;
        }
        if (StringList.quickEq(_key, _keyWords.getKeyWordInterface()))  {
            return true;
        }
        return StringList.quickEq(_key, _keyWords.getKeyWordAnnotation());
    }
    private static String getDeclaringParamType(String _found) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        char prev_ = ' ';
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (declTypeName_.toString().trim().endsWith(VARARG)) {
                typeDeclaring_ = true;
                break;
            }
            if (currentCharFound_ == END_ARRAY && nbOpenedTmp_ == 0) {
                String after_ = _found.substring(indexInstr_ + 1);
                String nextPart_ = after_.trim();
                if (StringList.isDollarWordChar(nextPart_.charAt(0))) {
                    declTypeName_.append(currentCharFound_);
                    typeDeclaring_ = true;
                    break;
                }
            }
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                if (StringList.isDollarWordChar(prev_)) {
                    String nextPart_ = _found.substring(indexInstr_).trim();
                    if (StringList.isDollarWordChar(nextPart_.charAt(0))) {
                        typeDeclaring_ = true;
                        break;
                    }
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            if (currentCharFound_ == BEGIN_TEMPLATE) {
                nbOpenedTmp_++;
                prev_ = currentCharFound_;
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    declTypeName_.append(currentCharFound_);
                    String after_ = _found.substring(indexInstr_ + 1);
                    String nextPart_ = after_.trim();
                    if (nextPart_.startsWith(".") && !nextPart_.startsWith(VARARG)) {
                        indexInstr_++;
                        prev_ = currentCharFound_;
                        continue;
                    }
                    if (nextPart_.startsWith("[")) {
                        indexInstr_++;
                        prev_ = currentCharFound_;
                        continue;
                    }
                    if (nextPart_.startsWith(VARARG)) {
                        int offset_ = StringList.getFirstPrintableCharIndex(after_);
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
            if (!Character.isWhitespace(currentCharFound_)) {
                prev_ = currentCharFound_;
            }
        }
        if (typeDeclaring_) {
            return declTypeName_.toString();
        }
        return EMPTY_STRING;
    }
    private static String getDeclaringTypeOper(String _found) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        char prev_ = ' ';
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                String nextPart_ = _found.substring(indexInstr_).trim();
                if (StringList.isDollarWordChar(prev_)) {
                    if (nextPart_.trim().startsWith(String.valueOf(BEGIN_CALLING))) {
                        break;
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
                prev_ = currentCharFound_;
                continue;
            }
            if (currentCharFound_ == END_TEMPLATE) {
                nbOpenedTmp_--;
                if (nbOpenedTmp_ == 0) {
                    String nextPart_ = _found.substring(indexInstr_+1).trim();
                    if (nextPart_.startsWith(".")) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        prev_ = currentCharFound_;
                        continue;
                    }
                    if (nextPart_.startsWith(String.valueOf(BEGIN_ARRAY))) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        prev_ = currentCharFound_;
                        continue;
                    }
                    declTypeName_.append(currentCharFound_);
                    break;
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                prev_ = currentCharFound_;
                continue;
            }
            if (nbOpenedTmp_ > 0) {
                if (!Character.isWhitespace(currentCharFound_)) {
                    prev_ = currentCharFound_;
                }
                declTypeName_.append(currentCharFound_);
                indexInstr_++;
                continue;
            }
            prev_ = currentCharFound_;
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
        if (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (currentCharFound_ == BEGIN_ARRAY) {
                while (indexInstr_ < instLen_) {
                    currentCharFound_ = _found.charAt(indexInstr_);
                    if (Character.isWhitespace(currentCharFound_)) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == BEGIN_ARRAY) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == END_ARRAY) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    break;
                }
            }
        }
        return declTypeName_.toString();
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
        if (_char == '&') {
            return true;
        }
        if (_char == '|') {
            return true;
        }
        if (_char == '^') {
            return true;
        }
        return _char == '~';
    }
    private static String getDeclaringTypeBlock(String _found) {
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                String trimmed_ = declTypeName_.toString().trim();
                String nextPart_ = _found.substring(indexInstr_).trim();
                int length_ = trimmed_.length();
                if (length_ > 0) {
                    char ch_ = trimmed_.charAt(length_ - 1);
                    if (StringList.isDollarWordChar(ch_)) {
                        if (!nextPart_.isEmpty()) {
                            if (StringList.isDollarWordChar(nextPart_.charAt(0))) {
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
                    if (nextPart_.startsWith(".")) {
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


        if (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (currentCharFound_ == BEGIN_ARRAY) {
                while (indexInstr_ < instLen_) {
                    currentCharFound_ = _found.charAt(indexInstr_);
                    if (Character.isWhitespace(currentCharFound_)) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == BEGIN_ARRAY) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == END_ARRAY) {
                        declTypeName_.append(currentCharFound_);
                        indexInstr_++;
                        continue;
                    }
                    break;
                }
            }
        }
        return declTypeName_.toString();
    }
    private static String getDeclaringTypeInstr(String _found, KeyWords _options) {
        String keyWordNew_ = _options.getKeyWordNew();
        int indexInstr_ = 0;
        int instLen_ = _found.length();
        boolean typeDeclaring_ = false;
        StringBuilder declTypeName_ = new StringBuilder();
        int nbOpenedTmp_ = 0;
        while (indexInstr_ < instLen_) {
            char currentCharFound_ = _found.charAt(indexInstr_);
            if (Character.isWhitespace(currentCharFound_) && nbOpenedTmp_ == 0) {
                String trimmed_ = declTypeName_.toString().trim();
                if (StringList.quickEq(trimmed_,keyWordNew_)) {
                    break;
                }
                if (trimmed_.endsWith(keyWordNew_)) {
                    //there exist a character before the pseudo key word
                    int lenDeclTypeName_ = trimmed_.length() - keyWordNew_.length();
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
                    if (nextPart_.startsWith(".")) {
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
                        ok_ = false;
                        indexInstr_++;
                        continue;
                    }
                    if (currentCharFound_ == END_ARRAY) {
                        declTypeName_.append(currentCharFound_);
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
    private static int getIndex(String _info, char _endLine) {
        int indexInstr_ = 0;
        int instrLen_ = _info.length();
        Ints localCallings_ = new Ints();
        boolean localConstChar_ = false;
        boolean localConstString_ = false;
        boolean localConstText_ = false;
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
            if (localConstText_) {
                if (locChar_ == DEL_TEXT) {
                    if (indexInstr_ + 1 >= instrLen_ ||_info.charAt(indexInstr_ + 1) != DEL_TEXT) {
                        indexInstr_++;
                        localConstText_ = false;
                        continue;
                    }
                    indexInstr_++;
                }
                indexInstr_++;
                continue;
            }
            if (localCallings_.isEmpty() && locChar_ == _endLine) {
                return indexInstr_;
            }
            if (locChar_ == DEL_CHAR) {
                localConstChar_ = true;
            }
            if (locChar_ == DEL_STRING) {
                localConstString_ = true;
            }
            if (locChar_ == DEL_TEXT) {
                localConstText_ = true;
            }
            if (locChar_ == BEGIN_CALLING) {
                localCallings_.add(indexInstr_);
            }
            if (locChar_ == END_CALLING) {
                localCallings_.removeLast();
            }
            if (locChar_ == BEGIN_ARRAY) {
                localCallings_.add(indexInstr_);
            }
            if (locChar_ == END_ARRAY) {
                localCallings_.removeLast();
            }
            if (locChar_ == BEGIN_BLOCK) {
                localCallings_.add(indexInstr_);
            }
            if (locChar_ == END_BLOCK) {
                localCallings_.removeLast();
            }
            indexInstr_++;
        }
        return -1;
    }

    private static int afterComments(String _found, int _from) {
        int i_ = _from;
        int len_ = _found.length();
        boolean commentedSingleLine_ = false;
        boolean commentedMultiLine_ = false;
        while (i_ < len_) {
            char cur_ = _found.charAt(i_);
            if (commentedSingleLine_) {
                if (cur_ == LINE_RETURN) {
                    commentedSingleLine_ = false;
                }
                i_++;
                continue;
            }
            if (commentedMultiLine_) {
                if (cur_ == SECOND_COMMENT && i_ + 1 < len_ && _found.charAt(i_ + 1) == BEGIN_COMMENT) {
                    commentedMultiLine_ = false;
                    i_++;
                    i_++;
                    continue;
                }
                i_++;
                continue;
            }
            if (cur_ == BEGIN_COMMENT && i_ + 1 < len_) {
                if (_found.charAt(i_ + 1) == BEGIN_COMMENT) {
                    commentedSingleLine_ = true;
                    i_++;
                    i_++;
                    continue;
                }
                if (_found.charAt(i_ + 1) == SECOND_COMMENT) {
                    commentedMultiLine_ = true;
                    i_++;
                    i_++;
                    continue;
                }
            }
            if (Character.isWhitespace(cur_)) {
                i_++;
                continue;
            }
            return i_;
        }
        return -1;
    }
    private static int incrementRowCol(int _index, int _delta, String _file, EnablingSpaces _enabling) {
        int index_ = _index;
        for (int i = 0; i < _delta; i++) {
            index_ = incrementRowCol(index_, _file, _enabling);
        }
        return index_;
    }

    static int incrementRowCol(int _index, String _file, EnablingSpaces _enabling) {
        updateAllowedSpaces(_index, _file, _enabling);
        return _index+1;
    }
    private static void updateAllowedSpaces(int _index, String _file, EnablingSpaces _enabledSpaces) {
        char cur_ = _file.charAt(_index);
        int tabWidth_ = _enabledSpaces.getTabWidth();
        if (cur_ == LINE_RETURN) {
            if (_enabledSpaces.isOnlySpacesLine()) {
                _enabledSpaces.getFile().getLineReturns().add(_enabledSpaces.getBegin());
                _enabledSpaces.getFile().getLineReturns().add(Math.max(_index - 1,_enabledSpaces.getBegin()));
                int length_ = getLength(_index, _file, tabWidth_, _enabledSpaces.getBegin() + 1);
                _enabledSpaces.getFile().getLeftSpaces().add(length_);
            }
            _enabledSpaces.setOnlySpacesLine(true);
            _enabledSpaces.setEnabledTab(true);
            _enabledSpaces.setBegin(_index);
            return;
        }
        if (!Character.isWhitespace(cur_)) {
            if (_enabledSpaces.isOnlySpacesLine()) {
                boolean empty_ = _enabledSpaces.getFile().getLineReturns().isEmpty();
                if (_index == 0) {
                    _enabledSpaces.getFile().getLineReturns().add(-1);
                    _enabledSpaces.getFile().getLineReturns().add(Math.max(_index - 1,_enabledSpaces.getBegin()));
                } else {
                    _enabledSpaces.getFile().getLineReturns().add(_enabledSpaces.getBegin());
                    _enabledSpaces.getFile().getLineReturns().add(Math.max(_index - 1,_enabledSpaces.getBegin()));
                }
                int firstIndex_;
                if (empty_) {
                    firstIndex_ = 0;
                } else {
                    firstIndex_ = _enabledSpaces.getBegin() + 1;
                }
                int length_ = getLength(_index, _file, tabWidth_, firstIndex_);
                _enabledSpaces.getFile().getLeftSpaces().add(length_);
            }
            _enabledSpaces.setOnlySpacesLine(false);
            _enabledSpaces.setEnabledTab(false);
            return;
        }
        if (cur_ == TAB) {
            if (!_enabledSpaces.isOnlySpacesLine()) {
                _enabledSpaces.getFile().getTabulations().add(_index);
            }
        }
        if (cur_ == TAB && _enabledSpaces.isCheckTabs()) {
            if (!_enabledSpaces.isEnabledTab()) {
                _enabledSpaces.setOk(false);
            }
        }
    }

    private static int getLength(int _index, String _file, int _tabWidth, int _firstIndex) {
        int length_ = 0;
        for (int i = _firstIndex; i < _index; i++) {
            char current_ = _file.charAt(i);
            if (current_ == TAB) {
                length_ += _tabWidth;
                length_ -= length_ % _tabWidth;
            } else {
                length_++;
            }
        }
        return length_;
    }

    static int skipWhitespace(int _nextIndex, String _file, EnablingSpaces _enabledSpaces) {
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
            nextIndex_ = incrementRowCol(nextIndex_, _file, _enabledSpaces);
        }
        return nextIndex_;
    }
}
