package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ClassFieldBlock;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.accessing.OperatorAccessor;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaPartTypeUtil;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingSuperTypes;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.Members;
import code.expressionlanguage.analyze.util.TypeInfo;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.assign.blocks.*;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.files.FileResolver;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.Struct;
import code.util.*;

public final class ClassesUtil {
    private static final char DOT = '.';

    private ClassesUtil(){
    }

    public static void tryValidateCustom(StringMap<String> _files, ContextEl _context) {
        builtTypes(_files, _context, false);
    }

    public static void buildPredefinedBracesBodies(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        StringMap<String> files_ = stds_.buildFiles(_context);
        builtTypes(files_, _context, true);
        ValidatorStandard.buildIterable(_context);
    }
    public static void processBracedClass(ExecFileBlock _exFile, RootBlock _outer, RootBlock _root, ContextEl _context) {
        String fullName_ = _root.getFullName();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        if (_context.getClasses().getClassBodiesMap().contains(fullName_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedType(),
                    fullName_);
            _context.addError(d_);
            _root.addNameErrors(d_);
        }
        page_.setCurrentBlock(_root);
        page_.setCurrentAnaBlock(_root);
        String packageName_;
        packageName_ = _root.getPackageName();
        LgNames lgNames_ = _context.getStandards();
        if (packageName_.trim().isEmpty()) {
            FoundErrorInterpret badCl_ = new FoundErrorInterpret();
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setIndexFile(_root.getBegin());
            //key word category len
            badCl_.buildError(_context.getAnalysisMessages().getEmptyPackage());
            _context.addError(badCl_);
            _root.addNameErrors(badCl_);
        } else {
            StringList elements_ = StringList.splitChars(packageName_, DOT);
            for (String e: elements_) {
                String tr_ = e.trim();
                TokenErrorMessage res_ = ManageTokens.partClass(_context).checkStdToken(_context, tr_);
                if (res_.isError()) {
                    FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                    badCl_.setFileName(_root.getFile().getFileName());
                    badCl_.setIndexFile(_root.getIdRowCol());
                    //pkg part or dot
                    badCl_.setBuiltError(res_.getMessage());
                    _context.addError(badCl_);
                    _root.addNameErrors(badCl_);
                }
            }
        }
        String className_;
        className_ = _root.getName().trim();
        TokenErrorMessage resClName_ = ManageTokens.partClass(_context).checkStdToken(_context, className_);
        if (resClName_.isError()) {
            FoundErrorInterpret badCl_ = new FoundErrorInterpret();
            badCl_.setFileName(_root.getFile().getFileName());
            badCl_.setIndexFile(_root.getIdRowCol());
            //name part if possible or original type
            badCl_.setBuiltError(resClName_.getMessage());
            _context.addError(badCl_);
            _root.addNameErrors(badCl_);
        }
        String fullDef_ = _root.getFullDefinition();
        StringList varTypes_ = new StringList();
        String objectClassName_ = _context.getStandards().getAliasObject();
        StringList params_ = StringExpUtil.getAllTypes(fullDef_);
        StringList namesFromParent_ = new StringList();
        RootBlock r_ = _root;
        if (!r_.isStaticType()) {
            while (true) {
                r_ = (RootBlock) r_.getParent();
                for (TypeVar t: r_.getParamTypes()) {
                    namesFromParent_.add(t.getName());
                }
                if (r_.isStaticType()) {
                    break;
                }
            }
        }
        int tempOff_ = _root.getTemplateDefOffset() + 1;
        for (String p: params_.mid(CustList.SECOND_INDEX)) {
            int delta_ = 0;
            String name_;
            if (p.startsWith(Templates.PREFIX_VAR_TYPE)) {
                delta_++;
                name_ = p.substring(Templates.PREFIX_VAR_TYPE.length());
            } else {
                name_ = p;
            }
            TypeVar type_ = new TypeVar();
            int indexDef_ = name_.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(name_, indexDef_);
            String id_ = parts_.first();
            int offId_ = tempOff_ + StringList.getFirstPrintableCharIndex(p);
            if (id_.isEmpty()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //char after def
                badCl_.buildError(_context.getAnalysisMessages().getEmptyPartClassName());
                _context.addError(badCl_);
                StringList constraints_ = new StringList();
                Ints ct_ = new Ints();
                ct_.add(0);
                _root.getParamTypesConstraintsOffset().add(ct_);
                constraints_.add(objectClassName_);
                type_.getErrors().add(badCl_.getBuiltError());
                type_.setConstraints(constraints_);
                type_.setName(id_);
                _root.getParamTypes().add(type_);
                type_.setOffset(offId_);
                type_.setLength(id_.length()+1);
                tempOff_ += p.length() + 1;
                continue;
            }
            TokenErrorMessage res_ = ManageTokens.partVarClass(_context).checkStdToken(_context,id_);
            if (res_.isError()) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.setBuiltError(res_.getMessage());
                _context.addError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            if (StringList.contains(varTypes_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.buildError(_context.getAnalysisMessages().getDuplicatedPartVarClassName(),
                        id_
                );
                _context.addError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            if (StringList.contains(namesFromParent_, id_)) {
                FoundErrorInterpret badCl_ = new FoundErrorInterpret();
                badCl_.setFileName(_root.getFile().getFileName());
                badCl_.setIndexFile(offId_);
                //id_ len
                badCl_.buildError(_context.getAnalysisMessages().getDuplicatedPartVarClassName(),
                        id_
                );
                _context.addError(badCl_);
                type_.getErrors().add(badCl_.getBuiltError());
            }
            varTypes_.add(id_);
            StringList constraints_ = new StringList();
            if (indexDef_ != CustList.INDEX_NOT_FOUND_ELT) {
                int begin_ = delta_ + indexDef_;
                Ints ct_ = new Ints();
                for (String b: StringList.splitChars(parts_.last().substring(1), Templates.SEP_BOUNDS)) {
                    int off_ = begin_ + StringList.getFirstPrintableCharIndex(b);
                    constraints_.add(b);
                    ct_.add(off_);
                    begin_ += b.length() + 1;
                }
                _root.getParamTypesConstraintsOffset().add(ct_);
            } else {
                Ints ct_ = new Ints();
                ct_.add(0);
                _root.getParamTypesConstraintsOffset().add(ct_);
                constraints_.add(objectClassName_);
            }
            type_.setConstraints(constraints_);
            type_.setName(id_);
            _root.getParamTypes().add(type_);
            type_.setOffset(offId_);
            type_.setLength(id_.length()+delta_);
            tempOff_ += p.length() + 1;
        }
        if (_root instanceof EnumBlock) {
            String fullNameOrig_ = ((EnumBlock)_root).getOriginalName();
            StringBuilder generic_ = new StringBuilder(fullNameOrig_);
            if (!_root.getParamTypes().isEmpty()) {
                StringList vars_ = new StringList();
                for (TypeVar t:_root.getParamTypes()) {
                    vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
                }
                generic_.append(Templates.TEMPLATE_BEGIN);
                generic_.append(StringList.join(vars_, Templates.TEMPLATE_SEP));
                generic_.append(Templates.TEMPLATE_END);
            }
            StringBuilder sBuild_ = new StringBuilder(_context.getStandards().getAliasEnumParam());
            sBuild_.append(Templates.TEMPLATE_BEGIN);
            sBuild_.append(generic_);
            sBuild_.append(Templates.TEMPLATE_END);
            String type_ = sBuild_.toString();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof InnerElementBlock) {
            InnerElementBlock i_ = (InnerElementBlock) _root;
            EnumBlock par_ = (EnumBlock) _root.getParent();
            String type_ = StringList.concat(par_.getOriginalName(),i_.getTempClass());
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (_root instanceof AnnotationBlock) {
            String type_ = _context.getStandards().getAliasAnnotationType();
            _root.getDirectSuperTypes().add(type_);
            _root.getExplicitDirectSuperTypes().put(-1, false);
            _root.getRowColDirectSuperTypes().put(-1, type_);
        }
        if (lgNames_.getStandards().contains(fullName_)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedTypeStd(),
                    fullName_);
            _context.addError(d_);
            _root.addNameErrors(d_);
        }
        if (PrimitiveTypeUtil.isPrimitive(fullName_, _context)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(_root.getFile().getFileName());
            d_.setIndexFile(_root.getIdRowCol());
            //original type len
            d_.buildError(_context.getAnalysisMessages().getDuplicatedTypePrim(),
                    fullName_);
            _context.addError(d_);
            _root.addNameErrors(d_);
        }
        if (_root.getNameLength() == 0) {
            return;
        }
        page_.getFoundTypes().add(_root);
        page_.getAllFoundTypes().add(_root);
        page_.getHeaders().getAllFound().add(_root);
        if (_root instanceof ClassBlock) {
            ExecClassBlock e_ = new ExecClassBlock((ClassBlock) _root);
            page_.getMapTypes().put(_root, e_);
            page_.getMapTypesUniqType().put((ClassBlock) _root, e_);
            _context.getClasses().getClassBodiesMap().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof EnumBlock) {
            ExecEnumBlock e_ = new ExecEnumBlock(_root);
            page_.getMapTypes().put(_root, e_);
            page_.getMapTypesUniqType().put((EnumBlock) _root, e_);
            _context.getClasses().getClassBodiesMap().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InterfaceBlock) {
            ExecInterfaceBlock e_ = new ExecInterfaceBlock(_root);
            page_.getMapTypes().put(_root, e_);
            page_.getMapInterfaces().put(_root, e_);
            _context.getClasses().getClassBodiesMap().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof AnnotationBlock) {
            ExecAnnotationBlock e_ = new ExecAnnotationBlock(_root);
            page_.getMapTypes().put(_root, e_);
            page_.getMapInterfaces().put(_root, e_);
            _context.getClasses().getClassBodiesMap().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
        if (_root instanceof InnerElementBlock) {
            ExecInnerElementBlock e_ = new ExecInnerElementBlock((InnerElementBlock) _root);
            page_.getMapTypes().put(_root, e_);
            page_.getMapTypesUniqType().put((InnerElementBlock) _root, e_);
            page_.getMapInnerEltTypes().put((InnerElementBlock) _root, e_);
            _context.getClasses().getClassBodiesMap().put(fullName_, e_);
            appendType(_exFile, _outer, _root, e_);
        }
    }

    private static void appendType(ExecFileBlock _exFile, RootBlock _outer, RootBlock _root, ExecRootBlock e_) {
        if (_outer == _root) {
            _exFile.appendChild(e_);
        }
    }

    public static void builtTypes(StringMap<String> _files, ContextEl _context, boolean _predefined) {
        tryBuildBracedClassesBodies(_files, _context, _predefined);
        validateInheritingClasses(_context);
        validateIds(_context);
        validateOverridingInherit(_context);
        validateEl(_context);
        AnaTypeUtil.checkInterfaces(_context);
    }

    public static void tryBuildBracedClassesBodies(StringMap<String> _files, ContextEl _context, boolean _predefined) {
        parseFiles(_context, _files, _predefined);
        AnalyzedPageEl page_ = _context.getAnalyzing();
        IdMap<RootBlock, ExecRootBlock> mapTypes_ = page_.getMapTypes();
        for (EntryCust<RootBlock,ExecRootBlock> e: mapTypes_.entryList()) {
            ExecRootBlock current_ = e.getValue();
            RootBlock k_ = e.getKey();
            Members mem_ = new Members();
            mem_.getAllAnnotables().addEntry(k_,current_);
            for (Block b: getDirectChildren(k_)) {
                if (b instanceof RootBlock) {
                    ExecRootBlock val_ = mapTypes_.getVal((RootBlock) b);
                    current_.appendChild(val_);
                    mem_.getAllAnnotables().addEntry((RootBlock) b,val_);
                }
                if (b instanceof InnerElementBlock) {
                    ExecInnerElementBlock val_ = page_.getMapInnerEltTypes().getVal((InnerElementBlock) b);
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllElementFields().addEntry((InnerElementBlock) b,val_);
                }
                if (b instanceof ElementBlock) {
                    ExecElementBlock val_ = new ExecElementBlock((ElementBlock) b);
                    current_.appendChild(val_);
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllElementFields().addEntry((ElementBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((ElementBlock) b,val_);
                }
                if (b instanceof FieldBlock) {
                    ExecFieldBlock val_ = new ExecFieldBlock((FieldBlock) b);
                    current_.appendChild(val_);
                    mem_.getAllFields().addEntry((InfoBlock) b,val_);
                    mem_.getAllExplicitFields().addEntry((FieldBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((FieldBlock) b,val_);
                }
                if (b instanceof ConstructorBlock) {
                    ExecConstructorBlock val_ = new ExecConstructorBlock((ConstructorBlock)b);
                    current_.appendChild(val_);
                    mem_.getAllCtors().addEntry((ConstructorBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((ConstructorBlock) b,val_);
                    mem_.getAllNamed().addEntry((ConstructorBlock) b,val_);
                }
                if (b instanceof OverridableBlock) {
                    ExecOverridableBlock val_ = new ExecOverridableBlock((OverridableBlock)b);
                    current_.appendChild(val_);
                    mem_.getAllMethods().addEntry((OverridableBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((OverridableBlock) b,val_);
                    mem_.getAllNamed().addEntry((OverridableBlock) b,val_);
                }
                if (b instanceof AnnotationMethodBlock) {
                    ExecAnnotationMethodBlock val_ = new ExecAnnotationMethodBlock((AnnotationMethodBlock)b);
                    current_.appendChild(val_);
                    mem_.getAllAnnotMethods().addEntry((AnnotationMethodBlock) b,val_);
                    mem_.getAllAnnotables().addEntry((AnnotationMethodBlock) b,val_);
                    mem_.getAllNamed().addEntry((AnnotationMethodBlock) b,val_);
                }
                if (b instanceof InstanceBlock) {
                    ExecInstanceBlock val_ = new ExecInstanceBlock(b.getOffset());
                    current_.appendChild(val_);
                    mem_.getAllInits().put((InitBlock) b,val_);
                }
                if (b instanceof StaticBlock) {
                    ExecStaticBlock val_ = new ExecStaticBlock(b.getOffset());
                    current_.appendChild(val_);
                    mem_.getAllInits().put((InitBlock) b,val_);
                }
            }
            page_.getMapMembers().addEntry(k_, mem_);
        }
        StringList pkgFound_ = _context.getAnalyzing().getPackagesFound();
        pkgFound_.addAllElts(getPackages(_context));
        validatePkgNames(_context);
    }

    private static void parseFiles(ContextEl _context, StringMap<String> _files, boolean _predefined) {
        for (EntryCust<String,String> f: _files.entryList()) {
            String file_ = f.getKey();
            String content_ = f.getValue();
            FileResolver.parseFile(file_, content_, _predefined, _context);
        }
    }

    private static void validatePkgNames(ContextEl _context) {
        StringList pkgFound_ = _context.getAnalyzing().getPackagesFound();
        for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
            if (!(r.getParent() instanceof FileBlock)) {
                continue;
            }
            String fullName_ = r.getFullName();
            for (String p: pkgFound_) {
                if (!p.startsWith(fullName_)) {
                    continue;
                }
                //ERROR
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(r.getFile().getFileName());
                d_.setIndexFile(r.getIdRowCol());
                //original id len
                d_.buildError(_context.getAnalysisMessages().getDuplicatedTypePkg(),
                        fullName_,
                        p);
                _context.addError(d_);
                r.addNameErrors(d_);
            }
        }
    }

    public static CustList<ExecBlock> getSortedDescNodes(ExecBlock _root) {
        CustList<ExecBlock> list_ = new CustList<ExecBlock>();
        ExecBlock c_ = _root;
        ExecBlock f_ = c_.getFirstChild();
        list_.add(c_);
        if (f_ == null) {
            return list_;
        }
        c_ = f_;
        while (c_ != null) {
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    public static ExecBlock getNext(ExecBlock _current, ExecBlock _root) {
        ExecBlock n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        ExecBlock current_ = _current;
        while (true) {
            n_ = current_.getNextSibling();
            if (n_ != null) {
                return n_;
            }
            n_ = current_.getParent();
            if (n_ == _root) {
                return null;
            }
            current_ = n_;
        }
    }

    public static CustList<Block> getDirectChildren(Block _element) {
        CustList<Block> list_ = new CustList<Block>();
        if (_element == null) {
            return list_;
        }
        Block elt_ = _element.getFirstChild();
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public static void validateInheritingClasses(ContextEl _context) {
        String objectClassName_ = _context.getStandards().getAliasObject();
        Classes classes_ = _context.getClasses();
        validateInheritingClassesId(_context);
        AnalyzedPageEl page_ = _context.getAnalyzing();
        CustList<RootBlock> listTypes_ = page_.getListTypesNames();
        for (RootBlock s: listTypes_) {
            page_.setCurrentBlock(s);
            page_.setCurrentAnaBlock(s);
            ExecRootBlock val_ = page_.getMapTypes().getVal(s);
            s.buildDirectGenericSuperTypes(_context, val_);
        }
        for (RootBlock c: page_.getFoundTypes()) {
            page_.setCurrentBlock(c);
            page_.setCurrentAnaBlock(c);
            c.buildMapParamType(_context,page_.getMapTypes().getVal(c));
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: page_.getMapTypes().entryList()) {
            e.getValue().buildMapParamType(e.getKey());
        }
        for (RootBlock c: page_.getFoundTypes()) {
            if (c.isStaticType()) {
                continue;
            }
            StringList allPossibleDirectSuperTypes_ = new StringList();
            StringList allDirectSuperTypes_ = new StringList();
            StringList allAncestors_ = new StringList();
            RootBlock p_ = c.getParentType();
            while (p_ != null) {
                allAncestors_.add(p_.getFullName());
                p_ = p_.getParentType();
            }
            for (AnaResultPartType s: c.getResults()) {
                GeneType s_ = _context.getClassBody(StringExpUtil.getIdFromAllTypes(s.getResult()));
                if (!(s_ instanceof ExecRootBlock)) {
                    continue;
                }
                if (s_.isStaticType()) {
                    continue;
                }
                allDirectSuperTypes_.add(s_.getFullName());
            }
            for (String a: allAncestors_) {
                ExecRootBlock a_ = classes_.getClassBody(a);
                for (ExecBlock m: ExecBlock.getDirectChildren(a_)) {
                    if (!(m instanceof ExecRootBlock)) {
                        continue;
                    }
                    ExecRootBlock m_ = (ExecRootBlock) m;
                    allPossibleDirectSuperTypes_.add(m_.getFullName());
                }
                for (String s: a_.getAllSuperTypes()) {
                    GeneType g_ = _context.getClassBody(s);
                    if (!(g_ instanceof ExecRootBlock)) {
                        continue;
                    }
                    ExecRootBlock s_ = (ExecRootBlock) g_;
                    for (ExecBlock m: ExecBlock.getDirectChildren(s_)) {
                        if (!(m instanceof ExecRootBlock)) {
                            continue;
                        }
                        ExecRootBlock m_ = (ExecRootBlock) m;
                        allPossibleDirectSuperTypes_.add(m_.getFullName());
                    }
                }
            }
            if (!allPossibleDirectSuperTypes_.containsAllObj(allDirectSuperTypes_)) {
                for (String s: allDirectSuperTypes_) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(c.getFile().getFileName());
                    enum_.setIndexFile(c.getIdRowCol());
                    //super type len
                    enum_.buildError(_context.getAnalysisMessages().getBadInheritsType(),
                            c.getFullName(),
                            s);
                    _context.addError(enum_);
                    c.addNameErrors(enum_);
                }
            }
        }
        validateSingleParameterizedClasses(_context);
        checkTemplatesDef(_context, objectClassName_);
    }

    public static void validateInheritingClassesId(ContextEl _context) {
        String objectClassName_ = _context.getStandards().getAliasObject();
        String enumClassName_ = _context.getStandards().getAliasEnumType();
        String enumParamClassName_ = _context.getStandards().getAliasEnumParam();
        String annotName_ = _context.getStandards().getAliasAnnotationType();
        StringMap<Boolean> builtTypes_ = new StringMap<Boolean>();
        IdList<RootBlock> stClNames_ = new IdList<RootBlock>(_context.getAnalyzing().getFoundTypes());
        for (RootBlock r: _context.getAnalyzing().getPreviousFoundTypes()) {
            builtTypes_.put(r.getFullName(), true);
        }
        for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
            builtTypes_.put(r.getFullName(), false);
        }
        while (true) {
            IdList<RootBlock> next_ = new IdList<RootBlock>();
            for (RootBlock r: stClNames_) {
                ExecRootBlock exec_ = _context.getAnalyzing().getMapTypes().getVal(r);
                String c= r.getFullName();
                boolean ready_ = true;
                int index_ = 0;
                StringMap<Integer> foundNames_ = new StringMap<Integer>();
                for (EntryCust<Integer, String> e: r.getRowColDirectSuperTypes().entryList()) {
                    String s = e.getValue();
                    s = StringExpUtil.removeDottedSpaces(s);
                    String idSuper_ = StringExpUtil.getIdFromAllTypes(s);
                    int offset_ = e.getKey();
                    String void_ = _context.getStandards().getAliasVoid();
                    if (StringList.quickEq(idSuper_, void_)) {
                        FoundErrorInterpret undef_ = new FoundErrorInterpret();
                        undef_.setFileName(r.getFile().getFileName());
                        undef_.setIndexFile(offset_);
                        //_in len
                        undef_.buildError(_context.getAnalysisMessages().getVoidType(),
                                void_);
                        _context.addError(undef_);
                        r.addNameErrors(undef_);
                        index_++;
                        continue;
                    }
                    if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                        if (_context.getStandards().getStandards().contains(idSuper_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //idSuper_ len
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    idSuper_);
                            _context.addError(undef_);
                            r.addNameErrors(undef_);
                            index_++;
                            continue;
                        }
                        if (r instanceof AnnotationBlock) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //idSuper_ len
                            undef_.buildError(_context.getAnalysisMessages().getBadInheritsType(),
                                    c,
                                    idSuper_);
                            _context.addError(undef_);
                            r.addNameErrors(undef_);
                            index_++;
                            continue;
                        }
                    }
                    if (r instanceof AnnotationBlock) {
                        foundNames_.addEntry(annotName_,e.getKey());
                        index_++;
                        continue;
                    }
                    StringList readyTypes_ = new StringList();
                    for (EntryCust<String, Boolean> f: builtTypes_.entryList()) {
                        if (f.getValue()) {
                            readyTypes_.add(f.getKey());
                        }
                    }
                    String foundType_ = ResolvingSuperTypes.resolveBaseInherits(_context,idSuper_, r, readyTypes_);
                    if (foundType_.isEmpty()) {
                        ready_ = false;
                        break;
                    }
                    if (r.getExplicitDirectSuperTypes().getValue(index_)) {
                        if (StringList.quickEq(enumParamClassName_, foundType_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //original type len
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    foundType_);
                            _context.addError(undef_);
                            r.addNameErrors(undef_);
                            index_++;
                            continue;
                        }
                        if (StringList.quickEq(enumClassName_, foundType_) && !StringList.quickEq(c, enumParamClassName_)) {
                            FoundErrorInterpret undef_;
                            undef_ = new FoundErrorInterpret();
                            undef_.setFileName(r.getFile().getFileName());
                            undef_.setIndexFile(offset_);
                            //original type len
                            undef_.buildError(_context.getAnalysisMessages().getReservedType(),
                                    c,
                                    foundType_);
                            _context.addError(undef_);
                            r.addNameErrors(undef_);
                            index_++;
                            continue;
                        }
                    }
                    foundNames_.addEntry(foundType_,e.getKey());
                    index_++;
                }
                if (!ready_) {
                    continue;
                }
                CustList<String> dup_ = foundNames_.getKeys();
                StringMap<Integer> counts_ = new StringMap<Integer>();
                for (String s: dup_) {
                    counts_.put(s,0);
                }
                for (String s: dup_) {
                    counts_.put(s, counts_.getVal(s)+1);
                }
                boolean hasDuplicates_ = false;
                for (EntryCust<String,Integer> e: counts_.entryList()) {
                    if (e.getValue() > 1) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(r.getFile().getFileName());
                        undef_.setIndexFile(0);
                        //original type len
                        undef_.buildError(_context.getAnalysisMessages().getDuplicateSuper(),
                                c,e.getKey(),Integer.toString(e.getValue()));
                        _context.addError(undef_);
                        r.addNameErrors(undef_);
                        hasDuplicates_ = true;
                    }
                }
                if (hasDuplicates_) {
                    continue;
                }
                int indexType_ = -1;
                int nbDirectSuperClass_ = 0;
                for (EntryCust<String,Integer> e: foundNames_.entryList()) {
                    indexType_++;
                    String k_ = e.getKey();
                    int ind_ = e.getValue();
                    if (r instanceof AnnotationBlock) {
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        exec_.getImportedDirectBaseSuperTypes().add(k_);
                        continue;
                    }
                    ExecRootBlock s_ =_context.getClasses().getClassBody(k_);
                    int offset_ = r.getRowColDirectSuperTypes().getKey(indexType_);
                    if (s_ instanceof ExecUniqueRootedBlock) {
                        nbDirectSuperClass_++;
                    }
                    if (r.isStaticType()) {
                        if (!s_.isStaticType()) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original k_ string len
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeInn(),
                                    c,
                                    k_);
                            _context.addError(enum_);
                            r.addNameErrors(enum_);
                        }
                    } else {
                        int subSise_ = exec_.getSelfAndParentTypes().size();
                        int supSise_ = s_.getSelfAndParentTypes().size();
                        if (supSise_ > subSise_) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original k_ string len
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeAsInn(),
                                    c,
                                    k_,
                                    Integer.toString(subSise_-1),
                                    Integer.toString(supSise_-1));
                            _context.addError(enum_);
                            r.addNameErrors(enum_);
                        }
                    }
                    if (exec_ instanceof ExecInterfaceBlock) {
                        if (!(s_ instanceof ExecInterfaceBlock)) {
                            FoundErrorInterpret enum_;
                            enum_ = new FoundErrorInterpret();
                            enum_.setFileName(r.getFile().getFileName());
                            enum_.setIndexFile(offset_);
                            //original type len
                            enum_.buildError(_context.getAnalysisMessages().getBadInheritsTypeInt(),
                                    c,k_);
                            _context.addError(enum_);
                            r.addNameErrors(enum_);
                        }
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        exec_.getImportedDirectBaseSuperTypes().add(k_);
                        continue;
                    }
                    if (r instanceof InnerTypeOrElement) {
                        r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                        exec_.getImportedDirectBaseSuperTypes().add(k_);
                        continue;
                    }
                    if (s_.isFinalType()) {
                        FoundErrorInterpret enum_;
                        enum_ = new FoundErrorInterpret();
                        enum_.setFileName(r.getFile().getFileName());
                        enum_.setIndexFile(offset_);
                        //original type len
                        enum_.buildError(_context.getAnalysisMessages().getFinalType(),
                                c,k_);
                        _context.addError(enum_);
                        r.addNameErrors(enum_);
                    }
                    r.getImportedDirectBaseSuperTypes().put(ind_,k_);
                    exec_.getImportedDirectBaseSuperTypes().add(k_);
                }
                if (nbDirectSuperClass_ > 1) {
                    FoundErrorInterpret enum_;
                    enum_ = new FoundErrorInterpret();
                    enum_.setFileName(r.getFile().getFileName());
                    enum_.setIndexFile(r.getIdRowCol());
                    //second super class
                    enum_.buildError(_context.getAnalysisMessages().getSuperClass(),
                            c,Integer.toString(nbDirectSuperClass_));
                    _context.addError(enum_);
                    r.addNameErrors(enum_);
                }
                r.getAllSuperTypes().addAllElts(foundNames_.getKeys());
                exec_.getAllSuperTypes().addAllElts(foundNames_.getKeys());
                for (String f: foundNames_.getKeys()) {
                    ExecRootBlock s_ = _context.getClasses().getClassBody(f);
                    if (s_ != null) {
                        exec_.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                        r.getAllSuperTypes().addAllElts(s_.getAllSuperTypes());
                    }
                }
                r.getAllSuperTypes().add(objectClassName_);
                exec_.getAllSuperTypes().add(objectClassName_);
                r.getAllSuperTypes().removeDuplicates();
                exec_.getAllSuperTypes().removeDuplicates();
                _context.getAnalyzing().getListTypesNames().add(r);
                builtTypes_.set(c, true);
                next_.add(r);
            }
            if (next_.isEmpty()) {
                for (RootBlock r: stClNames_) {
                    _context.getAnalyzing().getListTypesNames().add(r);
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFileName(r.getFile().getFileName());
                    undef_.setIndexFile(0);
                    //id len
                    undef_.buildError(_context.getAnalysisMessages().getUnknownSuperType(),
                            r.getFullName());
                    _context.addError(undef_);
                    r.addNameErrors(undef_);
                }
                break;
            }
            stClNames_.removeAllElements(next_);
        }
    }

    private static void checkTemplatesDef(ContextEl _context,
                                          String _objectClassName) {
        LgNames stds_ = _context.getStandards();
        for (RootBlock s: _context.getAnalyzing().getFoundTypes()) {
            String c = s.getFullName();
            Mapping mapping_ = new Mapping();
            StringMap<StringList> cts_ = new StringMap<StringList>();
            StringList variables_ = new StringList();
            boolean ok_ = true;
            for (TypeVar t: s.getParamTypesMapValues()) {
                cts_.put(t.getName(), t.getConstraints());
                variables_.add(t.getName());
            }
            if (!variables_.isEmpty() && s instanceof AnnotationBlock) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(s.getFile().getFileName());
                b_.setIndexFile(s.getIdRowCol());
                //first < after type id
                b_.buildError(_context.getAnalysisMessages().getAnnotationParam(),
                        c);
                _context.addError(b_);
                s.addNameErrors(b_);
                continue;
            }
            mapping_.setMapping(cts_);
            StringList cyclic_ = mapping_.getCyclic();
            if (!cyclic_.isEmpty()) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(s.getFile().getFileName());
                b_.setIndexFile(s.getIdRowCol());
                //first < after type id
                b_.buildError(_context.getAnalysisMessages().getCyclicMapping(),
                        c);
                _context.addError(b_);
                s.addNameErrors(b_);
                continue;
            }
            for (TypeVar t: s.getParamTypesMapValues()) {
                boolean existNative_ = false;
                boolean existCustom_ = false;
                StringList upper_ = Mapping.getAllUpperBounds(cts_, t.getName(),_objectClassName);
                StringList upperNotObj_ = new StringList();
                for (String b: upper_) {
                    if (b.startsWith("[")) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_context.getAnalysisMessages().getUnexpectedTypeBound(),
                                b);
                        _context.addError(un_);
                        s.addNameErrors(un_);
                    }
                    if (PrimitiveTypeUtil.isPrimitive(b,_context)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_context.getAnalysisMessages().getUnexpectedTypeBound(),
                                b);
                        _context.addError(un_);
                        s.addNameErrors(un_);
                    }
                    String baseParams_ = StringExpUtil.getIdFromAllTypes(b);
                    String base_ = StringExpUtil.getQuickComponentBaseType(baseParams_).getComponent();
                    upperNotObj_.add(b);
                    if (_context.getClasses().getClassBodiesMap().contains(base_)) {
                        existCustom_ = true;
                    } else {
                        existNative_ = true;
                    }
                }
                boolean okLoc_ = true;
                if (existNative_ && existCustom_) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    //type var len => at def
                    un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                            c);
                    _context.addError(un_);
                    s.addNameErrors(un_);
                    okLoc_ = false;
                    ok_ = false;
                }
                for (CustList<TypeInfo> g: OperationNode.typeLists(_context,upper_,MethodAccessKind.INSTANCE)) {
                    StringList all_ = new StringList();
                    for (TypeInfo i: g) {
                        all_.add(i.getType());
                    }
                    StringMap<StringList>baseParams_ = getBaseParams(all_);
                    for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                        if (!e.getValue().onlyOneElt()) {
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setFileName(s.getFile().getFileName());
                            duplicate_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            duplicate_.buildError(_context.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                                    StringList.join(e.getValue(),"&"));
                            _context.addError(duplicate_);
                            s.addNameErrors(duplicate_);
                        }
                    }

                }
                if (okLoc_) {
                    int nbAbs_ = 0;
                    int nbFinal_ = 0;
                    if (existNative_) {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = StringExpUtil.getIdFromAllTypes(b);
                            String base_ = StringExpUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            StandardType type_ = stds_.getStandards().getVal(base_);
                            if (!(type_ instanceof StandardClass)) {
                                continue;
                            }
                            StandardClass class_ = (StandardClass) type_;
                            if (class_.isFinalStdType()) {
                                nbFinal_++;
                            }
                            if (class_.isAbstractStdType()) {
                                nbAbs_++;
                            }
                        }
                    } else {
                        for (String b: upperNotObj_) {
                            String baseParamsUpp_ = StringExpUtil.getIdFromAllTypes(b);
                            String base_ = StringExpUtil.getQuickComponentBaseType(baseParamsUpp_).getComponent();
                            ExecRootBlock r_ = _context.getClasses().getClassBody(base_);
                            if (!(r_ instanceof ExecUniqueRootedBlock)) {
                                continue;
                            }
                            if (r_ instanceof ExecEnumBlock) {
                                nbFinal_++;
                                continue;
                            }
                            if (r_.isAbstractType()) {
                                nbAbs_++;
                            }
                            if (r_.isFinalType()) {
                                nbFinal_++;
                            }
                        }
                    }
                    if (nbAbs_ > 1 || nbFinal_ > 0) {
                        if (nbAbs_ > 1) {
                            //error
                            FoundErrorInterpret inh_;
                            inh_ = new FoundErrorInterpret();
                            inh_.setFileName(s.getFile().getFileName());
                            inh_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            inh_.buildError(_context.getAnalysisMessages().getAbsMapping(),
                                    Integer.toString(nbAbs_));
                            _context.addError(inh_);
                            s.addNameErrors(inh_);
                            ok_ = false;
                        }
                        if (nbFinal_ > 0) {
                            //error
                            FoundErrorInterpret inh_;
                            inh_ = new FoundErrorInterpret();
                            inh_.setFileName(s.getFile().getFileName());
                            inh_.setIndexFile(s.getIdRowCol());
                            //type var len => at def
                            inh_.buildError(_context.getAnalysisMessages().getFinalMapping(),
                                    Integer.toString(nbFinal_));
                            _context.addError(inh_);
                            s.addNameErrors(inh_);
                            ok_ = false;
                        }
                    }
                }
            }
            if (!ok_) {
                continue;
            }
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            for (TypeVar t: s.getParamTypesMapValues()) {
                map_.put(t.getName(), t.getConstraints());
            }
            for (TypeVar t: s.getParamTypesMapValues()) {
                for (AnaResultPartType b: t.getResults()) {
                    if (!AnaPartTypeUtil.processAnalyzeConstraints(b, map_, _context, true)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(s.getFile().getFileName());
                        un_.setIndexFile(s.getIdRowCol());
                        //type var len => at def
                        un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                                b.getResult());
                        _context.addError(un_);
                        s.addNameErrors(un_);
                    }
                }
            }
            for (AnaResultPartType t: s.getResults()) {
                if (!AnaPartTypeUtil.processAnalyzeConstraints(t, map_, _context, true)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(s.getFile().getFileName());
                    un_.setIndexFile(s.getIdRowCol());
                    // char : before super type
                    un_.buildError(_context.getAnalysisMessages().getBadParamerizedType(),
                            t.getResult());
                    _context.addError(un_);
                    s.addNameErrors(un_);
                }
            }
        }
    }

    private static void validateSingleParameterizedClasses(ContextEl _context) {
        for (RootBlock i: _context.getAnalyzing().getFoundTypes()) {
            ExecRootBlock val_ = _context.getAnalyzing().getMapTypes().getVal(i);
            StringList genericSuperTypes_ = i.getAllGenericSuperTypes(_context,val_);
            StringMap<StringList> baseParams_ = getBaseParams(genericSuperTypes_);
            for (EntryCust<String, StringList> e: baseParams_.entryList()) {
                if (!e.getValue().onlyOneElt()) {
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setFileName(i.getFile().getFileName());
                    duplicate_.setIndexFile(i.getIdRowCol());
                    //original id len
                    duplicate_.buildError(_context.getAnalysisMessages().getDuplicatedGenericSuperTypes(),
                            StringList.join(e.getValue(),"&"));
                    _context.addError(duplicate_);
                    i.addNameErrors(duplicate_);
                }
            }
            i.getAllGenericSuperTypes().addAllElts(genericSuperTypes_);
            StringList genericClasses_ = i.getAllGenericClasses(_context,val_);
            i.getAllGenericClasses().addAllElts(genericClasses_);
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: _context.getAnalyzing().getMapTypes().entryList()) {
            e.getValue().getAllGenericSuperTypes().addAllElts(e.getKey().getAllGenericSuperTypes());
            e.getValue().getAllGenericClasses().addAllElts(e.getKey().getAllGenericClasses());
        }
    }

    private static StringMap<StringList> getBaseParams(StringList _genericSuperTypes) {
        StringMap<StringList> baseParams_ = new StringMap<StringList>();
        for (String t: _genericSuperTypes) {
            String key_ = StringExpUtil.getIdFromAllTypes(t);
            if (baseParams_.contains(key_)) {
                baseParams_.getVal(key_).add(t);
            } else {
                baseParams_.put(key_, new StringList(t));
            }
        }
        return baseParams_;
    }

    public static void validateIds(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        IdMap<RootBlock, ExecRootBlock> mapTypes_ = page_.getMapTypes();
        for (RootBlock c: page_.getFoundTypes()) {
            ExecRootBlock type_ = mapTypes_.getVal(c);
            page_.setGlobalClass(type_.getGenericString());
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
            page_.setImportingTypes(c);
            c.validateIds(_context, type_,page_.getMapMembers().getVal(c));
            if (c.getNbOperators() > 0) {
                _context.getAnalyzing().getTypesWithInnerOperators().add(c.getFullName());
            }
        }
        for (EntryCust<RootBlock,ExecRootBlock> e: mapTypes_.entryList()) {
            e.getValue().validateIds(e.getKey(),page_.getMapMembers());
        }
        CustList<MethodId> idMethods_ = new CustList<MethodId>();
        page_.setGlobalClass("");
        for (EntryCust<OperatorBlock,ExecOperatorBlock> e: page_.getMapOperators().entryList()) {
            String name_ = e.getKey().getName();
            page_.setImporting(e.getKey());
            page_.setImportingAcces(new OperatorAccessor());
            page_.setImportingTypes(e.getKey());
            e.getKey().buildImportedTypes(_context);
            e.getValue().buildImportedTypes(e.getKey());
            if (!StringExpUtil.isOper(name_)) {
                FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                badMeth_.setFileName(_context.getCurrentFileName());
                badMeth_.setIndexFile(e.getKey().getNameOffset());
                //key word len
                badMeth_.buildError(_context.getAnalysisMessages().getBadOperatorName(),
                        name_);
                _context.addError(badMeth_);
                e.getKey().addNameErrors(badMeth_);
            }
            MethodId id_ = e.getKey().getId();
            for (MethodId m: idMethods_) {
                if (m.eq(id_)) {
                    FoundErrorInterpret duplicate_;
                    duplicate_ = new FoundErrorInterpret();
                    duplicate_.setIndexFile(e.getKey().getOffset().getOffsetTrim());
                    duplicate_.setFileName(_context.getCurrentFileName());
                    //key word len
                    duplicate_.buildError(_context.getAnalysisMessages().getDuplicateOperator(),
                            id_.getSignature(_context));
                    _context.addError(duplicate_);
                    e.getKey().addNameErrors(duplicate_);
                }
            }
            idMethods_.add(id_);
            StringList l_ = e.getKey().getParametersNames();
            StringList seen_ = new StringList();
            int i_ = 0;
            for (String v: l_) {
                e.getKey().addParamErrors();
                TokenErrorMessage res_ = ManageTokens.partParam(_context).checkStdToken(_context, v);
                if (res_.isError()) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setIndexFile(e.getKey().getOffset().getOffsetTrim());
                    //param name len
                    b_.setBuiltError(res_.getMessage());
                    _context.addError(b_);
                    e.getKey().addParamErrors(i_,b_);
                }
                if (StringList.contains(seen_, v)){
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFileName(_context.getCurrentFileName());
                    b_.setIndexFile(e.getKey().getOffset().getOffsetTrim());
                    //param name len
                    b_.buildError(_context.getAnalysisMessages().getDuplicatedParamName(),
                            v);
                    _context.addError(b_);
                    e.getKey().addParamErrors(i_,b_);
                } else {
                    seen_.add(v);
                }
                i_++;
            }
        }
    }

    public static void validateOverridingInherit(ContextEl _context) {
        IdMap<RootBlock, ExecRootBlock> mapTypes_ = _context.getAnalyzing().getMapTypes();
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            ExecRootBlock type_ = mapTypes_.getVal(c);
            c.setupBasicOverrides(_context,type_);
        }
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            ExecRootBlock type_ = mapTypes_.getVal(c);
            c.checkCompatibility(_context,type_);
            c.checkImplements(_context,type_);
        }
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            c.checkCompatibilityBounds(_context);
        }
    }

    public static CustList<ExecRootBlock> accessedClassMembers(ExecRootBlock _clOwner) {
        CustList<ExecRootBlock> inners_ = new CustList<ExecRootBlock>();
        for (ExecBlock b: ExecBlock.getDirectChildren(_clOwner)) {
            if (!(b instanceof ExecRootBlock)) {
                continue;
            }
            if (b instanceof ExecInnerElementBlock) {
                continue;
            }
            ExecRootBlock r_ = (ExecRootBlock) b;
            inners_.add(r_);
        }
        return inners_;
    }
    public static CustList<GeneCustStaticMethod> getMethodBlocks(AnaGeneType _element) {
        CustList<GeneCustStaticMethod> methods_ = new CustList<GeneCustStaticMethod>();
        if (_element instanceof RootBlock) {
            for (GeneCustStaticMethod m:getMethodAnaBlocks((RootBlock) _element)) {
                methods_.add(m);
            }
        }
        if (_element instanceof StandardType) {
            for (StandardMethod m : ((StandardType) _element).getMethods().values()) {
                methods_.add(m);
            }
        }
        return methods_;
    }


    public static CustList<GeneCustStaticMethod> getMethodAnaBlocks(RootBlock _element) {
        CustList<GeneCustStaticMethod> methods_ = new CustList<GeneCustStaticMethod>();
        for (Block b: getDirectChildren(_element)) {
            if (b instanceof OverridableBlock) {
                methods_.add((GeneCustStaticMethod) b);
            }
            if (b instanceof AnnotationMethodBlock) {
                methods_.add((GeneCustStaticMethod) b);
            }
        }
        return methods_;
    }
    public static CustList<ExecRootBlock> accessedInnerElements(ExecRootBlock _clOwner) {
        CustList<ExecRootBlock> inners_ = new CustList<ExecRootBlock>();
        for (ExecBlock b: ExecBlock.getDirectChildren(_clOwner)) {
            if (!(b instanceof ExecInnerElementBlock)) {
                continue;
            }
            ExecRootBlock r_ = (ExecRootBlock) b;
            inners_.add(r_);
        }
        return inners_;
    }

    //validate el and its possible returned type
    private static void validateEl(ContextEl _context) {
        initStaticFields(_context);
        AnalyzedPageEl page_ = _context.getAnalyzing();
        CustList<BracedBlock> brBl_ = new CustList<BracedBlock>();
        for (OperatorBlock c: page_.getMapOperators().getKeys()) {
            brBl_.add(c);
        }
        for (RootBlock c: page_.getFoundTypes()) {
            brBl_.add(c);
        }
        for (BracedBlock c: brBl_) {
            for (int i: c.getBadIndexes()) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(c.getFile().getFileName());
                b_.setIndexFile(Math.max(0,Math.min(c.getFile().getLength()-1,i)));
                //underline index char
                b_.buildError(_context.getAnalysisMessages().getBadIndexInParser());
                _context.addError(b_);
                GraphicErrorInterpret g_ = new GraphicErrorInterpret(b_);
                g_.setLength(1);
                c.getGlobalErrorsPars().add(g_);
            }
        }
        if (!_context.getOptions().isReadOnly()) {
            validateFinals(_context);
        } else {
            for (RootBlock c: page_.getFoundTypes()) {
                Members mem_ = page_.getMapMembers().getVal(c);
                ExecRootBlock type_ = page_.getMapTypes().getVal(c);
                page_.setImporting(c);
                page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
                page_.setImportingTypes(c);
                page_.getInitFields().clear();
                page_.getAssignedDeclaredFields().clear();
                page_.getAllDeclaredFields().clear();
                String fullName_ = c.getFullName();
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (!(b instanceof InfoBlock)) {
                        continue;
                    }
                    InfoBlock f_ = (InfoBlock) b;
                    page_.getAllDeclaredFields().addAllElts(f_.getFieldName());
                    if (!f_.isStaticField()) {
                        continue;
                    }
                    if (f_ instanceof FieldBlock) {
                        page_.getAssignedDeclaredFields().addAllElts(((FieldBlock)f_).getAssignedDeclaredFields());
                    }
                    if (f_ instanceof InnerTypeOrElement) {
                        page_.getAssignedDeclaredFields().add(((InnerTypeOrElement)f_).getUniqueFieldName());
                    }
                    int v_ = 0;
                    for (String f: f_.getFieldName()) {
                        StringList err_ = getErFields(f_, v_);
                        checkConstField(_context, err_,c, fullName_, f);
                        v_++;
                    }
                }
                for (Block b: bl_) {
                    if (b instanceof InnerTypeOrElement) {
                        page_.setGlobalClass(type_.getGenericString());
                        InnerTypeOrElement method_ = (InnerTypeOrElement) b;
                        page_.setCurrentBlock(b);
                        page_.setCurrentAnaBlock(b);
                        method_.buildExpressionLanguageReadOnly(_context,mem_.getAllElementFields().getVal(method_));
                    }
                    if (b instanceof FieldBlock) {
                        page_.setGlobalClass(type_.getGenericString());
                        FieldBlock method_ = (FieldBlock) b;
                        if (!method_.isStaticField()) {
                            continue;
                        }
                        page_.setCurrentBlock(b);
                        page_.setCurrentAnaBlock(b);
                        method_.buildExpressionLanguageReadOnly(_context,mem_.getAllExplicitFields().getVal(method_));
                    }
                    if (b instanceof StaticBlock) {
                        page_.setGlobalClass(type_.getGenericString());
                        StaticBlock method_ = (StaticBlock) b;
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllInits().getVal(method_));
                        page_.clearAllLocalVarsReadOnly();
                    }
                }

            }
            _context.setAssignedStaticFields(true);
            for (RootBlock c: page_.getFoundTypes()) {
                ExecRootBlock type_ = page_.getMapTypes().getVal(c);
                page_.setImporting(c);
                page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
                page_.setImportingTypes(c);
                Members mem_ = page_.getMapMembers().getVal(c);
                page_.getInitFields().clear();
                page_.getAssignedDeclaredFields().clear();
                page_.getAllDeclaredFields().clear();
                String fullName_ = c.getFullName();
                _context.getCoverage().putCalls(_context,fullName_);
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        InfoBlock method_ = (InfoBlock) b;
                        page_.getAllDeclaredFields().addAllElts(method_.getFieldName());
                        if (method_.isStaticField()) {
                            page_.getAssignedDeclaredFields().addAllElts(method_.getFieldName());
                            continue;
                        }
                    }
                    if (b instanceof FieldBlock) {
                        page_.getAssignedDeclaredFields().addAllElts(((FieldBlock)b).getAssignedDeclaredFields());
                    }
                }
                for (Block b: bl_) {
                    if (b instanceof InfoBlock) {
                        InfoBlock method_ = (InfoBlock) b;
                        if (method_.isStaticField()) {
                            continue;
                        }
                    }
                    if (b instanceof FieldBlock) {
                        page_.setGlobalClass(type_.getGenericString());
                        FieldBlock method_ = (FieldBlock) b;
                        page_.setCurrentBlock(b);
                        page_.setCurrentAnaBlock(b);
                        method_.buildExpressionLanguageReadOnly(_context,mem_.getAllExplicitFields().getVal(method_));
                    }
                    if (b instanceof InstanceBlock) {
                        page_.setGlobalClass(type_.getGenericString());
                        InstanceBlock method_ = (InstanceBlock) b;
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllInits().getVal(method_));
                    }
                }
                processInterfaceCtor(_context, c, fullName_, bl_);
                for (Block b: bl_) {
                    if (b instanceof ConstructorBlock) {
                        page_.getInitFieldsCtors().clear();
                        page_.getInitFieldsCtors().addAllElts(page_.getInitFields());
                        page_.setGlobalClass(type_.getGenericString());
                        ConstructorBlock method_ = (ConstructorBlock) b;
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllCtors().getVal(method_));
                        page_.getParameters().clear();
                        page_.clearAllLocalVarsReadOnly();
                    }
                }
            }
            _context.setAssignedFields(true);
            for (RootBlock c: page_.getFoundTypes()) {
                ExecRootBlock type_ = page_.getMapTypes().getVal(c);
                page_.setImporting(c);
                page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
                page_.setImportingTypes(c);
                Members mem_ = page_.getMapMembers().getVal(c);
                String fullName_ = c.getFullName();
                CustList<Block> bl_ = getDirectChildren(c);
                for (Block b: bl_) {
                    if (!(b instanceof OverridableBlock)) {
                        continue;
                    }
                    OverridableBlock method_ = (OverridableBlock) b;
                    if (isStdOrExplicit(method_)) {
                        page_.setGlobalClass(type_.getGenericString());
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllMethods().getVal(method_));
                        page_.getParameters().clear();
                        page_.clearAllLocalVarsReadOnly();
                    } else {
                        page_.setGlobalClass(type_.getGenericString());
                        _context.getCoverage().putCalls(_context,fullName_,method_);
                        StringList params_ = method_.getParametersNames();
                        StringList types_ = method_.getImportedParametersTypes();
                        prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                        processValueParam(_context, page_, type_, method_);
                        method_.buildFctInstructionsReadOnly(_context,mem_.getAllMethods().getVal(method_));
                        page_.getParameters().clear();
                        page_.clearAllLocalVarsReadOnly();
                    }
                }
            }
            page_.setGlobalClass("");
            _context.getCoverage().putCalls(_context,"");
            for (EntryCust<OperatorBlock,ExecOperatorBlock> e: page_.getMapOperators().entryList()) {
                page_.setImporting(e.getKey());
                page_.setImportingAcces(new OperatorAccessor());
                page_.setImportingTypes(e.getKey());
                _context.getCoverage().putCalls(_context,"",e.getKey());
                StringList params_ = e.getKey().getParametersNames();
                StringList types_ = e.getKey().getImportedParametersTypes();
                prepareParams(page_, e.getKey().getParametersNamesOffset(),e.getKey().getParamErrors(),params_, types_, e.getKey().isVarargs());
                e.getKey().buildFctInstructionsReadOnly(_context,e.getValue());
                page_.getParameters().clear();
                page_.clearAllLocalVarsReadOnly();
            }
        }
        _context.setAnnotAnalysis(true);
        for (RootBlock c: page_.getFoundTypes()) {
            ExecRootBlock type_ = page_.getMapTypes().getVal(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
            page_.setImportingTypes(c);
            Members mem_ = page_.getMapMembers().getVal(c);
            page_.setGlobalClass(type_.getGenericString());
            CustList<Block> annotated_ = new CustList<Block>();
            if (!(c instanceof InnerElementBlock)) {
                annotated_.add(c);
            }
            annotated_.addAllElts(getDirectChildren(c));
            _context.getCoverage().putBlockOperations(_context,page_.getMapTypes().getVal(c),c);
            for (Block b:annotated_) {
                page_.setCurrentBlock(b);
                page_.setCurrentAnaBlock(b);
                if (b instanceof AnnotationMethodBlock) {
                    _context.setAnnotAnalysisField(true);
                    ((AnnotationMethodBlock)b).buildExpressionLanguage(_context,mem_.getAllAnnotMethods().getVal(((AnnotationMethodBlock)b)));
                    _context.getCoverage().putBlockOperations(_context, mem_.getAllAnnotMethods().getVal((AnnotationMethodBlock) b),b);
                    _context.setAnnotAnalysisField(false);
                }
                if (b instanceof RootBlock) {
                    _context.setAnnotAnalysisField(false);
                    _context.getCoverage().putBlockOperationsField(_context,b);
                    ((RootBlock)b).buildAnnotations(_context,mem_.getAllAnnotables().getVal((AnnotableBlock) b));
                }
                if (b instanceof NamedFunctionBlock) {
                    _context.setAnnotAnalysisField(false);
                    _context.getCoverage().putBlockOperationsField(_context,b);
                    ((NamedFunctionBlock)b).buildAnnotations(_context,mem_.getAllNamed().getVal((NamedFunctionBlock) b));
                    ((NamedFunctionBlock)b).buildAnnotationsParameters(_context,mem_.getAllNamed().getVal((NamedFunctionBlock) b));
                }
                if (b instanceof InfoBlock) {
                    _context.setAnnotAnalysisField(false);
                    _context.getCoverage().putBlockOperationsField(_context,b);
                    _context.getCoverage().putBlockOperations(_context, (ExecBlock) mem_.getAllFields().getVal((InfoBlock) b),b);
                    ((InfoBlock)b).buildAnnotations(_context,mem_.getAllAnnotables().getVal((AnnotableBlock) b));
                }
            }
        }
        page_.setGlobalClass("");
        for (EntryCust<OperatorBlock,ExecOperatorBlock> e: page_.getMapOperators().entryList()) {
            page_.setImporting(e.getKey());
            page_.setImportingAcces(new OperatorAccessor());
            page_.setImportingTypes(e.getKey());
            page_.setCurrentBlock(e.getKey());
            page_.setCurrentAnaBlock(e.getKey());
            _context.setAnnotAnalysisField(false);
            _context.getCoverage().putBlockOperationsField(_context,e.getKey());
            e.getKey().buildAnnotations(_context,e.getValue());
            e.getKey().buildAnnotationsParameters(_context,e.getValue());
        }
        _context.setAnnotAnalysis(false);
        //init annotations here
        for (RootBlock c: page_.getFoundTypes()) {
            c.validateConstructors(_context);
        }
    }

    private static void validateFinals(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        AssignedVariablesBlock assVars_ = new AssignedVariablesBlock();
        for (RootBlock c: page_.getFoundTypes()) {
            Members mem_ = page_.getMapMembers().getVal(c);
            ExecRootBlock type_ = page_.getMapTypes().getVal(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
            page_.setImportingTypes(c);
            page_.getInitFields().clear();
            page_.getAssignedDeclaredFields().clear();
            page_.getAllDeclaredFields().clear();
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (Block b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock f_ = (InfoBlock) b;
                if (!f_.isStaticField()) {
                    continue;
                }
                int v_ = 0;
                for (String f: f_.getFieldName()) {
                    AssignmentBefore as_ = new AssignmentBefore();
                    StringList err_ = getErFields(f_, v_);
                    checkConstField(_context, err_,c, fullName_, f);
                    as_.setUnassignedBefore(true);
                    ass_.put(f, as_);
                    v_++;
                }
            }
            StringMap<AssignmentBefore> b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();
            assVars_.getFinalVariablesGlobal().getFields().clear();
            assVars_.getFinalVariablesGlobal().getFieldsRoot().clear();
            assVars_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            assVars_.getFinalVariablesGlobal().getFieldsBefore().clear();
            b_.putAllMap(ass_);
            StringMap<SimpleAssignment> assAfter_;
            assAfter_ = new StringMap<SimpleAssignment>();
            AssBlock pr_ = null;
            for (Block b: bl_) {
                if (b instanceof InnerTypeOrElement) {
                    page_.setGlobalClass(type_.getGenericString());
                    InnerTypeOrElement method_ = (InnerTypeOrElement) b;
                    page_.setCurrentBlock(b);
                    page_.setCurrentAnaBlock(b);
                    ExecInnerTypeOrElement val_ = mem_.getAllElementFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(_context, val_);
                    AssInfoBlock aInfo_ = new AssElementBlock(val_);
                    aInfo_.setAssignmentBeforeAsLeaf(_context,assVars_,pr_);
                    aInfo_.buildExpressionLanguage(_context,assVars_);
                    aInfo_.setAssignmentAfterAsLeaf(_context,assVars_,pr_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal((AssBlock) aInfo_).getFieldsRoot());
                    pr_ = (AssBlock) aInfo_;
                }
                if (b instanceof FieldBlock) {
                    page_.setGlobalClass(type_.getGenericString());
                    FieldBlock method_ = (FieldBlock) b;
                    if (!method_.isStaticField()) {
                        continue;
                    }
                    page_.setCurrentBlock(b);
                    page_.setCurrentAnaBlock(b);
                    ExecFieldBlock exp_ = mem_.getAllExplicitFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(_context, exp_);
                    AssInfoBlock aInfo_;
                    aInfo_ = new AssFieldBlock(exp_);
                    aInfo_.setAssignmentBeforeAsLeaf(_context,assVars_,pr_);
                    aInfo_.buildExpressionLanguage(_context,assVars_);
                    aInfo_.setAssignmentAfterAsLeaf(_context,assVars_,pr_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal((AssBlock) aInfo_).getFieldsRoot());
                    pr_ = (AssBlock) aInfo_;
                }
                if (b instanceof StaticBlock) {
                    page_.setGlobalClass(type_.getGenericString());
                    StaticBlock method_ = (StaticBlock) b;
                    AssMemberCallingsBlock res_ = AssBlockUtil.buildFctInstructions(method_,mem_.getAllInits().getVal(method_),_context,pr_,assVars_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal(res_).getFieldsRoot());
                    page_.clearAllLocalVars(assVars_);
                    pr_ = res_;
                }
            }
            for (EntryCust<String, SimpleAssignment> a: assAfter_.entryList()) {
                String key_ = a.getKey();
                ClassField id_ = new ClassField(fullName_, key_);
                if (!ContextUtil.isFinalField(_context,id_)) {
                    continue;
                }
                if (!StringList.contains(page_.getInitFields(),key_)) {
                    //error
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(c.getFile().getFileName());
                    un_.setIndexFile(c.getOffset().getOffsetTrim());
                    un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                            key_,fullName_);
                    _context.addError(un_);
                }
            }

        }
        _context.setAssignedStaticFields(true);
        for (RootBlock c: page_.getFoundTypes()) {
            Members mem_ = page_.getMapMembers().getVal(c);
            ExecRootBlock type_ = page_.getMapTypes().getVal(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
            page_.setImportingTypes(c);
            page_.getInitFields().clear();
            page_.getAssignedDeclaredFields().clear();
            page_.getAllDeclaredFields().clear();
            String fullName_ = c.getFullName();
            _context.getCoverage().putCalls(_context,fullName_);
            CustList<Block> bl_ = getDirectChildren(c);
            StringMap<AssignmentBefore> ass_;
            ass_ = new StringMap<AssignmentBefore>();
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        continue;
                    }
                }
                if (b instanceof FieldBlock) {
                    InfoBlock f_ = (InfoBlock) b;
                    for (String f: f_.getFieldName()) {
                        AssignmentBefore as_ = new AssignmentBefore();
                        as_.setUnassignedBefore(true);
                        ass_.put(f, as_);
                    }
                }
            }
            StringMap<AssignmentBefore> b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
            assVars_.getFinalVariablesGlobal().getFields().clear();
            assVars_.getFinalVariablesGlobal().getFieldsRoot().clear();
            assVars_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
            assVars_.getFinalVariablesGlobal().getFieldsBefore().clear();
            b_.clear();
            b_.putAllMap(ass_);
            boolean hasCtor_ = false;
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    hasCtor_ = true;
                    break;
                }
            }
            StringMap<SimpleAssignment> assAfter_;
            assAfter_ = new StringMap<SimpleAssignment>();
            AssBlock pr_ = null;
            for (Block b: bl_) {
                if (b instanceof InfoBlock) {
                    InfoBlock method_ = (InfoBlock) b;
                    if (method_.isStaticField()) {
                        continue;
                    }
                }
                if (b instanceof FieldBlock) {
                    page_.setGlobalClass(type_.getGenericString());
                    FieldBlock method_ = (FieldBlock) b;
                    page_.setCurrentBlock(b);
                    page_.setCurrentAnaBlock(b);
                    ExecFieldBlock exp_ = mem_.getAllExplicitFields().getVal(method_);
                    method_.buildExpressionLanguageReadOnly(_context, exp_);
                    AssFieldBlock aInfo_ = new AssFieldBlock(exp_);
                    aInfo_.setAssignmentBeforeAsLeaf(_context,assVars_,pr_);
                    aInfo_.buildExpressionLanguage(_context,assVars_);
                    aInfo_.setAssignmentAfterAsLeaf(_context,assVars_,pr_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal(aInfo_).getFieldsRoot());
                    pr_ = aInfo_;
                }
                if (b instanceof InstanceBlock) {
                    page_.setGlobalClass(type_.getGenericString());
                    InstanceBlock method_ = (InstanceBlock) b;
                    AssMemberCallingsBlock res_ = AssBlockUtil.buildFctInstructions(method_,mem_.getAllInits().getVal(method_), _context,pr_, assVars_);
                    assAfter_.putAllMap(assVars_.getFinalVariables().getVal(res_).getFieldsRoot());
                    page_.clearAllLocalVars(assVars_);
                    pr_ = res_;
                }
            }
            b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
            b_.clear();
            if (!hasCtor_) {
                for (EntryCust<String, SimpleAssignment> a : assAfter_.entryList()) {
                    String fieldName_ = a.getKey();
                    ClassField key_ = new ClassField(fullName_, fieldName_);
                    if (!ContextUtil.isFinalField(_context,key_)) {
                        continue;
                    }
                    if (StringList.contains(page_.getInitFields(), fieldName_)) {
                        continue;
                    }
                    //error
                    for (Block b : bl_) {
                        if (b instanceof InfoBlock) {
                            if (StringList.contains(((InfoBlock) b).getFieldName(), fieldName_)) {
                                FoundErrorInterpret un_ = new FoundErrorInterpret();
                                un_.setFileName(c.getFile().getFileName());
                                un_.setIndexFile(((InfoBlock) b).getFieldNameOffset());
                                un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                                        fieldName_,fullName_);
                                _context.addError(un_);
                            }
                        }
                    }
                }
            }
            b_.putAllMap(AssignmentsUtil.assignSimpleBefore(assAfter_));
            processInterfaceCtor(_context, c, fullName_, bl_);
            for (Block b: bl_) {
                if (b instanceof ConstructorBlock) {
                    page_.getInitFieldsCtors().clear();
                    page_.getInitFieldsCtors().addAllElts(page_.getInitFields());
                    page_.setGlobalClass(type_.getGenericString());
                    ConstructorBlock method_ = (ConstructorBlock) b;
                    _context.getCoverage().putCalls(_context,fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                    AssMemberCallingsBlock met_ = AssBlockUtil.buildFctInstructions(method_,mem_.getAllCtors().getVal(method_), _context,null, assVars_);
                    IdMap<AssBlock, AssignedVariables> id_ = assVars_.getFinalVariables();
                    AssignedVariables assTar_ = id_.getVal(met_);
                    for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                        String fieldName_ = f.getKey();
                        ClassField key_ = new ClassField(fullName_, fieldName_);
                        if (!ContextUtil.isFinalField(_context,key_)) {
                            continue;
                        }
                        if (StringList.contains(page_.getInitFieldsCtors(),fieldName_)) {
                            continue;
                        }
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(c.getFile().getFileName());
                        un_.setIndexFile(method_.getNameOffset());
                        un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                                fieldName_,fullName_);
                        _context.addError(un_);
                    }
                    page_.getParameters().clear();
                    page_.clearAllLocalVars(assVars_);
                }
            }
        }
        _context.setAssignedFields(true);
        assVars_.getFinalVariablesGlobal().getFields().clear();
        assVars_.getFinalVariablesGlobal().getFieldsRoot().clear();
        assVars_.getFinalVariablesGlobal().getFieldsRootBefore().clear();
        assVars_.getFinalVariablesGlobal().getFieldsBefore().clear();
        StringMap<AssignmentBefore> b_ = assVars_.getFinalVariablesGlobal().getFieldsRootBefore();
        b_.clear();

        for (RootBlock c: page_.getFoundTypes()) {
            ExecRootBlock type_ = page_.getMapTypes().getVal(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
            page_.setImportingTypes(c);
            Members mem_ = page_.getMapMembers().getVal(c);
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
                if (!(b instanceof OverridableBlock)) {
                    continue;
                }
                OverridableBlock method_ = (OverridableBlock) b;
                if (isStdOrExplicit(method_)) {
                    page_.setGlobalClass(type_.getGenericString());
                    _context.getCoverage().putCalls(_context,fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(page_,method_.getParametersNamesOffset(), method_.getParamErrors(),params_, types_, method_.isVarargs());
                    AssBlockUtil.buildFctInstructions(method_,mem_.getAllMethods().getVal(method_),_context,null,assVars_);
                    page_.getParameters().clear();
                    page_.clearAllLocalVars(assVars_);
                } else {
                    page_.setGlobalClass(type_.getGenericString());
                    _context.getCoverage().putCalls(_context,fullName_,method_);
                    StringList params_ = method_.getParametersNames();
                    StringList types_ = method_.getImportedParametersTypes();
                    prepareParams(page_, method_.getParametersNamesOffset(),method_.getParamErrors(),params_, types_, method_.isVarargs());
                    processValueParam(_context, page_, type_, method_);
                    AssBlockUtil.buildFctInstructions(method_,mem_.getAllMethods().getVal(method_),_context,null,assVars_);
                    page_.getParameters().clear();
                    page_.clearAllLocalVars(assVars_);
                }
            }
        }
        page_.setGlobalClass("");
        _context.getCoverage().putCalls(_context,"");
        for (EntryCust<OperatorBlock,ExecOperatorBlock> e: page_.getMapOperators().entryList()) {
            page_.setImporting(e.getKey());
            page_.setImportingAcces(new OperatorAccessor());
            page_.setImportingTypes(e.getKey());
            _context.getCoverage().putCalls(_context,"",e.getKey());
            StringList params_ = e.getKey().getParametersNames();
            StringList types_ = e.getKey().getImportedParametersTypes();
            prepareParams(page_,e.getKey().getParametersNamesOffset(),e.getKey().getParamErrors(), params_, types_, e.getKey().isVarargs());
            AssBlockUtil.buildFctInstructions(e.getKey(),e.getValue(),_context,null,assVars_);
            page_.getParameters().clear();
            page_.clearAllLocalVars(assVars_);
        }
    }

    private static StringList getErFields(InfoBlock f_, int v_) {
        StringList errs_ = new StringList();
        if (f_ instanceof FieldBlock) {
            errs_ = ((FieldBlock)f_).getNameErrorsFields().get(v_);
        }
        return errs_;
    }

    private static boolean isStdOrExplicit(OverridableBlock method_) {
        return method_.getKind() == MethodKind.STD_METHOD || method_.getKind() == MethodKind.TO_STRING || method_.getKind() == MethodKind.EXPLICIT_CAST || method_.getKind() == MethodKind.IMPLICIT_CAST;
    }

    private static void processValueParam(ContextEl _context, AnalyzedPageEl _page, ExecRootBlock _cl, OverridableBlock _method) {
        if (_method.getKind() == MethodKind.SET_INDEX) {
            String p_ = _context.getKeyWords().getKeyWordValue();
            CustList<ExecOverridableBlock> getIndexers_ = new CustList<ExecOverridableBlock>();
            for (ExecBlock d: ExecBlock.getDirectChildren(_cl)) {
                if (!(d instanceof ExecOverridableBlock)) {
                    continue;
                }
                ExecOverridableBlock i_ = (ExecOverridableBlock) d;
                if (i_.getKind() != MethodKind.GET_INDEX) {
                    continue;
                }
                if (!i_.getId().eqPartial(_method.getId())) {
                    continue;
                }
                getIndexers_.add(i_);
            }
            if (getIndexers_.size() == 1) {
                ExecOverridableBlock matching_ = getIndexers_.first();
                String c_ = matching_.getImportedReturnType();
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(c_);
                _page.getParameters().put(p_, lv_);
            }
        }
    }

    private static void processInterfaceCtor(ContextEl _context, RootBlock _cl, String _name, CustList<Block> _blocks) {
        boolean hasCtor_ = false;
        for (Block b: _blocks) {
            if (b instanceof ConstructorBlock) {
                hasCtor_ = true;
                break;
            }
        }
        StringList filteredCtor_ = new StringList();
        if (_cl instanceof UniqueRootedBlock) {
            UniqueRootedBlock un_ = (UniqueRootedBlock) _cl;
            StringList all_ = _cl.getAllSuperTypes();
            StringList allCopy_ = new StringList(all_);
            StringList.removeAllElements(allCopy_, _context.getStandards().getPredefinedInterfacesInitOrder());
            String superClass_ = un_.getImportedDirectGenericSuperClass();
            String superClassId_ = StringExpUtil.getIdFromAllTypes(superClass_);
            RootBlock superType_ = _context.getAnalyzing().getAnaClassBody(superClassId_);
            if (superType_ instanceof UniqueRootedBlock) {
                StringList.removeAllElements(allCopy_, superType_.getAllSuperTypes());
            }
            for (String i: allCopy_) {
                RootBlock int_ = _context.getAnalyzing().getAnaClassBody(i);
                if (!(int_ instanceof InterfaceBlock)) {
                    continue;
                }
                for (Block b: getDirectChildren(int_)) {
                    if (b instanceof NamedFunctionBlock) {
                        continue;
                    }
                    if (b instanceof GeneField) {
                        GeneField a_ = (GeneField) b;
                        if (!a_.isStaticField()) {
                            filteredCtor_.add(i);
                        }
                    }
                    if (b instanceof InstanceBlock) {
                        filteredCtor_.add(i);
                    }
                }
            }
        }
        _context.getNeedInterfaces().clear();
        _context.getNeedInterfaces().addAllElts(filteredCtor_);
        if (!hasCtor_ && !filteredCtor_.isEmpty()) {
            FoundErrorInterpret undef_;
            undef_ = new FoundErrorInterpret();
            undef_.setFileName(_cl.getFile().getFileName());
            undef_.setIndexFile(0);
            //original id len
            undef_.buildError(_context.getAnalysisMessages().getMustCallIntCtor(),
                    _cl.getFullName());
            _context.addError(undef_);
            _cl.addNameErrors(undef_);
        }
    }

    private static void prepareParams(AnalyzedPageEl _page, Ints _offs, CustList<StringList> _paramErrors,StringList _params, StringList _types, boolean _varargs) {
        int len_ = _params.size();
        if (!_varargs) {
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                if (!_paramErrors.get(i).isEmpty()) {
                    continue;
                }
                String p_ = _params.get(i);
                String c_ = _types.get(i);
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(c_);
                lv_.setRef(_offs.get(i));
                _page.getParameters().put(p_, lv_);
            }
        } else {
            for (int i = CustList.FIRST_INDEX; i < len_ - 1; i++) {
                if (!_paramErrors.get(i).isEmpty()) {
                    continue;
                }
                String p_ = _params.get(i);
                String c_ = _types.get(i);
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(c_);
                lv_.setRef(_offs.get(i));
                _page.getParameters().put(p_, lv_);
            }
            if (_paramErrors.last().isEmpty()) {
                String p_ = _params.last();
                String c_ = _types.last();
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(StringExpUtil.getPrettyArrayType(c_));
                lv_.setRef(_offs.last());
                _page.getParameters().put(p_, lv_);
            }
        }
    }

    private static void checkConstField(ContextEl _context, StringList _err, RootBlock _cl, String _clName, String _field) {
        if (_context.getClasses().getStaticFieldMap(_clName).getVal(_field) == null) {
            if (!_cl.isStaticType()) {
                //ERROR
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cl.getFile().getFileName());
                un_.setIndexFile(_cl.getOffset().getOffsetTrim());
                //field name len
                un_.buildError(_context.getAnalysisMessages().getUnassignedFinalField(),
                        _field,_clName);
                _context.addError(un_);
                _err.add(un_.getBuiltError());
            }
        }
    }

    public static void initStaticFields(ContextEl _context) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            String fullName_ = c.getFullName();
            CustList<Block> bl_ = getDirectChildren(c);
            StringMap<Struct> cl_ = new StringMap<Struct>();
            for (Block b: bl_) {
                if (!(b instanceof InfoBlock)) {
                    continue;
                }
                InfoBlock i_ = (InfoBlock)b;
                if (!i_.isStaticField()) {
                    continue;
                }
                for (String f: i_.getFieldName()) {
                    cl_.put(f, null);
                }
            }
            _context.getClasses().getStaticFields().put(fullName_, cl_);
        }
        IdMap<ClassField,ClassFieldBlock> cstFields_ = new IdMap<ClassField,ClassFieldBlock>();
        for (RootBlock c: _context.getAnalyzing().getFoundTypes()) {
            ExecRootBlock type_ = _context.getAnalyzing().getMapTypes().getVal(c);
            page_.setImporting(c);
            page_.setImportingAcces(new TypeAccessor(type_.getFullName()));
            page_.setImportingTypes(c);
            CustList<Block> bl_ = getDirectChildren(c);
            for (Block b: bl_) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (!f_.isStaticField()) {
                    continue;
                }
                if (!f_.isFinalField()) {
                    continue;
                }
                page_.setGlobalClass(type_.getGenericString());
                page_.setCurrentBlock(f_);
                page_.setCurrentAnaBlock(f_);
                CustList<OperationNode> list_ = f_.buildExpressionLanguageQuickly(_context);
                String cl_ = c.getFullName();
                for (String f: f_.getFieldName()) {
                    ClassField k_ = new ClassField(cl_, f);
                    cstFields_.addEntry(k_,new ClassFieldBlock(list_,f_));
                }
            }
        }
        while (true) {
            boolean calculatedValue_ = false;
            for (EntryCust<ClassField,ClassFieldBlock> e: cstFields_.entryList()) {
                ClassField k_ = e.getKey();
                Struct value_ = _context.getClasses().getStaticField(k_);
                if (value_ != null) {
                    continue;
                }
                ClassFieldBlock cf_ = e.getValue();
                FieldBlock f_ = cf_.getFieldName();
                CustList<OperationNode> ops_ = cf_.getClassName();
                ElUtil.tryCalculate(f_,ops_, _context, k_.getFieldName());
                value_ = _context.getClasses().getStaticField(k_);
                if (value_ != null) {
                    calculatedValue_ = true;
                    break;
                }
            }
            if (!calculatedValue_) {
                break;
            }
        }
    }

    private static StringList getPackages(ContextEl _context) {
        StringList pkgs_ = new StringList();
        for (RootBlock r: _context.getAnalyzing().getFoundTypes()) {
            String pkg_ = r.getPackageName();
            StringBuilder id_ = new StringBuilder();
            for (String p: StringList.splitChars(pkg_, '.')) {
                id_.append(p);
                pkgs_.add(id_.toString());
                id_.append('.');
            }
        }
        pkgs_.removeDuplicates();
        return pkgs_;
    }


    public static CustList<InfoBlock> getFieldBlocks(RootBlock _element){
        CustList<InfoBlock> methods_ = new CustList<InfoBlock>();
        for (Block b: getDirectChildren(_element)) {
            if (b instanceof InfoBlock) {
                methods_.add((InfoBlock) b);
            }
        }
        return methods_;
    }


    public static CustList<OverridableBlock> getMethodExecBlocks(RootBlock _element) {
        CustList<OverridableBlock> methods_ = new CustList<OverridableBlock>();
        for (Block b: getDirectChildren(_element)) {
            if (b instanceof OverridableBlock) {
                methods_.add((OverridableBlock) b);
            }
        }
        return methods_;
    }
}
