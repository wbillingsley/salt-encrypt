name := "salt-encrypt"

organization := "com.wbillingsley"

version := "0.1.0-RC1"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

crossScalaVersions := Seq("2.10.3")

parallelExecution in Test := false

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

licenses in ThisBuild := Seq("MIT" -> url("http://www.opensource.org/licenses/mit-license.php"))

homepage in ThisBuild := Some(url("http://github.com/wbillingsley/salt-encrypt"))

publishMavenStyle in ThisBuild := true

// Bintray settings for publishing releases
seq(bintrayPublishSettings:_*)

publishTo in ThisBuild := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    publishTo.value
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

