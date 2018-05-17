package house.general

import java.util.concurrent.Executors

import akka.actor.{Actor, ActorContext}
import akka.util.Timeout
import spray.http.{HttpEntity, HttpResponse, MediaTypes, Timedout}
import spray.routing.HttpService

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import spray.routing.Directives._

class Api extends Actor with HttpService {

  implicit val timeout: Timeout = 1.second

  //TODO should use rate limiter atleast to defend against scripts ?
//  implicit val ec = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(10))

  val collectorRoute =
    path("set" / Segment / Segment) { (key, value) =>
      get {
        complete(HttpResponse(status = 200, entity = HttpEntity(s"hello $key $value")))
      }
    } ~
      path("version") {
        get {
          respondWithMediaType(MediaTypes.`text/plain`) {
            complete(HttpResponse(status = 200, entity = HttpEntity("hello")))
          }
        }
      }

  // For the actor 'asks'
  override def actorRefFactory: ActorContext = context

  def handleTimeouts: Receive = {
    case Timedout(_) =>
      //TODO log timeout
      sender ! complete(HttpResponse(200))
  }

  override def postStop() : Unit = {
    context.system.terminate()
  }

  // Message loop for the Spray service.
  def receive = handleTimeouts orElse runRoute(collectorRoute)
}
