package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InterfacesPart;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.IntMap;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FileResolver {

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
    private static final char END_CALLING = ')';
    private static final char PART_SEPARATOR = '=';
    private static final char ANNOT = '@';

    private FileResolver(){
    }

    public static void parseFile(FileBlock _block, String _file, AnalyzedPageEl _page) {
        CustList<SegmentStringPart> stringParts_ = _block.getStringParts();
        StringList importedTypes_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _file.length();
        int indexImport_ = 0;
        Ints badIndexes_ = new Ints();
        Ints offsetsImports_ = new Ints();
        int braces_ = 0;
        int parentheses_ = 0;
        ParsedInstruction parsedInstruction_ = new ParsedInstruction();
        parsedInstruction_.setInstructionLocation(indexImport_);
        ParseDelimitersState parsPars_ = new ParseDelimitersState(braces_,parentheses_);
        while (i_ < len_) {
            int next_ = tryIncrBegin(_block, _file, _page, i_, parsedInstruction_, parsPars_, badIndexes_);
            if (next_ <= i_) {
                break;
            }
            i_ = next_;
        }
        if (i_ >= len_) {
            badIndexes_.add(i_);
        }
        _block.getImports().addAllElts(importedTypes_);
        _block.getImportsOffset().addAllElts(offsetsImports_);
        if (!badIndexes_.isEmpty()) {
            bad(_block, _page, badIndexes_);
            return;
        }
        InputTypeCreation input_ = new InputTypeCreation();
        input_.setCont(new FileResolverContext(_page.getKeyWords(),_page.getStaticContext(), _page.getDefaultAccess(), _page.getPrimTypes(), _page.getAliasVoid()));
        input_.setNextIndex(i_);
        input_.setType(OuterBlockEnum.OUTER_TYPE);
        input_.setStringParts(stringParts_);
        input_.setFile(_block);
        remFile(_block, _file, _page, i_, input_);
    }
    private static int tryIncrBegin(FileBlock _block, String _file, AnalyzedPageEl _page, int _i,ParsedInstruction _parsedInstruction,ParseDelimitersState _parsPars,Ints _badIndexes) {
        KeyWords keyWords_ = _page.getKeyWords();
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
        CustList<SegmentStringPart> stringParts_ = _block.getStringParts();
        char currentChar_ = _file.charAt(_i);
        int until_ = strPartsBetweenTypes(stringParts_, _i);
        if (until_ > _i) {
            return until_;
        }
        _parsPars.parse(currentChar_,false);
        if (_parsPars.isExitLoop()) {
            _badIndexes.add(_i);
            return _i;
        }
        if (_parsPars.getParentheses() > 0) {
            return _i + 1;
        }
        StringBuilder str_ = _parsedInstruction.getBuilder();
        if (str_.toString().trim().isEmpty() && (StringExpUtil.startsWithKeyWord(_file, _i, keyWordPublic_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordOperator_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordProtected_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordPackage_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordPrivate_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordAbstract_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordAnnotation_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordClass_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordEnum_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordFinal_) || StringExpUtil.startsWithKeyWord(_file, _i, keyWordInterface_) || currentChar_ == ANNOT)) {
            return _i;
        }
        if (currentChar_ == END_IMPORTS) {
            _block.getImports().add(str_.toString());
            _block.getImportsOffset().add(_parsedInstruction.getInstructionLocation());
            str_.delete(0, str_.length());
        } else {
            if (!StringUtil.isWhitespace(currentChar_)) {
                _parsedInstruction.setInstructionLocation(setInstLocation(str_, _parsedInstruction.getInstructionLocation(), _i));
            }
            str_.append(currentChar_);
        }
        return _i + 1;
    }

    private static void remFile(FileBlock _block, String _file, AnalyzedPageEl _page, int _i, InputTypeCreation _input) {
        int len_ = _file.length();
        int i_ = _i;
        //the file is not trimmed empty
        while (true) {
            String def_ = defPkg(_file, i_, _input.getCont().getKeys(), _page.getDefaultPkg());
            ResultCreation res_ = createType(_file, _input, def_, _page.getPrimTypes(), _page.getAliasVoid());
            Ints badIndexes_ = _input.getBadIndexes();
            bad(_block, _page, badIndexes_);
            AbsBk block_ = res_.getBlock();
            if (block_ != null) {
                _block.appendChild(block_);
            }
            i_ = res_.getNextIndex();
            boolean ended_ = true;
            int braces_ = 0;
            int parentheses_ = 0;
            ParseDelimitersState parsPars_ = new ParseDelimitersState(braces_,parentheses_);
            while (i_ < len_) {
                char currentChar_ = _file.charAt(i_);
                int next_ = tryIncrBetween(_block, _file, _page, i_, parsPars_);
                if (next_ <= i_) {
                    if (StringExpUtil.isTypeLeafChar(currentChar_) || currentChar_ == ANNOT) {
                        ended_ = false;
                    }
                    break;
                }
                i_ = next_;
            }
            if (ended_) {
                return;
            }
            _input.setNextIndex(i_);
        }
    }
    private static int tryIncrBetween(FileBlock _block, String _file, AnalyzedPageEl _page, int _i, ParseDelimitersState _parsPars) {
        CustList<SegmentStringPart> stringParts_ = _block.getStringParts();
        char currentChar_ = _file.charAt(_i);
        int until_ = strPartsBetweenTypes(stringParts_, _i);
        if (until_ > _i) {
            return until_;
        }
        _parsPars.parse(currentChar_,false);
        if (_parsPars.isExitLoop()) {
            iterBad(_block, _page, _i);
            return _i;
        }
        if (_parsPars.getParentheses() > 0) {
            return _i + 1;
        }
        if (StringExpUtil.isTypeLeafChar(currentChar_) || currentChar_ == ANNOT) {
            return _i;
        }
        return _i + 1;
    }

    private static String defPkg(String _file, int _i, KeyWords _keyWords, String _defaultPkg) {
        String keyWordOperator_ = _keyWords.getKeyWordOperator();
        String def_;
        if (StringExpUtil.startsWithKeyWord(_file, _i, keyWordOperator_)) {
            def_ = _defaultPkg;
        } else {
            def_ = EMPTY_STRING;
        }
        return def_;
    }

    private static int strPartsBetweenTypes(CustList<SegmentStringPart> _stringParts, int _i) {
        int until_ = _i;
        for (SegmentStringPart s: _stringParts) {
            if (s.getBegin() == _i) {
                until_ = s.getEnd();
                break;
            }
        }
        return until_;
    }

    private static void bad(FileBlock _block, AnalyzedPageEl _page, Ints _badIndexes) {
        for (int i: _badIndexes) {
            iterBad(_block, _page, i);
        }
    }

    private static void iterBad(FileBlock _block, AnalyzedPageEl _page, int _i) {
        int indexErr_ = indexErr(_block, _i);
        FoundErrorInterpret b_ = err(_block, indexErr_, _page);
        GraphicErrorInterpret g_ = grErr(b_, indexErr_);
        _block.getErrorsFiles().getLi().add(g_);
    }

    private static int indexErr(FileBlock _block, int _i) {
        return Math.max(0, Math.min(_block.getLength() - 1, _i));
    }

    private static FoundErrorInterpret err(FileBlock _block, int _indexErr, AnalyzedPageEl _page) {
        FoundErrorInterpret b_ = new FoundErrorInterpret();
        b_.setFile(_block);
        b_.setIndexFile(_indexErr);
        //if empty file => add underlined space
        //else underline last char
        b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
        _page.addLocError(b_);
        return b_;
    }

    private static GraphicErrorInterpret grErr(FoundErrorInterpret _b, int _indexErr) {
        GraphicErrorInterpret g_ = new GraphicErrorInterpret(_b, _indexErr);
        g_.setLength(1);
        return g_;
    }

    static void appendEndComment(StringBuilder _str, String _endCom) {
        for (char c: _endCom.toCharArray()) {
            appendChar(_str, c);
        }
    }

    static void appendChar(StringBuilder _str, char _c) {
        if (_c < ' ') {
            _str.append(_c);
        } else {
            _str.append(' ');
        }
    }

    private static ResultCreation createType(String _file, InputTypeCreation _input, String _defaultPkg, PrimitiveTypes _primTypes, String _aliasVoid) {
        _input.setOffset(0);
        return processOuterTypeBody(_input, _defaultPkg, _file, _primTypes, _aliasVoid);
    }
    public static ResultCreation processOuterTypeBody(InputTypeCreation _input, String _pkgName,
                                                      String _file, PrimitiveTypes _primTypes, String _aliasVoid) {
        ResultCreation out_ = new ResultCreation();
        int len_ = _file.length();
        int instructionLocation_ = _input.getNextIndex();
        int braces_ = 0;
        int parentheses_ = 0;

        ParsedInstruction parsedInstruction_ = new ParsedInstruction();
        parsedInstruction_.setInstructionLocation(instructionLocation_);
        parsedInstruction_.setIndex(_input.getNextIndex());
        parsedInstruction_.setPackageName(_pkgName);
        ParseDelimitersState parsPars_ = new ParseDelimitersState(braces_,parentheses_);
        while (parsedInstruction_.getIndex() < len_) {
            if (exitLoop(_input,_file, parsedInstruction_,parsPars_,out_, _primTypes, _aliasVoid)) {
                break;
            }
        }
        if (out_.isOkType()) {
            parsedInstruction_.setIndex(parsedInstruction_.getIndex()+1);
        } else {
            addPossibleEmpty(parsedInstruction_.getCurrentParent());
            addBadIndex(_input, parsedInstruction_.getCurrentParent(), out_, len_+_input.getOffset());
        }
        out_.setNextIndex(parsedInstruction_.getIndex());
        return out_;
    }
    private static boolean exitLoop(InputTypeCreation _input,
                                    String _file, ParsedInstruction _parsedInstruction, ParseDelimitersState _parsPars, ResultCreation _out, PrimitiveTypes _primTypes, String _aliasVoid) {
        CustList<SegmentStringPart> stringParts_ = _input.getStringParts();
        int i_ = _parsedInstruction.getIndex();
        char currentChar_ = _file.charAt(i_);
        _parsedInstruction.setCurChar(currentChar_);
        int until_ = lookStrParts(_input.getOffset(), _file, stringParts_, _parsedInstruction, i_);
        if (until_ > i_) {
            _parsedInstruction.setIndex(until_);
            return false;
        }
        EndInstruction endInstr_ = endInstr(_input, _parsPars.getParentheses(), _parsedInstruction, currentChar_, _input.getCont().getKeys());
        _parsedInstruction.setEndInstruction(endInstr_);
        if (endInstr_ == EndInstruction.NONE) {
            setInstLocationIncr(_parsedInstruction, i_, currentChar_);
        }
        _parsPars.parse(currentChar_,endInstr_ != EndInstruction.NONE);
        if (_parsPars.isExitLoop()) {
            FileResolver.addBadIndex(_input, _parsedInstruction.getCurrentParent(), _out, i_+_input.getOffset());
            return true;
        }
        if (endInstr_ != EndInstruction.NONE) {
            AfterBuiltInstruction after_ = processInstruction(_out, _input, _parsedInstruction.getPackageName(), _parsedInstruction.getCurrentParent(),
                    _parsedInstruction, _primTypes, _aliasVoid);
            _parsedInstruction.setCurrentParent(after_.getParent());
            _parsedInstruction.setIndex(i_);
            _parsedInstruction.setPackageName(after_.getPackageName());
            _parsedInstruction.setInstructionLocation(i_);
            _parsedInstruction.clear();
            if (_parsPars.getBraces() == 0) {
                _out.setOkType(true);
                return true;
            }
        }

        _parsedInstruction.setIndex(i_ + 1);
        return false;
    }

    private static EndInstruction endInstr(InputTypeCreation _input, int _parentheses, ParsedInstruction _parsedInstruction, char _currentChar, KeyWords _keyWords) {
        String keyWordCase_ = _keyWords.getKeyWordCase();
        String keyWordDefault_ = _keyWords.getKeyWordDefault();
        EndInstruction endInstr_ = EndInstruction.NONE;
        if (_parentheses != 0) {
            return endInstr_;
        }
        if (_currentChar == END_LINE) {
            _parsedInstruction.parseAnnotation(_input, _keyWords);
            endInstr_ = EndInstruction.NO_DECLARE_TYPE;
            String str_ = _parsedInstruction.getBuilder().toString().trim();
            if (isCaseDefault(str_, keyWordCase_, keyWordDefault_)) {
                _parsedInstruction.setCurrentParent(possibleGoUp(_parsedInstruction.getCurrentParent()));
            }
        }
        if (_currentChar == END_BLOCK) {
            _parsedInstruction.parseAnnotation(_input, _keyWords);
            endInstr_ = EndInstruction.NO_DECLARE_TYPE;
        }
        if (_currentChar == BEGIN_BLOCK) {
            endInstr_ = endInstructionAdj(_input, _parsedInstruction.getCurrentParent(), _parsedInstruction, _keyWords);
        }
        if (canHaveElements(_parsedInstruction.getCurrentParent())) {
            if (_currentChar == BEGIN_TEMPLATE) {
                ((EnumBlock) _parsedInstruction.getCurrentParent()).setLtGt(((EnumBlock) _parsedInstruction.getCurrentParent()).getLtGt()+1);
            }
            if (_currentChar == END_TEMPLATE) {
                ((EnumBlock) _parsedInstruction.getCurrentParent()).setLtGt(((EnumBlock) _parsedInstruction.getCurrentParent()).getLtGt()-1);
            }
            if (_currentChar == SEP_ENUM_CONST && ((EnumBlock) _parsedInstruction.getCurrentParent()).getLtGt() == 0) {
                _parsedInstruction.parseAnnotation(_input, _keyWords);
                endInstr_ = EndInstruction.NO_DECLARE_TYPE;
            }
        }
        //End line
        return endInstr_;
    }

    private static int lookStrParts(int _offset, String _file, CustList<SegmentStringPart> _stringParts, ParsedInstruction _parsedInstruction, int _i) {
        int until_ = _i;
        for (SegmentStringPart s: _stringParts) {
            if (s.getBegin() == _offset + _i) {
                until_ = s.getEnd() - _offset;
                _parsedInstruction.getStringParts().add(s);
                appendChars(_file, _parsedInstruction, _i, until_);
                break;
            }
        }
        return until_;
    }

    private static void appendChars(String _file, ParsedInstruction _parsedInstruction, int _i, int _until) {
        for (int c = _i; c < _until; c++) {
            setInstLocationIncr(_parsedInstruction, c, _file.charAt(c));
        }
    }

    private static void setInstLocationIncr(ParsedInstruction _parsedInstruction, int _c, char _ch) {
        int instructionLocation_ = setInstLocation(_parsedInstruction.getBuilder(), _parsedInstruction.getInstructionLocation(), _c);
        _parsedInstruction.setInstructionLocation(instructionLocation_);
        _parsedInstruction.appendCh(_ch);
    }

    static String getEndCom(String _file, int _i, CommentDelimiters _current) {
        String endCom_ = "";
        for (String e: _current.getEnd()) {
            if (_file.startsWith(e, _i)) {
                endCom_ = e;
                break;
            }
        }
        return endCom_;
    }
    private static boolean isCaseDefault(String _str, String _keyWordCase, String _keyWordDefault) {
        return StringExpUtil.startsWithKeyWord(_str, _keyWordCase)
                || StringUtil.quickEq(_str, _keyWordDefault)
                || startsWithDefVar(_str, _keyWordDefault);
    }

    static int setInstLocation(StringBuilder _instruction, int _instructionLocation, int _i) {
        if (_instruction.length() == 0) {
            return _i;
        }
        return _instructionLocation;
    }

    private static void addBadIndex(InputTypeCreation _input, BracedBlock _currentParent, ResultCreation _root, int _index) {
        if (_currentParent != null) {
            if (_currentParent == _root.getBlock()) {
                _currentParent.getBadIndexesGlobal().add(_index);
            } else {
                _currentParent.getBadIndexes().add(_index);
            }
        } else {
            _input.getBadIndexes().add(_index);
        }
    }
    static void appendEnd(int _i, String _e, Ints _endComments) {
        if (_e.trim().isEmpty()) {
            _endComments.add(_i -2);
        } else {
            _endComments.add(_i -1);
        }
    }

    private static EndInstruction endInstructionAdj(InputTypeCreation _input, BracedBlock _parent, ParsedInstruction _instruction, KeyWords _keyWords) {
        EndInstruction endInstruction_ = endInstruction(_input, _parent, _instruction, _keyWords);
        if (endInstruction_ != EndInstruction.NONE) {
            _instruction.parseAnnotation(_input, _keyWords);
        }
        return endInstruction_;
    }
    private static EndInstruction endInstruction(InputTypeCreation _input, BracedBlock _parent, ParsedInstruction _instruction, KeyWords _keyWords) {
        String tr_ = _instruction.getBuilder().toString().trim();
        if (_parent == null) {
            return EndInstruction.DECLARE_TYPE;
        }
        if (tr_.isEmpty()) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (StringExpUtil.startsWithKeyWord(tr_, _keyWords.getKeyWordIntern())) {
            return EndInstruction.NONE;
        }
        if (canHaveElements(_parent)) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        String trTmp_ = tr_;
        if (ParsedAnnotations.startsWithAnnot(tr_, _keyWords.getKeyWordClass(), _keyWords.getKeyWordInterface())) {
            _instruction.parseAnnotation(_input, _keyWords);
            tr_ = _instruction.getAfter();
        }
        String word_ = getWord(getAccess(tr_, _keyWords), _keyWords);
        String afterAccess_ = tr_.substring(word_.length()).trim();
        String keyWordAnnotation_ = _keyWords.getKeyWordAnnotation();
        String keyWordClass_ = _keyWords.getKeyWordClass();
        String keyWordEnum_ = _keyWords.getKeyWordEnum();
        String keyWordInterface_ = _keyWords.getKeyWordInterface();
        String beforeQu_ = classModifier(_keyWords,afterAccess_);
        boolean dType_ = false;
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordClass_)) {
            beforeQu_ = beforeQu_.substring(keyWordClass_.length()).trim();
            dType_ = true;
        } else if (StringExpUtil.startsWithArobaseKeyWord(beforeQu_,keyWordClass_)) {
            beforeQu_ = beforeQu_.substring(keyWordClass_.length()+1).trim();
            dType_ = true;
        } else if (StringExpUtil.startsWithArobaseKeyWord(beforeQu_,keyWordInterface_)) {
            beforeQu_ = beforeQu_.substring(keyWordInterface_.length()+1).trim();
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
            return endInstType(beforeQu_);
        }
        return notAtype(_parent, tr_, _keyWords, trTmp_);
    }

    private static EndInstruction endInstType(String _beforeQu) {
        if (!StringExpUtil.nextCharIs(_beforeQu,0, _beforeQu.length(),'(')) {
            return EndInstruction.DECLARE_TYPE;
        }
        return EndInstruction.NONE;
    }

    private static EndInstruction notAtype(BracedBlock _parent, String _tr, KeyWords _keyWords, String _trTmp) {
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        if (_parent instanceof AnnotationBlock){
            return EndInstruction.NONE;
        }
        if (StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordIf()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordElse()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordElseif()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordSwitch()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordTry()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordCatch()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordFinally()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordFor()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordForeach()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordIter()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordDo()) || StringExpUtil.startsWithKeyWord(_trTmp, _keyWords.getKeyWordWhile()) || StringUtil.quickEq(_trTmp, keyWordStatic_)) {
            return EndInstruction.NO_DECLARE_TYPE;
        }
        if (!(_parent instanceof RootBlock)) {
            return EndInstruction.NONE;
        }
        return members(_tr, _keyWords);
    }

    private static String classModifier(KeyWords _keyWords, String _afterAccess) {
        String keyWordAbstract_ = _keyWords.getKeyWordAbstract();
        String keyWordFinal_ = _keyWords.getKeyWordFinal();
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        String beforeQu_ = _afterAccess;
        if (StringExpUtil.startsWithKeyWord(beforeQu_, keyWordAbstract_)) {
            beforeQu_ = beforeQu_.substring(keyWordAbstract_.length()).trim();
        }
        if (StringExpUtil.startsWithKeyWord(beforeQu_, keyWordStatic_)) {
            beforeQu_ = beforeQu_.substring(keyWordStatic_.length()).trim();
        }
        if (StringExpUtil.startsWithKeyWord(beforeQu_, keyWordFinal_)) {
            beforeQu_ = beforeQu_.substring(keyWordFinal_.length()).trim();
        }
        return beforeQu_;
    }

    private static EndInstruction members(String _tr, KeyWords _keyWords) {
        String keyWordAbstract_ = _keyWords.getKeyWordAbstract();
        String keyWordFinal_ = _keyWords.getKeyWordFinal();
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        String word_;
        if (StringExpUtil.startsWithKeyWord(_tr, _keyWords.getKeyWordOperator())) {
//            _instruction.parseAnnotation(_input,_page);
            return EndInstruction.NO_DECLARE_TYPE;
        }
        word_ = getWord(getAccess(_tr, _keyWords), _keyWords);
        String trimmedAfterAccess_ = _tr.substring(word_.length()).trim();
        boolean ctor_ = false;
        if (trimmedAfterAccess_.startsWith("(")) {
            ctor_ = true;
        } else {
            int leftPar_ = trimmedAfterAccess_.indexOf('(');
            if (leftPar_ > -1&&StringExpUtil.isTypeLeafPart(trimmedAfterAccess_.substring(0,leftPar_).trim())){
                ctor_ = true;
            }
        }
        if (ctor_) {
//            _instruction.parseAnnotation(_input,_page);
            return EndInstruction.NO_DECLARE_TYPE;
        }
        String keyWordNormal_ = _keyWords.getKeyWordNormal();
        String keyWordStaticCall_ = _keyWords.getKeyWordStaticCall();
        String keyWordThat_ = _keyWords.getKeyWordThat();
        StringList wordsSep_ = StringExpUtil.getDollarWordSeparators(trimmedAfterAccess_);
        int i_ = 0;
        int len_ = wordsSep_.size();
        while (i_ < len_) {
            String ws_ = wordsSep_.get(i_);
            if (StringExpUtil.isDollarWord(ws_) && !StringUtil.quickEq(ws_, keyWordNormal_) && !StringUtil.quickEq(ws_, keyWordAbstract_) && !StringUtil.quickEq(ws_, keyWordStatic_) && !StringUtil.quickEq(ws_, keyWordStaticCall_) && !StringUtil.quickEq(ws_, keyWordThat_) && !StringUtil.quickEq(ws_, keyWordFinal_)) {
                break;
            }
            i_++;
        }
        if (i_ >= len_) {
            return EndInstruction.NONE;
        }
        return fieldOrMethod(wordsSep_, i_);
    }

    private static EndInstruction fieldOrMethod(StringList _wordsSep, int _i) {
        String trAfterType_ = afterDeclaringType(_wordsSep, _i);
        int lenTrAfterType_ = trAfterType_.length();
        int indexTrAfterType_ = 0;
        while (indexTrAfterType_ < lenTrAfterType_) {
            char cur_ = trAfterType_.charAt(indexTrAfterType_);
            if (!StringExpUtil.isTypeLeafChar(cur_)) {
                break;
            }
            indexTrAfterType_++;
        }
        while (indexTrAfterType_ < lenTrAfterType_) {
            char cur_ = trAfterType_.charAt(indexTrAfterType_);
            if (!StringUtil.isWhitespace(cur_)) {
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
        String join_ = StringUtil.join(_wordsSep.mid(_index), "");
        String typeStr_ = getFoundType(join_);
        return join_.substring(typeStr_.length()).trim();
    }

    private static AfterBuiltInstruction processInstruction(ResultCreation _out, InputTypeCreation _input, String _pkgName,
                                                            BracedBlock _currentParent,
                                                            ParsedInstruction _parsedInstruction, PrimitiveTypes _primTypes, String _aliasVoid) {
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        BracedBlock currentParent_ = _currentParent;
        KeyWords keyWords_ = _input.getCont().getKeys();
        int instructionTrimLocation_ = _parsedInstruction.instLoc();
        if (currentParent_ == null) {
            return outer(_out, _input, _parsedInstruction, _pkgName, _input.getCont().getDef());
        }
        String trimmedInstruction_ = _parsedInstruction.getBuilder().toString().trim();
        if (StringExpUtil.startsWithKeyWord(trimmedInstruction_,keyWords_.getKeyWordIntern())) {
            return intern(_input, currentParent_, trimmedInstruction_, keyWords_, instructionTrimLocation_, _pkgName);
        }
        if (currentParent_ instanceof AnnotationBlock) {
            return annotation(_input, _pkgName, _parsedInstruction, currentParent_, trimmedInstruction_, _pkgName, _input.getCont().getDef());
        }
        if (canHaveElements(currentParent_)) {
            return element(_input, _pkgName, _parsedInstruction, currentParent_, _pkgName);
        }
        if (_parsedInstruction.getCurChar() == END_BLOCK) {
            return endBlock(_input, _parsedInstruction, currentParent_, trimmedInstruction_, _pkgName);
        }
        //currentChar_ != END_BLOCK
        AbsBk bl_ = processInstructionBlock(_input.getOffset(), _parsedInstruction, currentParent_, trimmedInstruction_, _primTypes, _input.getCont().getKeys());
        if (bl_ != null) {
            currentParent_ = possibleVisit(_parsedInstruction.getCurChar(), currentParent_, bl_);
            after_.setParent(currentParent_);
            after_.setPackageName(_pkgName);
            return after_;
        }
        if (_parsedInstruction.getEndInstruction() == EndInstruction.DECLARE_TYPE) {
            return types(_input, _pkgName, _parsedInstruction, currentParent_, _pkgName, _input.getCont().getDef());
        }
        if (currentParent_ instanceof RootBlock) {
            //fields, constructors or methods
            AbsBk br_ = processTypeMember(_parsedInstruction.getCurChar(), _parsedInstruction, _input, (RootBlock) currentParent_, _input.getCont().getDef(), _aliasVoid);
            currentParent_ = possibleVisit(_parsedInstruction.getCurChar(), currentParent_, br_);
            after_.setParent(currentParent_);
            after_.setPackageName(_pkgName);
            return after_;
        }
        return line(_input, _parsedInstruction, currentParent_, trimmedInstruction_, keyWords_, _pkgName);
    }

    private static AfterBuiltInstruction outer(ResultCreation _out, InputTypeCreation _input, ParsedInstruction _parsedInstruction, String _packageName, DefaultAccess _defaultAccess) {
        FileBlock file_ = _input.getFile();
        KeyWords keyWords_ = _input.getCont().getKeys();
        int instructionTrimLocation_ = _parsedInstruction.instLoc();
        if (_out.getBlock() != null) {
            AfterBuiltInstruction after_ = new AfterBuiltInstruction();
            _out.getBlock().getBadIndexes().add(_parsedInstruction.getIndex()+ _input.getOffset());
            _out.getBlock().getBadIndexesGlobal().add(_parsedInstruction.getIndex()+ _input.getOffset());
            return after_;
        }
        if (_input.getType() == OuterBlockEnum.SWITCH_METHOD) {
            SwitchMethodBlock typeBlock_ = new SwitchMethodBlock(instructionTrimLocation_ + _input.getOffset(), _input.getCont().getStat());
            anonHeader(_input, instructionTrimLocation_, typeBlock_);
            typeBlock_.setAnnotations(_input.getAnnotations());
            typeBlock_.getAnnotationsParams().addAllElts(_input.getAnnotationsParams());
            return afterAnonElt(_out, _packageName, file_, typeBlock_);
        }
        if (_input.getType() == OuterBlockEnum.ANON_FCT) {
            NamedCalledFunctionBlock typeBlock_ = new NamedCalledFunctionBlock(_input.getNextIndexBef() + _input.getOffset(),
                    instructionTrimLocation_ + _input.getOffset(), _input.getCont().getStat(), _input.getCont().getKeys());
            anonHeader(_input, instructionTrimLocation_, typeBlock_);
            typeBlock_.setAnnotations(_input.getAnnotations());
            typeBlock_.getAnnotationsParams().addAllElts(_input.getAnnotationsParams());
            return afterAnonElt(_out, _packageName, file_, typeBlock_);
        }
        if (_input.getType() == OuterBlockEnum.ANON_TYPE) {
            RootBlock typeBlock_ = new AnonymousTypeBlock(instructionTrimLocation_ + _input.getOffset(), _packageName,
                    new OffsetAccessInfo(instructionTrimLocation_ + _input.getOffset(), AccessEnum.PUBLIC), "", new IntMap<String>(),
                    instructionTrimLocation_ + _input.getOffset(), _input.getGeneratedId());
            typeHeader(typeBlock_, instructionTrimLocation_, _input, 1);
            typeBlock_.setNameLength(1);
            typeBlock_.setAnnotations(_input.getAnnotations());
            return afterAnonElt(_out, _packageName, file_, typeBlock_);
        }
        String keyWordOperator_ = keyWords_.getKeyWordOperator();
        if (StringExpUtil.startsWithKeyWord(_parsedInstruction.getAfter(), keyWordOperator_)) {
            ResultParsedAnnots annotationsTypes_ = _parsedInstruction.getAnnotationsTypes();
            Ints badIndexes_ = _input.getBadIndexes();
            String afterAccessType_ = _parsedInstruction.getAfter();
            int nextIndex_ = _parsedInstruction.getAfterOffset();
            AfterBuiltInstruction after_ = new AfterBuiltInstruction();
            nextIndex_ += keyWordOperator_.length();
            String afterOper_ = afterAccessType_.substring(keyWordOperator_.length());
            int offAfterOper_ = StringUtil.getFirstPrintableCharIndex(afterOper_);
            nextIndex_ += offAfterOper_;
            int symbolIndex_ = nextIndex_;
            String trAfterOper_ = afterOper_.trim();
            StringBuilder symbol_ = fetchSymbol(trAfterOper_);
            nextIndex_ += symbol_.length();
            String afterSymbol_ = trAfterOper_.substring(symbol_.length());
            int offAfterSymbol_ = StringUtil.getFirstPrintableCharIndex(afterSymbol_);
            nextIndex_ += offAfterSymbol_;
            String trAfterSymbol_ = afterSymbol_.trim();
            ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _input.getOffset(), trAfterSymbol_);
            StringList importedTypes_ = p_.getImportedTypes();
            Ints offsetsImports_ = p_.getOffsetsImports();
            String afterImports_ = p_.getNextPart();
            nextIndex_ = p_.getOffset();
            String info_ = afterImports_;
            int typeOffset_ = nextIndex_;
            String keyWordThat_ = keyWords_.getKeyWordThat();
            boolean retRef_ = false;
            if (StringExpUtil.startsWithKeyWord(info_,keyWordThat_)) {
                info_ = info_.substring(keyWordThat_.length());
                typeOffset_ += StringExpUtil.getOffset(info_);
                typeOffset_ += keyWordThat_.length();
                info_ = info_.trim();
                retRef_ = true;
            }
            String declaringType_ = getFoundType(info_);
            int declTypeLen_ = declaringType_.length();
            String afterType_ = info_.substring(declTypeLen_);
            int afterTypeOff_ = StringUtil.getFirstPrintableCharIndex(afterType_);
            info_ = afterType_.trim();
            int leftParIndex_ = info_.indexOf(BEGIN_CALLING);
            if (leftParIndex_ < 0) {
                badIndexes_.add(nextIndex_);
            }
            String afterMethodName_ = info_.substring(leftParIndex_ + 1);
            int paramOffest_ = typeOffset_ + declTypeLen_ + afterTypeOff_ + 1 + StringUtil.getFirstPrintableCharIndex(afterMethodName_);
            info_ = afterMethodName_.trim();
            ParsedFctHeader parseHeader_ = new ParsedFctHeader();
            parseHeader_.parse(_parsedInstruction.getStringParts(), info_, paramOffest_ + _input.getOffset(), _input.getCont().getKeys());
            parseHeader_.setRetRef(retRef_);
            CustList<ResultParsedAnnots> annotationsParams_ = parseHeader_.getAnnotationsParams();
            boolean ok_ = parseHeader_.isOk();
            OperatorBlock currentParent_ = new OperatorBlock(parseHeader_, new OffsetStringInfo(typeOffset_ + _input.getOffset(), declaringType_.trim()),
                    new OffsetStringInfo(symbolIndex_ + _input.getOffset(), symbol_.toString().trim()),
                    nextIndex_ + _input.getOffset());
            currentParent_.getAnnotationsParams().addAllElts(annotationsParams_);
            currentParent_.getImports().addAllElts(importedTypes_);
            currentParent_.getImportsOffset().addAllElts(offsetsImports_);
            currentParent_.setAnnotations(annotationsTypes_);
            currentParent_.setFile(file_);
            _out.setBlock(currentParent_);
            if (!ok_) {
                currentParent_.getBadIndexesGlobal().add(_parsedInstruction.getIndex()+ _input.getOffset());
            }
            after_.setParent(currentParent_);
            after_.setPackageName(_packageName);
            return after_;
        }
        return outerType(_out, _input, _parsedInstruction, _packageName, _defaultAccess);
    }

    private static AfterBuiltInstruction afterAnonElt(ResultCreation _out, String _packageName, FileBlock _file, BracedBlock _typeBlock) {
        _typeBlock.setFile(_file);
        _out.setBlock(_typeBlock);
        return afterIns(_packageName, _typeBlock);
    }

    private static AfterBuiltInstruction afterIns(String _packageName, BracedBlock _typeBlock) {
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        after_.setParent(_typeBlock);
        after_.setPackageName(_packageName);
        return after_;
    }

    private static void typeHeader(RootBlock _typeBlock, int _instructionTrimLocation, InputTypeCreation _input, int _lengthHeader) {
        _typeBlock.setBegin(_instructionTrimLocation + _input.getOffset());
        _typeBlock.setLengthHeader(_lengthHeader);
    }

    private static void anonHeader(InputTypeCreation _input, int _instructionTrimLocation, AbsBk _typeBlock) {
        _typeBlock.setBegin(_instructionTrimLocation + _input.getOffset());
        _typeBlock.setLengthHeader(1);
    }

    private static AfterBuiltInstruction outerType(ResultCreation _out, InputTypeCreation _input, ParsedInstruction _parsedInstruction, String _packageName, DefaultAccess _defaultAccess) {
        FileBlock file_ = _input.getFile();
        KeyWords keyWords_ = _input.getCont().getKeys();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        int instructionTrimLocation_ = _parsedInstruction.instLoc();
        ResultParsedAnnots annotationsTypes_ = _parsedInstruction.getAnnotationsTypes();
        String afterAccessType_ = _parsedInstruction.getAfter();
        int accessOffsetType_ = _parsedInstruction.getAfterOffset();
        int nextIndex_ = accessOffsetType_;
        AccessEnum access_ = _defaultAccess.getAccOuter();
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordAnnotation_ = keyWords_.getKeyWordAnnotation();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordEnum_ = keyWords_.getKeyWordEnum();
        String keyWordInterface_ = keyWords_.getKeyWordInterface();
        AccessEnum accessFct_ = getAccess(afterAccessType_, keyWords_);
        String word_ = getWord(accessFct_, keyWords_);
        boolean okHeader_ = true;
        if (!word_.isEmpty()) {
            nextIndex_ += word_.length();
            String afterAccess_ = afterAccessType_.substring(word_.length());
            nextIndex_ += StringUtil.getFirstPrintableCharIndex(afterAccess_);
            afterAccessType_ = afterAccess_.trim();
            access_ = accessFct_;
        } else if (StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordAbstract_) || StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordFinal_) || StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordClass_) || StringExpUtil.startsWithArobaseKeyWord(afterAccessType_, keyWordClass_) || StringExpUtil.startsWithArobaseKeyWord(afterAccessType_, keyWordInterface_) || StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordInterface_) || StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordEnum_) || StringExpUtil.startsWithKeyWord(afterAccessType_, keyWordAnnotation_)) {
            access_ = _defaultAccess.getAccOuter();
        } else {
            //ERROR
            okHeader_ = false;
        }
        boolean abstractType_ = false;
        boolean finalType_ = false;
        String beforeQu_ = afterAccessType_;
        if (StringExpUtil.startsWithKeyWord(beforeQu_, keyWordAbstract_)) {
            abstractType_ = true;
            nextIndex_ = nextIndex_ + keyWordAbstract_.length();
            String afterAccess_ = beforeQu_.substring(keyWordAbstract_.length());
            nextIndex_ += StringUtil.getFirstPrintableCharIndex(afterAccess_);
            beforeQu_ = afterAccess_.trim();
        }
        if (StringExpUtil.startsWithKeyWord(beforeQu_, keyWordFinal_)) {
            finalType_ = true;
            nextIndex_ = nextIndex_ + keyWordFinal_.length();
            String afterAccess_ = beforeQu_.substring(keyWordFinal_.length());
            nextIndex_ += StringUtil.getFirstPrintableCharIndex(afterAccess_);
            beforeQu_ = afterAccess_.trim();
        }
        int categoryOffset_ = nextIndex_;
        String type_ = typeKind(_input.getCont().getKeys(), beforeQu_);
        boolean okCat_ = okHeader_;
        if (!type_.isEmpty()) {
            nextIndex_ = nextIndex_ + type_.length();
            String afterAccess_ = beforeQu_.substring(type_.length());
            nextIndex_ += StringUtil.getFirstPrintableCharIndex(afterAccess_);
            beforeQu_ = afterAccess_.trim();
        } else {
            //ERROR
            okCat_ = false;
        }
        ParsedImportedTypes p_ = new ParsedImportedTypes(nextIndex_, _input.getOffset(), beforeQu_);
        String afterImports_ = p_.getNextPart();
        int nextIndexImp_ = p_.getOffset();
        //insert interfaces static initialization for class and enums
        InterfacesPart interfacesPart_ = parseInterfaces(_input, type_, afterImports_, nextIndexImp_);
        InheritingPart inh_ = parseInherits(_input, afterImports_, nextIndexImp_, interfacesPart_.getLocIndex());
        String typeName_ = inh_.getTypeName();
        int lastDot_ = typeName_.lastIndexOf(PKG);
        String packageName_ = pkgName(_packageName, typeName_, lastDot_);
        String baseName_ = baseName(typeName_, lastDot_, packageName_);
        if (!okCat_) {
            int intsOff_ = nextIndexImp_ + _input.getOffset();
            RootErrorBlock typeBlock_;
            typeBlock_ = new RootErrorBlock(inh_.getBeginDefinition(), baseName_, packageName_,
                    new OffsetAccessInfo(accessOffsetType_+ _input.getOffset(), access_) , inh_.getTempDef(), inh_.getSuperTypes(), instructionTrimLocation_ + _input.getOffset());
            typeBlock_.setCategoryOffset(categoryOffset_+ _input.getOffset());
            typeBlock_.setupOffsets(baseName_, packageName_);
            typeHeader(typeBlock_, _parsedInstruction.getIndex(), _input, 1);
            badIndexTypes(intsOff_, interfacesPart_.isOk(), typeBlock_);
            initTypeSupp(file_, annotationsTypes_,  p_.getImportedTypes(), p_.getOffsetsImports(), interfacesPart_, typeBlock_,packageName_);
            return afterType(_out, packageName_, typeBlock_);
        }
        StringList importedTypes_ = p_.getImportedTypes();
        Ints offsetsImports_ = p_.getOffsetsImports();
        int intsOff_ = nextIndexImp_ + _input.getOffset();
        boolean okType_ = interfacesPart_.isOk();
        IntMap<String> superTypes_ = inh_.getSuperTypes();
        String tempDef_ = inh_.getTempDef();
        int beginDefinition_ = inh_.getBeginDefinition();
        RootBlock typeBlock_;
        if (StringUtil.quickEq(type_, keyWordEnum_)) {
            typeBlock_ = new EnumBlock(beginDefinition_, baseName_, packageName_,
                    new OffsetAccessInfo(accessOffsetType_+ _input.getOffset(), access_) , tempDef_, superTypes_,  instructionTrimLocation_ + _input.getOffset());
        } else if (StringUtil.quickEq(type_, keyWordClass_)) {
            typeBlock_ = new ClassBlock(beginDefinition_, baseName_, packageName_,
                    new OffsetAccessInfo(accessOffsetType_ + _input.getOffset(), access_), tempDef_, superTypes_, finalType_, abstractType_, true,
                    instructionTrimLocation_ + _input.getOffset());
        } else if (StringUtil.quickEq(type_, "@" + keyWordClass_)) {
            typeBlock_ = new RecordBlock(false, beginDefinition_, baseName_, packageName_,
                    new OffsetAccessInfo(accessOffsetType_ + _input.getOffset(), access_), tempDef_, superTypes_, true,
                    instructionTrimLocation_ + _input.getOffset());
        } else if (StringUtil.quickEq(type_, "@" + keyWordInterface_)) {
            typeBlock_ = new RecordBlock(true, beginDefinition_, baseName_, packageName_,
                    new OffsetAccessInfo(accessOffsetType_ + _input.getOffset(), access_), tempDef_, superTypes_, true,
                    instructionTrimLocation_ + _input.getOffset());
        } else if (StringUtil.quickEq(type_, keyWordInterface_)) {
            typeBlock_ = new InterfaceBlock(beginDefinition_, baseName_, packageName_,
                    new OffsetAccessInfo(accessOffsetType_ + _input.getOffset(), access_), tempDef_, superTypes_, true, instructionTrimLocation_ + _input.getOffset());
        } else {
            typeBlock_ = new AnnotationBlock(beginDefinition_, baseName_, packageName_,
                    new OffsetAccessInfo(accessOffsetType_ + _input.getOffset(), access_), tempDef_, superTypes_, instructionTrimLocation_ + _input.getOffset());
        }
        typeBlock_.setupOffsets(baseName_, packageName_);
        normalHeader(_input, categoryOffset_, type_, typeBlock_);
        badIndexTypes(intsOff_, okType_, typeBlock_);
        initTypeSupp(file_, annotationsTypes_, importedTypes_, offsetsImports_, interfacesPart_, typeBlock_,packageName_);
        return afterType(_out, packageName_, typeBlock_);
    }

    private static String typeKind(KeyWords _keyWords, String _beforeQu) {
        String keyWordAnnotation_ = _keyWords.getKeyWordAnnotation();
        String keyWordClass_ = _keyWords.getKeyWordClass();
        String keyWordEnum_ = _keyWords.getKeyWordEnum();
        String keyWordInterface_ = _keyWords.getKeyWordInterface();
        String type_ = "";
        if (StringExpUtil.startsWithKeyWord(_beforeQu, keyWordClass_)) {
            type_ = keyWordClass_;
        } else if (StringExpUtil.startsWithArobaseKeyWord(_beforeQu, keyWordClass_)) {
            type_ = "@"+ keyWordClass_;
        } else if (StringExpUtil.startsWithArobaseKeyWord(_beforeQu, keyWordInterface_)) {
            type_ = "@"+ keyWordInterface_;
        } else if (StringExpUtil.startsWithKeyWord(_beforeQu, keyWordEnum_)) {
            type_ = keyWordEnum_;
        } else if (StringExpUtil.startsWithKeyWord(_beforeQu, keyWordInterface_)) {
            type_ = keyWordInterface_;
        } else if (StringExpUtil.startsWithKeyWord(_beforeQu, keyWordAnnotation_)) {
            type_ = keyWordAnnotation_;
        }
        return type_;
    }

    private static InheritingPart parseInherits(InputTypeCreation _input, String _afterImports, int _nextIndexImp, int _afterInterfaces) {
        InheritingPart inh_ = new InheritingPart(_afterInterfaces, _afterImports.substring(_afterInterfaces - _nextIndexImp));
        inh_.parse(_input.getOffset());
        return inh_;
    }

    private static InterfacesPart parseInterfaces(InputTypeCreation _input, String _type, String _afterImports, int _nextIndexImp) {
        InterfacesPart interfacesPart_ = new InterfacesPart(_afterImports, _nextIndexImp);
        interfacesPart_.parse(_input.getCont().getKeys(), _type, _nextIndexImp, _input.getOffset());
        return interfacesPart_;
    }

    private static AfterBuiltInstruction afterType(ResultCreation _out, String _packageName, RootBlock _typeBlock) {
        _out.setBlock(_typeBlock);
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        after_.setParent(_typeBlock);
        after_.setPackageName(_packageName);
        return after_;
    }

    private static void badIndexTypes(int _intsOff, boolean _okType, RootBlock _typeBlock) {
        if (!_okType) {
            _typeBlock.getBadIndexes().add(_intsOff);
        }
    }

    private static void normalHeader(InputTypeCreation _input, int _categoryOffset, String _type, RootBlock _typeBlock) {
        typeHeader(_typeBlock, _categoryOffset, _input, _type.length());
    }

    private static void initTypeSupp(FileBlock _file, ResultParsedAnnots _annotationsTypes, StringList _importedTypes, Ints _offsetsImports, InterfacesPart _interfacesPart, RootBlock _typeBlock, String _packageName) {
        _typeBlock.getImports().addAllElts(_importedTypes);
        _typeBlock.getImportsOffset().addAllElts(_offsetsImports);
        _typeBlock.getStaticInitInterfaces().addAllElts(_interfacesPart.getStaticInitInterfaces());
        _typeBlock.getStaticInitInterfacesOffset().addAllElts(_interfacesPart.getStaticInitInterfacesOffset());
        _typeBlock.getInstInitInterfaces().addAllElts(_interfacesPart.getInstInitInterfaces());
        _typeBlock.getInstInitInterfacesOffset().addAllElts(_interfacesPart.getInstInitInterfacesOffset());
        _typeBlock.setAnnotations(_annotationsTypes);
        _typeBlock.setFile(_file);
        _file.getPackages().add(StringExpUtil.removeDottedSpaces(_packageName));
        int indexDotPkg_ = Math.max(0, _packageName.indexOf('.'));
        _file.getBasePackages().add(StringExpUtil.removeDottedSpaces(_packageName.substring(0,indexDotPkg_)));
    }

    private static String baseName(String _typeName, int _lastDot, String _packageName) {
        String baseName_;
        if (_lastDot >= 0) {
            baseName_ = _typeName.substring(_lastDot + 1);
        } else {
            baseName_ = _typeName;
        }
        if (_lastDot >= 0&& _packageName.isEmpty()) {
            baseName_ = _typeName.substring(_lastDot);
        }
        return baseName_;
    }

    private static String pkgName(String _packageName, String _typeName, int _lastDot) {
        String packageName_;
        if (_lastDot >= 0) {
            packageName_ = _typeName.substring(0, _lastDot);
        } else {
            packageName_ = _packageName;
        }
        return packageName_;
    }

    private static AfterBuiltInstruction intern(InputTypeCreation _input, BracedBlock _currentParent, String _trimmedInstruction, KeyWords _keyWords, int _instructionTrimLocation, String _packageName) {
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        String exp_ = _trimmedInstruction.substring(_keyWords.getKeyWordIntern().length());
        int internOffest_ = _instructionTrimLocation + _keyWords.getKeyWordIntern().length();
        int lastPar_ = exp_.lastIndexOf('}');
        int beg_ = exp_.indexOf('{');
        boolean ok_ = false;
        if (beg_ >= 0 && lastPar_ >= beg_ + 1) {
            internOffest_ += beg_ +1;
            exp_ = exp_.substring(beg_ +1,lastPar_);
            internOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
            exp_ = exp_.trim();
            ok_ = true;
        }
        InternOverrideBlock int_ = new InternOverrideBlock( _instructionTrimLocation + _input.getOffset(),exp_, internOffest_+ _input.getOffset());
        if (!ok_) {
            int_.getBadIndexes().add(_instructionTrimLocation + 1+ _input.getOffset());
        }
        int_.setBegin(_instructionTrimLocation + _input.getOffset());
        int_.setLengthHeader(_keyWords.getKeyWordIntern().length());
        _currentParent.appendChild(int_);
        after_.setParent(_currentParent);
        after_.setPackageName(_packageName);
        return after_;
    }

    private static AfterBuiltInstruction annotation(InputTypeCreation _input, String _pkgName, ParsedInstruction _parsedInstruction, BracedBlock _currentParent, String _trimmedInstruction, String _packageName, DefaultAccess _defaultAccess) {
        String keyWordFinal_ = _input.getCont().getKeys().getKeyWordFinal();
        int instructionTrimLocation_ = _parsedInstruction.instLoc();
        AbsBk br_ = null;
        if (_trimmedInstruction.isEmpty()) {
            //implicit static block
            if (_parsedInstruction.getCurChar() != END_BLOCK) {
                br_ = new StaticBlock(instructionTrimLocation_ + _input.getOffset());
                int initNb_ = ((RootBlock) _currentParent).getCountInit();
                ((InitBlock) br_).setNumber(initNb_);
                ((StaticBlock) br_).setStaticNb(((RootBlock) _currentParent).getStaticBlocks().size());
                ((RootBlock) _currentParent).getStaticBlocks().add((StaticBlock) br_);
                br_.setBegin(_parsedInstruction.getIndex()+ _input.getOffset());
                br_.setLengthHeader(1);
                _currentParent.appendChild(br_);
            }
            return endAnnot(_input, _parsedInstruction, _trimmedInstruction, _packageName, _currentParent, br_);
        }
        if (_parsedInstruction.getEndInstruction() == EndInstruction.DECLARE_TYPE) {
            RootBlock built_ = processTypeHeader(_input, _pkgName,true,
                    _parsedInstruction,
                    _defaultAccess.getAccessInner(_currentParent).getAccInners());
            _currentParent.appendChild(built_);
            built_.setParentType((AnnotationBlock) _currentParent);
            ((AnnotationBlock) _currentParent).getChildrenRootBlocks().add(built_);
            br_ = built_;
            return endAnnot(_input, _parsedInstruction, _trimmedInstruction, _packageName, _currentParent, br_);
        }
        ResultParsedAnnots annotations_ = _parsedInstruction.getAnnotationsTypes();
        int typeOffset_ = _parsedInstruction.getAfterOffset();
        String found_ = _parsedInstruction.getAfter();
        String infoModifiers_ = found_.trim();
        int finalOff_ = 0;
        boolean final_ = false;
        boolean meth_ = true;
        if (StringExpUtil.startsWithKeyWord(infoModifiers_, keyWordFinal_)) {
            int lenLoc_ = keyWordFinal_.length();
            String sub_ = infoModifiers_.substring(lenLoc_);
            int deltaSec_ = StringUtil.getFirstPrintableCharIndex(sub_);
            int deltaFinal_ = lenLoc_ + deltaSec_;
            finalOff_ = typeOffset_;
            found_ = sub_.substring(deltaSec_);
            final_ = true;
            meth_ = false;
            typeOffset_ += deltaFinal_;
        }
        String declaringType_ = getFoundType(found_).trim();
        found_ = found_.substring(declaringType_.length());
        int offAfterType_ = StringExpUtil.getOffset(found_);
        found_ = found_.trim();
        boolean notMethod_ = isNotMethod(found_);
        if (notMethod_) {
            meth_ = false;
        }
        int fieldNameOffest_ = typeOffset_+declaringType_.length() + offAfterType_;
        if (!meth_) {
            FieldBlock field_ = new FieldBlock((RootBlock) _currentParent,
                    new OffsetAccessInfo(-1, AccessEnum.PUBLIC),
                    new OffsetBooleanInfo(-1, true),
                    new OffsetBooleanInfo(finalOff_+ _input.getOffset(), final_),
                    new OffsetStringInfo(typeOffset_+ _input.getOffset(), declaringType_),
                    new OffsetStringInfo(fieldNameOffest_+ _input.getOffset(), found_),
                     instructionTrimLocation_ + _input.getOffset());
            field_.setAnnotations(annotations_);
            field_.setFieldNumber(((RootBlock) _currentParent).getFieldsBlocks().size());
            field_.getRes().partsAbsol(_parsedInstruction.getStringParts());
            ((RootBlock) _currentParent).getFieldsBlocks().add(field_);
            br_ = field_;
            br_.setBegin(_parsedInstruction.getIndex()+ _input.getOffset());
            br_.setLengthHeader(1);
            _currentParent.appendChild(br_);
            return endAnnot(_input, _parsedInstruction, _trimmedInstruction, _packageName, _currentParent, br_);
        }
        int indexBeginCalling_ = found_.indexOf(BEGIN_CALLING);
        String fieldName_ = found_.substring(0, indexBeginCalling_);
        int rightPar_ = found_.indexOf(END_CALLING, indexBeginCalling_);
        int expressionOffest_;
        String expression_;
        if (rightPar_ > -1) {
            expression_ = found_.substring(rightPar_ + 1);
            expressionOffest_ = fieldNameOffest_ + rightPar_ + 1;
        } else {
            expression_ = found_.substring(fieldName_.length());
            expressionOffest_ = fieldNameOffest_ + fieldName_.length();
        }
        if (!expression_.trim().isEmpty()) {
            expressionOffest_ += StringUtil.getFirstPrintableCharIndex(expression_);
        }
        ParsedFctHeader header_ = new ParsedFctHeader();
        header_.setRetRef(false);
        NamedCalledFunctionBlock annMeth_ = new NamedCalledFunctionBlock(
                header_,
                new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                new OffsetStringInfo(typeOffset_ + _input.getOffset(), declaringType_),
                new OffsetStringInfo(expressionOffest_ + _input.getOffset(), expression_.trim()),
                new OffsetStringInfo(fieldNameOffest_ + _input.getOffset(), fieldName_.trim()),
                instructionTrimLocation_ + _input.getOffset(), rightPar_ - offAfterType_);
        annMeth_.setNameNumber(((RootBlock) _currentParent).getAnnotationsMethodsBlocks().size());
        annMeth_.getRes().partsAbsol(_parsedInstruction.getStringParts());
        ((RootBlock) _currentParent).getAnnotationsMethodsBlocks().add(annMeth_);
        if (rightPar_ < indexBeginCalling_ || !found_.substring(indexBeginCalling_ + 1, rightPar_).trim().isEmpty()) {
            annMeth_.setKo();
        }
        annMeth_.setAnnotations(annotations_);
        br_ = annMeth_;
        br_.setBegin(_parsedInstruction.getIndex()+ _input.getOffset());
        br_.setLengthHeader(1);
        _currentParent.appendChild(br_);
        return endAnnot(_input, _parsedInstruction, _trimmedInstruction, _packageName, _currentParent, br_);
    }

    private static boolean isNotMethod(String _found) {
        int lenAfterModifiers_ = _found.length();
        int indexMod_ = 0;
        while (indexMod_ < lenAfterModifiers_) {
            char cur_ = _found.charAt(indexMod_);
            if (!StringExpUtil.isTypeLeafChar(cur_)) {
                break;
            }
            indexMod_++;
        }
        while (indexMod_ < lenAfterModifiers_) {
            char cur_ = _found.charAt(indexMod_);
            if (!StringUtil.isWhitespace(cur_)) {
                break;
            }
            indexMod_++;
        }
        return _found.indexOf(BEGIN_CALLING, indexMod_) != indexMod_;
    }

    private static AfterBuiltInstruction endAnnot(InputTypeCreation _input, ParsedInstruction _parsedInstruction, String _trimmedInstruction, String _packageName, BracedBlock _currentParent, AbsBk _br) {
        int instructionTrimLocation_ = _parsedInstruction.instLoc();
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        BracedBlock currentParent_ = _currentParent;
        if (_parsedInstruction.getCurChar() == END_BLOCK) {
            if (!_trimmedInstruction.isEmpty()) {
                Line br_ = new Line(new OffsetStringInfo(instructionTrimLocation_ + _input.getOffset(), _trimmedInstruction), instructionTrimLocation_ + _input.getOffset());
                br_.getBadIndexes().add(_parsedInstruction.getIndex()+ _input.getOffset());
                br_.setBegin(_parsedInstruction.getIndex()+ _input.getOffset());
                br_.setLengthHeader(1);
                br_.getRes().partsAbsol(_parsedInstruction.getStringParts());
                currentParent_.appendChild(br_);
            }
            currentParent_ = possibleEmptyGoUp(currentParent_);
        } else {
            currentParent_ = possibleVisit(_parsedInstruction.getCurChar(), currentParent_, _br);
        }
        after_.setParent(currentParent_);
        after_.setPackageName(_packageName);
        return after_;
    }

    private static AfterBuiltInstruction element(InputTypeCreation _input, String _pkgName, ParsedInstruction _parsedInstruction, BracedBlock _currentParent, String _packageName) {
        BracedBlock currentParent_ = _currentParent;
        int instructionTrimLocation_ = _parsedInstruction.instLoc();
        AbsBk br_;
        char curChar_ = _parsedInstruction.getCurChar();
        if (_parsedInstruction.getFirstPrIndex() <= -1) {
            tryAllow((EnumBlock) currentParent_, curChar_);
            return endElement(_packageName, currentParent_, curChar_);
        }
        int fieldOffest_ = _parsedInstruction.getAfterOffset();
        String found_ = _parsedInstruction.getAfter();
        String expression_ = EMPTY_STRING;
        ResultParsedAnnots annotations_ = _parsedInstruction.getAnnotationsTypes();
        int size_ = annotations_.getParts().size();
        CustList<SegmentStringPart> afterStr_ = _parsedInstruction.getStringParts().mid(size_);
        boolean ok_ = true;
        int indexBeginCalling_ = found_.indexOf(BEGIN_CALLING);
        String fieldName_;
        int expressionOffest_;
        if (indexBeginCalling_ >= 0) {
            fieldName_ = found_.substring(0, indexBeginCalling_);
            int endIndex_ = found_.lastIndexOf(END_CALLING);
            expressionOffest_ = fieldOffest_ + indexBeginCalling_ + 1;
            if (endIndex_ < indexBeginCalling_ + 1) {
                ok_ = false;
            } else {
                expression_ = found_.substring(indexBeginCalling_ + 1, endIndex_);
                if (!expression_.isEmpty()) {
                    expressionOffest_ += StringUtil.getFirstPrintableCharIndex(expression_);
                }
            }
        } else {
            fieldName_ = found_;
            expressionOffest_ = fieldOffest_;
            expressionOffest_ += fieldName_.length();
        }
        int indexTmp_ = fieldName_.indexOf(StringExpUtil.TEMPLATE_BEGIN);
        String tmpPart_ = EMPTY_STRING;
        int templateOffset_ = 0;
        if (indexTmp_ > -1) {
            templateOffset_ = fieldOffest_;
            tmpPart_ = fieldName_.substring(indexTmp_);
            fieldName_ = fieldName_.substring(0, indexTmp_);
            templateOffset_ += fieldName_.trim().length();
            templateOffset_ += fieldName_.length() - StringUtil.getLastPrintableCharIndex(fieldName_) - 1;
        }
        if (curChar_ == BEGIN_BLOCK) {
            InnerElementBlock elt_ = new InnerElementBlock((EnumBlock) currentParent_, _pkgName, new OffsetStringInfo(fieldOffest_+ _input.getOffset(), fieldName_.trim()),
                    new OffsetStringInfo(templateOffset_+ _input.getOffset(), tmpPart_.trim()),
                    new OffsetStringInfo(expressionOffest_+ _input.getOffset(), expression_.trim()), instructionTrimLocation_ + _input.getOffset());
            elt_.setAnnotations(annotations_);
            elt_.getRes().partsAbsol(afterStr_);
            br_ = elt_;
        } else {
            ElementBlock elt_ = new ElementBlock((EnumBlock) currentParent_, new OffsetStringInfo(fieldOffest_+ _input.getOffset(), fieldName_.trim()),
                    new OffsetStringInfo(templateOffset_+ _input.getOffset(), tmpPart_.trim()),
                    new OffsetStringInfo(expressionOffest_+ _input.getOffset(), expression_.trim()),  instructionTrimLocation_ + _input.getOffset());
            elt_.setAnnotations(annotations_);
            elt_.getRes().partsAbsol(afterStr_);
            br_ = elt_;
        }
        ((InfoBlock)br_).setFieldNumber(((RootBlock) currentParent_).getFieldsBlocks().size());
        ((RootBlock) currentParent_).getFieldsBlocks().add((InfoBlock)br_);
        ((EnumBlock) currentParent_).getEnumBlocks().add((InnerTypeOrElement)br_);
        if (!ok_) {
            br_.getBadIndexes().add(indexBeginCalling_ + 1+ _input.getOffset());
        } else if (!((EnumBlock) currentParent_).isAllow()) {
            ((InnerTypeOrElement)br_).getLastBadIndexes().add(_parsedInstruction.getIndex()+ _input.getOffset());
        }
        br_.setBegin(_parsedInstruction.getIndex()+ _input.getOffset());
        br_.setLengthHeader(1);
        currentParent_.appendChild(br_);
        if (curChar_ == BEGIN_BLOCK) {
            currentParent_ = (BracedBlock) br_;
        }
        return endElement(_packageName, currentParent_, curChar_);
    }

    private static void tryAllow(EnumBlock _currentParent, char _curChar) {
        if (_curChar == SEP_ENUM_CONST) {
            _currentParent.setAllow(true);
        }
    }

    private static AfterBuiltInstruction endElement(String _packageName, BracedBlock _currentParent, char _curChar) {
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        BracedBlock currentParent_ = _currentParent;
        if (_curChar == END_BLOCK) {
            ((EnumBlock) currentParent_).setCanHaveElements(false);
            currentParent_ = possibleEmptyGoUp(currentParent_);
        } else if (_curChar == END_LINE){
            ((EnumBlock) currentParent_).setCanHaveElements(false);
        }
        after_.setParent(currentParent_);
        after_.setPackageName(_packageName);
        return after_;
    }

    private static AfterBuiltInstruction endBlock(InputTypeCreation _input, ParsedInstruction _parsedInstruction, BracedBlock _currentParent, String _trimmedInstruction, String _packageName) {
        BracedBlock currentParent_ = _currentParent;
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        int instructionTrimLocation_ = _parsedInstruction.instLoc();
        Line br_;
        if (!_trimmedInstruction.isEmpty()) {
            br_ = new Line(new OffsetStringInfo(instructionTrimLocation_ + _input.getOffset(), _trimmedInstruction), instructionTrimLocation_ + _input.getOffset());
            br_.getBadIndexes().add(_parsedInstruction.getIndex() + _input.getOffset());
            br_.setBegin(_parsedInstruction.getIndex() + _input.getOffset());
            br_.setLengthHeader(1);
            br_.getRes().partsAbsol(_parsedInstruction.getStringParts());
            currentParent_.appendChild(br_);
        }
        currentParent_ = possibleGoUpTwice(currentParent_);
        if (canHaveElements(currentParent_)) {
            ((EnumBlock) currentParent_).setAllow(false);
        }
        after_.setParent(currentParent_);
        after_.setPackageName(_packageName);
        return after_;
    }

    private static AfterBuiltInstruction types(InputTypeCreation _input, String _pkgName, ParsedInstruction _parsedInstruction, BracedBlock _currentParent, String _packageName, DefaultAccess _defaultAccess) {
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        BracedBlock currentParent_ = _currentParent;
        AbsBk br_;
        //Inner types
        boolean defStatic_;
        MemberCallingsBlock outerFuntion_ = AbsBk.getOuterFuntionInType(currentParent_);
        AccessEnum defAcc_;
        if (outerFuntion_ != null) {
            defAcc_ = _defaultAccess.getAccessInner(outerFuntion_).getAccLocalTypes();
            defStatic_ = outerFuntion_.getStaticContext() != MethodAccessKind.INSTANCE;
        } else {
            defAcc_ = _defaultAccess.getAccessInner(currentParent_).getAccInners();
            defStatic_ = false;
        }
        RootBlock built_ = processTypeHeader(_input, _pkgName, defStatic_,
                _parsedInstruction,
                defAcc_);
        RootBlock retrieve_ = currentParent_.retrieveParentType();
        built_.setParentType(retrieve_);
        if (currentParent_ instanceof RootBlock) {
            ((RootBlock) currentParent_).getChildrenRootBlocks().add(built_);
        }
        currentParent_.appendChild(built_);
        br_ = built_;
        currentParent_ = possibleVisit(_parsedInstruction.getCurChar(), currentParent_, br_);
        after_.setParent(currentParent_);
        after_.setPackageName(_packageName);
        return after_;
    }

    private static AfterBuiltInstruction line(InputTypeCreation _input, ParsedInstruction _parsedInstruction, BracedBlock _currentParent, String _trimmedInstruction, KeyWords _keyWords, String _packageName) {
        int instructionTrimLocation_ = _parsedInstruction.instLoc();
        AfterBuiltInstruction after_ = new AfterBuiltInstruction();
        String keyWordFinal_ = _keyWords.getKeyWordFinal();
        AbsBk br_ = null;
        BracedBlock currentParent_ = _currentParent;
        String keyWordThat_ = _keyWords.getKeyWordThat();
        boolean ok_ = false;
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction, keyWordThat_)) {
            int thatLength_ = keyWordThat_.length();
            String substring_ = _trimmedInstruction.substring(thatLength_);
            int next_ = StringExpUtil.nextPrintChar(0, substring_.length(), substring_);
            String declaringType_ = "";
            if (next_ > -1 && StringExpUtil.isTypeLeafChar(substring_.charAt(next_))) {
                declaringType_ = getDeclaringTypeInstr(substring_, _keyWords);
            }
            if (!declaringType_.trim().isEmpty()) {
                String info_ = substring_.substring(declaringType_.length());
                int afterThat_ = instructionTrimLocation_ + thatLength_;
                int realTypeOffset_ = afterThat_ + next_;
                int varNameOffset_ = afterThat_ + declaringType_.length() + StringUtil.getFirstPrintableCharIndex(info_);
                br_ = new DeclareVariable(new OffsetBooleanInfo(0, false),
                        new OffsetStringInfo(realTypeOffset_ + _input.getOffset(), declaringType_.trim()),
                        instructionTrimLocation_ + _input.getOffset(), true);
                currentParent_.appendChild(br_);
                br_ = new Line(new OffsetStringInfo(varNameOffset_ + _input.getOffset(), info_.trim()), instructionTrimLocation_ + _input.getOffset());
                br_.setBegin(_parsedInstruction.getIndex() + _input.getOffset());
                br_.setLengthHeader(1);
                ((Line) br_).getRes().partsAbsol(_parsedInstruction.getStringParts());
                currentParent_.appendChild(br_);
                ok_ = true;
            }
        }
        if (ok_) {
            currentParent_ = possibleVisit(_parsedInstruction.getCurChar(), currentParent_, br_);
            after_.setParent(currentParent_);
            after_.setPackageName(_packageName);
            return after_;
        }
        boolean finalLocalVar_ = StringExpUtil.startsWithKeyWord(_trimmedInstruction, keyWordFinal_);
        int deltaAfterTrim_;
        String found_;
        if (finalLocalVar_) {
            int deltaAfter_ = keyWordFinal_.length();
            found_ = _trimmedInstruction.substring(deltaAfter_);
            int delSec_ = StringUtil.getFirstPrintableCharIndex(found_);
            deltaAfterTrim_ = deltaAfter_ + delSec_;
            if (delSec_ >= 0) {
                found_ = found_.substring(delSec_);
            }
        } else {
            deltaAfterTrim_ = 0;
            found_ = _trimmedInstruction;
        }
        String declaringType_ = getDeclaringTypeInstr(found_, _keyWords);
        boolean typeDeclaring_ = !declaringType_.trim().isEmpty();
        int realTypeOffset_ = instructionTrimLocation_ + deltaAfterTrim_;
        int afterDeclareOffset_;
        String info_;
        if (typeDeclaring_) {
            info_ = found_.substring(declaringType_.length());
            afterDeclareOffset_ = realTypeOffset_ + declaringType_.length() + StringUtil.getFirstPrintableCharIndex(info_);
        } else {
            info_ = found_;
            afterDeclareOffset_ = realTypeOffset_;
        }
        if (typeDeclaring_) {
            br_ = new DeclareVariable(new OffsetBooleanInfo(instructionTrimLocation_ + _input.getOffset(), finalLocalVar_),
                    new OffsetStringInfo(realTypeOffset_ + _input.getOffset(), declaringType_.trim()),
                    instructionTrimLocation_ + _input.getOffset(), false);
            currentParent_.appendChild(br_);
        }
        br_ = new Line(new OffsetStringInfo(afterDeclareOffset_ + _input.getOffset(), info_.trim()), instructionTrimLocation_ + _input.getOffset());
        br_.setBegin(_parsedInstruction.getIndex() + _input.getOffset());
        br_.setLengthHeader(1);
        ((Line) br_).getRes().partsAbsol(_parsedInstruction.getStringParts());
        currentParent_.appendChild(br_);
        currentParent_ = possibleVisit(_parsedInstruction.getCurChar(), currentParent_, br_);
        after_.setParent(currentParent_);
        after_.setPackageName(_packageName);
        return after_;
    }

    private static BracedBlock possibleGoUpTwice(BracedBlock _currentParent) {
        BracedBlock currentParent_ = possibleGoUp(_currentParent);
        currentParent_ = possibleEmptyGoUp(currentParent_);
        return currentParent_;
    }

    private static BracedBlock possibleVisit(char _currentChar, BracedBlock _currentParent, AbsBk _br) {
        if (_br instanceof BracedBlock && (_br instanceof SwitchPartBlock || _currentChar != END_LINE)) {
            return (BracedBlock) _br;
        }
        if (_br instanceof BracedBlock) {
            return possibleEmptyGoUp((BracedBlock) _br);
        }
        return _currentParent;
    }
    private static BracedBlock possibleGoUp(BracedBlock _currentParent) {
        if (_currentParent instanceof SwitchPartBlock) {
            return possibleEmptyGoUp(_currentParent);
        }
        return _currentParent;
    }

    private static BracedBlock possibleEmptyGoUp(BracedBlock _currentParent) {
        addPossibleEmpty(_currentParent);
        return _currentParent.getParent();
    }

    private static void addPossibleEmpty(BracedBlock _en) {
        if (_en instanceof SwitchBlock) {
            return;
        }
        if (_en instanceof DoWhileCondition) {
            return;
        }
        if (!AbsBk.isVisitableBlock(_en)) {
            return;
        }
        if (_en.getFirstChild() != null) {
            return;
        }
        int off_ = _en.getOffset();
        EmptyInstruction empty_ = new EmptyInstruction(off_);
        _en.appendChild(empty_);
    }
    private static RootBlock processTypeHeader(InputTypeCreation _offset, String _pkgName,
                                               boolean _defStatic,
                                               ParsedInstruction _instructionTrimLocation,
                                               AccessEnum _defAccess) {
        //Inner types
        KeyWords keyWords_ = _offset.getCont().getKeys();
        String trimmedInstruction_ = _instructionTrimLocation.getAfter();
        ResultParsedAnnots annotations_ = _instructionTrimLocation.getAnnotationsTypes();
        int typeOffset_ = _instructionTrimLocation.getAfterOffset();
        AccessEnum accessFct_ = getAccess(trimmedInstruction_, keyWords_);
        String word_ = getWord(accessFct_, keyWords_);
        if (accessFct_ == null) {
            accessFct_ = _defAccess;
        }
        String afterAccess_ = trimmedInstruction_.substring(word_.length());
        int locIndex_ = typeOffset_ + word_.length() + StringUtil.getFirstPrintableCharIndex(afterAccess_);
        boolean staticType_ = _defStatic;
        boolean abstractType_ = false;
        boolean finalType_ = false;
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordClass_ = keyWords_.getKeyWordClass();
        String keyWordEnum_ = keyWords_.getKeyWordEnum();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordInterface_ = keyWords_.getKeyWordInterface();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String beforeQu_ = afterAccess_.trim();
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordAbstract_)) {
            abstractType_ = true;
            String sub_ = beforeQu_.substring(keyWordAbstract_.length());
            beforeQu_ = sub_.trim();
            locIndex_ += keyWordAbstract_.length();
            locIndex_ += StringUtil.getFirstPrintableCharIndex(sub_);
        }
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordStatic_)) {
            staticType_ = true;
            String sub_ = beforeQu_.substring(keyWordStatic_.length());
            beforeQu_ = sub_.trim();
            locIndex_ += keyWordStatic_.length();
            locIndex_ += StringUtil.getFirstPrintableCharIndex(sub_);
        }
        if (StringExpUtil.startsWithKeyWord(beforeQu_,keyWordFinal_)) {
            finalType_ = true;
            String sub_ = beforeQu_.substring(keyWordFinal_.length());
            beforeQu_ = sub_.trim();
            locIndex_ += keyWordFinal_.length();
            locIndex_ += StringUtil.getFirstPrintableCharIndex(sub_);
        }
        String type_ = typeKind(keyWords_, beforeQu_);
        int categoryOffset_ = locIndex_;
        locIndex_ += type_.length();
        String sub_ = beforeQu_.substring(type_.length());
        beforeQu_ = sub_.trim();
        locIndex_ += StringUtil.getFirstPrintableCharIndex(sub_);

        ParsedImportedTypes p_ = new ParsedImportedTypes(locIndex_, _offset.getOffset(), beforeQu_);
        StringList importedTypes_ = p_.getImportedTypes();
        Ints offsetsImports_ = p_.getOffsetsImports();
        locIndex_ = p_.getOffset();
        //insert interfaces static initialization for class and enums
        String infoPart_ = p_.getNextPart();
        InterfacesPart interfacesPart_ = parseInterfaces(_offset, type_, infoPart_, locIndex_);
        locIndex_ = interfacesPart_.getLocIndex();
        infoPart_ = interfacesPart_.getPart();
        boolean ok_ = interfacesPart_.isOk();
        InheritingPart inh_ = new InheritingPart(locIndex_, infoPart_);
        inh_.parse(_offset.getOffset());
        String typeName_ = inh_.getTypeName();
        IntMap<String> superTypes_ = inh_.getSuperTypes();
        String tempDef_ = inh_.getTempDef();
        int beginDefinition_ = inh_.getBeginDefinition();
        RootBlock typeBlock_;
        if (StringUtil.quickEq(type_, keyWordEnum_)) {
            typeBlock_ = new EnumBlock(beginDefinition_, typeName_, _pkgName,
                    new OffsetAccessInfo(typeOffset_ - 1+ _offset.getOffset(), accessFct_) , tempDef_, superTypes_,
                   _instructionTrimLocation.instLoc() + _offset.getOffset());
        } else if (StringUtil.quickEq(type_, keyWordClass_)) {
            typeBlock_ = new ClassBlock(beginDefinition_, typeName_, _pkgName,
                    new OffsetAccessInfo(typeOffset_ - 1+ _offset.getOffset(), accessFct_), tempDef_, superTypes_, finalType_, abstractType_, staticType_,
                     _instructionTrimLocation.instLoc() + _offset.getOffset());
        } else if (StringUtil.quickEq(type_, "@"+ keyWordClass_)) {
            typeBlock_ = new RecordBlock(false,beginDefinition_, typeName_, _pkgName,
                    new OffsetAccessInfo(typeOffset_ - 1+ _offset.getOffset(), accessFct_), tempDef_, superTypes_,staticType_,
                     _instructionTrimLocation.instLoc() + _offset.getOffset());
        } else if (StringUtil.quickEq(type_, "@"+ keyWordInterface_)) {
            typeBlock_ = new RecordBlock(true,beginDefinition_, typeName_, _pkgName,
                    new OffsetAccessInfo(typeOffset_ - 1+ _offset.getOffset(), accessFct_), tempDef_, superTypes_,staticType_,
                     _instructionTrimLocation.instLoc() + _offset.getOffset());
        } else if (StringUtil.quickEq(type_, keyWordInterface_)) {
            typeBlock_ = new InterfaceBlock(beginDefinition_, typeName_, _pkgName,
                    new OffsetAccessInfo(typeOffset_ - 1+ _offset.getOffset(), accessFct_) , tempDef_, superTypes_, staticType_,
                     _instructionTrimLocation.instLoc() + _offset.getOffset());
        } else {
            typeBlock_ = new AnnotationBlock(beginDefinition_, typeName_, _pkgName,
                    new OffsetAccessInfo(typeOffset_ - 1+ _offset.getOffset(), accessFct_) , tempDef_, superTypes_,
                     _instructionTrimLocation.instLoc() + _offset.getOffset());
        }
        typeHeader(typeBlock_, categoryOffset_, _offset, type_.length());
        typeBlock_.setupOffsets(typeName_,"");
        typeBlock_.getImports().addAllElts(importedTypes_);
        typeBlock_.getImportsOffset().addAllElts(offsetsImports_);
        typeBlock_.getStaticInitInterfaces().addAllElts(interfacesPart_.getStaticInitInterfaces());
        typeBlock_.getStaticInitInterfacesOffset().addAllElts(interfacesPart_.getStaticInitInterfacesOffset());
        typeBlock_.getInstInitInterfaces().addAllElts(interfacesPart_.getInstInitInterfaces());
        typeBlock_.getInstInitInterfacesOffset().addAllElts(interfacesPart_.getInstInitInterfacesOffset());
        typeBlock_.setAnnotations(annotations_);
        if (!ok_) {
            typeBlock_.getBadIndexes().add(locIndex_ + _offset.getOffset());
        }
        return typeBlock_;
    }

    private static AccessEnum getAccess(String _inst, KeyWords _keyWords) {
        String keyWordPackage_ = _keyWords.getKeyWordPackage();
        String keyWordPrivate_ = _keyWords.getKeyWordPrivate();
        String keyWordProtected_ = _keyWords.getKeyWordProtected();
        String keyWordPublic_ = _keyWords.getKeyWordPublic();
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordPrivate_)) {
            return AccessEnum.PRIVATE;
        }
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordPackage_)) {
            return AccessEnum.PACKAGE;
        }
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordProtected_)) {
            return AccessEnum.PROTECTED;
        }
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordPublic_)) {
            return AccessEnum.PUBLIC;
        }
        return null;
    }

    private static String getWord(AccessEnum _access, KeyWords _keyWords) {
        String keyWordPackage_ = _keyWords.getKeyWordPackage();
        String keyWordPrivate_ = _keyWords.getKeyWordPrivate();
        String keyWordProtected_ = _keyWords.getKeyWordProtected();
        String keyWordPublic_ = _keyWords.getKeyWordPublic();
        if (_access == AccessEnum.PRIVATE) {
            return keyWordPrivate_;
        }
        if (_access == AccessEnum.PACKAGE) {
            return keyWordPackage_;
        }
        if (_access == AccessEnum.PROTECTED) {
            return keyWordProtected_;
        }
        if (_access == AccessEnum.PUBLIC) {
            return keyWordPublic_;
        }
        return "";
    }
    private static AbsBk processTypeMember(char _currentChar,
                                           ParsedInstruction _i, InputTypeCreation _offset, RootBlock _currentParent, DefaultAccess _defaultAccess, String _aliasVoid) {
        String trimmedInstruction_ = _i.getAfter();
        KeyWords keyWords_ = _offset.getCont().getKeys();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        int accessOffest_ = _i.getAfterOffset();
        int offsetFile_ = _offset.getOffset();
        AccessEnum access_ = getAccess(trimmedInstruction_, keyWords_);
        String word_ = getWord(access_, keyWords_);
        AccessEnum accessFct_ = access(_currentParent, access_, _defaultAccess);
        String afterAccess_ = trimmedInstruction_.substring(word_.length());
        String trimmedAfterAccess_ = afterAccess_.trim();
        boolean field_ = isField(_currentChar,keyWords_, trimmedAfterAccess_);
        String ctorName_ = tryGetCtorName(trimmedAfterAccess_, field_);
        boolean oper_ = StringExpUtil.startsWithKeyWord(trimmedAfterAccess_,keyWords_.getKeyWordOperator());
        boolean meth_ = isMethod(keyWords_, trimmedAfterAccess_, field_, ctorName_, oper_);
        if (!meth_ && !oper_ && ctorName_ == null && _currentChar != '{') {

            //fields
            return field(_i,keyWords_, _currentParent, offsetFile_, word_, accessFct_, afterAccess_);
        }
       //constructors or methods or types
        if (ctorName_ != null) {
            int modifierOffest_ = accessOffest_ + word_.length() + StringUtil.getFirstPrintableCharIndex(afterAccess_);
            String info_ = afterAccess_.trim();


            ParsedFctHeader parseHeader_ = new ParsedFctHeader();

            int paramOffest_ = modifierOffest_;
            int indexLeftPar_ = info_.indexOf(BEGIN_CALLING);
            paramOffest_ += indexLeftPar_ + 1;
            int leftPar_ = paramOffest_;
            String after_ = info_.substring(indexLeftPar_ + 1);
            paramOffest_ += StringUtil.getFirstPrintableCharIndex(after_);
            info_ = after_.trim();
            parseHeader_.parse(_i.getStringParts(), info_, paramOffest_ + offsetFile_, ParsedFctHeader.isIndexerSet(false, EMPTY_STRING, EMPTY_STRING, keyWords_, _aliasVoid), keyWords_);
            parseHeader_.setRetRef(false);
            StringList parametersType_ = parseHeader_.getParametersType();
            ConstructorBlock br_ = new ConstructorBlock(parseHeader_, new OffsetAccessInfo(accessOffest_ + offsetFile_, accessFct_),
                    new OffsetStringInfo(accessOffest_ + offsetFile_, EMPTY_STRING),
                    new OffsetStringInfo(accessOffest_ + offsetFile_, EMPTY_STRING),
                    leftPar_ + offsetFile_, _i.instLoc() + offsetFile_);
            br_.setCtorName(ctorName_);
            br_.setCtorNumber(_currentParent.getConstructorBlocks().size());
            _currentParent.getConstructorBlocks().add(br_);
            emptyCtor(_currentParent, parametersType_, br_);
            return fct(_i, _currentParent, offsetFile_, parseHeader_, br_);
        }
        if (!meth_) {
            int modifierOffest_ = accessOffest_ + word_.length() + StringUtil.getFirstPrintableCharIndex(afterAccess_);
            String info_ = afterAccess_.trim();
            boolean retRef_ = false;

            String modifier_ = keyWordStatic_;
            String prefModifier_ = keyWords_.getKeyWordOperator();
            String afterModifier_ = info_.substring(prefModifier_.length());
            int methodNameOffest_ = modifierOffest_ + prefModifier_.length();
            methodNameOffest_ += StringUtil.getFirstPrintableCharIndex(afterModifier_);
            afterModifier_ = afterModifier_.substring(StringUtil.getFirstPrintableCharIndex(afterModifier_));
            String methodName_ = fetchSymbol(afterModifier_).toString();
            afterModifier_ = afterModifier_.substring(methodName_.length());
            int typeOffset_ = methodNameOffest_ + methodName_.length();
            typeOffset_ += StringUtil.getFirstPrintableCharIndex(afterModifier_);
            info_ = afterModifier_.trim();
            if (StringExpUtil.startsWithKeyWord(info_,keyWordStaticCall_)) {
                modifier_ = keyWordStaticCall_;
                typeOffset_ += keyWordStaticCall_.length();
                String after_ = info_.substring(keyWordStaticCall_.length());
                typeOffset_ += StringUtil.getFirstPrintableCharIndex(after_);
                info_ = after_.trim();
            }
            String keyWordThat_ = keyWords_.getKeyWordThat();
            if (StringExpUtil.startsWithKeyWord(info_,keyWordThat_)) {
                info_ = info_.substring(keyWordThat_.length());
                typeOffset_ += keyWordThat_.length();
                typeOffset_ += StringExpUtil.getOffset(info_);
                info_ = info_.trim();
                retRef_ = true;
            }
            String declaringType_ = getFoundType(info_);
            String afterType_ = info_.substring(declaringType_.length());
            int leftParIndex_ = afterType_.indexOf('(');
            int paramOffest_ = typeOffset_ + declaringType_.length() + leftParIndex_ + 1;
            String afterMethodName_ = afterType_.substring(leftParIndex_ + 1);
            paramOffest_ += StringUtil.getFirstPrintableCharIndex(afterMethodName_);
            info_ = afterMethodName_.trim();
            ParsedFctHeader parseHeader_ = new ParsedFctHeader();
            parseHeader_.parse(_i.getStringParts(), info_, paramOffest_ + offsetFile_, ParsedFctHeader.isIndexerSet(false, declaringType_.trim(), methodName_.trim(), keyWords_, _aliasVoid), keyWords_);
            parseHeader_.setRetRef(retRef_);
            String retType_ = declaringType_.trim();
            String trimMeth_ = methodName_.trim();
            MethodKind kind_ = MethodKind.OPERATOR;
            NamedCalledFunctionBlock ov_ = new NamedCalledFunctionBlock(parseHeader_, new OffsetAccessInfo(accessOffest_ + offsetFile_, AccessEnum.PUBLIC),
                    new OffsetStringInfo(typeOffset_ + offsetFile_, retType_),
                    new OffsetStringInfo(methodNameOffest_ + offsetFile_, trimMeth_),
                    new OffsetStringInfo(modifierOffest_ + offsetFile_, modifier_),
                    _i.instLoc() + offsetFile_, keyWords_);
            ov_.setKind(kind_);
            countOverrides(ov_, _currentParent);
            return fct(_i, _currentParent, offsetFile_, parseHeader_, ov_);
        }
        int modifierOffest_ = accessOffest_ + word_.length() + StringUtil.getFirstPrintableCharIndex(afterAccess_);
        String info_ = afterAccess_.trim();
        boolean retRef_ = false;
        String expModifier_ = getExplicitModifier(info_, keyWords_);
        String modifier_ = modifier(_currentParent, keyWords_, expModifier_);
        String afterModifier_ = info_.substring(expModifier_.length());
        int typeOffset_ = modifierOffest_ + expModifier_.length();
        typeOffset_ += StringUtil.getFirstPrintableCharIndex(afterModifier_);
        info_ = afterModifier_.trim();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        if (StringExpUtil.startsWithKeyWord(info_,keyWordThat_)) {
            info_ = info_.substring(keyWordThat_.length());
            typeOffset_ += keyWordThat_.length();
            typeOffset_ += StringExpUtil.getOffset(info_);
            info_ = info_.trim();
            retRef_ = true;
        }
        String declaringType_ = getFoundType(info_);
        String afterType_ = info_.substring(declaringType_.length());
        int methodNameOffest_ = typeOffset_ + declaringType_.length();
        methodNameOffest_ += StringUtil.getFirstPrintableCharIndex(afterType_);
        info_ = afterType_.trim();
        int leftParIndex_ = info_.indexOf(BEGIN_CALLING);
        String methodName_ = info_.substring(0, leftParIndex_);
        String afterMethodName_ = info_.substring(leftParIndex_ + 1);
        int paramOffest_ = methodNameOffest_ + leftParIndex_ + 1;
        paramOffest_ += StringUtil.getFirstPrintableCharIndex(afterMethodName_);
        info_ = afterMethodName_.trim();
        ParsedFctHeader parseHeader_ = new ParsedFctHeader();
        parseHeader_.parse(_i.getStringParts(), info_, paramOffest_ + offsetFile_, ParsedFctHeader.isIndexerSet(true, declaringType_.trim(), methodName_.trim(), keyWords_, _aliasVoid), keyWords_);
        parseHeader_.setRetRef(retRef_);
        info_ = parseHeader_.getInfo();
        StringList parametersType_ = parseHeader_.getParametersType();
        int offsetLast_ = parseHeader_.getOffsetLast();
        String retType_ = declaringType_.trim();
        String trimMeth_ = methodName_.trim();
        MethodKind kind_;
        NamedCalledFunctionBlock ov_;
        if (StringUtil.quickEq(trimMeth_, keyWords_.getKeyWordFalse())) {
            kind_ = MethodKind.FALSE_OPERATOR;
            ov_ = new NamedCalledFunctionBlock(parseHeader_, new OffsetAccessInfo(accessOffest_+ offsetFile_, accessFct_),
                    new OffsetStringInfo(typeOffset_+ offsetFile_, retType_),
                    new OffsetStringInfo(methodNameOffest_+ offsetFile_, trimMeth_),
                    new OffsetStringInfo(modifierOffest_+ offsetFile_, modifier_),
                    _i.instLoc() + offsetFile_, keyWords_);
            ov_.setKind(kind_);
            countOverrides(ov_, _currentParent);
            return fct(_i, _currentParent, offsetFile_, parseHeader_, ov_);
        }
        if (StringUtil.quickEq(trimMeth_, keyWords_.getKeyWordTrue())) {
            kind_ = MethodKind.TRUE_OPERATOR;
            ov_ = new NamedCalledFunctionBlock(parseHeader_, new OffsetAccessInfo(accessOffest_+ offsetFile_, accessFct_),
                    new OffsetStringInfo(typeOffset_+ offsetFile_, retType_),
                    new OffsetStringInfo(methodNameOffest_+ offsetFile_, trimMeth_),
                    new OffsetStringInfo(modifierOffest_+ offsetFile_, modifier_),
                    _i.instLoc() + offsetFile_, keyWords_);
            ov_.setKind(kind_);
            countOverrides(ov_, _currentParent);
            return fct(_i, _currentParent, offsetFile_, parseHeader_, ov_);
        }
        if (StringUtil.quickEq(trimMeth_, keyWords_.getKeyWordNull())) {
            kind_ = MethodKind.RAND_CODE;
            ov_ = new NamedCalledFunctionBlock(parseHeader_, new OffsetAccessInfo(accessOffest_+ offsetFile_, accessFct_),
                    new OffsetStringInfo(typeOffset_+ offsetFile_, retType_),
                    new OffsetStringInfo(methodNameOffest_+ offsetFile_, trimMeth_),
                    new OffsetStringInfo(modifierOffest_+ offsetFile_, modifier_),
                    _i.instLoc() + offsetFile_, keyWords_);
            defBehavior(ov_, info_, offsetLast_, kind_);
            countOverrides(ov_, _currentParent);
            return fct(_i, _currentParent, offsetFile_, parseHeader_, ov_);
        }
        if (StringUtil.quickEq(trimMeth_, keyWords_.getKeyWordExplicit())) {
            kind_ = MethodKind.EXPLICIT_CAST;
            ov_ = new NamedCalledFunctionBlock(parseHeader_, new OffsetAccessInfo(accessOffest_+ offsetFile_, accessFct_),
                    new OffsetStringInfo(typeOffset_+ offsetFile_, retType_),
                    new OffsetStringInfo(methodNameOffest_+ offsetFile_, trimMeth_),
                    new OffsetStringInfo(modifierOffest_+ offsetFile_, modifier_),
                    _i.instLoc() + offsetFile_, keyWords_);
            ov_.setKind(kind_);
            countOverrides(ov_, _currentParent);
            return fct(_i, _currentParent, offsetFile_, parseHeader_, ov_);
        }
        if (StringUtil.quickEq(trimMeth_, keyWords_.getKeyWordCast())) {
            kind_ = MethodKind.IMPLICIT_CAST;
            ov_ = new NamedCalledFunctionBlock(parseHeader_, new OffsetAccessInfo(accessOffest_+ offsetFile_, accessFct_),
                    new OffsetStringInfo(typeOffset_+ offsetFile_, retType_),
                    new OffsetStringInfo(methodNameOffest_+ offsetFile_, trimMeth_),
                    new OffsetStringInfo(modifierOffest_+ offsetFile_, modifier_),
                    _i.instLoc() + offsetFile_, keyWords_);
            ov_.setKind(kind_);
            countOverrides(ov_, _currentParent);
            return fct(_i, _currentParent, offsetFile_, parseHeader_, ov_);
        }
        if (StringUtil.quickEq(trimMeth_, keyWords_.getKeyWordThis())) {
            boolean get_ = !StringUtil.quickEq(retType_, _aliasVoid);
            kind_ = getMethodKind(get_);
            trimMeth_ = geneMethodIndexer(kind_);
            ov_ = new NamedCalledFunctionBlock(parseHeader_, new OffsetAccessInfo(accessOffest_+ offsetFile_, accessFct_),
                    new OffsetStringInfo(typeOffset_+ offsetFile_, retType_),
                    new OffsetStringInfo(methodNameOffest_+ offsetFile_, trimMeth_),
                    new OffsetStringInfo(modifierOffest_+ offsetFile_, modifier_),
                    _i.instLoc() + offsetFile_, keyWords_);
            defBehavior(ov_, info_, offsetLast_, kind_);
            countOverrides(ov_, _currentParent);
            return fct(_i, _currentParent, offsetFile_, parseHeader_, ov_);
        }
        kind_ = stdOrToStr(retRef_, modifier_, parametersType_, trimMeth_, keyWords_);
        ov_ = new NamedCalledFunctionBlock(parseHeader_, new OffsetAccessInfo(accessOffest_+ offsetFile_, accessFct_),
                new OffsetStringInfo(typeOffset_+ offsetFile_, retType_),
                new OffsetStringInfo(methodNameOffest_+ offsetFile_, trimMeth_),
                new OffsetStringInfo(modifierOffest_+ offsetFile_, modifier_),
                _i.instLoc() + offsetFile_, keyWords_);
        defBehavior(ov_, info_, offsetLast_, kind_);
        countOverrides(ov_, _currentParent);
        return fct(_i, _currentParent, offsetFile_, parseHeader_, ov_);
    }

    private static NamedFunctionBlock fct(ParsedInstruction _i, RootBlock _currentParent, int _offsetFile, ParsedFctHeader _parseHeader, NamedFunctionBlock _ov) {
        ResultParsedAnnots annotations_ = _i.getAnnotationsTypes();
        CustList<ResultParsedAnnots> annotationsParams_ = _parseHeader.getAnnotationsParams();
        badIndexesFct(_i, _offsetFile, _parseHeader, _ov);
        _ov.setBegin(_i.getIndex()+ _offsetFile);
        _ov.setLengthHeader(1);
        _ov.getAnnotationsParams().addAllElts(annotationsParams_);
        _ov.setAnnotations(annotations_);
        _currentParent.appendChild(_ov);
        return _ov;
    }

    private static void defBehavior(NamedCalledFunctionBlock _ov, String _info, int _offsetLast, MethodKind _kind) {
        _ov.setDefinition(_info);
        _ov.setDefinitionOffset(_offsetLast);
        _ov.setKind(_kind);
    }

    private static void countOverrides(NamedCalledFunctionBlock _ov, RootBlock _currentParent) {
        _ov.setNameOverrideNumber(_currentParent.getOverridableBlocks().size());
        _currentParent.getOverridableBlocks().add(_ov);
    }

    private static void emptyCtor(RootBlock _currentParent, StringList _parametersType, ConstructorBlock _br) {
        if (_parametersType.isEmpty()) {
            _currentParent.setEmptyCtor(_br);
        }
    }

    private static String geneMethodIndexer(MethodKind _kind) {
        String trimMeth_;
        if (_kind == MethodKind.SET_INDEX) {
            trimMeth_ = "[]=";
        } else {
            trimMeth_ = "[]";
        }
        return trimMeth_;
    }

    private static MethodKind getMethodKind(boolean _get) {
        MethodKind kind_;
        if (!_get) {
            kind_ = MethodKind.SET_INDEX;
        } else {
            kind_ = MethodKind.GET_INDEX;
        }
        return kind_;
    }

    private static MethodKind stdOrToStr(boolean _retRef, String _modifier, StringList _parametersType, String _trimMeth, KeyWords _keyWords) {
        MethodKind kind_;
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        String keyWordStaticCall_ = _keyWords.getKeyWordStaticCall();
        if (!_retRef
                &&StringUtil.quickEq(_trimMeth, _keyWords.getKeyWordToString())
                &&!StringUtil.quickEq(_modifier, keyWordStatic_)
                &&!StringUtil.quickEq(_modifier, keyWordStaticCall_)
                && _parametersType.isEmpty()) {
            kind_ = MethodKind.TO_STRING;
        } else {
            kind_ = MethodKind.STD_METHOD;
        }
        return kind_;
    }

    private static String modifier(RootBlock _currentParent, KeyWords _keyWords, String _expModifier) {
        String modifier_ = _expModifier;
        String keyWordAbstract_ = _keyWords.getKeyWordAbstract();
        String keyWordNormal_ = _keyWords.getKeyWordNormal();
        if (modifier_.isEmpty()) {
            if (_currentParent instanceof InterfaceBlock) {
                modifier_ = keyWordAbstract_;
            } else {
                modifier_ = keyWordNormal_;
            }
        }
        return modifier_;
    }

    private static AccessEnum access(RootBlock _currentParent, AccessEnum _access, DefaultAccess _defaultAccess) {
        AccessEnum accessFct_ = _defaultAccess.getAccessInner(_currentParent).getAccMember();
        if (_access != null) {
            accessFct_ = _access;
        }
        return accessFct_;
    }

    private static FieldBlock field(ParsedInstruction _i, KeyWords _keyWords, RootBlock _currentParent, int _offsetFile, String _word, AccessEnum _accessFct, String _afterAccess) {
        String keyWordFinal_ = _keyWords.getKeyWordFinal();
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        int accessOffest_ = _i.getAfterOffset();
        ResultParsedAnnots annotations_ = _i.getAnnotationsTypes();
        int typeOffest_ = accessOffest_ + _word.length() + StringUtil.getFirstPrintableCharIndex(_afterAccess);
        String info_ = _afterAccess.trim();
        int staticOffest_ = -1;
        int finalOffest_ = -1;
        boolean static_ = false;
        boolean final_ = false;
        if (StringExpUtil.startsWithKeyWord(info_, keyWordStatic_)) {
            staticOffest_ = typeOffest_;
            static_ = true;
            String afterStatic_ = info_.substring(keyWordStatic_.length());
            typeOffest_ += keyWordStatic_.length();
            typeOffest_ += StringUtil.getFirstPrintableCharIndex(afterStatic_);
            info_ = afterStatic_.trim();
        }
        if (StringExpUtil.startsWithKeyWord(info_, keyWordFinal_)) {
            finalOffest_ = typeOffest_;
            final_ = true;
            String afterFinal_ = info_.substring(keyWordFinal_.length());
            typeOffest_ += keyWordFinal_.length();
            typeOffest_ += StringUtil.getFirstPrintableCharIndex(afterFinal_);
            info_ = afterFinal_.trim();
        }
        if (!static_ && _currentParent instanceof RecordBlock && !((RecordBlock) _currentParent).isMutable()) {
            final_ = true;
        }
        String declaringType_ = getFoundType(info_);
        String afterType_ = info_.substring(declaringType_.length());
        int fieldNameOffest_ = StringUtil.getFirstPrintableCharIndex(afterType_) +declaringType_.length() + typeOffest_;
        FieldBlock br_ = new FieldBlock(_currentParent,
                new OffsetAccessInfo(accessOffest_ + _offsetFile, _accessFct),
                new OffsetBooleanInfo(staticOffest_ + _offsetFile, static_), new OffsetBooleanInfo(finalOffest_ + _offsetFile, final_),
                new OffsetStringInfo(typeOffest_ + _offsetFile, declaringType_.trim()),
                new OffsetStringInfo(fieldNameOffest_ + _offsetFile, afterType_.trim()),
                _i.instLoc() + _offsetFile);
        br_.setAnnotations(annotations_);
        br_.setFieldNumber(_currentParent.getFieldsBlocks().size());
        br_.getRes().partsAbsol(_i.getStringParts());
        _currentParent.getFieldsBlocks().add(br_);
        if (!static_){
            _currentParent.getFieldsInstBlocks().add(br_);
        }
        br_.setBegin(_i.getIndex()+ _offsetFile);
        br_.setLengthHeader(1);
        _currentParent.appendChild(br_);
        return br_;
    }

    private static boolean isMethod(KeyWords _keyWords, String _trimmedAfterAccess, boolean _field, String _ctorName, boolean _oper) {
        if (_oper || _field || _ctorName != null) {
            return false;
        }
        String infoModifiers_ = _trimmedAfterAccess;
        String mod_ = getExplicitModifier(infoModifiers_, _keyWords);
        infoModifiers_ = infoModifiers_.substring(mod_.length()).trim();
        String keyWordThat_ = _keyWords.getKeyWordThat();
        if (StringExpUtil.startsWithKeyWord(infoModifiers_,keyWordThat_)) {
            infoModifiers_ = infoModifiers_.substring(keyWordThat_.length()).trim();
        }
        String typeStr_ = getFoundType(infoModifiers_);
        infoModifiers_ = infoModifiers_.substring(typeStr_.length()).trim();
        int lenAfterModifiers_ = infoModifiers_.length();
        int indexMod_ = 0;
        while (indexMod_ < lenAfterModifiers_) {
            char cur_ = infoModifiers_.charAt(indexMod_);
            if (!StringExpUtil.isTypeLeafChar(cur_)) {
                break;
            }
            indexMod_++;
        }
        while (indexMod_ < lenAfterModifiers_) {
            char cur_ = infoModifiers_.charAt(indexMod_);
            if (!StringUtil.isWhitespace(cur_)) {
                break;
            }
            indexMod_++;
        }
        return StringExpUtil.nextCharIs(infoModifiers_, indexMod_, lenAfterModifiers_, BEGIN_CALLING);
    }

    private static String tryGetCtorName(String _trimmedAfterAccess, boolean _field) {
        String ctorName_ = null;
        if (!_field) {
            if (_trimmedAfterAccess.startsWith("(")) {
                ctorName_ = "";
            } else {
                int indexPar_ = Math.max(0, _trimmedAfterAccess.indexOf('('));
                String firstPart_ = _trimmedAfterAccess.substring(0, indexPar_).trim();
                if (StringExpUtil.isTypeLeafPart(firstPart_)) {
                    ctorName_ = firstPart_;
                }
            }
        }
        return ctorName_;
    }

    private static boolean isField(char _currentChar, KeyWords _keyWords, String _trimmedAfterAccess) {
        String keyWordFinal_ = _keyWords.getKeyWordFinal();
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        boolean field_ = false;
        if (_currentChar != '{' && StringExpUtil.startsWithKeyWord(_trimmedAfterAccess, keyWordStatic_)) {
            String infoModifiers_ = _trimmedAfterAccess;
            int lenLoc_ = keyWordStatic_.length();
            String sub_ = infoModifiers_.substring(lenLoc_);
            int delta_ = StringUtil.getFirstPrintableCharIndex(sub_);
            infoModifiers_ = sub_.substring(delta_);
            if (StringExpUtil.startsWithKeyWord(infoModifiers_, keyWordFinal_)) {
                field_ = true;
            }
        }
        return field_;
    }

    private static void badIndexesFct(ParsedInstruction _i, int _off, ParsedFctHeader _parse, AbsBk _br) {
        if (!_parse.isOk()) {
            _br.getBadIndexes().add(_i.getIndex()+ _off);
        }
    }

    private static String getExplicitModifier(String _inst, KeyWords _keyWords) {
        String keyWordAbstract_ = _keyWords.getKeyWordAbstract();
        String keyWordFinal_ = _keyWords.getKeyWordFinal();
        String keyWordNormal_ = _keyWords.getKeyWordNormal();
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        String keyWordStaticCall_ = _keyWords.getKeyWordStaticCall();
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordNormal_)) {
            return keyWordNormal_;
        }
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordAbstract_)) {
            return keyWordAbstract_;
        }
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordStatic_)) {
            return keyWordStatic_;
        }
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordStaticCall_)) {
            return keyWordStaticCall_;
        }
        if (StringExpUtil.startsWithKeyWord(_inst,keyWordFinal_)) {
            return keyWordFinal_;
        }
        return "";
    }
    private static StringBuilder fetchSymbol(String _afterModifier) {
        int len_ = _afterModifier.length();
        int j_ = 0;
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

    private static AbsBk processInstructionBlock(int _offset,
                                                 ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, PrimitiveTypes _primTypes, KeyWords _keyWords) {
        String keyWordBreak_ = _keyWords.getKeyWordBreak();
        String keyWordCase_ = _keyWords.getKeyWordCase();
        String keyWordCatch_ = _keyWords.getKeyWordCatch();
        String keyWordContinue_ = _keyWords.getKeyWordContinue();
        String keyWordDefault_ = _keyWords.getKeyWordDefault();
        String keyWordDo_ = _keyWords.getKeyWordDo();
        String keyWordElse_ = _keyWords.getKeyWordElse();
        String keyWordElseif_ = _keyWords.getKeyWordElseif();
        String keyWordFinally_ = _keyWords.getKeyWordFinally();
        String keyWordIf_ = _keyWords.getKeyWordIf();
        String keyWordReturn_ = _keyWords.getKeyWordReturn();
        String keyWordThrow_ = _keyWords.getKeyWordThrow();
        String keyWordTry_ = _keyWords.getKeyWordTry();
        String keyWordWhile_ = _keyWords.getKeyWordWhile();
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordBreak_)) {
            return keyWordBreak(_offset, _i, _currentParent, _trimmedInstruction, keyWordBreak_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordContinue_)) {
            return keyWordContinue(_offset, _i, _currentParent, _trimmedInstruction, keyWordContinue_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordReturn_)) {
            return keyWordReturn(_offset, _i, _currentParent, _trimmedInstruction, keyWordReturn_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordThrow_)) {
            return keyWordThrow(_offset, _i, _currentParent, _trimmedInstruction, keyWordThrow_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordCase_)) {
            return keyWordCase(_offset, _i, _currentParent, _trimmedInstruction, _keyWords);
        }
        if (StringUtil.quickEq(_trimmedInstruction,keyWordDefault_)) {
            return keyWordDefaultShort(_offset, _i, _currentParent, keyWordDefault_);
        }
        if (startsWithDefVar(_trimmedInstruction, keyWordDefault_)) {
            return keyWordDefaultLong(_offset, _i, _currentParent, _trimmedInstruction, keyWordDefault_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordWhile_)) {
            return keyWordWhile(_offset, _i, _currentParent, _trimmedInstruction, keyWordWhile_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordCatch_)) {
            return keyWordCatch(_offset, _i, _currentParent, _trimmedInstruction, keyWordCatch_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordIf_)) {
            return keyWordIf(_offset, _i, _currentParent, _trimmedInstruction, keyWordIf_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordElseif_)) {
            return keyWordElseIf(_offset, _i, _currentParent, _trimmedInstruction, keyWordElseif_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordElse_)) {
            return keyWordElse(_offset, _i, _currentParent, _trimmedInstruction, _keyWords);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordDo_)) {
            return keyWordDo(_offset, _i, _currentParent, _trimmedInstruction, keyWordDo_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordFinally_)) {
            return keyWordFinally(_offset, _i, _currentParent, keyWordFinally_);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction,keyWordTry_)) {
            return keyWordTry(_offset, _i, _currentParent, _trimmedInstruction, keyWordTry_);
        }
        return processInstructionBlockSec(_offset, _i, _currentParent, _trimmedInstruction, _primTypes, _keyWords);
    }

    private static AbsBk processInstructionBlockSec(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, PrimitiveTypes _primTypes, KeyWords _keyWords) {
        String keyWordFor_ = _keyWords.getKeyWordFor();
        String keyWordForeach_ = _keyWords.getKeyWordForeach();
        String keyWordIter_ = _keyWords.getKeyWordIter();
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        String keyWordSwitch_ = _keyWords.getKeyWordSwitch();
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction, keyWordForeach_)) {
            return keyWordForEach(_offset, _i, _currentParent, _trimmedInstruction, _primTypes, _keyWords);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction, keyWordIter_)) {
            return keyWordIter(_offset, _i, _currentParent, _trimmedInstruction, _primTypes, _keyWords);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction, keyWordFor_)) {
            return keyWordFor(_offset, _i, _currentParent, _trimmedInstruction, _primTypes, _keyWords);
        }
        if (StringExpUtil.startsWithKeyWord(_trimmedInstruction, keyWordSwitch_)) {
            return keyWordSwitch(_offset, _i, _currentParent, _trimmedInstruction, keyWordSwitch_);
        }
        if (StringUtil.quickEq(_trimmedInstruction, keyWordStatic_)) {
            return keyWordStatic(_offset, _i, _currentParent, keyWordStatic_);
        }
        if (_trimmedInstruction.isEmpty()) {
            return keyWord(_offset, _i, _currentParent);
        }
        //Not an error
        return null;
    }

    private static AbsBk keyWord(int _offset, ParsedInstruction _i, BracedBlock _currentParent) {
        AbsBk br_;
        if (_currentParent instanceof RootBlock) {
            br_ = new InstanceBlock( _i.instLoc()+ _offset);
            int initNb_ = ((RootBlock) _currentParent).getCountInit();
            ((InitBlock) br_).setNumber(initNb_);
            ((InstanceBlock) br_).setInstanceNb(((RootBlock) _currentParent).getInstanceBlocks().size());
            ((RootBlock) _currentParent).getInstanceBlocks().add((InstanceBlock) br_);
        } else {
            br_ = new UnclassedBracedBlock( _i.instLoc()+ _offset);
        }
        br_.setBegin(_i.getIndex()+ _offset);
        br_.setLengthHeader(1);
        _currentParent.appendChild(br_);
        return br_;
    }

    private static StaticBlock keyWordStatic(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _keyWordStatic) {
        StaticBlock br_;
        br_ = new StaticBlock( _i.instLoc()+ _offset);
        _currentParent.appendChild(br_);
        if (_currentParent instanceof RootBlock) {
            int initNb_ = ((RootBlock) _currentParent).getCountInit();
            br_.setNumber(initNb_);
            br_.setStaticNb(((RootBlock) _currentParent).getStaticBlocks().size());
            ((RootBlock) _currentParent).getStaticBlocks().add(br_);
        }
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordStatic.length());
        return br_;
    }

    private static SwitchBlock keyWordSwitch(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordSwitch) {
        String exp_ = _trimmedInstruction.substring(_keyWordSwitch.length());
        int valueOffest_ = _i.instLoc() + _keyWordSwitch.length();
        int lastPar_ = exp_.lastIndexOf(END_CALLING);
        int labelOff_ = valueOffest_ + lastPar_+ 1;
        int afterLeftPar_ = exp_.indexOf(BEGIN_CALLING) + 1;
        valueOffest_ += afterLeftPar_;
        String label_ = exp_;
        boolean ok_ = false;
        if (afterLeftPar_ <= lastPar_) {
            exp_ = exp_.substring(afterLeftPar_, lastPar_);
            valueOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
            ok_ = true;
        }
        label_ = label_.substring(lastPar_ + 1);
        if (!label_.isEmpty()) {
            labelOff_ += StringUtil.getFirstPrintableCharIndex(label_);
        }
        SwitchBlock br_ = new SwitchBlock(new OffsetStringInfo(valueOffest_ + _offset, exp_.trim()), new OffsetStringInfo(labelOff_ + _offset, label_.trim()), _i.instLoc() + _offset);
        br_.getRes().partsAbsol(_i.getStringParts());
        if (!ok_) {
            br_.getBadIndexes().add(_i.getIndex()+ _offset);
        }
        _currentParent.appendChild(br_);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordSwitch.length());
        return br_;
    }

    private static AbsBk keyWordFor(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, PrimitiveTypes _primTypes, KeyWords _keyWords) {
        String keyWordFinal_ = _keyWords.getKeyWordFinal();
        String keyWordFor_ = _keyWords.getKeyWordFor();
        String exp_ = _trimmedInstruction.substring(keyWordFor_.length());
        int indexClassOffest_ = _i.instLoc() + keyWordFor_.length();
        int lastPar_ = exp_.lastIndexOf(END_CALLING);
        int labelOff_ = indexClassOffest_ + lastPar_+ 1;
        String label_ = exp_;
        int typeOffset_ = _i.instLoc() + _trimmedInstruction.indexOf(BEGIN_CALLING) + 1;
        ParsedLoopArrIndex parsedLoopArrIndex_ = new ParsedLoopArrIndex(exp_,indexClassOffest_);
        String indexClassName_ = parsedLoopArrIndex_.getIndexClassName();
        indexClassOffest_ = parsedLoopArrIndex_.getIndexClassOffest();
        exp_ = parsedLoopArrIndex_.getExp();
        int begCall_ = exp_.indexOf(BEGIN_CALLING);
        int endCall_ = exp_.lastIndexOf(END_CALLING);
        boolean okIndex_ = okIndex(parsedLoopArrIndex_, begCall_, endCall_);
        exp_ = mutableParts(exp_, begCall_, endCall_);
        String keyWordThat_ = _keyWords.getKeyWordThat();
        boolean finalLocalVar_ = false;
        boolean refVariable_ = false;
        int deltaAfter_;
        int finalOffset_ = typeOffset_;
        if (StringExpUtil.startsWithKeyWord(exp_.trim(), keyWordThat_)) {
            int thatLength_ = keyWordThat_.length();
            String substring_ = exp_.trim().substring(thatLength_);
            int next_ = StringExpUtil.nextPrintChar(0, substring_.length(), substring_);
            if (next_ > -1 && StringExpUtil.isTypeLeafChar(substring_.charAt(next_))) {
                refVariable_ = true;
                int delta_ = StringUtil.getFirstPrintableCharIndex(exp_) + keyWordThat_.length();
                deltaAfter_ = delta_;
                String afterDelta_ = exp_.substring(delta_);
                deltaAfter_ += StringExpUtil.getOffset(afterDelta_);
            } else {
                deltaAfter_ = StringExpUtil.getOffset(exp_);
            }
        } else {
            finalLocalVar_ = StringExpUtil.startsWithKeyWord(exp_.trim(), keyWordFinal_);
            deltaAfter_ = mutableNonRef(keyWordFinal_, exp_, finalLocalVar_);
        }
        typeOffset_ += deltaAfter_;
        exp_ = exp_.substring(deltaAfter_);
        String declaringType_ = getDeclaringTypeInstr(exp_, _keyWords);
        exp_ = exp_.substring(declaringType_.length());
        int initOff_ = typeOffset_ + declaringType_.length();
        CustList<SegmentStringPart> strInit_ = new CustList<SegmentStringPart>();
        int nextEltMut_ = getIndex(_offset +initOff_, _i.getStringParts(),exp_, strInit_);
        String expAfterType_ = exp_;
        if (nextEltMut_ > -1) {
            String init_ = exp_.substring(0, nextEltMut_);
            int off_ = StringExpUtil.getOffset(init_);
            int toOff_ = initOff_ + nextEltMut_+1;
            initOff_ += off_;
            exp_ = exp_.substring(nextEltMut_+1);
            CustList<SegmentStringPart> strTo_ = new CustList<SegmentStringPart>();
            int nextElt_ = getIndex(_offset +toOff_, _i.getStringParts(),exp_, strTo_);
            if (nextElt_ > -1) {
                String to_ = exp_.substring(0, nextElt_);
                int offTwo_ = StringExpUtil.getOffset(to_);
                int stepOff_ = toOff_ + nextElt_+1;
                toOff_ += offTwo_;
                exp_ = exp_.substring(nextElt_ + 1);
                String step_ = exp_;
                stepOff_ += StringExpUtil.getOffset(step_);
                label_ = label_.substring(lastPar_ + 1);
                labelOff_ += getLabelOffset(label_);
                OffsetStringInfo lab_ = new OffsetStringInfo(labelOff_ + _offset, label_.trim());
                OffsetStringInfo clNa_ = className(new OffsetStringInfo(indexClassOffest_ + _offset, indexClassName_.trim()), _primTypes);
                ForMutableIterativeLoop br_ = new ForMutableIterativeLoop(
                        _i.instLoc() + _offset, lab_,
                        new ManyLoopExpressionsContent(new OffsetFinalInfo(new OffsetBooleanInfo(finalOffset_ + _offset, finalLocalVar_),refVariable_),
                                new OffsetStringInfo(typeOffset_ + _offset, declaringType_.trim()),
                                new OffsetStringInfo(initOff_ + _offset, init_.trim()), new OffsetStringInfo(toOff_ + _offset, to_.trim()),
                                new OffsetStringInfo(stepOff_ + _offset, step_.trim()),
                                clNa_));
                _currentParent.appendChild(br_);
                br_.setTestOffset(_i.getIndex()+ _offset);
                br_.getResInit().partsAbsol(strInit_);
                br_.getResExp().partsAbsol(strTo_);
                br_.getResStep().partsAbsol(_i.getStringParts().mid(strInit_.size()+strTo_.size()));
                return endMutableFor(_offset, _i, keyWordFor_, okIndex_, br_);
            }
        }
        int nextElt_ = expAfterType_.indexOf(FOR_BLOCKS);
        if (nextElt_ <= -1) {
            return errForMutable(_offset, _i, _currentParent, keyWordFor_, okIndex_);
        }
        int expOffset_ = typeOffset_ + declaringType_.length();
        expOffset_ += nextElt_+1;
        int setOff_ = expOffset_-1;
        String init_ = expAfterType_.substring(0, nextElt_);
        exp_ = expAfterType_.substring(nextElt_+1);
        expOffset_ += StringExpUtil.getOffset(exp_);
        String variableName_ = init_.trim();
        label_ = label_.substring(lastPar_ + 1);
        labelOff_ += getLabelOffset(label_);
        if (StringExpUtil.isTypeLeafPart(variableName_)) {
            int varOffset_ = typeOffset_ + declaringType_.length();
            varOffset_ += StringUtil.getFirstPrintableCharIndex(init_);
            OffsetStringInfo lab_ = new OffsetStringInfo(labelOff_ + _offset, label_.trim());
            OffsetStringInfo clName_ = className(new OffsetStringInfo(indexClassOffest_ + _offset, indexClassName_.trim()),
                    _primTypes);
            ForEachLoop br_ = new ForEachLoop(new ListLoopExpressionContent(new OffsetClassVariableInfo(new OffsetStringInfo(typeOffset_ + _offset, declaringType_.trim()),
                    new OffsetStringInfo(varOffset_ + _offset, variableName_)), new OffsetStringInfo(expOffset_ + _offset, exp_.trim()),
                    clName_), _i.instLoc() + _offset, setOff_ + _offset,refVariable_, lab_);
            br_.getRes().partsAbsol(_i.getStringParts());
            _currentParent.appendChild(br_);
            return endMutableFor(_offset, _i, keyWordFor_, okIndex_, br_);
        }
        int nextIndexVar_ = variableName_.indexOf(',');
        if (nextIndexVar_ < 0) {
            return errForMutable(_offset, _i, _currentParent, keyWordFor_, okIndex_);
        }
        String firstVar_ = variableName_.substring(0, nextIndexVar_);
        String afterFirst_ = variableName_.substring(nextIndexVar_+1);
        String declaringTypeSec_ = getFoundType(afterFirst_);
        String padSecVar_= afterFirst_.substring(declaringTypeSec_.length());
        String secVar_ = padSecVar_.trim();
        if (StringExpUtil.isTypeLeafPart(secVar_)) {
            int varOffset_ = typeOffset_ + declaringType_.length();
            varOffset_ += StringUtil.getFirstPrintableCharIndex(init_);
            int secType_ = varOffset_;
            secType_ += nextIndexVar_+1;
            int secVarOff_ = secType_;
            secType_ += StringExpUtil.getOffset(declaringTypeSec_);
            secVarOff_ += declaringTypeSec_.length();
            secVarOff_ += StringUtil.getFirstPrintableCharIndex(padSecVar_);
            OffsetStringInfo lab_ = new OffsetStringInfo(labelOff_ + _offset, label_.trim());
            OffsetStringInfo clNa_ = className(new OffsetStringInfo(indexClassOffest_ + _offset, indexClassName_.trim()), _primTypes);
            ForEachTable br_ = new ForEachTable(
                    new TableLoopExpressionContent(
                    new OffsetClassVariableInfo(new OffsetStringInfo(typeOffset_ + _offset, declaringType_.trim()), new OffsetStringInfo(varOffset_ + _offset, firstVar_.trim())),
                    new OffsetClassVariableInfo(new OffsetStringInfo(secType_ + _offset, declaringTypeSec_.trim()), new OffsetStringInfo(secVarOff_ + _offset, secVar_)),
                    new OffsetStringInfo(expOffset_ + _offset, exp_.trim()), clNa_),
                    _i.instLoc() + _offset, setOff_ + _offset, refVariable_, lab_);
            br_.getRes().partsAbsol(_i.getStringParts());
            _currentParent.appendChild(br_);
            return endMutableFor(_offset, _i, keyWordFor_, okIndex_, br_);
        }
        return errForMutable(_offset, _i, _currentParent, keyWordFor_, okIndex_);
    }

    private static int mutableNonRef(String _keyWordFinal, String _exp, boolean _finalLocalVar) {
        int deltaAfter_;
        if (_finalLocalVar) {
            int delta_ = StringUtil.getFirstPrintableCharIndex(_exp) + _keyWordFinal.length();
            deltaAfter_ = delta_;
            String afterDelta_ = _exp.substring(delta_);
            deltaAfter_ += StringExpUtil.getOffset(afterDelta_);
        } else {
            deltaAfter_ = StringExpUtil.getOffset(_exp);
        }
        return deltaAfter_;
    }

    private static String mutableParts(String _exp, int _begCall, int _endCall) {
        String exp_ = _exp;
        if (_begCall >= 0 && _endCall >= _begCall + 1) {
            exp_ = exp_.substring(_begCall + 1, _endCall);
        }
        return exp_;
    }

    private static boolean okIndex(ParsedLoopArrIndex _parsedLoopArrIndex, int _begCall, int _endCall) {
        boolean okIndex_ = _parsedLoopArrIndex.isOkIndex();
        if (_begCall < 0 || _endCall < _begCall + 1) {
            okIndex_ = false;
        }
        return okIndex_;
    }

    private static AbsBk errForMutable(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _keyWordFor, boolean _okIndex) {
        AbsBk br_ = new UnclassedBracedBlock(_i.instLoc() + _offset);
        _currentParent.appendChild(br_);
        br_.getBadIndexes().add(_i.getIndex()+ _offset);
        return endMutableFor(_offset, _i, _keyWordFor, _okIndex, br_);
    }

    private static AbsBk endMutableFor(int _offset, ParsedInstruction _i, String _keyWordFor, boolean _okIndex, AbsBk _br) {
        _br.setBegin(_i.instLoc()+ _offset);
        _br.setLengthHeader(_keyWordFor.length());
        if (!_okIndex) {
            _br.getBadIndexes().add(_i.getIndex()+ _offset);
        }
        return _br;
    }

    private static ForIterativeLoop keyWordIter(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, PrimitiveTypes _primTypes, KeyWords _keyWords) {
        String keyWordIter_ = _keyWords.getKeyWordIter();
        String exp_ = _trimmedInstruction.substring(keyWordIter_.length());
        int indexClassOffest_ = _i.instLoc() + keyWordIter_.length();
        int lastPar_ = exp_.lastIndexOf(END_CALLING);
        int labelOff_ = indexClassOffest_ + lastPar_+ 1;
        String label_ = exp_;
        int typeOffset_ = _i.instLoc() + _trimmedInstruction.indexOf(BEGIN_CALLING) + 1;
        ParsedLoopArrIndex parsedLoopArrIndex_ = new ParsedLoopArrIndex(exp_,indexClassOffest_);
        boolean ok_ = parsedLoopArrIndex_.isOkIndex();
        String indexClassName_ = parsedLoopArrIndex_.getIndexClassName();
        indexClassOffest_ = parsedLoopArrIndex_.getIndexClassOffest();
        exp_ = parsedLoopArrIndex_.getExp();
        int begCall_ = exp_.indexOf(BEGIN_CALLING);
        int endIndex_ = exp_.lastIndexOf(END_CALLING);
        if (begCall_ < 0 || endIndex_ < begCall_ + 1) {
            ok_ = false;
        } else {
            exp_ = exp_.substring(begCall_ + 1, endIndex_);
        }
        String declaringType_ = getFoundType(exp_);
        int aftTypeOffset_ = typeOffset_ + declaringType_.length();
        int varOffset_ = aftTypeOffset_;
        typeOffset_ += StringExpUtil.getOffset(exp_);
        exp_ = exp_.substring(declaringType_.length());
        int eqIndex_ = exp_.indexOf(PART_SEPARATOR);
        String variable_ = "";
        if (eqIndex_ < 0) {
            ok_ = false;
        } else {
            variable_ = exp_.substring(0, eqIndex_);
            int firstOff_ = StringExpUtil.getOffset(variable_);
            varOffset_ += firstOff_;
            exp_ = exp_.substring(eqIndex_ + 1);
        }
        int aftVarOffset_ = aftTypeOffset_ + variable_.length()+1;
        CustList<SegmentStringPart> strInit_ = new CustList<SegmentStringPart>();
        int nextElt_ = getIndex(_offset +aftVarOffset_, _i.getStringParts(),exp_, strInit_);
        int initOff_ = aftVarOffset_;
        String init_ = "";
        if (nextElt_ < 0) {
            ok_ = false;
        } else {
            init_ = exp_.substring(0, nextElt_);
            int secondOff_ = StringExpUtil.getOffset(init_);
            initOff_ += secondOff_;
            exp_ = exp_.substring(nextElt_+1);
        }
        int afToOffset_ = aftVarOffset_ + init_.length()+1;
        CustList<SegmentStringPart> strTo_ = new CustList<SegmentStringPart>();
        nextElt_ = getIndex(_offset +afToOffset_, _i.getStringParts(),exp_, strTo_);
        int toOff_ = afToOffset_;
        String to_ = "";
        if (nextElt_ < 0) {
            ok_ = false;
        } else {
            to_ = exp_.substring(0, nextElt_);
            int thirdOff_ = StringExpUtil.getOffset(to_);
            toOff_ += thirdOff_;
        }
        boolean eq_ = false;
        int expOff_ = afToOffset_ + to_.length();
        int stepOff_ = expOff_ + 1;
        if (nextElt_ + 1 >= exp_.length()) {
            ok_ = false;
            stepOff_--;
        } else {
            if (exp_.charAt(nextElt_ + 1) == END_LINE) {
                eq_ = true;
                nextElt_++;
                stepOff_++;
            }
            exp_ = exp_.substring(nextElt_ + 1);
        }
        String step_ = exp_;
        stepOff_ += StringExpUtil.getOffset(step_);
        label_ = label_.substring(lastPar_ + 1);
        if (!label_.isEmpty()) {
            labelOff_ += StringUtil.getFirstPrintableCharIndex(label_);
        }
        OffsetStringInfo lab_ = new OffsetStringInfo(labelOff_ + _offset, label_.trim());
        OffsetStringInfo clNa_ = className(new OffsetStringInfo(indexClassOffest_ + _offset, indexClassName_.trim()),
                _primTypes);
        ForIterativeLoop br_ = new ForIterativeLoop(new OneLoopExpressionsContent(new OffsetClassVariableInfo(new OffsetStringInfo(typeOffset_ + _offset, declaringType_.trim()), new OffsetStringInfo(varOffset_ + _offset, variable_.trim())),
                new OffsetStringInfo(initOff_ + _offset, init_.trim()), new OffsetStringInfo(toOff_ + _offset, to_.trim()),
                new OffsetStringInfo(stepOff_ + _offset, step_.trim()), new OffsetBooleanInfo(expOff_ + _offset, eq_), clNa_), _i.instLoc() + _offset, lab_);
        if (!ok_) {
            br_.getBadIndexes().add(_i.getIndex()+ _offset);
        }
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(keyWordIter_.length());
        br_.getResInit().partsAbsol(strInit_);
        br_.getResExp().partsAbsol(strTo_);
        br_.getResStep().partsAbsol(_i.getStringParts().mid(strInit_.size()+strTo_.size()));
        _currentParent.appendChild(br_);
        return br_;
    }

    private static AbsBk keyWordForEach(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, PrimitiveTypes _primTypes, KeyWords _keyWords) {
        String keyWordForeach_ = _keyWords.getKeyWordForeach();
        String exp_ = _trimmedInstruction.substring(keyWordForeach_.length());
        int indexClassOffest_ = _i.instLoc() + keyWordForeach_.length();
        int lastPar_ = exp_.lastIndexOf(END_CALLING);
        int labelOff_ = indexClassOffest_ + lastPar_+ 1;
        String label_ = exp_;
        label_ = label_.substring(lastPar_ + 1);
        if (!label_.isEmpty()) {
            labelOff_ += StringUtil.getFirstPrintableCharIndex(label_);
        }
        int typeOffset_ = _i.instLoc() + _trimmedInstruction.indexOf(BEGIN_CALLING) + 1;
        ParsedLoopArrIndex parsedLoopArrIndex_ = new ParsedLoopArrIndex(exp_,indexClassOffest_);
        boolean ok_ = parsedLoopArrIndex_.isOkIndex();
        String indexClassName_ = parsedLoopArrIndex_.getIndexClassName();
        indexClassOffest_ = parsedLoopArrIndex_.getIndexClassOffest();
        exp_ = parsedLoopArrIndex_.getExp();
        String afterIndex_ = exp_.substring(exp_.indexOf(BEGIN_CALLING) + 1);
        typeOffset_ += StringUtil.getFirstPrintableCharIndex(afterIndex_);
        exp_ = afterIndex_;
        boolean refVariable_ = false;
        String keyWordThat_ = _keyWords.getKeyWordThat();
        if (StringExpUtil.startsWithKeyWord(exp_.trim(), keyWordThat_)) {
            refVariable_ = true;
            int delta_ = StringUtil.getFirstPrintableCharIndex(exp_) + keyWordThat_.length();
            int deltaAfter_ = delta_;
            String afterDelta_ = exp_.substring(delta_);
            deltaAfter_ += StringExpUtil.getOffset(afterDelta_);
            typeOffset_ += deltaAfter_;
            exp_ = exp_.substring(deltaAfter_);
        } else {
            exp_ = exp_.trim();
        }
        String declaringType_ = getFoundType(exp_);
        int varOffset_ = typeOffset_ + declaringType_.length();
        exp_ = exp_.substring(declaringType_.length());
        int forBlocks_ = exp_.indexOf(FOR_BLOCKS);
        int endIndex_ = exp_.lastIndexOf(END_CALLING);
        String variable_ = "";
        int expOffset_ = varOffset_;
        int setOff_ = expOffset_-1;
        if (forBlocks_ < 0 || endIndex_ < forBlocks_ + 1) {
            ok_ = false;
        } else {
            variable_ = exp_.substring(0, forBlocks_);
            varOffset_ += StringUtil.getFirstPrintableCharIndex(variable_);
            expOffset_ += forBlocks_+1;
            setOff_ = expOffset_-1;
            exp_ = exp_.substring(forBlocks_ + 1, endIndex_);
            expOffset_ += StringUtil.getFirstPrintableCharIndex(exp_);
        }
        String variableName_ = variable_.trim();
        AbsBk br_;
        if (StringExpUtil.isTypeLeafPart(variableName_)) {
            OffsetStringInfo lab_ = new OffsetStringInfo(labelOff_ + _offset, label_.trim());
            OffsetStringInfo clNa_ = className(new OffsetStringInfo(indexClassOffest_ + _offset, indexClassName_.trim()),
                    _primTypes);
            br_ = new ForEachLoop(new ListLoopExpressionContent(new OffsetClassVariableInfo(new OffsetStringInfo(typeOffset_+ _offset, declaringType_.trim()),
                    new OffsetStringInfo(varOffset_+ _offset, variableName_)),
                    new OffsetStringInfo(expOffset_+ _offset, exp_.trim()), clNa_), _i.instLoc()+ _offset,setOff_+ _offset,refVariable_, lab_);
            ((ForEachLoop)br_).getRes().partsAbsol(_i.getStringParts());
        } else {
            int nextIndexVar_ = variableName_.indexOf(',');
            String firstVar_ = "";
            String afterFirst_ = "";
            if (nextIndexVar_ < 0) {
                ok_ = false;
            } else {
                firstVar_ = variableName_.substring(0, nextIndexVar_).trim();
                afterFirst_ = variableName_.substring(nextIndexVar_+1);
            }
            String declaringTypeSec_ = getFoundType(afterFirst_);
            int secType_ = varOffset_;
            secType_ += nextIndexVar_+1;
            int secVarOff_ = secType_;
            secType_ += StringUtil.getFirstPrintableCharIndex(declaringTypeSec_);
            secVarOff_ += declaringTypeSec_.length();
            String padSecVar_= afterFirst_.substring(declaringTypeSec_.length());
            secVarOff_ += StringUtil.getFirstPrintableCharIndex(padSecVar_);
            String secVar_ = padSecVar_.trim();
            OffsetStringInfo lab_ = new OffsetStringInfo(labelOff_ + _offset, label_.trim());
            OffsetStringInfo clNa_ = className(new OffsetStringInfo(indexClassOffest_ + _offset, indexClassName_.trim()),
                    _primTypes);
            br_ = new ForEachTable(
                    new TableLoopExpressionContent(new OffsetClassVariableInfo(new OffsetStringInfo(typeOffset_+ _offset, declaringType_.trim()), new OffsetStringInfo(varOffset_+ _offset, firstVar_)),
                    new OffsetClassVariableInfo(new OffsetStringInfo(secType_+ _offset, declaringTypeSec_.trim()), new OffsetStringInfo(secVarOff_+ _offset, secVar_)),
                    new OffsetStringInfo(expOffset_+ _offset, exp_.trim()), clNa_), _i.instLoc()+ _offset,setOff_+ _offset, refVariable_, lab_);
            ((ForEachTable)br_).getRes().partsAbsol(_i.getStringParts());
        }
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(keyWordForeach_.length());
        if (!ok_) {
            br_.getBadIndexes().add(_i.getIndex()+ _offset);
        }
        _currentParent.appendChild(br_);
        return br_;
    }

    private static TryEval keyWordTry(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordTry) {
        String exp_ = _trimmedInstruction.substring(_keyWordTry.length());
        String label_ = exp_.trim();
        int conditionOffest_ = _i.instLoc() + _keyWordTry.length();
        int lastPar_ = StringUtil.getFirstPrintableCharIndex(exp_);
        if (!exp_.isEmpty()) {
            lastPar_--;
        }
        int labelOff_ = conditionOffest_ + lastPar_+ 1;
        TryEval br_ = new TryEval(new OffsetStringInfo(labelOff_ + _offset, label_.trim()), _i.instLoc() + _offset);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordTry.length());
        _currentParent.appendChild(br_);
        return br_;
    }

    private static FinallyEval keyWordFinally(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _keyWordFinally) {
        FinallyEval br_ = new FinallyEval(_i.instLoc() + _offset);
        _currentParent.appendChild(br_);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordFinally.length());
        return br_;
    }

    private static DoBlock keyWordDo(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordDo) {
        String exp_ = _trimmedInstruction.substring(_keyWordDo.length());
        String label_ = exp_.trim();
        int conditionOffest_ = _i.instLoc() + _keyWordDo.length();
        int lastPar_ = StringUtil.getFirstPrintableCharIndex(exp_);
        if (!exp_.isEmpty()) {
            lastPar_--;
        }
        int labelOff_ = conditionOffest_ + lastPar_+ 1;
        DoBlock br_ = new DoBlock(new OffsetStringInfo(labelOff_ + _offset, label_.trim()), _i.instLoc() + _offset);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordDo.length());
        _currentParent.appendChild(br_);
        return br_;
    }

    private static AbsBk keyWordElse(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, KeyWords _keyWords) {
        String keyWordElse_ = _keyWords.getKeyWordElse();
        String keyWordIf_ = _keyWords.getKeyWordIf();
        String afterElse_ = _trimmedInstruction.substring(keyWordElse_.length());
        String exp_ = afterElse_.trim();
        AbsBk br_;
        if (StringExpUtil.startsWithKeyWord(exp_, keyWordIf_)) {
            int beforeFirst_ = keyWordElse_.length();
            int firstPr_ = StringUtil.getFirstPrintableCharIndex(afterElse_);
            beforeFirst_ += firstPr_;
            exp_ = exp_.substring(keyWordIf_.length());
            int conditionOffest_ = _i.instLoc();
            conditionOffest_ += keyWordElse_.length();
            conditionOffest_ += firstPr_;
            conditionOffest_ += keyWordIf_.length();
            int beg_ = exp_.indexOf(BEGIN_CALLING);
            conditionOffest_ += beg_ +1;
            int lastPar_ = exp_.lastIndexOf(END_CALLING);
            boolean ok_ = false;
            if (beg_ >= 0 && lastPar_ >= beg_ + 1) {
                exp_ = exp_.substring(beg_ +1, lastPar_);
                conditionOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
                ok_ = true;
            }
            br_ = new ElseIfCondition(new OffsetStringInfo(conditionOffest_+ _offset, exp_.trim()),  _i.instLoc()+ _offset, keyWordIf_.length()+beforeFirst_);
            if (!ok_) {
                br_.getBadIndexes().add(_i.getIndex()+ _offset);
            }
            ((ConditionBlock)br_).setTestOffset(_i.getIndex()+ _offset);
            br_.setBegin(_i.instLoc()+beforeFirst_+ _offset);
            br_.setLengthHeader(keyWordIf_.length());
            ((ConditionBlock)br_).getRes().partsAbsol(_i.getStringParts());
        } else {
            br_ = new ElseCondition( _i.instLoc()+ _offset);
            br_.setBegin(_i.instLoc()+ _offset);
            br_.setLengthHeader(keyWordElse_.length());
        }
        _currentParent.appendChild(br_);
        return br_;
    }

    private static ElseIfCondition keyWordElseIf(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordElseif) {
        String exp_ = _trimmedInstruction.substring(_keyWordElseif.length());
        int conditionOffest_ = _i.instLoc() + _keyWordElseif.length();
        int beg_ = exp_.indexOf(BEGIN_CALLING);
        conditionOffest_ += beg_ +1;
        int lastPar_ = exp_.lastIndexOf(END_CALLING);
        boolean ok_ = false;
        if (beg_ >= 0 && lastPar_ >= beg_ + 1) {
            exp_ = exp_.substring(beg_ +1, lastPar_);
            conditionOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
            ok_ = true;
        }
        ElseIfCondition br_ = new ElseIfCondition(new OffsetStringInfo(conditionOffest_ + _offset, exp_.trim()), _i.instLoc() + _offset, _keyWordElseif.length());
        if (!ok_) {
            br_.getBadIndexes().add(_i.getIndex()+ _offset);
        }
        br_.setTestOffset(_i.getIndex()+ _offset);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordElseif.length());
        br_.getRes().partsAbsol(_i.getStringParts());
        _currentParent.appendChild(br_);
        return br_;
    }

    private static IfCondition keyWordIf(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordIf) {
        String exp_ = _trimmedInstruction.substring(_keyWordIf.length());
        int conditionOffest_ = _i.instLoc() + _keyWordIf.length();
        int lastPar_ = exp_.lastIndexOf(END_CALLING);
        int labelOff_ = conditionOffest_ + lastPar_+ 1;
        int beg_ = exp_.indexOf(BEGIN_CALLING);
        boolean ok_ = false;
        String label_ = exp_;
        if (beg_ >= 0 && lastPar_ >= beg_ + 1) {
            conditionOffest_ += beg_ +1;
            exp_ = exp_.substring(beg_ +1,lastPar_);
            conditionOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
            ok_ = true;
        }
        label_ = label_.substring(lastPar_ + 1);
        if (!label_.isEmpty()) {
            labelOff_ += StringUtil.getFirstPrintableCharIndex(label_);
        }
        IfCondition br_ = new IfCondition(new OffsetStringInfo(conditionOffest_ + _offset, exp_.trim()),
                new OffsetStringInfo(labelOff_ + _offset, label_.trim()),
                _i.instLoc() + _offset);
        if (!ok_) {
            br_.getBadIndexes().add(_i.getIndex()+ _offset);
        }
        br_.setTestOffset(_i.getIndex()+ _offset);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordIf.length());
        br_.getRes().partsAbsol(_i.getStringParts());
        _currentParent.appendChild(br_);
        return br_;
    }

    private static AbsBk keyWordCatch(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordCatch) {
        String info_ = _trimmedInstruction.substring(_keyWordCatch.length());
        int leftPar_ = info_.indexOf(BEGIN_CALLING);
        AbsBk br_;
        if (leftPar_ > -1) {
            int typeOffset_ = _keyWordCatch.length() + _i.instLoc() + leftPar_+1;
            info_ = info_.substring(leftPar_+1);
            String declaringType_ = getFoundType(info_);
            typeOffset_ += StringUtil.getFirstPrintableCharIndex(declaringType_);
            int variableOffset_ = typeOffset_ + declaringType_.length();
            info_ = info_.substring(declaringType_.length());
            variableOffset_ += StringUtil.getFirstPrintableCharIndex(info_);
            int endIndex_ = info_.indexOf(END_CALLING);
            String variable_ = "";
            boolean ok_ = false;
            if (endIndex_ >= 0) {
                variable_ = info_.substring(0, endIndex_);
                ok_ = true;
            }
            br_ = new CatchEval(new OffsetStringInfo(typeOffset_+ _offset, declaringType_.trim()),
                    new OffsetStringInfo(variableOffset_+ _offset,variable_.trim()),
                    _i.instLoc()+ _offset);
            if (!ok_) {
                br_.getBadIndexes().add(_i.getIndex()+ _offset);
            }
        } else {
            br_ = new NullCatchEval( _i.instLoc()+ _offset);
        }
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordCatch.length());
        _currentParent.appendChild(br_);
        return br_;
    }

    private static ConditionBlock keyWordWhile(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordWhile) {
        AbsBk child_ = _currentParent.getFirstChild();
        if (child_ != null) {
            while (child_.getNextSibling() != null) {
                child_ = child_.getNextSibling();
            }
        }
        String exp_ = _trimmedInstruction.substring(_keyWordWhile.length());
        int conditionOffest_ = _i.instLoc() + _keyWordWhile.length();
        int lastPar_ = exp_.lastIndexOf(END_CALLING);
        int labelOff_ = conditionOffest_ + lastPar_+ 1;
        int beg_ = exp_.indexOf(BEGIN_CALLING);
        String label_ = exp_;
        boolean ok_ = false;
        if (beg_ >= 0 && lastPar_ >= beg_ + 1) {
            conditionOffest_ += beg_ +1;
            exp_ = exp_.substring(beg_ +1, lastPar_);
            ok_ = true;
        }
        conditionOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
        ConditionBlock br_;
        if (child_ instanceof DoBlock) {
            br_ = new DoWhileCondition(new OffsetStringInfo(conditionOffest_+ _offset, exp_.trim()),  _i.instLoc()+ _offset);
        } else {
            label_ = label_.substring(lastPar_ + 1);
            if (!label_.isEmpty()) {
                labelOff_ += StringUtil.getFirstPrintableCharIndex(label_);
            }
            br_ = new WhileCondition(new OffsetStringInfo(conditionOffest_+ _offset, exp_.trim()),
                    new OffsetStringInfo(labelOff_+ _offset, label_.trim()),
                    _i.instLoc()+ _offset);
        }
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordWhile.length());
        if (!ok_) {
            br_.getBadIndexes().add(_i.getIndex()+ _offset);
        }
        br_.setTestOffset(_i.getIndex()+ _offset);
        br_.getRes().partsAbsol(_i.getStringParts());
        _currentParent.appendChild(br_);
        return br_;
    }

    private static DefaultCondition keyWordDefaultLong(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordDefault) {
        String exp_ = _trimmedInstruction.substring(_keyWordDefault.length());
        int valueOffest_ = _i.instLoc() + _keyWordDefault.length();
        valueOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
        DefaultCondition br_ = new DefaultCondition(
                _i.instLoc() + _offset, exp_.trim(), valueOffest_ + _offset);
        _currentParent.appendChild(br_);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordDefault.length());
        return br_;
    }

    private static DefaultCondition keyWordDefaultShort(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _keyWordDefault) {
        DefaultCondition br_ = new DefaultCondition(
                _i.instLoc() + _offset);
        _currentParent.appendChild(br_);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordDefault.length());
        return br_;
    }

    private static CaseCondition keyWordCase(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, KeyWords _keyWords) {
        String keyWordCase_ = _keyWords.getKeyWordCase();
        String exp_ = _trimmedInstruction.substring(keyWordCase_.length());
        int valueOffest_ = _i.instLoc() + keyWordCase_.length();
        if (!exp_.trim().isEmpty()) {
            valueOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
        }
        String value_ = exp_.trim();
        String declaringType_ = getDeclaringTypeInstr(value_, _keyWords);
        String varName_;
        if (!declaringType_.isEmpty()) {
            varName_ = value_.substring(declaringType_.length());
        } else {
            varName_ = "";
        }
        String trimPreVar_ = varName_.trim();
        int sepCond_ = trimPreVar_.indexOf(':');
        String trimVar_;
        if (sepCond_ >= 0) {
            trimVar_ = trimPreVar_.substring(0,sepCond_).trim();
        } else {
            trimVar_ = trimPreVar_;
        }
        int fullValueOffset_ = valueOffest_ + _offset;
        boolean isVar_ = StringExpUtil.isTypeLeafPart(trimVar_);
        CaseCondition br_;
        if (!isVar_){
            br_ = new CaseCondition(
                    new OffsetStringInfo(fullValueOffset_, value_),
                    _i.instLoc()+ _offset, "", new OffsetStringInfo(0,""),new OffsetStringInfo(fullValueOffset_,""));
        } else if (sepCond_ >= 0) {
            int afterTypeOff_ = fullValueOffset_ + declaringType_.length();
            int variableOffset_ = afterTypeOff_ + StringExpUtil.getOffset(varName_);
            String substring_ = trimPreVar_.substring(sepCond_ + 1);
            int conditionOffset_ = variableOffset_ + 1 + sepCond_ + StringExpUtil.getOffset(substring_);
            br_ = new CaseCondition(
                    new OffsetStringInfo(fullValueOffset_, value_),
                    _i.instLoc()+ _offset, declaringType_, new OffsetStringInfo(variableOffset_,trimVar_),new OffsetStringInfo(conditionOffset_,substring_.trim()));
        } else {
            int afterTypeOff_ = fullValueOffset_ + declaringType_.length();
            int variableOffset_ = afterTypeOff_ + StringExpUtil.getOffset(varName_);
            br_ = new CaseCondition(
                    new OffsetStringInfo(fullValueOffset_, value_),
                    _i.instLoc()+ _offset, declaringType_, new OffsetStringInfo(variableOffset_,trimVar_),new OffsetStringInfo(fullValueOffset_,""));
        }

        //if next after i starts with brace or not
        _currentParent.appendChild(br_);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(keyWordCase_.length());
        br_.getRes().partsAbsol(_i.getStringParts());
        return br_;
    }

    private static Throwing keyWordThrow(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordThrow) {
        String exp_ = _trimmedInstruction.substring(_keyWordThrow.length());
        int expressionOffest_ = _i.instLoc() + _keyWordThrow.length();
        if (!exp_.trim().isEmpty()) {
            expressionOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
        }
        Throwing br_ = new Throwing(new OffsetStringInfo(expressionOffest_ + _offset, exp_.trim()), _i.instLoc() + _offset);
        _currentParent.appendChild(br_);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordThrow.length());
        br_.getRes().partsAbsol(_i.getStringParts());
        return br_;
    }

    private static ReturnMethod keyWordReturn(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordReturn) {
        String exp_ = _trimmedInstruction.substring(_keyWordReturn.length());
        int expressionOffest_ = _i.instLoc() + _keyWordReturn.length();
        if (!exp_.trim().isEmpty()) {
            expressionOffest_ += StringUtil.getFirstPrintableCharIndex(exp_);
        }
        ReturnMethod br_ = new ReturnMethod(new OffsetStringInfo(expressionOffest_ + _offset, exp_.trim()), _i.instLoc() + _offset);
        _currentParent.appendChild(br_);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordReturn.length());
        br_.getRes().partsAbsol(_i.getStringParts());
        return br_;
    }

    private static ContinueBlock keyWordContinue(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordContinue) {
        String exp_ = _trimmedInstruction.substring(_keyWordContinue.length());
        String label_ = exp_.trim();
        int conditionOffest_ = _i.instLoc() + _keyWordContinue.length();
        int lastPar_ = StringUtil.getFirstPrintableCharIndex(exp_);
        if (!exp_.isEmpty()) {
            lastPar_--;
        }
        int labelOff_ = conditionOffest_ + lastPar_+ 1;
        ContinueBlock br_ = new ContinueBlock(new OffsetStringInfo(labelOff_ + _offset, label_.trim()), _i.instLoc() + _offset);
        _currentParent.appendChild(br_);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordContinue.length());
        return br_;
    }

    private static BreakBlock keyWordBreak(int _offset, ParsedInstruction _i, BracedBlock _currentParent, String _trimmedInstruction, String _keyWordBreak) {
        String exp_ = _trimmedInstruction.substring(_keyWordBreak.length());
        String label_ = exp_.trim();
        int conditionOffest_ = _i.instLoc() + _keyWordBreak.length();
        int lastPar_ = StringUtil.getFirstPrintableCharIndex(exp_);
        if (!exp_.isEmpty()) {
            lastPar_--;
        }
        int labelOff_ = conditionOffest_ + lastPar_+ 1;
        BreakBlock br_ = new BreakBlock(new OffsetStringInfo(labelOff_ + _offset, label_.trim()), _i.instLoc() + _offset);
        br_.setBegin(_i.instLoc()+ _offset);
        br_.setLengthHeader(_keyWordBreak.length());
        _currentParent.appendChild(br_);
        return br_;
    }

    private static boolean startsWithDefVar(String _trimmedInstruction, String _keyWordDefault) {
        return StringExpUtil.startsWithKeyWord(_trimmedInstruction, _keyWordDefault)&&StringExpUtil.isTypeLeafPart(_trimmedInstruction.substring(_keyWordDefault.length()).trim());
    }

    private static int getLabelOffset(String _label) {
        if (_label.isEmpty()) {
            return 0;
        }
        return StringUtil.getFirstPrintableCharIndex(_label);
    }

    static String getFoundType(String _found) {
        ParsedType p_ = new ParsedType();
        p_.parse(_found);
        return p_.getInstruction().toString();
    }

    private static boolean isOperatorCharacter(char _char) {
        return _char == '+' || _char == '-' || _char == '*' || _char == '%' || _char == '/' || _char == '!' || _char == '=' || _char == '<' || _char == '>' || _char == '&' || _char == '|' || _char == '^' || _char == '~';
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
    private static int getIndex(int _offset, CustList<SegmentStringPart> _strs, String _info, CustList<SegmentStringPart> _filter) {
        int indexInstr_ = 0;
        int instrLen_ = _info.length();
        int localCallings_ = 0;
        while (indexInstr_ < instrLen_) {
            char locChar_ = _info.charAt(indexInstr_);
            int until_ = until(_offset, _strs, indexInstr_, _filter);
            if (until_ > indexInstr_) {
                indexInstr_ = until_;
                continue;
            }
            if (localCallings_ == 0 && locChar_ == END_LINE) {
                return indexInstr_;
            }
            if (locChar_ == BEGIN_CALLING||locChar_ == BEGIN_ARRAY||locChar_ == BEGIN_BLOCK) {
                localCallings_++;
            }
            if (locChar_ == END_CALLING||locChar_ == END_ARRAY||locChar_ == END_BLOCK) {
                localCallings_--;
            }
            indexInstr_++;
        }
        return -1;
    }

    public static OffsetStringInfo className(OffsetStringInfo _classIndex, PrimitiveTypes _primTypes) {
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _primTypes.getAliasPrimInteger();
        }
        return new OffsetStringInfo(_classIndex.getOffset(),classIndex_);
    }
    private static int until(int _offset, CustList<SegmentStringPart> _strs, int _indexInstr, CustList<SegmentStringPart> _filter) {
        int until_ = _indexInstr;
        for (SegmentStringPart s: _strs) {
            if (s.getBegin() == _offset + _indexInstr) {
                _filter.add(s);
                until_ = s.getEnd() - _offset;
                break;
            }
        }
        return until_;
    }

    private static boolean canHaveElements(AbsBk _bl) {
        if (!(_bl instanceof EnumBlock)) {
            return false;
        }
        return ((EnumBlock)_bl).isCanHaveElements();
    }

}
