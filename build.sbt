name := "salt-encrypt"

organization := "com.wbillingsley"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.0"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

crossScalaVersions := Seq("2.10.0")

publishTo <<= version { (v: String) =>
  val localm = "/Users/wbillingsley/sourcecode/external/repos/mymavenrepo/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some(Resolver.file("snapshots", new File(localm + "snapshots")))
  else
    Some(Resolver.file("releases", new File(localm + "releases")))
}

parallelExecution in Test := false

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

