import zio.*

object ZIOTemplate extends ZIOAppDefault:

  def exec = for {
   // _ <- InitDB.run
    persons <- Persons.findPeople
  } yield persons.mkString("\n")

  def run: ZIO[Environment & ZEnv & ZIOAppArgs, Any, Any] = 
    exec.debug.provide(Quill.live >>> Persons.live)
    // should work with exec.provide(Quill.live, Persons.live)
    // but causes the following compile error
    // Found:    (zio.ULayer[Quill], zio.URLayer[Quill, Persons])
    // Required: zio.ZLayer[ZIOTemplate.Environment & zio.ZEnv & zio.ZIOAppArgs, Any, Any]
