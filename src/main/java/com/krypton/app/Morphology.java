package com.krypton.app;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Morphology extends JFrame {
    private final String IMAGE_PATH = "/home/employee/workspace/DIP/OpenKrypton/resources/monkey.jpg";
    private final int ELEMENT_SHAPE = 0;

    private JRadioButton erodeRbtn = new JRadioButton("Erode");
    private JRadioButton dilateRbtn = new JRadioButton("Dilate");
    private JRadioButton openRbtn = new JRadioButton("Open");
    private JRadioButton closeRbtn = new JRadioButton("Close");
    private JSlider sizeSlider = new JSlider();
    private JRadioButton rectangleRbtn = new JRadioButton("Rectangle");
    private JRadioButton ellipseRbtn = new JRadioButton("Ellipse");
    private JRadioButton crossRbtn = new JRadioButton("Cross");
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JLabel imageLbl = new JLabel();
    private ImageProcessor processor = new ImageProcessor();

    public Morphology() {
        this.init();
    }

    private void init() {
        this.initGui();
        this.initComp();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initMainPanel() {
        this.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
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

        this.erodeRbtn.setSelected(true);
        this.rectangleRbtn.setSelected(true);
        this.initListeners();
    }

    private void initListeners() {
        erodeRbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mat m = processor.erode(Imgcodecs.imread(IMAGE_PATH), sizeSlider.getValue(),ELEMENT_SHAPE);
                Image image = ImageProcessor.toBufferedImage(m);
                imageLbl.setIcon(new ImageIcon(image));
            }
        });

        dilateRbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mat m = processor.dilate(Imgcodecs.imread(IMAGE_PATH), sizeSlider.getValue(),ELEMENT_SHAPE);
                Image image = ImageProcessor.toBufferedImage(m);
                imageLbl.setIcon(new ImageIcon(image));
            }
        });

        openRbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mat m = processor.open(Imgcodecs.imread(IMAGE_PATH), sizeSlider.getValue(),ELEMENT_SHAPE);
                Image image = ImageProcessor.toBufferedImage(m);
                imageLbl.setIcon(new ImageIcon(image));
            }
        });

        crossRbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mat m = processor.open(Imgcodecs.imread(IMAGE_PATH), sizeSlider.getValue(),ELEMENT_SHAPE);
                Image image = ImageProcessor.toBufferedImage(m);
                imageLbl.setIcon(new ImageIcon(image));
            }
        });



        sizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Mat m = null;
                if(erodeRbtn.isSelected())
                {
                    m = processor.erode(Imgcodecs.imread(IMAGE_PATH), sizeSlider.getValue(),0);
                }
                if(dilateRbtn.isSelected())
                {
                    m = processor.dilate(Imgcodecs.imread(IMAGE_PATH), sizeSlider.getValue(),0);
                }
                if(openRbtn.isSelected())
                {
                    m = processor.open(Imgcodecs.imread(IMAGE_PATH), sizeSlider.getValue(),0);
                }
                if(crossRbtn.isSelected())
                {
                    m = processor.close(Imgcodecs.imread(IMAGE_PATH), sizeSlider.getValue(),0);
                }
                Image image = ImageProcessor.toBufferedImage(m);
                imageLbl.setIcon(new ImageIcon(image));
            }
        });
    }

    private void initGui() {
        this.initMainPanel();
        this.initTop();
        this.initDown();
    }


    private void initTop() {
        this.initTopPanel();
        this.initTopLeft();
        this.initTopRight();
    }

    private void initDown() {
        mainPanel.add(imageLbl);
    }

    private void initTopPanel() {
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        mainPanel.add(topPanel);
    }

    private void initTopLeft() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
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
        g.add(crossRbtn);
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

    public void openPicture(String filePath) {
        ImageIcon icon = new ImageIcon(filePath);
        imageLbl.setIcon(icon);
    }
}









