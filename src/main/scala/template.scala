package com.ahmedsoliman

import org.apache.thrift.transport.TServerSocket
import org.apache.thrift.server.TSimpleServer
import org.apache.thrift.server.TServer.Args
import org.apache.thrift.protocol.TBinaryProtocol
import java.net.InetSocketAddress
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.thrift.ThriftServerFramedCodec
import com.twitter.util.Duration
import com.typesafe.config.ConfigFactory
import java.io.File
import scopt.immutable.OptionParser
import com.ahmedsoliman.rpc.HelloWorld

case class CommandLineOptions(configName: String = "./conf/template.conf")

object BonderServer {
  def main(args: Array[String]): Unit = {
    val parser = new OptionParser[CommandLineOptions]("template") {
      def options = Seq(
        opt("c", "config", "ConfigFile", "Path to the configuration file") {
          (v: String, c: CommandLineOptions) => c.copy(configName = v)
        })
    }
    parser.parse(args, CommandLineOptions()) map { config =>

      println(Console.GREEN + "Template" + Console.WHITE +" is Booting...")
      println("Using Config file: %s".format(config.configName))
      val configFile = new File(config.configName)
      if (configFile.exists()) {
        val c = ConfigFactory.parseFile(configFile)
        val host = c.getString("server.host")
        val port = c.getInt("server.port")
        val protocol = new TBinaryProtocol.Factory()
        val handler = new HelloWorldHandler(c)
        val serverService = new HelloWorld.FinagledService(handler, protocol)
        val address = new InetSocketAddress(host, port)
        print("Template server listening on <" + host + ":" + port + ">...")
        var builder = ServerBuilder()
          .codec(ThriftServerFramedCodec())
          .name("binary_service")
          .bindTo(address)
          .build(serverService)
        //loop forever
        print(Console.GREEN + "[READY]\n" + Console.WHITE)
        println()
        println("Press Ctrl+D to exit")
        while (Console.in.read.toInt != 4) {}
        println("Bye.")
        builder.close(Duration.fromMilliseconds(500))
        handler.close
      } else {
        Console.err.println("Cannot find file %s".format(config.configName))
      }
    } getOrElse {
      println("Invalid argument passed!")
    }

  }

}