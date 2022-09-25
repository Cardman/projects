package code.expressionlanguage.tsts;

import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.common.StringDataLetterUtil;
import code.expressionlanguage.common.StringDataUtil;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.NumberUtil;

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
            max = NumberUtil.max(dir_,max);
            min = NumberUtil.min(dir_,min);
            maxType = NumberUtil.max(type_,maxType);
            minType = NumberUtil.min(type_,minType);
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
