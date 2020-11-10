package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.exec.blocks.ExecInfoBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.Members;
import code.util.CustList;

public final class FetchMemberUtil {
    private FetchMemberUtil() {
    }

    public static void setImplicits(AnaClassArgumentMatching _ana, ImplicitMethods _implicitsOp, ImplicitMethods _implicitsTestOp, Forwards _forwards) {
        CustList<ClassMethodId> implicits_ = _ana.getImplicits();
        String owner_ = "";
        ExecTypeFunction conv_ = null;
        if (!implicits_.isEmpty()) {
            owner_ = implicits_.first().getClassName();
            conv_ = fetchTypeFunction(_ana.getRootNumber(),_ana.getMemberNumber(), _forwards);
        }
        if (conv_ != null) {
            _implicitsOp.getConverter().add(conv_);
            _implicitsOp.setOwnerClass(owner_);
        }
        CustList<ClassMethodId> implicitsTest_ = _ana.getImplicitsTest();
        String ownerTest_ = "";
        ExecTypeFunction convTest_ = null;
        if (!implicitsTest_.isEmpty()) {
            ownerTest_ = implicitsTest_.first().getClassName();
            convTest_ = fetchTypeFunction(_ana.getRootNumberTest(),_ana.getMemberNumberTest(), _forwards);
        }
        if (convTest_ != null) {
            _implicitsTestOp.getConverter().add(convTest_);
            _implicitsTestOp.setOwnerClass(ownerTest_);
        }
    }

    public static ImplicitMethods fetchImplicits(ClassMethodId _clMet, int _root, int _member, Forwards _forwards) {
        ExecTypeFunction conv_ = null;
        String converterClass_ = "";
        if (_clMet != null) {
            converterClass_ = _clMet.getClassName();
            conv_ = fetchTypeFunction(_root,_member, _forwards);
        }
        if (conv_ != null) {
            ImplicitMethods converter_ = new ImplicitMethods();
            converter_.getConverter().add(conv_);
            converter_.setOwnerClass(converterClass_);
            return converter_;
        }
        return null;
    }

    public static ExecRootBlock fetchType(int _nbRoot, Forwards _forwards) {
        if (_forwards.getMapMembers().isValidIndex(_nbRoot)) {
            return _forwards.getMapMembers().getValue(_nbRoot).getRootBlock();
        }
        return null;
    }

    public static ExecInfoBlock fetchField(int _rootNumber, int _memberNumber, Forwards _forwards) {
        if (_forwards.getMapMembers().isValidIndex(_rootNumber)) {
            if (_forwards.getMapMembers().getValue(_rootNumber).getAllFields().isValidIndex(_memberNumber)) {
                return _forwards.getMapMembers().getValue(_rootNumber).getAllFields().getValue(_memberNumber);
            }
        }
        return null;
    }

    public static MethodAccessKind getKind(ClassMethodId _cl) {
        if (_cl == null) {
            return MethodAccessKind.STATIC;
        }
        return _cl.getConstraints().getKind();
    }

    public static String getType(ClassMethodId _cl) {
        if (_cl == null) {
            return "";
        }
        return _cl.getClassName();
    }

    public static ExecNamedFunctionBlock fetchFunctionOp(int _rootNumber, int _memberNumber, Forwards _forwards) {
        return fetchFunctionOrOp(_rootNumber,_memberNumber,_memberNumber, _forwards);
    }

    public static ExecNamedFunctionBlock fetchFunctionOrOp(int _rootNumber, int _memberNumber, int _operatorNumber, Forwards _forwards) {
        if (_forwards.getMapMembers().isValidIndex(_rootNumber)) {
            if (_forwards.getMapMembers().getValue(_rootNumber).getAllNamed().isValidIndex(_memberNumber)) {
                return _forwards.getMapMembers().getValue(_rootNumber).getAllNamed().getValue(_memberNumber);
            }
            return null;
        }
        if (_forwards.getMapOperators().isValidIndex(_operatorNumber)) {
            return _forwards.getMapOperators().getValue(_operatorNumber);
        }
        return null;
    }

    public static ExecNamedFunctionBlock fetchFunction(Members _member, int _nbMember) {
        if (_member.getAllNamed().isValidIndex(_nbMember)) {
            return _member.getAllNamed().getValue(_nbMember);
        }
        return null;
    }

    public static ExecTypeFunction fetchTypeFunction(int _nbRoot, int _nbMember, Forwards _forwards) {
        if (_forwards.getMapMembers().isValidIndex(_nbRoot)) {
            Members mem_ = _forwards.getMapMembers().getValue(_nbRoot);
            if (mem_.getAllNamed().isValidIndex(_nbMember)) {
                return new ExecTypeFunction(mem_.getRootBlock(),mem_.getAllNamed().getValue(_nbMember));
            }
        }
        return null;
    }

    public static ExecTypeFunction fetchTypeCtor(int _nbRoot, int _nbMember, Forwards _forwards) {
        if (_forwards.getMapMembers().isValidIndex(_nbRoot)) {
            Members mem_ = _forwards.getMapMembers().getValue(_nbRoot);
            return new ExecTypeFunction(mem_.getRootBlock(),fetchFunction(mem_,_nbMember));
        }
        return null;
    }
    public static ExecClassArgumentMatching toExec(AnaClassArgumentMatching _cl) {
        return new ExecClassArgumentMatching(_cl.getNames(),_cl.getUnwrapObjectNb(),
                _cl.isCheckOnlyNullPe(),_cl.isConvertToString());
    }

    public static ExecTypeFunction defPair(ExecRootBlock _ex, ExecTypeFunction _pair) {
        ExecTypeFunction pair_ = _pair;
        if (pair_ == null) {
            pair_ = new ExecTypeFunction(_ex,null);
        }
        return pair_;
    }
}
