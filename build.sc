import mill._, scalalib._

object ziotemplate extends ScalaModule {
  val zioVersion = "2.0.0-M6-2"
  def scalaVersion = "3.1.0"

  def ivyDeps = Agg(
    ivy"dev.zio::zio::${zioVersion}",
    ivy"io.getquill::quill-jdbc-zio:3.7.2.Beta1.4",
    ivy"org.slf4j:slf4j-simple:1.7.32",
    ivy"org.postgresql:postgresql:42.2.18",
  )

  val a = 1
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
