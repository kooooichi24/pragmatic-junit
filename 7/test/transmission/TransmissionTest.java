package transmission;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;

public class TransmissionTest {

    private Transmission transmission;
    private Car car;

    @Before
    public void create() {
        car = new Car();
        transmission = new Transmission(car);
    }

    // 加速を始めたらDの状態を保つ
    @Test
    public void remainsInDriveAfterAcceleration() {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(35);

        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }

    // 走行中はPへのシフトチェンジを無視する
    @Test
    public void ignoresShiftToPartWhileInDrive() {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(30);

        transmission.shift(Gear.PARK);

        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }

    // 停止中はPにシフトチェンジできる
    @Test
    public void allowsShiftToParkWhenNotMoving() {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(30);
        car.brakeToStop();

        transmission.shift(Gear.PARK);

        assertThat(transmission.getGear(), equalTo(Gear.PARK));
    }
}