package com.cgev.openkrypton.morphologyMy;

import javafx.embed.swing.SwingFXUtils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ImageProcessor {

    public Mat addition(Mat input, int elementSize, int elementShape) {
        return null;
    }

    public Mat boundaryExtraction(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat(input.rows(), input.cols(), CvType.CV_8UC1);
        Mat m = dilate(input, elementSize, elementShape);
        Core.subtract(m, input, outputImage);
        return outputImage;
    }

    public Mat invert(Mat input) {
        Mat outputImage = new Mat();
        Mat invertColorMatrix = new Mat(input.rows(), input.cols(), input.type(), new Scalar(255, 255, 255));
        Core.subtract(invertColorMatrix, input, outputImage);
        return outputImage;
    }

    public Mat erode(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.erode(input, outputImage, element);
        return outputImage;
    }

    public Mat dilate(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.dilate(input, outputImage, element);
        return outputImage;
    }

    public Mat open(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.morphologyEx(input, outputImage, Imgproc.MORPH_OPEN,
                element);
        return outputImage;
    }

    public Mat close(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.morphologyEx(input, outputImage, Imgproc.MORPH_CLOSE,
                element);
        return outputImage;
    }

    private Mat getKernelFromShape(int elementSize, int elementShape) {
        return Imgproc.getStructuringElement(elementShape, new
                Size(elementSize * 2 + 1, elementSize * 2 + 1), new Point(elementSize,
                elementSize));
    }

    public static Image toBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer); // get all the pixels
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.
                rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().
                getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }

    //dont work
    public static Mat bufferedImage2Mat(BufferedImage in) {
        byte[] data = ((DataBufferByte) in.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(in.getHeight(), in.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data);
        return mat;
    }

    /**
     * don't support yet
     */
    public static Mat image2Mat(javafx.scene.image.Image image) {
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        return bufferedImage2Mat(bImage);
    }
}
