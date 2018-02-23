package cards.gui.animations;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import cards.belote.GameBelote;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.gui.SplashWindow;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingVideo extends Thread {
    private SplashWindow progressingWindow;
    private ContainerGame container;
    private JProgressBar barreProgression;
    private JLabel label;

    /**This class thread is independant from EDT*/
    public LoadingVideo(ContainerGame _container) {
        container = _container;
        barreProgression=new JProgressBar();
        barreProgression.setValue(0);
        barreProgression.setPreferredSize(new Dimension(200,50));
        JPanel container_=new JPanel();
        container_.setLayout(new GridLayout(0,1));
        label=new JLabel(StringList.simpleNumberFormat(container.getMessages().getVal(MainWindow.LOADING), barreProgression.getValue()));
        container_.add(label);
        container_.add(barreProgression);
        progressingWindow = new SplashWindow(container.getOwner());
        progressingWindow.setIconImage(container.getOwner().getImageIconFrame());
        progressingWindow.setLocationRelativeTo(container.getOwner());
        progressingWindow.setContentPane(container_);
        progressingWindow.pack();
        progressingWindow.setVisible(true);
//        addPropertyChangeListener(new ProgressingPropertyEvent(this));
    }

    public void setProgessingBar(int _value) {
        barreProgression.setValue(_value);
        label.setText(StringList.simpleNumberFormat(container.getMessages().getVal(MainWindow.LOADING), _value));
    }

    @Override
    public void run() {
//        JProgressBar barreProgression_=new JProgressBar();
//        barreProgression_.setValue(0);
//        barreProgression_.setPreferredSize(new Dimension(200,50));
//        Container container_=new Container();
//        container_.setLayout(new GridLayout(0,1));
//        JLabel etiquette_=new JLabel(StringList.simpleFormat(container.getMessages().getVal(MainWindow.LOADING), barreProgression_.getValue()));
//        container_.add(etiquette_);
//        container_.add(barreProgression_);
//        JWindow fenetre2=new JWindow(container.getOwner());
//        fenetre2.setLocationRelativeTo(container.getOwner());
//        fenetre2.setContentPane(container_);
//        fenetre2.pack();
//        fenetre2.setVisible(true);
        int max_=barreProgression.getMaximum();
//        int max_= 100;
        int current_ = barreProgression.getValue();
//        int current_ = 0;
        while(current_<max_) {
            int maxAvancement_ = Math.max(GameBelote.getChargementSimulation(), GameTarot.getChargementSimulation());
            maxAvancement_ = Math.max(maxAvancement_, GamePresident.getChargementSimulation());
            setProgessingBar(maxAvancement_);
//            barreProgression.setValue(maxAvancement_);
            current_ = maxAvancement_;
//            label.setText(StringList.simpleFormat(container.getMessages().getVal(MainWindow.LOADING), maxAvancement_));
        }
        progressingWindow.setVisible(false);
        progressingWindow.getContentPane().removeAll();
        progressingWindow.removeAll();
        progressingWindow.dispose();
        progressingWindow = null;
        container = null;
//        ThreadInvoker.invokeNow(new DoneThread(progressingWindow));
    }

//    @Override
//    protected void done() {
//        progressingWindow.setVisible(false);
//        progressingWindow.getContentPane().removeAll();
//        progressingWindow.removeAll();
//        progressingWindow.dispose();
//        progressingWindow = null;
//        container = null;
//    }
}
