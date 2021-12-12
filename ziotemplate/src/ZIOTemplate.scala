import zio.*

object ZIOTemplate extends ZIOAppDefault:

  def exec = for {
    persons <- Persons.findPeople
    _ <- Console.printLine(s"Persons Loaded:\n${persons.mkString("\n")}")
  } yield ()

  def run: ZIO[Environment with ZEnv with ZIOAppArgs, Any, Any] = 
    exec.provideCustomLayer(Quill.live >>> Persons.live)