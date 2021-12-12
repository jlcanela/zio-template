import io.getquill.*

object Queries:
  inline def findPersonWithName = quote {
   (name: String) => query[PersonService.Person].filter(p => p.firstName == name)
  }
  