ThisBuild / resolvers ++= Seq(
  "Confluent Repository".at("https://packages.confluent.io/maven")
)

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.7"

val avroVersion = "1.11.3"
val confluentRegistry = "5.3.0"
val flinkVersion = "1.17.0"

val rootDependencies = Seq(
  "org.apache.flink" %% "flink-scala" % flinkVersion,
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion,
  "org.apache.flink" % "flink-runtime-web" % flinkVersion,
  "org.apache.flink" % "flink-table" % flinkVersion,
  "org.apache.flink" % "flink-table-common" % flinkVersion,
  "org.apache.flink" %% "flink-table-planner" % flinkVersion,
  "org.apache.flink" % "flink-table-api-java-bridge" % flinkVersion,
  "org.apache.flink" %% "flink-table-api-scala-bridge" % flinkVersion,
  ("org.apache.flink" % "flink-connector-kafka" % flinkVersion),
  "org.apache.flink" % "flink-clients" % flinkVersion,
  "org.apache.avro" % "avro" % avroVersion,
  "org.apache.flink" % "flink-connector-elasticsearch6" % "3.0.1-1.17",
  ("io.confluent" % "kafka-avro-serializer" % confluentRegistry)
    .exclude("io.confluent", "kafka-schema-registry-client"),
  ("org.apache.flink" % "flink-avro-confluent-registry" % flinkVersion)
    .exclude("io.confluent", "kafka-schema-registry-client")
    .exclude("io.confluent", "kafka-avro-serializer"),
  "io.confluent" % "kafka-schema-registry-client" % confluentRegistry,
  "org.apache.logging.log4j" % "log4j-core" % "2.23.1"
)

lazy val root = (project in file("."))
  .aggregate(common, transactionsProducer, transactionsMlFeatures)
  .settings(
    name := "RemoteBankTechAssignment",
    customMergeStrategy
  )

lazy val common = (project in file("modules/common"))
  // Setup sbt-avro
  .settings(javaSource in AvroConfig := baseDirectory.value / "src/main/avro_generated")
  .settings(Defaults.itSettings)
  .settings(
    libraryDependencies := rootDependencies
  )


lazy val transactionsProducer = (project in file("modules/transactionsProducer"))
  .dependsOn(common)
  .settings(Defaults.itSettings)
  .settings(name := "transactions-producer")
  .settings(version := "1.0.0")
  .settings(
    libraryDependencies := rootDependencies)

lazy val transactionsMlFeatures = (project in file("modules/transactionsMlFeatures"))
  .dependsOn(common)
  .settings(Defaults.itSettings)
  .settings(name := "transactions-ml-features")
  .settings(version := "1.0.0")
  .settings(
    libraryDependencies := rootDependencies
  )

scalacOptions += "-Ypartial-unification"

lazy val customMergeStrategy = assemblyMergeStrategy in assembly := {
  case "module-info.class"                  => MergeStrategy.discard
  case PathList("META-INF", _*)             => MergeStrategy.discard
  case PathList("io", "confluent", xs @ _*) => MergeStrategy.last
  case PathList("org", "slf4j", xs @ _*)    => MergeStrategy.last
  // from jaskson lib and flink-metrics, tested won't cause problem
  case PathList("org", "objectweb", xs @ _*) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

// exclude Scala library from assembly
assembly / assemblyOption := (assembly / assemblyOption).value.copy(includeScala = false)

