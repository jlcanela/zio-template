import zio.*
import io.getquill.*

trait PersonService:
  def findPeople: IO[Nothing, List[PersonService.Person]]

object PersonService extends Accessible[PersonService]:

  case class Person(firstName: String, lastName: String, age: Int)

  def findPeople = this.apply(_.findPeople)

  val live: URLayer[ConfigService.QuillContext, PersonService] =
    PersonLive.apply.toLayer

case class PersonLive(ctx: ConfigService.QuillContext) extends PersonService:

  import ctx._

  def findPeople(name: String) = ZIO.succeed {
    run {
       Queries.findPersonWithName(lift(name))
    }
  }


  def findPeople: IO[Nothing, List[PersonService.Person]] = ZIO.succeed {

    val named = "Joe"
    inline def somePeople = quote {
       Queries.findPersonWithName(lift(named))
    }

    run(somePeople)

  }
