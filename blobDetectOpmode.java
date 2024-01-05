package org.firstinspires.ftc.teamcode.openCV__autoDetect;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class blobDetectOpmode extends LinearOpMode {

    OpenCvCamera webCam;

//    WebcamName webcamName = hardwareMap.get(WebcamName.class, "webCam1");


    @Override
    public void runOpMode() throws InterruptedException {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "webCam1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webCam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        blobDetectionTest detector = new blobDetectionTest();
        webCam.setPipeline(detector);

        webCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened() {
                // Usually this is where you'll want to start streaming from the camera (see section 4) this is in the easy opencv docs
                webCam.startStreaming(1920, 1080, OpenCvCameraRotation.UPRIGHT);


            }
            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
                telemetry.addLine("the camera is dead! help!");
            }
        });

        waitForStart();

        if (blobDetectionTest.tp_zone == 1) {
            // code to be executed if expression == LEFT (left)
            telemetry.addLine("team prop's position detected is left");
        } else if (blobDetectionTest.tp_zone == 2) {
            // code to be executed if expression == 2 (mid)
            telemetry.addLine("team prop's position detected is mid");
        } else {
                // code to be executed if none of above matches (tp_zone == 3 (right))
                telemetry.addLine("team prop's position detected is right");
        }

        webCam.stopStreaming();



    }
}
