val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin, BuildInfoPlugin)
  .settings(
    name := "cloudflare-worker-scala-exercise",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
    },

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies +=
      "com.indoorvivants.cloudflare" %%% "worker-types" % "3.3.0",

    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "buildinfo"
  )

lazy val buildWorkers = taskKey[Unit]("Copy Scala.js output to the ./function folder")

buildWorkers := {
  // where Scala.js puts the generated .js files
  val output = (Compile / fastLinkJS / scalaJSLinkerOutputDirectory).value
  // trigger (if necessary) JS compilation
  val _      = (Compile / fastLinkJS).value

  // where (relative to root of our build) we want to copy them
  val destination = (ThisBuild / baseDirectory).value / "functions"

  // access SBT's logger, for ease of debugging
  val log = streams.value.log

  // .js files produced by Scala.js
  val filesToCopy = IO.listFiles(output).filter(_.ext == "js")

  if(destination.exists()) {
    // .js files already at the destination
    val filesToDelete = IO.listFiles(destination).filter(_.ext == "js")
    // delete stale .js files
    filesToDelete.foreach {f =>
      log.debug(s"Deleting $f")
    }
  }

  // copy new .js files to destination
  filesToCopy.foreach {from =>
    val to = destination / from.name
    log.debug(s"Copying $from to $to")
    IO.copyFile(from, to)
  }
}