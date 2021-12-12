import mill._, scalalib._

object ziotemplate extends ScalaModule {
  val zioVersion = "2.0.0-M6-2"
  def scalaVersion = "3.1.0"

  def ivyDeps = Agg(
    ivy"dev.zio::zio::${zioVersion}"
  )

  // override def mainClass = Some("MyApp")

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
