package com.cgev.openkrypton.cascadeVideo;

import com.cgev.openkrypton.cascadeVideo.utils.ImageProcessor;
import javafx.util.Pair;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;

public class App {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private JPanel panel;
    private JLabel imageLabel;
    private CascadeClassifier faceDetector;

    public static void main(String[] args) {
        App app = new App();
        app.initGUI();

        app.loadCascade();

        app.runMainLoop();


    }

    private void loadCascade() {
        String cascadePath = "src/main/resources/cascades/lbpcascade_frontalface.xml";
        faceDetector = new CascadeClassifier(cascadePath);
    }

    private void initGUI() {
        panel = new JPanel();//("Camera Input Example");
        //panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(400, 400);
        imageLabel = new JLabel();
        panel.add(imageLabel);
        panel.setVisible(true);
    }

    private void runMainLoop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageProcessor imageProcessor = new ImageProcessor();
                Mat webcamMatImage = new Mat();
                Image tempImage;
                VideoCapture capture = new VideoCapture(0);
                capture.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, 640);
                capture.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, 480);

                if (capture.isOpened()) {
                    while (true) {
                        capture.read(webcamMatImage);
                        if (!webcamMatImage.empty()) {
                            detectAndDrawFace(webcamMatImage);
                            tempImage = imageProcessor.toBufferedImage(webcamMatImage);

                            ImageIcon imageIcon = new ImageIcon(tempImage, "Captured video");
                            imageLabel.setIcon(imageIcon);
                            //frame.pack();  //this will resize the window to fit the image
                        } else {
                            System.out.println(" -- Frame not captured -- Break!");
                            break;
                        }
                    }
                } else {
                    System.out.println("Couldn't open capture.");
                }
            }
        }).start();
    }

    private void detectAndDrawFace(Mat image) {
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections, 1.1, 7, 0, new Size(250, 40), new Size());
        // Draw a bounding box around each face.
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
    }

    /**
     * Detect people using Pedestrian detection algorithm
     * @param mat
     */
    private void detectPeople(Mat mat) {
        Mat grayMat = new Mat();

        //Converting the image to grayscale
        Imgproc.cvtColor(mat, grayMat, Imgproc.COLOR_BGR2GRAY);

        HOGDescriptor hog = new HOGDescriptor();
        hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());

        MatOfRect faces = new MatOfRect();
        MatOfDouble weights = new MatOfDouble();

        hog.detectMultiScale(grayMat, faces, weights);
        Rect[] facesArray = faces.toArray();
        for (int i = 0; i < facesArray.length; i++)
            Imgproc.rectangle(mat, facesArray[i].tl(), facesArray[i].br(), new Scalar(100), 3);

        //Converting Mat back to Bitmap
    }

    public static Pair<String, JPanel> create() throws Exception {
        App app = new App();
        app.initGUI();

        app.loadCascade();

        app.runMainLoop();
        return new Pair<>("Cascade", app.panel);
    }
}
