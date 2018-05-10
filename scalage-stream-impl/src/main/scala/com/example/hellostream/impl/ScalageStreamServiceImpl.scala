package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.hellostream.api.ScalageStreamService
import com.example.hello.api.ScalageService

import scala.concurrent.Future

/**
  * Implementation of the ScalageStreamService.
  */
class ScalageStreamServiceImpl(scalageService: ScalageService) extends ScalageStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(scalageService.hello(_).invoke()))
  }
}
