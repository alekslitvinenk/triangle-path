ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "triangles-path"
  )

addCommandAlias(
  "build",
  """|;
     |clean;
     |assembly;
  """.stripMargin)