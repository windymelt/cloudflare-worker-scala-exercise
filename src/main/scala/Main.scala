package app

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import com.indoorvivants.cloudflare.cloudflareWorkersTypes.{global, *}
import com.indoorvivants.cloudflare.std

import scala.scalajs.js.annotation.JSExportTopLevel

type Params = std.Record[String, scala.Any]


@JSExportTopLevel(name = "onRequestGet", moduleID = "index")
def index(context: EventContext[Any, String, Params]) =
  global.Response(s"Hello, Cloudflare Workers from Scala 3! Build info: ${buildinfo.BuildInfo}")

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
  global.Response(js.JSON.stringify(js.Dictionary("what" -> "json", "hello" -> "world")))