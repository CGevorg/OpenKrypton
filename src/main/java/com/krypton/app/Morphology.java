package com.krypton.app;


import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Morphology extends JFrame {

    private static Logger log = Logger.getLogger(Morphology.class.getName());

    public static String IMAGE_PATH = "/home/employee/workspace/DIP/OpenKrypton/resources/apple.jpg";

    private JButton openFileBtn = new JButton("Open New Image");
    private JButton changeBtn = new JButton("Change");
    private JButton resetBtn = new JButton("Reset");
    private JCheckBox immediatelyChk = new JCheckBox("Immediately change");

    private JRadioButton additionRbtn = new JRadioButton("Addition");
    private JRadioButton boundaryExtractionRbtn = new JRadioButton("BoundaryExtraction");
    private JRadioButton invertRbtn = new JRadioButton("Invert");
    private JRadioButton erodeRbtn = new JRadioButton("Erode");
    private JRadioButton dilateRbtn = new JRadioButton("Dilate");
    private JRadioButton openRbtn = new JRadioButton("Open");
    private JRadioButton closeRbtn = new JRadioButton("Close");
    private JRadioButton HITMISSRbtn = new JRadioButton("HITMISS");

    private JSlider sizeSlider = new JSlider();
    private JRadioButton rectangleRbtn = new JRadioButton("Rectangle");
    private JRadioButton ellipseRbtn = new JRadioButton("Ellipse");
    private JRadioButton crossRbtn = new JRadioButton("Cross");
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JLabel imageLbl = new JLabel();
    private ImageProcessor processor = new ImageProcessor();

    private ButtonGroup typeGroup = new ButtonGroup();
    private ButtonGroup shapeGroup = new ButtonGroup();

    private Mat currentMat = null;

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
        sizeSlider.setMajorTickSpacing(1);

        this.erodeRbtn.setSelected(true);
        this.rectangleRbtn.setSelected(true);
        this.initListeners();
    }

    private void initListeners() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (immediatelyChk.isSelected()) {
                    getNewImage();
                }
            }
        };


        additionRbtn.addActionListener(listener);
        boundaryExtractionRbtn.addActionListener(listener);
        invertRbtn.addActionListener(listener);
        erodeRbtn.addActionListener(listener);
        dilateRbtn.addActionListener(listener);
        openRbtn.addActionListener(listener);
        closeRbtn.addActionListener(listener);
        rectangleRbtn.addActionListener(listener);
        ellipseRbtn.addActionListener(listener);
        crossRbtn.addActionListener(listener);
        sizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (immediatelyChk.isSelected()) {
                    getNewImage();
                }
            }
        });

        openFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir") + File.separator + "resources");
                int returnVal = fc.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    openPicture(file.getPath());
                } else {
                    log.log(Level.WARNING, "Cannot open file return code is " + returnVal);
                }
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPicture(IMAGE_PATH);
            }
        });
        changeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNewImage();
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
        panel.add(Box.createVerticalStrut(20));
        panel.add(openFileBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(changeBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(resetBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(immediatelyChk);


        topPanel.add(panel);
    }

    private void initTopRight() {
        typeGroup.add(additionRbtn);
        typeGroup.add(boundaryExtractionRbtn);
        typeGroup.add(invertRbtn);
        typeGroup.add(erodeRbtn);
        typeGroup.add(dilateRbtn);
        typeGroup.add(openRbtn);
        typeGroup.add(closeRbtn);
        typeGroup.add(HITMISSRbtn);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(additionRbtn);
        panel.add(boundaryExtractionRbtn);
        panel.add(invertRbtn);
        panel.add(erodeRbtn);
        panel.add(dilateRbtn);
        panel.add(openRbtn);
        panel.add(closeRbtn);
        panel.add(HITMISSRbtn);

        shapeGroup.add(rectangleRbtn);
        shapeGroup.add(ellipseRbtn);
        shapeGroup.add(crossRbtn);
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

    int getElementShape() {
        Enumeration<AbstractButton> elements = shapeGroup.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton btn = elements.nextElement();
            if (btn.isSelected()) {
                if (btn == rectangleRbtn) {
                    return Imgproc.MORPH_RECT;
                } else if (btn == ellipseRbtn) {
                    return Imgproc.MORPH_ELLIPSE;
                } else if (btn == crossRbtn) {
                    return Imgproc.MORPH_CROSS;
                }
            }
        }
        return Imgproc.MORPH_RECT;
    }

    void getNewImage() {
        Mat m = null;
        int sh = getElementShape();
        Enumeration<AbstractButton> elements = typeGroup.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton btn = elements.nextElement();
            if (btn.isSelected()) {
                if (btn == additionRbtn) {
                    m = processor.addition(currentMat, sizeSlider.getValue(), sh);
                } else if (btn == boundaryExtractionRbtn) {
                    m = processor.boundaryExtraction(currentMat, sizeSlider.getValue(), sh);
                } else if (btn == invertRbtn) {
                    m = processor.invert(currentMat);
                } else if (btn == erodeRbtn) {
                    m = processor.erode(currentMat, sizeSlider.getValue(), sh);
                } else if (btn == dilateRbtn) {
                    m = processor.dilate(currentMat, sizeSlider.getValue(), sh);
                } else if (btn == openRbtn) {
                    m = processor.open(currentMat, sizeSlider.getValue(), sh);
                } else if (btn == closeRbtn) {
                    m = processor.close(currentMat, sizeSlider.getValue(), sh);
                }
            }
        }
        currentMat = m;
        Image image = ImageProcessor.toBufferedImage(m);
        imageLbl.setIcon(new ImageIcon(image));
    }

    public void openPicture(String filePath) {
        ImageIcon icon = new ImageIcon(filePath);
        imageLbl.setIcon(icon);
        IMAGE_PATH = filePath;
        currentMat = Imgcodecs.imread(IMAGE_PATH);
    }
}









