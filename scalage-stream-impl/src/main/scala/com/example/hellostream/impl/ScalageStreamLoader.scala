package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.hellostream.api.ScalageStreamService
import com.example.hello.api.ScalageService
import com.softwaremill.macwire._

class ScalageStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new ScalageStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new ScalageStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[ScalageStreamService])
}

abstract class ScalageStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[ScalageStreamService](wire[ScalageStreamServiceImpl])

  // Bind the ScalageService client
  lazy val scalageService = serviceClient.implement[ScalageService]
}
