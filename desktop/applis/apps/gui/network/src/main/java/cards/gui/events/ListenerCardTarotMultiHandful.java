package cards.gui.events;



import cards.facade.Games;
import cards.gui.containers.ContainerMultiTarot;
import cards.gui.containers.ContainerTarot;
import cards.tarot.GameTarotCommonPlaying;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.AbsMouseLocation;
import code.gui.GuiConstants;
import code.network.WindowNetWork;
import code.util.core.StringUtil;

public class ListenerCardTarotMultiHandful extends AbstractListenerCardTarot {
    private ContainerMultiTarot container;
    private boolean included;
    public ListenerCardTarotMultiHandful(ContainerMultiTarot _container,CardTarot _pcarte, boolean _included) {
        super(_container,_pcarte);
        container = _container;
        included = _included;
    }
    @Override
    protected boolean canListen() {
        return container.isCanExcludeTrumps();
    }
    @Override
    protected boolean playCardExited(AbsMouseLocation _event) {
        return _event.getYcoord() < 0;
    }
    @Override
    protected void verifierRegles() {
        String lg_ = container.getOwner().getLanguageKey();
        if(StringUtil.quickEq(container.getRaisonCourante(),ContainerTarot.EMPTY)) {
            if (included) {
                container.getCurrentIncludedTrumps().jouer(getCarteVerif());
                container.getCurrentExcludedTrumps().ajouter(getCarteVerif());
            } else {
                container.getCurrentIncludedTrumps().ajouter(getCarteVerif());
                container.getCurrentExcludedTrumps().jouer(getCarteVerif());
            }
            container.displayTrumpsForHandfulMulti(GameTarotCommonPlaying.atoutsPoignee(container.getPlayerHand().couleurs()));
            if (container.getChoosenHandful() != Handfuls.NO) {
                String mes_ = container.getMessages().getVal(WindowNetWork.REMOVE_TRUMPS_HANDFUL);
                int exces_ = container.getCurrentIncludedTrumps().total()-container.getRequiredTrumps().getVal(container.getChoosenHandful());
                container.getInfoCurrentHandful().setText(StringUtil.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(container.getChoosenHandful(),lg_)));
            }
        }else{
            String finalMessage_ = StringUtil.concat(container.getMessages().getVal(WindowNetWork.CANT_PLAY),container.getRaisonCourante());
            String title_ = container.getMessages().getVal(WindowNetWork.TOO_GAME);
            container.getOwner().getFrames().getMessageDialogAbs().input(container.getOwner().getCommonFrame(), finalMessage_,title_, lg_, GuiConstants.ERROR_MESSAGE);
        }
    }

}
