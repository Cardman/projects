package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ImportedField;
import code.expressionlanguage.ImportedMethod;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardType;
import code.util.*;

public final class ResolvingImportTypes {
    private ResolvingImportTypes() {

    }
    public static String resolveAccessibleIdTypeWithoutError(Analyzable _analyzable, int _loc, String _in) {
        String void_ = _analyzable.getStandards().getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            return "";
        }
        AccessingImportingBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        int rc_ = _analyzable.getCurrentLocationIndex()+_loc;
        String gl_ = _analyzable.getGlobalClass();
        AccessingImportingBlock a_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> offs_ = _analyzable.getContextEl().getCoverage().getCurrentParts();
        return PartTypeUtil.processAnalyzeLine(_in,new AlwaysReadyTypes(),false,gl_,_analyzable,a_,r_, rc_, offs_);
    }
    public static String resolveCorrectAccessibleType(Analyzable _analyzable, int _loc,String _in, String _fromType) {
        ContextEl ctx_ = _analyzable.getContextEl();
        int rc_ = _analyzable.getCurrentLocationIndex()+_loc;
        String void_ = _analyzable.getStandards().getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(ctx_.getAnalysisMessages().getVoidType(),
                    void_);
            _analyzable.addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        AccessingImportingBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        String idFromType_ = Templates.getIdFromAllTypes(_fromType);
        GeneType from_ = _analyzable.getContextEl().getClassBody(idFromType_);
        String ref_ = "";
        if (ElUtil.isFromCustFile(from_)) {
            ref_ = ((Block)from_).getFile().getRenderFileName();
        }
        _analyzable.getAnalyzing().getAvailableVariables().clear();
        if (from_ != null) {
            for (TypeVar t: from_.getParamTypesMapValues()) {
                _analyzable.getAnalyzing().getAvailableVariables().addEntry(t.getName(),t.getOffset());
                vars_.addEntry(t.getName(), t.getConstraints());
            }
        }
        CustList<PartOffset> partOffsets_ = _analyzable.getContextEl().getCoverage().getCurrentParts();
        partOffsets_.clear();
        String resType_ = PartTypeUtil.processAnalyzeAccessibleId(_in, _analyzable, r_, ref_,rc_,partOffsets_);
        if (resType_.trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(ctx_.getAnalysisMessages().getUnknownType(),
                    _in);
            _analyzable.addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        if (!Templates.isCorrectTemplateAll(resType_, vars_, _analyzable)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(ctx_.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _analyzable.addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        return resType_;
    }
    public static String resolveCorrectTypeWithoutErrors(Analyzable _analyzable, int _loc,String _in, boolean _exact) {
        String void_ = _analyzable.getStandards().getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            return "";
        }
        AccessingImportingBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _analyzable.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        _analyzable.buildCurrentConstraintsFull();
        AccessingImportingBlock a_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> partOffsets_ = _analyzable.getContextEl().getCoverage().getCurrentParts();
        String gl_ = _analyzable.getGlobalClass();
        int rc_ = _analyzable.getCurrentLocationIndex() + _loc;
        _analyzable.getAnalyzing().getCurrentBadIndexes().clear();
        String resType_;
        if (_exact) {
            resType_ = PartTypeUtil.processAnalyze(_in, false,gl_, _analyzable, a_,r_, rc_,partOffsets_);
        } else {
            resType_ = PartTypeUtil.processAnalyzeLine(_in, new AlwaysReadyTypes(),false,gl_, _analyzable, a_,r_, rc_,partOffsets_);
        }
        if (resType_.trim().isEmpty()) {
            partOffsets_.clear();
            return "";
        }
        if (!_analyzable.getAnalyzing().getCurrentBadIndexes().isEmpty()) {
            partOffsets_.clear();
            return "";
        }
        if (!Templates.isCorrectTemplateAll(resType_, varsCt_, _analyzable, _exact)) {
            partOffsets_.clear();
            return "";
        }
        return resType_;
    }
    public static String resolveCorrectType(Analyzable _analyzable,int _loc,String _in, boolean _exact) {
        ContextEl ctx_ = _analyzable.getContextEl();
        int rc_ = _analyzable.getCurrentLocationIndex() + _loc;
        String void_ = _analyzable.getStandards().getAliasVoid();
        String tr_ = _in.trim();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(ctx_.getAnalysisMessages().getVoidType(),
                    void_);
            _analyzable.addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        AccessingImportingBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _analyzable.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        _analyzable.buildCurrentConstraintsFull();
        AccessingImportingBlock a_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> partOffsets_ = _analyzable.getContextEl().getCoverage().getCurrentParts();
        String gl_ = _analyzable.getGlobalClass();
        String resType_;
        _analyzable.getAnalyzing().getCurrentBadIndexes().clear();
        if (_exact) {
            resType_ = PartTypeUtil.processAnalyze(tr_, false,gl_, _analyzable, a_,r_, rc_,partOffsets_);
        } else {
            resType_ = PartTypeUtil.processAnalyzeLine(tr_,new AlwaysReadyTypes(),false, gl_, _analyzable,a_,r_, rc_,partOffsets_);
        }
        for (int i: _analyzable.getAnalyzing().getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getCurrentFileName());
            un_.setIndexFile(rc_+i);
            //part len
            un_.buildError(ctx_.getAnalysisMessages().getInaccessibleType(),
                    resType_,gl_);
            _analyzable.addError(un_);
        }
        if (resType_.trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(ctx_.getAnalysisMessages().getUnknownType(),
                    _in);
            _analyzable.addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        if (!Templates.isCorrectTemplateAll(resType_, varsCt_, _analyzable, _exact)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(ctx_.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _analyzable.addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        return resType_;
    }
    public static String resolveAccessibleIdType(Analyzable _analyzable,int _loc,String _in) {
        ContextEl ctx_ = _analyzable.getContextEl();
        int rc_ = _analyzable.getCurrentLocationIndex();
        String void_ = _analyzable.getStandards().getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(ctx_.getAnalysisMessages().getVoidType(),
                    void_);
            _analyzable.addError(un_);
            return "";
        }
        AccessingImportingBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringList inners_ = getParts(_analyzable,_in);
        String firstFull_ = inners_.first();
        int firstOff_ = StringList.getFirstPrintableCharIndex(firstFull_);
        String base_ = firstFull_.trim();
        String res_ = StringExpUtil.removeDottedSpaces(base_);
        if (_analyzable.getStandards().getStandards().contains(res_)) {
            return res_;
        }
        CustList<PartOffset> partOffsets_ = _analyzable.getContextEl().getCoverage().getCurrentParts();
        partOffsets_.clear();
        RootBlock b_ = _analyzable.getClasses().getClassBody(res_);
        if (b_ == null) {
            String id_ = lookupImportType(_analyzable,base_,r_, new AlwaysReadyTypes());
            if (id_.isEmpty()) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_analyzable.getCurrentFileName());
                undef_.setIndexFile(rc_);
                //_in len
                undef_.buildError(ctx_.getAnalysisMessages().getUnknownType(),
                        _in);
                _analyzable.addError(undef_);
                return "";
            }
            _analyzable.getContextEl().appendParts(firstOff_+_loc,firstOff_+_loc + base_.length(),id_,partOffsets_);
            res_ = id_;
        } else {
            _analyzable.getContextEl().appendParts(firstOff_+_loc,firstOff_+_loc + res_.length(),res_,partOffsets_);
        }
        int offset_ = _loc;
        offset_ += inners_.first().length();
        offset_ ++;
        for (String i: inners_.mid(1)) {
            String resId_ = StringList.concat(res_,"..",i.trim());
            RootBlock inner_ = _analyzable.getClasses().getClassBody(resId_);
            if (inner_ == null) {
                //ERROR
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_analyzable.getCurrentFileName());
                undef_.setIndexFile(rc_);
                //_in len
                undef_.buildError(ctx_.getAnalysisMessages().getUnknownType(),
                        _in);
                _analyzable.addError(undef_);
                return "";
            }
            _analyzable.getContextEl().appendParts(offset_,offset_ + i.trim().length(),resId_,partOffsets_);
            res_ = resId_;
            offset_ += i.length() + 1;
        }
        return res_;
    }
    public static String resolveCorrectType(Analyzable _an,String _in) {
        return _an.getStandards().checkCorrectType(_an,0,_in,true);
    }
    public static String resolveCorrectType(Analyzable _an,int _loc, String _in) {
        return _an.getStandards().checkCorrectType(_an,_loc,_in,true);
    }

    public static String lookupImportType(Analyzable _an,String _type, AccessingImportingBlock _rooted, ReadyTypes _ready) {
        String prefixedType_;
        prefixedType_ = getRealSinglePrefixedMemberType(_an,_type, _rooted,_ready);
        return prefixedType_;
    }
    private static String getRealSinglePrefixedMemberType(Analyzable _an,String _type, AccessingImportingBlock _rooted, ReadyTypes _ready) {
        String look_ = _type.trim();
        StringList types_ = new StringList();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(_rooted, imports_);
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, look_)) {
                    continue;
                }
                if (stopLookup(_an,types_,tr_,look_,_ready)) {
                    return "";
                }
            }
        }
        if (types_.onlyOneElt()) {
            return types_.first();
        }
        if (!types_.isEmpty()) {
            return "";
        }
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            String type_ = StringExpUtil.removeDottedSpaces(StringList.concat(r_.getPackageName(),".",_type));
            if (_an.getClasses().isCustomType(type_)) {
                return type_;
            }
        }
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                if (stopLookup(_an,types_,tr_,look_,_ready)) {
                    return "";
                }
            }
        }
        if (types_.onlyOneElt()) {
            return types_.first();
        }
        if (!types_.isEmpty()) {
            return "";
        }
        String defPkg_ = _an.getStandards().getDefaultPkg();
        String type_ = StringExpUtil.removeDottedSpaces(StringList.concat(defPkg_,".",_type));
        if (_an.getContextEl().getClassBody(type_) != null) {
            return type_;
        }
        return "";
    }

    private static boolean stopLookup(Analyzable _an,StringList _types, String _import, String _look, ReadyTypes _ready) {
        String beginImp_ = StringExpUtil.removeDottedSpaces(_import.substring(0, _import.lastIndexOf('.')+1));
        String keyWordStatic_ = _an.getContextEl().getKeyWords().getKeyWordStatic();
        boolean stQualifier_ = false;
        if (StringExpUtil.startsWithKeyWord(beginImp_, keyWordStatic_)) {
            beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
            stQualifier_ = true;
        }
        String typeInner_ = StringList.concat(beginImp_, _look);
        String foundCandidate_ = StringList.join(Templates.getAllInnerTypes(typeInner_, _an), "..");
        StringList allInnerTypes_ = Templates.getAllInnerTypes(foundCandidate_);
        String owner_ = allInnerTypes_.first();
        GeneType cl_ = _an.getContextEl().getClassBody(owner_);
        String res_ = owner_;
        boolean addImport_ = true;
        if (cl_ != null) {
            for (String i: allInnerTypes_.mid(1)) {
                String i_ = i.trim();
                if (!_ready.isReady(res_)) {
                    return true;
                }
                StringList builtInners_ = TypeUtil.getInners(res_, i_, stQualifier_, _an);
                if (builtInners_.onlyOneElt()) {
                    res_ = builtInners_.first();
                    continue;
                }
                if (!builtInners_.isEmpty()) {
                    return true;
                }
                addImport_ = false;
                break;
            }
        } else {
            addImport_ = false;
        }
        if (addImport_) {
            _types.add(res_);
        }
        return false;
    }

    public static ObjectMap<ClassMethodId,ImportedMethod> lookupImportStaticMethods(Analyzable _analyzable,String _glClass, String _method, Block _rooted) {
        ObjectMap<ClassMethodId,ImportedMethod> methods_ = new ObjectMap<ClassMethodId,ImportedMethod>();
        AccessingImportingBlock type_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(type_, imports_);
        String keyWordStatic_ = _analyzable.getKeyWords().getKeyWordStatic();
        int import_ = 1;
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(_analyzable,typeLoc_);
                GeneType root_ = _analyzable.getContextEl().getClassBody(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, _method.trim())) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_analyzable,_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(_analyzable,typeLoc_);
                GeneType root_ = _analyzable.getContextEl().getClassBody(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_analyzable,_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        return methods_;
    }

    private static void fetchImportStaticMethods(Analyzable _analyzable,String _glClass, String _method, ObjectMap<ClassMethodId, ImportedMethod> _methods, int _import, String _typeLoc, StringList _typesLoc) {
        for (String s: _typesLoc) {
            GeneType super_ = _analyzable.getContextEl().getClassBody(s);
            for (GeneMethod e: ContextEl.getMethodBlocks(super_)) {
                if (!e.isStaticMethod()) {
                    continue;
                }
                if (!StringList.quickEq(_method.trim(), e.getId().getName())) {
                    continue;
                }
                if (e instanceof AccessibleBlock) {
                    if (!Classes.canAccess(_typeLoc, (AccessibleBlock)e, _analyzable)) {
                        continue;
                    }
                    if (!Classes.canAccess(_glClass, (AccessibleBlock)e, _analyzable)) {
                        continue;
                    }
                }
                ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                _methods.add(clMet_, new ImportedMethod(_import,e.getImportedReturnType()));
            }
        }
    }

    public static ObjectMap<ClassField,ImportedField> lookupImportStaticFields(Analyzable _analyzable,String _glClass, String _method, Block _rooted) {
        ObjectMap<ClassField,ImportedField> methods_ = new ObjectMap<ClassField,ImportedField>();
        int import_ = 1;
        AccessingImportingBlock type_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(type_, imports_);
        String keyWordStatic_ = _analyzable.getKeyWords().getKeyWordStatic();
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(_analyzable,typeLoc_);
                GeneType root_ = _analyzable.getContextEl().getClassBody(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, _method.trim())) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFieldsTmp(_analyzable,_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(_analyzable,typeLoc_);
                GeneType root_ = _analyzable.getContextEl().getClassBody(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFieldsTmp(_analyzable,_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        return methods_;
    }

    public static String resolveCandidate(Analyzable _analyzable,String _c) {
        StringList allInnerTypes_ = getParts(_analyzable,_c);
        String owner_ = allInnerTypes_.first();
        GeneType cl_ = _analyzable.getContextEl().getClassBody(owner_);
        String res_ = owner_;
        if (cl_ != null) {
            for (String i: allInnerTypes_.mid(1)) {
                String i_ = i.trim();
                StringList builtInners_ = TypeUtil.getOwners(res_, i_, false, _analyzable);
                if (builtInners_.onlyOneElt()) {
                    res_ = StringList.concat(builtInners_.first(),"..",i_);
                    continue;
                }
                break;
            }
        }
        return res_;
    }

    public static StringList getParts(Analyzable _analyzable,String _c) {
        StringList allInnerTypes_;
        allInnerTypes_ = Templates.getAllInnerTypes(_c, _analyzable);
        return allInnerTypes_;
    }

    private static void fetchImportStaticFieldsTmp(Analyzable _analyzable,String _glClass, String _method, ObjectMap<ClassField, ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc) {
        fetchImportStaticFields(_analyzable,_glClass,_method,_methods,_import,_typeLoc,_typesLoc);
    }

    public static void fetchImportStaticFields(Analyzable _analyzable,String _glClass, String _method, ObjectMap<ClassField, ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc) {
        for (String s: _typesLoc) {
            GeneType super_ = _analyzable.getContextEl().getClassBody(s);
            if (super_ instanceof StandardType) {
                for (StandardField m: ((StandardType)super_).getFields().values()) {
                    if (notMatch(_method, m)) {
                        continue;
                    }
                    ClassField field_ = new ClassField(s, _method);
                    _methods.add(field_, new ImportedField(_import,m));
                }
            } else {
                for (InfoBlock e: ContextEl.getFieldBlocks((RootBlock) super_)) {
                    if (notMatch(_method, e)) {
                        continue;
                    }
                    if (e instanceof AccessibleBlock) {
                        if (!Classes.canAccess(_typeLoc, (AccessibleBlock)e, _analyzable)) {
                            continue;
                        }
                        if (!Classes.canAccess(_glClass, (AccessibleBlock)e, _analyzable)) {
                            continue;
                        }
                    }
                    ClassField field_ = new ClassField(s, _method);
                    _methods.add(field_, new ImportedField(_import,e));
                }
            }
        }
    }
    private static boolean notMatch(String _method, GeneField _field) {
        if (!_field.isStaticField()) {
            return true;
        }
        return !StringList.contains(_field.getFieldName(), _method.trim());
    }
    private static void fetchImports(AccessedBlock _rooted, CustList<StringList> _imports) {
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            _imports.add(r_.getImports());
            for (RootBlock r: r_.getAllParentTypes()) {
                addImports(r,_imports);
            }
        } else {
            addImports(_rooted, _imports);
        }
        if (_rooted != null) {
            _imports.add(_rooted.getFileImports());
        }
    }

    private static void addImports(AccessedBlock _rooted, CustList<StringList> _imports) {
        if (_rooted != null) {
            _imports.add(_rooted.getImports());
        }
    }
}
