package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import java.util.LinkedList;
import java.util.Queue;

public class ResettableLift {
    private final DcMotorEx liftMotor;
    private final DigitalChannel liftTouchSensor;
    private LiftState state = LiftState.DOWN;
    private final Queue<LiftMessage> messageQueue = new LinkedList<>();

    public ResettableLift(OpMode om) {
        this.liftMotor = om.hardwareMap.get(DcMotorEx.class,"lift");
        this.liftTouchSensor = om.hardwareMap.get(DigitalChannel.class,"lift_touch");
        this.liftTouchSensor.setMode(DigitalChannel.Mode.INPUT);
    }

    public void addMessage(LiftMessage message) {
        messageQueue.add(message);
    }

    public void update() {
        LiftMessage message = messageQueue.poll();
        if (message == null || state == LiftState.RESETTING) {message = LiftMessage.NONE;}
        switch (message) {
            case STOP:
                state = LiftState.STOPPED;
                break;
            case RESET:
                state = LiftState.RESETTING;
                break;
            case RAISE:
                state = LiftState.UP;
                break;
            case LOWER:
                state = LiftState.DOWN;
                break;
            case NONE:
                int speed = 10;
                switch (state) {
                    case STOPPED:
                        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        liftMotor.setTargetPosition(liftMotor.getCurrentPosition());
                        break;
                    case UP:
                        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        liftMotor.setTargetPosition(liftMotor.getCurrentPosition()+ speed);
                        break;
                    case DOWN:
                        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        liftMotor.setTargetPosition(liftMotor.getCurrentPosition()- speed);
                        break;
                    case RESETTING:
                        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                        liftMotor.setPower(-1);
                        if (!liftTouchSensor.getState()) {
                            liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        }
                }
                break;
        }
    }
}
