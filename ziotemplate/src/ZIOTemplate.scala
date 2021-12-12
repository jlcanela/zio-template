import zio.*

object ZIOTemplate extends ZIOAppDefault:

  def exec = for {
    _ <- InitDB.run
    persons <- PersonService.findPeople
  } yield persons.mkString("\n")

  def run: ZIO[Environment & ZEnv & ZIOAppArgs, Any, Any] = 
    exec.debug.inject(ConfigService.live, InitDB.live, PersonService.live)
