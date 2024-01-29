package cards.gui.events;

import cards.gui.WindowCards;
import cards.gui.containers.Containable;
import cards.gui.containers.ContainerPlayableGame;
import cards.gui.containers.ContainerSingle;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;
import code.gui.events.AbsMouseListenerWithoutClickPr;

public abstract class AbstractListenerCard implements AbsMouseListenerWithoutClickPr, AbsMouseListenerIntRel {

    private final ContainerPlayableGame containerBase;

    protected AbstractListenerCard(ContainerPlayableGame _container) {
        containerBase = _container;
    }

    protected abstract void affecterCarteSurvolee();
    protected abstract void jeuCarte(boolean _carteSurvolee);
    protected abstract void verifierRegles();
    protected abstract boolean canListen();
    protected abstract boolean playCardExited(AbsMouseLocation _event);
    protected void testEntreeSortie() {
        if(!containerBase.isThreadAnime()&& containerBase.isCarteSortie()&&!containerBase.isCarteEntree()) {
            jeuCarte(true);
        }
    }
    protected boolean clicCarte() {
        return containerBase.getParametres().getJeuCarteClic();
    }
    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!enabledEvents(containerBase,this)) {
            return;
        }
        if(!clicCarte()) {
            containerBase.setCarteEntree(true);
            containerBase.setCarteSortie(false);
            affecterCarteSurvolee();
            testEntreeSortie();
        }
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!enabledEvents(containerBase,this)) {
            return;
        }
        if(!clicCarte()) {
            if (!playCardExited(_location)) {
                return;
            }
            containerBase.setCarteSortie(true);
            containerBase.setCarteEntree(false);
            affecterCarteSurvolee();
            testEntreeSortie();
        }
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!enabledEvents(containerBase,this)) {
            return;
        }
        if(clicCarte()) {
            jeuCarte(false);
        }
        if(containerBase.isaJoueCarte()&&clicCarte()) {
            containerBase.setRaisonCourante(containerBase.getMessages().getVal(WindowCards.ALREADY_PLAYED));
            containerBase.setaJoueCarte(false);
        }
    }

    public static boolean enabledEvents(Containable _c,AbstractListenerCard _a) {
        return enabledEvents(_c)&&_a.canListen();
    }

    public static boolean enabledEvents(Containable _c) {
        return !(_c instanceof ContainerSingle)||!((ContainerSingle)_c).window().getFileSaveFrame().getFrame().isVisible();
    }
//    @Override
//    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }
}
