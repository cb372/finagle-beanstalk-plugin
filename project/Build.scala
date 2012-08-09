import sbt._
import Keys._

object MinimalBuild extends Build {
  
  lazy val buildVersion =  "0.0.1-SNAPSHOT"
  lazy val playVersion = "2.0.3"
  lazy val typesafeRepo = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  
  lazy val root = Project(id = "finagle-beanstalk-plugin", base = file("."), settings = Project.defaultSettings).settings(
    version := buildVersion,
    organization := "com.github.cb372",
    resolvers += typesafeRepo,
    resolvers += "Chris Birchall's repo (snapshots)" at "http://cb372.github.com/m2/snapshots/",
    publishTo := Some(Resolver.file("snapshots", new File("../cb372.github.com/m2/snapshots"))),
    javacOptions += "-Xlint:unchecked",
    libraryDependencies += "play" %% "play" % playVersion, 
    libraryDependencies += "com.github.cb372" % "finagle-beanstalk_2.9.2" % "0.0.1-SNAPSHOT"
  )
}
