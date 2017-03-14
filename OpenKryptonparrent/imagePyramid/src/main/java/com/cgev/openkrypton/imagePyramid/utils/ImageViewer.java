package com.cgev.openkrypton.imagePyramid.utils;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;

public class ImageViewer {
	private JLabel imageView;
	
	public void show(Mat image){
		show(image, "");
	}

	public void show(Mat image,String windowName){
		setSystemLookAndFeel();
		
		JFrame frame = createJFrame(windowName);
        ImageProcessor imageProcessor = new ImageProcessor();
        Image loadedImage = imageProcessor.toBufferedImage(image);
        imageView.setIcon(new ImageIcon(loadedImage));
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
	}

	private JFrame createJFrame(String windowName) {
		JFrame frame = new JFrame(windowName);
		imageView = new JLabel();
		final JScrollPane imageScrollPane = new JScrollPane(imageView);
        imageScrollPane.setPreferredSize(new Dimension(640, 480));
        frame.add(imageScrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		return frame;
	}

	private void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
}
