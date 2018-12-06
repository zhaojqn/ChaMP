package com.cyclone.ipmi.tool.command

import com.cyclone.akka.ActorSystemShutdown
import com.cyclone.ipmi.sdr._
import com.cyclone.ipmi.tool.command.SensorTool.SensorReading
import org.scalatest.{Inside, Matchers, WordSpecLike}

/**
  * Tests for [[SensorTool]]
  */
class SensorToolCommandTestEx
  extends BaseToolCommandTest
    with WordSpecLike
    with Matchers
    with Inside
    with ActorSystemShutdown {

  "sensor command" must {
    "get all sensor records when required" in new Fixture {
      val records = executeCommand(SensorTool.Command(SdrFilter.All)).records

      records.size should be > 1
    }

    "get a correct specific sensor record when required" in new Fixture {
      val sensorReading = executeCommand(SensorTool.Command(SdrFilter.BySensorIds(SensorId("-12 V")))).records.head

      inside(sensorReading) {
        case SensorReading(id, Some(analogReadings), _) =>
          analogReadings.sensorValue.value should (be > -14.0 and be < -11.0)
          analogReadings.sensorValue.sensorUnits shouldBe SensorUnits.Simple(SensorUnit.Volts)

          analogReadings.thresholdComparisons(ThresholdComparison.UpperNonRecoverable).value shouldBe -10.546
      }
    }

  }
}