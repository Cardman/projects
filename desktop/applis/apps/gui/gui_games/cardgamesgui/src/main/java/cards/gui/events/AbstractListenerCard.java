package cards.gui.events;

import cards.gui.WindowCards;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingle;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerIntRel;
import code.gui.events.AbsMouseListenerWithoutClickPr;

public abstract class AbstractListenerCard implements AbsMouseListenerWithoutClickPr, AbsMouseListenerIntRel {

    private final ContainerGame container;

    protected AbstractListenerCard(ContainerGame _container) {
        container = _container;
    }

    protected ContainerGame getContainer() {
        return container;
    }

    protected abstract void affecterCarteSurvolee();
    protected abstract void jeuCarte(boolean _carteSurvolee);
    protected abstract void verifierRegles();
    protected abstract boolean canListen();
    protected abstract boolean playCardExited(AbsMouseLocation _event);
    protected void testEntreeSortie() {
        if(!container.isThreadAnime()&&container.isCarteSortie()&&!container.isCarteEntree()) {
            jeuCarte(true);
        }
    }
    protected boolean clicCarte() {
        return container.getParametres().getJeuCarteClic();
    }
    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!enabledEvents(container,this)) {
            return;
        }
        if(!clicCarte()) {
            container.setCarteEntree(true);
            container.setCarteSortie(false);
            affecterCarteSurvolee();
            testEntreeSortie();
        }
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!enabledEvents(container,this)) {
            return;
        }
        if(!clicCarte()) {
            if (!playCardExited(_location)) {
                return;
            }
            container.setCarteSortie(true);
            container.setCarteEntree(false);
            affecterCarteSurvolee();
            testEntreeSortie();
        }
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!enabledEvents(container,this)) {
            return;
        }
        if(clicCarte()) {
            jeuCarte(false);
        }
        if(container.isaJoueCarte()&&clicCarte()) {
            container.setRaisonCourante(container.getMessages().getVal(WindowCards.ALREADY_PLAYED));
            container.setaJoueCarte(false);
        }
    }

    public static boolean enabledEvents(ContainerGame _c,AbstractListenerCard _a) {
        return enabledEvents(_c)&&_a.canListen();
    }

    public static boolean enabledEvents(ContainerGame _c) {
        return !(_c instanceof ContainerSingle)||!((ContainerSingle)_c).window().getFileSaveFrame().getFrame().isVisible();
    }
//    @Override
//    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
//        //
//    }
}
