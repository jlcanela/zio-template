import zio.*
import io.getquill.*

import com.typesafe.config.ConfigFactory
import java.io.File

trait Quill:

  def ctx: IO[Nothing, PostgresJdbcContext[SnakeCase]]

object Quill extends Accessible[Quill] :
  
  def ctx = this.apply(_.ctx) 
  val live: ULayer[Quill] = ZLayer.succeed { 
    val config = ConfigFactory.load("application.properties").getConfig("ctx")
    .withFallback(ConfigFactory.parseFile(new File(".env")))

    // SnakeCase turns firstName -> first_name
    val context: PostgresJdbcContext[SnakeCase] = new PostgresJdbcContext(SnakeCase, config)
    QuillPostgres(context)
  } 

case class QuillPostgres(context: PostgresJdbcContext[SnakeCase]) extends Quill:
    
  def ctx = ZIO.succeed(context)

