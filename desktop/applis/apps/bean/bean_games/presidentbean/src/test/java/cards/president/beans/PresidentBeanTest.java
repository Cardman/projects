package cards.president.beans;

import cards.president.ResultsPresident;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Navigation;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.scripts.pages.cards.MessPresidentPage;
import code.scripts.pages.cards.PagesPresidents;
import code.util.*;
import code.util.consts.Constants;
import org.junit.Test;

public final class PresidentBeanTest extends BeanPresidentCommonTs {
    @Test
    public void getNickNames() {
        Struct res_ = callPresidentBeanNicknames(displaying(beanResults(EN, build(fourPseudos("0", "1", "2", "3"), oneDeal(1, 3, 2, 4), (byte) 2, (byte) 1, (byte) 3, (byte) 4))));
        assertSizeEq(4,res_);
        assertEq("0",res_,0);
        assertEq("1",res_,1);
        assertEq("2",res_,2);
        assertEq("3",res_,3);
    }
    @Test
    public void getLinesDeal() {
        Struct res_ = callPresidentBeanLinesDeal(displaying(beanResults(EN, build(fourPseudos("0", "1", "2", "3"), oneDeal(1, 3, 2, 4), (byte) 2, (byte) 1, (byte) 3, (byte) 4))));
        assertSizeEq(2,res_);
        assertSizeEq(4,res_,0);
        assertNumberEq(0,res_,0);
        assertEq(1,res_,0,0);
        assertEq(3,res_,0,1);
        assertEq(2,res_,0,2);
        assertEq(4,res_,0,3);
        assertSizeEq(4,res_,1);
        assertNumberEq(1,res_,1);
        assertEq(2,res_,1,0);
        assertEq(1,res_,1,1);
        assertEq(3,res_,1,2);
        assertEq(4,res_,1,3);
    }
    @Test
    public void init() {
        StringMap<String> other_ = MessPresidentPage.ms();
        AnaRendBlock.adjust(other_);
        PresidentStandardsResults stds_ = new PresidentStandardsResults();
        Navigation nav_ = stds_.nav(Constants.getAvailableLanguages(),EN,new ResultsPresidentLoader(),PagesPresidents.build(),other_,other_,"");
        stds_.setDataBase(build(fourPseudos("0", "1", "2", "3"), oneDeal(1, 3, 2, 4), (byte) 2, (byte) 1, (byte) 3, (byte) 4));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\"resources_cards/css/president.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor: #0000FF;\n" +
                "}\n" +
                "</style></head><body><table border=\"1\"><caption>Ranks</caption><thead><tr><td/><td>0</td><td>1</td><td>2</td><td>3</td></tr></thead><tbody><tr><td>0</td><td>1</td><td>3</td><td>2</td><td>4</td></tr><tr><td>1</td><td>2</td><td>1</td><td>3</td><td>4</td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
    }
    private static CustList<Longs> oneDeal(long..._values) {
        CustList<Longs> ps_ = new CustList<Longs>();
        ps_.add(Longs.newList(_values));
        return ps_;
    }
    private static StringList fourPseudos(String _one, String _two, String _three, String _four) {
        StringList ps_ = new StringList();
        ps_.add(_one);
        ps_.add(_two);
        ps_.add(_three);
        ps_.add(_four);
        return ps_;
    }

    private static ResultsPresident build(StringList _pseudos, CustList<Longs> _scores, byte... _r) {
        ResultsPresident res_ = new ResultsPresident();
        res_.initialize(_pseudos, _scores, Bytes.newList(_r));
        return res_;
    }
}
