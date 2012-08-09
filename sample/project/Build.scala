import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "MyApp"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
       "com.github.cb372" %% "finagle-beanstalk-plugin" % "0.0.1-SNAPSHOT"
      
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      //resolvers += Resolver.file("Local snapshots", new File("/Users/chris/code/cb372.github.com/m2/snapshots/"))
      resolvers += "Local snapshots" at "file:///Users/chris/code/cb372.github.com/m2/snapshots/"
      //resolvers += "Chris Birchall's repo (snapshots)" at "http://cb372.github.com/m2/snapshots/"
    )

}
