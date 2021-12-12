import zio.*
import io.getquill.*

import com.typesafe.config.ConfigFactory
import java.io.File

object ConfigService:
  
  type QuillContext = PostgresJdbcContext[SnakeCase]

  val live: ULayer[QuillContext] = ZLayer.succeed { 
    val config = ConfigFactory.load("application.properties").getConfig("ctx")
    .withFallback(ConfigFactory.parseFile(new File(".env")))

    new PostgresJdbcContext(SnakeCase, config)
  } 
