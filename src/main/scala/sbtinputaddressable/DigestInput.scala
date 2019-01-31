package sbtinputaddressable

import java.security.MessageDigest

import sbt.IO

import java.io.File

object DigestInput {
  def digest(algorithm: String = "SHA-256")(inputFiles: Seq[File]) = {
    val md: MessageDigest = MessageDigest.getInstance(algorithm)

    inputFiles.foreach(f => {
      val input = IO.readBytes(f)
      md.update(input)
    })
    hex(md.digest())
  }

  /**
    * Turn the bytes into a nice hex-string,
    * from https://github.com/josephearl/sbt-verify/blob/8e1e2e633ed807d8cb8b9367d777125cce31b4de/src/main/scala/uk/co/josephearl/sbt/verify/HashAlgorithm.scala
   */
  private def hex(b: Array[Byte]): String = {
    b.map("%02X" format _).mkString.toLowerCase
  }
}
