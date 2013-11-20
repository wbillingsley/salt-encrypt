name := "salt-encrypt"

organization := "com.wbillingsley"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

crossScalaVersions := Seq("2.10.2")

parallelExecution in Test := false

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

licenses in ThisBuild := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php"))

homepage in ThisBuild := Some(url("http://wbillingsley.github.io/salt-encrypt"))

publishMavenStyle in ThisBuild := true

publishTo in ThisBuild <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra in ThisBuild := (
  <scm>
    <url>git@github.com:wbillingsley/salt-encrypt.git</url>
    <connection>scm:git:git@github.com:wbillingsley/salt-encrypt.git</connection>
  </scm>
  <developers>
    <developer>
      <id>wbillingsley</id>
      <name>William Billingsley</name>
      <url>http://www.wbillingsley.com</url>
    </developer>
  </developers>
)

