package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.ResettableLift;

@TeleOp(name = "Misumi Advanced")
public class StateMachineLiftOpMode extends LinearOpMode {
    ResettableLift misumi_lift;
    ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        misumi_lift = new ResettableLift(this,"misumi_lift","misumi_touch",10);
        RobotControls.initControls(this,misumi_lift);
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            RobotControls.updateControls();
            misumi_lift.update();
        }
    }
}
