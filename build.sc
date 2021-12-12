import mill._, scalalib._, scalafmt._

object ziotemplate extends ScalaModule with ScalafmtModule{
  val zioVersion = "2.0.0-M6-2"
  def scalaVersion = "3.1.0"

  def ivyDeps = Agg(
    ivy"dev.zio::zio::${zioVersion}",
    ivy"io.getquill::quill-jdbc:3.10.0.Beta1.6",
    ivy"org.slf4j:slf4j-simple:1.7.32",
    ivy"org.postgresql:postgresql:42.2.18",
  )

  override def mainClass = Some("ZIOTemplate")

  /*
  object test extends Tests {
    def ivyDeps = Agg(
      ivy"org.scalactic::scalactic:3.1.1",
      ivy"org.scalatest::scalatest:3.1.1"
    )
    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }
  */

}
