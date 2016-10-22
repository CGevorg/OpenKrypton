package com.krypton.app;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

/**
 * Created by employee on 10/22/16.
 */
public class Morphology extends JFrame{
    private JRadioButton erodeRbtn = new JRadioButton("Erode");
    private JRadioButton dilateRbtn = new JRadioButton("Dilate");
    private JRadioButton openRbtn = new JRadioButton("Open");
    private JRadioButton closeRbtn = new JRadioButton("Close");
    private JSlider sizeSlider = new JSlider();
    private JRadioButton rectangleRbtn = new JRadioButton("Rectangle");
    private JRadioButton ellipseRbtn = new JRadioButton("Ellipse");
    private JRadioButton crossRbtn = new JRadioButton("Cross");
    private JPanel topPanel = new JPanel();

    public Morphology() {
        this.init();
    }

    private void init() {
        this.initGui();
    }

    private void initGui() {
        this.initTop();
    }

    private void initTop() {
        this.initTopLeft();
        this.initTopRight();
    }

    private void initTopLeft() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(new JLabel("Operation:"));
        panel.add(new JLabel("Kernel size:"));
        panel.add(new JLabel("Shape:"));




    }

    private void initTopRight() {
    }
}
