package com.krypton.app;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

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
        this.initComp();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComp() {
        sizeSlider.setOrientation(JSlider.HORIZONTAL);
        sizeSlider.setMinimum(0);
        sizeSlider.setMaximum(20);
        sizeSlider.setValue(0);
        sizeSlider.setPaintTrack(true);
        sizeSlider.setSnapToTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setMinorTickSpacing(0);
        sizeSlider.setMajorTickSpacing(5);
    }

    private void initGui() {
        this.initTop();
    }

    private void initTop() {
        this.initTopPanel();
        this.initTopLeft();
        this.initTopRight();
    }

    private void initTopPanel() {
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
        this.add(topPanel);
    }

    private void initTopLeft() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(new JLabel("Operation:"));
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Kernel size:"));
        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Shape:"));

        topPanel.add(panel);
    }

    private void initTopRight() {
        ButtonGroup group = new ButtonGroup();
        group.add(erodeRbtn);
        group.add(dilateRbtn);
        group.add(openRbtn);
        group.add(closeRbtn);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(erodeRbtn);
        panel.add(dilateRbtn);
        panel.add(openRbtn);
        panel.add(closeRbtn);

        ButtonGroup g = new ButtonGroup();
        g.add(rectangleRbtn);
        g.add(ellipseRbtn);
        g.add(closeRbtn);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(rectangleRbtn);
        p.add(ellipseRbtn);
        p.add(crossRbtn);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(panel);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(sizeSlider);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(p);
        topPanel.add(rightPanel);
    }
}









