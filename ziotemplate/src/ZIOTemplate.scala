import zio.*

object ZIOTemplate extends ZIOAppDefault:

  def exec = for {
    _ <- InitDB.run
    persons <- Persons.findPeople
  } yield persons.mkString("\n")

  def run: ZIO[Environment & ZEnv & ZIOAppArgs, Any, Any] = 
    exec.debug.inject(Quill.live, InitDB.live, Persons.live)
