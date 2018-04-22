package code.gui;

import java.awt.Graphics;

import javax.swing.JLabel;

public interface Paintable {

    void paintComponent(Graphics _g);
    JLabel getComponent();
}
