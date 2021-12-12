import zio.*
import io.getquill._

trait Persons:
  def findPeople: IO[Nothing, List[Persons.Person]]

end Persons

object Persons extends Accessible[Persons]:

  case class Person(firstName: String, lastName: String, age: Int)
  
  def findPeople = this.apply(_.findPeople)

  val live: URLayer[Quill, Persons] = PersonsImpl.apply.toLayer
  
case class PersonsImpl(quill: Quill) extends Persons:

  def findPeople: IO[Nothing, List[Persons.Person]] = for {
    ctx <- quill.ctx
  } yield {
    import ctx._

    val named = "Joe"
    inline def somePeople = quote {
      query[Persons.Person].filter(p => p.firstName == lift(named))
    }
     run(somePeople)

  }
    
