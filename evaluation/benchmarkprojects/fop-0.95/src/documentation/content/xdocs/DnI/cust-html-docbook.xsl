<?xml version="1.0" encoding="utf-8"?>

<!--
  * Licensed to the Apache Software Foundation (ASF) under one or more
  * contributor license agreements.  See the NOTICE file distributed with
  * this work for additional information regarding copyright ownership.
  * The ASF licenses this file to You under the Apache License, Version 2.0
  * (the "License"); you may not use this file except in compliance with
  * the License.  You may obtain a copy of the License at
  * 
  *      http://www.apache.org/licenses/LICENSE-2.0
  * 
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  -->

<!-- $Id: cust-html-docbook.xsl 426576 2006-07-28 15:44:37Z jeremias $ -->

<xsl:stylesheet
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">

  <xsl:import
    href="http://docbook.sourceforge.net/release/xsl/current/html/chunk.xsl"/>
  <!-- Use this import if you do not want chunks -->
  <!-- 
  <xsl:import
    href="http://docbook.sourceforge.net/release/xsl/current/html/docbook.xsl"/> 
-->

  <xsl:param name="chunk.section.depth" select="2"/>
  <xsl:param name="section.autolabel" select="1"/>
  <xsl:param name="base.dir" select="'DnI-html/'"/>

</xsl:stylesheet>
