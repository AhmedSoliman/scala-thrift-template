organization := "com.ahmedsoliman"

name := "scalatemplate"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq("org.apache.thrift" % "libthrift" % "0.9.0",
			   "org.neo4j" % "neo4j" % "1.8.1",
			   "org.neo4j" % "neo4j-cypher" % "1.8.1",
			   "ch.qos.logback" % "logback-classic" % "1.0.9",
			   "org.specs2" %% "specs2" % "1.12.3" % "test",
			   "junit" % "junit" % "4.7",
		           "org.specs2" %% "specs2-scalaz-core" % "6.0.1" % "test",
			   "com.twitter" %% "util-core" % "6.0.5",
			   "com.twitter" %% "finagle-thrift" % "6.0.5",
			   "joda-time" % "joda-time" % "2.1",
			    "commons-io" % "commons-io" % "2.4",
			   "org.joda" % "joda-convert" % "1.2",
			   "com.github.scopt" %% "scopt" % "2.1.0",
		  	   "com.typesafe" % "config" % "1.0.0")


testOptions in Test += Tests.Argument("junitxml", "console")

logBuffered := false
