
# Multilingual indexing 
We identify the language of several fields in the metadata and we index them in the language in which the text is written. 
Query is searched simultaneously in the different languages and the results merged in a single response.

The actual request handler is /multilingsearch
e.g. for "Auschwitz" the query is
http://localhost:8983/solr/portal/multilingsearch?q=Auschwitz&wt=json&indent=true


## TODOs
- Complete the schema.xml: copy the rest of the fields in the text field.
- Improve the resolution of German diacritics (partially improved already).
- Discuss how to use that together with suggest, facetting, etc. integrating the request handler
- In the future: implement expansion of access points (multilingual and semantic). 




## Libraries
Following libraries must be copied in the ...solr/lib/ directory

a) For Unicode normalization
* icu4j-49.1.jar --> Delivered with the Solr distribution in solr-4.X.X/contrib/analysis-extras/lib
* lucene-analyzers-icu-4.5.0.jar --> Delivered with the Solr distribution in solr-4.X.X/contrib/analysis-extras/lucene-libs

b) For Polish text analyisis
* lucene-analyzers-morfologik-4.5.0.jar --> Delivered with the Solr distribution in solr-4.X.X/contrib/analysis-extras/lucene-libs
* morfologik-fsa-1.7.1.jar --> Delivered with the Solr distribution in solr-4.X.X/contrib/analysis-extras/lib
* morfologik-polish-1.7.1.jar --> Delivered with the Solr distribution in solr-4.X.X/contrib/analysis-extras/lib
* morfologik-stemming-1.7.1.jar --> Delivered with the Solr distribution in solr-4.X.X/contrib/analysis-extras/lib

   Together with the libraries, the list of stopwords stopwords_pl.txt must be included in portal/conf/lang folder

c) For language identification 
* langdetect-1.1-20120112.jar --> Delivered with the Solr distribution in solr-4.X.X/contrib/langid/lib 
* jsonic-1.2.7.jar --> Delivered with the Solr distribution in solr-4.X.X/contrib/langid/lib  
* solr-langid-4.5.0.jar--> Delivered with the Solr distribution in solr-4.X.X/contrib/distr

An alternative might be to add the following lines to `/solr-4.X.X/example/solr/collection1/conf/solrconfig.xml`

```
<lib dir="../../../contrib/analysis-extras/lib/" />
<lib dir="../../../contrib/analysis-extras/lucene-libs/" />
<lib dir="../../../contrib/langid/lib/" />
<lib dir="../../../dist/" /> 
```

## Chronology of changes

24.10.2013
- schema.xml has been updated to remove diacritics from query and indexes using ICUFoldingFilterFactory.

29.10.2014
- schema.xml and solarconf.xml have been modified to allow multilingual indexing and multilingual simultaneous querying.
- polish list of stopwords have been added
- modification in code of the indexer in file JSONConversion.java to change the output and allow language identification in the desired fields
