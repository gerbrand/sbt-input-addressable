lazy val root = (project in file("."))
  .settings(
    sbtPlugin := true,
    organization := "nl.software-creation",
    name := "sbt-input-addressable"
  )