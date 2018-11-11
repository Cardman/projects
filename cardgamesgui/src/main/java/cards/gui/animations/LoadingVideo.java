package cards.gui.animations;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JProgressBar;

import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import code.gui.Panel;
import code.gui.SplashWindow;
import code.gui.TextLabel;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingVideo extends Thread {
    private SplashWindow progressingWindow;
    private ContainerGame container;
    private JProgressBar barreProgression;
    private TextLabel label;
    private SimulationGame simu;
    private AtomicInteger adv;

    /**This class thread is independant from EDT*/
    public LoadingVideo(ContainerGame _container, SimulationGame _simu) {
        container = _container;
        simu = _simu;
        barreProgression=new JProgressBar();
        barreProgression.setValue(0);
        barreProgression.setPreferredSize(new Dimension(200,50));
        Panel container_=new Panel();
        container_.setLayout(new GridLayout(0,1));
        label=new TextLabel(StringList.simpleNumberFormat(container.getMessages().getVal(MainWindow.LOADING), barreProgression.getValue()));
        container_.add(label);
        container_.add(barreProgression);
        progressingWindow = new SplashWindow(container.getOwner());
        progressingWindow.setIconImage(container.getOwner().getImageIconFrame());
        progressingWindow.setLocationRelativeTo(container.getOwner());
        progressingWindow.setContentPane(container_);
        progressingWindow.pack();
        progressingWindow.setVisible(true);
    }
    public LoadingVideo(ContainerGame _container, AtomicInteger _adv) {
        container = _container;
        adv = _adv;
        barreProgression=new JProgressBar();
        barreProgression.setValue(0);
        barreProgression.setPreferredSize(new Dimension(200,50));
        Panel container_=new Panel();
        container_.setLayout(new GridLayout(0,1));
        label=new TextLabel(StringList.simpleNumberFormat(container.getMessages().getVal(MainWindow.LOADING), barreProgression.getValue()));
        container_.add(label);
        container_.add(barreProgression);
        progressingWindow = new SplashWindow(container.getOwner());
        progressingWindow.setIconImage(container.getOwner().getImageIconFrame());
        progressingWindow.setLocationRelativeTo(container.getOwner());
        progressingWindow.setContentPane(container_);
        progressingWindow.pack();
        progressingWindow.setVisible(true);
    }

    public void setProgessingBar(int _value) {
        barreProgression.setValue(_value);
        label.setText(StringList.simpleNumberFormat(container.getMessages().getVal(MainWindow.LOADING), _value));
    }

    @Override
    public void run() {
        int max_=barreProgression.getMaximum();
        int current_ = barreProgression.getValue();
        if (simu == null) {
            while(current_<max_) {
                int maxAdv_ = adv.get();
                setProgessingBar(maxAdv_);
                current_ = maxAdv_;
            }
            progressingWindow.setVisible(false);
            progressingWindow.getPane().removeAll();
            progressingWindow.removeAll();
            progressingWindow.dispose();
            progressingWindow = null;
            container = null;
            return;
        }
        Games gs_ = simu.getGames();
        while(current_<max_) {
            int maxAdv_ = 0;
            if (gs_.enCoursDePartieBelote()) {
                maxAdv_ = gs_.partieBelote().getChargementSimulation();
            }
            if (gs_.enCoursDePartiePresident()) {
                maxAdv_ = gs_.partiePresident().getChargementSimulation();
            }
            if (gs_.enCoursDePartieTarot()) {
                maxAdv_ = gs_.partieTarot().getChargementSimulation();
            }
            setProgessingBar(maxAdv_);
            current_ = maxAdv_;
        }
        progressingWindow.setVisible(false);
        progressingWindow.getPane().removeAll();
        progressingWindow.removeAll();
        progressingWindow.dispose();
        progressingWindow = null;
        container = null;
    }

}
