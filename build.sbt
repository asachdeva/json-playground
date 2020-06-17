import Dependencies._

ThisBuild / organization := "com.asachdeva"
ThisBuild / scalaVersion := "2.13.2"
ThisBuild / version := "0.0.1-SNAPSHOT"

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:_",
  "-unchecked",
  // "-Wunused:_",
  // "-Xfatal-warnings",
  "-Ymacro-annotations"
)

lazy val `json-playground` =
  project
    .in(file("."))
    .settings(
      name := "JSON Playground",
      addCompilerPlugin(org.typelevel.`kind-projector`),
      libraryDependencies ++= Seq(
        "org.typelevel" %% "cats-core" % "2.1.1"
      ),
      libraryDependencies ++= Seq(
        "io.circe" %% "circe-core",
        "io.circe" %% "circe-generic",
        "io.circe" %% "circe-generic-extras",
        "io.circe" %% "circe-literal",
        "io.circe" %% "circe-parser"
      ).map(_ % "0.13.0"),
      libraryDependencies ++= Seq(
        com.github.alexarchambault.`scalacheck-shapeless_1.14`,
        org.scalacheck.scalacheck,
        org.scalatest.scalatest,
        org.scalatestplus.`scalacheck-1-14`
      ).map(_ % Test),
      Compile / console / scalacOptions --= Seq(
        "-Wunused:_",
        "-Xfatal-warnings"
      ),
      Test / console / scalacOptions :=
        (Compile / console / scalacOptions).value
    )
