package cards.gui.events;

import cards.gui.WindowCards;
import cards.gui.containers.ContainerGame;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListener;

public abstract class AbstractListenerCard implements AbsMouseListener {

    private ContainerGame container;

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
        if (!canListen()) {
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
        if (!canListen()) {
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
        if (!canListen()) {
            return;
        }
        if(container.isaJoueCarte()&&clicCarte()) {
            container.setRaisonCourante(container.getMessages().getVal(WindowCards.ALREADY_PLAYED));
            container.setaJoueCarte(false);
        }
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!canListen()) {
            return;
        }
        if(clicCarte()) {
            jeuCarte(false);
        }
    }

    @Override
    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        //
    }
}
