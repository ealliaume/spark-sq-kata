name := "spark-kata"
version := "0.1-SNAPSHOT"
organization := "au.com.octo"
scalaVersion := "2.11.12"

test in assembly := {}

parallelExecution in Test := false

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.1" % Provided
libraryDependencies += "org.specs2" %% "specs2-core" % "3.9.0" % Test

dependencyOverrides += "org.slf4j" % "slf4j-api" % "1.7.25"
dependencyOverrides += "org.apache.hadoop" % "hadoop-client" % "2.8.4"
dependencyOverrides += "commons-net" % "commons-net" % "3.6"