package cards.president.beans;

import cards.consts.GameType;
import cards.president.*;
import cards.president.enumerations.CardPresident;
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
    public void init1() {
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
    @Test
    public void init2() {
        StringMap<String> other_ = MessPresidentPage.ms();
        AnaRendBlock.adjust(other_);
        PresidentStandardsResults stds_ = new PresidentStandardsResults();
        Navigation nav_ = stds_.nav(Constants.getAvailableLanguages(),EN,new ResultsPresidentLoader(),PagesPresidents.build(),other_,other_,"");
        stds_.setDataBase(build(game(),fourPseudos("0", "1", "2", "3"), oneDeal(1, 3, 2, 4)));
        stds_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Results</title><link href=\"resources_cards/css/president.css\" rel=\"stylesheet\" type=\"text/css\"/><style>h1 {\n" +
                "\tcolor: #0000FF;\n" +
                "}\n" +
                "</style></head><body><table border=\"1\"><caption>Ranks</caption><thead><tr><td/><td>0</td><td>1</td><td>2</td><td>3</td></tr></thead><tbody><tr><td>0</td><td>1</td><td>3</td><td>2</td><td>4</td></tr><tr><td>1</td><td>2</td><td>5</td><td>5</td><td>8</td></tr></tbody></table><br/></body></html>",nav_.getHtmlText());
    }
    private static GamePresident game() {
        RulesPresident r_ = new RulesPresident(4);
        Bytes rk_ = Bytes.newList();
        CustList<HandPresident> hs_ = deal1();
        DealPresident d_ = new DealPresident(hs_, (byte) 0);
        GamePresident g_ = new GamePresident(GameType.RANDOM, d_, r_, rk_);
        g_.initCartesEchanges();
        //
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.SPADE_3,1);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.HEART_3,1);
        addCardsToCurrentTrick(g_,(byte) 3, CardPresident.DIAMOND_3,1);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.CLUB_3,1);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.DIAMOND_4,2);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.HEART_4,2);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.CLUB_7,1);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.HEART_7,1);
        addCardsToCurrentTrick(g_,(byte) 3, CardPresident.DIAMOND_7,1);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.SPADE_7,1);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.HEART_8,2);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.SPADE_8,2);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.SPADE_9,1);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.HEART_9,1);
        addCardsToCurrentTrick(g_,(byte) 3, CardPresident.DIAMOND_9,1);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.CLUB_9,1);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.SPADE_10,1);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.CLUB_10,1);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.HEART_10,1);
        addCardsToCurrentTrick(g_,(byte) 3, CardPresident.DIAMOND_10,1);
        addCardsToCurrentTrick(g_,(byte) 3, CardPresident.HEART_QUEEN,2);
        noPlay(g_,(byte) 0);
        noPlay(g_,(byte) 1);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.SPADE_QUEEN,2);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.HEART_KING,1);
        addCardsToCurrentTrick(g_,(byte) 3, CardPresident.DIAMOND_KING,1);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.SPADE_KING,1);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.CLUB_KING,1);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.DIAMOND_2,1);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.HEART_1,1);
        noPlay(g_,(byte) 2);
        noPlay(g_,(byte) 3);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.DIAMOND_1,1);
        noPlay(g_,(byte) 2);
        noPlay(g_,(byte) 3);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.HEART_2,1);
        addCardsToCurrentTrick(g_,(byte) 0, CardPresident.SPADE_JACK,2);
        addCardsToCurrentTrick(g_,(byte) 1, CardPresident.HEART_JACK,2);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.HEART_5,2);
        addCardsToCurrentTrick(g_,(byte) 3, CardPresident.DIAMOND_5,2);
        addCardsToCurrentTrick(g_,(byte) 3, CardPresident.HEART_6,2);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.SPADE_2,2);
        addCardsToCurrentTrick(g_,(byte) 2, CardPresident.SPADE_6,2);
        return g_;
    }
    static void addCardsToCurrentTrick(GamePresident _g ,byte _player, CardPresident _card, int _nb) {
        _g.addCardsToCurrentTrick(_player,_card, (byte) _nb);
    }
    static void noPlay(GamePresident _g ,byte _player) {
        _g.noPlay(_player);
    }

    static CustList<HandPresident> deal1() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
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

    private static ResultsPresident build(GamePresident _g,StringList _pseudos, CustList<Longs> _scores) {
        ResultsPresident res_ = new ResultsPresident();
        res_.setGame(_g);
        res_.initialize(_pseudos, _scores);
        return res_;
    }
}
