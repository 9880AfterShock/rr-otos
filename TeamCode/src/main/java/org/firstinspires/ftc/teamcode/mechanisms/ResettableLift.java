package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import java.util.LinkedList;
import java.util.Queue;

public class ResettableLift {
    private DcMotorEx liftMotor;
    private DigitalChannel liftTouchSensor;
    private LiftState state = LiftState.DOWN;
    private Queue<LiftMessage> messageQueue = new LinkedList<>();
    private int targetPos = 0;
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
        if (message == null) {message = LiftMessage.NONE;}
        switch (message) {
            case STOP:
                
                break;
            case RESET:

                break;
            case RAISE:

                break;
            case LOWER:

                break;
            case NONE:

                break;
        }
    }
}
