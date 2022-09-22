package code.expressionlanguage.tsts;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.inherits.OverridesTypeUtil;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.types.OverridingMethodDto;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.common.StringDataLetterUtil;
import code.expressionlanguage.common.StringDataUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.coverage.BlockCoverageResult;
import code.expressionlanguage.exec.coverage.FunctionCoverageResult;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.Struct;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class TstsCharacters {
    private int max = -1;
    private int maxType = -1;
    private int min = 100;
    private int minType = 100;
    private int maxLetter = -1;
    private int minLetter = -1;
    private int maxLetterDigit = -1;
    private int minLetterDigit = -1;
    public TstsCharacters() {
        for (int i = 0; i < 256*256;i++) {
            int dir_ = StringDataUtil.getDirectionality((char) i);
            int type_ = StringDataUtil.getType((char) i);
            max = Math.max(dir_,max);
            min = Math.min(dir_,min);
            maxType = Math.max(type_,maxType);
            minType = Math.min(type_,minType);
            if (StringDataLetterUtil.isLetter((char) i)) {
                if (minLetter == -1) {
                    minLetter = i;
                }
                maxLetter = i;
            }
            if (StringDataUtil.isLetterOrDigit((char) i)) {
                if (minLetterDigit == -1) {
                    minLetterDigit = i;
                }
                maxLetterDigit = i;
            }
        }
    }
    public static Ints values(StrTypes _str) {
        Ints s_ = new Ints();
        for (IndexStrPart e: _str.getValues()) {
            s_.add(e.getIndex());
        }
        return s_;
    }

    public static void setAvailableVar(AnalyzedPageEl _cont, StringMap<StringList> _t) {
        _cont.getCurrentBadIndexes().clear();
        _cont.getAvailableVariables().clear();
        for (EntryCust<String,StringList> s: _t.entryList()) {
            _cont.getAvailableVariables().addEntry(s.getKey(),0);
        }
    }
    public static int countStaticFields(StringMap<StringMap<Struct>> _staticFields) {
        int sum_ = 0;
        for (EntryCust<String, StringMap<Struct>> c: _staticFields.entryList()) {
            for (EntryCust<String, Struct> e: c.getValue().entryList()) {
                if (e.getValue() == null) {
                    continue;
                }
                sum_++;
            }
        }
        return sum_;
    }

    public static CustList<CustList<AbstractCoverageResult>> results(CustList<BlockCoverageResult> _blocks) {
        CustList<CustList<AbstractCoverageResult>> m_ = new CustList<CustList<AbstractCoverageResult>>();
        for (BlockCoverageResult e: _blocks) {
            m_.add(e.getCovers());
        }
        return m_;
    }

    public static CustList<BoolVal> listCalls(CustList<FunctionCoverageResult> _fcts) {
        CustList<BoolVal> v_ = new CustList<BoolVal>();
        for (FunctionCoverageResult e: _fcts) {
            v_.add(ComparatorBoolean.of(e.isCalled()));
        }
        return v_;
    }

    public static StringMap<ClassMethodId> getConcreteMethodsToCall(AnalyzedPageEl _cont, MethodId _id, RootBlock _r) {
        StringMap<GeneStringOverridable> conc_ = OverridesTypeUtil.getConcreteMethodsToCall(_r, _id, _cont);
        StringMap<ClassMethodId> tr_ = new StringMap<ClassMethodId>();
        for (EntryCust<String,GeneStringOverridable> e: conc_.entryList()) {
            GeneStringOverridable value_ = e.getValue();
            tr_.addEntry(e.getKey(),new ClassMethodId(StringExpUtil.getIdFromAllTypes(value_.getGeneString()),value_.getBlock().getId()));
        }
        return tr_;
    }

    public static StringList listOfTypes(CustList<OverridingMethodDto> _map, MethodId _id) {
        StringList l_ = new StringList();
        for (OverridingMethodDto o: _map) {
            if (o.getFormattedMethodId().eq(new FormattedMethodId(_id))) {
                for (GeneStringOverridable i : o.getMethodIds()) {
                    if (StringUtil.contains(l_,i.getGeneString())) {
                        continue;
                    }
                    l_.add(i.getGeneString());
                }
            }
        }
        return l_;
    }
    public static CustList<RootBlock> customTypes(AnalyzedPageEl _cont) {
        CustList<RootBlock> count_ = new CustList<RootBlock>();
        for (RootBlock r: _cont.getFoundTypes()) {
            if (!r.getFile().isPredefined()) {
                count_.add(r);
            }
        }
        return count_;
    }

    public static CustList<FileBlock> customFiles(AnalyzedPageEl _cont) {
        CustList<FileBlock> count_ = new CustList<FileBlock>();
        for (EntryCust<String, FileBlock> r: _cont.getFilesBodies().entryList()) {
            if (!r.getValue().isPredefined()) {
                count_.add(r.getValue());
            }
        }
        return count_;
    }

    public static StringList directSuperTypes(RootBlock _r) {
        StringList l_ = new StringList();
        for (String p: _r.getDirectSuperTypes()) {
            l_.add(StringExpUtil.removeDottedSpaces(p));
        }
        return l_;
    }

    public static CustList<StringList> annotationsParams(CustList<ResultParsedAnnots> _list) {
        CustList<StringList> ls_ = new CustList<StringList>();
        for (ResultParsedAnnots r: _list) {
            ls_.add(getAnnotations(r));
        }
        return ls_;
    }

    public static StringList getAnnotations(ResultParsedAnnots _p) {
        return annotations(_p.getAnnotations());
    }

    public static StringList annotations(CustList<ResultParsedAnnot> _list) {
        StringList ls_ = new StringList();
        for (ResultParsedAnnot i: _list) {
            ls_.add(i.getAnnotation());
        }
        return ls_;
    }

    public static CustList<Ints> annotationsIndexesParams(CustList<ResultParsedAnnots> _list) {
        CustList<Ints> ls_ = new CustList<Ints>();
        for (ResultParsedAnnots r: _list) {
            ls_.add(annotationsIndexes(r));
        }
        return ls_;
    }

    public static Ints annotationsIndexes(ResultParsedAnnots _p) {
        return annotationsIndexes(_p.getAnnotations());
    }

    public static Ints annotationsIndexes(CustList<ResultParsedAnnot> _p) {
        Ints ls_ = new Ints();
        for (ResultParsedAnnot i: _p) {
            ls_.add(i.getIndex());
        }
        return ls_;
    }

    public int getMax() {
        return max;
    }

    public int getMaxLetter() {
        return maxLetter;
    }

    public int getMaxLetterDigit() {
        return maxLetterDigit;
    }

    public int getMaxType() {
        return maxType;
    }

    public int getMin() {
        return min;
    }

    public int getMinLetter() {
        return minLetter;
    }

    public int getMinLetterDigit() {
        return minLetterDigit;
    }

    public int getMinType() {
        return minType;
    }
}
