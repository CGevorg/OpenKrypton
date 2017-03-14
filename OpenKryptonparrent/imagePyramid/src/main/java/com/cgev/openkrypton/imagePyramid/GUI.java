package com.cgev.openkrypton.imagePyramid;

import com.cgev.openkrypton.imagePyramid.utils.ImageProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JLabel imageView;
    private String windowName;
    private Mat image, originalImage;
    private JPanel panel;

    private final ImageProcessor imageProcessor = new ImageProcessor();

    public GUI(String windowName, Mat newImage) {
        super();
        this.windowName = windowName;
        this.image = newImage;
        this.originalImage = newImage.clone();
    }

    public void init() {
        initGUI();
    }

    private void initGUI() {
        JPanel frame = createJFrame(windowName);

        updateView(image);

        frame.setVisible(true);
        this.panel = frame;
    }


    private JPanel createJFrame(String windowName) {
        JPanel frame = new JPanel();//(windowName);
        //frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        setupButton(frame);
        setupImage(frame);

        return frame;
    }

    private void setupImage(JPanel frame) {
        imageView = new JLabel();
        imageView.setHorizontalAlignment(SwingConstants.CENTER);

        final JScrollPane imageScrollPane = new JScrollPane(imageView);
        imageScrollPane.setPreferredSize(new Dimension(640, 480));
        frame.add(imageScrollPane);
    }

    private void setupButton(JPanel frame) {
        final JPanel buttonsPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        buttonsPanel.setLayout(flowLayout);

        JButton restoreButton = new JButton("Restore");
        restoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                image = originalImage.clone();
                updateView(originalImage);
            }
        });


        JButton pyramidDown = new JButton("Pyramid Down");
        pyramidDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Imgproc.pyrDown(image, image);
                updateView(image);
            }
        });

        JButton pyramidUp = new JButton("Pyramid Up");
        pyramidUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Imgproc.pyrUp(image, image);
                updateView(image);
            }
        });

        JButton laplacian = new JButton("Laplacian");
        laplacian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Mat gp1 = new Mat();
                Imgproc.pyrDown(image, gp1);
                Imgproc.pyrUp(gp1, gp1);
                Core.subtract(image, gp1, gp1);
                updateView(gp1);
            }
        });


        buttonsPanel.add(restoreButton);
        buttonsPanel.add(pyramidDown);
        buttonsPanel.add(pyramidUp);
        buttonsPanel.add(laplacian);
        frame.add(buttonsPanel);

    }

    public JPanel getPanel() {
        return panel;
    }

    private void updateView(Mat newMat) {
        Image outputImage = imageProcessor.toBufferedImage(newMat);
        imageView.setIcon(new ImageIcon(outputImage));
    }

}
