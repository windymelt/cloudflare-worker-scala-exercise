package app

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import com.indoorvivants.cloudflare.cloudflareWorkersTypes.{global, *}
import com.indoorvivants.cloudflare.std

import scala.scalajs.js.annotation.JSExportTopLevel

type Params = std.Record[String, scala.Any]


@JSExportTopLevel(name = "onRequestGet", moduleID = "index")
def index(context: EventContext[Any, String, Params]) =
  val r = global.Response(
    s"""<!DOCTYPE html>
     |<html>
     |<head>
     |    <title>Cloudflare Workers + Scala 3</title>
     |    <meta charset="utf-8"/>
     |    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/milligram/1.4.1/milligram.min.css">
     |</head>
     |<body>
     |<div class="container">
     |<h1>Hello, Cloudflare Workers from Scala 3!</h1>
     |<p>Build info: <code>${buildinfo.BuildInfo}</code></p>
     |<p>Inspired by <a href="https://blog.indoorvivants.com/2022-02-14-cloudflare-functions-with-scalajs" target="_blank">Cloudflare Functions with Scala.js</a></p>
     |</div>
     |<hr />
     |<address>
     |<a href="https://github.com/windymelt/cloudflare-worker-scala-exercise" target="_blank">windymelt/cloudflare-worker-scala-exercise</a>
     |</address>
     |</body>
     |</html>""".stripMargin)
  r.headers.set("content-type", "text/html")
  r

@JSExportTopLevel(name = "onRequest", moduleID = "request_headers")
def request_headers(context: EventContext[Any, String, Params]) =
  val str = StringBuilder()
  context.request.headers.forEach { (_, value, key, _) =>
    str.append(s"Keys: $key, value: $value\n")
  }
  global.Response("hello, world. Your request comes with \n" + str.result)

@JSExportTopLevel(name = "onRequestGet", moduleID = "request_method")
def request_method(context: EventContext[Any, String, Params]) =
  global.Response("Your request came via " + context.request.method)

@JSExportTopLevel(name = "onRequestGet", moduleID = "request_json")
def request_json(context: EventContext[Any, String, Params]) =
  val r = global.Response(js.JSON.stringify(js.Dictionary("what" -> "json", "hello" -> "world")))
  r.headers.set("content-type", "application/json")
  r