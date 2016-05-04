package org.bigchange.nlu

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations._
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree

/**
  * Created by C.J.YOU on 2016/4/20.
  */
object StanfordCoreNLP {

  def main(args: Array[String]) {
    // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
    val props = new Properties()
    // StanfordCoreNLP的各个组件（annotator）按“tokenize（分词）, ssplit（断句）, pos（词性标注）, lemma（词元化）, ner（命名实体识别）, parse（语法分析）, dcoref（同义词分辨）”顺序进行处理。
    props.setProperty("annotators", "tokenize, ssplit,pos,ner,lemma,parse,dcoref")
    val pipeline = new StanfordCoreNLP(props)
    // read some text in the text variable
    val textCN = "约翰去了中国，他参观了北京。"
    val textEN = "John Doe went to China. There, he visited Beijing."
    // create an empty Annotation just with the given text
    val doc= new Annotation(textCN)
    // run all Annotators on this text
    pipeline.annotate(doc)

    // these are all the sentences in this document
    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
    val sentences = doc.get(classOf[SentencesAnnotation]) // reference : http://stanfordnlp.github.io/CoreNLP/pos.html
    val iterator = sentences.iterator()
    while(iterator.hasNext){
      // traversing the words in the current sentence
      // a CoreLabel is a CoreMap with additional token-specific methods
      val tokens =  iterator.next().get(classOf[TokensAnnotation])
      val tokenIterator = tokens.iterator()
      while(tokenIterator.hasNext){
        val token = tokenIterator.next()
        // this is the text of the token
        val word = token.get(classOf[TextAnnotation])
        // this is the POS tag of the token
        val pos = token.get(classOf[PartOfSpeechAnnotation])
        // this is the NER label of the token
        val ne = token.get(classOf[NamedEntityTagAnnotation])
        val lemma = token.get(classOf[LemmaAnnotation])
        // this is the sentiment label of the token
        val sentiment = token.get(classOf[SentimentCoreAnnotations.SentimentAnnotatedTree])
        println(word+"\t"+pos+"\t"+lemma+"\t"+ne+"\t"+sentiment)
      }
    }


  }

}
