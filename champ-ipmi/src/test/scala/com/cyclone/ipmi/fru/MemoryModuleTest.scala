package com.cyclone.ipmi.fru

import akka.util.ByteString
import com.cyclone.ipmi.codec._
import org.scalatest.{Matchers, WordSpec}

/**
  * Tests for [[MemoryModule]] decoding
  */
class MemoryModuleTest extends WordSpec with Matchers {

  "a dimm fru decoder" must {
    "decode" in {
      val bs = ByteString(-110, 17, 9, 18, 72, 35, 7, 16, 81, 1, 4, 12, 32, 51, 60, 34, 60, 66, 64, 60, 30, 60, 0, -76,
        -36, -92, 1, 30, 30, 3, 7, 1, -62, 82, 50, 4, 14, 10, 13, 24, 15, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0, 2, 0, 0, 56, 56, 52, 42, 98, 119,
        97, -97, -124, 0, 0, 0, 0, 0, 31, 10, 0, 64, -64, 18, 68, -100, 48, 96, 51, 96, 27, 96, 27, 96, 27, -128, -77,
        -128, 44, 8, 7, 36, -36, 15, -76, 98, 83, 83, 51, 54, 72, 84, 70, 50, 53, 54, 55, 50, 70, 54, 54, 55, 68, 49,
        68, 51, 68, 49, -128, 44, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1)

      println(bs.as[MemoryModule])
    }
  }
}
