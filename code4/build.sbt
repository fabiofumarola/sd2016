
name := "akka.js_demo"

scalaVersion in ThisBuild := "2.11.8"
scalacOptions in ThisBuild := Seq("-feature", "-language:_", "-deprecation")

lazy val root = project.in(file(".")).
  aggregate(demoJS, demoJVM)

lazy val demo = crossProject.in(file(".")).
  settings(
    name := "demo",
    fork in run := true
  ).
  jvmSettings(
    resolvers += "Akka Snapshots" at " http://repo.akka.io/snapshots/",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.4.4",
      "com.typesafe.akka" %% "akka-http-experimental" % "2.4.4"
    )
  ).
  jsSettings(
    resolvers += Resolver.sonatypeRepo("snapshots"),
    libraryDependencies ++= Seq(
      "eu.unicredit" %%% "akkajsactor" % "0.1.1-SNAPSHOT",
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "com.lihaoyi" %%% "scalatags" % "0.5.4"
    ),
    persistLauncher in Compile := true,
    scalaJSStage in Global := FastOptStage,
    scalaJSUseRhino in Global := false
  )

lazy val demoJVM = demo.jvm
lazy val demoJS = demo.js

cancelable in Global := true
