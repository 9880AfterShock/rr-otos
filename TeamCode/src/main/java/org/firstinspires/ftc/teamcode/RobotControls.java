package org.firstinspires.ftc.teamcode;

import androidx.annotation.Nullable;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mechanisms.LiftMessage;
import org.firstinspires.ftc.teamcode.mechanisms.ResettableLift;

public class RobotControls {
    private static Gamepad mechPlayer;
    private static Gamepad movePlayer;
    private static ResettableLift misumiLift;
    public static void initControls(OpMode opmode, @Nullable ResettableLift misumiLift){
        RobotControls.misumiLift = misumiLift;
        mechPlayer = opmode.gamepad1;
        movePlayer = opmode.gamepad2;
    }

    public static void updateControls() {

        // misumi lift controls
        boolean misumiUp = mechPlayer.dpadUpWasPressed();
        boolean misumiDown = mechPlayer.dpadDownWasPressed();
        boolean misumistop = mechPlayer.dpadUpWasReleased() || mechPlayer.dpadDownWasReleased();
        boolean misumiReset = mechPlayer.dpad_down && mechPlayer.backWasReleased();

        // misumi lift logic
        if (misumiLift != null) {
            LiftMessage message = null;
            if (misumiReset) {
                message = LiftMessage.RESET;
            } else if (misumiUp) {
                message = LiftMessage.RAISE;
            } else if (misumiDown) {
                message = LiftMessage.LOWER;
            } else if (misumistop) {
                message = LiftMessage.STOP;
            }
            misumiLift.addMessage(message);
        }

    }

}