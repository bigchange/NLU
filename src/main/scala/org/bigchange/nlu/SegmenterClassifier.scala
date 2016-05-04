package org.bigchange.nlu

import java.util.Properties

import edu.stanford.nlp.ie.crf.CRFClassifier

/**
  * Created by C.J.YOU on 2016/5/4.
  */
object SegmenterClassifier {

  val basedir = "src/main/resources/data"
  // val basedir = System.getProperty("SegDemo", "data"); // 获取data的目录路径
  def main(args: Array[String]) {
    val props = new Properties()
    if (args.length > 0) {
      props.setProperty("testFile", args(0))
    }
    props.setProperty("serDictionary",basedir + "/dict-chris6.ser.gz")
    props.setProperty("inputEncoding", "UTF-8")
    props.setProperty("sighanPostProcessing", "true")

    val segmenter = new CRFClassifier(props)
    segmenter.loadClassifierNoExceptions(basedir + "/ctb.gz",props)
    segmenter.flags.setProperties(props)
    for (filename <- args) {
      // segmenter.classifyAndWriteAnswers(filename)
      val sample = "我住在中国。"
      val segmented = segmenter.segmentString(sample).toArray
      for(item <- segmented){
        println(item)
      }

    }

  }

}
