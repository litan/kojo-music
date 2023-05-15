import Dependencies._

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.3"
ThisBuild / organization := "net.kogics"
ThisBuild / organizationName := "kojo"

lazy val root = (project in file("."))
  .settings(
    name := "kojo-music",
    libraryDependencies += javaOSC,
    libraryDependencies += scalaTest % Test,
    scalacOptions ++= Seq("-feature", "-deprecation")
  )
  .enablePlugins(JavaAppPackaging)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
