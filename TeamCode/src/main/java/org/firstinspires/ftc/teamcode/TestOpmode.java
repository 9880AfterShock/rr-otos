package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.MisumiSlides;


public class TestOpmode extends LinearOpMode {

    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        //Init functions
        MisumiSlides.initLift(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            //Loop Functions
            MisumiSlides.updateLift();

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
