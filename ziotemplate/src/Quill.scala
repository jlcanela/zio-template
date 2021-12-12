import zio.*
import io.getquill._
import com.typesafe.config.ConfigFactory
import java.io.File

trait Quill:

  def ctx: IO[Nothing, PostgresJdbcContext[SnakeCase]]

object Quill extends Accessible[Quill] :
  
  def ctx = this.apply(_.ctx) 
  val live = ZLayer.succeed(QuillPostgres())

  
case class QuillPostgres() extends Quill:
    
  def ctx = ZIO.succeed {
    val config = ConfigFactory.load("application.properties").getConfig("ctx").withFallback(ConfigFactory.parseFile(new File(".env")))
    // SnakeCase turns firstName -> first_name
    new PostgresJdbcContext(SnakeCase, config)
  }

