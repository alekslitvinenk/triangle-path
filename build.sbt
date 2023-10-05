ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "triangles-path",
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.9.0"
  )

addCommandAlias(
  "build",
  """|;
     |clean;
     |assembly;
  """.stripMargin)