package asachdeva
package jsonplayground

import io.circe.{ Decoder, HCursor }
import io.circe.Json
import cats.implicits._

import io.circe._
// import io.circe.generic.auto._
// import io.circe.generic.extras._
import io.circe.literal._
import io.circe.parser._
import io.circe.syntax._

// @ConfiguredJsonCodec
final case class Data(
    name: String,
    age: Int
  ) {
  final override def toString: String =
    s"""|$productPrefix (
      |$content
      |)""".stripMargin

  private[this] def content: String =
    productElementNames
      .zip(productIterator)
      .map {
        case (name, value) => s" $name = $value"
      }
      .mkString("\n")
}

import io.circe._
object Data {
  implicit val decoder: Decoder[Data] = c =>
    (
      c.downField("name").as[String],
      c.downField("age").as[Int]
    ).mapN(apply)

  implicit val encoder: Encoder[Data] = a =>
    Json.obj(
      "name" -> Json.fromString(a.name),
      "age" -> Json.fromInt(a.age)
    )
}
// @ConfiguredJsonCodec
final case class SubData(key1: String, key2: Int)

// @ConfiguredJsonCodec
final case class GithubResponse(name: String)

// @ConfiguredJsonCodec
final case class License(
    key: String,
    name: String,
    spdxId: String,
    url: Option[String],
    nodeId: String
  )

object Main extends App {
  println("─" * 100)

  // val data = Data("Akshay", 27)

  // val dataEncodedAsJsonConvertedToString: String =
  //   // Encoder[data].apply(data).spaces2SortKeys
  //   data // ADT
  //   .asJson // JSON ADT
  //   .spaces2 // String

  // println(dataEncodedAsJsonConvertedToString)

  // val alreadyJson: Json =
  // json"""
  // {
  //   "name" : "Akshay",
  //   "age" : 27
  // }
  // """

  // val alreadyJson: Json =
  //   json"""$data"""

  // val fromStringDecodeddata: Either[Error, Data] =
  //   // decode[data](alreadyJson) // One way
  //   // parse(alreadyJson).flatMap(Decoder[data].decodeJson) // another way
  //   // alreadyJson.pipe(parse).flatMap(Decoder[data].decodeJson) //using chaining
  //   // alreadyJson //String
  //   // .pipe(parse) //Either[ParsingFailure, Json]
  //   // .flatMap(_.as[data]) //using chaining Either[Error, data]

  //   // alreadyJson.pipe(decode[data])  // Cleanest and clearest
  //   alreadyJson.pipe(_.as[Data])

  println("─" * 100)

  // println(fromStringDecodeddata)

  println("─" * 100)

  println("hello world")

  println("─" * 100)

  val fromResourceDecodedData: Either[Throwable, Data] =
    ReadJsonFrom
      .resourceInto[Data](resourceName = "data.json")
      .tap(_.bimap(printlnBad, printlnGood))

  // val fromUrlDecodedGitHubResponse: Either[Throwable, Seq[GithubResponse]] =
  //   "https://api.github.com/users/asachdeva/repos"
  //     .pipe(ReadJsonFrom.urlInto[Seq[GithubResponse]])
  //     .tap(_.bimap(printlnBad, _.foreach(printlnGood)))

  println(fromResourceDecodedData)
}
