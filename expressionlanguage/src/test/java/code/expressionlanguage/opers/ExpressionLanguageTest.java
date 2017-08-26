package code.expressionlanguage.opers;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.variables.LocalVariable;

@SuppressWarnings("static-method")
public class ExpressionLanguageTest {

    @Ignore
    @Test
    public void affectMember1Test() {
        ContextEl context_ = new ContextEl();
        PageEl page_ = new PageEl();
        context_.addPage(page_);
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        lv_.setElement(8);
        page_.getLocalVars().put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        page_.getLocalVars().put("k", lv_);
        ExpLanguages e_ = ElUtil.analyzeAffect("","","","k;.", "v;.", "=", context_, true);
        ExpressionLanguage left_;
        left_ = e_.getLeft();
        ExpressionLanguage right_;
        right_ = e_.getRight();
        assertNull( page_.getLocalVars().getVal("k").getElement());
        left_.affectMember(context_, right_, "=");
//        System.out.println(left_.getRoot().getArgument().getObject());
        assertEq(8, (Number)page_.getLocalVars().getVal("k").getElement());
        lv_ = page_.getLocalVars().getVal("v");
        lv_.setElement(9);
        left_.affectMember(context_, right_, "=");
        assertEq(9, (Number)page_.getLocalVars().getVal("k").getElement());
    }
}
