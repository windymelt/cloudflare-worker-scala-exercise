package app

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import com.indoorvivants.cloudflare.cloudflareWorkersTypes.{global, *}
import com.indoorvivants.cloudflare.std

import scala.scalajs.js.annotation.JSExportTopLevel

type Params = std.Record[String, scala.Any]

@JSExportTopLevel(name = "onRequestGet", moduleID = "index")
def index(context: EventContext[Any, String, Params]) =
  val r = global.Response(s"""<!DOCTYPE html>
     |<html>
     |<head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# article: http://ogp.me/ns/article#">
     |    <title>Cloudflare Workers + Scala 3</title>
     |    <meta charset="utf-8"/>
     |    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
     |    <meta property="og:title" content="Cloudflare Workers + Scala 3" />
     |    <meta property="og:type" content="website" />
     |    <meta property="og:url" content="https://cloudflare-worker-scala-exercise.pages.dev/" />
     |    <meta property="og:image" content="https://ogi.3qe.us/?title=Cloudflare%20Workers%20%2B%20Scala%203" />
     |    <meta name="description" content="Cloudflare Workers + Scala 3" />
     |    <meta name="twitter:card" content="summary_large_image" />
     |    <meta name="twitter:site" content="@windymelt" />
     |    <meta name="twitter:creator" content="@windymelt" />
     |    <meta name="twitter:title" content="Cloudflare Workers + Scala 3" />
     |    <meta name="twitter:description" content="Cloudflare Workers + Scala 3" />
     |    <meta name="twitter:image" content="https://ogi.3qe.us/?title=Cloudflare%20Workers%20%2B%20Scala%203" />
     |    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
     |    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css">
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
  val r = global.Response(
    js.JSON.stringify(js.Dictionary("what" -> "json", "hello" -> "world"))
  )
  r.headers.set("content-type", "application/json")
  r
