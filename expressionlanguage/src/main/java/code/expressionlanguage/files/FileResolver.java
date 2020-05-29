package code.expressionlanguage.files;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.options.KeyWords;
import code.util.*;

public final class FileResolver {

    private static final char INHERIT = ':';
    private static final char FOR_BLOCKS = ':';
    private static final char END_LINE = ';';
    private static final char END_IMPORTS = END_LINE;
    private static final char PKG = '.';
    private static final String EMPTY_STRING = "";
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
        Classes cls_ = _context.getClasses();
        cls_.putFileBlock(_fileName, fileBlock_);
        StringList importedTypes_ = new StringList();
        StringBuilder str_ = new StringBuilder();
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
        if (fileBlock_.processLinesTabsWithError(_context,_file)) {
            return;
        }
        int i_ = CustList.FIRST_INDEX;
        int len_ = _file.length();
        CommentDelimiters current_ = null;
        int indexImport_ = 0;
        Ints badIndexes_ = new Ints();
        Ints offsetsImports_ = new Ints();
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (current_ != null) {
                String endCom_ = getEndCom(_file, i_, current_);
                int length_ = endCom_.length();
                if (length_ > 0) {
                    i_ += length_;
                    appendEnd(fileBlock_, i_, endCom_);
                    current_ = null;
                    continue;
                }
                i_++;
                continue;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordPublic_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordOperator_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordProtected_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordPackage_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordPrivate_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordAbstract_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordAnnotation_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordClass_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordEnum_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordFinal_)) {
                break;
            }
            if (StringExpUtil.startsWithKeyWord(_file,i_,keyWordInterface_)) {
                break;
            }
            if (currentChar_ == ANNOT) {
                break;
            }
            if (str_.toString().trim().isEmpty()) {
                boolean skip_= false;
                for (CommentDelimiters c: _context.getComments()) {
                    if (_file.startsWith(c.getBegin(),i_)) {
                        current_ = c;
                        fileBlock_.getBeginComments().add(i_);
                        i_ += c.getBegin().length();
                        skip_ = true;
                        break;
                    }
                }
                if (skip_) {
                    continue;
                }
            }
            if (currentChar_ == END_IMPORTS) {
                importedTypes_.add(str_.toString());
                offsetsImports_.add(indexImport_);
                str_.delete(0, str_.length());
            } else {
                if (!Character.isWhitespace(currentChar_)) {
                    if (str_.length() == 0) {
                        indexImport_ = i_;
                    }
                    str_.append(currentChar_);
                } else {
                    str_.append(currentChar_);
                }
            }
            i_ = i_ + 1;
        }
        if (i_ >= len_) {
            badIndexes_.add(i_);
        }
        InputTypeCreation input_ = new InputTypeCreation();
        input_.setNextIndex(i_);
        fileBlock_.getImports().addAllElts(importedTypes_);
        fileBlock_.getImportsOffset().addAllElts(offsetsImports_);
        input_.setFile(fileBlock_);
        if (!badIndexes_.isEmpty()) {
            for (int i: badIndexes_) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(_fileName);
                b_.setIndexFile(i);
                //if empty file => add underlined space
                //else underline last char
                b_.buildError(_context.getAnalysisMessages().getBadIndexInParser());
                _context.addError(b_);
            }
            return;
        }
        //the file is not trimmed empty
        while (true) {
            ResultCreation res_ = createType(_context, _file, input_);
            badIndexes_ = input_.getBadIndexes();
            if (!res_.isOk()) {
                for (int i: badIndexes_) {
                    FoundErrorInterpret b_ = new FoundErrorInterpret();
                    b_.setFileName(_fileName);
                    b_.setIndexFile(i);
                    //underline index char
                    b_.buildError(_context.getAnalysisMessages().getBadIndexInParser());
                    _context.addError(b_);
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
                                FoundErrorInterpret d_ = new FoundErrorInterpret();
                                d_.setFileName(_fileName);
                                d_.setIndexFile(cur_.getIdRowCol());
                                //s_ len
                                d_.buildError(_context.getAnalysisMessages().getDuplicatedInnerType(),
                                        s_);
                                _context.addError(d_);
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
                            if (c_ instanceof RootBlock) {
                                simpleNames_.removeLast();
                            }
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
            boolean ended_ = true;
            current_ = null;
            while (i_ < len_) {
                char currentChar_ = _file.charAt(i_);
                if (current_ != null) {
                    String endCom_ = getEndCom(_file, i_, current_);
                    int length_ = endCom_.length();
                    if (length_ > 0) {
                        i_ += length_;
                        appendEnd(fileBlock_, i_, endCom_);
                        current_ = null;
                        continue;
                    }
                    i_++;
                    continue;
                }
                if (StringList.isDollarWordChar(currentChar_)) {
                    hasNext_ = true;
                    ended_ = false;
                    break;
                }
                if (currentChar_ == ANNOT) {
                    hasNext_ = true;
                    ended_ = false;
                    break;
                }
                boolean skip_= false;
                for (CommentDelimiters c: _context.getComments()) {
                    if (_file.startsWith(c.getBegin(),i_)) {
                        current_ = c;
                        fileBlock_.getBeginComments().add(i_);
                        i_ += c.getBegin().length();
                        skip_ = true;
                        break;
                    }
                }
                if (skip_) {
                    continue;
                }

                i_ = i_ + 1;
            }
            if (ended_ && current_ != null) {
                fileBlock_.getEndComments().add(len_ - 1);
            }
            if (!hasNext_) {
                for (int i: badIndexes_) {
                    FoundErrorInterpret b_ = new FoundErrorInterpret();
                    b_.setFileName(_fileName);
                    b_.setIndexFile(i);
                    //underline index char
                    b_.buildError(_context.getAnalysisMessages().getBadIndexInParser());
                    _context.addError(b_);
                }
                return;
            }
            input_.setNextIndex(i_);
        }
    }

    private static ResultCreation createType(ContextEl _context, String _file, InputTypeCreation _input) {
        KeyWords keyWords_ = _context.getKeyWords();
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
        if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordPublic_)) {
            access_ = AccessEnum.PUBLIC;
            nextIndex_ += keyWordPublic_.length();
            nextIndex_ = skipWhitespace(nextIndex_, _file);
       } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordProtected_)) {
            access_ = AccessEnum.PROTECTED;
            nextIndex_ += keyWordProtected_.length();
            nextIndex_ = skipWhitespace(nextIndex_, _file);
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordPackage_)) {
            access_ = AccessEnum.PACKAGE;
            nextIndex_ += keyWordPackage_.length();
            nextIndex_ = skipWhitespace(nextIndex_, _file);
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordPrivate_)) {
            access_ = AccessEnum.PRIVATE;
            nextIndex_ += keyWordPrivate_.length();
            nextIndex_ = skipWhitespace(nextIndex_, _file);
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordOperator_)) {
            access_ = AccessEnum.PUBLIC;
            oper_ = true;
            nextIndex_ += keyWordOperator_.length();
            nextIndex_ = skipWhitespace(nextIndex_, _file);
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordAbstract_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordFinal_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordClass_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordInterface_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordEnum_)) {
            access_ = AccessEnum.PACKAGE;
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordAnnotation_)) {
            access_ = AccessEnum.PACKAGE;
        } else {
            //ERROR
            badIndexes_.add(nextIndex_);
            return out_;
        }
        if (oper_) {
            symbolIndex_ = nextIndex_;
            symbol_ = fetchSymbol(_file,nextIndex_);
            nextIndex_ += symbol_.length();
            nextIndex_ = skipWhitespace(nextIndex_, _file);
        }
        StringList importedTypes_;
        Ints offsetsImports_;
        boolean enableByEndLine_ = false;
        BracedBlock currentParent_;
        Ints braces_ = new Ints();
        String packageName_ = EMPTY_STRING;
        if (oper_) {
            ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _file);
            importedTypes_ = p_.getImportedTypes();
            offsetsImports_ = p_.getOffsetsImports();
            nextIndex_ = p_.getNextIndex();
            if (!p_.isOk()) {
                //ERROR
                badIndexes_.add(nextIndex_);
                return out_;
            }
            out_ = new ResultOperatorCreation();
            int until_ = getIndex(nextIndex_,_file,BEGIN_BLOCK);
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
            declaringType_ = getFoundType(info_);
            int declTypeLen_ = declaringType_.length();
            String afterType_ = info_.substring(declTypeLen_);
            int afterTypeOff_ = StringList.getFirstPrintableCharIndex(afterType_);
            info_ = afterType_.trim();
            int leftParIndex_ = info_.indexOf(BEGIN_CALLING);
            if (leftParIndex_ < 0) {
                badIndexes_.add(nextIndex_);
                return out_;
            }
            String afterMethodName_ = info_.substring(leftParIndex_ + 1);
            paramOffest_ = afterTypeOff_ + typeOffset_ + declTypeLen_ + 1;
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
                if (trim_.indexOf(ANNOT) == 0) {
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
                String paramType_ = getFoundType(info_);
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
            currentParent_ = new OperatorBlock(new OffsetStringInfo(typeOffset_, declaringType_.trim()),
                    new OffsetStringInfo(symbolIndex_, symbol_.toString().trim()), parametersType_,
                    offestsTypes_, parametersName_, offestsParams_, new OffsetsBlock(nextIndex_, nextIndex_));
            ((NamedFunctionBlock)currentParent_).getAnnotationsParams().addAllElts(annotationsParams_);
            ((NamedFunctionBlock)currentParent_).getAnnotationsIndexesParams().addAllElts(annotationsIndexesParams_);
            ((OperatorBlock)currentParent_).getImports().addAllElts(importedTypes_);
            ((OperatorBlock)currentParent_).getImportsOffset().addAllElts(offsetsImports_);
            ((OperatorBlock)currentParent_).getAnnotations().addAllElts(annotationsTypes_);
            ((OperatorBlock)currentParent_).getAnnotationsIndexes().addAllElts(annotationsIndexesTypes_);
            ((ResultOperatorCreation)out_).setType((OperatorBlock) currentParent_);
            braces_.add(until_);

            nextIndex_ = until_ + 1;
        } else {
            out_ = new ResultTypeCreation();
            char currentChar_;
            boolean abstractType_ = false;
            boolean finalType_ = false;
            String beforeQu_ = _file.substring(nextIndex_);
            if (StringExpUtil.startsWithKeyWord(beforeQu_, keyWordAbstract_)) {
                abstractType_ = true;
                nextIndex_ = nextIndex_ + keyWordAbstract_.length();
                nextIndex_ = skipWhitespace(nextIndex_, _file);
            }
            beforeQu_ = _file.substring(nextIndex_);
            if (StringExpUtil.startsWithKeyWord(beforeQu_, keyWordFinal_)) {
                finalType_ = true;
                nextIndex_ = nextIndex_ + keyWordFinal_.length();
                nextIndex_ = skipWhitespace(nextIndex_, _file);
            }
            int categoryOffset_ = nextIndex_ - 1;
            String type_;
            String beforeCat_ = _file.substring(nextIndex_);
            if (StringExpUtil.startsWithKeyWord(beforeCat_, keyWordClass_)) {
                type_ = keyWordClass_;
                nextIndex_ = nextIndex_ + keyWordClass_.length();
            } else if (StringExpUtil.startsWithKeyWord(beforeCat_, keyWordEnum_)) {
                type_ = keyWordEnum_;
                nextIndex_ = nextIndex_ + keyWordEnum_.length();
            } else if (StringExpUtil.startsWithKeyWord(beforeCat_, keyWordInterface_)) {
                type_ = keyWordInterface_;
                nextIndex_ = nextIndex_ + keyWordInterface_.length();
            } else if (StringExpUtil.startsWithKeyWord(beforeCat_, keyWordAnnotation_)) {
                type_ = keyWordAnnotation_;
                nextIndex_ = nextIndex_ + keyWordAnnotation_.length();
            } else {
                //ERROR
                badIndexes_.add(nextIndex_);
                return out_;
            }
            nextIndex_ = skipWhitespace(nextIndex_, _file);
            ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _file);
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
            if (StringExpUtil.startsWithKeyWord(_file.substring(nextIndex_), keyWordInterfaces_)) {
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
                for (String p: StringList.splitChars(interfacesInfo_, SEP_CALLING)) {
                    staticInitInterfaces_.add(p);
                    staticInitInterfacesOffset_.add(interfaceOffest_);
                    interfaceOffest_ += p.length() + 1;
                }
                nextIndex_ = end_ + 1;
            }
            nextIndex_ = skipWhitespace(nextIndex_, _file);
            StringBuilder str_ = new StringBuilder();
            IntMap< String> superTypes_ = new IntMap< String>();
            StringBuilder typeNamePref_ = new StringBuilder();
            StringBuilder templateDef_ = new StringBuilder();
            int nbOpened_ = 0;
            boolean ok_ = false;
            boolean foundInherit_ = false;
            int beginDefinition_ = nextIndex_;
            int inheritIndex_ = -1;
            while (nextIndex_ < len_) {
                currentChar_ = _file.charAt(nextIndex_);
                if (currentChar_ == BEGIN_TEMPLATE) {
                    nbOpened_++;
                }
                if (nbOpened_ > 0) {
                    if (!foundInherit_) {
                        templateDef_.append(currentChar_);
                    }
                } else {
                    if (templateDef_.length() == 0 && currentChar_ != BEGIN_BLOCK
                            && !foundInherit_ && currentChar_ != INHERIT) {
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

                    nextIndex_ = nextIndex_ + 1;
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

                nextIndex_ = nextIndex_ + 1;
            }
            if (foundInherit_) {
                superTypes_.put(inheritIndex_, str_.toString());
            }
            if (!ok_) {
                //ERROR
                badIndexes_.add(len_-1);
                return out_;
            }

            nextIndex_ = nextIndex_ + 1;
            RootBlock typeBlock_;
            String tempDef_ = templateDef_.toString();
            String typeName_ = typeNamePref_.toString();
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
                typeBlock_ = new EnumBlock(beginDefinition_, categoryOffset_, baseName_, packageName_,
                        new OffsetAccessInfo(accessOffsetType_, access_) , tempDef_, superTypes_, new OffsetsBlock(beginType_,beginType_));
            } else if (StringList.quickEq(type_, keyWordClass_)) {
                typeBlock_ = new ClassBlock(beginDefinition_, categoryOffset_, baseName_, packageName_,
                        new OffsetAccessInfo(accessOffsetType_, access_), tempDef_, superTypes_, finalType_, abstractType_, true,
                        new OffsetsBlock(beginType_,beginType_));
            } else if (StringList.quickEq(type_, keyWordInterface_)) {
                typeBlock_ = new InterfaceBlock(beginDefinition_, categoryOffset_, baseName_, packageName_,
                        new OffsetAccessInfo(accessOffsetType_, access_) , tempDef_, superTypes_, true, new OffsetsBlock(beginType_,beginType_));
            } else {
                typeBlock_ = new AnnotationBlock(beginDefinition_, categoryOffset_, baseName_, packageName_,
                        new OffsetAccessInfo(accessOffsetType_, access_) , tempDef_, superTypes_, new OffsetsBlock(beginType_,beginType_));
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
        return processOuterTypeBody(_context, _input, packageName_,nextIndex_, _file, currentParent_, braces_, enableByEndLine_, out_);
    }
    private static ResultCreation processOuterTypeBody(ContextEl _context, InputTypeCreation _input, String _pkgName,int _nextIndex,
            String _file, BracedBlock _currentParent, Ints _braces, boolean _enabledEnum, ResultCreation _out) {
        int len_ = _file.length();
        KeyWords keyWords_ = _context.getKeyWords();
        String keyWordCase_ = keyWords_.getKeyWordCase();
        String keyWordDefault_ = keyWords_.getKeyWordDefault();
        StringBuilder instruction_ = new StringBuilder();
        boolean printableInst_ = false;
        int instructionLocation_ = -1;
        FileBlock fileBlock_ = _input.getFile();
        Ints badIndexes_ = _input.getBadIndexes();
        Ints parentheses_ = new Ints();
        boolean constChar_ = false;
        boolean constString_ = false;
        boolean constText_ = false;
        boolean declType_ = false;
        BracedBlock currentParent_ = _currentParent;

        int i_ = _nextIndex;
        boolean okType_ = false;
        boolean enableByEndLine_ = _enabledEnum;
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        after_.setEnabledEnumHeader(enableByEndLine_);
        after_.setIndex(i_);
        after_.setParent(currentParent_);
        CommentDelimiters current_ = null;
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (current_ != null) {
                String endCom_ = getEndCom(_file, i_, current_);
                int length_ = endCom_.length();
                if (length_ > 0) {
                    i_ += length_;
                    appendEnd(fileBlock_, i_, endCom_);
                    instruction_.delete(0, instruction_.length());
                    printableInst_ = false;
                    current_ = null;
                    continue;
                }
                i_++;
                continue;
            }
            if (constChar_) {
                printableInst_ = true;
                instruction_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        badIndexes_.add(i_);
                        break;
                    }
                    instruction_.append(_file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_CHAR) {

                    i_ = i_ + 1;
                    constChar_ = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constString_) {
                printableInst_ = true;
                instruction_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        badIndexes_.add(i_);
                        break;
                    }
                    instruction_.append(_file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_STRING) {

                    i_ = i_ + 1;
                    constString_ = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constText_) {
                printableInst_ = true;
                instruction_.append(currentChar_);
                if (i_ + 1 >= len_) {
                    //ERROR
                    badIndexes_.add(i_);
                    break;
                }
                if(currentChar_ == DEL_TEXT) {
                    if (_file.charAt(i_ + 1) != DEL_TEXT) {

                        i_ = i_ + 1;
                        constText_ = false;
                        continue;
                    }
                    instruction_.append(_file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (!printableInst_) {
                boolean skip_= false;
                for (CommentDelimiters c: _context.getComments()) {
                    if (_file.startsWith(c.getBegin(),i_)) {
                        current_ = c;
                        fileBlock_.getBeginComments().add(i_);
                        i_ += c.getBegin().length();
                        skip_ = true;
                        break;
                    }
                }
                if (skip_) {
                    continue;
                }
            }
            if (currentChar_ == DEL_CHAR) {
                if (instruction_.length() == 0) {
                    instructionLocation_ = i_;
                }
                printableInst_ = true;
                instruction_.append(currentChar_);
                constChar_ = true;

                i_ = i_ + 1;
                continue;
            }
            if (currentChar_ == DEL_STRING) {
                if (instruction_.length() == 0) {
                    instructionLocation_ = i_;
                }
                printableInst_ = true;
                instruction_.append(currentChar_);
                constString_ = true;

                i_ = i_ + 1;
                continue;
            }
            if (currentChar_ == DEL_TEXT) {
                if (instruction_.length() == 0) {
                    instructionLocation_ = i_;
                }
                printableInst_ = true;
                instruction_.append(currentChar_);
                constText_ = true;

                i_ = i_ + 1;
                continue;
            }
            boolean endInstruction_ = false;
            if (parentheses_.isEmpty()) {
                if (currentChar_ == END_LINE) {
                    endInstruction_ = true;
                }
                if (currentChar_ == ':') {
                    String str_ = instruction_.toString().trim();
                    if (StringExpUtil.startsWithKeyWord(str_, keyWordCase_)
                            || StringList.quickEq(str_, keyWordDefault_)) {
                        endInstruction_ = true;
                    }
                    if (endInstruction_ && currentParent_ instanceof SwitchPartBlock) {
                        currentParent_ = currentParent_.getParent();
                    }
                }
                if (currentChar_ == SEP_ENUM_CONST && enableByEndLine_) {
                    endInstruction_ = true;
                }
                if (currentChar_ == END_BLOCK) {
                    endInstruction_ = true;
                }
                if (currentChar_ == BEGIN_BLOCK) {
                    EndInstruction end_ = endInstruction(currentParent_, instruction_, enableByEndLine_, _context);
                    if (end_ != EndInstruction.NONE) {
                        endInstruction_ = true;
                        if (end_ == EndInstruction.DECLARE_TYPE) {
                            declType_ = true;
                        }
                    }
                }
                if (enableByEndLine_) {
                    if (currentChar_ == BEGIN_TEMPLATE) {
                        //increment to last greater
                        printableInst_ = true;
                        ParsedTemplatedType par_ = new ParsedTemplatedType(instruction_,i_);
                        par_.parse(_file);
                        i_ = par_.getCurrent();
                        continue;
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
                printableInst_ = true;
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
                after_ = processInstruction(_context,currentChar_, _input,_pkgName, currentChar_, currentParent_,
                        instructionLocation_,
                        instruction_, _file, declType_, i_, _nextIndex, enableByEndLine_);
                printableInst_ = false;
                if (after_ == null) {
                    badIndexes_.add(_nextIndex);
                    return _out;
                }
                enableByEndLine_ = after_.isEnabledEnumHeader();
                currentParent_ = after_.getParent();
                i_ = after_.getIndex();
                declType_ = false;
            }
            if (_braces.isEmpty()) {
                okType_ = true;
                break;
            }

            i_ = i_ + 1;
        }
        if (okType_) {

            i_ = i_ + 1;
        } else {
            badIndexes_.add(len_);
        }
        _out.setNextIndex(i_);
        _out.setOk(okType_);
        return _out;
    }

    private static void appendEnd(FileBlock _fileBlock, int _i, String _e) {
        if (_e.trim().isEmpty()) {
            _fileBlock.getEndComments().add(_i -2);
        } else {
            _fileBlock.getEndComments().add(_i -1);
        }
    }

    private static EndInstruction endInstruction(BracedBlock _parent, StringBuilder _instruction,
                                                 boolean _enableByEndLine, ContextEl _context) {
        String tr_ = _instruction.toString().trim();
        if (tr_.isEmpty()) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (_enableByEndLine) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        KeyWords keyWords_ = _context.getKeyWords();
        String trTmp_ = tr_;
        if (tr_.charAt(0) == ANNOT) {
            ParsedAnnotations par_ = new ParsedAnnotations(tr_, 0);
            par_.parse();
            tr_ = par_.getAfter();
        }
        String word_ = EMPTY_STRING;
        if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordPrivate())) {
            word_ = keyWords_.getKeyWordPrivate();
        } else if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordPackage())) {
            word_ = keyWords_.getKeyWordPackage();
        } else if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordProtected())) {
            word_ = keyWords_.getKeyWordProtected();
        } else {
            if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordPublic())) {
                word_ = keyWords_.getKeyWordPublic();
            }
        }
        String afterAccess_ = tr_.substring(word_.length()).trim();
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordAnnotation_ = keyWords_.getKeyWordAnnotation();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordEnum_ = keyWords_.getKeyWordEnum();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordInterface_ = keyWords_.getKeyWordInterface();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String beforeQu_ = afterAccess_;
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordAbstract_)) {
            beforeQu_ = beforeQu_.substring(keyWordAbstract_.length()).trim();
        }
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordStatic_)) {
            beforeQu_ = beforeQu_.substring(keyWordStatic_.length()).trim();
        }
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordFinal_)) {
            beforeQu_ = beforeQu_.substring(keyWordFinal_.length()).trim();
        }
        boolean dType_ = false;
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordClass_)) {
            beforeQu_ = beforeQu_.substring(keyWordClass_.length()).trim();
            dType_ = true;
        } else if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordEnum_)) {
            beforeQu_ = beforeQu_.substring(keyWordEnum_.length()).trim();
            dType_ = true;
        } else if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordInterface_)) {
            beforeQu_ = beforeQu_.substring(keyWordInterface_.length()).trim();
            dType_ = true;
        } else {
            if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordAnnotation_)) {
                beforeQu_ = beforeQu_.substring(keyWordAnnotation_.length()).trim();
                dType_ = true;
            }
        }
        if (dType_) {
            if (!StringExpUtil.nextCharIs(beforeQu_,0,beforeQu_.length(),'(')) {
                return EndInstruction.DECLARE_TYPE;
            }
            return EndInstruction.NONE;
        }
        if (_parent instanceof AnnotationBlock){
            return EndInstruction.NONE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordIf())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordElse())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordElseif())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordSwitch())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordTry())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordCatch())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordFinally())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordFor())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordForeach())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordIter())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordDo())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordWhile())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringList.quickEq(trTmp_, keyWordStatic_)) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordReturn())) {
            return EndInstruction.NONE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordThrow())) {
            return EndInstruction.NONE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordBreak())) {
            return EndInstruction.NONE;
        }
        if (StringExpUtil.startsWithKeyWord(trTmp_,keyWords_.getKeyWordContinue())) {
            return EndInstruction.NONE;
        }
        char lastCh_ = trTmp_.charAt(trTmp_.length() - 1);
        if (lastCh_ =='=') {
            return EndInstruction.NONE;
        }
        if (lastCh_ ==']') {
            return EndInstruction.NONE;
        }
        if (lastCh_ ==':') {
            return EndInstruction.NONE;
        }
        if (lastCh_ =='?') {
            return EndInstruction.NONE;
        }
        if (!(_parent instanceof RootBlock)) {
            return EndInstruction.NONE;
        }
        if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordOperator())) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        word_ = EMPTY_STRING;
        if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordPrivate())) {
            word_ = keyWords_.getKeyWordPrivate();
        } else {
            if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordPackage())) {
                word_ = keyWords_.getKeyWordPackage();
            } else {
                if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordProtected())) {
                    word_ = keyWords_.getKeyWordProtected();
                } else {
                    if (StringExpUtil.startsWithKeyWord(tr_,keyWords_.getKeyWordPublic())) {
                        word_ = keyWords_.getKeyWordPublic();
                    }
                }
            }
        }
        afterAccess_ = tr_.substring(word_.length());
        String trimmedAfterAccess_ = afterAccess_.trim();
        boolean ctor_ = false;
        if (trimmedAfterAccess_.startsWith("(")) {
            ctor_ = true;
        } else {
            int leftPar_ = trimmedAfterAccess_.indexOf('(');
            if (leftPar_ > -1&&StringList.isDollarWord(trimmedAfterAccess_.substring(0,leftPar_).trim())){
                ctor_ = true;
            }
        }
        if (ctor_) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        String keyWordNormal_ = keyWords_.getKeyWordNormal();
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        StringList wordsSep_ = StringList.getDollarWordSeparators(trimmedAfterAccess_);
        int i_ = 0;
        int len_ = wordsSep_.size();
        while (i_ < len_) {
            String ws_ = wordsSep_.get(i_);
            if (!StringList.isDollarWord(ws_)) {
                i_++;
                continue;
            }
            if (StringList.quickEq(ws_,keyWordNormal_)) {
                i_++;
                continue;
            }
            if (StringList.quickEq(ws_,keyWordAbstract_)) {
                i_++;
                continue;
            }
            if (StringList.quickEq(ws_,keyWordStatic_)) {
                i_++;
                continue;
            }
            if (StringList.quickEq(ws_,keyWordStaticCall_)) {
                i_++;
                continue;
            }
            if (StringList.quickEq(ws_,keyWordFinal_)) {
                i_++;
                continue;
            }
            break;
        }
        if (i_ >= len_) {
            return EndInstruction.NONE;
        }
        String trAfterType_ = afterDeclaringType(wordsSep_, i_);
        int lenTrAfterType_ = trAfterType_.length();
        int indexTrAfterType_ = 0;
        while (indexTrAfterType_ < lenTrAfterType_) {
            char cur_ = trAfterType_.charAt(indexTrAfterType_);
            if (!StringList.isDollarWordChar(cur_)) {
                break;
            }
            indexTrAfterType_++;
        }
        while (indexTrAfterType_ < lenTrAfterType_) {
            char cur_ = trAfterType_.charAt(indexTrAfterType_);
            if (!Character.isWhitespace(cur_)) {
                break;
            }
            indexTrAfterType_++;
        }
        if (!StringExpUtil.nextCharIs(trAfterType_, indexTrAfterType_, lenTrAfterType_, BEGIN_CALLING)) {
            return EndInstruction.NONE;
        }
        return EndInstruction.NO_DECLARE_TYPE;
    }

    private static String afterDeclaringType(StringList _wordsSep, int _index) {
        String join_ = StringList.join(_wordsSep.mid(_index), "");
        String typeStr_ = getFoundType(join_);
        return join_.substring(typeStr_.length()).trim();
    }

    private static AfterBuiltInstruction processInstruction(ContextEl _context, char _end, InputTypeCreation _input, String _pkgName,char _currentChar,
                                                            BracedBlock _currentParent,
                                                            int _instructionLocation, StringBuilder _instruction, String _file, boolean _declType, int _i, int _nextIndex, boolean _enabledEnum) {
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        Ints badIndexes_ = _input.getBadIndexes();
        BracedBlock currentParent_ = _currentParent;
        int instructionLocation_ = _instructionLocation;
        Block br_ = null;
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
                    RootBlock built_ = processTypeHeader(_context, _input, _pkgName,true,
                            _file,
                            instructionLocation_, instructionRealLocation_,
                            found_,
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
                    if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordFinal_)) {
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
                    String declaringType_ = getFoundType(found_);
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
            } else {
                if (br_ instanceof BracedBlock && _currentChar != END_LINE) {
                    currentParent_ = (BracedBlock) br_;
                }
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
                    br_ = new InnerElementBlock((EnumBlock) currentParent_,_pkgName, new OffsetStringInfo(fieldOffest_, fieldName_.trim()),
                            new OffsetStringInfo(templateOffset_, tmpPart_.trim()),
                            new OffsetStringInfo(expressionOffest_, expression_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                } else {
                    br_ = new ElementBlock((EnumBlock) currentParent_,new OffsetStringInfo(fieldOffest_, fieldName_.trim()),
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
            if (_currentChar == END_LINE || _currentChar == END_BLOCK) {
                enableByEndLine_ = false;
            }
        } else if (_currentChar != END_BLOCK) {
            Block bl_ = processInstructionBlock(_context, badIndexes_, instructionLocation_, instructionRealLocation_, _i, currentParent_, trimmedInstruction_);
            if (bl_ == null) {
                if (_declType || currentParent_ instanceof RootBlock) {
                    //fields, constructors or methods
                    if (_declType) {
                        //Inner types
                        RootBlock built_ = processTypeHeader(_context, _input, _pkgName,false,
                                _file,
                                instructionLocation_, instructionRealLocation_,
                                found_,
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
                        br_ = processTypeMember(_context,_end,_input, _instruction, instructionLocation_, _i, currentParent_);
                        if (br_ == null) {
                            return null;
                        }
                    }
                } else {
                    int affectOffset_;
                    int afterDeclareOffset_;
                    boolean finalLocalVar_ = StringExpUtil.startsWithKeyWord(trimmedInstruction_, keyWordFinal_);
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
                        br_ = new DeclareVariable(new OffsetBooleanInfo(instructionLocation_, finalLocalVar_),
                                new OffsetStringInfo(realTypeOffset_, declaringType_.trim()),
                                new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                        currentParent_.appendChild(br_);
                    }
                    br_ = new Line(new OffsetStringInfo(afterDeclareOffset_, inst_.trim()), new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                    currentParent_.appendChild(br_);
                }
            } else {
                br_ = bl_;
            }
            boolean emptySwitchPart_ = false;
            if (br_ instanceof SwitchPartBlock) {
                int c_ = afterComments(_context,_file, _i + 1);
                if (c_ < 0) {
                    badIndexes_.add(_file.length());
                    return null;
                }
                if (StringExpUtil.startsWithKeyWord(_file,c_, keyWordCase_)) {
                    emptySwitchPart_ = true;
                }
                if (StringExpUtil.startsWithKeyWord(_file,c_, keyWordDefault_)) {
                    int n_ = StringExpUtil.nextPrintChar(c_ + keyWordDefault_.length(), _file.length(), _file);
                    if (StringExpUtil.nextCharIs(_file,n_,_file.length(),':')) {
                        emptySwitchPart_ = true;
                    }
                }
            }
            if (!emptySwitchPart_ && br_ instanceof BracedBlock && _currentChar != END_LINE) {
                currentParent_ = (BracedBlock) br_;
            }
        } else {
            //currentChar_ == END_BLOCK
            if (currentParent_ instanceof SwitchPartBlock) {
                currentParent_ = currentParent_.getParent();
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
        after_.setIndex(_i);
        after_.setParent(currentParent_);
        after_.setEnabledEnumHeader(enableByEndLine_);
        return after_;
    }
    private static RootBlock processTypeHeader(ContextEl _context, InputTypeCreation _input,String _pkgName,
                                               boolean _defStatic, String _file,
                                               int _instructionLocation, int _instructionRealLocation, String _found,
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
        if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWordPrivate_)) {
            accessFct_ = AccessEnum.PRIVATE;
            word_ = keyWordPrivate_;
        } else {
            if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWordPackage_)) {
                accessFct_ = AccessEnum.PACKAGE;
                word_ = keyWordPackage_;
            } else {
                if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWordProtected_)) {
                    accessFct_ = AccessEnum.PROTECTED;
                    word_ = keyWordProtected_;
                } else {
                    if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWordPublic_)) {
                        accessFct_ = AccessEnum.PUBLIC;
                        word_ = keyWordPublic_;
                    }
                }
            }
        }
        String afterAccess_ = trimmedInstruction_.substring(word_.length());
        int locIndex_ = typeOffset_ + word_.length();
        locIndex_ += StringList.getFirstPrintableCharIndex(afterAccess_);
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
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordAbstract_)) {
            abstractType_ = true;
            locIndex_ = locIndex_ + keyWordAbstract_.length();
            locIndex_ = skipWhitespace(locIndex_, _file);
        }
        beforeQu_ = _file.substring(locIndex_);
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordStatic_)) {
            staticType_ = true;
            locIndex_ = locIndex_ + keyWordStatic_.length();
            locIndex_ = skipWhitespace(locIndex_, _file);
        }
        beforeQu_ = _file.substring(locIndex_);
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordFinal_)) {
            finalType_ = true;
            locIndex_ = locIndex_ + keyWordFinal_.length();
            locIndex_ = skipWhitespace(locIndex_, _file);
        }
        String type_;
        int categoryOffset_ = locIndex_;
        String infoModifiers_ = _file.substring(locIndex_);
        if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordClass_)) {
            type_ = keyWordClass_;
            locIndex_ = locIndex_ + keyWordClass_.length();
        } else if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordEnum_)) {
            type_ = keyWordEnum_;
            locIndex_ = locIndex_ + keyWordEnum_.length();
        } else if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordInterface_)) {
            type_ = keyWordInterface_;
            locIndex_ = locIndex_ + keyWordInterface_.length();
        } else {
            type_ = keyWordAnnotation_;
            locIndex_ = locIndex_ + keyWordAnnotation_.length();
        }
        locIndex_ = skipWhitespace(locIndex_, _file);

        return tryBuiltTypeWithInfos(_file, _instructionLocation, _instructionRealLocation,_pkgName,
                _currentParent, accessFct_, trFound_, annotationsIndexes_, annotations_, typeOffset_,
                locIndex_, badIndexes_, staticType_, abstractType_, finalType_, keyWordClass_,
                keyWordEnum_, keyWordInterface_, keyWordInterfaces_, type_, categoryOffset_);
    }

    private static RootBlock tryBuiltTypeWithInfos(String _file, int _instructionLocation, int _instructionRealLocation, String _pkgName,BracedBlock _currentParent, AccessEnum _accessFct, int _trFound,
                                                   Ints _annotationsIndexes, StringList _annotations, int _typeOffset, int _locIndex, Ints _badIndexes,
                                                   boolean _staticType, boolean _abstractType, boolean _finalType,
                                                   String _keyWordClass, String _keyWordEnum, String _keyWordInterface, String _keyWordInterfaces, String _type,
                                                   int _categoryOffset) {
        ParsedImportedTypes p_ = new ParsedImportedTypes(_locIndex, _file);
        StringList importedTypes_ = p_.getImportedTypes();
        Ints offsetsImports_ = p_.getOffsetsImports();
        int locIndex_ = p_.getNextIndex();
        //insert interfaces static initialization for class and enums
        StringList staticInitInterfaces_ = new StringList();
        Ints staticInitInterfacesOffset_ = new Ints();
        if (StringExpUtil.startsWithKeyWord(_file,locIndex_, _keyWordInterfaces)) {
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
            for (String p: StringList.splitChars(interfacesInfo_, SEP_CALLING)) {
                staticInitInterfaces_.add(p);
                staticInitInterfacesOffset_.add(interfaceOffest_);
                interfaceOffest_ += p.length() + 1;
            }
            locIndex_ = end_ + 1;
        }
        return tryBuildType(_file, _instructionLocation, _instructionRealLocation,_pkgName,
                _currentParent, _accessFct, _trFound, _annotationsIndexes,
                _annotations, _typeOffset, locIndex_, _staticType, _abstractType, _finalType,
                _keyWordClass, _keyWordEnum, _keyWordInterface, _type, _categoryOffset, importedTypes_,
                offsetsImports_, staticInitInterfaces_, staticInitInterfacesOffset_);
    }

    private static RootBlock tryBuildType(String _file, int _instructionLocation, int _instructionRealLocation, String _pkgName,BracedBlock _currentParent,
                                          AccessEnum _accessFct, int _trFound, Ints _annotationsIndexes, StringList _annotations, int _typeOffset, int _locIndex,
                                          boolean _staticType, boolean _abstractType, boolean _finalType,
                                          String _keyWordClass, String _keyWordEnum, String _keyWordInterface, String _type, int _categoryOffset, StringList _importedTypes,
                                          Ints _offsetsImports, StringList _staticInitInterfaces, Ints _staticInitInterfacesOffset) {
        int locIndex_ = skipWhitespace(_locIndex, _file);
        StringBuilder str_ = new StringBuilder();
        IntMap< String> superTypes_ = new IntMap< String>();
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
            } else {
                if (templateDef_.length() == 0 && locChar_ != BEGIN_BLOCK
                        && !foundInherit_ && locChar_ != INHERIT) {
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

                locIndex_ = locIndex_ + 1;
                inheritIndex_ = locIndex_;
                continue;
            }
            if (locChar_ == BEGIN_BLOCK) {
                break;
            }
            if (foundInherit_) {
                str_.append(locChar_);
            }

            locIndex_ = locIndex_ + 1;
        }
        if (foundInherit_) {
            superTypes_.put(inheritIndex_, str_.toString());
        }

        RootBlock typeBlock_;
        String tempDef_ = templateDef_.toString();
        String typeName_ = typeNamePref_.toString();

        if (StringList.quickEq(_type, _keyWordEnum)) {
            typeBlock_ = new EnumBlock(beginDefinition_, _categoryOffset, typeName_, _pkgName,
                    new OffsetAccessInfo(_typeOffset - 1, _accessFct) , tempDef_, superTypes_,
                    new OffsetsBlock(_instructionRealLocation + _trFound, _instructionLocation + _trFound));
        } else if (StringList.quickEq(_type, _keyWordClass)) {
            typeBlock_ = new ClassBlock(beginDefinition_, _categoryOffset, typeName_, _pkgName,
                    new OffsetAccessInfo(_typeOffset - 1, _accessFct), tempDef_, superTypes_, _finalType, _abstractType, _staticType,
                    new OffsetsBlock(_instructionRealLocation + _trFound, _instructionLocation + _trFound));
        } else if (StringList.quickEq(_type, _keyWordInterface)) {
            typeBlock_ = new InterfaceBlock(beginDefinition_, _categoryOffset, typeName_, _pkgName,
                    new OffsetAccessInfo(_typeOffset - 1, _accessFct) , tempDef_, superTypes_, _staticType,
                    new OffsetsBlock(_instructionRealLocation + _trFound, _instructionLocation + _trFound));
        } else {
            typeBlock_ = new AnnotationBlock(beginDefinition_, _categoryOffset, typeName_, _pkgName,
                    new OffsetAccessInfo(_typeOffset - 1, _accessFct) , tempDef_, superTypes_,
                    new OffsetsBlock(_instructionRealLocation + _trFound, _instructionLocation + _trFound));
        }
        typeBlock_.setupOffsets(typeName_,"");
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

    private static Block processTypeMember(ContextEl _context,char _end,InputTypeCreation _input,
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
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        if (found_.trim().charAt(0) == ANNOT) {
            ParsedAnnotations par_ = new ParsedAnnotations(trimmedInstruction_, accessOffest_);
            par_.parse();
            annotationsIndexes_ = par_.getAnnotationsIndexes();
            annotations_ = par_.getAnnotations();
            trimmedInstruction_ = par_.getAfter();
            accessOffest_ = par_.getIndex();
            deltaAccess_ = accessOffest_ - (trFound_ + _i - found_.length());
        }
        if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWordPrivate_)) {
            accessFct_ = AccessEnum.PRIVATE;
            word_ = keyWordPrivate_;
        } else {
            if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWordPackage_)) {
                word_ = keyWordPackage_;
            } else {
                if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWordProtected_)) {
                    accessFct_ = AccessEnum.PROTECTED;
                    word_ = keyWordProtected_;
                } else {
                    if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWordPublic_)) {
                        accessFct_ = AccessEnum.PUBLIC;
                        word_ = keyWordPublic_;
                    }
                }
            }
        }
        String afterAccess_ = trimmedInstruction_.substring(word_.length());
        String trimmedAfterAccess_ = afterAccess_.trim();
        String infoModifiers_ = trimmedAfterAccess_;
        boolean field_ = false;
        if (_end == ';' && StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordStatic_)) {
            String otherModifier_;
            otherModifier_ = keyWordStatic_;
            int lenLoc_ = otherModifier_.length();
            String sub_ = infoModifiers_.substring(lenLoc_);
            int delta_ = StringList.getFirstPrintableCharIndex(sub_);
            infoModifiers_ = sub_.substring(delta_);
            if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordFinal_)) {
                field_ = true;
            }
        }
        boolean ctor_ = false;
        if (!field_) {
            if (trimmedAfterAccess_.startsWith("(")) {
                ctor_ = true;
            } else {
                String name_ = ((RootBlock) _currentParent).getName();
                if (StringExpUtil.startsWithKeyWord(trimmedAfterAccess_,name_)){
                    String after_ = trimmedAfterAccess_.substring(name_.length()).trim();
                    if (after_.startsWith("(")) {
                        ctor_ = true;
                    }
                }
            }
        }
        boolean meth_ = false;
        boolean oper_ = false;
        if (StringExpUtil.startsWithKeyWord(trimmedAfterAccess_,keyWords_.getKeyWordOperator())) {
            oper_ = true;
        } else if (!field_ && !ctor_) {
            infoModifiers_ = trimmedAfterAccess_;
            String otherModifier_;
            if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordNormal_)) {
                otherModifier_ = keyWordNormal_;
                int lenLoc_ = otherModifier_.length();
                String sub_ = infoModifiers_.substring(lenLoc_);
                int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                infoModifiers_ = sub_.substring(delta_);
            } else {
                if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordAbstract_)) {
                    otherModifier_ = keyWordAbstract_;
                    int lenLoc_ = otherModifier_.length();
                    String sub_ = infoModifiers_.substring(lenLoc_);
                    int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                    infoModifiers_ = sub_.substring(delta_);
                } else {
                    if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordStatic_)) {
                        otherModifier_ = keyWordStatic_;
                        int lenLoc_ = otherModifier_.length();
                        String sub_ = infoModifiers_.substring(lenLoc_);
                        int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                        infoModifiers_ = sub_.substring(delta_);
                    } else {
                        if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordStaticCall_)) {
                            otherModifier_ = keyWordStaticCall_;
                            int lenLoc_ = otherModifier_.length();
                            String sub_ = infoModifiers_.substring(lenLoc_);
                            int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                            infoModifiers_ = sub_.substring(delta_);
                        } else {
                            if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordFinal_)) {
                                otherModifier_ = keyWordFinal_;
                                int lenLoc_ = otherModifier_.length();
                                String sub_ = infoModifiers_.substring(lenLoc_);
                                int delta_ = StringList.getFirstPrintableCharIndex(sub_);
                                infoModifiers_ = sub_.substring(delta_);
                            }
                        }
                    }
                }
            }
            String typeStr_ = getFoundType(infoModifiers_);
            infoModifiers_ = infoModifiers_.substring(typeStr_.length()).trim();
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
            if (StringExpUtil.nextCharIs(infoModifiers_,indexMod_,lenAfterModifiers_,BEGIN_CALLING)) {
                meth_ = true;
            }
        }
        if (meth_|| oper_||ctor_||_end != ';') {
            if (_currentParent instanceof InterfaceBlock && word_.isEmpty()) {
                accessFct_ = AccessEnum.PUBLIC;
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
            int leftPar_=0;
            if (meth_) {
                if (StringExpUtil.startsWithKeyWord(info_,keyWordNormal_)) {
                    modifier_ = keyWordNormal_;
                    prefModifier_ = modifier_;
                } else {
                    if (StringExpUtil.startsWithKeyWord(info_,keyWordAbstract_)) {
                        modifier_ = keyWordAbstract_;
                        prefModifier_ = modifier_;
                    } else {
                        if (StringExpUtil.startsWithKeyWord(info_,keyWordStatic_)) {
                            modifier_ = keyWordStatic_;
                            prefModifier_ = modifier_;
                        } else {
                            if (StringExpUtil.startsWithKeyWord(info_,keyWordStaticCall_)) {
                                modifier_ = keyWordStaticCall_;
                                prefModifier_ = modifier_;
                            } else {
                                if (StringExpUtil.startsWithKeyWord(info_,keyWordFinal_)) {
                                    modifier_ = keyWordFinal_;
                                    prefModifier_ = modifier_;
                                }
                            }
                        }
                    }
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
                declaringType_ = getFoundType(info_);
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
            } else if (oper_){
                accessFct_ = AccessEnum.PUBLIC;
                modifier_ = keyWordStatic_;
                prefModifier_ = keyWords_.getKeyWordOperator();
                String afterModifier_ = info_.substring(prefModifier_.length());
                methodNameOffest_ = modifierOffest_ + prefModifier_.length();
                methodNameOffest_ += StringList.getFirstPrintableCharIndex(afterModifier_);
                afterModifier_ = afterModifier_.substring(StringList.getFirstPrintableCharIndex(afterModifier_));
                methodName_ = fetchSymbol(afterModifier_, 0).toString();
                afterModifier_ = afterModifier_.substring(methodName_.length());
                typeOffset_ = methodNameOffest_ + methodName_.length();
                typeOffset_ += StringList.getFirstPrintableCharIndex(afterModifier_);
                info_ = afterModifier_.trim();
                declaringType_ = getFoundType(info_);
                String afterType_ = info_.substring(declaringType_.length());
                int leftParIndex_ = afterType_.indexOf('(');
                paramOffest_ = typeOffset_+declaringType_.length() + leftParIndex_ + 1;
                String afterMethodName_ = afterType_.substring(leftParIndex_ + 1);
                paramOffest_ += StringList.getFirstPrintableCharIndex(afterMethodName_);
                info_ = afterMethodName_.trim();
            } else {
                paramOffest_ = modifierOffest_;
                int indexLeftPar_ = info_.indexOf(BEGIN_CALLING);
                paramOffest_ += indexLeftPar_ + 1;
                leftPar_ = paramOffest_;
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
                String paramType_ = getFoundType(info_);
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
            if (oper_) {
                String retType_ = declaringType_.trim();
                String trimMeth_ = methodName_.trim();
                MethodKind kind_;
                OverridableBlock ov_;
                kind_ = MethodKind.OPERATOR;
                ov_ = new OverridableBlock(_context, new OffsetAccessInfo(accessOffest_, accessFct_),
                        new OffsetStringInfo(typeOffset_, retType_),
                        new OffsetStringInfo(methodNameOffest_, trimMeth_), parametersType_, offestsTypes_,
                        parametersName_, offestsParams_, new OffsetStringInfo(modifierOffest_, modifier_),
                        new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                ov_.setKind(kind_);
                br_ = ov_;
            } else if (meth_) {
                String retType_ = declaringType_.trim();
                String trimMeth_ = methodName_.trim();
                MethodKind kind_;
                OverridableBlock ov_;
                if (StringList.quickEq(trimMeth_,_context.getKeyWords().getKeyWordExplicit())) {
                    kind_ = MethodKind.EXPLICIT_CAST;
                    ov_ = new OverridableBlock(_context, new OffsetAccessInfo(accessOffest_, accessFct_),
                            new OffsetStringInfo(typeOffset_, retType_),
                            new OffsetStringInfo(methodNameOffest_, trimMeth_), parametersType_, offestsTypes_,
                            parametersName_, offestsParams_, new OffsetStringInfo(modifierOffest_, modifier_),
                            new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                } else if (StringList.quickEq(trimMeth_,_context.getKeyWords().getKeyWordThis())) {
                    boolean get_ = !StringList.quickEq(retType_,_context.getStandards().getAliasVoid());
                    if (!get_) {
                        kind_ = MethodKind.SET_INDEX;
                        trimMeth_ = "[]=";
                    } else {
                        kind_ = MethodKind.GET_INDEX;
                        trimMeth_ = "[]";
                    }
                    ov_ = new OverridableBlock(_context, new OffsetAccessInfo(accessOffest_, accessFct_),
                            new OffsetStringInfo(typeOffset_, retType_),
                            new OffsetStringInfo(methodNameOffest_, trimMeth_), parametersType_, offestsTypes_,
                            parametersName_, offestsParams_, new OffsetStringInfo(modifierOffest_, modifier_),
                            new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                } else {
                    kind_ = MethodKind.STD_METHOD;
                    ov_ = new OverridableBlock(_context, new OffsetAccessInfo(accessOffest_, accessFct_),
                            new OffsetStringInfo(typeOffset_, retType_),
                            new OffsetStringInfo(methodNameOffest_, trimMeth_), parametersType_, offestsTypes_,
                            parametersName_, offestsParams_, new OffsetStringInfo(modifierOffest_, modifier_),
                            new OffsetsBlock(instructionRealLocation_, instructionLocation_));
                }
                ov_.setKind(kind_);
                br_ = ov_;
            } else {
                if (!ctor_) {
                    _input.getBadIndexes().add(_i);
                }
                br_ = new ConstructorBlock(new OffsetAccessInfo(accessOffest_, accessFct_),
                        new OffsetStringInfo(accessOffest_, EMPTY_STRING),
                        new OffsetStringInfo(accessOffest_, EMPTY_STRING), parametersType_, offestsTypes_,
                        parametersName_, offestsParams_,leftPar_, new OffsetsBlock(instructionRealLocation_, instructionLocation_));
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
            if (StringExpUtil.startsWithKeyWord(info_, keyWordStatic_)) {
                staticOffest_ = _i - found_.length() + delta_;
                static_ = true;
                String afterStatic_ = info_.substring(keyWordStatic_.length());
                delta_ += keyWordStatic_.length();
                delta_ += StringList.getFirstPrintableCharIndex(afterStatic_);
                info_ = afterStatic_.trim();
            }
            if (StringExpUtil.startsWithKeyWord(info_,keyWordFinal_)) {
                finalOffest_ = _i - found_.length() + delta_;
                final_ = true;
                String afterFinal_ = info_.substring(keyWordFinal_.length());
                delta_ += keyWordFinal_.length();
                delta_ += StringList.getFirstPrintableCharIndex(afterFinal_);
                info_ = afterFinal_.trim();
            }
            int typeOffest_ = _i - found_.length() + delta_;
            String declaringType_ = getFoundType(info_);
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

    private static StringBuilder fetchSymbol(String _afterModifier, int _from) {
        int len_ = _afterModifier.length();
        int j_ = _from;
        StringBuilder symbol_ = new StringBuilder();
        while (j_ < len_) {
            char currentChar_ = _afterModifier.charAt(j_);
            if (!isOperatorCharacter(currentChar_)) {
                //found space or import or return type
                break;
            }
            symbol_.append(currentChar_);
            j_++;
        }
        return symbol_;
    }

    private static Block processInstructionBlock(ContextEl _context, Ints _badIndexes,
                                                 int _instructionLocation,
                                                 int _instructionRealLocation, int _i, BracedBlock _currentParent, String _trimmedInstruction) {
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
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordBreak_)) {
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
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordContinue_)) {
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
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordReturn_)) {
            String exp_ = _trimmedInstruction.substring(keyWordReturn_.length());
            int expressionOffest_ = _instructionLocation + keyWordReturn_.length();
            if (!exp_.trim().isEmpty()) {
                expressionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            }
            br_ = new ReturnMethod(new OffsetStringInfo(expressionOffest_,exp_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordThrow_)) {
            String exp_ = _trimmedInstruction.substring(keyWordThrow_.length());
            int expressionOffest_ = _instructionLocation + keyWordThrow_.length();
            if (!exp_.trim().isEmpty()) {
                expressionOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            }
            br_ = new Throwing(new OffsetStringInfo(expressionOffest_,exp_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordCase_)) {
            String exp_ = _trimmedInstruction.substring(keyWordCase_.length());
            int valueOffest_ = _instructionLocation + keyWordCase_.length();
            valueOffest_ += StringList.getFirstPrintableCharIndex(exp_);
            br_ = new CaseCondition(
                    new OffsetStringInfo(valueOffest_, exp_.trim()),
                    new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            //if next after i starts with brace or not
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringList.quickEq(_trimmedInstruction,keyWordDefault_)) {
            br_ = new DefaultCondition(
                    new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordWhile_)) {
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
                br_ = new WhileCondition(new OffsetStringInfo(conditionOffest_, exp_.trim()),
                        new OffsetStringInfo(labelOff_, label_.trim()),
                        new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            }
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordCatch_)) {
            String info_ = _trimmedInstruction.substring(keyWordCatch_.length());
            int leftPar_ = info_.indexOf(BEGIN_CALLING);
            if (leftPar_ > -1) {
                int typeOffset_ = keyWordCatch_.length() + _instructionLocation + leftPar_+1;
                info_ = info_.substring(leftPar_+1);
                String declaringType_ = getFoundType(info_);
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
                br_ = new CatchEval(new OffsetStringInfo(typeOffset_, declaringType_.trim()),
                        new OffsetStringInfo(variableOffset_,variable_.trim()),
                        new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            } else {
                br_ = new NullCatchEval(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            }
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordIf_)) {
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
            br_ = new IfCondition(new OffsetStringInfo(conditionOffest_, exp_.trim()),
                    new OffsetStringInfo(labelOff_, label_.trim()),
                    new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordElseif_)) {
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
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordElse_)) {
            String afterElse_ = _trimmedInstruction.substring(keyWordElse_.length());
            String exp_ = afterElse_.trim();
            if (StringExpUtil.startsWithKeyWord(exp_,keyWordIf_)) {
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
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordDo_)) {
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
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordFinally_)) {
            br_ = new FinallyEval(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordTry_)) {
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
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordForeach_)) {
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
            String declaringType_ = getFoundType(exp_);
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
            if (StringList.isDollarWord(variableName_)) {
                br_ = new ForEachLoop(_context, new OffsetStringInfo(typeOffset_, declaringType_.trim()),
                        new OffsetStringInfo(varOffset_, variableName_),
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
                String declaringTypeSec_ = getFoundType(afterFirst_);
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
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordIter_)) {
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
            String declaringType_ = getFoundType(exp_);
            typeOffset_ += StringList.getFirstPrintableCharIndex(exp_);
            int varOffset_ = typeOffset_ + declaringType_.length();
            exp_ = exp_.substring(declaringType_.length());
            int eqIndex_ = exp_.indexOf(PART_SEPARATOR);
            if (eqIndex_ < 0) {
                _badIndexes.add(_i);
                return null;
            }
            String variable_ = exp_.substring(0, eqIndex_);
            int firstOff_ = StringList.getFirstPrintableCharIndex(variable_);
            varOffset_ += firstOff_;
            exp_ = exp_.substring(eqIndex_ + 1);
            int nextElt_ = getIndex(exp_, END_LINE);
            if (nextElt_ < 0) {
                _badIndexes.add(_i);
                return null;
            }
            int initOff_ = varOffset_ + variable_.length()-firstOff_+1;
            String init_ = exp_.substring(0, nextElt_);
            initOff_ += StringList.getFirstPrintableCharIndex(init_);
            exp_ = exp_.substring(init_.length()+1);
            nextElt_ = getIndex(exp_, END_LINE);
            if (nextElt_ < 0) {
                _badIndexes.add(_i);
                return null;
            }
            int toOff_ = initOff_ + init_.trim().length()+1;
            boolean eq_ = false;
            String to_ = exp_.substring(0, nextElt_);
            toOff_ += StringList.getFirstPrintableCharIndex(to_);
            int expOff_ = toOff_ + nextElt_;
            int stepOff_ = expOff_ + 1;
            if (nextElt_ + 1 >= exp_.length()) {
                _badIndexes.add(_i);
                return null;
            }
            if (exp_.charAt(nextElt_ + 1) == END_LINE) {
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
                    new OffsetStringInfo(initOff_,init_.trim()), new OffsetStringInfo(toOff_,to_.trim()),
                    new OffsetBooleanInfo(expOff_, eq_) , new OffsetStringInfo(stepOff_,step_.trim()), new OffsetStringInfo(indexClassOffest_,indexClassName_.trim()),
                    new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordFor_)) {
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
            boolean finalLocalVar_ = StringExpUtil.startsWithKeyWord(exp_.trim(), keyWordFinal_);
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
            int nextEltMut_ = getIndex(exp_, END_LINE);
            if (nextEltMut_ > -1) {
                int nextElt_ = getIndex(exp_, END_LINE);
                String init_ = exp_.substring(0, nextElt_);
                initOff_ += StringList.getFirstPrintableCharIndex(init_);
                exp_ = exp_.substring(init_.length()+1);
                int toOff_ = initOff_ + nextElt_;
                nextElt_ = getIndex(exp_, END_LINE);
                if (nextElt_ > -1) {
                    String to_ = exp_.substring(0, nextElt_);
                    int expOff_ = toOff_ + nextElt_;
                    toOff_ += StringList.getFirstPrintableCharIndex(to_);
                    int stepOff_ = expOff_ + 1;
                    exp_ = exp_.substring(nextElt_ + 1);
                    String step_ = exp_;
                    stepOff_ += StringList.getFirstPrintableCharIndex(step_);
                    label_ = label_.substring(lastPar_ + 1);
                    labelOff_ += getLabelOffset(label_);
                    br_ = new ForMutableIterativeLoop(_context,
                            new OffsetBooleanInfo(finalOffset_, finalLocalVar_),
                            new OffsetStringInfo(typeOffset_,declaringType_.trim()),
                            new OffsetStringInfo(initOff_,init_.trim()), new OffsetStringInfo(toOff_,to_.trim()),
                            new OffsetStringInfo(stepOff_,step_.trim()), new OffsetStringInfo(indexClassOffest_,indexClassName_.trim()),
                            new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                    _currentParent.appendChild(br_);
                    ok_ = true;
                }
            }
            if (!ok_) {
                int nextElt_ = getIndex(exp_,FOR_BLOCKS);
                if (nextElt_ > -1) {
                    String init_ = exp_.substring(0, nextElt_);
                    String variableName_ = init_.trim();
                    if (StringList.isDollarWord(variableName_)) {
                        exp_ = exp_.substring(init_.length()+1);
                        int varOffset_ = typeOffset_ + declaringType_.length();
                        varOffset_ += StringList.getFirstPrintableCharIndex(init_);
                        int expOffset_ = varOffset_;
                        expOffset_ += init_.length();
                        expOffset_ += StringList.getFirstPrintableCharIndex(exp_);
                        label_ = label_.substring(lastPar_ + 1);
                        labelOff_ += getLabelOffset(label_);
                        br_ = new ForEachLoop(_context, new OffsetStringInfo(typeOffset_, declaringType_.trim()),
                                new OffsetStringInfo(varOffset_, variableName_), new OffsetStringInfo(expOffset_, exp_.trim()),
                                new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                                new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                        _currentParent.appendChild(br_);
                    } else {
                        int nextIndexVar_ = variableName_.indexOf(',');
                        if (nextIndexVar_ >= 0) {
                            String firstVar_ = variableName_.substring(0, nextIndexVar_);
                            String afterFirst_ = variableName_.substring(nextIndexVar_+1);
                            String declaringTypeSec_ = getFoundType(afterFirst_);
                            String padSecVar_= afterFirst_.substring(declaringTypeSec_.length());
                            String secVar_ = padSecVar_.trim();
                            if (StringList.isDollarWord(secVar_)) {
                                int varOffset_ = typeOffset_ + declaringType_.length();
                                varOffset_ += StringList.getFirstPrintableCharIndex(init_);
                                int expOffset_ = varOffset_;
                                expOffset_ += init_.length();
                                int secType_ = varOffset_;
                                secType_ += nextIndexVar_+1;
                                int secVarOff_ = secType_;
                                secType_ += StringList.getFirstPrintableCharIndex(declaringTypeSec_);
                                secVarOff_ += declaringTypeSec_.length();
                                secVarOff_ += StringList.getFirstPrintableCharIndex(padSecVar_);
                                label_ = label_.substring(lastPar_ + 1);
                                labelOff_ += getLabelOffset(label_);
                                exp_ = exp_.substring(init_.length()+1);
                                expOffset_ += StringList.getFirstPrintableCharIndex(exp_);
                                br_ = new ForEachTable(_context,
                                        new OffsetStringInfo(typeOffset_, declaringType_.trim()), new OffsetStringInfo(varOffset_, firstVar_),
                                        new OffsetStringInfo(secType_, declaringTypeSec_.trim()), new OffsetStringInfo(secVarOff_, secVar_),
                                        new OffsetStringInfo(expOffset_, exp_.trim()), new OffsetStringInfo(indexClassOffest_, indexClassName_.trim()),
                                        new OffsetStringInfo(labelOff_, label_.trim()), new OffsetsBlock(_instructionRealLocation, _instructionLocation));
                                _currentParent.appendChild(br_);
                            }
                        }
                    }
                }
            }
            return br_;
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordSwitch_)) {
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
            return br_;
        }
        if (StringList.quickEq(_trimmedInstruction, keyWordStatic_)) {
            br_ = new StaticBlock(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            _currentParent.appendChild(br_);
            return br_;
        }
        if (_trimmedInstruction.isEmpty()) {
            if (_currentParent instanceof RootBlock) {
                br_ = new InstanceBlock(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            } else {
                br_ = new UnclassedBracedBlock(new OffsetsBlock(_instructionRealLocation, _instructionLocation));
            }
            _currentParent.appendChild(br_);
        }
        //Not an error
        return br_;
    }

    private static int getLabelOffset(String _label) {
        if (_label.isEmpty()) {
            return 0;
        }
        return StringList.getFirstPrintableCharIndex(_label);
    }

    private static String getFoundType(String _found) {
        ParsedType p_ = new ParsedType();
        p_.parse(_found);
        return p_.getInstruction().toString();
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

    private static String getDeclaringTypeInstr(String _found, KeyWords _options) {
        String keyWordNew_ = _options.getKeyWordNew();
        ParsedType p_ = new ParsedType();
        p_.parse(_found);
        if (p_.isOk(new CustList<String>(keyWordNew_))) {
            return p_.getInstruction().toString();
        }
        return "";
    }
    private static int getIndex(String _info, char _endLine) {
        return getIndex(0,_info,_endLine);
    }
    private static int getIndex(int _from,String _info, char _endLine) {
        int indexInstr_ = _from;
        int instrLen_ = _info.length();
        int localCallings_ = 0;
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
            if (localCallings_ == 0 && locChar_ == _endLine) {
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
                localCallings_++;
            }
            if (locChar_ == END_CALLING) {
                localCallings_--;
            }
            if (locChar_ == BEGIN_ARRAY) {
                localCallings_++;
            }
            if (locChar_ == END_ARRAY) {
                localCallings_--;
            }
            if (locChar_ == BEGIN_BLOCK) {
                localCallings_++;
            }
            if (locChar_ == END_BLOCK) {
                localCallings_--;
            }
            indexInstr_++;
        }
        return -1;
    }

    private static int afterComments(ContextEl _context,String _file, int _from) {
        int i_ = _from;
        int len_ = _file.length();
        CommentDelimiters current_ = null;
        while (i_ < len_) {
            char cur_ = _file.charAt(i_);
            if (current_ != null) {
                String endCom_ = getEndCom(_file,i_,current_);
                int length_ = endCom_.length();
                if (length_ > 0) {
                    i_ += length_;
                    current_ = null;
                    continue;
                }
                i_++;
                continue;
            }
            boolean skip_= false;
            for (CommentDelimiters c: _context.getComments()) {
                if (_file.startsWith(c.getBegin(),i_)) {
                    current_ = c;
                    i_ += c.getBegin().length();
                    skip_ = true;
                    break;
                }
            }
            if (skip_) {
                continue;
            }
            if (Character.isWhitespace(cur_)) {
                i_++;
                continue;
            }
            return i_;
        }
        return -1;
    }

    static int skipWhitespace(int _nextIndex, String _file) {
        int nextIndex_ = _nextIndex;
        int len_ = _file.length();
        while (nextIndex_ < len_) {
            char currentChar_ = _file.charAt(nextIndex_);
            if (!Character.isWhitespace(currentChar_)) {
                break;
            }
            nextIndex_ = nextIndex_ + 1;
        }
        return nextIndex_;
    }

    private static String getEndCom(String _file, int _i, CommentDelimiters _current) {
        String endCom_ = "";
        for (String e: _current.getEnd()) {
            if (_file.startsWith(e, _i)) {
                endCom_ = e;
                break;
            }
        }
        return endCom_;
    }
}
