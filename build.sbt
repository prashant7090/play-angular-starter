name := """play-angular-seed"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.38",
  "com.typesafe.play" %% "play-json" % "2.3.4",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

enablePlugins(PlayEbean)