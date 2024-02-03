package cards.gui.events;



import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPresident;
import cards.gui.containers.ContainerSinglePresident;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.enumerations.CardPresident;
import code.gui.AbsMouseLocation;
import code.gui.GuiConstants;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.core.StringUtil;

public class ListenerCardPresidentSingleGame extends
        AbstractListenerCardPresident {

    private ContainerSinglePresident container;

    public ListenerCardPresidentSingleGame(ContainerSinglePresident _container,
            CardPresident _card, byte _index) {
        super(_container, _card, _index);
        container = _container;
    }

    @Override
    public boolean canListen() {
        return container.isCanPlay();
    }

    @Override
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }

    @Override
    protected void verifierRegles() {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if(StringUtil.quickEq(container.getRaisonCourante(),ContainerPresident.EMPTY)){
            GamePresident game_ = container.partiePresident();
            boolean allow_ = game_.allowPlaying(DealPresident.NUMERO_UTILISATEUR, getCarteVerif());
            if (!allow_) {
                StringBuilder mes_ = new StringBuilder(StringUtil.simpleStringsFormat(container.file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(getCarteVerif(),lg_)));
                mes_.append(ContainerGame.RETURN_LINE);
                mes_.append(Games.autorisePresident(game_,DealPresident.NUMERO_UTILISATEUR, getCarteVerif(), getIndexVerif(), lg_));
                String finalMessage_ = mes_.toString();
                String title_ = container.getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
                container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(), finalMessage_, title_, GuiConstants.ERROR_MESSAGE);
            } else {
                container.setaJoueCarte(true);
                container.finPliPresident(getCarteVerif(), getIndexVerif());
            }
        }else{
            String finalMessage_ = StringUtil.concat(container.file().getVal(MessagesGuiCards.MAIN_CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(WindowCards.TOO_GAME);
            container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(), finalMessage_, title_, GuiConstants.ERROR_MESSAGE);
        }
    }
}
