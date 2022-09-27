
      package de.allianz.Groovy6

      def lib = library identifier: 'BizDevOps_JSL@develop', retriever: modernSCM(
      [\$class: 'GitSCMSource',
        remote: 'https://github.developer.allianz.io/JEQP/BizDevOps-JSL.git',
        credentialsId: 'TOKEN'])

      def jslMaven      = lib.de.allianz.bdo.pipeline.JSLMaven.new()
      def jslGradle     = lib.de.allianz.bdo.pipeline.JSLGradle.new()
      def jslNexus      = lib.de.allianz.bdo.pipeline.JSLNexus.new()
      def jslSonarqube  = lib.de.allianz.bdo.pipeline.JSLSonarqube.new()

      def build() {
        jslGradle.build()
      }

      def componentTest {
        jslGradle.testunit("component") 
      }

      def integrationTest {
        jslGradle.testunit("integration")
      }

      def uatTest {
        jslGradle.testunit("uat")
      }

      def acceptanceTest {
        jslGradle.testunit("acceptance")
      }

      def publishArtifacts() {
        jslNexus.push()
      }
    