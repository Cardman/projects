package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.exec.blocks.ExecInfoBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.Forwards;
import code.util.CustList;

public final class FetchMemberUtil {
    private FetchMemberUtil() {
    }

    public static void setImplicits(AnaClassArgumentMatching _ana, ImplicitMethods _implicitsOp, ImplicitMethods _implicitsTestOp, Forwards _forwards) {
        CustList<ClassMethodId> implicits_ = _ana.getImplicits();
        String owner_ = "";
        ExecNamedFunctionBlock conv_ = null;
        if (!implicits_.isEmpty()) {
            owner_ = implicits_.first().getClassName();
            conv_ = fetchFunction(_ana.getRootNumber(),_ana.getMemberNumber(), _forwards);
        }
        if (conv_ != null) {
            ExecRootBlock classBody_ = fetchType(_ana.getRootNumber(), _forwards);
            _implicitsOp.getConverter().add(conv_);
            _implicitsOp.setOwnerClass(owner_);
            _implicitsOp.setRootBlock(classBody_);
        }
        CustList<ClassMethodId> implicitsTest_ = _ana.getImplicitsTest();
        String ownerTest_ = "";
        ExecNamedFunctionBlock convTest_ = null;
        if (!implicitsTest_.isEmpty()) {
            ownerTest_ = implicitsTest_.first().getClassName();
            convTest_ = fetchFunction(_ana.getRootNumberTest(),_ana.getMemberNumberTest(), _forwards);
        }
        if (convTest_ != null) {
            ExecRootBlock classBody_ = fetchType(_ana.getRootNumberTest(), _forwards);
            _implicitsTestOp.getConverter().add(convTest_);
            _implicitsTestOp.setOwnerClass(ownerTest_);
            _implicitsTestOp.setRootBlock(classBody_);
        }
    }

    public static ImplicitMethods fetchImplicits(ClassMethodId _clMet, int _root, int _member, Forwards _forwards) {
        ExecNamedFunctionBlock conv_ = null;
        String converterClass_ = "";
        if (_clMet != null) {
            converterClass_ = _clMet.getClassName();
            conv_ = fetchFunction(_root,_member, _forwards);
        }
        if (conv_ != null) {
            ImplicitMethods converter_ = new ImplicitMethods();
            ExecRootBlock classBody_ = fetchType(_root, _forwards);
            converter_.getConverter().add(conv_);
            converter_.setOwnerClass(converterClass_);
            converter_.setRootBlock(classBody_);
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
        return fetchFunction(_rootNumber,_memberNumber,_memberNumber, _forwards);
    }

    public static ExecNamedFunctionBlock fetchFunction(int _rootNumber, int _memberNumber, int _operatorNumber, Forwards _forwards) {
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

    public static ExecNamedFunctionBlock fetchFunction(AbstractCallFctOperation _l, Forwards _forwards) {
        return fetchFunction(_l.getRootNumber(),_l.getMemberNumber(), _forwards);
    }

    public static ExecNamedFunctionBlock fetchFunction(int _nbRoot, int _nbMember, Forwards _forwards) {
        if (_forwards.getMapMembers().isValidIndex(_nbRoot)) {
            if (_forwards.getMapMembers().getValue(_nbRoot).getAllNamed().isValidIndex(_nbMember)) {
                return _forwards.getMapMembers().getValue(_nbRoot).getAllNamed().getValue(_nbMember);
            }
        }
        return null;
    }

    public static ExecClassArgumentMatching toExec(AnaClassArgumentMatching _cl) {
        return new ExecClassArgumentMatching(_cl.getNames(),_cl.getUnwrapObjectNb(),
                _cl.isCheckOnlyNullPe(),_cl.isConvertToString());
    }
}
