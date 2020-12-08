package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.PrimitiveType;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaClassArgumentMatching {

    private static final String ARR_CLASS = "[";

    private final StringList className = new StringList();

    private byte unwrapObjectNb = (byte)-1;

    private boolean checkOnlyNullPe;
    private boolean convertToString;
    private CustList<ClassMethodId> implicits = new CustList<ClassMethodId>();
    private CustList<ClassMethodId> implicitsTest = new CustList<ClassMethodId>();
    private MemberId memberId = new MemberId();
    private MemberId memberIdTest = new MemberId();

    public AnaClassArgumentMatching(String _className) {
        className.add(_className);
    }

    public AnaClassArgumentMatching(String _className, byte _conv) {
        className.add(_className);
        unwrapObjectNb = _conv;
    }

    public AnaClassArgumentMatching(String _className, StringMap<PrimitiveType> _primitiveTypes) {
        className.add(_className);
        setUnwrapObject(_className, _primitiveTypes);
    }

    public AnaClassArgumentMatching(StringList _className) {
        className.addAllElts(_className);
    }

    public static AnaClassArgumentMatching copy(AnaClassArgumentMatching _copy, StringMap<PrimitiveType> _primitiveTypes) {
        AnaClassArgumentMatching cl_ = new AnaClassArgumentMatching(_copy.className);
        cl_.setUnwrapObject(_copy, _primitiveTypes);
        return cl_;
    }

    public boolean isNumericInt(AnalyzedPageEl _page) {
        return isNumericInt(getSingleNameOrEmpty(),_page);
    }
    public static boolean isNumericInt(String _p, AnalyzedPageEl _page) {
        String intPr_ = _page.getAliasPrimInteger();
        String shortPr_ = _page.getAliasPrimShort();
        String charPr_ = _page.getAliasPrimChar();
        String bytePr_ = _page.getAliasPrimByte();
        String prim_ = AnaTypeUtil.toPrimitive(_p, _page.getPrimitiveTypes());
        if (StringUtil.quickEq(prim_,intPr_)) {
            return true;
        }
        if (StringUtil.quickEq(prim_,shortPr_)) {
            return true;
        }
        if (StringUtil.quickEq(prim_,charPr_)) {
            return true;
        }
        return StringUtil.quickEq(prim_,bytePr_);
    }
    public boolean isArray() {
        for (String b: className) {
            if (b.startsWith(ARR_CLASS)) {
                return true;
            }
        }
        return false;
    }
    public boolean matchVoid(AnalyzedPageEl _page) {
        StringList l_ = new StringList(_page.getAliasVoid());
        return StringUtil.equalsSet(className, l_);
    }

    public boolean matchClass(String _class) {
        StringList l_ = new StringList(_class);
        return StringUtil.equalsSet(className, l_);
    }

    public boolean matchClass(AnaClassArgumentMatching _class) {
        return StringUtil.equalsSet(className, _class.className);
    }

    public boolean isVariable() {
        return StringUtil.contains(className, "");
    }

    public boolean isPrimitive(AnalyzedPageEl _page) {
        for (String b: className) {
            if (AnaTypeUtil.isPrimitive(b, _page)) {
                return true;
            }
        }
        return false;
    }

    public byte getPrimitiveCast(AnalyzedPageEl _page) {
        return getPrimitiveCast(className, _page);
    }

    public static byte getPrimitiveCast(StringList _className, AnalyzedPageEl _page) {
        return ClassArgumentMatching.getPrimitiveCast(_className, _page.getPrimTypes());
    }

    public boolean isBoolType(AnalyzedPageEl _context) {
        String aliasBoolean_ = _context.getAliasBoolean();
        String aliasPrBoolean_ = _context.getAliasPrimBoolean();
        return isBoolType(aliasBoolean_, aliasPrBoolean_);
    }

    public boolean isBoolType(String _aliasBoolean, String _aliasPrBoolean) {
        for (String b: className) {
            if (b.isEmpty()) {
                return true;
            }
            if (matchClass(_aliasBoolean)) {
                return true;
            }
            if (matchClass(_aliasPrBoolean)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return className.first();
    }

    public String getSingleNameOrEmpty() {
        return NumParsers.getSingleNameOrEmpty(className);
    }

    public StringList getNames() {
        return className;
    }

    public void setUnwrapObject(String _unwrapObject, StringMap<PrimitiveType> _primitiveTypes) {
        unwrapObjectNb = (byte)-1;
        for (EntryCust<String,PrimitiveType> p: _primitiveTypes.entryList()) {
            if (StringUtil.quickEq(p.getKey(),_unwrapObject)) {
                unwrapObjectNb = p.getValue().getCastNb();
            }
        }
    }

    public void setUnwrapObject(AnaClassArgumentMatching _other, StringMap<PrimitiveType> _primitiveTypes) {
        unwrapObjectNb = (byte)-1;
        for (EntryCust<String,PrimitiveType> p: _primitiveTypes.entryList()) {
            if (_other.matchClass(p.getKey())) {
                unwrapObjectNb = p.getValue().getCastNb();
            }
        }
    }

    public byte getUnwrapObjectNb() {
        return unwrapObjectNb;
    }

    public void setUnwrapObjectNb(byte _unwrapObjectNb) {
        this.unwrapObjectNb = _unwrapObjectNb;
    }

    public boolean isCheckOnlyNullPe() {
        return checkOnlyNullPe;
    }

    public void setCheckOnlyNullPe(boolean _checkOnlyNullPe) {
        checkOnlyNullPe = _checkOnlyNullPe;
    }

    public boolean isConvertToString() {
        return convertToString;
    }

    public void setConvertToString(boolean _convertToString) {
        convertToString = _convertToString;
    }

    public CustList<ClassMethodId> getImplicits() {
        return implicits;
    }

    public CustList<ClassMethodId> getImplicitsTest() {
        return implicitsTest;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

    public MemberId getMemberIdTest() {
        return memberIdTest;
    }

    public void setMemberIdTest(MemberId _memberIdTest) {
        this.memberIdTest = _memberIdTest;
    }

}
