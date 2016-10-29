package com.krypton.app;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Created by employee on 10/29/16.
 */
public class ImageProcessor {

    public Mat erode(Mat input, int elementSize, int elementShape){
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.erode(input,outputImage, element);
        return outputImage;
    }

    public Mat dilate(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.dilate(input,outputImage, element);
        return outputImage;
    }

    public Mat open(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.morphologyEx(input,outputImage, Imgproc.MORPH_OPEN,
                element);
        return outputImage;
    }

    public Mat close(Mat input, int elementSize, int elementShape) {
        Mat outputImage = new Mat();
        Mat element = getKernelFromShape(elementSize, elementShape);
        Imgproc.morphologyEx(input,outputImage, Imgproc.MORPH_CLOSE,
                element);
        return outputImage;
    }

    private Mat getKernelFromShape(int elementSize, int elementShape) {
        return Imgproc.getStructuringElement(elementShape, new
                Size(elementSize*2+1, elementSize*2+1), new Point(elementSize,
                elementSize) );
    }

}
