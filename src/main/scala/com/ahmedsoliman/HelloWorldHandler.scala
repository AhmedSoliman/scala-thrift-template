package com.ahmedsoliman

import com.ahmedsoliman.rpc.HelloWorld
import com.typesafe.config.Config
import com.twitter.util._

class HelloWorldHandler(config: Config) extends HelloWorld.FutureIface {
    override def getHelloWorld(): Future[String] = 
      Future.value("Hello World")
      
     
    def close = println("Handler is Closing")
}