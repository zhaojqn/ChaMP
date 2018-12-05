package com.cyclone.ipmi.command.chassis

import akka.util.{ByteString, ByteStringBuilder}
import com.cyclone.ipmi.codec._
import com.cyclone.ipmi.command.{CommandCode, StatusCodeTranslator}
import com.cyclone.ipmi.protocol.packet.{CommandResultCodec, IpmiCommandResult, IpmiStandardCommand, NetworkFunction}

/**
  * Set Power Cycle Interval command and response
  */
object SetPowerCycleInterval {

  object CommandResult {
    implicit val decoder: Decoder[CommandResult] = new Decoder[CommandResult] {

      def decode(data: ByteString): CommandResult = {

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

        b += powerCycleInterval

        b.result()
      }
    }

    implicit val codec: CommandResultCodec[Command, CommandResult] =
      CommandResultCodec.commandResultCodecFor[Command, CommandResult]

  }

  case class Command(powerCycleInterval: Byte = 0x00) extends IpmiStandardCommand {

    val networkFunction: NetworkFunction = NetworkFunction.ChassisRequest
    val commandCode = CommandCode(0x0B)
  }

}
