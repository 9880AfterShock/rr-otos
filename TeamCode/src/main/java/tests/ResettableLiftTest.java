import static org.junit.Assert.assertEquals;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.fakes.FakeDcMotorEx;
import org.firstinspires.ftc.teamcode.fakes.FakeDigitalChannel;
import org.firstinspires.ftc.teamcode.mechanisms.LiftMessage;
import org.firstinspires.ftc.teamcode.mechanisms.LiftState;
import org.firstinspires.ftc.teamcode.mechanisms.ResettableLift;
import org.junit.Before;
import org.junit.Test;

public class ResettableLiftTest {
    FakeDcMotorEx motorMock;
    FakeDigitalChannel touchMock;
    ResettableLift lift;

    @Before
    public void setup() {
        motorMock = new FakeDcMotorEx();
        touchMock = new FakeDigitalChannel();
        lift = new ResettableLift(motorMock, touchMock,10);
    }

    @Test
    public void move_up() {
        // set start position
        motorMock.setCurrentPosition(86);
        lift.addMessage(LiftMessage.RAISE);
        lift.update();
        // test if its state is correct
        assertEquals(LiftState.UP, lift.getState());
        lift.update();

        // test whether it wants to move up
        assertEquals(96, motorMock.getTargetPosition());

        // move it up
        motorMock.setCurrentPosition(motorMock.getTargetPosition());
        lift.update();
        // test if its state is correct
        assertEquals(LiftState.UP, lift.getState());

        // test if it continues wanting to move up
        assertEquals(106, motorMock.getTargetPosition());

        // stop it
        lift.addMessage(LiftMessage.STOP);
        lift.update();
        // test if its state is correct
        assertEquals(LiftState.STOPPED, lift.getState());
        lift.update();

        // test whether it stops where it is
        assertEquals(96, motorMock.getTargetPosition());
    }

    @Test
    public void move_down() {
        // set start position
        motorMock.setCurrentPosition(86);
        lift.addMessage(LiftMessage.LOWER);
        lift.update();

        // test if its state is correct
        assertEquals(LiftState.DOWN, lift.getState());
        lift.update();

        // test whether it wants to move down
        assertEquals(76, motorMock.getTargetPosition());

        // move it down
        motorMock.setCurrentPosition(motorMock.getTargetPosition());
        lift.update();
        // test if its state is correct
        assertEquals(LiftState.DOWN, lift.getState());

        // test if it continues wanting to move down
        assertEquals(66, motorMock.getTargetPosition());

        // stop it
        lift.addMessage(LiftMessage.STOP);
        lift.update();
        // test if its state is correct
        assertEquals(LiftState.STOPPED, lift.getState());
        lift.update();

        // test whether it stops where it is
        assertEquals(76, motorMock.getTargetPosition());
    }

    @Test
    public void reset() {
        // reset it
        lift.addMessage(LiftMessage.RESET);
        lift.update();
        // test if its state is correct
        assertEquals(LiftState.RESETTING, lift.getState());
        lift.update();

        // is it going down?
        assertEquals(-0.25,motorMock.getPower(),0.0);

        //make time pass

        for (int i = 0; i < 100; i++) {
            lift.update();
        }

        // is it still going down?
        assertEquals(-0.25,motorMock.getPower(),0.0);

        // make it hit the sensor
        touchMock.setState(false);
        lift.update();

        // did it stop?
        assertEquals(LiftState.STOPPED, lift.getState());

        // was the encoder reset?
        assertEquals(0, motorMock.getCurrentPosition());
    }
}
