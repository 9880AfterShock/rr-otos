package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class MisumiSlides {
    public static DcMotor lift;
    public static double pos = 0.0;
    public static double currentSpeed = 0.0;
    public static double speed = 0.1;
    public static final double encoderTicks = 384.5;
    public static double minPos = 0.0;
    public static double maxPos = 0.0;
    public static OpMode opmode;
    public static DcMotor.RunMode resetMode = DcMotor.RunMode.STOP_AND_RESET_ENCODER;
    public static DcMotor.RunMode runMode = DcMotor.RunMode.RUN_TO_POSITION;

    public static void initLift(OpMode opmode){
        pos = 0.0;
        lift = opmode.hardwareMap.get(DcMotorEx.class, "extension"); //config name
        lift.setTargetPosition((int) (pos * encoderTicks));
        lift.setMode(resetMode);
        lift.setMode(runMode);
        MisumiSlides.opmode = opmode;
    }

    public static void updateLift(){



        opmode.telemetry.addData("Pos", pos);
    }



}
