package com.cyclone.ipmi.command.oem.fujitsu.common.power

import akka.util.{ByteString, ByteStringBuilder}
import com.cyclone.ipmi.codec._
import com.cyclone.ipmi.command.oem.IanaEnterpriseNumber
import com.cyclone.ipmi.command.{CommandCode, StatusCodeTranslator}
import com.cyclone.ipmi.protocol.packet.{CommandResultCodec, IpmiCommandResult, IpmiStandardCommand, NetworkFunction}
import org.joda.time.Instant

/**
  * SetNextPowerOnTime command and response
  */
object SetNextPowerOnTime {

  object CommandResult {
    implicit val decoder: Decoder[CommandResult] = new Decoder[CommandResult] {

      def decode(data: ByteString): CommandResult = {
        //        val iterator = data.iterator
        //        val is = iterator.asInputStream

        //val ianaNumber = is.read(3).as[IanaEnterpriseNumber] // IANA number LSB First (Should always be 80 28 00 for Fujitsu

        CommandResult()
      }
    }

    implicit val statusCodeTranslator: StatusCodeTranslator[CommandResult] =
      StatusCodeTranslator[CommandResult]()
  }

  case class CommandResult() extends IpmiCommandResult

  object Command {
    implicit val coder: Coder[Command] = new Coder[Command] {

      def encode(request: Command): ByteString = {
        import request._

        val b = new ByteStringBuilder

        val fujitsu: IanaEnterpriseNumber = IanaEnterpriseNumber.Fujitsu
        b ++= fujitsu.toBin

        b += 0x20.toByte // Command
        b += 0x00.toByte // Object Id
        b += 0x00.toByte
        b += 0x00.toByte // Value Id
        b += 0x04.toByte // Data length

        // Fixme - Check that instant is in unix specific format as that is what is expected here
        b ++= powerOnTime.toBin

        b.result()
      }
    }

    implicit val codec: CommandResultCodec[Command, CommandResult] =
      CommandResultCodec.commandResultCodecFor[Command, CommandResult]

  }

  case class Command(powerOnTime: Instant) extends IpmiStandardCommand {

    val networkFunction: NetworkFunction = NetworkFunction.FujitsuGroupRequest
    val commandCode = CommandCode(0x01)
  }

}
