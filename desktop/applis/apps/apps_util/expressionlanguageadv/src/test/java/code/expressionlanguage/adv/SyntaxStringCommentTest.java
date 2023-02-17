package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.gui.GuiConstants;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockAttrSet;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.mock.MockTextPane;
import code.util.CustList;
import code.util.StringList;
import org.junit.Test;

public final class SyntaxStringCommentTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        changeNow(pr_, w_,"hello \"_\" ");
        assertEq(10,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(GuiConstants.GREEN,((MockAttrSet)((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).get(0)).getFore());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(GuiConstants.GREEN,((MockAttrSet)((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).get(0)).getFore());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(GuiConstants.GREEN,((MockAttrSet)((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).get(0)).getFore());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfosInitConf();
        WindowCdmEditor w_ =windowLoadDef(pr_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        w_.setComments(comments_);
        changeNow(pr_, w_,"hello \\*_*\\ ");
        assertEq(12,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(GuiConstants.GRAY,((MockAttrSet)((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).get(0)).getFore());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(GuiConstants.GRAY,((MockAttrSet)((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).get(0)).getFore());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(GuiConstants.GRAY,((MockAttrSet)((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).get(0)).getFore());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(GuiConstants.GRAY,((MockAttrSet)((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).get(0)).getFore());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(GuiConstants.GRAY,((MockAttrSet)((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).get(0)).getFore());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(11).size());
    }
}
