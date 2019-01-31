package sbtinputaddressable
import sbt._
import Keys._

object InputAddressablePlugin extends AutoPlugin {
  object autoImport {
    val algorithm = settingKey[String]("algorithm")
    val digest = taskKey[Unit]("digest all build input")
  }
  import autoImport._
  override def trigger = allRequirements
  override def requires = sbt.plugins.JvmPlugin

  override lazy val buildSettings = Seq(
    algorithm := "SHA-256",
    digest := inputAddressableDigestTask.value)

  lazy val inputAddressableDigestTask =
    Def.task {
      println(s"Going to digest using ${algorithm.value} sources: ${sources.value.map(_.getPath).mkString(", ")}")
      val hash = DigestInput.digest(algorithm = algorithm.value)(sources.value)
      println(s"Got ${hash}, now to do something with it")
    }

}