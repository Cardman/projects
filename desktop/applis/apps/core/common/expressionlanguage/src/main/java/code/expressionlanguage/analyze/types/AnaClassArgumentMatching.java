package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ImplicitInfos;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
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
    private final CustList<AnaFormattedRootBlock> implicits = new CustList<AnaFormattedRootBlock>();
    private final CustList<AnaFormattedRootBlock> implicitsTest = new CustList<AnaFormattedRootBlock>();
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;
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

    public static byte getPrimitiveCast(String _className, PrimitiveTypes _page) {
        return ClassArgumentMatching.getPrimitiveCast(new StringList(_className), _page);
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

    public void implicitInfos(ImplicitInfos _info) {
        getImplicits().add(_info.getIdMethod());
        setMemberId(_info.getMemberId());
        setFunction(_info.getFunction());
    }
    public void implicitInfos(ClassMethodIdReturn _info) {
        implicitInfosCore(_info);
        setFunction(_info.getPair());
    }

    public void implicitInfosCore(ClassMethodIdReturn _info) {
        getImplicits().add(_info.getFormattedType());
        setMemberId(_info.getMemberId());
    }
    public void implicitInfosTest(ClassMethodIdReturn _info) {
        getImplicitsTest().add(_info.getFormattedType());
        setMemberIdTest(_info.getMemberId());
    }
    public void implicitInfosTest(OperatorConverter _info) {
        getImplicitsTest().add(_info.getTest().getFormattedType());
        setMemberIdTest(_info.getTest().getMemberId());
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

    public CustList<AnaFormattedRootBlock> getImplicits() {
        return implicits;
    }

    public CustList<AnaFormattedRootBlock> getImplicitsTest() {
        return implicitsTest;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

    public AnaTypeFct getFunction() {
        return function;
    }

    public void setFunction(AnaTypeFct _function) {
        this.function = _function;
    }

    public MemberId getMemberIdTest() {
        return memberIdTest;
    }

    public void setMemberIdTest(MemberId _memberIdTest) {
        this.memberIdTest = _memberIdTest;
    }

}
