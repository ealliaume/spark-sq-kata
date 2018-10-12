import aether.AetherKeys._

logLevel in aetherDeploy := Level.Info

name := "spark-kata"
version := "0.1-SNAPSHOT"
organization := "au.com.octo"
scalaVersion := "2.11.8"
publishMavenStyle := true
test in assembly := {}
packAutoSettings
coursierMaxIterations := 100

parallelExecution in Test := false

resolvers += Resolver.mavenLocal
resolvers += "atlassian" at "https://packages.atlassian.com/maven/repository/public"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

val specs2Version = "3.9.0"
val sparkVersion = "2.2.1"
val hadoopVersion = "2.8.3"
val jacksonVersion = "2.6.7"
val slf4jVersion = "1.7.25"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % "2.9.5",
  "org.specs2" %% "specs2-core" % specs2Version % Test,

  "org.apache.spark" % "spark-core_2.11" % sparkVersion % Provided,
  "org.apache.spark" % "spark-sql_2.11" % sparkVersion % Provided,
  "org.apache.spark" % "spark-hive_2.11" % sparkVersion % Provided,
  "org.apache.hadoop" % "hadoop-aws" % hadoopVersion % Provided,
  "org.apache.hadoop" % "hadoop-client" % hadoopVersion % Provided,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion % Provided,
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion % Provided,
  "org.slf4j" % "slf4j-api" % slf4jVersion % Provided,
  "org.slf4j" % "slf4j-log4j12" % slf4jVersion % Provided,
  //Forcing this as it is the only one supported by Hadoop 2.8.3
  "com.amazonaws" % "aws-java-sdk" % "1.11.297" % Provided,
  "net.java.dev.jets3t" % "jets3t" % "0.9.3" % Provided
)
